package pages.homepage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.BasePage;

public class HomePage extends BasePage{
	
	private By main_header = By.id("header");
	private By slider_section = By.id("slider");
	private By left_sidebar = By.className("left-sidebar");
	private By items_list = By.className("features_items");
	private By footer = By.id("footer");
	private List<By> body = new ArrayList<>();
	
	
	public HomePage(WebDriver driver) {
		super(driver);
		body.add(main_header);
		body.add(slider_section);
		body.add(left_sidebar);
		body.add(items_list);
		body.add(footer);
	}
	
	public boolean isMainHeaderVisible() {
		return isElementVisible(main_header);
	}
	
	public boolean isSliderSectionVisible() {
		return isElementVisible(slider_section);
	}
	
	public boolean isLeftSiderBarVisible() {
		return isElementVisible(left_sidebar);
	}
	
	public boolean isItemsContainerVisible() {
		return isElementVisible(items_list);
	}
	
	public boolean isFooterVisible() {
		return isElementVisible(footer);
	}
	
	public List<By> getBody() {
		return body;
	}
	
	public boolean isHomePageVisible() {
		
		if(isMainHeaderVisible() && isSliderSectionVisible() && isLeftSiderBarVisible() && isItemsContainerVisible() && isFooterVisible()) {
			return true;
		}
		
		return false;
	}
}
