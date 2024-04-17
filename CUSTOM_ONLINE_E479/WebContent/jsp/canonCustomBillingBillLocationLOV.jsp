<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingManualDAO"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="oracle.apps.custombilling.beans.CanonCustomBillingBillToSiteBean"%>
<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="java.math.BigDecimal"%>
<%-- <%@page import="oracle.apps.bsd.site.util.CanonBSDSiteBillingDAO"%>
<%@page import="oracle.apps.jtf.util.SecurityCrossScript" %>
<%@page import="oracle.apps.bsd.admin.util.*"%> --%>
<%@page import="java.util.*" %>
<%@page language="java" import="java.net.URLEncoder"%>
<%@page import="canon.apps.pci.util.CanonPCISecurityUtil"%>

<%-- <%
    if(!oracle.apps.custombilling.util.CanonCustomBillingManualUtil.hasAccess(request,response)){
        %>
   <jsp:forward page="canonInvalidAccess.jsp"></jsp:forward>
	<%	
	}
%> --%>  

<style>
    .oddtableDataCellModal { background-color: #E5E5C7; text-align: left; font-family: verdana; font-size: 8pt }
    .oddtableDataCellBoxModal { text-align: left; font-family: verdana; font-size: 8pt }

    .eventableDataCellModal { background-color: #f7f7e7; text-align: left; font-family: verdana; font-size: 8pt }
    .eventableDataCellBoxModal { text-align: left; font-family: verdana; font-size: 8pt }
    A.linkCanonTableCell{color:white}
</style>

<script type="text/javascript">
    

  function searchBillLocationLOV(mName, formName, pageNo, sortBy){
        showPleaseWait();	
        formId = "#"+formName;
        modelName = "#"+mName;
        if(pageNo){
            $(formId +" input[name='pageNo']").val(pageNo);
        }
        if(sortBy){
            existingSortOrder = $(formId+" input[name='sortOrder']").val();
            existingSortBy = $(formId+" input[name='sortBy']").val();   

            if(sortBy==existingSortBy){
                if(existingSortOrder=='asc'){
                    existingSortOrder = 'desc';
                }else{
                    existingSortOrder = 'asc'
                }
                $(formId+" input[name='sortOrder']").val(existingSortOrder);
            }else{
                existingSortOrder ='asc';
                $(formId+" input[name='sortOrder']").val(existingSortOrder);
                $(formId+" input[name='sortBy']").val(sortBy);
            }
        }
        var url = $(formId).attr('action');
        $.post( url, $(formId).serialize(),
        function( data ) {  
        hidePleaseWait();
         $(modelName).html("");
        $(modelName).html(data);
      });
    
    
  }
    
  function setBillLocationValue(mName,data){
     modelName = "#"+mName;
      $(modelName).html("");
     $(modelName).dialog("close");
     $(modelName).dialog("destroy");     
     $(modelName).trigger("selected",data );
  }  
    
    jQuery(document).ready(function (){
        jQuery(".btn1").mouseover(function (){
            jQuery(this).css({"background-color":"#FBE3E4"});}).mouseout(function (){
            jQuery(this).css({"background-color":"#F5F5F5"});
        });
		 
        $("#billLocationLOV").submit(function (){
            //$(".btn1").trigger("click");
            $("#billLocationLOV .btn1").focus();
            return false;
        });
        
        $("#billLocationLOV a.linkPage").click(function(event){
            var pageNo=$(this).data("page");
            $("#billLocationLOV #pageNo").val(pageNo);
            $("#billLocationLOV .btn1").click();
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

    String billLocationSrch = CanonCustomBillingCommon.nonNullify(
            request.getParameter("billLocationSrch"));

%>


<form name="billLocationLOV" id ="billLocationLOV" 
      action="canonCustomBillingBillLocationLOV.jsp" method="POST">

    <input type ="hidden" id="modalName"
           name="modalName" value="<%=CanonPCISecurityUtil.htmlEncode( modalName )%>">
    <input type ="hidden" id="pageNo" name="pageNo" value="<%=pageNo%>">
    <input type ="hidden" id="customer_id" name="customer_id" value="<%=customerId%>">
    <input type ="hidden" name="sortOrder" value="<%=CanonPCISecurityUtil.htmlEncode( CanonCustomBillingCommon.nonNullify(sortOrder) )%>">
    <input type ="hidden" name="sortBy" value="<%=CanonPCISecurityUtil.htmlEncode( CanonCustomBillingCommon.nonNullify(sortBy) )%>">

    <table width="650" align="center" bgColor="ffffff">
        <tr>
            <td nowrap class="sectionHeaderBlack" align="left" ><br>
                Search Bill Location</td>
        </tr>

        <tr>
            <td>
                <table align="center" width="100%" border="0" cellspacing="0" 
                       cellpadding="2" summary="">
                    <tr>
                        <td class="bsd_text" align="left" >
                            <label>Location :
                                <input type="text" name="billLocationSrch" id="billLocationSrch" 
                                       value="<%=billLocationSrch%>"  size="10"  tabindex="2">
                            </label>      
                        </td> 	
                        <td align="right">
                            <input style="font-size: 11px;" type="button" tabindex="2" class="btn1"   onclick="javascript:searchBillLocationLOV('<%=URLEncoder.encode(modalName,"UTF-8")%>','billLocationLOV')" value="Search >" /> 
                        </td> 
                    </tr>
                </table>
            </td>
        </tr>

        <%

            BigDecimal p_start_range = new BigDecimal((pageNo - 1) * recordsPerPage + 1);
            BigDecimal p_end_range = p_start_range.add(new BigDecimal( recordsPerPage - 1));
            
            System.out.println("in getBillLocationLov customerId - " + customerId);
            System.out.println("in getBillLocationLov p_start_range - " + p_start_range);
            System.out.println("in getBillLocationLov p_end_range - " + p_end_range);
            
            Object[] result = CanonCustomBillingManualDAO.getBillToLocationLov(customerId, 
            											            CanonPCISecurityUtil.sqlEncode( billLocationSrch ),
            											            p_start_range, p_end_range,
            														CanonPCISecurityUtil.sqlEncode( sortBy ),
            														CanonPCISecurityUtil.sqlEncode( sortOrder));
            
            System.out.println("in getBillLocationLov result.length - " + result.length);
            
            /*String status= result != null && result.length>2? (String)result[2] : null;
            System.out.println("in getBillLocationLov status - " + status);
            List list=null;
            if ("S".equals(status)) {
                list =(List)CanonCustomBillingCommon.first(result);
            }
            System.out.println("in getBillLocationLov list - " + list);

            totalPages = totalRecords / recordsPerPage;
            if (totalRecords % recordsPerPage > 0) {
                totalPages++;
            }
            */
            List list=null;
            if (result != null && result[0] != null) {
                list = (List) result[0];
                totalRecords = ((BigDecimal) result[1]).intValue();
            }
            System.out.println("in getBillLocationLov list - " + list);
            System.out.println("in getBillLocationLov totalRecords - " + totalRecords);
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
                            <a class="linkCanonTableCell"  href="#" onclick="javascript:searchBillLocationLOV('<%=URLEncoder.encode(modalName,"UTF-8")%>','billLocationLOV',1,'LOCATION')">Location</a>
                        </th>
                        <th height="30" width="150" style="text-align:center;">
                            <a class="linkCanonTableCell" href="#" onclick="javascript:searchBillLocationLOV('<%=URLEncoder.encode(modalName,"UTF-8")%>','billLocationLOV',1,'CUSTOMER_NAME')">Customer Name</a>
                        </th>             
                        <th height="30" width="200" style="text-align:center;">
                            <a class="linkCanonTableCell" href="#" onclick="javascript:searchBillLocationLOV('<%=URLEncoder.encode(modalName,"UTF-8")%>','billLocationLOV',1,'ADDRESS1')">Address</a>
                        </th>             
                    </tr>

                    <%


                        int i = 0;
                        int size = list.size();
                        System.out.println("in getBillLocationLov size - " + size);
                        if (size > 0) {
                            for (i = 0; i < size; i++) {
                                CanonCustomBillingManualDAO.BillLocationInfo bean =
                                        (CanonCustomBillingManualDAO.BillLocationInfo) list.get(i);

                                String partyName = CanonCustomBillingCommon.nonNullify(bean.getCustomerName());
                                String location_ = billLocationSrch = CanonCustomBillingCommon.nonNullify(bean.getLocation());
                                String address= CanonCustomBillingCommon.getAddress(bean.getAddress1(), bean.getAddress2(),
                                        bean.getCity(), bean.getState(), bean.getPostalCode());
                                
                                System.out.println("in partyName: "+partyName);
                                System.out.println("in location_: "+location_);
                                System.out.println("in address: "+address);
                                
                    %>
                    <tr class='eventableDataCellModal'> 
                        <td height="30" style="word-break:break-all" align="center">
                            <a href="#" onclick="setBillLocationValue('<%=URLEncoder.encode(modalName,"UTF-8")%>',{index:<%=i%>,location:'<%=location_%>',customerName:'<%=partyName%>'})">
                                <%=location_%>
                            </a>
                        </td>

                        <td height="30" style="word-break:break-all" align="center">
                            <%=partyName%>
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