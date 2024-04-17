<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="com.canon.apps.servreq.util.CanonE307FileUploadDownloadAPIUtil"%>
<%@page import="java.util.*" %>
<%
	String strFsrNum = request.getParameter("fsrNum");	
	String strUserName = request.getParameter("userName");
	String strSerNum = request.getParameter("serial");
	String strFileName=request.getParameter("fileName");
	int fileId = 0; 
	System.out.println("Attachment fsrNum : " + strFsrNum+" strUserName : "+strUserName+" strSerNum : "+strSerNum+" strFileName : "+strFileName);
	int result=0;	
	String[] strRes = new String[2];

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    CanonE307FileUploadDownloadAPIUtil objInvDAO = new CanonE307FileUploadDownloadAPIUtil();
	if(strFsrNum!=null && strFsrNum!=""){
		strRes = objInvDAO.uploadAttachment(strFsrNum, strSerNum, strUserName, strFileName);
		buff.append(strRes[0]+"|"+strRes[1]);
	}

	System.out.println("file upload: "+ buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>