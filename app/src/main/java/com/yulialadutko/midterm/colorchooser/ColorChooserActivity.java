package com.yulialadutko.midterm.colorchooser;

//Yulia Ladutko
//yulialadutko@my.smccd.edu
//CIS 135
//ColorChooserActivity.java
//Midterm
//03/14/2015

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.graphics.Color;
import android.content.SharedPreferences.Editor;


public class ColorChooserActivity extends ActionBarActivity
        implements OnSeekBarChangeListener {

    // instance variables
    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;
    private SeekBar alphaSeekBar;
    private View colorView;
    private TextView colorTextView;

    private int redValue = 0;
    private int greenValue = 0;
    private int blueValue = 0;
    private int alphaValue = 0;

    // define the SharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_chooser);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        // get references to the widgets
        redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);
        alphaSeekBar = (SeekBar) findViewById(R.id.alphaSeekBar);
        colorView = (View) findViewById(R.id.colorView);
        colorTextView = (TextView) findViewById(R.id.colorTextView);

        // set the listeners
        redSeekBar.setOnSeekBarChangeListener(this);
        greenSeekBar.setOnSeekBarChangeListener(this);
        blueSeekBar.setOnSeekBarChangeListener(this);
        alphaSeekBar.setOnSeekBarChangeListener(this);

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

    }
    @Override
    public void onPause() {

        // save the instance variables
        Editor editor = savedValues.edit();

        editor.putInt("redSeekBarProgress", redSeekBar.getProgress());
        editor.putInt("greenSeekBarProgress", greenSeekBar.getProgress());
        editor.putInt("blueSeekBarProgress", blueSeekBar.getProgress());
        editor.putInt("alphaSeekBarProgress", alphaSeekBar.getProgress());
        editor.commit();
        super.onPause();
    }

    @Override
    public void onResume() {

        super.onResume();

        // get the instance variables
        int redProgress = savedValues.getInt("redSeekBarProgress", 0);
        int greenProgress = savedValues.getInt("greenSeekBarProgress", 0);
        int blueProgress = savedValues.getInt("blueSeekBarProgress", 0);
        int alphaProgress = savedValues.getInt("alphaSeekBarProgress", 0);
        redSeekBar.setProgress(redProgress);
        greenSeekBar.setProgress(greenProgress);
        blueSeekBar.setProgress(blueProgress);
        alphaSeekBar.setProgress(alphaProgress);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

        this.redValue = redSeekBar.getProgress();
        this.greenValue = greenSeekBar.getProgress();
        this.blueValue = blueSeekBar.getProgress();
        this.alphaValue = alphaSeekBar.getProgress();

        this.colorView.setBackgroundColor(Color.argb(
                alphaSeekBar.getProgress(), redSeekBar.getProgress(),
                greenSeekBar.getProgress(), blueSeekBar.getProgress()));

        String red = Integer.toString(redSeekBar.getProgress());
        String green = Integer.toString(greenSeekBar.getProgress());
        String blue = Integer.toString(blueSeekBar.getProgress());
        String alpha = Integer.toString(alphaSeekBar.getProgress());

        colorTextView.setText("Red: " + red + " Green: "+ green + " Blue: "
                + blue + " Alpha: " + alpha);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar){

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar){

    }
}
