package uc.tjt.estadium;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MenuListAdapter extends BaseAdapter{

	Context mContext;
	ArrayList<Consumable> mConsumables;
	Bill mBill;
	
	MenuListAdapter(Context c,ArrayList<Consumable> l,Bill bill){
		mContext=c;
		mConsumables=l;
		mBill = bill;
	}
	
	@Override
	public int getCount() {
		if(mConsumables==null) return 0;
		return mConsumables.size();
	}

	@Override
	public Object getItem(int idx) {
		return mConsumables.get(idx);
	}

	@Override
	public long getItemId(int idx) {
		return mConsumables.get(idx).ID;
	}

	@Override
	public View getView(int idx, View view, ViewGroup viewGroup) {
		return new MenuItemView(mContext,mConsumables.get(idx),mBill);
	}
}
