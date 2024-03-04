package ru.mirea.logunovao.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toast toast = Toast.makeText(getApplicationContext(),
//                "Здравствуй MIREA!",
//                Toast.LENGTH_SHORT);
//        toast.show();
//        СТУДЕНТ № 16 ГРУППА БСБО-11-21 Количество символов - Х
    }

    public void CountSymbol(View view){
        EditText symbol = findViewById(R.id.editTextText);
        Toast toast = Toast.makeText(getApplicationContext(),
                "СТУДЕНТ № 16 ГРУППА БСБО-11-21 Количество символов - " + symbol.length(),
                Toast.LENGTH_SHORT);
        toast.show();
    }
}