package edu.csulb.android.lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList(); //populate list
        ListView lv = (ListView) findViewById(R.id.listView);

        //adapter for the list
        SimpleAdapter simpleAdpt = new SimpleAdapter(this, planetsList,
                android.R.layout.simple_list_item_1, new String[]{"planet"}, new int[]{android.R.id.text1} );

        lv.setAdapter(simpleAdpt);

        //set listener for list items
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id){
                TextView clickedView = (TextView) view;

                Toast.makeText(MainActivity.this, "Item with id ["+id+"] - Position ["+position
                        + "] - Planet["+clickedView.getText()+"]",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //list of data to show
    List<Map<String, String>> planetsList = new ArrayList<Map<String, String>>();

    private void initList(){
        //populate the planets
        planetsList.add(createPlanet("planet", "Mercury"));
        planetsList.add(createPlanet("planet", "Venus"));
        planetsList.add(createPlanet("planet", "Earth"));
        planetsList.add(createPlanet("planet", "Mars"));
        planetsList.add(createPlanet("planet", "Jupiter"));
        planetsList.add(createPlanet("planet", "Saturn"));
        planetsList.add(createPlanet("planet", "Uranus"));
        planetsList.add(createPlanet("planet", "Neptune"));
    }



    private HashMap<String, String> createPlanet(String key, String name){
        HashMap<String, String> planet = new HashMap<String, String>();
        planet.put(key, name);
        return planet;
    }
}
