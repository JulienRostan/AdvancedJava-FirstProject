/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        ArrayList<TracerouteItem> result = new ArrayList<TracerouteItem>();

        Process pr = null;

        String cmd = getTracerouteCommand(destination);

        try { pr = run.exec(cmd); } catch(IOException e) { e.printStackTrace(); }

        BufferedReader buf = new BufferedReader(new InputStreamReader( pr.getInputStream()));

        String line = ""; int nbLine=0;
        try {new TraceFile("Doc", "test.txt"); while((line = buf.readLine()) != null) { try{ new TraceFile("Doc", "test.txt",line); }catch (IOException ioe) { ioe.printStackTrace(); } TracerouteItem item = parse(line,nbLine); if (item!=null){ result.add(item); } nbLine++; } } catch(IOException e) { return null; } return result; }

        public abstract TracerouteItem parse(String line, int nbLine);

        public abstract String getTracerouteCommand(String destination);

}

