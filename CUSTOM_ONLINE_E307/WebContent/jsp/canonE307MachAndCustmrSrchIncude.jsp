<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<%@ page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestSearchDao" %> 
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@ page import="java.text.*"%> 
<%@ page import="java.util.*"%>
<%@page import="java.util.Date"%> 

	<%
	  CanonCommonUtil util = new CanonCommonUtil();  
	  CanonE307MachineCustSearchRec custSearchObj = new CanonE307MachineCustSearchRec();
	  custSearchObj.setStrSerialTagNumber(request.getParameter("serialTagNumber")==null?"":request.getParameter("serialTagNumber"));
	  custSearchObj.setStrSolutionName(request.getParameter("solutionName")==null?"": request.getParameter("solutionName"));
	  custSearchObj.setStrAccountNumber(request.getParameter("accountNumber")==null?"":request.getParameter("accountNumber"));
	  custSearchObj.setStrCustomerName(request.getParameter("customerName")==null?"": request.getParameter("customerName"));
	  custSearchObj.setStrAddress(request.getParameter("custAddress")==null?"":request.getParameter("custAddress"));
	  custSearchObj.setStrCity(request.getParameter("custCity")==null?"":request.getParameter("custCity"));
	  custSearchObj.setStrState(request.getParameter("custState")==null?"":request.getParameter("custState"));
	  custSearchObj.setStrPostalCode(request.getParameter("custPostalCode")==null?"":request.getParameter("custPostalCode"));
	  custSearchObj.setStrPhone1(request.getParameter("custPhoneNumber")==null?"":request.getParameter("custPhoneNumber"));
	  custSearchObj.setStrSortBy(request.getParameter("sortBy")==null?"":request.getParameter("sortBy"));
	  custSearchObj.setStrSortOrder(request.getParameter("sortOrder")==null?"":request.getParameter("sortOrder"));
	  
		int pageNumber = (request.getParameter("pageNumber")==null)?1:Integer.parseInt(request.getParameter("pageNumber"));
		//System.out.println("Search sortBy : " + request.getParameter("sortBy")+" sortOrder: "+request.getParameter("sortOrder")==null?"":request.getParameter("sortOrder"));

		int count=10; 
		int start = ((pageNumber-1)*count) + 1 ;
		int end= pageNumber*count;
		String contextPath =request.getContextPath();
		int totalLks=0;
		String fnGetRes="fnGetMachSrchRes";
		CanonE307ServiceRequestSearchDao srchDao = new CanonE307ServiceRequestSearchDao();
		
		Object[] obj = srchDao.getMachineSearchResults(custSearchObj, start, end);
		
	//	Object[] obj = srchDao.getUGWErrCodeDtls(util.checkNull(strCustName), util.checkNull(strSrlTagNum), util.checkNull(strModel), util.checkNull(strBranch), start, end, sortBy, sortOrder);
		int totalRecords = 0;
		if(obj[0]!=null){
			totalRecords = ((Integer)obj[0]).intValue();
		}
		ArrayList<CanonE307MachineCustSearchResultsRec> lstSearchRes = new ArrayList<CanonE307MachineCustSearchResultsRec>();
		if(obj[1]!=null){
			lstSearchRes = (ArrayList<CanonE307MachineCustSearchResultsRec>) obj[1];
		}
		totalLks = (totalRecords%count>0)?((totalRecords/count)+1):totalRecords/count;		
		String pageLinkMsg="";
 	   
	    if(totalLks>1){
	    	  pageLinkMsg= start+" to "+(end-(count-lstSearchRes.size())) +" of "+ totalRecords +" records found.";
	    }
	    System.out.println("pageLinkMsg : " + pageLinkMsg);
	    String strErrStyle="align:center;";
	    if(lstSearchRes.size()>10){
	    	strErrStyle="height: 400px;  overflow-y:auto;align:center;";
	    }
	%>
		<div class="table-inner">
			<table width="100%" id="pgLinks" cellspacing="0">
				<tr>
					  <td width="50%" id="pagination">
				           <jsp:include page="canonE307ServiceReqPgIncl.jsp">
						   <jsp:param value="<%=totalLks %>" name="totalLinks"/>
						   <jsp:param value="<%=pageNumber %>" name="pageNum"/>
						   <jsp:param value="<%=fnGetRes %>" name="fn"/>
						   </jsp:include>
					  </td>
					  <%
					  if(pageLinkMsg!="" && pageLinkMsg!=null){
					  %>
						 <td width="50%" align="right" id="showing"><b>Showing</b>&nbsp;<%=pageLinkMsg %></td>  
					  <%
					  }
					  %>
			   </tr>
		  </table>
		<table class="supplies-table" cellspacing="1">
			<tr >
				<th style="width:11%;"><a style="color:#FFFFFF;text-decoration: underline; text-align:left;" href="Javascript:fnSortFilterCriteria('SER_NUM','<%=pageNumber %>')">Serial#</a></th>
				<th style="width:8%;"><a style="color:#FFFFFF;text-decoration: underline; text-align:left;" href="Javascript:fnSortFilterCriteria('CUST_MACH_CTRL_NUM','<%=pageNumber %>')">Tag#</a></th>
				<th style="width:8%;"><a style="color:#FFFFFF;text-decoration: underline; text-align:left;" href="Javascript:fnSortFilterCriteria('Model','<%=pageNumber %>')">Model</a></th>
				<th style="width:11%;"><a style="color:#FFFFFF;text-decoration: underline; text-align:left;" href="Javascript:fnSortFilterCriteria('SOLUTION_NAME','<%=pageNumber %>')">Solution Name</a></th>
				<th style="width:8%;"><a style="color:#FFFFFF;text-decoration: underline; text-align:left;" href="Javascript:fnSortFilterCriteria('SELL_TO_CUST_CD','<%=pageNumber %>')">Account#</a></th>
				<th style="width:15%;"><a style="color:#FFFFFF;text-decoration: underline; text-align:left;" href="Javascript:fnSortFilterCriteria('SHIP_TO_CUST_NAME','<%=pageNumber %>')">Customer Name</a></th>
				<th style="width:17%;"><a style="color:#FFFFFF;text-decoration: underline; text-align:left;" href="Javascript:fnSortFilterCriteria('ADDRESS','<%=pageNumber %>')">Address</a></th>
				<th style="width:11%;"><a style="color:#FFFFFF;text-decoration: underline; text-align:left;" href="Javascript:fnSortFilterCriteria('CONTACTNAME','<%=pageNumber %>')">Contact</a></th>
				<th style="width:11%;"><a style="color:#FFFFFF;text-decoration: underline; text-align:left;" href="Javascript:fnSortFilterCriteria('LASTSERVICE','<%=pageNumber %>')">Last Service</a></th>
			</tr>
			<%
			if(lstSearchRes!=null && lstSearchRes.size()>0){
				for(int i=0;i<lstSearchRes.size();i++){
					CanonE307MachineCustSearchResultsRec resultObj = (CanonE307MachineCustSearchResultsRec)lstSearchRes.get(i);
			%>
			<tr>
				<td style="width:11%;" nowrap><a href="javascript:fnSelectCreateSR('<%=util.checkNull(resultObj.getSerNum())%>','<%=util.checkNull(resultObj.getSvcMachMstrPk())%>','<%=util.checkNull(resultObj.getModel())%>','<%=util.checkNull(resultObj.getCrtSRFlg())%>', '<%=util.checkNull(resultObj.getHardHoldFlg() )%>', '<%=util.checkNull(resultObj.getCrossSrvcFlg() )%>')"><%=util.checkNull(resultObj.getSerNum())%></a>
					<input type="hidden" name="selSerialNumber<%=i%>" id="selSerialNumber<%=i%>" value="<%=util.checkNull(resultObj.getSerNum())%>"></td>
				<td style="width:8%;"><%=util.checkNull(resultObj.getCustMachCtrlNum()) %>
					<input type="hidden" name="selTagNumber<%=i%>" id="selTagNumber<%=i%>" value="<%=util.checkNull(resultObj.getCustMachCtrlNum()) %>"></td>
				<td style="width:8%;"><%=util.checkNull(resultObj.getModel()) %>
					<input type="hidden" name="selModel<%=i%>" id="selModel<%=i%>" value="<%=util.checkNull(resultObj.getModel())%>">
				</td>					
				<td style="width:11%;"><%=util.checkNull(resultObj.getSolutionName()) %>
					<input type="hidden" name="selSolutionName<%=i%>" id="selSolutionName<%=i%>" value="">	
				</td>
				<td style="width:8%;"><%=util.checkNull(resultObj.getSellToCustNo()) %>
					<input type="hidden" name="selAccountNumber<%=i%>" id="selAccountNumber<%=i%>" value="">
				</td>
				<td style="width:15%;"><%=util.checkNull(resultObj.getShipToCustName()) %>
					<input type="hidden" name="selCustomerName<%=i%>" id="selCustomerName<%=i%>" value="">
				</td>
				<td style="width:17%;"><%=util.checkNull(resultObj.getShipToAddress1()) %>
					<input type="hidden" name="selAddress<%=i%>" id="selAddress<%=i%>" value="<%=util.checkNull(resultObj.getShipToAddress1()) %>">	
				</td>
				<td style="width:11%;"><%=util.checkNull(resultObj.getContact()) %>
					<input type="hidden" name="selContact<%=i%>" id="selContact<%=i%>" value="<%=util.checkNull(resultObj.getContact())%>">	
				</td>
				<td style="width:11%;"><%=util.formatDateString(util.DT_FORMAT3,util.DT_FORMAT5,resultObj.getLastServiceCallDt())%>
				<input type="hidden" name="selLastService<%=i%>" id="selLastService<%=i%>" value="<%=util.checkNull(resultObj.getLastServiceCallDt()) %>">	
				<input type="hidden" name="selMachPk<%=i%>" id="selMachPk<%=i%>" value="<%=util.checkNull(resultObj.getSvcMachMstrPk()) %>">	
				<input type="hidden" name="selSaleDate<%=i%>" id="selSaleDate<%=i%>" value="">	
				
				</td>
			</tr>	
			<%		
				}
			}else{
			%>
				<tr class='eventableDataCell'><td colspan=10  align="center"> No data found for this criteria.</td></tr>
			<%
			}
			%>
		</table>
  </div>
