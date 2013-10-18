package org.huebel;

import java.util.ArrayList;
import java.util.List;

import org.huebel.controller.NewspaperController;
import org.huebel.exception.NewspaperException;
import org.huebel.service.NewsPaperService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * The unit test uses Springs MockMvc and Mockito to test
 * the NewspaperController.  We want to test the controller only, not
 * the service it contains.
 * 
 * @author ahuebel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class NewspaperControllerTest {

	@Autowired
	private WebApplicationContext ctx;

	@Mock
	NewsPaperService newsservice;

	@InjectMocks
	NewspaperController newspaperController;

	private MockMvc mockMvc;

	/**
	 * Set up mocks. Inject service mock into 
	 * controller. Using 'standalone' mode because we
	 * want to specifically mock the internal service
	 * of the controller.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(newspaperController)
				.build();
	}
	
	/**
	 * Create Newspaper. 
	 * 
	 * @throws Exception
	 */
	@Test
	public void createNewspaper() throws Exception {
		System.out.println("Testing creating a newspaper...");
		String name = "Ann Arbor News";
		mockMvc.perform(
				post("/newspaper/{name}", name).accept(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		Mockito.verify(newsservice).createNewspaper(name);
		Mockito.verify(newsservice, Mockito.times(1)).createNewspaper(name);

	}

	/**
	 * Create an ad for a newspaper.
	 * 
	 * @throws Exception
	 */
	@Test
	public void createAd() throws Exception {
		System.out.println("Testing creating an ad for a newspaper...");
		String name = "Ann Arbor News";
		String ad = "Go 4 it";

		mockMvc.perform(
				post("/newspaper/{name}/{ad}", name, ad).accept(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());
		
		Mockito.verify(newsservice).addAdToNewspaper(name, ad);
		Mockito.verify(newsservice, Mockito.times(1)).addAdToNewspaper(name, ad);

	}
	
	/**
	 * Test getting an ad (happy path).
	 * @throws Exception
	 */
	@Test
	public void getAd() throws Exception {
		System.out.println("Testing getting an ad for a newspaper...");
		String name = "Ann Arbor News";
		List<String> ads = new ArrayList<String>();
		ads.add("Go 4 it");

		Mockito.when(newsservice.getAds(name)).thenReturn(ads);

		String adJson = ("[Go 4 it]");

		mockMvc.perform(
				get("/newspaper/{name}", name).accept(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(adJson)).andDo(print());
		
		Mockito.verify(newsservice).getAds(name);
		Mockito.verify(newsservice, Mockito.times(1)).getAds(name);
	}

	/**
	 * Test getting an ad that throws an exception.
	 * 
	 * @throws Exception
	 */
	@Test(expected = org.springframework.web.util.NestedServletException.class)
	public void getAdErrorPath() throws Exception {
		System.out.println("Testing creating an ad for a newspaper that doesn't exist...");
		String name = "Detroit News";

		Mockito.when(newsservice.getAds(name)).thenThrow(
				new NewspaperException());

		mockMvc.perform(
				get("/newspaper/{name}", name).accept(
						MediaType.APPLICATION_JSON)).andDo(print());
		
		fail("An exception should have been thrown!");

	}

	/**
	 * Configuration. 
	 * @author ahuebel
	 *
	 */
	@Configuration
	@EnableWebMvc
	public static class TestConfiguration {
	}

}
