package ui.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//TODO - Improve method for fetching and reading data.

public class DataUtils {

	private static String demoUrl ;
	private static String demoUsername ;
	private static String demoPassword ;

	public static void call() throws IOException {
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\testExcelFile.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook work_book = new XSSFWorkbook(fi);
		XSSFSheet sheet = work_book.getSheet("TestData");
		
		XSSFRow row = sheet.getRow(0);
		int row_count = sheet.getPhysicalNumberOfRows();
		
		
		for(int i =0 ; i < row_count; i++) {
			row = sheet.getRow(i);
			int column_count = row.getPhysicalNumberOfCells();
			for(int j = 0; j<column_count;j++) {
				System.out.print(row.getCell(j).getStringCellValue());
			}
			System.out.println();
		}
		
			
		}

	public static void getWebAppDatafromConfig(Properties prop) {
		if(System.getProperty("url") != null)
			setDemoUrl(System.getProperty("url"));
		else
			setDemoUrl(prop.getProperty("demourl2"));

		if(System.getProperty("username") != null)
			setDemoUsername(System.getProperty("username"));
		else
			setDemoUsername(prop.getProperty("demousername2"));

		if(System.getProperty("password") != null)
			setDemoPassword(System.getProperty("password"));
		else
			setDemoPassword(prop.getProperty("demopassword2"));

	}

	public static String getDemoUrl() {
		return demoUrl;
	}

	public static void setDemoUrl(String demoUrl) {
		DataUtils.demoUrl = demoUrl;
	}

	public static String getDemoUsername() {
		return demoUsername;
	}

	public static void setDemoUsername(String demoUsername) {
		DataUtils.demoUsername = demoUsername;
	}

	public static String getDemoPassword() {
		return demoPassword;
	}

	public static void setDemoPassword(String demoPassword) {
		DataUtils.demoPassword = demoPassword;
	}
		
	
}
