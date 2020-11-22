package ExcelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {
	
	
	public static Object[][] readDataFromExcel(String sheetname) throws IOException {
		FileInputStream fi = null;
		XSSFWorkbook w;
		XSSFSheet s;
		
		try {
			fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Testdatasheet_swagger.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		 w = new XSSFWorkbook(fi);	
		 s =  w.getSheet(sheetname);
		 int lr = s.getLastRowNum();
		 int lc = s.getRow(0).getLastCellNum();
		 int tr = lr+1;
		 System.out.println("lr : "+lr+" lc : "+lc);
		 Object data[][] = new Object[lr][lc];
		 for(int i=1;i<=lr;i++) {
			 for(int j=0;j<lc;j++) {
				 String val = s.getRow(i).getCell(j).toString();
				 System.out.println(val+" : "+val.getClass().getName()); //.getClass().getName() will give type of value like String
				 
				 data[i-1][j] = val; 
			 }
		 }
		 
		 return data;
		
	}

}
