<!-- $Header: ITG# 74988 canon_E193_csTicketHistory.jsp $ -->
<%--========================================================================
 |
 | FILE 
 | canon_E193_csTicketHistory.jsp - Ticket History.
 |   
 | DESCRIPTION
 |   Details of the ticket history
 |
 | AUTHOR
 | Subba 
 |
 | CREATION DATE
 | 09/07/2005
 |
 | HISTORY
 | DATE        WHO               WHY
 | 30-Nov-06   Vikas Basal       ITG# 74988 CFS Changes
 | 12-Dec-06   Kireet K Bollam   ITG# 74988 CFS Changes
 | 17-Mar-2008 Chandra Sekhar    ITG # 154956  Change
 |
 +=======================================================================--%>
<%@page language="java" %> 

<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objHistArrayList" scope="request" />

<%@ include file="canon_E193_csTopInc.jsp" %>

<%
   // Menu Prompt
   strPageName = "History";
   
   // Check page from to show the path
   strPageFrom = request.getParameter("hPageFrom");
   String strAccountYesNo = request.getParameter("hRemoveAcctInfo")==null?"N":request.getParameter("hRemoveAcctInfo");
   if("Y".equalsIgnoreCase(strAccountYesNo)) {
     session.removeValue("objSessionAcctInfo");
     session.removeValue("alAddrList");
   }
%>
<%@ include file="canon_E193_csMenuInc.jsp" %>

<script language="JavaScript">
   // Global variables
   var orderAsc = "asc";
   var orderDesc = "desc";
   var tArray = new Array("number", "category", "date", "status", "acctName", "acctNo", "invNo", "contractNo", "ordNo", "daysOpen", "linesUnassigned");
   
   /*function InvoiceDetails(val1) {
      var vLink = "canon_E193_csActualInvoiceController.jsp?InvNo=" + val1;
      var vWin = window.open(vLink, "newwin","height=300,width=900,toolbar=no,menubar=no,scrollbars=yes, resizable=yes, alwaysRaised=yes" );
      vWin.focus();
   }*/
   

   function fnOrderBy(name) {
      var objForm = document.ticketHistoryForm;
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
      objForm.submit();
      return false;
   }
   
   function action_func1(iPageIndex) {
	  resetErroMsg();
      var objForm = document.ticketHistoryForm;
      var objSearchType = objForm.searchType;
      var objSearchValue = objForm.searchValue;
      if (objSearchType.value == '')   {
    	  displayErrorMsg("Please select one of the search criteria");
         objSearchType.focus();
      }else if (objSearchValue.value == '')  {
    	  displayErrorMsg("Please enter value ");
         objSearchValue.select();
      }else if(validateField(objSearchValue)) {
         objSearchValue.value = objSearchValue.value.toUpperCase();
         objForm.iPageNo.value = iPageIndex;
         objForm.iPageNo.value = "0";
         objForm.iTotPageNo.value = "0";
         objForm.submit();
      }
   }
   
   /* ITG Ticket# 74988 -- Begin: Kireet K Bollam*/
   /* Function to validate the form data on press of return key.*/
   function submit_action_func1(iPageIndex) {
	   	 resetErroMsg();
         var objForm = document.ticketHistoryForm;
         var objSearchType = objForm.searchType;
         var objSearchValue = objForm.searchValue;
         if (objSearchType.value == '')   {
        	 displayErrorMsg("Please select one of the search criteria");
            objSearchType.focus();
            return false;
         }else if (objSearchValue.value == '')  {
        	 displayErrorMsg("Please enter value ");
            objSearchValue.select();
            return false;
         }else if(validateField(objSearchValue)) {
            objSearchValue.value = objSearchValue.value.toUpperCase();
            objForm.iPageNo.value = iPageIndex;
            objForm.iPageNo.value = "0";
            objForm.iTotPageNo.value = "0";
            objForm.submit();
            return true;
         }else{
            return false;
         }
   }
   /* ITG Ticket# 74988 -- End*/
   
   function action_func2(iPageIndex) {
      var objForm = document.ticketHistoryForm;
      objForm.searchType.value = '<%=request.getParameter("searchType")==null?"":request.getParameter("searchType")%>';
      objForm.searchValue.value = '<%=request.getParameter("searchValue")==null?"":request.getParameter("searchValue")%>';
      objForm.iPageNo.value = iPageIndex;
      objForm.submit();
   }  

