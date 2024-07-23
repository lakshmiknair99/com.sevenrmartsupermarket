package com.sevenrmartsupermarket.pages;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.GeneralUtility;
import com.sevenrmartsupermarket.utilities.PageUtility;

public class LoginPage {

	WebDriver driver;

	Properties properties = new Properties();
	PageUtility pageUtility;
	GeneralUtility generalutility = new GeneralUtility();

	@FindBy(xpath = "//input[@name='username']")
	private WebElement userNameField;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordField;
	@FindBy(xpath = "//button[contains(text(),'Sign In')]")
	private WebElement signInButton;
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement alertMessage;
	@FindBy(xpath="//input[@id='remember']")
	private WebElement remembMeCheckBox;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement failedAlertMsgBox;
	
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		try {

			FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE_PATH);
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterUserName(String userName) {
		userNameField.sendKeys(userName);

	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);

	}

	public void clickOnSignInButton() {
		signInButton.click();
	}

	public DashBoardPage login(String userName, String password) {
		enterUserName(userName);
		enterPassword(password);
		clickOnSignInButton();
		return new DashBoardPage(driver);
	}
	public boolean checkIfFailedTextIsPresent(String expectedData) {

		return generalutility.is_TextPresent(failedAlertMsgBox, expectedData);
	}
	

	public DashBoardPage login() {
		String userName = properties.getProperty("userName");
		String password = properties.getProperty("password");
		enterUserName(userName);
		enterPassword(password);
		clickOnSignInButton();
		return new DashBoardPage(driver);
	}
	public String getInvalidAlertMessage()
	{
		return alertMessage.getText();
	}
	public void clickOnRememberMeCheckBox()
	{
		remembMeCheckBox.click();
	}
	public boolean isRememberMeCheckBoxSelected()
	{
		String userName = properties.getProperty("userName");
		String password = properties.getProperty("password");
		enterUserName(userName);
		enterPassword(password);
		clickOnRememberMeCheckBox();
		return remembMeCheckBox.isSelected();
	}
	public boolean isAlertDisplayed()
	{
		pageUtility=new PageUtility(driver);
		pageUtility.js_Click(signInButton);
		return true;
	}
	
}
