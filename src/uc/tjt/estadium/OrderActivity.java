package uc.tjt.estadium;

import java.util.List;
import java.util.Vector;



import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
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
	
    private class Consumable{
    	String name;
    	float price;
    	Consumable(String n,float p){
    		name=n;price=p;
    	}
    }
    private class BillItem{
    	Consumable item;
    	int count=0;
    	float cost=item.price*count;
    }
    private class Bill{
    	List <BillItem> items;
    }
    private void UpdateMenu(){
    	TableLayout mTableLayout = (TableLayout)findViewById(R.id.refreshmentsMenuTableLayout);
    	mTableLayout.removeAllViews();
    	for(Consumable item:mRefreshmentsMenu){
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
    public void addDrink(Consumable c){}
    public void removeDrink(Consumable c){}
    public void clearDrink(Consumable c){}
    public void reset(){}
    public void order(){}
    
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
