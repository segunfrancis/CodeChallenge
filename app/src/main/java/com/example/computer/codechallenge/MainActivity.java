package com.example.computer.codechallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutButton = findViewById(R.id.button_aboutALC);
        Button profileButton = findViewById(R.id.button_profile);

        aboutButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AboutActivity.class)));

        profileButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
    }
}
