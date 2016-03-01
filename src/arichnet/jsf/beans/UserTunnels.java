/** Ariel Vásquez v1.0
User Tunnels opening
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
    private String fhost = "127.0.0.1";
    private int fport = 80;
    private final int HTTPTUNNEL_MINPORT = 34000;
    private final int HTTPTUNNEL_MAXPORT = 34500;
    
    public UserTunnels () {
        this.username = "default";
        LOGGER.info("Initializing UserTunnels with default name");
    }
    
    public UserTunnels (String username, String sid) {
        this.username = username;
        this.sid = sid;
        LOGGER.info("Initializing UserTunnels data: " + username + "|" + sid + 
                    "-" + fhost + "@" + fport);
    }
    
    public UserTunnels (String username, String sid, String fhost, int fport) {
        this.username = username;
        this.sid = sid;
        this.fhost = fhost;
        this.fport = fport;
        LOGGER.info("Initializing UserTunnels data: " + username + "|" + sid + 
                    "-" + fhost + "@" + fport);
    }
    
    public void OpenTunnels() {
        LOGGER.info("Starting tunnels for user:" + username + " sid:" + sid );
        String thread_name = sid + "-" + username + "-" + fhost + "-" + fport
        LOGGER.debug("Registering thread with name: " + thread_name);
        Thread tunnel_thread = new Thread(new OpenTunnel(), thread_name);
        tunnel_thread.start();
    }
    
    public class OpenTunnel implements Runnable {
        @Override
        public void run() {
            try {
            	LOGGER.info("Starting tunnel A with data:"data: " + username + "|" + sid + 
                            "-" + fhost + "@" + fport );
                LOGGER.debug("Inside thread " + getName());
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
