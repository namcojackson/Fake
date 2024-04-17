<!-- $Header: canon_E193_csWorkbenchAssigned.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csWorkbenchAssigned.jsp - Workbench
 |   
 | DESCRIPTION
 |   
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	07/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 18-Dec-2006    Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 | 16-Sep-13   Hema Doniparthi	 ITG#475337
 | 03-Mar-2014    Kiran K               ITG 501837   type to contract branch num
 | NOTE: Col "Type" is replaced with "contract branch number" but the variable names not changed.
 | 29-Jan-2016  Mangala Shenoy  Modified for S21 changes.
 +=======================================================================--%>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_WBTicket" id="objWBOwnedTicket" scope="page" />
<%@ include file="canon_E193_csTopInc.jsp" %>
<% String requestURL = request.getRequestURL() == null ? "" : request.getRequestURL().toString(); %>

<%
		//Get User Id
		String iUserId = objCiSession.getUserId();
	// Menu Prompt
	strPageName = "Workbench";
	strSubMenuPageName = "Assigned";
	 
    // Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");
	
	//Get orgId and ResourceId
	int iOrgId = (int)objCiSession.getOrgId();

	/* ITG# 73987 : Begin */	
	String strRegionCode = (String)objCiSession.getRegionCode();	
        /* ITG# 73987 : End */
	if("".equals(strRegionCode) || "null".equals(strRegionCode)){
		strRegionCode="EAST_REGION";
	}
	//Start changes for S21 by Mangala
	//int iResourceId = (int)objCiSession.getResourceId();
	String iResourceId = objCiSession.getResourceId();		
	//End Changes for S21 by Mangala		
	String strOwnedYesNo = request.getParameter("ownedYesNo");

	String hDeptVal = request.getParameter("slDept");
	String hOwnVal = request.getParameter("slOwner");
	//Start changes for S21 by Mangala
	//int hResId = -1;
	String hResId = "";
	//End changes for S21 by Mangala		
	if ((hOwnVal == null) || ("".equalsIgnoreCase(hOwnVal)) || ("null".equalsIgnoreCase(hOwnVal))){
		hResId = iResourceId;
	}
			//Start changes for S21 by Mangala
	//else hResId = Integer.parseInt(hOwnVal);
	else{ 
		hResId = hOwnVal;
	}
	
	//End changes for S21 by Mangala		
	 	String strAction = request.getParameter("assignFlag");

	if("updateAssignee".equals(strAction)){
	// Object to invoke update methods
		Canon_E193_Assignment updateLineStsAssign = new Canon_E193_Assignment();
		String strTSize = request.getParameter("ticketSize");
		int size=-1;
		if(!("".equals(strTSize)) && (!("null".equals(strTSize)))){
			size = Integer.parseInt(strTSize);
		}

		if(size>0){
			for(int y=0;y<size;y++){
				String selItem = request.getParameter("selectedItem"+y);
				if(selItem!=null){
					ArrayList alTktStsAssignMulti = new ArrayList();
					Canon_E193_TicketLinesObj objTktStsAssign = new Canon_E193_TicketLinesObj();
					String strTLineDept = request.getParameter("hLineDept");
					String strLineRole = request.getParameter("hLineRole");
					String strLineResource = request.getParameter("hLineResource");
					//Start changes for S21 by Mangala
					//int iResId = 0;
					String iResId = "";		
					//End Changes for S21 by Mangala		
					String iRoleId = "";
					if(strLineResource != null && !("".equals(strLineResource)) && !("null".equalsIgnoreCase(strLineResource)) )
						//Start changes for S21 by Mangala
						//iResId = Integer.parseInt(strLineResource);
						iResId = strLineResource;		
						//End changes for S21 by Mangala		
					String strLineId = request.getParameter("lineId"+y);
					String strTicketId = request.getParameter("ticketId"+y);
					if(strLineRole != null && !("".equals(strLineRole)) && !("null".equalsIgnoreCase(strLineRole)) )
						iRoleId = strLineRole;						
					int iLineId = -1;
					if(strLineId != null && !("".equals(strLineId)) && !("null".equalsIgnoreCase(strLineId)) )
						iLineId = Integer.parseInt(strLineId);	
							
					objTktStsAssign.setLineId(iLineId);
					objTktStsAssign.setDeptCode(strTLineDept);
					objTktStsAssign.setRoleId(iRoleId);
					objTktStsAssign.setResourceId(iResId);
					objTktStsAssign.setCreatedBy(iUserId);
				
					alTktStsAssignMulti.add(objTktStsAssign);
					updateLineStsAssign.addAssignments(alTktStsAssignMulti); 	
					updateLineStsAssign.updateNotes(iOrgId, iLineId, iUserId);
					
					int iTicketId = -1;
					
					if(strTicketId != null && !("".equals(strTicketId)) && !("null".equalsIgnoreCase(strTicketId)) ){
						iTicketId = Integer.parseInt(strTicketId);
						updateLineStsAssign.sendEmailNotificationToAsignee(iTicketId, iLineId, requestURL, iUserId);
					}
				}
			}
		}
	}	
	 
	 
	// get departments
	System.out.println(" csWorkbenchAssigned:: "+ "IResourceId!! " +iResourceId +" IOrgId!! "+iOrgId+ " StrRegionCode!! "+ strRegionCode);
	ArrayList alWBDept = objWBOwnedTicket.getWBTktDept(iResourceId, iOrgId, strRegionCode);
	//System.out.println(" AlWBDept Size: "+ alWBDept.size() + " Data~~~ "+alWBDept.toString());
	if((hDeptVal == null || "".equals(hDeptVal)) && alWBDept != null && alWBDept.size() > 0) {
		hDeptVal = ((Canon_E193_WorkbenchObj)alWBDept.get(0)).getStrAttribute2();
		System.out.println("hDeptVal" + hDeptVal);
	}
   	// get owners for the department
   //	System.out.println(" csWorkbenchAssigned:: "+ " hDeptVal~~ " + hDeptVal + " iResourceId~~ "+iResourceId + "  iOrgId~~ "+iOrgId + " strRegionCode~~ "+strRegionCode);
   	
	ArrayList alWBAssigned = objWBOwnedTicket.getWBTktOwnerAssignee(hDeptVal, iResourceId, iOrgId, strRegionCode);
   // System.out.println(" AlWBAssigned~~~ "+ alWBAssigned.size() + " Data~~~ "+alWBAssigned.toString());
	int iPageNo = 0;
	String strPageNo = request.getParameter("iPageNo");
	if (strPageNo != null && !strPageNo.equalsIgnoreCase("null")) 
		iPageNo = (int)(Integer.parseInt(strPageNo));
	
	// Get Total Page Numbers
	int iTotPageNo = 0;
	String strTotPageNo = request.getParameter("iTotPageNo");
	if (strTotPageNo != null && !strTotPageNo.equalsIgnoreCase("null"))
		iTotPageNo = (int)(Integer.parseInt(strTotPageNo));
	
	String strOrderName = request.getParameter("orderName");
	String strOrderBy = request.getParameter("orderBy");
	if("null".equals(strOrderName) || "".equals(strOrderName)) strOrderName = null;
	if("null".equals(strOrderBy) || "".equals(strOrderBy)) strOrderBy = null;

