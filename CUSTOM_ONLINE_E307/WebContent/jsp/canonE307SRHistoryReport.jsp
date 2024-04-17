<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.File"%>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307SRTaskHistNewRec"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="com.aspose.cells.BackgroundType" %>
<%@page import="com.aspose.cells.BorderType" %>
<%@page import="com.aspose.cells.CellBorderType" %>
<%@page import="com.aspose.cells.Cells" %>
<%@page import="com.aspose.cells.Color" %>
<%@page import="com.aspose.cells.Font" %>
<%@page import="com.aspose.cells.FontUnderlineType" %>
<%@page import="com.aspose.cells.Protection" %>
<%@page import="com.aspose.cells.ProtectionType" %>
<%@page import="com.aspose.cells.SaveFormat" %>
<%@page import="com.aspose.cells.Style" %>
<%@page import="com.aspose.cells.TextAlignmentType" %>
<%@page import="com.aspose.cells.Workbook" %>
<%@page import="com.aspose.cells.Worksheet" %>
<%@page import="com.aspose.cells.License" %>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>
<%@page import="java.util.Collections"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServReqUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.io.InputStream" %>;
<%@page import="java.io.OutputStream" %>;
<%@page import="canon.apps.common.CanonCustomProfile"%>
<%
 
 	request.getSession().setAttribute("dowloadFlag", "P");
 
   	String strSerial =request.getParameter("serialNumber")==null?"":request.getParameter("serialNumber");
	String strTagNum = request.getParameter("tagNum")==null?"":request.getParameter("tagNum");
	String strSolName = request.getParameter("solName")==null?"":request.getParameter("solName");
	String strModel =  request.getParameter("model")==null?"":request.getParameter("model");
	String strAcctNum = request.getParameter("acctNum")==null?"":request.getParameter("acctNum");
	String strCustName =  request.getParameter("custName")==null?"":request.getParameter("custName");
	String strServRqstNum =  request.getParameter("servRqstNumber")==null?"":request.getParameter("servRqstNumber");
	String strTaskNumber =  request.getParameter("taskNumber")==null?"":request.getParameter("taskNumber");	
	String strCreatedBy =  request.getParameter("createdBy")==null?"":request.getParameter("createdBy");	
	String strCreationDt =  request.getParameter("creationDt")==null?"":request.getParameter("creationDt");
	String sortOrder = request.getParameter("sortOrder")==null?"":request.getParameter("sortOrder");
	String sortBy = request.getParameter("sortBy")==null?"":request.getParameter("sortBy");
	String strSrStatus	= request.getParameter("srStatus")==null?"":request.getParameter("srStatus");
	String strTaskSts	= request.getParameter("taskSts")==null?"":request.getParameter("taskSts");
	String strTaskType = request.getParameter("taskType")==null?"":request.getParameter("taskType");
	
	String checkProgress = request.getParameter("checkProgress")==null?"":request.getParameter("checkProgress");
	
	System.out.println("sortOrder: "+ sortOrder+" sortBy: "+sortBy+" strServRqstNum: "+strServRqstNum+" strTaskType: "+strTaskType);
	
	CanonE307ServiceRequestDetailsDao srObj = new CanonE307ServiceRequestDetailsDao();
	String file = "/WebSphere/apps/filebox/attachment/CanonE307SummaryReport.xlsx";
	

	if("Y".equalsIgnoreCase(checkProgress)) {
		System.out.println("Inside checkProgress: "+checkProgress);
		String dowloadFlag=(String) request.getSession().getAttribute("dowloadFlag");
		PrintWriter pw = response.getWriter();
		pw.println("P");
        pw.flush();  
		// return respnose with 'dowloadFlag'

	}else{
	
	License lic = new License();
	InputStream is = lic.getClass().getClassLoader().getResourceAsStream("META-INF/Aspose.Cells.lic");

	if (is != null) {
		lic.setLicense(is);
		is.close();
		System.out.println("ASPOSE License loaded.");
	} else {
		System.out.println("ASPOSE License not found.");
	}

	
	Workbook workbook = new Workbook(file);
	Worksheet workSheet = workbook.getWorksheets().get(0);
	Style style = workbook.createStyle();
	Font font = style.getFont();
	font.setSize(9);
	font.setName("Verdana");
	style.setHorizontalAlignment(TextAlignmentType.CENTER);
	Cells cells  = workSheet.getCells();
	style.setForegroundColor(Color.getWhite()); 
	style.setPattern(BackgroundType.SOLID);
	style.getBorders().getByBorderType(BorderType.BOTTOM_BORDER).setLineStyle(CellBorderType.THIN);
	style.getBorders().getByBorderType(BorderType.RIGHT_BORDER).setLineStyle(CellBorderType.THIN);
	style.setTextWrapped(true);
	
	int hStart = 0;
	int hEnd = 0;

	Object[] taskObj = srObj.getTaskHistoryNew(strSerial,strTagNum,strSolName, strModel, strAcctNum,strCustName, 0, 0, sortBy, sortOrder, strServRqstNum, strTaskNumber, strCreatedBy, strCreationDt, strSrStatus, strTaskSts, strTaskType);
	
	
	int columnCountInt = 18; 
	int irowCreate = 1;
	System.out.println("1");
	if(taskObj!=null){
		int totalCnt = 0;
		CanonE307ServiceReqSumryAPIUtil utiSrObj = new CanonE307ServiceReqSumryAPIUtil();
		if(taskObj[0]!=null){
			totalCnt = ((Integer)taskObj[0]).intValue();
		}
		if(totalCnt>0){ 
			List dwnldLst = (List) taskObj[1];
		    if (dwnldLst!=null && dwnldLst.size()>0) {
		    	for(int i=0;i<dwnldLst.size();i++){
		    		CanonE307SRTaskHistNewRec histObj = (CanonE307SRTaskHistNewRec)dwnldLst.get(i);
		    		String creationDt = utiSrObj.getTmZone(histObj.getStrPostalCd(), histObj.getStrCntryCd(), histObj.getStrTskCrtnDt());
       				String lbrStrtDt = utiSrObj.getTmZone(histObj.getStrPostalCd(), histObj.getStrCntryCd(), histObj.getStrLbrStrt());
       				String lbrEndDt = utiSrObj.getTmZone(histObj.getStrPostalCd(), histObj.getStrCntryCd(), histObj.getStrLbrEnd());

    				for(int colIndex=0; colIndex<columnCountInt; colIndex++ )  
    				{
    					style.setPattern(BackgroundType.SOLID);
						style.getBorders().getByBorderType(BorderType.BOTTOM_BORDER).setLineStyle(CellBorderType.THIN);
						style.getBorders().getByBorderType(BorderType.RIGHT_BORDER).setLineStyle(CellBorderType.THIN);
						style.setTextWrapped(true);
						if(colIndex==0){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrFSR());
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==1){
							cells.get(irowCreate, colIndex).setValue(creationDt);
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==2){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrTskNum());
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==3){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrTskTpe());
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==4){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrTskSts() );
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==5){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrSrProbCd());
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==6){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrSrPblmNt() );
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==7){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrMblNt());
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==8){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrPrmryMtr());
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==9){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrRespTm());
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==10){
							cells.get(irowCreate, colIndex).setValue(lbrStrtDt);
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==11){
							cells.get(irowCreate, colIndex).setValue(lbrEndDt);
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==12){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrSrProbCd());
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==13){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrCorctnCd());
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==14){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrLoctnCd());
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==15){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrRsnCd());
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==16){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrMachnSts());
							cells.get(irowCreate, colIndex).setStyle(style);
						}else if(colIndex==17){
							cells.get(irowCreate, colIndex).setValue(histObj.getStrTechnician());
							cells.get(irowCreate, colIndex).setStyle(style);
						}
    				}
    				irowCreate++;
		    	}
		    	workSheet.protect(ProtectionType.ALL);
				workSheet.autoFitRows();
		    }
		}
	}
	System.out.println("2 ");
	request.getSession().setAttribute("dowloadFlag", "C");
	try{
		 response.setContentType("application/vnd.ms-excel");
		 response.setHeader("Content-disposition","inline; filename=CanonE307SummaryReport.xls");
		 OutputStream outpt = response.getOutputStream();
		 workbook.save(outpt, SaveFormat.EXCEL_97_TO_2003);
		 outpt.close();

	 }catch(Exception e){
		 response.getWriter().println("Error occurred in download process. Please try again later."); 
	 }
	}
	

%>