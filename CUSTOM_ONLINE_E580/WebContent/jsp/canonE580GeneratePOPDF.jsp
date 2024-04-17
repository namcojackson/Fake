<%@ page trimDirectiveWhitespaces="true" %>
<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchPOHelper"%>
<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchUtil"%>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContext" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextFactory" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.config.S21ConfigurationException" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.helpers.S21AuthenticationHelper" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.authentication.token.S21UserPasswordAuthenticationToken" %>
<%@ page import="com.canon.cusa.s21.framework.security.S21AuthorizationAction"%>
<%@page import="javax.servlet.http.*" %>
<%@page import="java.io.File" %>
<%!
    final String PAGE_NAME = "canonE580GeneratePOPDF.jsp";
%>
<%
	S21SecurityContext context = S21SecurityContextHolder.getContext();   
    try {
        String ittNumber= request.getParameter("itt_number");
        String userName= CanonE580ITTWorkbenchUtil.getUserName(pageContext, request, response);
        String lineNums=null;
        String [] selectedLineNums=request.getParameterValues("partial_po_pdf_generation_line_number");
        for(int i=0;selectedLineNums!=null && i<selectedLineNums.length;i++){
        	if(lineNums==null){
        		lineNums=selectedLineNums[i];
        	}else{
        		lineNums=lineNums+","+selectedLineNums[i];
        	}
        }
        
        String type= request.getParameter("type");
      	type= type==null? "pdf" :type;
       
      	String cntType= "xls".equals(type)? "vnd.ms-excel":"pdf";
        response.setContentType("application/"+cntType);
        response.setHeader("Content-disposition", "attachment; filename= " +ittNumber+"."+type );
        System.out.println("Priro to call createPOPDFOutputStream:");
        ServletContext servletContext = getServletContext();
		String contextPath = "/WebSphere/apps/filebox/attachment";//servletContext.getRealPath(File.separator);
        System.out.println("contextPath:"+contextPath);        
        CanonE580ITTWorkbenchPOHelper.POPDFOutputStream popdfos= CanonE580ITTWorkbenchPOHelper.createPOPDFOutputStream(ittNumber, lineNums, userName,contextPath);
        if("xls".equals(type)){
            popdfos.save(response.getOutputStream());
        }else if ("pdf".equals(type)){
            popdfos.savePDF(response.getOutputStream());           
        }
    } catch (Exception e) {
        out.println(e.getMessage());
        e.printStackTrace();
    }    
%>