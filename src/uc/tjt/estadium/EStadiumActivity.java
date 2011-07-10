package uc.tjt.estadium;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageView;

public class EStadiumActivity extends Activity {
	DeviceIdentifications mID; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Identification stuff here
        //Ref: http://stackoverflow.com/questions/2785485/is-there-a-unique-android-device-id
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(this.TELEPHONY_SERVICE);
        mID = new DeviceIdentifications();
        mID.DevId= tm.getDeviceId();
        mID.SIMId = tm.getSimSerialNumber();
        mID.AndroidId= "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
       
        // Display Elements...
        
        ImageView mRefreshmentsButton = (ImageView)findViewById(R.id.refreshmentsButton);
        ImageView mInfoButton = (ImageView)findViewById(R.id.infoButton);
        ImageView mPromotionsButton = (ImageView)findViewById(R.id.promotionsButton);
        
        ImageView mAdidasLogo = (ImageView)findViewById(R.id.adidasLogo);
        ImageView mUEFALogo = (ImageView)findViewById(R.id.uefaLogo);
        
        
        mRefreshmentsButton.setOnClickListener(new OnClickListener(){    
			@Override
			public void onClick(View v) {
				try{
         		 Intent intent = new Intent(v.getContext(), OrderActivity.class);
        		 startActivityForResult(intent,0);
				}catch(ActivityNotFoundException ane){
					
				}
			}
        	
        });
        mInfoButton.setOnClickListener(new OnClickListener(){    
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
         		 Intent intent = new Intent(v.getContext(), InformationActivity.class);
        		 startActivityForResult(intent,0);
				}catch(ActivityNotFoundException ane){
					
				}
			}
        	
        });
        mPromotionsButton.setOnClickListener(new OnClickListener(){    
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
         		 Intent intent = new Intent(v.getContext(), PromotionsActivity.class);
        		 startActivityForResult(intent,0);
				}catch(ActivityNotFoundException ane){
					
				}
			}
        	
        });
        /*
         * Adverts Below
         */
        
        mAdidasLogo.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				try{
					String address = getString(R.string.AdidasAdvertAddress);
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(address)));
				}catch(ActivityNotFoundException ane){}
			}
        });
        

        mUEFALogo.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				try{
					String address = getString(R.string.UEFAAdvertAddress);
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(address)));
				}catch(ActivityNotFoundException ane){}
			}
        });
    }
}