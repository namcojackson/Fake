<%--
 /*===========================================================================+
 |      Copyright (c) 1999 Oracle Corporation, Redwood Shores, CA, USA       |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  FILENAME                                                                 |
 |            CanonE008GetSelectedComponentDetails.jsp                     |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |   													  					 |
 |    																		 |
 |                                                                           |
 |  NOTES                                                                    |
 |         Using template                                                    |
 |  DEPENDENCIES                                                             |
 |                           	    											 |
 |                                                                           |
 |  HISTORY                                                                  |
 |                                                                           |
 |   12-22-2015      Madhusudan Duna --  Ver:1                              |
 +===========================================================================*/
--%>
<%@ page language="java" import="oracle.apps.jtf.calendar.util.CalendarUtil"%>
<%@ page import="oracle.apps.jtf.base.Logger"%>
<%@ page import="oracle.apps.jtf.util.GeneralPreference"%>
<%@ page import="oracle.apps.jtf.base.resources.AOLMessageManager"%>
<%@ page import="java.text.*"%>
<%@ page language="java" import="java.math.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="oracle.apps.jtf.base.resources.*"%>
<%@ page import="oracle.apps.fnd.common.*"%>
<%@ page import="java.net.URLEncoder"%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessUtil"%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessDAO"%>
<%@page import="oracle.apps.e008.item.process.CanonE008TemplateDAO"%>
<%@page	import="oracle.apps.e008.item.process.CanonE008ItemProcessHelper"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@ page import="canon.apps.common.CanonS21SessionValidate"%>
<%@include file="CanonE008ItemProcessCommon.jsp"%>


<%!static String[] parameterValues(HttpServletRequest request, String name) {
		String param_prefix = CanonE008ItemProcessUtil.nonNullify(request
				.getParameter("param_prefix"));
		return request.getParameterValues(param_prefix + name);
	}

	static class ItemProcessException extends Exception {
		public ItemProcessException(String message, Throwable cause) {
			super(message, cause);
		}
	}

	static void checkResult(Object[] result) throws ItemProcessException {
		if (result == null) {
			Exception cause = CanonE008TemplateDAO.getException();
			throw new ItemProcessException("Database exception.", cause);
		}
	}

	static void checkErrors(Object[] result, int error_flag_index,
			int error_message_index) throws ItemProcessException {
		checkResult(result);
		String error_flag = (String) result[error_flag_index];
		String error_message = (String) result[error_message_index];
		if ("E".equals(error_flag)) {
			throw new ItemProcessException("Database error.", new Exception(
					error_message));
		}
	}

	 static String nonNullify(Object o){
	        if(o==null){
	            return "";
	        }else if(o instanceof String){
	            return ((String)o).trim();
	        }else {
	            return o.toString();
	        }
	    }
	    
	static String  getelementValue(HttpServletRequest request)
		{
			String var1 =   request.getParameter("template_id1");
			System.out.println("var1 is " + var1);
			return var1  ;
		}	 
%>

<%

System.out.println("Inside CanonE008GetSelectedComponentDetails.jsp ajax");          
String errFlag="E";
String errMsg="";	
String strAction = "";
String strComponentName = "";
String strTemplateStatus = "";
String strTemplateDesc = "";
String strTemplateType = "";
String strTemplateCopy = "";
String strUserid ="";
Object result[] = null;
BigDecimal template_id= null;

int cnttemplate=0;

Object result1[] = null;
Object resultAttribute[] = null;
Object resultAttributeAssg[] = null;
Object resulttemplate[] = null;
String loginUserID = s21Authentication.getIdentityDetails().getUID();//(BigDecimal)CanonE590ItemProcessUtil.second(result);

String strReturnmessg = "";

StringBuffer odata = new StringBuffer(100);

String msg ="";
String[] msg1;

if (request.getParameter("component") != null)
		// strComponentName=java.net.URLDecoder.decode(request.getParameter("component"));
		strComponentName=request.getParameter("component").trim();
	 	

System.out.println("Start CanonE008GetSelectedComponentDetails ComponentName:"+strComponentName);

System.out.println("Start CanonE008GetSelectedComponentDetails:");
CanonE008ItemProcessDAO obj = new CanonE008ItemProcessDAO();
Object[] itemMainAttrValuesObjects=obj.getComponentDetails(strComponentName);

System.out.println("itemMainAttrValuesObjects[0]:::::"+itemMainAttrValuesObjects[0]);
errFlag=(String)itemMainAttrValuesObjects[1];
errMsg=(String)itemMainAttrValuesObjects[2];
	strReturnmessg =itemMainAttrValuesObjects[0]+"@@"+ errFlag+"@@"+errMsg+"@@";

	 System.out.println("strReturnmessg::::"+strReturnmessg);	 
	 odata.append(strReturnmessg); 
	 response.getWriter().write(odata.toString()); 



%>