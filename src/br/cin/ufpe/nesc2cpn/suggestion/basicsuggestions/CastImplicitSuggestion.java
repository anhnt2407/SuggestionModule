/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cin.ufpe.nesc2cpn.suggestion.basicsuggestions;

import br.cin.ufpe.nesc2cpn.nescModule.ProjectDate;
import br.cin.ufpe.nesc2cpn.nescModule.creator.AssignCreator;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Assign;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Instruction;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Operation;
import br.cin.ufpe.nesc2cpn.suggestion.Suggestion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davi
 */
public class CastImplicitSuggestion implements Suggestion<Assign>{
    
    private Assign assign;

    public CastImplicitSuggestion() {
        
    }
    
    

    public boolean identify(Instruction ins) {
        return ins instanceof Assign;
    }

    public boolean analyse(Assign instruction) {
        assign = instruction;
        
        if( !(assign.getValue() instanceof Operation) )
        {   
            
            return false;
        }

        Operation op = (Operation) assign.getValue();
        
       
        
        if (!op.getLeft().toString().matches("[0-9]*") && op.getRight().getType().equals("value") && "value".equals(op.getLeft().getType())) {

            if (!(assign.getVariableType().equals(ProjectDate.getInstance().getVariableType(op.getLeft().toString()))) ) {

                return true;
            }

        }
       
        if (!op.getRight().toString().matches("[0-9]*") && op.getLeft().getType().equals("value") && "value".equals(op.getRight().getType())) {

            if (!(assign.getVariableType().equals(ProjectDate.getInstance().getVariableType(op.getRight().toString()))) ) {

                return true;
            }

        }
      
        
        
        List <String> castMap =new ArrayList<String>();
        for(String as: assign.getValue().toString().replaceAll("[(,),*,--,/,+,;,0-9]", "").split(" ")){
            
            
            if(as.length()> 0){
                castMap.add(ProjectDate.getInstance().getVariableType(as));
            }
        }
           
       for(int i=0;i<castMap.size()-1;i++){
           //System.out.println(castMap.get(i) + " "+castMap.get(i+1));
           if(!castMap.get(i).equals(castMap.get(i+1))){
               
               return true;
           }
       }
        
       
        return false;
    }

    public String getSuggestion() {
        return("Warning: Operation with different types (Cast Implicit)");
    }
    
    public static void main (String [] args){
        
        ProjectDate.getInstance().putVariableType("y", "int");
        ProjectDate.getInstance().putVariableType("x", "double");
        //ProjectDate.getInstance().putVariableType("array", "int");
        //String assignTxt = "mudar = mudar + 388;";
        String assignTxt = "y = y + x;";
        //String assignTxt = "mudar = 1 - mudar;";
        
        AssignCreator creator = new AssignCreator();
        Assign assign = (Assign) creator.convertTo(assignTxt);
        
        System.out.println("sssssssssssssssssss "+assign.getValue().getType());
        
        Operation op = (Operation) assign.getValue();
        System.out.println("op Left: "+op.getLeft().toString());
        System.out.println("type Left: "+op.getLeft().getType());
        
        System.out.println("op Right: "+op.getRight().toString());
        System.out.println("type Right: "+op.getRight().getType());

        // ---------- ANALISO O OBJ
        Suggestion sug = new CastImplicitSuggestion();

        if (sug.identify(assign)) {
            boolean result = sug.analyse(assign);

            if (result) {

                System.out.println("tem sugestão1: " + result);
                System.out.println("sugestão na linha "
                        + assign.getLineNumber()
                        + ": " + sug.getSuggestion());
            } else {
                System.out.println("tem sugestão2: " + result);

            }
        }
        
    }
    
}
