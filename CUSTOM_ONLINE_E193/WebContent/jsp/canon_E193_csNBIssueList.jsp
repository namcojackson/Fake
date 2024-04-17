<!-- $Header: canon_E193_csNBIssueList.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csNBIssueList - Non billing issues.
 |   
 | DESCRIPTION
 |   Details of the non billing issue.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	09/15/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 18-Dec-2006    Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_IssueListObj" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_IssueList" id="objIssueList" scope="page"/>

<%@ include file="canon_E193_csTopInc.jsp" %>

<%
	// Menu Prompt
	strPageName = "Enter & Inquiry";
	
	// Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");
	String hPath = request.getParameter("hPath");
	if (hPath == null)
		hPath = strNBIssueListT1;
	else
		if(hPath.indexOf(strNBIssueListT1) < 0)
			hPath = hPath + " -> " + strNBIssueListT1;	
	System.out.println("hello in canon_E193_csNBIssueList.jsp");
%>
<%@ include file="canon_E193_csMenuInc.jsp" %>

<script language="JavaScript">
	
	function onSelect(val1, val2, val3) {
		document.nonBillingIssueForm.hCatId.value = val1;
		document.nonBillingIssueForm.hParentCatId.value = val2;
		document.nonBillingIssueForm.hCatDesc.value = val3;
	}
	
	function action_func1() {
		resetErroMsg();
		var objForm = document.nonBillingIssueForm;
		var isSelected = false;
		var v = objForm.rIssue.length;
		if(v > 0) {
			for(i=0; i<v; i++) {
				if(objForm.rIssue[i].checked) {
					isSelected = true;
					break;
				}
			}
		}else if(objForm.rIssue.checked) isSelected = true;
		
		if(!isSelected) {
			displayErrorMsg("Please select one issue");
		}else{
			objForm.submit();
		}
	}
	
//-->
</script>
<%
	//Get Org ID
	int iOrgId = objCiSession.getOrgId();
	
	Object objAcct = session.getAttribute("objSessionAcctInfo");
	//Canon_E193_AcctInfoObj objSessionAcctInfo = new Canon_E193_AcctInfoObj();
	
	Canon_E193_AcctInfoObj objSessionAcctInfo = (Canon_E193_AcctInfoObj)session.getAttribute("objSessionAcctInfo");	
    //System.out.println("in canon_E193_csNBIssueList.jsp 2" + iOrgId);
	
	//if(objAcct != null) 
	//	objSessionAcctInfo = (Canon_E193_AcctInfoObj)objAcct;
	
	// getting the data from database
	ArrayList alIssueList = new ArrayList();
	
	/*ITG#: : Kireet: RegionCode for Non-billing issues is EAST_REGION : Begin */
	/*	      
	/* ITG# 73987 : Begin * /	
	String strRegionCode = (String)objCiSession.getRegionCode();	
        /* ITG# 73987 : End * /
        */
        String strRegionCode = com.canon.oracle.custapp.custinq.dao.Canon_E193_RegionCodeDAO.EASTERNREGCODE;
        System.out.println("in canon_E193_csNBIssueList.jsp strRegionCode is " + strRegionCode);
      //  objCiSession = (com.canon.oracle.custapp.custinq.beans.Canon_E193_SystemObj)session.getAttribute("objCiSession");
	objCiSession.setRegionCode(strRegionCode);
	session.setAttribute("objCiSession",objCiSession);
      	/*ITG#: : Kireet: RegionCode for Non-billing issues is EAST_REGION : End */
	//System.out.println("in canon_E193_csNBIssueList.jsp strRegionCode objCiSession is set " + objCiSession	);
	alIssueList = objIssueList.getIssueList(iOrgId, strRegionCode, "NONBILL", 0);
	 //System.out.println("in canon_E193_csNBIssueList.jsp 3" + objSessionAcctInfo);
%> 

