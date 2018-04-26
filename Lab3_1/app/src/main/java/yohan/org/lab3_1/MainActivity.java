package yohan.org.lab3_1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn = findViewById(R.id.button);

        // register the button to which the context menu should be associated
        registerForContextMenu(mBtn);
    }

    // when the registered view(button) receives a long-click event, the system calls this method
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // set menu's title
        menu.setHeaderTitle("Button Menu");

        // add menu items
        // menu.add(groupId, itemId, order, title);
        menu.add(0, 0, 0 , "Red");
        menu.add(0, 1, 1, "Green");
        menu.add(0, 2, 2, "Blue");

    }

    // when the user selects a menu item, the system calls this method
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId(); // get item id

        if(itemId == 0) {
            mBtn.setTextColor(Color.RED);
        }
        else if(itemId == 1) {
            mBtn.setTextColor(Color.GREEN);
        }
        else if(itemId == 2) {
            mBtn.setTextColor(Color.BLUE);
        }

        return true;
    }
}
