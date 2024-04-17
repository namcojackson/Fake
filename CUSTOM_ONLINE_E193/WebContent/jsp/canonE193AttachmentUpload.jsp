<%@page import="com.canon.oracle.custapp.util.CanonE193FileUploadDownloadAPIUtil"%>

<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="java.util.*" %>

<%@page import="parts.dbcommon.EZDDBCICarrier"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.canon.cusa.s21.framework.security.S21AuthenticationException"%>
<%@page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileService"%>
<%@page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory"%>
<%@page import="com.canon.cusa.s21.framework.security.S21SecurityException"%>
<%@page import="com.canon.cusa.s21.framework.security.S21Authentication"%>
<%@page import="com.canon.cusa.s21.framework.security.S21AuthorizationAction"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@ page language="java" import="parts.common.EZDCommonFunc" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContext" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextFactory" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.config.S21ConfigurationException" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.helpers.S21AuthenticationHelper" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.authentication.token.S21UserPasswordAuthenticationToken" %>
<%@ page language="java" import="com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SvcChrg"%>

<%
	System.out.println("Inside canonE193AttachmentUpload.jsp");
	String strTicketNum = request.getParameter("ticket_number");
	String strUserName = CanonS21SessionValidate.getUserName();
	String strAcctNum = request.getParameter("invNum");
	String strFileName=request.getParameter("fileName");
	int fileId = 0; 
	System.out.println("Attachment tktId : " + strTicketNum+" strUserName : "+strUserName+" strSerNum : "+strAcctNum+" strFileName : "+strFileName);
	int result=0;	
	String[] strRes = new String[2];

	PrintWriter pw = response.getWriter();
    StringBuffer buff = new StringBuffer();
     CanonE193FileUploadDownloadAPIUtil objInvDAO = new CanonE193FileUploadDownloadAPIUtil();
     	if(strTicketNum!=null && !"".equalsIgnoreCase(strTicketNum)){
		fileId = objInvDAO.uploadAttachment(strTicketNum, strAcctNum, strUserName, strFileName);
	}
	if(fileId>0){
		buff.append(fileId);
	}

	//System.out.println("Get Eta: "+ buff.toString());
        pw.println(buff.toString());
        pw.flush();  
%>