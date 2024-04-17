<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="oracle.apps.custombilling.beans.CanonCustomBillingBillToSiteBean"%>
<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.*" %>
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
		 
        $("#billToSiteLOV").submit(function (){
            //$(".btn1").trigger("click");
            $("#billToSiteLOV .btn1").focus();
            return false;
        });
        
        $("#billToSiteLOV a.linkPage").click(function(event){
            var pageNo=$(this).data("page");
            $("#billToSiteLOV #pageNo").val(pageNo);
            $("#billToSiteLOV .btn1").click();
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

    String billToSiteSrch = CanonCustomBillingCommon.nonNullify(
            request.getParameter("billToSiteSrch"));
    String customerNameSrch = CanonCustomBillingCommon.nonNullify(
            request.getParameter("customerNameSrch"));

%>


<form name="billToSiteLOV" id ="billToSiteLOV" 
      action="canonCustomBillingBillToSiteLOV.jsp" method="POST">

    <input type ="hidden" id="modalName"
           name="modalName" value="<%=CanonPCISecurityUtil.htmlEncode( modalName )%>">
    <input type ="hidden" id="pageNo" name="pageNo" value="<%=pageNo%>">
    <input type ="hidden" name="sortOrder" value="<%=CanonPCISecurityUtil.htmlEncode( CanonCustomBillingCommon.nonNullify(sortOrder) )%>">
    <input type ="hidden" name="sortBy" value="<%=CanonPCISecurityUtil.htmlEncode( CanonCustomBillingCommon.nonNullify(sortBy) )%>">

    <table width="650" align="center" bgColor="ffffff">
        <tr>
            <td nowrap class="sectionHeaderBlack" align="left" ><br>
                Search Bill To Location</td>
        </tr>

        <tr>
            <td>
                <table align="center" width="100%" border="0" cellspacing="0" 
                       cellpadding="2" summary="">
                    <tr>
                        <td class="bsd_text" align="left" >
                            <label>Customer Name :
                                <input type="text" name="customerNameSrch" id="customerNameSrch" 
                                       value="<%=customerNameSrch%>"  size="25"  tabindex="1" >
                            </label>      
                            <label>Location :
                                <input type="text" name="billToSiteSrch" id="billToSiteSrch" 
                                       value="<%=billToSiteSrch%>"  size="10"  tabindex="2">
                            </label>      
                        </td> 	
                        <td align="right">
                            <input style="font-size: 11px;" type="button" tabindex="2" class="btn1"   onclick="javascript:searchBillToSiteLOV('<%=modalName%>','billToSiteLOV')" value="Search >" /> 
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
            Object[] result = dao.getBillToSites(CanonPCISecurityUtil.sqlEncode( billToSiteSrch ),
            		CanonPCISecurityUtil.sqlEncode( customerNameSrch ), p_start_range, p_end_range,
            		CanonPCISecurityUtil.sqlEncode( sortBy ),
            		CanonPCISecurityUtil.sqlEncode( sortOrder));
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
                        <th height="30" width="50" style="text-align:center;">
                            <a class="linkCanonTableCell"  href="#" onclick="javascript:searchBillToSiteLOV('<%=modalName%>','billToSiteLOV',1,'LOCATION')">Location</a>
                        </th>
                        <th height="30" width="150" style="text-align:center;">
                            <a class="linkCanonTableCell" href="#" onclick="javascript:searchBillToSiteLOV('<%=modalName%>','billToSiteLOV',1,'CUSTOMER_NAME')">Customer Name</a>
                        </th>             
                        <th height="30" width="200" style="text-align:center;">
                            <a class="linkCanonTableCell" href="#" onclick="javascript:searchBillToSiteLOV('<%=modalName%>','billToSiteLOV',1,'ADDRESS1')">Address</a>
                        </th>             
                    </tr>

                    <%


                        int i = 0;
                        int size = list.size();
                        if (size > 0) {
                            for (i = 0; i < size; i++) {
                                CanonCustomBillingBillToSiteBean bean =
                                        (CanonCustomBillingBillToSiteBean) list.get(i);

                                String customerName = CanonCustomBillingCommon.nonNullify(bean.getCustomerName());
                                String location_ = billToSiteSrch = CanonCustomBillingCommon.nonNullify(bean.getLocation());
                                String address= CanonCustomBillingCommon.getAddress(bean.getAddress1(), bean.getAddress2(),
                                        bean.getCity(), bean.getState(), bean.getPostalCode());
                                
                    %>
                    <tr class='eventableDataCellModal'> 
                        <td height="30" style="word-break:break-all" align="center">
                            <a href="#" onclick="setCustomerNameValue('<%=modalName%>',{index:<%=i%>,location:'<%=location_%>',customerName:'<%=customerName%>'})">
                                <%=location_%>
                            </a>
                        </td>

                        <td height="30" style="word-break:break-all" align="center">
                            <%=customerName%>
                        </td>

                        <td height="30" style="word-break:break-all" align="center">
                            <%=address%>
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