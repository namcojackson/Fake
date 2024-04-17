<%@page import="static canon.apps.common.CanonS21SessionValidate.commonRoot"%>

<script language="javascript">
    var NLSformat = "MM/DD/RRRR";
</script>
<link rel="stylesheet" type="text/css" href="<%=commonRoot(request)%>/canonBSDGlobal.css">
<link type="text/css" href="<%=commonRoot(request)%>/jquery/jquery-ui-1.8.13.custom.bsd.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=commonRoot(request)%>/jquery/jquery.ui.accordion.css">
<script language="javascript" src="<%=commonRoot(request)%>/canonDefault.js"></script>
<script language="javascript" src="<%=commonRoot(request)%>/canonDropdown.js"></script>
<link type="text/css" href="<%=commonRoot(request)%>/jquery/jquery-ui-1.8.13.custom.bsd.css" rel="stylesheet" />
<script src='<%=commonRoot(request)%>/jquery/jquery-1.5.1.min.js' type='text/javascript'></script>      
<script src='<%=commonRoot(request)%>/jquery/jquery-ui-1.8.13.custom.min.js' type='text/javascript'></script>  
<script src='<%=commonRoot(request)%>/jquery/jquery.blockUI.js' type='text/javascript' ></script>
<script type="text/javascript" src="../js/canonCustomBillingCommonFunction.js"></script>  
<script type="text/javascript" src="<%=commonRoot(request)%>/jquery/jquery.ui.accordion.js"></script>
<script type="text/javascript" src="<%=commonRoot(request)%>/jquery/excanvas.js"></script>
<script type="text/javascript" src="<%=commonRoot(request)%>/jquery/jquery.bt.js"></script>
<script type="text/javascript" src="<%=commonRoot(request)%>/jquery/jquery.validity.js"></script>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@ page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileService"%>
<%@ page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory"%>
<%@page import="oracle.apps.fnd.common.WebRequestUtil"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingUtil"%>
<%
	
   boolean isJspAccessValid = false;// CanonSecurityUtil.isJSPAccessValid(PAGE_NAME,userId,roleId);
   oracle.apps.fnd.common.WebAppsContext objWebAppsContext =
           WebRequestUtil.validateContext(request, response);
   CanonCustomBillingUtil util = new CanonCustomBillingUtil();

   String userName = objWebAppsContext.getUserName();
   System.out.println(" UIInclude: User name: "+userName);
   
   isJspAccessValid = util.hasRole(userName);

	if (!isJspAccessValid) {
%>
<jsp:forward page="canonInvalidAccess.jsp"></jsp:forward>
<%
	}
%> 
