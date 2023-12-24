package UIAutomator.Selenium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.pageobject.JdoodleCompilerPage;
import ui.utilities.BaseDriver;

import java.io.IOException;
/*

This is a Demo test created for an e-commerce website ( https://www.demoblaze.com/ )
        for buying a mobile phone
*/

public class Jdoodle {
    BaseDriver baseDriver;
    JdoodleCompilerPage jdoodleCompilerPage;
    String text;

    private static final Logger log = LoggerFactory.getLogger(Jdoodle.class);

    @BeforeClass(alwaysRun = true)
    public void setUp(ITestContext context) throws IOException {
    	log.info("start of BeforeClass > setup method");
    	baseDriver = new BaseDriver();
        baseDriver.initialize();
        jdoodleCompilerPage = new JdoodleCompilerPage(baseDriver);
        log.info("end of BeforeClass > setup method");
    }

    @Test()
    public void testByDefaultJavaCodingLanguageIsSelected() {

        jdoodleCompilerPage.navigateToCompilerPage("https://www.jdoodle.com/online-java-compiler");
        text = jdoodleCompilerPage.getPageTitle().getText();
        Assert.assertEquals(text , "Online Java Compiler IDE", "text "+ text );
        text = jdoodleCompilerPage.getCodeEditorLineText(1);
        Assert.assertEquals(text.trim() , "public static void main(String args[]) {", "text "+ text );
    }

    @Test(dependsOnMethods = {"testByDefaultJavaCodingLanguageIsSelected"})
    public void testUserIsAbleToSearchCodingLanguageUsingSearchBox() {
        jdoodleCompilerPage.searchLanguage("C#");
        int count = jdoodleCompilerPage.getCountOfSearchResult();
        Assert.assertEquals(count, 2, "count "+ count);
        jdoodleCompilerPage.selectLanguage("C#");
    }

    @Test(dependsOnMethods = {"testUserIsAbleToSearchCodingLanguageUsingSearchBox"})
    public void testChangesAsPerNewCodingLanguage() {
        text = jdoodleCompilerPage.getCodeEditorLineText(0);
        Assert.assertEquals(text , "using System;", "text "+ text );

        text = jdoodleCompilerPage.getPageTitle().getText();
        Assert.assertEquals(text , "Online C# Compiler IDE", "text "+ text );
    }

    /*@Test(dependsOnMethods = {"testChangesAsPerNewCodingLanguage"})
    public void testExecuteButtonResult() {
        jdoodleCompilerPage.clickOnExecuteButton();
        jdoodleCompilerPage.verifyOutputOfCode("Sum of x + y = 35");
    }*/

    @Test(dependsOnMethods = {"testMenuItems"})
    public void testLogin() {
        jdoodleCompilerPage.clickOnLoginButton();
        jdoodleCompilerPage.checkLogin("Sign in with Google");
        jdoodleCompilerPage.checkLogin("Sign in with Microsoft");

    }

    @Test(dependsOnMethods = {"testChangesAsPerNewCodingLanguage"})
    public void testMenuItems() {
        jdoodleCompilerPage.verifyMenuItemsDisplayed("Products");
        jdoodleCompilerPage.verifyMenuItemsDisplayed("Solutions");
        jdoodleCompilerPage.verifyMenuItemsDisplayed("Resources");
        jdoodleCompilerPage.verifyMenuItemsDisplayed("Pricing");
    }
    @AfterClass(alwaysRun = true)
    public void tearDown() {
    	log.info("start of AfterClass > tearDown method");
        baseDriver.close();
        log.info("end of AfterClass > tearDown method");
    }
}
