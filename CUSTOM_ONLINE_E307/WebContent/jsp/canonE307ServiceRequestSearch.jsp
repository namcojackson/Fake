
<%@ page import="com.canon.apps.servreq.dao.*" %>  
<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="canon.apps.common.CanonCustomProfile"%>

<%
//	final int _pc = pageContext.REQUEST_SCOPE;
//	pageContext.setAttribute("_pageTitle", "Search", _pc);

	String pageTitle="Search";
	ArrayList<String> menuList = new ArrayList<String>();
	 menuList.add("MENU4:N:IWR Errors:fnUgwDtlsView();");
	 menuList.add("MENU2:N:Future Calls:fnFutureCallsView();");
	 menuList.add("MENU3:N:3rd Party Calls:fn3rdPartyCallsView();");
//	 menuList.add("navSearch:menuToggleSearch:Search:N");
	 menuList.add("sp:btn:Scratch Pad:opnSCPad();");
%>


<%@ include file="canonE307ServReqHeader.jsp" %> 
 <%
    String strAction = request.getParameter("Action");
	CanonE307ServiceRequestSearchDao objDet = new CanonE307ServiceRequestSearchDao();
	CanonE307ServiceRequestDetailsDao srDetObj = new CanonE307ServiceRequestDetailsDao();
	String sortBy = "";
	String sortOrder = "asc";  
	if(request.getParameter("sortBy")!=null){
		sortBy = request.getParameter("sortBy"); 
	}
	if(request.getParameter("sortOrder")!=null){
		sortOrder = request.getParameter("sortOrder");
	}	
	String fSortBy = "";
	String fSortOrder = "asc";  
	if(request.getParameter("fSortBy")!=null){
		fSortBy = request.getParameter("fSortBy"); 
	}
	if(request.getParameter("fSortOrder")!=null){
		fSortOrder = request.getParameter("fSortOrder");
	}	
	String vSortBy = "";
	String vSortOrder = "asc";  
	if(request.getParameter("vSortBy")!=null){
		vSortBy = request.getParameter("vSortBy"); 
	}
	if(request.getParameter("vSortOrder")!=null){
		vSortOrder = request.getParameter("vSortOrder");
	}		
  String strSerialNumber = request.getParameter("serialTagNumber");
  String fCallDivDis =request.getParameter("fCallDiv")==null?"":request.getParameter("fCallDiv");
  String vendCallDiv = request.getParameter("vCallDiv")==null?"":request.getParameter("vCallDiv");
  System.out.println(" strSerialNumber: "+strSerialNumber);
  %>
<div id ="main_content">
   		<div id="page_top">
			<h1>Advanced Service Call Center</h1>
		</div>
<div class="table-inner">
    <form id="searchFrm" name="searchFrm" action="canonE307ServiceRequestSearch.jsp" method="post">
	<input type='hidden' name='sortOrder' id='sortOrder' value="<%=sortOrder%>" />
	<input type='hidden' name='sortBy' id='sortBy' value="<%=sortBy%>" />
	<input type='hidden' name='fSortOrder' id='fSortOrder' value="<%=fSortOrder%>" />
	<input type='hidden' name='fSortBy' id='fSortBy' value="<%=fSortBy%>" />	
	<input type='hidden' name='vSortOrder' id='vSortOrder' value="<%=vSortOrder%>" />
	<input type='hidden' name='vSortBy' id='vSortBy' value="<%=vSortBy%>" />	
	<input type="hidden" name="fCallDiv" id="fCallDiv" value="<%=fCallDivDis%>">	
	<input type="hidden" name="vCallDiv" id="vCallDiv" value="<%=vendCallDiv%>">
	<input type="hidden" name="scratchPad" id="scratchPad" value=""> 
	<input type="hidden" name="srvcMsgSrl" id="srvcMsgSrl" value="">
	<input type="hidden" name="srvcMsgMdl" id="srvcMsgMdl" value="">
	<input type="hidden" name="srvcMsgMachPk" id="srvcMsgMachPk" value="">
	<input type="hidden" name="srvcMsgSelDt" id="srvcMsgSelDt" value="">
	<input type="hidden" name="srCrtFlg" id="srCrtFlg" value="">
	<input type="hidden" name="srvcMsgs" id="srvcMsgs" value="">
	<input type="hidden" name="callTp" id="callTp" value="">
	
	
	<div id="errorWidget"  style="display: none;color:red;padding-top: 5px;font-size: 15">
 		<p id="eMsg"></p>
	</div>	
	
	<div class="service" style="width:100%">
			<table id="srInfTbl" cellspacing="5">
					<tr>
						<th colspan=2>Existing Service Request</th>
					</tr>
					<tr>
						<td><input type="text" id="servRqstNumber" name="servRqstNumber" placeholder="Service Request#"></td>
					
						<td><input type="text" id="createdBy" name="createdBy" placeholder="Created By"></td>
					</tr>
					<tr>
						<td>
							<%
							ArrayList srStsLst = (ArrayList)srDetObj.getSRSts();
							%>
								<select id="srStatus" name="srStatus" style="width:155px;margin: 0px 5px 0px 5px !important;">
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
						<select id="taskSts" name="taskSts" style="width:155px;margin: 0px 5px 0px 5px !important;">
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
						<td><input type="text" id="taskNumber" name="taskNumber" placeholder="Task#"></td>
				 		
						<td nowrap><input type="text" id="creationDt" name="creationDt" placeholder="Creation Date" class="datePicker" ></td>
					</tr>
					<tr>
					<td>
					<% 
						ArrayList arTskTpeLst = objDet.getTaskTypeLovVal();
					%>
						<select id="taskType" name="taskType" style="width:155px;margin: 0px 5px 0px 5px !important;">
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
					<tr valign="top" align="right">
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
						<td width=""><input type="text" id="serialTagNumber" name="serialTagNumber" placeholder="Serial/Tag#"></td>
						<td width="">
							<input type="text" name="accountNumber" id="accountNumber" placeholder="Account#" />
							<input type="text" id="customerName" name="customerName" placeholder="Customer Name" />
							<input type="text" id="custPhoneNumber" name="custPhoneNumber" placeholder="Phone#" />
						</td>
					</tr>
					<tr align="center">
						<td><input type="text" id="solutionName" name="solutionName" placeholder="Solution Name"></td>
						<td>
							<input type="text" id="custAddress" name="custAddress" placeholder="Address">
							<input type="text" id="custCity" name="custCity" onchange='fnCheckZip()' placeholder="City">
							<input type="text" id="custState" name="custState" placeholder="State" style="width: 54px;">
							<input type="text" id="custPostalCode" name="custPostalCode" maxlength="12" placeholder="Postal Code" style="width: 80px;">
						</td>
					</tr>
					<tr valign="top" align="right">
						<td colspan="2" style="text-align: right;">
							<a href="javascript:fnSubmitForm()" class="btn">Search</a> 
							<a href="javascript:fnClearCust()" class="btn">Clear</a>
						</td>
					</tr>
				</table>
				<div><h1>Home</h1></div>
			</div>
