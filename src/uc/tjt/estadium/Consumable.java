package uc.tjt.estadium;

import android.graphics.Bitmap;

public class Consumable{
	int ID;
	String name;
	float price;
	Bitmap mIcon;
	Consumable(int id,String n,float p){
		name=n;price=p;ID=id;
	}
}