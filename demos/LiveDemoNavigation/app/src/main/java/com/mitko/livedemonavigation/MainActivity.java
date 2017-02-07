package com.mitko.livedemonavigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String INTENT_EXTRA_KEY = "args_key";

    private int count;

    public MainActivity() {
        super();
        this.count=1;
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent=this.getIntent();
        this.count=intent.getIntExtra(INTENT_EXTRA_KEY, 1);
        TextView tvCounter = (TextView) this.findViewById(R.id.tv_counter);
        tvCounter.setText(String.format("Created %d Main Activity", count));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent=this.getIntent();
//        int count=intent.getIntExtra(INTENT_EXTRA_KEY, 1);
//
//        TextView tvCounter = (TextView) this.findViewById(R.id.tv_counter);
//        tvCounter.setText(String.format("Created %d Main Activity", count));

        Button btnGoNext = (Button) this.findViewById(R.id.button_go_next);
        btnGoNext.setOnClickListener(view -> {
            Intent initialIntent = new Intent(this, MainActivity.class);
            initialIntent.putExtra(INTENT_EXTRA_KEY, this.count+1);
            this.startActivity(initialIntent);
        });
    }
}
