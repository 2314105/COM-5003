package com.example.mymessenger;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RecieveMessageActivity extends AppCompatActivity {

    TextView userMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recieve_message);

        userMessageTextView = findViewById(R.id.userMessage);

        // Call the function to retrieve and display the message
        displayReceivedMessage();

        // Handle system insets separately
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Separate function to retrieve and display the message
    private void displayReceivedMessage() {
        String receivedMessage = getIntent().getStringExtra("message");

        if (receivedMessage != null && !receivedMessage.isEmpty()) {
            userMessageTextView.setText(receivedMessage);
        } else {
            userMessageTextView.setText("No message received.");
        }
    }
}