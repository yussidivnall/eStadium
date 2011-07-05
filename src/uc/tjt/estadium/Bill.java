package uc.tjt.estadium;

import java.util.Vector;

import android.widget.Toast;

public class Bill{
	Vector <BillItem> items;
	public Bill(){
		items = new Vector <BillItem>();
	}
	
    public void addDrink(Consumable c){
    	BillItem item = getBillableItem(c);
    	item.count=item.count++;
    	
    }
    public void removeDrink(Consumable c){}
    public void clearDrink(Consumable c){}
    public void reset(){}
    
    public BillItem getBillableItem(Consumable c){
    	try{
	    	BillItem ret = new BillItem();
	    	
	    	for(BillItem item:items){
	    		if(c.equals(item)) return item;
	    	}
	    	return ret;
    	}catch (NullPointerException npe){
    //		Toast.makeText(this, "Null pointer caught in getBillItem...", Toast.LENGTH_SHORT);
    		return new BillItem();
    	}catch (Exception e){
   // 		Toast.makeText(this, "exception in getBillItem...", Toast.LENGTH_SHORT);
    		e.printStackTrace();
    		return new BillItem();
    	}
    	
    }
}
