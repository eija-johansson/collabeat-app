package com.collabeat.collabeat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int beatGridMaxHeight = 7;

    private List<BeatRow> beatRows = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        View beatPanel = findViewById(R.id.beatpanel);
        beatRows.add(new BeatRow(getApplicationContext(), beatPanel, R.raw.tom_short));

        ImageButton addButton = (ImageButton)findViewById(R.id.add_sound);
        addButton.setOnClickListener(v -> {
            addSoundRow();
        });

    }

    private void addSoundRow() {
        if (beatRows.size() < beatGridMaxHeight) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            GridLayout parent = (GridLayout) findViewById(R.id.parentPanel);
            View addPanel = findViewById(R.id.add_panel);
            parent.removeView(addPanel);
            View beatPanelView = inflater.inflate(R.layout.beat_row, null);
            parent.addView(beatPanelView);
            beatRows.add(new BeatRow(getApplicationContext(), beatPanelView, R.raw.clap_808));
            if (beatRows.size() < beatGridMaxHeight) {
                parent.addView(addPanel);
            }
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
        updateWhichButtonIsHighlighted(time);
        time += 1;
        time %= 16;

        // Trigger again
        scheduleRendering();
    };

    private void updateWhichButtonIsHighlighted(int currentActiveButton){

        for (BeatRow row : beatRows) {
            row.updateButtons(currentActiveButton);
        }

    }

    private void scheduleRendering() {
        handler.removeCallbacks(rendering);
        handler.postDelayed(rendering, 200);
    }
}