/* ITG# 74988 - Begin */
// added searchType cfsSerialNo option and validation for same
/* ITG# 74988 - Begin */
   function validateField(textfield) {
	  resetErroMsg();
      var vChar;
      var OK = true;
      var wcOK = true;
      var vSearchType = document.ticketHistoryForm.searchType.value;
      
      textfield.value = $.trim(textfield.value);
      var textfieldValue = textfield.value ;
      
      /* if (vSearchType == "acctName" || vSearchType == "owner" || vSearchType == "createdBy" ) {
         var acctNameOK = true;
         
         if(textfield.value.length < 5) {
        	 displayErrorMsg("Atleast five characters are required for this search criteria.");
             textfield.focus();
             textfield.select();
             return false;
         }else{
            var countChar = 0;
            var theChar = "";
            
            for (var i=0; i<textfield.value.length; i++) {
               theChar = textfield.value.charAt(i);
               if ((theChar >= "0") && (theChar <= "9")) {
                  countChar++;
                  continue;
               }
               if ((theChar >= "a") && (theChar <= "z")) {
                  countChar++;
                  continue;
               }
               if ((theChar >= "A") && (theChar <= "Z")) {
                  countChar++;
                  continue;
               }
               if (theChar == "%")  {
                  if(countChar < 5) {
                     acctNameOK = false;
                     break;
                  }else
                     acctNameOK = true;
               }
            }
         }     
         if (acctNameOK == false) {
        	displayErrorMsg("Wild Card search not allowed for this search criteria. Please enter complete value.");
            textfield.focus();
            textfield.select();
            return false;
         }
      }  */
   
      for (var i=0; i<textfield.value.length; i++) {
         vChar = textfield.value.charAt(i);
         
         if (vChar >= "0" && vChar <= "9") {
             continue;
         }else if (vChar >= "A" && vChar <= "Z") {
            continue;
         }else if (vChar >= "a" && vChar <= "z") {
            continue;                  
         }
         else if (vChar == "-" && (vSearchType == "acctName" || vSearchType == "cfsSerialNo" || vSearchType == "contractBranch" 
        		 ||  vSearchType == "serialNo" ||  vSearchType == "ticketStatus" ||  vSearchType == "owner" ||  vSearchType == "createdBy") ) {
             continue;
          }
         else if (vChar== " " && (vSearchType == "acctName" || vSearchType == "owner" || vSearchType == "createdBy"  
        		 //|| vSearchType == "cfsSerialNo"
        		 )) {
            continue;
         }else if (vChar == "%" && (vSearchType != "acctName" && vSearchType != "owner" && vSearchType != "createdBy" 
        		 //|| vSearchType == "cfsSerialNo"
        		 )) {
            wcOK = false;
            break;
         }else if (vChar == "%" && (vSearchType == "acctName" || vSearchType == "owner" || vSearchType == "createdBy" 
        		 //|| vSearchType == "cfsSerialNo"
        		 )) {
            continue;
         }else if (vSearchType != "acctName"){
            OK = false;
            break;
         }
      }
    
      if (wcOK == false) {
    	 displayErrorMsg("Wild Card search not allowed for this search criteria. Please enter complete value. ");
         textfield.focus();
         textfield.select();
         return false;
      }
      
      if (OK == false) {
    	 displayErrorMsg("Please enter valid value. ");
         textfield.focus();
         textfield.select();
         return false;
      }
      
      if(vSearchType == "orderNo" && isNaN(textfield.value)) {
    	 displayErrorMsg("Please enter numbers only. ");
         textfield.focus();
         textfield.select();
         return false;
      }
      
      return (OK && wcOK);
   }
   
   function TicketStatus(tktVal)
   {
      document.ticketHistoryForm.ticketId.value = tktVal;
      document.ticketHistoryForm.action = "canon_E193_csTicketStatusController.jsp";
      document.ticketHistoryForm.submit();
   }
   
   function copyTicket(ticketNumber)
   {
	   var objForm = document.ticketHistoryForm;
	   objForm.ticketId.value = ticketNumber;
	   objForm.copyTicketFromHistory.value = "Y";
	   objForm.action = "canon_E193_csEICheckRequest.jsp";
	   objForm.submit();
   }
   
   /*function fnAssgn(tktVal)
   {
      var vLink = "canon_E193_csAssignmentP.jsp?ticketId=" + tktVal; 
      var vWin = window.open(vLink, "newwin", "height=300,width=900,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,alwaysRaised=yes"); 
      vWin.focus();
   }*/
   
   function action_Clear()
   {
      var mesg = "If ticket creation is in process, all data entered for this account will be lost. Are you sure, you want to proceed? ";
      if(confirm(mesg))
      {
         document.ticketHistoryForm.hRemoveAcctInfo.value = "Y";
         document.ticketHistoryForm.searchType.value = null;
         document.ticketHistoryForm.submit();
      }
   }
   
   function getAssignMentDetails(pageName,paramName,value,title) {
	 showPleaseWait();
     var modelName ="#dlg";
	   $(modelName).dialog({
					height: 500,
					title: title, 
					modal: true ,
					zIndex:1005,
					width: 1010,		
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
//-->
</script>
<%
   Object objAcct = session.getAttribute("objSessionAcctInfo");
   Canon_E193_AcctInfoObj objSessionAcctInfo = null;
   if(objAcct != null) {
      //objSessionAcctInfo = (Canon_E193_AcctInfoObj)objAcct; 
	   session.removeValue("objSessionAcctInfo");
	   session.removeValue("alAddrList");
   }

%> 


<!-- ITG Ticket# 74988  Begin: Kireet K Bollam : Included the following. -->
<div id="main_content">
	<div id="page_top">
		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strTicketHistoryT1 %></h1>			
	</div>	

<form name="ticketHistoryForm" action="canon_E193_csTicketHistoryController.jsp" method="post" onSubmit="javascript:return(submit_action_func1(0));">
<!-- ITG Ticket# 74988  End -->
   <input type="hidden" name="iPageNo" value="<%=request.getParameter("iPageNo")==null?"0":request.getParameter("iPageNo")%>">
   <input type="hidden" name="iTotPageNo" value="<%=request.getParameter("iTotPageNo")==null?"0":request.getParameter("iTotPageNo")%>">
   <input type="hidden" name="orderName" value="<%=request.getParameter("orderName")%>">
   <input type="hidden" name="orderBy" value="<%=request.getParameter("orderBy")%>">
   <input type="hidden" name="hRemoveAcctInfo" id="hRemoveAcctInfo" value="<%=strAccountYesNo%>">
   <input type="hidden" name="hPageFrom" value="TicketHistory">
   <input type="hidden" name="ticketId" value="" >
   
   <input type="hidden" name="copyTicketFromHistory" value="">
   
	<div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
		<p id="eMsg"></p>
   </div>
<% if(objSessionAcctInfo != null) { %>
	<table class="supplies-table" style="align:center" cellspacing="1" border="0">
			<tr>
				<th>Account Name</th>
				<th>Account Number</th>
				<th>Contact Name</th>
				<th>Contact Number</th>
			</tr>
			<tr>
               <td>
                  <% String strDisplayName = "";
                     if(objSessionAcctInfo.getAcctName() != null && !objSessionAcctInfo.getAcctName().equalsIgnoreCase("null")) 
                        strDisplayName = objSessionAcctInfo.getAcctName();
                  %><%=strDisplayName%>
               </td>
               <td>
                  <% String strDisplayNo = "";
                     if(objSessionAcctInfo.getAcctNum() != null && !objSessionAcctInfo.getAcctNum().equalsIgnoreCase("null")) 
                        strDisplayNo = objSessionAcctInfo.getAcctNum();
                  %><%=strDisplayNo%>
               </td>
               <td>
                  <% String strDisplayContName = "";
                     if(objSessionAcctInfo.getContactName() != null && !objSessionAcctInfo.getContactName().equalsIgnoreCase("null")) 
                        strDisplayContName = objSessionAcctInfo.getContactName();
                  %><%=strDisplayContName%>
               </td>
               <td>
                  <% String strDisplayContNo = "";
                     if(objSessionAcctInfo.getContactNum() != null && !objSessionAcctInfo.getContactNum().equalsIgnoreCase("null")) 
                        strDisplayContNo = objSessionAcctInfo.getContactNum();
                  %><%=strDisplayContNo%>
               </td>
            </tr>
			<tr>
			
			</table>
			<div style="margin-left:30px;"><font color="#FF0000"><b><%=strTicketHistoryN3%></b></font></div>
			<% if("N".equalsIgnoreCase(strAccountYesNo)) { %>
			<div style="margin-left:30px;"><a class="btn_red" href="javascript:action_Clear();">Clear Account Information</a></div>
			<%} else{%>
			<div style="margin-left:30px;">&nbsp;</div>
			<%} %>

		<% } %>				

<br>

<table class="request-service" style="align:center" cellspacing="1" border="0">   
   <tr>      
      <td colspan="2">
         <table width="587" border="0" align="left" cellpadding="5" cellspacing="1">
            <tr> 
               <td class="subhead">Search By</td>
               <td class="tdTableCellStyle">
                  <%
                     String strSearchType = request.getParameter("searchType")==null?"":request.getParameter("searchType");
                  
/* ITG# 74988 - Begin */

		     if( ("Y".equals(objCiSession.getCFSAccessFlag())) && ("Y".equals(objCiSession.getCFSUserFlag())) )
		     {
                  %>
						
			<select name="searchType" size="1" class="searchBarLink">
				<option value="" <%=strSearchType.equals("")?"selected":""%>>SELECT</option>
				<option value="cfsSerialNo" <%=strSearchType.equals("cfsSerialNo")?"selected":""%>>CFS Serial Number</option>
				<option value="ticketNo" <%=strSearchType.equals("ticketNo")?"selected":""%>>Ticket Number</option>
				<option value="createdBy" <%=strSearchType.equals("createdBy")?"selected":""%>>Created By</option>
			</select>

		<% 
			} else
			{
		%>
			<select name="searchType" size="1" class="searchBarLink">
				<option value="" <%=strSearchType.equals("")?"selected":""%>>SELECT</option>
				<option value="acctName" <%=strSearchType.equals("acctName")?"selected":""%>>Account Name</option>
				<option value="acctNo" <%=strSearchType.equals("acctNo")?"selected":""%>>Account Number</option> 
				<option value="invoiceNo" <%=strSearchType.equals("invoiceNo")?"selected":""%>>Invoice Number</option>
				<option value="serialNo" <%=strSearchType.equals("serialNo")?"selected":""%>>Serial Number</option>
				<option value="contractNo" <%=strSearchType.equals("contractNo")?"selected":""%>>Contract Number</option>
				<option value="contractBranch" <%=strSearchType.equals("contractBranch")?"selected":""%>>Contract Branch</option>

				<option value="cfsSerialNo" <%=strSearchType.equals("cfsSerialNo")?"selected":""%>>CFS Serial Number</option>

				<option value="orderNo" <%=strSearchType.equals("orderNo")?"selected":""%>>Order Number</option>
				<option value="ticketNo" <%=strSearchType.equals("ticketNo")?"selected":""%>>Ticket Number</option>

				<option value="ticketStatus" <%=strSearchType.equals("ticketStatus")?"selected":""%>>Ticket  Status</option>

				<option value="owner" <%=strSearchType.equals("owner")?"selected":""%>>Owner</option>
				<option value="createdBy" <%=strSearchType.equals("createdBy")?"selected":""%>>Created By</option>
			</select>

		<% 
			}
/* ITG# 74988 - End */
		%>

               </td>
               <td class="subhead">Value</td>
               <td class="tdTableCellStyle">
                  <input name="searchValue" type="text" value="<%=request.getParameter("searchValue")==null?"":request.getParameter("searchValue")%>" size="30" maxlength="30" class="inTxt">
               </td>
               <td style="text-align: right;">
						 
							<a class="btn_red" href="javascript:action_func1(0)">Go</a> 													
						</td>                    
            </tr>
         </table>
      </td>
   </tr>   
   <%
      if(request.getParameter("searchType") != null || objSessionAcctInfo != null) {
   %>
   <tr>
      <td class="tableQuestionCell" colspan="2">
         <font class="promptReadOnly" color="#FF0000"><b><%=strTicketHistoryN2%></b></font>
      </td>
   </tr>   
   <tr> 
      <td class="tdTableCellStyle" colspan="2">Ticket History</td>
   </tr>          
   <tr>      
      <td>
         <table cellspacing="1" class="supplies-table">
            <tr>
               <th>&nbsp;</th>
               <th><a href="#" onClick="return fnOrderBy(tArray[0])">Number</a></th>
               <th><a href="#" onClick="return fnOrderBy(tArray[1])">Category</a></th>
               <th><a href="#" onClick="return fnOrderBy(tArray[2])">Date</a></th>
               <th><a href="#" onClick="return fnOrderBy(tArray[3])">Status</a></th>
               <th><a href="#" onClick="return fnOrderBy(tArray[4])">Account Name</a></th>
               <th><a href="#" onClick="return fnOrderBy(tArray[5])">Account Number</a></th>
               <th><a href="#" onClick="return fnOrderBy(tArray[6])">Invoice Number</a></th>
               <th><a href="#" onClick="return fnOrderBy(tArray[7])">Contract Number</a></th>
               <th><a href="#" onClick="return fnOrderBy(tArray[8])">Order Number</a></th>
               <th><a href="#" onClick="return fnOrderBy(tArray[9])">Days Open</a></th>
               <th><a href="#" onClick="return fnOrderBy(tArray[10])">Lines Unassigned</a></th>
               <th>&nbsp;</th>
            </tr>
            <%
               Canon_E193_TicketHeaderObj objTicketHeader = null;
               ArrayList alTicket = (ArrayList)objHistArrayList.getArrayList();
               if(alTicket != null && alTicket.size() > 0) {
                  for(int i = 0; i < alTicket.size(); i++) {
                     objTicketHeader = (Canon_E193_TicketHeaderObj)alTicket.get(i);
                     String strOrdrNo = String.valueOf(objTicketHeader.getOrderNo());
                     if("0".equalsIgnoreCase(strOrdrNo))
                        strOrdrNo = "";
                     String strUnassignedLinesFlag = objTicketHeader.getAttribute3();
                     String strUnassignedLinesFlagDesc = "";
                     if("N".equalsIgnoreCase(strUnassignedLinesFlag))
                        strUnassignedLinesFlagDesc = "No";
                     else if("Y".equalsIgnoreCase(strUnassignedLinesFlag))
                        strUnassignedLinesFlagDesc = "Yes";
            %>
                     <tr class="tdTableCellStyle">
                     	<%if(objTicketHeader.getAcctNo() != null && objTicketHeader.getAcctNo() != "-1" && objTicketHeader.getAcctName() != null && !objTicketHeader.getAcctName().trim().isEmpty())  {%>
                     		<td><a href="javascript:void copyTicket('<%=objTicketHeader.getTicketNo()%>')" > <img src="<%=ctxPath%>/common/images/copy.jpg" width=20 height=20 title="Ticket to Copy" alt ="Ticket to Copy"></a></</td>
                     	<%} else { %>
                     		<td>&nbsp;</td>
                     	<%} %>
                        <td><a style="color:#cc0000;" href="javascript:void TicketStatus('<%=objTicketHeader.getTicketNo()%>');"><%= objTicketHeader.getTicketNo()==null?"":objTicketHeader.getTicketNo()%></a></td>
                        <td><%= objTicketHeader.getCategory()==null?"":objTicketHeader.getCategory()%></td>
                        <td nowrap><%= objTicketHeader.getDate()==null?"":objTicketHeader.getDate()%></td>
                        <td><%= objTicketHeader.getStatus()==null?"":objTicketHeader.getStatus()%></td>
                        <td><%= objTicketHeader.getAcctName()==null?"":objTicketHeader.getAcctName()%></td>
                        <td><%= objTicketHeader.getAcctNo()==null?"":objTicketHeader.getAcctNo()%></td>
                        <td><a style="color:#cc0000;" href="javascript:void InvoiceDetailsPDF('<%=objTicketHeader.getInvoiceNo()%>');"><%= objTicketHeader.getInvoiceNo()==null?"":objTicketHeader.getInvoiceNo()%></a></td>
                        <td><%= objTicketHeader.getContractNo()==null?"":objTicketHeader.getContractNo()%></td>
                        <td><%=strOrdrNo%></td>
                        <td><%=objTicketHeader.getIDaysOpen()%></td>
                        <td><%=strUnassignedLinesFlagDesc%></td>
                        <td><a style="color:#cc0000;" href="#" onClick="getAssignMentDetails('canon_E193_csAssignmentP.jsp','ticketId','<%=objTicketHeader.getTicketNo()%>','Assignment Details');">Assignment<br>Details</a></td>
                     </tr>
            <%    }
                  int iTotPageNo = (int)(Integer.parseInt(request.getParameter("iTotPageNo")));
                  int iPageNo = (int)(Integer.parseInt(request.getParameter("iPageNo")));
                  if (iTotPageNo > 1) {
            %>
                     <tr class="tdTableCellStyle">
                        <td colspan="13"> Pages:  &nbsp; &nbsp;
            <%
                           for(int j=1;j<=iTotPageNo;j++) {
                              if(j == iPageNo) {
            %>                         
                                 &nbsp;<%=j%>
            <%                            
                              }else{
            %>
                                 &nbsp;<a style="color:#cc0000;" href="javascript:action_func2(<%=j%>);"><%=j%></a>
            <%
                              }
                           }
            %>
                        </td>
                     </tr>
            <%                         
                  }
               }else{
            %>
            <tr class="tdTableCellStyle">
               <td colspan="12">
                     <font class="promptReadOnly"><b><%=strTicketHistoryM1%></b></font>
               </td>
            </tr>
            <% } %>
         </table>   
      </td>
   </tr>
   <% } %>   
   </table>
</form>
<div id="dlg"></div>
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>
<script>
var msg = '<%=request.getParameter("errorMsg")==null?"":request.getParameter("errorMsg")%>';
if(msg != '' && msg != 'null') {
   alert(msg);
}
</script>
