<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.canon.apps.servreq.dao.*" %>  
<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="canon.apps.common.CanonCustomProfile"%>

<%
	String pageTitle="Search";
	
	ArrayList<String> menuList = new ArrayList<String>();
	 menuList.add("sp:btn:Scratch Pad:opnSCPad();");
%>
<%@ include file="canonE307ServReqHeader.jsp" %> 
<script type="text/javascript" src="<%=ctxPath%>/common/jquery/jquery.scrolltable.js"></script>

  <%
  CanonE307ServiceRequestSearchDao objDet = new CanonE307ServiceRequestSearchDao();
  CanonE307ServiceRequestDetailsDao srDetObj = new CanonE307ServiceRequestDetailsDao();
	
  String strAction = request.getParameter("Action");
  String sortBy = "";
  String sortOrder = "asc";  
  if(request.getParameter("sortBy")!=null){
	sortBy = request.getParameter("sortBy");
  }
  if(request.getParameter("sortOrder")!=null){
	sortOrder = request.getParameter("sortOrder");
  }	
  System.out.println("strAction : "+ strAction);
  List machLst = new ArrayList();
//  if("search".equals(strAction)){
	  String strSerialNumber = request.getParameter("serialTagNumber");
	  
	  System.out.println("strAction : "+ strAction+" strSerialNumber: "+strSerialNumber+" Sort By: "+request.getParameter("sortBy")+" Sort Order: "+request.getParameter("sortOrder"));
	  
	  String strSolName = request.getParameter("solutionName")==null?"": request.getParameter("solutionName");
	  String strAccNum = request.getParameter("accountNumber")==null?"":request.getParameter("accountNumber");
	  String strCustName = request.getParameter("customerName")==null?"": request.getParameter("customerName");
	  String strCustAddress = request.getParameter("custAddress")==null?"":request.getParameter("custAddress");
	  String strCustCity = request.getParameter("custCity")==null?"":request.getParameter("custCity");
	  String strCustState = request.getParameter("custState")==null?"":request.getParameter("custState");
	  String strCustPostalCd = request.getParameter("custPostalCode")==null?"":request.getParameter("custPostalCode");
	  String strphoneNum = request.getParameter("custPhoneNumber")==null?"":request.getParameter("custPhoneNumber");
	  String strSortBy = request.getParameter("sortBy")==null?"":request.getParameter("sortBy");
	  String strSortOrd = request.getParameter("sortOrder")==null?"":request.getParameter("sortOrder");	  
