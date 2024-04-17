<%-- 
 /*===========================================================================+
 |             ORACLE NAIO (INDIA)              BANGALORE , INDIA            |
 |                         All rights reserved.                              |
+===========================================================================+
 |  FILENAME                                                                 |
 |     canon_E193_csAttachmentsController.jsp                                |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |     Controller class for attachments functionality                        | 
 |                                                                           | 
 |  MODIFICATION HISTORY                                                     |
 |                                                                           |
 |       18-SEPTEMBER-05 Vijayakumar.B    1.0   Initial Create               |
 |                                                                           |
 |                                                                           |
 +==========================================================================*/
--%>
<!-- Java Imports -->
<%@ page import="java.sql.Blob"%>
<%@ page import="javax.servlet.ServletOutputStream"%>
<%@ page import="oracle.sql.BLOB"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="javax.servlet.ServletConfig"%>
<%@ page import="javax.servlet.ServletException"%>
<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.StringTokenizer"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.List"%>

<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@	page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@	page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@	page import="org.apache.commons.io.FilenameUtils"%>

<!-- Custom Imports -->
<%@ page import="com.canon.oracle.custapp.custinq.dao.Canon_E193_AttachmentUploadDAO"%>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AttachmentObj"%>
<%@ page import="com.canon.oracle.custapp.util.CanonUploadUtil"%>
<%@ page import="com.canon.oracle.custapp.util.CanonE193FileUploadDownloadAPIUtil"%>
<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp"%>
<%
	byte[] fileContent;
	byte[] bReturnValue;
	String strResult			= "";
	String strFileId			= "";
	int iFileId					= -1;
	Blob bResult				= null;
	byte[] buffer				= new byte[10* 1024]; 
	String strFileFormat		= "";
	String filename				= "";    
	String fileType				= "";  
	String strFileContentType	= "";
	String strFileName			= ""; 
	String CompFilePath			= ""; 
	String strTicketNumber		= "";
	String strDocumentId		= "";
	String strAttachJsp			= "canon_E193_csAttachmentsP.jsp";
	String strNewJsp			= "canon_E193_csUploadNew_Page.jsp"; // Called when click on the filenames on attachments jsp.
    String UPLOAD_DIRECTORY       = "/WebSphere/apps/s21/csawds/updownfiles/upload"; // I need to check it.
    boolean isFileUploaded = false;
    
    String message = "";
    
 
        /**
		 * START FILE UPLOAD ON SERVER.
		 */

     // upload settings
     String strValue				= request.getParameter("hidValue");
     int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
	 int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	 int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
     DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
     Date date = new Date();
	 //System.out.println(" Current Data Time~ " + dateFormat.format(date));
	 // Getting User Name.
	 String strUserName = CanonS21SessionValidate.getUserName();
		// File Upload in Server 
		
	boolean isSuccess = false;
 	System.out.println("strValue:"+strValue);
  if(strValue.equalsIgnoreCase("insert")) { 
  if (!ServletFileUpload.isMultipartContent(request)) {
	  System.out.println(" ~Stop here~ ");
      // if not, we stop here
      PrintWriter writer = response.getWriter();
      writer.println("Error: Form must has enctype=multipart/form-data.");
      writer.flush();
      return;
  }else{
	// configures upload settings
	  System.out.println(" ~Start from here~ ");
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // sets memory threshold - beyond which files are stored in disk
      factory.setSizeThreshold(MEMORY_THRESHOLD);
      // sets temporary location to store files
     // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File(UPLOAD_DIRECTORY));
      ServletFileUpload upload = new ServletFileUpload(factory);
      // sets maximum size of upload file
      //upload.setFileSizeMax(MAX_FILE_SIZE);
      // sets maximum size of request (include file + form data)
      //upload.setSizeMax(MAX_REQUEST_SIZE);
      

     //creates the directory if it does not exist
      File uploadDir = new File(UPLOAD_DIRECTORY);
    if (!uploadDir.exists()) {
         uploadDir.mkdir();
       }
      try {
     // parses the request's content to extract file data
    //  @SuppressWarnings("unchecked")
     //List<FileItem> formItems = upload.parseRequest(request);
     List<FileItem> formItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
      
      if (formItems != null && formItems.size() > 0) {
      	   // iterates over form's fields
          for (FileItem item : formItems) {
          	 // processes only fields that are not form fields
              if (!item.isFormField()) {
              	
                  String fileName = new File(item.getName()).getName();
                  System.out.println(" FileName:~  first" + fileName);
                  if(fileName!=null){
                	      			//For work around.
                			  ArrayList<String> tokenList = new ArrayList<String>();
                				int z= 0;
                				StringTokenizer st = new StringTokenizer(fileName, ".");
                				while(st.hasMoreTokens()){
                					tokenList.add((String)(st.nextToken()));
                				}
                				int tokenlength = tokenList.size();
                				System.out.println("Token Length~ " + tokenlength);
                				strFileName =  tokenList.get(tokenlength-2);
                				
                				String fileNameOnly = "";
                				if (strFileName != null && !strFileName.trim().equals("")) {
                					fileNameOnly = strFileName.substring(
                							strFileName.lastIndexOf("\\") + 1,
                							strFileName.length());
                				}
                				
                				//System.out.println("File Name --> " + strFileName + " File.separator " + File.separator + " fileNameOnly "+ fileNameOnly);
                			// Newly Added for Unique file name  
                			strFileName = fileNameOnly+"_"+dateFormat.format(date)+"_@"+strUserName+"."+tokenList.get(tokenlength-1);
                  }
                  
                 // String filePath = UPLOAD_DIRECTORY + File.separator + strFileName;
                  CompFilePath = UPLOAD_DIRECTORY + File.separator + strFileName;				
                  System.out.println(" FileName:~ " + strFileName);
                  File storeFile = new File(CompFilePath);
                  storeFile.setExecutable(true);
                  storeFile.setReadable(true);
                  storeFile.setWritable(true);
                  System.out.println(" ~StoreFile~ " +storeFile +" ~FilePath~ "+ CompFilePath);
	              // saves the file on disk
                  item.write(storeFile);
                  request.setAttribute("message",
                      "Upload has been done successfully!");
                  isSuccess = true;
              }
          }
      }
  } catch (Exception ex) {
	  System.err.println("There was an error: " + ex.getMessage());
	  ex.printStackTrace();
	  message = "Error occured in file upload.";
      request.setAttribute("message",
              "There was an error: " + ex.getMessage());
  }
  
  } 
}
   // FILE UPLOAD ON SERVER END.    
      
    // Get the values from request.
	//String strValue				= request.getParameter("hidValue");
	strFileId					= request.getParameter("fileId");
	strTicketNumber				= request.getParameter("ticketnumber");
	strDocumentId				= request.getParameter("documentId");
	
	// Check for the null values.
	strValue					= strValue			== null ? "0" : strValue;
	strFileId					= strFileId			== null ? "0" : strFileId;
	strTicketNumber				= strTicketNumber	== null ? "0" : strTicketNumber;
	strDocumentId				= strDocumentId		== null ? "0" : strDocumentId;
	strValue					= strValue.trim();
	iFileId						= Integer.parseInt(strFileId);

	
	CanonUploadUtil canonUtil	= new CanonUploadUtil();

	//intializing the customized java file
	 Canon_E193_AttachmentUploadDAO objFileUploadDAO = new Canon_E193_AttachmentUploadDAO();


	
	  
	  if(strValue.equalsIgnoreCase("insert"))
	  {	
		  File tempFile = null;
		  try{
			  tempFile = new File(CompFilePath);
		  
		  if(isSuccess && tempFile.exists()){
	//System.out.println("Inside canonE193AttachmentController.jsp");
	String strTicketNum = request.getParameter("ticket_number");
		//	String strUserName = CanonS21SessionValidate.getUserName();
	String strAcctNum = request.getParameter("invNum");
	//strFileName=request.getParameter("fileName");
	int fileId = 0; 
	System.out.println("Attachment tktId : " + strTicketNum+" strUserName : "+strUserName+" strSerNum : "+strAcctNum+" strFileName : "+strFileName);
	
  			int result=0;	
	String[] strRes = new String[2];

	//PrintWriter pw = response.getWriter();
		    StringBuffer buff = new StringBuffer();
		     CanonE193FileUploadDownloadAPIUtil objInvDAO = new CanonE193FileUploadDownloadAPIUtil();
		     	if(strTicketNum!=null && !"".equalsIgnoreCase(strTicketNum)){
		fileId = objInvDAO.uploadAttachment(strTicketNum, strAcctNum, strUserName, strFileName);
	}
		     	System.out.println("fileId after upload:"+fileId);
		     	String strResultMsg = "Failure";
		     	if (fileId>0)
		{
		     		isFileUploaded = true;
		     		System.out.println(" Uploaded Successfully ");
		  strResultMsg = "Uploaded Successfully ";
		}else
		{
			System.out.println(" Error While Uploading the File ");
		  	strResultMsg = " Error While Uploading the file "; 
		  	message = "Not able to upload the file, please try again.";
		}
		
		session.setAttribute("KEY",strResultMsg);
		//System.out.println("Forwarding to:"+strAttachJsp);
		//System.out.println(" Complete File Path: "+CompFilePath);
		// Delete The Temporary file from the server(/WebSphere/apps/s21/csawds/updownfiles/upload)
		//if(isFileUploaded){ // If it's true delete the Temporary file from Temporary folder on server.
		boolean boolDelete	= objFileUploadDAO.deleteAttachedFileFromTempFolder(CompFilePath);
		String strDelete	= "";
		if(boolDelete)
		{
	     strDelete = "Successfully Deleted";
		}else
		{
	     strDelete = "File is not deleted";
		}// end of if
		System.out.println(" File Is Deleted: " + strDelete);
		}
		if(!isFileUploaded){
			message = "Not able to upload the file, please try again.";
		}
		  }catch(Exception exception){
			  exception.printStackTrace();
		  }
%>
<input type="hidden" name="ticket_number" value="<%=strTicketNumber%>">
<jsp:forward page="<%=strAttachJsp%>">
	<jsp:param name="message" value="<%=message%>" />
</jsp:forward>
<%
	}else if (strValue.equalsIgnoreCase("select")) // end of insert if and start of select if
	  {
		  
		  
		Canon_E193_AttachmentObj objValObj  = null;
		ArrayList alDocList					= null;
		//System.out.println("prior to calling getDocuments:"+iFileId);
		String strTicketNum = request.getParameter("ticket_number");
		int iTicketNum=Integer.parseInt(strTicketNum);
		System.out.println("prior to calling getDocuments ticket number is:"+iTicketNum);
		//alDocList							= objFileUploadDAO.getDocuments(iFileId);
		alDocList							= objFileUploadDAO.getFilesFromDB(iTicketNum);
		int iDocumentSize					= 0;	

		if(alDocList != null)
		{
	iDocumentSize = alDocList.size();
		}// end of if
		
		for(int iValue = 0; iValue < iDocumentSize; iValue++)
		{
	objValObj = new Canon_E193_AttachmentObj();
	objValObj = (Canon_E193_AttachmentObj)alDocList.get(iValue);
	//System.out.println("objValObj.getFileId():"+objValObj.getFileId());
	if(objValObj.getFileId()==iFileId)
	{
            bResult				= objValObj.getDownloadData();
	strFileContentType	= objValObj.getFileContentType();
	strFileName			= objValObj.getFileName();
	System.out.println(" File is Seleceted, Selected File Name is: " + strFileName);
	}
		}// end of for loop
		//Split the strFileName String because we need only FileName not entire path;
		//System.out.println("StrFileName before put in session~ "+ strFileName);		
		session.setAttribute("bResult",bResult);
		session.setAttribute("FileContentType",strFileContentType);
		session.setAttribute("FileName",strFileName);
%>
<input type="hidden" name="ticket_number" id="ticket_number"
	value="<%=strTicketNumber%>">
<jsp:forward page="<%=strNewJsp%>"></jsp:forward>
<%
	}else if (strValue.equalsIgnoreCase("delete")) // end of select if and start of delete if
	 {
		// To delete the file manually 
        String prodPack = request.getParameter("attFileName");
        String prodPack1 = request.getParameter("attFileName1");
        String isCSAFile = request.getParameter("isCSAFile");
        String ticketNumber = request.getParameter("ticket_number");
        int id = Integer.parseInt(request.getParameter("id"));
	    //System.out.println(" radio1 = " + prodPack + " radio1 = "+ prodPack1);
	    
		String strDelete	= "";
		System.out.println(" Call Delete method With This strDocumentId~ " + strDocumentId + "Seleceted File Name: "+strFileName 
					+ " isCSAFile " + isCSAFile + " ticketNumber " + ticketNumber + " id " + id);
		boolean boolDelete = false;
		if(isCSAFile != null && isCSAFile.equalsIgnoreCase("Y"))
		{
			boolDelete	= objFileUploadDAO.deleteCSAFile(ticketNumber, strDocumentId, id);
		}
		else 
		{
			CanonE193FileUploadDownloadAPIUtil objInvDAO = new CanonE193FileUploadDownloadAPIUtil();
			boolDelete = objInvDAO.detachAttachment(strDocumentId);
		}
		

	 	if(boolDelete)
		{
	    	strDelete = "Successfully Deleted";
		}else
		{
	    	strDelete = "File is not deleted";
		}// end of if
		System.out.println(" File Is Deleted: " + strDelete);
		session.setAttribute("KEY",strDelete); 
%>
<input type="hidden" name="ticket_number" value="<%=strTicketNumber%>">
<jsp:forward page="<%=strAttachJsp%>"></jsp:forward>
<%
	}// end of delete if

    }catch(Exception eExp) {
		response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
	} // end of try catch
%>

