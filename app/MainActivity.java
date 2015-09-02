package app.mysqlapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

	Button execute, delete, update;
	EditText valuetxt, proid;
	ListView Lista;
	TextView formatTxt, contentTxt;
	TextView i;
	Connection connect;
	SimpleAdapter AD;

	private void declarar() {
		execute = (Button) findViewById(R.id.insertbtn);
		valuetxt = (EditText) findViewById(R.id.txt_value);
		Lista = (ListView) findViewById(R.id.list_output);
		delete = (Button) findViewById(R.id.deletebtn);
		update = (Button) findViewById(R.id.updatebtn);
		proid = (EditText) findViewById(R.id.txt_proid);

		formatTxt = (TextView) findViewById(R.id.format);
		contentTxt = (TextView) findViewById(R.id.bvalue);

	}

	private void inicializar() {
		declarar();
		//valuetxt.setText("SELECT * FROM barcodetable");
		connect = CONN("sa", "P@ssw0rd", "Nursery", "MAHMOUDBADR");
	}

	@SuppressLint("NewApi")
	private Connection CONN(String _user, String _pass, String _DB,
			String _server) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		Connection conn = null;
		String ConnURL = null;
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			ConnURL = "jdbc:jtds:sqlserver://" + _server + ";"+ "databaseName=" + _DB + ";user=" + _user + ";password="+ _pass + ";";
			conn = DriverManager.getConnection(ConnURL);
		} catch (SQLException se) {
			Log.e("ERRO", se.getMessage());
		} catch (ClassNotFoundException e) {
			Log.e("ERRO", e.getMessage());
		} catch (Exception e) {
			Log.e("ERRO", e.getMessage());
		}

		return conn;
	}

	public void QuerySQL(String COMANDOSQL) {
		final ResultSet rs;
		try {

			Statement statement = connect.createStatement();
			rs = statement.executeQuery(COMANDOSQL);

			List<Map<String, String>> data = null;
			data = new ArrayList<Map<String, String>>();

			while (rs.next()) {
				Map<String, String> datanum = new HashMap<String, String>();
				datanum.put("A", rs.getString("B_ID"));
				datanum.put("B", rs.getString("B_Code"));
				data.add(datanum);
			}
			String[] from = { "A", "B" };
			int[] views = { R.id.txt_titulo, R.id.txt_conteudo };
			AD = new SimpleAdapter(this, data, R.layout.listrows, from, views);
			Lista.setAdapter(AD);

		} catch (Exception e) {
			Log.e("ERRO", e.getMessage());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity);

		inicializar();

		//String s = valuetxt.getText().toString();
		execute.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				QuerySQL("insert into barcodetable values ('"
						+ contentTxt.getText().toString() + "')");
				QuerySQL("select * from barcodetable");

			}
		});

		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				QuerySQL("delete from barcodetable where B_Id='"
						+ proid.getText().toString() + "'");
				QuerySQL("select * from barcodetable");

			}
		});

		update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				QuerySQL("update barcodetable set B_code='"
						+ valuetxt.getText().toString() + "' where B_ID='"
						+ proid.getText().toString() + "'");
				QuerySQL("select * from barcodetable");

			}
		});

	}



}