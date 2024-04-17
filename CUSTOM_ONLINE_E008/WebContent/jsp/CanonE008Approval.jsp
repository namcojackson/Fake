<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessUtil"%>
<%@ page import="canon.apps.common.CanonS21SessionValidate"%>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContext"%>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder"%>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.S21Authentication"%>

<html>
<head>
<% String ctxPath = request.getContextPath() ; %>

<TITLE>Project Approvals</TITLE>
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta charset="utf-8">
<meta content="" name="description">
<meta content="" name="keywords">
<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">

<link href='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-ui-1.7.2.custom.canon.css' rel='stylesheet' type='text/css' />
<link href='<%=ctxPath%>/e008/css/jquery.dataTables.min.css' rel='stylesheet' type='text/css' />
<link href='<%=ctxPath%>/e008/css/fixedColumns.dataTables.min.css' rel='stylesheet' type='text/css' />
<link href='<%=ctxPath%>/e008/css/canon_e008_style.css' rel='stylesheet' type='text/css' />
<link href='<%=ctxPath%>/e008/css/canonE008_Item_WB.css'	rel='stylesheet' type='text/css' />
<link href='<%=ctxPath%>/e008/css/styles.css'	rel='stylesheet' type='text/css' />

<%--<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-1.10.2.min.js'	type='text/javascript'></script>--%>
<%--<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-ui-1.10.3.custom.min.js' type='text/javascript'></script>--%>
<script src="<%=ctxPath%>/common/jquery/jquery-3.5.1.min.js" type='text/javascript'></script>    
<script src="<%=ctxPath%>/common/jquery/jquery-ui.min.js" type='text/javascript'></script>  
<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-ui-timepicker-addon.js'	type='text/javascript'></script>
<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery.blockUI2.js'	type='text/javascript'></script>
<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/ui.core.js'	type='text/javascript'></script>
<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/autoNumeric-1.7.1.min.js'	type='text/javascript'></script>
<script type="text/javascript" charset="utf8"	src="<%=ctxPath%>/e008/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"	src="<%=ctxPath%>/e008/js/dataTables.fixedColumns.js"></script>
<style>
	  
header { 
    background: #ffffff none repeat scroll 0 0;
    border-bottom: 1px solid #cc0000;
    border-top: 1px solid #4ab1e3;
    height: 80px;
    position: fixed;
    width: 100%;
    ##z-index: 1030;
}

.apprval-table {
	width: 100%;
	background: #cccccc;
	margin-bottom: 5px;
	}

.apprval-table th, .template-table td {
	padding: 12px;
	border: none;
	}
.apprval-table th {
    font-size: 12px;
	/* font-weight: bold; */
	/* text-align: left; */
	background: #003b4e;
	color: #FFFFFF;
	}
.apprval-table td {
	/* text-align: left; #center; */
	font-size: 11px;
	height: 20px;
	padding: 6px;
	}
.apprval-table input[type=text] {
	font-family: 'Raleway', sans-serif;
	font-size: 11px;
	border: 1px #cccccc solid;
  	height: 20px;
  	##background: #FFFFFF;
  	margin-right: 0px;
	}
.apprval-table th a:link, .template-table th a:visited, .template-table th a:active {
	color: #FFFFFF;
	text-decoration: none !important;
    }
.apprval-table th a:hover, .template-table th a:visited:hover {
	text-decoration: underline !important;
	}

.apprval-table .even {
	background-color: #FFFFFF;
	}
.apprval-table .odd {
	background-color: #ebebeb;
	}	
		     
</style>
</head>

<%-- <body>
	<header>
		<div class="logo">
		<img width=15% height="80" title="Canon Solutons America" alt="Canon Solutons America" src="<%=ctxPath%>/common/images/csa_logo.jpg">
		</div>
	</header>
    <div id = "divCanonE008MainApproval">
        <jsp:include page="CanonE008ApprovalInclude.jsp"/>
    </div>
</body>
</html> --%>

	<body>
	<div class="logo-img">
		<div class="logo">
		<a href="#">
		<img width=15% height="80" title="Canon Solutons America" alt="Canon Solutons America" src="<%=ctxPath%>/common/images/csa_logo.jpg">
		</a>
		</div>
	</div>   
	<!-- 	<script src="<%=ctxPath%>/common/jquery/jquery.scrolltable.js" type="text/javascript"></script>	 -->
        <div id = "divCanonE008MainApproval">
	    	<jsp:include page="CanonE008ApprovalInclude.jsp"/>
        </div>
		<div id="validatation-DataDiv"></div>

    </body>
</html>   















