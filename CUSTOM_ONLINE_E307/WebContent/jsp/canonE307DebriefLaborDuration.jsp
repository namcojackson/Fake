<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@ page import="com.canon.apps.servreq.dao.CanonE307DebriefDetailsDAO" %>  
<%@page import="java.util.*" %>
<%
	String strLaborStartDate = request.getParameter("laborStartDate");	
	String strLaborStartTime = request.getParameter("laborStartTime");	
	String strLaborEndDate = request.getParameter("laborEndDate");	
	String strLaborEndTime = request.getParameter("laborEndTime");	
	String strtAmPm = request.getParameter("strtAmPm");
	String endAmPm = request.getParameter("endAmPm");
	CanonE307DebriefDetailsDAO durObj = new CanonE307DebriefDetailsDAO();
	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    
	double duration = durObj.getLbrDuration(strLaborStartDate, strLaborStartTime, strLaborEndDate, strLaborEndTime, strtAmPm, endAmPm);
		buff.append(duration);
	  System.out.println("buff : " + buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>
