<!-- $Header: canon_E193_csEISupplyLineDetails.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csEISupplyLineDetails.jsp - Supply line details
 |   
 | DESCRIPTION
 |   Details of the incorrect billing issue details.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	10/21/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 04/21/2008   Venkat V			To avoid creating duplicate tickets
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<%@ page import="com.canon.oracle.custapp.util.CanonCustAppUtil" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_InvoiceLinesObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objIBList" scope="request" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objIssueList" scope="request" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Severity" id="objSeverityDao" scope="page"/>
<%@ include file="canon_E193_csTopInc.jsp" %>
 
<%
	// Menu Prompt
	strPageName = "Enter & Inquiry";
	
	// Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");
	String hPath = request.getParameter("hPath");
	if (hPath == null)
		hPath = strSupplyLineDetailsT1;
	else
		if(hPath.indexOf(strSupplyLineDetailsT1) < 0)
			hPath = hPath + " -> " + strSupplyLineDetailsT1;
			
			 String hParentCatCode=request.getParameter("hParentCatCode")==null?"":request.getParameter("hParentCatCode");			
%>
<%@ include file="canon_E193_csMenuInc.jsp" %>

<!-- 21-APR-2008, Venkat Velagala, Start -->
<script language="javascript">
window.history.forward(1);
</script>
<!-- 21-APR-2008, Venkat Velagala, End -->

