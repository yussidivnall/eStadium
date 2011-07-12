package uc.tjt.estadium;




import java.sql.Connection;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageView;

public class EStadiumActivity extends Activity {
	DeviceIdentifications mID; 
	Messenger mService; //Outgoing to service
	SharedPreferences mSettings;
	final Messenger mMessenger = new Messenger(new IncomingHandler()); //incoming from service 
	class IncomingHandler extends Handler{
		
		
	}
	
    
	public void startService(){
    	Intent intent=new Intent(this,EStadiumService.class);
    	startService(intent);
    }
    public void stopService(){
    	
    }
    public void getEventsList(){
    	String oldEventList = mSettings.getString("EventsList", "");        
		Intent intent = new Intent(this, EventSelectionActivity.class);
		intent.putExtra("EventsList", oldEventList);
		
		//From here it will crash...
		//startActivityForResult(intent,0);    
    }
	
    
    
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //SharedPreferences mSettings = getSharedPreferences(PREFS_NAME, 0);
        mSettings = this.getPreferences(MODE_PRIVATE);
        
        //Identification stuff here
        //Ref: http://stackoverflow.com/questions/2785485/is-there-a-unique-android-device-id
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(this.TELEPHONY_SERVICE);
        mID = new DeviceIdentifications();
        mID.DevId= tm.getDeviceId();
        mID.SIMId = tm.getSimSerialNumber();   
        mID.AndroidId= "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        //connection stuff here
        //First connect to our server and fetch event list...
        getEventsList();
        
        ConnectivityManager mConnectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        Connection mDatabaseConnection;
        
        //Service Stuff here...
        final ServiceConnection mConnection = new ServiceConnection(){

			@Override
			public void onServiceConnected(ComponentName name, IBinder binder) {
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {				
			}
        	
        };
        startService();
        
        
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
				}catch(ActivityNotFoundException ane){}
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