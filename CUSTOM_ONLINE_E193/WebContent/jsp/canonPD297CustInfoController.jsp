
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page language="java" import="com.canon.oracle.custapp.custprof.dao.CanonPD297CustInfoDAO" %>
<%@ page language="java" import="com.canon.oracle.custapp.util.CanonCustAppExceptionUtil"%>
<%@ page language="java" import="java.util.ArrayList"%>
<%@ page language="java" import="java.util.Calendar"%>
<%@ page language="java" import="java.util.TimeZone"%>
<%@ page language="java" import="java.io.PrintWriter"%>
<%@ page language="java" import="java.io.IOException"%>
<%@ page language="java" import="java.util.Date"%>
<%@ page language="java" import="java.text.SimpleDateFormat"%>
<%@ page import="com.canon.oracle.custapp.custprof.beans.canonPD297SecurityBean" %>
<%@ include  file="canonPD297CheckSession.jsp"%>

<HTML>
<%
	int iRespId = objSecurity.getRespId();
	String strContextPath =(String)request.getClass().getMethod("getContextPath",new  Class[]{}).invoke(request,null);
%>
<%
	session.removeValue("custInfoDetailsSite");
    System.out.println("Inside the CustInfo Renderer class =================");
    ArrayList results         = new ArrayList();
    String    strRecordType   = "";
    String strBackURL         = "";
    String searchType         = "";
    String refreshDate        = "";
    String custAcctId         = ""; 
    String orgId              = "";
    String customerName       = "";
    String customerNumber     = "";
    String strSiteLevelFlag   = "";
	String strDBQueried       = "false";
	int iErrorCode            = 0;
	String strErrorDesc       = "";
    String strErrorLocation   = "";
	int  iExceptionStrLength  = 0;

    CanonPD297CustInfoDAO objCustInfoDAO = new CanonPD297CustInfoDAO();

	String strPageName        = new String();

    strPageName               =  request.getParameter("pageName");

    strPageName = strPageName == null ? "PD297CustInfoSearch" : strPageName;
    
    String DATE_FORMAT = "dd'-'MMM'-'yyyy HH24:mi:ss";
    strRecordType   = request.getParameter("recordType");
	strRecordType =strRecordType == null ? "ALL" : strRecordType;
    searchType = (String)request.getParameter("actionFlag");
    custAcctId = (String)request.getParameter("custAcctId");
	String strParentApp		  = "";
	strParentApp              = request.getParameter("PD297ParentApp");
	strParentApp              = (strParentApp == null) ? "" :strParentApp;

    strBackURL      = "/pls/portal/url/page/canon/canonPD297CustInfo";
    
    //Site Level 
    strSiteLevelFlag = request.getParameter("siteLevelFlag");
    strSiteLevelFlag = strSiteLevelFlag == null ? "" : strSiteLevelFlag;


    orgId = (String)request.getParameter("orgId");	
	try
	{
		if (orgId == null)
		{
			orgId = objCustInfoDAO.getOrgId(iRespId);
		}
		
		if(strSiteLevelFlag.equals("true"))
		{
		  customerName = (String)request.getParameter("customerName"); 
		  customerNumber = (String)request.getParameter("customerNumber");
		}
		else
		{
		  customerName = (String)request.getParameter("custName"); 
		  customerNumber = (String)request.getParameter("custNumber");
		  customerName = (customerName == "" || customerName == null) ? request.getParameter("customerName"):customerName; 
		  customerNumber = (customerNumber == "" || customerNumber == null) ? request.getParameter("customerNumber"):customerNumber; 

		}
  	    customerName = (customerName == "" || customerName == null) ? "" : customerName; 
		customerNumber = (customerNumber == "" || customerNumber == null) ? "" : customerNumber; 
		
		strRecordType   = strRecordType == null ? "ALL" : strRecordType;
		searchType = searchType == null ? "" : searchType;

	   
		if (searchType.equals("custSearch"))
		{
			String searchCriteria = request.getParameter("searchField");
			if (searchCriteria!= null && !(searchCriteria.equals("")))
			{            
			  /*if (strParentApp.equals("RESPONSIBILITY"))
			  {
				results = objCustInfoDAO.getCustomerDetails_responsibility(strRecordType,custAcctId, orgId);
			  }
			  else
			  {*/
			  	results = objCustInfoDAO.getCustomerDetails(strRecordType,custAcctId, orgId);
			  //}
			}
			out.println(results);
			session.setAttribute("custInfoDetails",results);
			strDBQueried = "true";
		}
		else if (searchType.equals("refresh"))
		{

		  /*if (strParentApp.equals("RESPONSIBILITY"))
		  {
			results = objCustInfoDAO.refreshData_responsibility(strRecordType,custAcctId, orgId);
		  }
		  else
		  {*/
			results = objCustInfoDAO.refreshData(strRecordType,custAcctId, orgId);
		  //}
			session.setAttribute("custInfoDetails",results);
			strDBQueried = "true";
		}
    
	}
	catch (Exception eExp) 
	{
		iErrorCode       = 100001;
		iExceptionStrLength = eExp.toString().length();
		strErrorDesc     = (iExceptionStrLength > 200) ? eExp.toString().substring(0,iExceptionStrLength) : eExp.toString();
		strErrorLocation = "Class: canonPD297CustomerLOVController";

		%>
		<jsp:forward page="canonPD297ErrorPage.jsp">
		<jsp:param name="iErrorCode"       value = "<%= iErrorCode %>" />
		<jsp:param name="strErrorDesc"     value = "<%= strErrorDesc %>" />      
		<jsp:param name="strErrorLocation" value = "<%= strErrorLocation %>" />
		<jsp:param name="pageName"         value = "PD297CustInfoSearch" />
		</jsp:forward>
		<% 
	}  

	strRecordType     = (strRecordType == null) ? "ALL" : strRecordType;
	strSiteLevelFlag  = (strSiteLevelFlag == null) ? "" : strSiteLevelFlag;
    searchType        = (searchType == null) ? "" : searchType;
    orgId             = (orgId == null) ? "" : orgId;
	custAcctId        = (custAcctId == null) ? "" : custAcctId;
	strSiteLevelFlag  = (strSiteLevelFlag == null) ? "" : strSiteLevelFlag;
	refreshDate       = (refreshDate == null) ? "" : refreshDate;
	customerName      = (customerName == null) ? "" : customerName; 
	customerNumber    = (customerNumber == null) ? "" : customerNumber; 

%>
<jsp:forward page="canonPD297CustInfo.jsp">
  <jsp:param name="refreshDate" value="<%= refreshDate %>"/>
  <jsp:param name="custName" value="<%= customerName %>"/>
  <jsp:param name="custNumber" value="<%= customerNumber %>"/>
  <jsp:param name="custAcctId" value="<%= custAcctId %>"/>
  <jsp:param name="recordType" value="<%= strRecordType %>"/>
  <jsp:param name="orgId" value="<%= orgId %>"/>
  <jsp:param name="pageName"          value="<%= strPageName %>"/> 
  <jsp:param name="DBQueried"         value="<%= strDBQueried %>"/> 
  <jsp:param name="PD297ParentApp"    value="<%= strParentApp %>"/> 
</jsp:forward>

</HTML>

