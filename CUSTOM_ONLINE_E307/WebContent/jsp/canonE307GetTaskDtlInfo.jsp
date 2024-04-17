<%@page import="com.canon.apps.servreq.beans.CanonE307TaskDtlInfoRec"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>

<%@page import="java.util.*" %>
<%
try{
	String strSrNum = request.getParameter("servReqNum");	
	String strlastUpdateDate = request.getParameter("lastUpdateDate");

	
	System.out.println("Inside canonE307GetTaskDtlInfo : " + strSrNum+" strlastUpdateDate: "+strlastUpdateDate);


	StringBuffer buff = new StringBuffer("");
    CanonE307ServiceRequestDetailsDao objCount = new CanonE307ServiceRequestDetailsDao();

	String strRes = objCount.getTaskDtlInfo(strSrNum, strlastUpdateDate);

	System.out.println("canonE307GetTaskDtlInfo strRes: "+ strRes);
	
	response.setContentType("application/json");
	if(strRes!=null && strRes.trim().length()>0) {
		 strRes=strRes.trim();
		 response.getWriter().write(strRes);
	}else{
		response.getWriter().write("{ \"data\" : []}");
	}
}catch(Exception e){
	System.out.println("Inside canonE307GetTaskDtlInfo Exception");
	response.setContentType("application/json");
	response.getWriter().write("{ \"data\" : []}");
  }
 
%>