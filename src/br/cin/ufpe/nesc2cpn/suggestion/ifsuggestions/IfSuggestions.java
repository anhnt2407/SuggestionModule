package br.cin.ufpe.nesc2cpn.suggestion.ifsuggestions;

import br.cin.ufpe.nesc2cpn.nescModule.instructions.IfElse;
import br.cin.ufpe.nesc2cpn.suggestion.Suggestion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davi
 */
public class IfSuggestions
{
    private Suggestion sug;
    
    public IfSuggestions()
    {
        sug = new IfOrderSuggestions();
    }

    public List<String> suggestions( IfElse ifElse )
    {
        List <String> listBasic = new ArrayList<String>();

        if ( sug.identify( ifElse ) )
        {
            boolean result = sug.analyse( ifElse );

            if ( result )
            {
                System.out.println( "Tem sugestão: "  + ifElse.getLineNumber() + " " +  result );
                listBasic.add( sug.getSuggestion() );
            }
        }
        
        return listBasic;
    }
}
