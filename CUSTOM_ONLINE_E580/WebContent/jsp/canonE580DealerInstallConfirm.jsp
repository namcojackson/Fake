<%@ page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchUtil"%>
<%@ page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchDAO"%>
<%@ page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchPOHelper"%>
<%@ page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchS21Api"%>
<%@ page language="java" import="java.math.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="oracle.apps.jtf.base.resources.*" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="canon.apps.pci.codecs.OracleCodec" %>
<%@ page language="java" import="canon.apps.pci.util.CanonPCISecurityUtil" %>
<%
    String p_itt_number = request.getParameter("itt_number");
    String userName = CanonE580ITTWorkbenchUtil.getUserName(pageContext, request, response);
    String dealer_install_confirm_save_flag = request.getParameter("dealer_install_confirm_save_flag");
    System.out.println("dealer_install_confirm_save_flag:"+dealer_install_confirm_save_flag);
    //CanonE580ApiTest canonE580ApiTest=new CanonE580ApiTest();
    //canonE580ApiTest.createServicerequest(request);
    boolean hasError=false;
    String status_message =null;
    if ("Y".equals(dealer_install_confirm_save_flag)) {
        String[] dealer_install_confirm_line_selectors = request.getParameterValues("dealer_install_confirm_line_selector");
        String[] so_numbers = request.getParameterValues("so_number");
        String[] line_numbers = request.getParameterValues("line_number");
       // String[] hold_pks=request.getParameterValues("hold_pk");
        String dealer_install_confirm_release_comment = request.getParameter("dealer_install_confirm_release_comment");
        System.out.println("dealer_install_confirm_release_comment is " + dealer_install_confirm_release_comment);      
        CanonE580ITTWorkbenchS21Api multiAPI=new CanonE580ITTWorkbenchPOHelper.DealerInstallConfirmAPI();
        String[] result=multiAPI.createServicerequest(request);
        if(!"Success".equals(result[0])){
        	hasError=true;
        	status_message=result[1];	
        }
        
//        for (int i = 0; line_numbers != null && i < line_numbers.length; i++) {
//            String dealer_install_confirm_line_selector = dealer_install_confirm_line_selectors[i];
//            if("Y".equals(dealer_install_confirm_line_selector)){
//                String so_number = so_numbers[i];
//                String line_number = line_numbers[i];                               
//                CanonE580ITTWorkbenchDAO.releaseHold(so_number, p_itt_number, line_number, dealer_install_confirm_release_comment, userName);                
//            }
//        }
    }
    if(!hasError){
    
    List list = null;
    CanonE580ITTWorkbenchDAO.MachConfInfo machConfInfo=null;
    try {
    	System.out.println("Prior to call ittLinesOnHold:");
        Object[] result = CanonE580ITTWorkbenchDAO.ittLinesOnHold(p_itt_number);
        list = (List) CanonE580ITTWorkbenchUtil.first(result);
        machConfInfo=(CanonE580ITTWorkbenchDAO.MachConfInfo)CanonE580ITTWorkbenchUtil.first(CanonE580ITTWorkbenchUtil.second(result));
    	System.out.println("machConfInfo:"+machConfInfo);
    } catch (Exception e) {
        e.printStackTrace();
    }

%>

<style>
    #dealer_install_confirm_table table {
        width:100%;
    }
    #dealer_install_confirm_table td {
        white-space: nowrap;
    }
    #dealer_install_confirm_table td.ellipsis {
        max-width:100px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        word-break: break-all;
        word-wrap: break-word;
    }

</style>
<div class="table-inner">
<form id="dealer_install_confirm_form" method="post" action="canonE580DealerInstallConfirm.jsp">
    <div style="text-align:center;">
        <input type="hidden" name="itt_number" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_itt_number)%>">
        <input type="hidden" id ="dealer_install_confirm_save_flag"  name="dealer_install_confirm_save_flag" value="N">
		<% if (machConfInfo!=null){ %>            
		<input type="hidden" name="svc_config_mstr_pk" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(machConfInfo.getSvcConfigMstrPk())%>">
		<input type="hidden" name="svc_mach_mstr_pk" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(machConfInfo.getSvcMachMstrPk())%>">
		<input type="hidden" name="stk_sts_cd" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(machConfInfo.getStkStsCd())%>">
		<input type="hidden" name="svc_mach_mstr_loc_sts_cd" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(machConfInfo.getSvcMachMstrLocStsCd())%>">
		<% } %>	

        <table id="dealer_install_confirm_table" border="0" width="100%" cellspacing="1" cellpadding="4" class="supplies-table" >

            <tr id="report_tbl_first">
                <th class="hd" style="width:40px">Select</th>
                <th class="hd">PO#</th>
                <th class="hd">Order Line#</th>
                <th class="hd">Item Code</th>
                <th class="hd">Description</th>
                <th class="hd">Hold Flag</th>
            </tr>


            <%
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        CanonE580ITTWorkbenchDAO.LinesOnHoldInfo line = (CanonE580ITTWorkbenchDAO.LinesOnHoldInfo) list.get(i);
            %>

            <tr class="<%=(i % 2 == 0) ? "oddtableDataCell" : "eventableDataCell"%>" >
                <td align="center">
                    <input type="hidden" name="so_number" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getSoNumber())%>">                    
                    <input type="hidden" name="hold_pk" id="hold_pk" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getHoldPk())%>">
                    <input type="hidden" name="hold_rsn_cd" id="hold_rsn_cd" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getHoldRsnCd())%>">
                    <input type="hidden" name="deliv_conf_flag" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getDelivConfFlag())%>">
                    <input type="hidden" name="so_sub_line_number" id="so_sub_line_number" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getSoSubLineNumber())%>">
                    <input type="hidden" name="dealer_install_confirm_line_selector">
                    <input type="checkbox" class="dealer_install_confirm_line_selector_helper" name="chk<%=i%>">
                </td>
                <td align="center">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getPoNumber())%>
                </td>
                <td align="center">
                    <input type="hidden" name="line_number" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getSoLineNumber())%>">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getDispLineNumber())%>
                </td>
                <td align="center">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getDispItem())%>
                </td>
                <td align="left">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getItemDescription())%> 
                </td>
                <td align="center">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getHoldFlag())%> 
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td class="search_res" style="color:#FF0000" colspan="12" align="center"> No records found. Please adjust your search criteria.&nbsp;</td>
            </tr>
            <%                }
            %>
        </table>
        <%
            if (list.size() > 0) {
        %>       
        <div class="select_all_div">
            Select: <a class="select_all" href="#" id="dealer_install_confirm_select_all_button">All</a> Deselect: <a class="select_all" href="#" id="dealer_install_confirm_deselect_all_button">All</a>
        </div>	
        
        <%
            }
        %>
        <div class="clear">
            Release Comments
            <p>
            <textarea style="width:50%" rows="8" name="dealer_install_confirm_release_comment" id="dealer_install_confirm_release_comment"></textarea>
        </div>

        <div style ="margin-top: 10px">
            <button id="dealer_install_confirm_confirm" class="btn save_button edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text" title="Click to confirm.">Confirm</span>
            </button>
            <button id="dealer_install_confirm_close" class="btn export-excel-button edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text" title="Close the current tab.">Cancel</span>
            </button>
        </div>	

    </div>

</form>
</div>
<%}else{%>
ERROR:<%=CanonE580ITTWorkbenchUtil.nonNullify(status_message)%>
<%}%>	