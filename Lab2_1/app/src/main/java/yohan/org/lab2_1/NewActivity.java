package yohan.org.lab2_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        //receive intent
        Intent passedIntent = getIntent();
        if(passedIntent != null) {
            //get the bundle including info(name, age) from intent
            Bundle myBundle = passedIntent.getExtras();

            //get the name and age from bundle
            String loginName = myBundle.getString("name");
            String loginAge = myBundle.getString("age");

            Toast.makeText(this, "Student info : " + loginName + ", " + loginAge, Toast.LENGTH_LONG).show();
        }

        Button closeBtn = findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //finish this activity.
                finish();
            }
        });
    }
}
