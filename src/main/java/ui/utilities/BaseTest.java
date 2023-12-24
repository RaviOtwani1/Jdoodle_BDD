package ui.utilities;

import org.testng.annotations.BeforeClass;
import ui.pageobject.JdoodleCompilerPage;

import java.io.IOException;

public class BaseTest {
    public BaseDriver baseDriver = new BaseDriver();

    @BeforeClass
    public void setUp(){
        baseDriver = new BaseDriver();
        try {
            baseDriver.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
