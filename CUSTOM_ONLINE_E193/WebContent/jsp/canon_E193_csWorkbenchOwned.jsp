<!-- $Header: canon_E193_csWorkbenchOwned.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csWorkbenchOwned.jsp - Workbench.
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
 | 03-Mar-2014    Kiran K		ITG 501837   type to contract branch num
 | NOTE: Col "Type" is replaced with "contract branch number" but the variable names not changed
 | 29-Jan-2016	Mangala Shenoy	    Modified for S21 changes.
 +=======================================================================--%>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>

<%@ include file="canon_E193_csTopInc.jsp" %>
<% 
	// Menu Prompt
	strPageName = "Workbench";
	strSubMenuPageName = "Owned";
	
    // Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");
				
	//Get orgId and ResourceId
	int iOrgId = (int)objCiSession.getOrgId();
	//Start Changes for S21 by Mangala
	//int iResourceId = (int)objCiSession.getResourceId();
	String iResourceId = objCiSession.getResourceId();
	System.out.println(" iResourceId = " + iResourceId);
	//End Changes for S21 by Mangala
	String ownedYesNo = request.getParameter("ownedYesNo");
			
	if(ownedYesNo == null)
		ownedYesNo = "OWNED";
	
	/* ITG# 73987 : Begin : Alert */
	String strResult = request.getParameter("iResult");
	int iResult      = -999;
	iResult = (strResult==null ||  "".equals(strResult))?-1:Integer.parseInt(strResult);
	/* ITG# 73987 : End : Alert */
	
	Canon_E193_WBTicket objWBOwnedTicket = new Canon_E193_WBTicket();
	String hOwnVal = request.getParameter("slOwner");
	System.out.println(" hOwnVal = " + hOwnVal);
	//Start Changes for S21 by Mangala
	//int hResId = -1;
	String hResId = "";
	//End Changes for S21 by Mangala		
	if ((hOwnVal == null) || ("".equalsIgnoreCase(hOwnVal)) || ("null".equalsIgnoreCase(hOwnVal))){
		hResId = iResourceId;
		System.out.println(" Old hResId Value = " +  hResId);
	}
	else{
		//Start Changes for S21 by Mangala
		//hResId = Integer.parseInt(hOwnVal);
		hResId = hOwnVal;
		//End Changes for S21 by Mangala
		System.out.println(" New hResId Value = " +  hResId);
	}
	/* ITG# 73987 : Begin */	
	String strRegionCode = (String)objCiSession.getRegionCode();	
        /* ITG# 73987 : End */
	
	// Get owner resources
	ArrayList alWBOwner = objWBOwnedTicket.getWBTktOwnerAssignee(null, iResourceId, iOrgId, strRegionCode);
	
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
		
	// Get tickets for that resource
	ArrayList alWBOwnedTicket = objWBOwnedTicket.getOwnedTickets(null, hResId, iOrgId, ownedYesNo, strOrderName, strOrderBy, iPageNo, iTotPageNo, iResourceId);
	System.out.println(" alWBOwnedTicket Size =" + alWBOwnedTicket.size());
	if(alWBOwnedTicket != null && alWBOwnedTicket.size() > 0) {
		iPageNo = ((Integer)alWBOwnedTicket.get(0)).intValue();
		alWBOwnedTicket.remove(0);
		iTotPageNo = ((Integer)alWBOwnedTicket.get(0)).intValue();
		alWBOwnedTicket.remove(0);
	}else{
		iPageNo = 0;
		iTotPageNo = 0;
	}
	
	// Get all resources for change owner list box
	ArrayList alWBRes = objWBOwnedTicket.getWBTktRes(iOrgId);
	
%>
	
<%@ include file="canon_E193_csMenuInc.jsp" %> 
		
<script language="JavaScript">

var orderAsc = "asc";
var orderDesc = "desc";
var tArray = new Array("tktNo", "acctName", "catDesc", "date", "daysOpen", "linesUnassigned", 
		"status", "type", "res", "dept", "lastupdatedate");

