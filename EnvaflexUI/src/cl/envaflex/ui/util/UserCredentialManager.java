package cl.envaflex.ui.util;

import org.h2.engine.User;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

public class UserCredentialManager {
 
    private static final String KEY_USER_MODEL = UserCredentialManager.class.getName()+"_MODEL";
    private String userName;
     
    private UserCredentialManager(String userName){
        this.userName = userName;
    }
     
    public static UserCredentialManager getInstance(String userName){
        return getInstance(Sessions.getCurrent(),userName);
    }
    /**
     * 
     * @return
     */
    public static UserCredentialManager getInstance(Session zkSession,String userName){
         
         
//      Session session = Executions.getCurrent().getDesktop().getSession();
//      Session session = Executions.getCurrent().getSession();
        synchronized(zkSession){
            UserCredentialManager userModel = (UserCredentialManager) zkSession.getAttribute(KEY_USER_MODEL);
            if(userModel==null){
                zkSession.setAttribute(KEY_USER_MODEL, userModel = new UserCredentialManager(userName));
            }
            return userModel;
        }
    }
}