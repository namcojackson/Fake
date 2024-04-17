<!DOCTYPE html>
<%@page import="canon.apps.common.CanonConstants"%>
<%@page import="java.util.Date"%>
<%@page import="business.parts.NSZC043001PMsg"%>
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

<%@page import="com.canon.oracle.beans.CanonE307ServReqTest"%>
<%@page import="static canon.apps.common.CanonS21SessionValidate.isAuthenticated"%>
<%@page import="static canon.apps.common.CanonS21SessionValidate.getUserName"%>
<%@page import="static canon.apps.common.CanonS21SessionValidate.commonRoot"%>

<html>
<head>
<title>Canon E307 Test</title>
</head>
<body style="margin:0px 100px;background-color:#000;">


<%

if(!isAuthenticated(session)){
%>
    <jsp:forward page="/common/jsp/canonInvalidAccess.jsp"></jsp:forward>
<%
}
%>
<div style="width: 100%; height: 100px; background-color: white; padding-top: 10px;" id="main_logo">
<img src="<%=commonRoot(request)%>/images/canon_smd_logo.jpg" alt="Canon Logo" /></div>
<div style="background-color: #FFF; min-height: 600px; padding-left: 10px;">	
	
<%

String strMsg="";

String loginUser1= getUserName();

  CanonE307ServReqTest canonE307 = new CanonE307ServReqTest();
  SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
  
  	// for test
		String loginUser = "C12945";
       
        
        SimpleDateFormat format = new SimpleDateFormat("z");
        String timezone = format.format(new Date());
        String invokeTimestamp = lsDateFmt.format(new Date());


        // Initialize S21DB-Carrier (It should be done. It leads NullPointer exception when didn't initialize.)
        // These contents are used as a default data in S21-Standard table. (Update user, time, time-zone, program id, company code)
		EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
        EZDDBCICarrier.setProgID("POC_APP");
  
  try{
	  
	   strMsg =  canonE307.createServicerequest(loginUser);
    
	   if ( strMsg.equalsIgnoreCase("Success") )
		   
		   strMsg="Service Request Created Sucessfully";
	   
  }catch(Exception e){
	  
	  strMsg=e.getMessage();
  }
  
%>
<br><br><br><br>

 <%=strMsg%>
<br>
<%=loginUser1 %>
</div>
</body>
</html>
