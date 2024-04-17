<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingUtil"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="canon.apps.pci.util.CanonPCISecurityUtil"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>

<%!    
    static String status(CanonCustomBillingSearchingDAO.CustomInvoiceInfo b) {
        String reviewflag = b.getReviewRequired();
        String pendingAction = b.getPendingAction();
        if ("Y".equals(reviewflag)) {
            if (pendingAction == null || "Y".equals(pendingAction)) {
                return "<a href='#' title='click to approve or reject' class='approval' >PENDING</a>";
            } else if ("S".equals(pendingAction)) {
                return "<a href='#' title='click to email invoice to cutomer.' class='email' >APPROVED</a>";
            } else if ("R".equals(pendingAction)) {
                return "REJECTED";
            }
            else if ("RETURNED".equals(pendingAction)) { 
      		  return "Invoice Returned";
            }
            else if ("INVOICE REJECTED".equals(pendingAction)) { 
        		  return "Invoice Rejected";
              }
        } else if ("N".equals(reviewflag)) {
        	if (pendingAction == null) { 
            return "<a href='#' title='click to email invoice to cutomer.' class='email' >APPROVED</a>";
        	}
        	else if ("RETURNED".equals(pendingAction)) { 
        		  return "Invoice Returned";
            }
        }
        return "";
    }

 static String nonBillerStatus(CanonCustomBillingSearchingDAO.CustomInvoiceInfo b) {
    String reviewflag = b.getReviewRequired();
    String pendingAction = b.getPendingAction();
    if ("Y".equals(reviewflag)) {
        if (pendingAction == null || "Y".equals(pendingAction)) {
            return "PENDING";
        } else if ("S".equals(pendingAction)) {
            return "APPROVED";
        } else if ("R".equals(pendingAction)) {
            return "REJECTED";
        }
        else if ("RETURNED".equals(pendingAction)) { 
  		  return "Invoice Returned";
        }
        else if ("INVOICE REJECTED".equals(pendingAction)) { 
    		  return "Invoice Rejected";
          }
    } else if ("N".equals(reviewflag)) {
    	if (pendingAction == null) { 
        return "APPROVED";
    	}
    	else if ("RETURNED".equals(pendingAction)) { 
    		  return "Invoice Returned";
        }
    }
    return "";
}
%>
<script language="javascript">
    
    function selRow(tableRow, highLight,color1) 
    { 
        if(highLight) 
        { 
            tableRow.style.color = 'white'; 
            tableRow.style.backgroundColor = '#A10304'; 
            tableRow.style.cursor ='pointer';
            
        } 
        else 
        { 
            tableRow.style.color = 'black';
            tableRow.style.backgroundColor = color1; 
            tableRow.style.cursor ='pointer';
        } 
    }
			
    function clearAll(){
        var myFrm = document.forms['invSearchForm'];
        myFrm.searchLocation.value = "";
        myFrm.searchBillNumber.value = "";
        myFrm.searchUrnNumber.value = "";				
        myFrm.searchBillPeriod.value="";
        myFrm.searchBillerName.value="";
        myFrm.searchParentCustomerName.value="";
        myFrm.searchCustomerGroup.value="";
        myFrm.searchCustomerName.value="";
        myFrm.searchPendingAction.value="";
        myFrm.fromDate.value="";
        myFrm.toDate.value="";
    }
    
    function invSearch(divName, pageNo, formName){
        showPleaseWait();	
        formId = "#"+formName;
        divId = "#"+divName;
        $('#pageNo').val(pageNo);
        var url = $(formId).attr('action');
     
        $.post( encodeURI(url), $(formId).serialize(),
        function( data ) {  
            hidePleaseWait();
            $(divId).html("");
            $(divId).html(data);
        });
    
    
    }
    function sortinvSearchData(sortBy){
        existingSortOrder = $("#sortOrder").val();
        existingSortBy = $("#sortBy").val();   
    
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
    
        invSearch('invSearchTopDiv', 1, 'invSearchForm');
    }
