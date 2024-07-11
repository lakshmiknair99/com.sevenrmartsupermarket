package com.sevenrmartsupermarket.tests;

import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.pages.DashBoardPage;
import com.sevenrmartsupermarket.pages.LoginPage;

public class DashBoardTest extends Base {
	
	DashBoardPage dashboardpage;
	LoginPage loginpage;
	@Test
	public void verifyWhetherManagePagesCardIsClickable()
	{
		dashboardpage=new DashBoardPage(driver);
		loginpage=new LoginPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Manage Pages");
	}

}
