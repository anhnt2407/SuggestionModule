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
public class ParenthesisSuggestion implements Suggestion<Assign>{
    
    private Assign assign;
    
    public ParenthesisSuggestion(){
        
    }

    public boolean identify(Instruction ins) {
        return ins instanceof Assign;
    }
    public boolean analyseOperation(Operation instruction) {
        boolean leftRight = false;

        
        
        if("parentese".equals(instruction.getRight().getType()) || "parentese".equals(instruction.getLeft().getType())){
  
            
            return true;
        }
        
        if ("operation".equals(instruction.getLeft().getType())) {
            
            Operation opLeft = (Operation) instruction.getLeft();

        
            
            leftRight = analyseOperation(opLeft);

        }
        
        if("operation".equals(instruction.getRight().getType())){
            
            Operation opRight = (Operation) instruction.getRight();
            
            
            leftRight = analyseOperation(opRight);
            
            

        }
        
        
     
        
        return leftRight;
    
    }
    

    public boolean analyse(Assign instruction) {
        boolean leftRight = false;
        assign = instruction;
        
        if( !(assign.getValue() instanceof Operation) )
        {   
            
            return false;
        }
        
        Operation op = (Operation) assign.getValue();
        
        if("parentese".equals(op.getRight().getType()) || "parentese".equals(op.getLeft().getType()) ){
            
           
            return true;
        }
        
        
        
        if ("operation".equals(op.getLeft().getType())) {
            
            Operation opLeft = (Operation) op.getLeft();

           
            
            leftRight = analyseOperation(opLeft);


        }
        
        if("operation".equals(op.getRight().getType())){
            
            Operation opRight = (Operation) op.getRight();
            
            
            leftRight = analyseOperation(opRight);
            
            

        }
        
        return leftRight;
    }

    public String getSuggestion() {
        int i = assign.getValue().toString().lastIndexOf("(");
        int f = assign.getValue().toString().lastIndexOf(")");
        String vst = assign.getValue().toString().substring(i+1, f);
        
        return("Warning: Replace the parenthesis by assignment, e.g replace ( " + vst + " ) by variable = " + vst);
    }
    
}
