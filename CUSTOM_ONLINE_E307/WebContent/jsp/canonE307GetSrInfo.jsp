<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>

<%@page import="java.util.*" %>
<%
	String strSrNum = request.getParameter("servReqNum");	
	String strTskNum = request.getParameter("taskNum");
	String cntryCd = request.getParameter("cntryCd");
	String postalCd = request.getParameter("postalCd");
	
	System.out.println("Inside srinfo : " + strSrNum);
	String[] result=new String[7];

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    CanonE307ServiceRequestDetailsDao objCount = new CanonE307ServiceRequestDetailsDao();
    CanonE307ServiceReqSumryAPIUtil utilObj = new CanonE307ServiceReqSumryAPIUtil();
	result = objCount.getTskDtls(strSrNum, strTskNum);
	String estmtFrom ="";
	String estmtTo ="";
	String scheduleStrt="";
	
	//System.out.println("estmtFrom : "+result[4]+" estmtTo : "+result[5]+" scheduleStrt : "+result[6]);
	String earlyStart = utilObj.getTmZone(postalCd, cntryCd, result[2]);
	String lateStart = utilObj.getTmZone(postalCd, cntryCd, result[3]);
	
/*	if("".equals(result[4]) || "null".equals(result[4])){
		// Do Nothing
	}else{
		estmtFrom = utilObj.getTmZone(postalCd, cntryCd, result[4]);
	}
	
	if("".equals(result[5]) || "null".equals(result[5])){
		// Do Nothing
	}else{
		estmtTo = utilObj.getTmZone(postalCd, cntryCd, result[5]);
	} */
	if("".equals(result[6]) || "null".equals(result[6])){
		// Do Nothing
	}else{
		scheduleStrt = utilObj.getTmZone(postalCd, cntryCd, result[6]);
	}


	buff.append(result[0]).append("|");
	buff.append(result[1]).append("|");
	buff.append(earlyStart).append("|");
	buff.append(lateStart).append("|");
	buff.append(estmtFrom).append("|");
	buff.append(estmtTo).append("|");
	buff.append(scheduleStrt);
	//System.out.println("Get srinfo: "+ buff.toString());
       pw.println(buff.toString());
       pw.flush();  
%>