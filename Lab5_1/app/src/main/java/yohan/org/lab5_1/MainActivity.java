package yohan.org.lab5_1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    EditText editText;
    Button button;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DogThread dog1 = new DogThread(1);
                DogThread dog2 = new DogThread(2);
                // 강아지1 애니메이션 시작
                dog1.start();
                // 강아지2 애니메이션 시작
                dog2.start();
            }
        });
    }

    // min이상 max미만의 랜덤 수를 생성해내는 메소드
    public int getRandomTime(int min, int max) {
        return min + (int)(Math.random() * (max - min));
    }

    class DogThread extends Thread {
        int dogIndex, stateIndex;
        ArrayList<Integer> images = new ArrayList<Integer>();

        public DogThread(int index) {
            dogIndex = index;
            images.add(R.drawable.dog_eating);
            images.add(R.drawable.dog_standing);
            images.add(R.drawable.dog_study);
        }

        public void run() {
            //stateIndex = 0;
            for(int i = 0; i < 9; i++) {
                final String msg = "dog #" + dogIndex + " state: " + stateIndex;

                // post(runnable)로 바로 처리할 수 있다.
                // Handler 의 post() method는 runnable 객체 내의 run()메소드를 실행하게 함
                handler.post(new Runnable() {
                    public void run() {
                        editText.append(msg + "\n");

                        if(dogIndex == 1) { // 강아지1의 쓰레드일 경우
                            imageView1.setImageResource(images.get(stateIndex));
                        }
                        else if(dogIndex == 2) { // 강아지2의 쓰레드일 경우
                            imageView2.setImageResource(images.get(stateIndex));
                        }
                    }
                });

                try {
                    //500ms이상 3000ms미만의 시간 동안 sleep
                    int sleepTime = getRandomTime(500, 3000);
                    sleep(sleepTime);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

                // 다음 상태로 넘어간다
                stateIndex++;
                // 마지막 상태였던 경우 그 다음 상태는 처음 상태이므로 처음 상태로 바꿔준다.
                if(stateIndex >= images.size()) {
                    stateIndex = 0;
                }
            }
        }
    }
}
