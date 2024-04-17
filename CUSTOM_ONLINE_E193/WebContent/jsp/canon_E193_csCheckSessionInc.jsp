<!-- $Header:ITG# 74988 canon_E193_csCheckSessionInc.jsp $ -->
<%--========================================================================
 |
 | FILE 
 | canon_E193_csCheckSessionInc.jsp - GateKeeper.
 |   
 | DESCRIPTION
 |   Authenticates session and redirects it if invalid.
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
 | 21-Mar-06   Vijay Janardhanan Session Time Out Issue ITG# 46113
 | 30-Nov-06   Vikas Basal       ITG# 74988 CFS Changes 
 | 18-Dec-06   Kireet K ollam    ITG# 73987 CBS Consolidation Changes
 |
 +=======================================================================--%>
<%@page language="java" %>
<%@ include file="canon_E193_csStdTxt.jsp" %> 
<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<%@page import="canon.apps.common.*"%>
<%@page import="java.util.ArrayList"%>
<%-- <%@ page import="canon.apps.common.CanonCustomProfile;"%>
<%@page import="canon.apps.common.CanonCommonUtilDao;"%> --%>
<%
System.out.println("in session jsp");
   /* System Bean Call*/      
   com.canon.oracle.custapp.custinq.beans.Canon_E193_SystemObj objCiSession = new com.canon.oracle.custapp.custinq.beans.Canon_E193_SystemObj();
   Object obj = null;//session.getAttribute("objCiSession");
   //System.out.println("obj is" + obj);
   if(obj == null) {

      /* ITG# 46113  Start*/
//System.out.println("in if");
      String strUserIdCookie     = "e193userid";
      String strUserNameCookie   = "e193username";
      String strEmpNameCookie    = "e193empname";
      String strResourceIdCookie = "e193resourceid";
      String strRespIdCookie     = "e193respid";
      String strApplIdCookie     = "e193applid";
      String strOrgIdCookie      = "e193orgid";
      String strOrgNameCookie    = "e193orgname";
      
/* ITG# 74988 - Begin */

      String strCFSAccessCookie  = "e193cfsaccess";
      String strCFSUserCookie  = "e193cfsuser";

/* ITG# 74988 - End */      

      /*ITG# 73987 - Begin */      
      String strRegionCodeCookie = "e193regioncode";   

//Start Changes for S21 by Mangala
        // objCiSession.setUserId("79004");
		//objCiSession.setUserId("Q08693");
      //objCiSession.setUserName("Q05028");
      //objCiSession.setEmpName("Q05028 Temp");
      //objCiSession.setResourceId("102041548");
      //objCiSession.setRespId(50234);
      
      // Getting User Profile Value.. it's call User region.
      String profileValue = CanonCustomProfile.getUserProfileValue("CSR_E193_REGION_ASSIGN");
      //if(profileValue!=null || profileValue!="")
      System.out.println(" ProfileValue= " + profileValue);
     
      objCiSession.setUserId(CanonS21SessionValidate.getUserName());
      objCiSession.setUserName(CanonS21SessionValidate.getUserName());
      objCiSession.setEmpName(CanonS21SessionValidate.getFullName());
      objCiSession.setResourceId(CanonS21SessionValidate.getUserName());
      objCiSession.setRespId(CanonS21SessionValidate.getUserName());
      
    //End Changes for S21 by Mangala    
      objCiSession.setApplId(20003);
      objCiSession.setOrgId(81);
      objCiSession.setOrgName("Canon Solutions America, Inc.");
      Canon_E193_SystemDAO objCiSys = new Canon_E193_SystemDAO();
      ArrayList alProfiles = (ArrayList)objCiSys.getUserProfiles(objCiSession.getUserId());
      objCiSession.setCFSAccessFlag((String)alProfiles.get(0));
      objCiSession.setCFSUserFlag((String)alProfiles.get(1));
     // objCiSession.setRegionCode((String)alProfiles.get(2));
     if(profileValue == null || (profileValue != null && (profileValue.trim().isEmpty() || "null".equals(profileValue.trim()))) ) // Newly Added To SetRegionCode
		objCiSession.setRegionCode("EAST_REGION"); //Default value.
     else 
    	objCiSession.setRegionCode(profileValue);
     // session.putValue("objCiSession", objCiSession);
     }else{
	   System.out.println("hello in else of session jsp");
      objCiSession = (com.canon.oracle.custapp.custinq.beans.Canon_E193_SystemObj)obj;
    //  System.out.println("hello in else of session jsp" );
   }

%>