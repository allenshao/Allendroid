package org.allendroid.whse;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UploadAccountActivity extends Activity{
	
	protected static final int NETWORK_CONNECTION_FAILED_MSG = 0;
	protected static final int UPLOAD_HOTSPOTSTORE_DONE_MSG = 1;
	protected static final String TAG = "UploadAccountActivity";
	ImageView 	menuImageView;
	TextView 	tv_des;
	EditText	et_des;
	TextView 	tv_ssid;
	EditText 	et_ssid;
	TextView 	tv_key;
	EditText 	et_key;
	TextView 	tv_long;
	EditText 	et_long;
	TextView 	tv_lat;
	EditText 	et_lat;
	Button 		btn_upload;
	
	static UploadAccountActivity instance;
	WifiInternet wi;
	
	{instance = this; }
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
	     setContentView(R.layout.uploadaccountactivity);         
	     getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
	     
	     wi = new WifiLocal();
	     
	     final Context context = UploadAccountActivity.this;
	        
	        menuImageView = (ImageView)findViewById(R.id.iv_titlebar_menu);
	        menuImageView.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(context, MenuActivity.class);
					//finish();
					startActivity(intent);

				}
	        	
	        });
	     tv_des		= (TextView)findViewById(R.id.tv_des);
	     et_des		= (EditText)findViewById(R.id.et_des);
	     tv_ssid 	= (TextView)findViewById(R.id.tv_ssid);
	     et_ssid 	= (EditText)findViewById(R.id.et_ssid);
	     tv_key 	= (TextView)findViewById(R.id.tv_key);
	     et_key 	= (EditText)findViewById(R.id.et_key);
	     tv_long 	= (TextView)findViewById(R.id.tv_long);
	     et_long 	= (EditText)findViewById(R.id.et_long);
	     tv_lat 	= (TextView)findViewById(R.id.tv_lat);
	     et_lat 	= (EditText)findViewById(R.id.et_lat);
	     btn_upload = (Button)findViewById(R.id.upload_btn);
	     
	     btn_upload.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				int longitude;
				int latitude;
				String description = et_des.getText().toString();
				String ssid = et_ssid.getText().toString();
				String key = et_key.getText().toString();
				String latitudeStr = et_lat.getText().toString();
				String longitudeStr = et_long.getText().toString();
				longitude = Integer.parseInt(longitudeStr);
				latitude = Integer.parseInt(latitudeStr);
	
				Log.i(TAG,"description="+description
						+" ssid="+ssid
						+" key="+key
						+" longitude="+longitude
						+" latitude="+latitude);
				uploadHotspotStore(description,
						ssid, 
						key,
						longitude,
						latitude);
			}
	    	 
	     });
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		
	}
	
	private void uploadHotspotStore(final String description, final String ssid, final String key, final int longitude, final int latitude){
		final ProgressDialog pd = ProgressDialog.show(UploadAccountActivity.this, 
				getResources().getString(R.string.networkConnection), 
				getResources().getString(R.string.uploadHotspotStore)+" "+ssid+" KEY: "+key
				+getResources().getString(R.string.toInternet)
			);
		new Thread(new Runnable(){
			public void run(){
				try{
					wi.uploadAccounts(description, ssid, key, longitude, latitude);
					String[] account = {ssid, key};
					sendUploadHotspotStoreDoneMsg(account);
				}catch(IOException e){
					sendNetworkConnectFailMsg();
					e.printStackTrace();
				}finally{
					pd.dismiss();
				}
			}
		}).start();
	}
	
	protected void sendNetworkConnectFailMsg() {
		// TODO Auto-generated method stub
		Message msg = new Message();
		msg.what = NETWORK_CONNECTION_FAILED_MSG;
		handler.sendMessage(msg);
	}
	
	protected void sendUploadHotspotStoreDoneMsg(String[] account) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		msg.what = UPLOAD_HOTSPOTSTORE_DONE_MSG;
		Bundle bundle = new Bundle();
		bundle.putStringArray("account", account);
		msg.setData(bundle);
		handler.sendMessage(msg);
	}
	
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			switch(msg.what){
			case NETWORK_CONNECTION_FAILED_MSG:
				AlertDialog.Builder builder = new AlertDialog.Builder(UploadAccountActivity.this).setTitle("Network").setMessage("Connection Failure");
				AlertDialog ad = builder.create();
				ad.setButton(String.valueOf("Confirm"), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				ad.show();
				break;
				
			case UPLOAD_HOTSPOTSTORE_DONE_MSG:
				String[] account = msg.getData().getStringArray("account");
				Toast t = Toast.makeText(UploadAccountActivity.this,
						account[0]+" "+account[1]+" "+getResources().getString(R.string.uploadDone), 3000);
				t.show();
				
				finish();
				break;
				
			}
		}
	};
	
	static public UploadAccountActivity getInstance(){
		return instance;
	}
}
