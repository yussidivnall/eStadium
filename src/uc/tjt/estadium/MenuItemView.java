package uc.tjt.estadium;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MenuItemView extends LinearLayout implements OnClickListener{
	Context mContext;
	
	LinearLayout mRootLayout;
	LinearLayout mTextLayout;
	LinearLayout mButtonsLayout;
	RelativeLayout mButtonsContainer;
	
	TextView mItemName;
	TextView mItemPrice;
	TextView mQuantity;
	TextView mTotal;
	
	Button addButton;
	Button removeButton;
	Bill mBill;
	Consumable mConsumable;
	
	ImageView mIcon;

	public MenuItemView(Context context, Consumable consumable,Bill bill) {
		super(context);
		setOrientation(HORIZONTAL);
		mContext=context;
		mBill = bill;
		mConsumable = consumable;

//		LayoutParams p = new LayoutParams(p);
		mRootLayout= new LinearLayout(mContext);
		mRootLayout.setOrientation(HORIZONTAL);
		mTextLayout = new LinearLayout(mContext);
		mTextLayout.setOrientation(VERTICAL);
		
		mButtonsContainer = new RelativeLayout(mContext);
		mButtonsContainer.setGravity(Gravity.RIGHT);
		
		mButtonsLayout = new LinearLayout(mContext);
		mButtonsLayout.setOrientation(VERTICAL);
		
		
		if(mConsumable.mIcon != null){
			mIcon = new ImageView(mContext);
			mIcon.setImageBitmap(mConsumable.mIcon);
			mIcon.setMinimumWidth(70);
			mIcon.setMaxWidth(70);
			mIcon.setMinimumHeight(100);
			mIcon.setMaxHeight(100);
			mRootLayout.addView(mIcon);
		}
		
		//mRootLayout.setLayoutParams(new LayoutParams(""))
		
		mItemName = new TextView(context);
		mItemPrice = new TextView(context);
		mQuantity=new TextView(mContext);
		mTotal=new TextView(mContext);
		addButton = new Button(context);
		removeButton = new Button(context);
		
		
		mItemName.setText(mConsumable.name);
		mItemName.setTextSize(30);
		
		mItemPrice.setText("Price: £"+mConsumable.price);
		mQuantity.setText("Quantity: 0");
		mTotal.setText("Total: £0.00");	
		
		
		mTextLayout.addView(mItemName, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mTextLayout.addView(mItemPrice, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mTextLayout.addView(mQuantity, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mTextLayout.addView(mTotal, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		mRootLayout.addView(mTextLayout);

		
		mButtonsLayout.setGravity(Gravity.RIGHT);
		mButtonsLayout.addView(addButton, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mButtonsLayout.addView(removeButton, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));		
		mButtonsContainer.addView(mButtonsLayout,new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
		mRootLayout.addView(mButtonsContainer,new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		


		this.addView(mRootLayout);
		
		addButton.setText("      +      ");
		removeButton.setText("      -      ");
		addButton.setOnClickListener(this);
		removeButton.setOnClickListener(this);
	}
	void updateView(){
		BillItem bItem = mBill.getBillableItem(mConsumable);
		if(bItem == null){
			mQuantity.setText("Quantity: 0");
			mTotal.setText("Total: £0.00");
		}else{
			mQuantity.setText("Quantity: "+bItem.count);
			mTotal.setText("Total: £"+bItem.cost);
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