//	System.out.println(" csWorkbenchAssigned:: "+ " hDeptVal~ "+ hDeptVal +" hResId~ " + hResId + " iOrgId~ "+iOrgId + " strOrderName~ " + strOrderName + " strOrderBy~ "+strOrderBy+ " iPageNo~ " + iPageNo+ " iTotPageNo~ "+iTotPageNo);
	// Get tickets for that resource
	ArrayList alWBOwnedTicket = objWBOwnedTicket.getAssignedTickets(hDeptVal, hResId, iOrgId, "ASSIGNED", strOrderName, strOrderBy, iPageNo, iTotPageNo, iResourceId);
	//System.out.println("AlWBOwnedTicket~~ "+ alWBOwnedTicket.size() + " ~~~Data~~~ " + alWBOwnedTicket );
	if(alWBOwnedTicket != null && alWBOwnedTicket.size() > 0) {
		iPageNo = ((Integer)alWBOwnedTicket.get(0)).intValue();
	//	System.out.println(" IPageNo! "+ iPageNo);
		alWBOwnedTicket.remove(0);
		iTotPageNo = ((Integer)alWBOwnedTicket.get(0)).intValue();
		//System.out.println(" iTotPageNo! "+ iPageNo);
		alWBOwnedTicket.remove(0);
	}else{
		iPageNo = 0;
		iTotPageNo = 0;
	}

%>
	
<%@ include file="canon_E193_csMenuInc.jsp" %> 

<script language="JavaScript" type="text/javascript">

