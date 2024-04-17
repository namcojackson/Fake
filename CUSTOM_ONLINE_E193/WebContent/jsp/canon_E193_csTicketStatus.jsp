<!-- $Header: ITG# 74988 canon_E193_csTicketStatus.jsp 2006/01/12 Vijay Janardhanan $ -->
<%--=========================================================================
 |      Copyright (c)2000 Oracle Corporation, Redwood Shores, CA
 |                         All rights reserved.
 +===========================================================================
 |
 | FILE 
 |   canon_E193_csTicketStatus.jsp - Ticket Status and Resolution Page.
 |
 | DESCRIPTION
 |  canon_E193_csTicketStatus
 |   
 |
 | HISTORY
 | DATE        WHO                  WHY
 | 	       Vijay Janardhanan    2.9   Restricted the notes MAX size to 32000
 | 30-Nov-06   Vikas Basal          ITG# 74988 CFS Changes 
 | 21-Apr-09   Chandra Sekhar       ITG # 176718 Changes
 | 22-Apr-09   Chandra Sekhar       ITG # 208045 - Canon Ball Changes
 | 02-Oct-09   Naveen Khandelwal    MW Project Changes.
 +=======================================================================--%>
<%@page language="java"%>
<%@page import="java.util.*"%>
<%@page import="java.lang.*"%>
<%@page import="com.canon.oracle.custapp.custinq.dao.*"%>
<%@page import="com.canon.oracle.custapp.custinq.beans.*"%>
<%@page import="com.canon.oracle.custapp.custinq.dao.Canon_E193_AttachmentUploadDAO"%>
<%@page import="java.util.StringTokenizer"%>

<jsp:useBean
	class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Severity"
	id="objSeverityDao" scope="page" />
	
<%@ include file="canon_E193_csTopInc.jsp"%>

<jsp:useBean
	class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj"
	id="objHeaderLinesDtls" scope="request" />

<!-- ITG # 208045 Changes -->
<jsp:useBean
	class="com.canon.oracle.custapp.custinq.dao.Canon_E193_NonBillingIssue"
	id="objNonBillInfo" scope="page" />
<jsp:setProperty name="objNonBillInfo" property="*" />
<!-- ITG # 208045 Changes -->

<%! 
 static String checkNull(String str){
	 if (str != null && !str.trim().equals("null")) {
	    return str;
	  }else {
		return "";
     }
 }
 
%>
<% 
String ctxPath1 = request.getContextPath();

   // Menu Prompt
   strPageName = "History";
System.out.println(" !!!!!! in canon_E193_csTicketStatus.jsp " + " ContextPath:~ "+ ctxPath1);
Canon_E193_AttachmentUploadDAO objFileUploadDAO	= new Canon_E193_AttachmentUploadDAO();
   // Check page from to show the path
   strPageFrom = request.getParameter("hPageFrom");
   //Start changes for S21 by Mangala
   ArrayList alResolutionList = objSeverityDao.getResolutionCode();  
   //End changes for S21 by Mangala	   
   ArrayList alTktObj = (ArrayList)objHeaderLinesDtls.getArrayList();
   //System.out.println("!!!!!!!!!!!!alTktObj =  " +alTktObj);      
   ArrayList alHeaderLineDtls = (ArrayList)alTktObj.get(0);
   //System.out.println("!!!!!!!!!!!!alHeaderLineDtls =  " +alHeaderLineDtls); 
   ArrayList alHeaderLineStatus = (ArrayList)alTktObj.get(1);
   //System.out.println("!!!!!!!!!!!!alHeaderLineStatus =  " +alHeaderLineStatus); 
   ArrayList alLineSeverity = (ArrayList)alTktObj.get(2);
   //System.out.println("!!!!!!!!!!!!alLineSeverity =  " +alLineSeverity); 
   String strCatIdP = "";
   String strLineIdConCat = "";
   //Get Header Object
   Canon_E193_TicketHeaderObj objHeader = (Canon_E193_TicketHeaderObj) alHeaderLineDtls.get(0);
   String hRoleVal = request.getParameter("hRoleVal")==null?"":request.getParameter("hRoleVal");
   
   String strTicketNumber = objHeader.getTicketNo();
  System.out.println("!!!!!!!!!!!!objHeader TicketNumber =  " + strTicketNumber ); 
  ArrayList<CanonE193AttFileRec> s21AttachedFileList=objFileUploadDAO.getFilesFromS21DB(Integer.parseInt(strTicketNumber));
  
  ArrayList<CanonE193AttFileRec> csaAttachedFileList =objFileUploadDAO.getCSAFilesFromS21DB(Integer.parseInt(strTicketNumber));
  
	String hPath = request.getParameter("hPath");
	if (hPath == null)
		hPath = strTicketStatusT1;
	else
		if(hPath.indexOf(strFreightIssueT1) < 0)
			hPath = hPath + " -> " + strTicketStatusT1;	
%>
<%@ include file="canon_E193_csMenuInc.jsp"%>

