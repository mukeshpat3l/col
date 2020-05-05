package com.automation.signUp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.init.Common;
import com.init.SeleniumInit;

public class SignUpIndex extends SeleniumInit{

	@Test(priority=1)
	public void signUp() throws UnsupportedEncodingException, URISyntaxException, IOException {

		int numOfFailure = 0;
		int step=1;
		int exp=1;
		testcaseId("TC_SignUp_01");

		testDescription(testName+" - ["+setget.getUrlFromSuite()+"]");

		testSubDescription(" To verify SignUp Functionality (Create New Account Successfully).");

		log("Step "+step++ +" : Open url: <a>" + testUrl +"</a>");

		Common.pause(4);
		System.out.println("-->   "+driver.getCurrentUrl()+"  < --- ");
		logverification(exp++,"To verify user redirects to the Home page and can see the all contents on the page with 'JOIN NOW' and 'SignIn' button.");
		if (signinVerificationPage.verifyHomePage()) {
			logStatus("pass");
		} else {
			logStatus("fail");
			numOfFailure++;
		}

		log("Step "+step++ +" : 'SignUp' (Create New Account) with valid Credentials");
		Common.pause(1);
		
		log("Step "+step++ +" : Open 'SignUp' form.");
		signupIndexPage.clickJoinNowBtn();

		logverification(exp++,"To verify user gets SignUp form with 'First Name', 'Last Name' and 'Birthday'.");
		if (signupVerificationPage.verifySignUpForm()) {
			logStatus("pass");
		} else {
			logStatus("fail");
			numOfFailure++;
		}

		
		String currentUrlFormProperty = setget.getUrlFromSuite();

		String firstName = signupIndexPage.generateFirstNameRandom();
		String lastName = signupIndexPage.generateLastNameRandom();
		String month = signupIndexPage.month();
		String date = signupIndexPage.date();
		String year = signupIndexPage.year();

		log("Environment:  <b>"+currentUrlFormProperty+"</b>");
		log("Step "+step++ +" : Enter valid 'SignUp' details.");
		signupIndexPage.enterSignUpDetails(firstName, lastName, month, date, year);

		logverification(exp++,"To verify user should be redirected to Select School page.");
		if (signupVerificationPage.verifySelectSchoolPage()) {
			logStatus("pass");
		} else {
			logStatus("fail");
			numOfFailure++;
		}

		log("Step "+step++ +" : Select School for this user. (If School selection feature is enable.)");
		String schoolName = signupIndexPage.school();
		signupIndexPage.selectSchool(schoolName);

		logverification(exp++,"To verify user should be redirected to enter email, username and password page.");
		if (signupVerificationPage.verifyEnterUsernameAndPasswordPage()) {
			logStatus("pass");
		} else {
			logStatus("fail");
			numOfFailure++;
		}

		String email = signupIndexPage.generateRandomEmail();
		String userName = signupIndexPage.generateRandomUsername();
		String password = signupIndexPage.statPassword();

		log("Step "+step++ +" : Enter valid Credentials (Email, Username, Password)");
		signupIndexPage.enterSignUpDetails(email,userName,password);

		signupIndexPage.clickLetStartBtn();
		signupIndexPage.closePopUp();

		logverification(exp++,"To verify user is redirected to 'My Profile' page and verify 'username' is same as entered.");
		if (signupVerificationPage.verifyHomeAfterSignUp(userName)) {
			logStatus("pass");
		} else {
			log("Expected Username: <b>"+userName+"</b>");
			logStatus("fail");
			numOfFailure++;
		}
		
		logverification(exp++,"To verify entered FirstName ,Last name and Email should be same as entered during SignUp");
		signupIndexPage.goToViewAccount();
		if (signupVerificationPage.verifyDetailsInViewAccount(firstName, lastName, email)) {
			logStatus("pass");
		} else {
			log("Expected firstname: <b>"+userName+"</b>");
			log("Expected lastname: <b>"+lastName+"</b>");
			log("Expected email: <b>"+email+"</b>");
			logStatus("fail");
			numOfFailure++;
		}
		
		log("Step "+step++ +"Open Edit My Info for more verification");
		signupIndexPage.clickEditMyInfo();
		
		logverification(exp++,"To verify entered DOB should be same as entered during SignUp");
		if (signupVerificationPage.verifyDOB(month, date, year)) {
			logStatus("pass");
		} else {
			logStatus("fail");
			numOfFailure++;
		}

		if (numOfFailure > 0) {
			Assert.assertTrue(false);
		}
	}
}