package ru.mirea.logunovao.mireaproject;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkInfoFragment extends Fragment {

    private TextView infoTextView;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_network_info, container, false);
        infoTextView = rootView.findViewById(R.id.infoTextView);
        context = getContext();
        fetchData();
        return rootView;
    }

    public void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CatApi catApi = retrofit.create(CatApi.class);

        catApi.getCat().enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                if (response.isSuccessful()) {
                    List<Cat> cats = response.body();
                    if (cats != null && !cats.isEmpty()) {
                        Cat cat = cats.get(0);
                        infoTextView.setText(cat.url);
                    } else {
                        showToast("Нет данных о котах");
                    }
                } else {
                    showToast("Ошибка при получении данных: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                showToast("Ошибка при выполнении запроса: " + t.getMessage());
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
