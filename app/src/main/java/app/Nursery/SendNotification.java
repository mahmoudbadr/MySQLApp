package app.Nursery;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SendNotification extends Activity {

	Button buttonSend;
	EditText textPhoneNo;
	EditText textSMS;

	Button add;
	TextView errorlbl;
	EditText name, address, pincode, subjects, classes;
	Connection connect;
	PreparedStatement preparedStatement;
	Statement st;
	String ipaddress, db, username, password;

	@SuppressLint("NewApi")
	private Connection ConnectionHelper(String user, String password,
										String database, String server) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		Connection connection = null;
		String ConnectionURL = null;
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			ConnectionURL = "jdbc:jtds:sqlserver://" + server + ";"
					+ "databaseName=" + database + ";user=" + user
					+ ";password=" + password + ";";
			connection = DriverManager.getConnection(ConnectionURL);
		} catch (SQLException se) {
			Log.e("ERRO", se.getMessage());
		} catch (ClassNotFoundException e) {
			Log.e("ERRO", e.getMessage());
		} catch (Exception e) {
			Log.e("ERRO", e.getMessage());
		}
		return connection;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_notification);

		buttonSend = (Button) findViewById(R.id.buttonSend);
		textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
		textSMS = (EditText) findViewById(R.id.editTextSMS);
		add = (Button) findViewById(R.id.btnaddnoti);

		ipaddress = "192.168.1.4:1433";
		db = "Nursery";
		username = "sa";
		password = "P@ssw0rd";
		connect = ConnectionHelper(username, password, db, ipaddress);
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					st = connect.createStatement();

					preparedStatement = connect
						.prepareStatement("select mobilenumber from Parents where studentid = '"
									+ textPhoneNo.getText().toString() + "')");
					preparedStatement.executeQuery();

					/*ResultSet reset = st.executeQuery("select mobilenumber from Parents where studentid = '"
							+ textPhoneNo.getText().toString() + "')");

					errorlbl.setText(reset.toString());*/
				} catch (SQLException e) {
					//errorlbl.setText(e.getMessage().toString());
				}

			}
		});

		buttonSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String phoneNo = textPhoneNo.getText().toString();
				String sms = textSMS.getText().toString();

				try {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNo, null, sms, null, null);
					Toast.makeText(getApplicationContext(), "SMS Sent!",
							Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"SMS failed, please try again later!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

			}
		});

	}
}