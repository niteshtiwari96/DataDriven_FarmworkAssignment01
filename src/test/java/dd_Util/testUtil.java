package dd_Util;


import java.io.BufferedInputStream;





import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import dd_Core.testCore;
	

public class testUtil extends testCore {

	public static String mailscreenshotpath;
	
	//public static WebDriver driver;
public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\java\\dd_properties\\testDataInClass.xlsx");
	
	public static boolean isExecutable(String testid)
	{
		String sheetName="test_suite";
		
		int rows=excel.getRowCount(sheetName);//2
		int cols=excel.getColumnCount(sheetName);//2
		
		for(int rowNum=2;rowNum<=rows;rowNum++)//only once
		{
			if(excel.getCellData(sheetName, "tcid", rowNum).equals(testid))
			{
				if(excel.getCellData(sheetName, "runmode", rowNum).equals("Y"))
				{
					return true;
				}else
				{
					return false;
				}
			}
		}
		return false;
	}
	
	
	public static Object[][] getData(String sheetName)
	{
		if(excel==null)
		{
			excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\java\\dd_properties\\testDataInClass.xlsx");
		}
		
		//String sheetName="TC_01_LoginTest";
		int rows=excel.getRowCount(sheetName);//2
		int cols=excel.getColumnCount(sheetName);//2
		System.out.println("Number of rows: "+rows);
		System.out.println("Number of cells: "+cols);
		
		Object[][] data=new Object[rows-1][cols];
		
		for(int rowNum=2;rowNum<=rows;rowNum++)//2
		{
			for(int colNum=0;colNum<cols;colNum++)//0,1,2
			{
				data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum);
				//data[0][0]=niteshtiwari186@yahoo.in
				//data[0][1]=115492
			}
				
		}
		return data; 
	}
	
	
	
	
	
	
	public static void captureScreenshot(String methodName) throws IOException{
		
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.HOUR_OF_DAY);
		
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		mailscreenshotpath = System.getProperty("user.dir")+"\\screenshot\\"+methodName+"_"+year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec+".jpeg";
		FileUtils.copyFile(scrFile, new File(mailscreenshotpath));
		}
	
	
	
	
	
   /* try {
    	
    	mailscreenshotpath = methodName+"_"+year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec+".jpeg";
    	
    	FileUtils.copyFile(scrFile, new File("C:\\11_Jan_2020Batch\\TestNG_ListenersOnFailure\\Screenshot "+mailscreenshotpath));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	*/
	
	
	
	
	// make zip of reports
		public static void zip(String filepath){
		 	try
		 	{
		 		File inFolder=new File(filepath);
		 		File outFolder=new File("Reports.zip");
		 		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
		 		BufferedInputStream in = null;
		 		byte[] data  = new byte[1000];
		 		String files[] = inFolder.list();
		 		for (int i=0; i<files.length; i++)
		 		{
		 			in = new BufferedInputStream(new FileInputStream
		 			(inFolder.getPath() + "/" + files[i]), 1000);  
		 			out.putNextEntry(new ZipEntry(files[i])); 
		 			int count;
		 			while((count = in.read(data,0,1000)) != -1)
		 			{
		 				out.write(data, 0, count);
		 			}
		 			out.closeEntry();
	  }
	  out.flush();
	  out.close();
		 	
	}
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  } 
	 }	
}
