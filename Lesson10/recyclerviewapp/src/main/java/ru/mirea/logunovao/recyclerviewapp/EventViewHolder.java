package ru.mirea.logunovao.recyclerviewapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {
    ImageView imageViewEvent;
    TextView textViewTitle;
    TextView textViewDescription;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        imageViewEvent = itemView.findViewById(R.id.imageViewEvent);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        textViewDescription = itemView.findViewById(R.id.textViewDescription);
    }
}
