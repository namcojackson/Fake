<!-- $Header: canon_E193_csEIHome.jsp $ -->
<%--========================================================================
 |
 | FILE 
 | canon_E193_csEIHome.jsp - Enter / Inquiry Home page
 |
 | DESCRIPTION
 |  Captures originators name, contact number, email address and type.
 |  Type specifies whether originator is a customer or internal.
 |
 | AUTHOR
 | Hema Doniparthi 
 |
 | CREATION DATE
 | 07/07/2005
 |
 | HISTORY
 | DATE        		WHO           WHY
 | 08/20/2013		Hema          Quick Ticket
 | 06/05/2013       Hema		  Modified for ITG#480668
 | 01/29/2016       Mangala 	  Modified for S21 Changes.
 |
 +=======================================================================--%>
	<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Severity" id="objSeverityDao" scope="page"/> 
 
 <%@ include file="canon_E193_csTopInc.jsp" %>
 <% 
   // Menu Prompt
      strPageName = "Quick Ticket";
   
   //session.removeValue("objSessionAcctInfo");
   //session.removeValue("alAddrList");
   String strName = objCiSession.getEmpName();
   if (strName.equals("") || strName.equals(" ") )
         strName = objCiSession.getUserName();

   
   String hPath = strQuickTicketT1;      
 %>  
 
  <%@ include file="canon_E193_csMenuInc.jsp" %> 
  <%@ page language="java" import="java.util.*" %>
  <%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>  
     <%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
    <%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
    <jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj" id="objHeaders" scope="page"/>
	<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj" id="objLines" scope="page"/>
	<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_NonBillingIssue" id="objNonBillDao" scope="page"/>
	<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_QuickTicketDAO" id="objQuickTicketDao" scope="page"/>
	<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Assignment" id="objAssignDao" scope="page"/>
	<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_AcctInfo" id="objCiAcctInfo" scope="page"/>
	
  <script language="JavaScript">
     emailOK = true; 
    
     function closeAccountModel () {
   		var modelName="#dlg1";
    	$(modelName).html("");
    	$(modelName).dialog("close");
    	$(modelName).dialog("destroy");
        $(modelName).trigger("closed"); 
     }
     
     function selectAccountPopUp () {
    	var modelName="#dlg1";
    	$('#partyName').val('');
		$('#acctNum').val('');
		$('#custAcctId').val('');
		$('#trxType').val('');
 		if($('#popUpPartyName').val() != '')
  		{
     	 	var accObj = document.getElementById("accName");
      		accObj.style.color = '';	
      		accObj.innerHTML = "<span style=\'background-color:#f0f0f0;padding:5px 5px;\'>"+ $('#popUpPartyName').val() +"</span>";
         	 
         	$('#partyName').val($('#popUpPartyName').val());
      		$('#acctNum').val($('#popUpAcctNum').val());
      		$('#custAcctId').val($('#popUpCustAcctId').val());
      		
      		$(modelName).html("");
         	 
      		$(modelName).dialog("close");
      		$(modelName).dialog("destroy");
            $(modelName).trigger("closed");
    	}else {
    		alert('Please select an account');
    	}
    }
     
     function populateAcctDetails(ele){
     	var objData= $(ele).data("obj");
     	var v=$(ele).val();
     	//alert("Selected Record \n\n\n\n" + objData);
     	$('#popUpPartyName').val(objData[0]);
   		$('#popUpAcctNum').val(objData[1]);
   		$('#popUpCustAcctId').val(objData[1]);
     }
     
	function getAccountName(){
		//alert("hello in getAccountName");
		var value = $('#val').val();
		var originalType = $('#origType').val();
		//alert("value = "+value);
		
		$('#partyName').val('');
		$('#acctNum').val('');
		$('#custAcctId').val('');
		$('#trxType').val('');
		$('#accName').html('');
		
		var origTypeName;
		if(originalType === 'accountNum'){
			origTypeName = 'Account Number:';
		}else if(originalType === 'invoiceNum'){
			origTypeName = 'Invoice Number:';
		}else if(originalType === 'ordNum'){
			origTypeName = 'Order Number:';
		}else if(originalType === 'serialNum'){
			origTypeName = 'Serial Number:';
		}else{
			origTypeName = 'Other:';
		}
		//alert("originalType = "+origTypeName + " value~ " + value );
		if(originalType!='' && originalType!='null'){
			//alert('alert("hello in if 1 value : '+value);
			if(originalType=='other'){
				//alert("hello in if2");
				var accObj = document.getElementById("accName");
				accObj.style.color = '';
				$('#val').val("Other");
					$('#accName').html('Other');
					$('#partyName').val('');
					$('#acctNum').val('');
					$('#custAcctId').val('');
					$('#trxType').val('');
			}else if(value!='' && value!='null'){
				//alert("hello in if3");
				if(value=='Other'){
					$('#val').val('');
					$('#accName').html('');
				}else{
					//alert("hello in if4");
					showPleaseWait();
					var url = "canon_E193_csGetAccountInfo.jsp";
					var qryStr="acctVal="+value+"&originalType="+originalType+"&orgId=" +<%=objCiSession.getOrgId()%>; // Newly Added this line
					//url = url+"&originalType="+originalType;
					//alert("URL = "+url+"?"+qryStr);
					var modelName = "#"+'accountInfoDiv';	
					//var count =  $("#report_tbl_first").attr('rows').length; 
					var count = 8;
					//alert("count = "+count);
				//	show();
					$.ajax({
						url: url,
						data:qryStr,
						type:"POST",
						cache: false,
						dataType : "text",
						success : function(data)
						{
							var sRes = JSON.parse(data);
							//alert(sRes)
							$(modelName).html(""); 
							
							if(sRes.length >1){
								openDlg(sRes);
							} else if(sRes.length == 1) {
								var acctData = sRes[0];
								//alert(acctData);
								var accObj = document.getElementById("accName");
								accObj.style.color = '';
								document.getElementById("accName").innerHTML = "<span style=\'background-color:#f0f0f0;padding:5px 5px;\'>"+ acctData[0]+"</span>";
								$('#partyName').val(acctData[0]);
								$('#acctNum').val(acctData[1]);
								$('#custAcctId').val(acctData[2]);
								$('#trxType').val(acctData[3]);
								if(originalType=='invoiceNum'){
									
									var newRow = "<tr id='invoiceTR' ><td width='10'></td> "+
									  "<td style='margin-bottom: 10px;width: 150px;'><font size='4'><b>"+origTypeName+"</b></font></td> "+
									  "<td><font size='4'><u><a href=javascript:invoiceDetailsPDF('"+value+"');>"+value+"</a></u></font>" +
									  "<\/td> </tr>";
									  $('#report_tbl_first').append(newRow); 
								}
								hidePleaseWait();
							}else{
								
								$('#accName').addClass('tableQuestionCell');
								var accObj = document.getElementById("accName");
								accObj.style.color = 'FF0000';						
								$('#accName').html('INVALID ACCOUNT');
								$('#partyName').val('');
								$('#acctNum').val('');
								$('#custAcctId').val('');
								$('#trxType').val('');
								hidePleaseWait();
							}
						},
						error : function(data)
						{
							hidePleaseWait();
						}
					});
				}				
			}
		}
	}
	
	function openDlg(objArr){
		$('#popUpPartyName').val('');
		$('#popUpAcctNum').val('');
		$('#popUpCustAcctId').val('');
		
    	var modelName="#dlg1";
        $(modelName).dialog({
            height: 600,
            title: "Select Account",
            modal: true ,
            autoOpen :false,
            width: 800,		
            resizable: false,      
        });
        
        var buttonHtml = "<div style='text-align: center; margin-right: 20px;'> <a class='btn_red' id='selectButton' href='javascript:selectAccountPopUp();'>Select</a>"; 
        buttonHtml+="<a class='btn_red' id='closeButton' style='margin-left: 10px;' href='javascript:closeAccountModel()'>Close</a> </div>";

        var tbl="<table id='tbl' class='customer-care' cellspacing='1'>";
        
        var count = objArr.length;
        
        for(i=0;i<objArr.length;i++){
        	 var o=objArr[i];
       
        	var tr="<tr>";
        	if(i==0){ // first obj is Labels
        		tr+="<th></th>";
        		for(j=0;j<o.length;j++){
                 tr+="<th>"+o[j]+"</th>";
        		}
                
        	}else{
        	    tr+="<td><input data-obj='"+JSON.stringify(o)+"'  type='radio' onclick='populateAcctDetails(this)' name='rdl' id='rdl"+(i-1)+"' value='"+o[1]+"' </td>";
        	    for(j=0;j<o.length;j++){
                    tr+="<td align='left' style='text-align:left;'>"+o[j]+"</td>";
           		}
            }
        	tr+="</tr>";
        	tbl+=tr;
        }
     	tbl+="</table>";
        tbl+="<table><br> ";
        tbl = tbl + buttonHtml;
        
        if(count > 10)
       	{
        	tbl = buttonHtml + "<br>" + tbl;
       	}
        
        $(modelName).html(tbl);
        hidePleaseWait();
        $(modelName).dialog("open");
        $(".ui-dialog-titlebar").addClass("hd");
  	    $(".ui-dialog").css({"top":"200px","position":"fixed"});
  	      	  
		 if(!($("input[name='rdl']").is(':checked')))
		{
			$('#rdl0').attr('checked', true);
			$('#rdl0').trigger( "click" );
		}
    }
	
	/*function fnGetReasonCode() {
		var val = document.origContactForm.parentCatCode.value;
		var vWin = window.open("canon_E193_csQuickTicketReasonCode.jsp?reasonCode="+val, "newwin","height=400,width=800,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
		vWin.focus();
	}
	function  invoiceDetails(invVal){   
		var vLink = "canonInvoiceDownloadNoSecurity.jsp?InvoiceNumber=" + invVal;
		var vWin = window.open(vLink, "newwin","height=300,width=900,toolbar=no,menubar=no,scrollbars=yes, resizable=yes, alwaysRaised=yes" );
		vWin.focus();
	}*/
	function submitQuickTicket(){
		$("#createButton").attr("disabled", true);
		$("#createButton").removeClass("btn_red");
		$("#createButton").addClass("btn_disabled");
		
		var isCorrectlyEnterDigit = true;
		 var reqSelector=".required, .requireds"; 
		 var thisForm=document.origContactForm;
		 var fullName = $('#origFirstName').val().toUpperCase() + ' ' + $('#origLastName').val().toUpperCase();
			$('#origName').val(fullName);
		 
		if(!validateParams(reqSelector)) { 
			if(!validatePhNum(thisForm.origPhCd)) {
		         return;
		      }else if(!validatePhNum(thisForm.origPhNo)) {
		         return ;
		      }else if(!validatePhNum(thisForm.origPhNo2)) {
		         return;  
//		       }else if(!validatePhNum(thisForm.origPhNoExt)) {
//		          return false;
		      }else if(thisForm.origPhCd.value != '' && thisForm.origPhCd.value.length < 3) {
		         alert("Area code should be 3 digits");
		         thisForm.origPhCd.focus();
		         thisForm.origPhCd.select();
		         //return false;
		          isCorrectlyEnterDigit = false;
		      }else if(thisForm.origPhNo.value != '' && thisForm.origPhNo.value.length < 3) {
		         alert("Phone no should be 3 digits");
		         thisForm.origPhNo.focus();
		         thisForm.origPhNo.select();
		         //return false;
		         isCorrectlyEnterDigit = false;
		      }else if(thisForm.origPhNo2.value != '' && thisForm.origPhNo2.value.length < 4) {
		         alert("Phone no should be 4 digits");
		         thisForm.origPhNo2.focus();
		         thisForm.origPhNo2.select();
		         //return false;
		         isCorrectlyEnterDigit = false;
		      }
			//alert("hello in if1");
			var acctType = $('#origType').val();
			var acctValue = $('#val').val();
			var notes = $('#notes').val();
			var reasonDesc = $('#reasonCdDesc').val();
			var partyName = $('#partyName').val();
			var checkFlag ='N';
			if(acctType=='other'){
				if(reasonDesc=='null' || reasonDesc==''){
						//alert("Reason Code is required.");
						$('#reasonCdDesc').focus();
						checkFlag ='Y';
					}else if(notes=='' || notes=='null'){
						//alert("Notes is required");
						$('#notes').focus();
						checkFlag ='Y';
					}else if(notes.length > 32000){
						alert("Total Size of Notes for an Issue Line is restricted to 32000 characters. Please use the attachment facility to add additional details.");
						checkFlag ='Y';
						$('#notes').focus();
					}
			}else{
				if(acctType=="" || acctType=="null"){
					//alert("Please select Account By");
					$('#origType').focus();
					checkFlag ='Y';
				}else if(acctValue=="" || acctValue=="null"){
					//alert("Please enter "+acctType+ " value.");
					$('#val').focus();
					checkFlag ='Y';
				}else if(reasonDesc=='null' || reasonDesc==''){
					//alert("Reason Code is required.");
					$('#reasonCdDesc').focus();
					checkFlag ='Y';
				}else if(notes=='' || notes=='null'){
					//alert("Notes is required");
					$('#notes').focus();
					checkFlag ='Y';
				}else if(notes.length > 32000){
					alert("Total Size of Notes for an Issue Line is restricted to 32000 characters. Please use the attachment facility to add additional details.");
					$('#notes').focus();
					checkFlag ='Y';
				}else if(partyName=='' || partyName=='null'){
					alert("Please enter valid Number or choose valid Account of multiple choices");
					$('#val').focus();
					checkFlag='Y';
				}
			}
			if(checkFlag =='N' && isCorrectlyEnterDigit === true){
				
				document.origContactForm.action = "canon_E193_csQuickTicketController.jsp";
				$('#Action').val('submit');
				//alert(" Form Going to submit!! ");
				document.origContactForm.submit();  		
			}
		}
		 $("#createButton").attr("disabled", false);
		  	$("#createButton").removeClass("btn_disabled");
		  	$("#createButton").addClass("btn_red");
	}
	function setReasonCode(reason) {
		$('#reasonCdDesc').val(reason); 
		//$('#reasonCode').val(code);
	}
   // Validate form 
   function validateForm(thisForm) {
      // Contact Name is mandatory
//      if((thisForm.origName.value.length == 0) || (thisForm.origName.value == null) 
//         || (thisForm.origName.value == "")) {
//         alert("NAME is required.");
//         thisForm.origName.focus();
//         thisForm.origName.select();
//         return false;
//    }
      if((thisForm.origFirstName.value.length == 0) || (thisForm.origFirstName.value == null) 
         || (thisForm.origFirstName.value == "")) {
         alert("First Name is required.");
         thisForm.origFirstName.focus();
         thisForm.origFirstName.select();
         return false;
      }
      if((thisForm.origLastName.value.length == 0) || (thisForm.origLastName.value == null) 
         || (thisForm.origLastName.value == "")) {
         alert("Last Name is required.");
         thisForm.origLastName.focus();
         thisForm.origLastName.select();
         return false;

      }
    //  thisForm.origName.value = thisForm.origLastName.value.toUpperCase() + ", " + thisForm.origFirstName.value.toUpperCase();
	var fullName = $('#origLastName').val().toUpperCase() +', '+$('#origFirstName').val().toUpperCase();
	$('#origName').val(fullName);
      // phone no is mandatory
      if(thisForm.origPhCd.value == '' || thisForm.origPhNo.value == '' || thisForm.origPhNo2.value == '') {
         alert("Number is required.");
         if(thisForm.origPhCd.value == '') {
            thisForm.origPhCd.focus();
            thisForm.origPhCd.select();
         }else if(thisForm.origPhNo.value == '') {
            thisForm.origPhNo.focus();
            thisForm.origPhNo.select();
         }else if(thisForm.origPhNo2.value == '') {
            thisForm.origPhNo2.focus();
            thisForm.origPhNo2.select();
         }
         return false;
      }else if(!validatePhNum(thisForm.origPhCd)) {
         return false;
      }else if(!validatePhNum(thisForm.origPhNo)) {
         return false;
      }else if(!validatePhNum(thisForm.origPhNo2)) {
         return false;  
      }else if(!validatePhNum(thisForm.origPhNoExt)) {
         return false;
      }else if(thisForm.origPhCd.value != '' && thisForm.origPhCd.value.length < 3) {
         alert("Area code should be 3 digits");
         thisForm.origPhCd.focus();
         thisForm.origPhCd.select();
         return false;
      }else if(thisForm.origPhNo.value != '' && thisForm.origPhNo.value.length < 3) {
         alert("Phone no should be 3 digits");
         thisForm.origPhNo.focus();
         thisForm.origPhNo.select();
         return false;
      }else if(thisForm.origPhNo2.value != '' && thisForm.origPhNo2.value.length < 4) {
         alert("Phone no should be 4 digits");
         thisForm.origPhNo2.focus();
         thisForm.origPhNo2.select();
         return false;
      }
      
      if(!emailOK) {
         return validateEmail(thisForm.origEmailAddress);
      }
      return true;
   }

   function validatePhNum(thisObj) {
      if(isNaN(thisObj.value)) {
         alert("Please enter numbers only.");
         thisObj.focus();
         thisObj.select();
         return false;
      }
      //Start Changes for S21 by Mangala to handle dot in phone number field
      else
    	  { for (var i=0; i < thisObj.value.length; i++) {
    	         vChar = thisObj.value.charAt(i);
    	         if (vChar == ".") {
    	        	 alert("Please enter numbers only.");
    	             thisObj.focus();
    	             thisObj.select();
    	             return false; 
    	        	 }
    	         }
    	  }
      //End Changes for S21 by Mangala
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
   
   function validateEmail(textfield) {
      emailOK = true; 
      var vCountChar = 0;
      var vCountAts = 0;
      var vCountDots = 0;
      var vChar;
   
       // Check that email address contains only "@ . a-z, A-Z, 0-9"  and
       // it should contains only one @ character and atleast one dot character
      
       if(textfield.value != null && textfield.value != '') {
         /* alert("Enter valid email address");
         textfield.focus();
         textfield.select();
         return false; */
       
        
	       for (var i=0; i < textfield.value.length; i++) {
	         vChar = textfield.value.charAt(i);
	   
	         if ((vChar >= "0") && (vChar <= "9")) {
	            vCountChar++;
	            continue;
	         }
	         if ((vChar >= "a") && (vChar <= "z")) {
	            vCountChar++;
	            continue;
	         }
	         if ((vChar >= "A") && (vChar <= "Z")) {
	            vCountChar++;
	            continue;
	         }
	         if (vChar == "@") {
	            if(vCountChar == 0) {
	              emailOK = false;
	              break;
	            } else {
	              vCountAts++;
	              vCountChar = 0;
	              continue;
	            }
	         }
	         if (vChar == ".") {
	            if(vCountChar == 0) {
	              emailOK = false;
	              break;
	            } else {
	              vCountDots++;
	              vCountChar = 0;
	              continue;
	            }
	         }
	       }
	   
	       // The string is OK if it contains only one
	       // ats character and atleast one dot character
	       if (textfield.value.length > 0)
	         emailOK = emailOK && (vCountAts == 1) && (vCountDots > 0) && (vCountChar == 3);
	       else
	         emailOK = true;
	   
	       if (!emailOK) {
	         textfield.value = '';   
	         alert("Please enter email address in following format - xxxxxx@xxxx.xxx ");
	         textfield.focus();
	         textfield.select();
	       }
       }
       return emailOK;
    } 
    function validateEmail1(email) 
	{
		var re = /\S+@\S+\.\S+/;
		return re.test(email);
	}
	/* function validateEmail(email) {
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

		if (!filter.test(email.value)) {
			 email.value = '';   
			 alert("Please enter valid email address");
			 email.focus();
			 email.select();
			 emailOK = false;
			return emailOK;
		}else{
			return emailOK;
		}
	 } */	
	
	
	
	 function setAccountName(){
		var origType = $('#origType').val();
		if(origType=='other'){
			$('#val').val("Other");
		}
		
	 
	 }
	 
	 function select_reasonCode() {
			var objForm = document.reasonCodeForm;
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
				alert("Please select the reasonCode");
			}else {				
				setReasonCode(val);				
				closeDlg();
			}
		}

 </script>
