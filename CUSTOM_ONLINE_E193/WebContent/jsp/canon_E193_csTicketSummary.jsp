<!-- $Header: canon_E193_csTicketSummary.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csTicketSummary.jsp - Ticket summary.
 |   
 | DESCRIPTION
 |   Details of the ticket summary.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	09/19/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 21-Apr-2009 Chandra Sekhar    ITG # 176718 Change
 | 22-Apr-2009 Chandra Sekhar    ITG # 208045 - CanonBall Changes.
 |
 +=======================================================================--%>
<%@page language="java" %>
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.Canon_E193_AttachmentUploadDAO"%>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objHeaderLinesList" scope="request" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Ticket" id="ticketDao" scope="page" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Invoice" id="objCiInvInfo" scope="page"/>
 
<!-- ITG # 208045 Changes -->
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_NonBillingIssue" id="objNonBillInfo" scope="page" />
<jsp:setProperty name="objNonBillInfo" property="*" />
<!-- ITG # 208045 Changes -->

<%@ include file="canon_E193_csTopInc.jsp" %>
<%!
 static String checkNull(String str){
	 if (str != null) {
	    return str;
	  }else {
		return "";
     }
		
 }
 
%>
<%

System.out.println("in ticket summary jsp");
	strPageName = "Enter & Inquiry";
	isActivePage = true;
	// Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");
	String hPath = request.getParameter("hPath");
	if (hPath == null)
		hPath = strTicketSummaryT1;
	else
		if(hPath.indexOf(strTicketSummaryT1) < 0)
			hPath = hPath + " -> " + strTicketSummaryT1;
	Canon_E193_AttachmentUploadDAO objFileUploadDAO1	= new Canon_E193_AttachmentUploadDAO();
%>
<%-- <%

objHeaderLinesList.setTicketSaveStatus(true);
%> --%>
<%@ include file="canon_E193_csMenuInc.jsp" %>

