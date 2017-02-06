package com.mitko.testactivities.Superhero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mitko.testactivities.R;

public class SuperheroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero);

        TextView tv_welcome = (TextView) this.findViewById(R.id.tv_welcome);
        tv_welcome.append("\nWelcome");

        Button btn = (Button) this.findViewById(R.id.btn_click);
        btn.setOnClickListener(view -> {
            String text = ((EditText) this.findViewById(R.id.et_message)).getText().toString();

            RadioGroup rg=(RadioGroup)this.findViewById(R.id.rg_good_bad);
            int checked=rg.getCheckedRadioButtonId();
            RadioButton checkedButton=(RadioButton)this.findViewById(checked);
            String checkedText=checkedButton.getText().toString();

            Toast.makeText(this, text+" "+checkedText,Toast.LENGTH_LONG).show();
        });
    }
}
