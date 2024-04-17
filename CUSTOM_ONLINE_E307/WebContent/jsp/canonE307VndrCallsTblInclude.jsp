<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<%@ page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestSearchDao" %> 
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@ page import="java.text.*"%> 
<%@ page import="java.util.*"%>
<%@page import="java.util.Date"%> 
	<%
	  CanonCommonUtil util = new CanonCommonUtil(); 
	  CanonE307ServiceReqSumryAPIUtil utilObj = new CanonE307ServiceReqSumryAPIUtil();
	  CanonE307ServiceRequestSearchDao objDet = new CanonE307ServiceRequestSearchDao();
	  String strVendCustNm = request.getParameter("vendCustNm");
	  String strVendSts = request.getParameter("vendSts");
	  String strVendAssgn = request.getParameter("vendAssgn");
	  int pageNumber = (request.getParameter("pageNumber")==null)?1:Integer.parseInt(request.getParameter("pageNumber"));
	  int count=10; 
	  int vStart = ((pageNumber-1)*count) + 1 ;
	  int vEnd= pageNumber*count;
	  int totalLks=0;
	  String fnGetVndr="fnGetVendrDtls";
	  
	  String vSortBy="";
	  String vSortOrder="";

		if(request.getParameter("vSortBy")!=null){
			vSortBy = request.getParameter("vSortBy"); 
		}
		if(request.getParameter("vSortOrder")!=null){
			vSortOrder = request.getParameter("vSortOrder");
		}		
	  
	 System.out.println("vSortBy : " + vSortBy+ " vSortOrder : "  + vSortOrder+" strVendCustNm: "+strVendCustNm);
	  Object[] objVend = objDet.getThirdPartyCalls(vStart, vEnd, vSortBy, vSortOrder, strVendCustNm, strVendSts, strVendAssgn);
	  int vendTotalRec = 0;
	  if(objVend[0]!=null){
		  vendTotalRec = ((Integer)objVend[0]).intValue();
	  }
	  ArrayList<CanonE307ServReqVendCallRec> arVndrList = new ArrayList<CanonE307ServReqVendCallRec>();
	  if(objVend[1]!=null){
		  arVndrList = (ArrayList<CanonE307ServReqVendCallRec>) objVend[1];
	  }	
	  totalLks = (vendTotalRec%count>0)?((vendTotalRec/count)+1):vendTotalRec/count;		
	  String pageLinkMsg="";
	   
	  if(totalLks>1 && arVndrList!=null){
	    	pageLinkMsg= vStart+" to "+(vEnd-(count-arVndrList.size())) +" of "+ vendTotalRec +" records found.";
	  }
	 
	  String strVendStyle="align:center;";
	//  if(arVndrList!=null && arVndrList.size()>5){
		 // strVendStyle="height: 300px;  overflow-y:auto;align:center;";
	//  }
		String contextVPath =request.getContextPath();

%>

	<table id=scrollVendTable class="supplies-table" cellspacing="1">
		<thead>
		<tr>
				<th width="11%"><a href="Javascript:fnSortFltrVendCriteria('SER_NUM')">Serial#</a></th>
				<th width="10%"><a href="Javascript:fnSortFltrVendCriteria('TAG_NUM')">Tag#</a></th>
				<th width="23%"><a href="Javascript:fnSortFltrVendCriteria('CUST_NM')">Customer Name</a></th>
				<th width="9%"><a href="Javascript:fnSortFltrVendCriteria('SVC_TASK_NUM')">Task#</a></th>
				<th width="10%"><a href="Javascript:fnSortFltrVendCriteria('CREATION_DT')">Creation Date</a></th>
				<th width="9%"><a href="Javascript:fnSortFltrVendCriteria('TASK_TYPE')">SR Type</a></th>
				<th width="13%"><a href="Javascript:fnSortFltrVendCriteria('ASSIGNEE_NAME')">Assignee</a></th>
				<th width="9%"><a href="Javascript:fnSortFltrVendCriteria('MODEL')">Model</a></th>
				<th width="6%"><a href="Javascript:fnSortFltrVendCriteria('STATUS')">Status</a></th>				
			</tr>
		</thead>
		<tbody>
			<%
				if(arVndrList!=null && arVndrList.size()>0){
					for(CanonE307ServReqVendCallRec assBean : arVndrList){
			%>
			<tr id= 'thirdPartyTableRow'>
				<td width="11%"><%=util.checkNull(assBean.getStrSerNum()) %></td>
				<td width="10%"><%=util.checkNull(assBean.getStrTagNum()) %></td>
				<td width="23%"><%=util.checkNull(assBean.getStrCustNm()) %></td>
				<td width="9%"><a href="javascript:fnGetVndrCalls('<%=contextVPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?showVend=Y&taskNum=<%=assBean.getSrTskNum()%>')"><%=util.checkNull(assBean.getSrTskNum()) %></a></td>
				<td width="10%"><%=util.checkNull(utilObj.getTmZone(assBean.getStrPostalCd(), assBean.getStrCntryCd(), assBean.getStrFutureDt())) %></td>
				<td width="9%"><%=util.checkNull(assBean.getStrTskTpe()) %></td>
				<td width="13%"><%=util.checkNull(assBean.getStrAssignee()) %></td>	
				<td width="9%"><%=util.checkNull(assBean.getStrModel()) %></td>
				<td width="6%"><%=util.checkNull(assBean.getStrStatus()) %></td>				
			</tr>
			<%
				}
			}
			%>
			</tbody>
		</table>
		<table width="100%" id="pgLinks" cellspacing="0">
			   <tr>
				    <td width="30%" id="paginationVnd">
							<div id="paginationVnd">
							<%
							int pageNum = pageNumber;
							
							int nop=totalLks;
							 
							 if(nop==0){ // no rows
								 
							 }else if(0<nop  && nop<=1){
								 
								 
							 }else if(nop>1){
							
								 if(nop>10 ){
								  
									 if(pageNum>2){
										 
											%>
											 <a href="javascript:<%=fnGetVndr%>('1');">First</a>
											<% 
										 }
									 if(pageNum>1){
										%>
										 <a href="javascript:<%=fnGetVndr%>('<%=(pageNum-1) %>');"> Prev</a>
										<% 
									 }
									 int temp=pageNum;
									 if(temp>5)
									  pageNum-=5;
									
									 for(int k=pageNum;k<(pageNum+10) && k<=totalLks ;k++){
									  %>
									   <a  id="a<%=k %>" href="javascript:<%=fnGetVndr%>('<%=k %>');"><%=k %></a>
							 		  <% 
								     }
									 if(temp>5)
										  pageNum+=5;
									 
									 if( (pageNum+1) <= nop){
										 %>
										  <a href="javascript:<%=fnGetVndr%>('<%= (pageNum+1) %>');">Next</a>
										 <%
									 }
									 %>
									  <a href="javascript:<%=fnGetVndr%>('<%= nop %>');">Last</a>
									 <%
								 }
								 
								 if(nop<=10){
									 for(int k=1;k<=nop;k++){
										%>
										 <a id="a<%=k %>" href="javascript:<%=fnGetVndr%>('<%= k %>');"><%= k %></a>
										<% 
										 
									 }
								 }
							 }else{
								 
							 }
							 
							 %>
							</div>							   
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