<script language="JavaScript">
	function action_func1() {
		resetErroMsg();
		var objForm = document.supplyLineForm;
		if(objForm.iLineId.value != '' && objForm.iLineId.value != '0') {
			if(fnValidateCheckbox(objForm) && fnValidateField(objForm)) {
				var notes = objForm.notes.value;
				var existingNotes = objForm.notesDisplay.value;
				if(notes == '') {
					displayErrorMsg("Please provide full details in comments");
					objForm.notes.focus();				
				}else if((notes.length + existingNotes.length) > 32000){
					displayErrorMsg("Total Size of Notes for an Issue Line is restricted to 32000 characters. Please use the attachment facility to add additional details.");
					objForm.notes.focus();
				}else{
					objForm.flag.value = "update";
					objForm.readData.value = "yes";
					objForm.submit();
				}
			}
		}else{
			var notes = objForm.notes.value;
			if(objForm.reasonCdDesc.value == '') {
				displayErrorMsg("Please choose a reason code");
            }else if(notes == '') {
            	displayErrorMsg("Please provide full details in comments");
				objForm.notes.focus();				
			}else if(notes.length > 32000){
				displayErrorMsg("Total Size of Notes for an Issue Line is restricted to 32000 characters. Please use the attachment facility to add additional details.");
				objForm.notes.focus();
			}else if(fnValidateCheckbox(objForm) && fnValidateField(objForm)) {
				objForm.submit();
			}
		}
	}
	
	/*function InvoiceLineDetails(pInvNo) {
		var vLink = "canon_E193_csActualInvoiceController.jsp?InvNo=" + pInvNo;
		var vWin = window.open(vLink, "newwin","height=300,width=900,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
		vWin.focus();
	}
	
	function fnGetReasonCode() {
		var val = document.supplyLineForm.hParentCatCode.value;
		var vWin = window.open("canon_E193_csTicketReasonCodeP.jsp?reasonCode="+val, "newwin","height=300,width=940,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
		vWin.focus();
	}*/
	
	function getReasonCode(code, reason) {
		var objForm = document.supplyLineForm;
		objForm.reasonCdDesc.value = reason;
		objForm.hReasonCd.value = code;
	}
	  
	function fnValidateCheckbox(objForm) {
		resetErroMsg();
		var len = 0;
		var isChecked = true;
		var errMsg = "please select a credit";
		if(objForm.supplyChkCredit != null) len = objForm.supplyChkCredit.length;
		if(len > 0) {
			for(i=0; i<len; i++) {
				if((objForm.supplyChkCredit[i].checked && objForm.supplySelCredit[i].value == '') ||
					(!objForm.supplyChkCredit[i].checked && objForm.supplySelCredit[i].value != '')) {
					if(objForm.supplyChkCredit[i].checked && objForm.supplySelCredit[i].value == '') {
						errMsg = "Please select a credit reason";
						objForm.supplySelCredit[i].focus();
					}
					isChecked = false;
					break;
				}
			}
		}else if(objForm.supplyChkCredit != null) {
			if((!objForm.supplyChkCredit.checked && (objForm.supplySelCredit.value == '' || objForm.supplySelCredit.value != '')) ||
				(objForm.supplyChkCredit.checked && objForm.supplySelCredit.value == '')) {
				if(objForm.supplyChkCredit.checked && objForm.supplySelCredit.value == '') {
					errMsg = "Please select a credit reason";
					objForm.supplySelCredit.focus();
				}
				isChecked = false;
			}
		}

		if(!isChecked) {
			displayErrorMsg(errMsg);
			return false;
		}
		return true;
	}
	
	function fnValidateSelectbox(objForm) {
		resetErroMsg();
		var len = 0;
		var isChecked = true;
		if(objForm.supplySelCredit != null) len = objForm.supplySelCredit.length;
		if(len > 0) {
			for(i=0; i<len; i++) {
				if(objForm.supplySelCredit[i].value != '') {
					isChecked = false;
					break;
				}
			}
		}else if(objForm.supplySelCredit != null && objForm.supplySelCredit.value != '') isChecked = false;
		
		if(isChecked) {
			displayErrorMsg("please select a credit reasons");
			return false;			
		}
		return true;
	}
	
	function fnValidateField(objForm) {
		resetErroMsg();
		var len = 0;
		var isValidField = true, isFormField = true, isDotValidate = false;
		if(objForm.supplyCreditAmt != null) len = objForm.supplyCreditAmt.length;
		
		var index = objForm.elements.length;
		for(i=0; i<index; i++) {
			if(objForm.elements[i].type == "text" && objForm.elements[i].name != "reasonCdDesc" && objForm.elements[i].value != '') {
				isFormField = false;
				if(objForm.elements[i].name == "supplyCreditQty" && objForm.elements[i].value.indexOf(".") != -1) {
					displayErrorMsg("Should not be allowed dot for this field");
					objForm.elements[i].select();
					objForm.elements[i].focus();
					isDotValidate = true;
					break;
				}
			}
		}
		if(isFormField) {
			displayErrorMsg("Please enter any value");
		}
		if(isFormField || isDotValidate) return false;
		
		if(len > 0) {
			for(i=0; i<len; i++) {
				if(objForm.supplyChkCredit[i].checked && (isNaN(objForm.supplyCreditQty[i].value) || isNaN(objForm.supplyCreditAmt[i].value))) {
					displayErrorMsg("Enter numbers only");
					if(objForm.supplyCreditQty[i].value != '') {
						objForm.supplyCreditQty[i].select();
						objForm.supplyCreditQty[i].focus();
					}else{
						objForm.supplyCreditAmt[i].select();
						objForm.supplyCreditAmt[i].focus();
					}
					isValidField = false;
					break;
				}else if(objForm.supplyChkCredit[i].checked && objForm.supplyCreditQty[i].value == '' && objForm.supplyCreditAmt[i].value == '') {
					displayErrorMsg("Please enter either Credit Qty or Credit Amount");
					objForm.supplyCreditQty[i].select();
					objForm.supplyCreditQty[i].focus();
					isValidField = false;
					break;
				}else if(objForm.supplyChkCredit[i].checked && objForm.supplyCreditQty[i].value != '' && objForm.supplyCreditAmt[i].value != '') {
					displayErrorMsg("Please enter either Credit Qty or Credit Amount");
					objForm.supplyCreditQty[i].select();
					objForm.supplyCreditQty[i].focus();
					isValidField = false;
					break;
				}
			}
		}else if(objForm.supplyCreditQty != null && objForm.supplyChkCredit.checked && (isNaN(objForm.supplyCreditQty.value) || isNaN(objForm.supplyCreditAmt.value))) {
			displayErrorMsg("Enter numbers only");
			if(objForm.supplyCreditQty.value != '') {
				objForm.supplyCreditQty.select();
				objForm.supplyCreditQty.focus();
			}else{
				objForm.supplyCreditAmt.select();
				objForm.supplyCreditAmt.focus();
			}
			isValidField = false;
		}else if(objForm.supplyCreditAmt != null && objForm.supplyChkCredit.checked && objForm.supplyCreditAmt.value == '' && objForm.supplyCreditQty.value == '') {
			displayErrorMsg("Please enter either Credit Qty or Credit Amount");
			objForm.supplyCreditQty.select();
			objForm.supplyCreditQty.focus();
			isValidField = false;
		}else if(objForm.supplyCreditAmt != null && objForm.supplyChkCredit.checked && objForm.supplyCreditAmt.value != '' && objForm.supplyCreditQty.value != '') {
			displayErrorMsg("Please enter either Credit Qty or Credit Amount");
			objForm.supplyCreditQty.select();
			objForm.supplyCreditQty.focus();
			isValidField = false;
		}
		return isValidField;
	}
	
	function fnSelAll(obj) {
		var objForm = document.supplyLineForm;
		var len = 0;
		if(objForm.supplyChkCredit != null) len = objForm.supplyChkCredit.length;
		if(obj.checked) {
			retChecked(objForm, len, true);
		}else{
			retChecked(objForm, len, false);
		}
	}
	
	function fnSel(obj, idx) {
		var objForm = document.supplyLineForm;
		var len = objForm.supplyChkCredit.length;
		if(!obj.checked && objForm.chkAll.checked) {
			objForm.chkAll.checked = false;
			if(len > 0) {
				objForm.supplyCreditQty[idx].readOnly = true;
				objForm.supplyCreditAmt[idx].readOnly = true;
				objForm.supplyCreditQty[idx].value = "";
				objForm.supplyCreditAmt[idx].value = "";
			}else{
				objForm.supplyCreditQty.readOnly = true;
				objForm.supplyCreditAmt.readOnly = true;
				objForm.supplyCreditQty.value = "";
				objForm.supplyCreditAmt.value = "";
			}
		}else if(obj.checked && checkRemP(objForm, len)) {
			objForm.chkAll.checked = true;
		}
	}
	
	function checkRemP(objForm, len) {
		var isChecked = true;
		if(len > 0) {
			for(i=0; i<len; i++) {
				if(!objForm.supplyChkCredit[i].checked) {
					isChecked = false;
					objForm.supplyCreditQty[i].readOnly = true;
					objForm.supplyCreditAmt[i].readOnly = true;
					objForm.supplyCreditQty[i].value = "";
					objForm.supplyCreditAmt[i].value = "";
					//break;
				}else{
					objForm.supplyCreditQty[i].readOnly = false;
					objForm.supplyCreditAmt[i].readOnly = false;
				}
			}
		}else if(!objForm.supplyChkCredit.checked) {
			isChecked = false;
			objForm.supplyCreditQty.readOnly = true;
			objForm.supplyCreditAmt.readOnly = true;
			objForm.supplyCreditQty.value = "";
			objForm.supplyCreditAmt.value = "";
		}else if(objForm.supplyChkCredit.checked) {
			objForm.supplyCreditQty.readOnly = false;
			objForm.supplyCreditAmt.readOnly = false;
		}
		return isChecked;
	}
	
	function retChecked(objForm, len, bool) {
		if(len > 0) {
			for(i=0; i<len; i++) {
				objForm.supplyChkCredit[i].checked = bool;
				if(bool) {
					objForm.supplyCreditQty[i].readOnly = false;
					objForm.supplyCreditAmt[i].readOnly = false;
				}else{
					objForm.supplyCreditQty[i].readOnly = true;
					objForm.supplyCreditAmt[i].readOnly = true;
					objForm.supplyCreditQty[i].value = "";
					objForm.supplyCreditAmt[i].value = "";
				}
			}
		}else if(objForm.supplyChkCredit != null) {
			objForm.supplyChkCredit.checked = bool;
			if(bool) {
				objForm.supplyCreditQty.readOnly = false;
				objForm.supplyCreditAmt.readOnly = false;
			}else{
				objForm.supplyCreditQty.readOnly = true;
				objForm.supplyCreditAmt.readOnly = true;
				objForm.supplyCreditQty.value = "";
				objForm.supplyCreditAmt.value = "";
			}
		}
	}
	
	function fnChgCreditAll(obj) {
		var objForm = document.supplyLineForm;
		var len = 0;
		if(objForm.supplyCreditQty != null) len = objForm.supplyCreditQty.length;
		if(len > 0) {
			for(i=0; i<len; i++) {
				for(j=0; j<obj.length; j++) {
					if(obj.options[j].selected) {
						objForm.supplySelCredit[i][j].selected = true;
						break;
					}
				}
			}
		}else if(objForm.supplySelCredit != null) {
			for(j=0; j<obj.length; j++) {
				if(obj.options[j].selected) {
					objForm.supplySelCredit[j].selected = true;
					break;
				}
			}
		}
	}
	
	$(function (){
		var objForm = document.supplyLineForm;
		
		if(objForm.supplyChkCredit != null)
        {
		    len = objForm.supplyChkCredit.length;
		    if(checkRemP(objForm, len))
		    {
		    	objForm.chkAll.checked = true;
		    }
        }
    });
	