//  }
 %>
  <div id="main_content">
	<div id="page_top">
		<h1>Advanced Service Call Center</h1>
	</div>

	<div class="table-inner">
		<div id="errorWidget"  style="display: none;color:red;padding-top: 5px;font-size: 15">
	 	<p id="eMsg"></p>
		</div>	
	 	<form id="machSearchFrm" name="machSearchFrm" action="canonE307MachineAndCustomerSearch.jsp" method="post">
	  		<input type='hidden' name='sortOrder' id='sortOrder' value="<%=sortOrder%>" />
	  		<input type='hidden' name='sortBy' id='sortBy' value="<%=sortBy%>" />
	  		<input type="hidden" name="scratchPad" id="scratchPad" value=""> 
			<input type="hidden" name="srvcMsgSrl" id="srvcMsgSrl" value="">
			<input type="hidden" name="srvcMsgMdl" id="srvcMsgMdl" value="">
			<input type="hidden" name="srvcMsgMachPk" id="srvcMsgMachPk" value="">
			<input type="hidden" name="srvcMsgSelDt" id="srvcMsgSelDt" value="">	  	
			<input type="hidden" name="crtSrFlg" id="crtSrFlg" value="">	
			<input type="hidden" name="callTp" id="callTp" value="">	
			<input type="hidden" name="pageNumber" id="pageNumber" value="">
	  		<div class="service">
				<table id="srInfTbl" cellspacing="5">
						<tr>
							<th colspan=2>Existing Service Request</th>
						</tr>
						<tr>
							<td><input type="text" id="servRqstNumber" name="servRqstNumber" placeholder="Service Request#" value="<%=util.checkNull(request.getParameter("servRqstNumber"))%>"></td>
							<td><input type="text" id="createdBy" name="createdBy" placeholder="Created By"></td>
						</tr>
						<tr>
						<td>
							<%
							ArrayList srStsLst = (ArrayList)srDetObj.getSRSts();
							%>
								<select id="srStatus" name="srStatus" style="width:166px;margin: 0px 5px 0px 5px !important;">
								 <option value="" selected>SR Status</option>
								<%
								
								if(srStsLst!=null && srStsLst.size()>0){
									for(int i=0;i<srStsLst.size();i++){
										CanonE307SRLovRec srStsObj = (CanonE307SRLovRec)srStsLst.get(i);
								%>
									<option value="<%=srStsObj.getStrVal() %>"><%=srStsObj.getStrValDesc() %></option>
								<%
									}
								}
								 %>
								 </select> 
						</td>
						<td>
					<%
						ArrayList stsTskLst = (ArrayList)srDetObj.getTaskSts();
					%>
						<select id="taskSts" name="taskSts" style="width:166px;margin: 0px 5px 0px 5px !important;">
						 <option value="" selected>Task Status</option>
						<%
						
						if(stsTskLst!=null && stsTskLst.size()>0){
							for(int i=0;i<stsTskLst.size();i++){
								CanonE307SRLovRec tskStsObj = (CanonE307SRLovRec)stsTskLst.get(i);
						%>
							<option value="<%=tskStsObj.getStrVal() %>"><%=tskStsObj.getStrValDesc() %></option>
						<%
							}
						}
						 %>
						 </select> 
					</td>				
					</tr>
						<tr>
							<td><input type="text" id="taskNumber" name="taskNumber" placeholder="Task#" value="<%=util.checkNull(request.getParameter("taskNumber"))%>"></td>
							<td nowrap><input type="text" id="creationDt" name="creationDt" placeholder="Creation Date" class="datePicker" ></td>
						</tr>
						<tr>
					<td>
					<% 
						ArrayList arTskTpeLst = objDet.getTaskTypeLovVal();
					%>
						<select id="taskType" name="taskType" style="width:166px;margin: 0px 5px 0px 5px !important;">
							 <option value="" selected>Task Type</option>
							<%
							if(arTskTpeLst!=null && arTskTpeLst.size()>0){
								for(int i=0;i<arTskTpeLst.size();i++){
									CanonE307SRLovRec tskTypeObj = (CanonE307SRLovRec)arTskTpeLst.get(i);
							%>
								<option value="<%=tskTypeObj.getStrVal() %>"><%=tskTypeObj.getStrValDesc() %></option>
							<%
								}
							}
							 %>
						 </select> 
					</td>
					<td>&nbsp;</td>
					</tr>
						<tr>
							
							<td style="text-align: right;" colspan=2>
								<a href="javascript:fnFsrTskSearch()" class="btn">Search</a>
								<a href="javascript:fnClearAll()" class="btn">Clear</a>
							</td>
						</tr>
					</table>
					<table id="machInfTbl" cellspacing="5">
						<tr>
							<th>Machine Information</th>
							<th colspan="3">Customer Information</th>
						</tr>
						<tr align="center">
							<td width=""><input type="text" id="serialTagNumber" name="serialTagNumber" placeholder="Serial/Tag#" value="<%=util.checkNull(request.getParameter("serialTagNumber"))%>"></td>
							<td width="">
								<input type="text" name="accountNumber" id="accountNumber" placeholder="Account#" value="<%=util.checkNull(request.getParameter("accountNumber"))%>">
								<input type="text" id="customerName" name="customerName" placeholder="Customer Name" value="<%=util.checkNull(request.getParameter("customerName"))%>">
								<input type="text" id="custPhoneNumber" name="custPhoneNumber" placeholder="Phone#" value="<%=util.checkNull(request.getParameter("custPhoneNumber"))%>">
							</td>
						</tr>
						<tr align="center">
							<td><input type="text" id="solutionName" name="solutionName" placeholder="Solution Name" value="<%=util.checkNull(request.getParameter("solutionName"))%>"></td>
							<td>
								<input type="text" id="custAddress" name="custAddress" placeholder="Address" value="<%=util.checkNull(request.getParameter("custAddress"))%>">
								<input type="text" id="custCity" name="custCity" placeholder="City" value="<%=util.checkNull(request.getParameter("custCity"))%>">
 								<input type="text" id="custState" name="custState" placeholder="State" style="width: 54px;" value="<%=util.checkNull(request.getParameter("custState"))%>"> 
		 						<input type="text" id="custPostalCode" name="custPostalCode" maxlength="12" placeholder="Postal Code" style="width: 80px;" value="<%=util.checkNull(request.getParameter("custPostalCode"))%>"> 
							</td>
						</tr>
						<tr valign="top" align="right">
							<td colspan="2" style="text-align: right;">
								<a href="javascript:fnSubmitForm()" class="btn">Search</a> 
								<a href="javascript:fnClearCust()" class="btn">Clear</a>
							</td>
						</tr>
					</table>
					<div><h1>Search Results</h1></div>
				</div>
		</form>
		</div>
	 	<div id="resultsDiv">
  			<jsp:include page="canonE307MachAndCustmrSrchIncude.jsp">
		    <jsp:param value="<%=strSerialNumber %>" name="serialTagNumber"/>
		    <jsp:param value="<%=strSolName %>" name="solutionName"/>
			<jsp:param value="<%=strAccNum %>" name="accountNumber"/>    
			<jsp:param value="<%=strCustName %>" name="customerName"/> 
		    <jsp:param value="<%=strCustAddress %>" name="custAddress"/>
		    <jsp:param value="<%=strCustCity %>" name="custCity"/>
			<jsp:param value="<%=strCustState %>" name="custState"/>    
			<jsp:param value="<%=strCustPostalCd %>" name="custPostalCode"/> 	
			<jsp:param value="<%=strphoneNum %>" name="custPhoneNumber"/>
		    <jsp:param value="<%=strSortBy %>" name="sortBy"/>
			<jsp:param value="<%=strSortOrd %>" name="sortOrder"/>
			<jsp:param value="1" name="pageNumber"/>        
		    </jsp:include>	 
		</div>

		<table>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
		</table>
	</div>
