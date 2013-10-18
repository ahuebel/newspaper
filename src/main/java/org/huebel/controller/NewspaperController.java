package org.huebel.controller;

import java.util.List;

import org.huebel.domain.Newspaper;
import org.huebel.exception.NewspaperException;
import org.huebel.service.NewsPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to handle newspaper/ad requests.  The real
 * work is performed by the autowired service layer.
 *
 * @author ahuebel
 *
 */
@Controller
public class NewspaperController {

	/**
	 * internal service layer to act on
	 * ads/newspapers.
	 */
	@Autowired
	private NewsPaperService newsservice;

	/**
	 * Create a newspaper.
	 * 
	 * @param name - name of the newspaper to create/add.
	 */
	@RequestMapping(value = "/newspaper/{name}", method = { RequestMethod.POST })
	public @ResponseBody
	void createNewspaper(@PathVariable("name") final String name) {
		newsservice.createNewspaper(name);
	}

	/**
	 * Get a list of all the newspapers.
	 *
	 * @return List<Newspaper> - List of current newspapers.
	 */
	@RequestMapping(value = "/newspapers", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody
	List<Newspaper> getAllNewspapers() {
		return newsservice.getAllNewspapers();
	}

	/**
	 * Add an ad to a newspaper.
	 * 
	 * @param name - name of newspaper to insert ad into.
	 * @param ad - the advertisement text.
	 * @throws NewspaperException - If the newspaper is not found, throw an exception.
	 */
	@RequestMapping(value = "/newspaper/{name}/{ad}", method = RequestMethod.POST)
	public @ResponseBody
	void addAd(@PathVariable("name") final String name, @PathVariable("ad") final String ad)
			throws NewspaperException {
		newsservice.addAdToNewspaper(name, ad);
	}

	/**
	 * Get the ads for a given newspaper.
	 * 
	 * @param name - name of the newspaper to add the ad to.
	 * @return List<String> - return a list of ads for a given newspaper.
	 * @throws NewspaperException - if the newspaper requested is not found, throw an exception.
	 */
	@RequestMapping(value = "/newspaper/{name}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody
	 String getAds(@PathVariable("name") final String name)//List<String>
			throws NewspaperException {
		return newsservice.getAds(name).toString();
	}
}
