<%--
 /*===========================================================================+
 |      Copyright (c) 1999 Oracle Corporation, Redwood Shores, CA, USA       |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  FILENAME                                                                 |
 |            CanonE008TemplateProcess.jsp                                     |
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
<%-- <%@ page language="java" import="oracle.apps.jtf.calendar.util.CalendarUtil"%>
<%@ page import="oracle.apps.jtf.base.Logger"%>
<%@ page import="oracle.apps.jtf.util.GeneralPreference"%>
<%@ page import="oracle.apps.jtf.base.resources.AOLMessageManager"%> --%>
<%@ page import="java.text.*"%>
<%@ page language="java" import="java.math.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%-- <%@ page import="oracle.apps.jtf.base.resources.*"%>
<%@ page import="oracle.apps.fnd.common.*"%>
<%@ page import="java.net.URLEncoder"%> --%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessUtil"%>
<%@page import="oracle.apps.e008.item.process.CanonE008TemplateDAO"%>
<%@page	import="oracle.apps.e008.item.process.CanonE008ItemProcessHelper"%>
<%-- <%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%> --%>
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

System.out.println("Inside CanonE008TemplateProcess.jsp ajax");          
String errFlag="E";
String errMsg="";	
String strAction = "";
String strTemplateName = "";
String strTemplateStatus = "";
String strTemplateDesc = "";
String strTemplateType = "";
String strTemplateCopy = "";
String strUserid ="";
Object result[] = null;
BigDecimal template_id= null;

String strcatType="";
String stritemAssigned="";
String strattribute1="";
String strattribute1value="";
String strattribute2="";
String strattribute2value="";
String strattribute3="";
String strattribute3value="";
String strattribute4="";
String strattribute4value="";
String strattribute5="";
String strattribute5value="";
String strsavedname="";
String strattributename="";
String strselectvalue ="";
String strsuppliercode ="";
String strproductlevel3 ="";

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

if(request.getParameter("action") != null) 
	strAction=request.getParameter("action");

if (request.getParameter("templateName") != null) 
	strTemplateName=request.getParameter("templateName");

if(request.getParameter("templateStatus") != null)  
	strTemplateStatus=request.getParameter("templateStatus");

if(request.getParameter("templateType") != null)  
	strTemplateType=request.getParameter("templateType");

if(request.getParameter("templateCopy") != null)  
	strTemplateCopy=request.getParameter("templateCopy");

if(request.getParameter("templateDesc") != null)  
	strTemplateDesc=request.getParameter("templateDesc");

if(request.getParameter("userId") != null)
	strUserid=request.getParameter("userId");

if(request.getParameter("cntTemplate") != null)
	cnttemplate= Integer.parseInt(request.getParameter("cntTemplate"));

if(request.getParameter("catType") != null)  
	strcatType=request.getParameter("catType");

if(request.getParameter("itemAssigned") != null)  
	stritemAssigned=request.getParameter("itemAssigned");

if(request.getParameter("attribute1") != null)  
	strattribute1=request.getParameter("attribute1");

if(request.getParameter("attribute1value") != null)  
	strattribute1value=request.getParameter("attribute1value");

if(request.getParameter("attribute2") != null)  
	strattribute2=request.getParameter("attribute2");

if(request.getParameter("attribute2value") != null)  
	strattribute2value=request.getParameter("attribute2value");

if(request.getParameter("attribute3") != null)  
	strattribute3=request.getParameter("attribute3");

if(request.getParameter("attribute3value") != null)  
	strattribute3value=request.getParameter("attribute3value");

if(request.getParameter("attribute4") != null)  
	strattribute4=request.getParameter("attribute4");

if(request.getParameter("attribute4value") != null)  
	strattribute4value=request.getParameter("attribute4value");

if(request.getParameter("attribute5") != null)  
	strattribute5=request.getParameter("attribute5");

if(request.getParameter("attribute5value") != null)  
	strattribute5value=request.getParameter("attribute5value");

if(request.getParameter("attributename") != null)  
	strattributename=request.getParameter("attributename");

if(request.getParameter("selectvalue") != null)  
	strselectvalue=request.getParameter("selectvalue");

if(request.getParameter("savedName") != null)  
	strsavedname=request.getParameter("savedName");

if(request.getParameter("suppliercode") != null)  
	strsuppliercode=request.getParameter("suppliercode");

if(request.getParameter("productlevel3") != null)  
	strproductlevel3=request.getParameter("productlevel3");

System.out.println("Start CanonE008TemplateProcess action:"+strAction);
System.out.println("Start CanonE008TemplateProcess templateName:"+strTemplateName);
System.out.println("Start CanonE008TemplateProcess templateStatus:"+strTemplateStatus);
System.out.println("Start CanonE008TemplateProcess templateType:"+strTemplateType);
System.out.println("Start CanonE008TemplateProcess templateCopy:"+strTemplateCopy);
System.out.println("Start CanonE008TemplateProcess templateDesc:"+strTemplateDesc);
System.out.println("Start CanonE008TemplateProcess userId:"+strUserid);
System.out.println("Start CanonE008TemplateProcess cntTemplate:"+cnttemplate);
System.out.println("Start CanonE008TemplateProcess strsavedname:"+strsavedname);


System.out.println("Start CanonE008TemplateProcess:");
CanonE008TemplateDAO obj = new CanonE008TemplateDAO();

System.out.println("Before SAVE SEARCH ");
if(((strAction).toUpperCase()).equals("SAVESEARCH")) {		
	 {			
		 System.out.println("Start CanonE008TemplateProcess: SAVESEARCH");
		 
		result= obj.savesearchcriteria(strTemplateName,strTemplateType,strcatType,strTemplateStatus,stritemAssigned,strTemplateDesc,
				strattribute1,strattribute1value,strattribute2,strattribute2value,strattribute3,strattribute3value,
				strattribute4,strattribute4value,strattribute5,strattribute5value,strUserid,strsavedname);
		errFlag=(String)CanonE008ItemProcessUtil.first(result);
		errMsg=(String)CanonE008ItemProcessUtil.second(result);
		System.out.println("Start CanonE008TemplateProcess: SAVESEARCH errFlag - "+errFlag); 
		strReturnmessg = errFlag+"@@@"+errMsg+"@@@";
		 
	}

	 System.out.println("End CanonE008TemplateProcess:");	 
	 odata.append(strReturnmessg); 
	 response.getWriter().write(odata.toString()); 
}

System.out.println("Before  GET SAVE SEARCH ");
if(((strAction).toUpperCase()).equals("GETSAVESEARCH")) {		
	 {			
		 System.out.println("Start CanonE008TemplateProcess: GETSAVESEARCH");
		 
		 Object[] savedSearchValuesObjects=obj.getsavedsearchvalues(strsavedname,strUserid);

		 System.out.println("itemMainAttrValuesObjects[0]:::::"+savedSearchValuesObjects[0]);
		 errFlag=(String)savedSearchValuesObjects[1];
		 errMsg=(String)savedSearchValuesObjects[2];
		 strReturnmessg =savedSearchValuesObjects[0]+"@@"+ errFlag+"@@"+errMsg+"@@";		 
		 
 		 System.out.println("Start CanonE008TemplateProcess: GETSAVESEARCH errFlag - "+errFlag); 
		 
	}

	 System.out.println("End CanonE008TemplateProcess:");	 
	 odata.append(strReturnmessg); 
	 response.getWriter().write(odata.toString()); 
}

System.out.println("Before CREATE ");
if(((strAction).toUpperCase()).equals("CREATE")) {		
	 {			
		 System.out.println("Start CanonE008TemplateProcess: CREATE");
		 
		 CanonE008TemplateDAO.CanonE008TemplateListRec templateRec=null;
		 templateRec =new CanonE008TemplateDAO.CanonE008TemplateListRec();
         
		 templateRec.setTemplatename(strTemplateName);
		 templateRec.setTemplatestatus(strTemplateStatus);
		 templateRec.setTemplatedescription(strTemplateDesc);
		 templateRec.setTemplatetype(strTemplateType);
		 templateRec.setItemassigned(strTemplateCopy);
		 templateRec.setCreatedBy(strUserid);
         
		System.out.println("Start CanonE008TemplateProcess: createTemplate()");             
		result= obj.createTemplate(templateRec);
		template_id=(BigDecimal)CanonE008ItemProcessUtil.first(result);
		errFlag=(String)CanonE008ItemProcessUtil.second(result);
		errMsg=(String)CanonE008ItemProcessUtil.third(result);
		System.out.println("Start CanonE008TemplateProcess: template_id "+template_id); 
		strReturnmessg = errFlag+"@@@"+errMsg+"@@@"+template_id+"@@@";
		 
	}

	 System.out.println("End CanonE008TemplateProcess:");	 
	 odata.append(strReturnmessg); 
	 response.getWriter().write(odata.toString()); 
}

System.out.println("Before SAVE ");

if(((strAction).toUpperCase()).equals("SAVE")) {
	System.out.println("Inside SAVE IF ");
	String attribute_ids[] =  parameterValues(request,"attribute_id");

	String wbdisplays[] = parameterValues(request, "wbDisplay");
	String displaysorts[] = parameterValues(request, "display_sort");
	String categorynames[] = parameterValues(request,"cat_name");
	String approvalgroupowners[] = parameterValues(request,	"approval_group");
	String attributenames[] = parameterValues(request,"attribute_name");

	List attributeTable = new ArrayList();
	List attributeAssignTable = new ArrayList();
	List templateTable = new ArrayList();
	
	System.out.println("Before For loop ");
	for (int i = 0; attribute_ids != null
			&& i < attribute_ids.length; i++) {
		
		CanonE008TemplateDAO.CanonE008TemplateAttributeRec attributeSet = new CanonE008TemplateDAO.CanonE008TemplateAttributeRec();
		if (attribute_ids != null) {
			BigDecimal attribute_id = CanonE008ItemProcessUtil.toBigDecimal(attribute_ids[i], false);
			System.out.println("Inside For loop attribute_id "+ attribute_id);
			attributeSet.setAttributeid(attribute_id);
		}
		attributeTable.add(attributeSet);

		String wbdisplay = request.getParameter("wb_display_h"+i); 
		BigDecimal displaysort = CanonE008ItemProcessUtil.toBigDecimal(request.getParameter("display_sort_h"+i), false);
		String categoryname = request.getParameter("cat_name_h"+i);
		String approvalgroupowner = request.getParameter("approval_group_h"+i);
		String attributename = attributenames != null? attributenames[i]: null;
		
		System.out.println("Inside For loop wbdisplay "+ wbdisplay);		
		System.out.println("Inside For loop displaysort "+ displaysort);
		System.out.println("Inside For loop categoryname "+ categoryname);
		System.out.println("Inside For loop approvalgroupowner "+ approvalgroupowner);
		System.out.println("Inside For loop approvalgroupowners i "+ approvalgroupowners[i]);
		//System.out.println("Inside For loop attributename "+ attributename);
		
		if (wbdisplay != null)
		{
 			attributeSet.setWorkbenchdisplay(wbdisplay);
		}
		if  (displaysort != null)
		{	
			attributeSet.setDisplaysort(displaysort);
		}	
		if  (categoryname != null)
		{
		    attributeSet.setCategoryname(categoryname);
		}
		if  (approvalgroupowner != null)
		{
			attributeSet.setApprovalgroupowner(approvalgroupowner);
			//attributeSet.setAttributename(attributename);
		}
		
		
		attributeSet.setLastUpdateBy(strUserid);
		//////////////////////////////////////////////////////////////
	
		
		for (int ii = 0; ii < cnttemplate; ii++) 
		{

	 			CanonE008TemplateDAO.CanonE008TemplateAttrAssignListRec attributeAssignSet = new CanonE008TemplateDAO.CanonE008TemplateAttrAssignListRec();
				//BigDecimal template_id;
				BigDecimal attribute_id;
				String reqval;
				String validValue;
				String defaultvalue;
			
				template_id=null;
				attribute_id=null;
				reqval="";
				validValue="";
				defaultvalue="";
				if (attribute_ids != null) {
					
					template_id = CanonE008ItemProcessUtil.toBigDecimal(request.getParameter("template_id"+ii), false);
					attribute_id = CanonE008ItemProcessUtil.toBigDecimal(attribute_ids[i], false);
					System.out.println("Inside For 2nd loop attribute_id "+ attribute_id);
					System.out.println("Inside For 2nd loop template_id "+ template_id);
					attributeAssignSet.setAttributeid(attribute_id);
					attributeAssignSet.setTemplateid(template_id);
				}
				attributeAssignTable.add(attributeAssignSet);
				
	 			reqval = request.getParameter(template_id+"req_value"+i); 
				validValue = request.getParameter(template_id+"valid_value"+i);
				defaultvalue = request.getParameter(template_id+"default_value"+i);
	
				
	 			System.out.println("Inside For loop reqval "+ reqval);		
				System.out.println("Inside For loop validValue "+ validValue);
				System.out.println("Inside For loop defaultvalue "+ defaultvalue);
				
				if (reqval != null)
				{
					attributeAssignSet.setRequired(reqval);
				}
				if  (validValue != null)
				{	
					attributeAssignSet.setValid(validValue);
				}	
				if  (defaultvalue != null)
				{
					attributeAssignSet.setDefaultvalue(defaultvalue);
				}
		
				//attributeAssignSet.setLastUpdateBy(strUserid);
		}
			
	}
	System.out.println("before For 1 Saving ");
	resultAttribute = CanonE008TemplateDAO.saveAttributeList(attributeTable);
	errFlag=(String)CanonE008ItemProcessUtil.first(resultAttribute);
	errMsg=(String)CanonE008ItemProcessUtil.second(resultAttribute);
	strReturnmessg = errFlag+"@@@"+errMsg+"@@@";
	//checkErrors(resultAttribute, 0, 1);
	System.out.println("before For 2 Saving ");
	resultAttributeAssg = CanonE008TemplateDAO.saveAttributeAssignList(attributeAssignTable);
	errFlag=(String)CanonE008ItemProcessUtil.first(resultAttributeAssg);
	errMsg=(String)CanonE008ItemProcessUtil.second(resultAttributeAssg);
	strReturnmessg = strReturnmessg + errFlag+"@@@"+errMsg+"@@@";

	//checkErrors(resultAttributeAssg, 0, 1);

	System.out.println("before For 3rd loop ");
	
	for (int j = 0; j < cnttemplate; j++) 
	{
		System.out.println("Inside For 3rd loop "); 
			CanonE008TemplateDAO.CanonE008TemplateListRec templateSet = new CanonE008TemplateDAO.CanonE008TemplateListRec();
			BigDecimal rectemplate_id = CanonE008ItemProcessUtil.toBigDecimal(request.getParameter("template_id"+j), false);
			templateSet.setTemplateid(rectemplate_id);
			templateTable.add(templateSet);
			System.out.println("Inside For 3rd loop 1");
			
			String templateType = request.getParameter("templateType"+j); 
			String templateStatus = request.getParameter("templateStatus"+j);
			String templateDesc = request.getParameter("template_desc"+j);
			
			System.out.println("Inside For loop templateId "+ template_id);
			System.out.println("Inside For loop templateType "+ templateType);		
		System.out.println("Inside For loop templateStatus "+ templateStatus);
		System.out.println("Inside For loop templateDesc "+ templateDesc);
		
		if (templateType != null)
		{
			templateSet.setTemplatetype(templateType);
		}
		if  (templateStatus != null)
		{	
			templateSet.setTemplatestatus(templateStatus);
		}	
		if  (templateDesc != null)
		{
			templateSet.setTemplatedescription(templateDesc);
		}
		
		//templateSet.setLastUpdateBy(strUserid);
			
	}   
	resulttemplate = CanonE008TemplateDAO.updateTemplateList(templateTable);
	//checkErrors(resulttemplate, 0, 1);
	errFlag=(String)CanonE008ItemProcessUtil.first(resulttemplate);
	errMsg=(String)CanonE008ItemProcessUtil.second(resulttemplate);
	strReturnmessg = strReturnmessg + errFlag+"@@@"+errMsg+"@@@";

	 System.out.println("End CanonE008TemplateProcess:" + strReturnmessg);	 
	 odata.append(strReturnmessg); 
	 response.getWriter().write(odata.toString()); 
}

System.out.println("Before GETLOV ");
if(((strAction).toUpperCase()).equals("GETLOV")) {		
	 {			
		 System.out.println("Start CanonE008TemplateProcess: GETLOV");
		 
		 Object[] lovObjects;
		 
		 if(((strattributename).toUpperCase()).equals("SUPPLIER_SITE")) {	
		 
			 lovObjects = obj.getitemattdefSupplierList(strsuppliercode,strattributename,strselectvalue);
		 }
		 else
		 {		 
			 lovObjects = obj.getitemattdefvalueListClick(strattributename,strselectvalue,strproductlevel3);	
		 } 
		 
		
  		 errFlag=(String)lovObjects[1];
		 errMsg=(String)lovObjects[2];
		 strReturnmessg =lovObjects[0]+"@@@"+ errFlag+"@@@"+errMsg+"@@@";
		
		//strReturnmessg = errFlag+"@@@"+errMsg+"@@@"+template_id+"@@@";
	}

	 System.out.println("End CanonE008TemplateProcess:");	 
	 odata.append(strReturnmessg); 
	 response.getWriter().write(odata.toString()); 
}

%>