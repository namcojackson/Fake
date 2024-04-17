<!-- $Header: canon_E193_csEIAcctSearch.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csEIAcctSearch.jsp - Enter search criteria.
 |
 | DESCRIPTION
 |  Captures customer search criteria.
 | 
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	07/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 17-Mar-2008  Chandra Sekhar      ITG #  Change .
 |
 |
 +=======================================================================--%>
 <%@ include file="canon_E193_csTopInc.jsp" %>
 <% 
 	// Menu Prompt
	strPageName = "Enter & Inquiry";
	
	// Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");
	
	String hPath = request.getParameter("hPath");
	if (hPath == null)
		hPath = strIdentificationOfInquiryAndAccountT1;
	else
		if(hPath.indexOf(strIdentificationOfInquiryAndAccountT1) < 0)
			hPath = hPath + " -> " + strIdentificationOfInquiryAndAccountT1;
 %>
 
 <%@ include file="canon_E193_csMenuInc.jsp" %> 
	
<script language="JavaScript">

	function onSelect(nPage,nVal) {
		document.acctSearchForm.hNextPage.value = nPage;
		document.acctSearchForm.hProbType.value = nVal;
		document.acctSearchForm.val1.focus();
		
		localStorage.setItem("hProbTypeSe", nVal);
		localStorage.setItem("hNextPageSe", nPage);
	}	
	
	function action_func1() {
		var OK = true;
		resetErroMsg();
		 if ((document.acctSearchForm.hProbType.value == null) || (document.acctSearchForm.hProbType.value == '') 
			|| (document.acctSearchForm.hProbType.length == 0)) {						
			displayErrorMsg("Please select one of the search criteria");
			OK = false;
		} 
		 if(localStorage.getItem("hProbTypeSe")== null || localStorage.getItem("hProbTypeSe") == ""){
			displayErrorMsg("Please select one of the search criteria");
			OK = false;
		}
		
		
		if ((OK == true) && ((document.acctSearchForm.val1.length == 0) || (document.acctSearchForm.val1.value == '') 
			|| (document.acctSearchForm.val1.value == null)) ) {						
			displayErrorMsg("Please enter value ");
			document.acctSearchForm.val1.focus();
			document.acctSearchForm.val1.select();
			OK = false;
		}
		if(!validateField(document.acctSearchForm.val1)) {
			OK = false;
		}
		
		if(localStorage.getItem("hProbTypeSe")!= null){
			document.acctSearchForm.hNextPage.value = localStorage.getItem("hNextPageSe");
			document.acctSearchForm.hProbType.value = localStorage.getItem("hProbTypeSe");
			//document.acctSearchForm.val1.focus();
		} 
		if(OK == true) {
			document.acctSearchForm.val1.value = document.acctSearchForm.val1.value.toUpperCase();
			document.acctSearchForm.action = document.acctSearchForm.hNextPage.value;
			document.acctSearchForm.submit();
		}	
	}
	
	//Start changes for S21 by Mangala
	
		function action_func3() {
		var objForm = document.acctSearchForm;
		//if(fnValidateField(objForm)) {
			//objForm.submitFlag.value = "quicktkt";
			objForm.action = "canon_E193_csQuickTicket.jsp";
			objForm.submit();
		//}
	}

		
		   function createQuickTicket(){
			   //alert("hello in create Quick Ticket");
			  // alert("First Name = "+"<%=request.getParameter("origFirstName")%>");
			var email = "<%=request.getParameter("origEmailAddress")%>" ;
			var firstName = "<%=request.getParameter("origFirstName")%>";
			var lastName = "<%=request.getParameter("origLastName")%>" ;
			var phoneCode = "<%=request.getParameter("origPhCd")%>"  ;
			var phone1 = "<%=request.getParameter("origPhNo")%>"  ;
			var phone2 = "<%=request.getParameter("origPhNo2")%>" ;
			var phoneExt = "<%=request.getParameter("origPhNoExt")%>";
			var qryStr="origEmailAddressQ="+email+"&origFirstNameQ="+firstName+"&origLastNameQ="+lastName+"&origPhCdQ="+phoneCode;
			qryStr=qryStr+"&origPhNoQ="+phone1+"&origPhNo2Q="+phone2+"&origPhNoExtQ="+phoneExt;
			window.location = "canon_E193_csQuickTicket.jsp?"+qryStr;
		   }
	
	//End Changes for S21 by Mangala
	
	function validateField(textfield) {
		var vChar;
		var OK = true;
		var wcOK = true;
		textfield.value = $.trim(textfield.value);
		var textFieldValue = textfield.value;
		
		var vProbType = document.acctSearchForm.hProbType.value;
		
		/* if (vProbType == "acctname" ) {
			var acctNameOK = true;
			//alert(textFieldValue.length);
			if(textFieldValue.length < 5) {
				acctNameOK = false;
				displayErrorMsg("Atleast five characters are required for this search criteria.");
				textfield.focus();
				textfield.select();
				return false;
			} else {
				var countChar = 0;
				var theChar = "";
				for (var i=0; i < textFieldValue.length; i++) {
					theChar = textFieldValue.charAt(i);
					if ((theChar >= "0") && (theChar <= "9")) {
						countChar++;
						continue;
					}
					if ((theChar >= "a") && (theChar <= "z")) {
						countChar++;
						continue;
					}
					if ((theChar >= "A") && (theChar <= "Z")) {
						countChar++;
						continue;
					}
					if (theChar == "%")	{
						if(countChar < 5) {
							acctNameOK = false
							break;
						} else
							acctNameOK = true;
					}
				}
			}
			
			
			if (acctNameOK == false) {								
				displayErrorMsg("Wild Card search not allowed here. Please enter complete value.");
				textfield.focus();
				textfield.select();
				return false;
			}
		} */
	
		for (var i=0; i < textFieldValue.length; i++) {
			vChar = textFieldValue.charAt(i);
			//alert(vChar + " " + vProbType);
			if ((vChar >= "0") && (vChar <= "9"))
				 continue;
			else if ((vChar >= "A") && (vChar <= "Z"))
				continue;
			else if ((vChar >= "a") && (vChar <= "z"))
				continue;
			else if (vChar == "-" && vProbType == "acctname")
            	continue;
			else if(vProbType == "sernum") // No validation for serial number
				continue;
			else if(vProbType == "ponum") // No validation for serial number
				continue;
			else if ((vChar== " ") && (vProbType == "acctname" ))
				continue;
			else if ((vChar == "%") && (vProbType != "acctname")) {
				wcOK = false;
				break;
			}
			else if ((vChar == "%") && (vProbType == "acctname"))
				continue;
			else {
				if (vProbType != "acctname")
				{
					OK = false;
					break;
				}
			}
		}
	 
		if (wcOK == false) {			
			displayErrorMsg("Wild Card search not allowed for this search criteria. Please enter complete value.");
			textfield.focus();
			textfield.select();
		}
		
		if (OK == false) {
			displayErrorMsg("Please enter valid value.");
			textfield.focus();
			textfield.select();
		}
		
    	return (OK && wcOK);
	}
	
	

 </script>
 <div id="main_content">
	<div id="page_top">
		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strIdentificationOfInquiryAndAccountT1 %></h1>
			<div class="breadcrumb"><%=hPath%></div>
	</div>