var orderAsc = "asc";
var orderDesc = "desc";
var tArray = new Array("tktNo","type","catDesc","lineNo","lineCatDesc","date", 
		"status", "severity", "daysOpen", "linesUnassigned", "res", "dept", 
		"acctName" ,"profileName","lastupdatedate");

function fnOrderBy(name) {
	var objForm = document.wbAssignedForm;
	objForm.ownedYesNo.value = "<%=strOwnedYesNo%>";
	var vOrdName = objForm.orderName.value;
	if(name == tArray[0] && vOrdName == "null") {
		vOrdName = tArray[0];
		objForm.orderBy.value = orderAsc;
	}
	if(vOrdName != name) objForm.orderBy.value = 'null';
	objForm.orderName.value = name;
	var reqOrderBy = objForm.orderBy.value;
	
	for(i=0; i<tArray.length; i++) {
		if(name == tArray[i] && (reqOrderBy == 'null' || reqOrderBy == orderDesc)) {
			objForm.orderBy.value = orderAsc;
		}else if(name == tArray[i] && reqOrderBy == orderAsc) {
			objForm.orderBy.value = orderDesc;
		}
	}
	objForm.action = "canon_E193_csWorkbenchAssigned.jsp";
	objForm.submit();
	return false;
}

function action_func2(iPageIndex) {
	var objForm = document.wbAssignedForm;
	objForm.iPageNo.value = iPageIndex;
	objForm.ownedYesNo.value = "<%=strOwnedYesNo%>";
	objForm.action = "canon_E193_csWorkbenchAssigned.jsp";
	objForm.submit();
}	

function action_go()
{
	/* var selectTicketOwner = $("slOwner").val(); */
	var selectTicketAssignee = document.getElementById("slOwner").value;
	//var value = selectTicketOwner.options[selectTicketOwner.selectedIndex].value;
	//alert(" selectTicketOwner = "+ selectTicketAssignee);
	document.wbAssignedForm.ownedYesNo.value = "<%=strOwnedYesNo%>";
	document.wbAssignedForm.iPageNo.value = "0";
	document.wbAssignedForm.iTotPageNo.value = "0";
	document.wbAssignedForm.slOwner.value = selectTicketAssignee;
	document.wbAssignedForm.action = "canon_E193_csWorkbenchAssigned.jsp";
	document.wbAssignedForm.submit();
}
function fnGetAssignee(){
	resetErroMsg();
	var totSize= document.getElementById("ticketSize").value;
	var checkFlag = "";
	for(i=0;i<totSize;i++){
		if(document.getElementById("selectedItem"+i).checked == true){
			checkFlag="true";
			break;
		}
	}
	if(checkFlag== "true"){
		var assignRes = document.getElementById("resourceId").value;
		var orgId = document.getElementById("orgId").value;
		var regionCode = document.getElementById("regionCode").value;
		var assignDept='';
		var assignRole='';
		
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
	 	  var qryStr="hPageFrom=WorkbenchAssigned&hDeptSel=yes&hRoleSel=yes&assignFlag=yes&assignDept="+assignDept+"&assignRole="+assignRole+"&hResourceId="+assignRes;
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
	     $(".ui-dialog").css({"top":"200px", "position":"fixed"}); 
	     var detectBrowser=msieversion();
 	     if("otherbrowser"!=detectBrowser)
 	 		{
 	 		$('.ui-widget-overlay').remove();
 	 		}
	}else{
		displayErrorMsg("Please select atleast one ticket to get assigned.");
	}
}

/*function dept_select()
{	   
	var selectedRoleVal=$('#assignRole :selected').val();	
	var hRoleVal=$('#hRoleVal').val();
	var qryStr="assignDept="+encodeURIComponent( $('select[name="assignDept"]').val() )+"&hDeptSel=yes&hRoleSel=yes&hChgDeptSel=yes";
	//qryStr+="&assignRole="+encodeURIComponent( $('select[name="assignRole"]').val() );	
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

function resource_select(selectField) {
	   var selectedResourceName=$(selectField).closest('tr').find('td[class="assignResource"]').text();	  
	   	document.AssignmentForm.hResourceId.value = selectField.value;
	   	document.AssignmentForm.hResourceIdText.value=selectedResourceName;
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
} */

