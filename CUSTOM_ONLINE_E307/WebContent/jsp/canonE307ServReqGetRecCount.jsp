<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestSearchDao"%>
<%@page import="java.util.*" %>
<%
	String strSerNum = request.getParameter("serialTagNumber");	
	String stsSolNm = request.getParameter("solutionName");
	String strAcctNum = request.getParameter("accountNumber");
	String strAddr = request.getParameter("custAddress");	
	String strCustName = request.getParameter("customerName");	
	String strCity = request.getParameter("custCity");	
	String strState = request.getParameter("custState");	
	String strPhoneNum = request.getParameter("custPhoneNumber");	
	String strPostCd = request.getParameter("custPostalCode");	
	
	System.out.println("Rec Count fsrNum : " + strSerNum+" stsSolNm : "+stsSolNm);
	int result=0;	

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    CanonE307ServiceRequestSearchDao objCount = new CanonE307ServiceRequestSearchDao();
    String[] strRes = objCount.getRecCount(strSerNum, stsSolNm, strAcctNum, strCustName, strAddr, strCity, strState, strPostCd, strPhoneNum);
		if(strRes[0]!=null && strRes[0]!=""){
			System.out.println("strRes[0]: "+ strRes[0]);
			//String[] eolInfo = objCount.getEOLInfo(strRes[0]);
			buff.append(strRes[0]+"^"+strRes[1]+"^"+strRes[2]+"^"+strRes[3]+"^"+strRes[4]+"^"+strRes[5]+"^"+strRes[6]);
		}else{
			buff.append("INFO");
		}
		// System.out.println("Get Count: "+ buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>