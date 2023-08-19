package tests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pageObjectModels.OnboardingScreensPOM;

import java.util.logging.Logger;

public class OnboardingScreenTest extends BaseClass{
	
	//Note:: please don't judge on test cases number as one automation case here is covering multiple unit cases.
	//will not write the otp expiry case bcs I only have one user and that may lead to user block on your server.
	
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
//		checkIsDisplayedWithText(OnboardingScreensPOM.getLegalText(), OnboardingScreensPOM.getLegalText_txt());
	}
	
	@Test()
	public void verifyCountrySelectionUiClickingOnUnitedStates() {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		click(OnboardingScreensPOM.getArrow_imv());
		checkIsElementDisplayed(OnboardingScreensPOM.getTitle_tv(), 2);
		checkIsDisplayedWithText(OnboardingScreensPOM.getSearch_edt(), OnboardingScreensPOM.getSearch_edt_txt());
		clickAndSendKeysToElement(OnboardingScreensPOM.getSearch_edt(), "United States");
		click(OnboardingScreensPOM.getFlag_imv());
		click(OnboardingScreensPOM.getCountry_name_tv());
		click(OnboardingScreensPOM.getCode_tv());
		click(OnboardingScreensPOM.getCountry_name_tv());
		checkIsElementDisplayed(OnboardingScreensPOM.getSelected_country_tv(), 2);
		checkIsDisplayedWithText(OnboardingScreensPOM.getSelected_country_tv(), "UNITED STATES (US) +1");
	}
	
	@Test()
	public void verifyUserEnteringInvalidOrIncompletePhoneNumber() {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), "bafjfbak"); //invalid
		checkIsElementDisplayed(OnboardingScreensPOM.getInvalidNumberToast(), 1);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), "111111954"); //incomplete
		checkIsElementDisplayed(OnboardingScreensPOM.getInvalidNumberToast(), 1);
	}
	
	@Test()
	public void verifyUserEnteringRightPhoneNumberAndMovingToOTPScreen() {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), phoneNumber); 
		click(OnboardingScreensPOM.getLoginPhoneBtn());
		checkIsElementDisplayed(OnboardingScreensPOM.getOtpText(), 4);
	}
	
	@Test()
	public void verifyOTPScreenUiAndVerifyEnteringWrongOTPAndRetryingForOTPThenEnteringRightOTPAndMovingToHomeScreen() throws InterruptedException {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), phoneNumber);
		click(OnboardingScreensPOM.getLoginPhoneBtn());
		checkIsElementDisplayed(OnboardingScreensPOM.getOtpText(), 1);
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
		checkIsElementDisplayed(OnboardingScreensPOM.getInvalidOtpToast(), 1);
		clickAndSendKeysToElement(OnboardingScreensPOM.getOtpEditText(), otp); 
		checkIsElementDisplayed(OnboardingScreensPOM.getProfileIconLayout(), 5);
	}
	
	@Test()
	public void verifykillingAppAndRelaunchingOnPhoneNumberScreen() {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), phoneNumber); 
		relaunchApp();
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		checkIsDisplayedWithText(OnboardingScreensPOM.getLoginPhoneET(), OnboardingScreensPOM.getLoginPhoneET_txt());
	}
	
	@Test()
	public void verifykillingAppAndRelaunchingOnOTPScreen() {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), phoneNumber); 
		click(OnboardingScreensPOM.getLoginPhoneBtn());
		checkIsElementDisplayed(OnboardingScreensPOM.getOtpText(), 4);
		clickAndSendKeysToElement(OnboardingScreensPOM.getOtpEditText(), "789"); //incomplete otp
		relaunchApp();
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		checkIsDisplayedWithText(OnboardingScreensPOM.getLoginPhoneET(), OnboardingScreensPOM.getLoginPhoneET_txt());
	}

	@Test()
	public void verifySuccessfulLoginTillHomeScreenAndkillingAppAndRelaunchingOnHomeScreenThenScrollHomeScreen() {
		checkIsElementDisplayed(OnboardingScreensPOM.getAppLogo(), 10);
		clickAndSendKeysToElement(OnboardingScreensPOM.getLoginPhoneET(), phoneNumber); 
		click(OnboardingScreensPOM.getLoginPhoneBtn());
		checkIsElementDisplayed(OnboardingScreensPOM.getOtpText(), 4);
		clickAndSendKeysToElement(OnboardingScreensPOM.getOtpEditText(), otp); 
		checkIsElementDisplayed(OnboardingScreensPOM.getProfileIconLayout(), 5);
		relaunchApp();
		checkIsElementDisplayed(OnboardingScreensPOM.getProfileIconLayout(), 5);
	}
//	
//	@Test()
//	public void verifyLoggingOutAndReachingPhoneNumberScreen(){
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsDisplayedWithText(OnboardingScreensPOM, OnboardingScreensPOM);
//		checkIsDisplayedWithText(OnboardingScreensPOM, OnboardingScreensPOM);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsDisplayedWithText(OnboardingScreensPOM, OnboardingScreensPOM);
//		checkIsDisplayedWithText(OnboardingScreensPOM, OnboardingScreensPOM);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsDisplayedWithText(OnboardingScreensPOM, OnboardingScreensPOM);
//		checkIsDisplayedWithText(OnboardingScreensPOM, OnboardingScreensPOM);
//	}
//	
//	@Test()
//	public void verifyNavigatingBackFromOtpScreenToChangePhoneNumber(){
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsDisplayedWithText(OnboardingScreensPOM, OnboardingScreensPOM);
//		checkIsDisplayedWithText(OnboardingScreensPOM, OnboardingScreensPOM);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsDisplayedWithText(OnboardingScreensPOM, OnboardingScreensPOM);
//		checkIsDisplayedWithText(OnboardingScreensPOM, OnboardingScreensPOM);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsElementDisplayed(OnboardingScreensPOM, 1);
//		checkIsDisplayedWithText(OnboardingScreensPOM, OnboardingScreensPOM);
//		checkIsDisplayedWithText(OnboardingScreensPOM, OnboardingScreensPOM);
//	}
//	
	@AfterMethod
	public void testTearDown() {
		Logger.getLogger("Khamma Ghanii :)");
		executeCommands("adb shell pm clear com.crafto.android");
		relaunchApp();
	}

}
