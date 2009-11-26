package org.allendroid.whse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class MenuLayer {

	public void createMenuLayer(){
		

	}
	
	
	
}

class MenuDrawView extends View{
	
	public final static int MENU_LOGIN = 100;
	public final static int MENU_GOOGLEMAP = 101;
	public final static int MENU_UPLOADACCOUNT = 102;
	public final static int MENU_QUICKSEARCH = 103;
	public final static int MENU_NEARBYSPOTLIST = 104;
	
	private Bitmap bmp;
	private int x, y;
	
	public MenuDrawView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setFocusable(true);
	}
	
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		Paint paint = new Paint();
		/*switch(posState){
			case PosState.LEFT_TOP:{
				if(leftTopState == LEFT_TOP_STATE_ONE){
					Log.i(TAG,"leftTopState == LEFT_TOP_STATE_ONE");
					canvas.drawBitmap(bmp, 0, 0, paint);
				}
				else if(leftTopState == LEFT_TOP_STATE_TWO){
					Log.i(TAG,"leftTopState == LEFT_TOP_STATE_TWO");
					canvas.drawBitmap(bmp, 0, 189, paint);
				}
				
				break;
			}
		}*/
		canvas.drawBitmap(bmp, x, y, paint);
	}
}
