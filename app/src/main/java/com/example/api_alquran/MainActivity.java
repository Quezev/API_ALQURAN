package com.example.api_alquran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.api_alquran.Audiomodel.Audio;
import com.example.api_alquran.Audiomodel.AudioFilesItem;
import com.example.api_alquran.model.Chapters;
import com.example.api_alquran.model.ChaptersItem;
import com.example.api_alquran.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AdapterMain adaptermain;
    private RecyclerView recyclerView;
    private List<ChaptersItem> results = new ArrayList<>();
    private List<AudioFilesItem> audior = new ArrayList<>();

    List<ChaptersItem> ayatre;
    List<AudioFilesItem> suarar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataFromApi();
        setUpView();
        setUpRecycleview();
    }

    private void setUpRecycleview() {
        adaptermain = new AdapterMain(results,audior);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptermain);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recycle);
    }

    private void getDataFromApi(){
        ApiService.endpoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, Response<Chapters> response) {
                if (response.isSuccessful()){
                    MainActivity.this.results = response.body().getChapters();
                    Log.d("Chaptertes", results.toString());
                    getAudioDataFromApi();
                }
            }

            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                Log.d("Errorchapter", toString());
            }
        });
    }
    private void getAudioDataFromApi(){
        ApiService.endpoint().getAudio().enqueue(new Callback<Audio>() {
            @Override
            public void onResponse(Call<Audio> call, Response<Audio> response) {
                if (response.isSuccessful()) {
                    MainActivity.this.audior = response.body().getAudioFiles();
                    Log.d("TesAudio", audior.toString());
                    adaptermain.setData(results, audior);
                }
            }

            @Override
            public void onFailure(Call<Audio> call, Throwable t) {
                Log.d("Erroraudio", toString());

            }
        });
    }
}