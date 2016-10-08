package edu.csulb.android.markzoo;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HarambeAlertDialog.NoticeDialogListener{

    private List<Animal> animals;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(android.R.id.list);
        animals = new ArrayList<>();
        animals.add(new Animal("Lion", "lion.jpg", "This Lion is a nice lion. Soft kitty. Good Kitty."));
        animals.add(new Animal("Giraffe", "giraffe.jpg", "Giraffes have insanely long necks!"));
        animals.add(new Animal("Elephant", "elephant.jpg", "Elephants hae long trunks that could pick up heavy objects and throw them around!"));
        animals.add(new Animal("Panda", "panda.jpg", "What a lazy panda..."));
        animals.add(new Animal("Harambe", "gorilla.jpg", "RIP Harambe. You will be missed <3"));

        listView.setAdapter(new CustomAdapter(this, R.layout.custom_rows, animals));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                if(position == animals.size()-1){ //if the user clicks on the last animal on the list
                    HarambeAlertDialog fragment = new HarambeAlertDialog();
                    fragment.show(getFragmentManager(), "question");
                    return;
                }
                Intent intent = new Intent(view.getContext(), DetailActivity.class );
                intent.putExtra("Name", animals.get(position).getName());
                intent.putExtra("Image", animals.get(position).getImage());
                intent.putExtra("Description", animals.get(position).getDescription());

                startActivity(intent);
            }
        });

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
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("Image", animals.get(animals.size()-1).getImage());
        intent.putExtra("Name", animals.get(animals.size()-1).getName());
        intent.putExtra("Description", animals.get(animals.size()-1).getDescription());
        Toast.makeText(this.getBaseContext(),"You are a cruel person </3",
                Toast.LENGTH_SHORT).show();
        View harambe = listView.getChildAt(animals.size()-1);

        harambe.setBackgroundColor(Color.BLACK);
        harambe.invalidate();
        harambe.setEnabled(false);
        harambe.setOnClickListener(null);


        startActivity(intent);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this.getBaseContext(),"Thank you for saving Harambe <3",
                Toast.LENGTH_SHORT).show();
        return;
    }





}
