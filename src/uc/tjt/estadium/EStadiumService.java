package uc.tjt.estadium;


import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class EStadiumService extends Service{
	private NotificationManager mNotificationManager;
	
	class IncomingHandler extends Handler{
        public void handleMessage(Message msg) {}
	}
	final Messenger mMessenger = new Messenger(new IncomingHandler());
	

	@Override
	public void onCreate(){
		System.out.println("service,on create...");
		super.onCreate();
		mNotificationManager=(NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
	}
	@Override
	public void onDestroy(){
		System.out.println("service,on destroy...");
		super.onDestroy();
		try{
			//stop();
		}catch(Exception ioe){
			//alert("WiiService.onDestroy()"+ioe.getMessage());
			Log.w("onDestroy()",ioe.getMessage());
		}
	}
	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(this,"onBind()...",Toast.LENGTH_LONG).show();
		System.out.println("Binding...");
		return mMessenger.getBinder();
	}
	@Override
	public int onStartCommand(Intent intent,int flags,int startID){
		//start(intent);
		System.out.println("service,start commad...");
		return Service.START_STICKY;
	}
}

