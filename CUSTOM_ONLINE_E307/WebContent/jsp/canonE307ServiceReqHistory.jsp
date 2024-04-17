<%@ page import="com.canon.apps.servreq.dao.*" %>  
<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<%@ page import="com.canon.apps.servreq.beans.CanonE307SRViewTskDtlsRec" %>  
<%@ page import="canon.apps.common.CanonS21SessionValidate"%>
<%@ page import="javax.servlet.http.*" %>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil"%>

<%
//	final int _pc = pageContext.REQUEST_SCOPE;
//	pageContext.setAttribute("_pageTitle", "Search", _pc);

	String pageTitle="History";
	
	ArrayList<String> menuList = new ArrayList<String>();
	 menuList.add("sp:btn:Scratch Pad:opnSCPad();");
%>
<%@ include file="canonE307ServReqHeader.jsp" %> 
<script src='<%=ctxPath%>/e307/js/canonE307ScratchPad.js' type='text/javascript'></script> 
<script src='<%=ctxPath%>/e307/js/collapse.js' type='text/javascript'></script>

<%
    request.getSession().setAttribute("dowloadFlag", "P");
	CanonE307ServiceRequestDetailsDao srObj = new CanonE307ServiceRequestDetailsDao();
	CanonE307ServiceReqSumryAPIUtil utiSrObj = new CanonE307ServiceReqSumryAPIUtil();
	CanonE307ServiceRequestDetailsDao srDetObj = new CanonE307ServiceRequestDetailsDao();
	CanonE307ServiceRequestSearchDao objDet = new CanonE307ServiceRequestSearchDao();

	String sortBy = "";
	String sortOrder = "asc";  
	int tskStart=0;
	int tskEnd=10;
	String tskSortBy="";
	String tskSortOrder="ASC";
	if(request.getParameter("sortBy")!=null){
		sortBy = request.getParameter("sortBy");
	}
	if(request.getParameter("sortOrder")!=null){
		sortOrder = request.getParameter("sortOrder");
	}	
	if(request.getParameter("tskSortBy")!=null){
		tskSortBy = request.getParameter("tskSortBy");
	}
	if(request.getParameter("tskSortOrder")!=null){
		tskSortOrder = request.getParameter("tskSortOrder");
	}	
	String strSerial = util.checkNull(request.getParameter("serialNumber"));
	String strTagNum = util.checkNull(request.getParameter("tagNum"));
	String strSolName = util.checkNull(request.getParameter("solName"));
	String strModel =  util.checkNull(request.getParameter("model"));
	String strAcctNum = util.checkNull(request.getParameter("acctNum"));
	String strCustName =  util.checkNull(request.getParameter("custName"));
	String strServRqstNum =  util.checkNull(request.getParameter("servRqstNumber"));
	String strTaskNumber =  util.checkNull(request.getParameter("taskNumber"));	
	String strCreatedBy =  util.checkNull(request.getParameter("createdBy"));	
	String strCreationDt =  util.checkNull(request.getParameter("creationDt"));	
	String exclDwnld = util.checkNull(request.getParameter("exclDwnld"));
	String taskSts = util.checkNull(request.getParameter("taskSts"));	
	String srSts = util.checkNull(request.getParameter("srStatus"));
	String taskType = util.checkNull(request.getParameter("taskType"));
	
	System.out.println("History strSerial: "+ strSerial+ "strTagNum: "+strTagNum+" strServRqstNum: "+strServRqstNum+" strTaskNumber: "+strTaskNumber+ "taskType: "+taskType);
	String strFsrNum="";
	String fnGetSrHistory="getFsrHistory";
	%>
<div id ="main_content">
   		<div id="page_top">
			<h1>Advanced Service Call Center</h1>
		</div>
<div class="table-inner">
  <form name="frmHistSrch" id="frmHistSrch" method="post" action="">
    <input type='hidden' name='sortOrder' id='sortOrder' value="<%=sortOrder%>" />
  	<input type='hidden' name='sortBy' id='sortBy' value="<%=sortBy%>" />
  	<input type='hidden' name='tskSortOrder' id='tskSortOrder' value="<%=tskSortOrder%>" />
  	<input type='hidden' name='tskSortBy' id='tskSortBy' value="<%=tskSortBy%>" />
    <input type="hidden" name="scratchPad" id="scratchPad" value="">
    <input type="hidden" name="pageNum" id="pageNum" value="">
	<div id="errorWidget"  style="display: none;padding-bottom: 5px;padding-top: 5px;">
 		<p id="eMsg"></p>
	</div>	
	
