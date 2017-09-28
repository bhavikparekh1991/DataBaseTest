package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class DBConnection_1 {
	
	public static void DBtest() throws ClassNotFoundException, SQLException, IOException{
		FileInputStream ExcelFile = new FileInputStream("//home/bhavik/Downloads/DataforDBValidation.xlsx");
		XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
		XSSFSheet ExcelWSheet = ExcelWBook.getSheetAt(0);
		int RowCount=ExcelWSheet.getLastRowNum()+1;
		System.out.println(RowCount);
		List<String> ExCamp = new ArrayList<String>();
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:/home/bhavik/Downloads/CMS_Data.db");
		
		for(int i=1;i<RowCount;i++)
		{
			XSSFRow Row  = ExcelWSheet.getRow(i);
		    Cell Cell = Row.getCell(1);
	 		try 
	 		{
	 			Statement stmt = conn.createStatement();
	 			ResultSet rs = stmt.executeQuery("Select client_name from CMT_DATA_20170919 where campaign_name ='" +ExcelWSheet.getRow(i).getCell(0).getStringCellValue().toString()+"'");
		        while ( rs.next() ) 
		        {
		        	String  name = rs.getString("client_name");
		        	//System.out.println( ExcelWSheet.getRow(i).getCell(0).getStringCellValue().toString() + " - " + name );
		        	if (Cell == null) 
		        	{
		        		Cell = Row.createCell(1);
						Cell.setCellValue(name);
					}
		        	else
		        	{
		        		Cell.setCellValue(name);
					}
		        }
		        rs.close();
		        stmt.close();
	 		}
	 		catch (SQLException e) 
	 		{
	            System.out.println(e.getMessage());
	            System.out.println("Connection to SQLite has not established.");
	        } 
		}
		
		conn.close();
		FileOutputStream fileOut = new FileOutputStream("//home/bhavik/Downloads/DataforDBValidation.xlsx");
		ExcelWBook.write(fileOut);
		fileOut.close();
		System.out.println("t");
	}
		
    /**
     * @param args the command line arguments
     * @throws ClassNotFoundException 
     * @throws IOException 
     * @throws SQLException 
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
    	DBtest();
    }
}
