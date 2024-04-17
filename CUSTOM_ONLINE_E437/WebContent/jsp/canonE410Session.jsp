<%@ page import="com.canon.apps.e437.*" %>

<%--
+==========================================================================+
|                                                                          |
|                                                                          |
+==========================================================================+
|  FILENAME                                                                |
|    canonE410Session.jsp					           					   |
|  DESCRIPTION                                                             |
|       This page is used for session handling           		   		   |
|  NOTES                                                                   |
|                                                                          |
|  DEPENDENCIES                                                            |
|  HISTORY                                                                 |
|                                    									   |
|  																		   |	
+==========================================================================+ 
--%>
<jsp:useBean class="com.canon.apps.e437.CanonE410PartScanning" scope="session" id="CanonE410PartScanning" />
<jsp:setProperty name="CanonE410PartScanning" property="*" />
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@ page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileService"%>
<%@ page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory"%>
<%@page import="oracle.apps.fnd.common.WebRequestUtil"%>
<%

	//Added for ITG#374470
	String strTechInv = "";
	//System.out.println("OUT strTechInv ->" );
	if(request.getParameter("techNum")!=null)
	{
	    System.out.println("INSIDE strTechInv ->" );
		strTechInv = request.getParameter("techNum");
	}
	System.out.println("strTechInv -> "+strTechInv );
	
	CanonE410PartScanning userSetUp = new CanonE410PartScanning();
    String [] names=userSetUp.getUserNameAndFullNameS21(request,response);
    String userName=names[0];
    String userId=names[1];
System.out.println("userName["+userName+"]userId["+userId+"]");

    boolean isAuthReadableUser = userSetUp.isAuthReadableUser(userId);
    //boolean isAuthReadableUser = true;
    boolean isAuthEditableUser = userSetUp.isAuthEditableUser(userId);
    //boolean isAuthEditableUser = false;
    String readonlyText = "";
    if (!isAuthEditableUser) {
        readonlyText = "readonly";
    }
System.out.println("isAuthReadableUser["+isAuthReadableUser+"]isAuthEditableUser["+isAuthEditableUser+"]");

  	//String userName=null;
	//String goBackAllowed ="Y";
	//int userId = -1;
	//int sessionId = -1;
	//userName = CanonE410PartScanning.getUserName();	
  
	//oracle.apps.fnd.common.WebAppsContext objWebAppsContext = WebRequestUtil.validateContext(request, response);
	//userName = objWebAppsContext.getUserName();
	//userId = objWebAppsContext.getUserId();	
	//sessionId = objWebAppsContext.getSessionIdAsInt();
	//int loginId = objWebAppsContext.getLoginId();
	//int RespId = objWebAppsContext.getRespId();
 
	if(!isAuthReadableUser) {
		%>
			<script language="javascript"> 
				var url = './canonE437Error.jsp?errorMsg=' + encodeURI("User cannot be authenticated."); //TODO FW login screen
				window.location.href = url;
			</script>
		<%
	} else {
		CanonE410PartScanning.setUserName(userName);
		//CanonE410PartScanning.setUserId(userId);
	}

	//String[] userSetUpInfo = userSetUp.getUserLoc(userName);
	
	//goBackAllowed = userSetUp.getMenuGoBack(RespId);
	// out.println(goBackAllowed);
	//String userOrgId = userSetUpInfo[0];
	//if (userOrgId == null){userOrgId="";}
	
	//String userSubInv = userSetUpInfo[1];
	//if (userSubInv==null) {userSubInv="";}
	
	//String userVset = userSetUpInfo[2];
	//if (userVset == null){userVset="";}
	
	//String userShowData = userSetUpInfo[3];
	//if (userShowData == null){userShowData="";}
	
	//String userOrgCode = userSetUpInfo[4];
	//if (userOrgCode == null){userOrgCode="";}
	
	//String userPrinter = userSetUpInfo[5];
	//if (userPrinter == null){userPrinter="";}
	
	//String userCCSubInv = userSetUpInfo[6];
	//if (userCCSubInv == null){userCCSubInv="";}

	//String userPhySubInv = userSetUpInfo[7];
	//if (userPhySubInv == null){userPhySubInv="";}
	
%>
