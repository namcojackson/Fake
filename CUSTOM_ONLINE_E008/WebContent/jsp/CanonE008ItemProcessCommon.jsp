<%@ page import="com.canon.oracle.beans.*"%>
<%@ page import="canon.apps.common.CanonS21SessionValidate"%>
<%@ page import="oracle.apps.e008.item.process.CanonE008ItemProcessUtil"%>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContext"%>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder"%>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.S21Authentication"%>

<% String ctxPath = request.getContextPath(); %>

<!-- <meta http-equiv="X-UA-Compatible" content="IE=10,chrome=1"> -->
<!-- <title>Item Template Workbench</title> -->
<!-- <meta content="IE=edge,IE=8,chrome=1" http-equiv="X-UA-Compatible">
<meta charset="utf-8">
<meta content="" name="description">
<meta content="" name="keywords">
<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge,IE=8" http-equiv="X-UA-Compatible">
 
<link href='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-ui-1.7.2.custom.canon.css' rel='stylesheet' type='text/css' />
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/e008/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/e008/css/dataTables.fixedColumns.min.css">
<link href='<%=ctxPath%>/e008/css/canon_e008_style.css' rel='stylesheet' type='text/css' />
<link href='<%=ctxPath%>/e008/css/canonE008_Item_WB.css'	rel='stylesheet' type='text/css' />
<link href='<%=ctxPath%>/e008/css/styles.css'	rel='stylesheet' type='text/css' />

<%--<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-1.10.2.min.js'	type='text/javascript'></script> --%>
<%-- <script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-ui-1.10.3.custom.min.js' type='text/javascript'></script> --%>
<script src="<%=ctxPath%>/common/jquery/jquery-3.5.1.min.js" type='text/javascript'></script>    
<script src="<%=ctxPath%>/common/jquery/jquery-ui.min.js" type='text/javascript'></script>   
<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-ui-timepicker-addon.js'	type='text/javascript'></script>
<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery.blockUI2.js'	type='text/javascript'></script>
<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/ui.core.js'	type='text/javascript'></script>
<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/autoNumeric-1.7.1.min.js'	type='text/javascript'></script>
<script src='<%=ctxPath%>/e008/js/jquery.dataTables.min.js'></script>
<script src='<%=ctxPath%>/e008/js/dataTables.fixedColumns.min.js'></script>

<%
	String dataFormat = "DD-MON-RRRR";

	String userName = "";
	String userFullName = "";
	String userId = "";
	String strPage = "0";
	String strRespId = ""; //request.getParameter("resp_id");
	String strApplId = ""; //request.getParameter("resp_appl_id");
	String PAGE_NAME = "CanonE008ItemProcessCommon.jsp"; // 12-Nov-2012, Fixed incorrect page name for PCI

	// out.println("strRespId -> "+strRespId+" strApplId-> "+strApplId);

	//WebAppsContext objWebAppsContext = WebRequestUtil.validateContext(request, response);
	userName = CanonS21SessionValidate.getUserName();//objWebAppsContext.getUserName();
	S21SecurityContext context = S21SecurityContextHolder.getContext();
	S21Authentication s21Authentication = context.getAuthentication();
	userId = s21Authentication.getIdentityDetails().getUID();//objWebAppsContext.getUserId();
	//roleId = objWebAppsContext.getRespId();
	//System.out.println("roleId = "+roleId);

	if (userName.equals("") || userName.equals("GUEST")
			|| userName.equals("ANONYMOUS")) {
		response.sendRedirect("AppsLocalLogin.jsp");
	}
	if (!(userName.equals("")) || !(userName.equals(null))) {
		userFullName = CanonS21SessionValidate.getFullName();//objName.getFullName(userName);
	}
	if (request.getParameter("page") != null) {
		strPage = request.getParameter("page");
	}

	CanonE008ItemProcessUtil util = new CanonE008ItemProcessUtil();
	boolean isJspAccessValid = util.hasRole(userName); //true;// CanonSecurityUtil.isJSPAccessValid(PAGE_NAME,userId,roleId);

	if (!isJspAccessValid) {
%>
<jsp:forward page="canonE008InvalidAccess.jsp"></jsp:forward>
<%
	}
%> 
  
