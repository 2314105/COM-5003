package com.example.mymessenger;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText message;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.message);
        sendButton = findViewById(R.id.sendButton);

        // Handle system insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Attach the function to the button click
        sendButton.setOnClickListener(view -> sendMessage());
    }

    // Separate function for sending a message
    private void sendMessage() {
        String userMessage = message.getText().toString().trim();

        if (!userMessage.isEmpty()) {
            Intent intent = new Intent(MainActivity.this, RecieveMessageActivity.class);
            intent.putExtra("message", userMessage);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Please add a message", Toast.LENGTH_SHORT).show();
        }
    }
}