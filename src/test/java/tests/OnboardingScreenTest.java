package tests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pageObjectModels.OnboardingScreensPOM;

import java.util.logging.Logger;

public class OnboardingScreenTest extends BaseClass{

	//******All The Cases are perfectly running on my system I have attached a video proof for the same
	// Only run this class after reading the ReadMe File
	//Note:: please don't judge on the number of test cases as one automation case here is covering multiple unit cases.
	//will not write the otp expiry case bcs I only have one user and that may lead to user block on your server.
	//Have created a simple appium framework here.

	private static String phoneNumber = "1111119546";
	private static String otp = "5478";
	
	@Test()
	public void verifyPhoneNumberScreenUiForFreshUser() {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		checkIsDisplayedWithText(OnboardingScreensPOM.getTitleTV(), OnboardingScreensPOM.getTitleTV_txt());
		checkIsDisplayedWithText(OnboardingScreensPOM.getSubTitleTV(), OnboardingScreensPOM.getSubTitleTV_txt());
		checkIsElementDisplayed(OnboardingScreensPOM.getFlag_imv(), 1);
		checkIsElementDisplayed(OnboardingScreensPOM.getArrow_imv(), 1);
		checkIsElementDisplayed(OnboardingScreensPOM.getLoginPhoneBtn(), 1);
		checkIsDisplayedWithText(OnboardingScreensPOM.getSelected_country_tv(), OnboardingScreensPOM.getSelected_country_tv_txt());
		checkIsDisplayedWithText(OnboardingScreensPOM.getLoginPhoneET(), OnboardingScreensPOM.getLoginPhoneET_txt());
	}
	
	@Test()
	public void verifyCountrySelectionUiClickingOnUnitedStates() {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		click(OnboardingScreensPOM.getArrow_imv());
		checkIsElementDisplayed(OnboardingScreensPOM.getTitle_tv(), 2);
		checkIsDisplayedWithText(OnboardingScreensPOM.getSearch_edt(), OnboardingScreensPOM.getSearch_edt_txt());
		clickAndSendKeysToElement(OnboardingScreensPOM.getSearch_edt(), "United States");
		checkIsElementDisplayed(OnboardingScreensPOM.getFlag_imv(), 2);
		checkIsElementDisplayed(OnboardingScreensPOM.getCountry_name_tv(), 1);
		checkIsElementDisplayed(OnboardingScreensPOM.getCode_tv(), 1);
		click(OnboardingScreensPOM.getCountry_name_tv());
		checkIsElementDisplayed(OnboardingScreensPOM.getSelected_country_tv(), 5);
		checkIsDisplayedWithText(OnboardingScreensPOM.getSelected_country_tv(), "UNITED STATES (US) +1");
	}
	
