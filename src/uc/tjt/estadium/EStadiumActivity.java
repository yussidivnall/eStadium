package uc.tjt.estadium;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class EStadiumActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ImageButton mRefreshmentsButton = (ImageButton)findViewById(R.id.refreshmentsButton);
        ImageView mAdidasLogo = (ImageView)findViewById(R.id.adidasLogo);
        mAdidasLogo.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				try{
					String address = getString(R.string.AdidasAdvertAddress);
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(address)));
				}catch(ActivityNotFoundException ane){}
			}
        });
        
        ImageView mUEFALogo = (ImageView)findViewById(R.id.uefaLogo);
        mUEFALogo.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				try{
					String address = getString(R.string.UEFAAdvertAddress);
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(address)));
				}catch(ActivityNotFoundException ane){}
			}
        });
        
        mRefreshmentsButton.setOnClickListener(new OnClickListener(){
        
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
         		 Intent intent = new Intent(v.getContext(), OrderActivity.class);
        		 startActivityForResult(intent,0);
				}catch(ActivityNotFoundException ane){
					
				}
			}
        	
        });
        /*
        */
    }
}