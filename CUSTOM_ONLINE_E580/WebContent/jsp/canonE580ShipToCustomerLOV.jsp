<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchUtil"%>
<%@ page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchDAO"%>
<%@ page language="java" import="java.math.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="oracle.apps.jtf.base.resources.*" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="canon.apps.pci.codecs.OracleCodec" %>
<%@ page language="java" import="canon.apps.pci.util.CanonPCISecurityUtil" %>

<%
    String modalName = request.getParameter("modalName");
    String cust_name= request.getParameter("cust_name");
    String cust_number= request.getParameter("cust_number");
%>

<style>
    #ship_to_custoemr_tbl table {
        width:100%;
    }
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

        $(".ship_to_customer_lov_selection").click(function() {
            var data = $(this).data("lov");
            var modelName = "#" + $("#modalName").val();
            $(modelName).trigger("selected", data);
        });

    });

</script>
<form name="nameList" method="post">
    <input type ="hidden" id="modalName" name="modalName" value="<%=CanonPCISecurityUtil.htmlEncode(CanonE580ITTWorkbenchUtil.nonNullify(modalName))%>">
    <input type ="hidden" id="cust_name" name="cust_name" value="<%=CanonPCISecurityUtil.htmlEncode(CanonE580ITTWorkbenchUtil.nonNullify(cust_name))%>">
    <input type ="hidden" id="cust_number" name="cust_number" value="<%=CanonPCISecurityUtil.htmlEncode(CanonE580ITTWorkbenchUtil.nonNullify(cust_number))%>">
    <%
        CanonE580ITTWorkbenchDAO dao = new CanonE580ITTWorkbenchDAO();
        Object objs= dao.ittCustAccountLov(cust_name,cust_number);
        List results=(List)CanonE580ITTWorkbenchUtil.first(objs);
        int cnt = results.size();

    %>	


    <table id="ship_to_custoemr_tbl" border="0" width="100%" cellspacing="1" cellpadding="4" class="supplies-table">
        <tr id="report_tbl_first"> 
            <th>Ship To Customer Name</th>
            <th>Ship To Account #</th>
        </tr>
    <%
        if (cnt == 0) {
    %>	
        <tr> 
            <td colspan="2" class="eventableDataCell" align="left">No record found.</td>
        </tr>
    <%  } else {


            String strName = "";
            String strId = "";
            int i = 0;

            for (i = 0; i < cnt; i++) {
                CanonE580ITTWorkbenchDAO.CustomerAccountInfo Obj = (CanonE580ITTWorkbenchDAO.CustomerAccountInfo) results.get(i);

                strName= Obj.getShipToCustomer() == null ? "" : Obj.getShipToCustomer();
                strId = Obj.getAccountNumber() == null ? "" : Obj.getAccountNumber();

                String className = "eventableDataCell";

                if (i % 2 == 0) {
                    className = "eventableDataCell";
                } else {
                    className = "oddtableDataCell";
                }
        %>
        <tr>
            <td align="left" nowrap>
                <a class ="ship_to_customer_lov_selection" data-lov='{"customer_name":"<%=strName%>","account_number":"<%=strId%>"}' href="#"> <%=strName%></a> 
            </td> 
            <td align="left"><%=strId%></td>

        </tr>

        <%
            }
            }%>
    </table>

</form>		
