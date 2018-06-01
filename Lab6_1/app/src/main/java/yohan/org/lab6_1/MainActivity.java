package yohan.org.lab6_1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 외장 메모리 접근 권한 관련
        // 현재 사용자의 스마트폰에서 외장 메모리 접근이 허용되지 않은 경우
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                System.out.println("test here here?");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }

        editText = findViewById(R.id.et_text);
        Button btn1 = findViewById(R.id.write_sd_file);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // edit text에 입력된 내용을 얻어온다.
                String text = editText.getText().toString();

                // 외장 메모리에 접근
                File sdCard = Environment.getExternalStorageDirectory();
                File directory = new File(sdCard.getAbsolutePath() + "/MyFiles");
                directory.mkdirs();
                File file = new File(directory, "mysdfile.txt");
                try {
                    FileOutputStream fOut = new FileOutputStream(file);
                    OutputStreamWriter osw = new OutputStreamWriter(fOut);
                    BufferedWriter bw = new BufferedWriter(osw);
                    System.out.println("test : write : " + text);
                    // 얻어온 내용을 외장 메모리에 저장한다.
                    bw.write(text);
                    // 스트림 닫아주기
                    bw.close();
                } catch(FileNotFoundException ef) {
                    ef.printStackTrace();
                } catch(IOException eio) {
                    eio.printStackTrace();
                }

                Toast.makeText(MainActivity.this, "Done writing SD 'mysdfile.txt", Toast.LENGTH_LONG).show();


            }
        });
        Button btn2 = findViewById(R.id.clear_screen);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // edit text 부분을 초기화 한다.
                editText.setText("");
            }
        });
        Button btn3 = findViewById(R.id.read_sd_file);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 외장 메모리 접근
                File sdCard = Environment.getExternalStorageDirectory();
                File directory = new File(sdCard.getAbsolutePath() + "/MyFiles");
                directory.mkdirs();
                File file = new File(directory, "mysdfile.txt");
                try {
                    FileInputStream fIn = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fIn);
                    BufferedReader br = new BufferedReader(isr);
                    // 외장 메모리에 저장된 내용을 읽어온다.
                    String text;
                    while((text = br.readLine()) != null) {
                        System.out.println("test : read : " + text);
                        // 읽어온 내용을 edit text에 출력한다.
                        editText.append(text + "\n");
                    }
                    // 스트림 닫아주기
                    br.close();
                } catch(FileNotFoundException ef) {
                    ef.printStackTrace();
                } catch(IOException eio) {
                    eio.printStackTrace();
                }

                Toast.makeText(MainActivity.this, "Done reading SD 'mysdfile.txt", Toast.LENGTH_LONG).show();
            }
        });
        Button btn4 = findViewById(R.id.finish_app);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 앱을 종료한다.
                finish();
            }
        });
    }

    public void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResults) {
        switch(requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                // if request is cancelled, the result arrays are empty.
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                }
                else {
                    // permission denied
                }
                return;
        }
    }
}
