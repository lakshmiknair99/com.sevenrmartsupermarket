package com.sevenrmartsupermarket.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.utilities.GeneralUtility;
import com.sevenrmartsupermarket.utilities.PageUtility;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class AdminUsersPage {

	WebDriver driver;

	PageUtility pageutility = new PageUtility(driver);
	WaitUtility waitutility = new WaitUtility(driver);
	GeneralUtility generalutility = new GeneralUtility();

	@FindBy(xpath = "//div[@class=\"content-header\"]//h1")
	private WebElement adminUserHeader;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	private WebElement newButton;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-primary']")
	private WebElement searchButton;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-warning']")
	private WebElement resetButton;
	@FindBy(xpath = "//label[@for='username']//following::input[@id='username']")
	private WebElement userNameField;
	@FindBy(xpath = "//label[@for='password']//following::input[@id='password']")
	private WebElement passwordField;
	@FindBy(xpath = "//label[@for='user_type']//following::select[@id='user_type']")
	private WebElement userTypeSelectDrpDown;
	@FindBy(xpath = "//div[@class='card-footer center']//following::button[@type='submit']")
	private WebElement saveButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement successAlertMsgBox;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement failedAlertMsgBox;
	@FindBy(xpath = "//button[@data-dismiss='alert']")
	private WebElement alertMsgBoxCloseBtn;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]//td[5]//a[@class='btn btn-sm btn btn-danger btncss']")
	private WebElement firstTContntDeleteBtn;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[1]")
	private List<WebElement> tableNames;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[3]")
	private List<WebElement> tableStatuses;
	@FindBy(xpath = "//button[@class='btn btn-block-sm btn-info' and @name='Update']")
	private WebElement updateButton;
	@FindBy(xpath = "//button[@name='Search']")
	WebElement searchSubmitBtn;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody")
	WebElement tableRow;
	@FindBy(xpath = "//label[@for='username']//following::input[@id='un']")
	WebElement searchUserNameField;
	@FindBy(xpath = "//label[@for='user_type']//following::select[@id='ut']")
	WebElement searchUserTypeDrpDown;
	@FindBy(xpath = "//span[contains(text(), 'Next')]")
	WebElement nextPageLink;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-warning']")
	WebElement resetBtn;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]//td[3]//span")
	WebElement tableStatusBtn;

	public AdminUsersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getAdminUsersTitle() {
		return adminUserHeader.getText();
	}

	public void clickOnNewButton() {
		newButton.click();
	}

	public void clickOnSearchButton() {
		searchButton.click();
	}

	public void enterUsername(String userName) {
		userNameField.sendKeys(userName);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void selectUserType(String value) {
		pageutility.select_Value(userTypeSelectDrpDown, value);
	}

	public void enterSearchUsername(String userName) {
		searchUserNameField.sendKeys(userName);
	}

	public void selectSearchUserType(String value) {
		pageutility.select_Value(searchUserTypeDrpDown, value);
	}

	public void clickOnSaveBtn() {
		saveButton.click();
	}

	public String getSuccessAlertMessage() {

		return successAlertMsgBox.getText();
	}

	public String getFailedAlertMessage() {

		return failedAlertMsgBox.getText();
	}

	public boolean checkIfSuccessTextIsPresent(String expectedData) {

		return generalutility.is_TextPresent(successAlertMsgBox, expectedData);
	}

	public boolean checkIfFailedTextIsPresent(String expectedData) {

		return generalutility.is_TextPresent(failedAlertMsgBox, expectedData);
	}

	public boolean checkRowTextValue(String expecetdRowText) {

		return generalutility.is_TextPresent(tableRow, expecetdRowText);
	}

	public void clickOnAlertMsgCloseBtn() {

		alertMsgBoxCloseBtn.click();

	}

	public void clickOnUpdateButton() {
		updateButton.click();
	}

	public void createNewAdminUser(String userName, String password, String userType) {
		enterUsername(userName);
		enterPassword(password);
		selectUserType(userType);
		clickOnSaveBtn();
	}

	public void deleteNewUser() {
		firstTContntDeleteBtn.click();
		driver.switchTo().alert().accept();
	}

	public String getUserTypeofaPerson(String userName) {
		List<String> names = new ArrayList<String>();
		names = generalutility.get_TextofElements(tableNames);
		int index = 0;
		for (index = 0; index < names.size(); index++) {
			if (names.get(index).equals(userName)) {
				index++;
				break;
			}
		}

		WebElement tUserType = driver.findElement(By
				.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[2]"));
		return tUserType.getText();
	}

	public void clickOnTableStatusElement(String userName) {
		List<String> names = new ArrayList<String>();
		names = generalutility.get_TextofElements(tableNames);
		int index = 0;
		for (index = 0; index < names.size(); index++) {
			if (names.get(index).equals(userName)) {
				index++;
				break;
			}
		}
		WebElement tableUserStatus = driver.findElement(By.xpath(
				"//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[3]//span"));
		tableUserStatus.click();

	}

	public void clickOnAUserUpdateBtnInTable(String userName) {
		List<String> names = new ArrayList<String>();
		names = generalutility.get_TextofElements(tableNames);
		int index = 0;
		for (index = 0; index < names.size(); index++) {
			if (names.get(index).equals(userName)) {
				index++;
				break;
			}
		}
		WebElement tUpdateButton = driver
				.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index
						+ "]//td[5]//a[@class='btn btn-sm btn btn-primary btncss']"));
		tUpdateButton.click();

	}

	public void selectUserTypeAndUpdate(String newUserTypeValue) {
		selectUserType(newUserTypeValue);
		clickOnUpdateButton();
	}

	public void deleteAUser(String userName) {
		List<String> names = new ArrayList<String>();
		names = generalutility.get_TextofElements(tableNames);
		int index = 0;
		for (index = 0; index < names.size(); index++) {
			if (names.get(index).equals(userName)) {
				index++;
				break;
			}
		}
		WebElement tDeleteButton = driver
				.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index
						+ "]//td[5]//a[@class='btn btn-sm btn btn-danger btncss']"));
		tDeleteButton.click();
		driver.switchTo().alert().accept();
	}

	public void updateAUser(String userName) {
		List<String> names = new ArrayList<String>();
		names = generalutility.get_TextofElements(tableNames);
		int index = 0;
		for (index = 0; index < names.size(); index++) {
			if (names.get(index).equals(userName)) {
				index++;
				break;
			}
		}
		WebElement tUpdateButton = driver
				.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index
						+ "]//td[5]//a[@class='btn btn-sm btn btn-primary btncss']"));
		tUpdateButton.click();

	}

	public void clickOnSearchSubmitButton() {
		searchSubmitBtn.click();
	}

	public void searchForAPersonByUserNameAndUserType(String userName, String userTypeValue) {
		clickOnSearchButton();
		enterSearchUsername(userName);
		selectSearchUserType(userTypeValue);
		clickOnSearchSubmitButton();

	}

	public void searchForAPersonByUserName(String userName) {
		clickOnSearchButton();
		enterSearchUsername(userName);
		clickOnSearchSubmitButton();

	}

	public String getTableRowText() {
		return tableRow.getText();
	}

	public void clickOnNextPage() {

		pageutility.scrollAndClick(nextPageLink);

	}

	public void clickOnResetButton() {
		resetBtn.click();
	}

	public void clickOnTableStatusButton() {
		tableStatusBtn.click();
	}

	public boolean checkWhetherAlertBoxIsVisible() {
		try {

			return successAlertMsgBox.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}

	}

}
