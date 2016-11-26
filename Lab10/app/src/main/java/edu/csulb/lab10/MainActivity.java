package edu.csulb.lab10;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    Button btnAdd;
    EditText inputLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        btnAdd = (Button) findViewById(R.id.btn_add);
        inputLabel = (EditText) findViewById(R.id.input_label);

        spinner.setOnItemSelectedListener(this);

        loadSpinnerData();

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v ){
                String label = inputLabel.getText().toString();

                if(label.trim().length()>0){
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    db.insertLabel(label);
                    inputLabel.setText("");

                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(inputLabel.getWindowToken(), 0);

                    // Loading spinner with new data
                    loadSpinnerData();
                }
                else
                    Toast.makeText(getApplicationContext(), "Please enter label name", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadSpinnerData(){
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<String> labels = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);

        // Drop down layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attach data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        // When selecting spinne ritem
        String label = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: "+label, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> v){
        // Do nothing
    }
}
