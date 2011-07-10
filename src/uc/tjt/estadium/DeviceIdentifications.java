package uc.tjt.estadium;

import java.util.UUID;

import android.content.Context;
//import android.telephony.TelephonyManager;

class DeviceIdentifications {
	//Context mContext;
	
	String DevId,SIMId,PhoneId,AndroidId,UUID;
	
	DeviceIdentifications(){
	//	tmDevice ="";
	//	tmSerial="";
	//	tmPhone="";
	//	androidId = "";
	}
	
	public UUID getUUID(){
		UUID ret = new UUID(AndroidId.hashCode(), ((long)DevId.hashCode() << 32) | SIMId.hashCode());
		return ret;
	}
}
