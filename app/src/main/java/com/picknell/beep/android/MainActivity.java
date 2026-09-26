package com.picknell.beep.android;

import com.jme3.app.AndroidHarness;

/**
 * Thin Android launcher for the shared BEEP simulation.
 */
public final class MainActivity extends AndroidHarness {

    public MainActivity() {
        appClass = "com.picknell.beep.Beep";
        screenFullScreen = true;
        screenShowTitle = false;
        exitDialogTitle = "Exit BEEP?";
        exitDialogMessage = "Quit simulation?";
    }
}
