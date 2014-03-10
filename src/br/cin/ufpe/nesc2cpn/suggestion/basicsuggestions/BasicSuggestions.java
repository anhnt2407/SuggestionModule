/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cin.ufpe.nesc2cpn.suggestion.basicsuggestions;

import br.cin.ufpe.nesc2cpn.nescModule.Module;
import br.cin.ufpe.nesc2cpn.nescModule.ProjectFile;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Assign;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Instruction;
import br.cin.ufpe.nesc2cpn.suggestion.Suggestion;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davi
 */
public class BasicSuggestions {

    public BasicSuggestions() {
    }



    public List<String> suggestions(Instruction inst) {

        Suggestion sug;
        List <String> listBasic = new ArrayList<String>();
        




        Assign assign = (Assign) inst;
        
        
        sug = new CastImplicitSuggestion();
                        
        if (sug.identify(assign)) {
            boolean result = sug.analyse(assign);

            if (result) {
                System.out.println("Tem sugestão CastImplicitSuggestion: " +inst.getLineNumber() +" "+ result);
                listBasic.add(sug.getSuggestion());
                
            } else {
                System.out.println("Tem sugestão CastImplicitSuggestion: " + result);

            }
        }
        

        sug = new AssignPlusXSuggestion();

        if (sug.identify(assign)) {
            boolean result = sug.analyse(assign);

            if (result) {
                System.out.println("Tem sugestão AssignPlusXSuggestion: "  +inst.getLineNumber() +" "+  result);
                listBasic.add(sug.getSuggestion());
                
            } else {
                System.out.println("Tem sugestão AssignPlusXSuggestion: "  +  result);

            }
        }

        sug = new AssignPlusPlusSuggestion();

        if (sug.identify(assign)) {
            boolean result = sug.analyse(assign);

            if (result) {
                System.out.println("Tem sugestão AssignPlusPlusSuggestion: "  +inst.getLineNumber() +" "+  result);
                listBasic.add(sug.getSuggestion());
                
            } else {
                System.out.println("Tem sugestão AssignPlusPlusSuggestion: "  +  result);

            }
        }

        sug = new AssignLessXSuggestion();

        if (sug.identify(assign)) {
            boolean result = sug.analyse(assign);

            if (result) {
                System.out.println("Tem sugestão AssignLessXSuggestion: "  +inst.getLineNumber() +" "+  result);
                listBasic.add(sug.getSuggestion());
                
            } else {
                System.out.println("Tem sugestão AssignLessXSuggestion: "  +  result);

            }
        }

        sug = new AssignLessLessSuggestion();

        if (sug.identify(assign)) {
            boolean result = sug.analyse(assign);

            if (result) {
                System.out.println("Tem sugestão AssignLessLessSuggestion: "  + inst.getLineNumber() +" "+ result);
                listBasic.add(sug.getSuggestion());
                
            } else {
                System.out.println("Tem sugestão AssignLessLessSuggestion: "  +  result);

            }
        }
        
        
        sug = new ParenthesisSuggestion();

        if (sug.identify(assign)) {
            
            boolean result = sug.analyse(assign);

            if (result) {
                System.out.println("Tem sugestão ParenthesisSuggestion: "  + inst.getLineNumber() +" "+ result);
                listBasic.add(sug.getSuggestion());
                
            } else {
                System.out.println("Tem sugestão ParenthesisSuggestion: "  +  result);

            }
        }
        
        
        sug = new DivisionSuggestions();

        if (sug.identify(assign)) {
            boolean result = sug.analyse(assign);

            if (result) {
                System.out.println("Tem sugestão DivisionSuggestions: "  +inst.getLineNumber() +" "+  result);
                listBasic.add(sug.getSuggestion());
                
            } else {
                System.out.println("Tem sugestão DivisionSuggestions: "  +  result);

            }
        }

//                }
//
//
//
//            }
        
        return listBasic;
        
    }

    public static void main(String[] args) throws Exception {



        BasicSuggestions bs = new BasicSuggestions();
        String filename = "/opt/tinyos-2.1.1/apps/RadioCountToLeds/RadioCountToLedsAppC.nc";

        File arquivo = new File(filename);


        ProjectFile projectFile = new ProjectFile();
        projectFile.addDiretory((arquivo.getParent() == null ? "." : arquivo.getParent()));
        projectFile.processFile(arquivo.getName());

        Module module = projectFile.getModuleList().get(0);


        //bs.suggestions(module);

    }
}
