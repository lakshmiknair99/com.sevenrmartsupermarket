package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
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

	@Test(groups = "regression")
	public void verifyAdminUsersTitle() {

		excelreader.setExcelFile("Logindata", "credentials");
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		String actualTitle = adminuserspage.getAdminUsersTitle();
		String expecetdTitle = "Admin Users";
		Assert.assertEquals(actualTitle, expecetdTitle);
	}

	@Test(retryAnalyzer = RetryAnalyzers.class)
	public void verifySuccessfulNewAdminUserCreation() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(2, 0) + " " + GeneralUtility.get_RandomFirstName();
		String newPassword = excelreader.getCellData(2, 1);
		String newUserType = excelreader.getCellData(2, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		String actualAlertMsg = adminuserspage.getSuccessAlertMessage();
		System.out.println(actualAlertMsg);
		String expectedAlertMsg = "User Created Successfully";
		Assert.assertTrue(actualAlertMsg.contains(expectedAlertMsg));
	}

	@Test(groups = "sanity")
	public void verifySuccessfulDeletionofNewAdminUser() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(3, 0) + " " + GeneralUtility.get_RandomFirstName();
		String newPassword = excelreader.getCellData(3, 1);
		String newUserType = excelreader.getCellData(3, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		adminuserspage.clickOnAlertMsgCloseBtn();
		adminuserspage.deleteNewUser();
		boolean isDeleteMsgPresent = adminuserspage.checkIfSuccessTextIsPresent("User Deleted Successfully");
		Assert.assertTrue(isDeleteMsgPresent);

	}

	@Test(groups = { "regression", "smoke" })
	public void verifyUnsuccessfulNewAdminUserCreation() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(7, 0);
		String newPassword = excelreader.getCellData(7, 1);
		String newUserType = excelreader.getCellData(7, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		Assert.assertTrue(adminuserspage.checkIfFailedTextIsPresent("Username already exists."));

	}

	@Test(groups = "sanity")
	public void verifyUserTypeofNewUser() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(3, 0) + " " + GeneralUtility.get_RandomFirstName();
		String newPassword = excelreader.getCellData(3, 1);
		String newUserType = excelreader.getCellData(3, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
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
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		adminuserspage.clickOnTableStatusElement(newUserName);
		Assert.assertTrue(adminuserspage.checkIfSuccessTextIsPresent("User Status Changed Successfully"));

	}


	@Test(groups = "sanity")
	public void verifySuccessfullUpdationofAUser() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(3, 0) + " " + GeneralUtility.get_RandomFirstName();
		String newPassword = excelreader.getCellData(3, 1);
		String newUserType = excelreader.getCellData(3, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		adminuserspage.updateAUser(newUserName);
		adminuserspage.selectUserTypeAndUpdate(newUserType);
		boolean updateStatus = adminuserspage.checkIfSuccessTextIsPresent("User Updated Successfully");
		Assert.assertTrue(updateStatus);
		String updateUserType = adminuserspage.getUserTypeofaPerson(newUserName);
		System.out.println(updateUserType);
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
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		adminuserspage.deleteAUser(newUserName);
		boolean deleteStatus = adminuserspage.checkIfSuccessTextIsPresent("User Deleted Successfully");
		Assert.assertTrue(deleteStatus);
		adminuserspage.searchForAPersonByUserName(newUserName);
		boolean isRowTextPresent = adminuserspage.checkRowTextValue(".........RESULT NOT FOUND.......");
		Assert.assertTrue(isRowTextPresent);
	}

	@Test(groups = "regression")
	public void verifySearchOfAPerson() {

		excelreader.setExcelFile("Logindata", "credentials");
		String newUserName = excelreader.getCellData(3, 0) + " " + GeneralUtility.get_RandomFirstName();
		String newPassword = excelreader.getCellData(3, 1);
		String newUserType = excelreader.getCellData(3, 2);
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnNewButton();
		adminuserspage.createNewAdminUser(newUserName, newPassword, newUserType);
		adminuserspage.searchForAPersonByUserName(newUserName);
		Assert.assertTrue(adminuserspage.checkRowTextValue(newUserName));
	}
	
	@Test
	public void verifyAlertMsgIsClosedWhileClickingOnResetBtn() throws InterruptedException
	{
		loginpage = new LoginPage(driver);
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserspage.clickOnTableStatusButton();
		Assert.assertTrue(adminuserspage.checkIfSuccessTextIsPresent("User Status Changed Successfully"));
		Assert.assertTrue(adminuserspage.checkWhetherAlertBoxIsVisible());
		adminuserspage.clickOnResetButton();
		Thread.sleep(3000);
		Assert.assertFalse(adminuserspage.checkWhetherAlertBoxIsVisible());
	}

}