function getAssignment(deptVal, roleVal, resourceVal, deptDesc, assignDesc, resourceDesc){
	document.wbAssignedForm.ownedYesNo.value = "<%=strOwnedYesNo%>";
	document.getElementById("hLineDept").value=deptVal;
	document.getElementById("hLineResource").value=resourceVal;
	document.getElementById("hLineRole").value=roleVal;
	
	document.wbAssignedForm.assignFlag.value = "updateAssignee";
	document.wbAssignedForm.action = "canon_E193_csWorkbenchAssigned.jsp";
	document.wbAssignedForm.submit();
}
</script>	

<div id="main_content">
	<div id="page_top">
	   <h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strWorkbenchAssignedT1%></h1>				
	</div>
<form name="wbAssignedForm" method="post">
	<!-- hidden variables -->
	<input type="hidden" name="hPageFrom" value="WBAssigned" >
	<input type="hidden" name="ownedYesNo" value="" >
	<input type="hidden" name="iPageNo" value="<%=String.valueOf(iPageNo)%>">
	<input type="hidden" name="iTotPageNo" value="<%=String.valueOf(iTotPageNo)%>">
	<input type="hidden" name="orderName" value="<%=request.getParameter("orderName")%>">
	<input type="hidden" name="orderBy" value="<%=request.getParameter("orderBy")%>">
	<input type="hidden" name="resourceId" id="resourceId" value="<%=iResourceId%>">
	<input type="hidden" name="orgId" id="orgId" value="<%=iOrgId%>">
	<input type="hidden" name="regionCode" id="regionCode" value="<%=strRegionCode%>">
	<input type="hidden" name="hLineDept" id="hLineDept" value="">
	<input type="hidden" name="hLineResource" id="hLineResource" value="">
	<input type="hidden" name="hLineRole" id="hLineRole" value="">
	<input type="hidden" name="assignFlag" id="assignFlag" value="">
	<table class="request-service" style="align:center;" cellpadding="1" cellspacing="0" border="0">
	<tr><td height="3" ></td></tr>	
	<tr>	
	
      	<td >
			<font color="#FF0000"><b><%=strWorkbenchAssignedM1%></b></font>
	 	</td>
	</tr>
	<tr>
		<td>
		  <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
			<p id="eMsg"></p>
		  </div>
		</td>
	</tr>		
	<tr>
		
		<td>
			<table>
				<tr>
					<td>
						<font class="promptReadOnly"><b>Departments:</b></font>
						<select name="slDept" class="searchBarLink" onchange="javascript:action_go();">
<%
						for(int idx=0; idx<alWBDept.size(); idx++) {
							Canon_E193_WorkbenchObj objWBTktDept = (Canon_E193_WorkbenchObj)alWBDept.get(idx);							
							String strCode = objWBTktDept.getStrAttribute2();
							String strDesc = objWBTktDept.getStrAttribute3();	
						//	System.out.println("In Workbench Assigned ~ " + " StrCode~ "+strCode + " StrDesc~ "+ strDesc);
%>						
						  	<option value="<%=strCode%>" <%=strCode.equalsIgnoreCase(hDeptVal)?"selected":""%>><%=strDesc==null?"":strDesc%></option>
<%
						}
%>
						</select>
					</td>
					<td>
						<font class="promptReadOnly"><b>Ticket Assignee: </b></font>
						<select name="slOwner" id="slOwner" class="searchBarLink">
<%
						for(int idx=0; idx<alWBAssigned.size(); idx++) {
							Canon_E193_WorkbenchObj objWBTktAssigned = (Canon_E193_WorkbenchObj)alWBAssigned.get(idx);
							//Start changes for S21 by Mangala
							//int iAssignedId = objWBTktAssigned.getIAttribute1();
							String iAssignedId = objWBTktAssigned.getIAttribute1();
							//End changes for S21 by Mangala
							String strAssignedName = objWBTktAssigned.getStrAttribute2();
							String strIsSelected = "";
							
							if (iAssignedId.equalsIgnoreCase(hResId))
								strIsSelected = "selected";
%>							
						  	<option value="<%=iAssignedId%>" <%=strIsSelected%>><%=strAssignedName==null?"":strAssignedName%></option>
<%
						}
