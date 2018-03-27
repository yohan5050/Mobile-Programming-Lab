package yohan.org.lab1_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public EditText edit_name;

    public Button btn_print;
    public Button btn_clear;
    public TextView view_print;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        edit_name = findViewById(R.id.editText1);
        btn_print = findViewById(R.id.printBtn);
        btn_clear = findViewById(R.id.clearBtn);
        view_print = findViewById(R.id.text1);
    }

    public void clearClicked(View v) {
        edit_name.setText("");
        view_print.setText("contents");
    }

    public void printClicked(View v) {
        String text = "";
        text = edit_name.getText().toString();
        view_print.setText(text);
    }
}