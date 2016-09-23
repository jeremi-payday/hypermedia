/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author guitp
 */
public class FlashSession {
    private Stack<FlashMessage> messages;
    
    public FlashSession(){
        this.messages = new Stack<FlashMessage>();
        
    }
    
    public FlashMessage[] getMessages(){
        return (FlashMessage[])this.messages.toArray();
    }
    
    public void addMessage(FlashMessage fm){
        this.messages.add(fm);
    }
    public void addMessage(String message, FlashMessage.Types type){
        this.messages.add( new FlashMessage(message, type) );
    }
    
    public ArrayList<FlashMessage> flushMessages(){
        ArrayList<FlashMessage> fm =  new ArrayList<FlashMessage>( this.messages );
        this.messages.empty();
        return fm;
    }
    
    public FlashMessage pop(){
        return this.messages.pop();
    }
    
    public FlashMessage peek(){
        return this.messages.peek();
    }
}
