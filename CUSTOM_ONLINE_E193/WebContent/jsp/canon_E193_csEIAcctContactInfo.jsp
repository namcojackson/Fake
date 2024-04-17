<!-- $Header: canon_E193_csEIAcctContactInfo.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csEIAcctContactInfo.jsp - Capture Customer Information .
 |
 | DESCRIPTION
 |  Captures customer's name, contact number, email address if originator
 |  is internal.
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	07/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 +=======================================================================--%>
 <%@ include file="canon_E193_csTopInc.jsp" %>
 <% 
 	// Menu Prompt
	strPageName = "Enter & Inquiry";
	String hPath = request.getParameter("hPath");
	if (hPath == null)
		hPath = strAccountContactInformationT1;
	else
		if(hPath.indexOf(strAccountContactInformationT1) < 0)
			hPath = hPath + " -> " + strAccountContactInformationT1;
%>

 <%@ include file="canon_E193_csMenuInc.jsp" %> 
 
 <script language="JavaScript">
 
	// global variables
	phoneOK = true;
	emailOK = true;
	 
	// Action on button click 
	function actionFunction() {
		if( validateForm(document.custContactForm))	{
			document.custContactForm.action = "canon_E193_csEIAcctSearch.jsp";
			document.custContactForm.submit();
		}
	}
	 
	// Validate form
	function validateForm(thisForm)	{
		// Contact Name is mandatory
		if((thisForm.custName.value.length == 0) || (thisForm.custName.value == null) 
			|| (thisForm.custName.value == ""))	{
			alert("NAME is required.");
			thisForm.custName.focus();
			thisForm.custName.select();
			return false;
		}
		// phone no is mandatory
		if(thisForm.custPhCd.value == '' || thisForm.custPhNo.value == '' || thisForm.custPhNo2.value == '') {
			alert("Number is required.");
			if(thisForm.custPhCd.value == '') {
				thisForm.custPhCd.focus();
				thisForm.custPhCd.select();
			}else if(thisForm.custPhNo.value == '') {
				thisForm.custPhNo.focus();
				thisForm.custPhNo.select();
			}else if(thisForm.custPhNo2.value == '') {
				thisForm.custPhNo2.focus();
				thisForm.custPhNo2.select();
			}
			return false;
		}else if(!validatePhNum(thisForm.custPhCd)) {
			return false;
		}else if(!validatePhNum(thisForm.custPhNo)) {
			return false;
		}else if(!validatePhNum(thisForm.custPhNo2)) {
			return false;			
		}else if(!validatePhNum(thisForm.custPhNoExt)) {
			return false;
		}else if(thisForm.custPhCd.value != '' && thisForm.custPhCd.value.length < 3) {
			alert("Area code should be 3 digits");
			thisForm.custPhCd.focus();
			thisForm.custPhCd.select();
			return false;
		}else if(thisForm.custPhNo.value != '' && thisForm.custPhNo.value.length < 3) {
			alert("Phone no should be 3 digits");
			thisForm.custPhNo.focus();
			thisForm.custPhNo.select();
			return false;
		}else if(thisForm.custPhNo2.value != '' && thisForm.custPhNo2.value.length < 4) {
			alert("Phone no should be 4 digits");
			thisForm.custPhNo2.focus();
			thisForm.custPhNo2.select();
			return false;
		}

		if(!emailOK) {
			return validateEmail(thisForm.origEmailAddress);
		}
		return true;
	}

	function validatePhNum(thisObj) {
		if(isNaN(thisObj.value)) {
			alert("Please enter numbers only. ");
			thisObj.focus();
			thisObj.select();
			return false;
		}
		return true;
	}
	
	function fnNextTab(obj) {
		if(obj.name == "custPhCd" && obj.value.length == 3)
			document.custContactForm.custPhNo.focus();
		else if(obj.name == "custPhNo" && obj.value.length == 3)
			document.custContactForm.custPhNo2.focus();
		else if(obj.name == "custPhNo2" && obj.value.length == 4)
			document.custContactForm.custPhNoExt.focus();
	}
	
	// Validate email address
	function validateEmail(textfield){
		emailOK = true; 
		
		var vCountChar = 0;
		var vCountAts = 0;
		var vCountDots = 0;
		var vChar;
	
		// Check that the email Id only contains  "@ . a-z, A-Z, 0-9" and contains only one @ character
		// and atleast one dot character
		
		 if(textfield.value != null && textfield.value != '') {
			/* alert("Enter valid email address");
			textfield.focus();
			textfield.select();
			return false; */
		
	
			 for (var i=0; i < textfield.value.length; i++) {
				vChar = textfield.value.charAt(i);
		
				if ((vChar >= "0") && (vChar <= "9")){
				   vCountChar++;
				   continue;
				}
				if ((vChar >= "a") && (vChar <= "z")){
				   vCountChar++;
				   continue;
				}
				if ((vChar >= "A") && (vChar <= "Z")){
				   vCountChar++;
				   continue;
				}
				if (vChar == "@"){
				   if(vCountChar == 0)  {
					  emailOK = false;
					  break;
				   }else{
					  vCountAts++;
					  vCountChar = 0;
					  continue;
				   }
				}
				if (vChar == "."){
				   if(vCountChar == 0) {
					  emailOK = false;
					  break;
				   }else{
					  vCountDots++;
					  vCountChar = 0;
					  continue;
				   }
				}
			 }
		
			 // The string is OK if it contains only one @ character and atleast one dot character
			 if (textfield.value.length > 0)
			 	emailOK = emailOK && (vCountAts == 1) && (vCountDots > 0) && (vCountChar == 3);
			 else
			 	emailOK = true;
		
			 if (!emailOK){
			 	textfield.value = '';
				alert("Please enter email address in following format - xxxxxx@xxxx.xxx ");
				textfield.focus();
				textfield.select();
			 }
		 }
		 return emailOK;
	 }
	 	 
 </script>
	<div id="main_content">
	  	<div id="page_top">
	  		<h1 style="padding-top:20px;" id="headerText"><%=strAccountContactInformationT1%></h1>
			<div class="breadcrumb"><%=hPath%></div>
		</div>

	<form name="custContactForm" method="post" >
	
	<!-- hidden variables -->
	<%
		String strPhCd = request.getParameter("origPhCd")==null?"":request.getParameter("origPhCd");
		String strPhNo = request.getParameter("origPhNo")==null?"":request.getParameter("origPhNo");
		String strPhNo2 = request.getParameter("origPhNo2")==null?"":request.getParameter("origPhNo2");
		String strExtNo = request.getParameter("origPhNoExt")==null?"":request.getParameter("origPhNoExt");
		String origCheckbox = request.getParameter("origCheckbox") == null ? "N":request.getParameter("origCheckbox");
		
		String strFormatPhNo = "";
		if((!"".equalsIgnoreCase(strPhCd)) && (!"null".equalsIgnoreCase(strPhCd)))
			strFormatPhNo = strPhCd + "." + strPhNo + "." + strPhNo2;
		strFormatPhNo = strFormatPhNo + " Ext " + strExtNo;
	%>
	<input type="hidden" name="origName" value="<%=request.getParameter("origName")%>" >
	<input type="hidden" name="origPhNo" value="<%=strFormatPhNo%>">
	<input type="hidden" name="origEmailAddress" value="<%=request.getParameter("origEmailAddress")%>">
	<input type="hidden" name="origCheckbox" value="<%=origCheckbox%>">
	<input type="hidden" name="origType" value="<%=request.getParameter("origType")%>" >
    <input type="hidden" name="sourceType" value="<%=request.getParameter("sourceType")%>" >
	<input type="hidden" name="hPageFrom" value="EIAcctContactInfo" >
	<input type="hidden" name="hPath" value="<%=hPath%>">
	<table class="request-service" style="align:center;" cellpadding="5" cellspacing="0" border="0">

	<tr>	
	<td width="10">&nbsp;</td>	
      		<td colspan="2">
	  		<b><%=strAccountContactInformationQ1%></b>
	</td>
	</tr>
	<tr>
			    <td colspan="3">
			       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
				   </div>
			   </td>
			</tr>
	<tr>
		<td width="10"></td>
		<td align="left" >
			Name
		</td>
		<td align="left" >
			<input name="custName" type="text" size="35" maxlength="60" style="background-color: #ffff00" class="inTxt" >&nbsp;&nbsp;
			<%=strAccountContactInformationN3%>
		</td>
	</tr>
	<tr>
		<td width="10"></td>	
		<td align="left" >
			<font class="promptReadOnly">Number<font color="#FF0000">*</font></font>
		</td>
		<td align="left" >
			<input name="custPhCd" type="text" size="3" maxlength="3" style="background-color: #ffff00;width:120px;" class="inTxt" onkeyup="fnNextTab(this)">
			<input name="custPhNo" type="text" size="3" maxlength="3" style="background-color: #ffff00;width:120px;" class="inTxt" onkeyup="fnNextTab(this)">
			<input name="custPhNo2" type="text" size="4" maxlength="4" style="background-color: #ffff00;width:120px;" class="inTxt" onkeyup="fnNextTab(this)">
				Ext
			<input name="custPhNoExt" type="text" size="5" maxlength="5" style="background-color: #fff;width:80px;" class="inTxt"> 
		</td>
	</tr>
    <tr>
		<td width="10"></td>	
		<td align="left" >
			<font class="promptReadOnly">Email Address </font>
		</td>
		<td align="left" >
			<input name="custEmailAddress" type="text" size="35" maxlength="60" class="inTxt" onChange="validateEmail(this)">
		</td>
	</tr>
	<tr>
		<td colspan="3" height="10"></td>
	</tr>		
	</table>
		</form>	
		<div style="text-align:center">
			<a class="btn_red" href="javascript:history.back();">Prev</a> 	
			<a class="btn_red" style="margin-left: 5px;" href="javascript:actionFunction();">Next</a> 							
		</div>
	<%@ include file="canon_E193_csBottomInc.jsp" %>
	</div>
			
