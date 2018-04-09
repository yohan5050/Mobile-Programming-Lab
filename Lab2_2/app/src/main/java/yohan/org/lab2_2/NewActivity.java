package yohan.org.lab2_2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    TextView urlTextView;
    Button goBtn;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        urlTextView = findViewById(R.id.urlTextView);
        goBtn = findViewById(R.id.goBtn);
        backBtn = findViewById(R.id.backBtn);

        //receive intent
        Intent passedIntent = getIntent();
        //get the url from intent , remove all space
        final String passedUrl = (passedIntent.getStringExtra("url")).replace(" ", "");

        urlTextView.setText(passedUrl);

        goBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!passedUrl.equals("")) { //if there is text
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + passedUrl));
                    startActivity(intent);
                }
                else { //if there is no text
                    Toast.makeText(getApplicationContext(), "주소를 다시 입력해 주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "뒤로가기 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                // finish this activity
                finish();
            }
        });
    }
}