<!-- 		<table class="service">
			<tr><td style="font-size: 20px;font-weight: bold;" align="left"><h2>Home</h2></td>
		</tr> -->
		
		</table>					
	</form>
	
	
</div>
	<%
	    String strUgwDisp="display: none;";
	//    if("yes".equals(fCallDivDis)){
	//    	strUgwDisp ="";
	//    }
		String strCustName = request.getParameter("imgCustomerName");
		String strSrlTagNum = request.getParameter("imgSerialTagNumber");
		String strModel = request.getParameter("imgModel");
		String strBranch = request.getParameter("imgBranchName");
		System.out.println("strCustName : " + strCustName + " strSrlTagNum : " + strSrlTagNum+" strModel: "+strModel +" strBranch : "+strBranch);
	%>
<!-- 		 	<div class="table-inner">
			 	<table width="100%" id="remotePrbtable" cellspacing="0">
					<tr>
						<td><a href="canonE307WasteContainerSearch.jsp" style="font-size: 16px;font-weight: bold;">Waste Toner Container Order Entry</a></td>
					</tr>
				</table>
			</div>  -->
			<div id="hieghtDiv" class="rmtProbDesc"><br />
				<div class="table-inner">
				<table width="100%" id="remotePrbtable" cellspacing="0">
	 		<!--	<tr>
					<td><a href="canonE307WasteContainerOrderEntry.jsp" style="font-size: 16px;font-weight: bold;">Waste Toner Container Order Entry</a></td>
				</tr>  -->
					<tr>
						<td width="20%" class="sectionHeaderBlack">IWR Errors</td>
						<td width="80%" align="right"><b>Filter By:</b>
							<input type="text" value="" id="imgCustomerName" name="imgCustomerName" size=20 placeholder="Customer Name" onchange="fnGetUGWDtls(1)" style="font-size: 11px;">
							<input type="text" name="imgBranchName" id="imgBranchName" value="" size="15" onchange="fnGetUGWDtls(1)" placeholder="Branch" style="font-size: 11px;">
							<input type="text" value="" id="imgModel" name="imgModel" size=10 placeholder="Model" onchange="fnGetUGWDtls(1)" style="font-size: 11px;">
						</td>
					</tr>
				</table>
			</div>
			<div id="imgProbDiv">
		  			<jsp:include page="canonE307UGWErrTblInclude.jsp">
				    <jsp:param value="<%=strCustName %>" name="imgCustomerName"/>
				    <jsp:param value="<%=strSrlTagNum %>" name="imgSerialTagNumber"/>
				    <jsp:param value="<%=strModel %>" name="imgModel"/>
				    <jsp:param value="<%=strBranch %>" name="imgBranchName"/>
					<jsp:param value="<%=sortBy %>" name="sortBy"/>    
					<jsp:param value="<%=sortOrder %>" name="sortOrder"/> 
				    </jsp:include>
		  </div>
	  </div>
	  <br>
	  <%
		  int fStart=1;
		  int fEnd=20;
		  String strFtrBranch=request.getParameter("ftrBrnch");
		  String strFtrDate=request.getParameter("ftrDate");
		  String strFtrCustmr = request.getParameter("ftrCustmr");
