package com.example.api_alquran.TerjemahanModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Translations{

	@SerializedName("translations")
	private List<TranslationsItem> translations;

	@SerializedName("meta")
	private Meta meta;

	public List<TranslationsItem> getTranslations(){
		return translations;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"Translations{" + 
			"translations = '" + translations + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}