function fnOrderBy(name) {
	var objForm = document.wbForm;
	var vOrdName = objForm.orderName.value;
	objForm.ownedYesNo.value = "OWNED";
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
	objForm.action = "canon_E193_csWorkbenchOwned.jsp";
	objForm.submit();
	return false;
}

function action_func2(iPageIndex) {
	var objForm = document.wbForm;
	objForm.iPageNo.value = iPageIndex;
	objForm.ownedYesNo.value = "OWNED";
	objForm.action = "canon_E193_csWorkbenchOwned.jsp";
	objForm.submit();
}	

function action_go()
{
	/* var selectTicketOwner = $("slOwner").val(); */
	var selectTicketOwner = document.getElementById("slOwner").value;
	//var value = selectTicketOwner.options[selectTicketOwner.selectedIndex].value;
	//alert(" selectTicketOwner = "+ selectTicketOwner);
	document.wbForm.ownedYesNo.value = "OWNED";
	document.wbForm.iPageNo.value = "0";
	document.wbForm.iTotPageNo.value = "0";
	document.wbForm.slOwner.value = selectTicketOwner;
	document.wbForm.action = "canon_E193_csWorkbenchOwned.jsp";
	document.wbForm.submit();
}

function action_func1()
{
	resetErroMsg();
	changeOwner();
	
	if(document.wbForm.isChanged.value == "false")
	{
		displayErrorMsg("No changes to save");
	}
	else
	{
		document.wbForm.action = "canon_E193_csWorkbenchChangeOwnerController.jsp";
		document.wbForm.submit();
	}
}

function changeOwner()
{
	resetErroMsg();
	if(document.wbForm.reassignOwner.value == document.wbForm.slOwner.value)
	{
		document.wbForm.isChanged.value = "false";
		displayErrorMsg("Please select new owner.");
	}
	else 
	{
		var v = document.wbForm.tktdetails.length;
		var isChecked = false;
		if(v > 0) 
		{
			for(var i=0; i<v; i++)
			{
				if(document.wbForm.tktdetails[i].checked) {
					isChecked = true;
					break;
				}
			}
		}
		else
		{
			if(document.wbForm.tktdetails.checked) 
					isChecked = true;
		}
		
		if(isChecked == false)
		{
			displayErrorMsg("Please select alteast one ticket.");
			document.wbForm.isChanged.value = "false";
		}
		else
		{
			document.wbForm.isChanged.value = "true";
		}
	}
}

</script>		
	<div id="main_content">
		<div id="page_top">
		   	<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strWorkbenchOwnedT1%></h1>
		</div>
	<form name="wbForm" method="post">

         
        <% /* ITG# 73987 : End : Alert */ 
        if(iResult == 0 ) { %>
          <script> alert('Region-code not setup for the user, please choose another user.'); </script>
        <% } 
        /* ITG# 73987 : End : Alert */ 
        %>

	<!-- hidden variables -->
	<input type="hidden" name="hPageFrom" value="WBOwned" >
	
	<input type="hidden" name="isChanged" value="false" >
	<input type="hidden" name="ownedYesNo" value="" >
	<input type="hidden" name="iPageNo" value="<%=""+iPageNo%>">
	<input type="hidden" name="iTotPageNo" value="<%=""+iTotPageNo%>">
	<input type="hidden" name="orderName" value="<%=request.getParameter("orderName")%>">
	<input type="hidden" name="orderBy" value="<%=request.getParameter("orderBy")%>">
	<table class="request-service" style="align:center; margin-top: 10px;" cellspacing="0">	
	<tr>		
      	<td colspan="2" style="margin-top: 5px;">
			<font color="#FF0000"><b><%=strWorkbenchOwnedM1%></b></font>
	 	</td>
	</tr>
	<tr>
		<td width="10"></td>
		<td>
			<table>
				<tr>
					<td>
						Ticket Owner:
						<select name="slOwner" id="slOwner" class="searchBarLink">
