package uc.tjt.estadium;
//Ripped off example at http://www.codeshogun.com/blog/2009/04/16/how-to-implement-swipe-action-in-android/
//All credit goes there...
import java.util.ArrayList;
import android.app.Activity;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ViewFlipper;

public class OrderActivity extends Activity  {
	ArrayList<Consumable> mConsumables;
	MenuListAdapter mAdapter;
	ListView mListView;
	FrameLayout mFrameLayout;
	ViewFlipper mViewFlipper;
	Bill mBill;
	
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
        
        mBill = new Bill();
        mListView = (ListView)findViewById(R.id.listView);
        mConsumables = new ArrayList<Consumable>();
        mConsumables.clear();
        fakeMenu();
        mAdapter = new MenuListAdapter(this,mConsumables,mBill);
        mListView.setAdapter(mAdapter);
        mViewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper1);
        mFrameLayout = (FrameLayout)findViewById(R.id.orderView);
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
        mConsumables.add(new Consumable(1,"Crack",4.23f));
        mConsumables.add(new Consumable(2,"Cocaine",4.50f));
        mConsumables.add(new Consumable(3,"Heroin",4.50f));
        mConsumables.add(new Consumable(3,"Methamphetamine",4.50f));
	}
	class MyGestureDetector extends SimpleOnGestureListener{

		private static final int SWIPE_MIN_DISTANCE = 120;
		private static final int SWIPE_MAX_OFF_PATH = 250;
		private static final int SWIPE_THRESHOLD_VELOCITY = 200;
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
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
	
}
