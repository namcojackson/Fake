
<!-- $Header: canon_E193_csBIssueList.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csBIssueList - Billing issues.
 |   
 | DESCRIPTION
 |   Details of the billing issue.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	09/26/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 18-Dec-2006    Kireet K Bollam   ITG# 73987 : CBS Consolidation Changes
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
		hPath = strBIssueListT1;
	else
		if(hPath.indexOf(strBIssueListT1) < 0)
			hPath = hPath + " -> " + strBIssueListT1;
	
%>
<%@ include file="canon_E193_csMenuInc.jsp" %>

<script language="JavaScript">
	
	function onSelect(val1, val2, val3, val4) {
		var objForm = document.billingIssueForm;
		objForm.hCatId.value = val1;
		objForm.hParentCatId.value = val2;
		objForm.hCatDesc.value = val3;
		objForm.hCatCode.value = val4;
	}
	
	function action_func1() {
		resetErroMsg();
		var objForm = document.billingIssueForm;
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
			var catCode = objForm.hCatCode.value;
			if(catCode == "TAX") objForm.action = "canon_E193_csBTaxIssue.jsp";
			else if(catCode == "PO") objForm.action = "canon_E193_csBPOIssue.jsp";
			else if(catCode == "OTHERS") objForm.action = "canon_E193_csBOthersIssue.jsp";
			else if(catCode == "BILLING") objForm.action = "canon_E193_csEIIBController.jsp";
			else if(catCode == "FREIGHT") objForm.action = "canon_E193_csBFreightIssue.jsp";
			else objForm.action = "canon_E193_csErrorPage.jsp";
			objForm.submit();
		}
	}
	
	/*function InvoiceDetails(val1) {
		var vLink = "canon_E193_csActualInvoiceController.jsp?InvNo=" + val1;
		var vWin = window.open(vLink, "newwin","height=300,width=900,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
		vWin.focus();
	}*/
	
//-->
</script>
<%
	//Get Org ID
	
	int iOrgId = objCiSession.getOrgId();
	
	Object objAcct = session.getAttribute("objSessionAcctInfo");
	Canon_E193_AcctInfoObj objSessionAcctInfo = new Canon_E193_AcctInfoObj();
	
	
	if(objAcct != null) 
		objSessionAcctInfo = (Canon_E193_AcctInfoObj)objAcct;
	String strIssueType = request.getParameter("InvSource")==null?"":request.getParameter("InvSource");
	// getting the data from database
	ArrayList alIssueList = new ArrayList();
	
	      
	/* ITG# 73987 : Begin */	
	//String strRegionCode = (String)objCiSession.getRegionCode();	
	String strRegionCode = (String)request.getAttribute("strRegionCode");
	//System.out.println("strRegionCode in issueList page is" + strRegionCode);
        /* ITG# 73987 : End */
      //to be removed later once session has the info  
        if("".equals(strRegionCode) || strRegionCode == null)
        	strRegionCode = "EAST_REGION";
	
        System.out.println("Before getIssueList  CALL " +iOrgId +" - "+ strRegionCode +" - "+  strIssueType +" -  0" );
	
        alIssueList = objIssueList.getIssueList(iOrgId, strRegionCode, strIssueType, 0);
       
        /*
       try{ 
    	   alIssueList = objIssueList.getIssueList(iOrgId, strRegionCode, strIssueType, 0);
       }catch(Exception e){
    	   System.out.println("MESSAGE : "+e.getMessage());
    	   e.printStackTrace();
       }
        */
        
        System.out.println("After Call "+alIssueList.size());
	
	
	String strInvNo = request.getParameter("invoiceNumber")==null?"":request.getParameter("invoiceNumber");
	

