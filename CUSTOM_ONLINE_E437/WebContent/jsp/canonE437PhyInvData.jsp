<%@ page language="java" import="com.canon.apps.e437.*" %>
<%@ page language="java" import="java.math.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="oracle.apps.jtf.base.resources.*" %>
<%@ page import="java.net.URLEncoder" %>

<%--
 +===========================================================================+
 |                                                                           |
 +===========================================================================+
 |  FILENAME                                                                 |
 |    canonE437PhyInvData.jsp                                         		 |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |    Physical Inventory Data                                                |
 |                                                                           |
 |  NOTES                                                                    |
 |                                                                           |
 |  DEPENDENCIES                                                             |
 |                                                                           |
 |  HISTORY                                                                  |
 |                                                                           |
 |                                                                           |
 +===========================================================================+
--%>

<%
	response.setContentType("text/xml");          
	response.setHeader("Cache-Control", "no-cache"); 

	String errorFlag = null;
	String errorMsg = null;

	String strFormMode = request.getParameter("formMode");
	String strCallType = request.getParameter("callType");
	String strUserId = request.getParameter("userId");
	String strTechId = request.getParameter("techId");
	String strMode = request.getParameter("callMode");
	String strOrgId = request.getParameter("orgId");
	String strSubInv = request.getParameter("subInv");
	String strLocatorId = request.getParameter("locatorId");
	String strTechName = request.getParameter("techName");
	String strPhyInvId = request.getParameter("phyInvId");
	String strItemNum = request.getParameter("itemNum");
	String strItemId = request.getParameter("itemId");
	String strEmpId = request.getParameter("empId");
	String strTagId = request.getParameter("tagId");
	String strTagQty = request.getParameter("tagQty");
	String strPageNum = request.getParameter("pageNum");
	String strBeginItem = request.getParameter("beginItem");
	String strEndItem = request.getParameter("endItem");
	

	String strEmpIdRtn = null; 
	String strEmpUserRtn = null; 
	String strPhyInvRtnId = null; 
	String strPhyInvRtn = null;
	String strPendCntRtn = null;
	
	
	StringBuffer odata = new StringBuffer(100);
		

	if (strCallType.equalsIgnoreCase("TECHLOV")) {	
		
		CanonE437PhysicalInventory obj = new CanonE437PhysicalInventory();	
			
		ArrayList results = obj.getTechLOV(strUserId,strTechId,strMode,strFormMode);
		
		errorFlag = (String)results.get(0);
		errorMsg = (String)results.get(1);
			
		odata.append("<techLovData>"); 	
		odata.append("<errFlag>"+(errorFlag)+"</errFlag>"); 
		odata.append("<errMsg>"+(errorMsg)+"</errMsg>"); 
		
		if (!(errorFlag.equals("E"))) {
			ArrayList techList = new ArrayList();
			
			techList = (ArrayList)results.get(2);
			
			int cnt = techList.size();
						
			odata.append("<rcount>"+(techList.size())+"</rcount> ");

			for(int i = 0; i < cnt; i++) {
				CanonE410AttributeBean custObj  = (CanonE410AttributeBean)techList.get(i);
				odata.append("<row>");
				odata.append("<techId>"+(custObj.getAttribute1()==null ?"-":custObj.getAttribute1())+"</techId> ");
				odata.append("<techNum>"+(custObj.getAttribute2()==null ?"-":custObj.getAttribute2())+"</techNum> ");
				odata.append("<techName>"+(custObj.getAttribute3()==null ?"-":custObj.getAttribute3())+"</techName> ");
				odata.append("<techOrgId>"+(custObj.getAttribute4()==null ?"-":custObj.getAttribute4())+"</techOrgId> ");
				odata.append("</row>");
			}
		}	
		
		odata.append("</techLovData>");
	
	} else if (strCallType.equalsIgnoreCase("WHPHYINV")) {

		CanonE437PhysicalInventory obj = new CanonE437PhysicalInventory();	
			
		ArrayList results = obj.getWhPhyInvData(strOrgId,strUserId,strSubInv,strFormMode);
		
		errorFlag = (String)results.get(0);
		errorMsg = (String)results.get(1);

	
		odata.append("<whPhyInvData>"); 	
		odata.append("<errFlag>"+(errorFlag)+"</errFlag>"); 
		odata.append("<errMsg>"+(errorMsg)+"</errMsg>"); 
		
		if (!(errorFlag.equals("E"))) {
			strEmpIdRtn = (String)results.get(2); 
			strEmpUserRtn = (String)results.get(3); 
			strPhyInvRtnId = (String)results.get(4); 
			strPhyInvRtn = (String)results.get(5); 
			odata.append("<EmpId>"+(strEmpIdRtn)+"</EmpId>"); 
			odata.append("<EmpUser>"+(strEmpUserRtn)+"</EmpUser>"); 
			odata.append("<PhyInvId>"+(strPhyInvRtnId)+"</PhyInvId>"); 
			odata.append("<PhyInv>"+(strPhyInvRtn)+"</PhyInv>"); 
		}

		odata.append("</whPhyInvData>");
		
	} else if (strCallType.equalsIgnoreCase("TECHPHYINV")) {

		CanonE437PhysicalInventory obj = new CanonE437PhysicalInventory();	
			
		ArrayList results = obj.getTechPhyInvData(strOrgId,strUserId,strTechName,strFormMode);
		
		errorFlag = (String)results.get(0);
		errorMsg = (String)results.get(1);
	
		odata.append("<techPhyInvData>"); 	
		odata.append("<errFlag>"+(errorFlag)+"</errFlag>"); 
		odata.append("<errMsg>"+(errorMsg)+"</errMsg>"); 
		
		if (!(errorFlag.equals("E"))) {
			strEmpIdRtn = (String)results.get(2); 
			strEmpUserRtn = (String)results.get(3); 
			strPhyInvRtnId = (String)results.get(4); 
			strPhyInvRtn = (String)results.get(5); 
			odata.append("<EmpId>"+(strEmpIdRtn)+"</EmpId>"); 
			odata.append("<EmpUser>"+(strEmpUserRtn)+"</EmpUser>"); 
			odata.append("<PhyInvId>"+(strPhyInvRtnId)+"</PhyInvId>"); 
			odata.append("<PhyInv>"+(strPhyInvRtn)+"</PhyInv>"); 
		}

		odata.append("</techPhyInvData>");
	
	} else if (strCallType.equalsIgnoreCase("VALIDATEITEM")) {
	
		CanonE437PhysicalInventory obj = new CanonE437PhysicalInventory();	
			
		ArrayList results = obj.validateItem(strOrgId,strPhyInvId,strEmpId,strUserId,strItemNum,strFormMode);
		
		errorFlag = (String)results.get(0);
		errorMsg = (String)results.get(1);
			
		odata.append("<validateItemData>"); 	
		odata.append("<errFlag>"+(errorFlag)+"</errFlag>"); 
		odata.append("<errMsg>"+"<![CDATA["+(errorMsg)+"]]>"+"</errMsg>"); 
		
		if (errorFlag.equals("M")) {	//multiple records found for the item
			ArrayList itemList = new ArrayList();
			
			itemList = (ArrayList)results.get(2);
			
			int cnt = itemList.size();
						
			odata.append("<rcount>"+(itemList.size())+"</rcount> ");

			for(int i = 0; i < cnt; i++) {
				CanonE410AttributeBean custObj  = (CanonE410AttributeBean)itemList.get(i);
				odata.append("<row>");
				odata.append("<whouseId>"+(custObj.getAttribute1()==null ?"-":custObj.getAttribute1())+"</whouseId> ");
				odata.append("<phyInvId>"+(custObj.getAttribute2()==null ?"-":custObj.getAttribute2())+"</phyInvId> ");
				odata.append("<itemId>"+(custObj.getAttribute3()==null ?"-":custObj.getAttribute3())+"</itemId> ");
				odata.append("<itemNum>"+(custObj.getAttribute4()==null ?"-":custObj.getAttribute4())+"</itemNum> ");
				odata.append("<tagId>"+(custObj.getAttribute5()==null ?"-":custObj.getAttribute5())+"</tagId> ");
				odata.append("<tagNum>"+(custObj.getAttribute6()==null ?"-":custObj.getAttribute6())+"</tagNum> ");
				odata.append("<subInv>"+(custObj.getAttribute7()==null ?"-":custObj.getAttribute7())+"</subInv> ");
				odata.append("<locatorId>"+(custObj.getAttribute8()==null ?"-":custObj.getAttribute8())+"</locatorId> ");
				odata.append("<locator>"+(custObj.getAttribute9()==null ?"-":custObj.getAttribute9())+"</locator> ");
				odata.append("<tagQty>"+(custObj.getAttribute10()==null ?"-":custObj.getAttribute10())+"</tagQty> ");
				odata.append("<scannedEmp>"+(custObj.getAttribute11()==null ?"-":custObj.getAttribute11())+"</scannedEmp> ");
				odata.append("<rowId>"+(custObj.getAttribute12()==null ?"-":custObj.getAttribute12())+"</rowId> ");
				odata.append("</row>");
			}
		}	
		
		odata.append("</validateItemData>");
	
	} else if (strCallType.equalsIgnoreCase("GETSCANNEDDATA")) {
	
		CanonE437PhysicalInventory obj = new CanonE437PhysicalInventory();	
			
		ArrayList results = obj.getScannedData(strOrgId,strPhyInvId,strEmpId,strFormMode);
		
		errorFlag = (String)results.get(0);
		errorMsg = (String)results.get(1);
			
		odata.append("<getScannedData>"); 	
		odata.append("<errFlag>"+(errorFlag)+"</errFlag>"); 
		odata.append("<errMsg>"+(errorMsg)+"</errMsg>"); 
		
		if (!(errorFlag.equals("E"))) {
			ArrayList itemList = new ArrayList();
			
			itemList = (ArrayList)results.get(2);
			
			int cnt = itemList.size();
						
			odata.append("<rcount>"+(itemList.size())+"</rcount> ");
			
			for(int i = 0; i < cnt; i++) {
				CanonE410AttributeBean custObj  = (CanonE410AttributeBean)itemList.get(i);
				odata.append("<row>");
				odata.append("<whouseId>"+(custObj.getAttribute1()==null ?"-":custObj.getAttribute1())+"</whouseId> ");
				odata.append("<phyInvId>"+(custObj.getAttribute2()==null ?"-":custObj.getAttribute2())+"</phyInvId> ");
				odata.append("<itemId>"+(custObj.getAttribute3()==null ?"-":custObj.getAttribute3())+"</itemId> ");
				odata.append("<itemNum>"+(custObj.getAttribute4()==null ?"-":custObj.getAttribute4())+"</itemNum> ");
				odata.append("<tagId>"+(custObj.getAttribute5()==null ?"-":custObj.getAttribute5())+"</tagId> ");
				odata.append("<tagNum>"+(custObj.getAttribute6()==null ?"-":custObj.getAttribute6())+"</tagNum> ");
				odata.append("<subInv>"+(custObj.getAttribute7()==null ?"-":custObj.getAttribute7())+"</subInv> ");
				odata.append("<locatorId>"+(custObj.getAttribute8()==null ?"-":custObj.getAttribute8())+"</locatorId> ");
				odata.append("<locator>"+(custObj.getAttribute9()==null ?"-":custObj.getAttribute9())+"</locator> ");
				odata.append("<tagQty>"+(custObj.getAttribute10()==null ?"-":custObj.getAttribute10())+"</tagQty> ");
				odata.append("<scannedEmp>"+(custObj.getAttribute11()==null ?"-":custObj.getAttribute11())+"</scannedEmp> ");
				odata.append("<rowId>"+(custObj.getAttribute12()==null ?"-":custObj.getAttribute12())+"</rowId> ");				
				odata.append("</row>");
			}
		}	
		
		odata.append("</getScannedData>");
	
	} else if (strCallType.equalsIgnoreCase("UPDATEDBMANUAL")) {
	
		CanonE437PhysicalInventory obj = new CanonE437PhysicalInventory();	
			
		ArrayList results = obj.updateDBManual(strOrgId,strPhyInvId,strTagId,strTagQty,strEmpId,strUserId,strSubInv,strLocatorId,strItemId,strFormMode);
		
		errorFlag = (String)results.get(0);
		errorMsg = (String)results.get(1);
			
		odata.append("<updateDBManual>"); 	
		odata.append("<errFlag>"+(errorFlag)+"</errFlag>"); 
		odata.append("<errMsg>"+(errorMsg)+"</errMsg>"); 		
		odata.append("</updateDBManual>");
	
	} else if (strCallType.equalsIgnoreCase("CHECKPENDINGCOUNTS")) {
	
		CanonE437PhysicalInventory obj = new CanonE437PhysicalInventory();	
			
		ArrayList results = obj.checkPendingCounts(strOrgId,strPhyInvId,strFormMode);
		
		errorFlag = (String)results.get(0);
		errorMsg = (String)results.get(1);
			
		odata.append("<checkPendingCounts>"); 	
		odata.append("<errFlag>"+(errorFlag)+"</errFlag>"); 
		odata.append("<errMsg>"+(errorMsg)+"</errMsg>"); 		
		if (errorFlag.equals("S")) {
			strPendCntRtn = (String)results.get(2);
			odata.append("<pendCntMsg>"+(strPendCntRtn)+"</pendCntMsg>");
		}
		odata.append("</checkPendingCounts>");
	
	} else if (strCallType.equalsIgnoreCase("ASSIGNZEROCOUNTS")) {
	
		CanonE437PhysicalInventory obj = new CanonE437PhysicalInventory();	
			
		ArrayList results = obj.assignZeroCounts(strOrgId,strPhyInvId,strEmpId,strUserId,strFormMode);
		
		errorFlag = (String)results.get(0);
		errorMsg = (String)results.get(1);
			
		odata.append("<assignZeroCounts>"); 	
		odata.append("<errFlag>"+(errorFlag)+"</errFlag>"); 
		odata.append("<errMsg>"+(errorMsg)+"</errMsg>"); 		
		odata.append("</assignZeroCounts>");
	
	} else if (strCallType.equalsIgnoreCase("GETSCANNEDDATABYPAGE")) {
	
		CanonE437PhysicalInventory obj = new CanonE437PhysicalInventory();	

		ArrayList results = obj.getScannedDataByPage(strOrgId,strPhyInvId,strEmpId,strPageNum,strBeginItem,strEndItem,strFormMode);
		
		errorFlag = (String)results.get(0);
		errorMsg = (String)results.get(1);
			
		odata.append("<getScannedDataByPage>"); 	
		odata.append("<errFlag>"+(errorFlag)+"</errFlag>"); 
		odata.append("<errMsg>"+(errorMsg)+"</errMsg>"); 
		
		if (!(errorFlag.equals("E"))) {
			ArrayList itemList = new ArrayList();
			
			itemList = (ArrayList)results.get(2);
			
			int cnt = itemList.size();
						
			odata.append("<rcount>"+(itemList.size())+"</rcount> ");
			
			for(int i = 0; i < cnt; i++) {
				CanonE410AttributeBean custObj  = (CanonE410AttributeBean)itemList.get(i);
				odata.append("<row>");
				odata.append("<whouseId>"+(custObj.getAttribute1()==null ?"-":custObj.getAttribute1())+"</whouseId> ");
				odata.append("<phyInvId>"+(custObj.getAttribute2()==null ?"-":custObj.getAttribute2())+"</phyInvId> ");
				odata.append("<itemId>"+(custObj.getAttribute3()==null ?"-":custObj.getAttribute3())+"</itemId> ");
				odata.append("<itemNum>"+(custObj.getAttribute4()==null ?"-":custObj.getAttribute4())+"</itemNum> ");
				odata.append("<tagId>"+(custObj.getAttribute5()==null ?"-":custObj.getAttribute5())+"</tagId> ");
				odata.append("<tagNum>"+(custObj.getAttribute6()==null ?"-":custObj.getAttribute6())+"</tagNum> ");
				odata.append("<subInv>"+(custObj.getAttribute7()==null ?"-":custObj.getAttribute7())+"</subInv> ");
				odata.append("<locatorId>"+(custObj.getAttribute8()==null ?"-":custObj.getAttribute8())+"</locatorId> ");
				odata.append("<locator>"+(custObj.getAttribute9()==null ?"-":custObj.getAttribute9())+"</locator> ");
				odata.append("<tagQty>"+(custObj.getAttribute10()==null ?"-":custObj.getAttribute10())+"</tagQty> ");
				odata.append("<scannedEmp>"+(custObj.getAttribute11()==null ?"-":custObj.getAttribute11())+"</scannedEmp> ");
				odata.append("<rowId>"+(custObj.getAttribute12()==null ?"-":custObj.getAttribute12())+"</rowId> ");				
				odata.append("</row>");
			}
		}	
		
		odata.append("</getScannedDataByPage>");
	
	} else if (strCallType.equalsIgnoreCase("PRINTREPORT")) { //ITG 301386
	
		CanonE437PhysicalInventory obj = new CanonE437PhysicalInventory();	
			
		ArrayList results = obj.printReport(strOrgId,strPhyInvId,strUserId,strFormMode);
		
		errorFlag = (String)results.get(0);
		errorMsg = (String)results.get(1);
			
		odata.append("<printReport>"); 	
		odata.append("<errFlag>"+(errorFlag)+"</errFlag>"); 
		odata.append("<errMsg>"+(errorMsg)+"</errMsg>"); 		
		odata.append("</printReport>");
	
	} 
	
	System.out.println("E437 odata --> "+odata);
	
	response.getWriter().write(odata.toString()); 
%>