<%@page import="canon.apps.common.CanonCustomProfile"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="com.canon.apps.e001.CanonE001CommonUtil"%>
<%@ page
	import="static canon.apps.common.CanonS21SessionValidate.commonRoot"%>
<!--[if lt IE 7]>      <html class="ie6"> <![endif]-->
<!--[if IE 7]>         <html class="ie7"> <![endif]-->
<!--[if IE 8]>         <html class="ie8"> <![endif]-->
<!--[if gt IE 8]><!--> <html>         <!--<![endif]-->
<head>
<%
	String commonRoot = commonRoot(request);
%>

<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8" />

<title><%=windowTitle%></title>

<link href="<%=commonRoot%>/jquery/jquery-ui-1.12.0.min.css" rel='stylesheet'
	type='text/css' />
<script src="<%=commonRoot%>/jquery/jquery-3.5.1.min.js"
	type='text/javascript'></script>
<script src="<%=commonRoot%>/jquery/jquery-ui.min.js"
	type='text/javascript'></script>
<script src='<%=commonRoot%>/jquery/jquery.blockUI2.js'
	type='text/javascript'></script>
<script type="text/javascript"
	src='<%=commonRoot%>/jquery/autoNumeric-1.6.2.js'></script>
<script type="text/javascript"
	src='<%=commonRoot%>/jquery/jquery.placeholder.min.js'></script>
<script type="text/javascript"
	src='<%=commonRoot%>/jquery/canonFileImport.js'></script>
<script type="text/javascript"
	src='../js/canonE001.js?v=7'></script>
<link rel="stylesheet" href="<%=commonRoot%>/css/s21extn.css?v=10"
	type="text/css">
<link rel="stylesheet" href="../css/canonE001.css?v=6"
	type="text/css">

</head>
<body>

	<!-- <header>  -->
	<div class="logo-img">
		<!--utility navigation start-->
		<div class="utility-nav">
			<div>
				<a id="home_page" href="<%=homePage%>">Home</a>
				
				
			</div>
		</div>
		<!--utility navigation end-->
		<div class="logo">
			<a href="#"><img src="<%=commonRoot%>/images/csa_logo.jpg"
				width="210" height="98" alt="Canon Solutons America"
				title="Canon Solutons America" /></a>
		</div>
		<!--  </header>  -->
	</div>

	<div id="toolTip"></div>

<script>

//
// Do not add javascript code here, add javascript in canonE001.js.
//
 
(function ( $ ) {
	$.s21extnCommonRoot='<%=commonRoot%>';
}( jQuery ));
 
function show_error(errmsg,response){
    defaultSingleErrorHandler.handle(errmsg);
    defaultSingleErrorHandler.showError();
    <% if(CanonE001CommonUtil.getDevFlag()){ %>
        if(typeof response != "undefined"){
          $("#eMsg" ).append('&nbsp;<a class="jsp_error" href="#">View error detail (visible only in dev environment)</a>');
          $("#eMsg .jsp_error").click(function (){
             var newWindow = window.open("","Error","width=400,height=500,scrollbars=1,resizable=1")
             newWindow .document.open()
             newWindow .document.write(response)
             newWindow .document.close()
          });
        }
    <%} %>    
}

</script>