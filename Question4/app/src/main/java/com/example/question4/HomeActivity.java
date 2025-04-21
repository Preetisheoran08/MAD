package com.example.question4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(v -> {
            // TODO: Handle Google sign-out
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        });
    }
}