<%
						for(int idx=0; idx<alWBOwner.size(); idx++)
						{
							Canon_E193_WorkbenchObj objWBTktOwner = (Canon_E193_WorkbenchObj)alWBOwner.get(idx);
							//Start changes for S21 by Mangala
							//int iOwnId = objWBTktOwner.getIAttribute1();
							String iOwnId = objWBTktOwner.getIAttribute1();	
							System.out.println(" iOwnId = " + iOwnId);
							//End changes for S21 by Mangala
							String strOwnerName = objWBTktOwner.getStrAttribute2();
							String strIsSelected = "";
							System.out.println(" hResId = " + hResId);
							if (iOwnId.equalsIgnoreCase(hResId))
								strIsSelected = "selected";
%>							
						  	<option value="<%=iOwnId%>" <%=strIsSelected%>><%=strOwnerName==null?"":strOwnerName%></option>
<%
						}
%>
						</select>
					</td>
					<td>
						<a class="btn_red" href="javascript:action_go()">Go</a>						
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<!-- <tr><td colspan="2" height="5"></td></tr> -->
	<tr>			
		<td colspan="2" class="tableQuestionCell"> 
			<font color="#FF0000"><b><%=strWorkbenchOwnedN1%></b></font>
		</td>
	</tr>		
   <!--  <tr><td colspan="2" height="5"></td></tr> -->	
    <tr> 	
      <td colspan="2">Owned Tickets:</td>
   </tr>
   </table>
   <table>
   	<tr> 
    <td width="10"></td>
    <td>
	    <table cellspacing="1" class="supplies-table" >
			<tr>
				<th>&nbsp;</th>
				<th><a href="#" onClick="return fnOrderBy(tArray[0])">Number</a></th>
				<th><a href="#" onClick="return fnOrderBy(tArray[1])">Account Name</a></th>
				<th><a href="#" onClick="return fnOrderBy(tArray[2])">Category</a></th>
				<th><a href="#" onClick="return fnOrderBy(tArray[3])">Date</a></th>			   
				<th><a href="#" onClick="return fnOrderBy(tArray[4])">Days Open</a></th>
				<th><a href="#" onClick="return fnOrderBy(tArray[10])">Revision<br>Date</a></th>
				<th width="10"><a href="#" onClick="return fnOrderBy(tArray[5])">Lines Unassigned</a></th>
				<th><a href="#" onClick="return fnOrderBy(tArray[6])">Status</a></th>			   
				<th width="10"><a href="#" onClick="return fnOrderBy(tArray[7])">Contract Branch Number</a></th>							
				<th><a href="#" onClick="return fnOrderBy(tArray[8])">Assignee</a></th>
				<th><a href="#" onClick="return fnOrderBy(tArray[9])">Assigned Department</a></th>		
			</tr>
