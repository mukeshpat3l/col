package com.automation.signUp;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.init.AbstractPage;
import com.init.Common;
import com.init.SetGet;

public class SignUpIndexPage extends AbstractPage{

	public SignUpIndexPage(WebDriver driver, SetGet setget) {
		super(driver,setget);
		// TODO Auto-generated constructor stub
	}

	WebDriverWait wait = new WebDriverWait(driver, 25);

	@FindBy(xpath="//a[text()='Join Now']")
	WebElement joinNowBtn;

	/* Join button xpath
	 * For Dallas Env
	*/
	@FindBy(xpath="//a[text()='JOIN']")
	WebElement joinBtnDallas;

	public SignUpVerificationPage clickJoinNowBtn() {

		if(setget.getEnvName().equals("dallas")){
			Common.pause(5);
			Common.clickOn(driver, joinBtnDallas);
			log("Clicked on 'Join' button.");
			Common.pause(3);

			return new SignUpVerificationPage(driver, setget);
		}else {
			Common.pause(5);
			Common.clickOn(driver, joinNowBtn);
			log("Clicked on 'Join Now' button.");
			Common.pause(2);

			return new SignUpVerificationPage(driver,setget);
		}
	}

	@FindBy(xpath="//input[@id='first_name']")
	WebElement firstNameTxtField;
	@FindBy(xpath="//input[@name='last_name']")
	WebElement lastNameTxtField;
	@FindBy(xpath="//select[@id='birthmonth']")
	WebElement monthDrpDown;
	@FindBy(xpath="//select[@id='birthdate']")
	WebElement dateDrpDown;
	@FindBy(xpath="//select[@id='birthyear']")
	WebElement yearDrpDown;
	@FindBy(xpath="//a[@id='btn_step1']")
	WebElement signUpBtn;

	public String generateFirstNameRandom() {
		String fname = RandomStringUtils.randomAlphabetic(5);
		return fname+"_automation";
	}

	public String generateLastNameRandom() {
		String lname = RandomStringUtils.randomAlphabetic(5);
		return lname+"_automation";
	}
	
	public String month() {
		return "January";
	}
	
	public String date() {
		return "28";
	}
	
	public String year() {
		return "2005";
	}

	public void enterFirstName(String firstName) {
		firstNameTxtField.sendKeys(firstName);
		log("Entered 'First Name' :-<b>"+firstName+"</b>");
		Common.pause(1);
	}

	public void enterLastName(String lastName) {
		lastNameTxtField.sendKeys(lastName);
		log("Entered 'Last Name' :-<b>"+lastName+"</b>");
		Common.pause(1);	
	}

	public void selectBirthMonth(String month) {
		Common.selectFromComboByVisibleElement(monthDrpDown,month);
		log("Selected 'Month' :-<b>"+month+"</b>");
		Common.pause(1);
	}

	public void selectBirthDate(String date) {
		Common.selectFromComboByVisibleElement(dateDrpDown,date);
		log("Selected 'Date' :-<b>"+date+"</b>");
		Common.pause(1);
	}

	public void selectBirthYear(String year) {
		Common.selectFromComboByVisibleElement(yearDrpDown,year);
		log("Selected 'Year' :-<b>"+year+"</b>");
		Common.pause(1);
	}

	public SignUpVerificationPage clickSignUpBtn() {
		Common.pause(1);
		Common.clickOn(driver, signUpBtn);
		log("Clicked on 'Continue to Sign Up'.");
		Common.pause(1);
		return new SignUpVerificationPage(driver,setget);
	}

	public SignUpVerificationPage enterSignUpDetails(String firstname, String lastname, String month, String date, String year) {
		Common.pause(2);
		enterFirstName(firstname);
		enterLastName(lastname);
		selectBirthMonth(month);
		selectBirthDate(date);
		selectBirthYear(year);
		clickSignUpBtn();
		Common.pause(5);
		return new SignUpVerificationPage(driver,setget);	
	}

	@FindBy(xpath="//span[@id='select2-chosen-4']")
	WebElement selectSchool;

	@FindBy(xpath="//input[@id='s2id_autogen4_search']")
	WebElement searchSchool;

	@FindBy(xpath="//a[@id='btn_step_3']")
	WebElement almostThere3Btn;

	public String school() {
		if(setget.getEnvName().equals("dallas"))
			return "SUNSET HIGH SCHOOL, DALLAS ISD";
		else if(setget.getEnvName().equals("san_antonio"))
			return "School selection feature is disabled.";
		else
			return "School";
	}
	
