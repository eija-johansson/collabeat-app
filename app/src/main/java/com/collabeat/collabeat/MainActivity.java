package com.collabeat.collabeat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    int[] buttonIds = {
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
            R.id.button10,
            R.id.button11,
            R.id.button12,
            R.id.button13,
            R.id.button14,
            R.id.button15,
            R.id.button16,
    };

    private final int beatGridWidth = buttonIds.length;

    private Context context;

    List<ImageButton> buttons = new ArrayList<>();
    boolean[] toggleOns = new boolean[beatGridWidth];
    private final Object lock = new Object();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = getApplicationContext();

        setContentView(R.layout.main_layout);

        for (int i = 0; i < toggleOns.length; i++) {
            toggleOns[i] = false;
        }

        for (int buttonId : buttonIds) {
            buttons.add((ImageButton)findViewById(buttonId));
        }

        View beatPanel = findViewById(R.id.beatpanel);

        setupToggleButtonListeners(buttons);

        ImageButton imgButton = new ImageButton(this);
        imgButton.setImageDrawable(getResources().getDrawable(R.drawable.light_blue_button));

    }

    private void setupToggleButtonListeners(List<ImageButton> buttons) {
        for (int i = 0 ; i < buttons.size(); i++) {
            ImageButton button = buttons.get(i);
            final int idx = i;
            button.setOnClickListener((view) -> {
                updateToggleOn(idx);
            });
        }
    }

    private void updateToggleOn(final int i) {
        synchronized (lock) {
            toggleOns[i] = !toggleOns[i];
        }
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
        int i = 0;
        for(ImageButton button : buttons){
            boolean on = toggleOns[i];
            if (on) {
                button.setImageDrawable(getDrawable(R.drawable.light_blue_button));
            } else {
                button.setImageDrawable(getDrawable(R.drawable.blue_button));
            }
            i++;
        }

        // Update
        buttons.get(time).setImageDrawable(getDrawable(R.drawable.pink_button));

    }

    private void scheduleRendering() {
        handler.removeCallbacks(rendering);
        handler.postDelayed(rendering, 200);
    }
}
