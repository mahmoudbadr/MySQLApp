package app.Nursery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Mahmoud on 9/19/2015.
 */
public class Explore extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explore);

        Button NewStudent = (Button) findViewById(R.id.newstd);

        NewStudent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(Explore.this, AddNewStudent.class);
                startActivity(i);


            }
        });

        Button NewTeacher = (Button) findViewById(R.id.newtchr);

        NewTeacher.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(Explore.this, AddNewTeacher.class);
                startActivity(i);


            }
        });
    }
}
