package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    TextInputLayout numberOneLayout, numberTwoLayout;
    EditText numberOne, numberTwo;
    TextView newResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        newResult = findViewById(R.id.result);
        numberOneLayout = findViewById(R.id.textInputLayout);
        numberTwoLayout = findViewById(R.id.textInputLayout2);
        numberOne = numberOneLayout.getEditText();
        numberTwo = numberTwoLayout.getEditText();
    }

    public void minusButton(View view) {
        double num1 = Double.parseDouble(numberOne.getText().toString());
        double num2 = Double.parseDouble(numberTwo.getText().toString());
        newResult.setText(String.valueOf(num1 - num2));

    }

    public void divideButton(View view) {
        double num1 = Double.parseDouble(numberOne.getText().toString());
        double num2 = Double.parseDouble(numberTwo.getText().toString());
        newResult.setText(String.valueOf(num1 / num2));
    }

    public void additionButton(View view) {
        double num1 = Double.parseDouble(numberOne.getText().toString());
        double num2 = Double.parseDouble(numberTwo.getText().toString());
        newResult.setText(String.valueOf(num1 + num2));
    }

    public void multiplyButton(View view) {
        double num1 = Double.parseDouble(numberOne.getText().toString());
        double num2 = Double.parseDouble(numberTwo.getText().toString());
        newResult.setText(String.valueOf(num1 * num2));
    }
}