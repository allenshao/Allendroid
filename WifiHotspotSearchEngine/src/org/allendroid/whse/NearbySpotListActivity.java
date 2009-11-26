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
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.util.Log;

public class NearbySpotListActivity extends Activity{
	protected static final int NETWORK_CONNECTION_FAILED_MSG = 0;
	protected static final int UPLOAD_HOTSPOTSTORE_DONE_MSG = 1;
	protected static final int UPDATE_HOTSPOT_LIST_MSG = 2;
	protected static final String TAG = "NearbySpotListActivity";

	private ImageView menuImageView;
	private TextView currentLocation;
	private ListView nearbySpotList;
	HotspotStore[] hotspotStoreArray;
	WifiInternet wi;

	@Override
	 public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
	     setContentView(R.layout.nearbyspotlist);         
	     getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
	     
	     final Context context =NearbySpotListActivity.this;
	        
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
	        
	        currentLocation = (TextView)findViewById(R.id.currentLocation);
	        nearbySpotList = (ListView)findViewById(R.id.nearbySpotList);
	        
	        currentLocation.setText("Your Current Location:");
	        wi = new WifiLocal();
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		refreshHotspotStoreList();
		Log.i(TAG,"onStart................");
	}
	
	
	BaseAdapter mAdapter = new BaseAdapter(){

		public int getCount() {
			// TODO Auto-generated method stub
			if( hotspotStoreArray != null)
				return hotspotStoreArray.length;
			else
				return 0;
			
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(hotspotStoreArray!=null){
				RelativeLayout rl = new RelativeLayout(NearbySpotListActivity.this);
				View view = NearbySpotListActivity.this.getLayoutInflater().inflate(R.layout.wifiitemview ,rl);
				TextView wifiitem_des = (TextView) view.findViewById(R.id.wifiitem_des);				
				TextView wifiitem_account = (TextView) view.findViewById(R.id.wifiitem_account);
				ImageView canbewifiset = (ImageView) view.findViewById(R.id.canBeWifiSet);
				
				canbewifiset.setImageResource(android.R.drawable.sym_call_incoming);
					
				
				wifiitem_des.setText( hotspotStoreArray[position].getDescription());
				wifiitem_account.setText("SSID:"+hotspotStoreArray[position].getSsid()+" KEY:"+hotspotStoreArray[position].getKey());

				
				return view;
				}
			else
				return null;
				
		}
		
	};
	
	
	private void refreshHotspotStoreList(){
		final ProgressDialog pd = ProgressDialog.show(NearbySpotListActivity.this,
				getResources().getString(R.string.networkConnection),
				getResources().getString(R.string.getNearbySpotListFromNet));
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					hotspotStoreArray = wi.getTotalWifiStore();
					sendUpdateHotspotListMsg();
				}catch(IOException e){
					sendNetworkConnectFailMsg();
					e.printStackTrace();
				}finally{
					pd.dismiss();
				}
			}
			
		}).start();
	}

	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			switch(msg.what){
			case NETWORK_CONNECTION_FAILED_MSG:
				AlertDialog.Builder builder = new AlertDialog.Builder(NearbySpotListActivity.this).setTitle("Network").setMessage("Connection Failure");
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
				
				break;
				
			case UPDATE_HOTSPOT_LIST_MSG:
				nearbySpotList.setAdapter(mAdapter);
				Log.i(TAG,"UPDATE_HOTSPOT_LIST_MSG...");
				break;
			}
		}
	};

	protected void sendNetworkConnectFailMsg() {
		// TODO Auto-generated method stub
		Message msg = new Message();
		msg.what = NETWORK_CONNECTION_FAILED_MSG;
		handler.sendMessage(msg);
	}
	
	protected void sendUpdateHotspotListMsg() {
		// TODO Auto-generated method stub
		Message msg = new Message();
		msg.what = UPDATE_HOTSPOT_LIST_MSG;
		handler.sendMessage(msg);
	}
}
