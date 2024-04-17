<!-- $Header: canon_E193_csEISelectAcct.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csEISelectAcct.jsp - Select Account.
 |   
 | DESCRIPTION
 |   
 |  
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	08/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 

<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
<%@page import="java.util.*" %>
<%@page import="java.lang.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>

<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objHistArrayList" scope="request" />
<jsp:setProperty name="objHistArrayList" property="*" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Ticket" id="objOpenTicket" scope="page" />

<%@ include file="canon_E193_csTopInc.jsp" %>

<% 
 	// Menu Prompt
	strPageName = "Enter & Inquiry";
	
	// Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");
	
	//Canon_E193_AcctInfoObj objSessionAcctInfo = (Canon_E193_AcctInfoObj)pageContext.getAttribute("objSessionAcctInfo",2);
	Canon_E193_AcctInfoObj objSessionAcctInfo = (Canon_E193_AcctInfoObj)session.getAttribute("objSessionAcctInfo");
	
//	String strURL = "canon_E193_csCustProfileLink.jsp"; 
//	strURL = strURL + "?custAcctId=" + objSessionAcctInfo.getAcctId();
//	strURL = strURL + "&customerName=" + objSessionAcctInfo.getAcctName();
//	strURL = strURL + "&customerNumber=" + objSessionAcctInfo.getAcctNum();
	int accountId = objSessionAcctInfo.getAcctId();
	String strCustName = objSessionAcctInfo.getAcctName();
	String strCustNumber = objSessionAcctInfo.getAcctNum();
	
	String hPath = request.getParameter("hPath");
	if (hPath == null)
		hPath = strSelectAccountT1;
	else
		if(hPath.indexOf(strSelectAccountT1) < 0)
			hPath = hPath + " -> " + strSelectAccountT1;	
%>
 
<%@ include file="canon_E193_csMenuInc.jsp" %> 

<script language="JavaScript">
	function action_func1()
	{
	    var acctId = "<%=accountId%>";
		var custName = "<%=strCustName%>";
		var custNumber = "<%=strCustNumber%>";
		custName = encodeURIComponent(custName);
		var vURL = "canon_E193_csCustProfileLink.jsp?custAcctId="+acctId+"&customerName="+custName+"&customerNumber="+custNumber;
		var vWin = window.open( vURL, "CustomerProfile","height=600,width=1100,resizable=yes,scrollbars=1,top=140,left=200" ); 
		vWin.focus();
	}
	
	/* function action_func2()
	{
		var bilFlg= $('#billFlg').val();
		if(bilFlg == 'Y'){
			//document.addrSelectForm.action = "canon_E193_csEICheckRequest.jsp?billing=Yes";
			document.addrSelectForm.action = "canon_E193_csEICheckRequest.jsp";
			document.addrSelectForm.submit();			
		}else{
			document.addrSelectForm.action = "canon_E193_csNBIssueList.jsp";
			document.addrSelectForm.submit();				
			
		}

	} */
	
	function action_func3(iPageIndex, iTotPageIndex) 
	{
	      var objForm = document.addrSelectForm;
	      objForm.sTicketPageNo.value = iPageIndex;
	      objForm.sTicketTotPageNo.value = iTotPageIndex;
	      objForm.submit();
	}
	
	//Start- Modified this function for Billing/Non-Billing Issue Selection
	function action_func2()
	{
		  document.addrSelectForm.action = "canon_E193_csEICheckRequest.jsp";
		  document.addrSelectForm.submit();			
	}
	//End