<%
	String strAction = request.getParameter("Action")==null?"":request.getParameter("Action");
	//Get Org ID
	int iOrgId = objCiSession.getOrgId();
	String strRegionCode = (String)objCiSession.getRegionCode();
	// get userId	
	String iUserId = objCiSession.getUserId();
	// get resource id
	//Start Changes for S21 by Mangala
	//int iResourceId = objCiSession.getResourceId();
	String iResourceId = objCiSession.getResourceId();
	//End Changes for S21 by Mangala
	System.out.println("strRegionCode : " + strRegionCode+ "iUserId : " + iUserId+ "iResourceId : " + iResourceId);
	int iTicketId = 0;
	int iLineId = 0;
	// getting severity codes for drop down list
	//System.out.println("!!!!!!!!!!! Before calling getseverity " );
	ArrayList alSeverityList = objSeverityDao.getSeverity();
	//System.out.println("!!!!!!!!!!! After calling getseverity = "+alSeverityList );
	//System.out.println("!!!!!!!!!!! After calling getseverity = "+alSeverityList );
%>
<div id="main_content">
	<div id="page_top">		
		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strQuickTicketT1 %></h1>
		<div class="breadcrumb"><%=hPath%></div>
	</div>
	
   <form name="origContactForm" id="origContactForm" method="post">
   <!-- Hidden variables -->
     <input type="hidden" name="partyName" id="partyName" value="">
    <input type="hidden" name="acctNum" id="acctNum" value="">
	<input type="hidden" name="custAcctId" id="custAcctId" value="">
	<input type="hidden" name="trxType" id="trxType" value="">
	<input type="hidden" name="parentCatCode" id="parentCatCode" value="NONBILL">
	<input type="hidden" name="origName" id="origName" value="">
	<input type="hidden" name="popUpPartyName" id="popUpPartyName" value="">
    <input type="hidden" name="popUpAcctNum" id="popUpAcctNum" value="">
	<input type="hidden" name="popUpCustAcctId" id="popUpCustAcctId" value="">
	
    <!-- <div style="margin-left:40px; margin-top: 50px"><font size="5" color="blue"><b>Quick Ticket:</b></font></div> -->
	<table class="request-servicehome" style="align:center;bgcolor:#FFFFFF" cellpadding="5" cellspacing="0" id="report_tbl_first">
    <%  if(iTicketId>0){ %>
   <tr>
   <td width="10">&nbsp;</td>
   <td>
  
   Ticket Number:<u><%=iTicketId%></u> 
   
   </td></tr>   
   <%}%>
   <tr>
	    <td colspan="3">
	       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
				<p id="eMsg"></p>
		   </div>
	   </td>
	</tr>
	<!-- First Name, Last Name, Ph No. info... Begin -->
    <tr>
    <td colspan=3>
    <table>
   <tr>
      <td width="10">&nbsp;</td>
      <td><b>First Name:</b></td>
      <td align="left">
         <input name="origFirstName" id="origFirstName" type="text" size="35" maxlength="60" class="inTxt required" style="background-color: #ffff00" value="<%=request.getParameter("origFirstNameQ")==null?"":request.getParameter("origFirstNameQ")%>">&nbsp;&nbsp;
         <!--font class="promptReadOnly" color="#FF0000">Enter the First Name</font-->
      </td>
   </tr>
   <tr>
      <td width="10">&nbsp;</td>
      <td><b>Last Name:</b></td>
      <td>
         <input name="origLastName" id="origLastName" type="text" size="35" maxlength="60"  class="inTxt required" style="background-color: #ffff00" value="<%=request.getParameter("origLastNameQ")==null?"":request.getParameter("origLastNameQ")%>">&nbsp;&nbsp;
      </td>
   </tr>
   
   <tr>
      <td width="10">&nbsp;</td> 
      <td>
        <b> Number: </b>
      </td>
      <td>
         <input name="origPhCd" id="origPhCd" type="text" size="3" maxlength="3" class="inTxt required" style="background-color: #ffff00;width:120px;" onkeyup="fnNextTab(this)" value="<%=request.getParameter("origPhCdQ")==null?"":request.getParameter("origPhCdQ")%>">
         <input name="origPhNo" id="origPhNo" type="text" size="3" maxlength="3" class="inTxt required" style="background-color: #ffff00;width:120px;" onkeyup="fnNextTab(this)" value="<%=request.getParameter("origPhNoQ")==null?"":request.getParameter("origPhNoQ")%>">
         <input name="origPhNo2" id="origPhNo2" type="text" size="4" maxlength="4" class="inTxt required" style="background-color: #ffff00;width:120px;" onkeyup="fnNextTab(this)" value="<%=request.getParameter("origPhNo2Q")==null?"":request.getParameter("origPhNo2Q")%>">
         <font class="promptReadOnly"><b>Ext</b></font>
         <input name="origPhNoExt" id="origPhNoExt" type="text" size="5" maxlength="5" class="inTxt" style="background-color: #fff;width:80px;" value="<%=request.getParameter("origPhNoExtQ")==null?"":request.getParameter("origPhNoExtQ")%>"> 
      </td>
   </tr>
    <tr>
      <td width="10"></td> 
      <td>
       <b>  Email Address: </b>
      </td>
      <td>
         <input name="origEmailAddress" id="origEmailAddress" type="text" size="35" maxlength="60"  class="inTxt"  onChange="validateEmail(this)" value="<%=request.getParameter("origEmailAddressQ")==null?"":request.getParameter("origEmailAddressQ")%>">
      </td>
   </tr>
   <tr> 
      <td width="10"></td>
      <td align="left"><b>Identify Account By:</b></td>
      <td align="left"nowrap>
	  <%
		 List searchTypes = objQuickTicketDao.getQTSearchTypes();
	  %>
        <select name="origType" id="origType" size="1" class="inTxt required"  style="background-color: #ffff00" onchange="getAccountName()" >
		  <option value="">Select One</option>
		<%
				if(searchTypes!=null && searchTypes.size()>0){
					for(int j=0;j<searchTypes.size();j++){
					String strArray[] = (String[]) searchTypes.get(j);
					//System.out.println("Identify Account By: " + strArray[0] );
		%>
					<option value="<%=strArray[0]%>"><%=strArray[1]%></option>
					<%
					}
				}%>
          </select> 
		 
		 	&nbsp;&nbsp;&nbsp; 
		 	<input type="text" name="val" id="val"  class="inTxt required" style="background-color: #ffff00" 
		 		value="<%=request.getParameter("val")==null?"":request.getParameter("val")%>" onchange="getAccountName()" on>
      </td>
	 <td id="accName"></td>
	<!--  <div id='accName' class="tableQuestionCell"></div>-->
	
   </tr>
   </table>
   </td>
   </tr> <!-- First Name, Last Name, Ph No. info... End -->
  
  <!--  <td colspan="2">&nbsp;</td> -->
    <%
		String partyName = request.getParameter("partyName")==null?"":request.getParameter("partyName");
		if(partyName!=null && partyName!=""){
		%>
		 <tr>	
		  <td id='accName' nowrap ><span style="background-color:#f0f0f0;padding:5px 5px;"><%=partyName%></span></td>
		  </tr>
		<%
		}
		/* else{ */
	  %>
	  <!-- <tr>
	  <td id='accName' nowrap>&nbsp;</td>
	  </tr> -->
	  <%-- <% --%>
	 <!--  }
	  %> -->
   
   </table>
	<div style="margin-left:30px; margin-top: 20px;">
		<font color="#FF0000"> <b><%=strNBIssueCaptureN1%></b>
		</font>
	</div>
	<table cellspacing="1" class="supplies-tablehome" style="width: 50%;margin-left:30px; margin-top: 20px;">
				<tr> 
					<th><a href="javascript:void(0)" onClick="javascript:getLovWithValue('canon_E193_csQuickTicketReasonCode.jsp','reasonCode','document.origContactForm.parentCatCode.value','Reason Code');">Reason Code</a><%=strReasonCodeN1%></th>
					<th>Urgency</th>
				</tr>
				<input type="hidden" name="reasonCode" id="reasonCode" value="">
				
				<tr>
						
  						<td><input type="text" name="reasonCdDesc" id="reasonCdDesc" onClick="javascript:getLovWithValue('canon_E193_csQuickTicketReasonCode.jsp','reasonCode','document.origContactForm.parentCatCode.value','Reason Code');" value="<%=request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc")%>" class="inTxt required" style="background-color:#ffff00" size="80" readonly=true></td>
  						<td>
  							<select name="severity" id="severity" size="1" class="searchBarLink">
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
   <div style="margin-left:30px; margin-top: 20px;"><font color="#FF0000"><b><%=strNBIssueCaptureN2%></b></font></div>     	
  	<%
  			String strNotesDisplay = request.getParameter("notes")==null?"":request.getParameter("notes");
   	%>

		<div style="margin-left:30px; margin-top: 20px;">
			<textarea name="notes" id="notes" rows="10" style="height:auto; width:800px;" wrap="OFF" class="inTxt required"><%=strNotesDisplay%></textarea>		
		
		</div>
   <div style="text-align: center; margin-top:20px;margin-bottom: 5px;"><a id="createButton" class="btn_red" href="javascript:submitQuickTicket();">Create Quick Ticket</a></div>   
   </form>

 <%@ include file="canon_E193_csBottomInc.jsp" %>
  <div id="accountInfoDiv">
  </div>
  <div id="dlg"></div>
  <div id="dlg1"></div>
  </div>