/*		  Object[] obj = objDet.getFutureCalls(fStart, fEnd, fSortBy, fSortOrder,strFtrBranch, strFtrDate, strFtrCustmr);
		  int totalRecords = 0;
		  if(obj[0]!=null){
		  	totalRecords = ((Integer)obj[0]).intValue();
		  }
		  ArrayList<CanonE307ServReqFutureCallRec> arFtrList = new ArrayList<CanonE307ServReqFutureCallRec>();
		  if(obj[1]!=null){
		  	  arFtrList = (ArrayList<CanonE307ServReqFutureCallRec>) obj[1];
		  }	*/	  
		  String strfDisp="display: none;";
		  if("yes".equals(fCallDivDis)){
		  	strfDisp ="";
		  }
			String strStyle="align:center;";
		//	if(arFtrList!=null && arFtrList.size()>5){
		//		strStyle="height: 300px;  overflow-y:auto;align:center;";
		//	}		  
		%>		  
		  <div id="futureCallDiv" style="<%=strfDisp%>">
			<div class="table-inner">
				<table width="100%" id="ftrCalltable" cellspacing="0" >
					<tr>
						<td width="20%" class="sectionHeaderBlack">Future Calls</td>
						<td width="80%" align="right"><b>Filter By:</b>
							<input type="text" value="" id="ftrCustmr" name="ftrCustmr" size=20 placeholder="Customer Name" onchange="fnGetFtrDtls(1)" style="font-size: 11px;">
							<input type="text" name="ftrBrnch" id="ftrBrnch" value="" size="15" onchange="fnGetFtrDtls(1)" placeholder="Branch" style="font-size: 11px;">
							<input type="text" name="ftrDate" id="ftrDate" value="" class="datePicker" placeholder="Future Date" size=10 onchange="fnGetFtrDtls(1)" style="font-size: 11px;">
						</td>
					</tr>
				</table>
			</div>
			 <div class="table-inner" id="fDiv">		  
		  			<jsp:include page="canonE307FtrCallsTblInclude.jsp">
				    <jsp:param value="<%=strFtrBranch %>" name="ftrBrnch"/>
				    <jsp:param value="<%=strFtrDate %>" name="ftrDate"/>
				    <jsp:param value="<%=strFtrCustmr %>" name="ftrCustmr"/>
					<jsp:param value="<%=fSortBy %>" name="fSortBy"/>    
					<jsp:param value="<%=fSortOrder %>" name="fSortOrder"/> 
				    </jsp:include>	  
		 	 </div>
	  	<br>
		<br>
	  </div>
		<%
		  int vStart=1;
		  int vEnd=20;
		
		  String vendDivDisp="display: none;";
		  if("yes".equals(vendCallDiv)){
			  vendDivDisp="";
		  }
		  String strVendSerNum = request.getParameter("vendSerNum");
		  String strVendDate = request.getParameter("vendDate");
		  String strCustNm = request.getParameter("vendCustNm");
		  String strVendSts = request.getParameter("vendSts");
		  String strVendAssgn = request.getParameter("vendAssgn");
		  System.out.println("vSortBy : " + vSortBy+ " vSortOrder : "  + vSortOrder);
	/*	  Object[] objVend = objDet.getThirdPartyCalls(vStart, vEnd, vSortBy, vSortOrder, strCustNm, strVendSts, strVendAssgn);
		  int VendTotalRec = 0;
		  if(objVend[0]!=null){
			  VendTotalRec = ((Integer)objVend[0]).intValue();
		  }
		  
		  ArrayList<CanonE307ServReqVendCallRec> arVndrList = new ArrayList<CanonE307ServReqVendCallRec>();
		  if(objVend[1]!=null){
			  arVndrList = (ArrayList<CanonE307ServReqVendCallRec>) objVend[1];
		  }	*/
		  String strVendStyle="align:center;";
	//	  if(arVndrList!=null && arVndrList.size()>5){
	//		  strVendStyle="height: 300px;  overflow-y:auto;align:center;";
	//	  }
		%>
		<div id="thirdPartyDiv" style="<%=vendDivDisp%>">
			<div class="table-inner">
				<table width="100%" id="remotePrbtable" cellspacing="0" >
					<tr>
						<td width="20%" class="sectionHeaderBlack">3rd Party Calls</td>
						<td width="80%" align="right"><b>Filter By:</b>
							<input type="text" value="" id="vendCustNm" name="vendCustNm" size=20 placeholder="Customer Name" onchange="fnGetVendrDtls(1)" style="font-size: 11px;">
							<input type="text" name="vendSts" id="vendSts" value="" size="15" onchange="fnGetVendrDtls(1)" placeholder="Status" style="font-size: 11px;">
							<input type="text" value="" id="vendAssgn" name="vendAssgn" size=10 placeholder="Assignee" onchange="fnGetVendrDtls(1)" style="font-size: 11px;">
						</td>
					</tr>
				</table>
			</div>	
         	<div class="table-inner" id="vendorDataDiv" style="padding-bottom: 40px;">
				<jsp:include page="canonE307VndrCallsTblInclude.jsp">
			    <jsp:param value="<%=strCustNm %>" name="vendCustNm"/>
			    <jsp:param value="<%=strVendSts %>" name="vendSts"/>
			     <jsp:param value="<%=strVendAssgn %>" name="vendAssgn"/>
				<jsp:param value="<%=vSortBy %>" name="vSortBy"/>    
				<jsp:param value="<%=vSortOrder %>" name="vSortOrder"/> 
			    </jsp:include>	  	 
		</div>
		</div>	
		<table>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
		</table>

	
	<div id="dlg"></div>
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
	</div>
<script>
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

