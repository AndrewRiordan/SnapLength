package boilermake.snaplength;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    // Detect and Access Camera
    // Create a Preview Class
    // Build a Preview Layout
    // Setup Listeners for Capture
    // Capture and Save Files
    // Release the Camera
    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private Sensor mSensorMagnetometer;

    Camera camera;
    FrameLayout frameLayout;
    ShowCamera showCamera;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        // open the camera
        camera = Camera.open();

        showCamera = new ShowCamera(this, camera);
        frameLayout.addView(showCamera);


        // System.out.println("init");

        mSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        mSensorAccelerometer = mSensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER);
        mSensorMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    /** Check whether or not device is equipped w camera */
    /*private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }*/

    /** Gets instance of Camera object */
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

    protected double getDistanceFromHorizon(double theta, double height){
        return height * Math.sin(theta);
    }
    //Additional Camera Stuff(lines 52-150)


































































































    //END of camera
    //Gysrocope stuff lines 152-250



    @Override
    protected void onStart() {
        super.onStart();

        // Listeners for the sensors are registered in this callback and
        // can be unregistered in onStop().
        //
        // Check to ensure sensors are available before registering listeners.
        // Both listeners are registered with a "normal" amount of delay
        // (SENSOR_DELAY_NORMAL).
        if (mSensorAccelerometer != null) {
            mSensorManager.registerListener(this, mSensorAccelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(mSensorMagnetometer != null){
            mSensorManager.registerListener(this, mSensorMagnetometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Unregister all sensor listeners in this callback so they don't
        // continue to use resources when the app is stopped.
        mSensorManager.unregisterListener(this);
    }
    float[] mAccelerometerData = new float[3];
    float[] mMagData = new float[3];
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        switch (sensorType){
            case Sensor.TYPE_ACCELEROMETER:
                mAccelerometerData = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mMagData = sensorEvent.values.clone();
                break;
            default:
                return;
        }
        FrameLayout helloTextView = findViewById(R.id.camera_preview);
        float[] rotationMatrix = new float[9];
        //float[] inclinationMatrix = new float[9];
        if(SensorManager.getRotationMatrix(rotationMatrix,null, mAccelerometerData,mMagData)){
            String s = ""+ (SensorManager.getOrientation(rotationMatrix, sensorEvent.values))[1];
            // helloTextView.set(s);
        }

    }

    /**
     * Must be implemented to satisfy the SensorEventListener interface;
     * unused in this app.
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        System.out.println("OnAccuracyChanged called");
    }
}


