package ru.mirea.logunovao.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Event> events = new ArrayList<>();
        events.add(new Event("Событие 1", "Описание события 1", R.drawable.ic_android_black_24dp));
        events.add(new Event("Событие 2", "Описание события 2", R.drawable.baseline_adjust_24));

        EventAdapter adapter = new EventAdapter(events);
        recyclerView.setAdapter(adapter);
    }
}