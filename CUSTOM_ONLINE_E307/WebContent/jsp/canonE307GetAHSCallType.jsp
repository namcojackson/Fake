<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceReqCreateDao"%>
<%@page import="java.util.*" %>
<%
	String strSrNum = request.getParameter("serialNum");	
	String strModel = request.getParameter("model");
	
	//System.out.println("Inside strSrNum : " + strSrNum+" strModel : "+strModel);
	String[] retVal = new String[2];	

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    CanonE307ServiceReqCreateDao objCount = new CanonE307ServiceReqCreateDao();
    retVal = objCount.getAHCallType(strSrNum, strModel);
    if(retVal!=null & retVal.length>0){
		buff.append(retVal[0]).append("|").append(retVal[1]);
    }
		//System.out.println("Get Call Info: "+ buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>