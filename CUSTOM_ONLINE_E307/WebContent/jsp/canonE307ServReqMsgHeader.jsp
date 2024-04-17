<%@page import="com.canon.apps.servreq.beans.CanonE307AccessRoleBean"%>
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
<%@ page import="com.canon.apps.servreq.dao.CanonE307ServReqUtil"%>
<%@ page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>


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
	String editRole = "E307_DIS";
//	CanonE307ServiceRequestDetailsDao detObj = new CanonE307ServiceRequestDetailsDao();
//    String editRole = detObj.getUserEditRole(loginUser);
    String rdOnlyRl = "";
    String srvcMngrRl = "";
    String techRl = "";

    CanonE307ServReqUtil reqObj = new CanonE307ServReqUtil();
    CanonE307AccessRoleBean beanObj = new CanonE307AccessRoleBean();
    Object rdOnlyObj= request.getSession().getAttribute("readOnlyRl");
	   if(rdOnlyObj!=null)
		   rdOnlyRl =(String)rdOnlyObj;
	   
    Object srvcMngrObj= request.getSession().getAttribute("srvcMngrRl");
	   if(srvcMngrObj!=null)
		   srvcMngrRl =(String)srvcMngrObj;	  

    Object techObj= request.getSession().getAttribute("techRl");
	   if(techObj!=null)
		   techRl =(String)techObj;
	   
	System.out.println("1 rdOnlyRl: "+rdOnlyRl+" srvcMngrRl: "+srvcMngrRl+" techRl: "+techRl+" loginUser..: "+loginUser);
	   
    if("".equals(rdOnlyRl) && "".equals(srvcMngrRl) && "".equals(techRl)){ 
	    beanObj = reqObj.hasASCCRole(loginUser);
	    rdOnlyRl = beanObj.getHasReadOnly()==null?"N":beanObj.getHasReadOnly();
	    srvcMngrRl = beanObj.getHasSrvcMngrRl()==null?"N":beanObj.getHasReadOnly();
	    techRl = beanObj.getHasTechRl()==null?"N":beanObj.getHasTechRl();
	   
	    request.getSession().setAttribute("readOnlyRl", rdOnlyRl);
	    request.getSession().setAttribute("srvcMngrRl", srvcMngrRl);
	    request.getSession().setAttribute("techRl", techRl);
    }
    System.out.println("2 rdOnlyRl: "+rdOnlyRl+" srvcMngrRl: "+srvcMngrRl+" techRl: "+techRl+" loginUser: "+loginUser);

    if("Y".equals(techRl)){
    	editRole="E307_DIS";
    }else if("Y".equals(srvcMngrRl)){
    	editRole="E307_SRM";
    }else if("Y".equals(rdOnlyRl)){
    	editRole="E307_OTH";
    }
    
    System.out.println(" loginUser : "+ loginUser+" editRole : "+editRole);

	//Initialize S21DB-Carrier (It should be done. It leads NullPointer exception when didn't initialize.)
	//These contents are used as a default data in S21-Standard table. (Update user, time, time-zone, program id, company code)
	EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
	EZDDBCICarrier.setProgID("EXTNE307");
	
	String strEntitleMsg=""; 
	CanonCommonUtil util = new CanonCommonUtil();
%>
<title>CSA Advanced Service Call Center:  <%=pageTitle%></title> 
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<!-- <meta content="IE=edge,IE=8" http-equiv="X-UA-Compatible"> -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,IE=8" />
    <meta charset="utf-8" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<link href="<%=commonRoot%>/jquery/jquery-ui.css" rel='stylesheet' type='text/css' />
<%-- 	<script src="<%=commonRoot%>/jquery/jquery-1.10.2.min.js" type='text/javascript'></script>   		
	<script src="<%=commonRoot%>/jquery/jquery-ui-1.10.3.custom.min.js" type='text/javascript'></script> --%>
	
	<script src="<%=commonRoot%>/jquery/jquery-3.5.1.min.js" type='text/javascript'></script>	
	<script src="<%=commonRoot%>/jquery/jquery-ui.min.js" type='text/javascript'></script>	
    <script src='<%=commonRoot%>/jquery/jquery.blockUI2.js' type='text/javascript'></script>	
	<script src='<%=commonRoot%>/jquery/jquery.timepicker.js' type='text/javascript' ></script>
    <link href='<%=commonRoot%>/jquery/jquery.timepicker.css' rel="stylesheet" type="text/css">
    <script src='<%=ctxPath%>/e307/js/canonE307ServiceReqCommon.js?v=7' type='text/javascript'></script>
    <script src='<%=ctxPath%>/e307/js/tcepps.js?v=7' type='text/javascript'></script>
    <script type="text/javascript" src='<%=commonRoot%>/jquery/autoNumeric-1.6.2.js'></script>
	<link rel="stylesheet" href="<%=ctxPath%>/e307/css/canon_e307_style.css" type="text/css">
	<link rel="stylesheet" href="<%=ctxPath%>/e307/css/styles.css" type="text/css">	
	<script src='<%=ctxPath%>/e307/js/canonE307ServReqBlockUI.js?v=2' type='text/javascript'></script>
	
	 
<!-- 	<link href='http://fonts.googleapis.com/css?family=Raleway:400,700,500,300' rel='stylesheet' type='text/css'> -->
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="<%=commonRoot%>/js/html5shiv.js"></script>
    <![endif]-->
    
    
<script type="text/javascript">

function subSPForm(url){
	$("#spForm").attr("action",url);
	$("#spForm").submit();
}


</script>  
</head>
<body> 

 	<!-- <header>  -->
   <div class="logo-img"> 
		<!--utility navigation start-->
 		<div class="utility-nav">
<%--   			<div><a href="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSearch.jsp" >Home</a>
  			<a href="<%=ctxPath%>/e307/jsp/canonE307ServiceReqHistory.jsp">Service Request History</a>
  			<a target="_blank" href="<%=ctxPath%>/e307/jsp/canonE307SrvcMsgSearch.jsp">Service Message Creation</a>
  			</div> --%>
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
<!--  </header>  -->
 </div> 

 <div id="toolTip"></div> 
<div id="sPadDiv" style="display: none;">
   <form id="spForm" id="spForm">
     <input type="hidden" id="spAction" name="spAction" value="add"/>
     <textarea name='scratchPad' id="scratchPad"  cols='33' rows='10'><%=srtchMsg %></textarea>
   </form>			
</div>


