<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="oracle.apps.custombilling.beans.CanonCustomBillingCustomerNameBean"%>
<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.*" %>

<style>
    .oddtableDataCellModal { background-color: #E5E5C7; text-align: left; font-family: verdana; font-size: 8pt }
    .oddtableDataCellBoxModal { text-align: left; font-family: verdana; font-size: 8pt }

    .eventableDataCellModal { background-color: #f7f7e7; text-align: left; font-family: verdana; font-size: 8pt }
    .eventableDataCellBoxModal { text-align: left; font-family: verdana; font-size: 8pt }
    A.linkCanonTableCell{color:white}
</style>

<script type="text/javascript">
    jQuery(document).ready(function (){
        jQuery(".btn1").mouseover(function (){
            jQuery(this).css({"background-color":"#FBE3E4"});}).mouseout(function (){
            jQuery(this).css({"background-color":"#F5F5F5"});
        });
		 
        $("#parentCustomerLOV").submit(function (){
            //$(".btn1").trigger("click");
            $("#parentCustomerLOV .btn1").focus();
            return false;
        });
        
        $("#parentCustomerLOV a.linkPage").click(function(event){
            var pageNo=$(this).data("page");
            $("#parentCustomerLOV #pageNo").val(pageNo);
            $("#parentCustomerLOV .btn1").click();
            event.preventDefault();
        });
    });
</script>

<%

    int recordsPerPage = 10;
    int pageNo = 1;
    String sortBy = "CUSTOMER_NAME";
    String sortOrder = "asc";
    int totalRecords = 0;
    int totalPages = 0;

    String modalName = request.getParameter("modalName");
    if (request.getParameter("pageNo") != null) {
        pageNo = CanonCustomBillingCommon.toInt(request.getParameter("pageNo"));
    }
    if (request.getParameter("sortBy") != null) {
        sortBy = request.getParameter("sortBy");
    }
    if (request.getParameter("sortOrder") != null) {
        sortOrder = request.getParameter("sortOrder");
    }

    String parentCustomerSrch = CanonCustomBillingCommon.nonNullify(
            request.getParameter("parentCustomerSrch"));

%>


<form name="parentCustomerLOV" id ="parentCustomerLOV" 
      action="canonCustomBillingParentCustomerLOV.jsp" method="POST">

    <input type ="hidden" id="modalName"
           name="modalName" value="<%=modalName%>">
    <input type ="hidden" id="pageNo" name="pageNo" value="<%=pageNo%>">
    <input type ="hidden" name="sortOrder" value="<%=CanonCustomBillingCommon.nonNullify(sortOrder)%>">
    <input type ="hidden" name="sortBy" value="<%=CanonCustomBillingCommon.nonNullify(sortBy)%>">

    <table width="650" align="center" bgColor="ffffff">
        <tr>
            <td nowrap class="sectionHeaderBlack" align="left" ><br>
                Search Parent Customer </td>
        </tr>

        <tr>
            <td>
                <table align="center" width="100%" border="0" cellspacing="0" 
                       cellpadding="2" summary="">
                    <tr>
                        <td class="bsd_text" align="left" >
                            <label>Customer Name :
                                <input type="text" name="customerNameSrch" id="customerNameSrch" 
                                       value="<%=parentCustomerSrch%>"  size="25"  tabindex="1" onkeyup='javascript:$(this).val($("#parentcustomerSrch").val().toUpperCase());'>
                            </label>      
                            
                        </td> 	
                        <td align="right">
                            <input style="font-size: 11px;" type="button" tabindex="2" class="btn1"   onclick="javascript:searchcustomerNameLOV('<%=modalName%>','parentCustomerLOV')" value="Search >" /> 
                        </td> 

                    </tr>
                </table>
            </td>
        </tr>

        <%

            BigDecimal p_start_range = new BigDecimal((pageNo - 1) * recordsPerPage + 1);
            BigDecimal p_end_range = p_start_range.add(new BigDecimal( recordsPerPage - 1));
            CanonCustomBillingSearchingDAO dao= new CanonCustomBillingSearchingDAO();
            
            ArrayList list =null;
            Object[] result = dao.getParentCustomer(parentCustomerSrch, p_start_range, p_end_range, sortBy, sortOrder);
            if (result != null && result[0] != null) {
                list = (ArrayList) result[0];
                totalRecords = ((BigDecimal) result[1]).intValue();
            }
            totalPages = totalRecords / recordsPerPage;
            if (totalRecords % recordsPerPage > 0) {
                totalPages++;
            }
        %>
        <tr>
          <table style="width: 100%;font-size: 11px;">
           <tr>
            <td style="text-align: right;"><%=CanonCustomBillingCommon.genPaginationSummary(totalRecords,recordsPerPage,pageNo) %></td>
           </tr>
          </table>
         </tr>
        <tr>
            <td>
                <table cellspacing="1" cellpadding="0" class="OraBGAccentDark" width="98%" align="center">

                    <tr class="canontableCell">
                        <th height="30" style="text-align:center;">
                            <a class="linkCanonTableCell" href="#" onclick="javascript:searchcustomerNameLOV('<%=modalName%>','parentCustomerLOV',1,'CUSTOMER_NAME')">Customer Name</a>
                        </th>             
                        <th height="30" style="text-align:center;">
                            <a class="linkCanonTableCell" href="#" onclick="javascript:searchcustomerNameLOV('<%=modalName%>','parentCustomerLOV',1,'CUSTOMER_NUMBER')">Customer Number</a>
                        </th>             
                    </tr>

                    <%


                        int i = 0;
                        int size = list.size();
                        if (size > 0) {
                            for (i = 0; i < size; i++) {
                            	CanonCustomBillingCustomerNameBean bean =
                                        (CanonCustomBillingCustomerNameBean) list.get(i);


                                String customerName = CanonCustomBillingCommon.nonNullify(bean.getCustomerName());
                                String customerNumber= CanonCustomBillingCommon.nonNullify(bean.getCustomerNumber());
                    %>
                    <tr class='eventableDataCellModal'> 
                        <td height="30" style="word-break:break-all" align="center">
                            <a href="#" onclick="setCustomerNameValue('<%=modalName%>',{index:<%=i%>,parentCustomer:'<%=customerName%>',parentCustomerNumber:'<%=customerNumber%>'})">
                                <%=customerName%>
                            </a>
                        </td>
                      
                        <td height="30" style="word-break:break-all" align="center">
                            <%=customerNumber%>
                        </td>

                    </tr>

                    <%
                            }
                        }




                    %> 


                </table>
                <div class='eventableDataCell' style="text-align:center;">
                    <%=CanonCustomBillingCommon.genPageLinks(null,
                            null,
                            null,
                            totalPages,
                            pageNo)%>
                </div>
            </td>
        </tr>    








    </table>



</form>