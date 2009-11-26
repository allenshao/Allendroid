package org.allendroid.whse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MenuActivity extends Activity{

	 private static final String TAG = "MenuActivity";
	 
	 public final static int MENU_LOGIN 			= 100;
	 public final static int MENU_GOOGLEMAP 		= 101;
	 public final static int MENU_UPLOADACCOUNT 	= 102;
	 public final static int MENU_QUICKSEARCH 		= 103;
	 public final static int MENU_NEARBYSPOTLIST 	= 104;

	 private static final int LAUNCH_UPLOAD_ACCOUNT_ACTIVITY = 0;
	 private static final int LAUNCH_NEARBY_SPOTLIST_ACTIVITY = 1;
	 private static final int LAUNCH_MAP_VIEW_ACTIVITY = 2;
	 
	 ImageView 							menuImageView;
	 static MenuActivity 				instance;
	 LinearLayout 						menuLayout;
/*	 LinearLayout 						menuLayout1;
	 LinearLayout 						menuLayout2;
	 LinearLayout 						menuLayout3;*/
	 /*drawViewMenuLogin 					dvLogin;
	 drawViewMenuGooglemap 				dvGooglemap;
	 drawViewMenuUploadaccount 			dvUploadaccount;
	 drawViewMenuQuickSearch 			dvQuicksearch;*/
/*	 drawViewMenuLoginGoogleMap			dvLogingooglemap;
	 drawViewMenuUploadQuickSearch		dvUploadquicksearch;
	 drawViewMenuNearbySpotList 		dvNearbyspotlist;*/
	 drawView dv;

	 {instance = this; }
	 
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
	     setContentView(R.layout.menuactivity);         
	     getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
	        
	     menuImageView = (ImageView)findViewById(R.id.iv_titlebar_menu);
	     
	     menuImageView.setOnClickListener(new OnClickListener(){
	    	 @Override
	    	 public void onClick(View v) {
	    		 // TODO Auto-generated method stub
	    		 finish();	
	    	 }
	     });
	     
	     menuLayout = (LinearLayout)findViewById(R.id.menulayout);
	     /*menuLayout1 = (LinearLayout)findViewById(R.id.menulayout1);
	     menuLayout2 = (LinearLayout)findViewById(R.id.menulayout2);
	     menuLayout3 = (LinearLayout)findViewById(R.id.menulayout3);*/
	     
	     /*dvLogingooglemap = new drawViewMenuLoginGoogleMap(this);
	     Animation anim1 = new TranslateAnimation (319, 0, 0, 0);
	     anim1.setDuration (200);
	     anim1.setInterpolator (new AccelerateInterpolator(3.0f));
	     dvLogingooglemap.setAnimation(anim1);
	     menuLayout.addView(dvLogingooglemap);
	     
	     
	     dvUploadquicksearch = new drawViewMenuUploadQuickSearch(this);
	     Animation anim2 = new TranslateAnimation (-100, 200, 0, 0);
	     anim2.setDuration (200);
	     anim2.setInterpolator (new AccelerateInterpolator(3.0f));
	     dvUploadquicksearch.setAnimation(anim2);
	     menuLayout.addView(dvUploadquicksearch);
	     
	     dvNearbyspotlist = new drawViewMenuNearbySpotList(this);
	     Animation anim3 = new TranslateAnimation (319, 0, 0, 0);
	     anim3.setDuration (200);
	     anim3.setInterpolator (new AccelerateInterpolator(3.0f));
	     dvNearbyspotlist.setAnimation(anim3);
	     menuLayout.addView(dvNearbyspotlist);*/
	     
	     dv = new drawView(this);
	     Animation anim3 = new TranslateAnimation (319, 0, 0, 0);
	     anim3.setDuration (500);
	     anim3.setInterpolator (new AccelerateInterpolator(3.0f));
	     dv.setAnimation(anim3);
	     menuLayout.addView(dv);
	     
	 }//end of onCreate
	
	public class drawView extends View{

		private Bitmap bmp_MENU_LOGIN;
		private int x_MENU_LOGIN, y_MENU_LOGIN;
		private Bitmap bmp_MENU_GOOGLEMAP;
		private int x_MENU_GOOGLEMAP, y_MENU_GOOGLEMAP;
		private Bitmap bmp_MENU_UPLOADACCOUNT;
		private int x_MENU_UPLOADACCOUNT, y_MENU_UPLOADACCOUNT;
		private Bitmap bmp_MENU_QUICKSEARCH;
		private int x_MENU_QUICKSEARCH, y_MENU_QUICKSEARCH;
		private Bitmap bmp_MENU_NEARBYSPOTLIST;
		private int x_MENU_NEARBYSPOTLIST, y_MENU_NEARBYSPOTLIST;
		
		
		public drawView(Context context){
			super(context);
			setFocusable(true);
			
			    //MENU_LOGIN:
				bmp_MENU_LOGIN = BitmapFactory.decodeResource(getResources(), R.drawable.menuactivity_login);
				x_MENU_LOGIN = 240;
				y_MENU_LOGIN = 50;
				
				//MENU_GOOGLEMAP:
				bmp_MENU_GOOGLEMAP = BitmapFactory.decodeResource(getResources(), R.drawable.menuactivity_googlemaps);
				x_MENU_GOOGLEMAP = 140;
				y_MENU_GOOGLEMAP = 120;
				
				//MENU_UPLOADACCOUNT:
				bmp_MENU_UPLOADACCOUNT = BitmapFactory.decodeResource(getResources(), R.drawable.menuactivity_uploadaccounts);
				x_MENU_UPLOADACCOUNT = 40;
				y_MENU_UPLOADACCOUNT = 190;
				
				//MENU_QUICKSEARCH:
				bmp_MENU_QUICKSEARCH = BitmapFactory.decodeResource(getResources(), R.drawable.menuactivity_quicksearch);
				x_MENU_QUICKSEARCH = 140;
				y_MENU_QUICKSEARCH = 260;
				
				//MENU_NEARBYSPOTLIST:
				bmp_MENU_NEARBYSPOTLIST = BitmapFactory.decodeResource(getResources(), R.drawable.menuactivity_nearbyspotlist);
				x_MENU_NEARBYSPOTLIST = 240;
				y_MENU_NEARBYSPOTLIST = 330;
				
		}
		
		@Override
		public void onDraw(Canvas canvas){
			super.onDraw(canvas);
			Paint paint = new Paint();
			canvas.drawBitmap(bmp_MENU_LOGIN, x_MENU_LOGIN, y_MENU_LOGIN, paint);
			canvas.drawBitmap(bmp_MENU_GOOGLEMAP, x_MENU_GOOGLEMAP, y_MENU_GOOGLEMAP, paint);
			canvas.drawBitmap(bmp_MENU_UPLOADACCOUNT, x_MENU_UPLOADACCOUNT, y_MENU_UPLOADACCOUNT, paint);
			canvas.drawBitmap(bmp_MENU_QUICKSEARCH, x_MENU_QUICKSEARCH, y_MENU_QUICKSEARCH, paint);
			canvas.drawBitmap(bmp_MENU_NEARBYSPOTLIST, x_MENU_NEARBYSPOTLIST, y_MENU_NEARBYSPOTLIST, paint);
		}
	}
	
	/*public class drawViewMenuGooglemap extends View{

		private Bitmap bmp_MENU_GOOGLEMAP;
		private int x_MENU_GOOGLEMAP, y_MENU_GOOGLEMAP;
		
		public drawViewMenuGooglemap(Context context){
			super(context);
			setFocusable(true);
			
				//MENU_GOOGLEMAP:
				bmp_MENU_GOOGLEMAP = BitmapFactory.decodeResource(getResources(), R.drawable.menuactivity_googlemaps);
				x_MENU_GOOGLEMAP = 200;
				y_MENU_GOOGLEMAP = 100;
			
		}
		
		@Override
		public void onDraw(Canvas canvas){
			super.onDraw(canvas);
			Paint paint = new Paint();
			canvas.drawBitmap(bmp_MENU_GOOGLEMAP, x_MENU_GOOGLEMAP, y_MENU_GOOGLEMAP, paint);
		}
	}*/
	
	/*public class drawViewMenuUploadQuickSearch extends View{

		private Bitmap bmp_MENU_UPLOADACCOUNT;
		private int x_MENU_UPLOADACCOUNT, y_MENU_UPLOADACCOUNT;
		private Bitmap bmp_MENU_QUICKSEARCH;
		private int x_MENU_QUICKSEARCH, y_MENU_QUICKSEARCH;
		
		public drawViewMenuUploadQuickSearch(Context context){
			super(context);
			setFocusable(true);

				//MENU_UPLOADACCOUNT:
				bmp_MENU_UPLOADACCOUNT = BitmapFactory.decodeResource(getResources(), R.drawable.menuactivity_uploadaccounts);
				x_MENU_UPLOADACCOUNT = 100;
				y_MENU_UPLOADACCOUNT = 200;
				
				//MENU_QUICKSEARCH:
				bmp_MENU_QUICKSEARCH = BitmapFactory.decodeResource(getResources(), R.drawable.menuactivity_quicksearch);
				x_MENU_QUICKSEARCH = 200;
				y_MENU_QUICKSEARCH = 200;
			
		}
		
		@Override
		public void onDraw(Canvas canvas){
			super.onDraw(canvas);
			Paint paint = new Paint();
			canvas.drawBitmap(bmp_MENU_UPLOADACCOUNT, x_MENU_UPLOADACCOUNT, y_MENU_UPLOADACCOUNT, paint);
			canvas.drawBitmap(bmp_MENU_QUICKSEARCH, x_MENU_QUICKSEARCH, y_MENU_QUICKSEARCH, paint);
		}
	}*/
	
	/*public class drawViewMenuQuickSearch extends View{

		private Bitmap bmp_MENU_QUICKSEARCH;
		private int x_MENU_QUICKSEARCH, y_MENU_QUICKSEARCH;
		
		public drawViewMenuQuickSearch(Context context){
			super(context);
			setFocusable(true);

				//MENU_QUICKSEARCH:
				bmp_MENU_QUICKSEARCH = BitmapFactory.decodeResource(getResources(), R.drawable.menuactivity_quicksearch);
				x_MENU_QUICKSEARCH = 200;
				y_MENU_QUICKSEARCH = 200;
		}
		
		@Override
		public void onDraw(Canvas canvas){
			super.onDraw(canvas);
			Paint paint = new Paint();
			canvas.drawBitmap(bmp_MENU_QUICKSEARCH, x_MENU_QUICKSEARCH, y_MENU_QUICKSEARCH, paint);
		}
	}*/
	
