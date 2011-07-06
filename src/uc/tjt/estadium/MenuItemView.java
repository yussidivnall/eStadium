package uc.tjt.estadium;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MenuItemView extends LinearLayout implements OnClickListener{
	Context mContext;
	
	TextView mItemName;
	TextView mItemPrice;
	TextView mQuantity;
	TextView mTotal;
	
	Button addButton;
	Button removeButton;
	Bill mBill;
	Consumable mConsumable;
	

	public MenuItemView(Context context, Consumable consumable,Bill bill) {
		super(context);
		setOrientation(HORIZONTAL);
		mContext=context;
		mBill = bill;
		mConsumable = consumable;
		mItemName = new TextView(context);
		mItemPrice = new TextView(context);
		mQuantity=new TextView(mContext);
		mTotal=new TextView(mContext);
		addButton = new Button(context);
		removeButton = new Button(context);
		
		
		mItemName.setText(mConsumable.name);
		mItemPrice.setText(""+mConsumable.price);
		mQuantity.setText("Quantity:0");
		mTotal.setText("Total:£0.00");		
		
		addView(mItemName, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		addView(mItemPrice, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		addView(mQuantity, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		addView(mTotal, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		addView(addButton, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		addView(removeButton, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		addButton.setText("Add");
		removeButton.setText("Remove");
		addButton.setOnClickListener(this);
		removeButton.setOnClickListener(this);
	}
	void updateView(){
		BillItem bItem = mBill.getBillableItem(mConsumable);
		if(bItem == null){
			mQuantity.setText("Quantity:0");
			mTotal.setText("Total:£0.00");
		}else{
			mQuantity.setText("Quantity:"+bItem.count);
			mTotal.setText("Total:"+"£"+bItem.cost);
		}		
	}

	@Override
	public void onClick(View v) {
		if(v.equals(addButton)){
			mBill.addDrink(mConsumable);
		}
		if(v.equals(removeButton)){
			mBill.removeDrink(mConsumable);
		}
		updateView();
	}
	
	
}
