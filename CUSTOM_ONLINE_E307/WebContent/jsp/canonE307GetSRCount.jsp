<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestSearchDao"%>
<%@page import="java.util.*" %>
<%
	String strSrNum = request.getParameter("servRqstNumber");	
	String stsTskNum = request.getParameter("taskNumber");
	String strCreatedBy = request.getParameter("createdBy");
	String strCreationDt = request.getParameter("creationDt");
	String strSrSts =  request.getParameter("srStatus");
	String strTskSts = request.getParameter("taskSts");
	String strTaskType= request.getParameter("taskType");
	
	System.out.println("Inside fsrNum : " + strSrNum+" strSrNum : "+strSrNum+" strCreatedBy: "+strCreatedBy+"strCreationDt: "+strCreationDt+" strSrSts: "+strSrSts+" strTskSts: "+strTskSts);
	int result=0;	

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    
    CanonE307ServiceRequestSearchDao objCount = new CanonE307ServiceRequestSearchDao();
		result = objCount.getSrCount(strSrNum, stsTskNum, strCreatedBy, strCreationDt, strSrSts, strTskSts, strTaskType);
		buff.append(result);
		//System.out.println("Get SR Count: "+ buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>