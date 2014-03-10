package br.cin.ufpe.nesc2cpn.suggestion;

import br.cin.ufpe.nesc2cpn.nescModule.instructions.Instruction;

/**
 *
 * @author avld
 */
public interface Suggestion<type>
{
    public boolean identify(Instruction ins);
    public boolean analyse(type instruction);
    public String getSuggestion();
}
