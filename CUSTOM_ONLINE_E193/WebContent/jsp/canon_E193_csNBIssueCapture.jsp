<!-- $Header: canon_E193_csNBIssueCapture.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csNBIssueCapture - Non billing issues
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
 | 04/21/2008   Venkat V			To avoid creating duplicate tickets
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
		hPath = strNBIssueCaptureT1;
	else
		if(hPath.indexOf(strNBIssueCaptureT1) < 0)
			hPath = hPath + " -> " + strNBIssueCaptureT1;	
%>
<%@ include file="canon_E193_csMenuInc.jsp" %>

<!-- 21-APR-2008, Venkat Velagala, Start -->
<script language="javascript">
window.history.forward(1);
</script>
<!-- 21-APR-2008, Venkat Velagala, End -->

<script language="JavaScript">

	/*function fnGetReasonCode() {
		var val = document.nonBillingIssueForm.hParentCatCode.value;
		var vWin = window.open("canon_E193_csTicketReasonCodeP.jsp?reasonCode="+val, "newwin","height=300,width=940,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
		vWin.focus();
	}*/

	function nextPage() {
		var reqSelector=".required, .requireds"; 
		var objForm = document.nonBillingIssueForm;
		if(!validateParams(reqSelector)){			
			if(objForm.iLineId.value != '' && objForm.iLineId.value != '0') {
				var notes = objForm.notes.value;
				var existingNotes = objForm.notesDisplay.value;
				//alert(notes.length);
				//alert(existingNotes.length);
				if((notes.length + existingNotes.length) > 32000){
					displayErrorMsg("Total Size of Notes for an Issue Line is restricted to 32000 characters. Please use the attachment facility to add additional details.");
					objForm.notes.focus();
				}else {
					objForm.flag.value = "update";
					objForm.readData.value = "yes";
					objForm.submit();
				}
			}else{	
				var notes = objForm.notes.value;
				//alert(notes.length);
				if(notes.length > 32000){
					displayErrorMsg("Total Size of Notes for an Issue Line is restricted to 32000 characters. Please use the attachment facility to add additional details.");
					objForm.notes.focus();
				}else{
					objForm.submit();
				}
			}
			
		}
		
	}
	
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
	
	function getReasonCode(code, reason) {
		document.nonBillingIssueForm.reasonCdDesc.value = reason;
		document.nonBillingIssueForm.hReasonCd.value = code;
	}
	
	function rsnCodeRefresh() {
		//document.ReasonCodeForm.submit();		
		var qryStr="reasonCode="+encodeURIComponent( $('select[name="reasonCode"]').val() );
		   var modelName ="#dlg";
		       $.ajax({
					url: "canon_E193_csTicketReasonCodeP.jsp",
					data:qryStr,
					type:"POST",
				    cache: false,
				    cached: false,
					success: function(data){     
							  hidePleaseWait();     
		   					  $(modelName).html("");					
		   				      $(modelName).html(data);  				       
		   				       hidePleaseWait();
		 	        }             
		       });
		
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
%> 

	<div id="main_content">
		<div id="page_top">
			<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strNBIssueCaptureT1%></h1>
			<div class="breadcrumb"><%=hPath%></div>
		</div>	
	
	<form name="nonBillingIssueForm" id="nonBillingIssueForm" action="canon_E193_csTicketSummaryNBController.jsp" method="post">

	<input type="hidden" name="hPageFrom" value="NBIssueCapture">
	<input type="hidden" name="nextPage" value="">
	<input type="hidden" name="hReasonCd" value="<%=request.getParameter("hReasonCd")==null?"":request.getParameter("hReasonCd")%>">
	<input type="hidden" name="hCatId" value="<%=request.getParameter("hCatId")%>">
	<input type="hidden" name="hParentCatId" value="<%=request.getParameter("hParentCatId")%>">
	<input type="hidden" name="hParentCatCode" value="<%=request.getParameter("hParentCatCode")%>">
	<input type="hidden" name="hCatDesc" value="<%=request.getParameter("hCatDesc")%>">

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
	<input type="hidden" name="hPath" value="<%=hPath%>">
	
		
	<% if(objSessionAcctInfo != null) { %>
	<div style="margin-left:30px; margin-top: 10px;"><font color="#FF0000"><b><%=strNBIssueCaptureN1%></b></font></div>
	<table class="request-service" cellspacing="0">  		
   		<tr>
   			<td width="10">&nbsp;</td>
	    	<td>
		    	<table cellspacing="1" class="request-service" >
			 		<tr >
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
  		</table>
  		<% } %>
  		<div style="margin-left:30px; margin-top: 5px; margin-bottom: 10px"><font color="#FF0000"><b><%=strNBIssueCaptureN1%></b> </font></div>
  		 <div id="errorWidget"  style="display: none;padding-bottom: 5px;color:red;">
						<p id="eMsg"></p>
						</div>
   			<table cellspacing="1"class="supplies-tablehome" style="width:800px;margin-left:30px;">		   	
   				<tr>
 						<th><a href="javascript:void(0)" onClick="javascript:getLovWithValue('canon_E193_csTicketReasonCodeP.jsp','reasonCode','NONBILL','Reason Codes');">Reason Code</a><%=strReasonCodeN1%></th>
 						<th>Urgency</th>
 				</tr>
 				<tr>
  						<td><input type="text" name="reasonCdDesc" id="reasonCdDesc"  onClick="javascript:getLovWithValue('canon_E193_csTicketReasonCodeP.jsp','reasonCode','NONBILL','Reason Codes');" value="<%=request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc")%>" class="inTxt required"  style="background-color:#ffff00" size="80" readonly=true></td>
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
    	<div style="margin-left:30px; margin-top: 10px; margin-bottom: 10px"><font color="#FF0000"><b><%=strNBIssueCaptureN2%></b></font> </div>
    	<div style="margin-left:30px;">
    	   		    	
  		<%
  			String strNotesDisplay = request.getParameter("notes")==null?"":request.getParameter("notes");
  			if(!"".equalsIgnoreCase(strNotesDisplay)) {
  		%>
						
			<textarea  name="notesDisplay"  id="notesDisplay" rows="10" style="height:auto; width:800px;" wrap="OFF" class="txtbox" readonly="readonly"><%=strNotesDisplay%></textarea>		
			
		<%	}  %>
			<textarea  name="notes"  id="notes" rows="10" style="height:auto; width:800px;" wrap="OFF" class="inTxt required"></textarea>		
	</div>
	</form>
	<div style="text-align: center; margin-top: 10px;">	
			<a class="btn_red" href="javascript:history.back();">Prev</a> 
			<a class="btn_red" style="margin-left: 10px;" href="javascript:nextPage();">Next</a> 	
	</div>
	<div id="dlg"></div>
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>