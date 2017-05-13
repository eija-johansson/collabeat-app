package com.collabeat.collabeat;

import android.content.Context;
import android.content.res.ObbInfo;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * TODO: write something
 */
public class BeatButton extends ImageButton
{
    Drawable imgOff;
    Drawable imgOn;
    Drawable imgOnCurrent;
    Drawable imgOffCurrent;

    ImageButton imageButton;

    private boolean toggleOn = false;

    private final Object lock = new Object();

    public BeatButton(Context context) {
        super(context);

        imgOff = context.getDrawable(R.drawable.blue_button);
        
    }

    public BeatButton(ImageButton button, Context context) {
        super(context);
        this.imageButton = button;
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
}
