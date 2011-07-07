package uc.tjt.estadium;

import java.util.ArrayList;
import android.widget.TextView;


public class Bill{
	ArrayList <BillItem> items;
	TextView mTotalTextView;
	
	public Bill(TextView totalTV){
		items = new ArrayList<BillItem>();
		mTotalTextView = totalTV;
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
    	String totalStr = String.format("Total: £%.2f", getTotalBill());
    	mTotalTextView.setText(totalStr);
    	//mTotalTextView.setText("Total: £"+getTotalBill());
    }
    public void removeDrink(Consumable c){
    	BillItem bItem = getBillableItem(c);
    	if(bItem==null)return;
    	bItem.remove();
    	mTotalTextView.setText("Total: £"+getTotalBill());
    }
    public float getTotalBill(){
    	float ret=0;
    	for(BillItem bItem:items){
    		if(bItem.count >0)ret+=bItem.cost;
    	}
    	return ret;
    }
    public void clearDrink(Consumable c){}
    public void reset(){
    	items.clear();
    	mTotalTextView.setText("Total: £"+getTotalBill());
    }
    
    
    
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
