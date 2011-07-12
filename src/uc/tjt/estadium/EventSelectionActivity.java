package uc.tjt.estadium;

import java.io.FileInputStream;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

//Ref:http://www.androidsnippets.com/retrieve-json-from-a-rest-web-service
public class EventSelectionActivity extends Activity {
	public final int STARTED_FETCHING=0;
	public final int FINISHED_FETCHING=1;
	int state=-1;
	
	JSONArray events;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventselection);
        ((TextView)findViewById(R.id.StatusTextView)).setText("Fetching list of events");
 
        try{
        Thread mThread = new Thread(){
        	@Override
        	public void run(){
        		state=STARTED_FETCHING;
        		events = fetchEvents();
        		state=FINISHED_FETCHING;
        	}
        };
        
        mThread.start();
        
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
    public JSONArray fetchEvents(){
    	//String url=""+getString(R.string.TJTServer)+"/cgi-bin/getCurrentEvents.py";    	
    	String url="http://192.168.1.67/cgi-bin/getCurrentEvents.py";
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpGet httpget = new HttpGet(url);
    	HttpResponse response;
    	try{
    		response=httpclient.execute(httpget);
    		HttpEntity entity = response.getEntity();
    		
    		if (entity != null) {

    			InputStream instream = entity.getContent();
    			byte res[]= new byte[instream.available()];    			
    			instream.read(res);
    			
    			instream.close();
    			
    			String ret=new String(res);
    			System.out.println("-=-=-=-=-=-=--=");
    			System.out.println(res);
    			System.out.println("-=-=-=-=-=-=--=");
    			System.out.println(ret);
    			System.out.println("-=-=-=-=-=-=--=");    			
    			return new JSONArray();
    			//return new JSONArray(ret);
    			
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return (JSONArray)null;
    	
    }

}