function fnSubmitForm(){
	$('#eMsg').html('');
	 $("#errorWidget").hide();
//	 if(navigator.userAgent.indexOf('MSIE') > -1){
		$("#machInfTbl input").each(function() {
		    var input = $(this);
		    if (input.val() == input.attr('placeholder')) {
		      input.val('');
		    }
		});
	// }
	var searchVal ="false";
		var custPostalCode = $('#custPostalCode').val();
		if(custPostalCode!=null && custPostalCode!=''){
			if(fnCheckZip()==false){
				$('#eMsg').html('Please enter valid postal Code');
				 $("#errorWidget").show();
				return;
			}
		}
		$("#machInfTbl input").each(function() {
		      if(this.value!='null' && $.trim(this.value).length>0){
		    	  searchVal="true";
		      }
		  });
		if(searchVal=='false'){
			$('#eMsg').html("Please enter atleast one value for search criteria.");
			 $("#errorWidget").show();
		//	 if(navigator.userAgent.indexOf('MSIE') > -1){
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
		//	 }		 
		}else{
			var serialTagNumber =$.trim($('#serialTagNumber').val());
			var solName=$.trim($('#solutionName').val());
			var acctNum=$.trim($('#accountNumber').val());
			var custAddr=$.trim($('#custAddress').val());
			var custPhone=$.trim($('#custPhoneNumber').val());
			var custName=$.trim($('#customerName').val());
			var custCity=$.trim($('#custCity').val());
			var custState=$.trim($('#custState').val());
			var custpostal=$.trim($('#custPostalCode').val());
//			var sPadMsg = $("#toolTip textarea").val();
//			$('#searchFrm #scratchPad').val( $("#toolTip textarea").val() );
			
			var urlDetail = 'canonE307ServReqGetRecCount.jsp';
			/*var qryStr= "serialTagNumber="+serialTagNumber+"&solName="+solName;
			qryStr = qryStr+"&accountNumber="+acctNum+"&custAddress="+custAddr;
			qryStr = qryStr+"&custPhoneNumber="+custPhone+"&customerName="+custName;
			qryStr = qryStr+"&custCity="+custCity+"&custState="+custState;
			qryStr = qryStr+"&custPostalCode="+custpostal; */
			
			var qryStr=$('#searchFrm').serialize();
			
			var recCount=0;
			showPleaseWait();
		    $.ajax({
					url: urlDetail,
					cache: false,
					data : qryStr,
				//	type : "POST",			
					success: function(data){
						hidePleaseWait();
						result = $.trim(data);
						if(result.indexOf("INFO")==0){
							var urlDetail = "canonE307MachineAndCustomerSearch.jsp?Action=search";
							document.searchFrm.action = urlDetail;
							document.searchFrm.submit();	
						}else{
							
							var machineDet = result.split("^");
							if(machineDet!=null && machineDet.length>0){
								var serialNumber=machineDet[0];
								var machPk =machineDet[1];
								var model=machineDet[2];
								var srvcMsgs = machineDet[3];
								var eolFlg = machineDet[4];
								var hardHoldFlg = machineDet[5];
								var crossSrvcFlg = machineDet[6];
								//var dispCmnts = machineDet[5];
								//var eolEndDt = machineDet[6];
								var selDate="";
								if(crossSrvcFlg =='Y'){
									alert('This is an Oracle Cross Service machine. It can only be created in Oracle.');
									return false;
								}else{
									if(hardHoldFlg=='Y'){
										var afterHrsFlg = fnCheckAfterHoursCall(machPk, model, serialNumber);
										//console.log("afterHrsFlg: "+ afterHrsFlg);
										if(afterHrsFlg){
											var urlDet = 'canonE307UGWLockSrlCheck.jsp';
											var qryStr= "serialNumber="+serialNumber;
												qryStr =qryStr+"&userId="+'<%=loginUser%>';
			
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
															//	var retVal = fnGetSerialEol(serialNumber);
															if(eolFlg=='Y'){
																 $('#srCrtFlg').val('Y');
																$('#srvcMsgSrl').val(serialNumber);
																$('#srvcMsgMdl').val(model);
																$('#srvcMsgMachPk').val(machPk);
																$('#srvcMsgSelDt').val(selDate);
																$('#srvcMsgs').val(srvcMsgs);
																fnGetEolMsgs(serialNumber, machPk);
															}else{
																if(srvcMsgs!=null && srvcMsgs!="null" && srvcMsgs!=''){
																	//console.log("srvcMsgs: "+ srvcMsgs);
																	$('#srvcMsgSrl').val(serialNumber);
																	$('#srvcMsgMdl').val(model);
																	$('#srvcMsgMachPk').val(machPk);
																	$('#srvcMsgSelDt').val(selDate);
																	$('#srvcMsgs').val(srvcMsgs);
																	fnGetSrvcMsg(srvcMsgs);										
																}else{
			
																// alert(srvcMsgs);
																	var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqCreate.jsp?serialNumber="+encodeURIComponent(serialNumber)+"&model="+encodeURIComponent(model);
																	urlDetail +="&machPk="+machPk;
																	urlDetail +="&selDate="+selDate;
																	<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
																		urlDetail="<%="https://"+ request.getServerName()%>"+urlDetail;
																	<% } %>
																	showPleaseWait();
																	document.searchFrm.action = urlDetail;
																	document.searchFrm.submit();
																}
															}										    	
													    	
													    }else{
													    	$('#eMsg').html("This serial number is being worked upon by : "+ugwLockout);
															 $("#errorWidget").show();										    	
													    }
													}
												});
										}
									}else{
										var urlDet = 'canonE307UGWLockSrlCheck.jsp';
										var qryStr= "serialNumber="+serialNumber;
											qryStr =qryStr+"&userId="+'<%=loginUser%>';
		
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
														//	var retVal = fnGetSerialEol(serialNumber);
														if(eolFlg=='Y'){
															 $('#srCrtFlg').val('Y');
															$('#srvcMsgSrl').val(serialNumber);
															$('#srvcMsgMdl').val(model);
															$('#srvcMsgMachPk').val(machPk);
															$('#srvcMsgSelDt').val(selDate);
															$('#srvcMsgs').val(srvcMsgs);
															fnGetEolMsgs(serialNumber, machPk);
														}else{
															if(srvcMsgs!=null && srvcMsgs!="null" && srvcMsgs!=''){
																//console.log("srvcMsgs: "+ srvcMsgs);
																$('#srvcMsgSrl').val(serialNumber);
																$('#srvcMsgMdl').val(model);
																$('#srvcMsgMachPk').val(machPk);
																$('#srvcMsgSelDt').val(selDate);
																$('#srvcMsgs').val(srvcMsgs);
																fnGetSrvcMsg(srvcMsgs);										
															}else{
		
															// alert(srvcMsgs);
																var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqCreate.jsp?serialNumber="+encodeURIComponent(serialNumber)+"&model="+encodeURIComponent(model);
																urlDetail +="&machPk="+machPk;
																urlDetail +="&selDate="+selDate;
																<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
																	urlDetail="<%="https://"+ request.getServerName()%>"+urlDetail;
																<% } %>
																showPleaseWait();
																document.searchFrm.action = urlDetail;
																document.searchFrm.submit();
															}
														}										    	
												    	
												    }else{
												    	$('#eMsg').html("This serial number is being worked upon by : "+ugwLockout);
														 $("#errorWidget").show();										    	
												    }
												}
											});
										}
								}
							}else{
								showPleaseWait();
								var urlDetail = "canonE307MachineAndCustomerSearch.jsp?Action=search";
								document.searchFrm.action = urlDetail;
								document.searchFrm.submit();									
							}
						}
					}
				});			
		}
}
function fnSelectSerial(serialNum, machPk, model){
	$('#eMsg').html("");
	var ugwLockout ='';
	var eolEndDt='';
	var srvcMsgs= '';
	var srCrtFlg='';
	var dispCmnts ='';
	var hardHoldFlg="";
	var urlDet = 'canonE307UGWLockoutInfo.jsp';
	var qryStr= "serialNumber="+serialNum;
		qryStr =qryStr+"&machPk="+machPk;
		qryStr =qryStr+"&userId="+'<%=loginUser%>';
		showPleaseWait();
	  $.ajax({
			url: urlDet,
			cache: false,
			data : qryStr,
			async : false,
			success: function(data){ 
			    hidePleaseWait();
			    result = $.trim(data);
			    //console.log("result: "+ result);
			    var machineDet = result.split("*");
				if(machineDet!=null && machineDet.length>0){
					ugwLockout=machineDet[0];
					srvcMsgs = machineDet[1];
					srCrtFlg = machineDet[2];
					hardHoldFlg =machineDet[3];
				}
				if(hardHoldFlg=='Y'){
					var afterHrsFlg = fnCheckAfterHoursCall(machPk, model, serialNum);
					if(afterHrsFlg){
						if(srCrtFlg=='Y'){
							$('#srvcMsgSrl').val(serialNum);
							$('#srvcMsgMdl').val(model);
							$('#srvcMsgMachPk').val(machPk);
							$('#srvcMsgSelDt').val('');
							$('#srCrtFlg').val(srCrtFlg);
							$('#srvcMsgs').val(srvcMsgs);
							fnGetEolMsgs(serialNum, machPk);
						}else{
						    if(ugwLockout=='N' || ugwLockout=='null'|| ugwLockout==''){
						    	if(srvcMsgs!=null && srvcMsgs!="null" && srvcMsgs!=''){
						    		$('#srvcMsgSrl').val(serialNum);
									$('#srvcMsgMdl').val(model);
									$('#srvcMsgMachPk').val(machPk);
									$('#srvcMsgSelDt').val('');
									fnGetSrvcMsg(srvcMsgs);	
						    	}else{
							    	var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqCreate.jsp?serialNumber="+encodeURIComponent(serialNum)+"&model="+encodeURIComponent(model);
									urlDetail +="&machPk="+machPk;
									<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
										urlDetail="<%="https://"+ request.getServerName()%>"+urlDetail;
									<% } %>
									//$('#searchFrm #scratchPad').val( $("#toolTip textarea").val());
									showPleaseWait();
									document.searchFrm.action = urlDetail;
									document.searchFrm.submit();
						    	}
						    }else{
						    	$('#eMsg').html("This serial number is being worked upon by : "+ugwLockout);
								 $("#errorWidget").show();
			
						    }
						} 						
					}
				//	alert("Unable to create call at this time.  A service manager will call you shortly to discuss the issue");
				//	emailMngrNotif(serialNum, machPk);					
				}else{
					if(srCrtFlg=='Y'){
						$('#srvcMsgSrl').val(serialNum);
						$('#srvcMsgMdl').val(model);
						$('#srvcMsgMachPk').val(machPk);
						$('#srvcMsgSelDt').val('');
						$('#srCrtFlg').val(srCrtFlg);
						$('#srvcMsgs').val(srvcMsgs);
						fnGetEolMsgs(serialNum, machPk);
					}else{
					    if(ugwLockout=='N' || ugwLockout=='null'|| ugwLockout==''){
					    	if(srvcMsgs!=null && srvcMsgs!="null" && srvcMsgs!=''){
					    		$('#srvcMsgSrl').val(serialNum);
								$('#srvcMsgMdl').val(model);
								$('#srvcMsgMachPk').val(machPk);
								$('#srvcMsgSelDt').val('');
								fnGetSrvcMsg(srvcMsgs);	
					    	}else{
						    	var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqCreate.jsp?serialNumber="+encodeURIComponent(serialNum)+"&model="+encodeURIComponent(model);
								urlDetail +="&machPk="+machPk;
								<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
									urlDetail="<%="https://"+ request.getServerName()%>"+urlDetail;
								<% } %>
								//$('#searchFrm #scratchPad').val( $("#toolTip textarea").val());
								showPleaseWait();
								document.searchFrm.action = urlDetail;
								document.searchFrm.submit();
					    	}
					    }else{
					    	$('#eMsg').html("This serial number is being worked upon by : "+ugwLockout);
							 $("#errorWidget").show();
		
					    }
					} 
				}
			}
		});	
}

