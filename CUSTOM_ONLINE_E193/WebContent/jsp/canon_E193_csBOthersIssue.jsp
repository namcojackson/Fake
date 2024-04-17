<!-- $Header: canon_E193_csOthersIssue.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csOthersIssue.jsp - Others issues
 |   
 | DESCRIPTION
 |   Details of the Others issue.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	10/03/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 04/21/2008   Venkat V			To  avoid creating duplicate tickets.
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Severity" id="objSeverityDao" scope="page"/>
<%@ include file="canon_E193_csTopInc.jsp" %>

<%
	// Menu Prompt
	strPageName = "Enter & Inquiry";
	
	// Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");
	String hPath = request.getParameter("hPath");
	if (hPath == null)
		hPath = strOtherIssueT1;
	else
		if(hPath.indexOf(strOtherIssueT1) < 0)
			hPath = hPath + " -> " + strOtherIssueT1;	
    String hParentCatCode=request.getParameter("hParentCatCode")==null?"":request.getParameter("hParentCatCode");			
%>
<%@ include file="canon_E193_csMenuInc.jsp" %>

<!-- 21-APR-2008, Venkat Velagala, Start -->
<script language="javascript">
window.history.forward(1);
</script>
<!-- 21-APR-2008, Venkat Velagala, End -->

