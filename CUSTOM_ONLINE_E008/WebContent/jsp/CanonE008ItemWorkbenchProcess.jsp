<%--
 /*===========================================================================+
 |      															 	     |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  FILENAME                                                                 |
 |            CanonE008TemplateProcess.jsp                                   |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |   													  					 |
 |    																		 |
 |                                                                           |
 |  NOTES                                                                    |
 |         Using template                                                    |
 |  DEPENDENCIES                                                             |
 |                           	    										 |
 |                                                                           |
 |  HISTORY                                                                  |
 |                                                                           |
 |   12-22-2015      Madhusudan Duna --  Ver:1                               |
 +===========================================================================*/
--%>
<%@page import="canon.apps.common.CanonConstants"%>
<%@page import="java.sql.Date"%>
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
<%@page import="oracle.apps.e008.item.process.CanonE008TemplateDAO"%>
<%@ page import="oracle.apps.e008.item.process.CanonE008ItemProcessDAO" %>
<%@page	import="oracle.apps.e008.item.process.CanonE008ItemProcessHelper"%>
<%@ page import="oracle.apps.e008.item.process.CanonE008ItemRec" %>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@ page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="java.lang.reflect.InvocationTargetException"%>
<!-- %@page import="org.apache.commons.beanutils.BeanUtils"%-->
<%@include file="CanonE008ItemProcessCommon.jsp"%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessUtil"%>
<%@page import="canon.apps.common.CanonEmailUtil"%>
<%@page import="org.apache.commons.mail.HtmlEmail"%>
<%@page import="canon.apps.common.CanonCustomProfile"%>

<%

System.out.println("Inside CanonE008ItemWorkbenchProcess.jsp ajax:::::::::::::::::::::::");          
String errFlag="E";
String errMsg="";	
String strAction = "";
String strTemplateName = "";
String strTemplateStatus = "";
String strTemplateDesc = "";
String strTemplateType = "";
String strTemplateCopy = "";
String strUserid ="";
//String strprojectType="";
String strsavedname="";

BigDecimal strprojectNo=new BigDecimal(0);
//String strprojectNo = "";
String strprojectName="";
String strmodelNo="";
String strprojectDesc="";
String strprojectType="";
String stritemNo="";
String strprojectNote="";
String strmasterProj="";
String strProjectRequester="";
String strnew ="";
String strrole="";
String strreqname = "";
String strstatus = "";
String strcompList="";
int strstartRow = 0;
int strendRow=0;

Object result[] = null;
BigDecimal template_id= null;
String projectNo="";


int cntRow=0;
//int attributeCount=0;

Object result1[] = null;
Object resultAttribute[] = null;
Object resultAttributeAssg[] = null;
Object resulttemplate[] = null;
String loginUserID = s21Authentication.getIdentityDetails().getUID();//(BigDecimal)CanonE008ItemProcessUtil.second(result);

String strReturnmessg = "";

StringBuffer odata = new StringBuffer(100);

String msg ="";
String[] msg1;

List emailList;

String roleId="";

if(request.getParameter("action") != null) 
	strAction=request.getParameter("action");
System.out.println("Here:::::::::::::::::::::::"+request.getParameter("action"));

if((request.getParameter("projectNumber") != null) || (request.getParameter("projectNumber") != "")) 
	strprojectNo=CanonE008ItemProcessUtil.toBigDecimal(request.getParameter("projectNumber").trim(),false);
else
	strprojectNo=CanonE008ItemProcessUtil.toBigDecimal("0",false);
	
System.out.println("Start CanonE008ItemWorkbenchProcess strprojectNo:"+strprojectNo);	
	
if(request.getParameter("projectName") != null)
	strprojectName=request.getParameter("projectName").trim();

System.out.println("Start CanonE008ItemWorkbenchProcess strprojectName:"+strprojectName);	

if(request.getParameter("projectType") != null)
	strprojectType=request.getParameter("projectType");

System.out.println("Start CanonE008ItemWorkbenchProcess strProjectType:"+strprojectType);

if(request.getParameter("projectDesc") != null)
	strprojectDesc=request.getParameter("projectDesc");

System.out.println("Start CanonE008ItemWorkbenchProcess strprojectDesc:"+strprojectDesc);

if(request.getParameter("masterProject") != null)
	strmasterProj=request.getParameter("masterProject");

System.out.println("Start CanonE008ItemWorkbenchProcess masterProj:"+strmasterProj);

if(request.getParameter("projectRequester") != null)
	strProjectRequester=request.getParameter("projectRequester");

System.out.println("Start CanonE008ItemWorkbenchProcess projectRequester:"+strProjectRequester);

if(request.getParameter("projectNote") != null)
	strprojectNote=request.getParameter("projectNote");

if(request.getParameter("userId") != null)
	strUserid=request.getParameter("userId");

System.out.println("Start CanonE008ItemWorkbenchProcess userId:"+strUserid);

if(request.getParameter("approval_Satus") != null)
	strstatus=request.getParameter("approval_Satus");

if(request.getParameter("cntRow") != null)
	cntRow= Integer.parseInt(request.getParameter("cntRow"));

if(request.getParameter("excelRecord") != null)
	cntRow= Integer.parseInt(request.getParameter("excelRecord"));


if(request.getParameter("reqName") != null)
	strreqname= request.getParameter("reqName");


if(request.getParameter("savedName") != null)  
	strsavedname=request.getParameter("savedName");

if(request.getParameter("compList") != null)  
	strcompList=request.getParameter("compList");

if(request.getParameter("startRow") != null)
	strstartRow= Integer.parseInt(request.getParameter("startRow").trim());

if(request.getParameter("endRow") != null)
	strendRow=Integer.parseInt(request.getParameter("endRow").trim());


System.out.println("Start CanonE008ItemWorkbenchProcess cntRow:"+cntRow);

/* if(request.getParameter("attributeCount") != null)
	attributeCount= Integer.parseInt(request.getParameter("attributeCount"));
System.out.println("Start CanonE008ItemWorkbenchProcess attributeCount:"+attributeCount);
 */


System.out.println("Start CanonE008ItemWorkbenchProcess:");
 CanonE008ItemProcessDAO obj = new CanonE008ItemProcessDAO();

     
