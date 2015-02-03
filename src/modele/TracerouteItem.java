/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 *
 * @author julien
 */

public class TracerouteItem {

        private InetAddress address;

        private int line;

        public TracerouteItem(int line, String address)
        {
                this.line = line;
                try
                {
                        this.address = InetAddress.getByName(address);
                }
                catch(UnknownHostException e)
                {
                        e.printStackTrace();
                }
        }

        public String toString()
        {
                return line + " : " + address.getHostAddress();
        }
        
        public String getAddress()
        {
        	return address.getHostAddress();
        }

		public int getLine()
		{
			return line;
		}

}