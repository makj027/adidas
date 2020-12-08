package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.LoggerUtil;

import java.util.concurrent.TimeUnit;

@Log4j
public class WebDriverFactory {



    private static RemoteWebDriver getDriverSetup(String browser, RemoteWebDriver driver) throws Exception{
        try{
            switch (browser.toUpperCase()) {
                case "CHROME": {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                }
                case "FIREFOX": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                }
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
        }catch (Exception ex){
            throw new Exception("Exception occurred in getDriver() method",ex);
        }
        return driver;
    }

    /**
     * Method to get driver instance
     * @param browser
     * @param implicitWaitInMillSec
     * @param pageLoadTimeoutInMillSec
     * @return
     * @throws Exception
     */
    public static RemoteWebDriver getDriver(String browser, long implicitWaitInMillSec, long pageLoadTimeoutInMillSec) throws Exception {

        RemoteWebDriver driver=null;

        driver = getDriverSetup(browser, driver);

        LoggerUtil.logInfo("Driver SessionId: "+driver.getSessionId().toString(),log);
        driver = configureBrowser(driver,implicitWaitInMillSec, pageLoadTimeoutInMillSec);

        return driver;
    }

    private static RemoteWebDriver configureBrowser(RemoteWebDriver driver, long implicitWaitInMillSec, long pageLoadTimeoutInMillSec){

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitWaitInMillSec, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeoutInMillSec, TimeUnit.MILLISECONDS);

        return driver;
    }

    /**
     * Method is used to exit driver instance passed.
     * @param driver
     */
    public static void exitDriver(RemoteWebDriver driver){
        driver.close();
        driver.quit();
    }

}
