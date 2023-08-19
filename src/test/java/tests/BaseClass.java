package tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseClass {  
	
	public AppiumDriver driver = null;
	
	@BeforeSuite
	public void setup() {
		try {
            //SDK path should be set in bash profile for ANDROID_HOME
            //1 device should be connected using usb or wifi debugging
            //appium server should be installed and should be running
		DesiredCapabilities caps = new 	DesiredCapabilities();
		caps.setCapability("platformName", "ANDROID") ; 
		caps.setCapability("newCommandTimeout", 60);
		caps.setCapability("appWaitActivity", "com.crafto.android.ui.login.LoginActivity");
		caps.setCapability("appPackage","com.crafto.android");
		caps.setCapability("app",System.getProperty("user.dir") + "/src/test/resources/app/release_5.1.1_314.apk");
		caps.setCapability("appActivity","com.crafto.android.ui.splash.RouteActivity");
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver(url, caps);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void pressBack() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
    }
	
	public static String executeCommands(String command) {
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                output.append(line + "\n");
            }
            while ((line = error.readLine()) != null) {
                output.append("Error Occurred : \n" + line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
	
	public void runAdbCommand(String command) {
		 if (command.startsWith("adb")) {
             command = command.replace("adb ", System.getenv("ANDROID_HOME") + "/platform-tools/adb ");
         }
         executeCommands(command);
	}
	
	public void relaunchApp() {
		runAdbCommand("adb shell am force-stop com.crafto.android");
		((AndroidDriver) driver).activateApp("com.crafto.android");    
	}
    
    public boolean waitForElementVisible(By locator, Integer duration) {
        try {
            Wait wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(duration)).pollingEvery(Duration.ofMillis(200));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    
    public void clickAndSendKeysToElement(By element, String value) {
        click(element);
        driver.findElement(element).sendKeys(value);
    }
    
    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }
    
    public void checkIsElementDisplayed(By id, int duration) {
        Assert.assertTrue(waitForElementVisible(id, duration), "Element not found with ID: " + id.toString());
    }
    
    public void checkIsDisplayedWithText(By id, String text) {
        Assert.assertEquals(getText(id), text, "Element not found with ID: " + id.toString() + "and text" + text);
    }
    
    public void click(By locator) {
    	driver.findElement(locator).click();
    }

    public void click(By element, int duration) {
        if (waitForElementVisible(element, duration)) {
            click(element);
        }
    }

    public void scrollUp() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollHeightStart = (int) (dimension.getHeight() * .3);
        int scrollHeightEnd = (int) (dimension.getHeight() * .7);
        Duration STEP_DURATION = Duration.ofMillis(700);
        Duration NO_TIME = Duration.ofMillis(0);
        PointerInput.Origin VIEW = PointerInput.Origin.viewport();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(NO_TIME, VIEW, dimension.getWidth() / 2, scrollHeightStart));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(STEP_DURATION, VIEW, dimension.getWidth() / 2, scrollHeightEnd));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

    public void scrollDown() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollHeightStart = (int) (dimension.getHeight() * .5);
        int scrollHeightEnd = (int) (dimension.getHeight() * .2);
        Duration STEP_DURATION = Duration.ofMillis(700);
        Duration NO_TIME = Duration.ofMillis(0);
        PointerInput.Origin VIEW = PointerInput.Origin.viewport();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(NO_TIME, VIEW, dimension.getWidth() / 2, scrollHeightStart));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(STEP_DURATION, VIEW, dimension.getWidth() / 2, scrollHeightEnd));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

	@AfterSuite
	public void teardown() {
		driver.quit();
	}

}