//---------------------------------------------------------------------------------------
   // Global variables
   var orderAsc = "asc";
   var orderDesc = "desc";
   var tArray = new Array("number", "category", "date", "status", "acctName", "acctNo", "invNo", "contractNo", "ordNo", "daysOpen", "linesUnassigned");
   
   /*function InvoiceDetails(val1) {
      var vLink = "canon_E193_csActualInvoiceController.jsp?InvNo=" + val1;
      var vWin = window.open(vLink, "newwin","height=300,width=900,toolbar=no,menubar=no,scrollbars=yes, resizable=yes, alwaysRaised=yes" );
      vWin.focus();
   }
   
   function TicketStatus(val1) {
      var vLink = "canon_E193_csTicketStatusP.jsp?ticketId=" + val1;
      var vWin = window.open(vLink, "newwin","height=610,width=1000,toolbar=no,menubar=no,scrollbars=yes, resizable=yes, alwaysRaised=yes" );
      vWin.focus();
   }*/

   function fnOrderBy(name) {
      var objForm = document.addrSelectForm;
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
  
   /*function fnAssgn(tktVal)
   {
      var vLink = "canon_E193_csAssignmentP.jsp?ticketId=" + tktVal; 
      var vWin = window.open(vLink, "newwin", "height=300,width=900,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,alwaysRaised=yes"); 
      vWin.focus();
   }*/
   
   
   
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
	     var detectBrowser=msieversion();
	    if("otherbrowser"!=detectBrowser)
		{
	 		$('.ui-widget-overlay').remove();
	 	}
}
   
   function select_Line(selLineField, val1, size)
   {
      if (selLineField.checked)
      {
         document.tktStatusForm.hLineSel.value = selLineField.value;
         for(i=0; i<size; i++) {
            if(document.getElementById(i) != null && document.getElementById(i).style.visibility == 'visible') {
               document.getElementById(i).style.visibility = 'hidden';
               break;
            }
         }
         document.getElementById(val1).style.visibility = 'visible';
      }
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
      
   function get_sublines(val1,val2)
   {
      var tktId = document.tktStatusForm.tktId.value;
      var controlForm;
      var invNum = document.tktStatusForm.invNum.value;
      var invSource = document.tktStatusForm.invSource.value;
      var invSourceType = val2;
      var lineId = val1;
      
      if(document.tktStatusForm.hIsBilling.value == "Y")
         controlForm = "canon_E193_csBIssueList.jsp";
      else
         controlForm = "canon_E193_csNBIssueList.jsp";
      
      var vLink = "canon_E193_csTicketLineControllerP.jsp?iLineId="+lineId+"&iTicketId="+tktId+"&controlForm="+controlForm+"&invoiceNumber="+invNum+"&InvSource="+invSource+"&strInvoiceType="+invSourceType; 
      var vWin = window.open(vLink, "newwin2", "height=400,width=900,toolbar=no,menubar=no,scrollbars=yes,resizable=yes"); 
      vWin.focus();
   }
   
    function OpenDff(sourceApplication, sourceContext, contextValue, userId) 
   	{	
		showPleaseWait();	
	    var modelName ="#dlg2";
	     	
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
		 //$('.ui-widget-overlay').remove();
		 		
      }
//---------------------------------------------------------------------------------------
</script>

<div id="main_content">
	<div id="page_top">
		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strSelectAccountT1 %></h1>
		<div class="breadcrumb"><%=hPath%></div>
	</div>

	<form name="addrSelectForm" method="post">
	<!-- hidden variables -->
	<input type="hidden" name="origName" value="<%=request.getParameter("origName")%>" >
	<input type="hidden" name="origPhNo" value="<%=request.getParameter("origPhNo")%>">
	<input type="hidden" name="origEmailAddress" value="<%=request.getParameter("origEmailAddress")%>">
	<input type="hidden" name="origCheckbox" value="<%=request.getParameter("origCheckbox")%>">
	<input type="hidden" name="origType" value="<%=request.getParameter("origType")%>" >
	<input type="hidden" name="sourceType" value="<%=request.getParameter("sourceType")%>" >		
	<input type="hidden" name="custName" value="<%=request.getParameter("custName")%>" >
	<input type="hidden" name="custPhNo" value="<%=request.getParameter("custPhNo")%>">
	<input type="hidden" name="custEmailAddress" value="<%=request.getParameter("custEmailAddress")%>">
	
	<input type="hidden" name="recurProb" value="<%=request.getParameter("recurProb")%>">
	<input type="hidden" name="probType" value="<%=request.getParameter("probType")%>">
	<input type="hidden" name="vBillType" value="<%=request.getParameter("vBillType")%>">
	<input type="hidden" name="val1" value="<%=request.getParameter("val1")%>">
	
	<input type="hidden" name="selAcctId" value="<%=objSessionAcctInfo.getAcctId()%>" >
	<input type="hidden" name="selLocId" value="<%=request.getParameter("selLocId")%>" >
	<input type="hidden" name="selAcctName"  id="selAcctName" value="<%=objSessionAcctInfo.getAcctName()%>" >
	<input type="hidden" name="selAcctNum" id="selAcctNum" value="<%=objSessionAcctInfo.getAcctNum()%>" >
	
	<input type="hidden" name="hPageFrom" value="EISelectAddress" >
	<input type="hidden" name="hPath" value="<%=hPath%>">
	
	<input type="hidden" name="orderName" value="<%=request.getParameter("orderName")%>">
    <input type="hidden" name="orderBy" value="<%=request.getParameter("orderBy")%>">
    <input type="hidden" name="billFlg" id="billFlg" value="<%=objSessionAcctInfo.getStrBillFlg()%>">
    
    <input type="hidden" name="sTicketPageNo" value="<%=request.getParameter("sTicketPageNo")==null?"0":request.getParameter("sTicketPageNo")%>">    
    <input type="hidden" name="sTicketTotPageNo" value="<%=request.getParameter("sTicketTotPageNo")==null?"0":request.getParameter("sTicketTotPageNo")%>">
    
    <div style="margin-left:10px; margin-top: 10px;"><b><%=strSelectAccountN1%></b></div> 
    <table class="request-service" style="align:center;margin-left:10px;" cellpadding="1" cellspacing="0" border="0">
	    
		<tr>		 
		 <td>
	 		<table class="customer-care">
	         <tr>
				<th>Account Name</th>
				<th>Account Number</th>
				<th>Open Tickets</th> 
				<th>Tickets Last 90 Days</th>
			  </tr>
	<%	if(objSessionAcctInfo.getAcctId() == 0)	{
	%>				
				<tr>
					<td colspan="4">
						<b>Account not found for this value. Please revise your search criteria.</b>
					</td>
				</tr>
	<% }else{ %>
				<tr >
					<td><%=objSessionAcctInfo.getAcctName()%></td>
					<td><%=objSessionAcctInfo.getAcctNum()%></td>
					<td><%=objSessionAcctInfo.getOpenCIFlag()%></td>
					<td><%=objSessionAcctInfo.getOpenCIForNinetyDays()%></td>
					
				</tr>
	<%}%>					
		
	 </table>
	</td>
	</tr>	
    </table>
    <%
    String strOpenFlag = "";
    String strAcctNumber = "";
    strOpenFlag = objSessionAcctInfo.getOpenCIFlag();
    strAcctNumber = objSessionAcctInfo.getAcctNum();
    int iPageNo = Integer.parseInt(request.getParameter("sTicketPageNo")==null?"0":request.getParameter("sTicketPageNo"));
    int iTotPageNo = Integer.parseInt(request.getParameter("sTicketTotPageNo")==null?"0":request.getParameter("sTicketTotPageNo"));
    Canon_E193_TicketHeaderObj objTicketHeader = null;
    ArrayList alTicket = (ArrayList)objHistArrayList.getArrayList();
	   alTicket = objOpenTicket.getOpenTickets(strAcctNumber, iPageNo, iTotPageNo);
    
    if("Yes".equalsIgnoreCase(strOpenFlag)) { %>
    <div style="margin-left:10px;"><b>Open Tickets:</b></div>
    <%} %>
    <table class="request-service" style="align:center;margin-left:10px;" cellspacing="0">    
	<tr>	
	<td>
	 
	   <!-------------------------------------------------------------------------------------->
<% 
   if("Yes".equalsIgnoreCase(strOpenFlag))
   {
%>    
<table class="customer-care" cellspacing="1"> 		
   <tr>      
               <th>Number</th>
               <th>Category</th>
               <th>Date</th>
               <th>Status</th>
               <th>Account Name</th>
               <th>Account Number</th>
               <th>Invoice Number</th>
               <th>Contract Number</th>
               <th>Order Number</th>
               <th>Days Open</th>
               <th>Lines Unassigned</th>
               <th>&nbsp;</th>
            </tr>
<!-------------------------------------------------------------------------------------->
            <% 
               
			   
			   if(alTicket != null && alTicket.size() > 0) {
			      iPageNo = ((Integer)alTicket.get(0)).intValue();
			      alTicket.remove(0);
			      iTotPageNo = ((Integer)alTicket.get(0)).intValue();
			      alTicket.remove(0);
		       }
		       objHistArrayList.setArrayList(alTicket);
               
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
                     
					 String strStatus = String.valueOf(objTicketHeader.getStatus());
                     if("UNASSIGNED".equalsIgnoreCase(strStatus)) {
			%>       
                     <tr>
                        <td><%= objTicketHeader.getTicketNo()==null?"":objTicketHeader.getTicketNo()%></td>
                        <td><%= objTicketHeader.getCategory()==null?"":objTicketHeader.getCategory()%></td>
                        <td nowrap><%= objTicketHeader.getDate()==null?"":objTicketHeader.getDate()%></td>
                        <td><%= objTicketHeader.getStatus()==null?"":objTicketHeader.getStatus()%></td>
                        <td><%= objTicketHeader.getAcctName()==null?"":objTicketHeader.getAcctName()%></td>
                        <td><%= objTicketHeader.getAcctNo()==null?"":objTicketHeader.getAcctNo()%></td>
                        <td><a href="javascript:void InvoiceDetailsPDF('<%=objTicketHeader.getInvoiceNo()%>');"><%= objTicketHeader.getInvoiceNo()==null?"":objTicketHeader.getInvoiceNo()%></a></td>
                        <td><%= objTicketHeader.getContractNo()==null?"":objTicketHeader.getContractNo()%></td>
                        <td><%=strOrdrNo%></td>
                        <td><%=objTicketHeader.getIDaysOpen()%></td>
                        <td><%=strUnassignedLinesFlagDesc%></td>
                        <td><a href="#" onClick="javascript:getAssignMentDetails('canon_E193_csAssignmentP.jsp','ticketId','<%=objTicketHeader.getTicketNo()%>','Assignment Details');">Assignment<br>Details</a></td>
                     </tr>
            <%    
				     }
					 else {
			%>       
                     <tr>
                        <td><a href="javascript:void getLovWithValue('canon_E193_csTicketStatusP.jsp','ticketId','<%=objTicketHeader.getTicketNo()%>','Ticket Status');"><%= objTicketHeader.getTicketNo()==null?"":objTicketHeader.getTicketNo()%></a></td>
                        <td><%= objTicketHeader.getCategory()==null?"":objTicketHeader.getCategory()%></td>
                        <td nowrap><%= objTicketHeader.getDate()==null?"":objTicketHeader.getDate()%></td>
                        <td><%= objTicketHeader.getStatus()==null?"":objTicketHeader.getStatus()%></td>
                        <td><%= objTicketHeader.getAcctName()==null?"":objTicketHeader.getAcctName()%></td>
                        <td><%= objTicketHeader.getAcctNo()==null?"":objTicketHeader.getAcctNo()%></td>
                        <td><a href="javascript:void InvoiceDetailsPDF('<%=objTicketHeader.getInvoiceNo()%>');"><%= objTicketHeader.getInvoiceNo()==null?"":objTicketHeader.getInvoiceNo()%></a></td>
                        <td><%= objTicketHeader.getContractNo()==null?"":objTicketHeader.getContractNo()%></td>
                        <td><%=strOrdrNo%></td>
                        <td><%=objTicketHeader.getIDaysOpen()%></td>
                        <td><%=strUnassignedLinesFlagDesc%></td>
                        <td><a href="#" onClick="getAssignMentDetails('canon_E193_csAssignmentP.jsp','ticketId','<%=objTicketHeader.getTicketNo()%>','Assignment Details');">Assignment<br>Details</a></td>
                     </tr>
            <%    					 
					 }
			      }
                  //int iTotPageNo = (int)(Integer.parseInt(request.getParameter("iTotPageNo")));
                  //int iPageNo = (int)(Integer.parseInt(request.getParameter("iPageNo")));
                  if (iTotPageNo > 1) {
            %>
                     <tr class="tdTableCellStyle">
                        <td colspan="12"> Pages:  &nbsp; &nbsp;
            <%
                           for(int j=1;j<=iTotPageNo;j++) {
                              if(j == iPageNo) {
            %>                         
                                 &nbsp;<%=j%>
            <%                            
                              }else{
            %>
                                 &nbsp;<a href="javascript:action_func3(<%=j%>, <%=iTotPageNo %>);"><%=j%></a>
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
            <% } %>
<!-------------------------------------------------------------------------------------->         
<%
   }
%>
<!-------------------------------------------------------------------------------------->
	   </table>
	</td>
	</tr>
	</table>
    
	</form>
	<div style="text-align: center; margin-bottom: 5px;">
	<%								if(objSessionAcctInfo.getAcctId() == 0)
								{
	%>							
					<a class="btn_red" href="javascript:history.back();">Prev</a> 
					<a class="btn_red" style="margin-left: 10px;" href="#">Next</a> 
																			
	<%
								}
								else
								{
	%>	
			<a class="btn_red" href="javascript:history.back();">Prev</a> 
			<a class="btn_red" style="margin-left: 10px;" href="javascript:action_func2();">Next</a> 											
<%
								}
%>				
				
	</div>	
	<div id="dlg"></div>
	<div id="dlg2"></div>
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>