package uc.tjt.estadium;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class OrderActivity extends Activity {
	ArrayList<Consumable> mConsumables;
	MenuListAdapter mAdapter;
	ListView mListView;
	
	Bill mBill;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        
        mBill = new Bill();
        mListView = (ListView)findViewById(R.id.list);
        mConsumables = new ArrayList<Consumable>();
        mConsumables.clear();
        fakeMenu();
        mAdapter = new MenuListAdapter(this,mConsumables,mBill);
        mListView.setAdapter(mAdapter);
	}
	
	void fakeMenu(){
        mConsumables.add(new Consumable(1,"Crack",4.23f));
        
        
        mConsumables.add(new Consumable(2,"Cocaine",4.50f));
        mConsumables.add(new Consumable(3,"Heroin",4.50f));
        mConsumables.add(new Consumable(3,"Methamphetamine",4.50f));
        
	}

	
}
