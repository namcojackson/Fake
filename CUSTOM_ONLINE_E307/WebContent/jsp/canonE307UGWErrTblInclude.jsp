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
		String strCustName = request.getParameter("imgCustomerName")==null?"":request.getParameter("imgCustomerName");
		String strSrlTagNum = request.getParameter("imgSerialTagNumber")==null?"":request.getParameter("imgSerialTagNumber");
		String strModel = request.getParameter("imgModel")==null?"":request.getParameter("imgModel");
		String strBranch = request.getParameter("imgBranchName")==null?"":request.getParameter("imgBranchName");
		String sortBy = request.getParameter("sortBy"); 
		String sortOrder = request.getParameter("sortOrder");
		int pageNumber = (request.getParameter("pageNumber")==null)?1:Integer.parseInt(request.getParameter("pageNumber"));
		//System.out.println("UGW sortBy : " + sortBy+" sortOrder: "+sortOrder+" pageNumber: "+pageNumber);
		//System.out.println("strCustName : " + strCustName + " strSrlTagNum : " + strSrlTagNum+" strModel: "+strModel +" strBranch : "+strBranch);
		int count=10; 
		int start = ((pageNumber-1)*count) + 1 ;
		int end= pageNumber*count;
		String contextPath =request.getContextPath();
		int totalLks=0;
		String fnGetUgw="fnGetUGWDtls";
		CanonE307ServiceRequestSearchDao srchDao = new CanonE307ServiceRequestSearchDao();
		Object[] obj = srchDao.getUGWErrCodeDtls(util.checkNull(strCustName), util.checkNull(strSrlTagNum), util.checkNull(strModel), util.checkNull(strBranch), start, end, sortBy, sortOrder);
		int totalRecords = 0;
		if(obj[0]!=null){
			totalRecords = ((Integer)obj[0]).intValue();
		}
		ArrayList<CanonE307UGWErrCodeRec> arList = new ArrayList<CanonE307UGWErrCodeRec>();
		if(obj[1]!=null){
		 	arList = (ArrayList<CanonE307UGWErrCodeRec>) obj[1];
		}
		totalLks = (totalRecords%count>0)?((totalRecords/count)+1):totalRecords/count;		
		String pageLinkMsg="";
 	   
	    if(totalLks>1){
	    	  pageLinkMsg= start+" to "+(end-(count-arList.size())) +" of "+ totalRecords +" records found.";
	    }
	    String strErrStyle="align:center;";
	    if(arList.size()>10){
	    	strErrStyle="height: 400px;  overflow-y:auto;align:center;";
	    }
	%>
				<div class="table-inner">
					<table class="supplies-table" cellspacing="1">				
						<tr>
							<th width="12%"><a href="Javascript:fnSortFilterCriteria('SERIAL_NUMBER')">Serial#</a></th>
							<th width="12%"><a href="Javascript:fnSortFilterCriteria('TAG_NUMBER')">Tag#</a></th>
							<th width="22%"><a href="Javascript:fnSortFilterCriteria('PARTY_NAME')"> Customer Name</a></th>
							<th width="15%"><a href="Javascript:fnSortFilterCriteria('ERROR_DATE')">Error Date</a></th>
							<th width="12%">SR History</th>
							<th width="12%"><a href="Javascript:fnSortFilterCriteria('MODEL')">Model</a></th>
							<th width="15%"><a href="Javascript:fnSortFilterCriteria('BRANCH')">Branch</a></th>				
						</tr>
						<%
						if(arList!=null &arList.size()>0){
							for(CanonE307UGWErrCodeRec assBean : arList){
								//System.out.println("SerNum: "+ assBean.getStrSerNum() +"POstal Code: "+assBean.getStrPostalCd()+"ctryCd: "+assBean.getStrCtryCd()+" Error Date: "+assBean.getStrErrDt());
						%>
						<tr>
							<td  nowrap style="color:#000000;"><a href="javascript:fnSelectSerial('<%=assBean.getStrSerNum() %>','<%=assBean.getStrMachMstrPk() %>','<%=assBean.getStrModel() %>')"><%=util.checkNull(assBean.getStrSerNum()) %></a></td>
							<td nowrap ><%=util.checkNull(assBean.getStrTagNum()) %></td>
							<td ><%=util.checkNull(assBean.getStrCustName()) %></td>
							<td ><%=util.checkNull(utilObj.getTmZone(assBean.getStrPostalCd(), assBean.getStrCtryCd(), assBean.getStrErrDt())) %></td>
							<td ><a href="javascript:fnServReqHist('<%=contextPath%>/e307/jsp/canonE307ServiceReqHistory.jsp?serialNumber=<%=assBean.getStrSerNum() %>')">Click Here</a></td>
							<td ><%=util.checkNull(assBean.getStrModel()) %></td>
							<td nowrap><%=util.checkNull(assBean.getStrBranch()) %></td>
						</tr>
						<%	
							 }
						}						
						%>
						</table>
						<table width="100%" id="pgLinks" cellspacing="0">
					   <tr>
						    <td width="20%" id="paginationUGW">
								<div id="paginationUGW">
								<%
								int pageNum = pageNumber;
								
								int nop=totalLks;
								 
								 if(nop==0){ // no rows
									 
								 }else if(0<nop  && nop<=1){
									 
									 
								 }else if(nop>1){
								
									 if(nop>10 ){
									  
										 if(pageNum>2){
											 
												%>
												 <a href="javascript:<%=fnGetUgw%>('1');">First</a>
												<% 
											 }
										 if(pageNum>1){
											%>
											 <a href="javascript:<%=fnGetUgw%>('<%=(pageNum-1) %>');"> Prev</a>
											<% 
										 }
										 int temp=pageNum;
										 if(temp>5)
										  pageNum-=5;
										
										 for(int k=pageNum;k<(pageNum+10) && k<=totalLks ;k++){
										  %>
										   <a  id="a<%=k %>" href="javascript:<%=fnGetUgw%>('<%=k %>');"><%=k %></a>
								 		  <% 
									     }
										 if(temp>5)
											  pageNum+=5;
										 
										 if( (pageNum+1) <= nop){
											 %>
											  <a href="javascript:<%=fnGetUgw%>('<%= (pageNum+1) %>');">Next</a>
											 <%
										 }
										 %>
										  <a href="javascript:<%=fnGetUgw%>('<%= nop %>');">Last</a>
										 <%
									 }
									 
									 if(nop<=10){
										 for(int k=1;k<=nop;k++){
											%>
											 <a id="a<%=k %>" href="javascript:<%=fnGetUgw%>('<%= k %>');"><%= k %></a>
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
				  </div>