//-->
</script>
<%
	//Get Org ID
	int iOrgId = objCiSession.getOrgId();
	
	Object objAcct = session.getAttribute("objSessionAcctInfo");
	Canon_E193_AcctInfoObj objSessionAcctInfo = null;
	if(objAcct != null) objSessionAcctInfo = (Canon_E193_AcctInfoObj)objAcct;
	
	ArrayList alSupplyList = (ArrayList)objIBList.getArrayList();
  	ArrayList alIssueList = (ArrayList)objIssueList.getArrayList();

  	Canon_E193_InvoiceLinesObj objInvLines = null;
  	Canon_E193_IssueListObj objIL = null;
  	
	// getting severity codes for drop down list
	ArrayList alSeverityList = objSeverityDao.getSeverity();
	
	String strInvSource = request.getParameter("InvSource")==null?"":request.getParameter("InvSource");
	ArrayList alReasonCd = objSeverityDao.getCreditReasonCodes(strInvSource);
	
	String strInvNo = request.getParameter("invoiceNumber")==null?"":request.getParameter("invoiceNumber");
%>
 <div id="main_content">
	<div id="page_top">
	   	<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strSupplyLineDetailsT1%></h1>
		<div class="breadcrumb"><%=hPath%></div>		
	</div>
	<form name="supplyLineForm" action="canon_E193_csTicketSummaryB1Controller.jsp" method="post">

	<input type="hidden" name="hPageFrom" value="EISupplyLineDetails">
	<input type="hidden" name="nextPage" value="">
	<input type="hidden" name="hReasonCd" value="<%=request.getParameter("hReasonCd")==null?"":request.getParameter("hReasonCd")%>">
	<input type="hidden" name="hCatId" value="<%=request.getParameter("hCatId")%>">
	<input type="hidden" name="hParentCatId" value="<%=request.getParameter("hParentCatId")%>">
	<input type="hidden" name="hParentCatCode" value="<%=request.getParameter("hParentCatCode")%>">
	<input type="hidden" name="hCatCode" value="<%=request.getParameter("hCatCode")%>">
	<input type="hidden" name="hCatDesc" value="<%=request.getParameter("hCatDesc")%>">
	<input type="hidden" name="InvSource" value="<%=strInvSource%>">

	<input type="hidden" name="iTicketId" value="<%=request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId")%>">
	<input type="hidden" name="iLineId"  id="iLineId" value="<%=request.getParameter("iLineId")==null?"":request.getParameter("iLineId")%>">
	<input type="hidden" name="flag" value="">
	<input type="hidden" name="readData" value="">

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
	<input type="hidden" name="selAcctName" value="<%=request.getParameter("selAcctName")%>" >
	<input type="hidden" name="selAcctNum" value="<%=request.getParameter("selAcctNum")%>" >

	<input type="hidden" name="strPurchageOrd" value="<%=request.getParameter("strPurchageOrd")%>">
	<input type="hidden" name="strStatus" value="<%=request.getParameter("strStatus")%>">
	<input type="hidden" name="iTrxId" value="<%=request.getParameter("iTrxId")%>">
	<input type="hidden" name="iTaxOrig" value="<%=request.getParameter("iTaxOrig")%>">
	<input type="hidden" name="iFreightOrig" value="<%=request.getParameter("iFreightOrig")%>">
	<input type="hidden" name="iBillToSiteUseId" value="<%=request.getParameter("iBillToSiteUseId")%>">
	<input type="hidden" name="iShipToSiteUseId" value="<%=request.getParameter("iShipToSiteUseId")%>">
	<input type="hidden" name="invoiceNumber" value="<%=strInvNo%>">

	<input type="hidden" name="strContractNo" value="<%=request.getParameter("strContractNo")%>">
	<input type="hidden" name="strContactNoMod" value="<%=request.getParameter("strContactNoMod")%>">
	<input type="hidden" name="strOrderNo" value="<%=request.getParameter("strOrderNo")%>">
	<input type="hidden" name="strOrderType" value="<%=request.getParameter("strOrderType")%>">
