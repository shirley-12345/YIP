package mad.madyi60;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class MADAccSensor {
    // Fields
    SensorManager aSensorM; // a sensor manager
    Sensor aAccSensor; // the accelerometer sensor object
    MainBody parentAct;
    int num=0;
    int lost=0;
    SensorEventListener myListener = new SensorEventListener() { // Anonymous Class
        @Override // for interface: SensorEventListener
        public final void onAccuracyChanged(Sensor sensor, int accuracy) { }
        // method to be called with sensor changed //
        @Override // for interface: SensorEventListener
        public final void onSensorChanged(SensorEvent event) {
///////////////// Section of responding sensor data updated //////////////////////
            float accFactor = 3.0f; // accelerometer factor for object movement
            if (parentAct.imgV!=null) {
                float newX = parentAct.imgV.getX() - accFactor * event.values[0];
                float newY = parentAct.imgV.getY() + accFactor * event.values[1];
                if (newX<0) newX = 0; // exception cases (boundary)
                else if (newX > parentAct.cLayout.getWidth()-parentAct.imgV.getWidth())
                    newX = parentAct.cLayout.getWidth()-parentAct.imgV.getWidth();
                if (newY<0) newY = 0; // exception cases (boundary)
                else if (newY > parentAct.cLayout.getHeight()-parentAct.imgV.getHeight())
                    newY = parentAct.cLayout.getHeight()-parentAct.imgV.getHeight();
                parentAct.imgV.setX(newX); // update the x position
                parentAct.imgV.setY(newY); // update the y position

                ////////////// When catches / hits image 2, updates / repositions image 2 randomly.
                if (parentAct.imgV2!=null) {
                    Rect imgVRect=new Rect(), imgV2Rect=new Rect(), imgV3Rect=new Rect();
                    parentAct.imgV.getHitRect(imgVRect); // get Rect of imgV
                    parentAct.imgV2.getHitRect(imgV2Rect); // get Rect of imgV2
                    parentAct.imgV3.getHitRect(imgV3Rect); // get Rect of imgV2
                    if (imgVRect.intersect(imgV2Rect)) { // if collided, move randomly
                        parentAct.imgV3.setX((int)(Math.random()*
                                parentAct.cLayout.getWidth()-10-parentAct.imgV3.getWidth()));
                        parentAct.imgV3.setY((int)(Math.random()*(
                                parentAct.cLayout.getHeight()-10-parentAct.imgV3.getHeight())));
                        parentAct.imgV2.setX((int)(Math.random()*
                                parentAct.cLayout.getWidth()-10-parentAct.imgV2.getWidth()));
                        parentAct.imgV2.setY((int)(Math.random()*(
                                parentAct.cLayout.getHeight()-10-parentAct.imgV2.getHeight())));
                        num++;
                    }
                    if (imgVRect.intersect(imgV3Rect)) { // if collided, move randomly
                        parentAct.imgV3.setX((int)(Math.random()*
                                parentAct.cLayout.getWidth()-10-parentAct.imgV3.getWidth()));
                        parentAct.imgV3.setY((int)(Math.random()*(
                                parentAct.cLayout.getHeight()-10-parentAct.imgV3.getHeight())));
                        parentAct.imgV2.setX((int)(Math.random()*
                                parentAct.cLayout.getWidth()-10-parentAct.imgV2.getWidth()));
                        parentAct.imgV2.setY((int)(Math.random()*(
                                parentAct.cLayout.getHeight()-10-parentAct.imgV2.getHeight())));
                        lost++;
                    }

                }
                parentAct.text3.setText(" "+num);
                parentAct.text4.setText(" "+lost);



            }
//////////////////////////////////////////////////////////////////////////////////
        }
    };
    // Constructor
    MADAccSensor(MainBody parentAct){
        this.parentAct = parentAct;
        aSensorM = (SensorManager)
                this.parentAct.getSystemService(Context.SENSOR_SERVICE);
        if (aSensorM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null){ //Success!
            aAccSensor = aSensorM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        else aSensorM = null; // Failure! No ACCELEROMETER.
    }
    // Methods
    public void enableAccSensor(){
        aSensorM.registerListener(myListener,
                aAccSensor, SensorManager.SENSOR_DELAY_GAME);
    }
    public void disableAccSensor(){
        aSensorM.unregisterListener(myListener);
    }

}

