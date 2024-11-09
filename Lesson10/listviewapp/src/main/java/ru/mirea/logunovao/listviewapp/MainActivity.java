package ru.mirea.logunovao.listviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] booksAndAuthors = {
            "Лев Толстой - Война и мир",
            "Фёдор Достоевский - Преступление и наказание",
            "Александр Пушкин - Евгений Онегин",
            "Михаил Булгаков - Мастер и Маргарита",
            "Анна Каренина - Лев Толстой",
            "Николай Гоголь - Мёртвые души",
            "Александр Куприн - Олеся",
            "Максим Горький - Мать",
            "Антон Чехов - Вишневый сад",
            "Иван Тургенев - Отцы и дети",
            "Джордж Оруэлл - 1984",
            "Альбер Камю - Чума",
            "Франц Кафка - Процесс",
            "Эрих Мария Ремарк - На западном фронте без перемен",
            "Уильям Шекспир - Гамлет",
            "Джейн Остин - Гордость и предубеждение",
            "Джордж Элиот - Миддлмарч",
            "Марсель Пруст - В поисках утраченного времени",
            "Маргарет Митчелл - Унесенные ветром",
            "Эдгар Аллан По - Собрание рассказов",
            "Луис Кэрролл - Алиса в Стране чудес",
            "Фёдор Достоевский - Братья Карамазовы",
            "Чарльз Диккенс - Большие надежды",
            "Габриэль Гарсиа Маркес - Сто лет одиночества",
            "Джеймс Джойс - Улисс",
            "Джек Лондон - Мартин Иден",
            "Оскар Уайльд - Портрет Дориана Грея",
            "Герман Мелвилл - Моби Дик",
            "Мэри Шелли - Франкенштейн",
            "Брэм Стокер - Дракула",
            "Виктор Гюго - Собор Парижской Богоматери",
            "Артур Конан Дойл - Приключения Шерлока Холмса"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView bookListView = findViewById(R.id.book_list_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                booksAndAuthors
        );

        bookListView.setAdapter(adapter);
    }
}