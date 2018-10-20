package boilermake.snaplength;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    // Detect and Access Camera
    // Create a Preview Class
    // Build a Preview Layout
    // Setup Listeners for Capture
    // Capture and Save Files
    // Release the Camera

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /** Check whether or not device is equipped w camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }



    /** Gets instance of Camera object */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // tries to get Camera instance
        }
        catch (Exception e) {
            // Camera not available for whatever reason
        }
        return c; //return null if camera is unavailable
    }



    protected double getDistanceFromHorizon(double theta, double height){
        return height * Math.sin(theta);
    }

}
