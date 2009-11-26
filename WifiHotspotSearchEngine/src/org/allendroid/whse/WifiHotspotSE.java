package org.allendroid.whse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class WifiHotspotSE extends Activity {
    private static final String TAG = "WifiHotspotSE";
    
	/** Called when the activity is first created. */
	ImageView menuImageView;
	//static boolean isMenuActivityOn = false;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
        setContentView(R.layout.main);         
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        
        final Context context = WifiHotspotSE.this;
        
        menuImageView = (ImageView)findViewById(R.id.iv_titlebar_menu);
        menuImageView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, MenuActivity.class);
				startActivity(intent);

			}
        	
        });
    }
}