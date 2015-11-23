package app.Nursery;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class JSONParser {

	public JSONParser()
	{
	super();	
	}
	
	public ArrayList<ParentsTable> parseParents(JSONObject object)
	{
		ArrayList<ParentsTable> arrayList=new ArrayList<ParentsTable>();
		try {
			JSONArray jsonArray=object.getJSONArray("Value");
			JSONObject jsonObj=null;
			for(int i=0;i<jsonArray.length();i++)
			{
				jsonObj=jsonArray.getJSONObject(i);
				arrayList.add(new ParentsTable(jsonObj.getInt("parent_id"), jsonObj.getString("name")));
			}
			
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("JSONParser=>parseParent", e.getMessage());
		}
		return arrayList;
	}
	
	
	public boolean parseUserAuth(JSONObject object)
	{	boolean userAtuh=false;
			try {
				userAtuh= object.getBoolean("Value");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d("JSONPar=>parseUserAuth", e.getMessage());
			}
			
			return userAtuh;
	}
	
	public UserDetailsTable parseUserDetails(JSONObject object)
	{
		UserDetailsTable userDetail=new UserDetailsTable();
		
		try {
			JSONObject jsonObj=object.getJSONArray("Value").getJSONObject(0);
			
		
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("JSONPar=>parseUserDet", e.getMessage());
		}
		
		return userDetail;
			
	}
	
	
}
