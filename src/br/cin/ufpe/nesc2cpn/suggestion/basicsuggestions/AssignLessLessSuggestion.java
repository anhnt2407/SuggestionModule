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
public class AssignLessLessSuggestion implements Suggestion<Assign>
{
    private Assign assign;

    public AssignLessLessSuggestion() {
        
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

        if( "-".equals( op.getOperation() ) )
        {
            if( (op.getLeft().toString().equals( assign.getVariableName() ) && "1".equals(op.getRight().toString()))
                    || (op.getRight().toString().equals( assign.getVariableName() ) )&& "1".equals(op.getLeft().toString()))
            {
                return true;
            }
        }

        return false;
    }

    public String getSuggestion() {
        return "Warning: Replace \""+ assign.getValue().toString() +"\" by " +assign.getVariableName() + "--;";
    }
    
    
}
