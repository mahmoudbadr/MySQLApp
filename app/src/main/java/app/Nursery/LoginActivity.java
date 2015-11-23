package app.Nursery;

import org.json.JSONObject;


import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class LoginActivity extends Activity {

    EditText etUserid, etPassword;
    ImageButton btnLogin;
    Context context;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        // Show the Up button in the action bar.
//		setupActionBar();


        // Initialize  the layout components
        context=this;
        etUserid = (EditText) findViewById(R.id.et_userid);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (ImageButton) findViewById(R.id.btn_Login);
        header = (TextView) findViewById(R.id.textView1);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "CaviarDreams.ttf");
        Typeface typeFace1 = Typeface.createFromAsset(getAssets(), "Elaines_hand_printing.ttf");

        etUserid.setTypeface(typeFace);
        etPassword.setTypeface(typeFace);
        header.setTypeface(typeFace1);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String User_id=etUserid.getText().toString();
                String password=etPassword.getText().toString();

                // Execute the AsyncLogin class
                new AsyncLogin().execute(User_id,password);

            }
        });



    }

    protected class AsyncLogin extends AsyncTask<String, JSONObject, Boolean> {

        String User_id=null;
        String password=null;
        @Override
        protected Boolean doInBackground(String... params) {

            RestAPI api = new RestAPI();
            boolean userAuth = false;
            try {


                // Call the User Authentication Method in API
                JSONObject jsonObj = api.UserAuthentication(params[0],
                        params[1]);

                //Parse the JSON Object to boolean
                JSONParser parser = new JSONParser();
                userAuth = parser.parseUserAuth(jsonObj);
                User_id=params[0];
                password=params[1];
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncLogin", e.getMessage());

            }
            return userAuth;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub


            //Check user validity
            if (result) {
                Intent i = new Intent(LoginActivity.this,
                        UserDetailsActivity.class);
                i.putExtra("User_id",User_id);
                i.putExtra("password",password);
                startActivity(i);
            }
            else
            {
                Toast.makeText(context, "Not valid Userid/password ",Toast.LENGTH_SHORT).show();
            }

        }

    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            //
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
