package com.structure.app.structuremvp.views.customview;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.structure.app.structuremvp.R;

public class CustomActionBar {

    private AppCompatActivity activity;
    private View.OnClickListener clickListener;
    ImageView left_iv;
    ImageView right_iv;
    TextView center_tv;

    public CustomActionBar(AppCompatActivity activity) {
        this.activity = activity;
    }


    /**
     * Design and Set back button,forward button ,title with click action
     *
     * @param titleString
     * @param show_right
     * @param show_left
     * @param onClickListener
     */
    public void setActionbar(String titleString, boolean show_right, boolean show_left, View.OnClickListener onClickListener) {

        this.clickListener = onClickListener;
        ActionBar actionBar = activity.getSupportActionBar();

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_action_bar);

        View actionbarView = actionBar.getCustomView();

        Toolbar toolbar = (Toolbar) actionbarView.getParent();
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.setPadding(0, 0, 0, 0);

        left_iv = actionbarView.findViewById(R.id.left_iv);
        right_iv = actionbarView.findViewById(R.id.right_iv);
        center_tv = (TextView) actionbarView.findViewById(R.id.center_tv);


        if (titleString != null && !titleString.equalsIgnoreCase("")) {
            center_tv.setText(titleString);
            center_tv.setVisibility(View.VISIBLE);
        }

        if (show_right) {
            left_iv.setVisibility(View.VISIBLE);
            left_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (clickListener != null) {
                        clickListener.onClick(v);
                    } else {
                        activity.onBackPressed();
                    }
                }
            });
        }

        if (show_left) {
            right_iv.setVisibility(View.VISIBLE);
            right_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onClick(v);
                    }

                }
            });
        }
    }


    public void setLeftIcon(int id) {
        left_iv.setImageResource(id);
    }

    public void setRightIcon(int id) {
        right_iv.setImageResource(id);
    }

}
