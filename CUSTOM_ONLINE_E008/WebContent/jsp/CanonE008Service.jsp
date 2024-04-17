<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessUtil"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessDAO"%>
<%@page import="canon.apps.pci.util.CanonPCISecurityUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="application/json"%>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", -1);
  
    String action = request.getParameter("action");
    List errors=null;
    Object checkStatusMonitor=null;
    String ccidDesc=null;
    if("validate_project".equalsIgnoreCase(action) || "save_and_validate_project".equals(action)){
        errors= (List)pageContext.getAttribute("validate_project_errors",PageContext.REQUEST_SCOPE);
        System.out.println("errors save_and_validate_project - "+errors);
    }else if("validate_required_fields".equalsIgnoreCase(action)){
        errors= (List)pageContext.getAttribute("validate_required_fields",PageContext.REQUEST_SCOPE);
        System.out.println("errors "+errors);
    }else if("check_status_monitor".equalsIgnoreCase(action)){
        checkStatusMonitor=pageContext.getAttribute("check_status_monitor",PageContext.REQUEST_SCOPE);
        System.out.println("checkStatusMonitor "+checkStatusMonitor);
    }else if("get_ccid_desc".equalsIgnoreCase(action)){
        ccidDesc=(String)pageContext.getAttribute("get_ccid_desc",PageContext.REQUEST_SCOPE);
        System.out.println("ccidDesc "+ccidDesc);
    }
 
    if(errors!=null){
%>
[  
<%
    for(int i=0;errors!=null && i<errors.size();i++){
            CanonE008ItemProcessDAO.ProjectValidateInfo error=(CanonE008ItemProcessDAO.ProjectValidateInfo)errors.get(i);
%><%=i==0? "":","%>{"message":"<%=CanonPCISecurityUtil.htmlEncode(error.getMessage())%>","item_id":"<%=error.getItemId()%>","field_name":"<%=error.getFieldName()%>"}<%
    }
%>    
]
<%}else if(checkStatusMonitor!=null){
%>
    { "check_status_monitor":"<%=checkStatusMonitor%>" }
<%
 }else if(ccidDesc!=null){
%>
    { "ccid_desc":"<%=CanonPCISecurityUtil.htmlEncode(ccidDesc)%>"}
<%
 }
%>
    
