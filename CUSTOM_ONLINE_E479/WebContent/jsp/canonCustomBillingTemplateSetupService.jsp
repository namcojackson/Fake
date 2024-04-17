<%@page import="oracle.apps.custombilling.util.CanonCustomBillingConstants"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="application/json"%>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", -1);

    String viewName=request.getParameter("viewName");
    String sortOrder1=request.getParameter("sortOrder1");
    String sortOrder2=request.getParameter("sortOrder2");
    String action=request.getParameter("action");
    List values=null;
    if("getSortOrder1".equals(action)){
        values=(List)CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getSortingSeq1(viewName));
    }else if("getSortOrder2".equals(action)){
        values=(List)CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getSortingSeq2(viewName, sortOrder1));
    }else if("getSortOrder3".equals(action)){
        values=(List)CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getSortingSeq3(viewName, sortOrder1, sortOrder2));
    }else if("getValuesetData".equals(action)){
        String valueSetName=request.getParameter("valueSetName");
        values=(List)CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getValuesetData(valueSetName));
    }else if("getViewNames".equals(action)){
        values=(List)CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getValuesetData(CanonCustomBillingConstants.CANON_E479_TEMPL_VIEW_LIST));
    }else if("getColumnList".equals(action)){
        String columnType=request.getParameter("columnType");
        if("Standard".equals(columnType)){
            values=(List)CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getInitialColumnList(viewName));
        }else if("Computed".equals(columnType)){
            values=(List)CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getValuesetData(CanonCustomBillingConstants.CANON_E479_TEMPL_COMP_COLS));
        }else if("Concatenated".equals(columnType)){
            values=(List)CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getValuesetData(CanonCustomBillingConstants.CANON_E479_TEMPL_CONCAT_COLS));
        }
    }else if("getEligibleSumCols".equals(action)){
        values=(List)CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getEligibleSumCols(viewName));
    }
    else if("getEligibleNonNumericCols".equals(action)){
        values=(List)CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getEligibleNonNumericCols(viewName));
    }
    
%>
[
<%    for(int i=0; values!=null && i<values.size();i++){
        String value=(String)values.get(i);
        if(i>0){%>, <%} %> {"optionValue":"<%=value%>", "optionDisplay": "<%=value%>"}<%}
%>
]