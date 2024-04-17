<%-- 
 /*===========================================================================+
 |             ORACLE NAIO (INDIA)              BANGALORE , INDIA            |
 |                         All rights reserved.                              |
+===========================================================================+
 |  FILENAME                                                                 |
 |     canon_E193_csUploadNew_Page.jsp		                                 |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |     Used to open the files, when clicked on the file names in attachmens  |
 |	   Jsp page.															 |		
 |                                                                           | 
 |  MODIFICATION HISTORY                                                     |
 |                                                                           |
 |       18-SEPTEMBER-05 Vijayakumar.B    1.0   Initial Create               |
 |                                                                           |
 |                                                                           |
 +==========================================================================*/
--%>
<!-- Java Imports -->
<%@ page import="java.sql.Blob" %>
<%@ page import="java.io.*" %>

<% try {  %>

<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
	Blob bResult		   = null;
	Object objBlobResult   = session.getAttribute("bResult");
	Object objFileName     = session.getAttribute("FileName");
	Object objFileContType = session.getAttribute("FileContentType");
	String strFileName	   = "";
	String strFileContType = "";
	byte[] buffer		   = new byte[1024];

	if (objFileName != null) {
  		strFileName = (String)objFileName;
	}
	if (objFileContType != null) {
  		strFileContType = (String)objFileContType;
	}
	if (objBlobResult != null) {
  		bResult = (Blob)objBlobResult;
	}

	InputStream in       = bResult.getBinaryStream(); 
	int length           = (int) bResult.length(); 

	response.setContentType(strFileContType);
	response.setHeader("Content-length", String.valueOf(length)); 
	response.setHeader("cache-control", "must-revalidate");        
	response.setHeader("Content-Disposition","attachment; filename="+strFileName);

	while ((length = in.read(buffer)) != -1) { 
  		response.getOutputStream().write(buffer, 0, length); 
  		response.getOutputStream().flush(); 
	}

}catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}

%>