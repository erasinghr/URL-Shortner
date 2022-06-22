package com.crio.shorturl;

import java.util.HashMap;

public class XUrlImpl implements XUrl {
	HashMap<String, String>shortToLong;
	HashMap<String, Pair<String, Integer>>longToShort;
	SlugGenerator slugGenerator;
	private static final String URL = "http://short.url/";
	public XUrlImpl(){
		shortToLong = new HashMap<>();
		longToShort = new HashMap<>();
		slugGenerator = new SlugGenerator();
	}
	@Override
	public String registerNewUrl(String longUrl) {
		if(!longToShort.containsKey(longUrl)){
			String slug = slugGenerator.generateRandomSlug();
			longToShort.put(longUrl, new Pair<>(URL+slug, 0));
			shortToLong.put(URL+slug, longUrl);
		}
		return longToShort.get(longUrl).getKey();
	}

	@Override
	public String registerNewUrl(String longUrl, String shortUrl) {
		if(shortToLong.containsKey(shortUrl)){
			return null;
		}
		else{
			shortToLong.put(shortUrl, longUrl);
			longToShort.put(longUrl, new Pair<String, Integer>(shortUrl, 0));
			return longToShort.get(longUrl).getKey();
		}
	}

	@Override
	public String getUrl(String shortUrl) {
		if(shortToLong.containsKey(shortUrl)){
			String longUrl = shortToLong.get(shortUrl);
			longToShort.get(longUrl).setValue(longToShort.get(longUrl).getValue()+1);
			return shortToLong.get(shortUrl);
		}
		else{
			return null;
		}
	}

	@Override
	public Integer getHitCount(String longUrl) {
		if(longToShort.containsKey(longUrl)){
			return longToShort.get(longUrl).getValue();
		}
		return 0;
	}

	@Override
	public String delete(String longUrl) {
		shortToLong.remove(longToShort.get(longUrl).getKey());
		return null;
	}
	
}