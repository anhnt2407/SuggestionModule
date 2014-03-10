package br.cin.ufpe.nesc2cpn.suggestion;

import br.cin.ufpe.nesc2cpn.nescModule.Module;
import br.cin.ufpe.nesc2cpn.nescModule.ProjectDate;
import br.cin.ufpe.nesc2cpn.nescModule.ProjectFile;
import br.cin.ufpe.nesc2cpn.nescModule.creator.AssignCreator;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.*;
import br.cin.ufpe.nesc2cpn.suggestion.basicsuggestions.BasicSuggestions;
import br.cin.ufpe.nesc2cpn.suggestion.forsuggestions.ForSuggestions;
import br.cin.ufpe.nesc2cpn.suggestion.ifsuggestions.IfOrderSuggestions;
import br.cin.ufpe.nesc2cpn.suggestion.ifsuggestions.IfSuggestions;
import java.io.File;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author avld
 */
public class SuggestionMain {

    public SuggestionMain() {
    }


    public Map processSuggestions(String filename) throws Exception {
        
        Map<Integer, List<String>> mapSuggestion = new HashMap<Integer, List<String>>();

        File arquivo = new File(filename);
        ProjectFile projectFile = new ProjectFile();
        projectFile.addDiretory((arquivo.getParent() == null ? "." : arquivo.getParent()));
        projectFile.processFile(arquivo.getName());

        Module module = projectFile.getModuleList().get(0);


        for (Function fun : module.getFunctions()) {
            //System.out.println(fun.getFunctionName());
            

            for (Instruction inst : fun.getInstructions()) {
                
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX " + inst.getType() + " linha " + inst.getLineNumber()+" "+ inst.getText());
                
                if (inst instanceof Assign) {
                    
                    
                    BasicSuggestions basic = new BasicSuggestions();
                    //basic.suggestions(inst);
                    
                    for(String s : basic.suggestions(inst)){
                        
                        if(mapSuggestion.get(inst.getLineNumber())==null){
//                            mapSuggestion.put(inst.getLineNumber(), basic.suggestions(inst));
//                            break;
                            List <String> l = new ArrayList<String>();
                             l.add(s);
                             mapSuggestion.put(inst.getLineNumber(), l);
                        } else {
                            mapSuggestion.get(inst.getLineNumber()).add(s);
                        }
                        
                                           }
                    
                    
                }
                
                if (inst instanceof IfElse) {
                   
                    
                    IfSuggestions ifs = new IfSuggestions();
                    
                    
                    for(String s : ifs.suggestions(inst)){
                        //mapSuggestion.get(inst.getLineNumber()).add(s);
                        
                        if(mapSuggestion.get(inst.getLineNumber())==null){
//                            mapSuggestion.put(inst.getLineNumber(), ifs.suggestions(inst));
//                            break;
                            List <String> l = new ArrayList<String>();
                             l.add(s);
                             mapSuggestion.put(inst.getLineNumber(), l);
                        } else {
                            mapSuggestion.get(inst.getLineNumber()).add(s);
                        }
                        
                        
                        
                    }
                }
                
                if (inst instanceof For) {
                    
                    
                    ForSuggestions fors = new ForSuggestions();
                    //fors.suggestions(inst);
                    System.out.println("davi "+inst.getLineNumber());
                    for(String s : fors.suggestions(inst)){
                        System.out.println("davi2 "+inst.getLineNumber());
                       
                        
                         if(mapSuggestion.get(inst.getLineNumber())==null){
                             List <String> l = new ArrayList<String>();
                             l.add(s);
                             mapSuggestion.put(inst.getLineNumber(), l);
//                           
                        } else {
                            mapSuggestion.get(inst.getLineNumber()).add(s);
                        }
                    }
                }


            }
        }
         System.out.println("**************************************************************************************");
        for(Entry entry : mapSuggestion.entrySet()){
            System.out.println("Linha "+entry.getKey());
            for(Object valor : (List)entry.getValue()){
                System.out.println(valor.toString());
            }
        }
        



        return mapSuggestion;
    }

    public static void main(String args[]) throws Exception {
        // mudar = mudar + 1;       mudar++;
        // mudar = mudar + 1 + 1;   mudar++;
        // mudar = mudar + 2;       mudar += 2;

        SuggestionMain sugs = new SuggestionMain();
        //sugs.processSuggestions("/opt/tinyos-2.1.1/apps/RadioCountToLeds/RadioCountToLedsAppC.nc");
        sugs.processSuggestions("/home/davi/Downloads/Eclipse/eclipse1PluginYeti/workspaces/SuggestionsNesC/src/SuggestionsAppC.nc");
        /*
         * ProjectDate.getInstance().putVariableType("mudar", "int"); //String
         * assignTxt = "mudar = mudar + 388;"; String assignTxt = "mudar = 1 -
         * mudar;";
         *
         * // ---------- CRIO O OBJ AssignCreator creator = new
         * AssignCreator(); Assign assign = (Assign)
         * creator.convertTo(assignTxt);
         *
         * // ---------- ANALISO O OBJ Suggestion sug = new
         * AssignPlusPlusSuggestion();
         *
         * if (sug.identify(assign)) { boolean result = sug.analyse(assign);
         *
         * if (result) {
         *
         * System.out.println("tem sugestão: " + result);
         * System.out.println("sugestão na linha " + assign.getLineNumber() + ":
         * " + sug.getSuggestion()); } else { System.out.println("tem sugestão:
         * " + result);
         *
         * }
         * }
         *
         * sug = new AssignLessLessSuggestion();
         *
         *
         * if (sug.identify(assign)) { boolean result = sug.analyse(assign);
         *
         * if (result) {
         *
         * System.out.println("tem sugestão: " + result);
         * System.out.println("sugestão na linha " + assign.getLineNumber() + ":
         * " + sug.getSuggestion()); } else { System.out.println("tem sugestão:
         * " + result);
         *
         * }
         *
         * }
         *
         *
         *
         * sug = new AssignPlusXSuggestion();
         *
         *
         * if (sug.identify(assign)) { boolean result = sug.analyse(assign);
         *
         * if (result) {
         *
         * System.out.println("tem sugestão: " + result);
         * System.out.println("sugestão na linha " + assign.getLineNumber() + ":
         * " + sug.getSuggestion()); } else { System.out.println("tem sugestão:
         * " + result);
         *
         * }
         *
         * }
         *
         * sug = new AssignLessXSuggestion();
         *
         *
         * if (sug.identify(assign)) { boolean result = sug.analyse(assign);
         *
         * if (result) {
         *
         * System.out.println("tem sugestão: " + result);
         * System.out.println("sugestão na linha " + assign.getLineNumber() + ":
         * " + sug.getSuggestion()); } else { System.out.println("tem sugestão:
         * " + result);
         *
         * }
         *
         * }
         */


    }
}
