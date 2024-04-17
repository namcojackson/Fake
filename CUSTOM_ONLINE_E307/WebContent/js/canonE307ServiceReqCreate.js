
var urlSrUtil  = "canonE307ServiceReqCrUtil.jsp";
var urlCustLov = "canonE307ServiceReqCustLov.jsp";
var urlLocLov  = "canonE307ServiceReqLocLov.jsp";
var urlblCd = "canonE307GetBillCode.jsp";


function createSR(){
  var reqSelector=".required, .requireds";
  emailChk = true;
  $("#eMsg").html("");
  $("#errorWidget").hide();
  var lbrChrgFlg = $("#lbrChrgFlg").val();
  var poReqFlg = $('#pOReqFlg').val();
  if(lbrChrgFlg==null ||lbrChrgFlg=="null" ||lbrChrgFlg==''){
	 var selectedOpt = $("#sBillCode").find('option:selected');
     var lcf = selectedOpt.data('lchrgflg');
     var pcf = selectedOpt.data('pchrgflg');
     var bf = selectedOpt.data('blbleflg');
 	 $("#lbrChrgFlg").val(lcf);
 	 $("#prtChrgFlg").val(pcf);
 	 $("#bllbleFlg").val(bf);	  
  }   
if(!telPhoneValidate()){
	
	if(!validateParams(reqSelector)){
		 setCallPrty('N');
		 var chckDt = fnCheckDt();
		 if(checkEmail($('#emailAddr').val()) || $('#emailAddr').val().toUpperCase().indexOf("REFUSED")==0){
		 	emailChk = true; 
   		 }else{
   			emailChk = false;
   		 }
	   	
	   	 if(!emailChk){
	   		 $("#eMsg").html("Please enter email address in following format - xxxxxx@xxxx.xxx .");
	   		 $('#emailAddr').addClass("error");
	   		 $("#errorWidget").show();
	   	 }else{
			 if(chckDt){
				fnCrValidate('Y');
			 }else{
		//		 alert("Please Select Future Date later than today's Date.");
				 return false; 
			 }
	   	 }
	}
}

}
function telPhoneValidate(){
	var error=false;
	
	 $('#custTelNum').removeClass("error");
	 $('#custTelExtnNum').removeClass("error");
	 $('#cllrPhNum').removeClass("error");
	 $('#cllrPhNumExt').removeClass("error");
	 
	var custTelNum = $('#custTelNum').val();
	var telExtNum = $('#custTelExtnNum').val();
	var telVal = custTelNum+telExtNum;
	if(telVal.length>20){
		 $("#eMsg").html("Telephone Number & Extn combined can't be more than 20 characters.");
   		 $('#custTelNum').addClass("error");
   		 $('#custTelExtnNum').addClass("error");
   		 $("#errorWidget").show();
   		error= true;
	}
	var cllrPhNum = $('#cllrPhNum').val();
	var cllrPhNumExt = $('#cllrPhNumExt').val();
	var cllrTelVal = cllrPhNum+cllrPhNumExt;
	if(cllrTelVal.length>20){
		 $("#eMsg").html("Telephone Number & Extn combined can't be more than 20 characters.");
  		 $('#cllrPhNum').addClass("error");
  		 $('#cllrPhNumExt').addClass("error");
  		 $("#errorWidget").show();
  		error= true;
	}
	return error;
}
function fnCrValidate(callCrtnFlg){
var selectedOpt = $("#sBillCode").find('option:selected');
var poReqFlg = $('#pOReqFlg').val();
var bf = selectedOpt.data('blbleflg');
var termCd = $('#termCd').val();
	if($("#profileId").val().length >0){
		 showPleaseWait();
		$.creditCard("creditCardRequestS21CSA_ASCC.jsp",{
			action:"auth",
			profileId:$("#profileId").val(),
			amount:$("#authAmnt").val(),
			orderID:"CANON"+new Date().getTime()
		}).done(function(data){
			if($.trim(data.approvalStatus)==1){
		    	 $('#txRefNum').val(data.txRefNum);
		    	 showPleaseWait();
		    	 $('#poUpld').val("Credit Card Payment instead of PO.pdf");
				 $("#crSRFlg").val(callCrtnFlg);
				 $('#sNoteType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");
				 $('#sTaskType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");							 
				 $("#csForm").submit();				 			
			}else{
				var termCdVal = $('#termCd').val();
				if($.trim(termCdVal) =='CC'){
					hidePleaseWait();
					 $("#eMsg").html("Credit Card Authorization Failed! - Reason: "+ data.procStatusMessage + " \n - Review Credit Card Information and resubmit.");
					 $("#errorWidget").show();
					return false;
				}else{
					var authdecision= confirm("Credit Card Authorization Failed! - Reason: "+data.procStatusMessage+" \n Click OK to Proceed , Cancel to Retry!");
					if(authdecision == true)
	                {
						showPleaseWait();
				    //	 $('#txRefNum').val(data.txRefNum);
					//	$('#poUpld').val("testPO.pdf");
						$('#txRefNum').val(data.txRefNum);
						 $("#crSRFlg").val(callCrtnFlg);
						 $('#sNoteType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");
						 $('#sTaskType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");
						 $("#csForm").submit();
	               }else{
	            	   hidePleaseWait();
	               	return false;
	               }
				}
			}
		});			 	
	
	}else if(bf =='Y' && termCd=='CC' || termCd=='CO'){
		 $("#eMsg").html("Please enter credit card information");
		 $("#errorWidget").show();
		 return false;
	}else if($.trim(poReqFlg) == 'Y' && bf =='Y'){
		 if(validateCreditPO(bf)){
			 showPleaseWait();
			 $("#crSRFlg").val(callCrtnFlg);
			 $('#sNoteType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");
			 $('#sTaskType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");
			 $("#csForm").submit();	
		 }
	}else{
		 showPleaseWait();
		 $("#crSRFlg").val(callCrtnFlg);
		 $('#sNoteType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");	
		 $('#sTaskType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");
		 $("#csForm").submit();						 
	}
}
/*function creditValid(){
	$("#eMsg").html("");
	var poReqFlg = $('#pOReqFlg').val();
	var termCd = $('#termCd').val();
	var lob = $('#lob').val();
	var custPo = $('#custPo').val();
	var poUpld = $('#poUpld').val();
	if($.trim(lob) == 'ESS' && $.trim(poReqFlg)=='Y' && ($.trim(custPo) == "" ||$.trim(custPo) == 'null')){
		  $("#eMsg").html("Please enter Customer PO Number.");
		  $('#custPo').addClass("error");
		  return false;
	}else if($.trim(poReqFlg)=='Y'){
		if($.trim(custPo) == "" ||$.trim(custPo) == 'null'){
			  $("#eMsg").html("Please enter Customer PO Number.");
			  $('#custPo').addClass("error");
			  return false;
		}else if($.trim(poUpld) == "" ||$.trim(poUpld) == 'null'){
			  $("#eMsg").html("Customer PO Upload is mandatory.");
			  $('#poUpld').addClass("error");	
			  return false;
		}
	}
	return true;
}*/
function callResolved(){
   var reqSelector=".required , .required_r";
   if(!telPhoneValidate()){   
		if(!validateParams(reqSelector)){
			 if(checkEmail($('#emailAddr').val()) || $('#emailAddr').val().toUpperCase().indexOf("REFUSED")==0){
				 	emailChk = true; 
		   		 }else{
		   			emailChk = false;
		   		 }
			   	
			   	 if(!emailChk){
			   		 $("#eMsg").html("Please enter email address in following format - xxxxxx@xxxx.xxx .");
			   		 $('#emailAddr').addClass("error");
			   		 $("#errorWidget").show();
			   	 }else{
					  showPleaseWait();
				 /*	  $("#crSRFlg").val("R");
					  $('#sNoteType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");	 	
					  $('#sTaskType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");
					  $("#csForm").submit();
					  */
					  fnCrValidate("R");
			   	 }
		}
   }
}

function dispatchTech(){
	var reqSelector=".required, .required_dt";
	if(!telPhoneValidate()){
		if(!validateParams(reqSelector)){
			 if(checkEmail($('#emailAddr').val()) || $('#emailAddr').val().toUpperCase().indexOf("REFUSED")==0){
				 	emailChk = true; 
		   		 }else{
		   			emailChk = false;
		   		 }
			   	
			   	 if(!emailChk){
			   		 $("#eMsg").html("Please enter email address in following format - xxxxxx@xxxx.xxx .");
			   		 $('#emailAddr').addClass("error");
			   		 $("#errorWidget").show();
			   	 }else{		
				/*	showPleaseWait();
					$("#crSRFlg").val("DT");
					$('#sNoteType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");		
					$('#sTaskType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");
					$("#csForm").submit(); */
			   		 fnCrValidate("DT");
			   	 }
		}
	}
} 

function needMoreTime(){
	  var reqSelector=".required , .required_nmt";
	  if(!telPhoneValidate()){	  
		if(!validateParams(reqSelector)){
			 if(checkEmail($('#emailAddr').val()) || $('#emailAddr').val().toUpperCase().indexOf("REFUSED")==0){
				 	emailChk = true; 
		   		 }else{
		   			emailChk = false;
		   		 }
			   	
			   	 if(!emailChk){
			   		 $("#eMsg").html("Please enter email address in following format - xxxxxx@xxxx.xxx .");
			   		 $('#emailAddr').addClass("error");
			   		 $("#errorWidget").show();
			   	 }else{		
				  /*showPleaseWait();
					$("#crSRFlg").val("NMT");
					$('#sNoteType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");	
					$('#sTaskType').removeClass("rdl").removeAttr("disabled").removeAttr("readonly");
					$("#csForm").submit(); */
			   		 fnCrValidate("NMT");
			   	 }
		}
	 }
} 

function validateCreditPO(blblFlg){
	var error=false;
	 $("#eMsg").html("");
	 $("#errorWidget").hide();
	 $("#custPo").removeClass("error");
	 $("#poUpld").removeClass("error");
//	 $('#vupLbl').removeClass("error");
	 var custPo = $('#custPo').val();
	 var pOReqFlg =$('#pOReqFlg').val();
	if(pOReqFlg=='Y' && blblFlg=='Y'){
		var loBus = $('#lob').val();
		if(loBus =='PPS' || loBus=='LFS'){
			var poUpload = $('#poUpld').val();
			var pendPo=	$('#pendPo').is(':checked'); 
		//	var chkChekd=	$('#cvuPO').is(':checked'); 
			if(custPo.length < 1 && (!pendPo)){
				 $("#custPo").addClass("error");
				 error=true;
			}
			if(poUpload.length < 1 && (!pendPo)){
				 $("#poUpld").addClass("error");
				 error=true;
			}
		}
		/*else{
			if(custPo.length < 1){
				 $("#custPo").addClass("error");
				 error=true;
			}			
		}*/
	}
	if(error){
		 $("#eMsg").html("Please enter highlighted parameter.");
		 $("#errorWidget").show();
		 return false;
	}else{
		 $("#custPo").removeClass("requiredr");
		 $("#poUpld").removeClass("requiredr");
		// $('#cvuPO').removeClass("requiredr");	
		 return true;
	}
}

function validateParams(reqSelector){
	var error=false;
	var p =[];
	
	var blbFlg = $('#bllbleFlg').val();
	if(blbFlg=='Y'){
		var chkChekd=	$('#ccaChrg').is(':checked'); 
		if(!chkChekd){
			$('#cacLbl').addClass("error");
			 error=true;
			 $("#eMsg").html("Please enter highlighted parameter.");
			 $("#errorWidget").show();			 
		}else{
			$("#eMsg").html("");
		    $('#cacLbl').removeClass('required');
		    $('#cacLbl').removeClass('error');
		}		
	}	

	$(reqSelector).each(function (){
		 p[p.length]=$(this).attr("id"); 
	});
	
	var e =[];
	 $("#eMsg").html("");
	 $("#errorWidget").hide();	
	
	for(k=0;k<p.length;k++){
		$("#"+p[k]).removeClass("error");
		var pVal = $("#"+p[k]).val();
		if ($.trim(pVal).length < 1) {
              error=true;
             e[e.length]=p[k]; 
		}else{
			$("#"+e[k]).removeClass("error");
		}
	}
	
	if(error){
		 for(k=0;k<e.length;k++){
			 $("#"+e[k]).addClass("error");
			 $("#eMsg").html("Please enter highlighted parameter.");
			 $("#errorWidget").show();
		 }
	}
	
	return error;
}	
function fnValidateEmail(ele){
	 $("#eMsg").html("");
	// $(ele).removeClass("error");
	 if(!checkEmail($(ele).val())){
		 $("#eMsg").html("Please enter email address in following format - xxxxxx@xxxx.xxx .");
   		 $("#errorWidget").show(); 
   		 return false;
	  }
	 return true;
}
function checkEmail(emailAddress) {
  var regExp = /(^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)/i;
  return regExp.test(emailAddress);
}
function setProb(sId, source){
	 var model= $("#model").val();
  	 var sPbType= $("#sPbType").val();
	 var sPbDescription= $("#sPbDescription").val();
	 var probDescSelVal = $('#probDescSelVal').val();
	 var probCdSelVal = $('#probCdSelVal').val();
	 var qryStr=""; 
	 var selId="";
	 var pbData ="";
	
	if (sId == "sPbType" ){
		 qryStr ="reqType=pb&returnAttr=problemDesc&mdl="+model+"&type="+sPbType;
		 selId="sPbDescription";
		 $("#sPbCode").html("<option value=''>Select</option>").attr("disabled","disabled").addClass("rdl");
		 $("#sPbCode"+' option:eq(0)').prop('selected', true);
		 
	 } else if(sId == "sPbDescription" ){
		 qryStr ="reqType=pb&returnAttr=problemCode&mdl="+model+"&type="+sPbType+"&desc="+sPbDescription;
		 selId="sPbCode";
	 }	
	if(source=='PBLM' && probDescSelVal!='' && probDescSelVal!='null'){
		$('#probCdSelVal').val('');
		$('#probDescSelVal').val('');
		$('#pCode').val('');
	}
	
	 showPleaseWait();
		$.ajax( {
			url : urlSrUtil,
			cache : false,
			type : "POST",
			data : qryStr,
			success : function(data) {
				hidePleaseWait();
				//console.log("codes: "+ data);
				$("#"+selId).removeAttr("disabled").removeClass("rdl");
				if(selId == 'sPbCode'){
					 if (isIE () && isIE () < 10) {
				    	// alert(" is IE version less than 10");
							$("#"+selId).html(data);
							var optLen = $("#"+selId).children('option').length;
							if(optLen==2){
								 $('#'+selId+' option:eq(1)').prop('selected', true)	
								 $('#'+selId+' option:eq(0)').remove();
								 $('#'+selId).trigger("change");
								 var value = $('#sPbCode option:selected').val();
								 $('#pCode').val(value);
							}else{

								if(probCdSelVal!='' && probCdSelVal!='null'){
									$('#sPbCode'+' option[value="' + probCdSelVal + '"]').prop('selected', true);
									$('#pCode').val(probCdSelVal);
								}
							}
				    	} else {
				    	// alert("is IE 10 and later or not IE");
				    	 pbData = "<input name='pCode' id='pCode' type='text' value='' placeholder='Select' list='sPbCode' class='rdl required' onchange='getRemedy();' autofocus/> " +
				    	 		  " &nbsp;&nbsp;<datalist id='sPbCode' name='sPbCode'  onchange='getRemedy();'>" +
				    	 		  "</datalist>" ; 
				    	 //console.log("pbData: "+pbData);
				    	 $('#pbCdTd').html(pbData);	  
				    	 $("#"+selId).html(data);
							var optLen = $("#"+selId).children('option').length;
							if(optLen==2){
								 $('#'+selId+' option:eq(1)').prop('selected', true)	
								 $('#'+selId+' option:eq(0)').remove();
								// console.log("pbcode: "+ $('#sPbCode').val());
								// var value = $('#sPbCode option:selected').val();
								 var value = $('#sPbCode option:selected').html();
								// console.log("value: " + value);
								 $('#pCode').val(value);
								 $('#'+selId).trigger("change");
				    	}else{
							
							if(probCdSelVal!='' && probCdSelVal!='null'){
								$('#sPbCode'+' option[value="' + probCdSelVal + '"]').prop('selected', true);
								$('#pCode').val(probCdSelVal);
							}
						}	
				   	}
					
				}else{
					$("#"+selId).html(data);
					var optLen = $("#"+selId).children('option').length;
					if(optLen==2){
						 $('#'+selId+' option:eq(1)').prop('selected', true)	
						 $('#'+selId+' option:eq(0)').remove();
						 $('#'+selId).trigger("change");
					}else{
						
						if(probDescSelVal!='' && probDescSelVal!='null'){
							$('#sPbDescription'+' option[value="' + probDescSelVal + '"]').prop('selected', true);
							$('#'+selId).trigger("change");
						}
					}
				}
			
	 	 }
  	});
}

function getCustomer(cId) {
  	   showPleaseWait();		
       var modelName ="#dlg";
   	
       
       var custName = $("#"+cId+"CustName").val();
  	   $(modelName).dialog({
 					height: 500,
 					title: "Customer Name", 
 					modal: true ,
 					zIndex:1005,
 					width: 650,		
 	                resizable: false      
 				});
  	   
  	   
  	   var qryStr="pageNum=1&custName="+encodeURIComponent(custName)+"&cId="+cId+"&sortBy=&sortOrder=";
  	       $.ajax({
 			url: urlCustLov,
 			data:qryStr,
 			type:"POST",
 	        cache: false,
 			success: function(data){     
 					  hidePleaseWait();     
 					  $(modelName).html("");					
 				      $(modelName).html(data);
 				       setRowStyles(".lovTbl");
 				       $("#pgLinks #a1").addClass("pageLinkBg").css({"color":"#FFFFFF","background-color":"#CC0000"});
 				       hidePleaseWait();
 		 	        }             
 		 });
 		  
 	     $( modelName ).dialog("open");
 	     $(".ui-dialog-titlebar").addClass("hd");
 	     $(".ui-dialog").css({"top":"330px"}); 
}


function searchCustomer(pageNum) {

	var modelName = "#dlg";
	var custName = $("#sCustName").val();
	var sAccNum = $("#sAccNum").val();
	var so=$("#sortOrder").val();
	var sb=$("#sortBy").val();
	
	
	custName = encodeURIComponent(custName);
	var cId  = $("#cId").val();
    showPleaseWait();
	var qryStr="pageNum="+pageNum+"&custName="+custName+"&cId="+cId+"&accNum="+sAccNum+"&sortBy="+sb+"&sortOrder="+so;
	 $.ajax({
			url:urlCustLov,
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){     
					  hidePleaseWait();     
					  $(modelName).html("");					
				      $(modelName).html(data);
				       setRowStyles(".lovTbl");
				       $("#pgLinks #a"+pageNum).addClass("pageLinkBg").css({"color":"#FFFFFF","background-color":"#CC0000"});
				       hidePleaseWait();
		 	        }             
	});
}

function sortCustSrch(sortBy){
	$("#sortBy").val(sortBy);
    var so=$("#sortOrder").val();
	if(so=="DESC")
		$("#sortOrder").val("ASC");
	else
		$("#sortOrder").val("DESC");
	searchCustomer("1");
	
}


function getCustomerLoc(sId,locType) {
	
	showPleaseWait();		
    var modelName ="#dlg";
    var addr = $("#"+sId+"Addr").val();
    var accNum = $("#"+sId+"AcctNum").val();
	   $(modelName).dialog({
					height: 500,
					title: "Customer Location", 
					modal: true ,
					zIndex:1005,
					width: 650,		
	                resizable: false      
	          });
	   
	   var qryStr="pageNum=1&sId="+sId+"&accNum="+accNum+"&locType="+locType+"&sortBy=&sortOrder=";
	       $.ajax({
			url: urlLocLov,
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){     
					  hidePleaseWait();     
					  $(modelName).html("");					
				      $(modelName).html(data);
				       setRowStyles(".lovTbl");
				       $("#pgLinks #a1").addClass("pageLinkBg").css({"color":"#FFFFFF","background-color":"#CC0000"});
				       hidePleaseWait();
		 	        }             
		 });
		  
	     $( modelName ).dialog("open");
	     $(".ui-dialog-titlebar").addClass("hd");
	     $(".ui-dialog").css({"top":"330px"});
	     
}


function searchCustomerLoc(pageNum) {
	var modelName = "#dlg";
	var custAccNum = $("#sCustAccNum").val();
	custAccNum = encodeURIComponent(custAccNum);
	var locType = $("#sLocType").val();
	locType = encodeURIComponent(locType);
	var sId  = $("#sId").val();
	var so=$("#sortOrder").val();
	var sb=$("#sortBy").val();
	
	
	var addr = $("#sAddr").val();
	addr = encodeURIComponent(addr);
	
	showPleaseWait();
	var qryStr="pageNum="+pageNum+"&addr="+addr+"&sId="+sId+"&accNum="+custAccNum+"&locType="+locType+"&sortBy="+sb+"&sortOrder="+so;;
	 $.ajax({
			url:urlLocLov,
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){     
					  hidePleaseWait();     
					  $(modelName).html("");					
				      $(modelName).html(data);
				       setRowStyles(".lovTbl");
				       $("#pgLinks #a"+pageNum).addClass("pageLinkBg").css({"color":"#FFFFFF","background-color":"#CC0000"});
				       hidePleaseWait();
		 	        }             
	});
}

function sortLocSrch(sortBy){
	$("#sortBy").val(sortBy);
    var so=$("#sortOrder").val();
	if(so=="DESC")
		$("#sortOrder").val("ASC");
	else
		$("#sortOrder").val("DESC");
	searchCustomerLoc("1");
}

function chkEmail(ele){

	if($(ele).prop('checked')) {
		$('#trEmailInst').show();
		$('#trTrblLnk').show();
	} else {
		$('#trEmailInst').hide();
		$('#trTrblLnk').hide();
	}

}


function setRowStyles(tbl) {
	$(tbl + " tbody tr:odd").each(function() {
		$(this).addClass("oddRow");
	});
}


function emailCustomer(){
	
	var rmd=$("#remedy").val();
	var emailId=$("#emailAddrInst").val();
    var qryStr="reqType=email&rmd="+rmd+"&emailId="+emailId; 
    if(fnValidateEmail($("#emailAddrInst"))){
		showPleaseWait();
		$.ajax( {
			url : urlSrUtil,
			cache : false,
			type : "POST",
			data : qryStr,
			success : function(data) {	
				hidePleaseWait();
				if($.trim(data)=="Y"){
					$("#emailMsg").html("Email Sent").css({"color":"blue","font-weight":"bold","margin-left":"5px"});
				}else{
					$("#emailMsg").html("Error occured while sending email.").css({"color":"red","font-weight":"bold"});
				}
				$("#emailMsg").show();
			}
		});
    }
}
function checkAHS(t){
	showPleaseWait();		
    var modelName ="#dlg";
     $(modelName).dialog({
					height: 400,
					title: t, 
					modal: true ,
					width: 450,		
	                resizable: false
				});
    
     var tblAhsHtml= $("#divAhs").html();
     $(modelName).html("");					
     $(modelName).html(tblAhsHtml);
     $(".ui-dialog").css({"z-index":"10005"});
     $(".ui-dialog-titlebar").css({"background-color":"#003B4E"});
     $(".ui-dialog-titlebar").addClass("hd");
     $(".ui-dialog-titlebar-close").css({"visibility":"hidden"});
     $(".ui-dialog").css({"float":"none"}); 
     $(".ui-dialog-title").css({"float":"none"}); 
}

function selCallType(c){
	closeDlg();
	hidePleaseWait();
	
	if(c=="AHS"){
       // AHS LOGIC	After Hours Call
		// $("#trChngRsn").show();
		// setCallSelects("cr");
		 var model = $('#model').val();
		 var serialNum = $('#serialNumber').val();
		 var machPk = $('#machPk').val();
		 var callTpeCd =  $("#sTaskType").val();
		 var qryStr = "serialNum="+serialNum+"&model="+model;
		 var url ="canonE307GetAHSCallType.jsp";
			$.ajax( {
				url : url,
				cache : false,
				type : "POST",
				data : qryStr,
				async: false,
				success : function(data) {
					result = $.trim(data);
					if(result!='null' && result!=''){
						 var callTpeNm = $.trim(result.split("|")[0]);
						 var callTpe = $.trim(result.split("|")[1]);
						// $('#sBillCode'+' option[value="X"]').prop('selected', true);
						 $('#sTaskType'+' option[value="' + callTpe + '"]').prop('selected', true);
					}else{
						// $('#sBillCode'+' option[value="X"]').prop('selected', true);
						 $('#sTaskType'+' option[value="X1"]').prop('selected', true);
					}
				}
			});	
				 
		fnBillCodeSelVal();
	}else if(c=="NORMAL"){
		showPleaseWait();
		$("#billCdFlg").val('R');
		$("#sBillCode").val('');
		$("#csForm").submit();
	}else if(c=="CANCEL"){
		window.history.back();
	}
	var callTpeCd =  $("#sTaskType").val();
	var lnOfBus = $("#lnOfBsns").val();
	var blblFlg = $('#bllbleFlg').val();
	var lbrChrg = $('#lbrChrg').val();
	var trvlChrg = $('#trvlChrg').val();
	var ahsMsg ='';
	
	if((callTpeCd=='3' || callTpeCd=='X3') && lnOfBus=='ESS' && blblFlg=='Y'){
		var ahsMsg="The labor billing rate is $"+lbrChrg+" / hour. Charge for Labor and Parts Cost. <br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
		
		$('#ahsMsgDiv').html('');
		$('#ahsMsgDiv').html(ahsMsg);
	}else if((callTpeCd=='3'|| callTpeCd=='X3') && blblFlg=='Y'){
		ahsMsg="The labor billing rate is $"+lbrChrg+" / hour.";
	 	if(trvlChrg!=null && trvlChrg.length>0 )
	 		ahsMsg = ahsMsg+ " Charge for Labor, plus a Flat Travel charge not to exceed $"+trvlChrg+" will be added to the cost of service and Parts Cost.";
	 					
	 		ahsMsg = ahsMsg+ "<br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
	 		$('#ahsMsgDiv').html('');
	 		$('#ahsMsgDiv').html(ahsMsg);
	}
}

function closeDlg(){
	var dlgId="#dlg";
	$(dlgId).html("");
	$(dlgId).dialog("close");
	$(dlgId).dialog("destroy");
}

function getRemedy(){
	var ptype = 'E';
//	 $("#eMsg").html("");
	 $('#pCode').removeClass("error");
	// $("#errorWidget").hide();
	 var cFlg = $('#calAvdFlg').val();
	 var oFsr = $('#oFsr').val();
	 if (isIE () && isIE () < 10) {
		 var value = $('#sPbCode option:selected').val();
	 	$('#pCode').val(value);
	 }

	 var pCodeVal=$.trim($("#pCode").val());
	 $("#sPbCode option").each(function()
	  {
       var dOptVal= $(this).val();
       if(pCodeVal==dOptVal){
    	   ptype ='S';
       }else{ 
		   var ec=$.trim(dOptVal.split("-")[0]) ;
		   if(pCodeVal==ec){
		 	   ptype ='S';
		   }
       }
	});
	 if(ptype=='E'){
		 $('#pCode').val('');
		 $("#eMsg").html("Please select problem code from drop down list");
   		 $('#pCode').addClass("error");
   		 $("#errorWidget").show();
	 }else{
		 if(cFlg =='Y' && oFsr!='null' && oFsr.length<=0){
			showPleaseWait();	
			var pbCd = $("#pCode").val();
			var pbCode = $.trim(pbCd.split("-")[0]);
			var model= $("#model").val();	
			var  qryStr ="reqType=pbCd&pbCode="+pbCode+"&mdl="+model;
			$.ajax( {
				url : urlSrUtil,
				cache : false,
				type : "POST",
				data : qryStr,
				success : function(data) {
				  hidePleaseWait();
		 		  var res= jQuery.parseJSON(data);
		 		  
		 		  if((res.docNm=="" || res.docNm=="null") && (res.narrative=="" ||res.narrative=="null")){
		 			 $('.tdBtns').hide();
		 			 $(".callAvd").each(function (){
					   		$(this).addClass("rdl1").attr("disabled","true");
					   		$(this).attr("checked", false);
					  }); 
		 			var editRole = $('#editRole').val();
		 			if(editRole == 'E307_DIS'){
			 			  $('.tdCrSr').show();	 				
		 			 }
		 			  
				  $("#remedy").val('');
			 		  $('#trblLnk').html('');
			 		  $("#narrative").html('');	
				 	  $('#trEmailInst').hide();
					  $('#trTrblLnk').hide();	
			 	//	 $("#emailInst").css({"border":'0px'}); 
					//  $("#emailInst").css('border', '0px');
					/*  $("#emailInst").css('border-width', '0');
					  $(this).css({"border-color": "#C1E0FF", 
				             "border-weight":"1px", 
				             "border-style":"solid"});*/
	
		 		  }else{
			 			 alert("This call is eligible for phone fix");	 			  
			 			 $(".callAvd").each(function (){
						   		$(this).removeClass("rdl1").removeAttr("disabled");
						   		$(this).attr("checked", false);
						  }); 
	
			 		  $('.tdCrSr').hide(); 			  
			 		  $("#remedy").val(res.docNm);
			 		  $('#trblLnk').html(res.remedy);
			 		  $("#narrative").html(res.narrative);	 			  
		 		  }
	
		 	    }
			});
		}
	 }
	//enblNotes();
}

function  enblNotes(){
	var tId="#sNoteTypeTxt";
	var sId="#sNoteType";
	var sPbId="#sPbCode";
  //  var chkChecked=	$('#cOther').is(':checked'); 
	
	if(checkNull(sPbId)){
		 $(tId).removeClass("rdl").removeAttr("readonly");
	   //  $(sId).removeClass("rdl").removeAttr("disabled");
	}/*else{
		$(tId).addClass("rdl").attr("readonly","readonly").val("");
	    $(sId).addClass("rdl").attr("disabled","true");
	}*/
}
function fnBillCodeSelVal(){
	var selectedOpt = $("#sBillCode").find('option:selected');
    var lcf = selectedOpt.data('lchrgflg');
    var pcf = selectedOpt.data('pchrgflg');
    var bf = selectedOpt.data('blbleflg');
 	$("#lbrChrgFlg").val(lcf);
 	$("#prtChrgFlg").val(pcf);
 	$("#bllbleFlg").val(bf);
 	fnDisblEnblCrdt(bf);
 	var poReqVal = $('#pOReqFlg').val();
	var lnBsns = $('#lob').val();
 	if(poReqVal=='Y' && bf=='Y'){
		if(lnBsns!='ESS'){
			$('#custPo').addClass("requiredr");
			$('#poUpld').addClass("requiredr");			
		}
 	}else{
		$('#custPo').removeClass("requiredr");
		$('#poUpld').removeClass("requiredr");
 	}
}
function fnBillCode(){
	var billCdVal = $("#sBillCode").val();
	var origBilCd = $('#origBilCd').val();
	fnBillCodeSelVal();
 	if(billCdVal!=origBilCd){
 		$("#trChngRsn").show();
 		$("#sChngReason").addClass("required"); 
 	}else{
 		$("#trChngRsn").hide();
 		$('#sChngReason').removeClass("required");
 	}

 	setCallSelects("cr"); // change reason
 //	getRemedy();
 //	fnDisblEnblCrdt(bf);
 }

function fnDisblEnblCrdt(optn){
	if(optn=='Y'){
		 $('#profileId').val('');
		 $('#holderName').val('');
		 $('#authAmnt').val('');

		$('#holderName').removeClass("rdl").removeAttr("readonly");
		$('#holderName').removeClass("rdl").removeAttr("disabled");
		$('#authAmnt').removeClass("rdl").removeAttr("readonly");
		$('#authAmnt').removeClass("rdl").removeAttr("disabled");
		$('#crdtBtn').show();
		 $("#cacLbl").addClass("required");
	}else{
		$('#holderName').addClass("rdl").attr("readonly","readonly");
	    $('#holderName').addClass("rdl").attr("disabled","true");
		$('#authAmnt').addClass("rdl").attr("readonly","readonly");
	    $('#authAmnt').addClass("rdl").attr("disabled","true"); 
	    $('#crdtBtn').hide();
	    $('#cacLbl').removeClass('required');
	    $('#cacLbl').removeClass('error');
	}
}


function checkNull(s){
	var str="";
	str= $(s).val();
	if($.trim(str).length>0)
	return true;
	else 
	return false;
}


function setCallSelects(s){
	showPleaseWait();
    var eo="";
    var sc="";
    var wc="";
    var optTxt="";
    var optVal="";
    var sId="";
    
    var le="ListEntry";
	
    if(s=="cc"){
      sId="#sCrChannel";
      eo="channelLookup";	
      sc="creationChannel;creationCode";
      optTxt="creationChannel";
      optVal="creationCode";
    }else if(s=="tt"){
      sId="#sTaskType";
      eo="taskType";	
      sc="typeName;typeCode;typePrtyCd";
      wc="taskASCCFlag:Y"
      optTxt="typeName";
      optVal="typeCode";	 
    }else if(s=="cr"){
        sId="#sChngReason";
        eo="changeReason";
        sc="changeReasonCd;changeReasonname";
        optVal="changeReasonCd";
        optTxt="changeReasonname";
     }else if(s=="bc"){
    	 sId="#sBillCode";
         eo="billCode";	
         sc="billCodename;billCodeid;lbrChrgFlg;prtChrgFlg;billbleFlg";
         optTxt="billCodename";
         optVal="billCodeid";
     }else if(s=="nt"){
    	 sId="#sNoteType";
    	 sc="noteTypename;noteTypeCd;ntASCCDefFlg";
         eo="noteType";	
         wc="ntASCCSelFlg:Y"
         optTxt="noteTypename";
         optVal="noteTypeCd";
     }    
 	
	var defaultVal='';
	var noteDefVal="";
	var cAvoidVal =  $('#corrCodeSel').val();
	if(cAvoidVal!=null && cAvoidVal!="" && cAvoidVal!="null"){
		if(s=="crc"){
			defaultVal = cAvoidVal;
		}else if(s=='rc'){
			defaultVal = $('#resCodeSel').val();
		}else if(s=='lc'){
			defaultVal = $('#locCodeSel').val();			
		}
		
	}else{
		defaultVal= $(sId).val();
	}
		
	$(sId).html("");
	var qryStr="reqType=xml&eo="+eo+"&sc="+sc+"&wc="+wc;
	$.ajax({
	    url:urlSrUtil,
	    data:qryStr,
	    type:"POST",
	    success: function(data){
	           $xml = $( $.parseXML( data ) );
	             if(s=="nt" || s=="crc" || s=="rc" || s=="lc") 
	            	 $(sId).append($("<option></option>").attr("value","").text("Select"));
	             
                 if(s=="cr")
                	 $(sId).append($("<option></option>").attr("value","").text("Select a Reason"));
                 
	             $xml.find(le).each(function(){
	            	 
                         var ot = $(this).find(optTxt).text();
                         var ol = $(this).find(optVal).text();
                         var dataCallPrty="";
                         var lbrChrgFlag="";
                         var prtChrgFlag="";
                         var billbleFlag ="";
                         if(s=="tt") {
                        	var ol =  $(this).find(optVal).text();
                        	var cp= $(this).find("typePrtyCd").text();
                        	 ot=ol+"-"+ot;
                        	 dataCallPrty=" data-callPrty='"+cp+"' ";
                         }
                         if(s=="nt"){
                        	 var ntDefVal= $(this).find("ntASCCDefFlg").text();
                        	// console.log(" Note defVal : "+ ntDefVal +  ot + ol);
                        	 if(ntDefVal=="Y"){
                        		 defaultVal=ol;
                        		// console.log("note val : "+ ot + ol+" defaultVal : "+defaultVal);
                        	 }
                         }
                         if(s=="bc"){
                        	var ol =  $(this).find(optVal).text();
                        	var lc= $(this).find("lbrChrgFlg").text();
                        	var pc= $(this).find("prtChrgFlg").text();
                        	var bf= $(this).find("billbleFlg").text();
                        	ot=ol+"-"+ot;
                        	lbrChrgFlag=" data-lChrgFlg='"+lc+"' ";  
                        	prtChrgFlag=" data-pChrgFlg='"+pc+"' "; 
                        	billbleFlag=" data-blbleFlg='"+bf+"' "; 
                        	//console.log("defaultVal : "+defaultVal+" ol: "+ol);
                        	if(defaultVal!=null && defaultVal!="null"){
                        		if(ol == defaultVal){
                        		 	 $("#lbrChrgFlg").val(lc);
                        		 	 $("#prtChrgFlg").val(pc);
                        		 	 $("#bllbleFlg").val(bf);
                        		 	fnDisblEnblCrdt(bf);
                        		}
                        	}
                        	//console.log("defVal : " +defaultVal+" ol :"+ ol +" ot: "+ot);
                         }

                         $(sId).append($("<option "+dataCallPrty+"></option>").attr("value",ol).text(ot));
	             });
	             
/*	             var eolCallTp = $('#eolCallTp').val();
	             
	         	 if(eolCallTp == "DE_INSTALL"){
	         		 $('#sTaskType'+' option[value="F"]').prop('selected', true);
	         	 }
	    	 	*/
	    	

	             if(s=="cr"){
	 	            var billVal = $('#sBillCode').val();
	 	            if(billVal == 'X' || billVal == 'X3' ||billVal == 'X1'){
		            	 defaultVal = "40";//"After Hours Call";	 	            	
	 	            }
	 	           var chngBlRsn=$("#chngBlRsn").val();
	 	           if(chngBlRsn!='' && chngBlRsn!='null'){
	 	        	  defaultVal = chngBlRsn;
	 	           }
	 	            
	             }
	             $(sId+' option[value="' + defaultVal + '"]').prop('selected', true);

	             hidePleaseWait();
	            
	    },
	    error: function(data){
	       // console.log('Error loading XML data : '+sId);
	        hidePleaseWait() ;
	    }
	});
	
	
}

function setCallPrty(selVal){
	if(selVal == 'Y'){
		var machPk = $('#machPk').val();
		var callTpeCd =  $("#sTaskType").val();
		var result = fnGetBillCode(callTpeCd, machPk, 'N');	
		fnBillCodeSelVal();
		$("#trChngRsn").hide();
 		$('#sChngReason').removeClass("required");
	}

	var selectedOpt = $("#sTaskType").find('option:selected');
    var dt = selectedOpt.data('callprty');
 	$("#callPrtyCd").val(dt);
/*	var selectedOpt = $("#sBillCode").find('option:selected');
    var bf = selectedOpt.data('blbleflg');
    var pOReqFlg = $('#pOReqFlg').val();
 	var lnOfBsns = $('#lob').val();
 	if(pOReqFlg =="Y" && bf=='Y'){
 	 	if(lnOfBsns!='ESS'){
 	 		 $('#custPo').addClass('requiredr');
 	 		 $('#poUpld').addClass('requiredr');
 	 	}
 	}else{
 	 	 $('#custPo').removeClass('requiredr');
 	 	 $('#poUpld').removeClass('requiredr');
 	}*/
 	
}

function fnGetBillCode(callTpCd, machPk, callType ){
	var result="";
	if(machPk.length != 0){
		var userId = $('#userId').val();
		var urlDetail = 'canonE307GetBillCode.jsp';
		var qryStr= "machPk="+machPk;
		var qryStr= qryStr+"&callTpCd="+callTpCd+"&userId="+userId+"&callType="+callType;

			
		showPleaseWait();
	    $.ajax({
				url: urlDetail,
				cache: false,
				data : qryStr,
				async: false,
				success: function(data){
					hidePleaseWait();
					result = $.trim(data);
					 $('#sBillCode'+' option[value="' + result + '"]').prop('selected', true);
				}
			});			
	}
	return result;
}


$(function (){

     $(".datePicker").datepicker({
		 dateFormat: 'M dd yy',
		 changeMonth: true,
		 changeYear: true
	 });
/*	 $(".timePicker").timepicker({
		 timeFormat: 'HH:mm',
		 interval: 15
	 });*/
	 
	 setCallSelects("cc");// CreationChannel
	 setCallSelects("tt"); // task type
	   
	  
	 $("#trChngRsn").hide();
	 
	 $(".rdl").each(function (){
		 
		  var ele=$(this);
		  var tp = $(ele).attr("type");
		  if(tp=="text")
			  $(ele).addClass("rdl").attr("readonly","readonly"); 
		  else   
			  $(ele).addClass("rdl").attr("disabled","true");	  
		  
	 });
	 
	 $('input[type="text"]').each(function (){
		 $(this).addClass("inTxt");	  
	 });
	
	/* 
	 $(".hd").each(function (){
		    var h=$(this).html();
		   if( !( h=="Installed At:" || h=="Bill To:") )
		    $(this).css({"background":" #003b4e none repeat scroll 0 0","color":" #ffffff","font-weight":" bold","padding":" 4px","text-align":" center" });
		              
	 });
   */

     $('#sNoteTypeTxt').keyup(function() {
         var len = this.value.length;
         if (len >= 500) {
             this.value = this.value.substring(0, 500);
         }
     });
     $('#ccaChrg').click(function(){
    	 $('#cacLbl').removeClass('required');
      })
 
});	


function parseDate(dt) {
	  var months = {jan:0,feb:1,mar:2,apr:3,may:4,jun:5,
	                jul:6,aug:7,sep:8,oct:9,nov:10,dec:11};
	  var p = dt.split(' ');
	  return new Date(p[2], months[p[0].toLowerCase()], p[1]);
}
function fnCheckDt(){
	$("#eMsg").html("");
	 var dt = $("#ftrSrvDt").val();
	 var stMin = $('#ftrSrvMn').val();
	 var nwDt =  parseDate(dt);
	 var newHr = convertDateTo24Hour();
	 nwDt.setHours(newHr,stMin,0,0);
	 
	 var d = new Date(); 
	 var hor =d.getHours(); 
	 var min = d.getMinutes();
	 d.setHours(hor,min,0,0);
	 var day = d.getDate();
	 months = new Array('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec');
     curMonth = months[d.getMonth()];
	 var year = d.getFullYear();
	 var curDate = curMonth+" "+day+" "+year;

	 var ftrSysDate =  new Date($('#ftrSysDt').val());
	 
   if (nwDt < ftrSysDate) { 
		  $("#eMsg").html("Date & Time must be equal to or greater than current date.");
		  $("#errorWidget").show();
		//  $("#ftrSrvDt").val(curDate);
		  return false;
	  }else{
		  return true;
	  }
}
function convertDateTo24Hour(){
	    var stHour = $('#ftrSrvHr').val();
	    var stAmPm = $('#ftrAmPm').val();
	    
	    var newhr = 0;
	    var ampm = '';
	    var newtime = '';
	    
	    if (stAmPm=='PM')
	    { 
	        if (stHour!=12)
	        {
	            stHour=stHour*1+12;
	        }
	       
	    }else if(stAmPm=='AM' && stHour=='12'){
	       stHour = stHour -12;
	    }else{
	        stHour=stHour;
	    }
	    return stHour;
	}
function getSysDate(){
	 var sd = new Date(); 
	 var hor = sd.getHours(); 
	 var min =  sd.getMinutes();
	// console.log("hor : "+ hor+" min : "+min);
	 sd.setHours(hor,min,0,0);
	 $('#ftrSysDt').val(sd);
}
function isIE () {
	  var myNav = navigator.userAgent.toLowerCase();
	  return (myNav.indexOf('msie') != -1) ? parseInt(myNav.split('msie')[1]) : false;
	}
function fnChngPoSts(){
	var billableFlg = $('#bllbleFlg').val();
	var pOReqFlg = $('#pOReqFlg').val();
	if(billableFlg =='Y' && pOReqFlg =='Y'){
		var chkChekd = $('#pendPo').is(':checked'); 
		
		if(!chkChekd){
			 $("#custPo").addClass("requiredr");
			 $("#poUpld").addClass("requiredr");	
		}else{
			 $("#custPo").removeClass("requiredr");
			 $("#poUpld").removeClass("requiredr");		
		}		
	}


}
/*function getBillCodePO(){
	
	var acctNum=$("#bAcctNum").val();
    var qryStr="billToAcctNum="+acctNum; 
    if(acctNum!=null && acctNum!=""){
		showPleaseWait();
		$.ajax( {
			url : urlPoReq,
			cache : false,
			type : "GET",
			data : qryStr,
			success : function(data) {	
				hidePleaseWait();
				debugger;
				if($.trim(data)!=null){
					$("#pOReqFlg").val(data);
				}
			}
		});
    }
}*/