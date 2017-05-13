package com.collabeat.collabeat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    int[] buttonIds = { R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11,
            R.id.button12, R.id.button13, R.id.button14, R.id.button15, R.id.button16,
    };

    private final int beatGridWidth = buttonIds.length;

    List<BeatButton> buttons = new ArrayList<>();
    private final Object lock = new Object();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        for (int buttonId : buttonIds) {
            ImageButton imageButton = (ImageButton)findViewById(buttonId);
            buttons.add(new BeatButton(imageButton));
        }

        View beatPanel = findViewById(R.id.beatpanel);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        scheduleRendering();
    }

    int time = 0;
    Handler handler = new Handler();
    Runnable rendering = () -> {
        // Update
        synchronized (lock) {
            updateWhichButtonIsHighlighted();
        }
        time += 1;
        time %= 16;

        // Trigger again
        scheduleRendering();
    };

    private void updateWhichButtonIsHighlighted(){
        // Clear all to default
        for(BeatButton button : buttons){
            ImageButton imageButton = button.getImageButton();
            boolean on = button.getToggleOn();
            if (on) {
                imageButton.setImageDrawable(getDrawable(R.drawable.light_blue_button));
            } else {
                imageButton.setImageDrawable(getDrawable(R.drawable.blue_button));
            }
        }

        // Update
        buttons.get(time).getImageButton().setImageDrawable(getDrawable(R.drawable.pink_button));

    }

    private void scheduleRendering() {
        handler.removeCallbacks(rendering);
        handler.postDelayed(rendering, 200);
    }
}
