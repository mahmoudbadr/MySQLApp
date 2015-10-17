package app.Nursery;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class CreateUserActivity extends Activity {

	EditText etUserid, etPassword, etUserTypeID;
	Button btnCreateUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_user);
		// Show the Up button in the action bar.
		setupActionBar();

	
		etUserid = (EditText) findViewById(R.id.et_cu_userid);
		etPassword = (EditText) findViewById(R.id.et_cu_password);
		etUserTypeID = (EditText) findViewById(R.id.et_cu_usertypeid);
		btnCreateUser=(Button) findViewById(R.id.btn_createuser);

		btnCreateUser.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String  User_id, password, user_type_id;


				User_id = etUserid.getText().toString();
				password = etPassword.getText().toString();
				user_type_id = etUserTypeID.getText().toString();

				UserDetailsTable userDetail = new UserDetailsTable(User_id, password, user_type_id);
				
				
				new AsyncCreateUser().execute(userDetail);

			}
		});

	}

	protected class AsyncCreateUser extends
			AsyncTask<UserDetailsTable, Void, Void> {

		@Override
		protected Void doInBackground(UserDetailsTable... params) {

			RestAPI api = new RestAPI();
			try {

				api.CreateNewAccount(params[0].getUserid(),
						params[0].getPassword(),params[0].getUsertypeid());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.d("AsyncCreateUser", e.getMessage());

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			Intent i = new Intent(CreateUserActivity.this, LoginActivity.class);
			startActivity(i);
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
		getMenuInflater().inflate(R.menu.create_user, menu);
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
