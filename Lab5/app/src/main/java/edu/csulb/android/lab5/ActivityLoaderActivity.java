package edu.csulb.android.lab5;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityLoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_loader);

        final Button webbutton = (Button) findViewById(R.id.browser_button);
        webbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("http://www.amazon.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                Intent appChooser =Intent.createChooser(webIntent, "Choose a Browser");
                if (webIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(appChooser);
                }
            }
        });

        final Button callbutton = (Button) findViewById(R.id.call_button);
        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+194912344444"));
                startActivity(call);
            }
        });
    }
}
