package uc.tjt.estadium;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EStadiumActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button mRefreshmentsButton = (Button)findViewById(R.id.refreshmentsButton);
        //mRefreshmentsButton.setOnClickListener(new OnClickListener(){
        /*
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
         		 Intent intent = new Intent(v.getContext(), OrderActivity.class);
        		 startActivityForResult(intent,0);				
			}
        	
        });
        */
    }
}