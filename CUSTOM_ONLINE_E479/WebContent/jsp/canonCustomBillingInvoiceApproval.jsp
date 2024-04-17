<%@page import="oracle.apps.custombilling.util.CanonCustomBillingUtil"%>
<%@page import="oracle.apps.custombilling.beans.CanonCustomBillingInvSearchBean"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="oracle.apps.custombilling.beans.CanonCustomBillingPartyNameBean"%>
<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.*" %>

<%!
    static String succeed(String pendingAction) {
        if ("S".equals(pendingAction)) {
            return "The invoice has been approved. Email sent to customer.";
        } else if ("R".equals(pendingAction)) {
            return "The invoice has been rejected.";
        } else {
            return "";
        }
    }

    static String failed(String pendingAction) {
        if ("S".equals(pendingAction)) {
            return "Failed to approve the invoice.";
        } else if ("R".equals(pendingAction)) {
            return "Failed to rejecte the invoice.";
        } else {
            return "";
        }
    }

%>
<style>
    .oddtableDataCellModal { background-color: #E5E5C7; text-align: left; font-family: verdana; font-size: 8pt }
    .oddtableDataCellBoxModal { text-align: left; font-family: verdana; font-size: 8pt }

    .eventableDataCellModal { background-color: #f7f7e7; text-align: left; font-family: verdana; font-size: 8pt }
    .eventableDataCellBoxModal { text-align: left; font-family: verdana; font-size: 8pt }
    A.linkCanonTableCell{color:white}
</style>

<script type="text/javascript">
    jQuery(document).ready(function (){
        
        function approveOrRejectInvoice(action){
            showPleaseWait();		
            var modelName=$("#modalName").val();
            $("#action").val(action);
            var url =  $("#invApprovalForm").attr('action');
            var data=$("#invApprovalForm").serialize();
            $.post( url, data,
            function( data ) {  
                hidePleaseWait();           
                $('#'+modelName).html(data);
                $("#comment").attr("disabled", true).addClass("ui-state-disabled");
                $(".ui-dialog-buttonset :button:contains('Approve')").attr("disabled", true).addClass("ui-state-disabled");
                $(".ui-dialog-buttonset :button:contains('Reject')").attr("disabled", true).addClass("ui-state-disabled");
            });
        }
        
        $(".ui-dialog-buttonset :button:contains('Approve')").click(function(){
            approveOrRejectInvoice("S");
        });
        
        $(".ui-dialog-buttonset :button:contains('Reject')").click(function(){
            approveOrRejectInvoice("R");
        });
    });
</script>

<%

    String modalName = request.getParameter("modalName");
    BigDecimal invoiceId = CanonCustomBillingCommon.toBigDecimal(request.getParameter("invoiceId"), false);
    BigDecimal userId= CanonCustomBillingCommon.toBigDecimal(request.getParameter("userId"), false);
    String action = CanonCustomBillingCommon.nonNullify(request.getParameter("action"));
    String comment = CanonCustomBillingCommon.nonNullify(request.getParameter("comment"));
    BigDecimal status = null;
    String error=null;
    if (action != null && action.trim().length() > 0) {
        try{
            status = (BigDecimal) CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.approveRejectInvoices(invoiceId, action, userId, comment));
            if("S".equals(action) &&  status.intValue() == 1){
                CanonCustomBillingUtil.sendEmailToCustomer(userId,invoiceId);
            }
        }catch(Exception e){
            error=e.toString();
        }
    }

%>


<form name="invApprovalForm" id ="invApprovalForm" 
      action="canonCustomBillingInvoiceApproval.jsp" method="POST">

    <input type ="hidden" id="modalName" name="modalName" value="<%=modalName%>">
    <input type ="hidden" id="action" name="action" >
    <input type ="hidden" id="invoiceId" name="invoiceId" value="<%=invoiceId%>">
    <input type ="hidden" id="userId" name="userId" value="<%=userId%>">

    <table width="100%" align="center" bgColor="ffffff">
        <tr>
            <td nowrap class="sectionHeaderBlack" align="left" ><br>
                Invoice Approval</td>
        </tr>


        <%
            List list = (List) CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getInvDetailsForApproval(invoiceId));
        %>
        <tr>
            <td>
                <table cellspacing="1" cellpadding="0" class="OraBGAccentDark" width="98%" align="center">

                    <tr class="canontableCell">
                        <th height="30" style="text-align:center;">
                            URN Number
                        </th>
                        <th height="30" style="text-align:center;">
                            Amount Due
                        </th>             
                    </tr>

                    <%
                        int size = list.size();
                        if (size > 0) {
                            for (int i = 0; i < size; i++) {
                                CanonCustomBillingInvSearchBean bean =
                                        (CanonCustomBillingInvSearchBean) list.get(i);

                    %>
                    <tr class='eventableDataCellModal'> 
                        <td height="30" style="word-break:break-all" align="center">
                            <%=CanonCustomBillingCommon.nonNullify(bean.getUrnNumber())%>
                        </td>

                        <td height="30" style="word-break:break-all" align="center">
                            <%=bean.getAmountDue()%>
                        </td>

                    </tr>

                    <%
                            }
                        }
                    %> 

                </table>
            </td>
        </tr>    
        
    </table>
    <table width="100%" align="right" bgColor="ffffff">
        <tr>
            <td class="eventableDataCell" style="font-weight:bold;">
                Comment:
                <textarea id="comment" name="comment" style="vertical-align: middle; overflow: auto" rows="5" cols="62" maxlength="3299" ><%=CanonCustomBillingCommon.nonNullify(comment)%></textarea>
            </td>
        </tr>    
        <%
            if (action != null && action.trim().length() > 0 && status != null && error==null) {
        %> 
        <tr>
            <td class="eventableDataCell"  style="font-weight:bold;">
                <%=status.intValue() == 1 ? succeed(action) : failed(action)%>
            </td>
        </tr>    
        <%
            }
        %> 
        <tr>
            <td class="eventableDataCell"  style="font-weight:bold;">
                <%=CanonCustomBillingCommon.nonNullify(error)%>
            </td>
        </tr>    
        
    </table>



</form>
