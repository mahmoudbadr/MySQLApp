package app.Nursery;


import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class UserDetailsActivity extends Activity {

	TextView tvuserid, tvpassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_details);
		// Show the Up button in the action bar.
		setupActionBar();

		tvuserid=(TextView)findViewById(R.id.tv_firstname);
		tvpassword=(TextView)findViewById(R.id.tv_lastname);

		Intent i=getIntent();
		String user_id=i.getStringExtra("User_id");
		String password=i.getStringExtra("password");

		new AsyncUserDetails().execute(user_id);
		tvuserid.setText(user_id);
		tvpassword.setText(password);


	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

		}
	}

	protected class AsyncUserDetails extends AsyncTask<String,Void,UserDetailsTable>
	{

		@Override
		protected UserDetailsTable doInBackground(String... params) {
			// TODO Auto-generated method stub
			UserDetailsTable userDetail=null;
			RestAPI api = new RestAPI();
			try {

				JSONObject jsonObj = api.GetUserDetails(params[0],params[0]);
				JSONParser parser = new JSONParser();
				userDetail = parser.parseUserDetails(jsonObj);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.d("AsyncUserDetails", e.getMessage());

			}

			return userDetail;
		}

		@Override
		protected void onPostExecute(UserDetailsTable result) {
			// TODO Auto-generated method stub


		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_details, menu);
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

	@Override
	public void onBackPressed() {

		Intent myIntent = new Intent(UserDetailsActivity.this, MainActivity.class);
		UserDetailsActivity.this.startActivity(myIntent);
	}

}
