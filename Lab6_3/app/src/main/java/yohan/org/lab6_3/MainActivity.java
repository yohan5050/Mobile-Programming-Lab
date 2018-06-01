package yohan.org.lab6_3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    MySQLiteOpenHelper helper;
    EditText editTextName, editTextStudentNo;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 추가, 삭제 결과를 출력하는 list view
        listview = findViewById(R.id.listview);

        // db 생성
        helper = new MySQLiteOpenHelper(MainActivity.this, "person.db", null, 1);

        editTextName = findViewById(R.id.et_name);
        editTextStudentNo = findViewById(R.id.et_student_no);

        Button addBtn = findViewById(R.id.btn_insert);
        addBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nameStr = editTextName.getText().toString();
                String sNoStr = editTextStudentNo.getText().toString();

                // 추가시, 이름과 학번이 모두 입력되지 않으면 Toast message
                if(nameStr.replaceAll(" ", "").equals("") || sNoStr.replaceAll(" ", "").equals("")) {
                    Toast.makeText(MainActivity.this, "모든 항목을 입력해 주세요", Toast.LENGTH_LONG).show();
                }
                else { // 이름과 학번이 모두 입력되면 db에 저장하고 리스트뷰로 출력
                    // edit text clear
                    editTextName.setText("");
                    editTextStudentNo.setText("");

                    // 이름과 학번을 디비에 저장하고 list view에 추가
                    int sNo = Integer.parseInt(sNoStr);
                    insert(nameStr, sNo);
                     // list view update
                    invalidate();
                }
            }
        });

        Button deleteBtn = findViewById(R.id.btn_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nameStr = editTextName.getText().toString();

                //삭제시, 이름이 입력되지 않으면 Toast message
                if(nameStr.replaceAll(" ", "").equals("")) {
                    Toast.makeText(MainActivity.this, "이름을 입력해 주세요", Toast.LENGTH_LONG).show();
                }
                else { // 이름이 입력되었다면,
                    // edit text clear
                    editTextName.setText("");
                    editTextStudentNo.setText("");

                    // 이름에 해당하는 정보를 db에서 지우고, list view에서 제거
                    delete(nameStr);
                     // list view update
                    invalidate();
                }
            }
        });
    }

    public void insert(String name, int studentNo) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("studentNo", studentNo);
        db.insert("student", null, values);
    }

    public void delete(String name) {
        db = helper.getWritableDatabase();
        db.delete("student", "name=?", new String[]{name});
    }

    public String[] select() {
        db = helper.getReadableDatabase();
        Cursor c = db.query("student", null, null, null, null, null, null);
        /*
         * query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
         */

        String[] items = new String[c.getCount()];

        int count = 0;
        // db의 한 행씩 살펴 보면서 name, studentNo 열에 해당하는 값을 items 배열에 넣는다.
        while(c.moveToNext()) {
            items[count] = c.getString(c.getColumnIndex("name")) +  " " + c.getString(c.getColumnIndex("studentNo"));
            count++;
        }
        c.close();

        return items;
    }

    // list view 출력
    public void invalidate() {
        String[] items = select();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listview.setAdapter(adapter);
    }
}
