<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%><%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>

<%@page import="canon.apps.pci.util.CanonSecurityUtil"%>
<%@page import="oracle.apps.fnd.common.WebRequestUtil"%>

<%!
	public static Object first(Object obj) {
	    if (obj instanceof Object[]) {
	        Object[] objs = (Object[]) obj;
	        return objs.length < 1 ? null : objs[0];
	    } else if (obj instanceof List) {
	        List l = (List) obj;
	        return l.size() < 1 ? null : l.get(0);
	    }
	    return null;
	}
	
%>
<%
   String myRegionName = "";//"JTF_NOTES_CREATE";
   String myPermissionName = "";//"JTF_NOTES_CREATE";
   String initialPath = (String) first(CanonCustomBillingSearchingDAO.getInitialPath("ONLINE"));
   
%>
<%@ include file="jtfcalincps.jsp" %>
<%
	oracle.apps.fnd.common.WebAppsContext objWebAppsContext =
	    WebRequestUtil.validateContext(request, response);

	int user_Id = objWebAppsContext.getUserId();
	int resp_Id = objWebAppsContext.getRespId();
    String PAGE_NAME="canonInvoiceDownload.jsp";
	boolean isJspAccessValid=  CanonSecurityUtil.isJSPAccessValid(PAGE_NAME,user_Id,resp_Id);
	if(!isJspAccessValid){
	%>
	  
   <jsp:forward page="canonInvalidAccess.jsp"></jsp:forward>
	<%	
	}
%>

<%
    try {
        CanonCustomBillingCommon C=new CanonCustomBillingCommon();
        String path = request.getParameter("path");
        String invFileName = request.getParameter("invFileName");
        
        String separator =File.separator;
        int idx=path.lastIndexOf(separator);
        String name = path.substring(idx+1);
        
        if(invFileName!=null && invFileName.trim().length()>0){
        	name=invFileName;
        	name =   name.replaceAll(" +", "_");
        }
        
        String cntType="vnd.ms-excel";
        if(path!=null && path.indexOf("pdf")>0){
      	  cntType="pdf";
        }
        response.setContentType("application/"+cntType);
        response.setHeader("Content-disposition", "attachment; filename= " + name);
       
        InputStream in = null;
        in = new FileInputStream(initialPath+separator+path);
        for (int c = in.read(); c != -1; c = in.read()) {
            response.getWriter().write(c); 
        }
        in.close();
        
    } catch (Exception e) {
        out.println(e.getMessage());
        e.printStackTrace();
    }
%>
<%@ include file="jtfcalincpe.jsp" %>