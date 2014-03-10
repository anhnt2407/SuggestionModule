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
public class AssignLessXSuggestion implements Suggestion<Assign> {
    private Assign assign;
    private String value="";

    public AssignLessXSuggestion() {
        
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
            if( op.getLeft().toString().equals( assign.getVariableName() ) && (op.getRight().toString().matches("[0-9]*")))
                    
            {
                if (Integer.parseInt(op.getRight().toString()) > 1){
                    value = op.getRight().toString();
                    return true;
                }
                    
                
            }
            
            if (op.getRight().toString().equals( assign.getVariableName() ) && op.getLeft().toString().matches("[0-9]*"))
            
            {
                if (Integer.parseInt(op.getLeft().toString()) > 1){
                    value = op.getLeft().toString();
                    return true;
                }
                
            }
        }
        
        return false;
    }

    public String getSuggestion() {
        
        return assign.getVariableName() + "-=" + value;
    }

    
    
}
