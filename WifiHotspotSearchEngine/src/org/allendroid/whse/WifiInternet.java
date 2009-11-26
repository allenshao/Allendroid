package org.allendroid.whse;

import java.io.IOException;

public interface WifiInternet {
	
	public void uploadAccounts(String description, String ssid, String key, int longitude, int latitude)throws IOException;
	
	public HotspotStore[] getTotalWifiStore()throws IOException;
}