%>
						</select>
					</td>
					<td>
					<a class="btn_red" href="javascript:action_go();">Go</a> 						
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>	
	
	<td> 
			<font color="#FF0000"><b><%=strWorkbenchAssignedN1%></b></font>
	</td>
	</tr>	 
	<!-- <tr><td colspan="2" height="10"></td></tr>	 -->
	<tr> 
		<td class="tdTableCellStyle" ><font class="promptReadOnly"><b>Assigned Tickets: </b></font></td>
	</tr>
	</table>
	
			<div style="overflow-x:auto; width:97%;margin-left:20px;margin-right:20px;margin-bottom:20px; "> 
	    	<table cellspacing="1" class="supplies-table">
				<tr>
				   <th>Select</th>
				   <th><a href="#" onClick="return fnOrderBy(tArray[0])">Number</a></th>
				   <th><a href="#" onClick="return fnOrderBy(tArray[2])">Category</a></th>
				   <th><a href="#" onClick="return fnOrderBy(tArray[4])">Line<br>Category</a></th>
				  <!--  <th><a href="#" onClick="return fnOrderBy(tArray[12])">Account<br>Number</a></th> -->
				   <th><a href="#" onClick="return fnOrderBy(tArray[12])">Account<br>Name</a></th>
				   <th><a href="#" onClick="return fnOrderBy(tArray[13])">Profile<br>Name</a></th>
				   <th><a href="#" onClick="return fnOrderBy(tArray[5])">Date<br>Assigned</a></th>
				   <th><a href="#" onClick="return fnOrderBy(tArray[6])">Line<br>Status</a></th>
				   <th><a href="#" onClick="return fnOrderBy(tArray[8])">Days<br>Open</a></th>
				   <th><a href="#" onClick="return fnOrderBy(tArray[14])">Revision<br>Date</a></th>
				   <th><a href="#" onClick="return fnOrderBy(tArray[10])">Assigned<br>By</a></th>
				   <th><a href="#" onClick="return fnOrderBy(tArray[11])">Department</a></th>
				   <th width="10"><a href="#" onClick="return fnOrderBy(tArray[1])">Contract Branch</a></th>
                   <th><a href="#" onClick="return fnOrderBy(tArray[3])">Line#</a></th>
                   <th><a href="#" onClick="return fnOrderBy(tArray[7])">Line<br>Severity</a></th>
				   <th><a href="#" onClick="return fnOrderBy(tArray[9])">Lines<br>Unassigned</a></th>				   
				   
				</tr>
