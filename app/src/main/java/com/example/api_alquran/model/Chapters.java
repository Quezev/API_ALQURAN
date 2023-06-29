package com.example.api_alquran.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Chapters{

	@SerializedName("chapters")
	private List<ChaptersItem> chapters;

	public List<ChaptersItem> getChapters(){
		return chapters;
	}

	@Override
 	public String toString(){
		return 
			"Chapters{" + 
			"chapters = '" + chapters + '\'' + 
			"}";
		}
}