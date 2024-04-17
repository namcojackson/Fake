<%-- 
 /*==========================================================================+
 |             ORACLE NAIO (INDIA)              BANGALORE , INDIA            |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  FILENAME                                                                 |
 |     canon_E193_csAttachmentsP.jsp                                         |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |     Customer can upload file.                                             | 
 |                                                                           | 
 |  MODIFICATION HISTORY                                                     |
 |                                                                           |
 |       18-SEPTEMBER-05 Vijayakumar.B    1.0   Initial Create               |
 |                                                                           |
 |                                                                           |
 +==========================================================================*/
--%>
<!-- Java Imports -->
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.StringTokenizer"%>
<!-- Custom Imports -->
<%@ page import="com.canon.oracle.custapp.custinq.dao.Canon_E193_AttachmentUploadDAO"%>
<%@ page import="com.canon.oracle.custapp.util.CanonE193FileUploadDownloadAPIUtil"%>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AttachmentObj"%>
<%@	page import ="java.io.File" %>
<%@	page import ="java.io.IOException" %>
<%@	page import ="java.util.List" %> 
<%@ page import ="org.apache.commons.fileupload.FileItem" %>
<%@	page import ="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@	page import ="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@	page import ="org.apache.commons.io.FilenameUtils"%>

<% try {  %>

<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
  int iTicketNumber									= 0;
  Canon_E193_AttachmentUploadDAO objFileUploadDAO	= new Canon_E193_AttachmentUploadDAO();
  String strTicketNumber							= "";
  ArrayList alList									= null;
  String strMessage									= "";

  // Get the ticket_number from request.	
  strTicketNumber					= request.getParameter("ticket_number");
  String strInvNum=request.getParameter("invNum")==null?"":request.getParameter("invNum");
  String strSelAcctNum=request.getParameter("selAcctNum")==null?"":request.getParameter("selAcctNum");
  strTicketNumber = strTicketNumber == null ? "0" : strTicketNumber;	
  strMessage = request.getParameter("message");
  
  System.out.println("ERROR message :" + strMessage);

  iTicketNumber						=	Integer.parseInt(strTicketNumber);
  
  System.out.println("TicketNumber:~ "+strTicketNumber);
 
  alList							= objFileUploadDAO.getFilesFromDB(iTicketNumber);
  int iRecordSize					= 0;
  String strRadioCheck				= "";
  String strFileName				= "";
  int iFileId						= 0;
  int iDocumentId					= 0;	// Used for delete Functionality.
  
  if (alList != null)
  {
    iRecordSize = alList.size();
  }
   String loginUser=CanonS21SessionValidate.getUserName();
  ArrayList<CanonE193AttFileRec> s21AttachedFileList=objFileUploadDAO.getFilesFromS21DB(iTicketNumber);
  
  ArrayList<CanonE193AttFileRec> csaAttachedFileList=objFileUploadDAO.getCSAFilesFromS21DB(iTicketNumber);
  int is21AttachedFileListSize= 0;
  if(s21AttachedFileList!=null)
	  is21AttachedFileListSize=s21AttachedFileList.size();
  System.out.println("is21AttachedFileListSize~ " + is21AttachedFileListSize);

%>
<html>
  <head>
<%
	String ctxPath = request.getContextPath();
%>
<title> Attachments </title>
 
  
  <link href="<%=ctxPath%>/common/jquery/jquery-ui.css" rel='stylesheet' type='text/css' />
  <%-- <script src="<%=ctxPath%>/common/jquery/jquery-1.10.2.min.js" type='text/javascript'></script>   		
  <script src="<%=ctxPath%>/common/jquery/jquery-ui-1.10.3.custom.min.js" type='text/javascript'></script> --%>
  <script src="<%=ctxPath%>/common/jquery/jquery-3.5.1.min.js" type='text/javascript'></script>
  <script src="<%=ctxPath%>/common/jquery/jquery-ui.min.js" type='text/javascript'></script>
  <script src="<%=ctxPath%>/e193/js/canon_E193PrintPdf.js" type='text/javascript'></script>
    <script src='<%=ctxPath%>/common/jquery/jquery.blockUI2.js' type='text/javascript'></script>	
	<script src='<%=ctxPath%>/common/jquery/jquery.timepicker.js' type='text/javascript' ></script>
    <link href='<%=ctxPath%>/common/jquery/jquery.timepicker.css' rel="stylesheet" type="text/css">
    <script type="text/javascript" src='<%=ctxPath%>/common/jquery/autoNumeric-1.6.2.js'></script>	
    <script src='<%=ctxPath%>/common/jquery/jquery-ui-timepicker-addon.js' type='text/javascript'></script>
    <script src='<%=ctxPath%>/common/jquery/ui.core.js' type='text/javascript'></script>
	<link rel="stylesheet" href="<%=ctxPath%>/e193/css/styles.css" type="text/css">
	<link rel="stylesheet" href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css" type="text/css">
	<SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csHomeVald.js"></script>
<SCRIPT Lanugage="JavaScript">
  // When close button is pressed the fncClose() function is called.
  function fncClose() {
	window.close();
  }
  // When Attach button is pressed the fncUpload() function is called
  function fncUpload(strTicketNumber) {
	  $("#progress").show();
	  var fileName = $('input[type=file]').val();	  
    var filePath = document.frmUpload.fileUpload.value;
	if(filePath == null || (filePath != null && $.trim(filePath) == "")) {
		alert("Please select a file to upload");
		return false;
	}else if(filePath.indexOf("#") > 0){
		alert("File/Folder name should not contain #. Please choose a valid File name.");
		return false;
	}
	
	// Checking for the file extension.
	fileExtn   = filePath.substring(filePath.length - 3);
	if(fileExtn == 'log') {
		alert("Attaching log files are not allowed");
		return false;
	}else if(fileExtn == 'zip') {
		alert("Attaching zip files are not allowed");
		return false; 	
	}	
	var invNum = $('#invNum').val();
	var selAcctNum=$("#selAcctNum").val();       	      	
   	
	document.frmUpload.action = "canon_E193_csAttachmentsController.jsp?hidValue=insert&ticket_number="+strTicketNumber+"&fileName="+fileName+"&invNum="+invNum;
    document.frmUpload.submit();
	return true;
  }

  // When delete button is pressed the fncDelete() function is called.	
  function fncDelete(thisform) {
	myOption = -1;
	var len = 0;
	var isCSAFile = "";
	var fileIdentifier = "";
	var id = "";
	if(thisform.chkUpload != null) len = thisform.chkUpload.length;
	if(len > 0) {
		for (i=len-1; i > -1; i--) {
			if (thisform.chkUpload[i].checked) {
				myOption = i;
			}	
		}
	}else if(thisform.chkUpload != null && thisform.chkUpload.checked) myOption = -2;
	if (myOption == -1) {
		alert("Please select a radio button for delete");
		return false;
	}
	if(confirm("Are you sure you want to delete this file?")) {
		if(myOption == -2) fileIdentifier = thisform.chkUpload.value;
		else fileIdentifier = thisform.chkUpload[myOption].value;
		ticket_number	= document.frmUpload.ticket_number.value;
		//alert(fileIdentifier);
		document_id = fileIdentifier.split("-")[0];
		isCSAFile = fileIdentifier.split("-")[1];
		id = fileIdentifier.split("-")[2]
		var invNum = $('#invNum').val();
		
		var AttFileName = $('input[type=radio]:checked').val();
		var AttFileName1 = $("#chkUpload input[type='radio']:checked").val();
		
		//alert(document_id + " " + ticket_number + " " + AttFileName + " "+ AttFileName1 + " " + isCSAFile + " " + invNum + " " + id);
	  	document.frmUpload.action = "canon_E193_csAttachmentsController.jsp?hidValue=delete&documentId="+document_id
	  			+"&ticket_number="+ticket_number+"&attFileName="+AttFileName+"&attFileName1="+AttFileName1
	  			+"&isCSAFile="+isCSAFile+"&invNum="+invNum+"&id="+id;
		document.frmUpload.submit();
	}
  }

  // When the file name is clicked the fncSelect() function is called to open the file.		
  function fncSelect(jNumber) {
	 var ticket_number	= document.frmUpload.ticket_number.value;
    document.frmUpload.action = "canon_E193_csAttachmentsController.jsp?hidValue=select&fileId="+jNumber+"&ticket_number="+ticket_number;
    document.frmUpload.submit();
  }
  
  function fnDownAttach(fileName){
		var url="canonE193DownloadAttachment.jsp?fileName="+encodeURIComponent(fileName);
	    var l_newWindow = window.open(url);
	    l_newWindow.focus(); 
		//$("#frmSummary").attr("action",url);	 
		//$('#frmSummary').submit();	
	}
</SCRIPT>
</HEAD>
<body>
<form name="frmUpload" id="frmUpload" method="post" ENCTYPE="multipart/form-data">
	<input type="hidden" name="fileId" id="fileId">
	<input type="hidden" name="hidValue" id="hidValue">
	<input type="hidden" name="ticket_number" id="ticket_number" value="<%=strTicketNumber%>">
	<input type="hidden" name="invNum" id="invNum" value="<%=strInvNum%>">
	<input type="hidden" name="selAcctNum" id="selAcctNum" value="<%=strSelAcctNum%>">
	<table cellspacing="0" cellpadding="2"  class="request-service">
		<tr>
			<td colspan="2" height="10"></td>
		</tr>
		<tr>
			<td colspan="2"><font class="promptReadOnly">Attachments</font></td>
		</tr>
		 <tr>
		 	<td colspan="2" height="10"></td>
		</tr>
		<TR >
      		<TD><font class="promptReadOnly">File:</font></TD>
			<TD>
				<input type="file" name="fileUpload" id="fileUpload" size="25" multiple="multiple">
			</TD>
		</tr>
		 <tr >
			<td width="10"></td>
			<TD align="left" >
				<input type="button" value="Attach" class="btn_red" onClick="javascript:fncUpload('<%=strTicketNumber%>')">
				<p id="progress" style='color:blue;display:none'> Upload in Progress...</p>
			</TD>
		 </tr>
		 <% if(strMessage != null && strMessage != "null" &&  !"".equalsIgnoreCase(strMessage)) {%>
  		 <tr >
			<td colspan="2">
				<div style="padding-bottom: 5px; color: red; font-weight: bold;">
					<p > <%=strMessage %></p>
		   		</div>
			</td>
  		</tr>
  		<%} %>
  		 <tr><td colspan="2" height="10"><hr align="left" width="100%"></td></tr>
  		 <tr >
			<td colspan="2">
				<font class="promptReadOnly" color="#FF0000"><b><%=strAttachmentsPM1%><br><%=strAttachmentsPM2%></b></font>
			</td>
  		</tr>
  		<tr><td colspan="2" height="10"><hr align="left" width="100%"></td></tr>
  	</table>
  	<table cellspacing="0" cellpadding="2" class="request-service">
		<tr >
			<td colspan="2"><font class="promptReadOnly"> Attached Files </font></td>
		</tr>
		
		<%
		
			/* for (int iCounter = 0; iCounter < is21AttachedFileListSize; iCounter++) {
				if(iCounter == 0) {
					strRadioCheck = "checked";
				}else{
					strRadioCheck = "";
				} */
				int iCounter1 = 0;
				CanonE193FileUploadDownloadAPIUtil canonE193FileUploadDownloadAPIUtil=new CanonE193FileUploadDownloadAPIUtil();
				if(s21AttachedFileList!=null && s21AttachedFileList.size()>0){
					for(CanonE193AttFileRec attObj: s21AttachedFileList){
						if(iCounter1==0){
							strRadioCheck = "checked";
							iCounter1++;
						}else{
							strRadioCheck = "";
						}
						//System.out.println("file Id : "+attObj.getFileId());
						if(attObj.getFileId()>0){
							// For Temporary Purpose I have commmented below line.
							//String fileName1="";
							String fileName = canonE193FileUploadDownloadAPIUtil.downLoadAttachment(strTicketNumber, attObj.getStrBusinessId(), loginUser, attObj.getFileId());
							//System.out.println(" DownloadFile Name~ "+ fileName);
							%>
							<tr>
							<td width="1%"><input type="radio" name="chkUpload" value="<%=attObj.getFileId()%>-N-0"></td> <!--  -N to be added after document Id -->
								<!--  <td><a style="text-decoration:underline; color:blue"  href="javascript:void fnDownAttach('')"><></a></td> -->
								<td><a style="text-decoration:underline; color:blue" href="#"  onclick="fnDownAttach('<%=fileName %>')"><%=attObj.getStrfileName()%></a></td>
							</tr>
							<%
						}
					}
			   }
				
			  if(csaAttachedFileList != null && csaAttachedFileList.size() > 0)
			  {
					for(CanonE193AttFileRec attObj: csaAttachedFileList){
						if(iCounter1==0){
							strRadioCheck = "checked";
							iCounter1++;
						}else{
							strRadioCheck = "";
						}
						//System.out.println("file Id : "+attObj.getFileId());
						if(attObj.getFileId()>0){
							
							%>
							<tr>
							<td width="1%"><input type="radio" name="chkUpload" value="<%=attObj.getFileId()%>-Y-<%=attObj.getId()%>"></td>
								<td><a style="text-decoration:underline; color:blue" href="#"  onclick="fnDownAttach('<%=attObj.getStrFileNameWithPath() %>')"><%=attObj.getStrfileName()%></a></td>
							</tr>
							<%
						}
					}
				}
										
			%>							
			<%-- <% 
			}
			%> --%>   		
		<%
			for (int iCounter = 0; iCounter < iRecordSize; iCounter++) {
				if(iCounter == 0) {
					strRadioCheck = "checked";
				}else{
					strRadioCheck = "";
				}
				Canon_E193_AttachmentObj objValueObject  = (Canon_E193_AttachmentObj)alList.get(iCounter);
				strFileName = objValueObject.getFileName();
				//System.out.println(" Before Delete Functionality Complete path of StrFileName~ " + strFileName);
				//For Work around.
				//strFileName ="CUST SVC 001-E193-FD-CUSTOMER CARE_V2.docx";

     			/*  ArrayList<String> tokenList = new ArrayList<String>();
					int z= 0;
					StringTokenizer st = new StringTokenizer(strFileName, "\'\\'");
					while(st.hasMoreTokens()){
						tokenList.add((String)(st.nextToken()));
					}
					int length = tokenList.size();
					strFileName =  tokenList.get(length-1);
				System.out.println(" StrFileName~ " + strFileName); */
				strFileName = strFileName == null ? "0" : strFileName;
	
				iFileId	  = objValueObject.getFileId();
				iFileId	  = iFileId == -1 ? 0 : iFileId;
	
				iDocumentId = objValueObject.getDocumentId();
				iDocumentId = iDocumentId == -1 ? 0 : iDocumentId;
%>
				<tr><td colspan="2" height="10"></td></tr>
				<tr>
					<td width="1%"><input type="radio" name="chkUpload" value="<%=iDocumentId%>"></td> <!--  -N to be added after document Id -->
					<td><a class="btn_red" href="javascript:fncSelect('<%=iFileId%>')"><%=strFileName%></a></td>
				</tr>
<% 
			}
%>
			<tr><td colspan="2" height="10"></td></tr>
	  		<tr>
				<td width="1%">
					<input type="button" value="Delete" class="btn_red" onClick="javascript:fncDelete(frmUpload); return false;">
				</td>
				<td>
   					<input TYPE="button" value="Close" class="btn_red" onClick="javascript:fncClose()">
				</td>
	  		</tr>
      		<tr>
        		<td colspan="2" height="10"></td>
      		</tr> 
   		</TABLE>
   		
   		
   		
	</form>
</body>
<%
}
catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>

