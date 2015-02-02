/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ece.rostan.traceroute;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author julien
 */
public class TraceFile {

    public String fileName;
    public String directoryName;
    public ArrayList<IpAdress> ipList;
    public String filePath;
    
    public TraceFile(String directoryName, String fileName)throws IOException {
        this.directoryName = directoryName;
        this.fileName = fileName;

        String directoryPath = System.getProperty("user.dir") + "\\" + directoryName + "\\";
        this.filePath = System.getProperty("user.dir") + "\\" + directoryName + "\\" + fileName;

        //Getting filePath and directoryPath with the file name and the directory name. 
        File file = new File(this.filePath);
        File dir = new File(directoryPath);
        
        this.deleteFile(dir, file);
    }
    
    public TraceFile(String directoryName, String fileName, Object trace) throws IOException {
        this.directoryName = directoryName;
        this.fileName = fileName;

        String directoryPath = System.getProperty("user.dir") + "\\" + directoryName + "\\";
        this.filePath = System.getProperty("user.dir") + "\\" + directoryName + "\\" + fileName;

        //Getting filePath and directoryPath with the file name and the directory name. 
        File file = new File(this.filePath);
        File dir = new File(directoryPath);

        this.createFile(dir, file);
        this.writeTrace(trace.toString(), this.filePath);
    }

    public void createFile(File dir, File file) throws IOException {

        if (dir.exists() && dir.isDirectory() && file.exists()) {

        } else {
            dir.mkdir();
            file.createNewFile();
        }
    }
    public void deleteFile(File dir, File file) throws IOException {

        if (dir.exists() && dir.isDirectory() && file.exists()) {
            file.delete();
            file.createNewFile();
        }
    }

    public void writeFile(String filePath, String Text) throws IOException {

        try {
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter output = new BufferedWriter(fw);

            output.write(Text);
            output.flush();
            output.close();

        } catch (IOException ioe) {
// TODO Auto-generated catch block 
            ioe.printStackTrace();
        }

    }

    public void writeTrace(String trace, String filePath) throws IOException {
        trace = trace + "\r\n";
        try {
            this.writeFile(filePath, trace);
            //this.trace=txt;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void readFile()  throws IOException {
        String chaine="";
        int point = 0;
        this.ipList = new ArrayList<IpAdress>();
        
        //lecture du fichier texte	
        try {
            InputStream ips = new FileInputStream(this.filePath);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            int i=0;
            while ((ligne = br.readLine()) != null) {
                chaine += ligne + "\n";
                StringTokenizer st = new StringTokenizer(ligne, " ");
                
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
                                point++;
                            }
                        }
                        //Si on a 3 points on a une adresse ip
                        if (point>=3 && ip.length()>5){
                            //On crée une adresse ip et on note sa ligne
                            IpAdress newIp = new IpAdress(i,ip);
                            //On ajoute l'adresse a la liste
                            this.ipList.add(newIp);
                        }
                    }
                }
                i++;
            }
            for (int k=0; k<this.ipList.size(); k++){
                this.ipList.get(k).displayAdress();
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
