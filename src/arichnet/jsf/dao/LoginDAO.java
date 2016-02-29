/** Ariel Vásquez v1.0 
    DAO processes
**/ 
package arichnet.jsf.dao;

 
public class LoginDAO {
 
    public static boolean validate(String user, String password) {
    	boolean ret = false;
    	try {
	    	if (user.equals("arich.net") && password.equals("password")) {
	    		ret = true;
	    	}    
        } catch (Exception ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        }
        return ret;
    }
}
