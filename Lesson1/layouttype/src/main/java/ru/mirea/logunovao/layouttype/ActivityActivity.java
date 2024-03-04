package ru.mirea.logunovao.layouttype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ActivityActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        
        TextView tv = findViewById(R.id.textViewStudent);
        TextView myTextView = (TextView) findViewById(R.id.textView);
        myTextView.setText("New text in MIREA");

        Button button = findViewById(R.id.button);
        button.setText("MireaButton");

        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setChecked(true);
    }
}