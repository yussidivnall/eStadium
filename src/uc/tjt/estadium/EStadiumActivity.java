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
        ImageButton mInfoButton = (ImageButton)findViewById(R.id.infoButton);
        ImageButton mPromotionsButton = (ImageButton)findViewById(R.id.promotionsButton);
        
        ImageView mAdidasLogo = (ImageView)findViewById(R.id.adidasLogo);
        ImageView mUEFALogo = (ImageView)findViewById(R.id.uefaLogo);
        
        
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
			@Override
			public void onClick(View v) {
				try{
					String address = getString(R.string.UEFAAdvertAddress);
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(address)));
				}catch(ActivityNotFoundException ane){}
			}
        });
    }
}