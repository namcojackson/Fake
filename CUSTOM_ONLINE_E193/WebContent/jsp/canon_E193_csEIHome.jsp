<!-- $Header: canon_E193_csEIHome.jsp $ -->
<%--========================================================================
 |
 | FILE 
 | canon_E193_csEIHome.jsp - Enter / Inquiry Home page
 |
 | DESCRIPTION
 |  Captures originators name, contact number, email address and type.
 |  Type specifies whether originator is a customer or internal
 |
 | AUTHOR
 | Dipti Shedji 
 |
 | CREATION DATE
 | 07/07/2005
 |
 | HISTORY
 | DATE        WHO               WHY
 | 23-Aug-13   Hema Doniparthi	 ITG#361257.
 |
 |
 +=======================================================================--%>

 <%@ include file="canon_E193_csTopInc.jsp" %>
 <jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_EmailValidation" id="objEmailDao" scope="page"/>
 <%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
 <%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
 <%@page import="com.canon.oracle.custapp.util.CanonCustAppExceptionUtil" %>
 <%
 	// Menu Prompt
       strPageName = "Enter & Inquiry";
      // CanonE193MailNotificationDao canonMailDao = new CanonE193MailNotificationDao();
    // session.removeValue("objSessionAcctInfo");
    // session.removeValue("alAddrList");
    // Below Code for temporary purpose.
   /*  try{
 	   System.out.println("in csEIHome.JSP");
      canonMailDao.processEmails();
    }catch(CanonCustAppExceptionUtil e ) {	
 	   e.printStackTrace();
    }  */
    /* try{ // AutoSys Jobs.
    	CanonE193DataManagementDao canonE193Data = new CanonE193DataManagementDao();
    	System.out.println("Create Ticket Job:= "+ canonE193Data.createTicket());
    }catch(Exception e) {
    	
    } */
    String strName = objCiSession.getEmpName();
    if (strName.equals("") || strName.equals(" ") )
          strName = objCiSession.getUserName();
   
    String hPath = strGreetingsT1;
 %>  
 
 <%@ include file="canon_E193_csMenuInc.jsp" %> 

 <%-- <%@page import="com.canon.oracle.custapp.custinq.dao.Canon_E193_EmailValidation" %> --%>
  <%-- <jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_EmailValidation" id="objEmailValidationDao" scope="page"/>   --%>

