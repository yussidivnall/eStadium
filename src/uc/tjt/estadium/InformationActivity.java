package uc.tjt.estadium;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class InformationActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        Button mSeatingPlanButton = (Button)findViewById(R.id.SeatingPlanButton);
        mSeatingPlanButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				try{
	         		 Intent intent = new Intent(v.getContext(), SeatingActivity.class);
	        		 startActivityForResult(intent,0);
					}catch(ActivityNotFoundException ane){
						
					}
			}});
        
    }
	
}
