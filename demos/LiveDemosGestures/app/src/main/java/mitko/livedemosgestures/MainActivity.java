package mitko.livedemosgestures;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    ArrayAdapter<String> adapter;
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv=(ListView) this.findViewById(R.id.lv_gestures);
        this.adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        this.adapter.add("Test");
        lv.setAdapter(this.adapter);

        lv.setOnTouchListener(this);
        final Context context=this;
        detector=new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDown(MotionEvent e) {
                adapter.add("GESTURE DETECTOR DOWN");
                return super.onDown(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                adapter.add("GESTURE DETECTOR LONG PRESS");
                super.onLongPress(e);
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float xDiff=Math.abs(e1.getX()-e2.getX());
                float yDiff=Math.abs(e1.getY()-e2.getY());
                if(xDiff>yDiff){
                    adapter.add("Fling left - right");
                } else{
                    adapter.add("Fling up - down");
                }

                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.detector.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_UP:this.adapter.add("UP"); break;
            case MotionEvent.ACTION_DOWN:this.adapter.add("DOWN"); break;
            case MotionEvent.ACTION_SCROLL:this.adapter.add("SCROLL"); break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return this.onTouchEvent(event);
    }
}