<script language="JavaScript">
	$(document).ready(function() {
		$(".date-picker").datepicker({
			dateFormat : 'mm/dd/yy',
			changeMonth : true,
			changeYear : true
		});
		
		/* $("#updateheaderstatus").change(function(){
			resetErroMsg();
			if($("#updateheaderstatus").val()==="CLOSE"){
				$(".enable_email").show();
			}else{
				$(".enable_email").hide();
			}
			
		}); */
		
		$("#setup_email_button").click(function(){
			sendEmail();
		});
		
		$("#dlg").on("click","#setup_email_ok",function(){
			
			if(checkEmails())
			{
				var oldEmailSkipNotification =  $("#email_skip_notification").val();
				var oldEmailToAddress = $("#email_to_address").val();
				var oldEmailSubject = $("#email_subject").val();
				var oldEmailComment = $("#email_comment").val();
				
				$("#email_skip_notification").val($("#skipEmailNotification").is(':checked')? "Y":"N");
				$("#email_to_address").val($("#emailIdOnClose").val());
				$("#email_subject").val($("#subjectOnClose").val());
				$("#email_comment").val($("#commentsOnClose").val());
				
				var newEmailSkipNotification =  $("#email_skip_notification").val();
				var newEmailToAddress = $("#email_to_address").val();
				var newEmailSubject = $("#email_subject").val();
				var newEmailComment = $("#email_comment").val();
				
				var ticketId = $('#ticket_number').val();
				var userId = '<%=objCiSession.getUserId()%>' ;
				
				$("#email_body").val($("#email_body_top").text()+ $("#commentsOnClose").val()+$("#email_body_bottom").text());
				
				
				var qryStr = "user_id="+ userId +"&ticket_id="+ ticketId
						+"&email_skip_notification="+newEmailSkipNotification
						+ "&email_to_address=" +newEmailToAddress 
						+"&email_subject=" + newEmailSubject 
						+ "&email_comment="+ newEmailComment;
				
				$.ajax({
					url: "canon_E193_csSendEmailOnClose_util.jsp",
					data: qryStr,
					type:"POST",
					dataType : "text",
			        cache: false,
					success: function(data){
							console.log(data);
							if(data == "N")
							{
								alert("Error occurred while saving data. Please contact adminstrator");
							}else{
								var dlgId="#dlg";
						    	$(dlgId).html("");
						    	$(dlgId).dialog("close");
						    	$(dlgId).dialog("destroy");
							}
						    hidePleaseWait();
				 	   }
				});
			
			}
			
		});

		$("#dlg").on("change","#skipEmailNotification",function(){
			var skipEmail= $("#skipEmailNotification").is(':checked');
			
			var existingStatus = $("#existing_status").val();
			 
			var statusDisable =  (existingStatus != 'undefined' && existingStatus != "" && existingStatus == 'CLOSE') ? true : false;
			    
			var disable = false;
			    
			if(skipEmail  || statusDisable)
		    {
			    disable = true;
		    }
			
			$("#emailIdOnClose").prop('disabled', disable);
			$("#subjectOnClose").prop('disabled', disable);
			$("#commentsOnClose").prop('disabled', disable);
			$("#skipEmailNotification").prop('disabled', statusDisable);
		});
		
		/* $("#updateheaderstatus").trigger("change"); */
		onloadHeaderChange();
		
	});
	
	function checkEmail(textfield) {
		// var regExp = /(^[a-z]([a-z_\.]*)@([a-z_\.]*)([.][a-z]{3})$)|(^[a-z]([a-z_\.]*)@([a-z_\.]*)(\.[a-z]{3})(\.[a-z]{2})*$)/i;
	  	var regExp = /(^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)/i;
	   	return regExp.test(textfield);
	}

	function checkEmails(){
	   	var emailOK = true;
	   	
		var emails = document.getElementById("emailIdOnClose");
		
		var emailsValue = document.getElementById("emailIdOnClose").value;
	  	var emailArray = emailsValue.split(",");
	  	
	  	if(emailsValue == '') {
			
	        return true;
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
	   	}else
   		{
   			return true;
   		}
	   	
	}
	
	function onloadHeaderChange(){
		resetErroMsg();
		if($("#updateheaderstatus").val()==="CLOSE"){
			$(".enable_email").show();
		}else{
			$(".enable_email").hide();
		}
	}
	
	function headerChange(orgHeaderStatus){
		onloadHeaderChange();
		var notesEnable = false;
		
		if(orgHeaderStatus != $("#updateheaderstatus").val() || $('input[name=csdetails]:checked').length > 0)
		{
			notesEnable = true;
		}
		
		if(notesEnable)
		{
			$('#additionalnotes').attr("disabled", false);
			$('#additionalnotes').css("background-color","#ffff00");
		}else
		{
			$('#additionalnotes').val('');
			$('#additionalnotes').attr("disabled", true);
			$('#additionalnotes').css("background-color","#FFFFFF");
		}
		
	}
	
   function fnAssign(assignDept,assignRole,assignRes,val1)
   {
	   resetErroMsg();
      var lineIdField = "lineId" + val1;
      
      if(document.tktStatusForm.hLineSel.value != val1) 
      {
    	  displayErrorMsg("Please select the line first.");
      }
      else
      {
         document.tktStatusForm.hLineId.value = document.getElementById(lineIdField).value;
         showPleaseWait();	
	     var modelName ="#dlg";
	     	
	     //alert(assignDept + ' ' + assignRole + ' ' + assignRes + ' ' + val1);
	    	   $(modelName).dialog({
	   					height: 500,
	   					title: "Ticket Assignments", 
	   					modal: true ,
	   					zIndex:1005,
	   					width: 650,		
	   	                resizable: false      
	   				});
	    	   
	    	   var qryStr="hPageFrom=TicketSummary&hDeptSel=yes&&hRoleSel=yes&assignDept="+assignDept+"&assignRole="+assignRole+"&hResourceId="+assignRes+"&selectedRoleVal="+assignRole;
		       $.ajax({
				url: "canon_E193_csTicketAssignmentP.jsp",
				data:qryStr,
				type:"POST",
		        cache: false,
				success: function(data){     
						  hidePleaseWait();     
						  $(modelName).html("");					
					      $(modelName).html(data);  				       
					       hidePleaseWait();
			 	        }             
			 });
			  
		     $( modelName ).dialog("open");
		     $(".ui-dialog-titlebar").addClass("hd");
		     $(".ui-dialog").css({"top":"200px","position":"fixed"}); 
		     
		     var detectBrowser=msieversion();
      	     if("otherbrowser"!=detectBrowser)
      	 		{
      	 		$('.ui-widget-overlay').remove();
      	 		}
      }
   }
   
   function getAssignment(deptVal, roleVal, resourceVal, deptDesc, assignDesc, resourceDesc)
   {
      var vLineSel = document.tktStatusForm.hLineSel.value;
      
      var vDeptCode = "deptCode" + vLineSel;
      var vRoleId = "roleId" + vLineSel;
      var vResId = "resourceId" + vLineSel;
      var vDept = "deptName" + vLineSel;
      var vRole = "roleName" + vLineSel;
      var vRes = "resourceName" + vLineSel;
            
      
      var vDeptCodeDefaultVal=document.getElementById(vDeptCode).defaultValue;
      document.getElementById(vDeptCode).value = deptVal;
      document.getElementById(vRoleId).value = roleVal;
      document.getElementById(vResId).value = resourceVal;
      document.getElementById(vDept).value = deptDesc;
      document.getElementById(vRole).value = assignDesc;
      document.getElementById(vRes).value = resourceDesc;           
      /*if((deptVal != vDeptCodeDefaultVal) ||  (roleVal != document.getElementById(vRoleId).defaultValue) || (resourceVal !=  document.getElementById(vResId).defaultValue))
      { */
         document.tktStatusForm.hLineDept.value = deptVal;
         document.tktStatusForm.hLineRole.value = roleVal;
         document.tktStatusForm.hLineResource.value = resourceVal;
     /*  }  */      
  	closeDlg();
   }
   
   function select_Line(selLineField, val1, orgHeaderStatus)
   {
      if (selLineField.checked)
      {
         document.tktStatusForm.hLineSel.value = selLineField.value;
         for(i=0; i < <%=alHeaderLineDtls.size()%>; i++) {
        	 if(document.getElementById("lineNote"+i) != null && document.getElementById("lineNote"+i).style.visibility == 'visible') {
               document.getElementById("lineNote"+i).style.visibility = 'hidden';
               break;
             }
         }
         document.getElementById(val1).style.visibility = 'visible';
         $("#selectedLineIndex").val(val1);
      }
      headerChange(orgHeaderStatus);
   }
   
   function check_line_selected(value1, field, origVal)
   {
	   
	  resetErroMsg();
      var lineIdField = "lineId" + value1;
      var lineStatusFieldName = "updatestatus" + value1;
   
      if(document.tktStatusForm.hLineSel.value != value1) 
      {
    	 displayErrorMsg("Please select the line first.");
         for(var i=0;i<field.length;i++) 
         {
            if(field[i].defaultSelected)
            {
               field[i].selected = true;
               break;
            }
         }
      }
      else
      {
         if(field.value != origVal)
         {
            document.tktStatusForm.hLineId.value = document.getElementById(lineIdField).value;;
            if(field.name == lineStatusFieldName)
               document.tktStatusForm.hLineStatus.value = field.value;
            else
               document.tktStatusForm.hLineSeverity.value = field.value;
         }
      }
   }

   function notes_Change(txtField, origHeaderStatus, lineIdConCat)
   {
	  resetErroMsg();
	  var idx =  document.tktStatusForm.hLineSel.value;
      var lineIdField = "lineId" + idx;
   
      if((document.tktStatusForm.updateheaderstatus.value == "-1") || (document.tktStatusForm.updateheaderstatus.value == origHeaderStatus))
      {
         if((document.tktStatusForm.hLineSel.value == null) || (document.tktStatusForm.hLineSel.value == "") || (document.tktStatusForm.hLineSel.length == 0))
        {
        	 displayErrorMsg("Please select the line first.");
        }
        else
        {
           document.tktStatusForm.hLineId.value = document.getElementById(lineIdField).value;
           document.tktStatusForm.hLineNotes.value = txtField.value;
        }
      }
      else
      {

        document.tktStatusForm.hLineIdConCat.value = lineIdConCat + "E";
        document.tktStatusForm.hLineNotes.value = txtField.value;          
      }
   }
   
/* 
   function notes_Change(txtField, origHeaderStatus, lineIdConCat)
   {
      var idx =  document.tktStatusForm.hLineSel.value;
      var lineIdField = "lineId" + idx;
      if((document.tktStatusForm.hLineSel.value == null) || (document.tktStatusForm.hLineSel.value == "") ||
            (document.tktStatusForm.hLineSel.length == 0))
      {
         if((document.tktStatusForm.updateheaderstatus.value == "-1") || 
            (document.tktStatusForm.updateheaderstatus.value == origHeaderStatus))
            alert("Please select the line first.");
         else
         {
            document.tktStatusForm.hLineIdConCat.value = lineIdConCat + "E";
            document.tktStatusForm.hLineNotes.value = txtField.value;            
         }
      }
      else
      {
         document.tktStatusForm.hLineId.value = document.getElementById(lineIdField).value;
         document.tktStatusForm.hLineNotes.value = txtField.value;
      }
   }
*/ 


  
   

function checkEmailSetup()
{
    var status = $("#updateheaderstatus").val();

	if(status!=="CLOSE"){
		return true;
	}
	
	var skipEmail=$("#email_skip_notification").val()=="Y";
	if(skipEmail){
		return true;
	}
	
	return $("#email_to_address").val()!=="" && $("#email_subject").val()!=="";
}


