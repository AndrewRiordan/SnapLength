package boilermake.snaplength;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.hardware.Camera.*;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {

    Camera camera;
    SurfaceHolder holder;

    public ShowCamera(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Camera.Parameters params = camera.getParameters();
        // change the orientation of the camera...
        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            params.set("orientation", "portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
        } else {
            params.set("orientation", "landscape");
            camera.setDisplayOrientation(0);
            params.setRotation(0);
        }
        List<String> focusModes = params.getSupportedFocusModes();
        if(params.getFocusMode().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO))
            if(params.getMaxNumMeteringAreas()>0) {
                List<Area> meteringAreas = new ArrayList<Area>();

                Rect areaRect1 = new Rect(-100, -100, 100, 100);    // specify an area in center of image
                meteringAreas.add(new Area(areaRect1, 600)); // set weight to 60%
                Rect areaRect2 = new Rect(800, -1000, 1000, -800);  // specify an area in upper right of image
                meteringAreas.add(new Area(areaRect2, 400)); // set weight to 40%
                params.setMeteringAreas(meteringAreas);
                List<Area> x = params.getFocusAreas();
                int k = x.size();
                float[] c = new float[x.size()];
                int i = 0;
                while(i<k)

                {
                    c[i] = x.get(i).weight;
                }
                params.getFocusDistances(c);
            }
        camera.setParameters(params);
        try {
            camera.setPreviewDisplay(holder);

            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

            }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    }











