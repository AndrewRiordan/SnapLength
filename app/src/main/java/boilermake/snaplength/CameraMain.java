package boilermake.snaplength;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CameraMain extends AppCompatActivity implements SensorEventListener {
    Camera camera;
    ShowCamera showCamera;
    FrameLayout frameLayout;
    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private Sensor mSensorMagnetometer;
    static double height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam);
        frameLayout = (FrameLayout)findViewById(R.id.frameLayout);
        camera = Camera.open();
        showCamera = new ShowCamera(this, camera);
        frameLayout.addView(showCamera);
        mSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        mSensorAccelerometer = mSensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER);
        mSensorMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


        /*
                //Intent starter = new Intent(context, CameraMain.class);
        //starter.putExtra();
        //context.startActivity(starter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }



    protected double getDistanceFromHorizon(double theta, double height){
        return Math.abs(height * Math.tan(theta));
    }
    public static void setHeight(double feet, double inches){
        height = (feet * 12) + inches;
    }
    public String convertInchesToFeet(double inches){
        return ((int)inches / 12)+"'"+((String.format("%.2f",inches % 12)));
    }
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
        TextView distance = (TextView)findViewById(R.id.distance);
        float[] rotationMatrix = new float[9];
        //float[] inclinationMatri = new float[9];
        if(SensorManager.getRotationMatrix(rotationMatrix,null, mAccelerometerData,mMagData)){
            distance.setText(""+convertInchesToFeet(getDistanceFromHorizon(SensorManager.getOrientation(rotationMatrix, sensorEvent.values)[1],height)));
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

