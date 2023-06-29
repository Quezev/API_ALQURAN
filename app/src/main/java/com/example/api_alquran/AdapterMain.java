package com.example.api_alquran;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api_alquran.Audiomodel.AudioFilesItem;
import com.example.api_alquran.model.ChaptersItem;

import java.util.List;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.Adapterholder>{
    private List<ChaptersItem> results ;
    private List<AudioFilesItem> audior;

    public AdapterMain(List<ChaptersItem> hasil, List<AudioFilesItem> hasilsuara) {
        this.results = hasil;
        this.audior = hasilsuara;
    }

    @NonNull
    @Override
    public Adapterholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);

        return new Adapterholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterholder holder, int position) {
        final ChaptersItem chapter = results.get(position);
        final AudioFilesItem audio = audior.get(position);
        holder.surat.setText(chapter.getNameSimple());
        holder.arti.setText(chapter.getTranslatedName().getName());
        holder.arab.setText(chapter.getNameArabic());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(),MainActivity2.class);
                i.putExtra("namasurah", chapter.getNameSimple());
                i.putExtra("namaarab", chapter.getNameArabic());
                i.putExtra("artisur", chapter.getTranslatedName().getName());
                i.putExtra("id", chapter.getId());
                i.putExtra("urutan", chapter.getRevelationOrder());
                i.putExtra("tempat", chapter.getRevelationPlace());
                i.putExtra("jumlahay", chapter.getVersesCount());
                i.putExtra("audio_url",audio.getAudioUrl());
                holder.itemView.getContext().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class Adapterholder extends RecyclerView.ViewHolder {
        TextView surat, arti, arab;
        public Adapterholder(@NonNull View itemView) {
            super(itemView);
            surat = itemView.findViewById(R.id.namasurah);
            arti = itemView.findViewById(R.id.terjemahs);
            arab = itemView.findViewById(R.id.arab);
        }
    }

    public void setData(List<ChaptersItem> datac, List<AudioFilesItem> dataaudio){
        results.clear();
        results.addAll(datac);
        audior.clear();
        audior.addAll(dataaudio);
        notifyDataSetChanged();

    }
}
