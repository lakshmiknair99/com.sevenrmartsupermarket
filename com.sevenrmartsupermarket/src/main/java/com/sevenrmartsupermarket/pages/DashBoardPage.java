package com.sevenrmartsupermarket.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class DashBoardPage {

	WebDriver driver;
	WaitUtility waitutility;
	Actions actions;

	@FindBy(xpath = "//a[@class='d-block']")
	private WebElement profileName;
	@FindBy(xpath="//section//div[@class='container-fluid']//a")
	private List<WebElement> dashBoardCards;
	@FindBy(xpath="//section//div[@class='container-fluid']//div//p")
	private List<WebElement> cardNames;
	@FindBy(xpath="//a[@class='nav-link']//following::i[@class='nav-icon sidebar-item-icon fa fa-cog']")
	private WebElement settingsOptn;
	@FindBy(xpath = "//li[@class='breadcrumb-item']//a")
	private WebElement homeBtnHeaderTxt;
	@FindBy(xpath = "//a[@data-widget='pushmenu']")
	private WebElement pushMenuBtn;
	@FindBy(xpath = "//div[@class='os-content']")
	private WebElement marginContnt;
	
	
	

	public DashBoardPage(WebDriver driver) {                         
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getProfileName() {
		return profileName.getText();
	}
	public boolean navigateToCard(String name) 
	{
		int i=0;
		for(i=0;i<cardNames.size();i++)
		{
			String infoName=cardNames.get(i).getText(); 
			if(infoName.equalsIgnoreCase(name))  
			{
				break;
			}
		}
		dashBoardCards.get(i).click();
		return true;
	}
	public AdminUsersPage navigateToAdminUsers()
	{
		int i=0;
		for(i=0;i<cardNames.size();i++)
		{
			String infoName=cardNames.get(i).getText(); 
			if(infoName.equalsIgnoreCase(Constants.ADMIN_USERS_CARD))  
			{
				break;
			}
		}
		dashBoardCards.get(i).click();
		return new AdminUsersPage(driver);
	}
	
	public ManagepagesPage navigateToManagePagesCard()
	{
		int i=0;
		for(i=0;i<cardNames.size();i++)
		{
			String infoName=cardNames.get(i).getText(); 
			if(infoName.equalsIgnoreCase(Constants.MANAGE_PAGES_CARD))  
			{
				break;
			}
		}
		dashBoardCards.get(i).click();
		return new ManagepagesPage(driver);
	}
	
	public void clickOnSettingsOption()
	{
		settingsOptn.click();
	}
	
	public boolean checkWhetherHomeBtnHeaderTxtIsClickable() {

		return homeBtnHeaderTxt.isEnabled();
	}
	public void clickOnPushBtn() 
	{
		waitutility=new WaitUtility(driver);
		waitutility.waitForElementToBeClickable(pushMenuBtn, 20);
		pushMenuBtn.click();
	
	}
	public boolean isMarginProfileNameVisible()
	{
		return profileName.isDisplayed();
		
	}
	public void mouseOverToMarginContent()
	{
		actions=new Actions(driver);
		actions.moveToElement(marginContnt).build().perform();
	}

	

}
