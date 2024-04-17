/*========================================================================
 |
 | FILE 
 |  canon_E193_csHomeVald.js - Javascript validations for CFS home page..
 |   
 | DESCRIPTION
 |   For a given criteria insert records and forwards it to respective jsp page.
 |
 | AUTHOR
 |  Kireet K Bollam 
 |
 | CREATION DATE
 |  12/08/2006
 |
 | HISTORY
 | DATE         WHO              WHY
 | 12/08/2006   Kireet K Bollam  Inital version
 | 03/24/2008   Chandra Sekhar   ITG # 156613 - New Sub Categories
 |
 +=======================================================================*/

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
function actionFunction() {
	if (validateForm(document.origContactForm)) {
		if (document.cfsBillingIssueForm.origType.value == "customer") {
			document.cfsBillingIssueForm.action = "canon_E193_csEIAcctSearch.jsp";
			document.cfsBillingIssueForm.submit();
		} else {
			document.cfsBillingIssueForm.action = "canon_E193_csEIAcctContactInfo.jsp";
			document.cfsBillingIssueForm.submit();
		}
	}
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

	// Changed For ITG # 156613 By Chandra Sekhar - Start
      if((thisForm.category.value.length == 0) || (thisForm.category.value == null) 
			|| (thisForm.category.value == "Select One")) {
		alert("Please Select Category Other Than  'Select One'.");
		thisForm.category.focus();
		return false;
	}
	// Changed For ITG # 156613 By Chandra Sekhar - End

      thisForm.origName.value = thisForm.origFirstName.value.toUpperCase() + " " + thisForm.origLastName.value.toUpperCase();
	// phone no is mandatory
      if(thisForm.origPhCd.value == '' || thisForm.origPhNo.value == '' || thisForm.origPhNo2.value == '') {
		alert("Number is required.");
		if (thisForm.origPhCd.value == '') {
			thisForm.origPhCd.focus();
			thisForm.origPhCd.select();
		} else if (thisForm.origPhNo.value == '') {
			thisForm.origPhNo.focus();
			thisForm.origPhNo.select();
		} else if (thisForm.origPhNo2.value == '') {
			thisForm.origPhNo2.focus();
			thisForm.origPhNo2.select();
		}
		return false;
	} else if (!validatePhNum(thisForm.origPhCd)) {
		return false;
	} else if (!validatePhNum(thisForm.origPhNo)) {
		return false;
	} else if (!validatePhNum(thisForm.origPhNo2)) {
		return false;
	} else if (!validatePhNum(thisForm.origPhNoExt)) {
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

	// caller is mandatory
	if (thisForm.origType.value == '') {
		alert("Select Caller is required.");
		thisForm.origType.focus();
		return false;
	}
	if (!emailOK) {
		return validateEmail(thisForm.origEmailAddress);
	}
	// make sure that the size of CFS Serial Number is either 0 or atleast 5.
	if (thisForm.origCFSSerialNumber.value != '') {
		return validateCFSSerialNumber(thisForm.origCFSSerialNumber);
	}

	//alert('validateform returning true...');
	return true;
}

function validatePhNum(thisObj) {
	if (isNaN(thisObj.value)) {
		alert("Please enter numbers only.");
		thisObj.focus();
		thisObj.select();
		return false;
	}
	return true;
}

function fnNextTab(obj) {
	if (obj.name == "origPhCd" && obj.value.length == 3)
		document.cfsBillingIssueForm.origPhNo.focus();
	else if (obj.name == "origPhNo" && obj.value.length == 3)
		document.cfsBillingIssueForm.origPhNo2.focus();
	else if (obj.name == "origPhNo2" && obj.value.length == 4)
		document.cfsBillingIssueForm.origPhNoExt.focus();
}

function validateEmail(textfield) {
	emailOK = true;
	var vCountChar = 0;
	var vCountAts = 0;
	var vCountDots = 0;
	var vChar;

	// Check that email address contains only "@ . a-z, A-Z, 0-9"  and
	// it should contains only one @ character and atleast one dot character

	if (textfield.value != null && textfield.value != '') {
		//alert("Enter valid email address");
		//textfield.focus();
		//textfield.select();
		//return false;
	

		for (var i = 0; i < textfield.value.length; i++) {
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
				if (vCountChar == 0) {
					emailOK = false;
					break;
				} else {
					vCountAts++;
					vCountChar = 0;
					continue;
				}
			}
			if (vChar == ".") {
				if (vCountChar == 0) {
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

function validateCFSSerialNumber(textfield) {
	var serialNumberStr = textfield.value;
	serialNumberStr = serialNumberStr.replace(/^\s*|\s*$/g, "");
	if (serialNumberStr.length < 5) {
		alert("Please ensure that the size of CFS Serial Number is either 0 or atleast 5.");
		textfield.focus();
		textfield.select();
		return false;
	}
	return true;
}

/*function fnGetReasonCode() {
    var val = document.cfsBillingIssueForm.hParentCatCode.value;
    var vWin = window.open("canon_E193_csTicketReasonCodeP.jsp?reasonCode="+val, "newwin","height=300,width=940,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
    vWin.focus();
}*/

function action_func1() {
	var objForm = document.cfsBillingIssueForm;
	if (objForm.iLineId.value != '' && objForm.iLineId.value != '0') {
		if (objForm.notes.value == '') {
			alert("Please provide full details in comments");
			objForm.notes.focus();
		} else {
			objForm.flag.value = "update";
			objForm.readData.value = "yes";
			objForm.submit();
		}
	} else {
		if (validateForm(objForm)) {
			if (objForm.reasonCdDesc.value == '') {
				alert("Please choose a reason code");
			} else if (objForm.notes.value == '') {
				alert("Please provide full details in comments");
				objForm.notes.focus();
			} else {
				objForm.submit();
			}
		}
	}
}

function getReasonCode(code, reason) {
	document.cfsBillingIssueForm.reasonCdDesc.value = reason;
	document.cfsBillingIssueForm.hReasonCd.value = code;
}

function makeStaticHeader(gridId, height, width, headerId, mainContentId, rootId, headerWidthReduce) { 
    var tbl = document.getElementById(gridId);
    if (tbl) {
        var DivHR = document.getElementById(headerId); 
        var DivMC = document.getElementById(mainContentId); 
        var divRoot = document.getElementById(rootId);
        if(DivHR != null && DivMC != null && divRoot!= null)
        {
	        var hdrHeight = document.getElementById((gridId + "Hdr")).offsetHeight;
	        console.log(hdrHeight);
	        
	        divRoot.style.top = '0px';
	        divRoot.style.height = (height  + 10)  + 'px';
	
	        //*** Set divheaderRow Properties ****
	        DivHR.style.height = hdrHeight  + 'px';
	        DivHR.style.width = (parseInt(width) - headerWidthReduce) + '%';
	        DivHR.style.position = 'relative';
	        DivHR.style.top = '0px';
	        DivHR.style.zIndex = '10';
	        //DivHR.style.verticalAlign = 'top';
	
	        //*** Set divMainContent Properties ****
	        DivMC.style.width = width + '%';
	        DivMC.style.position = 'relative';
	        DivMC.style.top = -hdrHeight  + 'px';
	        DivMC.style.bottom = (height - hdrHeight)  + 'px';
	        DivMC.style.height = height + 'px';
	        DivMC.style.zIndex = '1';
	        
	        DivHR.appendChild(tbl.cloneNode(true));
	         
	        $("#" + headerId + " input[type='text'], #" + headerId + " input[type='hidden'], #" + headerId + " select" ).each(function (){
	        	$(this).removeAttr("name").removeAttr("id").removeAttr("class");
	        });	
        }
 	}
}

function OnScrollDiv(Scrollablediv, headerId) {
	document.getElementById(headerId).scrollLeft = Scrollablediv.scrollLeft;
}

function validateParams(reqSelector) {
	var error = false;
	var p = [];
	$(reqSelector).each(function() {
		p[p.length] = $(this).attr("id");
	});

	var e = [];
	$("#eMsg").html("");
	$("#errorWidget").hide();

	for (k = 0; k < p.length; k++) {
		$("#" + p[k]).removeClass("error");
		var pVal = $("#" + p[k]).val();
		if ($.trim(pVal).length < 1) {
			error = true;
			e[e.length] = p[k];
		} else {
			$("#" + e[k]).removeClass("error");
		}
	}

	if (error) {
		for (k = 0; k < e.length; k++) {
			$("#" + e[k]).addClass("error");
			$("#eMsg").html("Please enter highlighted parameter.");
			$("#errorWidget").show();
		}
	}
	return error;
}

function showPleaseWait() {
	$.blockUI({
		css : {
			border : 'none',
			padding : '15px',
			backgroundColor : '#000',
			'-webkit-border-radius' : '10px',
			'-moz-border-radius' : '10px',
			opacity : .5,
			color : '#fff',
			cursor : 'default'
		},
		overlayCSS : {
			cursor : 'default'
		},
		baseZ : 9000
	});
}

function hidePleaseWait() {
	$.unblockUI();
}

function closeDlg() {
	var dlgId = "#dlg";
	$(dlgId).html("");
	$(dlgId).dialog("close");
	$(dlgId).dialog("destroy");

}

function getReasonLov(pageName, title) {
	showPleaseWait();
	var modelName = "#dlg";

	$(modelName).dialog({
		height : 500,
		title : title,
		modal : true,
		zIndex : 1005,
		width : 650,
		resizable : false
	});

	var qryStr = "";
	$.ajax({
		url : pageName,
		data : qryStr,
		type : "POST",
		cache : false,
		success : function(data) {
			hidePleaseWait();
			$(modelName).html("");
			$(modelName).html(data);
			hidePleaseWait();
		}
	});

	$(modelName).dialog("open");
	$(".ui-dialog-titlebar").addClass("hd");
   	     $(".ui-dialog").css({"top":"200px","position":"fixed"}); 
}

function getLovWithValue(pageName, paramName, value, title) {
	showPleaseWait();
	var modelName = "#dlg";
	$(modelName).dialog({
		height : 500,
		title : title,
		modal : true,
		zIndex : 1005,
		width : 650,
		resizable : false,
		close : function() {
			// functionality goes here
			$(this).dialog("close");
		}
	});

	var paramVal = value;
	var qryStr = paramName + "=" + encodeURIComponent(paramVal);
	$.ajax({
		url : pageName,
		data : qryStr,
		type : "POST",
		cache : false,
		success : function(data) {
			hidePleaseWait();
			$(modelName).html("");
			$(modelName).html(data);
			hidePleaseWait();
		}
	});

	$(modelName).dialog("open");
	$(".ui-dialog-titlebar").addClass("hd");
	     $(".ui-dialog").css({"top":"200px","position":"fixed"}); 
	var detectBrowser = msieversion();
	     if("otherbrowser"!=detectBrowser)
	 		{
		$('.ui-widget-overlay').remove();
	}

}

function msieversion() {

	var ua = window.navigator.userAgent;
	var msie = ua.indexOf("MSIE ");

	if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) // If Internet Explorer, return version number
		return parseInt(ua.substring(msie + 5, ua.indexOf(".", msie)));
        else                 // If another browser, return 0
		return 'otherbrowser';

	return false;
}

    function displayErrorMsg(msg)
	{
	$("#eMsg").html(msg);
	$("#errorWidget").show();
}

    function resetErroMsg()
    {
	$("#eMsg").html("");
	$("#errorWidget").hide();
}

function action_func1ReasonCode() {
	resetErroMsg();
	var objForm = document.ReasonCodeForm;
	var isSelected = false;
	var val = '';
	var v = 0;
	if (objForm.thdetails != null) {
		v = objForm.thdetails.length;
		if (v > 0) {
			for (i = 0; i < v; i++) {
				if (objForm.thdetails[i].checked) {
					isSelected = true;
					val = objForm.thdetails[i].value;
					break;
				}
			}
		} else if (objForm.thdetails.checked) {
			val = objForm.thdetails.value;
			isSelected = true;
		}
	}
	if (!isSelected) {
		displayErrorMsg("Please select the reasonCode");
	} else {
		getReasonCode(objForm.reasonCode.value, val);
		closeDlg();
	}
}

function rsnCodeRefresh() {
	//document.ReasonCodeForm.submit();

    	var qryStr="reasonCode="+encodeURIComponent( $('select[name="reasonCode"]').val() );
	var modelName = "#dlg";

	$.ajax({
		url : "canon_E193_csTicketReasonCodeP.jsp",
		data : qryStr,
		type : "POST",
		cache : false,
		cached : false,
		success : function(data) {
			hidePleaseWait();
			$(modelName).html("");
			$(modelName).html(data);
			hidePleaseWait();
		}
	});

}

    function NoPrompt()
    {    	
	allowPrompt = false;
}

    function checkAllData(formName)
    {
	resetErroMsg();
	//alert(formName);
	var l_cnt = document.forms[formName].elements["Cnt"].value;
	var l_successFlag = true;

      for (i=0; i<l_cnt; i++)
      {
		var l_prompt = null;
		var l_reqFlag = null;
		var l_attributeId = null;
		var l_attrValue = null;
		var l_attrValue1 = null;
		var l_defaultValue = null;
		var l_enabledFlag = null;
		var l_displayFlag = null;

		l_attributeId = document.forms[formName].elements["attributes" + i].value;
		l_prompt = document.forms[formName].elements["prompt" + i].value;
		l_reqFlag = document.forms[formName].elements["reqFlag" + i].value;
		l_defaultValue = document.forms[formName].elements["defaultValue" + i].value;
		l_enabledFlag = document.forms[formName].elements["enabledFlag" + i].value;
		l_displayFlag = document.forms[formName].elements["displayFlag" + i].value;

        if (l_displayFlag == 'Y')
        {
        if (l_defaultValue == 'RADIOBUTTON')
        {
				l_attrValue = document.forms[formName].elements["attrvalue" + i].value;
          if (l_reqFlag == "Y" && (l_attrValue == null || l_attrValue == ""))
          {
					displayErrorMsg("Please Select The Value For " + l_prompt);
					l_successFlag = false;
					Prompt();
					return false;
				}
        } else if (l_defaultValue == 'LOV')
        {
				l_attrValue1 = document.forms[formName].elements[l_attributeId].value;
          if (l_attrValue1 == "-1")
          {
					l_attrValue = "";
					document.forms[formName].elements["attrvalue" + i].value = l_attrValue;
          } else
          {
					l_attrValue = l_attrValue1;
					document.forms[formName].elements["attrvalue" + i].value = l_attrValue;
				}

          if (l_reqFlag == "Y" && (l_attrValue == null || l_attrValue == ""))
          {
					displayErrorMsg("Please Select The Value For " + l_prompt);
					document.forms[formName].elements[l_attributeId].focus();
					l_successFlag = false;
					Prompt();
					return false;
				}
        } 
 		else if (l_defaultValue == 'MINUTES')
        {

 		  var hours = parseInt(document.forms[formName].elements["hours"+i].value);
 		  var minutes = parseInt(document.forms[formName].elements["minutes"+i].value);

				if ((hours >= 0 && minutes < 0) || (hours < 0 && minutes >= 0)) {
					displayErrorMsg("Please select values for both hours and minutes");
					document.forms[formName].elements["hours" + i].focus();
					l_successFlag = false;
					Prompt();
					return false;
 		  }           
 		  else if (hours == "-1" || minutes == "-1" || hours < 0 || minutes < 0)
          {
					l_attrValue = "";
					document.forms[formName].elements["attrvalue" + i].value = l_attrValue;
          }
 		  else
          {
					l_attrValue = (hours * 60) + minutes;
					document.forms[formName].elements["attrvalue" + i].value = l_attrValue;
					document.forms[formName].elements[l_attributeId].value = l_attrValue;
				}

          if (l_reqFlag == "Y" && (l_attrValue == null || l_attrValue == ""))
          {
					displayErrorMsg("Please Select The Value For " + l_prompt);
					document.forms[formName].elements["hours" + i].focus();
					l_successFlag = false;
					Prompt();
					return false;
				}
        }
 		else if (l_defaultValue == 'LINK')
        {
				l_attrValue = document.forms[formName].elements["attrvalue" + i].value;
				document.forms[formName].elements["attrvalue" + i].value = l_attrValue;
          if (l_reqFlag == "Y" && (l_attrValue == null || l_attrValue == ""))
          {
					displayErrorMsg("Please Define The Target For " + l_prompt);
					l_successFlag = false;
					Prompt();
					return false;
				}
        } else
        {
				l_attrValue = document.forms[formName].elements[l_attributeId].value;
				document.forms[formName].elements["attrvalue" + i].value = l_attrValue;
          if (l_reqFlag == "Y" && (l_attrValue == null || l_attrValue == ""))
          {
					displayErrorMsg("Please Enter " + l_prompt);
					document.forms[formName].elements[l_attributeId].focus();
					l_successFlag = false;
					Prompt();
					return false;
				}
			}
		}
	}
	//alert("successFlag = "+l_successFlag);
    if (l_successFlag)
    {

		showPleaseWait();
		var modelName = "#dlg2";
		//var val = document.origContactForm.parentCatCode.value;
		/*	   $(modelName).dialog({
						height: 500,
						title: "Additional Attributes", 
						modal: true ,
						zIndex:1005,
						width: 650,		
		                resizable: false      
					});   */

		document.forms[formName].elements["Mode"].value = "insert";
		var qryStr = $("#" + formName).serialize();
		$.ajax({
					url : formName + ".jsp",
					data : qryStr,
					type : "POST",
					cache : false,
					success : function(data) {
						var res = "";
						res = data;
				if ( $.trim(res).length>0 )
				{
							hidePleaseWait();
							$(modelName).html("");
							$(modelName).html(data);
							hidePleaseWait();
					}
				else
					$("#msg").html("Error occured while creating ticket.  Please try again.");					  
		 	}             
				});

		/*    $( modelName ).dialog("open");
			   $(".ui-dialog-titlebar").addClass("hd");
			   $(".ui-dialog").css({"top":"330px"}); 
		   $('.ui-widget-overlay').remove(); */
	}

}

/* function uploadAttachFile(fileName){
 	$('#fileNm').val(fileName);   
    	var tktId = $('#ticketnumber').val();
    	var invNum = $('#invNum').val();
    	
    	var selAcctNum=$("#selAcctNum").val();       	
    	if(selAcctNum.length>1)
    		invNum=selAcctNum;       	
    	if(fileName!=''){
    		var url = "canonE193AttachmentUpload.jsp";
    		var qryStr="ticketnumber="+encodeURIComponent(tktId)+"&fileName="+fileName+"&invNum="+invNum; 
    		alert(qryStr);
    		$.ajax({
    			url: url,
    			data: qryStr,
    			type:"POST",
    			cache: false,
    			success : function(result)
    			{
    				if($.trim(result)=='0'){
    					$("#eMsg").css({"color": "red", "font-size": "12"});
    					$('#eMsg').html('PO is not Uploaded successfully');
    					$('#errorWidget').show();
    				}else{
    					$('#eMsg').html("PO is uploaded Successsfully..");
    					$("#eMsg").css({"color": "green", "font-size": "12"}); 
    					$('#errorWidget').show();
    				}
    			}
    		});	
    	} 
 }*/

$(function() {
	$(".supplies-table tr:even").addClass("even");
	$(".supplies-table tr:odd").addClass("odd");
	$(".products-table tr:even").addClass("even");
	$(".products-table tr:odd").addClass("odd");
});
