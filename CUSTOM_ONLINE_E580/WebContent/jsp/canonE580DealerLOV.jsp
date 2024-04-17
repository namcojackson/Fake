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

    String html(BigDecimal d){
        return d==null?"": d.toString();
    }
    
%>
<%
    String modalName = request.getParameter("modalName");
    String dealer_name= request.getParameter("dealer_name");
    
%>

<style>
    #dealer_tbl td {
        word-wrap: break-word;
    }
	 .ui-dialog .ui-dialog-title{
	 	float: none;
	 }
</style>




<script language="Javascript">

    $(function() {

        $(".dealer_lov_selection").click(function() {
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
        Object objs= dao.ittDealerLov(dealer_name);
        List results=(List)CanonE580ITTWorkbenchUtil.first(objs);
        int cnt = results.size();
        
    %>    

    <table id="dealer_tbl" border="0" cellspacing="1" width="100%" cellpadding="4" class="supplies-table">
        <tr id="report_tbl_first"> 
            <th>Dealer Name</th>
            <th>Dealer Code</th>
            <th>Supplier Code</th>
            <th>Contact Name</th>
            <th>Phone And Email</th>
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
                CanonE580ITTWorkbenchDAO.DealerInfo d = (CanonE580ITTWorkbenchDAO.DealerInfo) results.get(i);
                String className = "eventableDataCell";

                if (i % 2 == 0) {
                    className = "eventableDataCell";
                } else {
                    className = "oddtableDataCell";
                }
    %>
        <tr>
            <td align="left">
                <a class ="dealer_lov_selection" 
                   data-lov='{"dealer_code_display":"<%=html(CanonE580ITTWorkbenchUtil.dealerCode(d.getDealerCode(),d.getDealerName()))%>","dealer_name":"<%=html(d.getDealerName())%>","dealer_code_id":"<%=html(d.getDealerCode())%>","dealer_supplier_code":"<%=html(d.getSupplierCode())%>","dealer_contact_name":"<%=html(CanonE580ITTWorkbenchUtil.fullName(d.getLastName(),d.getFirstName()))%>","dealer_phone":"<%=html(CanonE580ITTWorkbenchUtil.phoneNumber(d.getAreaCode(),d.getPhone()))%>","dealer_email":"<%=html(d.getEmailAddress())%>","addressLine1":"<%=html(d.getAddressLine1())%>","addressLine2":"<%=html(d.getAddressLine2())%>","addressLine3":"<%=html(d.getAddressLine3())%>","city":"<%=html(d.getCity())%>","state":"<%=html(d.getState())%>","zip":"<%=html(d.getZip())%>","country":"<%=html(d.getCountry())%>","vendor_id":"<%=html(d.getVendorId())%>","vendor_site_id":"<%=html(d.getVendorSiteId())%>"}' href="#"> <%=html(d.getDealerName())%></a>
            </td>
            <td align="center"><%=html(d.getDealerCode())%></td>
            <td align="center"><%=html(d.getSupplierCode())%></td>
            <td align="left"><%=html(CanonE580ITTWorkbenchUtil.fullName(d.getLastName(),d.getFirstName()))%></td>
            <td align="left"><%=html(CanonE580ITTWorkbenchUtil.concat(
                    CanonE580ITTWorkbenchUtil.phoneNumber(d.getAreaCode(),d.getPhone()),
                    d.getEmailAddress()," "))%></td>
            <td align="left"><%=html(CanonE580ITTWorkbenchUtil.address(d))%></td>
        </tr>

    <%
           }
      }
    %>    
    </table>

</form>		
