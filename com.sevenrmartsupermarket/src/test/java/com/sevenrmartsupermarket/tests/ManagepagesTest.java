package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.listeners.RetryAnalyzers;
import com.sevenrmartsupermarket.pages.AdminUsersPage;
import com.sevenrmartsupermarket.pages.DashBoardPage;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.pages.ManagepagesPage;
import com.sevenrmartsupermarket.utilities.ExcelReader;
import com.sevenrmartsupermarket.utilities.GeneralUtility;

public class ManagepagesTest extends Base {

	LoginPage loginpage;
	DashBoardPage dashboardpage;
	ManagepagesPage managepagespage;
	AdminUsersPage adminuserspage;
	ExcelReader excelreader = new ExcelReader();
	SoftAssert softAssert = new SoftAssert();

	@Test(groups = "regression")
	public void verifyManagePagesTitle() {

		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		managepagespage = dashboardpage.navigateToManagePagesCard();
		String actualTitle = managepagespage.getManagePagesTitle();
		String expecetdTitle = Constants.MANAGEPAGES_PAGE_TITLE;
		Assert.assertEquals(actualTitle, expecetdTitle);

	}

	@Test
	public void verifyHomeBtnInTheHeaderIsClickable() {
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		managepagespage = dashboardpage.navigateToManagePagesCard();
		softAssert.assertTrue(managepagespage.checkWhetherHomeBtnHeaderTxtIsClickable());
	}

	@Test
	public void verifySuccessfulNewPageCreation() {
		excelreader.setExcelFile("Logindata", "sheet1");
		String newTitle = excelreader.getCellData(1, 0) + " " + GeneralUtility.get_RandomFirstName();
		String newDescription = excelreader.getCellData(1, 1) + " " + GeneralUtility.get_RandomSentence();
		String newPageName = excelreader.getCellData(1, 2) + " " + GeneralUtility.get_RandomFirstName();
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		managepagespage = dashboardpage.navigateToManagePagesCard();
		managepagespage.clickOnNewButton();
		managepagespage.createNewPage(newTitle, newDescription, newPageName);
		Assert.assertTrue(managepagespage.checkIfSuccessTextIsPresent(Constants.PAGE_CREATION_SUCCS_MSG));
		managepagespage.deleteNewPage();

	}

	@Test(retryAnalyzer = RetryAnalyzers.class)
	public void verifyNextPageNavigation() {

		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		managepagespage = dashboardpage.navigateToManagePagesCard();
		String frstPageUname = managepagespage.getFirstTableUsername();
		managepagespage.clickOnNextPageBtn();
		String nextPageUname = managepagespage.getFirstTableUsername();
		Assert.assertNotEquals(frstPageUname, nextPageUname);
	}

	@Test(groups = "regression")
	public void verifySearchOfAPageByTitle() {

		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		managepagespage = dashboardpage.navigateToManagePagesCard();
		String firstTableTtlName = managepagespage.getFrstTitleNameFromTable();
		managepagespage.searchForAPageByTitleName(firstTableTtlName);
		boolean isRowTextPresent = managepagespage.checkRowTextValue(firstTableTtlName);
		Assert.assertTrue(isRowTextPresent);

	}

	@Test(groups = "regression")
	public void verifyPagellResetWhileClickngOnResetBtnAftrPageSearch() {

		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		managepagespage = dashboardpage.navigateToManagePagesCard();
		String firstTableTtlName = managepagespage.getFrstTitleNameFromTable();
		managepagespage.searchForAPageByTitleName(firstTableTtlName);
		String searchPageHeader = managepagespage.getSearchPageHeader();
		System.out.println(searchPageHeader);
		boolean isRowTextPresent = managepagespage.checkRowTextValue(firstTableTtlName);
		softAssert.assertTrue(isRowTextPresent);
		managepagespage.clickOnResetButton();
		Assert.assertFalse(managepagespage.checkWhetherSearchPageHeaderIsDisplayed());

	}

}
