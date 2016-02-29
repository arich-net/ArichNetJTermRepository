/** Ariel Vásquez v1.0 
    Login process
**/ 
package arichnet.jsf.beans;

import java.io.Serializable;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;
 
import arichnet.jsf.dao.LoginDAO;

@ManagedBean
@SessionScoped
public class Login implements Serializable {
	private static final Logger LOGGER = Logger.getLogger(Login.class);
	private static final long serialVersionUID = 1094801825228386363L;
     
    private String pwd;
    private String msg;
    private String user;
    private String from;
 
    public String getPwd() {
        return pwd;
    }
 
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    public String getUser() {
        return user;
    }
 
    public void setUser(String user) {
        this.user = user;
    }
 
    //validate login
    public String validateUsernamePassword() throws IOException {
        boolean valid = LoginDAO.validate(user, pwd);
        if (valid) {        	
            HttpSession session = SessionBean.getSession();            
            session.setAttribute("username", user);
            LOGGER.info("Session variable username: " + user);
            // We open the user tunnels
            UserTunnels tunnels = new UserTunnels(user, session.getId());
            session.setAttribute("tunnels", tunnels);
            LOGGER.info("Session variable tunnels: " + user);
            tunnels.start();
            
            from = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("from");
            if (from != null && !from.isEmpty()) {
            	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("from");
            	FacesContext.getCurrentInstance().getExternalContext().redirect(from);
            	return null;
            }
            else {
            	return "admin";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }
 
    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }	
}