%> 
<div id="main_content">
	<div id="page_top">
		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strBIssueListT1%></h1>
		<div class="breadcrumb"><%=hPath%>
		</div>		
	</div>	
	<form name="billingIssueForm" method="post">

	<input type="hidden" name="hPageFrom" value="BIssueList">
	<input type="hidden" name="nextPage" value="">
	<input type="hidden" name="hCatId" value="">
	<input type="hidden" name="hParentCatId" value="">
	<input type="hidden" name="hParentCatCode" value="<%=strIssueType%>">
	<input type="hidden" name="hCatCode" value="">
	<input type="hidden" name="hCatDesc" value="">
	<input type="hidden" name="InvSource" value="<%=strIssueType%>">
	
	<input type="hidden" name="iTicketId" value="<%=request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId")%>">

	<input type="hidden" name="origName" value="<%=request.getParameter("origName")%>">
	<input type="hidden" name="origPhNo" value="<%=request.getParameter("origPhNo")%>">
	<input type="hidden" name="origEmailAddress" value="<%=request.getParameter("origEmailAddress")%>">
	<input type="hidden" name="origCheckbox" value="<%=request.getParameter("origCheckbox")%>">
	<input type="hidden" name="origType" value="<%=request.getParameter("origType")%>">
	<input type="hidden" name="sourceType" value="<%=request.getParameter("sourceType")%>" >		
	<input type="hidden" name="custName" value="<%=request.getParameter("custName")%>">
	<input type="hidden" name="custPhNo" value="<%=request.getParameter("custPhNo")%>">
	<input type="hidden" name="custEmailAddress" value="<%=request.getParameter("custEmailAddress")%>">
	
	<input type="hidden" name="recurProb" value="<%=request.getParameter("recurProb")%>">
	<input type="hidden" name="probType" value="<%=request.getParameter("probType")%>">
	<input type="hidden" name="val1" value="<%=request.getParameter("val1")%>">
	
	<input type="hidden" name="selAcctId" value="<%=request.getParameter("selAcctId")%>">
	<input type="hidden" name="selLocId" value="<%=request.getParameter("selLocId")%>">
	<input type="hidden" name="selAcctName" id="selAcctName" value="<%=request.getParameter("selAcctName")%>">
	<input type="hidden" name="selAcctNum" value="<%=request.getParameter("selAcctNum")%>">

	<input type="hidden" name="strPurchageOrd" value="<%=request.getParameter("strPurchageOrd")%>">
	<input type="hidden" name="strStatus" value="<%=request.getParameter("strStatus")%>">
	<input type="hidden" name="iTrxId" value="<%=request.getParameter("iTrxId")%>">
	<input type="hidden" name="iTaxOrig" value="<%=request.getParameter("iTaxOrig")%>">
	<input type="hidden" name="iFreightOrig" value="<%=request.getParameter("iFreightOrig")%>">
	<input type="hidden" name="iBillToSiteUseId" value="<%=request.getParameter("iBillToSiteUseId")%>">
	<input type="hidden" name="iShipToSiteUseId" value="<%=request.getParameter("iShipToSiteUseId")%>">
	<input type="hidden" name="invoiceNumber" value="<%=strInvNo%>">
	
	<input type="hidden" name="strContractNo" value="<%=request.getParameter("strContractNo")==null?"":request.getParameter("strContractNo")%>">
	<input type="hidden" name="strContactNoMod" value="<%=request.getParameter("strContactNoMod")==null?"":request.getParameter("strContactNoMod")%>">
	<input type="hidden" name="strOrderNo" value="<%=request.getParameter("strOrderNo")==null?"":request.getParameter("strOrderNo")%>">
	<input type="hidden" name="strOrderType" value="<%=request.getParameter("strOrderType")==null?"":request.getParameter("strOrderType")%>">

	<input type="hidden" name="strInvoiceType" value="<%=request.getParameter("strInvoiceType")==null?"":request.getParameter("strInvoiceType")%>">
	<input type="hidden" name="strExpirationDate" value="<%=request.getParameter("strExpirationDate")==null?"":request.getParameter("strExpirationDate")%>">
	<input type="hidden" name="strContractStatus" value="<%=request.getParameter("strContractStatus")==null?"":request.getParameter("strContractStatus")%>">
	<input type="hidden" name="strServiceBranch" value="<%=request.getParameter("strServiceBranch")==null?"":request.getParameter("strServiceBranch")%>">
	<input type="hidden" name="strBasePeriod" value="<%=request.getParameter("strBasePeriod")==null?"":request.getParameter("strBasePeriod")%>">
	<input type="hidden" name="strOveragePeriod" value="<%=request.getParameter("strOveragePeriod")==null?"":request.getParameter("strOveragePeriod")%>">
	<input type="hidden" name="strCount" value="<%=request.getParameter("strCount")==null?"":request.getParameter("strCount")%>">
	<input type="hidden" name="consolidateBillNum" value="<%=request.getParameter("consolidateBillNum")==null?"":request.getParameter("consolidateBillNum")%>">
	<input type="hidden" name="vBillType" value="<%=request.getParameter("vBillType")%>">
	<input type="hidden" name="hPath" value="<%=hPath%>">
	<table class="request-service" style="align:center;"  cellspacing="0">	
	<tr>  
	<% if(objSessionAcctInfo != null) { %>
		<tr><td height="10" colspan="2"></td></tr>
   		<tr>
   			<td colspan="2"><font class="promptReadOnly">Customer Details:</font></td>
   		</tr>
		<!-- <tr><td height="10" colspan="2"></td></tr> -->
   		<tr>
   			<td width="10">&nbsp;</td>
	    	<td>
		    	<table cellspacing="1" class="request-service">
			 		<tr>
						<th>Account Name</th>
						<th>Account Number</th>
						<th>Contact Name</th>
						<th>Contact Number</th>		
					</tr>
			   		<tr >
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
							<%String strDisplayContName = "";
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
		     	</table>   
		  	</td>
  		</tr>
  		<% } %>
		<!-- <tr><td height="10" colspan="2"></td></tr> -->
   		<tr>
   			<td colspan="2"><font class="promptReadOnly">Selected Invoice: <a href="#" onClick="javascript:InvoiceDetailsPDF('<%=strInvNo%>');"><%=strInvNo%></a><%=strInvoiceNoN1%></font></td>
   		</tr>
		<!-- <tr><td height="10" colspan="2"></td></tr> -->
		<tr>
			<td class="tableQuestionCell" colspan="2"><font class="promptReadOnly"><b><%=strBIssueListQ1%></b></font></td>
		</tr>
		<% if(alIssueList == null || alIssueList.size() <= 0) { %>
		<tr>
			<td class="tableQuestionCell" colspan="2"><p style="color: red; font-size: 12">The invoice number <%=strInvNo%> is not eligible for ticket creation of type Billing.</p></td>
		</tr>
		<%} %> 
		<%  
			//System.out.println("hello" + alIssueList  + "size is" + alIssueList.size());
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
  					//System.out.println("strCatIdL" + strCatIdL  + "objIssue is" + objIssue);	
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
  							<input type="radio" name="rIssue" value="<%=objIssue.getCatId()%>" <%=strDisabled%> onClick="onSelect('<%=objIssue.getCatId()%>', '<%=objIssue.getParentCatId()%>', '<%=objIssue.getCatDesc()%>', '<%=objIssue.getCatCode()%>');">
  							<font class="promptReadOnly"><%=objIssue.getCatDesc()==null?"":objIssue.getCatDesc()%></font>
  						</td>
  					</tr>
  		<%	
  				}
  			}
  		%>		
		</table>
	</form>
	<div>
	</div>
	<div style="text-align: center;">	
	<a class="btn_red" href="javascript:history.back();">Prev</a>
	<% if(alIssueList != null && alIssueList.size() > 0) { %>
	<a class="btn_red" style="margin-left: 10px;" href="javascript:action_func1();">Next</a>
	<% } else { %>
	
	<a class="btn_disabled" style="margin-left: 10px;" href="#">Next</a>
	<% } %>
	</div>
<%@ include file="canon_E193_csBottomInc.jsp" %>	
	</div>
