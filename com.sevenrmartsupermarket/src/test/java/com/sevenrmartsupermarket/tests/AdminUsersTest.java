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
import com.sevenrmartsupermarket.utilities.ExcelReader;
import com.sevenrmartsupermarket.utilities.GeneralUtility;

public class AdminUsersTest extends Base {

	LoginPage loginpage;
	DashBoardPage dashboardpage;
	AdminUsersPage adminuserspage;
	ExcelReader excelreader = new ExcelReader();
	SoftAssert softassert = new SoftAssert();

	@Test(groups = "regression")
	public void verifyAdminUsersTitle() {

		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		adminuserspage = dashboardpage.navigateToAdminUsers();
		String actualTitle = adminuserspage.getAdminUsersTitle();
		String expecetdTitle = Constants.ADMINUSERS_PAGE_TITLE;
		Assert.assertEquals(actualTitle, expecetdTitle);
	}

	@Test(retryAnalyzer = RetryAnalyzers.class)
	public void verifySuccessfulNewAdminUserCreation() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(2, 0) + " " + GeneralUtility.get_RandomFirstName();
		String newPassword = excelreader.getCellData(2, 1);
		String newUserType = excelreader.getCellData(2, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		adminuserspage = dashboardpage.navigateToAdminUsers();
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		Assert.assertTrue(adminuserspage.checkIfSuccessTextIsPresent(Constants.USER_CREATION_SUCCS_MSG));
	}

	@Test(groups = "sanity")
	public void verifySuccessfulDeletionofNewAdminUser() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(3, 0) + " " + GeneralUtility.get_RandomFirstName();
		String newPassword = excelreader.getCellData(3, 1);
		String newUserType = excelreader.getCellData(3, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		adminuserspage = dashboardpage.navigateToAdminUsers();
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		adminuserspage.clickOnAlertMsgCloseBtn();
		adminuserspage.deleteNewUser();
		boolean isDeleteMsgPresent = adminuserspage.checkIfSuccessTextIsPresent(Constants.USER_DELETION_SUCCS_MSG);
		Assert.assertTrue(isDeleteMsgPresent);

	}

	@Test(groups = { "regression", "smoke" })
	public void verifyUnsuccessfulNewAdminUserCreation() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(7, 0);
		String newPassword = excelreader.getCellData(7, 1);
		String newUserType = excelreader.getCellData(7, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		adminuserspage = dashboardpage.navigateToAdminUsers();
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		Assert.assertTrue(adminuserspage.checkIfFailedTextIsPresent(Constants.UNAME_ALRDY_EXST_MSG));

	}

	@Test(groups = "sanity")
	public void verifyUserTypeofNewUser() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(3, 0) + " " + GeneralUtility.get_RandomFirstName();
		String newPassword = excelreader.getCellData(3, 1);
		String newUserType = excelreader.getCellData(3, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		adminuserspage = dashboardpage.navigateToAdminUsers();
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		String actualUserType = adminuserspage.getUserTypeofaPerson(newUserName);
		String expecetedUserType = newUserType;
		Assert.assertEquals(actualUserType, expecetedUserType);
	}

	@Test(groups = "sanity")
	public void verifyTableStatusElementofAUserIsClickable() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(3, 0) + " " + GeneralUtility.get_RandomFirstName();
		String newPassword = excelreader.getCellData(3, 1);
		String newUserType = excelreader.getCellData(3, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		adminuserspage = dashboardpage.navigateToAdminUsers();
		adminuserspage.clickOnNewButton().createNewAdminUser(newUserName, newPassword, newUserType)
				.clickOnTableStatusElement(newUserName);
		Assert.assertTrue(adminuserspage.checkIfSuccessTextIsPresent(Constants.USER_STATCHANGE_SUCCS_MSG));

	}

	@Test(groups = "sanity")
	public void verifySuccessfullUpdationofAUser() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(3, 0) + " " + GeneralUtility.get_RandomFirstName();
		String newPassword = excelreader.getCellData(3, 1);
		String newUserType = excelreader.getCellData(3, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		adminuserspage = dashboardpage.navigateToAdminUsers();
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		adminuserspage.updateAUser(newUserName);
		adminuserspage.selectUserTypeAndUpdate(newUserType);
		boolean updateStatus = adminuserspage.checkIfSuccessTextIsPresent(Constants.USER_UPDATION_SUCCS_MSG);
		softassert.assertTrue(updateStatus);
		String updateUserType = adminuserspage.getUserTypeofaPerson(newUserName);
		if (updateUserType.equals(newUserType)) {
			System.out.println("User Updation Successfull");
		} else {
			System.out.println("User Updation Failed");
		}

	}

	@Test(groups = "sanity")
	public void verifySuccessfullDeletionofAUser() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(3, 0) + " " + GeneralUtility.get_RandomFirstName();
		String newPassword = excelreader.getCellData(3, 1);
		String newUserType = excelreader.getCellData(3, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		adminuserspage = dashboardpage.navigateToAdminUsers();
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		adminuserspage.deleteAUser(newUserName);
		boolean deleteStatus = adminuserspage.checkIfSuccessTextIsPresent(Constants.USER_DELETION_SUCCS_MSG);
		softassert.assertTrue(deleteStatus);
		adminuserspage.searchForAPersonByUserName(newUserName);
		boolean isRowTextPresent = adminuserspage.checkRowTextValue(Constants.NO_RESULT_FND_MSG);
		Assert.assertTrue(isRowTextPresent);
	}

	@Test(groups = "regression")
	public void verifySearchOfAPerson() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(3, 0) + " " + GeneralUtility.get_RandomFirstName();
		String newPassword = excelreader.getCellData(3, 1);
		String newUserType = excelreader.getCellData(3, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		adminuserspage = dashboardpage.navigateToAdminUsers();
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		adminuserspage.searchForAPersonByUserName(newUserName);
		Assert.assertTrue(adminuserspage.checkRowTextValue(newUserName));
	}

	@Test
	public void verifyAlertMsgIsClosedWhileClickingOnResetBtn() {
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login();
		adminuserspage = dashboardpage.navigateToAdminUsers();
		adminuserspage.clickOnTableStatusButton();
		Assert.assertTrue(adminuserspage.checkIfSuccessTextIsPresent(Constants.USER_STATCHANGE_SUCCS_MSG));
		Assert.assertTrue(adminuserspage.checkWhetherAlertBoxIsVisible());
		adminuserspage.clickOnResetButton();
		Assert.assertFalse(adminuserspage.checkWhetherAlertBoxIsVisible());
	}

}
