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
    String processor_name = request.getParameter("processor_name");
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
        CanonE580ITTWorkbenchDAO dao = new CanonE580ITTWorkbenchDAO();    
        Object objs= dao.ittOrderProcessorLov(processor_name);
        List results=(List)CanonE580ITTWorkbenchUtil.first(objs);
        int cnt = results.size();

    %>	


    <table id="ship_to_custoemr_tbl" border="0" width="100%" cellspacing="1" cellpadding="4" class="supplies-table" >
        <tr id="report_tbl_first"> 
            <th >Source Name</th>
            <th >Source Number</th>
            <th >Role Name </th>
        </tr>
    <%
        if (cnt == 0) {
    %>	
        <tr> 
            <td colspan="3" class="eventableDataCell" align="left">No record found.</td>
        </tr>
    <%  } else {

            String strSurceName = "";
            String strRoleName = "";
            String strSourceNumber = "";
            int i = 0;

            for (i = 0; i < cnt; i++) {
                CanonE580ITTWorkbenchDAO.ITTOrderProcessorInfo Obj = (CanonE580ITTWorkbenchDAO.ITTOrderProcessorInfo) results.get(i);

                strSurceName= Obj.getSourceName() == null ? "" : Obj.getSourceName();
                strRoleName = Obj.getRoleName() == null ? "" : Obj.getRoleName();
                strSourceNumber = Obj.getSourceNumber() == null ? "" : Obj.getSourceNumber();
                String display=CanonE580ITTWorkbenchUtil.ittOrderProcessor(Obj.getSourceNumber(), Obj.getSourceName());
                String className = "eventableDataCell";

                if (i % 2 == 0) {
                    className = "eventableDataCell";
                } else {
                    className = "oddtableDataCell";
                }
        %>
        <tr>
            <td align="left" nowrap>
                <a class ="itt_order_processor_lov_selection" data-lov='{"display":"<%=display%>","source_name":"<%=strSurceName%>","source_number":"<%=strSourceNumber%>","role_name":"<%=strRoleName%>"}' href="#"> <%=strSurceName%></a> 
            </td> 
            <td align="left"><%=strSourceNumber%></td>
            <td align="left"><%=strRoleName%></td>

        </tr>

        <%
            }
            }%>
        </table>

</form>		
