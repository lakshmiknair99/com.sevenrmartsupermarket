package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.pages.DashBoardPage;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.utilities.ExcelReader;
import com.sevenrmartsupermarket.utilities.ScreenshotCapture;

import DataProviders.Dataproviders;

public class LoginTest extends Base {

	LoginPage loginpage;
	DashBoardPage dashboardpage;
	ExcelReader excelreader = new ExcelReader();

	@Test(dataProvider = "credentials", dataProviderClass = Dataproviders.class)
	public void verifyLogin(String userName, String password, String profileName) {

		loginpage = new LoginPage(driver);

		dashboardpage = loginpage.login(userName, password);
		String actualProfileName = dashboardpage.getProfileName();
		Assert.assertEquals(actualProfileName, profileName);

	}

	@Test(groups = "smoke")
	public void verifyInvalidLogin() {

		excelreader.setExcelFile("Logindata", "credentials");
		String userName = excelreader.getCellData(1, 0);
		String password = excelreader.getCellData(1, 1);
		loginpage = new LoginPage(driver);
		loginpage.login(userName, password);
		Assert.assertTrue(loginpage.checkIfFailedTextIsPresent(Constants.INVALID_LOGIN_MSG));
	}

	@Test(groups = "regression")
	public void verifyWhetherRememberMeChechkBxIsSelectedOrNot() {
		loginpage = new LoginPage(driver);
		Assert.assertTrue(loginpage.isRememberMeCheckBoxSelected());

	}

	@Test(groups = "smoke")
	public void verifyTheAlertWhileClickingOnSgnInBtnWithoutUnameAndPasswrd() {
		loginpage = new LoginPage(driver);
		Assert.assertTrue(loginpage.isAlertDisplayed());

	}

}
