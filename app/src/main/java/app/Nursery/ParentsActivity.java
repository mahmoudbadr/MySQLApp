package app.Nursery;

import java.util.ArrayList;


import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class ParentsActivity extends Activity {

	ArrayAdapter<String> adapter;
	ListView listv;
	Context context;
	ArrayList<String> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dept);
		// Show the Up button in the action bar.
		setupActionBar();
		data = new ArrayList<String>();
		listv = (ListView) findViewById(R.id.lv_dept);
		context = this;

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
		listv.setAdapter(adapter);
		Toast.makeText(this,"Loading Please Wait..",Toast.LENGTH_SHORT).show();
		new AsyncLoadDeptDetails().execute();

	}

	protected class AsyncLoadDeptDetails extends
			AsyncTask<Void, JSONObject, ArrayList<ParentsTable>> {
		ArrayList<ParentsTable> ParentsTable = null;

		@Override
		protected ArrayList<ParentsTable> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			
			RestAPI api = new RestAPI();
			try {
				
				JSONObject jsonObj = api.GetParentsDetails();

				JSONParser parser = new JSONParser();

				ParentsTable = parser.parseParents(jsonObj);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.d("AsyncLoadDeptDetails", e.getMessage());

			}

			return ParentsTable;
		}

		@Override
		protected void onPostExecute(ArrayList<ParentsTable> result) {
			// TODO Auto-generated method stub

			for (int i = 0; i < result.size(); i++) {
				data.add(result.get(i).getid() + " " + result.get(i).getName());
			}

			adapter.notifyDataSetChanged();

			Toast.makeText(context,"Loading Completed",Toast.LENGTH_SHORT).show();	
		}

	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dept, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