function fnSortFilterCriteria(sortBy){
	var existingSortOrder = $("#sortOrder").val();
	var existingSortBy = $("#sortBy").val();
	//$('#searchFrm #scratchPad').val( $("#toolTip textarea").val() );
	
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
	showPleaseWait();
	var urlDetail = "<%=ctxPath%>"+"/e307/jsp/canonE307ServiceRequestSearch.jsp";
	document.searchFrm.action = urlDetail;
	document.searchFrm.submit();	
}
function fnSortFilterFtreCall(sortBy){
	var existingSortOrder = $("#fSortOrder").val();
	var existingSortBy = $("#fSortBy").val();  
	$('#searchFrm #scratchPad').val( $("#toolTip textarea").val());
	
	if(sortBy==existingSortBy){
	  if(existingSortOrder=='asc'){
	    existingSortOrder = 'desc';
	  }else{
	    existingSortOrder = 'asc'
	  }
	  $("#fSortOrder").val(existingSortOrder);
	}else{
	  existingSortOrder ='asc';     
	   $("#fSortOrder").val(existingSortOrder);
	   $("#fSortBy").val(sortBy);
	}
	$('#fCallDiv').val('yes');
	showPleaseWait();
	var urlDetail = "<%=ctxPath%>"+"/e307/jsp/canonE307ServiceRequestSearch.jsp";
	document.searchFrm.action = urlDetail;
	document.searchFrm.submit();	
}
function fnSortFltrVendCriteria(sortBy){
	var existingSortOrder = $("#vSortOrder").val();
	var existingSortBy = $("#vSortBy").val();  
	$('#searchFrm #scratchPad').val( $("#toolTip textarea").val());
	
	if(sortBy==existingSortBy){
	  if(existingSortOrder=='asc'){
	    existingSortOrder = 'desc';
	  }else{
	    existingSortOrder = 'asc'
	  }
	  $("#vSortOrder").val(existingSortOrder);
	}else{
	  existingSortOrder ='asc';     
	   $("#vSortOrder").val(existingSortOrder);
	   $("#vSortBy").val(sortBy);
	}
	$('#vCallDiv').val('yes');
	showPleaseWait();
	var urlDetail = "<%=ctxPath%>"+"/e307/jsp/canonE307ServiceRequestSearch.jsp";
	document.searchFrm.action = urlDetail;
	document.searchFrm.submit();		
}
function fnDisableSearchCriteria(){
	var selVal = $('input[name=selSearch]:checked').val();
	if(selVal=='machineSearch'){
		$('#taskSearchDiv :input').attr('disabled', true);
		$('#imgProbSearchDiv :input').attr('disabled', true);
		$('#machineSearchDiv :input').attr('disabled', false);
		$('#custSearchDiv :input').attr('disabled', false);
		$("#taskSearchDiv input").each(function() {
		      this.value = "";
		  });
		$("#imgProbSearchDiv input").each(function() {
		      this.value = "";
		  });
		$('#imgBranchName').val('-1');
	}else if(selVal=='srTaskSearch'){
		$('#imgProbSearchDiv :input').attr('disabled', true);
		$('#machineSearchDiv :input').attr('disabled', true);
		$('#custSearchDiv :input').attr('disabled', true);
		$('#taskSearchDiv :input').attr('disabled', false);
		$("#imgProbSearchDiv input").each(function() {
		      this.value = "";
		  });
		$("#machineSearchDiv input").each(function() {
		      this.value = "";
		  });
		$("#custSearchDiv input").each(function() {
		      this.value = "";
		  });
		$('#imgBranchName').val('-1');
	}else if(selVal=='problemSearch'){
		$('#taskSearchDiv :input').attr('disabled', true);
		$('#machineSearchDiv :input').attr('disabled', true);
		$('#custSearchDiv :input').attr('disabled', true);
		$('#imgProbSearchDiv :input').attr('disabled', false);
		$("#machineSearchDiv input").each(function() {
		      this.value = "";
		  });
		$("#custSearchDiv input").each(function() {
		      this.value = "";
		  });
		$("#taskSearchDiv input").each(function() {
		      this.value = "";
		  });		
	}
}

