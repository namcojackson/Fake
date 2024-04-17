<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@ page import="com.canon.apps.servreq.dao.CanonE307ChargesDetailsDAO" %>
<%@ page import="com.canon.apps.servreq.beans.CanonE307ServReqChrgTrnsactDtlsRec" %>    
<%@page import="java.util.*" %>
<%
	String strFsr = request.getParameter("serReq");	
	String strTskNum = request.getParameter("tskNum");	
	String strBillTpe = request.getParameter("billTpe");	
	
	CanonE307ChargesDetailsDAO durObj = new CanonE307ChargesDetailsDAO();
	CanonE307ServReqChrgTrnsactDtlsRec objBean = new CanonE307ServReqChrgTrnsactDtlsRec();
	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    
	Object[] obj = durObj.getTransactionDtls(strFsr, strTskNum, strBillTpe);
	if(obj!=null){
		 objBean =(CanonE307ServReqChrgTrnsactDtlsRec)obj[0];
		buff.append(objBean.getStrTrxTpe()+"<br>"+objBean.getStrChrgSrc()+"<br>"+objBean.getStrCretionDt()+"<br>"+objBean.getStrTrxPrcLst()+"<br>"+objBean.getStrSourceNum()+"<br>"+objBean.getContractPrice());
	}
	
	    System.out.println("buff : " + buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>
