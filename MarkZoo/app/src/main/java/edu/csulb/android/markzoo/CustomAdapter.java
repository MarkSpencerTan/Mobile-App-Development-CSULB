package edu.csulb.android.markzoo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Mark on 9/30/2016.
 */
public class CustomAdapter extends ArrayAdapter<Animal> {
    private List<Animal> animals;
    public CustomAdapter(Context context, int resource, List<Animal> animals) {
        super(context, resource, animals);
        this.animals = animals;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Animal animal = animals.get(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_rows, null);
        // Set the text
        TextView textView = (TextView) row.findViewById(R.id.animalName);
        textView.setText(animal.getName());
        // Set the image
        try {
            ImageView imageView = (ImageView) row.findViewById(R.id.animalThumb);
            InputStream inputStream = getContext().getAssets().open(animal.getImage());
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) { e.printStackTrace();}
        return row;
    }
}
