package com.brunocassol.flashlightopensource;

import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;


public class Flashlight extends ActionBarActivity {
    private Flash flash = new Flash();
    private ToggleButton toggleFlashlightButton;
    private int oldOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);
    }

    public void onToggleFlashLightClicked(View view) {
        //flash.debug();

        if (((ToggleButton)view).isChecked()) {
            flash.on();
            view.setKeepScreenOn(true);
        } else {
            flash.off();
            view.setKeepScreenOn(false);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        setRequestedOrientation(oldOrientation);
        flash.off();
    }

    @Override
    public void onResume() {
        super.onResume();
        oldOrientation = getRequestedOrientation();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        ToggleButton the_button = (ToggleButton) findViewById(R.id.toggleFlashlightButton);
        if (the_button.isChecked()) {
            flash.on();
            the_button.setKeepScreenOn(true);
        }
    }
}
