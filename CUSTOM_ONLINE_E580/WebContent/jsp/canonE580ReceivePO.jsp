<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchUtil"%>
<%@ page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchDAO"%>
<%@ page language="java" import="java.math.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="oracle.apps.jtf.base.resources.*" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="canon.apps.pci.codecs.OracleCodec" %>
<%@ page language="java" import="canon.apps.pci.util.CanonPCISecurityUtil" %>
<%@page import="oracle.apps.e580.itt.workbench.CanonE580ReceivePOApi"%>
<%!
   // HashMap subLovCache=new HashMap();
    private List getSubInvLov(String invOrgCode){
     //   List l=(List)subLovCache.get(invOrgCode);
        /* if(l!=null){
            return l;
        } */
        List ittSubInvLovList=null;
        ittSubInvLovList= (List)CanonE580ITTWorkbenchUtil.first(CanonE580ITTWorkbenchDAO.ittSubInvLov(invOrgCode));
        ittSubInvLovList =ittSubInvLovList==null?Collections.EMPTY_LIST:ittSubInvLovList;
       // subLovCache.put(invOrgCode, l);
        return ittSubInvLovList;
    }
%>

<%
    String p_itt_number = request.getParameter("itt_number");
	String [] names=CanonE580ITTWorkbenchUtil.getUserNameAndFullNameS21(request, response);
	String userName=names[1]; 
    String receive_po_save_flag = request.getParameter("receive_po_save_flag");
    String status_message =null;
    Object result[]={"Y","Eligible"};//CanonE580ITTWorkbenchDAO.receivePoCheck(p_itt_number);
    boolean isRunning=!"Y".equals((String)CanonE580ITTWorkbenchUtil.first(result));
    String checkMessage=(String)CanonE580ITTWorkbenchUtil.second(result);
    String[] receive_po_serial_numbers = request.getParameterValues("receive_po-serial_number");
    String[] receive_po_line_selectors = request.getParameterValues("receive_po_line_selector");
    boolean isSave="Y".equals(receive_po_save_flag);
    boolean hasError=false;
    System.out.println("IsRunning:"+isRunning+" hasError:"+hasError+" isSave:"+isSave);
    if (isSave) {
    System.out.println("receive_po_line_selectors:"+receive_po_line_selectors+" receive_po_serial_numbers:"+receive_po_serial_numbers);
        result = CanonE580ITTWorkbenchUtil.checkSerialNumber(p_itt_number,receive_po_line_selectors,receive_po_serial_numbers);
        hasError= ((Boolean)result[0]).booleanValue();
        status_message=hasError? result[1].toString() : null;
    }
    System.out.println("IsRunning:"+isRunning+" hasError after process:"+hasError+" isSave:"+isSave);
    if (!hasError && isSave) {
        if(isRunning){
            hasError=true;
            status_message=checkMessage;
        }else{
				System.out.println("Inside else...");
            String[] line_numbers = request.getParameterValues("line_number");
            String[] receive_po_qtys = request.getParameterValues("receive_po-qty");
            String[] receive_po_lot_numbers = request.getParameterValues("receive_po-lot_number");
            String[] item_names = request.getParameterValues("item_name");
            String[] item_descs = request.getParameterValues("item_desc");
            String[] inv_orgs = request.getParameterValues("inv_org");
            String[] po_numbers = request.getParameterValues("po_number");
            String[] po_line_nums=request.getParameterValues("po_line_num");
            String[] receive_po_license_numbers= request.getParameterValues("receive_po-license_number");
            String[] eta_dts=request.getParameterValues("eta_dt");
            String[] stk_sts_Cds=request.getParameterValues("stk_sts_Cd");
            String[] rws_ref_nums=request.getParameterValues("rws_ref_num");
            
            

            boolean selected=false;
            Set uniqePOs=new HashSet();
            
            for (int i = 0; line_numbers != null && i < line_numbers.length; i++) {
                String receive_po_line_selector = receive_po_line_selectors[i];
                if("Y".equals(receive_po_line_selector)){
                    selected=true;
                    String line_number = line_numbers[i];
                    BigDecimal receive_po_qty = CanonE580ITTWorkbenchUtil.toBigDecimal(receive_po_qtys[i],false);
                    String receive_po_serial_number = receive_po_serial_numbers[i];
                    String receive_po_lot_number = receive_po_lot_numbers[i];
                    String item_name=item_names[i];
                    String po_number=po_numbers[i];
                    uniqePOs.add(po_number);
                    String item_desc=item_descs[i];
                    String inv_org=inv_orgs[i];
                    String po_line_num=po_line_nums[i];
                    String receive_po_license_number=receive_po_license_numbers[i];	
                    String etaDt=eta_dts[i];
                    String stkStscd=stk_sts_Cds[i];	
                    String rwsrefnum=rws_ref_nums[i];
					System.out.println("line_number prior to set all attributes::"+po_line_num+","+po_number+","+receive_po_qty+","+item_name+","+etaDt+","+stkStscd+","+receive_po_serial_number+" ,"+rwsrefnum);
					request.setAttribute("rcvline_number", po_line_num);
					request.setAttribute("rcvpo_number", po_number);
					request.setAttribute("rcvitem_qty", receive_po_qty.toString());
					request.setAttribute("rcvitem_code", item_name);
					request.setAttribute("rcveta_dt", etaDt);
					request.setAttribute("rcvstk_sts_cd", stkStscd);
					request.setAttribute("so_line_number", line_number);
					request.setAttribute("rcvserial_number", receive_po_serial_number);
					request.setAttribute("rws_ref_num", rwsrefnum);
					
					
					System.out.println("Prior to call receiVePO API...");
		            CanonE580ReceivePOApi canonE580ReceivePOApiTest=new CanonE580ReceivePOApi();
					String[] resArr= canonE580ReceivePOApiTest.createServicerequest(request);
					
					System.out.println("receiVePO API call completed:::....");
					String status_code =resArr[0];//"Success";
                        status_message =resArr[1];//"Receive PO successfull";//;
                        
                     CanonE580ITTWorkbenchDAO.insertReceiptPrc(p_itt_number,
                                                userName,
                                                line_number,
                                                po_number,
                                                po_line_num,
                                                item_name,
                                                item_desc,
                                                receive_po_qty,
                                                inv_org,
                                                null,
                                                receive_po_lot_number,
                                                receive_po_serial_number,
                                                receive_po_license_number); 
                   System.out.println("insertReceiptPrc completed:::");
                }
            }
           /*  if(selected){
                    for(Iterator i=uniqePOs.iterator();i.hasNext();){
                        String po_number=(String)i.next();
                        System.out.println("Prior to call canonCreateReceiptPrc:");
                        result = CanonE580ITTWorkbenchDAO.canonCreateReceiptPrc(p_itt_number,userName, new BigDecimal(po_number));
                        String status_code =(String)CanonE580ITTWorkbenchUtil.first(result);
                        status_message =(String)CanonE580ITTWorkbenchUtil.second(result);
                        hasError="ERROR".equals(status_code);
                    }
            } */
        }
    }
    if(!hasError){
    List list = null;
        try {
            result = CanonE580ITTWorkbenchDAO.eligiblePoLines(p_itt_number, userName);
            list = (List) CanonE580ITTWorkbenchUtil.first(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
%>

<style>
    #receive_po_table table {
        width:100%;
    }
    #receive_po_table td.ellipsis {
        max-width:100px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        word-break: break-all;
        word-wrap: break-word;
    }
    .check_message{
        color:blue;
        font-weight: bold;
    }
</style>

<div class="table-inner">
    <form id="receive_po_form" method="post" action="canonE580ReceivePO.jsp">
        <input type="hidden" name="itt_number" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_itt_number)%>">
        <input type="hidden" id ="receive_po_save_flag"  name="receive_po_save_flag" value="N">
        <input type="hidden" id ="status_message"  value="<%=CanonE580ITTWorkbenchUtil.nonNullify(status_message)%>">
        
        <div style ="margin-bottom:10px">
            <%if(isRunning){%>
                <span class="check_message"> <%=CanonE580ITTWorkbenchUtil.nonNullify(checkMessage)%></span>
                <a href="#" id="receive_po_refresh" >Click here to refresh the lines display.</a>
            <%}%>
        </div>	
        
        <table id="receive_po_table" border="0" width="100%" cellspacing="1" cellpadding="4" class="supplies-table" style="margin-left:-10px;">

            <col width="20px" />
            <col width="40px" />
            <col width="40px" />
            <col width="50px" />
            <col width="300px" />
            <col width="20px" />
            <col width="20px" />
            <col width="100px" />
            <col width="100px" />
            <col width="20px" />
            
            <tr id="report_tbl_first">
                <th class="hd">Select</th>
                <th class="hd">PO#</th>
                <th class="hd">Order Line#</th>
                <th class="hd">Item Code</th>
                <th class="hd">Description</th>
                <th class="hd">QTY</th>
                <th class="hd">Receiving Org</th>
                <th class="hd">Serial</th>
                <th class="hd">License</th>
                <th class="hd">LOT</th>
            </tr>


            <%
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        CanonE580ITTWorkbenchDAO.ReceivePOInfo line = (CanonE580ITTWorkbenchDAO.ReceivePOInfo) list.get(i);
                        boolean serial_number_flag = "Y".equals(line.getSerialNumberFlag());
                        boolean license_number_flag = "Y".equals(line.getLicenseNumberFlag());
                        boolean license_number_required_flag ="Y".equals(line.getLicenseNumberRequiredFlag());
                        boolean lot_flag = "Y".equals(line.getLotControlFlag());
            %>

            <tr >
                <td align="center">
                    <input type="hidden" name="receive_po_line_selector">
                    <input type="checkbox" class="receive_po_line_selector_helper" name="receive_po_check_box">
                    <input type="hidden" name="itt_line_num" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getSoLineNumber())%>">
                    <input type="hidden" name="item_name" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getOrderedItem())%>">
                    <input type="hidden" name="item_desc" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getDescription())%>">
                    <input type="hidden" name="inv_org" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getOrganizationCode())%>">
                    <input type="hidden" name="po_number" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getPoNumber())%>">
                    <input type="hidden" name="po_line_num" value="<%=line.getPoLineNumber()%>">
                    <input type="hidden" name="eta_dt" value="<%=line.getEtaDt()%>">
                    <input type="hidden" name="stk_sts_Cd" value="<%=line.getStkStsCd()%>">
                    <input type="hidden" name="rws_ref_num" value="<%=line.getRwsRefNum()%>">
                    
                </td>
                <td align="center">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getPoNumber())%>
                </td>
                <td align="center">
                    <input type="hidden" name="line_number" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getSoLineNumber())%>">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getDispLineNumber())%>
                </td>
                <td align="center">
                    <%=CanonPCISecurityUtil.htmlEncode(CanonE580ITTWorkbenchUtil.nonNullify(line.getDispItem()))%> 
                </td>
                <td align="left">
                    <%=CanonPCISecurityUtil.htmlEncode(CanonE580ITTWorkbenchUtil.nonNullify(line.getDescription()))%> 
                </td>
                <td align="center">
                    <% if (serial_number_flag) {%>
                    <input type="hidden" name="receive_po-qty" value="<%=line.getQtyAvbl()%>"><%=line.getQtyAvbl()%>
                    <%} else {%>
                    <input style="text-align:center;" name="receive_po-qty" class="receive_po-qty required_field" size="2" type="text" value="<%=line.getQtyAvbl()%>">
                    <% }%>
                </td>
                <td align="center">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getOrganizationCode())%> 
                </td>
                <td align="center">
                    <% if (serial_number_flag) {%>
                    <input name="receive_po-serial_number"  class="receive_po-serial_number required_field"  style="width:100%"  size="20" type="text" >
                    <%} else {%>
                    <input type="hidden" name="receive_po-serial_number" value="">
                    <% }%>
                </td>
                <td align="center">
                    <% if (license_number_flag) {%>
                    <input name="receive_po-license_number"  class="receive_po-license_number<%=license_number_required_flag? " required_field":""%>"  style="width:100%"  size="20" type="text" >
                    <%} else {%>
                    <input type="hidden" name="receive_po-license_number" value="">
                    <% }%>
                </td>
                <td align="center">
                    <% if (lot_flag) {%>
                    <input name="receive_po-lot_number"  class="receive_po-lot_number required_field" size="2" type="text" style="width:100%" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getLotNumber())%>">
                    <%} else {%>
                    <input type="hidden" name="receive_po-lot_number" value="">
                    <% }%>
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
            Select: <a class="select_all" href="#" id="receive_po_select_all_button">All</a> Deselect: <a class="select_all" href="#" id="receive_po_deselect_all_button">All</a>
        </div>	
        <%
            }
        %>
        
        <%if(!CanonE580ITTWorkbenchUtil.isEmpty(status_message)){%>
            <div id="status-message-dialog" title="PO Receipt">
                <span class="status-message"> <%=CanonE580ITTWorkbenchUtil.nonNullify(status_message)%> </span>
            </div>
        <%}%>
        <div style ="margin-top: 10px">
            <button id="receive_po_confirm" class="btn save_button edit_mode <%=isRunning ? "ui-state-disabled": ""%>" type="button"  style="margin-left: 40%" <%=isRunning ? "disabled": ""%> >
                <span class="ui-button-text" title="Click to confirm received PO.">Confirm</span>
            </button>
            <button id="receive_po_close" class="btn export-excel-button edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text" title="Close the Receive PO tab.">Cancel</span>
            </button>
        </div>	

    </form>		
</div>
<%}else{%>
ERROR:<%=CanonE580ITTWorkbenchUtil.nonNullify(status_message)%>
<%}%>