/*	public class drawViewMenuNearbySpotList extends View{

		private Bitmap bmp_MENU_NEARBYSPOTLIST;
		private int x_MENU_NEARBYSPOTLIST, y_MENU_NEARBYSPOTLIST;
		
		public drawViewMenuNearbySpotList(Context context){
			super(context);
			setFocusable(true);

				//MENU_NEARBYSPOTLIST:
				bmp_MENU_NEARBYSPOTLIST = BitmapFactory.decodeResource(getResources(), R.drawable.menuactivity_nearbyspotlist);
				x_MENU_NEARBYSPOTLIST = 150;
				y_MENU_NEARBYSPOTLIST = 300;
			
		}
		
		@Override
		public void onDraw(Canvas canvas){
			super.onDraw(canvas);
			Paint paint = new Paint();
			canvas.drawBitmap(bmp_MENU_NEARBYSPOTLIST, x_MENU_NEARBYSPOTLIST, y_MENU_NEARBYSPOTLIST, paint);
		}
	}*/


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		String action = "";
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			action = "ACTION_DOWN";
			break;
		case MotionEvent.ACTION_UP:
			action = "ACTION_UP";
			//Hot Area Response
			//UploadActivity
			if((event.getX()>243)&&(event.getX()<282)&&(event.getY()>107)&&(event.getY()<140)){
				
				/*if(!UploadAccountActivity.getInstance().isFinishing()){
					Log.i(TAG,"UploadAccountActivity not finish!");
					UploadAccountActivity.getInstance().finish();
				}*/
				//DrawView exit animation
				Animation anim3 = new TranslateAnimation (0,-320, 0, 0);
			    anim3.setDuration (500);
			    anim3.setInterpolator (new AccelerateInterpolator(3.0f));
			    dv.setAnimation(anim3);
			    dv.invalidate();
			    
			    dv.setVisibility(View.GONE);
			    
			    handler.sendEmptyMessageDelayed(LAUNCH_UPLOAD_ACCOUNT_ACTIVITY, 700);
			    
			    /*Intent intent = new Intent(MenuActivity.this, UploadAccountActivity.class);
				startActivity(intent);*/
				
				//finish();
			    
			}else if((event.getX()>243)&&(event.getX()<282)&&(event.getY()>388)&&(event.getY()<419)){
				Animation anim3 = new TranslateAnimation (0,-320, 0, 0);
			    anim3.setDuration (500);
			    anim3.setInterpolator (new AccelerateInterpolator(3.0f));
			    dv.setAnimation(anim3);
			    dv.invalidate();
			    
			    dv.setVisibility(View.GONE);
			    
			    handler.sendEmptyMessageDelayed(LAUNCH_NEARBY_SPOTLIST_ACTIVITY, 700);
			    
			}else if((event.getX()>40)&&(event.getX()<79)&&(event.getY()>245)&&(event.getY()<280)){
				Animation anim3 = new TranslateAnimation (0,-320, 0, 0);
			    anim3.setDuration (500);
			    anim3.setInterpolator (new AccelerateInterpolator(3.0f));
			    dv.setAnimation(anim3);
			    dv.invalidate();
			    
			    dv.setVisibility(View.GONE);
			    
			    handler.sendEmptyMessageDelayed(LAUNCH_MAP_VIEW_ACTIVITY, 700);
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
		return super.onTouchEvent(event);
	}

	Handler handler = new Handler(){
		public void handleMessage(Message msg){
			Intent intent;
			switch(msg.what){
			
				case LAUNCH_UPLOAD_ACCOUNT_ACTIVITY:				

					intent = new Intent(MenuActivity.this, UploadAccountActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
					startActivity(intent);
					finish();
					break;
					
				case LAUNCH_NEARBY_SPOTLIST_ACTIVITY:				

					intent = new Intent(MenuActivity.this, NearbySpotListActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
					startActivity(intent);
					finish();
					break;
					
				case LAUNCH_MAP_VIEW_ACTIVITY:				

					intent = new Intent(MenuActivity.this, MapViewActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
					startActivity(intent);
					finish();
					break;
			}
		}
	};
	
	static public MenuActivity getInstance(){
		return instance;
	}
}
