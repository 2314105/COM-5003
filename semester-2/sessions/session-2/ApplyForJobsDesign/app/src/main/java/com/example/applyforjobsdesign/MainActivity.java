package com.example.applyforjobsdesign;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextInputEditText firstName, lastName, dateOfBirth, country;
    Button applyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Adjust layout insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find Views by ID
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        country = findViewById(R.id.country);
        applyButton = findViewById(R.id.applyButton);

        // Button Click Listener
        applyButton.setOnClickListener(v -> validateForm());
    }

    private void validateForm() {
        String missingFields = "";

        if (firstName.getText().toString().trim().isEmpty()) {
            missingFields += "First Name\n";
        }
        if (lastName.getText().toString().trim().isEmpty()) {
            missingFields += "Last Name\n";
        }
        if (dateOfBirth.getText().toString().trim().isEmpty()) {
            missingFields += "Date of Birth\n";
        }
        if (country.getText().toString().trim().isEmpty()) {
            missingFields += "Country\n";
        }

        if (!missingFields.isEmpty()) {
            Toast.makeText(this, "Missing fields:\n" + missingFields, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Job Applied For!", Toast.LENGTH_LONG).show();
        }
    }
}