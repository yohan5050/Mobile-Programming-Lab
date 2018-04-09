package yohan.org.lab2_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText urlEditText;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEditText = findViewById(R.id.urlEditText);
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //when pushing the next button
                String myUrl = urlEditText.getText().toString(); // get url from EditText

                //make intent
                Intent myIntent = new Intent(MainActivity.this, NewActivity.class);
                //put the url in the intent
                myIntent.putExtra("url", myUrl);

                startActivity(myIntent);

                //initialize urlEditText
                urlEditText.setText("");
            }
        });
    }
}
