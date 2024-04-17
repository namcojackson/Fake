<!-- $Header: ITG# 74988 canon_E193_csGateKeeper.jsp $ -->
<%--========================================================================
 |
 | FILE 
 | canon_E193_csGateKeeper.jsp - GateKeeper.
 |   
 | DESCRIPTION
 |   Authenticates user and stores user information into existing session
 |
 | AUTHOR
 | Dipti Shedji 
 |
 | CREATION DATE
 | 08/07/2005
 |
 | HISTORY
 | DATE        WHO               WHY
 |
 | 21-Mar-06   Vijay Janardhanan ITG# 46113 Session Timeout
 | 30-Nov-06   Vikas Basal       ITG# 74988 CFS Changes
 | 18-Dec-06   Kireet K Bollam   ITG# 73987 CBS Consolidation Changes
 +=======================================================================--%>
<%@page language="java" %> 

<%@page import="java.lang.*" %>
<%@page import="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
<%@page import="javax.servlet.http.Cookie" %>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="parts.dbcommon.EZDDBCICarrier"%>

<%@include file="canon_E193_csStdTxt.jsp" %>

<% 
System.out.println("in gatekeeper jsp");
   try {
        /* System DAO Call */
      com.canon.oracle.custapp.custinq.dao.Canon_E193_SystemDAO objCiSys = new com.canon.oracle.custapp.custinq.dao.Canon_E193_SystemDAO();

      /* System Bean Call*/      
      com.canon.oracle.custapp.custinq.beans.Canon_E193_SystemObj objCiSession = new com.canon.oracle.custapp.custinq.beans.Canon_E193_SystemObj();
      
      /* Standard Session Values */
   /*   WebAppsContext objWebAppsContext = WebRequestUtil.validateContext(request, response);
      if (objWebAppsContext == null) {
         response.sendRedirect("AppsLocalLogin.jsp");
      } 
      */
      /* get UserID  */
      String iSessionUserId = "";
    //  iSessionUserId = (int)objWebAppsContext.getUserId();   
    iSessionUserId = (String)CanonS21SessionValidate.getUserName();  //UC
    System.out.println("in gatekeeper jsp iSessionUserId is " + iSessionUserId);
	EZDDBCICarrier.initOnline(CanonS21SessionValidate.getUserName(), invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE); //UC
	EZDDBCICarrier.setProgID("S21EXTN_E193"); //UC
      
      /* get responsibility id */
      String strRespId = request.getParameter("resp_id");
      String iSessionRespId = "";
      if (strRespId != null)
         iSessionRespId = strRespId;

      /* get application id */
      String strApplId = request.getParameter("resp_appl_id");
      int iSessionApplId = 0;
      iSessionApplId =  (int)Integer.parseInt(strApplId);

      /* get org id */
      int iSessionOrgId = 0;
      if (iSessionRespId != "")
         iSessionOrgId= (int)objCiSys.getOrgID(iSessionRespId);

      /* get org name */
      String strSessionOrgName = "";
      if (iSessionOrgId != 0)
         strSessionOrgName = (String)objCiSys.getOrgName(iSessionOrgId);         
         
      /* get EmpName, UserName, ResourceId */
      String strSessionEmpName = "";         
      String strSessionUserName = "";
      //Start changes for S21 by Mangala
      //int iSessionResId = -1;
      String iSessionResId =  "";
    //End changes for S21 by Mangala
    		  
      if (iSessionUserId != "")
      {
         ArrayList alNames = (ArrayList)objCiSys.getNames(iSessionUserId);
         strSessionEmpName = (String)alNames.get(0);
         strSessionUserName = (String)alNames.get(1);
         
         iSessionResId = objCiSys.getResourceId(iSessionUserId);
      }

/*ITG# 74988 - Begin */

      /* get CFSAccessFlag, CFSUserFlag -- User Level Profiles */
      String strCFSAccessFlag = "N";    
      String strCFSUserFlag = "N";

      /*ITG# 73987 - Begin */      
      String strRegionCode = null; //Canon_E193_SystemObj.CBS_REGION_CODE;    
      /*ITG# 73987 - End */

      if (iSessionUserId != "")
      {
         ArrayList alProfiles = (ArrayList)objCiSys.getUserProfiles(iSessionUserId);
         strCFSAccessFlag = (String)alProfiles.get(0);
         strCFSUserFlag = (String)alProfiles.get(1);
         
         /*ITG# : Kireet: Retrieve region code from Invoice number: Begin */
         /*
         /*ITG# 73987 - Begin * /
         strRegionCode = (String)alProfiles.get(2);
         if("XX".equalsIgnoreCase(strRegionCode))
           response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + "RegionCodeIncorrectErr");
         
         out.println("Debug: strRegionCode  = "+strRegionCode);
         /*ITG# 73987 - End * /
         */
         /*ITG# : Kireet: Retrieve region code from Invoice number: End */
      }


/*ITG# 74988 - End */


      /* if orgid is invlaid, redirect to error page*/   
      if (iSessionOrgId == 0) {
         response.sendRedirect("canon_E193_csErrorPage.jsp?err=Org ID Missing");
      }
         
      /* Put system variables in the session object */
      objCiSession.setUserId(iSessionUserId);
      objCiSession.setUserName(strSessionUserName);
      objCiSession.setEmpName(strSessionEmpName);
      objCiSession.setResourceId(iSessionResId);
      objCiSession.setRespId(iSessionRespId);
      objCiSession.setApplId(iSessionApplId);
      objCiSession.setOrgId(iSessionOrgId);
      objCiSession.setOrgName(strSessionOrgName);
      
/*ITG# 74988 - Begin */

      objCiSession.setCFSAccessFlag(strCFSAccessFlag);
      objCiSession.setCFSUserFlag(strCFSUserFlag);
      
      /*ITG# : Kireet: Retrieve region code from Invoice number: Begin */
      /*
      /*ITG# 73987 - Begin * /
      objCiSession.setRegionCode(strRegionCode);
      /*ITG# 73987 - End * / 
      /*ITG# : Kireet: Retrieve region code from Invoice number: End */
      

/*ITG# 74988 - End */
      
      /* Put objCiSession in session */
      session.setAttribute("objCiSession", objCiSession);

      /* ITG# 46113 Start*/      
      //Start changes for S21 by Mangala
      //Cookie userIdCookie = new Cookie("e193userid",  java.lang.Integer.toString(iSessionUserId));
      Cookie userIdCookie = new Cookie("e193userid",  iSessionUserId);
    //End changes for S21 by Mangala
      userIdCookie.setMaxAge(1 * 24 * 60 * 60);
      response.addCookie(userIdCookie);

      Cookie userNameCookie = new Cookie("e193username", strSessionUserName);
      userNameCookie.setMaxAge(1 * 24 * 60 * 60);
      response.addCookie(userNameCookie);

      Cookie empNameCookie = new Cookie("e193empname", strSessionEmpName);
      empNameCookie.setMaxAge(1 * 24 * 60 * 60);
      response.addCookie(empNameCookie);
	
    //Start changes for S21 by Mangala
     // Cookie resourceIdCookie = new Cookie("e193resourceid", java.lang.Integer.toString(iSessionResId));
      Cookie resourceIdCookie = new Cookie("e193resourceid",iSessionResId);
    //End changes for S21 by Mangala
      resourceIdCookie.setMaxAge(1 * 24 * 60 * 60);
      response.addCookie(resourceIdCookie);

      Cookie respIdCookie = new Cookie("e193respid", java.lang.Integer.toString(iSessionRespId));
      respIdCookie.setMaxAge(1 * 24 * 60 * 60);
      response.addCookie(respIdCookie);

      Cookie applIdCookie = new Cookie("e193applid", java.lang.Integer.toString(iSessionApplId));
      applIdCookie.setMaxAge(1 * 24 * 60 * 60);
      response.addCookie(applIdCookie);

      Cookie orgIdCookie = new Cookie("e193orgid", java.lang.Integer.toString(iSessionOrgId));
      orgIdCookie.setMaxAge(1 * 24 * 60 * 60);
      response.addCookie(orgIdCookie);

      Cookie orgNameCookie = new Cookie("e193orgname", strSessionOrgName);
      orgNameCookie.setMaxAge(1 * 24 * 60 * 60);
      response.addCookie(orgNameCookie);
      /* ITG# 46113 End*/
      
/*ITG# 74988 - Begin */

      Cookie cfsAccessCookie = new Cookie("e193cfsaccess", strCFSAccessFlag);
      cfsAccessCookie.setMaxAge(1 * 24 * 60 * 60);
      response.addCookie(cfsAccessCookie);      

      Cookie cfsUserCookie = new Cookie("e193cfsuser", strCFSUserFlag);
      cfsUserCookie.setMaxAge(1 * 24 * 60 * 60);
      response.addCookie(cfsUserCookie); 

/*ITG# 74988 - End */

      /*ITG# 73987 - Begin */      
      Cookie regionCodeCookie = new Cookie("e193regioncode", strRegionCode);
      regionCodeCookie.setMaxAge(1 * 24 * 60 * 60);
      response.addCookie(regionCodeCookie); 
      /*ITG# 73987 - End */

   /* session.putValue("pathName","CI_DEV/"); */   
   
      /* forward to requested page  */ 
      String strPageId = request.getParameter("varLoadPage");
/*ITG# 74988 - Begin */

      if ( "CFSIssueCapture".equals(strPageId) )
      {
         if ( "N".equals(strCFSAccessFlag) )
         {
            strPageId = "CFSAccessMsg";
         }
      }
/*ITG# 74988 - End */
      String strForwardUrl ="" ;
   /* strForwardUrl = (session.getAttribute("pathName")) + "canon_E193_cs" + strPageId + ".jsp"; */
      strForwardUrl = "canon_E193_cs" + strPageId + ".jsp";
      response.sendRedirect(strForwardUrl);
   } 
   catch(com.canon.oracle.custapp.util.CanonCustAppExceptionUtil eCustExp) {
      String strErr = "-- Exception : " + eCustExp.getStrErrorDesc() + " -- Exception Location :" + eCustExp.getStrErrorLocation();    
      response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + strErr);
   }
   catch (Exception eExp) {
      response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
   }
%>

