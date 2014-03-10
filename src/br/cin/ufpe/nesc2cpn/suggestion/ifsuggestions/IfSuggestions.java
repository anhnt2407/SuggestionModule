/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cin.ufpe.nesc2cpn.suggestion.ifsuggestions;

import br.cin.ufpe.nesc2cpn.nescModule.instructions.IfElse;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Instruction;
import br.cin.ufpe.nesc2cpn.suggestion.Suggestion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davi
 */
public class IfSuggestions {

    public IfSuggestions() {
    }



    public List<String> suggestions(Instruction inst) {

        Suggestion sug;
        List <String> listBasic = new ArrayList<String>();


        IfElse ifelse = (IfElse) inst;

        sug = new IfOrderSuggestions();

        //suggestions(sug, ifelse);
        
        if (sug.identify(ifelse)) {
            boolean result = sug.analyse(ifelse);

            if (result) {
                System.out.println("Tem sugestão: "  + inst.getLineNumber() +" "+  result);
                listBasic.add(sug.getSuggestion());

//                System.out.println("tem sugestão1: " + result);
//                System.out.println("sugestão na linha "
//                        + ifelse.getLineNumber()
//                        + ": " + sug.getSuggestion());
            } else {

                System.out.println("tem sugestão: " + result);

            }
        }
        
        return listBasic;



    }
}