<div id="eolMsgDiv" style="display: none;">
		<table id="tblEol" style="width: 430x;font-weight: bold;">

 			<tr>
 				<td style="align:center;" nowrap><input type="text" name="eolTpNm" id="eolTpNm" value="EOL - No Service"  class="rdl" > </td>
 				<td style="align:center;" nowrap><input type="text" name="eolEndDt" id="eolEndDt" value="" class="rdl"> </td>
 			<tr>
 			<tr>
 				<td colspan=2><textarea name='dispComments' id="dispComments"  rows='3' cols="40" style="height: 80px;font-size: 12pt" readonly>This machine is End of Life and is no longer eligible for service. Instruct customer to call sales.</textarea></td>
 			</tr>
 			<tr><td colspan=2>&nbsp;&nbsp;</td></tr>
 			<tr>		
 				<td align="center" colspan=2>
					<a class="btn" href="javascript:void fnCloseSearchDlg('eolDlg')">OK</a>
				</td>
 			</tr>		 				 			 			
 		</table>
</div>	
<div id="srvcMsgDiv" style="display: none;">
		<table id="tblsrvc" style="width: 430x;font-weight: bold;">

 			<tr>
 				<td style="align:center;" id="srvcMsgNm"> </td>
 			<tr>
 			<tr>
 				<td style="align:center;" >&nbsp; </td>
 			<tr> 			
  			<tr>		
 				<td align="center" colspan=2>
					<a class="btn" href="javascript:void fnCloseMsgDlg('eolDlg')">OK</a>
				</td>
 			</tr>					 			 			
 		</table>
</div>	
<div id="eolDlg"></div>
<script>
$(function(){ 
    $(".datePicker").datepicker({
		 dateFormat: 'M dd yy',
		 changeMonth: true,
		 changeYear: true
	 });
	$('#searchResultTbl').scrolltable({ 
		setWidths: false,
		stripe: false,
		maxHeight: 300 
	});
});	
$(document).ready(function() {
 $("#a1").css({"color":"white","background-color":"#A10304"});
});

