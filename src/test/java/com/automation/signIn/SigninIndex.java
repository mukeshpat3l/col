package com.automation.signIn;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.init.Common;
import com.init.SeleniumInit;
import com.init.TestData;

public class SigninIndex extends SeleniumInit {
	
	
	@Test(priority = 1)
	public void signIn() throws UnsupportedEncodingException, URISyntaxException, IOException {
		int numOfFailure = 0;
		int step=1;
		int exp=1;
		testcaseId("TC_SignIn_01 ");
		
		testDescription(testName+" - ["+setget.getUrlFromSuite()+"]");
		
		testSubDescription(" To verify Sign In Functionality.");
		
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
		
		log("Step "+step++ +" : 'SignIn' with valid Credentials");
		Common.pause(1);
		signinIndexPage.clickLoginBtn();
		
		logverification(exp++,"To verify user gets SignIn form for 'username' and 'password' .");
		
		if (signinVerificationPage.verifySignInForm()) {
			logStatus("pass");
		} else {
			logStatus("fail");
			numOfFailure++;
		}
		
		String currentUrlFormProperty = setget.getUrlFromSuite();
		String username;
		String password;
		
		log("Environment:  <b>"+currentUrlFormProperty+"</b>");
		username = setget.getUsernameFromSuite();
		password = setget.getPasswordFromSuite();
		signinIndexPage.enterSignInDetails(username,password);	
		
		logverification(exp++,"To verify user should SignIn successfully and verify user Profile name should be same as username.");
		username = setget.getUsernameFromSuite();
		if (signinVerificationPage.verifyHomeAfterSignIn(username)) {
			logStatus("pass");
			} else {
			log("Expected Username: "+username);
			logStatus("fail");
			numOfFailure++;
			}

		
		logverification(exp++,"To verify user is redirected to 'My Profile' page");
		if (signinVerificationPage.verifyRedirectedToMyProfile(currentUrlFormProperty)) {
			logStatus("pass");
			} else {
			log("Expected URL: "+currentUrlFormProperty+"/my-profile");
			logStatus("fail");
			numOfFailure++;
			}
		
		
		log("Step "+step++ +" : Open Profile-Menu options. ");
		signinIndexPage.clickonUsernameProfile();
		
		logverification(exp++,"To verify Username menu options should be displayed: 'MY PROFILE' - 'VIEW ACCOUNT' - 'SIGN OUT'");
		if (signinVerificationPage.verifyProfileOptions()) {
			logStatus("pass");
			} else {
			logStatus("fail");
			numOfFailure++;
		}
		
		
		// Sign Out
		
		testcaseId("TC_SignOut_01 ");
		
		testSubDescription(" To verify Sign Out Functionality.");
		
		log("Step "+step++ +" : Sign Out from current signin account.");
		signinIndexPage.clickonSignOutOption();
		
		logverification(exp++,"To verify user should get the 'Is this shared device' PopUp model.");
		if (signinVerificationPage.verifyPopupafterSignout()) {
			logStatus("pass");
			} else {
			logStatus("fail");
			numOfFailure++;
		}
		
		/*
		 * log("Step "+step++ +" : Close the 'Shared Device' confirmation PopUp..");
		 * signinIndexPage.closeSharedDevicePopup();
		 */
		
		logverification(exp++,"To verify user gets SignIn form for 'username' and 'password' after SignOut completely.");
		if (signinVerificationPage.verifySignInFormAfterSignOut()) {
			logStatus("pass");
		} else {
			logStatus("fail");
			numOfFailure++;
		}
		
		logverification(exp++,"To verify after SignOut user cannot go back to Profile page (via url hit).");
		if (signinVerificationPage.verifyURLHitAfterSignOut()) {
			logStatus("pass");
		} else {
			log("Expected : "+setget.getUrlFromSuite()+"/sign-in");
			logStatus("fail");
			numOfFailure++;
		}
		
		
		if (numOfFailure > 0) {
			Assert.assertTrue(false);
		}
		
	}
	
	
	@Test(priority=2)
	public void logInAdmin() throws UnsupportedEncodingException, URISyntaxException, IOException {
		int numOfFailure = 0;
		int step=1;
		int exp=1;
		testcaseId("TC_SignIn_02 ");
		
		testDescription(testName+" - ["+setget.getUrlFromSuite()+"]");
		
		testSubDescription(" To verify Sign In Functionality.");
		
		log("Step "+step++ +" : Open url: <a>" + testUrl +"</a>");
		
		Common.pause(4);
		System.out.println("-->   "+driver.getCurrentUrl()+"  < --- ");
		logverification(exp++,"To verify user redirects to Admin Login page and can see Login Form for - "+setget.getEnvName()+" Instance.");
		if (signinVerificationPage.verifyLoginformAdmin()) {
			logStatus("pass");
		} else {
			logStatus("fail");
			numOfFailure++;
		}
		
		log("Step "+step++ +" : 'Login' with valid Credentials");
		Common.pause(1);
		
		String currentUrlFormXML = setget.getUrlFromSuite();
		String emailAdmin;
		String password;
		
		log("Environment:  <b>"+currentUrlFormXML+"</b>");
		emailAdmin = setget.getUsernameFromSuite();
		password = setget.getPasswordFromSuite();
		signinIndexPage.enterLogInDetailsAdmin(emailAdmin,password);	
		
		logverification(exp++,"To verify user should LogIn successfully and verify Admin username should be same as entered email.");
		emailAdmin = setget.getUsernameFromSuite();
		if (signinVerificationPage.verifyAdminLoginSuccessful(emailAdmin)) {
			logStatus("pass");
			} else {
			log("Expected Username: "+emailAdmin);
			logStatus("fail");
			numOfFailure++;
			}

		
		logverification(exp++,"To verify user is redirected to 'Organizations and Programs' page");
		if (signinVerificationPage.verifyRedirectedToOrgProgAdmin(currentUrlFormXML)) {
			logStatus("pass");
			} else {
			log("Expected URL: "+currentUrlFormXML+"/super-admin");
			logStatus("fail");
			numOfFailure++;
			}
		
		
		// Log Out from Amdin
		
		testcaseId("TC_SignOut_02 ");
		
		testSubDescription(" To verify Sign Out Functionality.");
		
		log("Step "+step++ +" : Log Out from current Login account.");
		signinIndexPage.clickonLogOutAdmin();
		
		logverification(exp++,"To verify user gets LogIn form for 'Email' and 'Password' after LogOut Successfully.");
		if (signinVerificationPage.verifyLoginformAdmin()) {
			logStatus("pass");
		} else {
			logStatus("fail");
			numOfFailure++;
		}
		
		logverification(exp++,"To verify after LogOut user cannot go back to Admin Dasboard page.");
		if (signinVerificationPage.verifyURLHitAfterLogOutAdmin()) {
			logStatus("pass");
		} else {
			log("Expected : "+setget.getUrlFromSuite()+"/login?errors");
			logStatus("fail");
			numOfFailure++;
		}
		
		
		if (numOfFailure > 0) {
			Assert.assertTrue(false);
		}
		
	}
	
	
	@Test
	public void signOut() throws UnsupportedEncodingException, URISyntaxException, IOException {
	
		int numOfFailure = 0;
		int step=1;
		int exp=1;
		testcaseId("TC_SU_02 ");
		
		testDescription(" To verify SignOut functionality.");
		log("Step "+step++ +" : Open url: <a>" + testUrl +"</a>");
		
		log("Step "+step++ +" : 'SignIn' with valid Credentials");
		Common.pause(1);
		signinIndexPage.clickLoginBtn();
		
		if(TestData.getProperties("TestUrlFromSuite").equals("https://el-3.org")) {
			driver.findElement(By.xpath("//a[text()='Sign in']")).click();
			
			Common.pause(2);
			signinIndexPage.clickonYesBtnDuringSignIn();
		}
		
		String currentUrlFormProperty = TestData.getProperties("TestUrlFromSuite");
		String username;
		String password;
		
		
		log("Environment:  <b>"+currentUrlFormProperty+"</b>");
		username = TestData.getProperties("usernameFromSuite");
		password = TestData.getProperties("passwordFromSuite");
		signinIndexPage.enterSignInDetails(username,password);
		
		
		logverification(exp++,"To verify user should SignIn successfully and verify user Profile name should be same as username.");
		username = TestData.getProperties("usernameFromSuite");
		if (signinVerificationPage.verifyHomeAfterSignIn(username)) {
			logStatus("pass");
			} else {
			log("Expected Username: "+username);
			logStatus("fail");
			numOfFailure++;
			}
		
		log("Step "+step++ +" : Open Profile-Menu options. ");
		signinIndexPage.clickonUsernameProfile();
		
		log("Step "+step++ +" : Sign Out from current signin account.");
		signinIndexPage.clickonSignOutOption();
		
		logverification(exp++,"To verify user should get the 'Is this shared device' PopUp model.");
		if (signinVerificationPage.verifyPopupafterSignout()) {
			logStatus("pass");
			} else {
			logStatus("fail");
			numOfFailure++;
		}
		
		log("Step "+step++ +" : Proceed with -  - Other people use this device. Please sign me out of Google when I click Sign Out.");
		signinIndexPage.clickonYesBtnAfterSignOut();
		
		logverification(exp++,"To verify user gets SignIn form for 'username' and 'password' after SignOut completely.");
		if (signinVerificationPage.verifySignInForm()) {
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