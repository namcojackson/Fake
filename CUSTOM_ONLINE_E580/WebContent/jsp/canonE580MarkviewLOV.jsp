<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchUtil"%>
<%@ page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchDAO"%>
<%@ page language="java" import="java.math.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.net.URLEncoder" %>
<%@ page import="oracle.apps.jtf.base.resources.*" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="canon.apps.pci.codecs.OracleCodec" %>
<%@ page language="java" import="canon.apps.pci.util.CanonPCISecurityUtil" %>
<%
    String modalName = request.getParameter("modalName");
    String ittNumber = request.getParameter("itt_number");
    String userName=CanonE580ITTWorkbenchUtil.getUserName(pageContext, request, response);
%>

<style>
    #markview_attached_doc_tbl table {
        width:100%;
    }
    #markview_attached_doc_tbl td {
        white-space: nowrap;
    }
    #markview_attached_doc_tbl td.ellipsis {
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

        $(".markview_lov_selection").click(function() {
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
        Object objs= dao.ittMarkviewAttachedDocs(ittNumber,userName);
        List results=(List)CanonE580ITTWorkbenchUtil.first(objs);
        int cnt = results.size();

    %>	


    <table id="markview_attached_doc_tbl" border="0" width="100%" cellspacing="1" cellpadding="4" class="supplies-table">
        <tr id="report_tbl_first"> 
            <th>Attachment Date</th>
            <th>Description</th>
            <th>Document ID</th>
        </tr>
    <%
        if (cnt == 0) {
    %>	
        <tr> 
            <td colspan="3" class="eventableDataCell" align="left">No record found.</td>
        </tr>
    <%  } else {


            int i = 0;

            for (i = 0; i < cnt; i++) {
                CanonE580ITTWorkbenchDAO.MarkviewAttatchedDocInfo Obj = (CanonE580ITTWorkbenchDAO.MarkviewAttatchedDocInfo) results.get(i);

                String attachmentDate = CanonE580ITTWorkbenchUtil.formatDate2(Obj.getAttachmentDate());
                String description= CanonE580ITTWorkbenchUtil.nonNullify(Obj.getDescription());
                String documentID = CanonE580ITTWorkbenchUtil.nonNullify(Obj.getDocumentId());
                String url= CanonE580ITTWorkbenchUtil.nonNullify(Obj.getMarkviewDocLink());
                String className = "eventableDataCell";

                if (i % 2 == 0) {
                    className = "eventableDataCell";
                } else {
                    className = "oddtableDataCell";
                }
        %>
        <tr>
            <td align="center"><%=attachmentDate%></td>
            <td align="left"><%=description%></td>
            <td align="center" nowrap>
                <a class ="markview_lov_selection"
                   data-lov='{"attachmentDate":"<%=attachmentDate%>",
                   "description":"<%=CanonPCISecurityUtil.htmlEncode(description)%>",
                   "documentID":"<%=CanonPCISecurityUtil.htmlEncode(documentID)%>"}' 
                   href="<%=url%>"> <%=CanonPCISecurityUtil.htmlEncode(documentID)%></a>
            </td>
        </tr>

        <%
            }
            }%>
    </table>

</form>		
