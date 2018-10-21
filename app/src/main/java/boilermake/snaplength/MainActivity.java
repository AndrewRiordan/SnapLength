package boilermake.snaplength;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    // Detect and Access Camera
    // Create a Preview Class
    // Build a Preview Layout
    // Setup Listeners for Capture
    // Capture and Save Files
    // Release the Camera
    //private SensorManager mSensorManager;
    /*private Sensor mSensorAccelerometer;
    private Sensor mSensorMagnetometer;*/
    private static int SPLASH_TIMEOUT = 4000;
    //static Camera camera;
    //static ShowCamera showCamera;
    //static FrameLayout frameLayout;

    static double height;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // open the camera
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, HomepageActivity.class);
                startActivity(homeIntent);
                finish();
                /*Intent homeIntent = new Intent(MainActivity.this, InstructionPage.class);
                startActivity(homeIntent);
                finish();*/

            }
        }, SPLASH_TIMEOUT);


        // System.out.println("init");

        /*mSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        mSensorAccelerometer = mSensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER);
        mSensorMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);*/
    }

    /** Check whether or not device is equipped w camera */
    /*private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }*/

    /**
     * Gets instance of Camera object
     */
    /*public static Camera getCameraInstance(){
        Camera c = null;

        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }*/
    protected double getDistanceFromHorizon(double theta, double height) {
        return height * Math.tan(theta);
    }
    //Additional Camera Stuff(lines 52-150)

}
