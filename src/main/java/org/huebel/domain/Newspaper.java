package org.huebel.domain;

import java.util.ArrayList;
import java.util.List;

public class Newspaper {
	private String name;
	private List<String> ads;

	public Newspaper(final String name) {
		this.name = name;
		ads = new ArrayList<String>();
	}

	public void addAd(final String ad) {
		ads.add(ad);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAds() {
		return ads;
	}

	public void setAds(List<String> ads) {
		this.ads = ads;
	}

}