function action_Save(origHeaderStatusVal, strLineIdConCat)
{
	$("#saveButton").attr("disabled", true);
	$("#saveButton").removeClass("btn_red");
	$("#saveButton").addClass("btn_disabled");
   var isHdrChanged = "true";
   var isLineChanged = "true";
   var disMsg ="true";
   
   var lineId = document.tktStatusForm.hLineSel.value;
   var lineStatusSel = document.tktStatusForm.hLineStatus.value;
      
   // v2.9 Start
   var vEnlen = "";
   
   if(lineId != null && lineId != "")
   {
	   if ((parseInt(<%=alHeaderLineDtls.size()%>)-1) == 1)
	      vEnlen = document.tktStatusForm.enlen.value;
	   else
	      vEnlen = eval('document.tktStatusForm.enlen[' +(parseInt(lineId)-1)+'].value');
	}
   // v2.9 End

   document.tktStatusForm.ticketId.value = document.tktStatusForm.tktId.value;
   if(document.tktStatusForm.updateheaderstatus != null)
   {
      	if(document.tktStatusForm.updateheaderstatus.value == origHeaderStatusVal)
        	isHdrChanged = "false";
      	else
    	{
         	document.tktStatusForm.hHdrStatus.value = document.tktStatusForm.updateheaderstatus.value;
      		if (document.tktStatusForm.updateheaderstatus.value =="CLOSE" )
      		{
      			if(document.tktStatusForm.rescode.value != "")
     				document.tktStatusForm.hrescode.value = document.tktStatusForm.rescode.value;
	      		/*else {
	      			isHdrChanged = "false";
	     			disMsg ="false";
	      	 		alert("Please select the final resolution code");
	      		}*/
      		}
    	}
   }
   
   if(document.tktStatusForm.rescode.value != "")
     		document.tktStatusForm.hrescode.value = document.tktStatusForm.rescode.value;
   
            
   if((document.tktStatusForm.hLineSel.value == "") || (document.tktStatusForm.hLineSel.value == null) ||
      (document.tktStatusForm.hLineSel.length == 0)) 
      isLineChanged = "false";
   else
   {
      if(
         ((document.tktStatusForm.hLineStatus.length == 0) || (document.tktStatusForm.hLineStatus.value == "") || (document.tktStatusForm.hLineStatus.value == null))
          &&
         ((document.tktStatusForm.hLineSeverity.length == 0) || (document.tktStatusForm.hLineSeverity.value == "") || (document.tktStatusForm.hLineSeverity.value == null))
          &&
         ((document.tktStatusForm.hLineDept.length == 0) || (document.tktStatusForm.hLineDept.value == "") || (document.tktStatusForm.hLineDept.value == null))
          &&
         ((document.tktStatusForm.hLineRole.length == 0) || (document.tktStatusForm.hLineRole.value == "") || (document.tktStatusForm.hLineRole.value == null)) 
          &&
         ((document.tktStatusForm.hLineResource.length == 0) || (document.tktStatusForm.hLineResource.value == "") || (document.tktStatusForm.hLineResource.value == null))
          && 
         ((document.tktStatusForm.additionalnotes.length == 0) || (document.tktStatusForm.additionalnotes.value == "") || (document.tktStatusForm.additionalnotes.value == null))   
        ) 
         isLineChanged = "false";
      else
      {
         isLineChanged = "true";
         if(
            ((lineStatusSel.indexOf("UPDATE CUSTOMER") != -1) || (lineStatusSel.indexOf("RMA") != -1)) 
            &&
            (document.tktStatusForm.hLineDept.value == "" && document.tktStatusForm.hLineRole.value == "" &&
             document.tktStatusForm.hLineResource.value == "")
           )
         {
            document.tktStatusForm.hLineDept.value = document.tktStatusForm.hOwnerDeptCode.value;
            document.tktStatusForm.hLineRole.value = document.tktStatusForm.hOwnerRoleId.value;
            document.tktStatusForm.hLineResource.value = document.tktStatusForm.hOwnerResId.value;
         }
      }
   }      
   if((isHdrChanged == "true" || isLineChanged == "true" ) && disMsg == "true") 
   {  
      if(document.tktStatusForm.additionalnotes.value == "")
      {
         alert("Please provide full details in comments");
         document.tktStatusForm.additionalnotes.focus();
      }
      else if (lineId != null && lineId != "" && parseInt(vEnlen) + document.tktStatusForm.additionalnotes.value.length > 32000)
      {
         alert("Total Size of Notes for an Issue Line is restricted to 32000 characters. Please use the attachment facility to add additional details.");
         document.tktStatusForm.additionalnotes.focus();
      }
      else
      {
    	  if(isHdrChanged == "true")
   		  {
    		  document.tktStatusForm.hLineNotes.value = document.tktStatusForm.additionalnotes.value;
    		  document.tktStatusForm.hLineIdConCat.value = strLineIdConCat + 'E';
   		  }else 
   		  {
    	      var lineIdField = "lineId" + lineId;
    		  document.tktStatusForm.hLineId.value = document.getElementById(lineIdField).value;
    	      document.tktStatusForm.hLineNotes.value = document.tktStatusForm.additionalnotes.value;
   		  }
    	  
         document.tktStatusForm.hSaveChanges.value = "YES";
         document.tktStatusForm.action = "canon_E193_csTicketStatusController.jsp";
         document.tktStatusForm.submit();
      }
   }
   else if (disMsg == "true")
   {
      alert("No changes to save");
      $("#saveButton").attr("disabled", false);
	  	$("#saveButton").removeClass("btn_disabled");
	  	$("#saveButton").addClass("btn_red");
   }
   
}
   
   function action_Add_Issues(value1)
   {
      var nextPage = "";

      if(document.tktStatusForm.hIsBilling.value == "Y")
         nextPage = "canon_E193_csTicketStatusAddIssuesController.jsp";
      else
         nextPage = "canon_E193_csNBIssueList.jsp";
   
      document.tktStatusForm.strCatIdP.value = value1;
      document.tktStatusForm.action = nextPage;
      document.tktStatusForm.submit();
   }
   
   function get_sublines(val1,val2,val3)
   {
      var tktId = document.tktStatusForm.tktId.value;
      var controlForm;
      var invNum = document.tktStatusForm.invNum.value;
      var invSource = document.tktStatusForm.invSource.value;
      var invSourceType = val2;
      var lineId = val1;
      var titleName;
      if(val3!="" || val3!=null) {
    	  titleName = val3;
           }else {
    	  titleName = "Billing Contract Line Details";
      }
     
      if(document.tktStatusForm.hIsBilling.value == "Y")
         controlForm = "canon_E193_csBIssueList.jsp";
      else
         controlForm = "canon_E193_csNBIssueList.jsp";
      showPleaseWait();	
	     var modelName ="#dlg";
	     	
	     	//var val = document.origContactForm.parentCatCode.value;
	    	   $(modelName).dialog({
	   					height: 700,
	   					title: titleName, 
	   					modal: true ,
	   					zIndex:1005,
	   					width: 960,		
	   	                resizable: false      
	   				});
	    	   
	    	   var qryStr="iLineId="+lineId+"&iTicketId="+tktId+"&controlForm="+controlForm+"&invoiceNumber="+invNum+"&InvSource="+invSource+"&strInvoiceType="+invSourceType;
    	       $.ajax({
   			url: "canon_E193_csTicketLineControllerP.jsp",
   			data:qryStr,
   			type:"POST",
   	        cache: false,
   			success: function(data){     
   					  $(modelName).html("");
   				      $(modelName).html(data);
	   				  makeStaticHeader('baseChargeTable', 300, 100, 'divBaseHeaderRow', 'divBaseMainContent', 'divBaseRoot', 2);
	   				  makeStaticHeader('priceChargeTable', 400, 100, 'divPriceHeaderRow', 'divPriceMainContent', 'divPriceRoot', 2);
	   				  makeStaticHeader('readChargeTable', 300, 100, 'divReadHeaderRow', 'divReadMainContent', 'divReadRoot', 2);
	   				  hidePleaseWait();
   		 	}             
   		 });
   		  
   	     $( modelName ).dialog("open");
   	     $(".ui-dialog-titlebar").addClass("hd");
   	     $(".ui-dialog").css({"top":"100px","position":"fixed"}); 
   	  
	 		$('.ui-widget-overlay').remove();
   }
   
   function action_Attach(tktId)
   {
	   
	   var offset = $("#attachments").offset();
	   //alert($( window ).width()/2);
	   var left = ($( window ).width()/2) - 300;
	   var top = offset.top - 200;
	   //alert(left + '' + top);
	   
	   var windowStr = "height=500,width=600,left="+left +",top="+top+",toolbar=no,menubar=no,scrollbars=yes,resizable=yes";
		var invNum = $('#invNum').val();
		var selAcctNum=$("#selAcctNum").val();       	
	   	if(selAcctNum.length>1)
	   		invNum=selAcctNum;  
	   	  var vWin = window.open( "canon_E193_csAttachmentsP.jsp?ticket_number="+tktId+"&invNum="+invNum, "newwin",
	   			windowStr);
	      vWin.focus();
	}

   /*function view_Attach(tktId)
   {
      var vWin = window.open( "canon_E193_csViewAttachmentsP.jsp?ticket_number="+tktId, "newwin","height=350,width=400,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
      vWin.focus();
   }
   
   function InvoiceLineDetails(pInvNo) {
      var vLink = "canon_E193_csActualInvoiceController.jsp?InvNo=" + pInvNo; 
      var vWin = window.open(vLink, "newwin", "height=300,width=900,toolbar=no,menubar=no,scrollbars=yes,resizable=yes"); 
      vWin.focus();
   }*/
   //Newly Added.
	function action_redirectToBilling(ticketId) {
		var nextPage = "";
       	var objForm = document.tktStatusForm;
       	nextPage = "canon_E193_csEICheckRequest.jsp";
		/* Mangala requested to comment to go directly to Billing Yes or No page
		if(document.tktStatusForm.hIsBilling.value == "Y")
		   nextPage = "canon_E193_csEICheckRequest.jsp";
		else
		   nextPage = "canon_E193_csNBIssueList.jsp";*/
		//objForm.hPath.value = "<%=strGreetingsT1%>";
		objForm.action = nextPage;
		objForm.ticketId.value = ticketId;
		objForm.copyTicket.value = 'Y';
		objForm.submit();
	}
   function action_summary() {
	  resetErroMsg();
      var objForm = document.tktStatusForm;
      var vTicketId = objForm.tktId.value;
      var vLineId = '', len = 0;
      var isChecked = false;
      if(objForm.csdetails != null) len = objForm.csdetails.length;
      if(len > 0) {
         for(i=0; i<len; i++) {
            if(objForm.csdetails[i].checked) {
               vLineId = objForm.summaryLineId[i].value;
               isChecked = true;
               break;
            }
         }
      }else if(objForm.csdetails != null && objForm.csdetails.checked) {
         vLineId = objForm.summaryLineId.value;
         isChecked = true;
      }
      if(!isChecked) {
    	  displayErrorMsg("Please select the line first.");
      }else if(vLineId == 'N') {
    	  displayErrorMsg("Credit/Rebill not avaiable for this type of issue");
      }else{
       	   showPleaseWait();
            var modelName ="#dlg";
       	   $(modelName).dialog({
      					height: 500,
      					title: "Credit/Rebill Summary", 
      					modal: true ,
      					zIndex:1005,
      					width: 950,		
      	                resizable: false      
      				});
       	var arrLine = vLineId.split("|");    	   
       	   var qryStr="iTicketId="+vTicketId+"&iLineId="+arrLine[0]+"&iLineNo="+arrLine[1];
       	       $.ajax({
      			url: "canon_E193_csCRSummaryP.jsp",
      			data:qryStr,
      			type:"POST",
      	        cache: false,
      			success: function(data){     
      					  hidePleaseWait();     
      					  $(modelName).html("");					
      				      $(modelName).html(data);  				       
      				       hidePleaseWait();
      		 	        }             
      		 });
      		  
      	     $( modelName ).dialog("open");
      	     $(".ui-dialog-titlebar").addClass("hd");
      	     $(".ui-dialog").css({"top":"200px","position":"fixed"}); 
      	     var detectBrowser=msieversion();
      	     if("otherbrowser"!=detectBrowser)
      	 		{
      	 		$('.ui-widget-overlay').remove();
      	 		}
      }
   }

