package com.sevenrmartsupermarket.constants;

public class Constants {

	/** implicitily wait timeout **/

	public static final long IMPLICIT_WAIT = 10;
	public static final String CONFIG_FILE_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\config.properties"; // will get the path upto the project
	public static String EXCEL_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\ExcelFiles\\";
	public static final String SCREENSHOT_FILE_PATH = System.getProperty("user.dir") + "\\screenshots\\";
	public static final String EXTENT_REPORT_PATH = System.getProperty("user.dir") + "\\ExtentReports";
	public static final String IMAGE_FILE_PATH = System.getProperty("user.dir") + "\\Image\\logo.png";

	/** DashBoard Page Cards **/

	public static final String ADMIN_USERS_CARD = "Admin Users";
	public static final String MANAGE_PAGES_CARD = "Manage Pages";

	/** LoginCases Expected Results **/

	public static final String INVALID_LOGIN_MSG = "Invalid Username/Password";

	/** AdminUsersCases Expected Results **/

	public static final String ADMINUSERS_PAGE_TITLE = "Admin Users";
	public static final String USER_CREATION_SUCCS_MSG = "User Created Successfully";
	public static final String USER_DELETION_SUCCS_MSG = "User Deleted Successfully";
	public static final String UNAME_ALRDY_EXST_MSG = "Username already exists.";
	public static final String USER_STATCHANGE_SUCCS_MSG = "User Status Changed Successfully";
	public static final String USER_UPDATION_SUCCS_MSG = "User Updated Successfully";
	public static final String NO_RESULT_FND_MSG = ".........RESULT NOT FOUND.......";

	/** ManagePagesCases Expected Results **/

	public static final String MANAGEPAGES_PAGE_TITLE = "List Pages";
	public static final String PAGE_CREATION_SUCCS_MSG = "Page Created Successfully";
//	public static final String USER_DELETION_SUCCS_MSG = "User Deleted Successfully";

}