<script language="JavaScript">

	/* function fnGetReasonCode() {
		var val = document.taxIssueForm.hParentCatCode.value;
		var vWin = window.open("canon_E193_csTicketReasonCodeP.jsp?reasonCode="+val, "newwin","height=300,width=940,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
		vWin.focus();
	} */

	function action_func1() {
		resetErroMsg();
		var objForm = document.taxIssueForm;
		if(objForm.iLineId.value != '' && objForm.iLineId.value != '0') {
			var notes = objForm.notes.value;
			var existingNotes = objForm.notesDisplay.value;
			if(objForm.notes.value == '') {
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
		}else{
			var v = objForm.expTaxAmt;
			var notes = objForm.notes.value;
			if(objForm.reasonCdDesc.value == '') {
				displayErrorMsg("Please choose a reason code");
			}else if(notes == '') {
				displayErrorMsg("Please provide full details in comments");
				objForm.notes.focus();
			}else if(notes.length > 32000){
				displayErrorMsg("Total Size of Notes for an Issue Line is restricted to 32000 characters. Please use the attachment facility to add additional details.");
				objForm.notes.focus();
			}else{
				objForm.submit();
			}
		}
	}
	
	function getReasonCode(code, reason) {
		var objForm = document.taxIssueForm;
		objForm.reasonCdDesc.value = reason;
		objForm.hReasonCd.value = code;
	}
	
	/*function InvoiceDetails(val1) {
		var vLink = "canon_E193_csActualInvoiceController.jsp?InvNo=" + val1;
		var vWin = window.open(vLink, "newwin","height=300,width=900,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
		vWin.focus();
	}*/
	
	function action_func1ReasonCode() {
		resetErroMsg();
		var objForm = document.ReasonCodeForm;
		var isSelected = false;
		var val = '';
		var v = 0;
		if(objForm.thdetails != null) {
			v = objForm.thdetails.length;
			if(v > 0) {
				for(i=0; i<v; i++) {
					if(objForm.thdetails[i].checked) {
						isSelected = true;
						val = objForm.thdetails[i].value;
						break;
					}
				}
			}else if(objForm.thdetails.checked) {
				val = objForm.thdetails.value;
				isSelected = true;
			}
		}
		if(!isSelected) {
			displayErrorMsg("Please select the reasonCode");
		}else {		
			getReasonCode(objForm.reasonCode.value, val);
			closeDlg();
		}
	}
	
//-->
</script>
<%
	//Get Org ID
	int iOrgId = objCiSession.getOrgId();
	
	Object objAcct = session.getAttribute("objSessionAcctInfo");
	Canon_E193_AcctInfoObj objSessionAcctInfo = null;
	if(objAcct != null) objSessionAcctInfo = (Canon_E193_AcctInfoObj)objAcct;
	
	// getting severity codes for drop down list
	ArrayList alSeverityList = objSeverityDao.getSeverity();
	
	String strInvNo = request.getParameter("invoiceNumber")==null?"":request.getParameter("invoiceNumber");
%> 

	<div id="main_content">
		<div id="page_top">
	   		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strOtherIssueT1%></h1>
			<div class="breadcrumb"><%=hPath%></div>
		</div>	
	<form name="taxIssueForm" action="canon_E193_csTicketSummaryB2Controller.jsp" method="post">

	<input type="hidden" name="hPageFrom" value="OthersIssue">
	<input type="hidden" name="nextPage" value="">
	<input type="hidden" name="hReasonCd" value="<%=request.getParameter("hReasonCd")==null?"":request.getParameter("hReasonCd")%>">
	<input type="hidden" name="hCatId" value="<%=request.getParameter("hCatId")%>">
	<input type="hidden" name="hParentCatId" value="<%=request.getParameter("hParentCatId")%>">
	<input type="hidden" name="hParentCatCode" value="<%=request.getParameter("hParentCatCode")%>">
	<input type="hidden" name="hCatCode" value="<%=request.getParameter("hCatCode")%>">
	<input type="hidden" name="hCatDesc" value="<%=request.getParameter("hCatDesc")%>">
	<input type="hidden" name="InvSource" value="<%=request.getParameter("InvSource")%>">

	<input type="hidden" name="iTicketId" value="<%=request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId")%>">
	<input type="hidden" name="iLineId"  id="iLineId"  id="iLineId" id="iLineId" value="<%=request.getParameter("iLineId")==null?"":request.getParameter("iLineId")%>">
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
	
	<input type="hidden" name="strInvoiceType" value="<%=request.getParameter("strInvoiceType")==null?"":request.getParameter("strInvoiceType")%>">
	<input type="hidden" name="strExpirationDate" value="<%=request.getParameter("strExpirationDate")==null?"":request.getParameter("strExpirationDate")%>">
	<input type="hidden" name="strContractStatus" value="<%=request.getParameter("strContractStatus")==null?"":request.getParameter("strContractStatus")%>">
	<input type="hidden" name="strServiceBranch" value="<%=request.getParameter("strServiceBranch")==null?"":request.getParameter("strServiceBranch")%>">
	<input type="hidden" name="strBasePeriod" value="<%=request.getParameter("strBasePeriod")==null?"":request.getParameter("strBasePeriod")%>">
	<input type="hidden" name="strOveragePeriod" value="<%=request.getParameter("strOveragePeriod")==null?"":request.getParameter("strOveragePeriod")%>">
	<input type="hidden" name="strCount" value="<%=request.getParameter("strCount")==null?"":request.getParameter("strCount")%>">
	<input type="hidden" name="hPath" value="<%=hPath%>">
	<table class="request-service" style="align:center;" cellspacing="0">
		
		  
	<% if(objSessionAcctInfo != null) { %>
		<tr><td height="10" colspan="3"></td></tr>
   		<tr>
   			<td  colspan="3"><font class="promptReadOnly">Customer Details:</font></td>
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
		    	<table cellspacing="1" class="customer-care">
			 		<tr >
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
		     	</table>   
		  	</td>
  		</tr>
  		<% } %>
		<!-- <tr><td height="10" colspan="3"></td></tr> -->
   		<tr>
   			<td  colspan="3"><font class="promptReadOnly">Selected Invoice: <a href="#" onClick="javascript:InvoiceDetailsPDF('<%=strInvNo%>');"><%=strInvNo%></a><%=strInvoiceNoN1%></font></td>
   		</tr>
		<tr><td height="10" colspan="3"></td></tr>
   		<tr>
   			<td class="tableQuestionCell" colspan="3">
   				<font  color="#FF0000">
   					<b><%=strOtherIssueN1%></b>
   				</font>
   			</td>
   		</tr>
		<!-- <tr><td height="10" colspan="3"></td></tr> -->
  		<tr>
  			<td width="10">&nbsp;</td>
  			<td colspan="2">
  				<table cellspacing="1" class="customer-care">
  					<tr>
  						<th><a href="javascript:void(0)" onClick="getLovWithValue('canon_E193_csTicketReasonCodeP.jsp','reasonCode','<%=hParentCatCode%>','Reason Code');">Reason Code</a><%=strReasonCodeN1%></th>
  						<th>Urgency</th>
  					</tr>
  					<tr >
  						<td><input type="text" name="reasonCdDesc" onClick="getLovWithValue('canon_E193_csTicketReasonCodeP.jsp','reasonCode','<%=hParentCatCode%>','Reason Code');" value="<%=request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc")%>" class="inTxt required" style="background-color:#ffff00" size="80" readonly=true></td>
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
   					<b><%=strOtherIssueN2%></b>
   				</font>
   			</td>
   		</tr>
		<!-- <tr><td height="10" colspan="3"></td></tr> -->
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
			<td colspan="2"><textarea name="notes" id="notes"  rows="10" wrap="OFF" class="inTxt required" style="background-color:#ffff00;height:auto; width:800px;"></textarea></td>		
		</tr>			
	 </table>
	</form>	
	<div style="text-align: center;">	
		<a class="btn_red" href="javascript:history.back();">Prev</a>
		<a class="btn_red" style="margin-left: 5px;margin-bottom: 5px;" href="javascript:action_func1();">Next</a>
	</div>
	<div id="dlg"></div>
	<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>