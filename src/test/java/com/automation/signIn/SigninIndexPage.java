package com.automation.signIn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.init.AbstractPage;
import com.init.Common;
import com.init.SetGet;
import com.init.TestData;

public class SigninIndexPage extends AbstractPage{

	public SigninIndexPage(WebDriver driver, SetGet setget) {
		super(driver,setget);
		// TODO Auto-generated constructor stub
	}
	
	
	WebDriverWait wait = new WebDriverWait(driver, 25);

	@FindBy(xpath="//a[contains(text(),'Log In')]/i")
	WebElement loginBtn;
	
	/* Login button xpath
	 * For Dallas Env
	*/
	@FindBy(xpath="//a[contains(text(),'LOGIN')]")
	WebElement loginBtnDallas;
	
	public SigninVerificationPage clickLoginBtn() {
		
		if(setget.getEnvName().equals("dallas")){
			Common.pause(5);
			Common.clickOn(driver, loginBtnDallas);
			log("Clicked on 'LogIn' button.");
			Common.pause(3);

			return new SigninVerificationPage(driver,setget);
		}
		
		else if(setget.getEnvName().equals("qagold")) {
			Common.pause(5);
			Common.clickOn(driver, loginBtn);
			log("Clicked on 'LogIn' button.");
			Common.pause(2);
			
			return new SigninVerificationPage(driver,setget);
		}
		else if(setget.getEnvName().equals("steamville")) {
			Common.pause(5);
			Common.clickOn(driver, loginBtn);
			log("Clicked on 'LogIn' button.");
			Common.pause(2);
			
			return new SigninVerificationPage(driver,setget);
		}
		else if(setget.getEnvName().equals("mcmf")) {
			Common.pause(10);
			Common.clickOn(driver, loginBtn);
			log("Clicked on 'LogIn' button.");
			Common.pause(2);
			
			return new SigninVerificationPage(driver,setget);
		}
		else if(setget.getEnvName().equals("san_antonio")) {
			Common.pause(5);
			Common.clickOn(driver, loginBtn);
			log("Clicked on 'LogIn' button.");
			Common.pause(2);
			
			return new SigninVerificationPage(driver,setget);
		}
		else if(setget.getEnvName().equals("evanston")) {
			Common.pause(5);
			Common.clickOn(driver, loginBtn);
			log("Clicked on 'LogIn' button.");
			Common.pause(2);
			
			log("Env is: "+setget.getUrlFromSuite());
			driver.findElement(By.xpath("//a[text()='Sign in']")).click();
			log("Clicked on 'Sign In' link.");
				
			Common.pause(2);
			closeSharedDevicePopup();
			
			return new SigninVerificationPage(driver,setget);
		}
		else {
			Common.pause(5);
			Common.clickOn(driver, loginBtn);
			log("Clicked on 'LogIn' button.");
			Common.pause(2);
			
			return new SigninVerificationPage(driver,setget);
		}

		
	}
	
	@FindBy(xpath="//input[@name='username']")
	WebElement usernameTxtField;
	@FindBy(xpath="//input[@name='password']")
	WebElement pswdTxtField;
	
	
	/* Different Username - Password text field
	 * For -https://dallascityoflearning.org  and -https://el-3.org
	*/
	@FindBy(xpath="//input[@id='dsignin_username']")
	WebElement usernameTxt_dc_el;
	@FindBy(xpath="//input[@id='dsignin_password']")
	WebElement passwordTxt_dc_el;
	