$(function(){
    $(".datePicker").datepicker({
		 dateFormat: 'M dd yy',
		 changeMonth: true,
		 changeYear: true
	 });
   // if(navigator.userAgent.indexOf('MSIE') > -1){
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
 //   }
 });
function fnClearAll(){
	$("#srInfTbl input").each(function() {
	      this.value = "";
	      var input = $(this);
		  if (input.val() == '' || input.val() == input.attr('placeholder')) {
		    input.addClass('placeholder');
		    input.val(input.attr('placeholder'));  
		  }
	  });
	//$('select option:first-child').attr("selected", "selected");
	$('select option:nth-child(1)').prop("selected", true);
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
function fnFutureCallsView(){
	if (false == $('#futureCallDiv').is(':visible')) {
        $('#futureCallDiv').show(250);
  //      $('#fCallDiv').val('yes');
    } else {
        $('#futureCallDiv').hide(250);
        $('#fCallDiv').val('no');
    }
}
function fn3rdPartyCallsView(){
	if (false == $('#thirdPartyDiv').is(':visible')) {
        $('#thirdPartyDiv').show(250);
   //     $('#vCallDiv').val('yes');
    }
    else {
        $('#thirdPartyDiv').hide(250);
        $('#vCallDiv').val('no');
    }	
}
function fnUgwDtlsView(){
	if (false == $('#hieghtDiv').is(':visible')) {
        $('#hieghtDiv').show(250);
     }
    else {
        $('#hieghtDiv').hide(250);
    }	
}

function fnCheckZip() {
	var zipCode = $('#custPostalCode').val();
    var zipVal = (/(^\d{5}$)|(^\d{5}-\d{4}$)/).test(zipCode);
    return zipVal;
  //  $('#custPostalCode').val(zipVal);
//  if(zipVal==false){
// 	 $('#msgDiv').html('Please enter valid postal Code'); 
//  } 
}
function fnSearch(searchParam){
	if(searchParam=='Machine'){
		$('#mchDiv').show();
		$('#tskDiv').hide();
		$('#probDiv').hide();
	}else if(searchParam =='Task'){
		$('#mchDiv').hide();
		$('#tskDiv').show();
		$('#probDiv').hide();		
	}else{
		$('#mchDiv').hide();
		$('#tskDiv').hide();
		$('#probDiv').show();				
	}
} 
$('input[type="text"]').each(function (){
	 $(this).addClass("inTxt");	  
});
function fnGetUGWDtls(pageNum){
 	$("#remotePrbtable input").each(function() {
	    var input = $(this);
	    if (input.val() == input.attr('placeholder')) {
	      input.val('');
	    }
	 });	
	var custName = $.trim($('#imgCustomerName').val());
	//var srlTagNum = $('#imgSerialTagNumber').val();
	var model = $.trim($('#imgModel').val());
	var branchNm = $.trim($('#imgBranchName').val());
	var qryStr="imgCustomerName="+custName;
	qryStr = qryStr+"&imgModel="+model;
	qryStr = qryStr+"&imgBranchName="+branchNm+"&pageNumber="+pageNum;
	showPleaseWait();
	 $.ajax({
			url:"canonE307UGWErrTblInclude.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){  
			hidePleaseWait();
			document.getElementById("imgProbDiv").innerHTML = $.trim(data);
			$("#paginationUGW #a"+pageNum).css({"color":"white","background-color":"#A10304"});
		 	}             
	});
}
function fnGetFtrDtls(pageNum){
 	$("#ftrCalltable input").each(function() {
	    var input = $(this);
	    if (input.val() == input.attr('placeholder')) {
	      input.val('');
	    }
	 });	
	
	var ftrBrnch = $.trim($('#ftrBrnch').val());
	var ftrDate = $.trim($('#ftrDate').val());
	var ftrCustmr = $.trim($('#ftrCustmr').val());
	var fSortBy = $.trim($('#fSortBy').val());
	var fSortOrder = $.trim($('#fSortOrder').val());
	$('#fCallDiv').val('yes');
	
	var qryStr="ftrBrnch="+ftrBrnch+"&ftrCustmr="+ftrCustmr;
	qryStr = qryStr+"&ftrDate="+ftrDate+"&fSortBy="+fSortBy;
	qryStr = qryStr+"&fSortOrder="+fSortOrder+"&fCallDiv=yes"+"&pageNumber="+pageNum;
	showPleaseWait();
	 $.ajax({
			url:"canonE307FtrCallsTblInclude.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){  
			hidePleaseWait();
			document.getElementById("fDiv").innerHTML = $.trim(data);
			$("#pagination #a"+pageNum).css({"color":"white","background-color":"#A10304"});
		 	}             
	});
}
function fnGetVendrDtls(pageNum){
 	$("#remotePrbtable input").each(function() {
	    var input = $(this);
	    if (input.val() == input.attr('placeholder')) {
	      input.val('');
	    }
	 });		
	
	var vendCustNm = $.trim($('#vendCustNm').val());
	var vendSts = $.trim($('#vendSts').val());
	var vendAssgn = $.trim($('#vendAssgn').val());
	var vSortBy = $.trim($('#vSortBy').val());
	var vSortOrder = $.trim($('#vSortOrder').val());
	$('#vCallDiv').val('yes');
	
	var qryStr="vendCustNm="+vendCustNm+"&vendAssgn="+vendAssgn;
	qryStr = qryStr+"&vendSts="+vendSts+"&vSortBy="+vSortBy;
	qryStr = qryStr+"&vSortOrder="+vSortOrder+"&vCallDiv=yes"+"&pageNumber="+pageNum;
	showPleaseWait();
	 $.ajax({
			url:"canonE307VndrCallsTblInclude.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){     
			hidePleaseWait();
			document.getElementById("vendorDataDiv").innerHTML = $.trim(data);
			$("#paginationVnd #a"+pageNum).css({"color":"white","background-color":"#A10304"});
		 	}             
	});
}
function fnFsrTskSearch(){
	$('#eMsg').val('');
	 $("#errorWidget").hide();
	 $("#srInfTbl input").each(function() {
		    var input = $(this);
		    if (input.val() == input.attr('placeholder')) {
		      input.val('');
		    }
		});
	var servReqNum = $.trim($('#servRqstNumber').val());
	var tskNum = $.trim($('#taskNumber').val());
	var createdBy = $.trim($('#createdBy').val());
	var creationDt = $.trim($('#creationDt').val());
	var srStatus = $.trim($('#srStatus').val());
	var taskSts = $.trim($('#taskSts').val());
	var taskType = $.trim($('#taskType').val());
	
	if(servReqNum.length != 0 || tskNum.length != 0 || createdBy.length != 0 ||creationDt.length != 0 || srStatus.length!=0 || taskSts.length!=0 || taskType.length!=0){
		var urlDetail = 'canonE307GetSRCount.jsp';
		/*var qryStr= "servReqNum="+servReqNum+"&tskNum="+tskNum;
		qryStr = qryStr+ "&createdBy="+createdBy+"&creationDt="+creationDt;
		qryStr = qryStr+ "&taskSts="+taskSts+"&srStatus="+srStatus; */
	//	$('#searchFrm #scratchPad').val( $("#toolTip textarea").val());
		var qryStr = $('#searchFrm').serialize();
		var recCount=0;
		showPleaseWait();
	    $.ajax({
				url: urlDetail,
				cache: false,
				data : qryStr,
				async: false,
				type : "POST",			
				success: function(data){
					hidePleaseWait();
					result = $.trim(data);
					//console.log('SR Count: '+result);
					if(result!='' && result>1){
						 var url = '<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?showVend=Y&fsr='+result; 
						  //  url += "&taskNum=" +tskNum;
						    var l_newWindow = window.open(url);
						    l_newWindow.focus();  
					}else{
						showPleaseWait();
						/* var url = '<%=ctxPath%>/e307/jsp/canonE307ServiceReqHistory.jsp?fsr='+servReqNum; 
						    url += "&taskNum=" +tskNum;
						    url += "&createdBy=" +createdBy;
						    url += "&creationDt=" +creationDt;
						    url += "&srStatus=" +srStatus;
						    url += "&taskSts=" +taskSts; */
						    var url ='<%=ctxPath%>/e307/jsp/canonE307ServiceReqHistory.jsp';
							document.searchFrm.action = url;
							document.searchFrm.submit();								
						}
				}
			});			
	}else{
		$('#eMsg').html('Please enter atleast one value for search criteria.');
		 $("#errorWidget").show();
	}
}
function fnClearCust(){
	$('#eMsg').html('');
	$("#machInfTbl input").each(function() {
  	  this.value = "";
      var input = $(this);
	  if (input.val() == '' || input.val() == input.attr('placeholder')) {
	    input.addClass('placeholder');
	    input.val(input.attr('placeholder'));  
	  }  	  
	});
	$("#custInfTbl input").each(function() {
	    this.value = "";
	      var input = $(this);
		  if (input.val() == '' || input.val() == input.attr('placeholder')) {
		    input.addClass('placeholder');
		    input.val(input.attr('placeholder'));  
		  }
	});	
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
		    	  searchVal="true";
		      }
		  });
		if(searchVal=='true'){
	  		fnSubmitForm();
		}
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
//	});

}); 
 

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
function fnServReqHist(url){
    var l_newWindow = window.open(url);
    l_newWindow.focus(); 
}
function fnGetSRDtls(url){
	 var l_newWindow = window.open(url);
	 l_newWindow.focus(); 
}
function fnGetVndrCalls(url){
	/*var l_newWindow = window.open(url);
	 l_newWindow.focus(); */
	 var l_newWindow = window.open(url,'', 'scrollbars=yes,width='+screen.width+',height='+screen.height+',top=0,left=0,resizable=yes'); 
	 l_newWindow.focus();

}
/*function fnGetSerialEol(serialNum){
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
} */
function fnGetEolMsg(eolEndDt, dispCmnts){
	//console.log(" eolEndDt: "+eolEndDt);

	

    var modelName ="#eolDlg";
     $(modelName).dialog({
					height: 280,
					title: 'Machine is End of Life', 
					modal: true ,
					width: 400,		
	                resizable: false
				});
    
     var tblEolHtml= $("#eolMsgDiv").html();
     //console.log('tblEolHtml: '+tblEolHtml);
     $(modelName).html("");					
     $(modelName).html(tblEolHtml);
     $(".ui-dialog").css({"z-index":"10005"});
     $(".ui-dialog-titlebar").css({"background-color":"#003B4E"});
     $(".ui-dialog-titlebar").addClass("hd");
     $(".ui-dialog-titlebar-close").css({"visibility":"hidden"});
     $(".ui-dialog").css({"float":"none"}); 
     $(".ui-dialog-title").css({"float":"none"}); 
     $('#eolDlg #eolEndDt').val(eolEndDt);
     $('#eolDlg #dispComments').html(dispCmnts);
     
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
    // console.log('tblEolHtml: '+tblEolHtml);
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

function fnCloseSearchDlg(dId){
	var dlgId="#"+dId;
	$(dlgId).html("");
	$(dlgId).dialog("close");
	$(dlgId).dialog("destroy");
	var srFlg = $('#srCrtFlg').val();
	if (srFlg=='Y'){
		var srvcMsg = $('#srvcMsgs').val();
		if(srvcMsg!=null && srvcMsg!="null" && srvcMsg!=''){
			fnGetSrvcMsg(srvcMsg);
		}else{
			fnCreateSR();
		}
	}
}	
 function fnCloseMsgDlg(dId){
	var dlgId="#"+dId;
	$(dlgId).html("");
	$(dlgId).dialog("close");
	$(dlgId).dialog("destroy");
	fnCreateSR();
	
} 
 function fnCreateSR(){
		var serialNumber = $('#srvcMsgSrl').val();
		var model =  $('#srvcMsgMdl').val();
		var machPk = $('#srvcMsgMachPk').val();
		var selDate = $('#srvcMsgSelDt').val();
		var callTp = $('#callTp').val();
		
		var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqCreate.jsp?serialNumber="+encodeURIComponent(serialNumber)+"&model="+encodeURIComponent(model);
		urlDetail +="&machPk="+machPk;
		urlDetail +="&selDate="+selDate;
		urlDetail +="&callTp="+callTp;
		<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
			urlDetail="<%="https://"+ request.getServerName()%>"+urlDetail;
		<% } %>
		showPleaseWait();
		document.searchFrm.action = urlDetail;
		document.searchFrm.submit();
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

	var srFlg = $('#srCrtFlg').val();
	if (srFlg=='Y'){
		var srvcMsg = $('#srvcMsgs').val();
		if(srvcMsg!=null && srvcMsg!="null" && srvcMsg!=''){
			fnGetSrvcMsg(srvcMsg);
		}else{
			fnCreateSR();
		}
	}
    
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
 
</script>

	</body>
</html>
