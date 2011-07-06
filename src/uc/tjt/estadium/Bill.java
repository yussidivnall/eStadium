package uc.tjt.estadium;

import java.util.ArrayList;
import java.util.Vector;

import android.widget.Toast;

public class Bill{
	ArrayList <BillItem> items;
	public Bill(){
		items = new ArrayList<BillItem>();
	}
	
    public void addDrink(Consumable c){
    	BillItem bItem = getBillableItem(c);
    	if(bItem == null){
    		bItem = new BillItem(c);
    		bItem.count++;
    		bItem.cost=bItem.mConsumable.price*bItem.count;
    		items.add(bItem);
    	}else{
    		bItem.count++;
    		bItem.cost=bItem.mConsumable.price*bItem.count;
    	}
    	
    	System.out.println("Count:"+bItem.count);
    	System.out.println("Totals:"+items.size());
    }
    public void removeDrink(Consumable c){
    	BillItem bItem = getBillableItem(c);
    	if(bItem==null)return;
    	bItem.remove();
    	
    }
    public void clearDrink(Consumable c){}
    public void reset(){}
    
    
    
    public BillItem getBillableItem(Consumable c){
    	try{
	    	for(BillItem item:items){
	    		System.out.println("Found Item (In getBillableItem");
	    		if(c.equals(item.mConsumable)) return item;
	    		
	    	}
	    	System.out.println("DID NOT FIND Item (In getBillableItem");
	    	return null;
    	}catch (NullPointerException npe){
    		npe.printStackTrace();
    		return null;
    	}catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    	
    }
}
