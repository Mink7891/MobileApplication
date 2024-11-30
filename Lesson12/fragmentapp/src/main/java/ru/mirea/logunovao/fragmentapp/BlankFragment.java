package ru.mirea.logunovao.fragmentapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BlankFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Раздуваем макет
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Получаем номер студента из аргументов
        int numberStudent = requireArguments().getInt("my_number_student", -1); // -1 по умолчанию
        Log.d(BlankFragment.class.getSimpleName(), "Student Number: " + numberStudent);

        // Отображаем номер студента в TextView
        TextView textView = view.findViewById(R.id.textView);
        textView.setText("Student Number: " + numberStudent);
    }
}