</script>
<!-- --------------------------- Canon changes Begin -------------------------------- -->    
<%
    CanonCustomBillingCommon C = new CanonCustomBillingCommon();
    CanonCustomBillingUtil util = new CanonCustomBillingUtil();
    int userId = C.toInt(request.getParameter("userId"));
    String userName = request.getParameter("userName");

    String searchLocation = request.getParameter("searchLocation");
    String searchBillNumber = request.getParameter("searchBillNumber");
    String searchUrnNumber = request.getParameter("searchUrnNumber");
    String searchBillPeriod = request.getParameter("searchBillPeriod");

    String searchBillerName = request.getParameter("searchBillerName");
    String searchParentCustomerName = request.getParameter("searchParentCustomerName");
    String searchCustomerGroup = request.getParameter("searchCustomerGroup");
    String searchCustomerName = request.getParameter("searchCustomerName");
    String searchPendingAction = request.getParameter("searchPendingAction")==null? "Y" : request.getParameter("searchPendingAction");

    Timestamp p_bill_date_from = C.toTimestamp(request.getParameter("fromDate"));
    Timestamp p_bill_date_to = C.toTimestamp(request.getParameter("toDate"));

    String errorMessage = null;

    //Pagination Variables
    int recordsPerPage = 10;
    int pageNo = 1;
    String sortBy = "1";
    String sortOrder = "desc";
    int totalRecords = 0;
    if (request.getParameter("pageNo") != null) {
        pageNo = Integer.parseInt(request.getParameter("pageNo"));
    }
    if (request.getParameter("sortBy") != null) {
        sortBy = request.getParameter("sortBy");
    } else {
        sortBy = "CREATION_DATE";
    }
    if (request.getParameter("sortOrder") != null) {
        sortOrder = request.getParameter("sortOrder");
    }

    String bclr = "";
    String rowBgClr = "";

    boolean isUserBiller = util.isUserBiller(userName);
    System.out.println(" InvSearchInclude---> isUserBiller: "+isUserBiller);

%>

