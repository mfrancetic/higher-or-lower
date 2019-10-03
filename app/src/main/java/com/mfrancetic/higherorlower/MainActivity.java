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

    private Context context;
    private Random random;
    private int randomNumber;
    private int guessedNumber;
    private String guessedNumberString;

    private int max = 20;
    private int min = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateRandom();

        guessButton = findViewById(R.id.guess_button);
        numberEditText = findViewById(R.id.number_edit_text);

        context = numberEditText.getContext();

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
        if (guessedNumber < randomNumber) {
            Toast.makeText(context, R.string.toast_higher, Toast.LENGTH_SHORT).show();
        } else if (guessedNumber > randomNumber) {
            Toast.makeText(context, R.string.toast_lower, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.toast_correct, Toast.LENGTH_SHORT).show();
            generateRandom();
        }
    }

    private void clearEditText() {
        numberEditText.setText("");
    }

    private void generateRandom() {
        random = new Random();

        randomNumber = random.nextInt((max - min) + 1) + min;
    }
}
