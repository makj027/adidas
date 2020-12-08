package utilities;


import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Log4j
public class CommonWebActions {


    public static void waitforElement() throws InterruptedException {

            Thread.sleep(5000);

    }

    public static Alert switchToAlert(WebDriver driver) {

            Alert alert = driver.switchTo().alert();
            LoggerUtil.logTrace("Alert or popup is present",log);
            return alert;
    }

    public static String getAlertText(Alert alert) {

        String text = alert.getText();
        LoggerUtil.logTrace("Alert or popup is present",log);
        return text;
    }

    public static void acceptAlert(Alert alert) {

        alert.accept();
        LoggerUtil.logTrace("Alert or popup is present",log);
    }

    public static void clickElementWithoutWait(WebDriver driver,String locator) throws Exception {

        LoggerUtil.logTrace("Clicking on locator-- " + locator,log);
        waitforElement();
        driver.findElement(By.xpath(locator)).click();

    }

    public static boolean isDisplayed(WebDriver driver, String locator) {

            if (driver.findElement(By.xpath(locator)).isDisplayed() == true) {
                LoggerUtil.logTrace("Locator is displayed - " + locator,log);
                return true;
            } else {
                LoggerUtil.logTrace("Locator is not displayed - " + locator,log);

                return false;
            }
    }

    public static void fillTextWithoutClear(WebDriver driver, Duration duration, Duration interval, String locator, String text) throws Exception {

        LoggerUtil.logTrace("Entering text-" + text + " in locator--" + locator,log);
        waitforElement();
        fluentWaitTillElementIsVisible(locator, driver, duration, interval);
        fluentWaitTillElementClickable(locator, driver, duration, interval);
        driver.findElement(By.xpath(locator)).sendKeys(text);

    }

    public static void waitFor(long millis) throws InterruptedException {

            Thread.sleep(millis);

    }

    public static String fetchText(WebDriver driver, String xpath) {

            return driver.findElement(By.xpath(xpath)).getText();
    }

    public static boolean fluentWaitTillElementIsPresent(String xpath, WebDriver driver, Duration duration, Duration interval){

        LoggerUtil.logTrace("Waiting for Frame" + xpath, log);

        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(duration)
                .pollingEvery(interval)
                .ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

        return true;
    }

    public static boolean fluentWaitTillElementIsVisible(String xpath, WebDriver driver, Duration duration, Duration interval) throws Exception {

            LoggerUtil.logTrace("Inside fluentWaitTillElementIsVisible method. Waiting for element:" + xpath,log);
            Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                    .withTimeout(duration)
                    .pollingEvery(interval)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(ElementNotVisibleException.class);

            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        return true;
    }

    public static void fluentWaitTillElementClickable(String xpath, WebDriver driver, Duration duration, Duration interval) throws Exception {

            LoggerUtil.logTrace("Inside fluentWaitTillElementClickable method. Waiting for element:" + xpath,log);
            Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                    .withTimeout(duration)
                    .pollingEvery(interval)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(ElementNotVisibleException.class);
            fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

    }

    public static void fetchURL(WebDriver driver, String url) {

        driver.navigate().to(url);

    }

    public static boolean waitForAlertPresent(WebDriver driver, long timeoutInSec) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
        wait.until(ExpectedConditions.alertIsPresent());


        return true;
    }

  

}