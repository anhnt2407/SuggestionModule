package br.cin.ufpe.nesc2cpn.suggestion.forsuggestions;

import br.cin.ufpe.nesc2cpn.nescModule.instructions.For;
import br.cin.ufpe.nesc2cpn.suggestion.Suggestion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davi
 */
public class ForSuggestions
{
    List<Suggestion> suggestionList;
    
    public ForSuggestions()
    {
        suggestionList = new ArrayList<Suggestion>();
        suggestionList.add( new ForArraySuggestions()  );
        suggestionList.add( new ForNumbersSuggestion() );
    }

    public List<String> suggestions( For inst )
    {
        List <String> listBasic = new ArrayList<String>();

        for( Suggestion<For> sug : suggestionList )
        {
            if ( sug.identify( inst ) )
            {
                boolean result = sug.analyse( inst );

                if ( result )
                {
                    listBasic.add( sug.getSuggestion() );
                }
            }
        }
        
        return listBasic;
    }
}
