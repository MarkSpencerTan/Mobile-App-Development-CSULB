package edu.csulb.android.lab1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by Mark on 9/2/2016.
 */
public class GetName extends Activity implements android.view.View.OnClickListener{
    android.widget.EditText name;
    android.widget.Button button;
    public void onClick(View arg0){
        Intent myIntent = new Intent(this, Lab1Activity.class);
        myIntent.putExtra("uname", name.getText().toString());
        this.startActivity(myIntent);
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.name_getter);

        name =(EditText) this.findViewById(R.id.editText1);
        button = (Button) this.findViewById(R.id.button1);

        button.setOnClickListener(this);
    }

}

