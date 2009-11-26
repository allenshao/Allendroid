package org.allendroid.whse;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MapViewActivity extends MapActivity{

	private final static String TAG = "MapViewActivity";
	
	private final static int MENU_IC_TOP_LEFT_X = 276;
	private final static int MENU_IC_TOP_LEFT_Y = 27;
	private final static int MENU_IC_BTM_RIGHT_X = 312;
	private final static int MENU_IC_BTM_RIGHT_Y = 46;
	
	private GestureDetector mGestureDetector;
	
	private ImageView menuImageView;
	private MapView mapView;
	private MapController mc;
	List<Overlay> mapOverlays;
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
	    setContentView(R.layout.mapviewactivity);         
	    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
	    
	    //not used
	    //mGestureDetector = new GestureDetector(this, new LearnGestureListener());
	    
	    final Context context =MapViewActivity.this;
        
        menuImageView = (ImageView)findViewById(R.id.iv_titlebar_menu);
        menuImageView.setOnClickListener(new OnClickListener(){
        //allen: I don't know why it takes no effect. So make views on touch event.
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, MenuActivity.class);
				//finish();
				startActivity(intent);

			}
        	
        });
        
        mapView = (MapView)findViewById(R.id.mapview);
        mapView.setTraffic(true);
        mc = mapView.getController();
        
        //GPS get current location
        Location loc;
        LocationManager locMan;
        LocationProvider locPro;
       
        locMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        loc = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        
        //register periodic location update 
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,  
        		 1000, 0, locationListener);  
        
        double lat = loc.getLatitude();
        double lon = loc.getLongitude();
        double altitude = loc.getAltitude();
        Log.i(TAG,"lat="+lat+" lon="+lon);
        //allen test
        lat = 31.004999;
        lon = 121.408600;
        
        GeoPoint gp = new GeoPoint((int) (lat*1000000), (int) (lon*1000000));
        mc.animateTo(gp); 
        mc.setZoom(12); 
        
        //set locate bitmap
        mapOverlays = mapView.getOverlays();
        MyLocationOverlay myLocationOverlay = new MyLocationOverlay(gp);
        mapOverlays.add(myLocationOverlay);
        mapView.getController().setCenter(gp);
	}
	
	private final LocationListener locationListener = new LocationListener() {
	    public void onLocationChanged(Location location) { 
	        // log it when the location changes
	        if (location != null) {
	            Log.i("SuperMap", "Location changed : Lat: "
	              + location.getLatitude() + " Lng: "
	              + location.getLongitude());
	        }
	    }

	    public void onProviderDisabled(String provider) {
	   
	    }

	    public void onProviderEnabled(String provider) {
	   
	    }

	    public void onStatusChanged(String provider, int status, Bundle extras) {
	    
	    }
	};
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event){
		
		/*if(mGestureDetector.onTouchEvent(event))
			return true;
		else
			return super.dispatchTouchEvent(event);*/
		
		String action = "";
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			action = "ACTION_DOWN";
			break;
		case MotionEvent.ACTION_UP:
			action = "ACTION_UP";
			//press menu icon
			final Context context =MapViewActivity.this;
			if((event.getX()>MENU_IC_TOP_LEFT_X)&&(event.getX()<MENU_IC_BTM_RIGHT_X)&&(event.getY()>MENU_IC_TOP_LEFT_Y)&&(event.getY()<MENU_IC_BTM_RIGHT_Y)){
				Intent intent = new Intent(context, MenuActivity.class);
				startActivity(intent);
			}
			
			break;
		case MotionEvent.ACTION_MOVE:
			action = "ACTION_MOVE";
			break;
		case MotionEvent.ACTION_CANCEL:
			action = "ACTION_CANCEL";
			break;
		} 
		Log.v("MotionEvent","action = " + action + ", " +"x = " + String.valueOf(event.getX()) + ", " +"y = " + String.valueOf(event.getY()));
		return super.dispatchTouchEvent(event);
	}
	
	//not used.
	/*class LearnGestureListener extends GestureDetector.SimpleOnGestureListener{
		@Override
		public boolean onSingleTapUp(MotionEvent ev) {
			Log.d("onSingleTapUp",ev.toString());
			//press menu icon
			final Context context =MapViewActivity.this;
			if((ev.getX()>MENU_IC_TOP_LEFT_X)&&(ev.getX()<MENU_IC_BTM_RIGHT_X)&&(ev.getY()>MENU_IC_TOP_LEFT_Y)&&(ev.getY()<MENU_IC_BTM_RIGHT_Y)){
				Intent intent = new Intent(context, MenuActivity.class);
				startActivity(intent);
			}
			
			
			return true;
		}
		
		@Override
		public void onShowPress(MotionEvent ev) {
			Log.d("onShowPress",ev.toString());
		}
		
		@Override
		public void onLongPress(MotionEvent ev) {
			Log.d("onLongPress",ev.toString());
		}
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			Log.d("onScroll",e1.toString());
			return true;
		}
		
		@Override
		public boolean onDown(MotionEvent ev) {
			Log.d("onDown",ev.toString());
			return true;
		}
		
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			Log.d("onFling e1",e1.toString());
			Log.d("onFling e2",e2.toString());
			return true;
		}
	}*/
	
	protected class MyLocationOverlay extends Overlay{
		GeoPoint geoPoint;
		private Bitmap bmp_MYLOCATE;
		
		public MyLocationOverlay(GeoPoint point) {
			super();
			this.geoPoint = point;
		}

		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
			super.draw(canvas, mapView, shadow);
			
			Point scrPoint = new Point();
			mapView.getProjection().toPixels(geoPoint, scrPoint);
			Paint paint = new Paint();
			bmp_MYLOCATE = BitmapFactory.decodeResource(getResources(), R.drawable.mylocation);
			canvas.drawBitmap(bmp_MYLOCATE, scrPoint.x, scrPoint.y, paint);
			
			return true;
		
		}
	}
	
	
/*	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		String action = "";
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			action = "ACTION_DOWN";
			break;
		case MotionEvent.ACTION_UP:
			action = "ACTION_UP";		
			break;
		case MotionEvent.ACTION_MOVE:
			action = "ACTION_MOVE";
			break;
		case MotionEvent.ACTION_CANCEL:
			action = "ACTION_CANCEL";
			break;
		}
		Log.v("DispatcTouchEvent==>MotionEvent","action = " + action + ", " +"x = " + String.valueOf(event.getX()) + ", " +"y = " + String.valueOf(event.getY()));
		return super.dispatchTouchEvent(event);
	}*/
}