<script language="JavaScript">
	

   var now = new Date();
   var hours = now.getHours();
   var vGreetString = "";
   vGreetString = "Good Morning";
   if (hours > 11)
      vGreetString = "Good Afternoon";
   if (hours > 15)
      vGreetString = "Good Evening";
  
  
    // global variables
    phoneOK = true;
    emailOK = true;
 
   // Action on button click 
   function actionFunction() 
   {
	   	var isCorrectlyEnterDigit = true;
	   	var reqSelector=".required, .requireds";
	   	var thisForm=document.origContactForm;
	   	if(!validateParams(reqSelector))
	   	{
			if(!validatePhNum(thisForm.origPhCd)) {
		         return;
		    }else if(!validatePhNum(thisForm.origPhNo)) {
		         return;
		    }else if(!validatePhNum(thisForm.origPhNo2)) {
		         return;  
		    }else if(thisForm.origPhCd.value != '' && thisForm.origPhCd.value.length < 3) {
		         alert("Area code should be 3 digits");
		         thisForm.origPhCd.focus();
		         thisForm.origPhCd.select();
		         return false;
		         isCorrectlyEnterDigit = false;
		    }else if(thisForm.origPhNo.value != '' && thisForm.origPhNo.value.length < 3) {
		         alert("Phone no should be 3 digits");
		         thisForm.origPhNo.focus();
		         thisForm.origPhNo.select();
		         isCorrectlyEnterDigit = false;
		         return false;
		    }else if(thisForm.origPhNo2.value != '' && thisForm.origPhNo2.value.length < 4) {
		         alert("Phone no should be 4 digits");
		         thisForm.origPhNo2.focus();
		         thisForm.origPhNo2.select();
		         isCorrectlyEnterDigit = false;
		         return false;
		    }
		    var firstName=document.origContactForm.origFirstName.value;
		    var lastName=document.origContactForm.origLastName.value;
		    var origName=firstName + " " + lastName;
		    document.origContactForm.origName.value=origName;
		    	   
			if(!checkEmails())
		   	{
				return false;
		   	}
		   
		    if(document.origContactForm.origType.value == "customer" && isCorrectlyEnterDigit===true) {
	            document.origContactForm.action = "canon_E193_csEIAcctSearch.jsp";
	            document.origContactForm.submit();        
	        } else if(isCorrectlyEnterDigit===true){
	            document.origContactForm.action = "canon_E193_csEIAcctContactInfo.jsp";
	            document.origContactForm.submit();
	        } 
		}
	}
   
	function createQuickTicket(){
	 	//alert("hello in create Quick Ticket");
		var email = document.getElementById("origEmailAddress").value;
		var firstName = document.getElementById("origFirstName").value;
		var lastName = document.getElementById("origLastName").value;
		var phoneCode = document.getElementById("origPhCd").value;
		var phone1 = document.getElementById("origPhNo").value;
		var phone2 = document.getElementById("origPhNo2").value;
		var phoneExt = document.getElementById("origPhNoExt").value;
		var qryStr="origEmailAddressQ="+email+"&origFirstNameQ="+firstName+"&origLastNameQ="+lastName+"&origPhCdQ="+phoneCode;
		qryStr=qryStr+"&origPhNoQ="+phone1+"&origPhNo2Q="+phone2+"&origPhNoExtQ="+phoneExt;
		window.location = "canon_E193_csQuickTicket.jsp?"+qryStr;
	}

	function validatePhNum(thisObj) {
    	if(isNaN(thisObj.value)) {
        	alert("Please enter numbers only.");
        	thisObj.focus();
        	thisObj.select();
         	return false;
      	}
      	return true;
   	}
   
    function fnNextTab(obj) {
    	if(obj.name == "origPhCd" && obj.value.length == 3)
        	document.origContactForm.origPhNo.focus();
      	else if(obj.name == "origPhNo" && obj.value.length == 3)
         	document.origContactForm.origPhNo2.focus();
      	else if(obj.name == "origPhNo2" && obj.value.length == 4)
         	document.origContactForm.origPhNoExt.focus();                  
    }
   
    //Start Changes for S21 by Mangala
    //Logic for Multiple email address validation
   	function checkEmail(textfield) {
		// var regExp = /(^[a-z]([a-z_\.]*)@([a-z_\.]*)([.][a-z]{3})$)|(^[a-z]([a-z_\.]*)@([a-z_\.]*)(\.[a-z]{3})(\.[a-z]{2})*$)/i;
	  	var regExp = /(^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)/i;
	   	return regExp.test(textfield);
	}

	function checkEmails(){
	   	emailOK = true;
	   	if(document.getElementById("origCheckbox").checked == true)
	   	{
	   		document.getElementById("origCheckbox").value = "Y";
	   		
			var emails = document.getElementById("origEmailAddress");
			emails.style.backgroundColor = "#ffff00";
			var emailsValue = document.getElementById("origEmailAddress").value;
	   		var emailArray = emailsValue.split(",");
	   		if(emailsValue == '') {
				alert("Enter valid email address");
				emails.focus();
		        return false;
		    }
		   	for(i = 0; i <= (emailArray.length - 1); i++){
		   		if(checkEmail(emailArray[i])){
		   			emailOK = true; 
		   		}else{
		   			emailOK = false;
		   			break;
		   		}
		   	}
		   	if(!emailOK){
		   		alert("Please enter email address in following format - xxxxxx@xxxx.xxx ");
		   		emails.focus();
		   		return false;
		   	}
		   	else{
		   		//Logic to get Emanage registered users
	   			getEmanageUsers();
				return true;
		   	}
	   	}
	   	else 
	   	{
	   		document.getElementById("origCheckbox").value = "N";
	   		document.getElementById("origEmailAddress").style.backgroundColor = "#ffffff";
	   		getEmanageUsers();
	   		return true;	
	   	}
	}
	
	function getEmanageUsers(){
		var url = "canon_E193_csEIHomeUtil.jsp";
		var emails = document.getElementById("origEmailAddress").value;
		$.ajax( {
   			url : url,
   			cache : false,
   			type : "POST",
   			data : "email= "+emails,
   			success : function(data) {
   				$("#emanageUsers").html(data);
   		 	}
   		});
	}
	
	    
	//End Changes for S21 by Mangala   
 </script>
 
   <%--  <%
	  String strOrigEmailAddress = request.getParameter("origEmailAddress")==null?"":request.getParameter("origEmailAddress");
	  /* String selectEmanageUser = objEmailValidationDao.getEmanageUsername(strOrigEmailAddress); */
    %>  --%> 

  <div id="main_content">
   		<div id="page_top">
   			<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strGreetingsT1%></h1>
   			<div class="breadcrumb"><%=hPath%></div>
		</div>
   <form name="origContactForm" method="post">
    <input type="hidden" name="hPageFrom" value="EIHome">
   <input type="hidden" name="hPath" value="<%=hPath%>">
   <input type="hidden" name="origName" value="">
   
   <table class="request-servicehome" style="align:center;bgcolor:#FFFFFF" cellpadding="5" cellspacing="0" >  
   <!-- Hidden variables -->
    <tr>
    <td width="10" colspan="3" >&nbsp;</td>
    <!--  <td colspan="2">
         <b><%=strGreetingsT1%></b>
      </td> --> 
   <tr>           
   <tr>
    <td width="10">&nbsp;</td>
      <td colspan="2">
         <b><script>document.write(vGreetString);</script>&nbsp;<%=strGreetingsM1%><br><%=strGreetingsM2%><%=strName%><%=strGreetingsM3%></b>
      </td>
   <tr>
   </tr>     
   <tr>   
    <td width="10">&nbsp;</td>  
      <td colspan="2"><b><%=strGreetingsQ1%> &nbsp;</b></td>
   </tr>     
	<tr>
	    <td colspan="3">
	       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
				<p id="eMsg"></p>
		   </div>
	   </td>
	</tr>
	<!-- First Name, Last Name, Ph No. info... Begin -->
        <tr>
          <td colspan=2>
      <table>
   <tr>
      <td width="10"></td>
      <td align="left">
        <b> First Name: </b>
      </td>
      <td align="left" >
         <input name="origFirstName" id="origFirstName" type="text" size="35" maxlength="60" class="inTxt required" style="background-color: #ffff00">&nbsp;&nbsp;
         <!--font class="promptReadOnly" color="#FF0000">Enter the First Name</font-->
      </td>
   </tr>
   <tr>
      <td width="10"></td>
      <td align="left">
       <b>  Last Name: </b>
      </td>
      <td align="left" >
         <input name="origLastName" id="origLastName" type="text" size="35" maxlength="60" class="inTxt required" style="background-color: #ffff00">&nbsp;&nbsp;
         <!--font class="promptReadOnly" color="#FF0000">Enter the Last Name</font-->
      </td>
   </tr>
   
   <tr>
      <td width="10"></td> 
      <td align="left" >
       <b>  Number: </b>
      </td>
      <td align="left" >
         <input name="origPhCd" id="origPhCd" type="text" size="3" maxlength="3" class="inTxt required" style="background-color: #ffff00;width:120px;" onkeyup="fnNextTab(this)">
         <input name="origPhNo" id="origPhNo" type="text" size="3" maxlength="3" class="inTxt required" style="background-color: #ffff00;width:120px;" onkeyup="fnNextTab(this)">
         <input name="origPhNo2" id="origPhNo2" type="text" size="4" maxlength="4" class="inTxt required" style="background-color: #ffff00;width:120px;" onkeyup="fnNextTab(this)">
        <b> Ext </b>
         <input name="origPhNoExt" id="origPhNoExt" type="text" size="5" maxlength="5" class="inTxt" style="background-color: #fff;width:80px;"> 
      </td>
   </tr>
   
    <tr>
      <td width="10"></td> 
      <td align="left" >
        <b> Email Address: </b>
      </td>
      <td align="left" >
     
         <input name="origEmailAddress" id="origEmailAddress"  type="text" size="35" maxlength="400" class="inTxt txtbox" onChange="checkEmails();"> 
		 <input name="origCheckbox" id = "origCheckbox" type="checkbox" value="N"  onchange="checkEmails();"><font class="promptReadOnly">&nbsp;<b>Confirmation</b></font>
      </td>
   </tr>
   <tr></tr>
    <tr>
      <td width="10"></td> 
      <td align="left" >           
         <font class="promptReadOnly"><b>MyCSA User:</b> </font>
      </td>
      <td align="left" >
      <font class="promptEmanage" >
      <div id="emanageUsers"></div>
        </font>
      </td>
      </tr>
   <tr> 
      <td width="10"></td>
      <td align="left" >
         <b> Select Caller: </b>
      </td>
      <td align="left">
         <select name="origType" id="origType" size="1"  style="background-color: #ffff00;" class="inTxt required">
            <option value="">Select One</option>
            <option value="customer">Customer</option>
            <option value="thirdParty">Third Party</option>
            <option value="internal">Internal</option>   
         </select>
      </td>
   </tr>
   <tr> 
      <td width="10"></td>
      <td align="left" >
         <b> Select Source: </b>
      </td>
      <td align="left">
         <select name="sourceType" id="sourceType" size="1"  style="background-color: #ffff00;" class="inTxt required">
            <option value="">Select One</option>
            <option value="Phone" selected>Phone</option>
            <option value="Email">Email</option>
            <option value="Mail">Mail</option>   
         </select>
      </td>
   </tr>
   </table>
   </td>
   </tr>   <!-- First Name, Last Name, Ph No. info... End -->
   <tr valign="top" align="center">
						<td style="text-align: center;" colspan="3">
						<a class="btn_red" href="#" onclick= "javascript:actionFunction();">Next</a>
						 <%
							System.out.println("role : " + role + "respId : " + objCiSession.getRespId());
							if("Y".equals(role)){
						 %>
							<a class="btn_red" style="margin-left: 10px;" href="javascript:createQuickTicket()">Create Quick Ticket</a> 
							<%
							}
							%>
							
						</td>
					</tr>   
   </table>   
   </form>
  </div>
   <%@ include file="canon_E193_csBottomInc.jsp" %>
    
