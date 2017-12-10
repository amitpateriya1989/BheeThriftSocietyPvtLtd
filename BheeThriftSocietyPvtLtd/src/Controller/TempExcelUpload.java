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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.catalina.connector.Request;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import Model.Bean.AccountSubGroupBean;
import Model.Bean.BankBean;
import Model.Bean.BankBranchBean;
import Model.Bean.BulkExcelFileBean;
import Model.Bean.CategoryBean;
import Model.Bean.CityBean;
import Model.Bean.DepartmentBean;
import Model.Bean.DesignationBean;
import Model.Bean.DistrictBean;
import Model.Bean.LedgerAccountBean;
import Model.Bean.LoanTrxBean;
import Model.Bean.MemberAddressBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.NominationBean;
import Model.Bean.PostingDetailBean;
import Model.Bean.RelationBean;
import Model.Bean.StateBean;
import Model.Dao.BankBranchDao;
import Model.Dao.BulkExcelFileDao;
import Model.Dao.DepartmentDao;
import Model.Dao.DesignationDao;
import Model.Dao.LedgerAccountDao;
import Model.Dao.PostingDetailDao;
import Model.Dao.TempExcelUploadDao;




/**
 * Servlet implementation class TempExcelUpload
 */
@WebServlet("/TempExcelUpload")
public class TempExcelUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "ExcelFile";
	  HashMap<String,String>map=new HashMap<String,String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TempExcelUpload() {
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
			
			System.out.print("inner upload block");
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
	            //   response.sendRedirect("bulkexcelupload.jsp");
	               
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
		
		
		System.out.print(file);
		try{
		HSSFRow row;
		FileInputStream fis = new FileInputStream(new File(file));
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet spreadsheet = workbook.getSheetAt(0);
		 Iterator rowIterator =(Iterator) spreadsheet.iterator();
	
		 
		 int cells=0;
		while (rowIterator.hasNext())
		{
			cells=0;
			row=(HSSFRow) rowIterator.next();
			 Iterator cellIterator = row.cellIterator();
			//Iterator <HSSFCell> cellIterator = (HSSFCell)row.cellIterator();
			 MemberRegistrationBean bean =new MemberRegistrationBean();
		//	 MemberAddressBean bean =new MemberAddressBean();
			// CityBean cb =new CityBean();	
			BankBranchBean bbbean = new BankBranchBean();
			while(cellIterator.hasNext())
		{
				
			HSSFCell cell=(HSSFCell) cellIterator.next();
			
			switch (cell.getCellType())
			{
			case HSSFCell.CELL_TYPE_NUMERIC:
			//	System.out.println(cell.getNumericCellValue()+ "\t\t");
				if(cells==0){		
					bean.setAd_society_no((int)cell.getNumericCellValue());
				//	bean.setAd_member_id( new TempExcelUploadDao().getmemberid((int)cell.getNumericCellValue()) );
				//	System.out.println(cell.getNumericCellValue()+" "+ bean.getAd_member_id() +"\t\t");
				} 
				
				if(cells==1){
					bean.setAd_pf_no((int)cell.getNumericCellValue());
				}
				if(cells==2){
					
					bbbean=new BankBranchDao().getBankBranchBycode1((int)cell.getNumericCellValue());
					
					bean.setBranch(bbbean);
				}
				
				if(cells==4){
					bean.setAd_gender_id((int)cell.getNumericCellValue());
				}
				
				if(cells==6){
					CategoryBean cbean = new CategoryBean();
					cbean.setAd_category_id((int)cell.getNumericCellValue());
					bean.setCategory(cbean);
					
					//Ad_gender_id((int)cell.getNumericCellValue());
				}
				if (HSSFDateUtil.isCellDateFormatted(row.getCell(5))) {
			       
			          bean.setDob(row.getCell(5).getDateCellValue());
			    }
				if (HSSFDateUtil.isCellDateFormatted(row.getCell(7))) {
				       
			          bean.setDoj(row.getCell(7).getDateCellValue());
			    }
				
				break;
			case HSSFCell.CELL_TYPE_STRING:
				//System.out.println(cell.getStringCellValue()+ "\t\t");
				if(cells==3){	
					bean.setName(cell.getStringCellValue());
					
						
				} 
				
				if (HSSFDateUtil.isCellDateFormatted(row.getCell(5))) {
				       
			          bean.setDob(row.getCell(5).getDateCellValue());
			    }
				if (HSSFDateUtil.isCellDateFormatted(row.getCell(7))) {
				       
			          bean.setDoj(row.getCell(7).getDateCellValue());
			    }
			
				
				break;
		
			
			}
			
			cells++;
		}
			
			
		//int i= new TempExcelUploadDao().getBankBranchbycode(bbbean.getBranch_code());
		//if(i==0){
			new TempExcelUploadDao().addMemberRegistration(bean);
		//}
			
	 }
	 fis.close();
	 
	
		
		}catch( Exception e){
			e.printStackTrace();
		}
		
	}

}

/*if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
    System.out.println ("Row No.: " + row.getRowNum ()+ " " +
        row.getCell(0).getNumericCellValue());

    if (HSSFDateUtil.isCellDateFormatted(row.getCell(0))) {
        System.out.println ("Row No.: " + row.getRowNum ()+ " " + 
            row.getCell(0).getDateCellValue());
    }
}*/