<script language="JavaScript">
	var index = -1;
	function getLovWithValue(pageName,paramName,value,title) {
	 	   showPleaseWait();
	      var modelName ="#dlg";
	 	   $(modelName).dialog({
						height: 500,
						title: title, 
						modal: true ,
						zIndex:1005,
						width: 650,		
		                resizable: false      
					});
	 	   
	 	   var paramVal=value;
	 	   var qryStr=paramName+"="+encodeURIComponent(paramVal);
	 	       $.ajax({
				url: pageName,
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
	}
	
	function fnAssignment(id, assignDept, assignRole) {
		index = id;
		document.ticketSummaryForm.hLineSel.value=index;
		var val = $("#resourceId"+id).val();
		showPleaseWait();			 
		 var modelName ="#dlg";
	 	   $(modelName).dialog({
						height: 500,
						title: "Ticket Assignments", 
						modal: true ,
						zIndex:1005,
						width: 650,		
		                resizable: false      
					});
	 	   
	 	  var qryStr="hPageFrom=TicketSummary&hDeptSel=yes&&hRoleSel=yes&assignDept="+assignDept+"&assignRole="+assignRole+"&hResourceId="+val+"&selectedRoleVal="+assignRole;
	 	  //var qryStr="hPageFrom=TicketSummary&hDeptSel=yes&&hRoleSel=yes&assignDept="+assignDept+"&assignRole="+assignRole+"&hResourceId="+val;
	 	  //alert(qryStr);
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

	function action_func2() {
		var objForm = document.ticketSummaryForm;
		if(fnValidateField(objForm)) {
			var v = objForm.controlForm.value;
			if(v == null || v == '' || v == 'null') objForm.action = "canon_E193_csNBIssueList.jsp";
			else objForm.action = "canon_E193_csBIssueList.jsp";
			objForm.submit();
		}
	}
	
	function action_func1() {
		resetErroMsg();
		$("#saveButton").attr("disabled", true);
		$("#saveButton").removeClass("btn_red");
		$("#saveButton").addClass("btn_disabled");
		var objForm = document.ticketSummaryForm;
		var v = 0;
		
		if(objForm.iCountRecords != null) v = objForm.iCountRecords.value;
		//alert('Count ' + v);
		var isTrue = 'true';
		if(v > 0) {
			for(i=0; i < v; i++) {
				if($("#deptName"+i).val() == '' || $("#roleName"+i).val() == '' || $("#resourceName"+i).val()  == '') {
					displayErrorMsg("please select assignment");
					isTrue = 'false';
					break;
				}
			}
		}
		
		if(objForm.iCountRecords != null && isTrue == 'true') {
			var isChangedField = 'false';
			if(v > 0) {
				for(i=0; i < v; i++) {
					if($("#isChangedLine"+i).val() == "YES") {
						isChangedField = 'true';
						break;
					}
				}
			}
			if(isChangedField == 'false') {
				isTrue = 'false';
				alert("No change in the form");
			}
		}
		
		
		var consBillRadio = document.getElementsByName('consolidatedBill') ;
		var k = 0;
		if(isTrue == 'true' && consBillRadio != null &&  consBillRadio.length > 1 )
		{
			for(k = 0; k < consBillRadio.length; k++)
			{
				if (consBillRadio[k].checked)
					break;
			}
			if( k >= consBillRadio.length )
			{
				isTrue = 'false';
				displayErrorMsg("Please select option to generate a new Consolidated Bill. ");
			}
		}
		//alert('h2');
		if(isTrue == 'true') {
			var reAssignVal = objForm.reAssign.value;
			if( reAssignVal !=null )
			 objForm.noReAssign.value = "normal";
			else {
				objForm.reAssign.value = "reAssigned";
			}
			objForm.action = "canon_E193_csTicketSummaryAssignController.jsp";
			objForm.submit();
		}else{
			$("#saveButton").attr("disabled", false);
			$("#saveButton").removeClass("btn_disabled");
			$("#saveButton").addClass("btn_red");
			
		}
	}
	
	function fnEdit(lineId, lineStatus) {
		var objForm = document.ticketSummaryForm;
		if(fnValidateField(objForm)) {
			objForm.iLineId.value = lineId;
			objForm.nextPage.value = "canon_E193_csNBIssueCapture.jsp";
			objForm.action = "canon_E193_csTicketLineController.jsp";
			objForm.submit();
		}
	}
	
	function action_func3() {
		var objForm = document.ticketSummaryForm;
		if(fnValidateField(objForm)) {
			objForm.submitFlag.value = "wrapup";
			objForm.action = "canon_E193_csTicketSummaryAssignController.jsp";
			objForm.submit();
		}
	}
	
	function fnValidateField(objForm) {		
		var isChangedField = true;
		var v = 0;
		if(objForm.iCountRecords != null) v = objForm.iCountRecords.value;
		//alert('Count' + v);
		if(v > 0) {
			for(i=0; i < v; i++) {
				if(objForm['resourceName'+i].value != objForm['resourceName'+i].defaultValue ||
						 objForm['deptName'+i].value !=  objForm['deptName'+i].defaultValue ||
						 objForm['roleName'+i].value != objForm['roleName'+i].defaultValue) {
					isChangedField = false;
					break;
				} 
			}
		}
		if(!isChangedField) {
			return confirm("Please save changes otherwise changes will be lost. Do you want continue!");
		}
		return isChangedField;
	}
	
	function fnDelete(lineId, lineStatus) {
		resetErroMsg();
		var objForm = document.ticketSummaryForm;
		if(lineStatus == 'ASSIGNED' || lineStatus == 'UNASSIGNED') {
			if(confirm("Are you sure you want to delete this line?")) {
				objForm.iLineId.value = lineId;
				objForm.submitFlag.value = "remove";
				objForm.action = "canon_E193_csTicketSummaryAssignController.jsp";
				objForm.submit();
			}
		}else {
			displayErrorMsg("Line can only be deleted if it has status of ASSIGNED/UNASSIGNED");
		}
	}
	
	function fnReview(lineId) {
		var objForm = document.ticketSummaryForm;
		var ticketId = objForm.iTicketId.value;
		var cForm = objForm.controlForm.value;
		var invNo = objForm.invoiceNumber.value;
		var iSource = objForm.InvSource.value;
		var iSourceType = objForm.strInvoiceType.value;
		//alert("lineId---->"+lineId);
		//alert("ticketId---->"+ticketId);
		//alert("cForm---->"+cForm);
		//alert("invNo---->"+invNo);
		//alert("iSource---->"+iSource);
		//alert("iSourceType---->"+iSourceType);
		 showPleaseWait();			 
		 var modelName ="#dlg";
	 	   $(modelName).dialog({
						height: 500,
						title: "Review", 
						modal: true ,
						zIndex:1005,
						width: 650,		
		                resizable: false      
					});
	 	   
	 	  var qryStr="iLineId="+lineId+"&iTicketId="+ticketId+"&controlForm="+cForm+"&invoiceNumber="+invNo+"&InvSource="+iSource+"&strInvoiceType="+iSourceType;
	       $.ajax({
			url: "canon_E193_csTicketLineControllerP.jsp",
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
	}
	
	/*function InvoiceLineDetails(pInvNo) {
  		var vLink = "canon_E193_csActualInvoiceController.jsp?InvNo=" + pInvNo; 
  		var vWin = window.open(vLink, "newwin", "height=300,width=900,toolbar=no,menubar=no,scrollbars=yes,resizable=yes"); 
  		vWin.focus();
	}*/
	
	function fnAttachments(ticketNo) {
		//NewLy Added Attachment
		var invNum = $('#invNum1').val();
		var selAcctNum=$("#selAcctNum").val();       	
	   	if(selAcctNum.length>1)
	   		invNum=selAcctNum;  
		var vWin = window.open( "canon_E193_csAttachmentsP.jsp?ticket_number="+ticketNo+"&invNum="+invNum, "newwin","height=350,width=450,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
     	vWin.focus();
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
	   	  var detectBrowser=msieversion();
		     if("otherbrowser"!=detectBrowser)
		 		{
		 		$('.ui-widget-overlay').remove();
		 		}		 				 		     
   }
   
   function uploadAttachFile(fileName){
	   	$('#fileNm').val(fileName);   
	   	var tktId = $('#ticketnumber').val();
	   	var invNum = $('#invNum').val();
		var selAcctNum=$("#selAcctNum").val();       	
	   	if(selAcctNum.length>1)
	   		invNum=selAcctNum;  	   	
	   	
	   	if(fileName!=''){
	   		var url = "canonE193AttachmentUpload.jsp";
	   		var qryStr="ticketnumber="+encodeURIComponent(tktId)+"&fileName="+fileName+"&invNum="+invNum;   		
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
   
   /*function dept_select()
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
   }
   
   function select_assignee() 
   {
   		resetErroMsg();
	   	var resourceVal = document.AssignmentForm.hResourceId.value;
	   	var reAssignVal = document.AssignmentForm.reAssign.value;
	   	//reAssign
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
	   		 	if(resourceVal != null)
	   		 		resourceDesc = document.AssignmentForm.hResourceIdText.value;   		 	
	   		 getAssignment(deptVal, roleVal, resourceVal, deptDesc, assignDesc, resourceDesc,reAssignVal);
	   	}
   	}
   
   function resource_select(selectField){
	   	var selectedResourceName=$(selectField).closest('tr').find('td[class="assignResource"]').text();	  
	   	document.AssignmentForm.hResourceId.value = selectField.value;
	   	document.AssignmentForm.hResourceIdText.value=selectedResourceName;
	} */
   
   function getAssignment(deptVal, roleVal, resourceVal, deptDesc, assignDesc, resourceDesc, reAssignVal)
   {	   
      	var vLineSel = document.ticketSummaryForm.hLineSel.value;
      	
      	//alert('vLineSel :' + vLineSel);
      
      	var vDeptCode = "deptCode" + vLineSel;
      	var vRoleId = "roleId" + vLineSel;
      	var vResId = "resourceId" + vLineSel;
      	var vDept = "deptName" + vLineSel;
      	var vRole = "roleName" + vLineSel;
      	var vRes = "resourceName" + vLineSel;      
      	var vIsChangedLine = "isChangedLine" +  vLineSel;
      	
      	document.ticketSummaryForm.reAssign.value = reAssignVal;
      	$("#"+vDeptCode).val(deptVal);
       	$("#"+vRoleId).val(roleVal);
      	$("#"+vResId).val(resourceVal);      
      	$("#"+vDept).val(deptDesc);
     	$("#"+vRole).val(assignDesc);
     	$("#"+vRes).val(resourceDesc);
      	$("#"+vIsChangedLine).val("YES");
     	
     	//alert('vDeptCode : ' + $("#"+vDeptCode).val());
     	//alert('vRoleId :' + $("#"+vRoleId).val());
     	//alert('vResId :' + $("#"+vResId).val());
     	//alert('vDept :' + $("#"+vDept).val());
     	//alert('vRole :' + $("#"+vRole).val());
     	//alert('vRes :' + $("#"+vRes).val());
      
     	document.ticketSummaryForm.hLineDept.value = deptVal;
        document.ticketSummaryForm.hLineRole.value = roleVal;
        document.ticketSummaryForm.hLineResource.value = resourceVal;
        
  		closeDlg();
   }
   
  
// ITG # 208045  Changes
//-->
</script>
<%
 //  boolean isSaveClicked = true;
    Canon_E193_SessionObj sessObj1 = new Canon_E193_SessionObj();
    String iUserId = objCiSession.getUserId();
    Canon_E193_Assignment updateAssignRes = new Canon_E193_Assignment();
	String strParentCatId = request.getParameter("hParentCatId")==null?"":request.getParameter("hParentCatId");
   System.out.println("!!!!!strParentCatId = " + strParentCatId );
	int iIssueCount = 0;
	if(!"".equals(strParentCatId)) {
		int iParentCatId = Integer.parseInt(strParentCatId);
		iIssueCount = ticketDao.getIssueCount(iParentCatId);
	}
	Object objAcct = session.getAttribute("objSessionAcctInfo");
	Canon_E193_AcctInfoObj objSessionAcctInfo = null;
	if(objAcct == null) new Canon_E193_AcctInfoObj();
	else objSessionAcctInfo = (Canon_E193_AcctInfoObj)objAcct;
	Canon_E193_TicketHeaderObj objTicketHeader = null;
 	int iCountHistList = 0;
 	ArrayList alHeader = (ArrayList)objHeaderLinesList.getArrayList();
 	System.out.println(" ~!!!!!alHeader size=  " + alHeader.size() );
 	if(alHeader != null && alHeader.size() > 0) {
 		iCountHistList = alHeader.size();
 		System.out.println("~iCountHistList~ "+ iCountHistList);
 		objTicketHeader = (Canon_E193_TicketHeaderObj)alHeader.get(0);
 		alHeader.remove(0);
 	}else{
 		objTicketHeader = new Canon_E193_TicketHeaderObj();
 	}
	String strInvNo = objTicketHeader.getInvoiceNo()==null?"":objTicketHeader.getInvoiceNo();
	System.out.println("!!!!!strInvNo =  " + strInvNo );
 	int iBillToCustId = objSessionAcctInfo.getAcctId();
	int iOrgId = objCiSession.getOrgId();
	System.out.println("in ticket summary jsp" + iOrgId  + "iBillToCustId " + iBillToCustId);
	Canon_E193_InvoiceObj objInvoice = null;
	if(!"".equals(strInvNo)) objInvoice = objCiInvInfo.getInvoiceDetails(iBillToCustId, iOrgId, strInvNo);
	else objInvoice = new Canon_E193_InvoiceObj();
	System.out.println("!!!!!!!!!!objInvoice = ");
	String strConsolidatedBill = request.getParameter("consolidatedBill")==null?"": request.getParameter("consolidatedBill");
	String selConBillYes = "";
	String selConBillNo = "";
	if("Y".equalsIgnoreCase(strConsolidatedBill))
			selConBillYes = "checked";
		if("N".equalsIgnoreCase(strConsolidatedBill))
			selConBillNo = "checked";
		System.out.println("in ticket summary jsp selConBillNo is " + selConBillNo);	
		
		String strTicketNumber1 = objTicketHeader.getTicketNo();
		  System.out.println("!!!!!!!!!!!!objHeader TicketNumber =  " +strTicketNumber1); 
		  ArrayList<CanonE193AttFileRec> s21AttachedFileList1=objFileUploadDAO1.getFilesFromS21DB(Integer.parseInt(strTicketNumber1));
%> 

<div id="main_content">
	<div id="page_top">
		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strTicketSummaryT1%></h1>
		<div class="breadcrumb"><%=hPath%></div>		
	</div>	
<div class="table-inner">	
	<form name="ticketSummaryForm" id="ticketSummaryForm" method="post">
	
	<input type="hidden" name="hPageFrom" value="TicketSummary">
	<input type="hidden" name="nextPage" value="">
	<input type="hidden" name="iTicketId" id="iTicketId" value="<%=request.getParameter("iTicketId")%>">
	<input type="hidden" name="ticketnumber" id="ticketnumber" value="<%=request.getParameter("iTicketId")%>">
	
	<input type="hidden" name="iLineId"  id="iLineId" value="">
	<input type="hidden" name="submitFlag" value="">
	<input type="hidden" name="reAssign" value="">
	<input type="hidden" name="noReAssign" value="">
	<input type="hidden" name="invoiceNumber" id="invNum1" value="<%=strInvNo%>">
	<input type="hidden" name="InvSource" value="<%=request.getParameter("InvSource")==null?objTicketHeader.getCatCode():request.getParameter("InvSource")%>">
	<input type="hidden" name="strPurchageOrd" value="<%=objInvoice.getStrPurchageOrder()==null?"":objInvoice.getStrPurchageOrder()%>">
	<input type="hidden" name="strStatus" value="<%=objInvoice.getStrStatus()==null?"":objInvoice.getStrStatus()%>">
	<input type="hidden" name="iTrxId" value="<%=objInvoice.getICustTrxId()%>">
	<input type="hidden" name="iTaxOrig" value="<%=objInvoice.getITaxOrig()%>">
	<input type="hidden" name="iFreightOrig" value="<%=objInvoice.getIFreightOrig()%>">
	<input type="hidden" name="iBillToSiteUseId" value="<%=objInvoice.getIBillToSiteUseId()%>">
	<input type="hidden" name="iShipToSiteUseId" value="<%=objInvoice.getIShipToSiteUseId()%>">
	<input type="hidden" name="controlForm" value="<%=request.getParameter("controlForm")%>">
	<input type="hidden" name="hCatId" value="<%=request.getParameter("hCatId")%>">
	<input type="hidden" name="hParentCatId" value="<%=objTicketHeader.getCatId()%>">
	<input type="hidden" name="hCatCode" value="<%=objTicketHeader.getCatCode()==null?"":objTicketHeader.getCatCode()%>">
	<input type="hidden" name="hCatDesc" value="<%=objTicketHeader.getCatIdDesc()==null?"":objTicketHeader.getCatIdDesc()%>">

	<input type="hidden" name="strContractNo" value="<%=objTicketHeader.getContractNo()==null?"":objTicketHeader.getContractNo()%>">
	<input type="hidden" name="strContactNoMod" value="<%=objTicketHeader.getContractModifier()==null?"":objTicketHeader.getContractModifier()%>">
	<input type="hidden" name="strOrderNo" value="<%=""+objTicketHeader.getOrderNo()%>">
	<input type="hidden" name="strOrderType" value="<%=objTicketHeader.getOrderType()==null?"":objTicketHeader.getOrderType()%>">
	
	<input type="hidden" name="origName" value="<%=objTicketHeader.getOrigName()==null?"":objTicketHeader.getOrigName()%>" >
	<input type="hidden" name="origPhNo" value="<%=objTicketHeader.getOrigPhNo()==null?"":objTicketHeader.getOrigPhNo()%>">
	<input type="hidden" name="origEmailAddress" value="<%=objTicketHeader.getOrigEmail()==null?"":objTicketHeader.getOrigEmail()%>">
	<input type="hidden" name="origCheckbox" value="<%=objTicketHeader.getsendEmailFlag()==null?"":objTicketHeader.getsendEmailFlag()%>">
	<input type="hidden" name="origType" value="<%=objTicketHeader.getOrigType()==null?"":objTicketHeader.getOrigType()%>" >
	<input type="hidden" name="sourceType" value="<%=objTicketHeader.getAttribute9()==null?"":objTicketHeader.getAttribute9()%>" >
	<% if("customer".equalsIgnoreCase(objTicketHeader.getOrigType())) { %>
		<input type="hidden" name="custName" value="" >
		<input type="hidden" name="custPhNo" value="">
		<input type="hidden" name="custEmailAddress" value="">
	<% }else{ %>
		<input type="hidden" name="custName" value="<%=objTicketHeader.getCustContact()==null?"":objTicketHeader.getCustContact()%>" >
		<input type="hidden" name="custPhNo" value="<%=objTicketHeader.getCustPhNo()==null?"":objTicketHeader.getCustPhNo()%>">
		<input type="hidden" name="custEmailAddress" value="<%=objTicketHeader.getCustPhNo()==null?"":objTicketHeader.getCustPhNo()%>">
	<% } %>
	<input type="hidden" name="strInvoiceType" value="<%=objInvoice.getStrAttribute1()==null?"":objInvoice.getStrAttribute1()%>">
	<input type="hidden" name="strExpirationDate" value="<%=objInvoice.getStrAttribute2()==null?"":objInvoice.getStrAttribute2()%>">
	<input type="hidden" name="strContractStatus" value="<%=objInvoice.getStrAttribute3()==null?"":objInvoice.getStrAttribute3()%>">
	<input type="hidden" name="strServiceBranch" value="<%=objInvoice.getStrAttribute4()==null?"":objInvoice.getStrAttribute4()%>">
	<input type="hidden" name="strBasePeriod" value="<%=objInvoice.getStrAttribute5()==null?"":objInvoice.getStrAttribute5()%>">
	<input type="hidden" name="strOveragePeriod" value="<%=objInvoice.getStrAttribute6()==null?"":objInvoice.getStrAttribute6()%>">
	<input type="hidden" name="strCount" value="<%=objInvoice.getStrAttribute7()==null?"":objInvoice.getStrAttribute7()%>">
	<input type="hidden" name="vBillType" value="<%=request.getParameter("vBillType")%>">
	<input type="hidden" name="aggregateContractNum" value="<%=request.getParameter("aggregateContractNum")%>">
	<input type="hidden" name="hLineSel" id="hLineSel" value="">
	<input type="hidden" name="hLineDept" id="hLineDept" value=""> 
	<input type="hidden" name="hLineRole" value=""> 
	<input type="hidden" name="hLineResource" value="">
	
	<% 
		
		String recProb = "";
		if(objTicketHeader.getRecurring() != null && "N".equals(objTicketHeader.getRecurring()))
			recProb = "no";
		else if(objTicketHeader.getRecurring() != null && "Y".equals(objTicketHeader.getRecurring()))
			recProb = "yes";
	%>
	<input type="hidden" name="recurProb" value="<%=recProb%>">
	<input type="hidden" name="probType" value="">
	<input type="hidden" name="val1" value="">
	
	<input type="hidden" name="selAcctId" value="<%=String.valueOf(objTicketHeader.getCustAcctId())%>">
	<input type="hidden" name="selLocId" value="" >
	<input type="hidden" name="selAcctName" value="<%=objTicketHeader.getCustomerName()==null?"":objTicketHeader.getCustomerName()%>">
	<input type="hidden" name="selAcctNum" id="selAcctNum" value="<%=objTicketHeader.getCustomerNo()==null?"":objTicketHeader.getCustomerNo()%>">
	<input type="hidden" name="hPath" value="<%=hPath%>">
	<%-- Newly Added By Basudev --%>
  		        <input type="hidden" name="email_to_address" id="email_to_address" value="<%=checkNull(objTicketHeader.getCustEmail())%>">				
				<input type="hidden" name="email_subject" id="email_subject" value="Canon Solutions America - Closed Customer Care Inquiry Confirmation">				
				<input type="hidden" name="email_body" id="email_body" value="">				
				<input type="hidden" name="email_skip_notification" id="email_skip_notification" value="">				
				<input type="hidden" name="email_comment" id="email_comment" value="">
	<!-- <div style="margin-left:30px;"> Customer Details</div> -->
	<div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
	</div>
	<table class="request-service" style="align:center;" cellspacing="0" border="0">
	<% if(objSessionAcctInfo != null) { %>
	<!-- <tr><td height="5" colspan="1"></td></tr> -->
        <tr>
            <td class="tdTableCellStyle" colspan="3"><font class="promptReadOnly">Customer Details:</font></td>
            <!-- <td class="tdTableCellStyle" colspan="3"><a href="#" onclick="javascript:print();"><font class="promptReadOnly">print</font></a></td> -->
        </tr>
        <tr>
            <!-- <td width="10">&nbsp;</td>  -->     
            <td colspan="2">
		    	<table cellspacing="1" class="supplies-table">
			 		<tr>
						<th>Account Name</th>
						<th>Account Number</th>
						<th>Contact Name</th>
						<th>Contact Number</th>
					</tr>
			   		<tr>
						<td style="text-align: center;">
							<% String strDisplayName = "";
								if(objSessionAcctInfo.getAcctName() != null && !objSessionAcctInfo.getAcctName().equalsIgnoreCase("null")) 
									strDisplayName = objSessionAcctInfo.getAcctName();
							%><%=strDisplayName%>
						</td>
						<td style="text-align: center;">
							<% String strDisplayNo = "";
								if(objSessionAcctInfo.getAcctNum() != null && !objSessionAcctInfo.getAcctNum().equalsIgnoreCase("null")) 
									strDisplayNo = objSessionAcctInfo.getAcctNum();
							%><%=strDisplayNo%>
						</td>
						<td style="text-align: center;">
							<% String strDisplayContName = "";
								if(objSessionAcctInfo.getContactName() != null && !objSessionAcctInfo.getContactName().equalsIgnoreCase("null")) 
									strDisplayContName = objSessionAcctInfo.getContactName();
							%><%=strDisplayContName%>
						</td>
						<td style="text-align: center;">
							<% String strDisplayContNo = "";
								if(objSessionAcctInfo.getContactNum() != null && !objSessionAcctInfo.getContactNum().equalsIgnoreCase("null")) 
									strDisplayContNo = objSessionAcctInfo.getContactNum();
							%><%=strDisplayContNo%>
						</td>
			    	</tr>
	<%-- 	     			  	
  		<% } %> --%>
  		<% if(strInvNo != null && !"".equals(strInvNo)) { %>
   			<tr>
   			        <td colspan="4" style="text-align: center;">Selected Invoice: <a href="#" onClick="javascript:void InvoiceDetailsPDF('<%=strInvNo%>');"><%=strInvNo%></a><%=strInvoiceNoN1%></td>
   				<%-- <td colspan="4" style="text-align: center;">Selected Invoice: <a href="#" onClick="javascript:InvoiceLineDetailsPDF('<%=strInvNo%>');"><%=strInvNo%></a><%=strInvoiceNoN1%></td> --%>
   			</tr>
			<tr><td height="10" colspan="4"></td></tr>
  		<% } %>
  		 </table>   
          </td>
        </tr>
        <% } %>  
  		 <!-- <tr><td height="5" colspan="1"></td></tr> -->
        <tr>
            <td class="tdTableCellStyle" colspan="3"><font class="promptReadOnly">Ticket Details:</font></td>
        </tr>
       <!--  <tr><td height="10" colspan="3"></td></tr> -->
  		<!-- <div style="margin-left:30px;"> Ticket Details</div>  -->
		  <!-- ITG # 176718 Change -->
		   <% objCiSession.setRegionCode(objTicketHeader.getAttribute6()); %>
		<!-- ITG # 176718 Change -->	
		 <tr>
        <!--  <td width="10">&nbsp;</td> -->
         <td colspan="2">
  		<table class="request-service" cellspacing="1">
				<tr>
					<th>Number</th>
					<th>Type</th>
					<th>Status</th>
					<th>Attachments</th>
				</tr>
				<%
						if(iCountHistList == 0) {
					%>
						<tr>
							<td colspan="4" style="text-align: center;">
								<b>Ticket details not found.</b>
							</td>
						</tr>
					<% } else{ %>
					
				<tr>
						<td style="text-align: center;"><font color="#FF0000"><b>*<%= objTicketHeader.getTicketNo()==null?"":objTicketHeader.getTicketNo()%></b></font></td>
						<td style="text-align: center;"><%= objTicketHeader.getCatIdDesc()==null?"":objTicketHeader.getCatIdDesc()%></td>
						<td style="text-align: center;"><%= objTicketHeader.getStatus()==null?"":objTicketHeader.getStatus()%></td>
						
				<%		 //  Newly Added
	          if(s21AttachedFileList1.size()!=0) {  
	 			%>
						
						<td style="text-align: center;">
						 <b><a href="javascript:void(0)" style="color:blue;" onClick="javascript:fnAttachments('<%=objTicketHeader.getTicketNo()%>');">Click Here...</a><b/>
						</td>
						
		<%
	             }//End 
        else {
     %>	
                        <td style="text-align: center;">
						<a href="javascript:void(0)" style="color:black;" onClick="javascript:fnAttachments('<%=objTicketHeader.getTicketNo()%>');">Click Here...</a>
						<!-- <input type="button" disabled="disabled" value="Attachments"> -->
						</td>   	
				<%
	             } 			
				%>
						
						
						
					</tr>		
					<%} %>		
			    </table>
            </td>
        </tr>
         <!-- <tr><td height="5" colspan="3"></td></tr> -->
        <tr>
            <td><font color="#FF0000"><b><%=strTicketSummaryN1%></b></font></td>
        </tr>
        <tr>
            <td class="tdTableCellStyle" colspan="3"><font class="promptReadOnly">Ticket Line Details:</font></td>
        </tr>
        
        <!-- <tr><td height="10" colspan="3"></td></tr> -->
			<%-- <div style="margin-left:30px;"><font color="#FF0000"><b><%=strTicketSummaryN1%></b></font></div>
			<div style="margin-left:30px;"><b>Ticket Line Details</b></div>  --%> 
		</table>
		
		<!--<tr >
		 
            <td colspan="2"> --> 
            <div style="width: 98.5%; overflow: auto; margin-right: 4px; margin-left: 10px;">
			<table cellspacing="1" class="supplies-table">
		 			<tr>
						<th>Line No</th>
						<th>Category</th>
						<th>Status</th>
						<th>Assign to Department</th>
					   	<th>Role</th>
					   	<th>Resource</th>
					   	<th>&nbsp;</th>
					   	<th>&nbsp;</th>
					   	<th>&nbsp;</th>
					   	<th>&nbsp;</th>
                     <!-- ITG # 208045 Changes  -->
                     <th>Line Attributes</th>
                     <!-- ITG # 208045 Changes  -->
		 			</tr>
		 			<%
		 				Canon_E193_TicketLinesObj objTicketLines = null;
		 				String strCatId = "", strLineStatus = "", strChgLine = "";
		 				int countRecords = 0, iCallWrapUp = 0, iLineChange = 0;
		 				ArrayList alLines = (ArrayList)objHeaderLinesList.getArrayList();
		 				System.out.println("########## alLines Size~ "+alLines.size());
		 				if(alLines != null && alLines.size() > 0) {
		 					countRecords = alLines.size();
		 					for(int i=0; i<countRecords; i++) {
		 						objTicketLines = (Canon_E193_TicketLinesObj)alHeader.get(i);
		 						//System.out.println(" ~~objTicketLines~~ "+objTicketLines.toString() + " ILineID: " + objTicketLines.getLineId() );
		 						if(i == 0) strCatId = ""+objTicketLines.getCatId();
		 						else strCatId = strCatId + "|" + ""+objTicketLines.getCatId();
		 						strLineStatus = objTicketLines.getLineStatus()==null?"":objTicketLines.getLineStatus();
		 						strChgLine = "NO";
		 						iLineChange = Integer.parseInt(objTicketLines.getAttribute5());
		 						iCallWrapUp = iCallWrapUp + iLineChange;
                        
                        /* ITG # 208045 Changes  */
                        String sourceApplication = "CUSTOMER_SERVICE";
                        String sourceContext = "CS_E193_" +objTicketHeader.getCatCode() +"_" +objTicketLines.getCatCode();
                        
                        System.out.println("sourceContext!!!!!!!!!!!!!! ticket summary" + sourceContext);
                        int DffCount = (int)objNonBillInfo.getDffCount(sourceContext);
                        System.out.println(" DffCount " + DffCount);
                        /* ITG # 208045 Changes  */

								if(iLineChange == -10) strChgLine = "YES";
		 			%>
								
								
								<input type="hidden" name="lineId<%=i%>" id="lineId<%=i%>" value="<%=String.valueOf(objTicketLines.getLineId())%>">
								<input type="hidden" name="isChangedLine<%=i%>"  id="isChangedLine<%=i%>" value="<%=strChgLine%>">								
								<input type="hidden" name="deptCode<%=i%>" id="deptCode<%=i%>"	value="<%=objTicketLines.getDeptCode()%>">
								<input type="hidden" name="roleId<%=i%>" id="roleId<%=i%>" value="<%=objTicketLines.getRoleId()%>"> 
								<input type="hidden" name="resourceId<%=i%>" id="resourceId<%=i%>"	value="<%=objTicketLines.getResourceId()%>"> 
								<tr class="tdTableCellStyle">
					        		<td style="text-align: center;"><%= String.valueOf(objTicketLines.getLineNo())%></td>
									<td style="text-align: center;"><%= objTicketLines.getCatIdDesc()==null?"":objTicketLines.getCatIdDesc()%></td>
									<td style="text-align: center;"><%= strLineStatus %></td>
									
								    
									<td style="text-align: center;"><input type="text" name="deptName<%=i%>" id="deptName<%=i%>" value="<%= objTicketLines.getDeptName()==null?"":objTicketLines.getDeptName().trim()%>" class="inTxt" readonly=true></td>
									<td style="text-align: center;"><input type="text" name="roleName<%=i%>" id="roleName<%=i%>" value="<%= objTicketLines.getRoleName()==null?"":objTicketLines.getRoleName().trim()%>" class="inTxt" readonly=true></td>
									<td style="text-align: center;"><input type="text" name="resourceName<%=i%>" id="resourceName<%=i%>" value="<%= objTicketLines.getResourceName()==null?"":objTicketLines.getResourceName().trim()%>" class="inTxt" readonly=true></td>
                                 
									
									<% if("UNASSIGNED".equalsIgnoreCase(strLineStatus)){  %>
										<td style="text-align: center;"><a href="javascript:void(0)" onClick="javascript:fnAssignment('<%=i%>', '<%=objTicketLines.getDeptCode()%>','<%=String.valueOf(objTicketLines.getRoleId())%>');">Assign</a></td>
										<td style="text-align: center;"><a href="javascript:void(0)" onClick="javascript:fnReview('<%=objTicketLines.getLineId()%>');">Review</a></td>
										<td style="text-align: center;"><a href="#" onClick="javascript:fnEdit('<%=objTicketLines.getLineId()%>', '<%= objTicketLines.getLineStatus()==null?"":objTicketLines.getLineStatus().trim()%>');">Edit</a></td>
										<td style="text-align: center;"><a href="#" onClick="javascript:fnDelete('<%=objTicketLines.getLineId()%>', '<%= objTicketLines.getLineStatus()==null?"":objTicketLines.getLineStatus().trim()%>');">Delete</a></td>
                              <!-- ITG # 208045 Changes  -->
                              <td><% if (DffCount == 0) { %> <font size=4><b>...</b></font>
                                  <% } else { %> <a href="#" onClick="javascript:OpenDff('<%=sourceApplication%>',  '<%=sourceContext%>', '<%=objTicketHeader.getTicketNo()%>', '<%=objCiSession.getUserId()%>');"><font size=4><b>...</b></font></a>
                                  <% } %>
                              </td>
                              <!-- ITG # 208045 Changes  -->

									<% }else{ %>
										<td style="text-align: center;">Assign</td>
										<td style="text-align: center;"><a href="javascript:void(0)" onClick="javascript:fnReview('<%=objTicketLines.getLineId()%>');">Review</a></td>
										<td style="text-align: center;">Edit</td>
										<td style="text-align: center;">Delete</td>
                              <!-- ITG # 208045 Changes  -->
                              <td style="text-align: center;"><% if (DffCount == 0) { %> <b><u>...</u></b>
                                  <% } else { %> <a href="#" onClick="javascript:OpenDff('<%=sourceApplication%>',  '<%=sourceContext%>', '<%=objTicketHeader.getTicketNo()%>', '<%=objCiSession.getUserId()%>');"><b>...</b></a>
                                  <% } %>
                              </td>
                              <!-- ITG # 208045 Changes  -->
									<% } %>
								</tr>
					<%
							}
						}else{ 
					%>
					<tr class="tdTableCellStyle">
						<td colspan="11" style="text-align: center;">
								<font class="promptReadOnly"><b>Ticket line details not found.</b></font>
						</td>
					</tr>
					<% } %>
	     		</table>
	     		</div>
	     		
	<table class="request-service" style="align:left;" cellpadding="1" cellspacing="0" border="0">
	
   		<!-- <tr><td height="3" colspan="2"></td></tr> -->
		<tr>
			<td colspan="2" class="tableQuestionCell">
	  			<font class="promptReadOnly" color="#FF0000"><b><%=strTicketSummaryN2%></b></font>
	 		</td>
		</tr>				
		<!-- <tr><td height="5" colspan="2"></td></tr> -->
		<%
			String cons_bill_dtls[] = objCiInvInfo.getConsBillNumber(strInvNo,iOrgId);
			if("Y".equals(cons_bill_dtls[0]))
			{
		%>
		<tr>
			<td colspan="2" class="tableQuestionCell">
	  			<font class="promptReadOnly" color="#FF0000"><b>Generate a new Consolidated Bill?</b></font>
				<input type="radio" name="consolidatedBill" <%=selConBillYes%> value="Y" checked><font class="promptReadOnly">Yes</font>
				<input type="radio" name="consolidatedBill"  <%=selConBillNo%> value="N"><font class="promptReadOnly">No</font>
	 		</td>
		</tr>
		<%
			}
		%>
			<!-- <tr><td height="3" colspan="2"></td></tr> -->
   		<tr>
   			<td class="tableQuestionCell" colspan="2">
   				<font class="promptReadOnly" color="#FF0000">
   					<b><%=strTicketSummaryN3%></b>
   				</font>
   			</td>
   		</tr>
	</table>
	
	<div style="float:right; margin-right: 10px;margin-bottom: 5px">	
					<%
						//String strSwitch = request.getParameter("switch")==null?"N":request.getParameter("switch");
						//if("Y".equalsIgnoreCase(strSwitch)) {
					
						if(iCallWrapUp > 0) {
					
					%>
					 <a class="btn_red" style="margin-left: 10px;" href="javascript:action_func3();">Call Wrap-up</a>	
					 									 
					<%  
					
						}else{  %>
					<input name="callWrapUp" id="callWrapUp" type="button" size="15" value="Call Wrap-up" disabled="disabled"/>
					  																											
					<%  
					} %>
					<% boolean isAddIssueDisabled = false;
									if(iIssueCount == countRecords || iCallWrapUp < 0) { 
										isAddIssueDisabled = true;
										System.out.println("~iIssueCount~ "+ isAddIssueDisabled );
								%>
								<input name="addIssue" id="addIssue" type="button" size="15" value="Add Issues" disabled="disabled"/>						
								<% }else{ %>
					    <a class="btn_red" style="margin-left: 10px;" href="javascript:action_func2();">Add Issues</a>									
								<% } %>		
						<% if(iCountHistList == 0) { %>
						<a class="btn_red" href="#">Save</a>			   						
			   				<% 
			   				}else{ 
				   				%>
			   				<a id="saveButton" class="btn_red" style="margin-left: 10px;" href="javascript:action_func1();">Save</a>
			   				<% 
			   				} %>				
	</div>
	<input type="hidden" name="strCatIdP" value="<%=strCatId%>">
	<input type="hidden" name="iCountRecords" value="<%=countRecords%>">
	<input type="hidden" name="disabledAddIssueBtn" value="<%=isAddIssueDisabled%>">
	<div id="dlg"></div>
	<div id="dlg2"></div>
	</form>
		</div>	
		
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>
