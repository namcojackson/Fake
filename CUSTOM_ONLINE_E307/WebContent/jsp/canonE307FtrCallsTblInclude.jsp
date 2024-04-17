<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<%@ page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestSearchDao" %> 
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@ page import="java.text.*"%> 
<%@ page import="java.util.*"%>
<%@page import="java.util.Date"%> 
<%
CanonE307ServiceRequestSearchDao objDet = new CanonE307ServiceRequestSearchDao();
CanonE307ServiceReqSumryAPIUtil utilObj = new CanonE307ServiceReqSumryAPIUtil();
CanonCommonUtil util=new CanonCommonUtil();
String fCtxtPath =request.getContextPath();

String strFtrCustmr=request.getParameter("ftrCustmr");
String strFtrDate=request.getParameter("ftrDate");
String strFtrBrnch = request.getParameter("ftrBrnch");
String fSortBy = request.getParameter("fSortBy");
String fSortOrder = request.getParameter("fSortOrder");
String fCallDivDis =request.getParameter("fCallDiv")==null?"":request.getParameter("fCallDiv");
int pageNumber = (request.getParameter("pageNumber")==null)?1:Integer.parseInt(request.getParameter("pageNumber"));
int count=10; 
int fStart = ((pageNumber-1)*count) + 1 ;
int fEnd= pageNumber*count;
int totalLks=0;
String fnGetFtrCls="fnGetFtrDtls";

System.out.println("fSortBy : " + fSortBy+ "fSortOrder : " +fSortOrder);
Object[] obj = objDet.getFutureCalls(fStart, fEnd, fSortBy, fSortOrder, util.checkNull(strFtrBrnch), util.checkNull(strFtrDate), util.checkNull(strFtrCustmr));
int totalRecords = 0;
ArrayList<CanonE307ServReqFutureCallRec> arFtrList = new ArrayList<CanonE307ServReqFutureCallRec>();
if(obj[1]!=null){
	  arFtrList = (ArrayList<CanonE307ServReqFutureCallRec>) obj[1];
}
if(obj[0]!=null){
	totalRecords = ((Integer)obj[0]).intValue();
}
totalLks = (totalRecords%count>0)?((totalRecords/count)+1):totalRecords/count;	
String pageLinkMsg="";

if(totalLks>1 && arFtrList!=null){
  	pageLinkMsg= fStart+" to "+(fEnd-(count-arFtrList.size())) +" of "+ totalRecords +" records found.";
}

%>

	<table class="supplies-table" id="scrollFtrCallDiv" cellspacing="1">
		<thead>
			<tr>
				<th width="11%"><a href="Javascript:fnSortFilterFtreCall('SER_NUM')">Serial#</a></th>
				<th width="9%"><a href="Javascript:fnSortFilterFtreCall('TAG_NUM')">Tag#</a></th>
				<th width="20%"><a href="Javascript:fnSortFilterFtreCall('CUST_NM')">Customer Name</a></th>
				<th width="10%"><a href="Javascript:fnSortFilterFtreCall('SVC_TASK_NUM')">Task#</a></th>
				<th width="10%"><a href="Javascript:fnSortFilterFtreCall('FUTURE_DT')">Future Date</a></th>
				<th width="10%"><a href="Javascript:fnSortFilterFtreCall('TASK_TYPE')">SR Type</a></th>
				<th width="15%"><a href="Javascript:fnSortFilterFtreCall('ASSIGNEE_NAME')">Assignee</a></th>
				<th width="8%"><a href="Javascript:fnSortFilterFtreCall('MODEL')">Model</a></th>
				<th width="7%"><a href="Javascript:fnSortFilterFtreCall('BRANCH')">Branch</a></th>				
			</tr>
		</thead>
 		<tbody>
			<%
 			if(arFtrList!=null &arFtrList.size()>0){
 				for(CanonE307ServReqFutureCallRec assBean : arFtrList){
			%>
 			<tr id= 'futureCalltableRow'> 
				<td  nowrap ><%=util.checkNull(assBean.getStrSerNum()) %></td>
				<td><%=util.checkNull(assBean.getStrTagNum()) %></td>
				<td ><%=util.checkNull(assBean.getStrCustNm()) %></td>
				<td ><a href="javascript:fnGetSRDtls('<%=fCtxtPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?showVend=Y&taskNum=<%=assBean.getSrTskNum()%>')"><%=util.checkNull(assBean.getSrTskNum()) %></a></td>
				<td ><%=util.checkNull(utilObj.getTmZone(assBean.getStrPostalCd(), assBean.getStrCntryCd(), assBean.getStrFutureDt())) %></td>
				<td ><%=util.checkNull(assBean.getStrTskTpe()) %></td>
				<td ><%=util.checkNull(assBean.getStrAssignee()) %></td>	
				<td ><%=util.checkNull(assBean.getStrModel()) %></td>
				<td ><%=util.checkNull(assBean.getStrBranch()) %></td>				
 			</tr> 
			<%
 				}
 			}
			%>
 		</tbody>
	</table>

		<table width="100%" id="pgLinks" cellspacing="0">
			   <tr>
				    <td width="30%" id="pagination">
					         <jsp:include page="canonE307ServiceReqPgIncl.jsp">
							   <jsp:param value="<%=totalLks %>" name="totalLinks"/>
							   <jsp:param value="<%=pageNumber %>" name="pageNum"/>
							   <jsp:param value="<%=fnGetFtrCls %>" name="fn"/>
							   </jsp:include>
					</td>
					 <%
					 	if(pageLinkMsg!="" && pageLinkMsg!=null){
					%>
						 <td width="80%" align="right" id="showing"><b>Showing</b><%=pageLinkMsg %></td>							
					<%
					 	}
					 %> 
				   </tr>
		</table>