<%
       
		if(alWBOwnedTicket!=null && alWBOwnedTicket.size()>0){
			for(int i=0; i < alWBOwnedTicket.size(); i++)
			{
				System.out.println(" alWBOwnedTicket.size() " + alWBOwnedTicket.size());
				Canon_E193_WBTicketObj objWBOwnedTicketObj = (Canon_E193_WBTicketObj)alWBOwnedTicket.get(i);
				//System.out.println(" objWBOwnedTicketObj " + objWBOwnedTicketObj.toString() );
				int iTktId = objWBOwnedTicketObj.getITicketId();
				int iTktNum = objWBOwnedTicketObj.getITicketNumber();
				System.out.println(" iTktId " + iTktId + " iTktNum "+iTktNum);
				String strTktType = objWBOwnedTicketObj.getStrTicketType();
				String strCatDesc = objWBOwnedTicketObj.getStrCatDesc();
				int iLineId = objWBOwnedTicketObj.getILineId();
				int iLineNum = objWBOwnedTicketObj.getILineNumber();
				String strLineCatDesc = objWBOwnedTicketObj.getStrLineCatDesc();
				String strAssignDate = objWBOwnedTicketObj.getStrAssignDate();
				String strLineStatus = objWBOwnedTicketObj.getStrLineStatus();
				int iLineSevLvl = objWBOwnedTicketObj.getILineSevLvl();
				String strLineSeverity = objWBOwnedTicketObj.getStrLineSeverity();			
				int iDaysOpen = objWBOwnedTicketObj.getIDaysOpen();
				
				String strUnassignedLinesFlag = objWBOwnedTicketObj.getStrUnassignedLinesFlag();
				String strUnassignedLinesFlagDesc = "";
				if("N".equalsIgnoreCase(strUnassignedLinesFlag))
					strUnassignedLinesFlagDesc = "No";
				else if("Y".equalsIgnoreCase(strUnassignedLinesFlag))
					strUnassignedLinesFlagDesc = "Yes";
												
				String strTktAssgBy = objWBOwnedTicketObj.getStrTicketAssignee();			
				String strTktAssgByDept = objWBOwnedTicketObj.getStrTicketAssigneeDept();	
				System.out.println(" ~~ "+" ITktId " + iTktId+ " ITktNum " +iTktNum + " StrTktType "+ strTktType + " iLineId " + iLineId+ " iLineNum "+ iLineNum+ " strLineCatDesc "+ strLineCatDesc+" strAssignDate "+ strAssignDate+" strLineStatus "+strLineStatus);
				
				/*if(strLineStatus.contains("UPDATE CUSTOMER"))
				{
					for(int j = 0; j <strLineStatus.length(); j++ ){
						
						char character = strLineStatus.charAt(j);
						System.out.println("strLineStatus --->"  +  character + " " + (int)character);
					}
				}*/
				//strLineStatus = strLineStatus.replace("\u2013", "&ndash;");
	%>					
					<tr class="tdTableCellStyle">	
						<td><input type="checkbox" name="selectedItem<%=i%>"  value="<%=i%>" id="selectedItem<%=i%>"/></td>
						<td>
							<a href="canon_E193_csTicketStatusController.jsp?ticketId=<%=iTktNum%>&hPageFrom=WBAssigned"><%=iTktNum%></a>
							<input name="tktId" type="hidden" value="<%=iTktId%>">
							<input name="ticketId<%=i%>" id="ticketId<%=i%>" type="hidden" value="<%=iTktId%>">
						</td>
						
						<td><%=(strCatDesc == null)?"":strCatDesc%></td>
				
						<td><%=(strLineCatDesc == null)?"":strLineCatDesc%></td>
						<td><%=objWBOwnedTicketObj.getStrAccountName()==null?"":objWBOwnedTicketObj.getStrAccountName()%></td>
						<td align="center"><%=objWBOwnedTicketObj.getStrProfileName()==null?"-":objWBOwnedTicketObj.getStrProfileName()%></td>
						<td nowrap><%=(strAssignDate == null)?"":strAssignDate%></td>
						<td><%=(strLineStatus == null)?"":strLineStatus%></td>
											
						<td><%=iDaysOpen%></td>
						<td nowrap><%=objWBOwnedTicketObj.getStrLastUpdateDate()%></td>
						
						<td><%=(strTktAssgBy == null)?"":strTktAssgBy%></td>
						<td><%=(strTktAssgByDept == null)?"":strTktAssgByDept%></td>

						<td><%=(strTktType == null)?"":strTktType%></td>
                                                <td>
							<%=iLineNum%>
							<input name="lineId<%=i%>" id="lineId<%=i%>" type="hidden" value="<%=iLineId%>">
						</td>
						<td>
							<%=(strLineSeverity == null)?"":strLineSeverity%>
							<input name="lineSevLvl" type="hidden" value="<%=iLineSevLvl%>">
						</td>	
						<td><%=strUnassignedLinesFlagDesc%></td>
					</tr>
	<%
			}
	%>
		
	<%	
		}

		if(alWBOwnedTicket.size() == 0)
		{
%>		
			<tr>
				<td colspan="15"><font class="promptReadOnly"><b><%=strWorkbenchAssignedM2%></b></font></td>
			</tr>
<%
		}
%>		
			</table>  
			<input type="hidden" name="ticketSize" id="ticketSize" value="<%=alWBOwnedTicket.size()%>">
		</div>
			
			
		<table style="width: 100%">
			
<%
	if (iTotPageNo > 1) {
%>
		<tr class="tdTableCellStyle">
			<td colspan="3"> Pages:  &nbsp; &nbsp;
<%
			for(int j=1;j<=iTotPageNo;j++) {
				if(j == iPageNo) {
%>									
					&nbsp;<%=j%>
<%										
				}else{
%>
					&nbsp;<a href="javascript:action_func2(<%=j%>);"><%=j%></a>
<%
				}
			}
%>
			</td>
		</tr>
<%		
	}
	if(alWBOwnedTicket.size() > 0)
	{
	%>
	<tr align='center'>
	<td style="text-align: center;" colspan=2><input type="button" name="assignBtn" id="assignBtn" value="Assign" class="btn_red" onclick="javascript:fnGetAssignee()"></td>
	</tr> 
	<%
	}
	%>
   	<tr><td height="10" colspan="2"></td></tr>
</table>
</form>
<!-- Newly Added  -->
<div id="dlg"></div> 
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>
<script>
var msg = '<%=request.getParameter("errorMsg")==null?"":request.getParameter("errorMsg")%>';
if(msg != '' && msg != 'null') {
	alert(msg);
}
$(document).ready(function() {
	var detectBrowser=msieversion();
	if("otherbrowser"!=detectBrowser)
		{		
		$('#nav2').css({"float":"right"});
		}	
});
</script>

