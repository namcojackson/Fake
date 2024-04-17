<!-- $Header: canon_E193_csEICheckRequest.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csEICheckRequest.jsp - Select Addresses.
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
 | 14-Mar-2008  Chandra Sekhar      ITG #Change
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 
<%@page import="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Invoice" id="invList" scope="page" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Ticket" id="objTicket" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objHeaderLinesDtls" scope="request" />
<jsp:setProperty name="objHeaderLinesDtls" property="*" />

<%@ include file="canon_E193_csTopInc.jsp" %>

<% 
 	// Menu Prompt
	strPageName = "Enter & Inquiry";
	ArrayList invoiceList = new ArrayList();
	//Canon_E193_AcctInfoObj objSessionAcctInfo = (Canon_E193_AcctInfoObj)pageContext.getAttribute("objSessionAcctInfo",2);
	Canon_E193_AcctInfoObj objSessionAcctInfo = (Canon_E193_AcctInfoObj)session.getAttribute("objSessionAcctInfo");	
	//System.out.println("!!!!!!Canon_E193_AcctInfoObj= "+ objSessionAcctInfo);
	
	// Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");

	String strNewRequest = request.getParameter("newrequest");   
	if(strNewRequest == null){strNewRequest = "X1";}
	
	String strInvRelated = request.getParameter("invrelated");   
	if(strInvRelated == null){strInvRelated = "X2";}    
	
	String strExistingCI = request.getParameter("existingci");   
	if(strExistingCI == null){strExistingCI = "X3";}   
	
	String strBilling = request.getParameter("billing");   
	if(strBilling == null){strBilling = "X4";}

	String hPath = request.getParameter("hPath");
	//System.out.println("~HPath~ " +hPath);
	if (hPath == null)
		hPath = strCheckRequestT1;
	else if(hPath.indexOf(strCheckRequestT1) < 0)
		hPath = hPath + " -> " + strCheckRequestT1;	
			
	String origName = request.getParameter("origName");
	String origPhNo = request.getParameter("origPhNo");
	String origEmailAddress = request.getParameter("origEmailAddress");
	
	if(origEmailAddress == null || (origEmailAddress != null && "null".equalsIgnoreCase(origEmailAddress.trim())))
	{
		origEmailAddress = "";
	}
	
	String origCheckbox = request.getParameter("origCheckbox");
	String origType = request.getParameter("origType");
	String sourceType = request.getParameter("sourceType");
	String custName = request.getParameter("custName");
	String custPhNo = request.getParameter("custPhNo");
	String custEmailAddress = request.getParameter("custEmailAddress");
	
	String recurProb = request.getParameter("recurProb");
	String probType = request.getParameter("probType");
	String val1 = request.getParameter("val1");
	
	String selAcctIdString = request.getParameter("selAcctId");
	int selAcctId = 0;
	if(selAcctIdString != null && !selAcctIdString.trim().isEmpty())
	{
		selAcctId = Integer.parseInt(selAcctIdString);
	}
	
	String selLocId = request.getParameter("selLocId");
	String selAcctName = request.getParameter("selAcctName");
	String selAcctNum = request.getParameter("selAcctNum");
	String vBillType = request.getParameter("vBillType");
	
	String copyTicketFromHistory = request.getParameter("copyTicketFromHistory");
	
	String copyTicket = request.getParameter("copyTicket");
		
	/* System.out.println("!!!!!!!!!!! strPageFrom " + strPageFrom + " strNewRequest " + strNewRequest + " strInvRelated " + strInvRelated + 
			" strExistingCI " + strExistingCI + " strBilling " + strBilling + " hPath " + hPath + " origName " + origName + 
			" origPhNo " + origPhNo + " origEmailAddress " + origEmailAddress + " origCheckbox " + origCheckbox + 
			" origType " +  origType+ " sourceType " + sourceType + " custName " + custName + " custPhNo " + custPhNo + 
			" custEmailAddress " + custEmailAddress + " recurProb " + recurProb + " probType " + probType + " val1 " + val1 + 
			" selAcctId " + selAcctId + " selLocId " + selLocId + " selAcctName " + selAcctName + " selAcctNum " + selAcctNum + 
			" vBillType " + vBillType + " copyTicketFromHistory " + copyTicketFromHistory );  */
	
	if((copyTicketFromHistory != null && "Y".equalsIgnoreCase(copyTicketFromHistory)) 
			|| (copyTicket != null && "Y".equalsIgnoreCase(copyTicket)))
	{
		ArrayList alTktDtls = new ArrayList();
		Canon_E193_Severity objSeverity = new Canon_E193_Severity();
		
		int iOrgId = objCiSession.getOrgId();		
		String strTicketId = request.getParameter("ticketId");
		String iResId = objCiSession.getResourceId();
		int iTktId = 0;
				
		if(strTicketId != null && !("".equals(strTicketId)) && !("null".equalsIgnoreCase(strTicketId)) )
				iTktId = Integer.parseInt(strTicketId);
		
		System.out.println("iOrgId,iTktId,iResId-- "+iOrgId+","+iTktId+","+iResId);
		
		ArrayList alTktHeaderLineDtls =(ArrayList)objTicket.getTicketDetails(iOrgId,iTktId,iResId);
		alTktDtls.add(alTktHeaderLineDtls);
		if(alTktHeaderLineDtls.size() > 0)
		{
			//System.out.println("!!!!!! alTktHeaderLineDtls.size() = "+alTktHeaderLineDtls.size()  );
			// Add Header and Line Status
			ArrayList alTktStatusObj =(ArrayList)objTicket.getTicketStatus();
			//System.out.println("!!!!!! alTktStatusObj = "+ alTktStatusObj.toString());
			alTktDtls.add(alTktStatusObj);
	    	// Add Line Severity		
			ArrayList alSeverityObj =(ArrayList)objSeverity.getSeverity();
			//System.out.println("!!!!!! alSeverityObj = "+alSeverityObj  );
			alTktDtls.add(alSeverityObj);
	    	// Add this object to session object with scope as request
			objHeaderLinesDtls.setArrayList(alTktDtls); 
	    	
			Canon_E193_TicketHeaderObj objTktHdrObj = (Canon_E193_TicketHeaderObj)alTktHeaderLineDtls.get(0);
			
			objSessionAcctInfo = new Canon_E193_AcctInfoObj();
			objSessionAcctInfo.setContactName(objTktHdrObj.getCustContact()==null?objTktHdrObj.getOrigName():objTktHdrObj.getCustContact());
			objSessionAcctInfo.setContactNum(objTktHdrObj.getCustPhNo()==null?objTktHdrObj.getOrigPhNo():objTktHdrObj.getCustPhNo());
			objSessionAcctInfo.setAcctName(objTktHdrObj.getCustomerName()==null?"":objTktHdrObj.getCustomerName());
			objSessionAcctInfo.setAcctNum(objTktHdrObj.getCustomerNo()==null?"":objTktHdrObj.getCustomerNo());
			objSessionAcctInfo.setAcctId(objTktHdrObj.getCustAcctId());
			objSessionAcctInfo.setPORequiredFlag(objTktHdrObj.getAttribute2()==null?"":objTktHdrObj.getAttribute2());
			session.setAttribute("objSessionAcctInfo",objSessionAcctInfo);
			
			objSessionAcctInfo = (Canon_E193_AcctInfoObj)session.getAttribute("objSessionAcctInfo");	
			
			if(objTktHdrObj != null) 
			{
				origName = objTktHdrObj.getOrigName();
				origPhNo = objTktHdrObj.getOrigPhNo();
				origEmailAddress = objTktHdrObj.getOrigEmail() == null ? "" : objTktHdrObj.getOrigEmail();
				origCheckbox = objTktHdrObj.getsendEmailFlag();
				origType = objTktHdrObj.getOrigType();
				sourceType = objTktHdrObj.getAttribute9();
				custName = objTktHdrObj.getCustContact();
				custPhNo = objTktHdrObj.getCustPhNo();
				custEmailAddress = objTktHdrObj.getCustEmail() == null ?  "" : objTktHdrObj.getCustEmail();
				
				recurProb = objTktHdrObj.getRecurring();
				//probType = request.getParameter("probType");
				//val1 = request.getParameter("val1");
				
				selAcctId = objTktHdrObj.getCustAcctId();
				
				//selLocId = request.getParameter("selLocId");
				selAcctName = objTktHdrObj.getCustomerName();
				selAcctNum = objTktHdrObj.getCustomerNo();
				//vBillType = request.getParameter("vBillType");
			}
			/* System.out.println("!!!!!!!!!!! Inside strPageFrom " + strPageFrom + " strNewRequest " + strNewRequest + " strInvRelated " + strInvRelated + 
					" strExistingCI " + strExistingCI + " strBilling " + strBilling + " hPath " + hPath + " origName " + origName + 
					" origPhNo " + origPhNo + " origEmailAddress " + origEmailAddress + " origCheckbox " + origCheckbox + 
					" origType " +  origType+ " sourceType " + sourceType + " custName " + custName + " custPhNo " + custPhNo + 
					" custEmailAddress " + custEmailAddress + " recurProb " + recurProb + " probType " + probType + " val1 " + val1 + 
					" selAcctId " + selAcctId + " selLocId " + selLocId + " selAcctName " + selAcctName + " selAcctNum " + selAcctNum + 
					" vBillType " + vBillType + " copyTicketFromHistory " + copyTicketFromHistory + " objSessionAcctInfo " + objSessionAcctInfo);  */
		}
	}
	
 %>
 
