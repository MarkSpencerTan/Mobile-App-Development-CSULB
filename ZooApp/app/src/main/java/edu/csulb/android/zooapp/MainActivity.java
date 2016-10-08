package edu.csulb.android.zooapp;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Animal> animals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(android.R.id.list);
        animals = new ArrayList<>();
        animals.add(new Animal("Lion", "lion_thumb.jpg"));
        animals.add(new Animal("Giraffe", "giraffe_thumb.jpg"));
        animals.add(new Animal("Elephant", "elephant_thumb.jpg"));
        animals.add(new Animal("Panda", "panda_thumb.jpg"));
        animals.add(new Animal("Gorilla", "gorilla_thumb.jpg"));
        listView.setAdapter(new CustomAdapter(this, R.layout.custom_rows, animals));
    }


}
