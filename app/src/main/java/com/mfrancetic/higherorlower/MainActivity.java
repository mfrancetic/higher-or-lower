package com.mfrancetic.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button guessButton;
    private EditText numberEditText;

    private Random random;
    private int randomNumber;
    private int guessedNumber;
    private String guessedNumberString;

    private final static String randomNumberKey = "randomNumber";

    private int max = 20;
    private int min = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            randomNumber = savedInstanceState.getInt(randomNumberKey);
        } else {
            generateRandom();
        }

        guessButton = findViewById(R.id.guess_button);
        numberEditText = findViewById(R.id.number_edit_text);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfNumberIsCorrect();
                clearEditText();
            }
        });

    }

    private void checkIfNumberIsCorrect() {
        guessedNumberString = numberEditText.getText().toString();
        guessedNumber = Integer.parseInt(guessedNumberString);

        String message;

        if (guessedNumber < randomNumber) {
            message = getString(R.string.toast_higher);
        } else if (guessedNumber > randomNumber) {
            message = getString(R.string.toast_lower);
        } else {
            message = getString(R.string.toast_correct);
            generateRandom();
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void clearEditText() {
        numberEditText.setText("");
    }

    private void generateRandom() {
        random = new Random();

        randomNumber = random.nextInt((max - min) + 1) + min;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(randomNumberKey, randomNumber);
        super.onSaveInstanceState(outState);
    }
}
