package com.sevenrmartsupermarket.pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.GeneralUtility;
import com.sevenrmartsupermarket.utilities.PageUtility;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class ManagepagesPage {

	WebDriver driver;

	Properties properties = new Properties();
	PageUtility pageutility;
	GeneralUtility generalutility = new GeneralUtility();
	WaitUtility waitutility;
	File f;
	JavascriptExecutor js;

	@FindBy(xpath = "//div[@class='content-header']//h1")
	private WebElement managePagesHeader;
	@FindBy(xpath = "//li[@class='breadcrumb-item']//a")
	private WebElement homeBtnHeaderTxt;
	@FindBy(xpath = "//input[@id='title']")
	private WebElement titleInputField;
	@FindBy(xpath = "//div[@class='note-editable card-block']")
	private WebElement descrptnInputField;
	@FindBy(xpath = "//input[@id='page']")
	private WebElement pageNameInputField;
	@FindBy(xpath = "//input[@id='main_img']")
	private WebElement chooseFileBtn;
	@FindBy(xpath = "//button[@class='btn btn-danger']")
	private WebElement saveButton;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]//td[5]//a[@class='btn btn-sm btn btn-danger btncss']")
	private WebElement firstTContntDeleteBtn;
	@FindBy(xpath = "//div[@class='float-right d-none d-sm-inline-block']")
	private WebElement footerElement;
	@FindBy(xpath = "//span[contains(text(), 'Next')]")
	private WebElement nextPageLink;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]//td[1]")
	private WebElement tableFrstTitleName;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-primary']")
	private WebElement searchButton;
	@FindBy(xpath = "//input[@class='form-control']")
	private WebElement searchTitleInptField;
	@FindBy(xpath = "//button[@class='btn btn-danger btn-fix']")
	private WebElement searchSubmitBtn;
	@FindBy(xpath = "//div[@class='card-header']//following-sibling::h4[contains(text(),'Search List Pages')]")
	private WebElement searchPgHeader;
	@FindBy(xpath = "//a[@class='btn btn-default btn-fix']")
	private WebElement resetBtn;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement successAlertMsgBox;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement failedAlertMsgBox;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	private WebElement newButton;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody")
	private WebElement tableRow;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]//td[1]")
	private WebElement frstTableUsername;

	public ManagepagesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public String getManagePagesTitle() {
		return managePagesHeader.getText();
	}

	public boolean checkWhetherHomeBtnHeaderTxtIsClickable() {

		return homeBtnHeaderTxt.isEnabled();
	}

	public void enterTitle(String titleName) {
		titleInputField.sendKeys(titleName);
	}

	public void enterDescription(String description) {
		descrptnInputField.sendKeys(description);
	}

	public void enterPageName(String pageName) {
		pageNameInputField.sendKeys(pageName);
	}

	public void clickOnSaveBtn() {

		saveButton.click();

	}

	public void uploadImgFile() {

		File f = new File(Constants.IMAGE_FILE_PATH);
		String absolutePath = f.getAbsolutePath();
		chooseFileBtn.sendKeys(absolutePath);

	}

	public void createNewPage(String titleName, String description, String pageName) {

		pageutility = new PageUtility(driver);
		enterTitle(titleName);
		enterDescription(description);
		enterPageName(pageName);
		uploadImgFile();
		pageutility.scrollAndClick(saveButton);
	}

	public void deleteNewPage() {
		firstTContntDeleteBtn.click();
		driver.switchTo().alert().accept();
	}

	public void clickOnNextPageBtn()  {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", footerElement);
		System.out.println(footerElement.getText());
		nextPageLink.click();
	}

	public String getFrstTitleNameFromTable() {
		return tableFrstTitleName.getText();
	}

	public void clickOnSearchButton() {
		searchButton.click();
	}

	public void enterSearchTitle(String title) {
		searchTitleInptField.sendKeys(title);
	}

	public void clickOnSearchSubmitButton() {
		searchSubmitBtn.click();
	}

	public void searchForAPageByTitleName(String titleName) {
		clickOnSearchButton();
		enterSearchTitle(titleName);
		clickOnSearchSubmitButton();

	}

	public String getSearchPageHeader() {

		return searchPgHeader.getText();

	}

	public boolean checkWhetherSearchPageHeaderIsDisplayed() {

		return searchPgHeader.isDisplayed();

	}

	public void clickOnResetButton() {
		resetBtn.click();
	}

	public boolean checkIfSuccessTextIsPresent(String expectedData) {

		return generalutility.is_TextPresent(successAlertMsgBox, expectedData);
	}

	public boolean checkIfFailedTextIsPresent(String expectedData) {

		return generalutility.is_TextPresent(failedAlertMsgBox, expectedData);
	}

	public ManagepagesPage clickOnNewButton() {
		newButton.click();
		return this;
	}

	public boolean checkRowTextValue(String expecetdRowText) {

		return generalutility.is_TextPresent(tableRow, expecetdRowText);
	}

	public String getFirstTableUsername() {
		return frstTableUsername.getText();
	}

}
