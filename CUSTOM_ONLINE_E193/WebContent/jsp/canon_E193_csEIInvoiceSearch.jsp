<!-- $Header: canon_E193_csEIInvoiceSearch.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csEIInvoiceSearch.jsp - Invoice Search
 |   
 | DESCRIPTION
 |   
 |  
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	08/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 15-Sep-2009  Naveen Khandelwal   MW Project Changes
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 
<%@page import="java.util.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>


<%@ include file="canon_E193_csTopInc.jsp" %>

<% 
 	// Menu Prompt
	strPageName = "Enter & Inquiry";
    String imgSrc=ctxPath+"/common/images/jtfulov.gif";
	Canon_E193_AcctInfoObj objSessionAcctInfo = (Canon_E193_AcctInfoObj)session.getAttribute("objSessionAcctInfo");
	
	// Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");
	String hPath = request.getParameter("hPath");
	if (hPath == null)
		hPath = strInvoiceSearchT1;
	else
		if(hPath.indexOf(strInvoiceSearchT1) < 0)
			hPath = hPath + " -> " + strInvoiceSearchT1;
			
	String strInvSource = request.getParameter("strInvSource");   
	if( (strInvSource == null) || ("null".equalsIgnoreCase(strInvSource)) ){strInvSource = "X1";}
	
	int iTotPageNum;
	String strTotPageNum = request.getParameter("iTotPageNum");
	if (strTotPageNum == null) { iTotPageNum = 0; }
	else { iTotPageNum = (int)(Integer.parseInt(strTotPageNum)); }
		
	int iPageNum;
	String strPageNum = request.getParameter("iPageNum");
	if (strPageNum == null) { iPageNum = 0; }
	else { iPageNum = (int)(Integer.parseInt(strPageNum)); }
	
	String strInvFromDate = request.getParameter("strInvFromDate");
	if (strInvFromDate == null) { strInvFromDate = ""; }
	
	String strInvToDate = request.getParameter("strInvToDate");
	if (strInvToDate == null) { strInvToDate = ""; }
	
	// List Box selections
	String strContSel = "";
	String strManuSel = "";	
	String strMercSel = "";
	String strMiscSel = "";
	String strServSel = "";
	String strSuppSel = "";
	
	if (strInvSource.equals("CONTRACTS")) { strContSel ="selected" ;}
	else if (strInvSource.equals("AR MANUAL")) { strManuSel ="selected"; }
	else if (strInvSource.equals("MERCHANDISE")) { strMercSel ="selected"; }
	else if (strInvSource.equals("MISC")) { strMiscSel ="selected"; }
	else if (strInvSource.equals("SERVICE")) { strServSel ="selected"; }
	else if (strInvSource.equals("SUPPLY")) { strSuppSel ="selected"; }

%>
<%@ include file="canon_E193_csMenuInc.jsp" %> 

