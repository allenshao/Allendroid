package org.allendroid.whse;

public interface HotspotStore {
	
	public Long getId();
	public String getSsid();
	public String getKey();
	public String getDescription();
	public int getLongitude();
	public int getLatitude();
	
	public void setId(Long id);
	public void setSsid(String ssid);
	public void setKey(String key);
	public void setDescription(String description);
	public void setLongitude(int longitude);
	public void setLatitude(int latitude);
}
