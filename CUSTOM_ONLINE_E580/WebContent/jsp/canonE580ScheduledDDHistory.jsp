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
    String sql(String str){
        return CanonPCISecurityUtil.sqlEncode(CanonE580ITTWorkbenchUtil.nonNullify(str));
    }
%>
<%
    String itt_number = request.getParameter("itt_number");
    String line_number = request.getParameter("line_number");
%>

<style>
    #history_tbl td {
        word-wrap: break-word;
    }

</style>


<script language="Javascript">

</script>
<form name="nameList" method="post">
    <%
        CanonE580ITTWorkbenchDAO dao = new CanonE580ITTWorkbenchDAO();
        Object objs= dao.ittDelivDtReschdlHistory(sql(itt_number),sql(line_number));
        List results=(List)CanonE580ITTWorkbenchUtil.first(objs);
        int cnt = results.size();
        
    %>    

    <table id="history_tbl" border="0" cellspacing="1" width="100%" cellpadding="4" >
        <tr id="report_tbl_first"> 
            <td class="hd">Version</td>
            <td class="hd">Last Update By </td>
            <td class="hd">Schedule Date</td>
            <td class="hd">Reason Code</td>
            <td class="hd">Last Update Date</td>
        </tr>
    <%
        if (cnt == 0) {
    %>	
        <tr> 
            <td colspan="6" class="eventableDataCell" align="left">No record found.</td>
        </tr>
    <%  } else {

            for (int i = 0; i < cnt; i++) {
                CanonE580ITTWorkbenchDAO.ScheduledDTHistoryInfo d = (CanonE580ITTWorkbenchDAO.ScheduledDTHistoryInfo) results.get(i);
                String className = "eventableDataCell";

                if (i % 2 == 0) {
                    className = "eventableDataCell";
                } else {
                    className = "oddtableDataCell";
                }
    %>
        <tr class="<%=className%>">
            <td NOWRAP align="center"><%=d.getVersionNumber()==null?"":d.getVersionNumber().toString()%></td>
            <td NOWRAP align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(d.getLastUpdateBy())%></td>
            <td NOWRAP align="center"><%=CanonE580ITTWorkbenchUtil.formatDate2(d.getScheduledDeliveryDate())%></td>
            <td NOWRAP align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(d.getReasonCode())%></td>
            <td NOWRAP align="center"><%=CanonE580ITTWorkbenchUtil.formatDate4(d.getLastUpdateDate())%></td>
        </tr>

    <%
           }
      }
    %>    
    </table>

</form>		
