package yohan.org.lab1_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView; // blue android img
    ImageView imageView2; // red android img
    int imageIndex = 0; // whether the image is blue android or red android

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.blue_android);
        imageView2 = (ImageView) findViewById(R.id.red_android);
    }
    // the function called when 'change image' button is clicked
    public void onButton1Clicked(View v) {
        changeImage();
    }

    // the function that switch android image(red -> blue, blue -> red)
    private void changeImage() {
        if(imageIndex == 0) { // when the image is red android
            imageView.setVisibility(View.VISIBLE);  // set blue android image visible
            imageView2.setVisibility(View.INVISIBLE); // set red android image invisible

            imageIndex = 1;
        }

        else if(imageIndex == 1) { // when the image is blue android
            imageView.setVisibility(View.INVISIBLE); // set blue android image invisible
            imageView2.setVisibility(View.VISIBLE); // sete red android image visible

            imageIndex = 0;
        }
    }
}
