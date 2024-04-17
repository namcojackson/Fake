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
    String salesrep_name = request.getParameter("salesrep_name");
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

        $(".salesrep_lov_selection").click(function() {
            var data = $(this).data("lov");
            var modelName = "#" + $("#modalName").val();
            $(modelName).trigger("selected", data);
        });

    });

</script>
<form name="nameList" method="post">
    <input type ="hidden" id="modalName" name="modalName" value="<%=CanonPCISecurityUtil.htmlEncode(CanonE580ITTWorkbenchUtil.nonNullify(modalName))%>">
    <%
        CanonE580ITTWorkbenchDAO dao = new CanonE580ITTWorkbenchDAO();
        Object objs= dao.ittSalesrepLov(salesrep_name);
        List results=(List)CanonE580ITTWorkbenchUtil.first(objs);
        int cnt = results.size();

    %>	


    <table id="ship_to_custoemr_tbl" border="0" width="100%" cellspacing="1" cellpadding="4" class="supplies-table" >
        <tr id="report_tbl_first"> 
            <th>Salesrep Name</th>
        </tr>
    <%
        if (cnt == 0) {
    %>	
        <tr> 
            <td class="eventableDataCell" align="left">No record found.</td>
        </tr>
    <%  } else {

            for (int i = 0; i < cnt; i++) {
                String salesrep = (String) results.get(i);

                String className = "eventableDataCell";

                if (i % 2 == 0) {
                    className = "eventableDataCell";
                } else {
                    className = "oddtableDataCell";
                }
        %>
        <tr>
            <td align="left" nowrap>
                <a class ="salesrep_lov_selection" data-lov='{"salesrep":"<%=salesrep%>"}' href="#"> <%=salesrep%></a>
            </td> 

        </tr>

        <%
            }
            }%>
    </table> 

</form>		
