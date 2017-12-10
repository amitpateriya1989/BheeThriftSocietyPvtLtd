package Controller;


import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import java.io.File;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.catalina.connector.Request;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import Model.Bean.BulkExcelFileBean;
import Model.Dao.BulkExcelFileDao;




/**
 * Servlet implementation class AdExcelFile
 */
@WebServlet("/AdExcelFile")
public class AdExcelFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "ExcelFile";
	  HashMap<String,String>map=new HashMap<String,String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdExcelFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("upload")){
			
			
			if(ServletFileUpload.isMultipartContent(request)){
				
				 String uploadPath = getServletContext().getRealPath("")
				            + File.separator + UPLOAD_DIRECTORY;
				        // creates the directory if it does not exist
				        File uploadDir = new File(uploadPath);
				        if (!uploadDir.exists()) {
				            uploadDir.mkdir();
				        }
				
	            try {
	                @SuppressWarnings("unchecked")
					List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	                String filePath =null;
	                for(FileItem item : multiparts){
	                    if(!item.isFormField()){
	                        String name = new File(item.getName()).getName();
	                         filePath = uploadPath + File.separator + name;
	                        File  storeFile = new File(filePath);
		                    item.write(storeFile);
		                    map.put(item.getFieldName(), name);
	                        //item.write( new File(uploadPath + File.separator + name));
		                    item.write(storeFile);
		                    System.out.print(storeFile);
	                    }
	                }
	           
	               //File uploaded successfully
	               request.setAttribute("message", "File Uploaded Successfully");
	               bulkdbinsert(filePath);
	               response.sendRedirect("bulkexcelupload.jsp");
	               
	            } catch (Exception ex) {
	               //request.setAttribute("message", "File Upload Failed due to " + ex);
	            }          
	         
	        }else{
	            //request.setAttribute("message"," Sorry this Servlet only handles file upload request");
	        }
	    
	       // request.getRequestDispatcher("bulkexcelupload.jsp").forward(request, response);

			
	
			
		}
		
		
		
		
	}
	
	public void bulkdbinsert(String  file){
		try{
		HSSFRow row;
		FileInputStream fis = new FileInputStream(new File(file));
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet spreadsheet = workbook.getSheetAt(0);
		 Iterator rowIterator =(Iterator) spreadsheet.iterator();
		 BulkExcelFileBean bean =new BulkExcelFileBean();
		 
		 int cells=0;
		while (rowIterator.hasNext())
		{
			cells=0;
			row=(HSSFRow) rowIterator.next();
			 Iterator cellIterator = row.cellIterator();
			//Iterator <HSSFCell> cellIterator = (HSSFCell)row.cellIterator();
			while(cellIterator.hasNext())
		{
				
			HSSFCell cell=(HSSFCell) cellIterator.next();
			switch (cell.getCellType())
			{
			case HSSFCell.CELL_TYPE_NUMERIC:
				//System.out.println(cell.getNumericCellValue()+ "\t\t");
				if(cells==0){
					bean.setAd_pf_no((int)cell.getNumericCellValue() );
				} 
				if(cells==2){
					bean.setThrift_amt(cell.getNumericCellValue());
				}
				if(cells==3){
					bean.setLoan_amt(cell.getNumericCellValue());
				}
				if(cells==4){
					bean.setExcess_amt(cell.getNumericCellValue());
				}
				if(cells==5){
					bean.setTotal(cell.getNumericCellValue());
				}
				break;
			case HSSFCell.CELL_TYPE_STRING:
				//System.out.println(cell.getStringCellValue()+ "\t\t");
				if(cells==1){
					bean.setMember_name(cell.getStringCellValue());
				}								
				break;
			}
			
			cells++;
		}
		
			new BulkExcelFileDao().addtempExcelPayrol(bean);
	 }
	 fis.close();
	 
	
		
		}catch( Exception e){
			e.printStackTrace();
		}
		
	}

}