<script language="javascript">

	function getIdVal(x) {
		try {
			var m = document.getElementById(x).value;
			//alert(m);
			return m;
		} catch (err) {
			alert(err);
			return false;
		}
	}
	//set value for given id
	function setIdVal(x, m) {
		try {
			document.getElementById(x).value = m;
			//alert(m);
			//return m;
		} catch (err) {
			alert(err);
			return false;
		}
	}

    function blockUsrInterface() {
    	    
			$.blockUI({	css : {
							border : 'none',
							padding : '15px',
							backgroundColor : '#000',
							'-webkit-border-radius' : '10px',
							'-moz-border-radius' : '10px',
							opacity : .5,
							color : '#fff',
							cursor : 'default'
						},
						overlayCSS : {
							cursor : 'default'
						},
						baseZ : 9000,
						message : '<h1> Please Wait...</h1><img src="<%=ctxPath%>/e008/images/canonE008Wait.gif"/>'
					});
	
		} 
    
    	
		function unBlockUsrInterface() {
			$.unblockUI();
		}
 
  
	function validateDates(frmId, toId) {
		// alert(frmId);
		// alert(toId);
		var f = getIdVal(frmId);
		var t = getIdVal(toId);
		// alert("validateDates: "+f);
		// alert("validateDates: "+t);
		// if both dates are null
		if ((f == "" || f == null) && (t == "" || t == null)) {
			var r = 1;
			return 1;
		}

		// if from date is not null and to date is null
		if ((f !== "" || f !== null) && (t == "" || t == null)) {
			var ques = f.indexOf('?');
			// alert("quest"+ques);
			if (ques !== -1) {
				//alert("Please Enter Scan From Date in a valid format (DD-Mon-YYYY)");
				return ("Please Enter Scan From Date in a valid format (DD-Mon-YYYY)");
			} else if (ques == -1) {
				//document.forms['workingFolderForm'].scanDateTo.value = f;
				return 1;
			}

		}

		// if from date is null and to date is NOT null
		if ((f == "" || f == null) && (t !== "" || t !== null)) {
			//alert("Please Enter From Date" );
			return ("Please Enter From Date");
		}

		// if from date is NOT null and to date is NOT null	

		if ((f !== "" || f !== null) && (t !== "" || t !== null)) {
			var q = f.indexOf('?');
			var r = t.indexOf('?');

			if (q == -1 && r == -1) {
				var fArrDate = f.split('-');
				var tArrDate = t.split('-');
				if ((fArrDate.length == 3 && f.length == 11)
						&& (tArrDate.length == 3 && t.length == 11)) {
					var fd = new Date(fArrDate[2], arr[fArrDate[1]],
							fArrDate[0]);
					var td = new Date(tArrDate[2], arr[tArrDate[1]],
							tArrDate[0]);
					if (fd > td) {
						//alert("From Date Cannot Be greater than To Date");
						return ("From Date Cannot Be greater than To Date");
						document.getElementById(frmId).focus();
					} else {
						return 1;
					}

				}

			} else if (q !== -1 && r == -1) {
				return ("Enter From Date in a Valid Format (DD-MON-YYYY)");
			} else if (r !== -1 && q == -1) {
				return ("Enter To Date in a Valid Format (DD-MON-YYYY)");
			} else if (q !== -1 && r !== -1) {
				return ("Enter From Date & To Date in a Valid Format (DD-MON-YYYY)");
			}
		}
	}
	
	
   // END function validateDates
<%-- 	 if (!Modernizr.datalistelem) {
			 	alert('This browser does not support HTML5 datalist element, so we will load the polyfills');
			 }

  	Modernizr.load({
			 	test: Modernizr.datalistelem,
			 	nope: ['<%=ctxPath%>/e008/js/jquery.js', '<%=ctxPath%>/e008/js/jquery.datalist.js', '<%=ctxPath%>/e008/js/load.datalist.js']
			 });     
 --%>   
   	
  	
<%--   	 Modernizr.load({
			 	test: Modernizr.datalistelem,
			 	nope: ['<%=ctxPath%>/e008/js/jquery.datalist.js', '<%=ctxPath%>/e008/js/load.datalist.js']
			 });     --%> 
	 
				//document.createElement('datalist');          // IMPORTANT AS WELL
	/* window.onload = function() {
			    	alert(document.getElementById('masterProject').getElementsByTagName('option').length);//should alert 42
					}; 
	*/
			  
</script>