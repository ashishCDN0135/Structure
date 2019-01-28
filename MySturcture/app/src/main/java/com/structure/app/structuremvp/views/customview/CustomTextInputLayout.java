package com.structure.app.structuremvp.views.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;

public class CustomTextInputLayout extends TextInputLayout {

    private final String DEFAULT_FONT = "roboto_regular.ttf";

    public CustomTextInputLayout(Context context) {
        super(context);
        setCustomFont(context);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context);
    }

    public void setCustomFont(Context context) {
        try {
            Typeface face = Typeface.createFromAsset(context.getAssets(), DEFAULT_FONT);
            this.setTypeface(face);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