	public void enterUsername(String username) {
		
		if(setget.getEnvName().equals("dallas")){
			usernameTxt_dc_el.sendKeys(username);
			log("Entered 'Username' :-<b>"+username+"</b>");
			Common.pause(1);
			
		}
		else if(setget.getEnvName().equals("evanston")) {
			usernameTxt_dc_el.sendKeys(username);
			log("Entered 'Username' :-<b>"+username+"</b>");
			Common.pause(1);
		}
		else if(setget.getEnvName().equals("qagold")){
			usernameTxtField.sendKeys(username);
			log("Entered 'Username' :-<b>"+username+"</b>");
			Common.pause(1);
		}
		else if(setget.getEnvName().equals("steamville")){
			usernameTxtField.sendKeys(username);
			log("Entered 'Username' :-<b>"+username+"</b>");
			Common.pause(1);
		}
		else if(setget.getEnvName().equals("	")){
			usernameTxtField.sendKeys(username);
			log("Entered 'Username' :-<b>"+username+"</b>");
			Common.pause(1);
		}
		else if(setget.getEnvName().equals("san_antonio")){
			usernameTxtField.sendKeys(username);
			log("Entered 'Username' :-<b>"+username+"</b>");
			Common.pause(1);
		}
		else {
				usernameTxtField.sendKeys(username);
				log("Entered 'Username' :-<b>"+username+"</b>");
				Common.pause(1);
		}
	}
	public void enterPassword(String pswd) {
		
		if(setget.getEnvName().equals("dallas")){
			passwordTxt_dc_el.sendKeys(pswd);
			log("Entered 'Password' :-<b>"+pswd+"</b>");
			Common.pause(1);
		}
		else if(setget.getEnvName().equals("evanston")) {
			passwordTxt_dc_el.sendKeys(pswd);
			log("Entered 'Password' :-<b>"+pswd+"</b>");
			Common.pause(1);
		}
		
		else if(setget.getEnvName().equals("qagold")){
			pswdTxtField.sendKeys(pswd);
			log("Entered 'Password' :-<b>"+pswd+"</b>");
			Common.pause(1);
		}
		else if(setget.getEnvName().equals("https://steamville.org")){
			pswdTxtField.sendKeys(pswd);
			log("Entered 'Password' :-<b>"+pswd+"</b>");
			Common.pause(1);
		}
		else if(setget.getEnvName().equals("steamville")){
			pswdTxtField.sendKeys(pswd);
			log("Entered 'Password' :-<b>"+pswd+"</b>");
			Common.pause(3);
		}
		else if(setget.getEnvName().equals("san_antonio")){
			pswdTxtField.sendKeys(pswd);
			log("Entered 'Password' :-<b>"+pswd+"</b>");
			Common.pause(1);
		}
		else {
			pswdTxtField.sendKeys(pswd);
			log("Entered 'Password' :-<b>"+pswd+"</b>");
			Common.pause(1);
		}
	}
	
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement signinBtn;
	
	public SigninVerificationPage clickSigninBtn() {
		
		if(setget.getEnvName().equals("dallas")){
			WebElement signinBtn = driver.findElement(By.xpath("//input[@value='Log in']"));
			Common.pause(1);
			Common.clickOn(driver, signinBtn);
			log("Clicked on 'Log in' button.");
			return new SigninVerificationPage(driver,setget);
		}
		
		else if(setget.getEnvName().equals("evanston")) {
			Common.pause(1);
			Common.clickOn(driver, signinBtn);
			log("Clicked on 'SignIn' button.");
			return new SigninVerificationPage(driver,setget);
		}
		else if(setget.getEnvName().equals("qagold")){
		
			Common.pause(1);
			Common.clickOn(driver, signinBtn);
			log("Clicked on 'SignIn' button.");
			return new SigninVerificationPage(driver,setget);
		}
		else if(setget.getEnvName().equals("steamville")){
			
			Common.pause(1);
			Common.clickOn(driver, signinBtn);
			log("Clicked on 'SignIn' button.");
			return new SigninVerificationPage(driver,setget);
		}
		else if(setget.getEnvName().equals("mcmf")){
			
			Common.pause(2); 
			Common.clickOn(driver, signinBtn); 
			Common.pause(1);
			log("Clicked on 'SignIn' button.");

			return new SigninVerificationPage(driver,setget);
		}
		else if(setget.getEnvName().equals("san_antonio")){
			
			Common.pause(1);
			Common.clickOn(driver, signinBtn);
			log("Clicked on 'SignIn' button.");
			return new SigninVerificationPage(driver,setget);
		}
		else {
			Common.pause(1);
			Common.clickOn(driver, signinBtn);
			log("Clicked on 'SignIn' button.");
			return new SigninVerificationPage(driver,setget);
		}
	}
	
	
	/* For Sign In
	 * Full Method for Complete Sign In Flow
	 * @param username pswd 
	*/
	
