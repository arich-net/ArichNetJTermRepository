/** Ariel Vásquez v1.0 
    Session processes
**/ 
package arichnet.jsf.beans;
 
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
 
public class SessionBean {
	
	private static final Logger LOGGER = Logger.getLogger(SessionBean.class);
 
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
 
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }
 
    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }
 
    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("userid");
        else
            return null;
    }
    
    public static String getSessionId() {
    	HttpSession session = getSession();
        if (session != null) {
        	LOGGER.info("Returning Session ID: " + session.getId());
            return (String) session.getId();
        }
        else
            return null;
    }
}