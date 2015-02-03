/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.StringTokenizer;

/**
 *
 * @author julien
 */

public class LinuxTraceroute extends Traceroute
{

        public LinuxTraceroute()
        {
                super();
        }
        
        @Override
        public TracerouteItem parse(String line, int nbLine)
        {
            String address="";
        
            int point = 0;
        
                StringTokenizer st = new StringTokenizer(line, " ");
                
                while (st.hasMoreTokens()) {
                    point=0;
                    String ip = st.nextToken();
                    //filtrer encore les résultats et mettre ip dans une array
                    int intIndex = ip.indexOf(".");
                    if(intIndex == - 1){
                        //System.out.println("Ip not found line "+i);    
                    }else{
                        for (int j=0; j<ip.length(); j++){
                            if (ip.charAt(j)=='.'){
                                if (j!=ip.length()-1)
                                    point++;
                            }
                        }
                        //Si on a 3 points on a une adresse ip
                        if (point>=3 && ip.length()<=17 && ip.length()>5)
                        {
                            //On crée une adresse ip et on note sa ligne
                            if (ip.charAt(0)=='[')
                            {
                                
                                String ip2 = ip.substring(1,  ip.length()-1) ;
                                address=ip2;
                            }
                            if (ip.charAt(0)=='('){
                                
                                String ip2 = ip.substring(1,  ip.length()-1) ;
                                address=ip2;
                            }
                            else
                            {
                                address=ip;
                            }
                            System.out.println("Adresse IP finale : "+address);
                            return new TracerouteItem(nbLine, address); 
                        }
                    }
                }
                return null;  
        }
        
        @Override
        public String getTracerouteCommand(String destination)
        {
                return "traceroute " + destination;
        }

}