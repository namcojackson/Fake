<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil"%>
<%@page import="java.util.*" %>
<%
	String strFsrNum = request.getParameter("fsrNum");	
	String srFvstNum = request.getParameter("visitNum");
	String strTskNum = request.getParameter("taskNum");
	
	System.out.println("Get ETA fsrNum : " + strFsrNum);
	int result=0;	
	String[] strRes = new String[2];

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    CanonE307ServiceReqSumryAPIUtil objInvDAO = new CanonE307ServiceReqSumryAPIUtil();
	if(strFsrNum!=null && strFsrNum!=""){
		strRes = objInvDAO.servReqGetEta(strFsrNum, srFvstNum, strTskNum);
	}
	if(strRes!=null && strRes.length>0){
		buff.append(strRes[0]+":"+strRes[1]);
	}

	//System.out.println("Get Eta: "+ buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>