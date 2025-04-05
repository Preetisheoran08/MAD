package com.example.question1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner fromSpinner, toSpinner;
    EditText inputValue;
    TextView resultText;

    String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        inputValue = findViewById(R.id.inputValue);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        findViewById(R.id.convertButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertUnits();
            }
        });
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void convertUnits() {
        String fromUnit = fromSpinner.getSelectedItem().toString();
        String toUnit = toSpinner.getSelectedItem().toString();
        String inputStr = inputValue.getText().toString();

        if (inputStr.isEmpty()) {
            resultText.setText("Please enter a value");
            return;
        }

        double input = Double.parseDouble(inputStr);

        // Convert input to meters first
        double meters = toMeters(input, fromUnit);

        // Then from meters to desired unit
        double result = fromMeters(meters, toUnit);

        resultText.setText(String.format("%.4f %s", result, toUnit));
    }

    private double toMeters(double value, String unit) {
        switch (unit) {
            case "Feet": return value * 0.3048;
            case "Inches": return value * 0.0254;
            case "Centimeters": return value * 0.01;
            case "Meters": return value;
            case "Yards": return value * 0.9144;
            default: return 0;
        }
    }

    private double fromMeters(double value, String unit) {
        switch (unit) {
            case "Feet": return value / 0.3048;
            case "Inches": return value / 0.0254;
            case "Centimeters": return value / 0.01;
            case "Meters": return value;
            case "Yards": return value / 0.9144;
            default: return 0;
        }
    }
}
