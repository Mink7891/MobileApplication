package ru.mirea.logunovao.mireaproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ru.mirea.logunovao.mireaproject.databinding.FragmentFileBinding;

public class FileFragment extends Fragment {
    private FragmentFileBinding fragmentFileBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentFileBinding = FragmentFileBinding.inflate(inflater, container, false);

        fragmentFileBinding.fabAddFile.setOnClickListener(v -> showAddFileDialog());

        return fragmentFileBinding.getRoot();
    }

    private void showAddFileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setItems(new CharSequence[]{"Выбрать файл для конвертации в Word"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        readFileFromRaw();
                        break;
                }
            }
        });
        builder.create().show();
    }

    public void readFileFromRaw() {
        List<String> lines = new ArrayList<>();
        try {
            Resources res = getResources();
            InputStream inputStream = res.openRawResource(R.raw.citata1);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            Log.w("RawFile", String.format("Read from file successful: %s", lines.toString()));
            createDocx(lines);
            showToast("Файл успешно загружен и сконвертирован в формат Word");

        } catch (Exception e) {
            Log.w("RawFile", String.format("Read from file failed: %s", e.getMessage()));
            showToast("Ошибка загрузки файла");
        }
    }

    private void createDocx(List<String> lines) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, "citata1.docx");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
            OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);

            for (String line : lines) {
                output.write(line.toString());
            }

            output.close();


        } catch (IOException e) {

            Log.w("ExternalStorage", "Error	writing	" + file, e);
        }
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
