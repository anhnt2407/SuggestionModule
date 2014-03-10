/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cin.ufpe.nesc2cpn.suggestion.forsuggestions;

import br.cin.ufpe.nesc2cpn.nescModule.instructions.For;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Instruction;
import br.cin.ufpe.nesc2cpn.suggestion.Suggestion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davi
 */
public class ForSuggestions {

    public ForSuggestions() {
    }



    public List<String> suggestions(Instruction inst) {
        Suggestion sug;
        
        List <String> listBasic = new ArrayList<String>();


        For forarray = (For) inst;


        sug = new ForArraySuggestions();

        //suggestions(sug, forarray);
        
        if (sug.identify(forarray)) {
            boolean result = sug.analyse(forarray);

            if (result) {
                System.out.println("Tem sugestão: "   + inst.getLineNumber() +" "+sug.getSuggestion() +" "+  result);
                listBasic.add(sug.getSuggestion());

//                System.out.println("tem sugestão: " + result);
//                System.out.println("sugestão na linha "
//                        + forarray.getLineNumber()
//                        + ": " + sug.getSuggestion());
            } else {
                System.out.println("tem sugestão: " + result);

            }
        }


        sug = new ForNumbersSuggestion();

        //suggestions(sug, forarray);
        
        if (sug.identify(forarray)) {
            boolean result = sug.analyse(forarray);

            if (result) {
                System.out.println("Tem sugestão: "   + inst.getLineNumber() +" "+sug.getSuggestion() +" "+  result);
                listBasic.add(sug.getSuggestion());

//                System.out.println("tem sugestão: " + result);
//                System.out.println("sugestão na linha "
//                        + forarray.getLineNumber()
//                        + ": " + sug.getSuggestion());
            } else {
                System.out.println("tem sugestão: " + result);

            }
        }
        
        return listBasic;




        
    }
}
