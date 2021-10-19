package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IListener {
    private Presenter presenter;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        presenter = new Presenter(this);
        textView = findViewById(R.id.textViewPercent);
        EditText editText1 = findViewById(R.id.editTextTextPersonName);
        EditText editText2 = findViewById(R.id.editTextTextPersonName2);
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().length() != 0 && editText2.getText().length() != 0) {
                    presenter.getPercent(editText1.getText().toString(), editText2.getText().toString());
                } else
                    Toast.makeText(getApplicationContext(), "Введите данные", Toast.LENGTH_SHORT).show();
            }
        });

        button = findViewById(R.id.clearData);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("0.0%");
                editText1.setText("");
                editText2.setText("");
            }
        });
    }


    @Override
    public void setPercent(String percent) {
        textView.setText(percent);
    }
}