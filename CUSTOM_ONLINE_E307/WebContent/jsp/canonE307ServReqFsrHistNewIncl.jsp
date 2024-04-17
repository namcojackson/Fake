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
	CanonE307ServiceReqSumryAPIUtil utiSrObj = new CanonE307ServiceReqSumryAPIUtil();
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
	String strFsrSts	= request.getParameter("srStatus")==null?"":request.getParameter("srStatus");
	String strTaskSts	= request.getParameter("taskSts")==null?"":request.getParameter("taskSts");
	String strTaskType = request.getParameter("taskType")==null?"":request.getParameter("taskType");
	int ct=10; // number of Records per page 
	int start = ((pageNum-1)*ct) + 1 ;
	int end= pageNum*ct;	
	int totalLinks =0;
	String totalCountMsg = "";	
	System.out.println("strSerNum : "+ strSerNum);

	String strSortBy=request.getParameter("sortBy");
	String strSortOrder=request.getParameter("sortOrder");
	Object[] srHistObj = srObj.getSrHistoryNew(strSerNum, strTagNum, strSolName, strModel, strAcctNum, strCustName, start, end, strSortBy, strSortOrder, strServRqstNum, strTaskNumber, createdBy, creationDt, strFsrSts, strTaskSts, strTaskType);
	int totalCount =0;// 
	if(srHistObj[0]!=null){
		totalCount = ((Integer)srHistObj[0]).intValue();
	}
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
					buff.append("<a href='#' onclick=getFsrHistory('1','").append(strSortBy).append("','").append(strSortOrder).append("');>First</a>").append(" ");
				 }
			 if(pageNum>1){
				 buff.append("<a href='#' onclick=getFsrHistory('").append(pageNum-1).append("','").append(strSortBy).append("','").append(strSortOrder).append("')>Prev</a>").append(" ");
			 }
			 
			 for(int k=pageNum;k<(pageNum+10) && k<=totalLinks ;k++){
			   buff.append("<a  id='a").append(k).append("' href='#' onclick=getFsrHistory('").append(k).append("','").append(strSortBy).append("','").append(strSortOrder).append("')>").append(k).append("</a>").append(" ");
		     }
			 
			 if( (pageNum+1) <= nop){
				  buff.append("<a href='#' onclick=getFsrHistory('").append(pageNum+1).append("','").append(strSortBy).append("','").append(strSortOrder).append("')>Next</a>").append(" ");
			 } 
			  buff.append("<a href='#' onclick=getFsrHistory('").append(nop).append("','").append(strSortBy).append("','").append(strSortOrder).append("')>Last</a>").append(" ");
		 }
		  
		 if(nop<=10){
			 for(int k=1;k<=nop;k++){
				buff.append("<a id='a").append(k).append("' href='#' onclick=getFsrHistory('").append(k).append("','").append(strSortBy).append("','").append(strSortOrder).append("');>").append(k).append("</a>").append(" ");
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
	buff.append("<th style='width:15%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('FSR_NUM','").append(pageNum).append("')>Service Request#</a></th>");
	buff.append("<th style='width:10%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('FSR_CREATION_DATE','").append(pageNum).append("')>Creation Date</a></th>");
	buff.append("<th style='width:8%;' nowrap><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('FSR_TYPE','").append(pageNum).append("')>SR Type</a></th>");
	buff.append("<th style='width:7%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('FSR_STS_CD','").append(pageNum).append("')>SR Status</a></th>");
	buff.append("<th style='width:20%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('CUSTOMER_NAME','").append(pageNum).append("')>Problem Code</a></th>");
	buff.append("<th style='width:20%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('PROBLEM_TYPE_NAME','").append(pageNum).append("')>Problem Note</a></th>");
	buff.append("<th style='width:6%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('LAST_METER','").append(pageNum).append("')>Mobile Note</a></th>");
	buff.append("<th style='width:8%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('SER_NUM','").append(pageNum).append("')>Primary Meter</a></th>");
	buff.append("<th style='width:5%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('BRANCH','").append(pageNum).append("')>Response Time</a></th>");
	buff.append("<th style='width:5%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('OWNER_NAME','").append(pageNum).append("')>Restore Time</a></th>");
	buff.append("<th style='width:13%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('DISPATCHER','").append(pageNum).append("')>Latest Tech Problem Code</a></th>");
	buff.append("<th style='width:13%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('DISPATCHER','").append(pageNum).append("')>Correction Code</a></th>");	
	buff.append("<th style='width:13%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('DISPATCHER','").append(pageNum).append("')>Location Code</a></th>");
	buff.append("<th style='width:13%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('DISPATCHER','").append(pageNum).append("')>Reason Code</a></th>");
	buff.append("<th style='width:13%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('DISPATCHER','").append(pageNum).append("')>Machine Status</a></th>");
	buff.append("<th style='width:13%;'><a style='color:#FFFFFF' href=Javascript:fnSortFilterCriteria('DISPATCHER','").append(pageNum).append("')>Created By</a></th>");
	buff.append("<td></td>");
	buff.append("</tr>");
	
	if(lstSrHist!=null && lstSrHist.size()>0){
		for(int i=0;i<lstSrHist.size();i++){
			CanonE307SRHistoryNewRec srHObj = (CanonE307SRHistoryNewRec)lstSrHist.get(i);
			String bclr  = "";
			String crtionDt = util.checkNull(utiSrObj.getTmZone(srHObj.getStrPostalCd(), srHObj.getStrCntryCd(), srHObj.getStrCreationDt()));
			ArrayList tskLst = (ArrayList)srObj.getTaskDet(util.checkNull(srHObj.getStrFSR()));
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
	buff.append("<td align='center' style='width:5%;' nowrap> ");
	buff.append("<a id='link").append(i).append("' class='btn_red_sm' onclick=setTaskRows('").append(util.checkNull(srHObj.getStrFSR())).append("','").append(i).append("') href='javascript:void(0);'>+</a>");
	buff.append(" <a href=javascript:fnGetTaskDetails('").append(util.checkNull(srHObj.getStrFSR())).append("','").append(srHObj.getStrEOLFlg()).append("') style='color: #cc0000;'>").append(util.checkNull(srHObj.getStrFSR())).append(" <br /></a>  ");
	buff.append("</td>");
	buff.append(" <td align='center' style='width:9%;'>").append(crtionDt).append("</td>");
	buff.append("<td align='center' style='width:8%;'>").append(util.checkNull(srHObj.getStrFsrType())).append("</td>");
	buff.append("<td align='center' style='width:8%;'>").append(util.checkNull(srHObj.getStrFSRSts())).append("</td>");
	buff.append("<td align='center' style='width:20%;'>").append(util.checkNull(srHObj.getStrSvcPblmTpCd())).append("</td>");	
	buff.append("<td align='center' style='width:20%;'>").append(util.checkNull(srHObj.getStrPblmNt())).append("</td>");
	buff.append("<td align='center' style='width:20%;'>").append(util.checkNull(srHObj.getStrMblNt())).append("</td>");
	buff.append("<td align='center' style='width:8%;'>").append(util.checkNull(srHObj.getStrLastMtrRd())).append("</td>");
	buff.append("<td align='center' style='width:6%;'>").append(util.checkNull(srHObj.getStrRespTm())).append("</td>");
	buff.append("<td align='center' style='width:6%;'>").append(util.checkNull(srHObj.getStrRstrTm())).append("</td>");	
	buff.append("<td align='center' style='width:15%;'>").append(util.checkNull(srHObj.getStrTskPblmCd())).append("</td>");
	buff.append("<td align='center' style='width:15%;'>").append(util.checkNull(srHObj.getStrCorrectionCd())).append("</td>");
	buff.append("<td align='center' style='width:15%;'>").append(util.checkNull(srHObj.getStrLocationCd())).append("</td>");	
	buff.append("<td align='center' style='width:15%;'>").append(util.checkNull(srHObj.getStrRsnCd())).append("</td>");
	buff.append("<td align='center' style='width:8%;'>").append(util.checkNull(srHObj.getStrMachSts())).append("</td>");
	buff.append("<td align='center' style='width:8%;'>").append(util.checkNull(srHObj.getStrCreatedBy())).append("</td>");
	buff.append("<td></td>");
	buff.append("</tr>");
	buff.append("<tr style='display: none;' class='task_").append(util.checkNull(srHObj.getStrFSR())).append("'><td></td><td></td>");
	buff.append("<th>Task#</th>");
	buff.append("<th>Task Type</th>");
	buff.append("<th>Task Status</th>");
	buff.append("<th>Problem Code</th>");
	buff.append("<th>Problem Note</th>");
	buff.append("<th>Mobile Note</th>");
	buff.append("<th>Primary Meter</th>");
	buff.append("<th>Response Time</th>");
	buff.append("<th>Labor Start</th>");
	buff.append("<th>Labor End</th>");
	buff.append("<th>Problem Code</th>");
	buff.append("<th>Correction Code</th>");
	buff.append("<th>Location Code</th>");
	buff.append("<th>Reason Code</th>");
	buff.append("<th>Machine Status</th>");
	buff.append("<th>Technician</th>");

		if(tskLst!=null && tskLst.size()>0){
			//System.out.println("tskLst size 12: "+ tskLst.size());
			
			for(int k=0;k<tskLst.size();k++){
				CanonE307SRViewTskDtlsRec tskObj = (CanonE307SRViewTskDtlsRec)tskLst.get(k);
				//System.out.println("Task info: " + tskObj.toString());
				String tskLbrStrtTm = util.checkNull(utiSrObj.getTmZone(srHObj.getStrPostalCd(), srHObj.getStrCntryCd(), tskObj.getTskLbrStrtTm()));
				String tskLbrEndTm = util.checkNull(utiSrObj.getTmZone(srHObj.getStrPostalCd(), srHObj.getStrCntryCd(), tskObj.getTskLbrEndTm()));
				buff.append("	<tr style='display: none;' class='task_").append(util.checkNull(srHObj.getStrFSR())).append("'><td></td><td></td>");
				buff.append("	<td><a href=javascript:fnGetTaskInfo('").append(util.checkNull(srHObj.getStrFSR())).append("','").append(tskObj.getTaskNum()).append("','").append(srHObj.getStrPostalCd()).append("','").append(srHObj.getStrCntryCd()).append("','").append(srHObj.getStrEOLFlg()).append("') style='color: #cc0000;'>").append(util.checkNull(tskObj.getTaskNum())).append("</a></td>");
				buff.append(" <td>").append(util.checkNull(tskObj.getTskTpe())).append("</td>");
				buff.append(" <td>").append(util.checkNull(tskObj.getTskSts())).append("</td>");
				buff.append(" <td>").append(util.checkNull(tskObj.getTskPblmCd())).append("</td>");
				buff.append(" <td>").append(util.checkNull(tskObj.getTskPblmNt())).append("</td>");
				buff.append(" <td>").append(util.checkNull(tskObj.getMobileNt())).append("</td>");				
				buff.append(" <td>").append(util.checkNull(tskObj.getPrmryMtr())).append("</td>");
				buff.append(" <td>").append(util.checkNull(tskObj.getTskRespTm())).append("</td>");	
				buff.append(" <td>").append(tskLbrStrtTm).append("</td>");	
				buff.append(" <td>").append(tskLbrEndTm).append("</td>");	
				buff.append(" <td>").append(util.checkNull(tskObj.getPblmTpCd())).append("</td>");
				buff.append(" <td>").append(util.checkNull(tskObj.getCorCtnCd())).append("</td>");
				buff.append(" <td>").append(util.checkNull(tskObj.getTskLocTnCd())).append("</td>");
				buff.append(" <td>").append(util.checkNull(tskObj.getTskRsnCd())).append("</td>");
				buff.append(" <td>").append(util.checkNull(tskObj.getTskMachSts())).append("</td>");
				buff.append(" <td>").append(util.checkNull(tskObj.getTskTech())).append("</td>");
				buff.append("</tr>");
			}
		}
	
	
	
		}
	}
	buff.append("</table>");
	buff.append("</div> </div>");
	buff.append("<table><tr><td>&nbsp;</td></tr></table>");
	buff.append("<table><tr><td>&nbsp;</td></tr></table>");
	buff.append("<table><tr><td>&nbsp;</td></tr></table>");
	buff.append("<table><tr><td>&nbsp;</td></tr></table>");
	System.out.println("String SR View : " + buff.toString());
	response.getWriter().print(buff.toString());			
%>