package com.collabeat.collabeat;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: write something
 */
public class BeatRow {

    int[] buttonIds = { R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11,
            R.id.button12, R.id.button13, R.id.button14, R.id.button15, R.id.button16,
    };

    private final int beatGridWidth = buttonIds.length;

    private Context context;

    List<BeatButton> buttons = new ArrayList<>();
    private List<MediaPlayer> players = new ArrayList<>(beatGridWidth);

    public BeatRow(Context context, View beatPanelView, int soundRes) {

        this.context = context;

        for (int buttonId : buttonIds) {
            ImageButton imageButton = (ImageButton)beatPanelView.findViewById(buttonId);
            buttons.add(new BeatButton(imageButton));
        }

        for (int i = 0; i < beatGridWidth ; i++) {
            MediaPlayer mediaPlayerClap = MediaPlayer.create(context, soundRes);
            mediaPlayerClap.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayerClap.setLooping(false);
            players.add(mediaPlayerClap);
        }
    }

    public void updateButtons(int currentActiveButton) {
        // Clear all to default
        for(BeatButton button : buttons){
            ImageButton imageButton = button.getImageButton();
            boolean on = button.getToggleOn();
            if (on) {
                imageButton.setImageDrawable(context.getDrawable(R.drawable.light_blue_button));
            } else {
                imageButton.setImageDrawable(context.getDrawable(R.drawable.blue_button));
            }
        }

        // Highlight current button and if button is on, play sound
        BeatButton currentButton = buttons.get(currentActiveButton);
        currentButton.getImageButton().setImageDrawable(context.getDrawable(R.drawable.pink_button));
        boolean playSound = currentButton.getToggleOn();
        if (playSound) {
            MediaPlayer mediaPlayer = players.get(currentActiveButton);
            mediaPlayer.start();
        }
    }
}
