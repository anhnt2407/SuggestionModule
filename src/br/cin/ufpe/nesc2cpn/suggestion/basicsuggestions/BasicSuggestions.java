package br.cin.ufpe.nesc2cpn.suggestion.basicsuggestions;

import br.cin.ufpe.nesc2cpn.nescModule.instructions.Assign;
import br.cin.ufpe.nesc2cpn.suggestion.Suggestion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davi
 */
public class BasicSuggestions
{
    private List<Suggestion> suggestionList;
    
    public BasicSuggestions()
    {
        suggestionList = new ArrayList<Suggestion>();
        
        suggestionList.add( new CastImplicitSuggestion()   );
        suggestionList.add( new AssignPlusXSuggestion()    );
        suggestionList.add( new AssignPlusPlusSuggestion() );
        suggestionList.add( new AssignLessXSuggestion()    );
        suggestionList.add( new AssignLessLessSuggestion() );
        suggestionList.add( new ParenthesisSuggestion()    );
        suggestionList.add( new DivisionSuggestions()      );
    }

    public List<String> suggestions( Assign inst )
    {
        List<String> resultList = new ArrayList<String>();
        
        for( Suggestion<Assign> suggestion : suggestionList )
        {
            if( suggestion.identify( inst ) )
            {
                boolean result = suggestion.analyse( inst );
                
                if ( result )
                {
                    //System.out.println( "Tem sugestão CastImplicitSuggestion: " + inst.getLineNumber() +" " + result );
                    resultList.add( suggestion.getSuggestion() );
                }
            }
        }
        
        return resultList;
    }

}