function fnSubmitForm(){
	var searchVal = "false";
//	 if(navigator.userAgent.indexOf('MSIE') > -1){
			$("#machInfTbl input").each(function() {
			    var input = $(this);
			    if (input.val() == input.attr('placeholder')) {
			      input.val('');
			    }
			});
//		 }		
	$("#machInfTbl input").each(function() {
	      if(this.value!='null' && $.trim(this.value).length>0){
	    	  searchVal="true";
	      }
	  });
/*	$("#custSearchDiv input").each(function() {
	      if(this.value!='null' && $.trim(this.value).length>0){
	    	  searchVal="true";
	      }
	  });	*/
	if(searchVal=='false'){
		$('#eMsg').html("Please enter atleast one value for search criteria.");
		$('#errorWidget').show();
	    if (!$.support.placeholder) {
	    	$('[placeholder]').focus(function() {
	    		  var input = $(this);
	    		  if (input.val() == input.attr('placeholder')) {
	    		    input.val('');
	    		    input.removeClass('placeholder');
	    		  }
	    		}).blur(function() {
	    		  var input = $(this);
	    		  if (input.val() == '' || input.val() == input.attr('placeholder')) {
	    		    input.addClass('placeholder');
	    		    input.val(input.attr('placeholder'));
	    		  }
	    		}).blur();
	        }
	}else{
		showPleaseWait();
		var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307MachineAndCustomerSearch.jsp?Action=search";
		document.machSearchFrm.action = urlDetail;
		document.machSearchFrm.submit();		
	}
}
function fnGetSerialEol(serialNum){
	var result="Y";
	if(serialNum.length != 0){
		
		var urlDetail = 'canonE307GetSerialEol.jsp';
		var qryStr= "serialNum="+serialNum;

		
		showPleaseWait();
	    $.ajax({
				url: urlDetail,
				cache: false,
				data : qryStr,
				async: false,
				//type : "POST",			
				success: function(data){
					hidePleaseWait();
					result = $.trim(data);
				}
			});			
	}
	return result;
}
function fnClearAll(){
	$("#srInfTbl input").each(function() {
	      this.value = "";
		  var input = $(this);
		  if (input.val() == '' || input.val() == input.attr('placeholder')) {
		    input.addClass('placeholder');
		    input.val(input.attr('placeholder'));
		  }
	  });
	$('select option:nth-child(1)').prop("selected", true);
/* $('input[type=text]').each(function(){
	     $(this).val('');
	  }); */
}
function fnClearCust(){
	$("#machInfTbl input").each(function() {
	      this.value = "";
		  var input = $(this);
		  if (input.val() == '' || input.val() == input.attr('placeholder')) {
		    input.addClass('placeholder');
		    input.val(input.attr('placeholder'));	
		  }
	  });
}
function emailMngrNotif(serialNumber, machPk){
	
    var qryStr="reqType=notif&serialNumber="+serialNumber+"&machPk="+machPk; 
		$.ajax( {
			url : "canonE307ServiceReqCrUtil.jsp",
			cache : false,
			type : "POST",
			data : qryStr,
			success : function(data) {
				//alert('retVal '+$.trim(data));
			}
		});
}
function fnSelectCreateSR(serialNum, machPk, model, crtSrFlg, hardHoldFlg, crossSrvcFlg){
	if(crossSrvcFlg=='Y'){
		alert('This is an Oracle Cross Service machine. It can only be created in Oracle.');
	}else{
		$('#srvcMsgMachPk').val(machPk);
		if(hardHoldFlg=='Y'){
			var afterHrsFlg = fnCheckAfterHoursCall(machPk, model, serialNum);
			//alert("Unable to create call at this time.  A service manager will call you shortly to discuss the issue");
			//emailMngrNotif(serialNum);
			if(afterHrsFlg){
				
				$('#eMsg').html('');
					var selDate= "";
					var urlDet = 'canonE307UGWLockSrlCheck.jsp';
					var qryStr= "serialNumber="+serialNum;
						qryStr =qryStr+"&userId="+'<%=loginUser%>';
						showPleaseWait();
					  $.ajax({
							url: urlDet,
							cache: false,
							data : qryStr,
							async : false,
							success: function(data){ 
							    hidePleaseWait();
							    ugwLockout = $.trim(data);
								//console.log("ugwLockout: "+ ugwLockout);
							    if(ugwLockout=='N' || ugwLockout=='null'|| ugwLockout==''){
							    	
							    	if(crtSrFlg=='Y'){
										$('#crtSrFlg').val(crtSrFlg);
										$('#srvcMsgSrl').val(serialNum);
										$('#srvcMsgMdl').val(model);
										//$('#srvcMsgMachPk').val(machPk);
										$('#srvcMsgSelDt').val(selDate);
										fnGetEolMsgs(serialNum, machPk);
									}else{
										
										var urlDetail = 'canonE307ServReqSrvcMsgs.jsp';
										var qryStr= "serialTagNumber="+serialNum+"&machPk="+machPk;
										showPleaseWait();
									    $.ajax({
												url: urlDetail,
												cache: false,
												data : qryStr,
											//	type : "POST",			
												success: function(data){
													hidePleaseWait();
													result = $.trim(data);
													if(result!=null && result!="null" && result!=''){
														//alert("srvc Msgs: "+result);
														$('#srvcMsgSrl').val(serialNum);
														$('#srvcMsgMdl').val(model);
														//$('#srvcMsgMachPk').val(machPk);
														$('#srvcMsgSelDt').val(selDate);
														fnGetSrvcMsg(result);
													}else{
														var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqCreate.jsp?serialNumber="+encodeURIComponent(serialNum)+"&model="+encodeURIComponent(model);
														<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
															urlDetail="<%="https://"+ request.getServerName()%>"+urlDetail;
														<% } %>
														urlDetail +="&machPk="+machPk;
														urlDetail +="&selDate="+selDate;
														document.machSearchFrm.action = urlDetail;
														document.machSearchFrm.submit();								
													}
												}
									    });
										
									}				    	
							    }else{
							    	$('#eMsg').html("This serial number is being worked upon by : "+ugwLockout);
									 $("#errorWidget").show();						    	
							    }
							 }
						 });				
			}
		}else{
			
			$('#eMsg').html('');
				var selDate= "";
				var urlDet = 'canonE307UGWLockSrlCheck.jsp';
				var qryStr= "serialNumber="+serialNum;
					qryStr =qryStr+"&userId="+'<%=loginUser%>';
					showPleaseWait();
				  $.ajax({
						url: urlDet,
						cache: false,
						data : qryStr,
						async : false,
						success: function(data){ 
						    hidePleaseWait();
						    ugwLockout = $.trim(data);
							//console.log("ugwLockout: "+ ugwLockout);
						    if(ugwLockout=='N' || ugwLockout=='null'|| ugwLockout==''){
						    	
						    	if(crtSrFlg=='Y'){
									$('#crtSrFlg').val(crtSrFlg);
									$('#srvcMsgSrl').val(serialNum);
									$('#srvcMsgMdl').val(model);
									$('#srvcMsgMachPk').val(machPk);
									$('#srvcMsgSelDt').val(selDate);
									fnGetEolMsgs(serialNum, machPk);
								}else{
									
									var urlDetail = 'canonE307ServReqSrvcMsgs.jsp';
									var qryStr= "serialTagNumber="+serialNum+"&machPk="+machPk;
									showPleaseWait();
								    $.ajax({
											url: urlDetail,
											cache: false,
											data : qryStr,
										//	type : "POST",			
											success: function(data){
												hidePleaseWait();
												result = $.trim(data);
												if(result!=null && result!="null" && result!=''){
													//alert("srvc Msgs: "+result);
													$('#srvcMsgSrl').val(serialNum);
													$('#srvcMsgMdl').val(model);
													$('#srvcMsgMachPk').val(machPk);
													$('#srvcMsgSelDt').val(selDate);
													fnGetSrvcMsg(result);
												}else{
													var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqCreate.jsp?serialNumber="+encodeURIComponent(serialNum)+"&model="+encodeURIComponent(model);
													<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
														urlDetail="<%="https://"+ request.getServerName()%>"+urlDetail;
													<% } %>
													urlDetail +="&machPk="+machPk;
													urlDetail +="&selDate="+selDate;
													document.machSearchFrm.action = urlDetail;
													document.machSearchFrm.submit();								
												}
											}
								    });
									
								}				    	
						    }else{
						    	$('#eMsg').html("This serial number is being worked upon by : "+ugwLockout);
								 $("#errorWidget").show();						    	
						    }
						 }
					 });
	
			}	
	}
}

