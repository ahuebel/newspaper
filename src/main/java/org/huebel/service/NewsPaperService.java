package org.huebel.service;

import java.util.List;

import org.huebel.domain.Newspaper;
import org.huebel.exception.NewspaperException;

public interface NewsPaperService {
	public void createNewspaper(final String name);

	public void addAdToNewspaper(final String newspaper, final String ad) throws NewspaperException;

	public List<String> getAds(final String newspaper)
			throws NewspaperException;

	public List<Newspaper> getAllNewspapers();
}
