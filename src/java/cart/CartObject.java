/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;
import main.Item;

/**
 *
 * @author guitp
 */
public class CartObject {
    private Hashtable<Integer, Vector<Item>> items;

    public CartObject() {
        this.items = new Hashtable<Integer, Vector<Item>>();
    }
    
    public void addItem(Item item){
        if( !this.items.containsKey(item.getId()) ){
            this.items.put(item.getId(), new Vector<Item>());
        }
        this.items.get(item.getId()).add(item);
    }
    
    public void deleteItem(Item item, int amount){
        if( this.items.containsKey(item.getId())){
            if(amount == this.items.get(item.getId()).size()){
                deleteAllSimilarItems(item);
                return;
            }
            
            for(int i = 0; i < amount; i++){
                this.items.get(item.getId()).removeElementAt(0);
            }
            
        }
    }
    
    public void deleteAllSimilarItems(Item item){
        this.items.remove(item.getId());
    }
    
    public void emptyCart(){
        this.items = new Hashtable<Integer, Vector<Item>>();
    }

    public int getTotalItemCount(){
        Collection<Vector<Item>> lists = this.items.values();
        int totalCount = 0;
        
        for(Vector<Item> v : lists){
            totalCount += v.size();
        }
        
        return totalCount;
    }    
    
    //getters setters
    public Hashtable<Integer, Vector<Item>> getItems() {
        return items;
    }

    public void setItems(Hashtable<Integer, Vector<Item>> items) {
        this.items = items;
    }
    
    
    
    
}
