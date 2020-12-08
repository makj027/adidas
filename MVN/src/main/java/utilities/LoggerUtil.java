package utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;


public class LoggerUtil {

    /**
     * Log info level logs
     * @param message
     * @param log
     */
    public static void logInfo(String message, Logger log){
        log.info(message);
    }

    /**
     * Log debug level logs
     * @param message
     * @param log
     */
    public static void logDebug(String message,Logger log){
        log.debug(message);
    }

    /**
     * Log trace level logs
     * @param message
     * @param log
     */
    public static void logTrace(String message, Logger log){
        log.trace(message);
    }



}