<%
	String strInvoiceType = request.getParameter("strInvoiceType")==null?"":request.getParameter("strInvoiceType");
	String strExpirationDate = request.getParameter("strExpirationDate")==null?"":request.getParameter("strExpirationDate");
	String strContractStatus = request.getParameter("strContractStatus")==null?"":request.getParameter("strContractStatus");
	String strServiceBranch = request.getParameter("strServiceBranch")==null?"":request.getParameter("strServiceBranch");
	String strBasePeriod = request.getParameter("strBasePeriod")==null?"":request.getParameter("strBasePeriod");
	String strOveragePeriod = request.getParameter("strOveragePeriod")==null?"":request.getParameter("strOveragePeriod");
	String strCount = request.getParameter("strCount")==null?"":request.getParameter("strCount");
%>
	<input type="hidden" name="strInvoiceType" value="<%=strInvoiceType%>">
	<input type="hidden" name="strExpirationDate" value="<%=strExpirationDate%>">
	<input type="hidden" name="strContractStatus" value="<%=strContractStatus%>">
	<input type="hidden" name="strServiceBranch" value="<%=strServiceBranch%>">
	<input type="hidden" name="strBasePeriod" value="<%=strBasePeriod%>">
	<input type="hidden" name="strOveragePeriod" value="<%=strOveragePeriod%>">
	<input type="hidden" name="strCount" value="<%=strCount%>">
	<input type="hidden" name="hPath" value="<%=hPath%>">
	<table class="request-service" style="align:center;" cellpadding="1" cellspacing="0" border="0">			 
	<% if(objSessionAcctInfo != null) { %>
		<tr><td height="10" colspan="3"></td></tr>
   		<tr>
   			<td class="tdTableCellStyle" colspan="3"><font class="promptReadOnly">Customer Details:</font></td>
   		</tr>
   		<tr>
			    <td colspan="3">
			       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
				   </div>
			   </td>
			</tr>
   		<tr>
   			<td width="10">&nbsp;</td> 		
	    	<td colspan="2">
		    	<table cellspacing="1" cellpadding="2" class="request-service">
			 		<tr >
						<th>Account Name</th>
						<th>Account Number</th>
						<th>Contact Name</th>
						<th>Contact Number</th>		
					</tr>
			   		<tr class="tdTableCellStyle">
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
		     	</table>   
		  	</td>
  		</tr>
  		<% } %>
		<tr><td height="10" colspan="3"></td></tr>
   		<tr>
   		         <td class="tdTableCellStyle" colspan="3"><font class="promptReadOnly">Selected Invoice: <a href="#" onClick="javascript:void InvoiceDetailsPDF('<%=strInvNo%>');"><%=strInvNo%></a><%=strInvoiceNoN1%></font></td>
   			<%-- <td class="tdTableCellStyle" colspan="3"><font class="promptReadOnly">Selected Invoice: <a href="#" onClick="javascript:InvoiceLineDetailsPDF('<%=strInvNo%>');"><%=strInvNo%></a><%=strInvoiceNoN1%></font></td> --%>
   		</tr>
		<tr><td height="10" colspan="3"></td></tr>
  		<tr>
   			<td class="tableQuestionCell" colspan="3">
   				<font class="promptReadOnly" color="#FF0000">
   					<b><%=strSupplyLineDetailsN1%></b>
   				</font>
   			</td>
   		</tr>
		<tr><td height="10" colspan="3"></td></tr>
  		<tr>
  			<td width="10">&nbsp;</td>
  			<td colspan="2">
  			<% if(alSupplyList != null && alSupplyList.size() > 5) { %>
  			<div style="overflow:auto; width:875; height:250;">
  			<% } %>
  				<table cellspacing="1" class="request-service">
  					<tr>
  						<th>
  							<input type="checkbox" name="chkAll" value="All" onClick="javascript:fnSelAll(this)"> Credit All
  							<select name = "selCreditAll" size="1" class="searchBarLink" onchange="javascript:fnChgCreditAll(this);">
  								<option value="">Select One</option>
  							<%
  								if(alReasonCd != null && alReasonCd.size() > 0) {
  									String strCode = "";
  									for(int i=0; i<alReasonCd.size(); i++) {
  										strCode = alReasonCd.get(i).toString();
  							%>
  										<option value="<%=strCode%>"><%=strCode%></option>
  							<%
  									}
  								}
  							%>
  							</select>
  						</th>
  						<th>Item Number#</th>
  						<th>Description</th>
  						<th>Quantity</th>
  						<th>Unit Price</th>
  						<th>Extended Price</th>
  					<%
  						if(alIssueList != null && alIssueList.size() > 0) {
  							for(int i=0; i<alIssueList.size(); i++) {
  								objIL = (Canon_E193_IssueListObj)alIssueList.get(i);
  					%>
								<input type="hidden" name="supplyCatId" value="<%=objIL.getCatId()%>">
								<th><%=objIL.getCatDesc()%></th>
  					<%
  							}
  						}
  					%>
  					</tr>
  					<%
  						if(alSupplyList != null && alSupplyList.size() > 0) {
							String strCode = "", strInvCode = "";
  							for(int i=0; i<alSupplyList.size(); i++) {
  								objInvLines = (Canon_E193_InvoiceLinesObj)alSupplyList.get(i);
  								strInvCode = objInvLines.getStrReasonCd()==null?"":objInvLines.getStrReasonCd();
  					%>
  								<input type="hidden" name="supplyCustTrxLineId" value="<%=objInvLines.getCustTrxLineId()%>">
  								<input type="hidden" name="supplyQuantity" value="<%=objInvLines.getQuantity()%>">
  								<input type="hidden" name="supplyUnitPrice" value="<%=objInvLines.getUnitPrice()%>">  								
  								<tr class="tdTableCellStyle">
  									<td>
  										<input type="checkbox" name="supplyChkCredit" value="<%=i%>" onClick="javascript:fnSel(this, <%=i%>)" <%=objInvLines.isChekcboxCheck()?"checked":""%>> Credit
			  							<select name = "supplySelCredit" size="1" class="searchBarLink">
  											<option value="">Select One</option>
		  							<%
		  								if(alReasonCd != null && alReasonCd.size() > 0) {
		  									for(int j=0; j<alReasonCd.size(); j++) {
		  										strCode = alReasonCd.get(j).toString();
		  							%>
		  										<option value="<%=strCode%>" <%=strInvCode.equals(strCode)?"selected":""%>><%=strCode%></option>
		  							<%
		  									}
		  								}
		  							%>
			  							</select>
  									</td>
  									<td><%=objInvLines.getItemNo()==null?"":objInvLines.getItemNo()%></td>
  									<td><%=objInvLines.getLineDesc()==null?"":objInvLines.getLineDesc()%></td>
  									<td><%=String.valueOf(objInvLines.getQuantity())%></td>
  									<td>$<%=CanonCustAppUtil.getDoubleWithFormat(objInvLines.getUnitPrice())%></td>
  									<td>$<%=CanonCustAppUtil.getDoubleWithFormat(objInvLines.getLineTotal())%></td>
  									<td><input type="text" name="supplyCreditQty" class="inTxt" size="10" value="<%=objInvLines.getStrCreditQty()==null?"":objInvLines.getStrCreditQty()%>" <%=objInvLines.isChekcboxCheck()?"":"readonly"%>></td>
  									<td><input type="text" name="supplyCreditAmt" class="inTxt" size="10" value="<%=objInvLines.getStrCreditAmt()==null?"":objInvLines.getStrCreditAmt()%>" <%=objInvLines.isChekcboxCheck()?"":"readonly"%>></td>
  								</tr>  								
				<%
							}
						}else{
				%>
							<tr class="tdTableCellStyle">
								<td colspan="8">
									<font class="promptReadOnly"><b>Invoice lines not found.</b></font>
								</td>
							</tr>
				<%		} %>
  				</table>
  			<% if(alSupplyList != null && alSupplyList.size() > 5) { %>
  				</div>
  			<% } %>
  			</td>
  		</tr>
		<tr><td height="10" colspan="3"></td></tr>
   		<tr>
   			<td class="tableQuestionCell" colspan="3">
   				<font class="promptReadOnly" color="#FF0000">
   					<b><%=strSupplyLineDetailsN2%></b>
   				</font>
   			</td>
   		</tr>
		<tr><td height="10" colspan="3"></td></tr>
  		<tr>
  			<td width="10">&nbsp;</td>
  			<td colspan="3">
  				<table cellspacing="1" class="customer-care">
  					<tr>
  						<th><a href="javascript:void(0)" onClick="getLovWithValue('canon_E193_csTicketReasonCodeP.jsp','reasonCode','<%=hParentCatCode%>','Reason Code');">Reason Code</a><%=strReasonCodeN1%></th>
  						<th>Urgency</th>
  					</tr>
  					<tr>
  						<td><input type="text" name="reasonCdDesc" id="reasonCdDesc" onClick="getLovWithValue('canon_E193_csTicketReasonCodeP.jsp','reasonCode','<%=hParentCatCode%>','Reason Code');" value="<%=request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc")%>" class="inTxt required" style="background-color:#ffff00" size="80" readonly=true></td>
  						<td>
  							<select name="severity" size="1" class="searchBarLink">
  							<%
  								if(alSeverityList != null && alSeverityList.size() > 0) {
  									String reqSeverity = request.getParameter("severity")==null?"":request.getParameter("severity");
  									String code = "";
  									for(int i=0; i<alSeverityList.size(); i++) {
  										code = alSeverityList.get(i).toString();
  							%>
  										<option value="<%=code%>" <%=code.equals(reqSeverity)?"selected":""%>><%=code%></option>
  							<%		}
  								}
  							%>
  							</select>
  						</td>
  					</tr>
  				</table>
  			</td>
  		</tr>
		<tr><td height="10" colspan="3"></td></tr>
   		<tr>
   			<td class="tableQuestionCell" colspan="3">
   				<font class="promptReadOnly" color="#FF0000">
   					<b><%=strSupplyLineDetailsN3%></b>
   				</font>
   			</td>
   		</tr>
		<tr><td height="10" colspan="3"></td></tr>
  		<%
  			String strNotesDisplay = request.getParameter("notes")==null?"":request.getParameter("notes");
  			if(!"".equalsIgnoreCase(strNotesDisplay)) {
  		%>
			<tr>
				<td width="10">&nbsp;</td>
				<td colspan="2"><textarea name="notesDisplay" id="notesDisplay" rows="10" wrap="OFF" class="inTxt required" style="background-color:#ffff00;height:auto; width:800px;" readonly="readonly"><%=strNotesDisplay%></textarea></td>		
			</tr>
		<%	}  %>
		<tr>
			<td width="10">&nbsp;</td>
			<td colspan="2"><textarea name="notes" id="notes" rows="10" wrap="OFF" class="inTxt required" style="background-color:#ffff00;height:auto; width:800px;"></textarea></td>		
		</tr>			
	</table>
	</form>
	<div style="text-align: center;">
	 <a class="btn_red" href="javascript:history.back();">Prev</a>
		<a class="btn_red" href="javascript:action_func1();">Next</a>
	</div>	
		<div id="dlg"></div>
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>