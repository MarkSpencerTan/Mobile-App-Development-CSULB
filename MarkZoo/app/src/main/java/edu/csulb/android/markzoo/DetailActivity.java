package edu.csulb.android.markzoo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mark on 9/30/2016.
 */
public class DetailActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = this.getIntent().getExtras();
        String image = extras.getString("Image");
        String name = extras.getString("Name");
        String desc = extras.getString("Description");

        ImageView imageview = (ImageView) findViewById(R.id.full_image);
        TextView nametext = (TextView) findViewById(R.id.animalName_Desc);
        TextView desctext = (TextView) findViewById(R.id.description);

        nametext.setText(name);
        desctext.setText(desc);

        try {
            // load inputstream
            InputStream istream = getAssets().open(image);
            Drawable d = Drawable.createFromStream(istream, null);
            // set image to ImageView
            imageview.setImageDrawable(d);
        }catch(IOException ex){
            return;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_zooinfo) {
            Intent intent = new Intent(this, ZooInformation.class );
            startActivity(intent);
            return true;
        }
        if (id== R.id.action_uninstall){
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:edu.csulb.android.markzoo"));
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
