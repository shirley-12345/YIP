package mad.madyi60;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainBody extends AppCompatActivity {
    ImageView imgV; // foreground moving object image view
    ImageView imgV2;
    ImageView imgV3;
    TextView text3;
    TextView text4;
    ConstraintLayout cLayout; // background layout
    MADAccSensor mAccSensor; // motion sensor (Accelerometer)

    MediaPlayer myMusmb = null; // a field of MediaPlayer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_body);
        mAccSensor = new MADAccSensor(this);
        imgV = (ImageView) findViewById(R.id.image1);
        imgV2 = (ImageView) findViewById(R.id.image2);
        imgV3 = (ImageView) findViewById(R.id.image3);
        text3 = ((TextView)findViewById(R.id.texthunny));
        text4 = ((TextView)findViewById(R.id.textbomb));
        cLayout = (ConstraintLayout) findViewById(R.id.cLayout);
        myMusmb = MediaPlayer.create(this, R.raw.bs2);//sound file “bs” in raw folder
        myMusmb.setLooping(true); // set loop-playing mode
        Button butHome = (Button) findViewById(R.id.butHome); // Button, for back home
        butHome.setOnClickListener( // set button click action
                new View.OnClickListener() {
                    public void onClick(View v) {
                        finish(); // finish (terminate) this activity
                    }
                }
        );
    }
    @Override //overriding superclass, register to obtain data when app resumes
    protected void onResume() {
        super.onResume(); // always call superclass
        mAccSensor.enableAccSensor();
        if (myMusmb != null) myMusmb.start(); // start playing
    }
    @Override //overriding superclass, unregister(disable) when app pauses
    protected void onPause() {
        super.onPause(); // always call superclass
        mAccSensor.disableAccSensor();
        if (myMusmb != null) myMusmb.pause(); // pause playing
    }


}
