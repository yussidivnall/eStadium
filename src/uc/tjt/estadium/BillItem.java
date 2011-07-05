package uc.tjt.estadium;

public class BillItem{
	Consumable item;
	int count=0;
	float cost=item.price*count;
	
	public BillItem(){
		//item=new Consumable("-", 0.00f);
	}
}
