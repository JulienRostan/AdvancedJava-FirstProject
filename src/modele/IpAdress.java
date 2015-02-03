/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author julien
 */
public class IpAdress
{
    public String ip;
    public int line;
    
    public IpAdress(int line, String ip)
    {
        this.ip=ip;
        this.line=line;
    }
    
    public void displayAdress()
    {
        System.out.println("IpAdress : "+this.ip+" Line : "+this.line);
    }
}
