package org.huebel.service;

import java.util.ArrayList;
import java.util.List;

import org.huebel.domain.Newspaper;
import org.huebel.exception.NewspaperException;
import org.springframework.stereotype.Service;

/**
 * Newspaper service implementation to create newspapers, create ads for newspapers
 * and retrieve all the ads for a particular newspaper.
 * 
 * @author ahuebel
 *
 */
@Service
public class NewsPaperServiceImpl implements NewsPaperService {

	//list of newspapers
	private List<Newspaper> newspapers = new ArrayList<Newspaper>();
	
	/**
	 * Create a newspaper.
	 * 
	 * @param name - name of newspaper to create.
	 */
	@Override
	public final void createNewspaper(final String name) {
		Newspaper newspaper = new Newspaper(name);
		newspapers.add(newspaper);
	}

	/**
	 *  Get the Ads for a newspaper.
	 *  
	 *  @param newspaper - name of newspaper to retrieve ads for.
	 *  @return List<String> - list of ads for a newspaper.
	 *  @throws - NespaperException is thrown if the newspaper is not found.
	 */
	@Override
	public List<String> getAds(final String newspaper) throws NewspaperException {
		List<String> ads = new ArrayList<String>();
		boolean newspaperFound = false;
		for (Newspaper np : newspapers) {
			if (np.getName().compareToIgnoreCase(newspaper) == 0) {
				ads.addAll(np.getAds());
				newspaperFound = true;
			}
		}
		if (!newspaperFound) {
			throw new NewspaperException("Newspaper " + newspaper
					+ " not found.");
		}
		return ads;
	}

	/**
	 * Add an Ad to a Newspaper.
	 * 
	 * @param newspaper - the newspaper to create the ad for.
	 * @param ad - advertisement text to add to newspaper.
	 * @throws NewspaperException - If the newspaper is not found, throw exception.
	 */
	@Override
	public void addAdToNewspaper(final String newspaper, final String ad)
			throws NewspaperException {
		boolean found = false;
		for (Newspaper np : newspapers) {
			if (np.getName().compareToIgnoreCase(newspaper) == 0) {
				found = true;
				np.addAd(ad);
			}
		}
		if (!found) {
			throw new NewspaperException("Newspaper " + newspaper
					+ " not found.");
		}
	}

	/**
	 *  Get a List of all the newspapers.
	 *  
	 *  @return List<Newspaper> - list of current newspapers.
	 */
	@Override
	public List<Newspaper> getAllNewspapers() {
		return newspapers;
	}

}
