package mad.a1byi60;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Button myButton = (Button)findViewById(R.id.button);
    //myButton.setX(10);

    public void btnClick1(View view) {
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setX(btn2.getX()+10);
    }

    public void btnClick2(View view) {
        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setX(btn3.getX()+50);
    }

    public void btnClick3(View view) {
        Button btn4 = (Button) findViewById(R.id.button4);
        btn4.setX(btn4.getX()+100);
    }
        public void gotoClicked (View view){
            EditText eText = (EditText) findViewById(R.id.edit_message);
            TextView tV = (TextView) findViewById(R.id.textView4);
            String tStr = String.valueOf(eText.getText());
            tV.setText(tStr);
            ////// Launching a new Web View Activity, via Intent
            Intent intent2Web = new Intent(this, WebActivity.class);//intent for a web activity
            intent2Web.putExtra("KEY_URL", tStr); //message (string tStr) to be passed with
            startActivity(intent2Web); // start and pass to the intended activity
        }
    }


