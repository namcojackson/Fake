<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="com.canon.apps.servreq.dao.*" %>  
<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<%@page import="com.canon.common.*" %>  
<%@ page import="java.text.*"%> 
<%@ page import="java.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="parts.dbcommon.EZDDBCICarrier"%>
<%@page import="java.util.Enumeration"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="canon.apps.common.CanonConstants"%>
<%@page import="java.util.Date"%>
<%@page import="business.parts.NSZC043001PMsg"%>
<%@page import="parts.dbcommon.EZDDBCICarrier"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.canon.cusa.s21.framework.security.S21AuthenticationException"%>
<%@page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileService"%>
<%@page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory"%>
<%@page import="com.canon.cusa.s21.framework.security.S21SecurityException"%>
<%@page import="com.canon.cusa.s21.framework.security.S21Authentication"%>
<%@page import="com.canon.cusa.s21.framework.security.S21AuthorizationAction"%>
<%@ page language="java" import="parts.common.EZDCommonFunc" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContext" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextFactory" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.config.S21ConfigurationException" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.helpers.S21AuthenticationHelper" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.authentication.token.S21UserPasswordAuthenticationToken" %>

<%@ page language="java" import="com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SvcChrg"%>

<% 	
	String ctxPath = request.getContextPath();
	String imgSrc=ctxPath+"/common/images/jtfulov.gif";
	SimpleDateFormat sdf = new SimpleDateFormat("dd'-'MMM'-'yyyy");
	SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	SimpleDateFormat format = new SimpleDateFormat("z");
	String timezone = format.format(new Date());
	String invokeTimestamp = lsDateFmt.format(new Date());
	String loginUser= CanonS21SessionValidate.getUserName();

	
	//Initialize S21DB-Carrier (It should be done. It leads NullPointer exception when didn't initialize.)
	//These contents are used as a default data in S21-Standard table. (Update user, time, time-zone, program id, company code)
	EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
	EZDDBCICarrier.setProgID("S21EXTN_E307");
	String strEntitleMsg="";
	
	CanonE307ServiceRequestDetailsDao srObj = new CanonE307ServiceRequestDetailsDao();
	CanonE307ServiceReqSumryAPIUtil utilObj = new CanonE307ServiceReqSumryAPIUtil();

	CanonCommonUtil util = new CanonCommonUtil();
	ArrayList lstSrHist = new ArrayList();
	StringBuffer buff = new StringBuffer();
	String strSerNum = request.getParameter("serialNumber");
	String strTagNum = request.getParameter("tagNum");
	String strSolName = request.getParameter("solName");
	String strModel = request.getParameter("model");
	String strAcctNum = request.getParameter("acctNum");
	String strCustName = request.getParameter("custName");
	String strServRqstNum =  request.getParameter("servRqstNumber")==null?"":request.getParameter("servRqstNumber");
	String strTaskNumber =  request.getParameter("taskNumber")==null?"":request.getParameter("taskNumber");		
	int pageNum = (request.getParameter("pageNum")==null)?1:Integer.parseInt(request.getParameter("pageNum"));
	String creationDt = request.getParameter("creationDt")==null?"":request.getParameter("creationDt");
	String createdBy	= request.getParameter("createdBy")==null?"":request.getParameter("createdBy");
	String strSrStatus	= request.getParameter("srStatus")==null?"":request.getParameter("srStatus");
	String strTaskSts	= request.getParameter("taskSts")==null?"":request.getParameter("taskSts");
	String strTaskType = request.getParameter("taskType")==null?"":request.getParameter("taskType");
	int ct=10; // number of Records per page 
	int start = ((pageNum-1)*ct) + 1 ;
	int end= pageNum*ct;	
	int totalLinks =0;
	String totalCountMsg = "";	
	System.out.println("Task Hist View : "+ strSerNum);

	String strSortBy=request.getParameter("sortBy");
	String strSortOrder=request.getParameter("sortOrder");
	Object[] srHistObj = srObj.getTaskHistoryNew(strSerNum, strTagNum, strSolName, strModel, strAcctNum, strCustName, start, end, strSortBy, strSortOrder, strServRqstNum, strTaskNumber, createdBy, creationDt, strSrStatus, strTaskSts, strTaskType);
	int totalCount =0;// 
	System.out.println("Task Hist View After Execute: ");
	if(srHistObj[0]!=null){
		totalCount = ((Integer)srHistObj[0]).intValue();
	}
	//System.out.println("Task Hist View 1: "+ totalCount);
	if(totalCount>0){ 
		lstSrHist = (ArrayList) srHistObj[1];
	}
	totalLinks = (totalCount%ct>0)?((totalCount/ct)+1):totalCount/ct;
    totalCountMsg= start+" to "+(end-(ct-lstSrHist.size())) +" of "+ totalCount +" records.";
	int nop=totalLinks;
	buff.append("<div id='paging1' style='width: 99%; margin: 0px auto;' align='center'>");
	buff.append("<table id='pgLinks' width='100%'>");
	buff.append("<tr align='left'>");	
	buff.append("<td align='left'>");
	buff.append("<input type='hidden' name='pageNum' id='pageNum' value='").append(pageNum).append("'>");	 
	 
	 if(nop==0){ // no rows
		 
	 }else if(0<nop  && nop<=1){
		 
		 
	 }else if(nop>1){

		 if(nop>10 ){
		  
			 if(pageNum>2){
					buff.append("<a href='#' onclick=getTaskView('1','").append(strSortBy).append("','").append(strSortOrder).append("');>First</a>").append(" ");
				 }
			 if(pageNum>1){
				 buff.append("<a href='#' onclick=getTaskView('").append(pageNum-1).append("','").append(strSortBy).append("','").append(strSortOrder).append("')>Prev</a>").append(" ");
			 }
			 
			 for(int k=pageNum;k<(pageNum+10) && k<=totalLinks ;k++){
			   buff.append("<a  id='a").append(k).append("' href='#' onclick=getTaskView('").append(k).append("','").append(strSortBy).append("','").append(strSortOrder).append("')>").append(k).append("</a>").append(" ");
		     }
			 
			 if( (pageNum+1) <= nop){
				  buff.append("<a href='#' onclick=getTaskView('").append(pageNum+1).append("','").append(strSortBy).append("','").append(strSortOrder).append("')>Next</a>").append(" ");
			 } 
			  buff.append("<a href='#' onclick=getTaskView('").append(nop).append("','").append(strSortBy).append("','").append(strSortOrder).append("')>Last</a>").append(" ");
		 }
		  
		 if(nop<=10){
			 for(int k=1;k<=nop;k++){
				buff.append("<a id='a").append(k).append("' href='#' onclick=getTaskView('").append(k).append("','").append(strSortBy).append("','").append(strSortOrder).append("');>").append(k).append("</a>").append(" ");
			 }
		 }
	 
	 }else{
		 
	 }
	buff.append("</td>");
	buff.append("<td width='20%' class='sectionHeader2' align='right'>");
	buff.append(totalCountMsg);
	buff.append("</td>");
	buff.append("</tr>");
	buff.append("</table>");
	buff.append("</div>");
	buff.append("<div id='srHistTbl' style=''>");
	buff.append("	<div id='scrolltbl' style='overflow: auto; overflow-y: hidden; width:100%;'>");
	
	buff.append("<table id='taskTable' class='model-table' cellspacing='1' width='100%' style='border-width:0px'>");
	buff.append("<tbody>");
	buff.append("<tr>");
	buff.append("<th width='1%'></th>");
	buff.append("<th style='width:7%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('FSR_NUM','").append(pageNum).append("')>Service Request#</a></th>");
	buff.append("<th style='width:5%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('TASK_CRAT_DT','").append(pageNum).append("')>Creation Date</a></th>");
	buff.append("<th style='width:3%;' nowrap><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('SVC_TASK_NUM','").append(pageNum).append("')>Task#</a></th>");
	buff.append("<th style='width:3%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('TASK_TYPE_NM','").append(pageNum).append("')>Task Type</a></th>");
	buff.append("<th style='width:3%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('SVC_TASK_STS','").append(pageNum).append("')>Task Status</a></th>");
	buff.append("<th style='width:9%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('SVC_PBLM_TP_CD','").append(pageNum).append("')>Problem Code</a></th>");
	buff.append("<th style='width:9%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('PROBLEM_NOTE','").append(pageNum).append("')>Problem Note</a></th>");
	buff.append("<th style='width:9%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('MOBILE_NOTE','").append(pageNum).append("')>Mobile Note</a></th>");
	buff.append("<th style='width:4%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('PRIMARY_MTR_RD','").append(pageNum).append("')>Primary Meter</a></th>");
	buff.append("<th style='width:4%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('SVC_RSP_TM_MN_AOT','").append(pageNum).append("')>Response Time</a></th>");
	buff.append("<th style='width:6%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('LABOR_START_DATE','").append(pageNum).append("')>Labor Start</a></th>");
	buff.append("<th style='width:6%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('LABOR_END_DATE','").append(pageNum).append("')>Labor End</a></th>");
	buff.append("<th style='width:5%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('PROBLEM_CODE','").append(pageNum).append("')>Problem Code</a></th>");
	buff.append("<th style='width:5%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('CORRECTION_CODE','").append(pageNum).append("')>Correction Code</a></th>");	
	buff.append("<th style='width:5%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('LOCATION_CODE','").append(pageNum).append("')>Location Code</a></th>");
	buff.append("<th style='width:5%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('REASON_CODE','").append(pageNum).append("')>Reason Code</a></th>");
	buff.append("<th style='width:3%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('MACH_DWN_FLG','").append(pageNum).append("')>Machine Status</a></th>");
	buff.append("<th style='width:5%;'><a style='color:#FFFFFF' href=Javascript:fnSortTskFilterCriteria('ASSIGNEE_NAME','").append(pageNum).append("')>Technician</a></th>");
	buff.append("</tr>");
	buff.append("</tbody>");
	
	if(lstSrHist!=null && lstSrHist.size()>0){
		for(int i=0;i<lstSrHist.size();i++){
			CanonE307SRTaskHistNewRec srHObj = (CanonE307SRTaskHistNewRec)lstSrHist.get(i);
			String bclr  = "";
			String tskCrtionDt = util.checkNull(utilObj.getTmZone(srHObj.getStrPostalCd(), srHObj.getStrCntryCd(), srHObj.getStrTskCrtnDt()));
			String lbrStrt = util.checkNull(utilObj.getTmZone(srHObj.getStrPostalCd(), srHObj.getStrCntryCd(), srHObj.getStrLbrStrt()));
			String lbrEnd = util.checkNull(utilObj.getTmZone(srHObj.getStrPostalCd(), srHObj.getStrCntryCd(), srHObj.getStrLbrEnd()));
			if((i%2) == 0)
			{
				bclr   = "eventableDataCell";
			}
			else
			{
			    bclr = "oddtableDataCell";
			}
			buff.append("<tr id='srRow").append(i).append("'>");//.append("onclick=fnGetTaskDetails('").append(util.checkNull(srHObj.getStrFSR())).append("')>");
			buff.append(" <td style='width:1%;'><input type=radio name='histSelect' id='histSelect").append(i).append("' value=").append(i).append(" onclick=fnPopulateInfo('").append(util.checkNull(srHObj.getStrFSR())).append("') style='border:0px;'></td>");
			buff.append("<td align='center' style='width:7%;' nowrap> ");
			buff.append(" <a href=javascript:fnGetTaskDetails('").append(util.checkNull(srHObj.getStrFSR())).append("') style='color: #cc0000;'>").append(util.checkNull(srHObj.getStrFSR())).append("</a>  ");
			buff.append("</td>");
			buff.append(" <td align='center' style='width:5%;'>").append(tskCrtionDt).append("</td>");
			buff.append(" <td align='center' style='width:3%;' nowrap>");
			buff.append(" <a href=javascript:fnGetTaskInfo('").append(util.checkNull(srHObj.getStrFSR())).append("','").append(srHObj.getStrTskNum()).append("','").append(srHObj.getStrPostalCd()).append("','").append(srHObj.getStrCntryCd()).append("') style='color: #cc0000;'>").append(util.checkNull(srHObj.getStrTskNum()));
			buff.append("</a></td> ");
			buff.append("<td align='center' style='width:3%;'>").append(util.checkNull(srHObj.getStrTskTpe())).append("</td>");
			buff.append("<td align='center' style='width:3%;'>").append(util.checkNull(srHObj.getStrTskSts())).append("</td>");
			buff.append("<td align='center' style='width:4%;'>").append(util.checkNull(srHObj.getStrSrProbCd())).append("</td>");	
			buff.append("<td align='center' style='width:9%;'>").append(util.checkNull(srHObj.getStrSrPblmNt())).append("</td>");
			buff.append("<td align='center' style='width:9%;'>").append(util.checkNull(srHObj.getStrMblNt())).append("</td>");
			buff.append("<td align='center' style='width:9%;'>").append(util.checkNull(srHObj.getStrPrmryMtr())).append("</td>");
			buff.append("<td align='center' style='width:4%;'>").append(util.checkNull(srHObj.getStrRespTm())).append("</td>");
			buff.append("<td align='center' style='width:4%;'>").append(util.checkNull(lbrStrt)).append("</td>");	
			buff.append("<td align='center' style='width:5%;'>").append(util.checkNull(lbrEnd)).append("</td>");
			buff.append("<td align='center' style='width:5%;'>").append(util.checkNull(srHObj.getStrTskPblmCd())).append("</td>");
			buff.append("<td align='center' style='width:5%;'>").append(util.checkNull(srHObj.getStrCorctnCd())).append("</td>");
			buff.append("<td align='center' style='width:5%;'>").append(util.checkNull(srHObj.getStrLoctnCd())).append("</td>");	
			buff.append("<td align='center' style='width:5%;'>").append(util.checkNull(srHObj.getStrRsnCd())).append("</td>");
			buff.append("<td align='center' style='width:3%;'>").append(util.checkNull(srHObj.getStrMachnSts())).append("</td>");
			buff.append("<td align='center' style='width:5%;'>").append(util.checkNull(srHObj.getStrTechnician())).append("</td>");
			buff.append("</tr>");
	
		}
	}
	buff.append("</table>");
	buff.append("</div> </div>");
	buff.append("<table><tr><td>&nbsp;</td></tr></table>");
	buff.append("<table><tr><td>&nbsp;</td></tr></table>");
	buff.append("<table><tr><td>&nbsp;</td></tr></table>");
	buff.append("<table><tr><td>&nbsp;</td></tr></table>");
	//System.out.println("String Task View : " + buff.toString());
	response.getWriter().print(buff.toString());			
%>