<%@ include file="canon_E193_csMenuInc.jsp" %> 
  
<script type="text/javascript">
<!--
	function validateField(textfield) {
		var vChar = "";
		var OK = true;
	
		for (var i=0; i<textfield.value.length; i++) {
			vChar = textfield.value.charAt(i);
			
			if ((vChar >= "0") && (vChar <= "9"))
				 continue;
			else if ((vChar >= "A") && (vChar <= "Z") && (textfield.name == "invoiceNumber"))
				continue;
			else if ((vChar >= "a") && (vChar <= "z") && (textfield.name == "invoiceNumber"))
				continue;
			else {
				OK = false;
				break;
			}
		}
		if (!OK) {
			if(textfield.name == "ticketNumber")
				alert("Please enter a valid Ticket Number");
			else
				alert("Please enter a valid Invoice Number");
			textfield.focus();
			textfield.select();
		}
		return OK;
	}
	
	function onSelect(nPage) {
		document.csCheckRequest.nextPage.value = nPage;
	}
	
	function onNext() {
		
		document.csCheckRequest.action = document.csCheckRequest.nextPage.value;
		document.csCheckRequest.submit();
	}

	function action_ticket_next() {
		document.csCheckRequest.ticketId.value = document.csCheckRequest.ticketNumber.value;
		document.csCheckRequest.action = "canon_E193_csTicketStatusController.jsp";
		document.csCheckRequest.submit();
	}	
	
	function ContractDetails() {
		alert('Page Under Construction !!!');
	} 
	
	
	
	function action_func2() {
		document.csCheckRequest.action = "canon_E193_csEIInvoiceSearch.jsp";
		document.csCheckRequest.submit();
	}
	function getInvoice(){
	if ((document.csCheckRequest.consolidateBillNum.length == 0) || (document.csCheckRequest.consolidateBillNum.value == "")
			|| (document.csCheckRequest.consolidateBillNum.value == null)) {
			alert("Consolidated Bill Number is mandatory.");
			document.csCheckRequest.consolidateBillNum.focus();
		}else{
			document.csCheckRequest.consolidateBillNum.value = document.csCheckRequest.consolidateBillNum.value;
			document.csCheckRequest.action = "canon_E193_csEICheckRequest.jsp";
			document.csCheckRequest.submit();
		}
	}
	
	function setInvoice() {  
		var listBox = document.getElementById("invNumberList");
		var myvalue= listBox.options[listBox.selectedIndex].value;
		document.csCheckRequest.invoiceNumber.value	= "";	
		document.csCheckRequest.invoiceNumber.value =myvalue;   
	}
	
