package misc;

import java.io.Serializable;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author mehdi
 */

public class SessionBean  implements HttpSessionListener, Serializable{
    
    private static int NB_SESSIONS=0;
    
    public SessionBean(){
        super();
    }
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        NB_SESSIONS++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        NB_SESSIONS--;
    }

    public static int getNB_SESSIONS() {
        return NB_SESSIONS;
    }   
}