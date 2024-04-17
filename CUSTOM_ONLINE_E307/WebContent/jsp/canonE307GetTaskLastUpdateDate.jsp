<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>

<%@page import="java.util.*" %>
<%
	String strSrNum = request.getParameter("servReqNum");	
	String strLastUpdateDt = request.getParameter("lastUpdateDate");
	
	
	System.out.println("canonE307GetTaskLastUpdateDate SR Num: " + strSrNum+" strLastUpdateDt: "+strLastUpdateDt);
	String result ="";

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    CanonE307ServiceRequestDetailsDao objCount = new CanonE307ServiceRequestDetailsDao();
   
	result = objCount.getTsklstUpdFlag(strSrNum, strLastUpdateDt);

	buff.append(result);
	//System.out.println("Task last update date: "+ buff.toString());
       pw.println(buff.toString());
       pw.flush();  
%>