<div><h1>Service History Search</h1>		
<div id="scrolltbl" style="overflow: auto; overflow-y: hidden; width:99%; align:center">
<table id="taskTable" width="100%" style="border-width:0px">
<tr><td>
		<!-- <div> -->
			<table style='float: right;width:15%'>
				<tr>
					<td style="font-weight:bolder;font-family:sans-serif;vertical-align:top">Model:</td><td id='mdlId' nowrap></td>
				</tr>
				<tr>
					<td style="font-weight:bolder;font-family:sans-serif">Serial:</td><td id='serial' nowrap></td>
				</tr>				
				<tr>
					<td style="font-weight:bolder;font-family:sans-serif">Branch:</td><td id='branch' nowrap></td>
				</tr>	
				<tr>
					<td style="font-weight:bolder;font-family:sans-serif" nowrap>Machine Manager:</td><td id='machMngr' nowrap></td>
				</tr>
				<tr><td colspan=2>&nbsp;</td></tr>
				<tr><td colspan=2>&nbsp;</td></tr>
 			<!-- 	<tr><td colspan=2>&nbsp;</td></tr>  -->
			</table>
	<!-- 	</div> -->
		</td>
	<td>
	<!-- 	<div> -->
			<table style='float: right;width:15%'>
				<tr>
					<td  nowrap style="font-weight:bolder;font-family:sans-serif">Customer Name:</td><td id="custNm" nowrap></td>
				</tr>
				<tr>
					<td  nowrap style="font-weight:bolder;font-family:sans-serif">Address:</td><td id="address" nowrap></td>
				</tr>				
				<tr>
					<td style="font-weight:bolder;font-family:sans-serif" nowrap>City, State, Postal Code:</td><td id="city" nowrap></td>
				</tr>	
				<tr>
					<td nowrap style="font-weight:bolder;font-family:sans-serif">Contact Name:</td><td id="cntctNm" nowrap></td>
				</tr>
				<tr>
					<td  nowrap style="font-weight:bolder;font-family:sans-serif">Contact Phone#:</td><td id="cntctPhn" nowrap></td>
				</tr>
				<tr>
					<td style="font-weight:bolder;font-family:sans-serif">Email Address:</td><td id="emailAdd" nowrap></td>
				</tr>
			</table>
	<!-- 	</div> -->
		</td>		
		<td>
			<div class="serviceNew">	
			<table cellspacing="5" class="machTbl" id="machTbl">
				<tr>
					<th colspan=2>Machine Information</th>
					<th>Customer Information</th>
					<th colspan=2>Existing Service Request</th>
				</tr>
		        <tr align="center">
			          <td><input type="text" name="serialNumber" id="serialNumber" value="<%=strSerial %>"  placeholder="Serial#" style="width: 135px;"/></td>
			          <td><input type="text" name="tagNum" id="tagNum" value="<%=strTagNum%>"  placeholder="Tag#" style="width: 135px;"></td>
			          <td><input type="text" name="acctNum" id="acctNum" value="<%=strAcctNum%>" placeholder="Account#" style="width:172px"/></td>
					  <td nowrap><input type="text" id="servRqstNumber" name="servRqstNumber" placeholder="Service Request#" value="<%=strServRqstNum %>" style="width:115px"></td>	
					  <td nowrap><input type="text" id="createdBy" name="createdBy" placeholder="Created By" value="<%=strCreatedBy %>" style="width:115px"></td>				          			          
		        </tr>
				<tr align="center">
	       	     	<td><input type="text" name="model" id="model" value="<%=strModel%>" placeholder="Model" style="width: 135px;"></td>
	          		<td><input type="text" name="solName" id="solName" value="<%=strSolName%>"  placeholder="Solution Name" style="width: 135px;"></td>
	          		<td><input type="text" name="custName" id="custName" value="<%=strCustName%>" placeholder="Customer Name" style="width:172px"/></td>
						<td>
							<%
							ArrayList srStsLst = (ArrayList)srDetObj.getSRSts();
							String stsSelect = "";
							%>
								<select id="srStatus" name="srStatus" style="width:115px;margin: 0px 5px 0px 5px !important;">
								 <option value="" selected>SR Status</option>
								<%
								
								if(srStsLst!=null && srStsLst.size()>0){
									for(int i=0;i<srStsLst.size();i++){
										CanonE307SRLovRec srStsObj = (CanonE307SRLovRec)srStsLst.get(i);
										if(srStsObj.getStrVal().equals(srSts)){
											stsSelect ="selected";
										}else{
											stsSelect ="";
										}
								%>
									<option value="<%=srStsObj.getStrVal() %>" <%=stsSelect%>><%=srStsObj.getStrValDesc() %></option>
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
						<select id="taskSts" name="taskSts" style="width:115px;margin: 0px 5px 0px 5px !important;">
						 <option value="" selected>Task Status</option>
						<%
						if(stsTskLst!=null && stsTskLst.size()>0){
							for(int i=0;i<stsTskLst.size();i++){
								CanonE307SRLovRec tskStsObj = (CanonE307SRLovRec)stsTskLst.get(i);
								if(tskStsObj.getStrVal().equals(taskSts)){
									stsSelect ="selected";
								}else{
									stsSelect ="";
								}
						%>
							<option value="<%=tskStsObj.getStrVal() %>" <%=stsSelect%>><%=tskStsObj.getStrValDesc() %></option>
						<%
							}
						}
						 %>
						 </select> 
					</td>
					</tr>
				<tr>
					<td>&nbsp;&nbsp;</td>	
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>			
	          		<td nowrap><input type="text" id="taskNumber" name="taskNumber" placeholder="Task#" value="<%=strTaskNumber %>" style="width:115px;"></td>
	          		<td nowrap><input type="text" id="creationDt" name="creationDt" placeholder="Creation Date" value="<%=strCreationDt %>" style="width:115px" class="datePicker" ></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;</td>	
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>
						<% 
						ArrayList arTskTpeLst = objDet.getTaskTypeLovVal();
						%>
						<select id="taskType" name="taskType" style="width:115px;margin: 0px 5px 0px 5px !important;">
							 <option value="" selected>Task Type</option>
							<%
							if(arTskTpeLst!=null && arTskTpeLst.size()>0){
								for(int i=0;i<arTskTpeLst.size();i++){
									CanonE307SRLovRec tskTypeObj = (CanonE307SRLovRec)arTskTpeLst.get(i);
									if(tskTypeObj.getStrVal().equals(taskType)){
										stsSelect ="selected";
									}else{
										stsSelect ="";
									}
							%>
								<option value="<%=tskTypeObj.getStrVal() %>" <%=stsSelect%>><%=tskTypeObj.getStrValDesc() %></option>
							<%
								}
							}
							 %>
							 </select> 
						</td>
						<td>&nbsp;</td>
					</tr>				
					<tr valign="top" align="right">
						<td colspan=5 style="text-align: right;">
						<a href="javascript:fnSbmtFrm()" class="btn" >Search</a>
						<a href="javascript:fnClrFrm()" class="btn" >Clear</a></td>
					</tr>
			</table>	
		</div>	
		

		</td>
		</tr>
		</table>
		</div>

	</form>
</div>	
	<%
/*	  if("Y".equals(exclDwnld)){
		  int hStart =1;
		  int hEnd =10;
		  Object[] obj = srObj.getSrHistory(strSerial,strTagNum,strSolName, strModel, strAcctNum,strCustName, hStart, hEnd, sortBy,sortOrder, strServRqstNum, strTaskNumber, strCreatedBy, strCreationDt);
		  if(obj[1]!=null){
	        pageContext.setAttribute("export-excel-detail", obj[1], PageContext.REQUEST_SCOPE);
	        pageContext.forward("canonE307ExcelDownload.jsp");
		  }
	  }else{*/

    	ArrayList lstSearchRes = new ArrayList();
		String strPageNumber = util.checkNull(request.getParameter("pageNumber"));
		int pageNumber =1;
		if(strPageNumber.length()>0){
			pageNumber = Integer.parseInt(strPageNumber); 
		}
		int count=10; 
		int hStart = ((pageNumber-1)*count) + 1 ;
		int hEnd= pageNumber*count;
		int totalLks =0;
		String totalCountMsg="";	
		int totalRecords=0;
		System.out.println("Start: "+ hStart+" end : "+hEnd+" strServRqstNum: "+strServRqstNum);
		boolean checkParam = false;
		System.out.println("Before If.");
	if((strSerial!=null && !"".equals(strSerial))||(strTagNum!=null && !"".equals(strTagNum))||(strSolName!=null && !"".equals(strSolName))||(strModel!=null && !"".equals(strModel))||(strAcctNum!=null && !"".equals(strAcctNum))
		||(strCustName!=null && !"".equals(strCustName))||(strServRqstNum!=null && !"".equals(strServRqstNum))||(strTaskNumber!=null && !"".equals(strTaskNumber))
		||(strCreatedBy!=null && !"".equals(strCreatedBy))||(strCreationDt!=null && !"".equals(strCreationDt)) || !"".equals(taskSts) || !"".equals(srSts) || !"".equals(taskType)){
		checkParam=true;
	}
	if(checkParam){
		Object[] obj = srObj.getSrHistoryNew(strSerial,strTagNum,strSolName, strModel, strAcctNum,strCustName, hStart, hEnd, sortBy,sortOrder, strServRqstNum, strTaskNumber, strCreatedBy, strCreationDt, srSts, taskSts, taskType);
		if(obj[0]!=null)
		totalRecords =((Integer )obj[0]).intValue();
		if(totalRecords>0){ 
			lstSearchRes = (ArrayList) obj[1];
			totalLks = (totalRecords%count>0)?((totalRecords/count)+1):totalRecords/count;
	
		    if(totalLks>1){
		    	totalCountMsg= hStart+" to "+(hEnd-(count-lstSearchRes.size())) +" of "+ totalRecords +" records.";
		    }
		}
	}
%>

<div class="table-inner">

		<div class="tab-information-hist">
			<div class="tab-nav">
					<a id="srView" class="active btn" style="padding: 5px 12px;" href="javascript:void fnSrView()"><b>SR View</b></a>
					<a id="tskView" class="btn" style="padding: 5px 12px;" href="javascript:void fnTaskView()"><b>Task View</b></a>
					<a id="dwnldExl" href="javascript:void fnExcelDownload()" class="active btn" style="padding: 5px 12px; float:right"><b>Download Excel</b></a>
			</div>	
	  </div>
<div id="historyTblDiv" >	
<%
if(lstSearchRes!=null && lstSearchRes.size()>0){
%>			
 	<div id="paging1">

		<table width="100%" id="pgLinks">
	<tr align="left">
	
	<td align="left">
	<%
	 int nop=totalLks;
	 
	 
	 if(nop==0){ // no rows
		 
	 }else if(0<nop  && nop<=1){
		 
		 
	 }else if(nop>1){
	
		 if(nop>10 ){
		  
			 if(pageNumber>2){
				 
					%>
					 <a href="#" onclick="getFsrHistory('1','<%=sortBy%>','<%=sortOrder%>');">First</a>
					<% 
				 }
			 if(pageNumber>1){
				 
				%>
				 <a href="#" onclick="getFsrHistory('<%=(pageNumber-1) %>','<%=sortBy%>','<%=sortOrder%>');"> Prev</a>
				<% 
			 }
			 for(int k=pageNumber;k<(pageNumber+10) && k<=totalLks ;k++){
			  %>
			   <a id="a<%=k%>" href="#" onclick="getFsrHistory('<%=k %>','<%=sortBy%>','<%=sortOrder%>');"><%=k %></a>
	 		  <% 
		     }
			 
			 if( (pageNumber+1) <= nop){
				 %>
				  <a href="#" onclick="getFsrHistory('<%= (pageNumber+1) %>','<%=sortBy%>','<%=sortOrder%>');">Next</a>
				 <%
			 }
			 %>
			  <a href="#" onclick="getFsrHistory('<%= nop %>','<%=sortBy%>','<%=sortOrder%>');">Last</a>
			 <%
		 }
		 
		 if(nop<=10){
			 for(int k=1;k<=nop;k++){
				%>
				 <a id="a<%=k %>" href="#" onclick="getFsrHistory('<%= k %>','<%=sortBy%>','<%=sortOrder%>');"><%= k %></a>
				<% 
			 }
		 }
	 
	 }else{
		 
	 }
	 
	 %>
	</td> 
	<%
	if(!("".equals(totalCountMsg)) && !("".equals(totalCountMsg))){
	%>
		<td width="20%" align="right" id="showing"><b>Showing</b><%=totalCountMsg %></td>		
	<%} %>
	</tr>

 </table> 
</div>
<%
}
%>
<!-- <div class="table-wrapper">
	<div class="table-inner"> -->
		<!-- <table class="model-table" id="fsrTbl"> -->
<div id="srHistTbl" style="">		
	<div id="scrolltbl" style="overflow: auto; overflow-y: hidden; width:99%;">
		<table id="taskTable" class="model-table" cellspacing="1" width="151%" style="border-width:0px">		
			<tbody>
				<tr>
					<th width=1%></th>
					<th width=15%><a href="Javascript:fnSortFilterCriteria('FSR_NUM','<%=pageNumber%>')">Service Request#</a></th>
					<th width=10%><a  href="Javascript:fnSortFilterCriteria('FSR_CREATION_DATE','<%=pageNumber%>')">Creation Date</a></th>
					<th width=8% nowrap><a href="Javascript:fnSortFilterCriteria('FSR_TYPE','<%=pageNumber%>')">SR Type</a></th>
					<th width=7%><a href="Javascript:fnSortFilterCriteria('FSR_STS','<%=pageNumber%>')">SR Status</a></th>
					<th width=20%;><a href="Javascript:fnSortFilterCriteria('PROBLEM_TYPE_NAME','<%=pageNumber%>')">Problem Code</a></th>
					<th width=20%;><a href="Javascript:fnSortFilterCriteria('PROBLEM_NOTE','<%=pageNumber%>')">Problem Note</a></th>
					<th width=20%;><a href="Javascript:fnSortFilterCriteria('MOBILE_NOTE','<%=pageNumber%>')">Mobile Note</a></th>
					<th width=8%;><a style="color:#FFFFFF" href="Javascript:fnSortFilterCriteria('LAST_METER','<%=pageNumber%>')">Primary Meter</a></th>
					<th width=6%;><a href="Javascript:fnSortFilterCriteria('RESPONSE_TIME','<%=pageNumber%>')">Response Time</a></th>
					<th width=6%;>Restore Time</th>
					<th width=15%;><a href="Javascript:fnSortFilterCriteria('TECH_PROBLEM_CODE','<%=pageNumber%>')">Latest Tech Problem Code</a></th>
					<th width=15%;><a href="Javascript:fnSortFilterCriteria('CORRECTION_CODE','<%=pageNumber%>')">Correction Code</a></th>
					<th width=15%;><a href="Javascript:fnSortFilterCriteria('LOCATION_CODE','<%=pageNumber%>')">Location Code</a></th>
					<th width=15%;><a href="Javascript:fnSortFilterCriteria('REASON_CODE','<%=pageNumber%>')">Reason Code</a></th>					
					<th width=8%;><a href="Javascript:fnSortFilterCriteria('MACHINE_STATUS','<%=pageNumber%>')">Machine Status</a></th>		
					<th width=15%;><a href="Javascript:fnSortFilterCriteria('CREATED_BY','<%=pageNumber%>')">Created By</a></th>
					<td>&nbsp;</td>
				</tr>
			</tbody>
		<%
		if(lstSearchRes!=null && lstSearchRes.size()>0){
			int clspCnt =1;
			for(int i=0;i<lstSearchRes.size();i++){
				CanonE307SRHistoryNewRec histObj = (CanonE307SRHistoryNewRec)lstSearchRes.get(i);
				ArrayList tskLst = (ArrayList)srObj.getTaskDet(util.checkNull(histObj.getStrFSR()));
				if(i==0)
					strFsrNum=util.checkNull(histObj.getStrFSR());
				
				String bclr  = "";
				if((i%2) == 0)
				{
					bclr   = "eventableDataCell";
				}
				else
				{
				    bclr = "oddtableDataCell";
				}
				%>
				<tr id='srRow<%=i%>'>
					<td style="width:1%;"><input type=radio name="histSelect" id="histSelect<%=i%>" value="<%=i%>" onclick="fnPopulateInfo('<%=util.checkNull(histObj.getStrFSR()) %>')" style="border:0px;"></td>
					<input type="hidden" id="fsrNum<%=i%>" name="fsrNum<%=i%>" value="<%=util.checkNull(histObj.getStrFSR()) %>">
					<td align="center" style="width:5%;" nowrap> 
					<a id="link<%=i%>" class="btn_red_sm" onclick="setTaskRows(<%=util.checkNull(histObj.getStrFSR()) %>,'<%=i%>')" href='javascript:void(0);'>+</a>
					<%-- <a class="btn_red_sm" data-toggle="collapse" href="#collapse<%=clspCnt%>">+</a> --%>
					    <a href="javascript:fnGetTaskDetails('<%=util.checkNull(histObj.getStrFSR()) %>','<%= util.checkNull(histObj.getStrEOLFlg()) %>')" style="color: #cc0000;"> <%=util.checkNull(histObj.getStrFSR()) %> <br /></a>  
					</td>
					<td align="center" style="width:9%;"><%=util.checkNull(utiSrObj.getTmZone(histObj.getStrPostalCd(), histObj.getStrCntryCd(), histObj.getStrCreationDt())) %></td>
					<td align="center" style="width:8%;"><%=util.checkNull(histObj.getStrFsrType()) %></td>
					<td align="center" style="width:8%;" nowrap><%=util.checkNull(histObj.getStrFSRSts()) %></td>
					<td align="center" style="width:20%;"><%=util.checkNull(histObj.getStrSvcPblmTpCd()) %></td>
					<td align="center" style="width:20%;"><%=util.checkNull(histObj.getStrPblmNt()) %></td>
					<td align="center" style="width:20%;" nowrap><%=util.checkNull(histObj.getStrMblNt()) %></td>
					<td align="center" style="width:8%;" nowrap><%=util.checkNull(histObj.getStrLastMtrRd()) %></td>
					<td align="center" style="width:8%;"><%=util.checkNull(histObj.getStrRespTm()) %></td>
					<td align="center" style="width:8%;"><%=util.checkNull(histObj.getStrRstrTm()) %></td>
					<td align="center" style="width:15%;"><%=util.checkNull(histObj.getStrTskPblmCd()) %></td>
					<td align="center" style="width:15%;"><%=util.checkNull(histObj.getStrCorrectionCd()) %></td>	
					<td align="center" style="width:15%;"><%=util.checkNull(histObj.getStrLocationCd()) %></td>
					<td align="center" style="width:15%;"><%=util.checkNull(histObj.getStrRsnCd()) %></td>
					<td align="center" style="width:8%;"><%=util.checkNull(histObj.getStrMachSts()) %></td>
					<td align="center" style="width:15%;"><%=util.checkNull(histObj.getStrCreatedBy()) %></td>	<td>&nbsp;</td>															
				</tr>
				<tr style="display: none;" class="task_<%=util.checkNull(histObj.getStrFSR()) %>"><td></td><td></td>
						<th>Task#</th>
						<th>Task Type</th>
						<th>Task Status</th>
						<th>Problem Code</th>
						<th>Problem Note</th>						
						<th>Mobile Note</th>
						<th>Primary Meter</th>
						<th>Response Time</th>												
						<th>Labor Start</th>		
						<th>Labor End</th>	
						<th>Problem Code</th>	
						<th>Correction Code</th>						
						<th>Location Code</th>
						<th>Reason Code</th>
						<th>Machine Status</th>	
						<th>Technician</th>																																													
					</tr>
					<%
						if(tskLst!=null && tskLst.size()>0){
							for(int k=0;k<tskLst.size();k++){
								CanonE307SRViewTskDtlsRec tskObj = (CanonE307SRViewTskDtlsRec)tskLst.get(k);
					%>
						<tr style="display: none;" class="task_<%=util.checkNull(histObj.getStrFSR()) %>"><td></td><td></td>
							<td><a href="javascript:fnGetTaskInfo('<%=util.checkNull(histObj.getStrFSR()) %>','<%=tskObj.getTaskNum() %>', '<%=histObj.getStrPostalCd() %>', '<%=histObj.getStrCntryCd() %>', '<%= util.checkNull(histObj.getStrEOLFlg()) %>')" style="color: #cc0000;"> <%=util.checkNull(tskObj.getTaskNum()) %></a></td>
							<td><%=util.checkNull(tskObj.getTskTpe()) %></td>
							<td><%=util.checkNull(tskObj.getTskSts()) %></td>
							<td><%=util.checkNull(tskObj.getTskPblmCd()) %></td>
							<td><%=util.checkNull(tskObj.getTskPblmNt()) %></td>
							<td><%=util.checkNull(tskObj.getMobileNt()) %></td>
							<td><%=util.checkNull(tskObj.getPrmryMtr()) %></td>
							<td><%=util.checkNull(tskObj.getTskRespTm()) %></td>
							<td><%=util.checkNull(utiSrObj.getTmZone(histObj.getStrPostalCd(), histObj.getStrCntryCd(), tskObj.getTskLbrStrtTm())) %></td>
							<td><%=util.checkNull(utiSrObj.getTmZone(histObj.getStrPostalCd(), histObj.getStrCntryCd(), tskObj.getTskLbrEndTm())) %></td>
							<td><%=util.checkNull(tskObj.getPblmTpCd()) %></td>
							<td><%=util.checkNull(tskObj.getCorCtnCd()) %></td>
							<td><%=util.checkNull(tskObj.getTskLocTnCd()) %></td>
							<td><%=util.checkNull(tskObj.getTskRsnCd()) %></td>
							<td><%=util.checkNull(tskObj.getTskMachSts()) %></td>
							<td><%=util.checkNull(tskObj.getTskTech()) %></td>
																																																																																																							
						</tr>
					<%							
							}
						}

					%>
	   <%
	   		clspCnt++;
			}

		}else{
			if(checkParam){
			%>
		<tr>
		<td class='eventableDataCell' colspan='18' align='left'> No records found. Please adjust your search criteria.&nbsp;</td>
		</tr>	
	
			<%
			}else{
		%>
		<tr>
		<td class='eventableDataCell' colspan='18' align='left'> Please use search criteria for Service Requests.&nbsp;</td>
		</tr>	

		<%	
			}
		}
		%>
		</table>
	</div>
	</div>
	</div>
	</div>
	<table>
		<tr><td colspan=18>&nbsp;</td></tr>
		<tr><td colspan=18>&nbsp;</td></tr>
		<tr><td colspan=18>&nbsp;</td></tr>
		<tr><td colspan=18>&nbsp;</td></tr>
	</table>
	
	<div id="hisInfoDiv"></div>
	<div id="lovInfoDiv"></div>
	<div id="dlg"></div>


<script>
$(document).ready(function() {
	 $("#a<%=pageNumber%>").css({"color":"white","background-color":"#A10304"});
	 var fsrNum = $('#fsrNum0').val();
	 $("#histSelect0").prop("checked", true);	 
	 fnPopulateInfo(fsrNum);
});
function fnValidateParams(){
	var searchVal='false';

	$("#machTbl input").each(function() {
	    var input = $(this);
	    if (input.val() == input.attr('placeholder')) {
	      input.val('');
	    }
	});
	$("#machTbl input").each(function() {
	      if(this.value!='null' && $.trim(this.value).length>0){
	    	  searchVal="true";
	      }
	  });
	
	var srStatus = $('#srStatus').val();
	var taskSts = $('#taskSts').val();
	var taskType = $('#taskType').val();
	
	if(searchVal=='false' && (srStatus=='null' || srStatus=='') && (taskSts=='null' || taskSts=='') && ( taskType=='null' || taskType=='')){
		$('#eMsg').html("Please enter atleast one value for search criteria.");
		$("#eMsg").css({"color": "red", "font-size": "15"}); 
		 $("#errorWidget").show();
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
				return false;
	}else{
		return true;
	}
}
function fnSbmtFrm(){
	$('#eMsg').html("");
	 $("#errorWidget").hide();
	var searchVal='false';

	// if(navigator.userAgent.indexOf('MSIE') > -1){
			$("#machTbl input").each(function() {
			    var input = $(this);
			    if (input.val() == input.attr('placeholder')) {
			      input.val('');
			    }
			});
	//}	
	$("#machTbl input").each(function() {
	      if(this.value!='null' && $.trim(this.value).length>0){
	    	  searchVal="true";
	      }
	  });
	var srStatus = $('#srStatus').val();
	var taskSts = $('#taskSts').val();
	var taskType = $('#taskType').val();
	
	if(searchVal=='false' && (srStatus=='null' || srStatus=='') && (taskSts=='null' || taskSts=='') &&( taskType=='null' || taskType=='')){
		$('#eMsg').html("Please enter atleast one value for search criteria.");
		$("#eMsg").css({"color": "red", "font-size": "15"}); 
		 $("#errorWidget").show();
		//   if(navigator.userAgent.indexOf('MSIE') > -1){
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
		//	    }		 
	}else{	
		//$('#frmHistSrch #scratchPad').val( $("#toolTip textarea").val() );
		showPleaseWait();
		var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqHistory.jsp";
		document.frmHistSrch.action = urlDetail;
		document.frmHistSrch.submit();	
	}
}
<%-- function fnExcelDownload1(){
	showPleaseWait();
	var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqHistory.jsp?exclDwnld=Y";
	document.frmHistSrch.action = urlDetail;
	document.frmHistSrch.submit();		
} --%>

function fnClrFrm(){
	$("#machTbl input").each(function() {
	      this.value = "";
	      var input = $(this);
		/*  if (input.val() == '' || input.val() == input.attr('placeholder')) {
		    input.addClass('placeholder');
		    input.val(input.attr('placeholder'));
		  }*/
	  });
//	$('select option:first-child').attr("selected", "selected");
	$('select option:nth-child(1)').prop("selected", true);
	showPleaseWait();
	var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqHistory.jsp";
	document.frmHistSrch.action = urlDetail;
	document.frmHistSrch.submit();	
}

function closeDlg(){
	var dlgId="#dlg";
	$(dlgId).html("");
	$(dlgId).dialog("close");
	$(dlgId).dialog("destroy");
	
}

function fnGetTaskDetails(fsr, eolFlg){
	if(eolFlg == 'Y'){
		fnGetEolMsg(fsr, "FSR", fsr, "","","");
	}else{
		var url = '<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?showVend=Y&fsr='+fsr;
		window.open(url,"ServiceRequestSummary","toolbar=no, menubar=no,location=no,directories=no,status=yes, scrollbars=yes,resizable=yes,target=_blank");
		window.moveTo(0,0);
		window.resizeTo(screen.width,screen.height-100);
	}
}
function fnGetTaskInfo1(fsr, tNum){
	var url = '<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?showVend=Y&fsr='+fsr+'&tNum='+tNum+'#collapse'+tNum;
	window.open(url,"ServiceRequestSummary","toolbar=no, menubar=no,location=no,directories=no,status=yes, scrollbars=yes,resizable=yes,target=_blank");
	window.moveTo(0,0);
	window.resizeTo(screen.width,screen.height-100);
}
	function fnGetTaskInfo(fsr, tNum, postalCd, cntryCd, eolFlg){
		if(eolFlg == 'Y'){
			fnGetEolMsg(fsr, "TASK", fsr, tNum, postalCd, cntryCd);
		}else{ 
			var url='<%=ctxPath%>/e307/jsp/canonE307ServiceRequestDebrief.jsp?fsr='+fsr+'&debriefTsk='+tNum+'&postalCd='+postalCd+'&cntryCd='+cntryCd;
			window.open(url,"Debrief","toolbar=no, menubar=no,location=no,directories=no,status=yes, scrollbars=yes,resizable=yes,target=_blank");
			window.moveTo(0,0);
			window.resizeTo(screen.width,screen.height-100);
		}
	}

	function setRowStyles(tbl) {
		$(tbl + " tbody tr:odd").each(function() {
			$(this).addClass("oddRow");
		});
	}

	function fnGetEolMsg(fsrNumber, source, fsr, tNum, postalCd, cntryCd){
	  var modelName = 'eolDlg';

	  var urlDetail = 'canonE307GetSrlEol.jsp?fsrNumber='+fsrNumber+'&source='+source;
	  	   urlDetail = urlDetail+'&taskNum='+tNum+'&postalCd='+postalCd+'&cntryCd='+cntryCd;
	  	   
			modelName = "#"+modelName;
	   showPleaseWait();
	   $(modelName).dialog({
					height: 650,
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
function getFsrHistory1(pageNum, sortBy, sortOrder){
	$("#machTbl input").each(function() {
	    var input = $(this);
	    if (input.val() == input.attr('placeholder')) {
	      input.val('');
	    }
	});
	$('#pageNum').val(pageNum);
	var serNum = $('#serialNumber').val();
	var tagNum = $('#tagNum').val();
	var solName = $('#solName').val();
	var model = $('#model').val();
	var acctNum = $('#acctNum').val();
	var custName = $('#custName').val();
	var servRqstNumber = $('#servRqstNumber').val();
	var taskNumber = $('#taskNumber').val();
	var createdBy = $('#createdBy').val();
	var creationDt = $('#creationDt').val();
	
/*	var qryStr="pageNum="+pageNum;
	qryStr = qryStr+"&serNum="+serNum+"&tagNum="+tagNum;
	qryStr = qryStr+"&solName="+solName+"&model="+model;
	qryStr = qryStr+"&acctNum="+acctNum+"&custName="+custName;
	qryStr = qryStr+"&sortBy="+sortBy+"&sortOrder="+sortOrder;
	qryStr = qryStr+"&servRqstNumber="+servRqstNumber+"&taskNumber="+taskNumber;
	qryStr = qryStr+"&createdBy="+createdBy+"&creationDt="+creationDt;	*/
	 var qryStr=$('#frmHistSrch').serialize();
	showPleaseWait();
	 $.ajax({
			url:"canonE307ServReqFsrHistIncl.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){  
				hidePleaseWait();
				document.getElementById("historyTblDiv").innerHTML = $.trim(data);
				setRowStyles(".fsrTbl");
				$("#paging1 #a"+pageNum).css({"color":"white","background-color":"#A10304"});
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

function getFsrHistory(pageNum, sortBy, sortOrder){
	$("#machTbl input").each(function() {
	    var input = $(this);
	    if (input.val() == input.attr('placeholder')) {
	      input.val('');
	    }
	});
	$('#pageNum').val(pageNum);
	var serNum = $('#serialNumber').val();
	var tagNum = $('#tagNum').val();
	var solName = $('#solName').val();
	var model = $('#model').val();
	var acctNum = $('#acctNum').val();
	var custName = $('#custName').val();
	var servRqstNumber = $('#servRqstNumber').val();
	var taskNumber = $('#taskNumber').val();
	var createdBy = $('#createdBy').val();
	var creationDt = $('#creationDt').val();
	var taskSts = $('#taskSts').val();
	var srStatus = $('#srStatus').val();
	
	/*var qryStr="pageNum="+pageNum;
	qryStr = qryStr+"&serNum="+serNum+"&tagNum="+tagNum;
	qryStr = qryStr+"&solName="+solName+"&model="+model;
	qryStr = qryStr+"&acctNum="+acctNum+"&custName="+custName;
	qryStr = qryStr+"&sortBy="+sortBy+"&sortOrder="+sortOrder;
	qryStr = qryStr+"&servRqstNumber="+servRqstNumber+"&taskNumber="+taskNumber;
	qryStr = qryStr+"&createdBy="+createdBy+"&creationDt="+creationDt;	
	qryStr = qryStr+"&taskSts="+taskSts+"&srStatus="+srStatus;	*/
	var qryStr=$('#frmHistSrch').serialize();
	showPleaseWait();
	 $.ajax({
			url:"canonE307ServReqFsrHistNewIncl.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){  
				hidePleaseWait();
				document.getElementById("historyTblDiv").innerHTML = $.trim(data);
				setRowStyles(".fsrTbl");
				$("#paging1 #a"+pageNum).css({"color":"white","background-color":"#A10304"});
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

function fnSortFilterCriteria(sortBy, pageNum){
    var existingSortOrder = $("#sortOrder").val();
    var existingSortBy = $("#sortBy").val();   
    $("#pageNum").val(pageNum);
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
    getFsrHistory(pageNum, sortBy, existingSortOrder)
  }
function fnSortTskFilterCriteria(sortBy, pageNum){
    var existingSortOrder = $("#tskSortOrder").val();
    var existingSortBy = $("#tskSortBy").val();   
    $("#pageNum").val(pageNum);
    if(sortBy==existingSortBy){
      if(existingSortOrder=='asc'){
        existingSortOrder = 'desc';
      }else{
        existingSortOrder = 'asc'
      }
      $("#tskSortOrder").val(existingSortOrder);
    }else{
      existingSortOrder ='asc';     
       $("#tskSortOrder").val(existingSortOrder);
       $("#tskSortBy").val(sortBy);
    }
    getTaskView(pageNum, sortBy, existingSortOrder)
  }  
$(function(){    
	
	     $(".datePicker").datepicker({
			 dateFormat: 'M dd yy',
			 changeMonth: true,
			 changeYear: true
		 });
	
  //  if(navigator.userAgent.indexOf('MSIE') > -1){
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
   // }
 });
$(document).keypress(function(e) {
	 var searchVal="false";
	 var srSrchVal = "false";
//	 $('input').on("focus", function(){
	  if(e.which == 13) {
		// if(navigator.userAgent.indexOf('MSIE') > -1){
			 $("#machTbl input").each(function() {
			    var input = $(this);
			    if (input.val() == input.attr('placeholder')) {
			      input.val('');
			    }
			 });
		// }			  
		$("#machTbl input").each(function() {
		      if(this.value!='null' && $.trim(this.value).length>0){
		    	  searchVal="true";
		      }
		  });
		if(searchVal=='true'){
			fnSbmtFrm();
		}
	  }
//	});

}); 
var newWin=null;
function fnExcelDownload()
{
	 var searchVal="false";
	
	$("#machTbl input").each(function() {
	    var input = $(this);
	    if (input.val() == input.attr('placeholder')) {
	      input.val('');
	    }
	});
	$("#machTbl input").each(function() {
	      if(this.value!='null' && $.trim(this.value).length>0){
	    	  searchVal="true";
	      }
	  });
	
	var srStatus = $('#srStatus').val();
	var taskSts = $('#taskSts').val();
	var taskType = $('#taskType').val();
	
	if(searchVal=='false' && (srStatus=='null' || srStatus=='') && (taskSts=='null' || taskSts=='') && ( taskType=='null' || taskType=='')){
		$('#eMsg').html("Please enter atleast one value for search criteria.");
		$("#eMsg").css({"color": "red", "font-size": "15"}); 
		 $("#errorWidget").show();
		//   if(navigator.userAgent.indexOf('MSIE') > -1){
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
		//	    }		 
	}else{
		 //	showPleaseWait();
			var serNum = $('#serialNumber').val();
			var tagNum = $('#tagNum').val();
			var solName = $('#solName').val();
			var model = $('#model').val();
			var acctNum = $('#acctNum').val();
			var custName = $('#custName').val();
			var servRqstNumber = $('#servRqstNumber').val();
			var taskNumber = $('#taskNumber').val();
			var createdBy = $('#createdBy').val();
			var creationDt = $('#creationDt').val();
			var sortOrder = $('#sortOrder').val();
			var sortBy = $('#sortBy').val();
			var taskSts = $('#taskSts').val();
			var srStatus = $('#srStatus').val();
			var taskType = $('#taskType').val();
			
			var qryStr="?serialNumber="+encodeURIComponent(serNum)+"&tagNum="+encodeURIComponent(tagNum);
			qryStr = qryStr+"&solName="+encodeURIComponent(solName)+"&model="+encodeURIComponent(model);
			qryStr = qryStr+"&acctNum="+encodeURIComponent(acctNum)+"&custName="+encodeURIComponent(custName);
			qryStr = qryStr+"&sortBy="+encodeURIComponent(sortBy)+"&sortOrder="+encodeURIComponent(sortOrder);
			qryStr = qryStr+"&servRqstNumber="+encodeURIComponent(servRqstNumber)+"&taskNumber="+encodeURIComponent(taskNumber);
			qryStr = qryStr+"&createdBy="+encodeURIComponent(createdBy)+"&creationDt="+encodeURIComponent(creationDt);	
			qryStr = qryStr+"&taskSts="+encodeURIComponent(taskSts)+"&srStatus="+encodeURIComponent(srStatus);	
			qryStr = qryStr+"&taskType="+encodeURIComponent(taskType)+"&checkProgress=N";	
			
		    
		    var url  = "canonE307SRHistoryReport.jsp"+qryStr;
		  //  var url  = "CanonE307ExcelDownoad.jsp"+qryStr;
		    
			    
		  document.location.href= url;
		  
    	// newWin = window.open(url);
		//  window.parent.focus();
		//  checkDownloadProgress('I');

	}
	
}

function checkDownloadProgress(check){
	//newWin.parent().focus();
	var urlDetail = "canonE307SRHistoryReport.jsp?checkProgress=Y";
	var dFlg = "<%= (String)request.getSession().getAttribute("dowloadFlag")%>";
	
	if(dFlg == 'P'|| check=='I'){
	 $.ajax({
			url: "canonE307SRHistoryReport.jsp?checkProgress=Y",
			cache: false,
			success: function(data){
				var result ='';
				result = $.trim(data);
				console.log("result: "+result);
			//	if(result!='null' && result!=''){
					if( result.indexOf('P') > -1){
						 setTimeout(function (){
								 checkDownloadProgress('I');	
							}, 100);
						 
					}else{
						hidePleaseWait();
						newWin.close();
					}	 
					
			//		 } 
				}
		});
	}else{
		hidePleaseWait();
	}
	
}

function setTaskRows(srNumber, iVal){

	 $(".task_"+srNumber).toggle();
	 if($('#link'+iVal).text()=='+'){
		 $('#link'+iVal).text('-');
	 }else{
		 $('#link'+iVal).text('+');
	 }
	 
	 $('#link'+iVal).attr("title",'-');
}
function fnPopulateInfo(fsrNum){
			
		//if(fsrNum.length != 0){
			if(fsrNum!='' && fsrNum!='null'){
			var urlDetail = 'canonE307HistHdrLvllInfo.jsp';
			var qryStr= "fsrNum="+fsrNum;

		    $.ajax({
					url: urlDetail,
					cache: false,
					data : qryStr,
					async: false,
					type : "POST",			
					success: function(data){
						result = $.trim(data);
						if(result!='null' && result!=''){
							//	console.log("model: "+ $.trim(result.split(":")[0]));							
								$('#mdlId').html($.trim(result.split(":")[0]));
								$('#serial').html($.trim(result.split(":")[1]));	
								$('#branch').html($.trim(result.split(":")[2]));								
								$('#machMngr').html($.trim(result.split(":")[3]));
								$('#custNm').html($.trim(result.split(":")[4]));
								$('#address').html($.trim(result.split(":")[5]));
								$('#city').html($.trim(result.split(":")[6]) + ", " + $.trim(result.split(":")[7])+", "+$.trim(result.split(":")[8]));
								$('#cntctNm').html($.trim(result.split(":")[9]));
								$('#cntctPhn').html($.trim(result.split(":")[10]));								
								$('#emailAdd').html($.trim(result.split(":")[11]));
							 } 
						}
				});		
		}
}
function fnSrView(){
	if(fnValidateParams()){
		$(".active").each(function() {
			 $(this).removeClass("active");
		 });
			$('#srView').addClass("active");
			$('#dwnldExl').addClass("active");
			getFsrHistory('1','','asc');
	}

}
function fnTaskView(){
	if(fnValidateParams()){
		$(".active").each(function() {
			 $(this).removeClass("active");
		 });
			$('#tskView').addClass("active");
			$('#dwnldExl').addClass("active");
			getTaskView('1','','asc');
	}

}
function getTaskView(pageNum, sortBy, sortOrder){
	$("#machTbl input").each(function() {
	    var input = $(this);
	    if (input.val() == input.attr('placeholder')) {
	      input.val('');
	    }
	});
	$('#pageNum').val(pageNum);
	var serNum = $('#serialNumber').val();
	var tagNum = $('#tagNum').val();
	var solName = $('#solName').val();
	var model = $('#model').val();
	var acctNum = $('#acctNum').val();
	var custName = $('#custName').val();
	var servRqstNumber = $('#servRqstNumber').val();
	var taskNumber = $('#taskNumber').val();
	var createdBy = $('#createdBy').val();
	var creationDt = $('#creationDt').val();
	var taskSts = $('#taskSts').val();
	var srStatus = $('#srStatus').val();
	var taskType = $('#taskType').val();
	
	/*var qryStr="pageNum="+pageNum;
	qryStr = qryStr+"&serNum="+serNum+"&tagNum="+tagNum;
	qryStr = qryStr+"&solName="+solName+"&model="+model;
	qryStr = qryStr+"&acctNum="+acctNum+"&custName="+custName;
	qryStr = qryStr+"&sortBy="+sortBy+"&sortOrder="+sortOrder;
	qryStr = qryStr+"&servRqstNumber="+servRqstNumber+"&taskNumber="+taskNumber;
	qryStr = qryStr+"&createdBy="+createdBy+"&creationDt="+creationDt;
	qryStr = qryStr+"&taskSts="+taskSts+"&srStatus="+srStatus;
	*/
	var qryStr=$('#frmHistSrch').serialize();
	showPleaseWait();
	 $.ajax({
			url:"canonE307ServReqTskHistNewIncl.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){  
				hidePleaseWait();
				document.getElementById("historyTblDiv").innerHTML = $.trim(data);
				setRowStyles(".fsrTbl");
				$("#paging1 #a"+pageNum).css({"color":"white","background-color":"#A10304"});
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

function fnCloseSearchDlg(dId, source, fsr, tNum, postalCd, cntryCd){
	var modelName="#"+dId;
	$(modelName).html("");
    $(modelName).dialog("close");
    $(modelName).dialog("destroy");
    
    if(source == "TASK"){
    	var url='<%=ctxPath%>/e307/jsp/canonE307ServiceRequestDebrief.jsp?fsr='+fsr+'&debriefTsk='+tNum+'&postalCd='+postalCd+'&cntryCd='+cntryCd;
		window.open(url,"Debrief","toolbar=no, menubar=no,location=no,directories=no,status=yes, scrollbars=yes,resizable=yes,target=_blank");
		window.moveTo(0,0);
		window.resizeTo(screen.width,screen.height-100);
    }else{
		var url = '<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?showVend=Y&fsr='+fsr;
		window.open(url,"ServiceRequestSummary","toolbar=no, menubar=no,location=no,directories=no,status=yes, scrollbars=yes,resizable=yes,target=_blank");
		window.moveTo(0,0);
		window.resizeTo(screen.width,screen.height-100);
    }
}
</script>
</div>
<div id="eolDlg"></div>
</body>	

</html>
