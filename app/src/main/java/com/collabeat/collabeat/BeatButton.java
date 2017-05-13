package com.collabeat.collabeat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by eija on 2015-07-16.
 */
public class BeatButton extends ImageButton
{
    Drawable imgOff;
    Drawable imgOn;
    Drawable imgOnCurrent;
    Drawable imgOffCurrent;

    public BeatButton(Context context) {
        super(context);

        imgOff = context.getDrawable(R.drawable.blue_button);
        
    }
}
