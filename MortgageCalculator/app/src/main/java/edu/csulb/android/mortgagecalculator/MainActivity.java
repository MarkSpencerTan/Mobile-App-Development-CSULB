package edu.csulb.android.mortgagecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button calcButton;
    private EditText principal;
    private SeekBar interestRate;
    private CheckBox tax, insurance;
    private RadioGroup loanTerm;
    private TextView total;
    private static int months;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeVariables();
        calcButton.setOnClickListener(Calculate);
    }

    private View.OnClickListener Calculate = new View.OnClickListener(){
        public void onClick(View view){
            if( principal.getText().length() == 0 ){
                Toast.makeText(getBaseContext(), "Please enter a valid principal", Toast.LENGTH_LONG).show();
                return;
            }
            double p = Double.parseDouble(principal.getText().toString());
            double interest = ((double) interestRate.getProgress())/1200; //divide by 1200 to get monthly interest in decimal
            //Toast.makeText(getBaseContext(), "Your interest: "+interest, Toast.LENGTH_LONG).show();

            double additional = getAdditional(p); //adds taxes and insurance
            months = getTerm(); //gets number of months of the loan
            Toast.makeText(getBaseContext(),"years: "+months/12,Toast.LENGTH_LONG).show();

            if( interest == 0 ){
                setTotal(p/months + additional);
            }
            else{
                setTotal( (p * ( interest/( 1-Math.pow(1+interest, -1*months) ) ) ) +additional);
            }
        }
    };

    private int getTerm(){
        loanTerm = (RadioGroup) findViewById(R.id.loanTerm);
        int term = loanTerm.getCheckedRadioButtonId();
        RadioButton termButton = (RadioButton) findViewById(term);
        //parse text value on button to int
        return  Integer.parseInt(termButton.getText().toString().substring(0,2)) * 12;
    }


    //gets additional fees: taxes and insurance will be 0.1% of principal for each option
    //@param p = principal
    private double getAdditional(double p){
        if (tax.isChecked()){
            if(insurance.isChecked()){
                return p*0.002;
            }
            return p*.001;
        }
        else if(insurance.isChecked()){
            return p*.001;
        }
        return 0;
    }

    private void setTotal(double t){
        total.setText(Double.toString(t));
    }

    //initialize xml elements to java
    private void InitializeVariables(){
        calcButton = (Button) findViewById(R.id.calculateButton);
        principal = (EditText) findViewById(R.id.loanAmount);
        interestRate = (SeekBar) findViewById(R.id.interest);
        tax = (CheckBox) findViewById(R.id.taxesBox);
        insurance = (CheckBox) findViewById(R.id.insuranceBox);
        total = (TextView) findViewById(R.id.total);
    }

}
