<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestSearchDao"%>
<%@page import="java.util.*" %>
<%
	String strSrNum = request.getParameter("serialNumber");	
	String strUserId = request.getParameter("userId");
	String svcMachMstrPk = request.getParameter("machPk");

	
	System.out.println("UGW Lockout serialNumber : " + strSrNum+" strUserId: "+strUserId+"svcMachMstrPk: "+svcMachMstrPk);
	String[] result=new String[4];	
//	String[] eolDtl = new String[3];

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    CanonE307ServiceRequestSearchDao obj= new CanonE307ServiceRequestSearchDao();
    	//eolDtl = obj.getEOLInfo(strSrNum);
    	String eolFlg = obj.geEOLFlg(strSrNum, svcMachMstrPk);
		result[0] = obj.getUGWLockoutInfo(strSrNum, strUserId);
		result[1] = obj.getServiceMsgs(strSrNum, svcMachMstrPk);
		result[2] = eolFlg;
		result[3] = obj.geHardHoldFlg(strSrNum, svcMachMstrPk);
		buff.append(result[0]+"*"+result[1]+"*"+result[2]+"*"+result[3]);
		System.out.println("UGW result: "+ buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>