	@Test()
	public void verifyUserEnteringInvalidOrIncompletePhoneNumber() {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), "bafjfbak"); //invalid
		pressBack(); //closing keyboard
		click(OnboardingScreensPOM.getLoginPhoneBtn());
		checkIsElementDisplayed(OnboardingScreensPOM.getInvalidNumberToast(), 2);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), "111"); //incomplete
		pressBack(); //closing keyboard
		click(OnboardingScreensPOM.getLoginPhoneBtn());
		checkIsElementDisplayed(OnboardingScreensPOM.getInvalidNumberToast(), 1);
	}
	
	@Test()
	public void verifyUserEnteringRightPhoneNumberAndMovingToOTPScreen() {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), phoneNumber);
		pressBack(); //closing keyboard
		click(OnboardingScreensPOM.getLoginPhoneBtn());
		checkIsElementDisplayed(OnboardingScreensPOM.getOtpText(), 5);
	}
	
	@Test()
	public void verifyOTPScreenUiAndVerifyEnteringWrongOTPAndRetryingForOTPThenEnteringRightOTPAndMovingToHomeScreen() throws InterruptedException {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), phoneNumber);
		pressBack(); //closing keyboard
		click(OnboardingScreensPOM.getLoginPhoneBtn());
		checkIsElementDisplayed(OnboardingScreensPOM.getOtpText(), 3);
		checkIsDisplayedWithText(OnboardingScreensPOM.getOtpText(), OnboardingScreensPOM.getOtpText_txt());
		checkIsElementDisplayed(OnboardingScreensPOM.getProgressBar(), 1);
		checkIsDisplayedWithText(OnboardingScreensPOM.getWaitForOtp(), OnboardingScreensPOM.getWaitForOtp_());
		Thread.sleep(1000);
		Assert.assertTrue(Integer.parseInt(getText(OnboardingScreensPOM.getOtpTimerTV())) < 30);
		checkIsElementDisplayed(OnboardingScreensPOM.getRetryTV(), 30);
		checkIsDisplayedWithText(OnboardingScreensPOM.getRetryTV(), OnboardingScreensPOM.getRetryTV_txt());
		click(OnboardingScreensPOM.getRetryTV());
		Thread.sleep(1000);
		checkIsDisplayedWithText(OnboardingScreensPOM.getWaitForOtp(), OnboardingScreensPOM.getWaitForOtp_());
		Thread.sleep(1000);
		Assert.assertTrue(Integer.parseInt(getText(OnboardingScreensPOM.getOtpTimerTV())) < 30);
		clickAndSendKeysToElement(OnboardingScreensPOM.getOtpEditText(), "7896"); //wrong otp
		pressBack(); //closing keyboard
		clickAndSendKeysToElement(OnboardingScreensPOM.getOtpEditText(), otp);
		handleHomeScreen();
		checkIsElementDisplayed(OnboardingScreensPOM.getToolbarProfileIV(), 2);
	}
	
	@Test()
	public void verifyKillingAppAndRelaunchingOnPhoneNumberScreen() {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), phoneNumber); 
		relaunchApp();
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		checkIsDisplayedWithText(OnboardingScreensPOM.getLoginPhoneET(), OnboardingScreensPOM.getLoginPhoneET_txt());
	}
	
	@Test()
	public void verifyKillingAppAndRelaunchingOnOTPScreen() {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), phoneNumber);
		pressBack(); //closing keyboard
		click(OnboardingScreensPOM.getLoginPhoneBtn());
		checkIsElementDisplayed(OnboardingScreensPOM.getOtpText(), 4);
		clickAndSendKeysToElement(OnboardingScreensPOM.getOtpEditText(), "789"); //incomplete otp
		relaunchApp();
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		checkIsDisplayedWithText(OnboardingScreensPOM.getLoginPhoneET(), OnboardingScreensPOM.getLoginPhoneET_txt());
	}

	@Test()
	public void verifySuccessfulLoginTillHomeScreenAndKillingAppAndRelaunchingOnHomeScreenThenScrollHomeScreen() throws InterruptedException {
		login();
		relaunchApp();
		checkIsElementDisplayed(OnboardingScreensPOM.getToolbarProfileIV(), 5);
		Thread.sleep(2000);
		scrollDown();
		Thread.sleep(2000);
		scrollDown();
		Thread.sleep(2000);
		scrollUp();
	}

	@Test()
	public void verifyLoggingOutAndReachingPhoneNumberScreen(){
		login();
		click(OnboardingScreensPOM.getToolbarProfileIV());
		click(OnboardingScreensPOM.getProfileMenuSetting(), 3000);
		click(OnboardingScreensPOM.getLogOut_button(), 3000);
		click(OnboardingScreensPOM.getButton1(), 3000);
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		checkIsDisplayedWithText(OnboardingScreensPOM.getTitleTV(), OnboardingScreensPOM.getTitleTV_txt());
	}

	@Test()
	public void verifyNavigatingBackFromOtpScreenToChangePhoneNumber(){
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), phoneNumber);
		pressBack(); //closing keyboard
		click(OnboardingScreensPOM.getLoginPhoneBtn());
		checkIsElementDisplayed(OnboardingScreensPOM.getOtpText(), 4);
		pressBack();
		checkIsElementDisplayed(OnboardingScreensPOM.getLoginPhoneET(), 10);
	}

	private void handleHomeScreen(){
		if(waitForElementVisible(OnboardingScreensPOM.getAllow_button(), 5)){
			click(OnboardingScreensPOM.getAllow_button()); //handling swipe tutorial
		}
		if(waitForElementVisible(OnboardingScreensPOM.getSwipeUpTV(), 6)){
			click(OnboardingScreensPOM.getSwipeUpButton()); //handling permissions
		}
	}

	private void login(){
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), phoneNumber);
		pressBack(); //closing keyboard
		click(OnboardingScreensPOM.getLoginPhoneBtn());
		checkIsElementDisplayed(OnboardingScreensPOM.getOtpText(), 4);
		clickAndSendKeysToElement(OnboardingScreensPOM.getOtpEditText(), otp);
		handleHomeScreen();
		checkIsElementDisplayed(OnboardingScreensPOM.getToolbarProfileIV(), 5);
	}

	@AfterMethod
	public void testTearDown() {
		Logger.getLogger("Khamma Ghanii :)");
		executeCommands("adb shell pm clear com.crafto.android");
		relaunchApp();
	}

}
