package pageObjectModels;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.net.UrlChecker.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import tests.BaseClass;

public class OnboardingScreensPOM extends BaseClass {
	
	//This is the Page object mode in which all the elements needed for this project
	//and functions needed to check and perform action on them are there.
    
    static By appLogo = By.id("com.crafto.android:id/appLogo");
    static By titleTV = By.id("com.crafto.android:id/titleTV");
    static String titleTV_txt = "Crafto";
    static By subTitleTV = By.id("com.crafto.android:id/subTitleTV");
    static String subTitleTV_txt = "Get daily quotes/messages/story with your photo";
    static By flag_imv = By.id("com.crafto.android:id/flag_imv");
    static By selected_country_tv = By.id("com.crafto.android:id/selected_country_tv");
    static String selected_country_tv_txt = "INDIA (IN) +91";
    static By arrow_imv = By.id("com.crafto.android:id/arrow_imv");
    static By loginPhoneET = By.id("com.crafto.android:id/loginPhoneET");
    static String loginPhoneET_txt = "Enter Mobile No";
    static By loginPhoneBtn = By.id("com.crafto.android:id/loginPhoneBtn");
    static By legalText = By.id("com.crafto.android:id/legalText");
    static String legalText_txt = "static By signing-in, you agree to our \"Terms of Service \" and \"Privacy Policy \"";
    static By title_tv = By.id("com.crafto.android:id/title_tv");
    static String title_tv_txt = "Select Country";
    static By search_edt = By.id("com.crafto.android:id/search_edt");
    static String search_edt_txt = "Search…";
    static By country_name_tv = By.id("com.crafto.android:id/country_name_tv");
    static By code_tv = By.id("com.crafto.android:id/code_tv");
    static By invalidNumberToast = By.xpath("/hierarchy/android.widget.FrameLayout/android.view.View");
    static By tncScreen = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget."
    		+ "LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget."
    		+ "FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/"
    		+ "android.widget.TextView");
    static String tncScreen_txt = "Terms & Condition";
    static By privacyPolicyScreen = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/" +
            "android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view." +
            "ViewGroup/android.widget.ScrollView/android.widget.RelativeLayout/android.webkit.WebView/" +
            "android.webkit.WebView/android.widget.TextView[1]");
    static String privacyPolicyScreen_txt = "Crafto's Privacy Policy";
    static By otpText = By.id("com.crafto.android:id/otpText");
    static String otpText_txt = "4 digit OTP has been sent to your mobile number 1111119546 .Please Wait";
    static By progressBar = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout" +
            "/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/" +
            "android.widget.RelativeLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.RelativeLayout/android.widget.LinearLayout/" +
            "android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.ProgressBar");
    static By otpTimerTV = By.id("com.crafto.android:id/otpTimerTV"); //text should be less then "#30"
    static By waitForOtp = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/" +
            "android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.view.ViewGroup/" +
            "android.view.ViewGroup[2]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView");
    static String waitForOtp_ = "Waiting for an OTP";
    static By otpEditText = By.id("com.crafto.android:id/otpEditText");
    static By otpSubmitBtn = By.id("com.crafto.android:id/otpSubmitBtn");
    static By retryTV = By.id("com.crafto.android:id/retryTV");
    static String retryTV_txt = "Retry";
    static By profileIconLayout = By.id("com.crafto.android:id/profileIconLayout");
    static By invalidOtpToast = By.xpath("/hierarchy/android.widget.Toast[1]");
    static By swipeIV = By.id("com.crafto.android:id/swipeIV");
    static By swipeUpTV = By.id("com.crafto.android:id/swipeUpTV");
    static String swipeUpTV_TXT = "Swipe to read quotes";
    static By swipeUpButton = By.id("com.crafto.android:id/swipeUpButton");
    static String swipeUpButton_TXT = "Got it!";
    static By chipsCategoryLayout = By.id("com.crafto.android:id/chipsCategoryLayout");
    static By et_search = By.id("com.crafto.android:id/et_search");
    static String et_search_TXT = "search";
    static By tvCreate = By.id("com.crafto.android:id/tvCreate");
    static String tvCreate_TXT = "Create";
    static By toolbarProfileIV = By.id("com.crafto.android:id/toolbarProfileIV");
    static By profileMenuSetting = By.id("com.crafto.android:id/profileMenuSetting");
    static String profileMenuSetting_TXT = "Settings";
    static By logOut_button = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup[1]/" +
            "android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/" +
            "android.view.ViewGroup[4]/android.widget.TextView");
    static String logOut_button_txt = "Logout";
    static By alertTitle = By.id("com.crafto.android:id/alertTitle");
    static By button1 = By.id("android:id/button1");
    static By allow_button = By.id("com.android.permissioncontroller:id/permission_allow_button");
    static String yes_txt = "YES";

    public static By getAllow_button() {
        return allow_button;
    }

    public static By getSwipeIV() {
        return swipeIV;
    }

    public static By getSwipeUpTV() {
        return swipeUpTV;
    }

    public String getSwipeUpTV_TXT() {
        return swipeUpTV_TXT;
    }

    public static By getSwipeUpButton() {
        return swipeUpButton;
    }

    public String getSwipeUpButton_TXT() {
        return swipeUpButton_TXT;
    }

    public static By getChipsCategoryLayout() {
        return chipsCategoryLayout;
    }

    public static By getEt_search() {
        return et_search;
    }

    public String getEt_search_TXT() {
        return et_search_TXT;
    }

    public static By getTvCreate() {
        return tvCreate;
    }

    public String getTvCreate_TXT() {
        return tvCreate_TXT;
    }

    public static By getToolbarProfileIV() {
        return toolbarProfileIV;
    }

    public static By getProfileMenuSetting() {
        return profileMenuSetting;
    }

    public String getProfileMenuSetting_TXT() {
        return profileMenuSetting_TXT;
    }

    public static By getLogOut_button() {
        return logOut_button;
    }

    public String getLogOut_button_txt() {
        return logOut_button_txt;
    }

    public static By getAlertTitle() {
        return alertTitle;
    }

    public static By getButton1() {
        return button1;
    }

    public String getYes_txt() {
        return yes_txt;
    }

    public static By getInvalidOtpToast() {
        return invalidOtpToast;
    }
    
    public static By getInvalidNumberToast() {
        return invalidNumberToast;
    }
    
    public static By getTncScreen() {
        return tncScreen;
    }
    
    public static String getTncScreen_txt() {
        return tncScreen_txt;
    }

    public static By getAppLogo() {
        return appLogo;
    }

    public static By getTitleTV() {
        return titleTV;
    }

    public static String getTitleTV_txt() {
        return titleTV_txt;
    }

    public static By getSubTitleTV() {
        return subTitleTV;
    }

    public static String getSubTitleTV_txt() {
        return subTitleTV_txt;
    }

    public static By getFlag_imv() {
        return flag_imv;
    }

    public static By getSelected_country_tv() {
        return selected_country_tv;
    }

    public static String getSelected_country_tv_txt() {
        return selected_country_tv_txt;
    }

    public static By getArrow_imv() {
        return arrow_imv;
    }

    public static By getLoginPhoneET() {
        return loginPhoneET;
    }

    public static String getLoginPhoneET_txt() {
        return loginPhoneET_txt;
    }

    public static By getLoginPhoneBtn() {
        return loginPhoneBtn;
    }

    public static By getLegalText() {
        return legalText;
    }

    public static String getLegalText_txt() {
        return legalText_txt;
    }

    public static By getTitle_tv() {
        return title_tv;
    }

    public static String getTitle_tv_txt() {
        return title_tv_txt;
    }

    public static By getSearch_edt() {
        return search_edt;
    }

    public static String getSearch_edt_txt() {
        return search_edt_txt;
    }

    public static By getCountry_name_tv() {
        return country_name_tv;
    }

    public static By getCode_tv() {
        return code_tv;
    }

    public static By getPrivacyPolicyScreen() {
        return privacyPolicyScreen;
    }

    public static String getPrivacyPolicyScreen_txt() {
        return privacyPolicyScreen_txt;
    }

    public static By getOtpText() {
        return otpText;
    }

    public static String getOtpText_txt() {
        return otpText_txt;
    }

    public static By getProgressBar() {
        return progressBar;
    }

    public static By getOtpTimerTV() {
        return otpTimerTV;
    }

    public static By getWaitForOtp() {
        return waitForOtp;
    }

    public static String getWaitForOtp_() {
        return waitForOtp_;
    }

    public static By getOtpEditText() {
        return otpEditText;
    }

    public static By getOtpSubmitBtn() {
        return otpSubmitBtn;
    }

    public static By getRetryTV() {
        return retryTV;
    }

    public static String getRetryTV_txt() {
        return retryTV_txt;
    }

    public static By getProfileIconLayout() {
        return profileIconLayout;
    }
	
}
