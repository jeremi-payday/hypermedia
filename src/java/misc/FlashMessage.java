/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

/**
 *
 * @author guitp
 */
public class FlashMessage {
    public static enum Types{
        SUCCESS,
        FAILURE,
        WARNING,
        INFO
    }
    
    private String message;
    private Types type;

    public FlashMessage(String message, Types type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }
    
    
    
    
}
