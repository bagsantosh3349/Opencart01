package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider1
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
	    String path = ".\\TestData\\DDT.xlsx";
	    
	    // Ensure the file exists
	    File file = new File(path);
	    if (!file.exists()) {
	        throw new FileNotFoundException("Test data file not found at: " + path);
	    }

	    ExcelUtility xlutil = new ExcelUtility(path);
	    
	    int totalrows = xlutil.getRowCount("Sheet1");
	    int totalcols = 3; // We are expecting exactly 3 columns: email, password, and exp

	    String[][] logindata = new String[totalrows][totalcols];

	    for (int i = 1; i <= totalrows; i++) {
	        for (int j = 0; j < totalcols; j++) {
	            logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
	        }
	    }
	    
	    return logindata;
	}

	
	}
	
