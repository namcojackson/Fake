<%@page import="canon.apps.pci.util.CanonPCISecurityUtil"%>
<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchDAO"%>
<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="oracle.apps.e580.itt.workbench.CanonE580CreatePOS21Api"%>
<%@page contentType="application/json"%>

<%! 
    String html(String str){
        return CanonPCISecurityUtil.htmlEncode(CanonE580ITTWorkbenchUtil.nonNullify(str));
    }
    String sql(String str){
        return CanonPCISecurityUtil.sqlEncode(CanonE580ITTWorkbenchUtil.nonNullify(str));
    }
    
%>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", -1);

    String action=request.getParameter("action");
    if("validate_ship_to_cna_code".equals(action)){
        String code=request.getParameter("v");
        boolean validCNACode=CanonE580ITTWorkbenchUtil.validateShipToCNACode(sql(code));
%>
{"pass":<%=validCNACode%>}
<%
    }else{
    	String p_status_code=null;
    	String p_status_message=null;
        String save_flag=request.getParameter("save_flag");
        String p_itt_number = request.getParameter("itt_number");
        String [] names=CanonE580ITTWorkbenchUtil.getUserNameAndFullNameS21(request, response);
        String p_user_name=names[1];        
        if("CREATE_PO".equalsIgnoreCase(save_flag)){
    		if("A".equals(request.getParameter("create_po_called_from")) && CanonE580ITTWorkbenchUtil.isEmpty(request.getParameter("add_to_multiple_po"))){
    			Object[] objs = CanonE580ITTWorkbenchDAO.getAppendPoNumbers(p_itt_number,false);
    			try {
    				if (objs!=null && objs.length>0 && objs[0] != null) {
    					((java.sql.ResultSet) objs[0]).close();
    				}
    				if (objs!=null && objs.length>1 && objs[1] != null) {
    					((java.sql.ResultSet) objs[1]).close();
    				}
    			} catch (java.sql.SQLException e) {
    				e.printStackTrace();
    			}
                if(objs!=null && objs.length>3 && "S".equals(objs[3])){
                	p_status_code= objs!=null && objs.length>2 && "Y".equals(objs[2]) ? "MULTIPLE" :"SINGLE";
                }else{
                	p_status_code="ERROR";
                	p_status_message=(String)objs[4];
                }
    		}else{
	        	CanonE580CreatePOS21Api canonE580CreatePOS21Api=new CanonE580CreatePOS21Api();        	
	        	 
	            Object[] ret=canonE580CreatePOS21Api.createServicerequest(request);
	            //CanonE580ITTWorkbenchDAO.createPoPrc(p_itt_number,p_user_name);
	            System.out.println("createPoPrc returns "+ret);
	            p_status_code=(String)ret[0];//(String) CanonE580ITTWorkbenchUtil.first(ret);
	            p_status_message=(String)ret[2];//(String) CanonE580ITTWorkbenchUtil.second(ret);
	            System.out.println("p_status_code "+p_status_code);
	            System.out.println("p_status_message "+p_status_message);
    		}
            
%>
{"status_code":"<%=CanonE580ITTWorkbenchUtil.nonNullify(p_status_code)%>",
"status_message":"<%=CanonE580ITTWorkbenchUtil.escape(CanonE580ITTWorkbenchUtil.nonNullify(p_status_message))%>"
}
<%
        }
    } 
%>