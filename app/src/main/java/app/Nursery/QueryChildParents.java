package app.Nursery;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 10/24/2015.
 */
public class QueryChildParents  extends Activity {

    TextView textview7;
    Button buttonParent;
    EditText editTextparent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parents_query);
        // Show the Up button in the action bar.
        setupActionBar();


        textview7 = (TextView) findViewById(R.id.textView7);
        editTextparent = (EditText) findViewById(R.id.editTextparent);
        buttonParent = (Button) findViewById(R.id.buttonParent);


        buttonParent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String Child_id;


                Child_id = editTextparent.getText().toString();


                ParentsTable childDetail = new ParentsTable(Child_id);


                new AsyncQueryChildParents().execute(childDetail);
                //textview7.setText(ParentsTable getChild_id());

            }
        });

    }

    protected class AsyncQueryChildParents extends
            AsyncTask<ParentsTable, Void, Void> {

        @Override
        protected Void doInBackground(ParentsTable... params) {

            RestAPI api = new RestAPI();
            try {

                api.GetParentsDetailsQuery(params[0].getChild_id());

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncCreateUser", e.getMessage());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {


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
