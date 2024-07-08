package it.smartpaper.app;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHeading3;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class App {

	public static void main(String[] args) throws IOException {
		WebClient client = new WebClient();
		client.getOptions().setJavaScriptEnabled(false);
		HtmlPage page = client.getPage("https://scrapingbee.com");
		DomNodeList<DomNode> features = page
				.querySelectorAll("section:nth-child(3) h3.text-20.leading-a26.text-black-100.mb-15");

		for (DomNode domNode : features) {
			HtmlHeading3 heading = (HtmlHeading3) domNode;

			System.out.println(heading.getTextContent());
			System.out.println();
		}

		DomNode h4WithText = page.getFirstByXPath("//*[contains(text(),'Learning Web Scraping')]");
		DomNode siblingUl = h4WithText.getNextElementSibling();
		DomNodeList<DomNode> lis = siblingUl.getChildNodes();

		for (DomNode domNode : lis) {
			if (domNode.getNodeType() == DomNode.ELEMENT_NODE) {
				HtmlAnchor link = (HtmlAnchor) domNode.getFirstChild();

				System.out.println(link.getTextContent());
				System.out.println(link.getBaseURI() + link.getHrefAttribute());
				System.out.println();
			}

		}

		client.getOptions().setJavaScriptEnabled(true);
		client.getOptions().setThrowExceptionOnScriptError(false);

		HtmlPage teamsPage = client.getPage("https://www.scrapethissite.com/pages/forms");
		HtmlForm form = teamsPage.getForms().get(0);

		System.out.println(form.getLocalName());
		HtmlSubmitInput button = form.getInputByValue("Search");
		HtmlTextInput textField = form.getInputByName("q");

		textField.type("Toronto");
		HtmlPage resultsPage = button.click();

		DomNodeList<DomNode> teams = resultsPage.querySelectorAll("tr.team");
		for (DomNode team : teams) {
			String year = team.querySelector(".year").getTextContent().strip();
			String losses = team.querySelector(".losses").getTextContent().strip();
			String gol = team.querySelector(".gf").getTextContent().strip();
			System.out.println("Year: " + year + ", Losses: " + losses + ", GoL Fatti " + gol);
		}

	}
}
