package com.picknell.beep.desktop;

import com.jme3.system.AppSettings;
import com.picknell.beep.Beep;

/**
 * Desktop launcher for the shared BEEP simulation.
 */
public final class DesktopLauncher {

    private DesktopLauncher() {
    }

    public static void main(String[] arguments) {
        Beep beep = new Beep();

        AppSettings settings = new AppSettings(true);
        settings.setTitle("Liam's BEEP");
        settings.setResolution(1280, 720);

        beep.setSettings(settings);
        beep.start();
    }
}
