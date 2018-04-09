package yohan.org.lab2_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText, ageEditText;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() { // when pushing the add button
            public void onClick(View v) {
                //get the name info and age info
                String name = nameEditText.getText().toString();
                String age = ageEditText.getText().toString();

                Intent myIntent = new Intent(MainActivity.this, NewActivity.class);

                //load the info(name, age) on Bundle
                Bundle myBundle = new Bundle();
                myBundle.putString("name", name);
                myBundle.putString("age", age);

                //load the bundle on intent
                myIntent.putExtras(myBundle);
                startActivity(myIntent);

                //initialize EditText
                nameEditText.setText("");
                ageEditText.setText("");
            }
        });
    }
}