<form name="acctSearchForm" method="post" onsubmit="action_func1();">
	<!-- hidden variables -->

	<input type="hidden" name="origFirstName" value="<%=request.getParameter("origFirstName")%>">
	<input type="hidden" name="origLastName" value="<%=request.getParameter("origLastName")%>">
	<input type="hidden" name="origName" value="<%=request.getParameter("origName")%>">
	<%
		String strCustPhCd = "", strCustPhNo = "", strCustPhNo2 = "", strCustExtNo = "", strFormatPhNo = "";
		String strOrigPhCd = "", strOrigPhNo = "", strOrigPhNo2 = "", strOrigExtNo = "";
		
		strCustPhCd = request.getParameter("custPhCd")==null?"":request.getParameter("custPhCd");
		strCustPhNo = request.getParameter("custPhNo")==null?"":request.getParameter("custPhNo");
		strCustPhNo2 = request.getParameter("custPhNo2")==null?"":request.getParameter("custPhNo2");
		strCustExtNo = request.getParameter("custPhNoExt")==null?"":request.getParameter("custPhNoExt");
		
		String origCheckbox = request.getParameter("origCheckbox") == null ? "N":request.getParameter("origCheckbox");
		
		if("".equals(strCustPhNo)) {
			strOrigPhCd = request.getParameter("origPhCd")==null?"":request.getParameter("origPhCd");
			strOrigPhNo = request.getParameter("origPhNo")==null?"":request.getParameter("origPhNo");
			strOrigPhNo2 = request.getParameter("origPhNo2")==null?"":request.getParameter("origPhNo2");
			strOrigExtNo = request.getParameter("origPhNoExt")==null?"":request.getParameter("origPhNoExt");
			
			if((!"".equalsIgnoreCase(strOrigPhCd)) && (!"null".equalsIgnoreCase(strOrigPhCd)))
				strFormatPhNo = strOrigPhCd + "." + strOrigPhNo + "." + strOrigPhNo2;
			strFormatPhNo = strFormatPhNo + " Ext " + strOrigExtNo;
		}else{
			strFormatPhNo = request.getParameter("origPhNo");
		}
	%>
	<input type="hidden" name="origPhNo" value="<%=strFormatPhNo%>">
	<input type="hidden" name="origEmailAddress" value="<%=request.getParameter("origEmailAddress")%>">
	<input type="hidden" name="origCheckbox" value="<%=origCheckbox%>">
	<input type="hidden" name="origType" value="<%=request.getParameter("origType")%>" >
	<input type="hidden" name="sourceType" value="<%=request.getParameter("sourceType")%>" >		
	<%
		if(!"".equals(strCustPhNo)) {
			strFormatPhNo = "";
			if((!"".equalsIgnoreCase(strCustPhCd)) && (!"null".equalsIgnoreCase(strCustPhCd))) 
				strFormatPhNo = strCustPhCd + "." + strCustPhNo + "." + strCustPhNo2;
			strFormatPhNo = strFormatPhNo + " Ext " + strCustExtNo;
		}
		String strProbType = request.getParameter("probType")==null?"": request.getParameter("probType");
		String strVal1 = request.getParameter("val1")==null?"":request.getParameter("val1");
		
		String strSelAcctName = "";
		String strSelAcctNum = "";
		String strSelContNum = "";
		String strSelInvNum = "";
		String strSelOrdrNum = "";
		String strSelSerNum = "";				
		String strSelTktNum = "";
		String strSelPONum = "";
		
		if("acctname".equalsIgnoreCase(strProbType))
			strSelAcctName = "checked";
		if("acctnum".equalsIgnoreCase(strProbType))
			strSelAcctNum = "checked";
		if("contnum".equalsIgnoreCase(strProbType))
			strSelContNum = "checked";
		if("invnum".equalsIgnoreCase(strProbType))
			strSelInvNum = "checked";
		if("ordrnum".equalsIgnoreCase(strProbType))
			strSelOrdrNum = "checked";
		if("sernum".equalsIgnoreCase(strProbType))
			strSelSerNum = "checked";
		if("tktnum".equalsIgnoreCase(strProbType))
			strSelTktNum = "checked";
		if("ponum".equalsIgnoreCase(strProbType))
			strSelPONum = "checked";
	%>
	<input type="hidden" name="custName" value="<%=request.getParameter("custName")==null?request.getParameter("origName"):request.getParameter("custName")%>" >
	<input type="hidden" name="custPhNo" value="<%=strFormatPhNo%>">
	<input type="hidden" name="custEmailAddress" value="<%=request.getParameter("custEmailAddress")==null?request.getParameter("origEmailAddress"):request.getParameter("custEmailAddress")%>">
	
	<input type="hidden" name="hPageFrom" value="EIAcctSearch" >
	
	<!-- local hidden variables -->
	<input type="hidden" name="hProbType" value="<%=request.getParameter("probType")==null?"":request.getParameter("probType")%>">
	<input type="hidden" name="hPath" value="<%=hPath%>">
    <input type="hidden" name="hNextPage" value="<%=request.getParameter("hNextPage")==null?"":request.getParameter("hNextPage")%>">
	<input type="hidden" name="recurProb" value="no">
	<table class="request-service" style="align:center;" cellpadding="1" cellspacing="0" border="0">	 		    
	<tr>
	<td width="10"></td>		
      	<td colspan="2">
	  		<b><%=strIdentificationOfInquiryAndAccountQ2%></b>
	 	</td>
	</tr>
	<tr>
	    <td colspan="2">
	       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
				<p id="eMsg"></p>
		   </div>
	   </td>
	</tr>
    <!-- <tr>
		<td width="10"></td>	
		<td align="left" >
			<input type="radio" name="probType" <%=strSelAcctName%> value="acctname" onClick="onSelect('canon_E193_csEIAcctAddress.jsp','acctname')">
			<font class="promptReadOnly">Account Name</font>
		</td>
	</tr>	 -->
    <tr>
		<td width="10"></td>	
		<td align="left" >
			<!-- Changed For ITG #  By Chandra Sekhar
			<input type="radio" name="probType" <%=strSelAcctNum%> value="acctnum" onClick="onSelect('canon_E193_csEIAcctAddress.jsp','acctnum')">		
			-->
			<input type="radio" name="probType" <%=strSelAcctNum%> value="acctnum" onClick="onSelect('canon_E193_csEIAccountController.jsp','acctnum')">		
			<font class="promptReadOnly">Account Number</font>
		</td>
	</tr>
    <tr>
		<td width="10"></td>	
		<td align="left" >
			<input type="radio" name="probType" <%=strSelContNum%> value="contnum" onClick="onSelect('canon_E193_csEIAccountController.jsp','contnum')">		
			<font class="promptReadOnly">Contract Number</font>
		</td>
	</tr>	
    <tr>
		<td width="10"></td>	
		<td align="left" >
        	<input type="radio" name="probType" <%=strSelInvNum%> value="invnum" onClick="onSelect('canon_E193_csEIAccountController.jsp','invnum')"> 
        	<font class="promptReadOnly">Invoice Number / Consolidated Bill Number</font> </td>
	</tr>
    <tr>
		<td width="10"></td>	
		<td align="left" >
			<input type="radio" name="probType" <%=strSelOrdrNum%> value="ordrnum" onClick="onSelect('canon_E193_csEIAccountController.jsp','ordrnum')">		
			<font class="promptReadOnly">Order Number</font>
		</td>
	</tr>
    <tr>
		<td width="10"></td>	
		<td align="left" >
			<input type="radio" name="probType" <%=strSelSerNum%> value="sernum" onClick="onSelect('canon_E193_csEIAccountListController.jsp','sernum')">		
			<font class="promptReadOnly">Serial Number</font>
		</td>
	</tr>
    <tr>
		<td width="10"></td>	
		<td align="left" >
			<input type="radio" name="probType" <%=strSelTktNum%> value="tktnum" onClick="onSelect('canon_E193_csEIAccountController.jsp','tktnum')">		
			<font class="promptReadOnly">Ticket Number</font>
		</td>
	</tr>
	<tr>
		<td width="10"></td>	
		<td align="left" >
			<input type="radio" name="probType" <%=strSelPONum%> value="ponum" onClick="onSelect('canon_E193_csEIAccountListController.jsp','ponum')">		
			<font class="promptReadOnly">PO Number</font>
		</td>
	</tr>	
    <tr>
		<td width="10"></td>	
		<td align="left" >
			<font class="promptReadOnly">Value &nbsp;&nbsp;&nbsp;</font>
			<input name="val1" type="text" size="30" maxlength="30" class="inTxt" value="<%=strVal1%>">
		</td>
	</tr>						
	</table>
	</form>	
	<div style="text-align: center; margin-right: 20px;">
	        <a class="btn_red" href="javascript:history.back();">Prev</a> 
			<a class="btn_red" style="margin-left: 10px;" href="javascript:action_func1();">Next</a>
	<%
			System.out.println("role : " + role + "respId: " + objCiSession.getRespId());
			if("Y".equals(role)){
		    %>					
					<a class="btn_red" style="margin-left: 10px;" href="javascript:createQuickTicket()">Create Quick Ticket</a> 						
			<%
			}
			%>	
			<!-- <a class="btn_red" href="javascript:history.back();">Prev</a> 
			<a class="btn_red" style="margin-left: 10px;" href="javascript:action_func1();">Next</a> --> 	
	</div>	
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>
<script>
var msg = '<%=request.getParameter("errorMsg")==null?"":request.getParameter("errorMsg")%>';
if(msg != '' && msg != 'null') {
	alert(msg);
}
</script>
