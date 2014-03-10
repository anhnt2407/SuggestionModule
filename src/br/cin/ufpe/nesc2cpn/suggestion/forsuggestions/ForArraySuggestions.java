/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cin.ufpe.nesc2cpn.suggestion.forsuggestions;

import br.cin.ufpe.nesc2cpn.nescModule.ProjectDate;
import br.cin.ufpe.nesc2cpn.nescModule.creator.ForCreator;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Assign;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.For;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Instruction;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Operation;
import br.cin.ufpe.nesc2cpn.suggestion.Suggestion;
import java.util.Iterator;

/**
 *
 * @author davi
 */

    
public class ForArraySuggestions implements Suggestion<For> {

    private For forArray;
    private int numInstructions;
    private static int MAX_INSTRUCTION = 20;

    public ForArraySuggestions() {
    }

    public boolean identify(Instruction ins) {
        return ins instanceof For;
    }

    public boolean analyse(For instruction) {

        forArray = instruction;

        if (!(forArray.getPart01() instanceof Instruction)) {
            return false;
        }
        for (Instruction instruc : forArray.getInstructions()) {
            
            if(!(instruc instanceof Assign)){
                return false;
            }
            Operation op = (Operation) forArray.getPart02();
            Assign inst = (Assign) instruc;
            numInstructions = Integer.parseInt(op.getRight().toString()) * forArray.getInstructions().size();
            
            if(inst.isArray() && (forArray.getInstructions().size() == 1) && (numInstructions < MAX_INSTRUCTION)){                
       
                return true;
            }
            
            System.out.println(inst.getType());
        }



        return false;
    }

    public String getSuggestion() {


        return "Inicializer the Array " + numInstructions + " turn";
    }

    public static void main(String[] args) {

        ProjectDate.getInstance().putVariableType("i", "int");
        ProjectDate.getInstance().putVariableType("x", "int");
        ProjectDate.getInstance().putVariableType("array", "int");
        //String assignTxt = "mudar = mudar + 388;";
        String assignTxt = "for(i = 0; i < 10; i++){array[i] = x;}";

        ForCreator creator = new ForCreator();
        For forarray = (For) creator.convertTo(assignTxt);

        Suggestion sug = new ForArraySuggestions();

        if (sug.identify(forarray)) {
            boolean result = sug.analyse(forarray);

            if (result) {

                System.out.println("tem sugestão: " + result);
                System.out.println("sugestão na linha "
                        + forarray.getLineNumber()
                        + ": " + sug.getSuggestion());
            } else {
                System.out.println("tem sugestão: " + result);

            }
        }
    }
}
