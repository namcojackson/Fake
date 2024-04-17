<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>
<%@page import="java.util.Collections"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServReqUtil"%>
<%@page import="java.util.List"%>
<%
	System.out.println("Inside download");
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
   // Object o = pageContext.getAttribute("export-excel-summary",PageContext.REQUEST_SCOPE);
   
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
	
	System.out.println("sortOrder: "+ sortOrder+" sortBy: "+sortBy+" strServRqstNum: "+strServRqstNum+" strTaskType: "+strTaskType);
	
	CanonE307ServiceRequestDetailsDao srObj = new CanonE307ServiceRequestDetailsDao();
	int hStart = 0;
	int hEnd = 0;
//	List dwnldLst = srObj.getSrHistoryDownload(strSerial,strTagNum,strSolName, strModel, strAcctNum,strCustName, sortBy, sortOrder, strServRqstNum, strTaskNumber, strCreatedBy, strCreationDt);
	Object[] taskObj = srObj.getTaskHistoryNew(strSerial,strTagNum,strSolName, strModel, strAcctNum,strCustName, 0, 0, sortBy, sortOrder, strServRqstNum, strTaskNumber, strCreatedBy, strCreationDt, strSrStatus, strTaskSts, strTaskType);
	if(taskObj!=null){
		int totalCnt = 0;
		if(taskObj[0]!=null){
			totalCnt = ((Integer)taskObj[0]).intValue();
		}
		if(totalCnt>0){ 
			List dwnldLst = (List) taskObj[1];
		    if (dwnldLst!=null && dwnldLst.size()>0) {
		    	System.out.println("Inside lst 123 ..");
		        response.setHeader("Content-disposition", "attachment; filename=canonE307Summary.xlsx");
		        System.out.println("Before createSummaryExcelOutputStream1");
		        CanonE307ServReqUtil.createSummaryExcelOutputStream((List) dwnldLst).save(response.getOutputStream());
		    }			
		}
		
	}

%>