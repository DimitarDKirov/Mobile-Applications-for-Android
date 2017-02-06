package mitko.livedemolayouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Динамично създаване на елементи
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < 3; i++) {
            TextView textView = new TextView(this);
            // textView.setWidth(123);
            textView.setHeight(123);
            textView.setText("Dinamic generation " + (i + 1));
            linearLayout.addView(textView);
        }

        ((LinearLayout) this.findViewById(R.id.activity_main)).addView(linearLayout);
    }
}
