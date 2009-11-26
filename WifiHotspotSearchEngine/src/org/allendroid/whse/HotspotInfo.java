package org.allendroid.whse;

public class HotspotInfo implements HotspotStore{
	
	private Long count;
	private Long id;
	private String description;
	private String ssid;
	private String key;
	private int latitude;
	private int longitude;
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return key;
	}
	@Override
	public String getSsid() {
		// TODO Auto-generated method stub
		return ssid;
	}
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
		this.description = description;
	}
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	@Override
	public void setKey(String key) {
		// TODO Auto-generated method stub
		this.key = key;
	}
	@Override
	public void setSsid(String ssid) {
		// TODO Auto-generated method stub
		this.ssid = ssid;
	}
	@Override
	public int getLatitude() {
		// TODO Auto-generated method stub
		return latitude;
	}
	@Override
	public int getLongitude() {
		// TODO Auto-generated method stub
		return longitude;
	}
	@Override
	public void setLatitude(int latitude) {
		// TODO Auto-generated method stub
		this.latitude = latitude;
	}
	@Override
	public void setLongitude(int longitude) {
		// TODO Auto-generated method stub
		this.longitude = longitude;
		
	}

}
