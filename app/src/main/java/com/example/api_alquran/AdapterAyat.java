package com.example.api_alquran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api_alquran.AyatModel.VersesItem;
import com.example.api_alquran.TerjemahanModel.TranslationsItem;


import java.util.List;

public class AdapterAyat extends RecyclerView.Adapter<AdapterAyat.Adapterholder> {
    private List<VersesItem> ayatre;
    private List<TranslationsItem> terjemi;

    public AdapterAyat(List<VersesItem> itemay, List<TranslationsItem> itemter){
        this.ayatre = itemay;
        this.terjemi = itemter;
    }

    @NonNull
    @Override
    public Adapterholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapterholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemay,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterholder holder, int position) {
        VersesItem hasilayat = ayatre.get(position);
        TranslationsItem hasiltre = terjemi.get(position);

        holder.ayatsur.setText(hasilayat.getTextUthmani());
        holder.terjemahsur.setText(hasiltre.getText());
        holder.nosur.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return ayatre.size();
    }

    public class Adapterholder extends RecyclerView.ViewHolder {
        TextView ayatsur, terjemahsur, nosur;
        public Adapterholder(@NonNull View itemView) {
            super(itemView);
            ayatsur = itemView.findViewById(R.id.ayats);
            terjemahsur = itemView.findViewById(R.id.terjemaay);
            nosur = itemView.findViewById(R.id.nomor);
        }
    }

    public void setData(List<VersesItem> listay, List<TranslationsItem> listre){
        ayatre.clear();
        ayatre.addAll(listay);
        terjemi.clear();
        terjemi.addAll(listre);
        notifyDataSetChanged();
    }
}
