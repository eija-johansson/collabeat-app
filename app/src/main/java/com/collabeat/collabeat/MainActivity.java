package com.collabeat.collabeat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    ImageButton[] buttons = new ImageButton[16];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        buttons[0] = (ImageButton)findViewById(R.id.button1);
        buttons[1] = (ImageButton)findViewById(R.id.button2);
        buttons[2] = (ImageButton)findViewById(R.id.button3);
        buttons[3] = (ImageButton)findViewById(R.id.button4);
        buttons[4] = (ImageButton)findViewById(R.id.button5);
        buttons[5] = (ImageButton)findViewById(R.id.button6);
        buttons[6] = (ImageButton)findViewById(R.id.button7);
        buttons[7] = (ImageButton)findViewById(R.id.button8);
        buttons[8] = (ImageButton)findViewById(R.id.button9);
        buttons[9] = (ImageButton)findViewById(R.id.button10);
        buttons[10] = (ImageButton)findViewById(R.id.button11);
        buttons[11] = (ImageButton)findViewById(R.id.button12);
        buttons[12] = (ImageButton)findViewById(R.id.button13);
        buttons[13] = (ImageButton)findViewById(R.id.button14);
        buttons[14] = (ImageButton)findViewById(R.id.button15);
        buttons[15] = (ImageButton)findViewById(R.id.button16);

        View beatPanel = findViewById(R.id.beatpanel);

        /*TextView valueTV = new TextView(this);
        valueTV.setText("hallo hallo");
        valueTV.setId(5);
        //valueTV.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        ((LinearLayout) beatPanel).addView(valueTV);*/

        ImageButton imgButton = new ImageButton(this);
        imgButton.setImageDrawable(getResources().getDrawable(R.drawable.light_blue_button));

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        scheduleRendering();
    }

    int time = 0;
    Handler handler = new Handler();
    Runnable rendering = new Runnable() {
        @Override
        public void run(){

            // Update
            updateWhichButtonIsHighlighted();
            time += 1;
            time %= 16;

            // Trigger again
            scheduleRendering();
        }
    };

    private void updateWhichButtonIsHighlighted(){
        // Clear all to default
        for(ImageButton button : buttons){
            button.setImageDrawable(getResources().getDrawable(R.drawable.blue_button));
        }

        // Update
        buttons[time].setImageDrawable(getResources().getDrawable(R.drawable.pink_button));


    }

    private void scheduleRendering() {
        handler.removeCallbacks(rendering);
        handler.postDelayed(rendering, 200);
    }
}
