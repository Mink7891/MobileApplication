package ru.mirea.logunovao.mireaproject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SensorLogicFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorOrientation;
    private TextView textViewDirection;
    public SensorLogicFragment() {
        // Обязательный пустой публичный конструктор
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Инфлейтим макет для этого фрагмента
        View view = inflater.inflate(R.layout.fragment_sensorlogic, container, false);
        // Инициализируем элементы пользовательского интерфейса и обработчики событий
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view) {
        textViewDirection = view.findViewById(R.id.textView_direction);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Получаем экземпляр SensorManager
        sensorManager = (SensorManager) requireActivity().getSystemService(requireActivity().SENSOR_SERVICE);
        // Получаем датчик ориентации
        sensorOrientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if (sensorOrientation != null) {
            // Регистрируем слушателя датчика ориентации
            sensorManager.registerListener(this, sensorOrientation, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Отписываемся от обновлений датчика при уходе из фрагмента
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Получаем азимут (угол поворота относительно севера) из данных с датчика ориентации
        float azimuthRadians = event.values[0]; // Получаем значение в радианах
        float azimuthDegrees = (float) Math.toDegrees(azimuthRadians); // Переводим в градусы

        // Проверяем и корректируем диапазон азимута от 0 до 360 градусов
        if (azimuthDegrees < 0) {
            azimuthDegrees += 360;
        }

        // Определяем направление на основе азимута
        String direction;
        if (azimuthDegrees >= 337.5 || azimuthDegrees < 22.5) {
            direction = "Север";
        } else if (azimuthDegrees >= 22.5 && azimuthDegrees < 67.5) {
            direction = "Северо-Восток";
        } else if (azimuthDegrees >= 67.5 && azimuthDegrees < 112.5) {
            direction = "Восток";
        } else if (azimuthDegrees >= 112.5 && azimuthDegrees < 157.5) {
            direction = "Юго-Восток";
        } else if (azimuthDegrees >= 157.5 && azimuthDegrees < 202.5) {
            direction = "Юг";
        } else if (azimuthDegrees >= 202.5 && azimuthDegrees < 247.5) {
            direction = "Юго-Запад";
        } else if (azimuthDegrees >= 247.5 && azimuthDegrees < 292.5) {
            direction = "Запад";
        } else {
            direction = "Северо-Запад";
        }

        // Отображаем направление на экране
        textViewDirection.setText("Направление: " + direction);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Метод обратного вызова, вызывается при изменении точности датчика (можно проигнорировать для данной задачи)
    }
}