<form id="invSearchForm" name="invSearchForm" method="post" action="canonCustomBillingInvSearchInclude.jsp">
    <input type="hidden" id="userId" name="userId" value="<%=userId%>"></input>
    <input type="hidden" id="userName" name="userName" value="<%=userName%>"></input>
    <input type='hidden' name='sortBy' id='sortBy' value="<%=CanonPCISecurityUtil.htmlEncode( sortBy)%>"></input>
    <input type='hidden' name='pageNo' id='pageNo' value="<%=CanonPCISecurityUtil.htmlEncode(pageNo+"")%>"></input>

    <input type='hidden' name='sortOrder' id='sortOrder' value="<%=CanonPCISecurityUtil.htmlEncode(sortOrder)%>"></input>

    <table align="center" width="850" border="0" cellspacing="0" cellpadding="2" summary="">
        <tr>
            <td class="sectionHeaderBlack" align="left"><div id="serialNumberDiv">Search Custom Invoice</div></td>
        </tr>
        <tr>
            <td align="left" valign=top width="80%" colspan=2><hr width=100%></td>
        </tr>
    </table>
    <table align="center" width="800" border="0" cellspacing="1" cellpadding="2" summary="">
        <tr>
            <td align="left" class="eventableDataCell" style="font-weight:bold;">
                Location:&nbsp;
            </td>
            <td cellspacing="0" cellpadding="0" cellspacing="2" >
                <table>
                    <tr>
                        <td align="left" class="eventableDataCell" style="font-weight:bold;">
                            <input type="text" id="searchLocation" name="searchLocation" style="text-align:right" value="<%=C.nonNullify(CanonPCISecurityUtil.htmlEncode(searchLocation))%>"></input>
                        </td>
                        <td class="buttons">
                            <a href="#" class="negative billToSiteDot" style="text-align:'center';font-family:'Verdana,Arial,sans-serif';font-size:'12px';height:'15px';margin:0 0 0 0;padding:2px 2px 2px 2px;">...</a>
                        </td>
                    </tr>
                </table>
            </td>
            
        </tr>
        <tr>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                URN Number:&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input type="text" id="searchUrnNumber" name="searchUrnNumber" style="text-align:left;margin-left:3px" value="<%=C.nonNullify(CanonPCISecurityUtil.htmlEncode(searchUrnNumber))%>" /> 
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Bill Number:&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input type="text" id="searchBillNumber" name="searchBillNumber" style="text-align:left;margin-left:3px"  value="<%=C.nonNullify(CanonPCISecurityUtil.htmlEncode(searchBillNumber))%>" /> 
            </td>

        </tr>
        <tr>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Biller Name:&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input type="text" id="searchBillerName" name="searchBillerName" style="text-align:left;margin-left:3px" value="<%=C.nonNullify(CanonPCISecurityUtil.htmlEncode(searchBillerName))%>" /> 
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Parent Customer:&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input type="text" id="searchParentCustomerName" name="searchParentCustomerName" style="text-align:left;margin-left:3px" value="<%=C.nonNullify(CanonPCISecurityUtil.htmlEncode(searchParentCustomerName))%>" /> 
            </td>
        </tr>

        <tr>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Customer Name:&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input type="text" id="searchCustomerName" name="searchCustomerName" style="text-align:left;margin-left:3px" value="<%=C.nonNullify(CanonPCISecurityUtil.htmlEncode(searchCustomerName))%>" /> 
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Customer Group:&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input type="text" id="searchCustomerGroup" name="searchCustomerGroup" style="text-align:left;margin-left:3px" value="<%=C.nonNullify(CanonPCISecurityUtil.htmlEncode(searchCustomerGroup))%>" /> 
            </td>
         </tr>
         <tr>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Bill Period:&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input type="text" id="searchBillPeriod" name="searchBillPeriod" style="text-align:left;margin-left:3px" value="<%=C.nonNullify(CanonPCISecurityUtil.htmlEncode(searchBillPeriod))%>" /> 
            </td>
            <% if(isUserBiller){ %>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Pending Action:&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <select id ="searchPendingAction" name="searchPendingAction">
                    <%
                        String[] valueEnum = new String[]{"", "S", "R", "Y","RETURNED"};
                        String[] diaplayEnum = new String[]{"", "Approved", "Rejected", "Pending","Returned"};
                        for (int m = 0; m < valueEnum.length; m++) {
                            String value = valueEnum[m];
                            String display = diaplayEnum[m];
                            String selected = C.nonNullify(searchPendingAction).equals(value) ? " selected " : "";
                    %>
                    <option value="<%=value%>" <%=selected%> ><%=display%></option>
                    <% }%>  
                </select>
            </td>
            <% } %>
        </tr>

        <tr>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Bill Date From:&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input style="text-align:left;margin-left:3px" type="text" name="fromDate" id="fromDate" class="datePicker" readonly="readonly" value="<%=request.getParameter("fromDate") == null ? "" : CanonPCISecurityUtil.htmlEncode(request.getParameter("fromDate"))%>" size="9">
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Bill Date To:&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input style="text-align:left;margin-left:3px" type="text" name="toDate" id="toDate" class="datePicker" readonly="readonly" value="<%=request.getParameter("toDate") == null ? "" :CanonPCISecurityUtil.htmlEncode( request.getParameter("toDate"))%>" size="9">
            </td>
        </tr>

    </table>
    <table cellspacing="2" cellpadding="0" style="float:right;margin-right: 50;margin-bottom: 0">
        <tr>
            <td class="buttons">
                <a href="#" onclick="javascript:invSearch('invSearchTopDiv', 1, 'invSearchForm');"  class="negative" style="text-align:'center';height:'25px';font-size:'11px';width:'100px';">Search ></a>
            </td>
            <td class="buttons">
                <a href="#" onclick="javascript:clearAll();"  class="negative" style="text-align:'center';height:'25px';font-size:'11px';width:'100px';">Clear ></a>
            </td>
        </tr>
    </table>

    <table align="center" width="850" border="0" cellspacing="1" cellpadding="2" summary="">
        <tr><td>
                <div class="bsd_text_bold error_message" style="font-size:11px; color: blue"><%=C.nonNullify(errorMessage)%>
                </div>
            </td></tr>
    </table>

    <%
      if(searchLocation!=null){
        ArrayList invSearchList = new ArrayList();
        BigDecimal p_start_range = new BigDecimal((pageNo - 1) * recordsPerPage + 1);
        BigDecimal p_end_range = p_start_range.add(new BigDecimal(recordsPerPage - 1));
        BigDecimal totalAmount=null;
        String user_role;
        if (isUserBiller) 
        { user_role = "Approver";
        } 
        else {
        	user_role= "User";
        	} 
        Object[] result = CanonCustomBillingSearchingDAO.searchCustomInvoices(
        		new BigDecimal(userId),
        		user_role,        		
        		CanonPCISecurityUtil.sqlEncode( searchBillNumber), 
        		CanonPCISecurityUtil.sqlEncode(searchPendingAction), 
        		CanonPCISecurityUtil.sqlEncode(searchBillPeriod),
        		CanonPCISecurityUtil.sqlEncode(searchUrnNumber),
        		CanonPCISecurityUtil.sqlEncode(searchBillerName),
        		CanonPCISecurityUtil.sqlEncode(searchParentCustomerName),
        		CanonPCISecurityUtil.sqlEncode(searchCustomerGroup),
        		CanonPCISecurityUtil.sqlEncode(searchCustomerName),
        		CanonPCISecurityUtil.sqlEncode(searchLocation), 
        		p_bill_date_from, p_bill_date_to, p_start_range, p_end_range, 
        		CanonPCISecurityUtil.sqlEncode(sortBy), 
        		CanonPCISecurityUtil.sqlEncode(sortOrder));
        String totAmtDue="";
        if (result != null && result[0] != null) {
            invSearchList = (ArrayList) result[0];
            totalRecords = ((BigDecimal) result[1]).intValue();
            totalAmount= (BigDecimal) result[2];
            totAmtDue=NumberFormat.getCurrencyInstance(Locale.US).format(totalAmount.doubleValue()) ;
        }

        int totalPages = totalRecords / recordsPerPage;

        if (totalRecords % recordsPerPage > 0) {
            totalPages++;
        }

        int noOfInvSearch = invSearchList.size();


    %>
    <table id="invoiceTable" align="center" width="850" border="0" cellspacing="1" cellpadding="2" summary="">
        <% if (totalRecords>0) { %> 
        <tr>
            <td class='eventableDataCell' style="text-align:right;font-weight:bold;" colspan="13" >
                Found <%=totalRecords%> Records, Total Amount: <%=totAmtDue%>
            </td>
        </tr>
        <% } %>
        <tr class="canontableCell" >
            <th height="30" width="50" style="text-align:left;font-size:11px;">
                <a class="linkCanonTableCell" href="#" onclick="sortinvSearchData('URN_NUMBER')">URN Number</a>
            </th>
            <th height="30" width="200" style="text-align:left;font-size:11px;">
                <a class="linkCanonTableCell" href="#" onclick="sortinvSearchData('BILLER_NAME')">Biller Name</a>
            </th>
            <th height="30" width="200" style="text-align:left;font-size:11px;">
                <a class="linkCanonTableCell" href="#" onclick="sortinvSearchData('PARENT_CUSTOMER_NAME')">Parent Customer Name</a>
            </th>
            <th height="30" width="200" style="text-align:left;font-size:11px;">
                <a class="linkCanonTableCell" href="#" onclick="sortinvSearchData('CUSTOMER_GROUP')">Customer Group</a>
            </th>
            <th height="30" width="200" style="text-align:left;font-size:11px;">
                <a class="linkCanonTableCell" href="#" onclick="sortinvSearchData('CUSTOMER_NAME')">Customer Name</a>
            </th>
            <th height="30" width="50" style="text-align:left;font-size:11px;">
                Review Required
            </th>     
            <th height="30" width="100" style="text-align:left;font-size:11px;">
                Bill Number
            </th>
            <th height="30" width="100" style="text-align:left;font-size:11px;">
                Bill To Location
            </th>
            <th height="30" width="100" style="text-align:left;font-size:11px;">
                Bill Period
            </th>
            <th height="30" width="100" style="text-align:left;font-size:11px;">
                Bill Date
            </th>
            <th height="30" width="100" style="text-align:left;font-size:11px;">
                Status
            </th>
            <th height="30" width="100" style="text-align:left;font-size:11px;">
                <a class="linkCanonTableCell" href="#" onclick="sortinvSearchData('REMARKS')">Remarks</a>
            </th>
            <th height="30" width="100" style="text-align:left;font-size:11px;">
                Amount Due
            </th>
             <th height="30" width="100" style="text-align:left;font-size:11px;">
                Approve/Reject Comments
            </th>
            
            
        </tr>
        <%
            if (noOfInvSearch == 0) {
        %>
        <tr>
            <td cellpadding="8" align="center" class="eventableDataCell" colspan="9">
                <span class="bsd_text">No records found.</span>
            </td>       
        </tr>    
        <%} else {
            for (int i = 0; i < noOfInvSearch; i++) {
                CanonCustomBillingSearchingDAO.CustomInvoiceInfo invSearchBean = (CanonCustomBillingSearchingDAO.CustomInvoiceInfo) invSearchList.get(i);
                if ((i % 2) == 0) {
                    bclr = "eventableDataCell";
                    rowBgClr = "#FFFFFF";
                } else {
                    bclr = "oddtableDataCell";
                    rowBgClr = "#D3D3D3";
                }

              String invPath  =  invSearchBean.getInvoicePath();
              String invFileName  =  invSearchBean.getInvoiceFileName();
        %>
        <tr class='<%=bclr%> ' data-invoicePath="<%=invPath%>" data-invoiceId ="<%=invSearchBean.getInvoiceId()%>" data-urnNumber ="<%=invSearchBean.getUrnNumber()%>"  data-invFileName ="<%=invFileName%>"  > 
            <td style="word-break:break-all"> <a title="Click to download invoice" class="downloadInvoice" href="#"> <%=C.nonNullify(invSearchBean.getUrnNumber())%> </a> </td>
            <td style="word-break:break-all"><%=C.nonNullify(invSearchBean.getBillerName())%></td>
            <td style="word-break:break-all"><%=C.nonNullify(invSearchBean.getParentCustomerName())%></td>
            <td style="word-break:break-all"><%=C.nonNullify(invSearchBean.getCustomerGroup())%></td>
            <td style="word-break:break-all"><%=C.nonNullify(invSearchBean.getCustomerName())%></td>
            <td style="word-break:break-all"><%=C.nonNullify(invSearchBean.getReviewRequired())%></td>
            <td style="word-break:break-all"><%=C.nonNullify(invSearchBean.getBillNumber())%></td>
            <td style="word-break:break-all"><%=C.nonNullify(invSearchBean.getBillLocation())%></td>
            <td style="word-break:break-all"><%=C.nonNullify(invSearchBean.getBillPeriod())%></td>
            <td style="word-break:break-all" nowrap="nowrap"><%=C.getUSFormattedDate(invSearchBean.getBillDate())%></td>
            <% if(isUserBiller){ %>
                <td style="word-break:break-all" nowrap="nowrap"><%=status(invSearchBean )%></td>
            <%}else { %>
                <td style="word-break:break-all" nowrap="nowrap"><%=nonBillerStatus(invSearchBean )%></td>
            <%} %>
            <td style="word-break:break-all" nowrap="nowrap"><%=C.nonNullify(invSearchBean.getRemarks())%></td>
            <%
            String amountDue =C.nonNullify(invSearchBean.getAmountDue());
     	    String amtDue=(amountDue.length()>0)?(NumberFormat.getCurrencyInstance(Locale.US).format(Double.parseDouble(amountDue))).toString() :"";
            %>
            
            <td style="word-break:break-all" align="right"><%=amtDue%></td>
            <td style="word-break:break-all" nowrap="nowrap"><%=C.nonNullify(invSearchBean.getComments())%></td>
        </tr>
        <%
            }
        %>

        <tr>
            <td class='eventableDataCell' style="text-align:center;" colspan="13" >
                <%=CanonCustomBillingCommon.genPageLinks("invSearch",
                        "invSearchTopDiv",
                        "invSearchForm",
                        totalPages,
                        pageNo)%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        }
    %>

    <div height="5px">&nbsp;</div>
</form>