<script type="text/javascript">
	var orderAsc = "asc";
	var orderDesc = "desc";
	var tArray = new Array("invNo", "invDate", "contractNo", "ordType", "salesRep", "ticketNo");
	
	function fnOrderBy(name) {
		var objForm = document.invoiceSearchForm;
		var vOrdName = objForm.orderName.value;
		if(vOrdName != name) objForm.orderBy.value = 'null';
		if(name == tArray[0] && vOrdName == "null") {
			vOrdName = tArray[0];
			objForm.orderBy.value = orderAsc;
		}
		objForm.orderName.value = name;
		var reqOrderBy = objForm.orderBy.value;
		
		for(i=0; i<tArray.length; i++) {
			if(name == tArray[i] && (reqOrderBy == 'null' || reqOrderBy == orderDesc)) {
				objForm.orderBy.value = orderAsc;
			}else if(name == tArray[i] && reqOrderBy == orderAsc) {
				objForm.orderBy.value = orderDesc;
			}
		}
		
		objForm.invoiceNumber.value = '';
		objForm.action = "canon_E193_csEIInvoiceController.jsp";
		objForm.submit();
		return false;
	}

	function action_func1(iPageIndex, btn) {
		resetErroMsg();
		var objForm = document.invoiceSearchForm;
		// check if dates are valid
		if(objForm.invoiceFrom.length == 0 || objForm.invoiceFrom.value == "" || objForm.invoiceFrom.value == null) {
			displayErrorMsg("Invoice From Date is required.");
			objForm.invoiceFrom.focus();
			objForm.invoiceFrom.select();
		}else if(objForm.invoiceTo.length == 0 || objForm.invoiceTo.value == ""	|| objForm.invoiceTo.value == null) {
			displayErrorMsg("Invoice To Date is required.");
			objForm.invoiceTo.focus();
			objForm.invoiceTo.select();
		}else{
			objForm.iPageNum.value = iPageIndex;
			if(iPageIndex == 0) {
				objForm.iTotPageNum.value = "0";
			}
			//if( objForm.strInvSource.value != "X1") {
			if(btn == '') {
				objForm.strInvSource.value = document.invoiceSearchForm.InvSource.value;
			}else{
				objForm.strInvSource.value = document.invoiceSearchForm.InvSource1.value;
			}
			objForm.invoiceNumber.value = "";		
			objForm.action = "canon_E193_csEIInvoiceController.jsp";
			objForm.submit();
		}
	}
	
	//MW Project Changes
	function select_Func(objRB, custTrxNo, value1, strInvoiceType, isCancelledInErrTkt) {
		resetErroMsg();
		var objForm = document.invoiceSearchForm;
		
		if(value1 > 0)
		{
			displayErrorMsg("One ticket is already OPEN for this invoice. To create new ticket, please close the open ticket first!! ");
			objRB.checked = false;
		}
		else if(isCancelledInErrTkt != 'null' )
		{
			alert("Ticket no "+isCancelledInErrTkt+" is in CANCELLED IN ERROR status for this invoice, please process credit rebill manually.");
			objRB.checked = false;
		}
		else
		{
			objForm.invoiceNumber.value = custTrxNo;
			//MW Project Changes
			objForm.strInvoiceType.value = strInvoiceType;
		}
	}
	
	function action_func2(value2) {
		resetErroMsg();
		var objForm = document.invoiceSearchForm;
		var isSelected = false;
		var val = '';
		var v = objForm.rSlctInv.length;
		if(objForm.rSlctInv != null) {
			if(v > 0) {
				for(i=0; i<v; i++) {
					if(objForm.rSlctInv[i].checked) {
						isSelected = true;
						val = objForm.rSlctInv[i].value;
						break;
					}
				}
			}else if(objForm.rSlctInv.checked) {
				isSelected = true;
			}
		}
		if(!isSelected) {
			displayErrorMsg("Please select a invoice number");
		}else{	
			//objForm.action = "canon_E193_csBIssueList.jsp";
			objForm.action = "canon_E193_csEIInvoiceController.jsp";
			objForm.submit();
		}
	}
	
	/*function InvoiceDetails(val1) {
		var vLink = "canon_E193_csActualInvoiceController.jsp?InvNo=" + val1;
		var vWin = window.open(vLink, "newwin","height=300,width=900,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
		vWin.focus();
	}*/
	
	function ContractDetails() {
		
		alert('Page Under Construction !!!');
	}
	
	function TicketView(invoiceVal) {
		var vWin = window.open("canon_E193_csTicketHistoryController.jsp?popup=yes&searchType=invoiceNo&searchValue="+invoiceVal, "newwin","height=400,width=930,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
		vWin.focus();
	}

	
	 
	$(document).ready(function() {
		$(".date-picker").datepicker({
			dateFormat : 'mm/dd/yy',
			changeMonth : true,
			changeYear : true
		});
	});
	
</script>
<div id="main_content">
	<div id="page_top">
		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strInvoiceSearchT1 %></h1>
		<div class="breadcrumb"><%=hPath%></div>
	</div>


<form name="invoiceSearchForm" method="post">
 
 	<!-- hidden variables -->
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
	
	<input type="hidden" name="hPageFrom" value="EIInvoiceSearch" >

	<input type="hidden" name="strInvSource" value="<%=strInvSource%>" >
	<input type="hidden" name="iPageNum" value="<%=iPageNum%>" >
	<input type="hidden" name="iTotPageNum" value="<%=iTotPageNum%>" >
	<input type="hidden" name="orderName" value="<%=request.getParameter("orderName")%>">
	<input type="hidden" name="orderBy" value="<%=request.getParameter("orderBy")%>">
	<input type="hidden" name="invoiceNumber" value="">
	<!--MW Project Changes - Starts-->
	<input type="hidden" name="strInvoiceType" value=""> 
	<!--MW Project Changes - Ends-->
	<input type="hidden" name="hPath" value="<%=hPath%>">
	<table class="request-service" ALIGN="center" cellspacing="0" border="0" bgcolor="#FFFFFF">	
	<tr> 
		<td colspan="2"><font class="promptReadOnly">Caller Details:</font></td>
	</tr>
	<tr>
	    <td colspan="3">
	       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
				<p id="eMsg"></p>
		   </div>
	   </td>
	</tr>
  	<tr> 
    	<td>&nbsp;</td>
		<td>
	    	<table cellspacing="1" class="customer-care">
				<tr>
					<th>Account Name</th>
					<th>Account Number</th>
					<th>Contact Name</th>
					<th>Contact Number</th>		
				</tr>
			   <tr>
					<td><%=objSessionAcctInfo.getAcctName()%></td>
					<td><%=objSessionAcctInfo.getAcctNum()%></td>
					<td><%=objSessionAcctInfo.getContactName()%></td>
					<td><%=objSessionAcctInfo.getContactNum()%></td> 
				</tr> 
	     	</table>   
	  	</td>
	</tr>
  	<!-- <tr><td height="16" colspan="2"></td></tr> -->
	<tr align="left"> 
		<td class="tableQuestionCell" colspan="2"><font class="promptReadOnly" color="#FF0000"><b><%=strInvoiceSearchN1%></b></font></td>
	</tr>
  	<tr> 
   		<td>&nbsp;</td>
    	<td> 
			<table align="left" cellpadding="2" cellspacing="1" class="tabTableStyle" border="1">
        		<tr> 
					<td>
						<input name="invoiceFrom" placeholder="Invoice Date From" id="invoiceFrom" type="text" size="12" maxlength="30"   class="date-picker" value="<%=strInvFromDate%>" readonly>						
					</td>
					<td class="subhead">Invoice To Date</td>
					<td class="tdTableCellStyle">
						<input name="invoiceTo" id="invoiceTo" placeholder="Invoice Date To" type="text" size="12" maxlength="30" class="date-picker" value="<%=strInvToDate%>" readonly >						
					</td>
					<td class="subhead">Invoice Source</td>
					<td> 
					<%
						String strInvSource1 = request.getParameter("InvSource1")==null?"":request.getParameter("InvSource1");
					%>
						<select name="InvSource1" size="1" class="searchBarLink">
							<option value="CONTRACTS" <%="CONTRACTS".equals(strInvSource1)?"selected":""%>>Contracts</option>
							<option value="AR MANUAL" <%="AR MANUAL".equals(strInvSource1)?"selected":""%>>Manual</option>
							<option value="MERCHANDISE" <%="MERCHANDISE".equals(strInvSource1)?"selected":""%>>Merchandise</option>
							<option value="MISC" <%="MISC".equals(strInvSource1)?"selected":""%>>Misc</option>
							<option value="SERVICE" <%="SERVICE".equals(strInvSource1)?"selected":""%>>Service</option>
							<option value="SUPPLY" <%="SUPPLY".equals(strInvSource1)?"selected":""%>>Supply</option>
						</select> 
					</td>
          			<td> 
          			<a class="btn_red" href="javascript: action_func1(0, 'go');">Go</a>							
		  			</td>
        		</tr>
      		</table>
	  	</td>
  	</tr>
<%	
	if(strInvSource.equals("X1")) {
%>	
		<tr><td height="16"></td></tr> 
		<tr>
			<td colspan="2">
				<table style="text-align: center; margin-left: 15cm;" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
					<tr> 
						<td>
						<a class="btn_red" href="javascript:history.back();">Prev</a>							
						</td>
						<td> 
						<a class="btn_red" href="#">Next</a>							
						</td>
					</tr>
				</table>
			</td>
		</tr>
<%	
	} else {
%>
		<!-- <tr><td height="16" colspan="2"></td></tr> -->
		<tr align="left"> 
			<td class="tableQuestionCell" colspan="2"><font class="promptReadOnly" color="#FF0000"><b><%=strInvoiceSearchN2%></b></font></td>
		</tr>
		<!-- <tr><td colspan="2" height="16"></td></tr>  --> 
		<tr> 
			<td>&nbsp;</td>
			<td>  
				<table cellspacing="1" class="customer-care">
					<tr> 
						<th>&nbsp;</th>
						<th>Invoice Number</th>
						<th>Consolidated Bill Number</th>
						<th>Invoice Date</th>
						<th> 
							<select name="InvSource" size="1" class="searchBarLink" onChange="action_func1(0, '')">
								<option value="CONTRACTS" <%=strContSel%>>Contracts</option>
								<option value="AR MANUAL" <%=strManuSel%>>Manual</option>
								<option value="MERCHANDISE" <%=strMercSel%>>Merchandise</option>
								<option value="MISC" <%=strMiscSel%>>Misc</option>
								<option value="SERVICE" <%=strServSel%>>Service</option>
								<option value="SUPPLY" <%=strSuppSel%>>Supply</option>
							</select> 
						</th>
						<th>Contract Number<br> / Order Number</th>
						<th>Invoice Type<br> / Order Type</th>
						<th>Sales Rep</th>
						<th>Ticket Number</th>
					</tr>
						
					<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objInvArrayList" scope="request" />					
<%
					ArrayList alInvList = (ArrayList)objInvArrayList.getArrayList();
					int iArraySize = alInvList.size();
					int iAct = -99;
					int iMulti = -99;
					
					for(int i=0;i<iArraySize;i++) {
						Canon_E193_InvoiceObj objInvObj = (Canon_E193_InvoiceObj) alInvList.get(i);
						
						long iCustTrxId = objInvObj.getICustTrxId();
							
						String strTrxNum = objInvObj.getStrTrxNum();
						if (strTrxNum == null)
							strTrxNum = "";
							
						String strTrxDate = objInvObj.getStrTrxDate();
						if (strTrxDate == null)
							strTrxDate = "";
							
						String strTrxType = objInvObj.getStrTrxType();
						if (strTrxType == null)
							strTrxType = "";
							
						String strContNumOrdrNum = objInvObj.getStrContNum();
						if (strContNumOrdrNum == null)
							strContNumOrdrNum = "";
							
			/*
						String strContNumMod = objInvObj.getStrContNumMod();
						if (strContNumMod == null)
							strContNumMod = "";
							
						String strOrdrNum = objInvObj.getStrOrdrNum();
						if (strOrdrNum == null)
							strOrdrNum = "";
			*/							

						String strOrdrType = objInvObj.getStrOrdrType();
						if (strOrdrType == null)
							strOrdrType = "";
							
						String strSalesRep = objInvObj.getStrSalesRep();
						if (strSalesRep == null)
							strSalesRep = "";
							
						String strTicketNum = objInvObj.getStrAttribute1();
						String strTicketNumActual = ""; 
						if (strTicketNum == null) {
							strTicketNum = "";
							iAct = -1;
							iMulti = -1;
						}
						else { 
							iAct = strTicketNum.indexOf("A");
							iMulti = strTicketNum.indexOf("|");
							strTicketNumActual = strTicketNum.substring(0,strTicketNum.length()-1);
						}
						Canon_E193_Invoice objCiInvInfo = new Canon_E193_Invoice();
						String isCancelledInErrTkt = objCiInvInfo.getInvoiceStatus(strTrxNum);
						
						String strConsBillNum= objInvObj.getConsolidateBillNum();
						if (strConsBillNum == null)
							strConsBillNum = "";						
%>
						<tr> 
							<td>
							<!--MW Project Changes - Starts-->
							<input name="rSlctInv" type="radio" value="<%=i%>" onClick="select_Func(this,'<%=strTrxNum%>', '<%=iAct%>','<%=strOrdrType%>','<%=isCancelledInErrTkt%>')"> 
							<!--MW Project Changes - Ends-->
							</td> 
							 <td><a style="color:#cc0000;" href="javascript:void InvoiceDetailsPDF('<%=strTrxNum%>');"><%= strTrxNum%></a></td>
							 <%-- <td><a href="javascript: InvoiceDetails('<%=strTrxNum%>')"><%=strTrxNum%></a></td>  --%>
							<td><%=strConsBillNum%></td>
							<td><%=strTrxDate%></td>
							<td><%=strTrxType%></td>
							<td><%=strContNumOrdrNum%></td>
							<td><%=strOrdrType%></td>
							<td><%=strSalesRep%></td>
<%
							if(iMulti > 0) {
%>
								<td><a href="#" onClick="TicketView('<%=strTrxNum%>')">Multiple</a></td>
<%
							}
							else {
								if((strTicketNumActual == null) || ("".equalsIgnoreCase(strTicketNumActual))) {
							
%>
									<td>&nbsp;</td>
<%
								}
								else {
%>
									<td><a href="canon_E193_csTicketStatusController.jsp?ticketId=<%=strTicketNumActual%>"><%=strTicketNumActual%>(A)</a></td> 
<%
								}
							}
%>
						</tr>
<%
					}					
					if (iTotPageNum > 1) {
%>	
						<tr class="tdTableCellStyle">
							<td colspan="9"> Pages:  &nbsp; &nbsp;
<%
								for(int j=1;j<=iTotPageNum;j++) {
									if(j == iPageNum) {
%>									
										&nbsp;<%=j%>
<%										
									} else {
%>
										&nbsp;<a href="javascript: action_func1(<%=j%>, '');"><%=j%></a>
<%
									}
								}
					}
					if(iArraySize == 0) {
%>
						<tr class="tdTableCellStyle">
							<td colspan="8">
								<font class="promptReadOnly"><b>Invoice not found for this value. Please revise your search criteria.</b></font>
							</td>
						</tr>
<%
					}
%>					
				</table>
			</td>
		</tr>		 
		<tr>
			<td colspan="2">
				<table style="text-align:center; margin-left: 15cm;" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
					<tr> 
						<td>
						<a class="btn_red" href="javascript:history.back();">Prev</a>							
						</td>						
						<td> 
<%
							if(iArraySize == 0) {
%>						
							<a class="btn_red" href="#">Next</a>								
<%
							} else {
%>						
							<a class="btn_red" style="margin-left: 5px;" href="javascript:action_func2('<%=iAct%>');">Next</a>								
<%
							}
%>													
						</td>
					</tr>
				</table>
			</td>
		</tr>
<%
	}
%>
</table>
 </form>
<div id="dlg"></div>
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>
<script>
var msg = '<%=request.getParameter("errorMsg")==null?"":request.getParameter("errorMsg")%>';
if(msg != '' && msg != 'null') {
	alert(msg);
}
</script>

