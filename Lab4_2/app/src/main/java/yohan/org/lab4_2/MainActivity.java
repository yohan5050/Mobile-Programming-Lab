package yohan.org.lab4_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvBlue, tvGreen;
    Button btnOpen, btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBlue = findViewById(R.id.tvBlue);
        tvGreen = findViewById(R.id.tvGreen);

        btnOpen = findViewById(R.id.btnOpen);
        btnClose = findViewById(R.id.btnClose);

        // when open button is clicked
        btnOpen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left);
                // gree page become visible
                tvGreen.setVisibility(View.VISIBLE);
                // green page is moved to left
                tvGreen.startAnimation(anim);
                // open button disappears
                btnOpen.setVisibility(View.GONE);
                // close button appears
                btnClose.setVisibility(View.VISIBLE);
            }
        });

        // when close button is clicked
        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right);
                // green page is moved to right
                tvGreen.startAnimation(anim);
                // green page disappears
                tvGreen.setVisibility(View.GONE);
                // close button disappears
                btnClose.setVisibility(View.GONE);
                // open button appears
                btnOpen.setVisibility(View.VISIBLE);
            }
        });
    }
}
