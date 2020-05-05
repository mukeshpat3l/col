package com.automation.signIn;

import java.util.List;

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

public class SigninVerificationPage extends AbstractPage {

	public SigninVerificationPage(WebDriver driver, SetGet setget) {
		super(driver,setget);
		// TODO Auto-generated constructor stub
	}
	
	
	WebDriverWait wait = new WebDriverWait(driver, 55);
	
	
	public boolean verifyHomePage(){
		
		if(setget.getEnvName().equals("dallas")){
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'LOGIN')]")));
			
			return  driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]")).isDisplayed() &&
					driver.findElement(By.xpath("//li/a[contains(text(),'JOIN')]")).isDisplayed();
		}
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Log In')]/i")));
		
		return  driver.findElement(By.xpath("//a[contains(text(),'Log In')]/i")).isDisplayed() &&
				driver.findElement(By.xpath("//li/a[contains(text(),'Join Now')]")).isDisplayed();
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

	public boolean verifySignInForm() {
		
		if(setget.getEnvName().equals("dallas")){
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'LOGIN')]")));
			Common.pause(2);
			return usernameTxt_dc_el.isDisplayed() && passwordTxt_dc_el.isDisplayed();
		}else {	
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Log In')]/i")));
			Common.pause(2);
			return usernameTxtField.isDisplayed() && pswdTxtField.isDisplayed();
		}
	}
	
	public boolean verifyRedirectedToMyProfile(String URL) {

		String actualURL = driver.getCurrentUrl();
		String expectedURL = URL.concat("/my-profile");
		
		log("Actual URL after SignIn: "+actualURL);
		return actualURL.equals(expectedURL);
		
	}
	
	
	public boolean verifyRedirectedToOrgProgAdmin(String URL) {

		String actualURL = driver.getCurrentUrl();
		String expectedURL = URL.concat("/super-admin");
		log("Actual URL after LogIn: "+actualURL);
	
		return actualURL.equals(expectedURL) && 
				driver.findElement(By.xpath("//li/a[contains(text(),'Organizations and Programs')]")).isDisplayed();
		
	}
	
	
	@FindBy(xpath="//li[@class='profile-menu-item']//span")
	WebElement usernameAfterSignin;
	public boolean verifyHomeAfterSignIn(String username) {
		
		if(setget.getEnvName().equals("dallas")) {	
			Common.pause(2);
			
			if(setget.getUrlFromSuite().equals("https://dallascityoflearning.org")) {
				try {
				driver.findElement(By.xpath("//div[@id='getSetModal']/a[@class='close-reveal-modal']")).click();
				log("Closed 'Get Set Modal'...");
				}catch(Exception e) {
					log("Not Found 'Get Set Modal'...:: "+e);
				}
			}
			
		}else {
			
		}
		
		Common.pause(2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='profile-menu-item']//span")));
		Common.pause(1);
		String actualUsername = usernameAfterSignin.getText().trim();
		log("Actual Username after SignIn: <b>"+actualUsername+"</b>");
		return (actualUsername.toLowerCase()).equals(username);
				
	}
	
	
	/*
	 * For Admin Login 
	 * successful Verification
	*/
	@FindBy(xpath="//li/span[@class='admin_user_name']")
	WebElement adminUsernameAfterLogin;
	
	public boolean verifyAdminLoginSuccessful(String Email) {
		Common.pause(1);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Log out')]")));
		
		String actualUsername = adminUsernameAfterLogin.getText().trim();
		log("Actual Admin Username after LogIn: <b>"+actualUsername+"</b>");
		return adminUsernameAfterLogin.isDisplayed();
		
	}
	
	
	@FindBy(xpath="//ul[@id='profile-menu']/li/a")
	List<WebElement> profileOptions;
	public boolean verifyProfileOptions() {
		Common.pause(1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='profile-menu']/li/a")));
		
		int totalOption = profileOptions.size();
		log("Total Options in My Profile:" +totalOption);
		log("- "+driver.findElement(By.xpath("//ul[@id='profile-menu']/li/a[contains(text(),'My Profile')]")).getText());
		log("- "+driver.findElement(By.xpath("//ul[@id='profile-menu']/li/a[contains(text(),'View Account')]")).getText());
		log("- "+driver.findElement(By.xpath("//ul[@id='profile-menu']/li/a[contains(text(),'Sign Out')]")).getText());
		 
		return 	totalOption == 3;
	}
	
	@FindBy(xpath="//h2[@id='modalTitle']")
	WebElement popup;
	
	@FindBy(xpath="//div/a[@class='set_shared_resource_true button']")
	WebElement trueEyeBtn;
	
	@FindBy(xpath="//div/a[@class='set_shared_resource_false button']")
	WebElement falseLockBtn;
	
	@FindBy(xpath="//div/a[@class='close-reveal-modal']")
	WebElement closePopup;
	
	public boolean verifyPopupafterSignout() {
		Common.pause(2);
		
		if(setget.getEnvName().equals("san_antonio")){
			
			if(setget.getUrlFromSuite().equals("https://futurereadysa.org")) {
				log("Popup for Shared Deviced SignOut is not displayed for this Instance.");
			
			return true;
			}else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='modalTitle']")));
				log("Popup is displayed successfully.");
			
				if(popup.isDisplayed() && trueEyeBtn.isDisplayed() && falseLockBtn.isDisplayed()) {
					Common.pause(1);
					Common.clickOn(driver, closePopup);
					return true;
				}else {
					return false;
				}
			}
		}
		else if(setget.getEnvName().equals("qagold")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='modalTitle']")));
			log("Popup is displayed successfully.");
		
			if(popup.isDisplayed() && trueEyeBtn.isDisplayed() && falseLockBtn.isDisplayed()) {
				Common.pause(1);
				Common.clickOn(driver, closePopup);
				return true;
			}else {
				return false;
			}
		}
		else if(setget.getEnvName().equals("steamville")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='modalTitle']")));
			log("Popup is displayed successfully.");
		
			if(popup.isDisplayed() && trueEyeBtn.isDisplayed() && falseLockBtn.isDisplayed()) {
				Common.pause(1);
				Common.clickOn(driver, closePopup);
				return true;
			}else {
				return false;
			}
		}
		else if(setget.getEnvName().equals("evanston")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='modalTitle']")));
			log("Popup is displayed successfully.");
		
			if(popup.isDisplayed() && trueEyeBtn.isDisplayed() && falseLockBtn.isDisplayed()) {
				Common.pause(1);
				Common.clickOn(driver, closePopup);
				return true;
			}else {
				return false;
			}
		}
		else if(setget.getEnvName().equals("mcmf")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='modalTitle']")));
			log("Popup is displayed successfully.");
		
			if(popup.isDisplayed() && trueEyeBtn.isDisplayed() && falseLockBtn.isDisplayed()) {
				Common.pause(1);
				Common.clickOn(driver, closePopup);
				return true;
			}else {
				return false;
			}
		}
		else if(setget.getEnvName().equals("dallas")){
			/*
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
			 * "//h2[@id='modalTitle']"))); log("Popup is displayed successfully.");
			 * 
			 * if(popup.isDisplayed() && trueEyeBtn.isDisplayed() &&
			 * falseLockBtn.isDisplayed()) { Common.pause(1); Common.clickOn(driver,
			 * closePopup); return true; }else { return false; }
			 */
			log("Popup for Shared Deviced SignOut is not displayed for this Instance.");
			return true;
		}
		else {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='modalTitle']")));
			log("Popup is displayed successfully.");
		
			if(popup.isDisplayed() && trueEyeBtn.isDisplayed() && falseLockBtn.isDisplayed()) {
				Common.pause(1);
				Common.clickOn(driver, closePopup);
				return true;
			}else {
				return false;
			}
		}
	}
	
	
	@FindBy(xpath="//input[@id='dsignin_username']")
	WebElement usernameTxtFieldAfterSignOut;
	@FindBy(xpath="//input[@id='dsignin_password']")
	WebElement pswdTxtFieldAfterSignOut;

	@FindBy(xpath="//input[@id='first_name']")
	WebElement firstNameTxtSignUp;
	@FindBy(xpath="//input[@id='last_name']")
	WebElement lastNameTxtSignUp;
	
	
	public boolean verifySignInFormAfterSignOut() {
		Common.pause(1);
		
		if(setget.getEnvName().equals("dallas")){
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='first_name']")));
			Common.pause(3);
			log("Current URL After Sign Out:  <b>"+driver.getCurrentUrl()+"</b>");
			return firstNameTxtSignUp.isDisplayed() &&
					lastNameTxtSignUp.isDisplayed();
		}
		else if(setget.getEnvName().equals("san_antonio")) {
				
			if(setget.getUrlFromSuite().equals("https://futurereadysa.org")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='first_name']")));
				Common.pause(3);
				log("Current URL After Sign Out:  <b>"+driver.getCurrentUrl()+"</b>");
				return firstNameTxtSignUp.isDisplayed() &&
						lastNameTxtSignUp.isDisplayed();
			}else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='dsignin_username']")));
				Common.pause(3);
				log("Current URL After Sign Out:  <b>"+driver.getCurrentUrl()+"</b>");
				return usernameTxtFieldAfterSignOut.isDisplayed() && pswdTxtFieldAfterSignOut.isDisplayed();
			}
			
		} else{	
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='dsignin_username']")));
			Common.pause(3);
			log("Current URL After Sign Out:  <b>"+driver.getCurrentUrl()+"</b>");
			return usernameTxtFieldAfterSignOut.isDisplayed() && pswdTxtFieldAfterSignOut.isDisplayed();
		}
	}
	
	public boolean verifyURLHitAfterSignOut() {
		Common.pause(1);
		String url = setget.getUrlFromSuite()+"/my-profile";
		driver.navigate().to(url);
		Common.pause(5);
		log("Trying to Open URL: <b>"+url+"</b>");
		
		String actual =	driver.getCurrentUrl();
		String expected = setget.getUrlFromSuite()+"/sign-in";
		
		log("<b> System Redirects User to :: "+actual+"- As User is already SignOut. </b>");
		
		return actual.equals(expected);
	}
	
	
	
	/*
	 * 
	 *   Admin Login SignIn
	 *   Verification
	 * 	
	*/
	
	@FindBy(xpath="//input[@name='email']")
	WebElement emailTxtfieldAdmin;
	@FindBy(xpath="//input[@name='password']")
	WebElement pswdTxtFieldAdmin;
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginAdmin;
	
	public boolean verifyLoginformAdmin() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']")));
		log("<b> "+driver.findElement(By.xpath("//div/h4")).getText().trim()+" </b>");
		return  emailTxtfieldAdmin.isDisplayed() &&
				pswdTxtFieldAdmin.isDisplayed();
	} 
	
	
	public boolean verifyURLHitAfterLogOutAdmin() {
		Common.pause(1);
		String url = setget.getUrlFromSuite()+"/super-admin";
		driver.navigate().to(url);
		Common.pause(5);
		log("Directly Trying to Open URL: <b>"+url+"</b>");
		
		String actual =	driver.getCurrentUrl();
		
		String urlSplit[] = actual.split("errors"); 
		
		String expected = setget.getUrlFromSuite()+"/login?";
		
		log("<b> System Redirects User to :: <b>"+actual+"</b>- As User is already LogOut. </b>");
		
		return urlSplit[0].equals(expected);
	}
	
}