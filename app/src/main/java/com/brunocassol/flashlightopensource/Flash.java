package com.brunocassol.flashlightopensource;

import android.hardware.Camera;
import java.util.List;

public class Flash {

    private Camera camera = null;
    private Camera.Parameters cameraParameters;
    private String previousFlashMode = null;
    List<String> supportedFlashModes = null;

    public void debug() {
        int s = supportedFlashModes.size();
        System.out.println(s);
    }

    public synchronized void on() {
        camera = Camera.open();
        if (camera != null) {
            cameraParameters = camera.getParameters();
            previousFlashMode = cameraParameters.getFlashMode();
            supportedFlashModes = cameraParameters.getSupportedFlashModes();

            cameraParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(cameraParameters);
            camera.startPreview();
        }
    }

    public synchronized void off() {
        if (camera != null) {
            cameraParameters = camera.getParameters();
            cameraParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(cameraParameters);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

}
