/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cin.ufpe.nesc2cpn.suggestion.basicsuggestions;

import br.cin.ufpe.nesc2cpn.nescModule.instructions.Assign;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Instruction;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Operation;
import br.cin.ufpe.nesc2cpn.suggestion.Suggestion;

/**
 *
 * @author davi
 */
public class DivisionSuggestions implements Suggestion<Assign>
{
    private Assign assign;

    public boolean identify(Instruction ins) {
        return ins instanceof Assign;
    }

    public boolean analyse(Assign instruction) {
        
        assign = instruction;

        if( !(assign.getValue() instanceof Operation) )
        {
            return false;
        }
       
        
        if(assign.getValue().toString().contains("/")){
            //System.out.println(assign.getValue().toString());
            return true;
        }
        

        
        return false;
    }

    public String getSuggestion() {
        return "Warning: Replace the division by multiplication, e.g replace \"a / 2\" by \"a * 0.5\".";
    }
    
}
