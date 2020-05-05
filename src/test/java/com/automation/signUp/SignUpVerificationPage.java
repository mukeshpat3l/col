package com.automation.signUp;

import java.time.Month;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.init.AbstractPage;
import com.init.Common;
import com.init.SetGet;

public class SignUpVerificationPage extends AbstractPage{

	public SignUpVerificationPage(WebDriver driver, SetGet setget) {
		super(driver,setget);
		// TODO Auto-generated constructor stub
	}

	WebDriverWait wait = new WebDriverWait(driver, 55);

	@FindBy(xpath="//input[@id='first_name']")
	WebElement firstNameTxtField;
	@FindBy(xpath="//input[@name='last_name']")
	WebElement lastNameTxtField;
	@FindBy(xpath="//div[@id='s2id_birthmonth']")
	WebElement monthDrpDown;
	@FindBy(xpath="//div[@id='s2id_birthdate']")
	WebElement dateDrpDown;
	@FindBy(xpath="//div[@id='s2id_birthyear']")
	WebElement yearDrpDown;

	public boolean verifySignUpForm() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='first_name']")));
		Common.pause(2);
		return firstNameTxtField.isDisplayed() && lastNameTxtField.isDisplayed() && monthDrpDown.isDisplayed()
				&& dateDrpDown.isDisplayed() && yearDrpDown.isDisplayed();
	}

	@FindBy(xpath="//a[@id='go_back_2']")
	WebElement backBtn;
	@FindBy(xpath="//a[@id='btn_step_3']")
	WebElement almostThereBtn;

	public boolean verifySelectSchoolPage() {
		if(setget.getEnvName().equals("san_antonio")) {
			log("School selection feature is disabled.");
			return true;
			
		}
		else {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='btn_step_3']")));
			Common.pause(2);
			return backBtn.isDisplayed() && almostThereBtn.isDisplayed();
		}
	}

	@FindBy(xpath="//a[@id='go_back_3']")
	WebElement backBtn2;
	@FindBy(xpath="//a[@id='create_account']")
	WebElement createAccountBtn;
	
	@FindBy(xpath="//section[@class='step active']//a[@id='go_back_2']")
	WebElement evanstonBackBtn2;
	@FindBy(xpath="//section[@class='step active']//a[@id='go_back_2']")
	WebElement dallasBackBtn2;	

	public boolean verifyEnterUsernameAndPasswordPage() {
		if(setget.getEnvName().equals("dallas")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='create_account']")));
			Common.pause(1);
			return dallasBackBtn2.isDisplayed() && createAccountBtn.isDisplayed();
		}
		else if(setget.getEnvName().equals("evanston")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='create_account']")));
			Common.pause(1);
			return evanstonBackBtn2.isDisplayed() && createAccountBtn.isDisplayed();
		}
		else {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='create_account']")));
			Common.pause(1);
			return backBtn2.isDisplayed() && createAccountBtn.isDisplayed();
		}
	}

	@FindBy(xpath="//li[@class='profile-menu-item']//span")
	WebElement usernameAfterSignUp;

	public boolean verifyHomeAfterSignUp(String username){		
		String title = driver.getTitle();
		log("Actual Page Title after SignUp: <b>"+title+"</b>");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='profile-menu-item']//span")));
		Common.pause(1);
		String actualUsername = usernameAfterSignUp.getText().trim(); 
		log("Actual Username after SignUp: <b>"+actualUsername+"</b>");
		return title.equals("My Profile") && (actualUsername.toLowerCase()).equals(username);		
	}
	
	@FindBy(xpath="//label[@for='full_name']/strong")
	WebElement fullName;
	
	@FindBy(xpath="//label[@for='email_address']/strong")
	WebElement emailAddress;
	
	public boolean verifyDetailsInViewAccount(String firstName, String lastName, String email) {
		String expectedFname = firstName+" "+lastName;
		String actualFname = fullName.getText();
		log("Actual Full Name: <b>"+actualFname+"</b>");
		
		String expectedEmail = emailAddress.getText();	
		log("Actual Email: <b>"+email+"</b>");

		return actualFname.equals(expectedFname) && email.equals(expectedEmail);
	}
	
	@FindBy(xpath="//input[@id='dob']")
	WebElement editDOBTxtField;
	
	public boolean verifyDOB(String monthName, String date, String year) {
		int month = Month.valueOf(monthName.toUpperCase()).getValue();
		if(Integer.toString(month).length()!=2) {
			String actualDOB = editDOBTxtField.getAttribute("value");
			log("Actual DOB: <b>"+actualDOB+"</b>");
			
			return actualDOB.equals("0"+month+"/"+date+"/"+year);
		}else {
			String actualDOB = editDOBTxtField.getAttribute("value");
			log("Actual DOB: <b>"+actualDOB+"</b>");
			
			return actualDOB.equals(month+"/"+date+"/"+year);
		}
		
	}
}