package yohan.org.lab5_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView1 = findViewById(R.id.textview1);
        textView2 = findViewById(R.id.textview2);

        // 버튼을 누르면 계산 시작
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FacCalculateTask().execute(); // Starts the FacCalculateTask
            }
        });
    }

    private class FacCalculateTask extends AsyncTask<Void, Long, Void> {

        long num, ans = 1;
        String sentence = new String();

        // 백그라운드 작업을 수행하기 전에 호출된다.
        // UI를 초기화하고, editText로부터 값을 받아온다.
        @Override
        protected void onPreExecute() {
            textView2.setText("= ?");
            num =  Integer.parseInt(editText.getText().toString());
        }

        // 분리된 스레드에서 백그라운드 작업을 한다.
        // 500ms마다 Factorial계산을 하면서 숫자를 onProgressUpdate로 넘겨준다.
        @Override
        protected Void doInBackground(Void... params) {
            for(long i = num; i > 0; i--) {
                try {
                    Thread.sleep(500);
                    publishProgress(i);
                    ans *= i;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        // 백그라운드 작업에서 넘겨준 값을 이용해 UI작업을 한다.
        // 500ms마다 백그라운드 스레드에서 보내주는 숫자를 화면에 보여준다.
        @Override
        protected void onProgressUpdate(Long... values) {
            sentence += (values[0].longValue() + " ");
            textView1.setText(sentence);
        }

        // 백그라운드 작업이 끝난 후 마지막으로 호출된다.
        // UI에서 계산 결과를 출력한다.
        @Override
        protected void onPostExecute(Void param) {
            textView2.setText("= " + ans);
        }
    }
}