//-->
function action_func1() {
		if ((document.csCheckRequest.invoiceNumber.length == 0) || (document.csCheckRequest.invoiceNumber.value == "")
			|| (document.csCheckRequest.invoiceNumber.value == null)) {
			alert("Invoice Number is mandatory.");
			document.csCheckRequest.invoiceNumber.focus();
			document.csCheckRequest.invoiceNumber.select();
		}else{
			document.csCheckRequest.action = "canon_E193_csEIInvoiceController.jsp";
			document.csCheckRequest.submit();
		}
	}
</script>
	<div id="main_content">
	 <div id="page_top">	   		
		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strCheckRequestT1 %></h1>
			<div class="breadcrumb"><%=hPath%>	
		</div>		
	</div>	

	<form name="csCheckRequest" id="csCheckRequest" method="post">
 
 	<!-- hidden variables -->
	<input type="hidden" name="origName" value="<%=origName%>" >
	<input type="hidden" name="origPhNo" value="<%=origPhNo%>">
	<input type="hidden" name="origEmailAddress" value="<%=origEmailAddress%>">
	<input type="hidden" name="origCheckbox" value="<%=origCheckbox%>">
	<input type="hidden" name="origType" value="<%=origType%>" >
	<input type="hidden" name="sourceType" value="<%=sourceType%>" >		
	<input type="hidden" name="custName" value="<%=custName%>" >
	<input type="hidden" name="custPhNo" value="<%=custPhNo%>">
	<input type="hidden" name="custEmailAddress" value="<%=custEmailAddress%>">
	
	<input type="hidden" name="recurProb" value="<%=recurProb%>">
	<input type="hidden" name="probType" value="<%=probType%>">
	<input type="hidden" name="val1" value="<%=val1%>">
	
	<input type="hidden" name="selAcctId" value="<%=selAcctId%>" >
	<input type="hidden" name="selLocId" value="<%=selLocId%>" >
	<input type="hidden" name="selAcctName" id="selAcctName" value="<%=selAcctName%>" >
	<input type="hidden" name="selAcctNum" value="<%=selAcctNum%>" >
	
	<input type="hidden" name="hPageFrom" value="EICheckRequest" >
 	
	<input type="hidden" name="ticketId" value="">
 	<input type="hidden" name="nextPage" value="">
	<input type="hidden" name="hPath" value="<%=hPath%>">
	<input type="hidden" name="vBillType" value="<%=vBillType%>">
	
	<table class="request-service" style="align:center;" cellspacing="0" >		  
  	<tr>
		<td class="tdTableCellStyle" colspan="2"><font class="promptReadOnly">Caller Details:</font> </td>
	</tr>
   	<tr>
   	  	<td>&nbsp;</td>
      	<td>
	    	<table cellspacing="1" class="request-service">
				<tr >
					<th>Account Name</th>
					<th>Account Number</th>
					<th>Contact Name</th>
					<th>Contact Number</th>		
				</tr>
				<tr>
					<td Style="text-align: center;"><%=objSessionAcctInfo.getAcctName()%></td>
					<td Style="text-align: center;"><%=objSessionAcctInfo.getAcctNum()%></td>
					<td Style="text-align: center;"><%=objSessionAcctInfo.getContactName()%></td>
					<td Style="text-align: center;"><%=objSessionAcctInfo.getContactNum()%></td> 
				</tr> 
			</table>   
		</td>
	</tr>
