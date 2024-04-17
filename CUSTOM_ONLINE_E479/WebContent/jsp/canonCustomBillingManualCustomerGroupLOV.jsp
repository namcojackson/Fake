<%@page import="oracle.apps.custombilling.beans.CanonCustomBillingCustomerGroupBean"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingManualDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.*" %>
<%@page language="java" import="java.net.URLEncoder"%>
<%@page import="canon.apps.pci.util.CanonPCISecurityUtil"%>

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
		 
        $("#customerGroupLOV").submit(function (){
            //$(".btn1").trigger("click");
            $("#customerGroupLOV .btn1").focus();
            return false;
        });
        
        $("#customerGroupLOV a.linkPage").click(function(event){
            var pageNo=$(this).data("page");
            $("#customerGroupLOV #pageNo").val(pageNo);
            $("#customerGroupLOV .btn1").click();
            event.preventDefault();
        });
        

    });
</script>

<%

    int recordsPerPage = 10;
    int pageNo = 1;
    String sortBy = "1";
    String sortOrder = "asc";
    int totalRecords = 0;
    int totalPages = 0;

    String modalName = request.getParameter("modalName");
    String customerId = request.getParameter("customer_id");
    if (request.getParameter("pageNo") != null) {
        pageNo = CanonCustomBillingCommon.toInt(request.getParameter("pageNo"));
    }
    if (request.getParameter("sortBy") != null) {
        sortBy = request.getParameter("sortBy");
    }
    if (request.getParameter("sortOrder") != null) {
        sortOrder = request.getParameter("sortOrder");
    }

    String customerGroupSrch = CanonCustomBillingCommon.nonNullify(
            request.getParameter("customerGroupSrch"));

%>


<form name="customerGroupLOV" id ="customerGroupLOV" 
      action="canonCustomBillingManualCustomerGroupLOV.jsp" method="POST">

    <input type ="hidden" id="modalName"
           name="modalName" value="<%=modalName%>">
    <input type ="hidden" id="pageNo" name="pageNo" value="<%=pageNo%>">
    <input type ="hidden" id="customer_id" name="customer_id" value="<%=customerId%>">
    <input type ="hidden" name="sortOrder" value="<%=CanonCustomBillingCommon.nonNullify(sortOrder)%>">
    <input type ="hidden" name="sortBy" value="<%=CanonCustomBillingCommon.nonNullify(sortBy)%>">

    <table width="650" align="center" bgColor="ffffff">
        <tr>
            <td nowrap class="sectionHeaderBlack" align="left" ><br>
                Search Customer Group Name</td>
        </tr>

        <tr>
            <td>
                <table align="center" width="100%" border="0" cellspacing="0" 
                       cellpadding="2" summary="">
                    <tr>
                        <td class="bsd_text" align="left" >
                            <label>Customer Group Name :
                                <input type="text" name="customerGroupSrch" id="customerGroupSrch" 
                                       value="<%=customerGroupSrch%>"  size="25"  tabindex="1" onkeyup='javascript:$(this).val($("#customerGroupSrch").val().toUpperCase());'>
                            </label>      
                            
                        </td> 	
                        <td align="right">
                            <input style="font-size: 11px;" type="button" tabindex="2" class="btn1"   onclick="javascript:searchCustomerGroupLOV('<%=modalName%>','customerGroupLOV')" value="Search >" /> 
                        </td> 
                    </tr>
                </table>
            </td>
        </tr>

        <%

            BigDecimal p_start_range = new BigDecimal((pageNo - 1) * recordsPerPage + 1);
            BigDecimal p_end_range = p_start_range.add(new BigDecimal( recordsPerPage - 1));
            CanonCustomBillingManualDAO dao= new CanonCustomBillingManualDAO();
            
            ArrayList list =null;
            System.out.println(" customerId"+customerId);
            Object[] result = dao.getManualCustomGroupLOV(customerId,customerGroupSrch, p_start_range, p_end_range);
            if (result != null && result[0] != null) {
                list = (ArrayList) result[0];
                totalRecords = ((BigDecimal) result[1]).intValue();
            }
            System.out.println(" totalRecords"+totalRecords);
            System.out.println(" recordsPerPage "+recordsPerPage);
            totalPages = totalRecords / recordsPerPage;
            if (totalRecords % recordsPerPage > 0) {
                totalPages++;
            }
            System.out.println(" totalPages "+totalPages);
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
                        <th height="30" style="text-align:center; ">
                            <a class="linkCanonTableCell" href="#" onclick="javascript:searchCustomerGroupLOV('<%=URLEncoder.encode(modalName,"UTF-8")%>','customerGroupLOV',1,'GROUP_NAME')">Group Name</a>
                        </th>             
                    </tr>

                    <%
                        int i = 0;
                        int size = list.size();
                        System.out.println(" size "+size);
                        
                        if (size > 0) {
                            for (i = 0; i < size; i++) {
                            	 CanonCustomBillingManualDAO.CustGroupInfo bean =
                                        (CanonCustomBillingManualDAO.CustGroupInfo) list.get(i);
                                String groupName = CanonCustomBillingCommon.nonNullify(bean.getCustomerGroup());
                                System.out.println(" groupName "+groupName);
                                
                    %>
                    <tr class='eventableDataCellModal'> 
                        <td height="30" style="word-break:break-all" align="center">
                            <a href="#" onclick="setCustomerGroupValue('<%=modalName%>',{index:0,customerGroupName:'<%=groupName%>'})">
                                <%=groupName%>
                            </a>
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
