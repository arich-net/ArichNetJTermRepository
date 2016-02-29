/** Ariel Vásquez v1.0 
    Session processes
**/ 

package arichnet.jsf.beans;

import java.io.IOException;
import java.net.ServerSocket;
import java.io.*;
import java.net.*;

import org.apache.log4j.Logger;

public class UserTunnels {
	private static final Logger LOGGER = Logger.getLogger(UserTunnels.class);
	private String username = "";
	private String sid = "";
	private final int HTTPTUNNEL_MINPORT = 34000;
	private final int HTTPTUNNEL_MAXPORT = 34500;
	
	public UserTunnels () {
		this.username = "default";
		LOGGER.info("Initializing UserTunnels with default name");
	}	
	
	public UserTunnels (String username, String sid) {
		this.username = username;
		this.sid = sid;
		LOGGER.info("Initializing UserTunnels data: " + username + "|" + sid);
	}
	
	public void start() {
		LOGGER.info("Starting tunnel");
	}
	
	public class OpenTunnel implements Runnable {		
		@Override
		public void run() {
			try {
				
			}
			catch(Exception e){ }
		}		
	}
	
	private int getFirstLocalPortAvailable(){
		boolean port_found = false;
		int port_available = 0;
		ServerSocket ss = null;
		
		int port_counter=HTTPTUNNEL_MINPORT;
	    while(!(port_found && (port_counter<HTTPTUNNEL_MAXPORT))){
	      try {
	        ss = new ServerSocket(port_counter);
	        ss.setReuseAddress(true);
	        port_found = true;
	        port_available = port_counter;
	      }
	      catch (IOException e){
	        ss = null;
	        port_counter++;
	      }
	    }
	    if (ss!=null) {
	      try { ss.close(); }
	      catch (IOException e) { }
	    }
	    
	    return port_available;
	  }
}