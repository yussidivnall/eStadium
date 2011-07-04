package uc.tjt.estadium;

import java.util.List;
import java.util.Vector;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;

public class OrderActivity extends Activity {
	TextView mTotalBillTextView;
	java.util.Vector<Consumable> mRefreshmentsMenu = null;//can be ambigious with android vector
	Bill mBill;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        
        mRefreshmentsMenu=new java.util.Vector<Consumable> ();
        mTotalBillTextView = (TextView)findViewById(R.id.TotalBillTextView);
        mTotalBillTextView.setText("0.00");
        mBill = new Bill();
        
        FakeMenu();
        UpdateMenu();
        
    }
	
	public class Consumable{
    	String name;
    	float price;
    	Consumable(String n,float p){
    		name=n;price=p;
    	}
    }
    public class BillItem{
    	Consumable item;
    	int count=0;
    	float cost=item.price*count;
    	
    	public BillItem(){
    		item=new Consumable("-", 0.00f);
    	}
    }
    public class Bill{
    	Vector <BillItem> items;
    	public Bill(){
    		items = new Vector <BillItem>();
    	}
    }
    private void UpdateMenu(){
    	TableLayout mTableLayout = (TableLayout)findViewById(R.id.refreshmentsMenuTableLayout);
    	mTableLayout.removeAllViews();
    	for(final Consumable item:mRefreshmentsMenu){
    		TableRow row = new TableRow(this);
            row.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            LinearLayout lo = new LinearLayout(this);
            lo.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            lo.setOrientation(LinearLayout.HORIZONTAL);
            Button addButton = makeButton(item.name+" : £"+item.price);
            addButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					addDrink(item);
				}
            	
            	
            });
            
            
            Button removeButton = makeButton("-");
            Button clearButton = makeButton("Clear");
            
            lo.addView(addButton);
            lo.addView(removeButton);
            lo.addView(clearButton);
            row.addView(lo);
            mTableLayout.addView(row,new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
    	}
    }
    public void addDrink(Consumable c){
    	BillItem mitem = getBillableItem(c);
    	//item.count=item.count++;
    	
    }
    public void removeDrink(Consumable c){}
    public void clearDrink(Consumable c){}
    public void reset(){}
    public void order(){}
    
    public BillItem getBillableItem(Consumable c){
    	try{
	    	BillItem ret = new BillItem();
	    	
	    	for(BillItem item:mBill.items){
	    		if(c.equals(item)) return item;
	    	}
	    	return ret;
    	}catch (NullPointerException npe){
    		Toast.makeText(this, "Null pointer caught in getBillItem...", Toast.LENGTH_SHORT);
    		return new BillItem();
    	}catch (Exception e){
    		Toast.makeText(this, "exception in getBillItem...", Toast.LENGTH_SHORT);
    		e.printStackTrace();
    		return new BillItem();
    	}
    	
    }
    
    /*
     * A helper function to create a button
     */
    
    public Button makeButton(String text){
        Button ret = new Button(this);
        ret.setText(text);
        ret.setLayoutParams(new LayoutParams(
                  LayoutParams.WRAP_CONTENT,
                  LayoutParams.WRAP_CONTENT));
        return ret;
    }
    
    public void FakeMenu(){
    	    	
    	mRefreshmentsMenu.add(new Consumable("Beer1",2.20f));
    	mRefreshmentsMenu.add(new Consumable("Beer2",3.20f));
    	mRefreshmentsMenu.add(new Consumable("Beer3",4.80f));
    	mRefreshmentsMenu.add(new Consumable("Beer4",5.50f));
    	mRefreshmentsMenu.add(new Consumable("Beer5",7.20f));
    	
    }

}
