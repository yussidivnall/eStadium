package uc.tjt.estadium;

public class BillItem{
	public Consumable mConsumable;
	int count=0;
	float cost=0;
	
	public BillItem(Consumable c){
		mConsumable=c;
	}
	public void add(){
		count++;
		cost=mConsumable.price*count;
	}
	public void remove(){
		if(count <= 0) return;
		count--;
		cost=mConsumable.price*count;
	}
	
}
