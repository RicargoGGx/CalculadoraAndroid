package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private StringBuilder currentInput = new StringBuilder();
    private double result = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;
    private boolean isResultCalculated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        Log.d("Calculator", "onButtonClick called: " + buttonText);

        if (isResultCalculated) {
            clearAll();
        }

        if (isOperatorClicked && isNumeric(buttonText)) {
            currentInput.setLength(0);
            isOperatorClicked = false;
        }

        currentInput.append(buttonText);
        textViewResult.setText(currentInput);
    }

    public void onOperatorClick(View view) {
        if (!isOperatorClicked) {
            Button button = (Button) view;
            String newOperator = button.getText().toString();

            if (!operator.isEmpty()) {
                calculateResult();
                isResultCalculated = true;
            } else {
                result = Double.parseDouble(currentInput.toString());
            }

            operator = newOperator;
            isOperatorClicked = true;
        }
    }

    public void onEqualClick(View view) {
        if (!operator.isEmpty() && !isResultCalculated) {
            calculateResult();
            isResultCalculated = true;
        }
    }

    public void onClearClick(View view) {
        clearAll();
    }

    private void calculateResult() {
        double secondOperand = Double.parseDouble(currentInput.toString());

        switch (operator) {
            case "+":
                result += secondOperand;
                break;
            case "-":
                result -= secondOperand;
                break;
            case "*":
                result *= secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result /= secondOperand;
                } else {
                    result = 0;  // Handle division by zero
                }
                break;
        }

        currentInput.setLength(0);
        currentInput.append(result);
        textViewResult.setText(currentInput);
    }

    private void clearAll() {
        currentInput.setLength(0);
        result = 0;
        operator = "";
        isOperatorClicked = false;
        isResultCalculated = false;
        textViewResult.setText("0");
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
