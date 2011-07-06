package uc.tjt.estadium;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SpecialOffersActivity extends Activity {
    ArrayList<Offer> mOffers;
	OffersListAdapter mAdapter;
	ListView mListView;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offers);
        
        
        mListView = (ListView)findViewById(R.id.OffersListView);
        mOffers = new ArrayList<Offer>();
        fakeOffers();
        initListview();
    }
	void fakeOffers(){
		mOffers.add(new Offer(1,"Buy 2 beers get 1 at 1/2 price"));
		mOffers.add(new Offer(2,"Buy 5 beers get 1 at 4/2 price"));
		mOffers.add(new Offer(2,"Buy 10 beers get a liver pumped at 1/2 price"));
	}
	void initListview(){
        mAdapter = new OffersListAdapter(this,mOffers);
        mListView.setAdapter(mAdapter);
	}
}

class Offer{
	String Description;
	long id;
	Offer(long ID,String desc){
		id=ID;
		Description=desc;
	}
}



class OfferItemView extends LinearLayout{
	Offer mOffer;
	Context mContext;
	
	TextView mDescription;
	public OfferItemView(Context context,Offer offer) {
		super(context);
		mOffer =offer;
		mContext=context;
		mDescription=new TextView(mContext);
		mDescription.setText(mOffer.Description);
		this.addView(mDescription);
	}
}

class OffersListAdapter extends BaseAdapter{
	Context mContext;
	ArrayList<Offer> mOffers;
	
	OffersListAdapter(Context context,ArrayList<Offer> offers){
		mContext=context;
		mOffers=offers;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(mOffers==null) return 0;
		return mOffers.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mOffers.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mOffers.get(position).id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return new OfferItemView(mContext,mOffers.get(position));
	}
	
}