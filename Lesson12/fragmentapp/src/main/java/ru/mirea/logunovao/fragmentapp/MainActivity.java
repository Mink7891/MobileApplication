package ru.mirea.logunovao.fragmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Создаем объект Bundle и передаем данные
            Bundle bundle = new Bundle();
            bundle.putInt("my_number_student", 7); // Передаем номер студента (например, 7)

            // Создаем экземпляр BlankFragment
            BlankFragment blankFragment = new BlankFragment();
            blankFragment.setArguments(bundle); // Устанавливаем аргументы

            // Добавляем фрагмент в контейнер
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, blankFragment, null)
                    .commit();
        }
    }
}
