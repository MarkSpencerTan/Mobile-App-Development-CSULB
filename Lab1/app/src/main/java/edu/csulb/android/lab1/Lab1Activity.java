package edu.csulb.android.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Lab1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        Bundle myInput = this.getIntent().getExtras();

        TextView t = new TextView(this);
        t = (TextView) findViewById(R.id.textView2);
        t.setText(getText(R.string.Hello)+ " " + myInput.getString("uname") );

        Animation fade = new AlphaAnimation(0.0f, 1.0f);
        fade.setDuration(3000);

        t.startAnimation(fade);
    }
}
