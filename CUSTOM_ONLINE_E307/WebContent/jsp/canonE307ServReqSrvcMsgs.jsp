<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestSearchDao"%>
<%@page import="java.util.*" %>
<%
	String strSerNum = request.getParameter("serialTagNumber");	
	String strMachPk = request.getParameter("machPk"); 
	
	System.out.println("srvcMsgs fsrNum : " + strSerNum);
	int result=0;	

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    CanonE307ServiceRequestSearchDao objCount = new CanonE307ServiceRequestSearchDao();
    String strRes = objCount.getServiceMsgs(strSerNum, strMachPk);
		if(strRes!=null && strRes!=""){
			buff.append(strRes);
		}else{
			buff.append("");
		}
		System.out.println("Get srvcMsgs: "+ buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>