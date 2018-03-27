package yohan.org.lab1_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public EditText edit_name; // place to enter name

    public Button btn_print; // print button
    public Button btn_clear; // clear button
    public TextView view_print; // place to print result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    // the function initializing variables
    public void init() {
        edit_name = findViewById(R.id.editText1);
        btn_print = findViewById(R.id.printBtn);
        btn_clear = findViewById(R.id.clearBtn);
        view_print = findViewById(R.id.text1);
    }

    // the function called when clear button is clicked
    public void clearClicked(View v) {
        edit_name.setText(""); // set edit_name vacant
        view_print.setText("contents"); // set the view_print by "contents"
    }

    // the function called when print button is clicked
    public void printClicked(View v) {
        String text = "";
        text = edit_name.getText().toString(); // get the text from edit_name
        view_print.setText(text); // set the view_print by text
    }
}