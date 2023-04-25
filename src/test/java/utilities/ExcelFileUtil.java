package utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelFileUtil {
	XSSFWorkbook wb;
	public ExcelFileUtil(String ExcelFile) throws Throwable
	{
		FileInputStream fi = new FileInputStream(ExcelFile);
		wb = new XSSFWorkbook(fi);
	}
	public int rowCount(String Sheetname)
	{
		return wb.getSheet(Sheetname).getLastRowNum();	
	}
	public String getCelldata(String Sheetname,int row,int column)
	{
		String data="";
		if (wb.getSheet(Sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC) 
		{
			int celldata = (int) wb.getSheet(Sheetname).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else
		{
			data =wb.getSheet(Sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	public void SetCelldata(String SheetName,int row,int Column,String status,String WriteExcel) throws Throwable
	{
		XSSFSheet ws = wb.getSheet(SheetName);
		XSSFRow rowNum = ws.getRow(row);
		XSSFCell cell = rowNum.createCell(Column);
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("Pass")) 
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font =wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(Column).setCellStyle(style);
		}
		else if (status.equalsIgnoreCase("Fail")) 
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font =wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(Column).setCellStyle(style);
		}
		else if (status.equalsIgnoreCase("Blocked"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font =wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(Column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(WriteExcel);
		wb.write(fo);
	}
}
