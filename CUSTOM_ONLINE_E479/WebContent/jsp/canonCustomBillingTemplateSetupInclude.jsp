<%@page import="oracle.apps.custombilling.util.CanonCustomBillingConstants"%>
<%@page import="java.util.Collections"%>
<%@page import="oracle.apps.custombilling.beans.CanonCustomBillingColumnDetailBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="oracle.apps.custombilling.beans.CanonCustomBillingViewDetailBean"%>
<%@page import="oracle.apps.custombilling.beans.CanonCustomBillingTemplateInfoBean"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="java.util.List"%>
<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="static canon.apps.common.CanonS21SessionValidate.commonRoot"%>

<%
    String errorMessage = null;
    CanonCustomBillingCommon C = new CanonCustomBillingCommon();
    int userId = C.toInt(request.getParameter("userId"));
    String p_template_id = (String) request.getParameter("templateId");
    BigDecimal p_header_id = C.toBigDecimal(p_template_id, true);
    BigDecimal selectedViewId = C.toBigDecimal(request.getParameter("viewId"), false);
    String selectedViewName = request.getParameter("viewName");
    boolean deleteView=false;
    boolean forward=false;

    if ("Yes".equals(request.getParameter("saveFlag"))) {
    	System.out.println("Template Setup include - Start");
    	String p_biller_name = request.getParameter("BillerName");
    	String p_biller_first_name =p_biller_name.substring(p_biller_name.indexOf(",")+2,p_biller_name.length());
    	String p_biller_last_name =p_biller_name.substring(0,p_biller_name.indexOf(","));
    	String p_biller_contact_no=request.getParameter("BillerPhone");
    	String p_biller_fax_no =request.getParameter("BillerFax");
    	String p_biller_email =request.getParameter("BillerEmail");
        String p_customer_email = request.getParameter("customerEmail");
        String p_other_email = request.getParameter("otherEmail");
        String p_invoice_break = C.trimToNull(request.getParameter("invoiceBreak"));
        String p_multi_tab = C.trimToNull(request.getParameter("multiTab"));
        String p_within_tab = "N";
        String p_text_format=C.trimToNull(request.getParameter("includeTxtFormat"));
        String p_suppress_zero=C.trimToNull(request.getParameter("suppressZeroInvoice"));
        System.out.println("Template Setup include - update ->p_template_id: "+p_template_id);

        CanonCustomBillingSearchingDAO.updateTemplateInfo(p_template_id, p_biller_first_name, p_biller_last_name, p_biller_contact_no, p_biller_fax_no,p_biller_email,p_customer_email, p_other_email, p_invoice_break, p_multi_tab, p_within_tab, Integer.toString(userId),p_text_format,p_suppress_zero);

        if (request.getParameterValues("VIEW_NAME") != null) {
            int size = request.getParameterValues("VIEW_NAME").length;
            String[] viewNames = request.getParameterValues("VIEW_NAME");
            String[] viewIds = request.getParameterValues("VIEW_ID");
            String[] viewAlias = request.getParameterValues("VIEW_ALIAS");
            String[] viewSortorder1 = request.getParameterValues("VIEW_SORT_ORDER_1");
            String[] viewSortorder2 = request.getParameterValues("VIEW_SORT_ORDER_2");
            String[] viewSortorder3 = request.getParameterValues("VIEW_SORT_ORDER_3");

            for (int i = 0; i < size; i++) {
                BigDecimal p_view_id = C.toBigDecimal(viewIds[i], false);
                if (p_view_id != null && p_view_id.intValue() == -1) {
                    CanonCustomBillingSearchingDAO.insertViewDetails(p_header_id, viewNames[i], viewAlias[i], new BigDecimal(i), viewSortorder1[i], viewSortorder2[i], viewSortorder3[i], Integer.toString(userId), Integer.toString(userId));
                } else {
                    CanonCustomBillingSearchingDAO.updateViewDetails(p_header_id, p_view_id, viewNames[i], viewAlias[i], new BigDecimal(i), viewSortorder1[i], viewSortorder2[i], viewSortorder3[i], Integer.toString(userId));
                }
            }
        }

        if (request.getParameterValues("COLUMN_NAME") != null) {
            int size = request.getParameterValues("COLUMN_NAME").length;
            String[] columnNames = request.getParameterValues("COLUMN_NAME");
            String[] columnIds = request.getParameterValues("COLUMN_ID");
            String[] columnAlias = request.getParameterValues("COLUMN_ALIAS");
            String[] columnTypes = request.getParameterValues("COLUMN_TYPE");
            String[] columnSubtotal = request.getParameterValues("SUBTOTAL");

            for (int i = 0; i < size; i++) {
                BigDecimal p_column_id = C.toBigDecimal(columnIds[i], false);
                if (p_column_id != null && p_column_id.intValue() == -1) {
                    Object result[] = CanonCustomBillingSearchingDAO.insertColumnDetails(p_header_id, selectedViewId, columnTypes[i], columnNames[i], columnAlias[i], Integer.toString(i), columnSubtotal[i], Integer.toString(userId), Integer.toString(userId));
                    BigDecimal status = (BigDecimal) C.first(result);
                    if (status.intValue() < 0) {
                        errorMessage = "failed to create Column.";
                    } else {
                        System.out.println("creaated new column with i " + status.toString());
                    }
                } else {
                    Object result[] = CanonCustomBillingSearchingDAO.updateColumnDetails(p_header_id, selectedViewId, p_column_id, columnTypes[i], columnNames[i], columnAlias[i], Integer.toString(i), columnSubtotal[i], Integer.toString(userId));
                    BigDecimal status = (BigDecimal) C.first(result);
                    if (status.intValue() < 0) {
                        errorMessage = "failed to update Column.";
                    }
                }
            }
        }

        List columnDetails = (List) C.first(CanonCustomBillingSearchingDAO.getColumnDetails(p_header_id, selectedViewId));

    } else if ("delete_view".equals(request.getParameter("saveFlag"))) {
        CanonCustomBillingSearchingDAO.deleteViewDetails(p_header_id, selectedViewId);
        deleteView=true;
    } else if ("delete_column".equals(request.getParameter("saveFlag"))) {
        BigDecimal p_column_id = C.toBigDecimal(request.getParameter("columnId"), false);
        CanonCustomBillingSearchingDAO.deleteViewColumns(p_header_id, selectedViewId, p_column_id);
    } else if ("complete".equals(request.getParameter("saveFlag"))) {
        String isRunning= (String)C.first(CanonCustomBillingSearchingDAO.isConcurrentProgramRunning());
        if("Y".equals(isRunning)){
            errorMessage =CanonCustomBillingConstants.CONCURRENT_PROGRAM_RUNNING_MSG;
        }else{
            Object result[] = CanonCustomBillingSearchingDAO.updateForCompIncomp(p_header_id, "Complete");
            BigDecimal status = (BigDecimal) C.second(result);
            System.out.println("status "+status);
            if (status.intValue() < 0) {
                errorMessage = (String)C.first(result);
                System.out.println("errorMessage "+errorMessage);
            }
        }
    } else if ("incomplete".equals(request.getParameter("saveFlag"))) {
        String isRunning = (String)C.first(CanonCustomBillingSearchingDAO.isConcurrentProgramRunning());
        if("Y".equals(isRunning)){
            errorMessage=CanonCustomBillingConstants.CONCURRENT_PROGRAM_RUNNING_MSG;
        }else{
            Object result[] = CanonCustomBillingSearchingDAO.updateForCompIncomp(p_header_id, "Incomplete");
            BigDecimal status = (BigDecimal) C.second(result);
            System.out.println("status "+status);
            if (status.intValue() < 0) {
                errorMessage = (String)C.first(result);
                System.out.println("errorMessage "+errorMessage);
            }
        }
    } else if ("delete".equals(request.getParameter("saveFlag"))) {
        CanonCustomBillingSearchingDAO.deleteTemplate(p_header_id);
        pageContext.forward("canonCustomBillingTS.jsp");
        forward=true;
    }
    if(!forward){
        List l = (List) C.first(CanonCustomBillingSearchingDAO.getTemplateInfo(p_template_id));
        CanonCustomBillingTemplateInfoBean templ = (CanonCustomBillingTemplateInfoBean) C.first(l);
        boolean completeFlag="COMPLETE".equalsIgnoreCase(templ.getStatusFlag());
        Object[] billerDetail = CanonCustomBillingSearchingDAO.getBillerDetail(p_header_id);
    
%>
<script type="text/javascript">
    
    $().ready(function() {
        $("#viewSetupTable").highlight_table({
            "select":function(row_){
                $("#viewId").val($(row_).find("input[name='VIEW_ID']").val());
                $("#viewName").val($(row_).find("input[name='VIEW_NAME']").val());
                refreshPage();
            },
            "deselect":function(row_){
                $("#viewId").val($(row_).find("input[name='VIEW_ID']").val());
                $("#viewName").val($(row_).find("input[name='VIEW_NAME']").val());
                //            $("#save_button").click();
                return true;
            }
        });

        resetSelectTitles();
        <% if (errorMessage!=null){ %>
        $(".validity-summary-container").show();
        <% } %>
            
            
        <% if(completeFlag) {%>
            $(":input").attr('disabled',true);
            $('.negative').attr('readonly','readonly');
            $(".negative").not("#complete").not("#incomplete").removeClass("negative");
            
        <% } %>
            
    });


</script>

<table width="100%" align="center"  border="0" cellspacing="0" cellpadding="0" summary="">
    <tr>
        <td style="width: 90px; height: 20px"><img src="<%=commonRoot(request)%>/images/spacer.gif"></td>
        <td class="bsd_text_bold" align="center" style="font-size:11px;">
            Template ID: <font color="blue"><%=templ.getHeaderId()%></font> &nbsp;
            Status: <font color="blue"><%=C.capital(templ.getStatusFlag())%></font> &nbsp;
            <%
                if(!C.isEmpty(templ.getGroupName())){
            %>
            Customer Group: <font color="blue"><%=C.nonNullify(templ.getGroupName())%></font> &nbsp;
            <%
                }
                if(!C.isEmpty(templ.getParentCustomerName())){
                %>
               Parent Customer: <font color="blue"><%=C.nonNullify(templ.getParentCustomerName())%></font> &nbsp;
            <%
                 }
                if(!C.isEmpty(templ.getCustomerName())){
            %>
                  Customer Name: <font color="Blue"><%=C.nonNullify(templ.getCustomerName())%></font> &nbsp;
            <%
                }
                if(!C.isEmpty(templ.getBillToLocation()) && !"-1".equals(templ.getBillToLocation())){
            %>
                  Bill To Location: <font color="Blue"><%="-1".equals(templ.getBillToLocation()) ? "": templ.getBillToLocation()%></font> </td>    
            <%
                }
            %>    
        </td>
        <td align="right" style="font-size:11px; width: 90px; height: 20px" >
            <a id='search_button' href='canonCustomBillingTS.jsp' class="negative">Search</a>
        </td>
    </tr>    
</table>
<table align="right"  border="0" cellspacing="0" cellpadding="0" summary="">
    <tr>
    </tr>
</table>
<hr/>

<form id="frmTemplateSetup" name="frmTemplateSetup" method="POST" action="canonCustomBillingTemplateSetupInclude.jsp">
    <input type="hidden" name="saveFlag" value="No" id="saveFlag"/>
    <input type="hidden" name="userId" value="<%=userId%>" />
    <input type="hidden" name="templateId" value="<%=p_template_id%>" />
    <input type="hidden" name="viewId" id="viewId" value="<%=(selectedViewId == null ? "-1" : selectedViewId.toString())%>"/>
    <input type="hidden" name="viewName" id="viewName" value="<%=C.nonNullify(selectedViewName)%>"/>
    <input type="hidden" name="columnId" id="columnId" />

    <table align="center" width="850" border="0" cellspacing="0" cellpadding="0" summary="">
        <tr>
            <td class="sectionHeader1"><h1>Create/Update Template </h1><br></td>
        </tr>
    </table>

    <table align="center" width="850" border="0" cellspacing="0"
           cellpadding="0">
        <tr>
            <td colspan=3>

                <div class="validity-summary-container">
                    <ul>
                        <li><%=C.nonNullify(errorMessage) %><li>
                    </ul>
                </div>
                
            </td>
        </tr>        
        <tr>
            <td colspan=3>
                <fieldset>
                    <legend>Template </legend>
                    <div style="float: left;">
                        <table>
                            <tr class="eventableDataCell">
                                <td><img src="<%=commonRoot(request)%>/requiredicon_status.gif" alt="">File Break</td>
                                <td>
                                    <select name="invoiceBreak" id="invoiceBreak">
                                        <%
                                            List valueset1 = (List) C.first(CanonCustomBillingSearchingDAO.getValuesetData("CANON_E479_TEMPL_INV_BREAK"));
                                            for (int i = 0; i < valueset1.size(); i++) {
                                                String display = C.nonNullify((String) valueset1.get(i));
                                                System.out.println(" valueset data "+i+" ->"+display);
                                                String value = C.naToEmpty(display);
                                                String selected = value.equals(C.nonNullify(templ.getInvoiceBreak())) ? "selected=\"selected\"" : "";
                                        %>
                                        <option value="<%=value%>" <%= selected%>  ><%= display%> </option>
                                        <% }%>
                                    </select>
                                </td>                                    
                                </td>
                                <td><img src="<%=commonRoot(request)%>/requiredicon_status.gif" alt="">Tab Break</td>
                                <td>
                                    <select name="multiTab"  id="multiTab">
                                        <%
                                            List valueset2 = (List) C.first(CanonCustomBillingSearchingDAO.getValuesetData("CANON_E479_TEMPL_MULTITAB_BRK"));
                                            for (int i = 0; i < valueset2.size(); i++) {
                                                String display = C.nonNullify((String) valueset2.get(i));
                                                System.out.println(" valueset data "+i+" ->"+display);
                                                String value = C.naToEmpty(display);
                                                String selected = value.equals(C.nonNullify(templ.getMultiTab())) ? "selected=\"selected\"" : "";
                                        %>
                                        <option value="<%=value%>" <%= selected%>  ><%= display%> </option>
                                        <% }%>
                                    </select>

                                </td>
                            </tr>
                            
                             <tr class="eventableDataCell">
                                <td><img src="<%=commonRoot(request)%>/requiredicon_status.gif" alt="">Biller Name</td>
                                <td >
                                    <input title="LastName, FirstName" size="40" id="BillerName" class="{validate:{required:true,email:true}}" type="text" name="BillerName" maxlength="200" value="<%=C.fullName((String)billerDetail[0],(String)billerDetail[1])%>"></input>
                                </td>
                                 <td><img src="<%=commonRoot(request)%>/requiredicon_status.gif" alt="">Biller Email</td>
                                <td >
                                    <input size="40" id="BillerEmail" type="text" name="BillerEmail" class="{validate:{required:true,email:true}}"  maxlength="200" value="<%=C.nonNullify((String)billerDetail[4])%>"></input>
                                </td>
                            </tr>
                             <tr class="eventableDataCell">
                                <td><img src="<%=commonRoot(request)%>/requiredicon_status.gif" alt="">Biller Phone #</td>
                                <td >
                                    <input size="40" id="BillerPhone" class="{validate:{required:true,email:true}}" type="text" name="BillerPhone" maxlength="200" value="<%=C.nonNullify((String)billerDetail[2])%>"></input>
                                </td>
                                <td>Biller Fax #</td>
                                <td >
                                    <input size="40" id="BillerFax" type="text" name="BillerFax" class="{validate:{email:true}}"  maxlength="200" value="<%=C.nonNullify((String)billerDetail[3])%>"></input>
                                </td>
                            </tr>                            
                            <tr class="eventableDataCell">
                                <td><img src="<%=commonRoot(request)%>/requiredicon_status.gif" alt="">Customer Email(s) </td>
                                <td >
                                    <input size="40" id="customerEmail" class="{validate:{required:true,email:true}}" type="text" name="customerEmail" maxlength="200" value="<%=C.nonNullify(templ.getCustomerEmail())%>"></input>
                                </td>
                                <td>Other Email(s) </td>
                                <td >
                                    <input size="40" id="otherEmail" type="text" name="otherEmail" class="{validate:{email:true}}"  maxlength="200" value="<%=C.nonNullify(templ.getOtherEmail())%>"></input>
                                </td>
                            </tr>
                            <tr class="eventableDataCell">
                                <td>Suppress Zero Dollar Invoices</td>
                                <td><input type="checkbox" name="suppressZeroInvoice" <%="Y".equals(templ.getSuppressZeroInvoice())?"checked":""%> value="Y" />
                                </td>
                                <td> Send Bill Text Format</td>
                                <td><input type="checkbox" name="includeTxtFormat" <%="Y".equals(templ.getIncludeTxtFormat())?"checked":""%> value="Y" />
                                </td>
                            </tr>
                            
                        </table>
                    </div>

                </fieldset>
                </p>

                <%
                    List views = (List) C.first(CanonCustomBillingSearchingDAO.getViewDetails(templ.getHeaderId()));
                %>
                <fieldset>
                    <legend>Views</legend>
                    <table width="100%" cellspacing="1" cellpadding="2" border="0" align="center" summary="" id="viewSetupTable" data-selected_row="">
                        <tr class="canontableCell">
                            <th width="10" height="30" style="text-align:left;font-size:11px;">
                                View Name
                            </th>
                            <th width="10" height="30" style="text-align:left;font-size:11px;">
                                View Alias
                            </th>     
                            <th width="100" height="30" style="text-align:left;font-size:11px;">
                                Sort Order 1
                            </th>     
                            <th width="100" height="30" style="text-align:left;font-size:11px;">
                                Sort Order 2
                            </th>     
                            <th width="100" height="30" style="text-align:left;font-size:11px;">
                                Sort Order 3
                            </th>     
                            <th width="100" height="30" style="text-align:left;font-size:11px;">
                                Element Sequence
                            </th>
                            <th width="10" height="30" style="text-align:left;font-size:11px;">
                                Remove
                            </th>
                        </tr>
                        <tbody>
                            <%
                                for (int i = 0; i < views.size(); i++) {
                                    CanonCustomBillingViewDetailBean vl = (CanonCustomBillingViewDetailBean) views.get(i);
//                                    if(viewId==vl.getViewId()){
//                                        viewName=l.getLine_view_name();
//                                    }


                            %>
                            <tr class="eventableDataCell <%=vl.getViewId().equals(selectedViewId) ? "table_row_highligh" : ""%>" data-view_id="<%=vl.getViewId()%>" >
                        <input type="hidden" name="VIEW_ID" value="<%=vl.getViewId()%>"/>
                        <input type="hidden" class="viewName" name="VIEW_NAME" value="<%=C.nonNullify(vl.getViewName())%>"/>
                        <td style="word-break:break-all" nowrap="nowrap">
                            <a class="column_seletor" href="#"> <%=C.nonNullify(vl.getViewName())%></a>
                        </td>
                        <td style="word-break:break-all">
                            <input type="text" class="viewAlias" name="VIEW_ALIAS" size="20" value="<%=C.nonNullify(vl.getViewAlias())%>"/>
                        </td>
                        <td  width="100"  >
                            <div class="select-container" style="width:150px">
                                <select name="VIEW_SORT_ORDER_1" style="width:150px" class="sortOrder1">
                                    <option></option>
                                    <%
                                        List sortorder1 = (List) C.first(CanonCustomBillingSearchingDAO.getSortingSeq1(vl.getViewName()));
                                        for (int j = 0; j < sortorder1.size(); j++) {
                                            String value = C.nonNullify((String) sortorder1.get(j));
                                            String selected = value.equals(vl.getSortPrefCol1()) ? "selected=\"selected\"" : "";
                                    %>
                                    <option value="<%=value%>" <%= selected%>  ><%= value%> </option>
                                    <% }%>
                                </select>
                            </div>
                        </td>
                        <td  width="100" >
                            <div class="select-container" style="width:150px">
                                <select name="VIEW_SORT_ORDER_2" style="width:150px"  class="sortOrder2">
                                    <option></option>
                                    <%
                                        List sortorder2 = (List) C.first(CanonCustomBillingSearchingDAO.getSortingSeq2(vl.getViewName(), vl.getSortPrefCol1()));
                                        for (int j = 0; j < sortorder2.size(); j++) {
                                            String value = C.nonNullify((String) sortorder2.get(j));
                                            String selected = value.equals(vl.getSortPrefCol2()) ? "selected=\"selected\"" : "";
                                    %>
                                    <option value="<%=value%>" <%= selected%>  ><%= value%> </option>
                                    <% }%>
                                </select>
                            </div>

                        </td>

                        <td  >
                            <div class="select-container" style="width:150px">
                                <select name="VIEW_SORT_ORDER_3"  style="width:150px"  class="sortOrder3">
                                    <option></option>
                                    <%
                                        List sortorder3 = (List) C.first(CanonCustomBillingSearchingDAO.getSortingSeq3(vl.getViewName(), vl.getSortPrefCol1(), vl.getSortPrefCol2()));
                                        for (int j = 0; j < sortorder3.size(); j++) {
                                            String value = C.nonNullify((String) sortorder3.get(j));
                                            String selected = value.equals(vl.getSortPrefCol3()) ? "selected=\"selected\"" : "";
                                    %>
                                    <option value="<%=value%>" <%= selected%>  ><%= value%> </option>
                                    <% }%>
                                </select>
                            </div>

                        </td>


                        <td class="buttons" > 
                            <div style="width:80px">
                                <a href="#" class="negative move_up" style="display:inline" >Up</a>
                                <a href="#" class="negative move_down" style="display:inline" >Down</a>
                            </div>
                        </td>
                        <td class="buttons" > <a data-view_id="<%=vl.getViewId()%>" style="width:30;" href="#" class="negative delete_view">X</a></td>
                        </tr>
                        <%  }%>
                        </tbody></table>                                                
                    <table style="float:right">
                        <tr>
                            <td class="buttons">
                                <a id='add_view' href='#' class="negative">Add New View></a>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <%
                    if (!deleteView && selectedViewId != null && selectedViewId.intValue() >= 0) {
                %>

                <fieldset>
                    <legend>Columns</legend>
                    <table width="100%" cellspacing="1" cellpadding="2" border="0" align="center" summary="" id="ColumnSetupTable">
                        <tr class="canontableCell">
                            <th width="100" height="30" style="text-align:left;font-size:11px;">
                                Column Type
                            </th>
                            <th width="400" height="30" style="text-align:left;font-size:11px;">
                                Column
                            </th>     
                            <th width="200" height="30" style="text-align:left;font-size:11px;">
                                Alias
                            </th>
                            <th width="100" height="30" style="text-align:left;font-size:11px;">
                                Subtotal
                            </th>
                            <th width="100" height="30" style="text-align:left;font-size:11px;">
                                Change Order
                            </th>
                            <th width="10" height="30" style="text-align:left;font-size:11px;">
                                Remove
                            </th>
                        </tr>
                        <tbody>
                            <%
                                List columnDetails = (List) C.first(CanonCustomBillingSearchingDAO.getColumnDetails(p_header_id, selectedViewId));
                                List eligibleSumCols = (List) C.first(CanonCustomBillingSearchingDAO.getEligibleSumCols(selectedViewName));
                                List eligibleNonNumericCols = (List) C.first(CanonCustomBillingSearchingDAO.getEligibleNonNumericCols(selectedViewName));
                                
                                for (int j = 0; j < columnDetails.size(); j++) {
                                    CanonCustomBillingColumnDetailBean includedColdetail = (CanonCustomBillingColumnDetailBean) columnDetails.get(j);
                                    String columnType = C.nonNullify(includedColdetail.getColumnType());
                                    String iColName = C.nonNullify(includedColdetail.getColumnName());
                                    String columnAlias = C.nonNullify(includedColdetail.getColumnAlias());
                                    String columnId = includedColdetail.getColumnId().toString();
                                    boolean eligibleSum = "Computed".equalsIgnoreCase(columnType) || ("Standard".equalsIgnoreCase(columnType) && eligibleSumCols.indexOf(iColName) >= 0);
                                    boolean eligibleNonNumeric="Concatenated".equalsIgnoreCase(columnType) || ("Standard".equalsIgnoreCase(columnType) && eligibleNonNumericCols.indexOf(iColName) >= 0);
                                    
                            %>
                            <tr class="eventableDataCell"> 
                        <input type="hidden" name="COLUMN_NAME" value="<%=iColName%>"/>
                        <input type="hidden" name="COLUMN_ID" value="<%=columnId%>" />
                        <input type="hidden" name="COLUMN_TYPE" value="<%=columnType%>" />

                        <td style="word-break:break-all" style="width:100px" ><%=columnType%></td>
                        <td style="word-break:break-all" style="width:400px" ><%=iColName%></td>
                        <td style="word-break:break-all" ><input type="text" name="COLUMN_ALIAS" class="columnAlias" size="30" maxlength="200" value="<%=columnAlias.toUpperCase()%>"/> </td>
                        <td style="word-break:break-all" >
                            
                            <% if (eligibleNonNumeric) {
                                    String aggregateBy = C.nonNullify((String) includedColdetail.getAggregateBy());
                                    String selectedSUBTOTAL = "SUBTOTAL".equalsIgnoreCase(aggregateBy) ? "selected=\"selected\"" : "";
                            %>
                            <select name="SUBTOTAL">
                                <option value=""></option>
                                <option value="SUBTOTAL" <%= selectedSUBTOTAL%> >SUBTOTAL</option>
                            </select>
                            
                            <%}else if (eligibleSum) {
                                    String aggregateBy = C.nonNullify((String) includedColdetail.getAggregateBy());
                                    String selectedSUM = "SUM".equalsIgnoreCase(aggregateBy) ? "selected=\"selected\"" : "";
                            %>
                            <select name="SUBTOTAL">
                                <option value=""></option>
                                <option value="SUM" <%= selectedSUM%> >SUM</option>
                            </select>
                            <% } else {%>
                            <input type="hidden" name="SUBTOTAL"/>
                            <% }%>
                        </td>
                        <td class="buttons"> 
                            <a href="#" class="negative move_up" style="display:inline" >Up</a>
                            <a href="#" class="negative move_down" style="display:inline" >Down</a>
                        </td>
                        <td class="buttons" > <a data-column_id="<%=columnId%>" style="width:30;" href="#" class="negative delete_column">X</a></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody></table>

                    <table style="float:right">
                        <tr>
                            <td class="buttons">
                                <a id='add_column' href='#' class="negative">Add New Column></a>
                            </td>
                        </tr>
                    </table>

                </fieldset>
                <%
                    }
                %>


                <br></td>
        </tr>
    </table>
    <table align="center" width="850" cellspacing="0" cellpadding="0">
        <tr>
            <td align="right">
                <table cellspacing="20" cellpadding="0">
                    <tr>
                        <%
                            if (!completeFlag) {
                        %>
                        <td class="buttons">
                            <a id="delete" href="#" class="negative" >
                                Delete Template >
                            </a>
                        </td>
                        <td class="buttons">
                            <a id="complete" href="#" class="negative" >
                                "Mark" Complete >
                            </a>
                        </td>
                        <td class="buttons">            
                            <a id="save_button" href="#" class="negative" >
                                Save >
                            </a>
                        </td>
                        <% 
                            } else {
                        %>
                        <td class="buttons">
                            <a id="incomplete" href="#" class="negative" >
                                "Mark" Incomplete >
                            </a>
                        </td>
                    <%      
                            }
                    %>
        </tr>
    </table>          	
</td>
</tr>
</table>
</form>
<%
    }
%>
