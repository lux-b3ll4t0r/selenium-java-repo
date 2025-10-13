package ui.pages.delete;

import org.openqa.selenium.By;

import ui.utils.Webtool;

public class DeleteAccount {
	
	private By deletedMessage = By.xpath("//b[text() = 'Account Deleted!']");
	
	public boolean isDeleteMessageVisible() {return Webtool.isElementVisible(deletedMessage);}
}
