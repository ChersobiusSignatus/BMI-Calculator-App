package com.example.bmicalculator;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView resultText;
    private Button calculateButton;
    private RadioButton femaleButton;
    private RadioButton maleButton;
    private EditText ageEditText;
    private EditText heightEditText;
    private EditText weightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        femaleButton = findViewById(R.id.radio_button_female);
        maleButton = findViewById(R.id.radio_button_male);
        ageEditText = findViewById(R.id.edit_text_age);
        heightEditText = findViewById(R.id.edit_text_height);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18){
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }


            }
        });
    }



    private double calculateBmi() {
        String heightText = heightEditText.getText().toString();
        String weightText = weightEditText.getText().toString();
        //converting 'strings' into 'int' variables
        double height = Integer.parseInt(heightText);
        double weight = Integer.parseInt(weightText);

        //calculating
        double h = height/100;

        return weight/(h*h);
    }
    private void displayResult(double bmi){
        //must convert the decimal/double into a string for our TextView
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if(bmi < 18.5){
            //display underweight
            fullResultString = bmiResult + " - Yo, I didn't know skeletons were real bruh";
        } else if(bmi > 25){
            //display overweight
            fullResultString = bmiResult + " - Go to the gym, fat fucker";
        } else {
            //display normal
            fullResultString = bmiResult + " - You are okay";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if(maleButton.isChecked()){
            //display boy guidance
            fullResultString = bmiResult + " - As you are under 18, please consult with your doctor for the  healthy range for boys";
        } else if (femaleButton.isChecked()){
            //display girl guidance
            fullResultString = bmiResult + " - As you are under 18, please consult with your doctor for the  healthy range for girls";

        }else{
            //general guidance
            fullResultString = bmiResult + " - As you are under 18, please consult with your doctor for the  healthy range";

        }
        resultText.setText(fullResultString);
    }
}