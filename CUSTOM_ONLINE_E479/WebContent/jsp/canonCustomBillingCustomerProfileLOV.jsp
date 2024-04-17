<%@page import="oracle.apps.custombilling.beans.CanonCustomBillingProfileAccountBean"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="oracle.apps.custombilling.beans.CanonCustomBillingAccountProfileDataBean"%>
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
		 
        $("#customerProfileLOV").submit(function (){
            //$(".btn1").trigger("click");
            $("#customerProfileLOV .btn1").focus();
            return false;
        });
        
        $("#customerProfileLOV a.linkPage").click(function(event){
            var pageNo=$(this).data("page");
            $("#customerProfileLOV #pageNo").val(pageNo);
            $("#customerProfileLOV .btn1").click();
            event.preventDefault();
        });
        
        $(".profileName").bt({
          contentSelector: "$(this).next()",
          positions: ['right', 'left'],
          trigger: 'click',
          fill: '#F4F4F4',
          strokeStyle: '#666666', 
          spikeLength: 20,
          spikeGirth: 10,
          width: 330,
          overlap: 0,
          centerPointY: 1,
          cornerRadius: 0, 
          shadow: true,
          shadowColor: 'rgba(0,0,0,.5)',
          shadowBlur: 8,
          shadowOffsetX: 4,
          shadowOffsetY: 4
        })

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
    if (request.getParameter("pageNo") != null) {
        pageNo = CanonCustomBillingCommon.toInt(request.getParameter("pageNo"));
    }
    if (request.getParameter("sortBy") != null) {
        sortBy = request.getParameter("sortBy");
    }
    if (request.getParameter("sortOrder") != null) {
        sortOrder = request.getParameter("sortOrder");
    }

    String customerProfileSrch = CanonCustomBillingCommon.nonNullify(
            request.getParameter("customerProfileSrch"));

%>


<form name="customerProfileLOV" id ="customerProfileLOV" 
      action="canonCustomBillingCustomerProfileLOV.jsp" method="POST">

    <input type ="hidden" id="modalName"
           name="modalName" value="<%=modalName%>">
    <input type ="hidden" id="pageNo" name="pageNo" value="<%=pageNo%>">
    <input type ="hidden" name="sortOrder" value="<%=CanonCustomBillingCommon.nonNullify(sortOrder)%>">
    <input type ="hidden" name="sortBy" value="<%=CanonCustomBillingCommon.nonNullify(sortBy)%>">

    <table width="650" align="center" bgColor="ffffff">
        <tr>
            <td nowrap class="sectionHeaderBlack" align="left" ><br>
                Search Customer Profile Name</td>
        </tr>

        <tr>
            <td>
                <table align="center" width="100%" border="0" cellspacing="0" 
                       cellpadding="2" summary="">
                    <tr>
                        <td class="bsd_text" align="left" >
                            <label>Customer Profile Name :
                                <input type="text" name="customerProfileSrch" id="customerProfileSrch" 
                                       value="<%=customerProfileSrch%>"  size="25"  tabindex="1" onkeyup='javascript:$(this).val($("#customerProfileSrch").val().toUpperCase());'>
                            </label>      
                            
                        </td> 	
                        <td align="right">
                            <input style="font-size: 11px;" type="button" tabindex="2" class="btn1"   onclick="javascript:searchCustomerProfileLOV('<%=modalName%>','customerProfileLOV')" value="Search >" /> 
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
            Object[] result = dao.getAccountProfileData(customerProfileSrch, p_start_range, p_end_range, sortBy, sortOrder);
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
                        <th height="30" style="text-align:center; width:10%; white-space:nowrap">
                            <a class="linkCanonTableCell"  href="#" onclick="javascript:searchCustomerProfileLOV('<%=modalName%>','customerProfileLOV',1,'PROFILE_NUM')">Profile Num</a>
                        </th>
                        <th height="30" style="text-align:center; ">
                            <a class="linkCanonTableCell" href="#" onclick="javascript:searchCustomerProfileLOV('<%=modalName%>','customerProfileLOV',1,'PROFILE_NAME')">Profile Name</a>
                        </th>             
                        <th height="30" style="text-align:center; width:30%; white-space:nowrap">
                            <a class="linkCanonTableCell" href="#" onclick="javascript:searchCustomerProfileLOV('<%=modalName%>','customerProfileLOV',1,'REPORTING_NAME')">Reporting Name</a>
                        </th>             
                    </tr>

                    <%
                        int i = 0;
                        int size = list.size();
                        if (size > 0) {
                            for (i = 0; i < size; i++) {
                                CanonCustomBillingAccountProfileDataBean bean =
                                        (CanonCustomBillingAccountProfileDataBean) list.get(i);
                                BigDecimal profileId = bean.getProfileId();
                                String profileNum = CanonCustomBillingCommon.nonNullify(bean.getProfileNum());
                                String profileName = CanonCustomBillingCommon.nonNullify(bean.getProfileName());
                                String reportingName = CanonCustomBillingCommon.nonNullify(bean.getReportingName());
                                Object accountprofile[]=CanonCustomBillingSearchingDAO.getProfileAccounts(profileId);
                                List accountlist=(List)accountprofile[0];
                                
                    %>
                    <tr class='eventableDataCellModal'> 
                        <td height="30" style="word-break:break-all" align="center">
                            <a href="#" onclick="setCustomerProfileValue('<%=modalName%>',{index:0,profileNum:'<%=profileNum%>',profileName:'<%=profileName%>'})">
                                <%=profileNum%>
                            </a>
                        </td>
                        <td height="30" style="text-align:center; ">
                            <a href="#" class="profileName" ><%=profileName%></a>
                            <div style="display:none" >
                                <table cellspacing="1" cellpadding="0" width="100%" align="center" >
                                    <tr style="font-size:10px; background-color:#cccc99;">
                                        <th height="30" >Party Name</th>
                                        <th height="30" >Party Number</th>
                                        <th height="30" >Site Use ID</th>
                                        <th height="30" >Location</th>
                                        <th height="30" >Address</th>
                                    </tr>
                                    <% 
                                    for(int j=0;j<accountlist.size();j++){
                                        CanonCustomBillingProfileAccountBean accountBean=(CanonCustomBillingProfileAccountBean) accountlist.get(j);
                                    %>
                                    <tr style="font-size:10px">
                                        <td height="30" ><%=CanonCustomBillingCommon.nonNullify(accountBean.getPartyName())%></td>
                                        <td height="30" ><%=CanonCustomBillingCommon.nonNullify(accountBean.getPartySiteNumber())%></td>
                                        <td height="30" ><%=accountBean.getSiteUseId()%></td>
                                        <td height="30" ><%=CanonCustomBillingCommon.nonNullify(accountBean.getLocation())%></td>
                                        <td height="30" ><%=CanonCustomBillingCommon.getAddress(accountBean.getAddress1(), accountBean.getAddress2(),accountBean.getCity(), accountBean.getState(), accountBean.getPostalCode())%></td>
                                    </tr>    
                                    <%
                                    }
                                    %>     
                                </table>        
                            </div>
                            
                        </td>
                        <td height="30" style="word-break:break-all" align="center">
                            <%=reportingName%>
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
