package com.example.api_alquran.retrofit;

import com.example.api_alquran.Audiomodel.Audio;
import com.example.api_alquran.AyatModel.Verses;
import com.example.api_alquran.TerjemahanModel.Translations;
import com.example.api_alquran.model.Chapters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {
    @GET("chapters/?language=id")
    Call<Chapters> getSurah();
    @GET("quran/verses/uthmani")
    Call<Verses> getAyat(@Query("chapter_number") int id);
    @GET("quran/translations/33")
    Call<Translations> getTerjemahan(@Query("chapter_number") int id);
    @GET("chapter_recitations/33?language=id")
    Call<Audio> getAudio();
}
