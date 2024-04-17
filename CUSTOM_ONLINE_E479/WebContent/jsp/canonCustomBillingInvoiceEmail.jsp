<%@page import="oracle.apps.custombilling.util.CanonCustomBillingUtil"%>
<%@page import="oracle.apps.custombilling.beans.CanonCustomBillingInvSearchBean"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="oracle.apps.custombilling.beans.CanonCustomBillingPartyNameBean"%>
<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.*" %>

<%!
    static String succeed() {
        return "Email has been sent to customer.";
    }

    static String failed() {
        return "Failed to send email to customer.";
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
        
        function send(action){
            showPleaseWait();		
            var modelName=$("#modalName").val();
            $("#action").val(action);
            var url =  $("#invEmailForm").attr('action');
            var data=$("#invEmailForm").serialize();
            $.post( url, data,
            function( data ) {  
                hidePleaseWait();           
                $('#'+modelName).html(data);
            });
        }
        
        $(".ui-dialog-buttonset :button:contains('Send')").click(function(){
            send("email");
        });
    });
</script>

<%

    String modalName = request.getParameter("modalName");
    BigDecimal invoiceId = CanonCustomBillingCommon.toBigDecimal(request.getParameter("invoiceId"), false);
    BigDecimal userId= CanonCustomBillingCommon.toBigDecimal(request.getParameter("userId"), false);
    String action = CanonCustomBillingCommon.nonNullify(request.getParameter("action"));
    BigDecimal status = null;
    String error=null;
    if (action != null && action.trim().length() > 0) {
        try{
            CanonCustomBillingUtil.sendEmailToCustomer(userId,invoiceId);
            status=new BigDecimal(1); 
        }catch(Exception e){
            error=e.toString();
            status=new BigDecimal(-1);
        }
    }

%>


<form name="invEmailForm" id ="invEmailForm" 
      action="canonCustomBillingInvoiceEmail.jsp" method="POST">

    <input type ="hidden" id="modalName" name="modalName" value="<%=modalName%>">
    <input type ="hidden" id="action" name="action" >
    <input type ="hidden" id="invoiceId" name="invoiceId" value="<%=invoiceId%>">
    <input type ="hidden" id="userId" name="userId" value="<%=userId%>">

    <table width="100%" align="center" bgColor="ffffff">
        <tr>
            <td nowrap class="sectionHeaderBlack" align="left" ><br>
                Email Invoice To Customer</td>
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
        <%
            if (action != null && action.trim().length() > 0 && status != null && error==null) {
        %> 
        <tr>
            <td class="eventableDataCell"  style="font-weight:bold;">
                <%=status.intValue() == 1 ? succeed() : failed()%>
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
