<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqAPIUtil"%>
<%@page import="java.util.*" %>
<%
	String strBillToCustNum = request.getParameter("billToCustNum");	
	
	System.out.println("Inside poReqObj : " + strBillToCustNum);
	String retVal ="";

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    CanonE307ServiceReqAPIUtil poReqObj = new CanonE307ServiceReqAPIUtil();
    retVal = poReqObj.getPOReqFlg(strBillToCustNum,"");
    if(retVal!=null & retVal.length()>0){
		buff.append(retVal);
    }else{
    	buff.append("N");
    }
		System.out.println("Get PO Req Flag: "+ buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>