<!--  Changed For ITG #  By Chandra Sekhar - Start  -->


	<%-- <tr><td height="10" colspan="2"></td></tr>
	<tr align="left">
   		<td class="tableQuestionCell" colspan="2">
			<font class="promptReadOnly"><b><%=strCheckRequestQ1%></b></font>
		</td>
	</tr>
<%
   if (strNewRequest.equals("Yes")) {
%>
		<tr>
			<td width="10"></td>
			<td><input name="newrequest" type="radio" value="Yes" checked><font class="promptReadOnly">New</font></td>
		</tr>
		<tr>
			<td width="10"></td>
			<td><input name="newrequest" type="radio" value="No" disabled><font class="promptReadOnly">Previous</font></td>		
		</tr>
   		<tr><td height="10"></td></tr>	 --%>	


<!--  Changed For ITG #  By Chandra Sekhar - End  -->

		<!-- check if Billing issue starts -->
 		<tr align="left">
			<td class="tableQuestionCell" colspan="2"><font class="promptReadOnly"><b><%=strCheckRequestQ2%></b></font></td>
		</tr>
<%	
		if (strBilling.equals("Yes")) {
%>
			<tr>
				<td width="10"></td>
				<td><input name="billing" type="radio" value="Yes" checked><font class="promptReadOnly">Yes</font></td>
			</tr>
			<tr>
				<td width="10"></td>
				<td><input name="billing" type="radio" value="No" disabled><font class="promptReadOnly">No</font></td>		
			</tr>    		
	
			<!-- check for invoice related issue starts -->
			<tr align="left">
				<td class="tableQuestionCell" colspan="2">
					<font class="promptReadOnly"><b>
						<%=strCheckRequestQ3%>
						<font color="#FF0000"><%=strCheckRequestN1%></font>
					</b></font>
				</td>
			</tr>	
			<tr>
				<td width="10"></td>
				<td>
				<table>
					<tr>
					<td>
					<font class="promptReadOnly">Invoice Number</font>
					&nbsp;
					<%
						int iOrgId = 0;
						iOrgId = objCiSession.getOrgId();
	
						String strVal = "", strInvNo = "", strProbType = "",strBillType="",strConBillNo="",strBillVal="";
						strInvNo = request.getParameter("invoiceNumber");
						strConBillNo = request.getParameter("consolidateBillNum");
						strProbType = request.getParameter("probType");
						strBillType = request.getParameter("vBillType");
						System.out.println("########Value of strInvNo =  "+strInvNo);
						System.out.println("########Value of strConBillNo =  "+strConBillNo);
						System.out.println("Value of strProbType =  "+strProbType);
						System.out.println("Value of strBillType =  "+strBillType);
						if(strBillType!=null && strProbType != null && "invnum".equalsIgnoreCase(strProbType)) 
						{	if ("INV".equalsIgnoreCase(strBillType))
							{	if(strInvNo == null)
								{	
									strVal = request.getParameter("val1")==null?"":request.getParameter("val1");
									//System.out.println("########strVal1 =  "+strVal);
								}
								else if(strInvNo != null)
								{
									strVal = strInvNo;
									//System.out.println("########strVal2 =  "+strVal);
								}
								
								String cons_bill_dtls[] = invList.getConsBillNumber(strVal,iOrgId);
								if("Y".equals(cons_bill_dtls[0]))
								{
									strBillVal = cons_bill_dtls[1];
									//System.out.println("########strBillVal1 =  "+strBillVal);
								}
							}
							else 
							{
								if(strConBillNo == null) 
								{
									strBillVal = request.getParameter("val1")==null?"":request.getParameter("val1");
									//System.out.println("########strBillVal2 =  "+strBillVal);
								}
								else if(strConBillNo != null) 
								{
										strBillVal = strConBillNo;
										//System.out.println("########strBillVal3 =  "+strBillVal);
								}
							}
						}
						if(strConBillNo!=null && strConBillNo.length()>0){
							strBillVal = strConBillNo;
							//System.out.println("########strBillVal4 =  "+strBillVal);
						}

					%>
					<input name="invoiceNumber" type="text" size="30" maxlength="30" class="txtbox" value="<%=strVal%>" onChange="validateField(this)">
				</td>
				<td>
					<font class="promptReadOnly"> or <b> Consolidated Bill Number</b></font>
					&nbsp;
					<input name="consolidateBillNum" type="text" size="30" maxlength="30" value="<%=strBillVal%>" class="txtbox"  onChange="getInvoice();">
				</td>
				</tr>
				</table>
				</td>
			</tr>
			<!-- <tr><td height="10" colspan="2"></td></tr> -->
			<tr align="left">
				<td class="tableQuestionCell" colspan="2">
					<font class="promptReadOnly" color="#FF0000"><b>
						<%=strCheckRequestN2%>
					</b></font>
				</td>
			</tr>	
			<!-- <tr><td height="10" colspan="2"></td></tr> -->
			<tr>
				<td width="10"></td>
				<td>
					<table width="100%">
						<tr>							
            				<td align="left"> 
								<table>
								<tr>
								<td>
								<a class="btn_red" href="javascript:action_func2();">Invoice Search</a>								
								</td>
								<td> 
								<font class="promptReadOnly">or <b>Select an Invoice Number</b></font>
								&nbsp;
								<%
								String strProbsType = "",strBillTypes="",strBillVals="";
								strProbsType = request.getParameter("probType");
								strBillTypes = request.getParameter("vBillType");
								String selectedSelectInvoice = "";
								strBillVals = request.getParameter("consolidateBillNum");
								if(strBillTypes!=null && strProbsType != null && "invnum".equalsIgnoreCase(strProbsType)) 
								{
									if ("CONS".equalsIgnoreCase(strBillTypes))
									{
										strBillVals = request.getParameter("val1")==null?"":request.getParameter("val1");
										selectedSelectInvoice = "selected";
									}									
								}
								invoiceList = invList.getInvoiceList(iOrgId, strBillVal);
								String selected = "";
								%>
								<select name="invNumberList" id ="invNumberList" size="1"  class="searchBarLink" onChange="setInvoice();">
								 <option value=""  <%=selectedSelectInvoice%> >Select Invoice</option>
								  <%
                                if(invoiceList != null && invoiceList.size() > 0) 
								{
                                    for(int i=0; i<invoiceList.size(); i++) 
									{
                                        Canon_E193_InvoiceObj objInv = (Canon_E193_InvoiceObj)invoiceList.get(i);
										if(objInv.getStrTrxNum().equals(strVal))
										{
											selected = "selected";
										}
										else
										{
											selected = "";
										}
								%>
								 <option <%=selected%>  value = "<%=objInv.getStrTrxNum()%>"><%=objInv.getStrTrxNum()%></option>
								 <%
								 }
								 }
								 %>
								 
								</select>
								</td>
								</tr>
								</table>
								</td>
								</tr>
							<tr align="center"> 
	 			                <td align="center">
								<table>
									<tr>
										<td>
										<a class="btn_red" style="margin-left:13cm;" href="javascript:history.back();">Prev</a> 											
										</td>
										<td>
										<a class="btn_red" href="javascript:action_func1();">Next</a> 											
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>			
			<!-- check for invoice related issue ends -->
<%
		} else if (strBilling.equals("X4")) {
%>
			<tr>
				<td width="10"></td>
				<td><input name="billing" type="radio" value="Yes" onClick="onSelect('canon_E193_csEICheckRequest.jsp')"><font class="promptReadOnly">Yes</font></td>
			</tr>
			<tr>
				<td width="10"></td>
				<td><input name="billing" type="radio" value="No" onClick="onSelect('canon_E193_csNBIssueList.jsp')"><font class="promptReadOnly">No</font></td>		
			</tr>
			<tr align="right"> 
	 			<td>&nbsp;</td> 
	   			<td align="right">
					<table>
						<tr>
							<td>
							<a class="btn_red" href="javascript:history.back();">Prev</a>								
							</td>
							<td>
							<a class="btn_red" href="javascript:onNext();">Next</a>								
							</td>
						</tr>
					</table>
	 			</td>
			</tr>
			<!-- check if Billing issue ends -->
<%
        } // billing issue
%>


<!--  Changed For ITG #  By Chandra Sekhar - End  -->
</table>
</form>   

<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>
<script>
var msg = '<%=request.getParameter("errorMsg")==null?"":request.getParameter("errorMsg")%>';
if(msg != '' && msg != 'null') {
	alert(msg);
	if(document.csCheckRequest.invoiceNumber != null) 
		document.csCheckRequest.invoiceNumber.select();
	else if(document.csCheckRequest.ticketNumber != null) 
		document.csCheckRequest.ticketNumber.select();
}
</script>