<div id="main_content">
	<div id="page_top">
		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strNBIssueListT1 %></h1>
		<div class="breadcrumb"><%=hPath%></div>
	</div>
		
	<form name="nonBillingIssueForm" action="canon_E193_csNBIssueCapture.jsp" method="post">

	<input type="hidden" name="hPageFrom" value="NBIssueList">
	<input type="hidden" name="nextPage" value="">
	<input type="hidden" name="hCatId" value="">
	<input type="hidden" name="hParentCatId" value="">
	<input type="hidden" name="hParentCatCode" value="NONBILL">
	<input type="hidden" name="hCatDesc" value="">
	
	<input type="hidden" name="iTicketId" value="<%=request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId")%>">

	<input type="hidden" name="origName" value="<%=request.getParameter("origName")%>" >
	<input type="hidden" name="origPhNo" value="<%=request.getParameter("origPhNo")%>">
	<input type="hidden" name="origEmailAddress" value="<%=request.getParameter("origEmailAddress")%>">
	<input type="hidden" name="origCheckbox" value="<%=request.getParameter("origCheckbox")%>">
	<input type="hidden" name="origType" value="<%=request.getParameter("origType")%>" >
	<input type="hidden" name="sourceType" value="<%=request.getParameter("sourceType")%>" >		
	<input type="hidden" name="custName" value="<%=request.getParameter("custName")%>" >
	<input type="hidden" name="custPhNo" value="<%=request.getParameter("custPhNo")%>">
	<input type="hidden" name="custEmailAddress" value="<%=request.getParameter("custEmailAddress")%>">
	
	<input type="hidden" name="recurProb" value="<%=request.getParameter("recurProb")%>">
	<input type="hidden" name="probType" value="<%=request.getParameter("probType")%>">
	<input type="hidden" name="val1" value="<%=request.getParameter("val1")%>">
	
	<input type="hidden" name="selAcctId" value="<%=request.getParameter("selAcctId")%>" >
	<input type="hidden" name="selLocId" value="<%=request.getParameter("selLocId")%>" >
	<input type="hidden" name="selAcctName" id="selAcctName" value="<%=request.getParameter("selAcctName")%>" >
	<input type="hidden" name="selAcctNum" value="<%=request.getParameter("selAcctNum")%>" >
	<input type="hidden" name="hPath" value="<%=hPath%>">
	<div style="margin-left: 20px;"><b>Customer Details:</b> </div>
	<table class="request-service" style="align:center;" cellspacing="1">
	<tr>
			    <td colspan="4">
			       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
				   </div>
			   </td>
			</tr>
	<% if(objSessionAcctInfo != null) { %>  		   		
   		<tr>
						<th>Account Name</th>
						<th>Account Number</th>
						<th>Contact Name</th>
						<th>Contact Number</th>		
					</tr>
   		<tr>   			
			<td>
							<% String strDisplayName = "";
								if(objSessionAcctInfo.getAcctName() != null && !objSessionAcctInfo.getAcctName().equalsIgnoreCase("null")) 
									strDisplayName = objSessionAcctInfo.getAcctName();
							%><%=strDisplayName%>
						</td>
						<td>
							<% String strDisplayNo = "";
								if(objSessionAcctInfo.getAcctNum() != null && !objSessionAcctInfo.getAcctNum().equalsIgnoreCase("null")) 
									strDisplayNo = objSessionAcctInfo.getAcctNum();
							%><%=strDisplayNo%>
						</td>
						<td>
							<% String strDisplayContName = "";
								if(objSessionAcctInfo.getContactName() != null && !objSessionAcctInfo.getContactName().equalsIgnoreCase("null")) 
									strDisplayContName = objSessionAcctInfo.getContactName();
							%><%=strDisplayContName%>
						</td>
						<td>
							<% String strDisplayContNo = "";
								if(objSessionAcctInfo.getContactNum() != null && !objSessionAcctInfo.getContactNum().equalsIgnoreCase("null")) 
									strDisplayContNo = objSessionAcctInfo.getContactNum();
							%><%=strDisplayContNo%>
						</td>
			    	</tr>		     	
  		<% } %>
	</table>
	
	<table class="request-service" style="align:center;" cellpadding="1" cellspacing="0" border="0">	
		
	
  		<tr>
    		<td colspan="2">&nbsp;</td>
  		</tr>
		<tr>
		<td>&nbsp;</td>
			<td><b><%=strNBIssueListQ1%></b></td>
		</tr>
    	<tr>
    		<td colspan="2"></td>
  		</tr>
		<%  
  			if(alIssueList != null && alIssueList.size() > 0) {
  				Canon_E193_IssueListObj objIssue = null;
  				String strCatIdL = "", strCatIdS = "", strDisabled = "";
  				String strCatIdP = request.getParameter("strCatIdP")==null?"":request.getParameter("strCatIdP");
  				StringTokenizer st = null;
  				int countToken = 0;
  				for(int i=0; i<alIssueList.size(); i++) {
  					strDisabled = "";
  					strCatIdS = "";
  					objIssue = (Canon_E193_IssueListObj)alIssueList.get(i);
  					strCatIdL = ""+objIssue.getCatId();
  					if(!"".equals(strCatIdP)) {
  						st = new StringTokenizer(strCatIdP, "|");
  						countToken = st.countTokens();
  						for(int j=0;  j<countToken; j++) {
  							strCatIdS = st.nextToken();
  							if(strCatIdL.equals(strCatIdS)) {
  								strDisabled = "disabled";
  								break;
  							}
  						}
  					}
  		%>
  					<tr>
  						<td width="10">&nbsp;</td>
  						<td align="left">
  							<input type="radio" name="rIssue" value="<%=objIssue.getCatId()%>" <%=strDisabled%> onClick="onSelect('<%=objIssue.getCatId()%>', '<%=objIssue.getParentCatId()%>', '<%=objIssue.getCatDesc()%>');">
  							<font class="promptReadOnly"><%=objIssue.getCatDesc()==null?"":objIssue.getCatDesc()%></font>
  						</td>
  					</tr>
  		<%	
  				}
  			}
  		%>    			
	</table>
	</form>	
	<div style="text-align: center; margin-bottom: 5px;">	
			<a class="btn_red" href="javascript:history.back();">Prev</a> 
			<a class="btn_red" style="margin-left: 10px;" href="javascript:action_func1();">Next</a> 	
	</div>
	<%@ include file="canon_E193_csBottomInc.jsp" %>
	</div>