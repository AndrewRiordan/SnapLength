package boilermake.snaplength;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

public class HomepageActivity extends AppCompatActivity {

    private EditText height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void buttonOnClick(View v) {


        height = (EditText) findViewById(R.id.heightEntry);
        String userHght = height.getText().toString();
        String ft = "";
        ft = userHght.substring(0,userHght.indexOf("-"));
        String inch = userHght.substring(userHght.indexOf("-"));

        double ftNum = Double.parseDouble(ft);
        double inchNum = Double.parseDouble(inch);
        CameraMain.setHeight(ftNum,inchNum);
        double inchNuminFt = inchNum / 12;
        openCam();


    }

    public void openCam(){
        Intent intent = new Intent(this, CameraMain.class);
        startActivity(intent);
    }
}
