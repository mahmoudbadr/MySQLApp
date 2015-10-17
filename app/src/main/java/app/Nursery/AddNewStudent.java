package app.Nursery;
/***********
ParallelCodes.com
*************/

import android.annotation.SuppressLint;
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
import java.sql.SQLException;
import java.sql.Statement;

public class AddNewStudent extends ActionBarActivity {

    Button add;
    TextView errorlbl;
    EditText name, address, pincode, classid, email, age, gender, nationality, socialnumber, birthdate, joindate, motherlanguage;
    java.sql.Connection connect;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_student);

        add = (Button) findViewById(R.id.btnadd);

        errorlbl = (TextView) findViewById(R.id.lblerror);

        name = (EditText) findViewById(R.id.txtname);
        address = (EditText) findViewById(R.id.txtaddress);
        pincode = (EditText) findViewById(R.id.txtpincode);



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
                            .prepareStatement("insert into studentRecord(Name,Address,Pincode) values ('"
                                    + name.getText().toString()
                                    + "','"
                                    + address.getText().toString()
                                    + "','"
                                    + pincode.getText().toString() + "')");
                    preparedStatement.executeUpdate();
                    errorlbl.setText("Data Added successfully");
                } catch (SQLException e) {
                    errorlbl.setText(e.getMessage().toString());
                }

            }
        });

    }
}
