/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cin.ufpe.nesc2cpn.suggestion.ifsuggestions;

import br.cin.ufpe.nesc2cpn.nescModule.instructions.IfElse;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Instruction;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Operation;
import br.cin.ufpe.nesc2cpn.suggestion.Suggestion;

/**
 *
 * @author davi
 */
public class IfOrderSuggestions implements Suggestion<IfElse> {
    private IfElse ifelse;

    public boolean identify(Instruction ins) {
        
        return ins instanceof IfElse;
    }

    public boolean analyse(IfElse instruction) {
        
        ifelse = instruction;
        
        if( !(ifelse.getCondition() instanceof Operation) )
        {
            return false;
        }
        
        ifelse.calculeProbability();
        //System.out.println("///////////////////////////////////");
        //System.out.println(ifelse.getText());
        //System.out.println(ifelse.getElses());
        //ifelse.get
        System.out.println("Probability: "+ifelse.getProbability());
        
        //System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        
        if(ifelse.getProbability()< 1){
            return true;
        }
        
        
        return false;
    }

    public String getSuggestion() {
        return "Altere a ordem dos if";
    }
    
   
    
}