<%
		for(int i=0; i < alWBOwnedTicket.size(); i++)
		{
			Canon_E193_WBTicketObj objWBOwnedTicketObj = (Canon_E193_WBTicketObj)alWBOwnedTicket.get(i);
			int iTktId = objWBOwnedTicketObj.getITicketId();
			int iTktNum = objWBOwnedTicketObj.getITicketNumber();
			String strCatDesc = objWBOwnedTicketObj.getStrCatDesc();
			String strCrDate = objWBOwnedTicketObj.getStrCrDate();			
			int iDaysOpen = objWBOwnedTicketObj.getIDaysOpen();
			System.out.println("objWBOwnedTicketObj Account Name: " + objWBOwnedTicketObj.getStrAccountName());
			String strUnassignedLinesFlag = objWBOwnedTicketObj.getStrUnassignedLinesFlag();
			String strUnassignedLinesFlagDesc = "";
			if("N".equalsIgnoreCase(strUnassignedLinesFlag))
				strUnassignedLinesFlagDesc = "No";
			else if("Y".equalsIgnoreCase(strUnassignedLinesFlag))
				strUnassignedLinesFlagDesc = "Yes";
					
			String strTktStatus = objWBOwnedTicketObj.getStrTicketStatus();			
			String strTktType = objWBOwnedTicketObj.getStrTicketType();			
			String strTktAssg = objWBOwnedTicketObj.getStrTicketAssignee();			
			String strTktAssgDept = objWBOwnedTicketObj.getStrTicketAssigneeDept();				
%>		 
		   <tr>		   
			   <td><input name="tktdetails" type="checkbox" value="<%=iTktId%>"></td>
               <td><a href="canon_E193_csTicketStatusController.jsp?ticketId=<%=iTktNum%>&hPageFrom=WBOwned"><%=iTktNum%></a></td>
			   <td><%=objWBOwnedTicketObj.getStrAccountName()==null?"":objWBOwnedTicketObj.getStrAccountName()%></td> <!-- Newly Added -->
			   <td><%=(strCatDesc == null)?"":strCatDesc%></td>
			   <td nowrap><%=(strCrDate == null)?"":strCrDate%></td>							
			   <td><%=iDaysOpen%></td>
			   <td nowrap><%=objWBOwnedTicketObj.getStrLastUpdateDate()%></td>
			   <td><%=strUnassignedLinesFlagDesc%></td>
			   <td><%=(strTktStatus == null)?"":strTktStatus%></td>
	           <td><%=(strTktType == null)?"":strTktType%></td>
			   <td><%=(strTktAssg == null)?"":strTktAssg%></td>
			   <td><%=(strTktAssgDept == null)?"":strTktAssgDept%></td>
			</tr>	
<%
		}
		if (iTotPageNo > 1) {
%>
			<tr class="tdTableCellStyle">
				<td colspan="11"> Pages:  &nbsp; &nbsp;
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
		if(alWBOwnedTicket.size() == 0)
		{
%>		
			<tr class="tdTableCellStyle">
				<td colspan="10"><font class="promptReadOnly"><b><%=strWorkbenchOwnedM2%></b></font></td>
			</tr>
<%
		}
%>
	     </table>   
	  </td>
  </tr>
   <!--  <tr>
		<td colspan="2" height="10"></td>
	</tr> -->
	</table>
	<table class="request-service" style="align:center; margin-top: 10px;" cellspacing="0">
	<tr>			
		<td colspan="2" class="tableQuestionCell"> 
			<font class="promptReadOnly" color="#FF0000"><b><%=strWorkbenchOwnedN2%></b></font>
		</td>
	</tr>
	<tr>
		<td height="10"></td>
		<td>
			<table>
				<tr>
					<td>
						<font class="promptReadOnly">Change Owner:</font>
						<select name="reassignOwner" id="reassignOwner" size="1" class="searchBarLink" >
		<%
						for(int idx=0; idx<alWBRes.size(); idx++)
						{
							Canon_E193_WorkbenchObj objWBTktAllRes = (Canon_E193_WorkbenchObj)alWBRes.get(idx);
							
							//Start changes for S21 by Mangala
							//int iResId = objWBTktAllRes.getIAttribute1();
							String iResId = objWBTktAllRes.getIAttribute1();
							//End changes for S21 by Mangala
							String strResName = objWBTktAllRes.getStrAttribute2();
%>							
						  	<option value="<%=iResId%>"><%=strResName==null?"":strResName%></option>
<%
						}
%>						
		      			</select>
			 		</td>
			 		<td>
			 		<% if(alWBOwnedTicket.size() == 0) { %>			   					
			   					<a class="btn_red" href="#">Save</a>
			   				<% }else{ %>
			   				<a class="btn_red" href="javascript:action_func1();">Save</a>			   					
			   				<% } %>			 		
			 		</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>

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


