package boilermake.snaplength;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InstructionPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_page);
    }

    public void screenTapped(){
        Intent homeIntent = new Intent(this, HomepageActivity.class);
        startActivity(homeIntent);
        finish();
    }
}
