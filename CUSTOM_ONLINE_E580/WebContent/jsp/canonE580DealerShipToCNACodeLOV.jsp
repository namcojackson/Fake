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
    String modalName = request.getParameter("modalName");
    String dealer_ship_to_cna_code= request.getParameter("dealer_ship_to_cna_code");
    
%>

<style>
    #dealer_tbl td {
        word-wrap: break-word;
    }

</style>


<script language="Javascript">

    $(function() {

        $(".dealer_ship_to_cna_code_lov_selection").click(function(event) {
        	event.preventDefault();
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
        Object objs= dao.ittDealerShipToCnaCode(sql(dealer_ship_to_cna_code));
        List results=(List)CanonE580ITTWorkbenchUtil.first(objs);
        int cnt = results.size();
        
    %>    

    <table id="dealer_ship_to_cna_code_tbl" border="0" cellspacing="1" width="100%" cellpadding="4" class="supplies-table">
        <tr id="report_tbl_first"> 
            <th>Ship To Code (C.N.A)</th>
            <th>Address</th>
        </tr>
    <%
        if (cnt == 0) {
    %>	
        <tr> 
            <td colspan="6" class="eventableDataCell" align="left">No record found.</td>
        </tr>
    <%  } else {

            for (int i = 0; i < cnt; i++) {
                CanonE580ITTWorkbenchDAO.ShipToCNACodeInfo d = (CanonE580ITTWorkbenchDAO.ShipToCNACodeInfo) results.get(i);
                String className = "eventableDataCell";

                if (i % 2 == 0) {
                    className = "eventableDataCell";
                } else {
                    className = "oddtableDataCell";
                }
    %>
        <tr>
            <td align="left">
                <a class ="dealer_ship_to_cna_code_lov_selection"
                   data-lov='{"dealer_ship_to_cna_code":"<%=html(d.getShipToCnaCode())%>"}' href="#"> <%=html(d.getShipToCnaCode())%>
                </a>
            </td>
            <td align="left"><%=html(CanonE580ITTWorkbenchUtil.address(d))%></td>
        </tr>

    <%
           }
      }
    %>    
    </table>

</form>		
