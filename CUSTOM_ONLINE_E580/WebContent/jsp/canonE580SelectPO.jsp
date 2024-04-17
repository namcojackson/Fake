<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchUtil"%>
<%@ page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchDAO"%>
<%@ page language="java" import="java.math.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="oracle.apps.jtf.base.resources.*" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="canon.apps.pci.codecs.OracleCodec" %>
<%@ page language="java" import="canon.apps.pci.util.CanonPCISecurityUtil" %>

<%!

private static List cursorToList(ResultSet cursor, CanonE580ITTWorkbenchDAO.CanonE580RowMapper rowMapper) {
    List list = new ArrayList();
    try {
        while (cursor.next()) {
            list.add(rowMapper.mapRow(cursor, 0));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            cursor.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return list;
}

%>
<%
    String modalName = request.getParameter("modalName");
    String processor_name = request.getParameter("processor_name");
    String itt_number= request.getParameter("itt_number");
    int cnt=0; 
    List results1=null;
    List results2=null;
%>

<style>
    #ship_to_custoemr_tbl td {
        white-space: nowrap;
    }
    #ship_to_custoemr_tbl td.ellipsis {
        max-width:100px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        word-break: break-all;
        word-wrap: break-word;
    }

</style>


<script language="Javascript">

    $(function() {

        $(".itt_order_processor_lov_selection").click(function() {
            var data = $(this).data("lov");
            var modelName = "#" + $("#modalName").val();
            $(modelName).trigger("selected", data);
        });

    });

</script>
<form name="nameList" method="post">
    <input type ="hidden" id="modalName" name="modalName" value="<%=CanonPCISecurityUtil.htmlEncode(CanonE580ITTWorkbenchUtil.nonNullify(modalName))%>">
    <%
        
		Object[] objs = CanonE580ITTWorkbenchDAO.getAppendPoNumbers(itt_number,true);
        if(objs!=null && objs.length>3 && "S".equals(objs[3]) && "Y".equals(objs[2])){
            results1=(List)CanonE580ITTWorkbenchUtil.first(objs);
            results2=(List)CanonE580ITTWorkbenchUtil.second(objs);
            cnt = results1.size()+results2.size();
        }

    %>	
	<br>
	<div id="select_po_error_message" style="color:blue;font-weight: bold"></div>
	<br>
    <table id="cusa_select_po_tbl" border="0" width="100%" cellspacing="1" cellpadding="4" class="supplies-table" >
        <tr id="report_tbl_first"> 
            <th ></th>
            <th ></th>
            <th >PO Number</th>
            <th >PO Date</th>
            <th >PO Status </th>
            <th >Supplier</th>
        </tr>
    <%
        if (cnt == 0) {
    %>	
        <tr> 
            <td colspan="3" class="eventableDataCell" align="left">No record found.</td>
        </tr>
        </table>
    <%  } else {

            String strPONumber = "";
            String strPODate = "";
            String strPoStatus = "";
            int i = 0;

            for (i = 0; i < results1.size(); i++) {
                CanonE580ITTWorkbenchDAO.AppendPOInfo Obj = (CanonE580ITTWorkbenchDAO.AppendPOInfo) results1.get(i);

                strPONumber= CanonE580ITTWorkbenchUtil.nonNullify(Obj.getPoNumber());
                strPODate = CanonE580ITTWorkbenchUtil.formatDate2(Obj.getPoDate());
                strPoStatus = CanonE580ITTWorkbenchUtil.nonNullify(Obj.getPoSts());
                String strSupplier=CanonE580ITTWorkbenchUtil.nonNullify(Obj.getSupplier());
                String className = "eventableDataCell";

                if (i % 2 == 0) {
                    className = "eventableDataCell";
                } else {
                    className = "oddtableDataCell";
                }
        %>
        <tr>
        	<% if(i==0){ %>
            <td align="left" rowspan="<%=results1.size()%>" class="eventableDataCell" >CUSA PO</td>
            <%}%> 
            <td class="eventableDataCell" align="left" ><input type="radio" class="cusa_po"  name="cuser_po" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(strPONumber)%>"></td> 
            <td class="eventableDataCell" align="left" nowrap><%=strPONumber%></td> 
            <td class="eventableDataCell" align="left"><%=strPODate%></td>
            <td class="eventableDataCell" align="left"><%=strPoStatus%></td>
            <td class="eventableDataCell" align="left"><%=strSupplier%></td>
        </tr>

        	<%}%>
            
            
				<%	        
	            strPONumber = "";
	            strPODate = "";
	            strPoStatus = "";
	            i = 0;
	
	            for (i = 0; i < results2.size(); i++) {
	                CanonE580ITTWorkbenchDAO.AppendPOInfo Obj = (CanonE580ITTWorkbenchDAO.AppendPOInfo) results2.get(i);
	
	                strPONumber= CanonE580ITTWorkbenchUtil.nonNullify(Obj.getPoNumber());
	                strPODate = CanonE580ITTWorkbenchUtil.formatDate2(Obj.getPoDate());
	                strPoStatus = CanonE580ITTWorkbenchUtil.nonNullify(Obj.getPoSts());
	                String strSupplier=CanonE580ITTWorkbenchUtil.nonNullify(Obj.getSupplier());
	                String className = "eventableDataCell";
	
	                if (i % 2 == 0) {
	                    className = "eventableDataCell";
	                } else {
	                    className = "oddtableDataCell";
	                }
	        %>
	        
	        <tr>
        	<% if(i==0){ %>
	            <td align="left" rowspan="<%= results2.size()%>" class="oddtableDataCell" >Dealer PO</td> 
            <%}%> 
	            <td class="oddtableDataCell" align="left" ><input type="radio" class="dealer_po" name="dealer_po" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(strPONumber)%>"></td> 
	            <td class="oddtableDataCell" align="left" nowrap><%=strPONumber%></td> 
	            <td class="oddtableDataCell" align="left"><%=strPODate%></td>
	            <td class="oddtableDataCell" align="left"><%=strPoStatus%></td>
	            <td class="oddtableDataCell" align="left"><%=strSupplier%></td>
	        </tr>
	
	        	<%}%>
            
            </table>
            
        <%}%>

</form>		
