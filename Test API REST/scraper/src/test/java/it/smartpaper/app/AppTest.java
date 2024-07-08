package it.smartpaper.app;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class AppTest {

	@Test
	public void homePage() throws Exception {
	    try (final WebClient webClient = new WebClient()) {
	        final HtmlPage page = webClient.getPage("https://www.htmlunit.org/");
			assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());

			final String pageAsXml = page.asXml();
			assertTrue(pageAsXml.contains("<body class=\"topBarDisabled\">"));
			// System.out.println("Page As XML" + pageAsXml);

			final String pageAsText = page.asNormalizedText();
			assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
			// System.out.println("Page As Text" + pageAsText);
		}
	}

	@Test
	public void submittingForm() throws Exception {
		try (final WebClient webClient = new WebClient()) {
			// Get the first page
			final HtmlPage page1 = webClient.getPage("https://www.w3schools.com/html/html_forms.asp");

			// Get the form that we are dealing with and within that form,
			// find the submit button and the field that we want to change.
			final HtmlForm form = page1.getForms().get(0);

            final HtmlTextInput firstNameField = form.getInputByName("fname");
            final HtmlTextInput lastNameField = form.getInputByName("lname");

            // Change the values of the text fields
            firstNameField.type("John");
            lastNameField.type("Doe");

			// Change the value of the text field
            final HtmlSubmitInput button = (HtmlSubmitInput) form.getInputByValue("Submit");

			// Now submit the form by clicking the button and get back the second page.
			final HtmlPage page2 = button.click();
		}
	}
}
