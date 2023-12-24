package UIAutomator.Selenium;

import ui.utilities.ExtentReportUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IClassListener;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ui.utilities.BaseDriver;
import ui.utilities.ExtentReporterNG;
import ui.utilities.ScreenShotUtils;

public class Listeners extends ExtentReportUtils implements ITestListener, IClassListener {

    private static final Logger log = LoggerFactory.getLogger(Listeners.class);

    public void onTestStart(ITestResult result) {

        String methodName = result.getMethod().getMethodName();

        log.info("Start of onTestStart method. MethodName:" + methodName);
        startTest(result.getName());
        log.info("End of onTestStart method. MethodName:" + methodName);
    }

    public void onTestSuccess(ITestResult result) {

        String methodName = result.getMethod().getMethodName();

        log.info("Start of onTestSuccess method. MethodName:" + methodName);
        getTest().log(Status.PASS, "Test Passed");
        log.info("End of onTestSuccess method. MethodName:" + methodName);
        emptyTest();
    }


    public void onTestFailure(ITestResult result) {

        String methodName = result.getMethod().getMethodName();

        log.info("Start of onTestFailure method. MethodName: " + methodName);
        BaseDriver base = null;
        try {
            getTest().fail(result.getThrowable());
            base = (BaseDriver) result.getTestClass().getRealClass().getDeclaredField("baseDriver").get(result.getInstance());

            getTest()
                    .addScreenCaptureFromPath(ScreenShotUtils.getScreenShotPath(result.getName(), base.getDriver()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        log.info("End of onTestFailure method. MethodName:" + methodName);
        emptyTest();
    }

    public void onTestSkipped(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        log.info("Start of onTestSkipped method. MethodName:" + methodName);
        getTest().skip("Test case skipped");
        log.info("End of onTestSkipped method. MethodName:" + methodName);
        emptyTest();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {


    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public void onBeforeClass(ITestClass testClass) {
        System.out.println("Before before Class " + testClass.getRealClass().getSimpleName());

        System.out.println(Thread.currentThread().getId());
        startTest(testClass.getRealClass().getSimpleName());
    }


}
