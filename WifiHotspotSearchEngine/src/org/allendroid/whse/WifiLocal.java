package org.allendroid.whse;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Text;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

public class WifiLocal implements WifiInternet{
	private static final String TAG = "WifiLocal";
	private static final String hotspotInfoUrl="http://1.latest.allenpettle324.appspot.com/show";
	private static final String hotspotUploadUrl="http://1.latest.allenpettle324.appspot.com/upload";

	@Override
	public void uploadAccounts(String description, String ssid, String key, int longitude, int latitude) throws IOException {
		// TODO Auto-generated method stub
		URL url;
		try{
			url = new URL(hotspotUploadUrl);
			HttpURLConnection connection;
			connection = (HttpURLConnection)url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			//connection.setRequestProperty("METHOD", "POST");
			
			String hotspotInfo = "description="+description
								+"&ssid="+ssid+"&key="+key
								+"&longitude="+longitude
								+"&latitude="+latitude;
			Log.i(TAG,"hotspotInfo="+hotspotInfo);
			//connection.setConnectTimeout(20000);
			//connection.setReadTimeout(20000);
			connection.getOutputStream().write(hotspotInfo.getBytes());
			connection.getOutputStream().flush();
			connection.getOutputStream().close();
			Log.i(TAG,"Response code: "+connection.getResponseCode());
			connection.disconnect();
		}catch(ProtocolException e){
			e.printStackTrace();
		}catch(MalformedURLException e){
			e.printStackTrace();
		}
	}

	@Override
	public HotspotStore[] getTotalWifiStore() throws IOException {
		// TODO Auto-generated method stub
		HotspotStore[] hsList=null;
		try{
			URL url = new URL(hotspotInfoUrl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			InputStream is = connection.getInputStream();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			
			Document doc;
			doc = db.parse(is);
			doc.getDocumentElement().normalize();
			
			NodeList list1 = doc.getElementsByTagName("item");
			int len = list1.getLength();
			Log.i(TAG,"len = "+len);
			hsList = new HotspotStore[len];
			
			for(int i=0; i<len; i++){
				HotspotStore hs = new HotspotInfo();
				NodeList list2 = list1.item(i).getChildNodes();

				String description = getCData(list2.item(1).getFirstChild());
				String ssid = getCData(list2.item(2).getFirstChild());
				String key = getCData(list2.item(3).getFirstChild());
				
				Log.i(TAG,"description="+description+" ssid="+ssid+" key="+key);
				
				hs.setDescription(description);
				hs.setSsid(ssid);
				hs.setKey(key);
				hsList[i] = hs;
			}
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(ParserConfigurationException e){
			e.printStackTrace();
		}catch(SAXException e){
			e.printStackTrace();
		}
		return hsList;
	}

	private String getCData(Node node) {
		// TODO Auto-generated method stub
		int type;
    	type = node.getNodeType();
    	if(type == Node.CDATA_SECTION_NODE){
    		CDATASection cdata = (CDATASection) node;
       		return cdata.getData();
      	}
    	if(type == Node.TEXT_NODE){
    		Text text = (Text) node;
    		return text.getData();
	     }
		return null;
	}

}
