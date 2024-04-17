<%@page import="com.canon.apps.servreq.beans.CanonE307SRHistHdrLvlInfoRec"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="com.canon.common.*" %>  
<%@page import="java.util.*" %>
<%
	String strFsrNum = request.getParameter("fsrNum");	
	
	System.out.println("Hdr level Info fsrNum1 : " + strFsrNum);
	int result=0;	
	CanonCommonUtil util = new CanonCommonUtil();
	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    CanonE307ServiceRequestDetailsDao objHdrDAO = new CanonE307ServiceRequestDetailsDao();
    CanonE307SRHistHdrLvlInfoRec infoRec = new CanonE307SRHistHdrLvlInfoRec();
	if(strFsrNum!=null && strFsrNum!=""){
		infoRec = objHdrDAO.getHdrLvlInfo(strFsrNum);
	}
	if(infoRec!=null){
		buff.append(util.checkNull(infoRec.getStrModel())+":"+util.checkNull(infoRec.getStrSerNum())+":"+util.checkNull(infoRec.getStrBranch())+":"+util.checkNull(infoRec.getStrMachMngr())+":"+util.checkNull(infoRec.getStrCustName())+":"+util.checkNull(infoRec.getStrAddress())+":"+util.checkNull(infoRec.getStrCity())+":"+util.checkNull(infoRec.getStrState())+":"+util.checkNull(infoRec.getStrPostalCd())+":"+util.checkNull(infoRec.getStrContactNm())+":"+util.checkNull(infoRec.getStrCntactPhone())+":"+util.checkNull(infoRec.getStrEmailAddress()));
	}
//   	System.out.println("Hdr Lvl Info1: "+ buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>