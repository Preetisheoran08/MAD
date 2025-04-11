package com.example.question2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner fromUnitSpinner, toUnitSpinner;
    private Button convertButton, settingsButton;
    private TextView resultText;
    private SharedPreferences sharedPreferences;

    String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load Theme First
        SharedPreferences sharedPreferences = getSharedPreferences("settings_pref", MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("theme_key", false);

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        inputValue = findViewById(R.id.inputValue);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);
        settingsButton = findViewById(R.id.settingsButton);

        // Spinner Setup
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        // Convert Button Click
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });

        // Settings Button Click
//        settingsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
//                startActivity(intent);
//            }
//        });

        Button settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void convertUnits() {
        String inputStr = inputValue.getText().toString();
        if (inputStr.isEmpty()) {
            resultText.setText("Please enter a value!");
            return;
        }

        double input = Double.parseDouble(inputStr);
        String fromUnit = fromUnitSpinner.getSelectedItem().toString();
        String toUnit = toUnitSpinner.getSelectedItem().toString();

        double meters = convertToMeters(input, fromUnit);
        double result = convertFromMeters(meters, toUnit);

        resultText.setText(String.format("%.4f %s", result, toUnit));
    }

    private double convertToMeters(double value, String fromUnit) {
        switch (fromUnit) {
            case "Feet": return value * 0.3048;
            case "Inches": return value * 0.0254;
            case "Centimeters": return value * 0.01;
            case "Meters": return value;
            case "Yards": return value * 0.9144;
            default: return 0;
        }
    }

    private double convertFromMeters(double meters, String toUnit) {
        switch (toUnit) {
            case "Feet": return meters / 0.3048;
            case "Inches": return meters / 0.0254;
            case "Centimeters": return meters / 0.01;
            case "Meters": return meters;
            case "Yards": return meters / 0.9144;
            default: return 0;
        }
    }
}
