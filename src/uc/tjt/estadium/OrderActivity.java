package uc.tjt.estadium;

import java.util.List;
import java.util.Vector;



import android.app.Activity;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
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

            TextView txt = new TextView(this);
            txt.setText(item.name+" £"+item.price);
            
            Button addButton = makeButton("+");

            //OnClickListener clicklsn = new AddDrinkClick(item);
            //addButton.setOnClickListener(clicklsn);
    
            
            Button removeButton = makeButton("-");
            Button clearButton = makeButton("Clear");
            //lo.addView(img);
            lo.addView(txt);
            lo.addView(addButton);
            lo.addView(removeButton);
            lo.addView(clearButton);
            row.addView(lo);
            mTableLayout.addView(row,new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
    	}
    }

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
    	Consumable calrsberg = new Consumable(1,"Calrsberg",3.50f);
    	Consumable calrsberg_export = new Consumable(2,"Calrsberg Export",3.50f);
    	
    	mRefreshmentsMenu.add(calrsberg);
    	mRefreshmentsMenu.add(calrsberg_export);
    	  	
    }
    class AddDrinkClick implements OnClickListener{
    	Consumable mConsumable;
    	AddDrinkClick(Consumable item){
    		mConsumable = item;
    	}
		@Override
		public void onClick(View v) {
			mBill.addDrink(mConsumable);
		}
    	
    }

	
}
