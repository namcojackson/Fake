<%@page import="com.canon.common.*" %>  
<%@ page import="java.text.*"%> 
<%@ page import="java.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="parts.dbcommon.EZDDBCICarrier"%>
<%@page import="java.util.Enumeration"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="business.parts.NSZC045001PMsg"%>
<%@page import="com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001"%>
<%@page import="com.canon.common.CanonCommonUtil"%>
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
<%@ page language="java" import="com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SvcChrg"%>
<%@ page import="static canon.apps.common.CanonS21SessionValidate.commonRoot"%>

<html>
<head>  
<% 

	String ctxPath = request.getContextPath();
	String commonRoot=commonRoot(request);
	String imgSrc=ctxPath+"/common/images/jtfulov.gif";
	String imgSrc1=ctxPath+"/common/images/download.png";
	SimpleDateFormat sdf = new SimpleDateFormat("dd'-'MMM'-'yyyy");
	
	SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");   
	
	SimpleDateFormat format = new SimpleDateFormat("z");
	String timezone = format.format(new Date());
	String invokeTimestamp = lsDateFmt.format(new Date()); 
	String loginUser= CanonS21SessionValidate.getUserName();

	//Initialize S21DB-Carrier (It should be done. It leads NullPointer exception when didn't initialize.)
	//These contents are used as a default data in S21-Standard table. (Update user, time, time-zone, program id, company code)
	EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
	EZDDBCICarrier.setProgID("S21EXTN_E307");
	
	String strEntitleMsg=""; 
	CanonCommonUtil util = new CanonCommonUtil();
%>
<title>CSA Advanced Service Call Center:  <%=pageTitle%></title> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta charset="utf-8" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<link href="<%=commonRoot%>/jquery/jquery-ui.css" rel='stylesheet' type='text/css' />
	<script src="<%=commonRoot%>/jquery/jquery-1.10.2.min.js" type='text/javascript'></script>   		
	<script src="<%=commonRoot%>/jquery/jquery-ui-1.10.3.custom.min.js" type='text/javascript'></script>
    <script src='<%=commonRoot%>/jquery/jquery.blockUI2.js' type='text/javascript'></script>	
	<script src='<%=commonRoot%>/jquery/jquery.timepicker.js' type='text/javascript' ></script>
    <link href='<%=commonRoot%>/jquery/jquery.timepicker.css' rel="stylesheet" type="text/css">
    <script src='<%=ctxPath%>/e307/js/canonE307ServiceReqCommon.js' type='text/javascript'></script>
    <script src='<%=ctxPath%>/e307/js/tcepps.js' type='text/javascript'></script>
    <script type="text/javascript" src='<%=commonRoot%>/jquery/autoNumeric-1.6.2.js'></script>
	<link rel="stylesheet" href="<%=ctxPath%>/e307/css/canon_e307_style.css" type="text/css">
	<link rel="stylesheet" href="<%=ctxPath%>/e307/css/styles.css" type="text/css">	
	 
	<link href='http://fonts.googleapis.com/css?family=Raleway:400,700,500,300' rel='stylesheet' type='text/css'>
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="<%=ctxPath%>/common/js/html5shiv.js"></script>
    <![endif]-->
    
<script type="text/javascript">

function subSPForm(url){
	$("#spForm").attr("action",url);
	$("#spForm").submit();
}

</script>  
</head>
<body> 
  	<header>
		<!--utility navigation start-->
 		<div class="utility-nav">
  			<%-- <div><a href="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSearch.jsp" >Home</a><a href="<%=ctxPath%>/e307/jsp/canonE307ServiceReqHistory.jsp">Service Request History</a></div> --%>
		</div>
		<!--utility navigation end-->
		<div class="logo"><a href="#"><img src="<%=ctxPath%>/common/images/csa_logo.jpg" width="210" height="98" alt="Canon Solutons America" title="Canon Solutons America" /></a></div>
		<!--main navigation start-->
	  <nav>
				<% 
				
				  //Scratch pad
				  String srtchMsg =  util.checkNull(request.getParameter("scratchPad"));
				 System.out.println("srtchMsg : " + srtchMsg);
				  if(srtchMsg.trim().length()>0){
					  request.getSession().setAttribute("scratchPad", srtchMsg);
				  }else{
					   Object srtchMsgObj= request.getSession().getAttribute("scratchPad");
					   if(srtchMsgObj!=null)
						   srtchMsg =(String)srtchMsgObj;
				  } 
				 
				
				
				for(String m:menuList) {
				String[] mArr = m.split(":");
			       
				 String mId="";
				 String mCls="";      
				 String mLbl="";  
				 String mFunction="";
				 if(mArr[0]!=null && !(mArr[0].trim().equals("N"))){
					 mId=mArr[0].trim();
				 }
				 if(mArr[1]!=null && !(mArr[1].trim().equals("N"))){
					 mCls=mArr[1].trim();
				 }
				if(mArr[2]!=null && !(mArr[2].trim().equals("N"))){
					mLbl=mArr[2].trim();
				 }
		
				
				if(mArr[3]!=null && !(mArr[3].trim().equals("N"))){  
					mFunction= "onclick='"+mArr[3].trim()+"'";
				}
				
			%>
			<a id="<%=mId%>" class="<%=mCls%>" <%=mFunction%> style="color: #000000" href='#'><%=mLbl%></a>|
			<%} %>
			</nav>
		<!--main navigation end-->
</header>

<div id="toolTip"></div> 
<div id="sPadDiv" style="display: none;">
   <form id="spForm" id="spForm">
     <input type="hidden" id="spAction" name="spAction" value="add"/>
     <textarea name='scratchPad' id="scratchPad"  cols='33' rows='10'><%=srtchMsg %></textarea>
   </form>			
</div>


