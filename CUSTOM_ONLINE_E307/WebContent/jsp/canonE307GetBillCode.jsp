<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqAPIUtil"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="java.util.*" %>
<%
	String strMachPk = request.getParameter("machPk");	
	String strCallTpCd = request.getParameter("callTpCd");
	String userId = request.getParameter("userId");
	String strCallType = request.getParameter("callType");
	
	
	System.out.println("Inside strMachPk : " + strMachPk+"strCallType: "+strCallType);

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    CanonE307ServiceReqAPIUtil objBlCd = new CanonE307ServiceReqAPIUtil();
    String[] billCdDt = objBlCd.getBillCode(strMachPk, "", "0", "", "", strCallTpCd, userId, strCallType);
      	if(billCdDt!=null && billCdDt.length>0){	
			buff.append(billCdDt[0]);
    	}
		System.out.println("Get BillCode: "+ buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>