function autoTab(current,next){
	if (current.getAttribute&&current.value.length==current.getAttribute("maxlength"))
		next.focus();
}
$(".numeric").keypress(function (e) {
	if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
     	return false;
    }
});
function fnSortFilterCriteria(sortBy, pageNum){
	var existingSortOrder = $("#sortOrder").val();
	var existingSortBy = $("#sortBy").val();   
	
	if(sortBy==existingSortBy){
	  if(existingSortOrder=='asc'){
	    existingSortOrder = 'desc';
	  }else{
	    existingSortOrder = 'asc'
	  }
	  $("#sortOrder").val(existingSortOrder);
	}else{
	  existingSortOrder ='asc';     
	   $("#sortOrder").val(existingSortOrder);
	   $("#sortBy").val(sortBy);
	}
	fnGetMachSrchRes(pageNum);
}
$('input[type="text"]').each(function (){
	 $(this).addClass("inTxt");	  
});
function fnGetMachSrchRes(pageNum){
	$("#machInfTbl input").each(function() {
	    var input = $(this);
	    if (input.val() == input.attr('placeholder')) {
	      input.val('');
	    }
	});
	$('#pageNumber').val(pageNum);
	var srlTagNum = $('#serialTagNumber').val();
	var solnm = $('#solutionName').val();
	var acctNum = $('#accountNumber').val();
	var custAddress = $('#custAddress').val();
	
	var custPhNum = $('#custPhoneNumber').val();
	var custNm = $('#customerName').val();
	var custCity = $('#custCity').val();
	var custState = $('#custState').val();	
	var custPostalCd = $('#custPostalCode').val();	
	var sortBy = $('#sortBy').val();
	var sortOrder = $('#sortOrder').val();

	/*var qryStr="serialTagNumber="+encodeURIComponent(srlTagNum)+"&pageNumber="+pageNum;
	qryStr = qryStr+"&solutionName="+solnm+"&accountNumber="+acctNum;
	qryStr = qryStr+"&custAddress="+custAddress+"&custPhoneNumber="+custPhNum;
	qryStr = qryStr+"&customerName="+custNm+"&custCity="+custCity;
	qryStr = qryStr+"&custState="+custState+"&custPostalCode="+custPostalCd;	
	qryStr = qryStr+"&sortBy="+sortBy+"&sortOrder="+sortOrder;	*/
	
	var qryStr=$('#machSearchFrm').serialize();
	showPleaseWait();
	 $.ajax({
			url:"canonE307MachAndCustmrSrchIncude.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){  
			hidePleaseWait();
			document.getElementById("resultsDiv").innerHTML = $.trim(data);
				$("#pagination #a"+pageNum).css({"color":"white","background-color":"#A10304"});
			    if (!$.support.placeholder) {
			    	$('[placeholder]').focus(function() {
			    		  var input = $(this);
			    		  if (input.val() == input.attr('placeholder')) {
			    		    input.val('');
			    		    input.removeClass('placeholder');
			    		  }
			    		}).blur(function() {
			    		  var input = $(this);
			    		  if (input.val() == '' || input.val() == input.attr('placeholder')) {
			    		    input.addClass('placeholder');
			    		    input.val(input.attr('placeholder'));
			    		  }
			    		}).blur();
			        }					
		 	}             
	});
}
function fnFsrTskSearch(){
	$('#eMsg').html('');
	$('#errorWidget').hide();
	// if(navigator.userAgent.indexOf('MSIE') > -1){
		 $("#srInfTbl input").each(function() {
		    var input = $(this);
		    if (input.val() == input.attr('placeholder')) {
		      input.val('');
		    }
		 });
	// }
	var servReqNum = $('#servRqstNumber').val();
	var tskNum = $('#taskNumber').val();
	var createdBy = $('#createdBy').val();
	var creationDt = $('#creationDt').val();
	var srStatus = $('#srStatus').val();
	var taskSts = $('#taskSts').val();
	var taskType = $('#taskType').val();
	if(servReqNum.length != 0 || tskNum.length != 0 || createdBy.length != 0 || creationDt.length != 0 || srStatus.length != 0 || taskSts.length != 0 || taskType.length!=0){
		var urlDetail = 'canonE307GetSRCount.jsp';
	/*	var qryStr= "servReqNum="+encodeURIComponent(servReqNum)+"&tskNum="+encodeURIComponent(tskNum);
		qryStr = qryStr+ "&createdBy="+encodeURIComponent(createdBy)+"&creationDt="+encodeURIComponent(creationDt);
		qryStr = qryStr+ "&taskSts="+encodeURIComponent(taskSts)+"&srStatus="+encodeURIComponent(srStatus); */
	//	$('#machSearchFrm #scratchPad').val( $("#toolTip textarea").val());
	
	    var qryStr=$('#machSearchFrm').serialize();
		showPleaseWait();
		var recCount=0;
	    $.ajax({
				url: urlDetail,
				cache: false,
				data : qryStr,
				type : "POST",			
				success: function(data){
					result = $.trim(data);
					if(result!='' && result>1){
						hidePleaseWait();
						 var url = '<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?showVend=Y&fsr='+encodeURIComponent(result); 
						   // url += "&taskNum=" +encodeURIComponent(tskNum);
						    var l_newWindow = window.open(url);
						    l_newWindow.focus();  
					}else{
						$('#pageNumber').val('1');
						/* var url = '<%=ctxPath%>/e307/jsp/canonE307ServiceReqHistory.jsp?fsr='+encodeURIComponent(servReqNum); 
						    url += "&taskNum=" +encodeURIComponent(tskNum);
						    url += "&createdBy=" +encodeURIComponent(createdBy);
						    url += "&creationDt=" +encodeURIComponent(creationDt);
						    url += "&srStatus=" +encodeURIComponent(srStatus);
						    url += "&taskSts=" +encodeURIComponent(taskSts); */
						    var url = '<%=ctxPath%>/e307/jsp/canonE307ServiceReqHistory.jsp';
							document.machSearchFrm.action = url;
							document.machSearchFrm.submit();								
						}
				}
			});			
	}else{
		$('#eMsg').html('Please enter atleast one value for search criteria.');
		$('#errorWidget').show();
	    if (!$.support.placeholder) {
	    	$('[placeholder]').focus(function() {
	    		  var input = $(this);
	    		  if (input.val() == input.attr('placeholder')) {
	    		    input.val('');
	    		    input.removeClass('placeholder');
	    		  }
	    		}).blur(function() {
	    		  var input = $(this);
	    		  if (input.val() == '' || input.val() == input.attr('placeholder')) {
	    		    input.addClass('placeholder');
	    		    input.val(input.attr('placeholder'));
	    		  }
	    		}).blur();
	        }		
	}
}
$(document).keypress(function(e) {
	 var searchVal="false";
	 var srSrchVal = "false";
//	 $('input').on("focus", function(){
	  if(e.which == 13) {
		// if(navigator.userAgent.indexOf('MSIE') > -1){
			 $("#machInfTbl input").each(function() {
			    var input = $(this);
			    if (input.val() == input.attr('placeholder')) {
			      input.val('');
			    }
			 });
		// }		  
		$("#machInfTbl input").each(function() {
		      if(this.value!='null' && $.trim(this.value).length>0){
		    	  searchVal='true';
		      }
		  });
		if(searchVal=='true'){
	  		fnSubmitForm();
		}
		if(searchVal=="false"){
			// if(navigator.userAgent.indexOf('MSIE') > -1){
				 $("#srInfTbl input").each(function() {
				    var input = $(this);
				    if (input.val() == input.attr('placeholder')) {
				      input.val('');
				    }
				 });
			// }		 			
			$("#srInfTbl input").each(function() {
			      if(this.value!='null' && $.trim(this.value).length>0){
			    	  srSrchVal="true";
			      }
			  });
			if(srSrchVal=='true'){
				fnFsrTskSearch();
			}
		}
	  }
//	});

}); 
$(function(){    
    //if(navigator.userAgent.indexOf('MSIE') > -1){
    if (!$.support.placeholder) {
	$('[placeholder]').focus(function() {
		  var input = $(this);
		  if (input.val() == input.attr('placeholder')) {
		    input.val('');
		    input.removeClass('placeholder');
		  }
		}).blur(function() {
		  var input = $(this);
		  if (input.val() == '' || input.val() == input.attr('placeholder')) {
		    input.addClass('placeholder');
		    input.val(input.attr('placeholder'));
		  }
		}).blur();
    }
 });

 function fnCloseSearchDlg(dId){
	var dlgId="#"+dId;
	$(dlgId).html("");
	$(dlgId).dialog("close");
	$(dlgId).dialog("destroy");
	var srFlg = $('#crtSrFlg').val();
	if (srFlg=='Y'){
		
		var serialNum = $('#srvcMsgSrl').val();
		var model = $('#srvcMsgMdl').val();
		var machPk = $('#srvcMsgMachPk').val();
		var selDate = $('#srvcMsgSelDt').val();
		
		var urlDetail = 'canonE307ServReqSrvcMsgs.jsp';
		var qryStr= "serialTagNumber="+serialNum+"&machPk="+machPk;
		showPleaseWait();
	    $.ajax({
				url: urlDetail,
				cache: false,
				data : qryStr,
			//	type : "POST",			
				success: function(data){
					hidePleaseWait();
					result = $.trim(data);
					if(result!=null && result!="null" && result!=''){
						fnGetSrvcMsg(result);
					}else{
						var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqCreate.jsp?serialNumber="+encodeURIComponent(serialNum)+"&model="+encodeURIComponent(model);
						<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
							urlDetail="<%="https://"+ request.getServerName()%>"+urlDetail;
						<% } %>
						urlDetail +="&machPk="+machPk;
						urlDetail +="&selDate="+selDate;
						document.machSearchFrm.action = urlDetail;
						document.machSearchFrm.submit();								
					}		
				}
	    });
	}
 } 
 function fnGetEolMsg(eolEndDt, dispComments){
		//showPleaseWait();

		var modelName ="#eolDlg";
	     $(modelName).dialog({
						height: 280,
						title: 'Machine is End of Life', 
						modal: true ,
						width: 400,		
		                resizable: false
					});
	    
	     var tblEolHtml= $("#eolMsgDiv").html();
	     $(modelName).html("");					
	     $(modelName).html(tblEolHtml);
	     $(".ui-dialog").css({"z-index":"10005"});
	     $(".ui-dialog-titlebar").css({"background-color":"#003B4E"});
	     $(".ui-dialog-titlebar").addClass("hd");
	     $(".ui-dialog-titlebar-close").css({"visibility":"hidden"});
	     $(".ui-dialog").css({"float":"none"}); 
	     $(".ui-dialog-title").css({"float":"none"}); 
	     $('#eolDlg #eolEndDt').val(eolEndDt);
	     $('#eolDlg #dispComments').html(dispComments);
	} 
 function fnGetSrvcMsg(srvcMsg){
		//console.log(" srvcMsg: "+srvcMsg);

	    var modelName ="#eolDlg";
	     $(modelName).dialog({
						height: 350,
						title: 'Service Message Center', 
						modal: true ,
						width: 500,		
		                resizable: false
					});
	    
	     var tblEolHtml= $("#srvcMsgDiv").html();
	     //console.log('tblEolHtml: '+tblEolHtml);
	     $(modelName).html("");					
	     $(modelName).html(tblEolHtml);
	     $(".ui-dialog").css({"z-index":"10005"});
	     $(".ui-dialog-titlebar").css({"background-color":"#003B4E"});
	     $(".ui-dialog-titlebar").addClass("hd");
	     $(".ui-dialog-titlebar-close").css({"visibility":"hidden"});
	     $(".ui-dialog").css({"float":"none"}); 
	     $(".ui-dialog-title").css({"float":"none"}); 
	     $('#eolDlg #srvcMsgNm').html(srvcMsg);

	} 
 function fnCloseMsgDlg(dId){
		var dlgId="#"+dId;
		$(dlgId).html("");
		$(dlgId).dialog("close");
		$(dlgId).dialog("destroy");
		
		var serialNum = $('#srvcMsgSrl').val();
		var model =  $('#srvcMsgMdl').val();
		var machPk = $('#srvcMsgMachPk').val();
		var selDate = $('#srvcMsgSelDt').val();
		var callTp = $('#callTp').val();
	
		var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqCreate.jsp?serialNumber="+encodeURIComponent(serialNum)+"&model="+encodeURIComponent(model);
		<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
			urlDetail="<%="https://"+ request.getServerName()%>"+urlDetail;
		<% } %>
		urlDetail +="&machPk="+machPk;
		urlDetail +="&selDate="+selDate;
		urlDetail +="&callTp="+callTp;
		document.machSearchFrm.action = urlDetail;
		document.machSearchFrm.submit();								
	}  
 	function fnGetEolMsgs(serialNumber, svcMachMstrPk){
	  var modelName = 'eolDlg';

	  var urlDetail = 'canonE307GetSrvcEolMsgs.jsp?serialNumber='+serialNumber+'&svcMachMstrPk='+svcMachMstrPk;
	  	   
			modelName = "#"+modelName;
	   showPleaseWait();
	   $(modelName).dialog({
					height: 500,
					title: "End of Life Control Dates",
					modal: true ,
			autoOpen :false,
					 width: 750,		
			 resizable: false
				}); 
	   
		  $.ajax({
					url: urlDetail,
			cache: false,
					success: function(data){ 
					    hidePleaseWait();
					   $(modelName).html("");       
					   $(modelName).html(data);
					}             
				});
		  $(modelName).dialog("open");
		//  $(".ui-dialog").css({"top":"150px"});
		  $(".ui-dialog-titlebar").addClass("hd"); 	 
	} 
 	 function fnCloseDlg(dId, serialNumber){
 		var modelName="#"+dId;
 		$(modelName).html("");
 	    $(modelName).dialog("close");
 	    $(modelName).dialog("destroy");

  	}
 	function fnCreateCall(dId, serialNumber, callTp){
	 	var modelName="#"+dId;
	 	$(modelName).html("");
	     $(modelName).dialog("close");
	     $(modelName).dialog("destroy");
	     $('#callTp').val(callTp);

			var serialNum = $('#srvcMsgSrl').val();
			var model = $('#srvcMsgMdl').val();
			var machPk = $('#srvcMsgMachPk').val();
			var selDate = $('#srvcMsgSelDt').val();
			
			var urlDetail = 'canonE307ServReqSrvcMsgs.jsp';
			var qryStr= "serialTagNumber="+serialNum+"&machPk="+machPk;
			showPleaseWait();
		    $.ajax({
					url: urlDetail,
					cache: false,
					data : qryStr,
				//	type : "POST",			
					success: function(data){
						hidePleaseWait();
						result = $.trim(data);
						if(result!=null && result!="null" && result!=''){
							fnGetSrvcMsg(result);
						}else{
							var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqCreate.jsp?serialNumber="+encodeURIComponent(serialNum)+"&model="+encodeURIComponent(model);
							<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
								urlDetail="<%="https://"+ request.getServerName()%>"+urlDetail;
							<% } %>
							urlDetail +="&machPk="+machPk;
							urlDetail +="&selDate="+selDate;
							urlDetail +="&callTp="+callTp;
							document.machSearchFrm.action = urlDetail;
							document.machSearchFrm.submit();								
						}		
					}
		    });
	 	
 	}
 	function fnCheckAfterHoursCall(machPk, model, serialNumber){
 	    var qryStr="&serialNumber="+serialNumber+"&machPk="+machPk+"&model="+model; 
 	     qryStr =qryStr+"&userId="+'<%=loginUser%>';
 	     var bAfterHrsFlg = true;
 		$.ajax( {
 			url : "canonE307AfterHoursFlag.jsp",
 			cache : false,
 			async : false,
 			type : "POST",
 			data : qryStr,
 			dataType: "text",
 			success : function(data) {
 				if($.trim(data)=="Y"){
 					alert("Your account is undergoing maintenance at this time.  A Service Manager will be contacting you shortly.");
 					emailMngrNotif(serialNumber, machPk);
 					bAfterHrsFlg =  false;
 				}
 			}
 		});
 		return bAfterHrsFlg;
 	} 	
 	function fnCheckZip() {
 		var zipCode = $('#custPostalCode').val();
 	    var zipVal = (/(^\d{5}$)|(^\d{5}-\d{4}$)/).test(zipCode);
 	    return zipVal;
 	} 	
/* $(document).keypress(function(e) {
	  if(e.which == 13) {
		  fnSubmitForm();
	  }
	});  */
</script>

	<div id="dlg"></div>
	<div id="eolMsgDiv"></div>
	</body>
</html>