	public SignUpIndexPage searchSchool(String schoolName) {
		
		if(setget.getEnvName().equals("san_antonio")) {
			try {
				Common.clickOn(driver, selectSchool);
				Common.pause(1);
				searchSchool.sendKeys(schoolName);
				Common.pause(1);
				String xpath = "//span[text()='"+schoolName+"']";
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				driver.findElement(By.xpath(xpath)).click();
				log("Selected school: "+schoolName);
				Common.pause(1);
			}catch(Exception e){
				log("Feature not enabled.");
			}
		}
		
		else {
			Common.clickOn(driver, selectSchool);
			Common.pause(1);
			searchSchool.sendKeys(schoolName);
			Common.pause(1);
			String xpath = "//span[text()='"+schoolName+"']";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			driver.findElement(By.xpath(xpath)).click();
			log("Selected school: "+schoolName);
			Common.pause(1);
		}
		return new SignUpIndexPage(driver, setget);
	}

	public SignUpVerificationPage clickAlmostThereBtn() {
		Common.pause(1);
		Common.clickOn(driver, almostThere3Btn);
		Common.pause(1);
		log("Clicked on 'You're almost there!'.");

		return new SignUpVerificationPage(driver,setget);
	}

	public SignUpVerificationPage selectSchool(String schoolName) {
		if(setget.getEnvName().equals("san_antonio"))
			log("School selection feature is disabled. Redirecting to enter email, username and password page.");
		else{
			searchSchool(schoolName);
			Common.pause(2);
			clickAlmostThereBtn();
			Common.pause(1);
		}

		return new SignUpVerificationPage(driver,setget);
	}

	Random ran = new Random();
	int num = ran.nextInt(99999);

	@FindBy(xpath="//input[@id='email_address']")
	WebElement emailTxtField;
	@FindBy(xpath="//input[@id='username']")
	WebElement usernameTxtField;
	@FindBy(xpath="//input[@id='password']")
	WebElement passwordTxtField;
	@FindBy(xpath="//a[@id='create_account']")
	WebElement createAccountBtn;

	public String generateRandomEmail() {		
		return "tarpan.patel+"+num+setget.getEnvName()+"@kiwiqa.com";
	}

	public String generateRandomUsername() {
		return num+setget.getEnvName();
	}

	public String statPassword() {
		return "Kiwi1234auto";
	}

	public SignUpVerificationPage clickOnCreateAccountBtn() {
		Common.pause(1);
		Common.clickOn(driver, createAccountBtn);
		log("Clicked on 'You're almost there!'.");

		return new SignUpVerificationPage(driver,setget);
	}

	public void enterEmail(String email) {
		emailTxtField.sendKeys(email);
		log("Entered email: "+email);
	}
	
	public void enterUsername(String username) {
		usernameTxtField.sendKeys(username);
		log("Entered username: "+username);
	}
	
	public void enterPassword(String password) {
		passwordTxtField.sendKeys(password);
		log("Entered password: "+password);
	}
	
	public SignUpVerificationPage enterSignUpDetails(String email, String username, String password) {
		enterEmail(email);
		enterUsername(username);
		enterPassword(password);
		Common.clickOn(driver, createAccountBtn);
		Common.pause(1);
		log("Clicked on 'Agree & Create Account'.");

		return new SignUpVerificationPage(driver,setget);
	}

	@FindBy(xpath="//a[text()=\"Let's Start With...\"]")
	WebElement letsStartwithBtn;

	public SignUpVerificationPage clickLetStartBtn() {
		Common.pause(1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"Let's Start With...\"]")));
		Common.pause(1);
		Common.clickOn(driver, letsStartwithBtn);
		log("Clicked on 'Let's Start With...'.");
		return new SignUpVerificationPage(driver,setget);
	}

	public SignUpVerificationPage closePopUp() {
		Common.pause(2);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='interestsModal']/a[@class='close-reveal-modal']")));
			driver.findElement(By.xpath("//div[@id='interestsModal']/a[@class='close-reveal-modal']")).click();
			Common.pause(1);
			log("Closed pop-up.");
			return new SignUpVerificationPage(driver,setget);
		}catch(Exception e) {
			log("Pop-up not found"+e);
			return new SignUpVerificationPage(driver,setget);
		}
	}
	
	@FindBy(xpath="//li[@class='profile-menu-item']//span")
	WebElement usernameAfterSignUp;
	
	@FindBy(xpath="//ul[@id='profile-menu']/li/a[text()='View Account']")
	WebElement viewAccount;
	
	@FindBy(xpath="//a[@id='edit-account-button']")
	WebElement editMyInfoBtn;
	
	public SignUpVerificationPage goToViewAccount() {
		Common.pause(1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='profile-menu-item']//span")));
		Common.clickOn(driver, usernameAfterSignUp);
		Common.clickOn(driver, viewAccount);
		Common.pause(4);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='edit-account-button']")));

		return new SignUpVerificationPage(driver,setget);
	}
	
	public SignUpVerificationPage clickEditMyInfo() {
		Common.pause(1);
		Common.clickOn(driver, editMyInfoBtn);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='update_account_submit']")));
		Common.pause(1);
		
		return new SignUpVerificationPage(driver,setget);
	}
}