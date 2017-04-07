package net.offbeatpioneer.demoapp.retrographicsengine;

import android.view.View;

import net.offbeatpioneer.demoapp.R;

/**
 * @author Dominik Grzelak
 * @since 05.04.2017
 */

public class SampleItem {

    private String description = "PLACEHOLDER DESCRIPTION";
    private String buttonText = "PLACEHOLDER BUTTON TEXT";
    int backgroundColor = R.color.card_colour_first;
    private View.OnClickListener clickListener;

    public static class Builder {
        public SampleItem create(String descr, String buttontext) {
            return new SampleItem(descr, buttontext);
        }

        public SampleItem create(String descr, String buttontext, int color) {
            return new SampleItem(descr, buttontext, color);
        }

        public SampleItem create(String descr, String buttontext, View.OnClickListener clickListener) {
            return new SampleItem(descr, buttontext, R.color.card_colour_first, clickListener);
        }

        public SampleItem create(String descr, int color, View.OnClickListener clickListener) {
            return new SampleItem(descr, "", color, clickListener);
        }
    }

    public SampleItem(String description, String buttonText) {
        this(description,
                buttonText,
                R.color.card_colour_first);
    }

    public SampleItem(String description, String buttonText, int backgroundColor) {
        this(
                description,
                buttonText,
                backgroundColor, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
    }

    public SampleItem(String description, String buttonText, int backgroundColor, View.OnClickListener clickListener) {
        this.description = description;
        this.buttonText = buttonText;
        this.backgroundColor = backgroundColor;
        this.clickListener = clickListener;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public View.OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
