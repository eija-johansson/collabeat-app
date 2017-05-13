package com.collabeat.collabeat;

import android.widget.ImageButton;

/**
 * TODO: write something
 */
public class BeatButton
{
    private final ImageButton imageButton;

    private boolean toggleOn = false;

    private final Object lock = new Object();

    public BeatButton(ImageButton button) {
        this.imageButton = button;

        setupOnClickListener();
    }

    public void toggle() {
        synchronized (lock) {
            toggleOn = !toggleOn;
        }
    }

    public boolean getToggleOn() {
        synchronized (lock) {
            return toggleOn;
        }
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void setupOnClickListener() {
        imageButton.setOnClickListener((view) -> {
            this.toggle();
        });
    }

}
