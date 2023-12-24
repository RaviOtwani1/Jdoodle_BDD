package ui.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.output.WriterOutputStream;
import ui.utilities.ExtentReporterNG;

import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ExtentReportUtils {

    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    public ExtentTest test;
    public static ExtentReports extent = ExtentReporterNG.getReportObject();

    StringWriter requestLog = new StringWriter();
    StringWriter responseLog = new StringWriter();
    PrintStream requestPrintStream = new PrintStream(new WriterOutputStream(requestLog, Charset.defaultCharset()),
            true);
    PrintStream responsePrintStream = new PrintStream(new WriterOutputStream(responseLog, Charset.defaultCharset()),
            true);


    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }
    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized void emptyTest() {
        extentTestMap.remove((int) Thread.currentThread().getId());
    }
}
