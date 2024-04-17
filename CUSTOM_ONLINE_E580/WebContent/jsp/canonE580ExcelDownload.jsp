<%@page import="java.util.Collections"%>
<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchUtil"%>
<%@page import="java.util.List"%>
<%
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    Object o = pageContext.getAttribute("export-excel-summary",PageContext.REQUEST_SCOPE);
    if (o!=null) {
        response.setHeader("Content-disposition", "attachment; filename=canonITTSearch.xlsx");
        CanonE580ITTWorkbenchUtil.createSummaryExcelOutputStream((List) o).save(response.getOutputStream());
    }else{
        o = pageContext.getAttribute("export-excel-detail",PageContext.REQUEST_SCOPE);
        if (o!=null) {
            String ittNumber= request.getParameter("itt_number");
            response.setHeader("Content-disposition", "attachment; filename=canonITTLineDetail-"+CanonE580ITTWorkbenchUtil.nonNullify(ittNumber)+".xlsx");
            CanonE580ITTWorkbenchUtil.LineDetailInfo d=(CanonE580ITTWorkbenchUtil.LineDetailInfo)o;
            CanonE580ITTWorkbenchUtil.createDetailExcelOutputStream(d).save(response.getOutputStream());
        }
    }
%>