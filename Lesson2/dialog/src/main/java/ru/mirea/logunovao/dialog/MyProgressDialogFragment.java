package ru.mirea.logunovao.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyProgressDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Загрузка...");  // Установите сообщение для отображения
        progressDialog.setCancelable(false);  // Запрещаем закрытие ProgressDialog при касании вне него
        progressDialog.setIndeterminate(true);  // Индикатор занятости без конечной точки

        return progressDialog;
    }
}
