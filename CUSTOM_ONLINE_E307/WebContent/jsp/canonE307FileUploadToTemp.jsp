<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>
<%@page import="com.canon.apps.servreq.util.CanonE307FileUploadDownloadAPIUtil"%>
<%@page language="java" %>

<html>
  <head>
<%
	String ctxPath = request.getContextPath();
%>
  <title> Attachments </title>
  <meta http-equiv="Content-Script-Type" content="text/javascript">
  <meta http-equiv="Content-Style-Type" content="text/css">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<%@	page import ="java.io.File" %>
<%@	page import ="java.io.IOException" %>
<%@	page import ="java.util.List" %> 
<%@ page import ="org.apache.commons.fileupload.FileItem" %>
<%@	page import ="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@	page import ="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@	page import ="org.apache.commons.io.FilenameUtils"%>
	<link rel="stylesheet" href="<%=ctxPath%>/e307/css/canon_e307_style.css" type="text/css">
	<link rel="stylesheet" href="<%=ctxPath%>/e307/css/styles.css" type="text/css">	

</head>
<script>
function fnsubmitUpload(){
	var url = 'canonE307FileUploadToTemp.jsp';
    $.post(url
            ,$("#upload").serialize()
            ,function(data){
			 $('#fileDlg').html("");
             $('#fileDlg').html(data);
            });
} 
</script>
	<body style="background:none">
	<%
		String strSource = request.getParameter("source");
		System.out.println("strSource: "+strSource);
	%>

	<form name="upload" id="upload" method="post" ENCTYPE="multipart/form-data" action="canonE307FileUploadToTemp.jsp">
		<input type="hidden" id="source" name="source" value="<%=strSource%>">
	<%  

	 CanonE307ServiceRequestDetailsDao detObj = new CanonE307ServiceRequestDetailsDao();
	 String UPLOAD_DIRECTORY = detObj.getUploadFilePath();
	 String strFileName="";
	 String strMsg="Please select file to upload";
	 String strRetVal="";
	 String strFileNameVal="";
	 int fileId = 0;

	if(ServletFileUpload.isMultipartContent(request)){ 
		try { 
			List multiparts = new ServletFileUpload( 
									 new DiskFileItemFactory()).parseRequest(request); 
			for(int i=0;i<multiparts.size();i++){ 
				FileItem item = (FileItem)multiparts.get(i);
				if(!item.isFormField()){ 
					String name = new File(item.getName()).getName();
					strFileName = FilenameUtils.getName(name);
					//System.out.println("strFileName1 : "+ strFileName);
					if(strFileName.indexOf(".pdf")>-1){
						if("".equals(strFileName) || "null".equals(strFileName)){
							//strMsg="Please select file to upload";
						}else{
							item.write( new File(UPLOAD_DIRECTORY + File.separator + strFileName)); 
							strRetVal="S";
							System.out.println(" strRetVal : "+strRetVal+" strSource : "+strSource);
						}
					}else{
						strMsg="Please upload only pdf files.";
					}
				}				
			}
		} catch (Exception ex) { 
		   System.out.println("File Upload Failed due to " + ex); 
		}  
	}
 	if(("S".equals(strRetVal))){
	%>
	<script type="text/javascript">
	 window.opener.uploadItemImage('<%=strFileName%>'); 
	 window.close();
	</script>
	<%
	} 
	%>
	<table width=60% align="center" >
	<tr><td>&nbsp;</td></tr>
	<tr><td style='font-size:13px'><b><%=strMsg %></b></td></tr>
	<tr><td>
	   <input type="file" name="file" /> 
     <!--  <input type="submit" value="Upload" /> -->
	</td>  </tr> 
	<tr><td>&nbsp;&nbsp;</td></tr>
	<tr><td align="center" style="border-style: none;">
<%-- 	 <input type="image" style="white-space: nowrap;text-indent: -9999px;" src="<%=ctxPath%>/common/images/upload.gif" name="image" value="Upload" border=0>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void window.close()" class="btn" style="white-space: nowrap;">Close</a> --%> 
	 <input type="submit" style="white-space: nowrap;height:27px;width:60px;" class="btn"  name="image" value="Upload" border=0>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn" value="Close" style="height:27px;width:60px" onclick="javascript:void window.close()"><!-- <a href="javascript:void window.close()" class="btn" style="white-space: nowrap;">Close</a>  --> 
    </td></tr> 
	</table>

	</form>
	</body>
</html>	