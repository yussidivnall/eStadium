package uc.tjt.estadium;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class PromotionsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotions);
        Button mSponserButton = (Button) findViewById(R.id.sponserButton);
        mSponserButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				try{
	         		 Intent intent = new Intent(v.getContext(), SponserActivity.class);
	        		 startActivityForResult(intent,0);
				}catch(ActivityNotFoundException ane){
						ane.printStackTrace();
				}	
			}
        });
        Button mSpecialOffersButton = (Button)findViewById(R.id.offersButton);
        mSpecialOffersButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				try{
	         		 Intent intent = new Intent(v.getContext(), SpecialOffersActivity.class);
	        		 startActivityForResult(intent,0);
				}catch(ActivityNotFoundException ane){
						ane.printStackTrace();
				}	
			}});
        
        Button mRedeemButton = (Button)findViewById(R.id.redeemButton);
        mRedeemButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				try{
	         		 Intent intent = new Intent(v.getContext(), RedeemVoucherActivity.class);
	        		 startActivityForResult(intent,0);
				}catch(ActivityNotFoundException ane){
						ane.printStackTrace();
				}	
			}});

        
    }
}
