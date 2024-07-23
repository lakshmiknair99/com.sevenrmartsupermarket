package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.pages.DashBoardPage;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.pages.ManagepagesPage;

public class DashBoardTest extends Base {

	DashBoardPage dashboardpage;
	LoginPage loginpage;
	SoftAssert softassert = new SoftAssert();

	@Test
	public void verifyWhetherManagePagesCardIsClickable() {
		dashboardpage = new DashBoardPage(driver);
		loginpage = new LoginPage(driver);
		loginpage.login();
		Assert.assertTrue(dashboardpage.navigateToCard(Constants.MANAGE_PAGES_CARD));
	}

	@Test
	public void verifyWhetherAdminUsersCardIsClickable() {
		dashboardpage = new DashBoardPage(driver);
		loginpage = new LoginPage(driver);
		loginpage.login();
		Assert.assertTrue(dashboardpage.navigateToCard(Constants.ADMIN_USERS_CARD));
	}

	@Test
	public void verifyHomeBtnInTheDashBoardHeaderIsClickable() {
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		loginpage.login();
		Assert.assertTrue(dashboardpage.checkWhetherHomeBtnHeaderTxtIsClickable());
	}

	@Test
	public void verifyMarginContentTxtsNotVisibleWhileClickngOnPushBtn() {
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		loginpage.login();
		dashboardpage.clickOnPushBtn();
		Assert.assertFalse(dashboardpage.isMarginProfileNameVisible());
	}

	@Test
	public void verifyInvisibleMarginContentTxtsWillVisibleAftrMouseOvrToMargin() {
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		loginpage.login();
		dashboardpage.clickOnPushBtn();
		softassert.assertFalse(dashboardpage.isMarginProfileNameVisible());
		dashboardpage.mouseOverToMarginContent();
		Assert.assertTrue(dashboardpage.isMarginProfileNameVisible());

	}

}
