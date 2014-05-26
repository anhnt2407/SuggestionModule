package br.cin.ufpe.nesc2cpn.suggestion;

import br.cin.ufpe.nesc2cpn.nescModule.Module;
import br.cin.ufpe.nesc2cpn.nescModule.ProjectFile;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.*;
import br.cin.ufpe.nesc2cpn.suggestion.basicsuggestions.BasicSuggestions;
import br.cin.ufpe.nesc2cpn.suggestion.forsuggestions.ForSuggestions;
import br.cin.ufpe.nesc2cpn.suggestion.ifsuggestions.IfSuggestions;
import java.io.File;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author avld
 */
public class SuggestionMain
{
    private BasicSuggestions basic;
    private IfSuggestions ifs;
    private ForSuggestions fors;
    
    public SuggestionMain()
    {
        basic = new BasicSuggestions();
        ifs   = new IfSuggestions();
        fors  = new ForSuggestions();
    }

    public Map<Integer, List<String>> processSuggestions( String filename ) throws Exception
    {
        Map<Integer, List<String>> mapSuggestion = new HashMap<Integer, List<String>>();

        File arquivo            = new File( filename );
        
        ProjectFile projectFile = new ProjectFile();
        projectFile.addDiretory( ( arquivo.getParent() == null ? "." : arquivo.getParent() ) );
        projectFile.processFile( arquivo.getName() );

        Module module = projectFile.getModuleList().get( 0 );

        for ( Function fun : module.getFunctions() )
        {
            for ( Instruction inst : fun.getInstructions() )
            {
                System.out.println("----------- " 
                                    + inst.getType() 
                                    + " linha " 
                                    + inst.getLineNumber()
                                    + " " + inst.getText() );
                
                if ( inst instanceof Assign )
                {
                    List<String> lista = basic.suggestions( (Assign) inst );
                    
                    if( !mapSuggestion.containsKey( inst.getLineNumber() ) )
                    {
                        mapSuggestion.put( inst.getLineNumber() , lista );
                    }
                    else
                    {
                        mapSuggestion.get( inst.getLineNumber() ).addAll( lista );
                    }
                }
                else if ( inst instanceof IfElse )
                {
                    List<String> lista = ifs.suggestions( (IfElse) inst );
                    
                    if( !mapSuggestion.containsKey( inst.getLineNumber() ) )
                    {
                        mapSuggestion.put( inst.getLineNumber() , lista );
                    }
                    else
                    {
                        mapSuggestion.get( inst.getLineNumber() ).addAll( lista );
                    }
                }
                else if ( inst instanceof For )
                {
                    List<String> lista = fors.suggestions( (For) inst );
                    
                    if( !mapSuggestion.containsKey( inst.getLineNumber() ) )
                    {
                        mapSuggestion.put( inst.getLineNumber() , lista );
                    }
                    else
                    {
                        mapSuggestion.get( inst.getLineNumber() ).addAll( lista );
                    }
                }


            }
        }
        
        System.out.println( " ----------------------- " );
        for( Entry<Integer,List<String>> entry : mapSuggestion.entrySet() )
        {
            System.out.println( "Linha " + entry.getKey() );
            
            for( String valor : entry.getValue() )
            {
                System.out.println( valor );
            }
        }

        return mapSuggestion;
    }

    public static void main(String args[]) throws Exception
    {
        // mudar = mudar + 1;       mudar++;
        // mudar = mudar + 1 + 1;   mudar++;
        // mudar = mudar + 2;       mudar += 2;

        SuggestionMain sugs = new SuggestionMain();
        Map<Integer, List<String>> map = sugs.processSuggestions( "/opt/idea4wsn/1/Blink/BlinkAppC.nc" );
    }
}
