<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestSearchDao"%>
<%@page import="java.util.*" %>
<%
	String strSrNum = request.getParameter("serialNumber");	
	String strUserId = request.getParameter("userId");

	
	System.out.println("UGW Lockout serialNumber : " + strSrNum+" strUserId: "+strUserId);
	String result="";	
	String[] eolDtl = new String[3];

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    CanonE307ServiceRequestSearchDao obj= new CanonE307ServiceRequestSearchDao();
    	//eolDtl = obj.getEOLInfo(strSrNum);
		result = obj.getUGWLockoutInfo(strSrNum, strUserId);
		buff.append(result);
		System.out.println("UGW result: "+ buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>