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
    String html(String str){
        return CanonPCISecurityUtil.htmlEncode(CanonE580ITTWorkbenchUtil.nonNullify(str));
    }
%>
<%
    String modalName = request.getParameter("modalName");
%>

<style>
    #dealer_tbl td {
        word-wrap: break-word;
    }

</style>


<script language="Javascript">

    $(function() {

        $(".reason_code_lov_selection").click(function() {
            var data = $(this).data("lov");
            $.reason_code_container.trigger("selected", data);
        });

    });

</script>
<form name="nameList" method="post">
    <input type ="hidden" id="modalName" name="modalName" value="<%=CanonPCISecurityUtil.htmlEncode(CanonE580ITTWorkbenchUtil.nonNullify(modalName))%>">
    <%
        CanonE580ITTWorkbenchDAO dao = new CanonE580ITTWorkbenchDAO();
        Object objs= dao.ittSchdlDelivDtReasonCode();
        List results=(List)CanonE580ITTWorkbenchUtil.first(objs);
        int cnt = results.size();
        
    %>    

    <table id="reason_code_tbl" border="0" cellspacing="1" width="100%" cellpadding="4" class="supplies-table">
        <tr id="report_tbl_first"> 
            <th>Reason Code</th>
            <th>Description</th>
        </tr>
    <%
        if (cnt == 0) {
    %>	
        <tr> 
            <td colspan="6" class="eventableDataCell" align="left">No record found.</td>
        </tr>
    <%  } else {

            for (int i = 0; i < cnt; i++) {
                CanonE580ITTWorkbenchDAO.ReasonCodeInfo d = (CanonE580ITTWorkbenchDAO.ReasonCodeInfo) results.get(i);
                String className = "eventableDataCell";

                if (i % 2 == 0) {
                    className = "eventableDataCell";
                } else {
                    className = "oddtableDataCell";
                }
    %>
        <tr>
            <td align="left">
                <a class ="reason_code_lov_selection"
                   data-lov='{"reason_code":"<%=html(d.getFlexValueMeaning()) %>"}' href="#"> <%=html(d.getFlexValueMeaning())%>
                </a>
            </td>
            <td align="left"><%=html(d.getDescription())%></td>
        </tr>

    <%
           }
      }
    %>    
    </table>

</form>		
