package uc.tjt.estadium;
//Ripped off example at http://www.codeshogun.com/blog/2009/04/16/how-to-implement-swipe-action-in-android/
//All credit goes there...
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class OrderActivity extends Activity  {
	ArrayList<Consumable> mConsumables;
	MenuListAdapter mAdapter;
	ListView mListView;
	
	LinearLayout mOrderLayout;
	ViewFlipper mViewFlipper;
	Bill mBill;
	
	EditText mOrderEditText;
	Button mSendOrderButton;
	
	TextView mTotalBillTextView;
	Button mResetButton;
	
	Animation slideLeftIn;
	Animation slideLeftOut;
	Animation slideRightIn;
	Animation slideRightOut;
	
	
	GestureDetector mGestureDetector;
	OnTouchListener mOnTouchListener;
	
	


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        
        mTotalBillTextView = (TextView)findViewById(R.id.TotalBillTextView);
        
        
        mOrderEditText = (EditText)findViewById(R.id.OrderEditText);
        mOrderEditText.setText("");
        
        mBill = new Bill(mTotalBillTextView);
        mListView = (ListView)findViewById(R.id.listView);
        mConsumables = new ArrayList<Consumable>();
        mConsumables.clear();
        fakeMenu();
        initListview();

        initGestures();
        initButtons();
     
	}
	void initListview(){
        mAdapter = new MenuListAdapter(this,mConsumables,mBill);
        mListView.setAdapter(mAdapter);
        mViewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper1);
        mOrderLayout = (LinearLayout)findViewById(R.id.orderView);
	}
	
	void initButtons(){
        mResetButton = (Button)findViewById(R.id.ResetButton);
        mResetButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				mBill.reset();
				mTotalBillTextView.setText(getString(R.string.Total));
				mConsumables.clear();
		        fakeMenu();
		        initListview();
				UpdateOrderText();
				
			}
        });
        
        
        mSendOrderButton = (Button)findViewById(R.id.SendOrderButton);
        mSendOrderButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ getString(R.string.BillRecipientEmail)});
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Order");
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,mOrderEditText.getText());
				OrderActivity.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			}
		});
		
	}
	
	void initGestures(){
        mGestureDetector = new GestureDetector((android.view.GestureDetector.OnGestureListener)new MyGestureDetector());
        mOnTouchListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (mGestureDetector.onTouchEvent(event)) {
                    return true;
                }
                return false;
            }
        };
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
			System.out.println("On Touch Event");
			if(mGestureDetector.onTouchEvent(event))return true;
			else return false;
	}	
	void fakeMenu(){
        mConsumables.add(new Consumable(1,"Carlsberg",3.00f));
        mConsumables.add(new Consumable(2,"Heiniken",4.50f));
        mConsumables.add(new Consumable(3,"Carlsberg\nExport",4.90f));
        mConsumables.add(new Consumable(3,"Carlsberg\nSpecial Brew",5.90f));
	}
	class MyGestureDetector extends SimpleOnGestureListener{

		private static final int SWIPE_MIN_DISTANCE = 120;
		private static final int SWIPE_MAX_OFF_PATH = 250;
		private static final int SWIPE_THRESHOLD_VELOCITY = 200;
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			UpdateOrderText();
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) return false;
			if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				//mViewFlipper.setAnimation(mListView);
				//mViewFlipper.setInAnimation(mFrameFlayout);
				System.out.println("Swipe Left");
				mViewFlipper.showNext();
			}else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				mViewFlipper.showPrevious();
				System.out.println("Swipe Right");
			}
			
			
			return false;
		}
		
	}
	void UpdateOrderText(){
		mOrderEditText.setText("");
		for(BillItem bItem:mBill.items){
			if(bItem.count > 0){
				mOrderEditText.append(bItem.mConsumable.name+" \tX"+bItem.count+"\t £"+bItem.cost+"\n");
			}
		}
		mOrderEditText.append("Total Bill: £"+mBill.getTotalBill());
		
	}
	
	
}
