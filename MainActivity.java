package mad.madyi60;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    MediaPlayer myMus = null; // a field of MediaPlayer

    @Override
    protected void onResume() { // callback method, when interacting with user
        super.onResume(); // always call superclass
        if (myMus != null) myMus.start(); // start playing
    }

    @Override
    protected void onPause() { // callback method, inactive: when no interacting
        super.onPause(); // always call superclass
        if (myMus != null) myMus.pause(); // pause playing
    }

    void makeMoveThread() { // for making a thread to continuously move the image object
        final int DEF_SLEEP_GAP = 1100;
        final int DEF_MAX = 120; // max movement factor
        final int DEF_BASE = -DEF_MAX / 2; // base movement factor
        new Thread(new Runnable() { // create a new thread to animate, and then start it.
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(DEF_SLEEP_GAP); // interval in ms
                        // move (change location of) the image
                        final ImageButton imgB = (ImageButton) findViewById(R.id.fg_imgButton);
                        if (imgB == null) break;
                        imgB.post(new Runnable() { // for handling UI with thread in Android
                            public void run() {
                                // animate motion
                                int diffX = (DEF_BASE + (int) (Math.random() * DEF_MAX));
                                int diffY = (DEF_BASE + (int) (Math.random() * DEF_MAX));
                                imgB.setX(imgB.getX() + diffX);
                                imgB.setY(imgB.getY() + diffY);
                                // animate changing transparency
                                imgB.setImageAlpha(128 + (int) (Math.random() * 128));
                            }
                        });
                    } catch (InterruptedException ie) {
                    }
                }
            }
        }).start();
    }

///////////// END - Button Click to Diff. Activity Screens ////////////////

    ///////////// START - Button Click to Diff. Activity Screens ////////////////
    public void butClicked(View view){
        startActivity(new Intent(this, StoryBehindPage.class)); //start new Activity
    }
    public void but2Clicked(View view){
        startActivity(new Intent(this, AboutMePage.class)); //start new Activity
    }
    public void but3Clicked(View view){
        startActivity(new Intent(this, MainBody.class)); //start new Activity
    }
///////////// END - Button Click to Diff. Activity Screens ////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myMus = MediaPlayer.create(this, R.raw.bs);//sound file “bs” in raw folder
        myMus.setLooping(true); // set loop-playing mode
        makeMoveThread();

    }
}