System.out.println("Before SAVE ");
if(strAction.toUpperCase().equals("SAVE") || strAction.toUpperCase().equals("UPLOAD") || strAction.toUpperCase().equals("VALIDATEUPLOAD"))  		
	 {	 		

		if(strAction.toUpperCase().equals("UPLOAD") || strAction.toUpperCase().equals("VALIDATEUPLOAD"))
		{
			strstartRow = 0;
			strendRow = cntRow;
		}
		//System.out.println("Start CanonE008ItemWorkbenchProcess: SAVE");
		 
		 CanonE008ItemProcessDAO.CanonE008ProjectRec itemProjectRec=null;
		 //System.out.println("Start CanonE008ItemWorkbenchProcess: SAVE1");
		 itemProjectRec =new CanonE008ItemProcessDAO.CanonE008ProjectRec();
		 //System.out.println("Start CanonE008ItemWorkbenchProcess: SAVE2");
         itemProjectRec.setEztableid("");
         //System.out.println("Start CanonE008ItemWorkbenchProcess: SAVE3");
         itemProjectRec.setCompanycode("ABD");
         //System.out.println("Start CanonE008ItemWorkbenchProcess: SAVE4 strprojectNo - " + strprojectNo);
         
         itemProjectRec.setProjectId(strprojectNo);

         //System.out.println("Start CanonE008ItemWorkbenchProcess: SAVE5");
		 //itemProjectRec.setProjectNumber(strprojectNo);
		 itemProjectRec.setProjectNumber(new BigDecimal(1234));
		 //System.out.println("Start CanonE008ItemWorkbenchProcess: SAVE6");
		 itemProjectRec.setProjectName(strprojectName);
		 //System.out.println("Start CanonE008ItemWorkbenchProcess: SAVE7");
		 itemProjectRec.setProjectDesc(strprojectDesc);
		 System.out.println("Start CanonE008ItemWorkbenchProcess: strprojectType" + strprojectType);
		 itemProjectRec.setProjectType(strprojectType);
		 itemProjectRec.setMasterproject(strmasterProj);
		 itemProjectRec.setProjectRequester(strProjectRequester);
		 itemProjectRec.setRequestedDate(new Date(System.currentTimeMillis()));
		 itemProjectRec.setProjectNotes(strprojectNote);		 
		// itemProjectRec.setCreatedBy(strUserid);   
		 itemProjectRec.setApprovalStatus("");
		 itemProjectRec.setApprovalComments("");		
		 itemProjectRec.setCreatedBy(loginUserID);
		 itemProjectRec.setCreationDate(new Date(System.currentTimeMillis()));
         itemProjectRec.setLastUpdateBy(loginUserID);
       //  itemProjectRec.setLastUpdateDate(new Date(System.currentTimeMillis()));
		 
		 
  	    CanonE008ItemProcessDAO canonE008ItemProcessDAO=new CanonE008ItemProcessDAO();

        Object[] attributeHeaders = canonE008ItemProcessDAO.getItemMainAttributeHeaders(strprojectNo,loginUserID);
        ArrayList templateHeaderList = (ArrayList)attributeHeaders[0];

        Object[] attributeadditional = canonE008ItemProcessDAO.getProjItemAdditionalAttrValues(new BigDecimal("1"),new BigDecimal("1"));
        //ArrayList attributeList = (List)CanonE008ItemProcessUtil.first(attributeadditional);
        ArrayList templateAddtionalList = (ArrayList)attributeadditional[0];
		
		 
        
        System.out.println("Start CanonE008ItemWorkbenchProcess: canon008ItemRecsList()");
		List canon008ItemRecsList=new ArrayList();
		int headerAttribute=0;
		String mercuryCheck="";
		if (strprojectNo != null)
		{	
		//for(int i=0;i<cntRow;i++)
		for(int i=strstartRow;i<strendRow;i++)	
		{
			CanonE008ItemRec canon008ProjItemRecsToSet=new CanonE008ItemRec();	
			for(int k=0;k<templateHeaderList.size();k++)
			{	
				CanonE008ItemProcessDAO.attributeheaderInfo tempHeader = (CanonE008ItemProcessDAO.attributeheaderInfo)templateHeaderList.get(k);
				String templateHeader=(String)tempHeader.getAttributeName();
				
				//String templateHeader=(String)templateHeaderList.get(k);
				//System.out.println("Start CanonE008ItemWorkbenchProcess: 1");
				if (templateHeader.equals("Item Number"))
				{
					System.out.println("Start CanonE008ItemWorkbenchProcess: 1.1");
					String strPItemNumber[];
					strPItemNumber= request.getParameterValues("templateRowName"+i+"-"+k);
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item No " + strPItemNumber[0]);
					
					System.out.println("Start CanonE008ItemWorkbenchProcess: 1.2  "+i+"-"+k);
					
					canon008ProjItemRecsToSet.setItemnumber(strPItemNumber[0]);	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 1.3");
					
					if (strAction.toUpperCase().equals("SAVE"))
					{		
						mercuryCheck = request.getParameter("mercuryind"+i);
						if (!mercuryCheck.equalsIgnoreCase("")) 
						{
							canon008ProjItemRecsToSet.setMercuryinclude(mercuryCheck);
							System.out.println("Start CanonE008ItemWorkbenchProcess: mercuryCheck "+ mercuryCheck);
						}
					}				
				}
				
				if (!request.getParameter("item_name_"+i).equals(""))
					canon008ProjItemRecsToSet.setProjitemid(new BigDecimal(request.getParameter("item_name_"+i)));
				
				
				//System.out.println("Start CanonE008ItemWorkbenchProcess: 2");
				canon008ProjItemRecsToSet.setTemplatename(request.getParameter("canonTemplateNames_"+i));	
				//System.out.println("Start CanonE008ItemWorkbenchProcess: 3");
				
				canon008ProjItemRecsToSet.setCompanycode(CanonConstants.CSA_COMPANY_CODE);
				canon008ProjItemRecsToSet.setCriticality(request.getParameter("criticaLityAttr"+i));

				if (templateHeader.equals("Item Description"))
				{
					canon008ProjItemRecsToSet.setItemdescription(request.getParameter("templateRowName"+i+"-"+k));
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				//System.out.println("Start CanonE008ItemWorkbenchProcess: 4");
				if (templateHeader.equals("Manufacturer"))
				{
					canon008ProjItemRecsToSet.setManufacturer(request.getParameter("templateRowName"+i+"-"+k));
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Manufacturer " + request.getParameter("templateRowName"+i+"-"+k));
				}

				if (templateHeader.equals("Manufacturer Item"))
				{
					canon008ProjItemRecsToSet.setManufactureritemnumber(request.getParameter("templateRowName"+i+"-"+k));
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Manufacturer Item " + request.getParameter("templateRowName"+i+"-"+k));
				}

				if (templateHeader.equals("UPC Code"))
				{
					canon008ProjItemRecsToSet.setUpccode(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "UPC Code " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Long Description"))
				{
					canon008ProjItemRecsToSet.setLongdescription(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Long Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Item Type"))
				{
					canon008ProjItemRecsToSet.setItemtype(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Type " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Merchandise Type"))
				{
					canon008ProjItemRecsToSet.setMerchandisetype(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Merchandise Type " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Item Classification"))
				{
					canon008ProjItemRecsToSet.setItemclassfication(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Classification " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Product Code"))
				{
					canon008ProjItemRecsToSet.setProductcode(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Product Code " + request.getParameter("templateRowName"+i+"-"+k));
				}
				/*if (templateHeader.equals("Marketing Model"))
				{
					canon008ProjItemRecsToSet.setMarketingmodel(request.getParameter("modtemplateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Marketing Model " + request.getParameter("modtemplateRowName"+i+"-"+k));
				}*/
				if (templateHeader.equals("Marketing Model"))
				{
					canon008ProjItemRecsToSet.setMarketingmodel(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Marketing Model " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Marketing Segment"))
				{
					canon008ProjItemRecsToSet.setMarketingsegment(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Marketing Segment " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Commissions Group"))
				{
					canon008ProjItemRecsToSet.setCommissionsgroup(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Commissions Group " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("UOM Class"))
				{
					canon008ProjItemRecsToSet.setUomclass(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "UOM Class " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("UOM"))
				{
					canon008ProjItemRecsToSet.setUom(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "UOM " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Standard Cost"))
				{
					canon008ProjItemRecsToSet.setStandardcost(request.getParameter("templateRowName"+i+"-"+k));
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Standard Cost " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Asset Recovery Cost"))
				{
					canon008ProjItemRecsToSet.setAssetrecoverycost(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Asset Recovery Cost " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Product Level1"))
				{
					canon008ProjItemRecsToSet.setProductlevel1(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Product Level1 " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Product Level2"))
				{
					canon008ProjItemRecsToSet.setProductlevel2(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Product Level2 " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Product Level3"))
				{
					canon008ProjItemRecsToSet.setProductlevel3(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Product Level3 " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Product Level4"))
				{
					canon008ProjItemRecsToSet.setProductlevel4(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Product Level4 " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Product Level5"))
				{
					canon008ProjItemRecsToSet.setProductlevel5(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Product Level5 " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Material Group1"))
				{
					canon008ProjItemRecsToSet.setMaterialgroup1(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Material Group1 " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Material Group2"))
				{
					canon008ProjItemRecsToSet.setMaterialgroup2(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Material Group2 " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Material Group3"))
				{
					canon008ProjItemRecsToSet.setMaterialgroup3(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Material Group3 " + request.getParameter("templateRowName"+i+"-"+k));
				}

				if (templateHeader.equals("Inventory Trackable"))
				{
					canon008ProjItemRecsToSet.setInventorytrackable(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Inventory Trackable " + request.getParameter("templateRowName"+i+"-"+k));
				}
				
				if (templateHeader.equals("Return Controlled"))
				{
					canon008ProjItemRecsToSet.setReturncontrolled(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Return Controlled " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Return Control Type"))
				{
					canon008ProjItemRecsToSet.setReturncontroltype(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Return Control Type " + request.getParameter("templateRowName"+i+"-"+k));
				}

				if (templateHeader.equals("Return Warehouse"))
				{
					canon008ProjItemRecsToSet.setReturnwarehouse(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Return Warehouse " + request.getParameter("templateRowName"+i+"-"+k));
				}

				if (templateHeader.equals("Return Sub Warehouse"))
				{
					canon008ProjItemRecsToSet.setReturnsubwarehouse(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Return Sub Warehouse " + request.getParameter("templateRowName"+i+"-"+k));
				}

				if (templateHeader.equals("Return Vendor"))
				{
					canon008ProjItemRecsToSet.setReturnvendor(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Return Vendor " + request.getParameter("templateRowName"+i+"-"+k));
				}

				if (templateHeader.equals("Return Vendor Site"))
				{
					canon008ProjItemRecsToSet.setReturnvendorsite(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Return Vendor Site " + request.getParameter("templateRowName"+i+"-"+k));
				}
				
				if (templateHeader.equals("Item Billing Type"))
				{
					canon008ProjItemRecsToSet.setItembillingtype(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Billing Type " + request.getParameter("templateRowName"+i+"-"+k));
				}

				if (templateHeader.equals("Requires Config Center"))
				{
					canon008ProjItemRecsToSet.setConfigurationflag(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Requires Config Center " + request.getParameter("templateRowName"+i+"-"+k));
				}
				
				
				if (templateHeader.equals("Service Model"))
				{
					canon008ProjItemRecsToSet.setServicemodel(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Service Model " + request.getParameter("templateRowName"+i+"-"+k));
				}

				if (templateHeader.equals("Purchase Price"))
				{
					canon008ProjItemRecsToSet.setPurchasePrice(request.getParameter("templateRowName"+i+"-"+k));	
					System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Purchase Price " + request.getParameter("templateRowName"+i+"-"+k));
				}
				
				if (templateHeader.equals("Revenue"))
				{
					 canon008ProjItemRecsToSet.setRevenue(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Revenue " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Cost Of Goods"))
				{
					 canon008ProjItemRecsToSet.setCostofgoods(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Cost Of Goods " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Expense"))
				{
					 canon008ProjItemRecsToSet.setExpense(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Expense " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Accrual"))
				{
					 canon008ProjItemRecsToSet.setAccrual(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Accrual " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Owning Division"))
				{
					 canon008ProjItemRecsToSet.setOwningdivision(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Owning Division " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Coverage Base Type"))
				{
					 canon008ProjItemRecsToSet.setCoveragebasetype(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Coverage Base Type " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Coverage Type"))
				{
					 canon008ProjItemRecsToSet.setCoveragetype(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Coverage Type " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Service Allocation Type"))
				{
					 canon008ProjItemRecsToSet.setServiceallocationtype(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Service Allocation Type " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("TC Option"))
				{
					 canon008ProjItemRecsToSet.setTcoption(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "TC Option " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("TC Option Value"))
				{
					 canon008ProjItemRecsToSet.setTcoptionvalue(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "TC Option Value " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Weight (Pounds)"))
				{
					 canon008ProjItemRecsToSet.setWeight(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Weight (Pounds) " + request.getParameter("templateRowName"+i+"-"+k));
				}
				
				if (templateHeader.equals("Length (Inches)"))
				{
					 canon008ProjItemRecsToSet.setItemlength(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Length (Inches) " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Width (Inches)"))
				{
					 canon008ProjItemRecsToSet.setItemdepth(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Width (Inches) " + request.getParameter("templateRowName"+i+"-"+k));
				}
				
				if (templateHeader.equals("Height (Inches)"))
				{
					 canon008ProjItemRecsToSet.setHeight(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Height (Inches) " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Part Type"))
				{
					 canon008ProjItemRecsToSet.setParttype(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Part Type " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Parts Dropship Disabled"))
				{
					 canon008ProjItemRecsToSet.setPartsdropshipdisabled(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Parts Dropship Disabled " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Part Yield - Outputs"))
				{
					 canon008ProjItemRecsToSet.setPartyieldoutputs(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Part Yield - Outputs " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Part Yield - Days"))
				{
					 canon008ProjItemRecsToSet.setPartyielddays(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Part Yield - Days " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Parts Survey Required"))
				{
					 canon008ProjItemRecsToSet.setPartssurveyrequired(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Parts Survey Required " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Warranty"))
				{
					 canon008ProjItemRecsToSet.setWarranty(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Warranty " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Warranty Period (Days)"))
				{
					 canon008ProjItemRecsToSet.setWarrantyperiod(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Warranty Period (Days) " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Imageware Remote Enabled"))
				{
					 canon008ProjItemRecsToSet.setImagewareremoteenabled(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Imageware Remote Enabled " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Imageware Remote Model"))
				{
					 canon008ProjItemRecsToSet.setImagewareremotemodel(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Imageware Remote Model " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Imageware Remote Item"))
				{
					 canon008ProjItemRecsToSet.setImagewareremoteitem(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Imageware Remote Item " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Metered Machine"))
				{
					 canon008ProjItemRecsToSet.setMeteredmachine(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Metered Machine " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Installbase Controlled"))
				{
					 canon008ProjItemRecsToSet.setInstallbasecontrolled(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Installbase Controlled " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Service Call Enabled"))
				{
					 canon008ProjItemRecsToSet.setServicecallenabled(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Service Call Enabled " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Software Category"))
				{
					 canon008ProjItemRecsToSet.setSoftwarecategory(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Software Category " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Software Version"))
				{
					 canon008ProjItemRecsToSet.setSoftwareversion(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Software Versio " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Software Product Category"))
				{
					 canon008ProjItemRecsToSet.setSoftwareproductcategory(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Software Product Category " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("License Control"))
				{
					 canon008ProjItemRecsToSet.setLicensecontrol(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "License Control " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Elan Controlled"))
				{
					 canon008ProjItemRecsToSet.setElancontrolled(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Elan Controlled " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Seats From"))
				{
					 canon008ProjItemRecsToSet.setSeatsfrom(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Seats To"))
				{
					 canon008ProjItemRecsToSet.setSeatsto(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Fixed No of Seats"))
				{
					 canon008ProjItemRecsToSet.setFixednoofseats(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Software Maintenance Control"))
				{
					 canon008ProjItemRecsToSet.setSoftwaremaintenancecontrol(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Assurance Points Per License"))
				{
					 canon008ProjItemRecsToSet.setAssurancepointsperlicense(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Assurance Points Fixed Per Ordered Qty"))
				{
					 canon008ProjItemRecsToSet.setAssuranceptsfixedperordqty(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}				
				if (templateHeader.equals("Bundled Maintenance Item"))
				{
					 canon008ProjItemRecsToSet.setBundledmaintenanceitem(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Maintenance - POP Available"))
				{
					 canon008ProjItemRecsToSet.setMaintenancepopavaliable(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Extended Maintenance - POP Available"))
				{
					 canon008ProjItemRecsToSet.setExtendedmaintpopavailable(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Maintenance Item Type"))
				{
					 canon008ProjItemRecsToSet.setMaintenanceitemtype(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				} 
				if (templateHeader.equals("Maintenance Item Term"))
				{
					 canon008ProjItemRecsToSet.setMaintenanceitemterm(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Assurance Points Max"))
				{
					 canon008ProjItemRecsToSet.setAssurancepointsmax(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Assurance Points Min"))
				{
					 canon008ProjItemRecsToSet.setAssurancepointmin(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Supply OEM Manufacturer"))
				{
					 canon008ProjItemRecsToSet.setSupplyoemmanufactuer(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Supply OEM Code"))
				{
					 canon008ProjItemRecsToSet.setSupplyoemcode(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Supply Type"))
				{
					 canon008ProjItemRecsToSet.setSupplytype(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Supply Color"))
				{
					 canon008ProjItemRecsToSet.setSupplycolor(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Supply Yield"))
				{
					 canon008ProjItemRecsToSet.setSupplyyield(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Supply Yield Type"))
				{
					 canon008ProjItemRecsToSet.setSupplyyieldtype(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Supply Yield UOM"))
				{
					 canon008ProjItemRecsToSet.setSupplyyielduom(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Private Label Flag"))
				{
					 canon008ProjItemRecsToSet.setPrivatelabelflag(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Third Party"))
				{
					 canon008ProjItemRecsToSet.setThirdparty(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Tariff Code"))
				{
					 canon008ProjItemRecsToSet.setTariffcode(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Freight Class Code"))
				{
					 canon008ProjItemRecsToSet.setFreightclasscode(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Intangible Mdse Type"))
				{
					 canon008ProjItemRecsToSet.setIntangiblemdsetype(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				
				if (templateHeader.equals("Invoiceable"))
				{
					 canon008ProjItemRecsToSet.setInvoiceable(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Accounting Rules"))
				{
					 canon008ProjItemRecsToSet.setAccountingrules(request.getParameter("templateRowName"+i+"-"+k));	
					 System.out.println("Start CanonE008ItemWorkbenchProcess: 3" + "Item Description " + request.getParameter("templateRowName"+i+"-"+k));
				}
				if (templateHeader.equals("Invoicing Rules"))
				{
					 canon008ProjItemRecsToSet.setInvoicingrules(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Tax Code"))
				{
					 canon008ProjItemRecsToSet.setTaxcode(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Safety - Hazardous Material"))
				{
					 canon008ProjItemRecsToSet.setSafetyhazardousmaterial(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Safety - Hazardous Number"))
				{
					 canon008ProjItemRecsToSet.setSafetyhazardousnumber(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Safety - Hazardous Class"))
				{
					 canon008ProjItemRecsToSet.setSafetyhazardousclass(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Safety - Hazardous Shipping Label"))
				{
					 canon008ProjItemRecsToSet.setSafetyhazardousshiplabel(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Safety - Hazardous ID"))
				{
					 canon008ProjItemRecsToSet.setSafetyhazardousid(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Manufactured Country"))
				{
					 canon008ProjItemRecsToSet.setManufacturedcountry(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Assembled Country"))
				{
					 canon008ProjItemRecsToSet.setAssembledcountry(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("NMFC Class"))
				{
					 canon008ProjItemRecsToSet.setNfmcclass(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Costed"))
				{
					 canon008ProjItemRecsToSet.setCosted(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("RMA Allowed"))
				{
					 canon008ProjItemRecsToSet.setRmaallowed(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("RMA Inspection Required"))
				{
					 canon008ProjItemRecsToSet.setRmainspectionrequired(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Default Source Warehouse"))
				{
					 canon008ProjItemRecsToSet.setDefaultsourcewarehouse(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Default Source Sub Warehouse"))
				{
					 canon008ProjItemRecsToSet.setDefaultsourcesubwarehouse(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Serial Control"))
				{
					 canon008ProjItemRecsToSet.setSerialcontrol(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Serial From"))
				{
					 canon008ProjItemRecsToSet.setSerialfrom(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Serial To"))
				{
					 canon008ProjItemRecsToSet.setSerialto(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Main Engine"))
				{
					 canon008ProjItemRecsToSet.setMainengine(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Criticality"))
				{
					 canon008ProjItemRecsToSet.setCriticality(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Reman Available"))
				{
					 canon008ProjItemRecsToSet.setRemanavailable(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("SOW Required"))
				{
					 canon008ProjItemRecsToSet.setSowrequired(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Customer Orderable"))
				{
					 canon008ProjItemRecsToSet.setCustomerordereable(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Minimum Order Quantity"))
				{
					 canon008ProjItemRecsToSet.setMinimumorderquantity(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Maximum Order Quantity"))
				{
					 canon008ProjItemRecsToSet.setMaximumorderquantity(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Order Increments"))
				{
					 canon008ProjItemRecsToSet.setOrderincrements(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Remanufactured Item Exists"))
				{
					 canon008ProjItemRecsToSet.setRemanufactureditemexists(request.getParameter("templateRowName"+i+"-"+k));	
				}

				if (templateHeader.equals("Supplier"))
				{
					 canon008ProjItemRecsToSet.setSupplier(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Supplier Item"))
				{
					 canon008ProjItemRecsToSet.setSupplierItem(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Supplier Site"))
				{
					 canon008ProjItemRecsToSet.setSupplierSite(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Superseded By"))
				{
					 canon008ProjItemRecsToSet.setSupersededBy(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Area of Paper (Square foot)"))
				{
					 canon008ProjItemRecsToSet.setAreaofpaper(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Planning Group"))
				{
					 canon008ProjItemRecsToSet.setPlanninggroup(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Hard Allocation Type"))
				{
					 canon008ProjItemRecsToSet.setHardallocationtype(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Default Source Type"))
				{
					 canon008ProjItemRecsToSet.setDefaultsourcetype(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Easy PAC I"))
				{
					 canon008ProjItemRecsToSet.setEasypacki(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("MSRP"))
				{
					 canon008ProjItemRecsToSet.setMsrp(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Lead Time"))
				{
					 canon008ProjItemRecsToSet.setLeadTime(request.getParameter("templateRowName"+i+"-"+k));	
				}
				if (templateHeader.equals("Internal Item"))
				{
					 canon008ProjItemRecsToSet.setInternalItem(request.getParameter("templateRowName"+i+"-"+k));	
				}
				
			}

			System.out.println("Start CanonE008ItemWorkbenchProcessADD: "+ i +"-"+ templateAddtionalList.size());
 			for( int k=0;k<templateAddtionalList.size();k++){
				CanonE008ItemProcessDAO.ItemInfoRec itemrec=(CanonE008ItemProcessDAO.ItemInfoRec) templateAddtionalList.get(k);
				
				String templateAdditional=(String)itemrec.getAttribute();	
				String attributevalue = request.getParameter("templateaddRowName"+i+"-"+k);
				//System.out.println("Start CanonE008ItemWorkbenchProcessADD: templateAdditional k: "+ k +"-"+ templateAdditional);
				//System.out.println("Start CanonE008ItemWorkbenchProcessADD: attributevalue k: "+ k +"-"+ attributevalue);
				
				String[] actualAttributeval;
				String mainAttributeval;
		
				//System.out.println("Start CanonE008ItemWorkbenchProcessADD: kK:"+ k +"-"+ attributevalue);	
				//System.out.println("Start CanonE008ItemWorkbenchProcessADD: ACTION:"+ k +"-"+ strAction);	
				if (strAction.toUpperCase().equals("SAVE"))
				{	
					actualAttributeval = attributevalue.split("[**]");
					mainAttributeval = actualAttributeval[0];
				}	
				else
				{
					mainAttributeval = attributevalue;
				}
 		
				//System.out.println("Start CanonE008ItemWorkbenchProcessADD: templateAdditional: "+ k +" - "+ templateAdditional);
				
				//System.out.println("Start CanonE008ItemWorkbenchProcessADD: "+ k +"-"+ templateAdditional);
				if (templateAdditional.equals("Manufacturer"))
				{
					canon008ProjItemRecsToSet.setManufacturer(mainAttributeval);	
				}

				if (templateAdditional.equals("Manufacturer Item"))
				{
					canon008ProjItemRecsToSet.setManufactureritemnumber(mainAttributeval);	
				}

				if (templateAdditional.equals("UPC Code"))
				{
					canon008ProjItemRecsToSet.setUpccode(mainAttributeval);	
				}
				if (templateAdditional.equals("Long Description"))
				{
					canon008ProjItemRecsToSet.setLongdescription(mainAttributeval);	
				}
				if (templateAdditional.equals("Item Type"))
				{
					canon008ProjItemRecsToSet.setItemtype(mainAttributeval);	
				}
				if (templateAdditional.equals("Merchandise Type"))
				{
					canon008ProjItemRecsToSet.setMerchandisetype(mainAttributeval);	
				}
				if (templateAdditional.equals("Item Classification"))
				{
					canon008ProjItemRecsToSet.setItemclassfication(mainAttributeval);	
				}
				if (templateAdditional.equals("Product Code"))
				{
					canon008ProjItemRecsToSet.setProductcode(mainAttributeval);	
				}
				if (templateAdditional.equals("Marketing Model"))
				{
					canon008ProjItemRecsToSet.setMarketingmodel(mainAttributeval);	
				}
				if (templateAdditional.equals("Marketing Segment"))
				{
					canon008ProjItemRecsToSet.setMarketingsegment(mainAttributeval);	
				}
				if (templateAdditional.equals("Commissions Group"))
				{
					canon008ProjItemRecsToSet.setCommissionsgroup(mainAttributeval);	
				}
				if (templateAdditional.equals("UOM Class"))
				{
					canon008ProjItemRecsToSet.setUomclass(mainAttributeval);	
				}
				if (templateAdditional.equals("UOM"))
				{
					canon008ProjItemRecsToSet.setUom(mainAttributeval);	
				}
				if (templateAdditional.equals("Standard Cost"))
				{
					canon008ProjItemRecsToSet.setStandardcost(mainAttributeval);	
				}
				if (templateAdditional.equals("Asset Recovery Cost"))
				{
					canon008ProjItemRecsToSet.setAssetrecoverycost(mainAttributeval);	
				}
				if (templateAdditional.equals("Product Level1"))
				{
					canon008ProjItemRecsToSet.setProductlevel1(mainAttributeval);	
				}
				if (templateAdditional.equals("Product Level2"))
				{
					canon008ProjItemRecsToSet.setProductlevel2(mainAttributeval);	
				}
				if (templateAdditional.equals("Product Level3"))
				{
					canon008ProjItemRecsToSet.setProductlevel3(mainAttributeval);	
				}
				if (templateAdditional.equals("Product Level4"))
				{
					canon008ProjItemRecsToSet.setProductlevel4(mainAttributeval);	
				}
				if (templateAdditional.equals("Product Level5"))
				{
					canon008ProjItemRecsToSet.setProductlevel5(mainAttributeval);	
				}
				if (templateAdditional.equals("Material Group1"))
				{
					canon008ProjItemRecsToSet.setMaterialgroup1(mainAttributeval);	
				}
				if (templateAdditional.equals("Material Group2"))
				{
					canon008ProjItemRecsToSet.setMaterialgroup2(mainAttributeval);	
				}
				if (templateAdditional.equals("Material Group3"))
				{
					canon008ProjItemRecsToSet.setMaterialgroup3(mainAttributeval);	
				}

				if (templateAdditional.equals("Inventory Trackable"))
				{
					canon008ProjItemRecsToSet.setInventorytrackable(mainAttributeval);	
				}
				
				if (templateAdditional.equals("Return Warehouse"))
				{
					canon008ProjItemRecsToSet.setReturnwarehouse(mainAttributeval);	
				}

				if (templateAdditional.equals("Return Sub Warehouse"))
				{
					canon008ProjItemRecsToSet.setReturnsubwarehouse(mainAttributeval);	
				}
				if (templateAdditional.equals("Return Controlled"))
				{
					canon008ProjItemRecsToSet.setReturncontrolled(mainAttributeval);	
				}
				if (templateAdditional.equals("Return Control Type"))
				{
					canon008ProjItemRecsToSet.setReturncontroltype(mainAttributeval);	
				}
				
				if (templateAdditional.equals("Return Vendor"))
				{
					canon008ProjItemRecsToSet.setReturnvendor(mainAttributeval);	
				}

				if (templateAdditional.equals("Return Vendor Site"))
				{
					canon008ProjItemRecsToSet.setReturnvendorsite(mainAttributeval);	
				}
				
				if (templateAdditional.equals("Item Billing Type"))
				{
					canon008ProjItemRecsToSet.setItembillingtype(mainAttributeval);	
				}

				if (templateAdditional.equals("Requires Config Center"))
				{
					canon008ProjItemRecsToSet.setConfigurationflag(mainAttributeval);	
				}
				
				if (templateAdditional.equals("Service Model"))
				{
					canon008ProjItemRecsToSet.setServicemodel(mainAttributeval);	
				}

				if (templateAdditional.equals("Purchase Price"))
				{
					canon008ProjItemRecsToSet.setPurchasePrice(mainAttributeval);	
				}
				
				if (templateAdditional.equals("Revenue"))
				{
					 canon008ProjItemRecsToSet.setRevenue(mainAttributeval);	
				}
				if (templateAdditional.equals("Cost Of Goods"))
				{
					 canon008ProjItemRecsToSet.setCostofgoods(mainAttributeval);	
				}
				if (templateAdditional.equals("Expense"))
				{
					 canon008ProjItemRecsToSet.setExpense(mainAttributeval);	
				}
				if (templateAdditional.equals("Accrual"))
				{
					 canon008ProjItemRecsToSet.setAccrual(mainAttributeval);	
				}
				if (templateAdditional.equals("Owning Division"))
				{
					 canon008ProjItemRecsToSet.setOwningdivision(mainAttributeval);	
				}
				if (templateAdditional.equals("Coverage Base Type"))
				{
					 canon008ProjItemRecsToSet.setCoveragebasetype(mainAttributeval);	
				}
				if (templateAdditional.equals("Coverage Type"))
				{
					 canon008ProjItemRecsToSet.setCoveragetype(mainAttributeval);	
				}
				if (templateAdditional.equals("Service Allocation Type"))
				{
					 canon008ProjItemRecsToSet.setServiceallocationtype(mainAttributeval);	
				}
				if (templateAdditional.equals("TC Option"))
				{
					 canon008ProjItemRecsToSet.setTcoption(mainAttributeval);	
				}
				if (templateAdditional.equals("TC Option Value"))
				{
					 canon008ProjItemRecsToSet.setTcoptionvalue(mainAttributeval);	
				}
				if (templateAdditional.equals("Weight (Pounds)"))
				{
					 canon008ProjItemRecsToSet.setWeight(mainAttributeval);	
				}
				
				if (templateAdditional.equals("Length (Inches)"))
				{
					 canon008ProjItemRecsToSet.setItemlength(mainAttributeval);	
				}
				if (templateAdditional.equals("Width (Inches)"))
				{
					 canon008ProjItemRecsToSet.setItemdepth(mainAttributeval);	
				}
				
				if (templateAdditional.equals("Height (Inches)"))
				{
					 canon008ProjItemRecsToSet.setHeight(mainAttributeval);	
				}
				if (templateAdditional.equals("Part Type"))
				{
					 canon008ProjItemRecsToSet.setParttype(mainAttributeval);	
				}
				if (templateAdditional.equals("Parts Dropship Disabled"))
				{
					 canon008ProjItemRecsToSet.setPartsdropshipdisabled(mainAttributeval);	
				}
				if (templateAdditional.equals("Part Yield - Outputs"))
				{
					 canon008ProjItemRecsToSet.setPartyieldoutputs(mainAttributeval);	
				}
				if (templateAdditional.equals("Part Yield - Days"))
				{
					 canon008ProjItemRecsToSet.setPartyielddays(mainAttributeval);	
				}
				if (templateAdditional.equals("Parts Survey Required"))
				{
					 canon008ProjItemRecsToSet.setPartssurveyrequired(mainAttributeval);	
				}
				if (templateAdditional.equals("Warranty"))
				{
					 canon008ProjItemRecsToSet.setWarranty(mainAttributeval);	
				}
				if (templateAdditional.equals("Warranty Period (Days)"))
				{
					 canon008ProjItemRecsToSet.setWarrantyperiod(mainAttributeval);	
				}
				if (templateAdditional.equals("Imageware Remote Enabled"))
				{
					 canon008ProjItemRecsToSet.setImagewareremoteenabled(mainAttributeval);	
				}
				if (templateAdditional.equals("Imageware Remote Model"))
				{
					 canon008ProjItemRecsToSet.setImagewareremotemodel(mainAttributeval);	
				}
				if (templateAdditional.equals("Imageware Remote Item"))
				{
					 canon008ProjItemRecsToSet.setImagewareremoteitem(mainAttributeval);	
				}
				if (templateAdditional.equals("Metered Machine"))
				{
					 canon008ProjItemRecsToSet.setMeteredmachine(mainAttributeval);	
				}
				if (templateAdditional.equals("Installbase Controlled"))
				{
					 canon008ProjItemRecsToSet.setInstallbasecontrolled(mainAttributeval);	
				}
				if (templateAdditional.equals("Service Call Enabled"))
				{
					 canon008ProjItemRecsToSet.setServicecallenabled(mainAttributeval);	
				}
				if (templateAdditional.equals("Software Category"))
				{
					 canon008ProjItemRecsToSet.setSoftwarecategory(mainAttributeval);	
				}
				if (templateAdditional.equals("Software Version"))
				{
					 canon008ProjItemRecsToSet.setSoftwareversion(mainAttributeval);	
				}
				if (templateAdditional.equals("Software Product Category"))
				{
					 canon008ProjItemRecsToSet.setSoftwareproductcategory(mainAttributeval);	
				}
				if (templateAdditional.equals("License Control"))
				{
					 canon008ProjItemRecsToSet.setLicensecontrol(mainAttributeval);	
				}
				if (templateAdditional.equals("Elan Controlled"))
				{
					 canon008ProjItemRecsToSet.setElancontrolled(mainAttributeval);	
				}
				if (templateAdditional.equals("Seats From"))
				{
					 canon008ProjItemRecsToSet.setSeatsfrom(mainAttributeval);	
				}
				if (templateAdditional.equals("Seats To"))
				{
					 canon008ProjItemRecsToSet.setSeatsto(mainAttributeval);	
				}
				if (templateAdditional.equals("Fixed No of Seats"))
				{
					 canon008ProjItemRecsToSet.setFixednoofseats(mainAttributeval);	
				}
				if (templateAdditional.equals("Software Maintenance Control"))
				{
					 canon008ProjItemRecsToSet.setSoftwaremaintenancecontrol(mainAttributeval);	
				}
				if (templateAdditional.equals("Assurance Points Per License"))
				{
					 canon008ProjItemRecsToSet.setAssurancepointsperlicense(mainAttributeval);	
				}
				if (templateAdditional.equals("Assurance Points Fixed Per Ordered Qty"))
				{
					 canon008ProjItemRecsToSet.setAssuranceptsfixedperordqty(mainAttributeval);	
				}				
				if (templateAdditional.equals("Bundled Maintenance Item"))
				{
					 canon008ProjItemRecsToSet.setBundledmaintenanceitem(mainAttributeval);	
				}
				if (templateAdditional.equals("Maintenance - POP Available"))
				{
					 canon008ProjItemRecsToSet.setMaintenancepopavaliable(mainAttributeval);	
				}
				if (templateAdditional.equals("Extended Maintenance - POP Available"))
				{
					 canon008ProjItemRecsToSet.setExtendedmaintpopavailable(mainAttributeval);	
				}
				if (templateAdditional.equals("Maintenance Item Type"))
				{
					 canon008ProjItemRecsToSet.setMaintenanceitemtype(mainAttributeval);	
				}
				if (templateAdditional.equals("Maintenance Item Term"))
				{
					 canon008ProjItemRecsToSet.setMaintenanceitemterm(mainAttributeval);	
				}
				if (templateAdditional.equals("Assurance Points Max"))
				{
					 canon008ProjItemRecsToSet.setAssurancepointsmax(mainAttributeval);	
				}
				if (templateAdditional.equals("Assurance Points Min"))
				{
					 canon008ProjItemRecsToSet.setAssurancepointmin(mainAttributeval);	
				}
				if (templateAdditional.equals("Supply OEM Manufacturer"))
				{
					 canon008ProjItemRecsToSet.setSupplyoemmanufactuer(mainAttributeval);	
				}
				if (templateAdditional.equals("Supply OEM Code"))
				{
					 canon008ProjItemRecsToSet.setSupplyoemcode(mainAttributeval);	
				}
				if (templateAdditional.equals("Supply Type"))
				{
					 canon008ProjItemRecsToSet.setSupplytype(mainAttributeval);	
				}
				if (templateAdditional.equals("Supply Color"))
				{
					 canon008ProjItemRecsToSet.setSupplycolor(mainAttributeval);	
				}
				if (templateAdditional.equals("Supply Yield"))
				{
					 canon008ProjItemRecsToSet.setSupplyyield(mainAttributeval);	
				}
				if (templateAdditional.equals("Supply Yield Type"))
				{
					 canon008ProjItemRecsToSet.setSupplyyieldtype(mainAttributeval);	
				}
				if (templateAdditional.equals("Supply Yield UOM"))
				{
					 canon008ProjItemRecsToSet.setSupplyyielduom(mainAttributeval);	
				}
				if (templateAdditional.equals("Private Label Flag"))
				{
					 canon008ProjItemRecsToSet.setPrivatelabelflag(mainAttributeval);	
				}
				if (templateAdditional.equals("Third Party"))
				{
					 canon008ProjItemRecsToSet.setThirdparty(mainAttributeval);	
				}
				if (templateAdditional.equals("Tariff Code"))
				{
					 canon008ProjItemRecsToSet.setTariffcode(mainAttributeval);	
				}
				if (templateAdditional.equals("Freight Class Code"))
				{
					 canon008ProjItemRecsToSet.setFreightclasscode(mainAttributeval);	
				}
				if (templateAdditional.equals("Intangible Mdse Type"))
				{
					 canon008ProjItemRecsToSet.setIntangiblemdsetype(mainAttributeval);	
				}
				
				if (templateAdditional.equals("Invoiceable"))
				{
					 canon008ProjItemRecsToSet.setInvoiceable(mainAttributeval);	
				}
				if (templateAdditional.equals("Accounting Rules"))
				{
					 canon008ProjItemRecsToSet.setAccountingrules(mainAttributeval);	
				}
				if (templateAdditional.equals("Invoicing Rules"))
				{
					 canon008ProjItemRecsToSet.setInvoicingrules(mainAttributeval);	
				}
				if (templateAdditional.equals("Tax Code"))
				{
					 canon008ProjItemRecsToSet.setTaxcode(mainAttributeval);	
				}
				if (templateAdditional.equals("Safety - Hazardous Material"))
				{
					 canon008ProjItemRecsToSet.setSafetyhazardousmaterial(mainAttributeval);	
				}
				if (templateAdditional.equals("Safety - Hazardous Number"))
				{
					 canon008ProjItemRecsToSet.setSafetyhazardousnumber(mainAttributeval);	
				}
				if (templateAdditional.equals("Safety - Hazardous Class"))
				{
					 canon008ProjItemRecsToSet.setSafetyhazardousclass(mainAttributeval);	
				}
				if (templateAdditional.equals("Safety - Hazardous Shipping Label"))
				{
					 canon008ProjItemRecsToSet.setSafetyhazardousshiplabel(mainAttributeval);	
				}
				if (templateAdditional.equals("Safety - Hazardous ID"))
				{
					 canon008ProjItemRecsToSet.setSafetyhazardousid(mainAttributeval);	
				}
				if (templateAdditional.equals("Manufactured Country"))
				{
					 canon008ProjItemRecsToSet.setManufacturedcountry(mainAttributeval);	
				}
				if (templateAdditional.equals("Assembled Country"))
				{
					 canon008ProjItemRecsToSet.setAssembledcountry(mainAttributeval);	
				}
				if (templateAdditional.equals("NMFC Class"))
				{
					 canon008ProjItemRecsToSet.setNfmcclass(mainAttributeval);	
				}
				if (templateAdditional.equals("Costed"))
				{
					 canon008ProjItemRecsToSet.setCosted(mainAttributeval);	
				}
				if (templateAdditional.equals("RMA Allowed"))
				{
					 canon008ProjItemRecsToSet.setRmaallowed(mainAttributeval);	
				}
				if (templateAdditional.equals("RMA Inspection Required"))
				{
					 canon008ProjItemRecsToSet.setRmainspectionrequired(mainAttributeval);	
				}
				if (templateAdditional.equals("Default Source Warehouse"))
				{
					 canon008ProjItemRecsToSet.setDefaultsourcewarehouse(mainAttributeval);	
				}
				if (templateAdditional.equals("Default Source Sub Warehouse"))
				{
					 canon008ProjItemRecsToSet.setDefaultsourcesubwarehouse(mainAttributeval);	
				}
				if (templateAdditional.equals("Serial Control"))
				{
					 canon008ProjItemRecsToSet.setSerialcontrol(mainAttributeval);	
				}
				if (templateAdditional.equals("Serial From"))
				{
					 canon008ProjItemRecsToSet.setSerialfrom(mainAttributeval);	
				}
				if (templateAdditional.equals("Serial To"))
				{
					 canon008ProjItemRecsToSet.setSerialto(mainAttributeval);	
				}
				if (templateAdditional.equals("Main Engine"))
				{
					 canon008ProjItemRecsToSet.setMainengine(mainAttributeval);	
				}
				if (templateAdditional.equals("Criticality"))
				{
					 canon008ProjItemRecsToSet.setCriticality(mainAttributeval);	
				}
				if (templateAdditional.equals("Reman Available"))
				{
					 canon008ProjItemRecsToSet.setRemanavailable(mainAttributeval);	
				}
				if (templateAdditional.equals("SOW Required"))
				{
					 canon008ProjItemRecsToSet.setSowrequired(mainAttributeval);	
				}
				if (templateAdditional.equals("Customer Orderable"))
				{
					 canon008ProjItemRecsToSet.setCustomerordereable(mainAttributeval);	
				}
				if (templateAdditional.equals("Minimum Order Quantity"))
				{
					 canon008ProjItemRecsToSet.setMinimumorderquantity(mainAttributeval);	
				}
				if (templateAdditional.equals("Maximum Order Quantity"))
				{
					 canon008ProjItemRecsToSet.setMaximumorderquantity(mainAttributeval);	
				}
				if (templateAdditional.equals("Order Increments"))
				{
					 canon008ProjItemRecsToSet.setOrderincrements(mainAttributeval);	
				}
				if (templateAdditional.equals("Remanufactured Item Exists"))
				{
					 canon008ProjItemRecsToSet.setRemanufactureditemexists(mainAttributeval);	
				}

				if (templateAdditional.equals("Supplier"))
				{
					 canon008ProjItemRecsToSet.setSupplier(mainAttributeval);	
				}
				if (templateAdditional.equals("Supplier Item"))
				{
					 canon008ProjItemRecsToSet.setSupplierItem(mainAttributeval);	
				}
				if (templateAdditional.equals("Supplier Site"))
				{
					 canon008ProjItemRecsToSet.setSupplierSite(mainAttributeval);	
				}
				if (templateAdditional.equals("Superseded By"))
				{
					 canon008ProjItemRecsToSet.setSupersededBy(mainAttributeval);	
				}			
				if (templateAdditional.equals("Area of Paper (Square foot)"))
				{
					 canon008ProjItemRecsToSet.setAreaofpaper(mainAttributeval);	
				}
				if (templateAdditional.equals("Planning Group"))
				{
					 canon008ProjItemRecsToSet.setPlanninggroup(mainAttributeval);	
				}

				if (templateAdditional.equals("Hard Allocation Type"))
				{
					 canon008ProjItemRecsToSet.setHardallocationtype(mainAttributeval);	
				}
				if (templateAdditional.equals("Default Source Type"))
				{
					 canon008ProjItemRecsToSet.setDefaultsourcetype(mainAttributeval);	
				}
				if (templateAdditional.equals("Easy PAC I"))
				{
					 canon008ProjItemRecsToSet.setEasypacki(mainAttributeval);	
				}
			
				if (templateAdditional.equals("MSRP"))
				{
					 canon008ProjItemRecsToSet.setMsrp(mainAttributeval);	
				}
				if (templateAdditional.equals("UnBoxed Weight"))
				{
					 canon008ProjItemRecsToSet.setUnboxedweight(mainAttributeval);	
				}
				if (templateAdditional.equals("UnBoxed Weight UOM"))
				{
					 canon008ProjItemRecsToSet.setUnboxedweightuom(mainAttributeval);	
				}
				if (templateAdditional.equals("UnBoxed Length"))
				{
					 canon008ProjItemRecsToSet.setUnboxedlength(mainAttributeval);	
				}
				if (templateAdditional.equals("UnBoxed Length UOM"))
				{
					 canon008ProjItemRecsToSet.setUnboxedlengthuom(mainAttributeval);	
				}
				if (templateAdditional.equals("UnBoxed Width"))
				{
					 canon008ProjItemRecsToSet.setUnboxedwidth(mainAttributeval);	
				}
				if (templateAdditional.equals("UnBoxed Width UOM"))
				{
					 canon008ProjItemRecsToSet.setUnboxedwidthuom(mainAttributeval);	
				}
				if (templateAdditional.equals("UnBoxed Height"))
				{
					 canon008ProjItemRecsToSet.setUnboxedheight(mainAttributeval);	
				}
				if (templateAdditional.equals("UnBoxed Height UOM"))
				{
					 canon008ProjItemRecsToSet.setUnboxedheightuom(mainAttributeval);	
				}
				if (templateAdditional.equals("Lead Time"))
				{
					 canon008ProjItemRecsToSet.setLeadTime(mainAttributeval);	
				}
				if (templateAdditional.equals("Internal Item"))
				{
					 canon008ProjItemRecsToSet.setInternalItem(mainAttributeval);	
				}
				
 			} 
 			canon008ProjItemRecsToSet.setCreatedby(loginUserID);
 			canon008ProjItemRecsToSet.setLastupdateby(loginUserID);
			canon008ItemRecsList.add(canon008ProjItemRecsToSet);
	
		}
		}
		System.out.println("CanonE008ItemWorkbenchProcess canon008ItemRecsList size:"+canon008ItemRecsList.size());
		if (strAction.toUpperCase().equals("SAVE"))
			result= CanonE008ItemProcessDAO.saveProjectItems(itemProjectRec, canon008ItemRecsList);

		if (strAction.toUpperCase().equals("UPLOAD"))
			result= CanonE008ItemProcessDAO.uploadProjectItems(itemProjectRec, canon008ItemRecsList);
		
		if (strAction.toUpperCase().equals("VALIDATEUPLOAD"))
		{
			result=CanonE008ItemProcessDAO.validateUploaditems(strprojectNo,canon008ItemRecsList,loginUserID);
			checkErrors(result, 1,2);
	        String errors=(String)CanonE008ItemProcessUtil.first(result);
	        errFlag=(String)CanonE008ItemProcessUtil.second(result);
	        errMsg=(String)CanonE008ItemProcessUtil.third(result);
	        
	        odata.append(errors+ "@@@"+ errFlag+"@@@"+errMsg); 
	
		}
		System.out.println("End CanonE008ItemWorkbenchProcess: 111" );
		if (!strAction.toUpperCase().equals("VALIDATEUPLOAD"))
		{
			System.out.println("End CanonE008ItemWorkbenchProcess: 111A" );	
			//checkErrors(result, 1,2);
			System.out.println("End CanonE008ItemWorkbenchProcess: 222A" );	
	        BigDecimal p_project_number=(BigDecimal)CanonE008ItemProcessUtil.first(result);
	        errFlag=(String)CanonE008ItemProcessUtil.second(result);
	        errMsg=(String)CanonE008ItemProcessUtil.third(result);
	        
	        System.out.println("End CanonE008ItemWorkbenchProcess: p_project_number" + p_project_number );
	        System.out.println("End CanonE008ItemWorkbenchProcess: p_project_number" + errFlag );
			//errFlag="S";
			//errMsg="Success";
			System.out.println("strprojectNo " +strprojectNo);
			
			if (strprojectNo==null) 
				strnew="new";
			
			System.out.println("strnew " +strnew);
			
			strReturnmessg = p_project_number +"@@@"+ errFlag+"@@@"+errMsg+"@@@"+template_id+"@@@"+strnew+"@@@";
	
	  	    System.out.println("End CanonE008ItemWorkbenchProcess: " + strReturnmessg);				
	  	    odata.append(strReturnmessg);
		}
		
	 System.out.println("End CanonE008ItemWorkbenchProcess: 222" );
 	 response.getWriter().write(odata.toString()); 	
}

if(((strAction).toUpperCase()).equals("SUBMIT_PROJECT"))
{

	System.out.println("CanonE008ItemWorkbenchProcess: Inside SUBMIT_PROJECT " + strprojectNo );
	String emailSubject="";
	String emailSubInit ="" ;	

	result=CanonE008ItemProcessDAO.startapprovalprocess(strprojectNo);
    checkErrors(result, 0,1);
    errFlag=(String)CanonE008ItemProcessUtil.first(result);
    System.out.println("CanonE008ItemWorkbenchProcess: SUBMIT_PROJECT errFlag " + errFlag);
    if (errFlag.equals("S"))
    {	
    	
		  System.out.println("CanonE008ItemWorkbenchProcess: before sending mails" );

		  try
	        {
				emailSubInit = CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_EMAIL_SUBJECT_INIT");
				System.out.println("CanonE008ItemWorkbenchProcess: SUBMIT emailSubInit " + emailSubInit);
				if(emailSubInit.equals("null"))
					emailSubInit = "";
	        }
	      catch(NullPointerException e)
	        {
	            System.out.print("CanonE008ItemWorkbenchProcess: SUBMIT emailSubInit NullPointerException");
	            emailSubInit = "";
	        }
		  
			List approvaStatusList=(List)CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getcurrentapprovals(strprojectNo));
			
			System.out.println("before approvaStatusList "+ approvaStatusList.size());
			String[] TO_ADDRESS = new String[1];
			for(int i=0;approvaStatusList!=null && i<approvaStatusList.size();i++)
			{
		        CanonE008ItemProcessDAO.ApprovalStatusInfo status=(CanonE008ItemProcessDAO.ApprovalStatusInfo)approvaStatusList.get(i);
		        System.out.println("inside approvaStatusList "+ status.getApprovalStatus());
		        if(("PENDING".equalsIgnoreCase(status.getApprovalStatus())) && (("ENTERED".equalsIgnoreCase(strstatus)) || ("REJECTED".equalsIgnoreCase(strstatus))))	
		        {
		  
				  //emailList = (List) CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E008_MSTR"));
				  if ("E008_MERCH".equalsIgnoreCase(status.getApproverRole()))
				  { 
				  		emailList = (List) CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E008_MERCH"));
				  		emailSubject = emailSubInit + "Item Master Merchandise - Approval Required for Project "+ strprojectNo;
						  TO_ADDRESS = new String[emailList.size()];
						  for (int K = 0; K < emailList.size(); K++) 
						  {
									TO_ADDRESS[K]=(String)emailList.get(K);
									System.out.println("validate_project result "+emailList.get(K));	
						  }  // Added By MAdhusudan Duna
				  }
				  
				  if ("E008_PART".equalsIgnoreCase(status.getApproverRole()))
				  {	
					  emailList = (List) CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E008_PART"));
					  emailSubject = emailSubInit + "Item Master Parts - Approval Required for Project "+ strprojectNo;
						  TO_ADDRESS = new String[emailList.size()];
						  for (int K = 0; K < emailList.size(); K++) 
						  {
									TO_ADDRESS[K]=(String)emailList.get(K);
									System.out.println("validate_project result "+emailList.get(K));	
						  }  // Added By MAdhusudan Duna
				  }
				  
			     
		        }
			}
			
		  
		  //emailSubject = emailSubInit + "Item Master - Approval Required for Project "+ strprojectNo;     			
		  HtmlEmail email = CanonEmailUtil.createHtmlEmail();
		  email.setSubject(emailSubject)
				 .setMsg("Project has been submitted successfully")
		 		 .addTo(TO_ADDRESS); // Added By Madhusudan Duna
	
			String cid ="<table bgcolor=\"white\" width=\"75%\" border=\"1\" cellpadding=\"3\" cellspacing=\"1\">"
					+"<tr bgcolor=\"#003b4e\"><td align=\"left\"><font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
					+"<b>Project Number </b></td><td align=\"left\">"
					+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
					+"<b>Project Description </b></td><td align=\"left\">"
					+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
					+"<b>Project Submitter</b></td><td align=\"left\">"
					+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
					+"<b>Project Notes </b></td></tr>"
					+"<tr><td>"+strprojectNo+"</td><td>"+CanonE008ItemProcessUtil.nonNullify(strprojectDesc)+"</td><td>"+strreqname+"</td><td>"+strprojectNote+"</td></tr></table>"
					+"Please review and take appropriate action on the Project: <a href=" + "http://"+ request.getServerName()+ request.getContextPath()+"/e008/jsp/canonE008Project.jsp?project_number="+strprojectNo+"&roleId="+strrole
					+ ">Project Detail</a>";
			email.setHtmlMsg("<html>" + cid +"</html>");
			email.send();
			System.out.println("CanonE008ItemWorkbenchProcess: after EMAIL SENDING " );
    	}
    
		strReturnmessg = strprojectNo +"@@@"+ errFlag+"@@@"+errMsg+"@@@"; 
		odata.append(strReturnmessg); 
	 	response.getWriter().write(odata.toString());
	 	
	 	System.out.println("CanonE008ItemWorkbenchProcess: after SUBMIT_PROJECT " );
}


if(((strAction).toUpperCase()).equals("APPROVE_PROJECT"))
{
		String p_comments = request.getParameter("approve_comments"); 
		result = CanonE008ItemProcessDAO.notificationAction( strprojectNo.toString(), strUserid, p_comments, roleId, "APPROVED");
		errFlag=(String)CanonE008ItemProcessUtil.first(result);
		//checkErrors(result, 0, 1); 
		System.out.println("CanonE008ItemWorkbenchProcess: APPROVE_PROJECT errFlag " + errFlag);
	    if (errFlag.equals("S"))
    		{
				emailList = (List) CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E008_SVC"));
				String[] TO_SVC_ADDRESS = new String[emailList.size()];
				for (int i = 0; i < emailList.size(); i++) {
						
					TO_SVC_ADDRESS[i]=(String)emailList.get(i);
					System.out.println("Email Address project SVC "+TO_SVC_ADDRESS[i]);	
				}  
				
				emailList = (List) CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E008_SALES"));
				String[] TO_SALES_ADDRESS = new String[emailList.size()];
				for (int i = 0; i < emailList.size(); i++) {
						
					TO_SALES_ADDRESS[i]=(String)emailList.get(i);
					System.out.println("Email Address project SALES "+TO_SALES_ADDRESS[i]);	
				}  
				
				emailList = (List) CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E008_MKT"));
				String[] TO_MKT_ADDRESS = new String[emailList.size()];
				for (int i = 0; i < emailList.size(); i++) {
						
					TO_MKT_ADDRESS[i]=(String)emailList.get(i);
					System.out.println("Email Address project MKT "+TO_MKT_ADDRESS[i]);	
				}  
		
				emailList = (List) CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E008_ACCT"));
				String[] TO_ACCT_ADDRESS = new String[emailList.size()];
				for (int i = 0; i < emailList.size(); i++) {
		
					TO_ACCT_ADDRESS[i]=(String)emailList.get(i);
					System.out.println("Email Address project ACCT  "+TO_ACCT_ADDRESS[i]);	
				}  

				emailList = (List) CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E008_PRC"));
				String[] TO_PRC_ADDRESS = new String[emailList.size()];
				for (int i = 0; i < emailList.size(); i++) {
		
					TO_PRC_ADDRESS[i]=(String)emailList.get(i);
					System.out.println("Email Address project E008_PRC  "+TO_PRC_ADDRESS[i]);	
				}  

				emailList = (List) CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E008_SOL"));
				String[] TO_SOL_ADDRESS = new String[emailList.size()];
				for (int i = 0; i < emailList.size(); i++) {
		
					TO_SOL_ADDRESS[i]=(String)emailList.get(i);
					System.out.println("Email Address project E008_SOL  "+TO_SOL_ADDRESS[i]);	
				}  

				emailList = (List) CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getRoleEmailAddress("E008_SUP"));
				String[] TO_SUP_ADDRESS = new String[emailList.size()];
				for (int i = 0; i < emailList.size(); i++) {
		
					TO_SUP_ADDRESS[i]=(String)emailList.get(i);
					System.out.println("Email Address project E008_SUP  "+TO_SUP_ADDRESS[i]);	
				}  
				
				//String[] TO_SVC_ADDRESS={"mduna_consultant@cusa.canon.com","salu_consultant@cusa.canon.com"};
				//String[] TO_SALES_ADDRESS={"mduna_consultant@cusa.canon.com","salu_consultant@cusa.canon.com"};
				//String[] TO_MKT_ADDRESS={"mduna_consultant@cusa.canon.com","salu_consultant@cusa.canon.com"};
				//String[] TO_ACCT_ADDRESS={"mduna_consultant@cusa.canon.com","salu_consultant@cusa.canon.com"};
				
				List approvaStatusList=(List)CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getcurrentapprovals(strprojectNo));
				
				System.out.println("after approvaStatusList ");
				
				Map <String,String[]> itemEmailMap=new HashMap<String,String[]>();
				itemEmailMap.put("E008_SVC", TO_SVC_ADDRESS);
				itemEmailMap.put("E008_SALES", TO_SALES_ADDRESS);
				itemEmailMap.put("E008_MKT", TO_MKT_ADDRESS);
				itemEmailMap.put("E008_ACCT", TO_ACCT_ADDRESS);
				itemEmailMap.put("E008_PRC", TO_PRC_ADDRESS);
				itemEmailMap.put("E008_SOL", TO_SOL_ADDRESS);
				itemEmailMap.put("E008_SUP", TO_SUP_ADDRESS);
				
				System.out.println("before approvaStatusList "+ approvaStatusList.size());
				
				for(int i=0;approvaStatusList!=null && i<approvaStatusList.size();i++)
				{
			        CanonE008ItemProcessDAO.ApprovalStatusInfo status=(CanonE008ItemProcessDAO.ApprovalStatusInfo)approvaStatusList.get(i);

			        System.out.println("inside approvaStatusList "+ status.getApprovalStatus());
			        System.out.println("inside Email List "+ itemEmailMap.get(status.getApproverRole()));
			        
			        if(("PENDING".equalsIgnoreCase(status.getApprovalStatus())) && ("ITEM MASTER REVIEW".equalsIgnoreCase(strstatus))
			        	&&  (itemEmailMap.get(status.getApproverRole()).length>0) )	
			        {
			        	 System.out.println("inside if loop approvaStatusList "+ status.getApproverRole());
			        	 HtmlEmail email = CanonEmailUtil.createHtmlEmail();
			        	 
			        	String emailSubject="";
						String emailSubInit ="" ;
						try
				        {
							emailSubInit = CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_EMAIL_SUBJECT_INIT");
							System.out.println("CanonE008ItemWorkbenchProcess: APPROVE emailSubInit " + emailSubInit);
							if(emailSubInit.equals("null"))
								emailSubInit = "";
				        }
				        catch(NullPointerException e)
				        {
				            System.out.print("CanonE008ItemWorkbenchProcess: APPROVE emailSubInit NullPointerException");
				            emailSubInit = "";
				        }
			        	 
			            /*  if ("E008_MSTR".equalsIgnoreCase(status.getApproverRole()))
			 				emailSubject = emailSubInit + "Item Master - Approval Required for Project "+ strprojectNo; */
			 			 if ("E008_ACCT".equalsIgnoreCase(status.getApproverRole()))
			 				emailSubject = emailSubInit + "Accounting - Approval Required for Project "+ strprojectNo;
			 			 else if ("E008_MKT".equalsIgnoreCase(status.getApproverRole()))
			 				emailSubject = emailSubInit + "Marketing - Approval Required for Project "	+ strprojectNo;
			 			 else if ("E008_SALES".equalsIgnoreCase(status.getApproverRole()))
			 				emailSubject = emailSubInit + "Incentive Comp - Approval Required for Project "+ strprojectNo;
			 			 else if ("E008_SVC".equalsIgnoreCase(status.getApproverRole()))
			 				emailSubject = emailSubInit + "Service - Approval Required for Project "+ strprojectNo;
			 			 else if ("E008_PRC".equalsIgnoreCase(status.getApproverRole()))
			 				emailSubject = emailSubInit + "Pricing - Approval Required for Project "+ strprojectNo;
			 			 else if ("E008_SOL".equalsIgnoreCase(status.getApproverRole()))
				 				emailSubject = emailSubInit + "Solutions - Approval Required for Project "+ strprojectNo;
			 			 else if ("E008_SUP".equalsIgnoreCase(status.getApproverRole()))
				 				emailSubject = emailSubInit + "Supply - Approval Required for Project "+ strprojectNo;
			 						
			 				email.setSubject(emailSubject)
								.setMsg("Project has been submitted successfully")
								.addTo(itemEmailMap.get(status.getApproverRole()));
			 						
			 				String cid ="<table bgcolor=\"white\" width=\"75%\" border=\"1\" cellpadding=\"3\" cellspacing=\"1\">"
			 						+"<tr bgcolor=\"#003b4e\"><td align=\"left\"><font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
			 						+"<b>Project Number </b></td><td align=\"left\">"
			 						+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
			 						+"<b>Project Description </b></td><td align=\"left\">"
			 						+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
			 						+"<b>Project Submitter</b></td><td align=\"left\">"
			 						+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
			 						+"<b>Project Notes </b></td></tr>"
			 						+"<tr><td>"+strprojectNo+"</td><td>"+CanonE008ItemProcessUtil.nonNullify(strprojectDesc)+"</td><td>"+strreqname+"</td><td>"+strprojectNote+"</td></tr></table>"
			 						+"Please review and take appropriate action on the Project: <a href=" + "http://"+ request.getServerName()+ request.getContextPath()+"/e008/jsp/canonE008Project.jsp?project_number="+strprojectNo+"&roleId="+strrole
			 						+ ">Project Detail</a>";
			 				email.setHtmlMsg("<html>" + cid +"</html>");
			 				email.send();         						
			
			 				System.out.println("CanonE008ItemWorkbenchProcess: after EMAIL SENDING " );
			        } 				
 				
/*  				strReturnmessg = strprojectNo +"@@@"+ errFlag+"@@@"+errMsg+"@@@"; 
 				odata.append(strReturnmessg); 
 			 	response.getWriter().write(odata.toString());
 			 	
 			 	System.out.println("CanonE008ItemWorkbenchProcess: after APPROVE_PROJECT " ); 	 */			
 				
        	}
		}
		
		strReturnmessg = strprojectNo +"@@@"+ errFlag+"@@@"+errMsg+"@@@"; 
		odata.append(strReturnmessg); 
		response.getWriter().write(odata.toString());
		 	
		System.out.println("CanonE008ItemWorkbenchProcess: after APPROVE_PROJECT " + strReturnmessg ); 
}		
		 
if(((strAction).toUpperCase()).equals("REJECT_PROJECT")) 
{
		String p_comments = request.getParameter("reject_comments");
		result = CanonE008ItemProcessDAO.notificationAction( strprojectNo.toString(), strUserid, p_comments, roleId, "REJECTED");
		errFlag=(String)CanonE008ItemProcessUtil.first(result);

		System.out.println("CanonE008ItemWorkbenchProcess: REJECT_PROJECT errFlag " + errFlag);
		 if (errFlag.equals("S"))
	    {
                    
			emailList = (List) CanonE008ItemProcessUtil
					.first(CanonE008ItemProcessDAO.getRoleEmailAddress(strprojectNo.toString()));
			//List l = new ArrayList();
			String[] TO_REJECT_ADDRESS = new String[emailList.size()];
			for (int i = 0; i < emailList.size(); i++) {
					
				TO_REJECT_ADDRESS[i]=(String)emailList.get(i);
				System.out.println("validate_project result "+TO_REJECT_ADDRESS[i]);	
			}  
			
			
			Object []projectDetails = CanonE008ItemProcessDAO
					.getProjectItems(strprojectNo);
			if (projectDetails != null && projectDetails.length > 0) {
				CanonE008ItemProcessDAO.CanonE008ProjectRec p = (CanonE008ItemProcessDAO.CanonE008ProjectRec) CanonE008ItemProcessUtil
						.first(projectDetails);
				String p_project_created_by = p.getCreatedBy();
				HtmlEmail email = CanonEmailUtil.createHtmlEmail();
				String emailSubject = "";
				String emailSubInit ="" ;
				try
		        {
					emailSubInit = CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_EMAIL_SUBJECT_INIT");
					System.out.println("CanonE008ItemWorkbenchProcess: REJECT emailSubInit " + emailSubInit);
					if(emailSubInit.equals("null"))
						emailSubInit = "";
		        }
		        catch(NullPointerException e)
		        {
		            System.out.print("CanonE008ItemWorkbenchProcess: REJECT emailSubInit NullPointerException");
		            emailSubInit = "";
		        }
				
				System.out.println("CanonE008ItemWorkbenchProcess: REJECT1 emailSubInit " + emailSubInit);
				
				emailSubject= emailSubInit + "Project Rejected - "+ strprojectNo;
				
	   			String emailBody="The Project is Rejected with the following comments:"+p_comments;
				String cid ="<table bgcolor=\"white\" width=\"75%\" border=\"1\" cellpadding=\"3\" cellspacing=\"1\">"
					+"<tr bgcolor=\"#003b4e\"><td align=\"left\"><font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
					+"<b>Project Number </b></td><td align=\"left\">"
					+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
					+"<b>Project Description </b></td><td align=\"left\">"
					+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
					+"<b>Project Submitter</b></td><td align=\"left\">"
					+"<font color=\"#ffffff\" face=\"Arial, Helvetica, Geneva, sans-serif\">"
					+"<b>Project Notes </b></td></tr>"
					+"<tr><td>"+strprojectNo+"</td><td>"+CanonE008ItemProcessUtil.nonNullify(strprojectDesc)+"</td><td>"+strreqname+"</td><td>"+strprojectNote+"</td></tr></table>"
					+"Please review and take appropriate action on the Project: <a href=" + "http://"+ request.getServerName()+ request.getContextPath()+"/e008/jsp/canonE008Project.jsp?project_number="+strprojectNo+"&roleId="+strrole
					+ ">Project Detail</a>";
	   	
	   	email.setSubject(emailSubject)               	             

	   	.addTo(TO_REJECT_ADDRESS);
	   	email.setHtmlMsg(emailBody+"<html>" + cid +"</html>");
			email.send();   	
			projectDetails=null;
			}
		
	}
		 strReturnmessg = strprojectNo +"@@@"+ errFlag+"@@@"+errMsg+"@@@"; 
			odata.append(strReturnmessg); 
		 	response.getWriter().write(odata.toString());
		 	
		 	System.out.println("CanonE008ItemWorkbenchProcess: after REJECT_PROJECT " ); 			 
	}

if(((strAction).toUpperCase()).equals("SAVESEARCH")) {		
	 {			
		 System.out.println("Start CanonE008ItemProcess: SAVESEARCH");
		 
		 System.out.println("Start CanonE008ItemProcess: projectNo" + projectNo);
		 
		result= obj.savesearchcriteria(strprojectNo,strprojectName,strprojectType,strprojectDesc,
				strmasterProj,strProjectRequester,strstatus,"","",strUserid,strsavedname);

		System.out.println("Start CanonE008ItemProcess: After result" + projectNo);
		
		errFlag=(String)CanonE008ItemProcessUtil.first(result);
		errMsg=(String)CanonE008ItemProcessUtil.second(result);
		System.out.println("Start CanonE008ItemProcess: SAVESEARCH errFlag - "+errFlag); 
		strReturnmessg = errFlag+"@@@"+errMsg+"@@@";
		 
	}

	 System.out.println("End CanonE008TemplateProcess:");	 
	 odata.append(strReturnmessg); 
	 response.getWriter().write(odata.toString()); 
}

System.out.println("Before  GET SAVE SEARCH ");
if(((strAction).toUpperCase()).equals("GETSAVESEARCH")) {		
	 {			
		 System.out.println("Start CanonE008ItemProcess: GETSAVESEARCH");
		 
		 Object[] savedSearchValuesObjects= obj.getsavedsearchvalues(strsavedname,strUserid);

		 System.out.println("itemMainAttrValuesObjects[0]:::::"+savedSearchValuesObjects[0]);
		 errFlag=(String)savedSearchValuesObjects[1];
		 errMsg=(String)savedSearchValuesObjects[2];
		 strReturnmessg =savedSearchValuesObjects[0]+"@@"+ errFlag+"@@"+errMsg+"@@";		 
		 
		 System.out.println("Start CanonE008ItemProcess: GETSAVESEARCH errFlag - "+errFlag); 
		 
	}

	 System.out.println("End CanonE008ItemProcess:");	 
	 odata.append(strReturnmessg); 
	 response.getWriter().write(odata.toString()); 
}


System.out.println("Before  GET GETCOMPLIST " + strAction);
if(((strAction).toUpperCase()).equals("GETCOMPLIST")) {		
	 {			
		 System.out.println("Start CanonE008ItemProcess: GETCOMPLIST" + strcompList);
		 
		 Object[] savedSearchValuesObjects= obj.getComponentList(strcompList);

		 System.out.println("itemMainAttrValuesObjects[0]:::::"+savedSearchValuesObjects[0]);
		 errFlag=(String)savedSearchValuesObjects[1];
		 errMsg=(String)savedSearchValuesObjects[2];
		 strReturnmessg =savedSearchValuesObjects[0]+"@@"+ errFlag+"@@"+errMsg+"@@";		 
		 
		 System.out.println("Start CanonE008ItemProcess: GETCOMPLIST errFlag - "+errFlag); 
		 
	}

	 System.out.println("End CanonE008ItemProcess:");	 
	 odata.append(strReturnmessg); 
	 response.getWriter().write(odata.toString()); 
}



if(((strAction).toUpperCase()).equals("SAVEBOM")) 		
{	 		
	 System.out.println("Start CanonE008ItemWorkbenchProcess: SAVEBOM");
	 
    CanonE008ItemProcessDAO canonE008ItemProcessDAO=new CanonE008ItemProcessDAO();

    System.out.println("Start CanonE008ItemWorkbenchProcess: canon008bomItemRecsList()");
	List canon008bomItemRecsList=new ArrayList();
	int headerAttribute=0;
	for(int i=0;i<15;i++)
	{
		
		
		   CanonE008ItemProcessDAO.bomDetailsInfo bomitemProjectRec = canonE008ItemProcessDAO.new bomDetailsInfo();	 
		
			System.out.println("Start CanonE008ItemWorkbenchProcess: 102  "+ request.getParameter("ComponentId"+i));
			//if(!request.getParameter("ComponentId"+i).equals(""))
			if(!request.getParameter("selectCompId"+i).equals(""))	
			{   

								
				bomitemProjectRec.setEztableid("CANON_E008_BOM_ITEMS_T BL");
				bomitemProjectRec.setEzincompanycode("ABD");
				
				System.out.println("Start CanonE008ItemWorkbenchProcess: 100");
				String strPItemNumber;
				//strPItemNumber= request.getParameter("ComponentId"+i);
				strPItemNumber= request.getParameter("selectCompId"+i);
				System.out.println("Start CanonE008ItemWorkbenchProcess: 101  "+ strPItemNumber);
				bomitemProjectRec.setComponentitem(strPItemNumber);	
				
				System.out.println("Start CanonE008ItemWorkbenchProcess: 103 " + request.getParameter("bomItemid"));
				bomitemProjectRec.setSetitemid(new BigDecimal(request.getParameter("bomItemid")));	
				System.out.println("Start CanonE008ItemWorkbenchProcess: 104");
				
				if (!request.getParameter("bomInstructions").equals(""))
					bomitemProjectRec.setBominstructions(request.getParameter("bomInstructions"));
	
				System.out.println("Start CanonE008ItemWorkbenchProcess: 105"+ request.getParameter("bomInstructions").equals(""));
				
				if (!request.getParameter("bomQty"+i).equals(""))
					bomitemProjectRec.setQty(new BigDecimal(request.getParameter("bomQty"+i)));
				else	
					bomitemProjectRec.setQty(new BigDecimal("1"));
				
				System.out.println("Start CanonE008ItemWorkbenchProcess: 106" + request.getParameter("bomQty"+i));
				canon008bomItemRecsList.add(bomitemProjectRec);
			}
			

	}
	System.out.println("CanonE008ItemWorkbenchProcess canon008bomItemRecsList size:"+canon008bomItemRecsList.size());
	result= CanonE008ItemProcessDAO.savebomItems(canon008bomItemRecsList);
	System.out.println("End CanonE008ItemWorkbenchProcess: 103" );	
	//checkErrors(result, 1,2);
	System.out.println("End CanonE008ItemWorkbenchProcess: 104" );	
    errFlag="S"; //(String)CanonE008ItemProcessUtil.first(result);
    errMsg=(String)CanonE008ItemProcessUtil.second(result);
   
    System.out.println("End CanonE008ItemWorkbenchProcess: errFlag " + errFlag );
	//errFlag="S";
	//errMsg="Success";
	
	
	System.out.println("strnew " +strnew);
	
	strReturnmessg = strprojectNo + "@@@"+ errFlag+"@@@"+errMsg+"@@@"+template_id+"@@@"+strnew+"@@@";

	System.out.println("End CanonE008ItemWorkbenchProcess: " + strReturnmessg);				
	
 odata.append(strReturnmessg); 
 response.getWriter().write(odata.toString()); 	
}


%>

<%! 
static class ItemProcessException extends Exception{
    public ItemProcessException(String message, Throwable cause){
        super(message,cause);
    }
}

static void checkResult(Object [] result) throws ItemProcessException{
    if(result==null) {
        Exception cause=CanonE008ItemProcessDAO.getException();
        throw new ItemProcessException("Database exception.",cause);
    }
}

static void checkErrors(Object [] result,int error_flag_index,int error_message_index) throws ItemProcessException{
    checkResult(result);
    String error_flag=(String)result[error_flag_index];
    String error_message=(String)result[error_message_index];
    if( "E".equals(error_flag)){
        throw new ItemProcessException("Database error.", new Exception(error_message));
    }
}

%>