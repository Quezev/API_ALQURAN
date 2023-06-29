package com.example.api_alquran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.api_alquran.Audiomodel.AudioFilesItem;
import com.example.api_alquran.AyatModel.Verses;
import com.example.api_alquran.AyatModel.VersesItem;
import com.example.api_alquran.TerjemahanModel.Translations;
import com.example.api_alquran.TerjemahanModel.TranslationsItem;
import com.example.api_alquran.retrofit.ApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView2;
    private AdapterAyat adapteray;
    private List<VersesItem> aylist = new ArrayList<>();
    private List<TranslationsItem> trelist = new ArrayList<>();
    private List<AudioFilesItem> listau = new ArrayList<>();
    private MediaPlayer mediaPlayer;

    List<VersesItem> ay;
    List<TranslationsItem> tre;
    TextView Idsurah;
    Button butay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        int id = getIntent().getIntExtra("id",1);
        Idsurah = findViewById(R.id.idsur);
        Idsurah.setText("Surah Ke: "+(id));

        int urutan = getIntent().getIntExtra("urutan",1);
        Idsurah = findViewById(R.id.urutansur);
        Idsurah.setText("Urutan turunnya surah: "+(urutan));

        int jumlahay = getIntent().getIntExtra("jumlahay",1);
        Idsurah = findViewById(R.id.jumay);
        Idsurah.setText("Jumlah Ayat: "+(jumlahay));

        String namasurah = getIntent().getExtras().getString("namasurah");
        Idsurah = findViewById(R.id.namasur);
        Idsurah.setText("Nama Surah: "+(namasurah));

        String tempat = getIntent().getExtras().getString("tempat");
        Idsurah = findViewById(R.id.tempsur);
        Idsurah.setText("Tempat turunnya Surah: "+(tempat));

        String namaarab = getIntent().getExtras().getString("namaarab");
        Idsurah = findViewById(R.id.namaar);
        Idsurah.setText("("+(namaarab)+")");

        String artisur = getIntent().getExtras().getString("artisur");
        Idsurah = findViewById(R.id.artisurat);
        Idsurah.setText("Terjemahan Surah: "+(artisur));

        mediaPlayer = new MediaPlayer();
        String audioUrl = getIntent().getStringExtra("audio_url");
        butay = findViewById(R.id.butay);
        butay.setOnClickListener(view ->  {
            if (mediaPlayer.isPlaying()){
                pauseAudio();
            } else {
                playAudio(audioUrl);
            }
        });

        getDataFromApi(id);
        setUpView();
        setUpRecycleView();

    }


    private void pauseAudio() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    private void playAudio(String audio){
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(audio);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void setUpRecycleView() {
        adapteray = new AdapterAyat(aylist,trelist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(adapteray);
    }

    private void setUpView() {
        recyclerView2 = findViewById(R.id.recycleay);
    }
    private void getDataFromApi(int id) {
        ApiService.endpoint().getAyat(id).enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                if (response.isSuccessful()){
                    MainActivity2.this.ay = response.body().getVerses();
                    getTranslationdata(getIntent().getIntExtra("id",1));
                }
            }

            @Override
            public void onFailure(Call<Verses> call, Throwable t) {
                Log.d("Erroray", toString());
            }
        });

    }

    private void getTranslationdata(int id) {
        ApiService.endpoint().getTerjemahan(id).enqueue(new Callback<Translations>() {
            @Override
            public void onResponse(Call<Translations> call, Response<Translations> response) {
                if (response.isSuccessful()){
                    MainActivity2.this.tre = response.body().getTranslations();
                    adapteray.setData(ay,tre);
                }
            }

            @Override
            public void onFailure(Call<Translations> call, Throwable t) {
                Log.d("Errortre", toString());
            }
        });
    }
}