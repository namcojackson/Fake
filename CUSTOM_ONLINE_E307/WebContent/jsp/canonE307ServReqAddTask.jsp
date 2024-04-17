<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="java.util.Date"%>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil"%>
<%@page import="java.util.*" %>
<%@page import="canon.apps.common.CanonConstants"%>
<%@page import="parts.dbcommon.EZDDBCICarrier"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.canon.cusa.s21.framework.security.S21AuthenticationException"%>
<%@page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileService"%>
<%@page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory"%>
<%@page import="com.canon.cusa.s21.framework.security.S21SecurityException"%>
<%@page import="com.canon.cusa.s21.framework.security.S21Authentication"%>
<%@page import="com.canon.cusa.s21.framework.security.S21AuthorizationAction"%>
<%@ page language="java" import="parts.common.EZDCommonFunc" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContext" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextFactory" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.config.S21ConfigurationException" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.helpers.S21AuthenticationHelper" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.authentication.token.S21UserPasswordAuthenticationToken" %>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@ page language="java" import="com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SvcChrg"%>

<%
	SimpleDateFormat format = new SimpleDateFormat("z");
	SimpleDateFormat sdf = new SimpleDateFormat("dd'-'MMM'-'yyyy");
	SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
	String timezone = format.format(new Date());
	String invokeTimestamp = lsDateFmt.format(new Date()); 
	String loginUser= CanonS21SessionValidate.getUserName();

	//Initialize S21DB-Carrier (It should be done. It leads NullPointer exception when didn't initialize.)
	//These contents are used as a default data in S21-Standard table. (Update user, time, time-zone, program id, company code)
	EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
	EZDDBCICarrier.setProgID("EXTNE307");
	
	String strFsrNum = request.getParameter("fsrNum");	
	String strTasktpCd = request.getParameter("tskTpCd");
	//String strUniqueTech = request.getParameter("uniqueTech");
	String strDefltTech = request.getParameter("defltTech");
	String strPrefTech = request.getParameter("preftech");
	String strResType = request.getParameter("resType");
	String strFSvcDate = request.getParameter("fSvcDate");
	String strFSvcTm = request.getParameter("ftrSrvHr");
	String strFtrSrvMn = request.getParameter("ftrSrvMn");
	String strMachMstrPk = request.getParameter("machMstrPk");
	String strSvcCallTpCd = request.getParameter("svcCallTpCd");
	String strBillTpCd = request.getParameter("billTpCd");
	String strCallPrtyCd= request.getParameter("prtyCd");
	String techCd = request.getParameter("techCd");
	String slsDt = request.getParameter("slsDt");
	String strFtrAmPm = request.getParameter("ftrAmPm");
	String taskNotes = request.getParameter("tskNts");
	String noteType = request.getParameter("noteType");
	String postalCd = request.getParameter("postalCd");
	String cntryCd = request.getParameter("cntryCd");
	
	
	System.out.println("strDefltTech "+strDefltTech+" strTasktpCd : "+ strTasktpCd+" strPrefTech : "+ strPrefTech+" strResType : "+strResType+"strSvcCallTpCd : "+strSvcCallTpCd+" strCallPrtyCd : "+strCallPrtyCd +" taskNotes: "+taskNotes);
	
	System.out.println("Inside fsrNum : " + strFsrNum+" strFSvcDate : "+strFSvcDate+" strFSvcTm : "+strFSvcTm+" strDefltTech : "+strDefltTech+" strBillTpCd : "+strBillTpCd+" loginUser : "+loginUser);
	int result=0;	
	String[] strTask = new String[2];

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
    CanonE307ServiceReqSumryAPIUtil objInvDAO = new CanonE307ServiceReqSumryAPIUtil();
	if(strFsrNum!=null && strFsrNum!=""){
		strTask = objInvDAO.servReqAddTask(strFsrNum, strDefltTech, strPrefTech, strResType, strFSvcDate, strFSvcTm, strMachMstrPk, strTasktpCd, strBillTpCd, loginUser, strCallPrtyCd, techCd, slsDt, strFtrSrvMn, strFtrAmPm, taskNotes, noteType, postalCd, cntryCd);
	}
	
	
	if(strTask!=null && strTask.length>0){
		buff.append(strTask[0]+":"+strTask[1]);
	}

	//System.out.println("Add Task: "+ buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>