package com.example.myfirstapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        Button firstButton = findViewById(R.id.firstButton);

        firstButton.setOnClickListener(v -> {
            textView.setText("find a great idea for the next app");
        });
    }
}