// ITG # 208045 Changes
   function OpenDff(sourceApplication, sourceContext, contextValue, userId) {	
	   showPleaseWait();	
	     var modelName ="#dlg2";
	     	
	     	//var val = document.origContactForm.parentCatCode.value;
	    	   $(modelName).dialog({
	   					height: 500,
	   					title: "Additional Attributes", 
	   					modal: true ,
	   					zIndex:1005,
	   					width: 650,		
	   	                resizable: false      
	   				});
	    	   
	    	   var qryStr="sourceApplication="+encodeURIComponent(sourceApplication)+"&sourceContext="+sourceContext +"&contextValue="+contextValue +"&userId="+userId;
	    	       $.ajax({
	   			url: "canonE389DffUtility.jsp",
	   			data:qryStr,
	   			type:"POST",
	   	        cache: false,
	   			success: function(data){     
	   					  hidePleaseWait();     
	   					  $(modelName).html("");	   					  
	   				      $(modelName).html(data);  				       
	   				       hidePleaseWait();
	   		 	        }             
	   		 });
	   		  
	   	     $( modelName ).dialog("open");
	   	     $(".ui-dialog-titlebar").addClass("hd");
	   	     $(".ui-dialog").css({"top":"200px","position":"fixed"}); 
	   	  
		 		$('.ui-widget-overlay').remove();
   }
   
   function uploadAttachFile(fileName){
   	$('#fileNm').val(fileName);   
   	var tktId = $('#ticket_number').val();
   	var invNum = $('#invNum').val();
	var selAcctNum=$("#selAcctNum").val();       	
   	if(selAcctNum.length>1)
   		invNum=selAcctNum;     	
   	
   	if(fileName!=''){
   		var url = "canonE193AttachmentUpload.jsp";
   		var qryStr="ticket_number="+encodeURIComponent(tktId)+"&fileName="+fileName+"&invNum="+invNum;   		
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
   }
   
   function dept_select()
   {	   
   	var selectedRoleVal=$('#assignRole :selected').val();	
   	var hRoleVal=$('#hRoleVal').val();
   	var qryStr="assignDept="+encodeURIComponent( $('select[name="assignDept"]').val() )+"&hDeptSel=yes&hRoleSel=yes&hChgDeptSel=yes";
   	//qryStr+="&assignRole="+encodeURIComponent( $('#assignRole :selected').val() );	
   	qryStr+="&assignRole="+encodeURIComponent($('#hRoleVal').val() )+"&hRoleVal="+hRoleVal+"&selectedRoleVal="+selectedRoleVal;
   	$("#hDeptSel").val("yes");
   	$("#hRoleSel").val("yes");
   	$("#hChgDeptSel").val("yes");
   	
   	   var modelName ="#dlg";

   	       $.ajax({
   				url: "canon_E193_csTicketAssignmentP.jsp",
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

   function role_select()
   {		
   	var selectedRoleVal=$('#assignRole :selected').val();   	
   	var qryStr="assignRole="+encodeURIComponent( $('select[name="assignRole"]').val() );
   	qryStr+="&assignDept="+encodeURIComponent( $('select[name="assignDept"]').val() )+"&hDeptSel=yes&hRoleSel=yes&hChgDeptSel=yes"+"&selectedRoleVal="+selectedRoleVal;		
   	   var modelName ="#dlg";

   	       $.ajax({
   				url: "canon_E193_csTicketAssignmentP.jsp",
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
   	
   	//document.AssignmentForm.hRoleSel.value = "yes";
   	//document.AssignmentForm.action = "canon_E193_csTicketAssignmentP.jsp";
   	//document.AssignmentForm.submit();
   }

   function resource_select(selectField) {
   	document.AssignmentForm.hResourceId.value = selectField.value;
   }

   function select_assignee() 
   {
   	resetErroMsg();
   	var resourceVal = document.AssignmentForm.hResourceId.value;
   	
   	var objForm = document.AssignmentForm;
   	var isSelected = false;
   	var v = 0;
   	if(objForm.assignResource != null) {
   		v = objForm.assignResource.length;
   		if(v > 0) {
   			for(i=0; i<objForm.assignResource.length; i++) {
   				if(objForm.assignResource[i].checked) {
   					isSelected = true;
   					break;
   				}
   			}
   		}else if(objForm.assignResource.checked) {
   			isSelected = true;
   		}
   	}
   	
   	if (!isSelected) {
   		displayErrorMsg("Please select one of the above resources.");
   	}else{	
   		 
   		 	var f = document.AssignmentForm;
   		 	var deptVal = $('#assignDept :selected').val();//f.assignDept.options[f.assignDept.selectedIndex].value;
   		 	var roleVal = $('#assignRole :selected').val();//f.assignRole.options[f.assignRole.selectedIndex].value;
   		 	var deptDesc = $('#assignDept :selected').text();//f.assignDept.options[f.assignDept.selectedIndex].text;
   		 	var assignDesc = $('#assignRole :selected').text();//f.assignRole.options[f.assignRole.selectedIndex].text;
   		 	var resourceDesc = '';
   		 	if(document.getElementById(resourceVal) != null)
   		 		resourceDesc = document.getElementById(resourceVal).innerHTML;
   		 	
   		 getAssignment(deptVal, roleVal, resourceVal, deptDesc, assignDesc, resourceDesc);
   			
   		  
   	}
   }

   function getUpdatedAssignment(deptVal, roleVal, resourceVal, dDesc, rDesc, resDesc) {		  
   	var selectedLineIndex=$("#selectedLineIndex").val();   	 
   	$("#deptName"+selectedLineIndex).val(dDesc);   	
   	$("#roleName"+selectedLineIndex).val(rDesc);   	
   	$("#resourceName"+selectedLineIndex).val(resDesc);
   	
   	closeDlg();
   }
  
function getToday(){
	//var monthNames = ["January", "February", "March", "April", "May", "June",
	//  "July", "August", "September", "October", "November", "December"
	//];
	var month_names_short = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];
    var d = new Date();
    var curr_date = d.getDate();
    var curr_month = d.getMonth(); //Months are zero based
    var curr_year = d.getFullYear();
    //console.log(curr_date + "-" + monthNames[curr_month] + "-" + curr_year);
    return curr_date + "-" + month_names_short[curr_month] + "-" + curr_year;
}  

// ITG # 208045 Changes
//-->

// Begin To open a popup for email on close status
function sendEmail() {
	
  var status = $("#updateheaderstatus").val();
  
  var existingStatus = $("#existing_status").val();
  
  var qryStr = "ticketId="+$('#ticket_number').val() + "&existingStatus=" + existingStatus;
  
  if (status != 'undefined' && status !== "" && status == 'CLOSE') {
	  //alert(status);
	  var modelName ="#dlg";
	  $(modelName).dialog({
				height: 650,
				title: "Setup Email Notification", 
				modal: true ,
				zIndex:1005,
				width: 650,		
             resizable: false      
			});
	       $.ajax({
				url: "canon_E193_csSendEmailOnClose.jsp",
				data:qryStr,
				type:"POST",
			    cache: false,
			    cached: false,
				success: function(data){     
						  hidePleaseWait();     
	   					  $(modelName).html("");					
	   				      $(modelName).html(data);
	   				      
	   				      
	   				      var emailBody=$("#email_body_top").html();
	   				      emailBody=emailBody.replace(/\[CUSTOMER CONTACT NAME\]/g,"<%=checkNull(objHeader.getCustContact())%>" )
	   				      	.replace(/\[TICKET NUMBER\]/g,"<%=checkNull(objHeader.getTicketNo())%>")
	   				      	.replace(/\[DATE CLOSED\]/g, getToday());
	   				      	 				       
	   				      $("#email_body_top").html(emailBody).show();
	   				      
	   				    var skipEmail= $("#skipEmailNotification").is(':checked');
	   				    
	   				    var statusDisable =  (existingStatus != 'undefined' && existingStatus != "" && existingStatus == 'CLOSE') ? true : false;
	   				    
	   				    var disable = false;
	   				    
	   				    if(skipEmail  || statusDisable)
   				    	{
	   				    	disable = true;
   				    	}
	   				      
						  $("#emailIdOnClose").prop('disabled', disable);
						  $("#subjectOnClose").prop('disabled', disable);
						  $("#commentsOnClose").prop('disabled', disable);
						  $("#skipEmailNotification").prop('disabled', statusDisable); 				       
	   				      
	   				      $("#commentsOnClose").placeholder();
	   				      hidePleaseWait();
	 	        }          
	       });
	       $(".ui-dialog-titlebar").addClass("hd");
	   	   $(".ui-dialog").css({"top":"200px","position":"fixed"}); 
		   $('.ui-widget-overlay').remove();
		   $(modelName).dialog("widget").find(".ui-dialog-titlebar-close").remove();
	       $(modelName).dialog("open");
  }
}
// End To open a popup for email on close status
</script>
<div id="main_content">
	<div id="page_top">
	   <h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strTicketStatusT1%></h1>
	   <div class="breadcrumb"><%=strTicketStatusT1%></div>
	</div>	
<div class="inner-table">
	<form name="tktStatusForm" id="tktStatusForm" method="post">
			<input type="hidden" name="hPageFrom" value="EISelectAddress">
			<input type="hidden" name="hLineSel" value=""> 
			<input type="hidden" name="ticketId" value=""> 
			<input type="hidden" name="hHdrStatus" value="">
			<input type="hidden" name="hrescode" value=""> 
			<input type="hidden" name="hLineId" value=""> 
			<input type="hidden" name="hLineStatus" value=""> 
				<input type="hidden" name="hLineSeverity" value=""> 
				<input type="hidden" name="hLineDept" id="hLineDept" value=""> 
				<input type="hidden" name="hLineRole" value=""> 
				<input type="hidden" name="hLineResource" value=""> 
				<input type="hidden" name="hLineNotes" value=""> 
				<input type="hidden" name="hSaveChanges" value=""> 
				<input type="hidden" name="hLineIdConCat" value=""> 
				<input type="hidden" name="strCatIdP" value=""> 
				<input type="hidden" name="hCatId" value="<%=objHeader.getCatId()%>"> 
				<input type="hidden" name="hParentCatId" value=""> 
				<input type="hidden" name="hCatDesc" value="<%=objHeader.getCatIdDesc()%>"> 
				<input type="hidden" name="iTicketId" value="<%=objHeader.getTicketId()%>"> 
				<input type="hidden" name="origName" value="<%=objHeader.getOrigName()%>"> 
				<input type="hidden" name="origPhNo" value="<%=objHeader.getOrigPhNo()%>"> 
				<input type="hidden" name="origEmailAddress" value="<%=objHeader.getOrigEmail()%>">
			    <input type="hidden" name="origCheckbox" value="<%=objHeader.getsendEmailFlag()%>"> 
			    <input type="hidden" name="origType" value="<%=objHeader.getOrigType()%>"> 
			    <input type="hidden" name="sourceType" value="<%=objHeader.getAttribute9()%>">
			    <input type="hidden" name="custName" value="<%=objHeader.getCustContact()%>"> 
			    <input type="hidden" name="custPhNo" value="<%=objHeader.getCustPhNo()%>"> 
			    <input type="hidden" name="custEmailAddress" value="<%=objHeader.getCustEmail()%>"> 
			    <input type="hidden" name="recurProb" value="<%=objHeader.getRecurring()%>"> 
			    <input type="hidden" name="probType" value=""> 
			    <input type="hidden" name="val1" value=""> 
			    <input type="hidden" name="hPath" value="<%=hPath%>">
			    <input type="hidden" name="selAcctId" value="<%=objHeader.getCustAcctId()%>"> 
			    <input type="hidden" name="selLocId" value=""> <input type="hidden" name="selAcctName" value="<%=objHeader.getCustomerName()%>"> 
				<input type="hidden" name="selAcctNum" id="selAcctNum" value="<%=objHeader.getCustomerNo()%>"> 
				<input type="hidden" name="tktId" value="<%=objHeader.getTicketId()%>"> 
				<input type="hidden" name="hIsBilling"	value="<%=objHeader.getBillingIssue()%>"> 
				<input type="hidden" name="invNum" id="invNum" value="<%=objHeader.getInvoiceNo()%>"> 
				<input type="hidden" name="invSource" id="invSource" value="<%=objHeader.getCategory()%>">
				<input type="hidden" name="ticket_number" id="ticket_number" value="<%=objHeader.getTicketNo()%>">
				<input	type="hidden" name="selectedLineIndex" id="selectedLineIndex">	
				<input type="hidden" name="hRoleVal" id="hRoleVal" value="<%=hRoleVal%>">				
				<input type="hidden" name="email_to_address" id="email_to_address" value="">				
				<input type="hidden" name="email_subject" id="email_subject" value="">				
				<input type="hidden" name="email_body" id="email_body" value="">				
				<input type="hidden" name="email_skip_notification" id="email_skip_notification" value="">				
				<input type="hidden" name="email_comment" id="email_comment" value="">
				<input type="hidden" name="existing_status" id="existing_status" value="<%=objHeader.getStatus()%>">
				<input type="hidden" name="copyTicket" value="">
				
			<table class="request-service" style="align:center;" cellspacing="0">
		<!-- ITG # 176718 Change -->
		<% objCiSession.setRegionCode(objHeader.getAttribute6()); %>
		<!-- ITG # 176718 Change -->
		<tr>
			    <td>
			       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
				   </div>
			   </td>
			</tr>
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td><font class="promptReadOnly"><b>Ticket
						Number: &nbsp;<u><%=objHeader.getTicketNo()==null?"":objHeader.getTicketNo()%></u></b></font>
			</td>
		<tr>		
		<tr>
			<td><font
				class="promptReadOnly">Ticket Details:</font></td>
		</tr>
		<tr>
			<td>
				<table cellspacing="1" class="supplies-table">
					<tr>
						<% 
         if(objHeader.getInvoiceNo() != null && !"".equals(objHeader.getInvoiceNo()) && !"null".equalsIgnoreCase(objHeader.getInvoiceNo()) ) {  
%>
						<th>Invoice Number</th>
						<%
            }
%>
						<th>Date</th>
						<th>Type</th>
						<th>Created By</th>
						<th>Owner</th>
						<% //Start Changes for S21 by Mangala
               if("Y".equals(objHeader.getBillingIssue()) ) {  
	 %>
						<th>Collector</th>
						<%
	             }//End Changes for S21 by Mangala
%>
						<th>Status</th>
						<th>&nbsp;</th>
						<th>Source</th>					
						<th class="enable_email">&nbsp;</th>						
					</tr>
					<%       
         String strHeaderStatus = objHeader.getStatus();
         String strHdrAccess =  objHeader.getAttribute5();
         String strResolCode = objHeader.getresolutionCode();
         System.out.println("######## strResolCode = "+strResolCode + " strHdrAccess " + strHdrAccess);
/* ITG# 74988 - Begin */
         String strCFSSerialNo = objHeader.getAttribute4();
/* ITG# 74988 - End */
%>
					<tr >
						<% 
            if( objHeader.getInvoiceNo() != null && !"".equals(objHeader.getInvoiceNo()) && !"null".equalsIgnoreCase(objHeader.getInvoiceNo()) ) {    
%>  
                             <td style="text-align: center;"><a href="#"
							onClick="javascript:void InvoiceDetailsPDF('<%=objHeader.getInvoiceNo()%>');"><%=objHeader.getInvoiceNo()%></a></td>
						<%-- <td style="text-align: center;"><a href="#"
							onClick="javascript:InvoiceLineDetailsPDF('<%=objHeader.getInvoiceNo()%>');"><%=objHeader.getInvoiceNo()%></a></td> --%>
						<%
            }
%>
						
						<td style="text-align: center;"><%=objHeader.getCreationDate()==null?"":objHeader.getCreationDate()%></td>
						<td style="text-align: center;"><%=objHeader.getCategory()==null?"":objHeader.getCategory()%></td>
						<td style="text-align: center;"><%=objHeader.getCreatedByResName()%></td>
						<td style="text-align: center;"><%=objHeader.getOwnerResName()%>
						<input type="hidden"			name="hOwnerResId" value="<%=objHeader.getOwnerResId()%>">
						<input type="hidden" name="hOwnerRoleId"	value="<%=objHeader.getOwnerRoleId()%>"> 
						<input	type="hidden" name="hOwnerDeptCode"	value="<%=objHeader.getOwnerDeptCode()%>"> 	
						<input type="hidden" name="serialNumber" id="serialNumber" value="<%=strCFSSerialNo%>">					
						</td>
						<% //Start Changes for S21 by Mangala
	          if("Y".equals(objHeader.getBillingIssue()) ) {  
	 			%>
						<td style="text-align: center;"><%=objHeader.getCollectorName()==null?"":objHeader.getCollectorName()%>
						</td>
						<%
	             }//End Changes for S21 by Mangala
%>

						<td style="text-align: center;">
							<%
               if(("CLOSE".equalsIgnoreCase(strHeaderStatus)) || ("N".equalsIgnoreCase(strHdrAccess))){
            	   // Need to Implement here
            	   
%> <select name="updateheaderstatus" id="updateheaderstatus" size="1" class="searchBarLink"
							disabled>
								<%
               }
               else {
%>
								<select name="updateheaderstatus" id="updateheaderstatus" size="1" class="searchBarLink" onchange= "headerChange('<%=strHeaderStatus%>')" >
									<%
               }           
%>
									<option value="<%=strHeaderStatus%>" selected><%=strHeaderStatus%></option>
									<%       
                  ArrayList alHeaderStatus = (ArrayList)alHeaderLineStatus.get(0);
                  Canon_E193_TicketStatusObj objHdrSts = new Canon_E193_TicketStatusObj();
                  int iHdrStsLvl = -1;
                  for(int idx=0; idx < alHeaderStatus.size();idx++){
                     objHdrSts = (Canon_E193_TicketStatusObj) alHeaderStatus.get(idx);
                     if(strHeaderStatus.equalsIgnoreCase(objHdrSts.getStrAttribute2())) {
                        iHdrStsLvl = objHdrSts.getIAttribute1();
                        break;
                     }
                  }
                  for(int idx=0; idx < alHeaderStatus.size();idx++){
                     objHdrSts = (Canon_E193_TicketStatusObj) alHeaderStatus.get(idx);
                     if((iHdrStsLvl <= objHdrSts.getIAttribute1()) && !(strHeaderStatus.equalsIgnoreCase(objHdrSts.getStrAttribute2()))) {   
%>
									<option value="<%=objHdrSts.getStrAttribute2()%>"><%=objHdrSts.getStrAttribute2()%></option>
									<%
                     }
                  }
%>
							</select>
						</td>
				<%    /* Newly Added */     
	          if(s21AttachedFileList.size() > 0 || csaAttachedFileList.size() > 0) {  
	 			%>
						
						<td style="text-align: center;">
						 <b><a id="attachments" href="#"	onClick="javascript:action_Attach('<%=objHeader.getTicketNo()%>');" style="color:blue;">Attachments</a></b>
						</td>
						
		<%
	             }//End 
        else {
     %>	
                        <td>
						 <a id="attachments" href="#" onClick="javascript:action_Attach('<%=objHeader.getTicketNo()%>');">Attachments</a>
						</td>   	
				<%
	             } 			
				%>	
				<!-- Get Source type Name Start -->	
				<td style="text-align: center"><%=objHeader.getAttribute9()==null?"":objHeader.getAttribute9()%></td>	
				<!-- Get Source type Name End -->
				         <td class="enable_email">
							<a href="#" id="setup_email_button">Setup Email Notification</a>
						</td>						
					</tr>
				</table>
			</td>
		</tr>
		<% if("Internal".equalsIgnoreCase(objHeader.getOrigType())) {%>
		<tr>
			<td><font
				class="promptReadOnly">Caller Details:</font></td>
		</tr>
		<tr>
			<td>
				<table cellspacing="1" class="supplies-table">
					<tr>
						<th>Caller Type</th>
						<th>Internal Name</th>
						<th>Internal Phone #</th>
						<th>Internal E-Mail</th>
					</tr>
					<tr >
						<td style="text-align: center;">Internal</td>
						<td style="text-align: center;"><%=objHeader.getOrigName()==null?"":objHeader.getOrigName()%></td>
						<td style="text-align: center;"><%=objHeader.getOrigPhNo()==null?"":objHeader.getOrigPhNo()%></td>
						
						<td style="text-align: center;">
						<% 
						if (objHeader.getOrigEmail()!=null && !objHeader.getOrigEmail().trim().equals("") && !objHeader.getOrigEmail().trim().equals("null")) {
						%>	
							<a href="mailto:<%=objHeader.getOrigEmail()==null?"":objHeader.getOrigEmail()%>">
									<%=objHeader.getOrigEmail()==null?"":objHeader.getOrigEmail()%>
							</a>
						<% } %>
						</td>
					</tr>
				</table>
			</td>
		</tr>	
		<%} %>	
		<tr>
			<td><font
				class="promptReadOnly">Customer Details:</font></td>
		</tr>
		<tr>
			<td>
				<table cellspacing="1" class="supplies-table">
					<tr>
						<th>Account Name</th>
						<th>Account Number</th>
						<th>Contact Name</th>
						<th>Contact Number</th>
						<th>E-Mail</th>
						<%
/* ITG# 74988 - Begin */
	if ( "CFS".equals( objHeader.getCategory()) )
	{ 
%>
						<th>CFS Serial Number</th>
						<%
	}
/* ITG# 74988 - End */
%>

					</tr>
					<tr >
						<td style="text-align: center;"><%=objHeader.getCustomerName()==null?"":objHeader.getCustomerName()%></td>
						<td style="text-align: center;"><%=objHeader.getCustomerNo()==null?"":objHeader.getCustomerNo()%></td>
						<td style="text-align: center;"><%=objHeader.getCustContact()==null?"":objHeader.getCustContact()%></td>
						<td style="text-align: center;"><%=objHeader.getCustPhNo()==null?"":objHeader.getCustPhNo()%></td>
						<%/* ITG# 74988 - Begin */%>
						<%-- <td><%=objHeader.getCustEmail()==null?"":objHeader.getCustEmail()%></td> --%>
						<%/* ITG# 74988 - End */%>
						<%/* ITG# 74988 - Begin */%>
						<td style="text-align: center;">
						
						<% 
						
						if (objHeader.getCustEmail()!=null && !objHeader.getCustEmail().trim().equals("") && !objHeader.getCustEmail().trim().equals("null")) {
							
						%>	
						
						<a href="mailto:<%=objHeader.getCustEmail()==null?"":objHeader.getCustEmail()%>">
								<%=objHeader.getCustEmail()==null?"":objHeader.getCustEmail()%>
						</a>
						<% } %>
						
						
						</td>
						<%/* ITG# 74988 - End */%>
						<%
/* ITG# 74988 - Begin */
	if ( "CFS".equals( objHeader.getCategory()) )
	{ 
%>
						<td style="text-align: center;"><%=strCFSSerialNo==null?"":strCFSSerialNo%></td>
						<%
	}
/* ITG# 74988 - End */
%>
					</tr>
				</table>
			</td>
		</tr>		
		<tr>
			<td ><font
				class="promptReadOnly">Ticket Line Details:</font></td>
		</tr>
		</table>
		
				<div style="overflow: auto; width: 98.5%; margin-left:10px; margin-right:4px">
					<table cellspacing="1" class="supplies-table">
						<tr>
							<th valign="top">&nbsp;</th>
							<th valign="top">Category</th>
							<th valign="top">Updated By</th>
							<th valign="top" nowrap>Update Date</th>
							<th valign="top">Resolution Time<br>(in days)</th>
							<th valign="top" >Status</th>
							<th valign="top" >Urgency</th>
							<th valign="top" >Assignee Department</th>
							<th valign="top" >Assignee Role</th>
							<th valign="top" >Assignee Resource</th>
							<th valign="top">&nbsp;</th>
							<th valign="top">Reason</th>
							<!-- ITG # 208045 Changes  -->
							<th>Line Attributes</th>
							<!-- ITG # 208045 Changes  -->
						</tr>
						<%
            
            Canon_E193_Ticket objTktCatCount = new Canon_E193_Ticket();
            int iIssueCount = objTktCatCount.getIssueCount(objHeader.getCatId());
            int iTktIssueCount = 0;
            int iChildCatCount = -1;
            String strAttribute4 = "", strSummaryFlag = "N";
            boolean isFlagSummary = false;
          //  System.out.println("!!!!!!!!!!!!alHeaderLineDtls.size() is " + alHeaderLineDtls.size());
           
            /* for(int i=0; i<alHeaderLineDtls.size(); i++) {
            	
            	System.out.println(alHeaderLineDtls.get(i));
            } */
            
            String lineNote = "";
            for(int i=1; i<alHeaderLineDtls.size(); i++) {
				
            if(alHeaderLineDtls.get(i) !=null ) {	
            	
               Canon_E193_TicketLinesObj objLine = (Canon_E193_TicketLinesObj)alHeaderLineDtls.get(i);
               //Canon_E193_TicketHeaderObj objHeader = (Canon_E193_TicketHeaderObj) alHeaderLineDtls.get(0);
			 //  System.out.println("!!!!!!!!!!!!After executing  objLine.. " +objLine);
			   
               String strLineAccess =  objLine.getAttribute5();
               strLineIdConCat = strLineIdConCat + objLine.getLineId() + '|';
               String strLineStatus = objLine.getLineStatus();
               String strLineSeverity = objLine.getSeverity();
             
               /* ITG # 208045 Changes */
               String sourceApplication = "CUSTOMER_SERVICE";
               String sourceContext = "CS_E193_" +objHeader.getCategory() +"_" +objLine.getCatCode();
               
               //System.out.println("sourceContext!!!!!!!!!!!!!! ticket status " + sourceContext);
               int DffCount = (int)objNonBillInfo.getDffCount(sourceContext);
               /* ITG # 208045 Changes */
               // System.out.println("DffCount!!!!!!!!!!!!! ticket status " + DffCount);
               strAttribute4 = objLine.getAttribute4()==null?"":objLine.getAttribute4();
               //MW Project Changes
			   //if("USAGE".equalsIgnoreCase(strAttribute4)) {
                  strSummaryFlag = ""+objLine.getLineId()+"|"+objLine.getLineNo();
			                     
				  if(!isFlagSummary) isFlagSummary = true;
			// }
               
               // get total number of non_close issues added to this ticket
               if(!("CLOSE".equalsIgnoreCase(strLineStatus)))
               {
                  iTktIssueCount++;
                  
                  // get all non_close issues added to this ticket   
                  if(i == 1) 
                     strCatIdP = ""+objLine.getCatId();
                  else 
                     strCatIdP = strCatIdP + "|" + ""+objLine.getCatId();
               }
               
               iChildCatCount = objTktCatCount.getIssueCount(objLine.getCatId());   
               lineNote = "lineNote"+i;
%>

						<tr >
							<input type="hidden" name="lineId<%=i%>" id="lineId<%=i%>" value="<%=objLine.getLineId()%>">
							<input type="hidden" name="summaryLineId" id="summaryLineId"	value="<%=strSummaryFlag%>">
							<td style="text-align: center;"><input name="csdetails" type="radio" value="<%=i%>"
								onClick="select_Line(this, '<%=lineNote %>', '<%=strHeaderStatus%>')"></td>
							<td style="text-align: center;">
								<%
                  if(iChildCatCount > 0) {
                	  String titleName = "";
                	  String isBilling=  objHeader.getBillingIssue();
                	  String strCatIdDesc = objLine.getCatIdDesc();
                	  System.out.println("In TicketStatus.jsp= " + strCatIdDesc);
                	  if(strCatIdDesc!=null) {
                	  String[] result = strCatIdDesc.split(" ");
                	  String initialTitleName = result[0];
                	  if(isBilling.equalsIgnoreCase("Y")){
                		  titleName = "Billing"+" "+initialTitleName+" "+"Line Details";
                	  }else {
                		  titleName = initialTitleName+ " "+ "Line Details";
                	  }
                	  
                	  }  
                	  
%> <a href="#"
								onClick="javascript:get_sublines('<%=objLine.getLineId()%>', '<%=strAttribute4%>', '<%=titleName%>')"><%=objLine.getCatIdDesc()==null?"":objLine.getCatIdDesc()%></a>
								<%
                  } else {
%> <%=objLine.getCatIdDesc()==null?"":objLine.getCatIdDesc()%> <%
                  } 
%>
							</td>
							<td style="text-align: center;"><%=objLine.getLastUpdatedByName()==null?"":objLine.getLastUpdatedByName()%></td>
							<td nowrap style="text-align: center;"><%=objLine.getLastUpdatedDate()==null?"":objLine.getLastUpdatedDate()%></td>
							<td style="text-align: center;"><%=objLine.getAttribute3()==null?"":objLine.getAttribute3()%></td>
							<td style="text-align: center;">
								<%
				
			System.out.println("!!!!!!!!!!!!Ticket Status Page - strHeaderStatus : " + strHeaderStatus 
					+ " strLineStatus : " + strLineStatus  + " strHdrAccess : " + strHdrAccess + " strLineAccess : " + strLineAccess
					); 
								
               if( (!("ASSIGNED".equalsIgnoreCase(strHeaderStatus))) || ("CLOSE".equalsIgnoreCase(strLineStatus)) || 
                 (("N".equalsIgnoreCase(strHdrAccess)) && ("N".equalsIgnoreCase(strLineAccess)))){
%> <select name="updatestatus<%=i%>" size="1" class="searchBarLink"
								onChange="check_line_selected(<%=i%>,this,'<%=strLineStatus==null?"":strLineStatus%>')"
								disabled>
									<%
               }
               else {
%>
									<select name="updatestatus<%=i%>" size="1"
									class="searchBarLink"
									onChange="check_line_selected(<%=i%>,this,'<%=strLineStatus==null?"":strLineStatus%>')">
										<%
                    }
%>
										<option value="<%=strLineStatus%>" selected><%=strLineStatus%></option>
										<%
                  ArrayList alLineStatus = (ArrayList)alHeaderLineStatus.get(1);
                  Canon_E193_TicketStatusObj objLineSts = new Canon_E193_TicketStatusObj();
                  int iLineStsLvl = -1;
                  for(int idx=0; idx < alLineStatus.size();idx++){
                     objLineSts = (Canon_E193_TicketStatusObj) alLineStatus.get(idx);
                     if(strLineStatus.equalsIgnoreCase(objLineSts.getStrAttribute2())) {
                        iLineStsLvl = objLineSts.getIAttribute1();
                        break;
                     }
                  }
                  /* System.out.println("!!!!!!!!!!!!Ticket Status Page - alLineStatus : " + alLineStatus 
      					+ " iLineStsLvl : " + iLineStsLvl  ); */
                  for(int idx=0; idx < alLineStatus.size(); idx++) {
                     objLineSts = (Canon_E193_TicketStatusObj) alLineStatus.get(idx);
                     if((iLineStsLvl <= objLineSts.getIAttribute1()) && !(strLineStatus.equalsIgnoreCase(objLineSts.getStrAttribute2()))) {
                    	  /* System.out.println("!!!!!!!!!!!!Ticket Status Page - objLineSts.getStrAttribute2() : " + objLineSts.getStrAttribute2() 
               					);  */
                        if(("N".equalsIgnoreCase(strHdrAccess)) && !("CLOSE".equalsIgnoreCase(objLineSts.getStrAttribute2()))) { 
%>
										<option value="<%=objLineSts.getStrAttribute2()%>"><%=objLineSts.getStrAttribute2()%></option>
										<%
                        }
                        else if(!("N".equalsIgnoreCase(strHdrAccess))) {
%>
										<option value="<%=objLineSts.getStrAttribute2()%>"><%=objLineSts.getStrAttribute2()%></option>
										<%
                        }
                     }
                  }
%>
								</select>
							</td>
							<td style="text-align: center;">
								<%
               if((!("ASSIGNED".equalsIgnoreCase(strHeaderStatus))) || ("CLOSE".equalsIgnoreCase(strLineStatus)) || 
                 (("N".equalsIgnoreCase(strHdrAccess)) && ("N".equalsIgnoreCase(strLineAccess)))){
%> <select name="updateseverity<%=i%>" size="1" class="searchBarLink"
								onChange="check_line_selected(<%=i%>,this,'<%=strLineSeverity==null?"":strLineSeverity%>')"
								disabled>
									<%
               }
               else {
%>
									<select name="updateseverity<%=i%>" size="1"
									class="searchBarLink"
									onChange="check_line_selected(<%=i%>,this,'<%=strLineSeverity==null?"":strLineSeverity%>')">
										<%
                    }
               String strSelected = "";
               for(int idx=0; idx < alLineSeverity.size();idx++) {
                  String strSeverity = (String)alLineSeverity.get(idx);
                  if(strSeverity == null)
                     strSeverity = "";
                  if(strLineSeverity.equalsIgnoreCase(strSeverity))
                     strSelected = "selected";
                  else 
                     strSelected = "";
%>
										<option value="<%=strSeverity%>" <%=strSelected%>><%=strSeverity%></option>
										<%
               }
%>
								</select>
							</td>
							<td style="text-align: center;"><input type="hidden" name="deptCode<%=i%>" id="deptCode<%=i%>"	value="<%=objLine.getDeptCode()%>"> 
								<input type="text"	name="deptName<%=i%>" id="deptName<%=i%>" size="10" maxlength="100"	value="<%=objLine.getDeptName()==null?"":objLine.getDeptName()%>"
								class="txtbox" readonly=true></td>
							<td style="text-align: center;"><input type="hidden" name="roleId<%=i%>" id="roleId<%=i%>" value="<%=objLine.getRoleId()%>"> 
							    <input type="text"	name="roleName<%=i%>" id="roleName<%=i%>" size="10" maxlength="100"	value="<%=objLine.getRoleName()==null?"":objLine.getRoleName()%>"
								class="txtbox" readonly=true></td>
							<td style="text-align: center;"><input type="hidden" name="resourceId<%=i%>" id="resourceId<%=i%>"	value="<%=objLine.getResourceId()%>"> 
								<input type="text"	name="resourceName<%=i%>" id="resourceName<%=i%>" size="10" maxlength="100" value="<%=objLine.getResourceName()==null?"":objLine.getResourceName()%>"
								class="txtbox" readonly=true></td>
							<%
               if((!("ASSIGNED".equalsIgnoreCase(strHeaderStatus))) || ("CLOSE".equalsIgnoreCase(strLineStatus)) || 
                 ( ("N".equalsIgnoreCase(strHdrAccess)) && ("N".equalsIgnoreCase(strLineAccess)))){
%>
							<td style="text-align: center;">Assign</td>
							<%
               }
               else {
%>
							<td style="text-align: center;"><a href="#"
								onClick="javascript:fnAssign('<%=objLine.getDeptCode()%>', '<%=objLine.getRoleId()%>','<%=objLine.getResourceId()%>',<%=i%>);">
									Assign </a></td>
							<%
               }
%>
							<td style="text-align: center;"><input type="hidden" name="reasonCode"
								value="<%=objLine.getReasonCd()%>"> <%=objLine.getReason()==null?"":objLine.getReason()%>
							</td>
							<!-- ITG # 208045 Changes -->
							<td style="text-align: center;">
								<% if (DffCount == 0) { %> <font size=4><b>...</b></font> <% } else { %>
								<a href="#"
								onClick="javascript:OpenDff('<%=sourceApplication%>',  '<%=sourceContext%>', '<%=objHeader.getTicketNo()%>', '<%=objCiSession.getUserId()%>');"><font
									size=4><b>...</b></font></a> <% } %>
							</td>
							<!-- ITG # 208045 Changes -->
						</tr>
						<%    
            }
			else{
				
				 System.out.println(" objLine  is NULL  i = "+i);
				
				
			}
		}	
            if(alHeaderLineDtls.size() < 2) {
%>
						<tr >
							<td colspan="14"><font class="promptReadOnly"><b><%=strTicketStatusM1%></b></font>
							</td>
						</tr>
						<%
            }
%>
					</table>
				</div>
		<table class="request-service" style="align:center;" cellspacing="0" width="100%">
		<tr>
			<td height="10" ></td>
		<tr>
			<td class="tableQuestionCell" ><font
				class="promptReadOnly" color="#FF0000"> <b><%=strTicketStatusN2%></b>
			</font></td>
		</tr>		
		<tr>
			<td class="tableQuestionCell" ><font
				class="promptReadOnly">Ticket Comments:</font></td>
		</tr>
		<!-- <tr>
			<td height="10" colspan="2"></td>
		</tr> -->
		<tr id="0" style="position: absolute; visibility: visible;">

			<td ><textarea name="ticketComments" id="ticketComments" rows="10" style="height:auto; width:800px;" class="inTxt" readonly></textarea></td>
		</tr>
		<% 
      for(int i=1; i<alHeaderLineDtls.size(); i++) {
         Canon_E193_TicketLinesObj objLine = (Canon_E193_TicketLinesObj)alHeaderLineDtls.get(i);
    %>
		<tr id="lineNote<%=i%>" style="position: absolute; visibility: hidden;">
			<td ><textarea name="existingnotes" id="existingnotes" rows="10" style="height:auto;width:800px;" class="inTxt" readonly><%=objLine.getNotes()==null?"":objLine.getNotes()%></textarea>
				<input type=hidden name="enlen" id="enlen"	value="<%=objLine.getNotes()==null?0:objLine.getNotes().length()%>">
			</td>
		</tr>
		<%  }  %>
		<!-- <tr>
			<td height="10" colspan="2">&nbsp;</td>
		</tr> -->
		 <tr>
			<td height="90" >&nbsp;</td>
		</tr>
		<tr>
			<td height="90" >&nbsp;</td>
		</tr>
		<%//Start changes for S21 by Mangala %>
		<tr class="tabTableStyle">
			<td >
				<table width="100%" class="tabexttraSmallTableStyle">
					<td width="50px"><font class="promptReadOnly">Final ResolutionCode:</font></td>
					<td style="margin-left: 5cm;">
						<%  
      		if(("CLOSE".equalsIgnoreCase(strHeaderStatus)) || ("N".equalsIgnoreCase(strHdrAccess))){
      	%> <select name="rescode" size="1" class="searchBarLink" disabled>
							<%
               } else {  
               
            %>
							<select name="rescode" size="1" class="searchBarLink">
								<%  }  
						if( strResolCode != null && !"".equals(strResolCode) && !"null".equalsIgnoreCase(strResolCode) ){
      						//TBD strHeaderStatus needs to be replaced with resolution code 
                            %>
								<option value="<%=strResolCode%>" selected><%=strResolCode%></option>
								<%} %>
								   <option value="">Select One</option>
          						<%  
                               if((iHdrStsLvl <= objHdrSts.getIAttribute1()) && !(strHeaderStatus.equalsIgnoreCase(objHdrSts.getStrAttribute2()))) {   
                            	 if(alResolutionList != null && alResolutionList.size() > 0) {
                                    String reqResCd = request.getParameter("rescode")==null?"":request.getParameter("rescode");
                                    String code = "";
                                    for(int i=0; i<alResolutionList.size(); i++) {
                                        code = alResolutionList.get(i).toString();
                            %>
								<option value="<%=code%>"
									<%=code.equals(reqResCd)?"selected":""%>><%=code%></option>
								<%      }
                                }
                                }
                            %>
						</select>
					</td>
				</table>
			</td>
			<%//End changes for S21 by Mangala %>
		
		<!-- <tr>
			<td height="10" colspan="2"></td>
		</tr> -->
		<tr>
			<td class="tableQuestionCell" ><font
				class="promptReadOnly" color="#FF0000"><b><%=strTicketStatusN1%></b></font>
			</td>
		</tr>
		<tr>
			<td ><textarea name="additionalnotes" id="additionalnotes" rows="10" style="background-color:#FFFFFF;height:auto; width:800px;" class="inTxt required"  onChange="notes_Change(this,'<%=strHeaderStatus%>','<%=strLineIdConCat%>')" disabled="disabled"></textarea></td>
		</tr>
		<!-- <tr>
			<td height="10" colspan="2"></td>
		</tr> -->
		<tr>
			<td class="tableQuestionCell" ><font
				class="promptReadOnly" color="#FF0000"> <b><%=strTicketStatusN3%></b>
			</font></td>
		</tr>
		<!-- <tr>
			<td colspan="2" height="10"></td>
		</tr> -->
		<tr>
			<td class="tableQuestionCell" ><font
				class="promptReadOnly" color="#FF0000"> <b><%=strTicketStatusN4%></b>
			</font></td>
		</tr>
		<!-- <tr>
			<td colspan="2" height="10"></td>
		</tr> -->
		<tr>
			<td align="right">
				<table style="margin-left: 20px;">
					<tr>
						<td>
							<a class="btn_red" style="margin-left: -25px;" href="javascript:javascript:history.back();">Prev</a>
						</td>
						<td>
							<a id="saveButton" class="btn_red" href="javascript:action_Save('<%=objHeader.getStatus()%>','<%=strLineIdConCat%>');">Save Changes</a>
						</td>
						<%					
/* ITG# 74988 - Begin */
//              if( "N".equals(objCiSession.getCFSUserFlag()) )
              if ( !("CFS".equals( objHeader.getCategory())) )
              {
%>
						<td>
						<% if((iTktIssueCount < iIssueCount) && ("ASSIGNED".equalsIgnoreCase(strHeaderStatus)) ) { %>
							<a class="btn_red" href="javascript:action_Add_Issues('<%=strCatIdP%>');">Add Issues</a>                     
                  <% } else { %>
                  <a class="btn_red" href="#">Add Issues</a>                     
                  <% } %>
						
						<script language="javascript"> 
                  
                  </script></td>
						<td>
						<% if(isFlagSummary) { %>
						<a class="btn_red" href="javascript:action_summary();">Credit/Rebill Summary</a>
                     <% }else{ %>
                     <a class="btn_red" href="#">Credit/Rebill Summary</a>
                     <% } %>
						</td>
						<%
              }
/* ITG# 74988 - End */
%> 
                        <td>
                        	<% 	if (objHeader.getCustomerNo() != null && !objHeader.getCustomerNo().trim().isEmpty() 
                        			&& objHeader.getCustomerName() != null && !objHeader.getCustomerName().trim().isEmpty()) 
                        		{
                        	%>
							<a class="btn_red" href="javascript:action_redirectToBilling(<%=objHeader.getTicketNo()==null?"":objHeader.getTicketNo()%>);">Ticket To Copy</a>
							<%
                        		}
							%>
						</td>
					</tr>
				</table>
			</td>
		</tr>
</table>
	</form>
</div>
<div id="dlg"></div>
<div id="dlg2"></div> 
<%@ include file="canon_E193_csBottomInc.jsp"%>
</div>