	public SigninVerificationPage enterSignInDetails(String username, String pswd) {
		Common.pause(2);
		enterUsername(username);
		enterPassword(pswd);
		clickSigninBtn();
		Common.pause(10);
		return new SigninVerificationPage(driver,setget);	
	}


	@FindBy(xpath="//li[@class='profile-menu-item']//span")
	WebElement usernameAfterSignin;
	
	public SigninVerificationPage clickonUsernameProfile() {
		Common.pause(3);
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/a[contains(text(),'Enter Code')]")));
		
		Common.clickOn(driver, usernameAfterSignin);
		log("Clicked on 'Profile Menu' icon (From Username).");
		Common.pause(2);
		return new SigninVerificationPage(driver,setget);
	}
	
	@FindBy(xpath="//ul[@id='profile-menu']/li/a[contains(text(),'Sign Out')]")
	WebElement signOutOption;
	
	public SigninVerificationPage clickonSignOutOption() {
		Common.pause(2);
				
		try{
			Common.clickOn(driver, signOutOption);
		}catch(Exception e) {
			clickonUsernameProfile();
			Common.pause(1);
			Common.clickOn(driver, signOutOption);
		}
		log("Clicked on 'Sign Out' option (From Username menu).");
		Common.pause(4);
		return new SigninVerificationPage(driver,setget);
	}
	
	
	
	@FindBy(xpath="//a[contains(text(),'Log out')]")
	WebElement logoutAdmin;
	
	public SigninVerificationPage clickonLogOutAdmin() {
		Common.pause(1);
		Common.clickOn(driver, logoutAdmin);
		log("Clicked on 'Log Out' option (From Header).");
		Common.pause(4);
		return new SigninVerificationPage(driver,setget);
	}
	
	
	@FindBy(xpath="//div/a[@class='set_shared_resource_true button']")
	WebElement trueEyeBtn;
	
	public SigninVerificationPage clickonYesBtnAfterSignOut() {
		
		
		if(setget.getEnvName().equals("san_antonio") || 
				setget.getEnvName().equals("evanston")){
				
			}else {
				Common.clickOn(driver, trueEyeBtn);
				log("Clicked on 'YES' button [Other people use this device. Please sign me out of Google when I click Sign Out] .");
				Common.pause(2);
			}
		return new SigninVerificationPage(driver,setget);
	}
	
	public SigninVerificationPage clickonYesBtnDuringSignIn() {
		
		Common.clickOn(driver, trueEyeBtn);
		log("Clicked on 'YES' button [Other people use this device. Please sign me out of Google when I click Sign Out] .");
		Common.pause(2);
		return new SigninVerificationPage(driver,setget);
	}
	
	
	@FindBy(xpath="//div/a[@class='close-reveal-modal']")
	WebElement closePopup;
	public SigninVerificationPage closeSharedDevicePopup() {
		Common.pause(1);
		Common.clickOn(driver, closePopup);
		log("Clicked on 'Close' icon from Shared Device Popup.");
		return new SigninVerificationPage(driver,setget);
	}
	
	
	
	
	@FindBy(xpath="//input[@name='email']")
	WebElement emailTxtfieldAdmin;
	@FindBy(xpath="//input[@name='password']")
	WebElement pswdTxtFieldAdmin;
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginAdmin;
	
	public void enterEmailAdmin(String email) {		
		emailTxtfieldAdmin.sendKeys(email);
		log("Entered 'Email' :-<b>"+email+"</b>");
		Common.pause(1);
	}
	
	public void enterPasswordAdmin(String pswd) {		
		pswdTxtFieldAdmin.sendKeys(pswd);
		log("Entered 'Password' :-<b>"+pswd+"</b>");
		Common.pause(1);
	}
	
	public SigninVerificationPage clickLoginBtnAdmin() {
		
			Common.pause(1);
			Common.clickOn(driver, loginAdmin);
			log("Clicked on 'LogIn' button.");
			Common.pause(5);

			return new SigninVerificationPage(driver,setget);
		}
	
	public SigninVerificationPage enterLogInDetailsAdmin(String username, String pswd) {
		Common.pause(2);
		enterEmailAdmin(username);
		enterPasswordAdmin(pswd);
		clickLoginBtnAdmin();
		Common.pause(5);
		return new SigninVerificationPage(driver,setget);	
	}
	
	

}	