package yohan.org.lab6_2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextStudentNo, editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextStudentNo = findViewById(R.id.et_studentNo);
        editTextName = findViewById(R.id.et_name);

        Button showBtn = findViewById(R.id.btn_show);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // shared preference로부터 값을 읽어와 보여준다.
                SharedPreferences shPref = getSharedPreferences("student info", MODE_PRIVATE);
                if(shPref != null && shPref.contains("studentNo")) {
                    String studentNo = shPref.getString("studentNo", "no info");
                    editTextStudentNo.setText(studentNo);
                }
                if(shPref != null && shPref.contains("name")) {
                    String name = shPref.getString("name", "no name");
                    editTextName.setText(name);
                }
            }
        });


        Button saveBtn = findViewById(R.id.btn_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sNoStr = editTextStudentNo.getText().toString();
                String nameStr = editTextName.getText().toString();

                // shared preference를 이용해 저장한다.
                SharedPreferences shPref = getSharedPreferences("student info", MODE_PRIVATE);
                SharedPreferences.Editor editor = shPref.edit();
                editor.putString("studentNo", sNoStr);
                editor.putString("name", nameStr);
                editor.commit();
            }
        });

        Button initBtn = findViewById(R.id.btn_init);
        initBtn.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               // edit text를 초기화 한다.
               editTextStudentNo.setText("");
               editTextName.setText("");
           }
        });
    }
}
