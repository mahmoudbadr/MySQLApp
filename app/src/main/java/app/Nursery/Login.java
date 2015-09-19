package app.Nursery;
/***********
ParallelCodes.com
*************/

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends ActionBarActivity {

    Button loginbtn;
    TextView errorlbl;
    EditText edname, edpassword;
    java.sql.Connection connect;
    PreparedStatement preparedStatement;
    Statement st;
    String ipaddress, db, username, password;

    @SuppressLint("NewApi")
    private Connection ConnectionHelper(String user, String password,
                                        String database, String server ) {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginbtn = (Button) findViewById(R.id.btnlogin);

        errorlbl = (TextView) findViewById(R.id.lblerror);

        edname = (EditText) findViewById(R.id.txtname);
        edpassword = (EditText) findViewById(R.id.txtpassword);

        ipaddress = "192.168.1.7:1433";
        db = "Nursery";
        username = "sa";
        password = "P@ssw0rd";
        connect = ConnectionHelper(username, password, db, ipaddress);
        loginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    st = connect.createStatement();
                    ResultSet rs = st.executeQuery("select * from login where userid='" + edname.getText().toString() + "' and password='" + edpassword.getText().toString() + "'");
                    if (rs != null && rs.next()) {
                        Intent i = new Intent(Login.this, Explore.class);
                        startActivity(i);
                    } else {
                        errorlbl.setText("Sorry, wrong credentials!!!");
                    }


                } catch (SQLException e) {
                    errorlbl.setText(e.getMessage().toString());
                }

            }
        });

    }
}
