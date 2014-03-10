/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cin.ufpe.nesc2cpn.suggestion;

import br.cin.ufpe.nesc2cpn.Nesc2CpnMain;
import br.cin.ufpe.nesc2cpn.Nesc2CpnResult;
import br.cin.ufpe.nesc2cpn.nescModule.Interface;
import br.cin.ufpe.nesc2cpn.nescModule.Module;
import br.cin.ufpe.nesc2cpn.nescModule.ProjectFile;
import br.cin.ufpe.nesc2cpn.nescModule.instructions.Function;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author davi
 */
public class CalculeEnergyAll  {
    
    public Map<Integer, List<String>> listMarker;
    
    public CalculeEnergyAll(){
        listMarker = new HashMap<Integer, List<String>>();
    }
    
    public static void main (String args[])throws Exception{
        String filename="/opt/tinyos-2.1.1/apps/Blink/BlinkAppC.nc";
        String dir="/opt/tinyos-2.1.1/apps/Blink/";
        List<String> list;
        File arquivo = new File(filename);
        
        CalculeEnergyAll calc = new CalculeEnergyAll();

        System.out.println("file ..: " + filename);
        System.out.println("diretory ..: " + (arquivo.getParent() == null ? "." : arquivo.getParent()));

        ProjectFile projectFile = new ProjectFile();
        projectFile.addDiretory((arquivo.getParent() == null ? "." : arquivo.getParent()));
        projectFile.processFile(arquivo.getName());

        Module module = projectFile.getModuleList().get(0);
        //System.setProperty("nesc2cpn.output",dir);
        //System.out.println( ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>nesc2cpn.output: " + System.getProperty( "nesc2cpn.output" ) );
        for (Entry<String, Interface> entry : module.getInterfaceUses().entrySet()) {
            
            for (final Function m : module.getFunctions()) {
                
                if (entry.getKey().toString().equals(m.getInterfaceNick())
							&& m.getFunctionType().contentEquals("event")) {
                    
                    list =new ArrayList<String>();
                    
                    System.out.println(entry.getKey() +"\t"+m.getFunctionName()+ "\t"+m.getLineNumber());
                    String[] args1 = new String[] { "-file",filename,"-method", m.getFunctionName(),"-output", dir};
                    Nesc2CpnMain.process(args1);
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    //Nesc2CpnResult result = new Nesc2CpnResult();
                    
                    Properties prop = new Properties();

                    FileInputStream fis = new FileInputStream( System.getProperty( "nesc2cpn.output" )+"result.xml");
                    prop.loadFromXML(fis);
                    fis.close();
                    // prop.loa
                    
                    

                    String eMean = prop.getProperty("energy.mean", "0.0");
                    //setEnergyMean(Double.parseDouble(eMean));

                    String eVar = prop.getProperty("energy.variance", "0.0");
                    //setEnergyVariance(Double.parseDouble(eVar));

                    String pMean = prop.getProperty("power.mean", "0.0");
                    //setPowerMean(Double.parseDouble(pMean));

                    String pVar = prop.getProperty("power.variance", "0.0");
                    //setPowerVariance(Double.parseDouble(pVar));

                    String tMean = prop.getProperty("time.mean", "0.0");
                    //setTimeMean(Double.parseDouble(tMean));

                    String tVar = prop.getProperty("time.variance", "0.0");
                    //setTimeVariance(Double.parseDouble(tVar));
                    list.add(m.getFunctionName());
                    list.add("ENERGY_M "+eMean);
                    list.add("ENERGY_V "+eVar);
                    list.add("POWER_M "+pMean);
                    list.add("POWER_V "+pVar);
                    list.add("TIME_M "+tMean);
                    list.add("TIME_V "+tVar);
                    
                    calc.listMarker.put(m.getLineNumber(), list);


                }

            }
            
            
        }
        
        
        System.out.println("???????????????????????????????????????????????????????????");
        for(Entry<Integer, List<String>> entry : calc.listMarker.entrySet() ){
            
            System.out.println(entry.getKey());
            for(String lista : entry.getValue()){
                System.out.println("\t"+lista);
            }
            
        }
    }
    
}
