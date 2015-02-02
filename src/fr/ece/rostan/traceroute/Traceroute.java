/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ece.rostan.traceroute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import fr.ece.rostan.traceroute.TracerouteItem;
import java.io.File;
/**
 *
 * @author julien
 */

public abstract class Traceroute
{
        private Runtime run;

        public Traceroute()
        {
                run = Runtime.getRuntime();
        }

        public ArrayList<TracerouteItem> traceroute(String destination)
        {
                TraceFile t;

                ArrayList<TracerouteItem> result = new ArrayList<TracerouteItem>();

                Process pr = null;

                String cmd = getTracerouteCommand(destination);

                try
                {
                        pr = run.exec(cmd);
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }

                BufferedReader buf = new BufferedReader(new InputStreamReader(
                                pr.getInputStream()));

                String line = "";
                int nbLine=0, cmp=0;
                try
                {
                    
                    t = new TraceFile("Doc", "test.txt");
                        while((line = buf.readLine()) != null)
                        {   
                            cmp++;
                            try{
                                t = new TraceFile("Doc", "test.txt",line);
                    
                            }catch (IOException ioe) {
                                ioe.printStackTrace();
                            }
                                TracerouteItem item = parse(line,nbLine);
                                if (item!=null){
                                    result.add(item);
                                }
                            if (cmp>3){nbLine++;}
                        }
                }
                catch(IOException e)
                {
                        return null;
                }
                try{
                    t.readFile();
                    
                }catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                return result;
        }

        public abstract TracerouteItem parse(String line, int nbLine);

        public abstract String getTracerouteCommand(String destination);

}

