<%@ page import="com.canon.apps.*" %>  
<%@ page import="java.text.*"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.canon.apps.servreq.dao.*" %>  
<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<style>
	 .ui-dialog .ui-dialog-title{
	 	float: none;
	 }
</style>
<script>
function fnSearchExpenseItem(){
	var url = 'canonE307DebriefExpenseItemLov.jsp';
	showPleaseWait();
    $.post(url
            ,$("#expenseItemLovFrm").serialize()
            ,function(data){
    		 hidePleaseWait();
			 $('#expenseItemDiv').html("");
             $('#expenseItemDiv').html(data);
            });
} 
$(document).ready(function(){
	$("#expenseItemLovFrm").submit(function (){
		  $("#expenseItem").focus();
		  fnSearchExpenseItem();
		   return false;
	});
});
</script>
<%
	Object[] result = null;
	int pageNum = (request.getParameter("pageNum")==null)?1:Integer.parseInt(request.getParameter("pageNum")); 
	int ct=25;
	int start = ((pageNum-1)*ct) + 1 ;
	int end= pageNum*ct;
	int totalRecords=0;
	int totalLinks =0;
%>
<form name="expenseItemLovFrm" id="expenseItemLovFrm" method="post" action="canonE307DebriefExpenseItemLov.jsp">
<%
	String strExpenseItemNumber = request.getParameter("expenseItem")==null?"":request.getParameter("expenseItem");
	String mName= request.getParameter("modalName");
	String strIval = request.getParameter("iVal");
%>
<table style="width: 100%;">
	<tr>
		<td>
			<table width="100%" class="border-no" align="center">
				<input type="hidden" id="modalName" name="modalName" value="<%=mName%>">
				<input type="hidden" id="iVal" name="iVal" value="<%=strIval%>">
				<tr>    
				  <td align="right"><b>Expense Item&nbsp;</b></td>
				  <td style="width:180px"><input id="expenseItem" name="expenseItem" value="<%=strExpenseItemNumber==null?"":strExpenseItemNumber%>"></td>    
		    	  <td><a class="btn" style="white-space: nowrap;" href="javascript:void fnSearchExpenseItem()">Search</a></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table class="supplies-table" width="100%">
				<tr>
					<th width="10%">Select</th>
					<th width="30%">Expense Item</th>
					<th width="60%">Description</th>
				</tr>
				<%
					List lstExpenseItems= new ArrayList();
					CanonE307DebriefDetailsDAO expObj = new CanonE307DebriefDetailsDAO();
					result = expObj.getItemDetailsLov("EXPENSE",strExpenseItemNumber, start, end, "");
					if(result != null && result.length>1){
						lstExpenseItems=(ArrayList)result[0];
					   	Integer intObj=(Integer)result[1];
						if(intObj!=null){
							totalRecords = intObj.intValue();
						}
					}
					totalLinks = (totalRecords%ct>0)?((totalRecords/ct)+1):totalRecords/ct;	
					if(lstExpenseItems!=null && lstExpenseItems.size()>0){
						for(int i=0;i<lstExpenseItems.size();i++){
							CanonE307DebriefItemLov expenseBeanObj = (CanonE307DebriefItemLov)lstExpenseItems.get(i);	
				%>
				<tr>
					<td align="center"> <input type=radio name="selectedDet" id="selectedDet<%=i%>" value="<%=i%>" onclick="setExpenseItemDet('<%=mName%>','<%=strIval%>','<%=expenseBeanObj.getStrItmNm() %>','<%=expenseBeanObj.getStrItmDesc() %>','<%=expenseBeanObj.getStrStrtDt() %>','<%=expenseBeanObj.getStrUom()==null?"":expenseBeanObj.getStrUom()%>', '<%=expenseBeanObj.getStrItmCnt() %>')" /></td>
					<td><%=expenseBeanObj.getStrItmNm() %></td>
					<td><%=expenseBeanObj.getStrItmDesc() %></td>
				</tr>	
			<%	
				}
			}else{
			%>
			<tr class='eventableDataCell'>
			<td colspan=3> No data found for the search criteria.	
			</td>
			</tr>
			<%	
			}
			%>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table  style="font-size: 11px;width:100%;" id="pgLinks">
				<tr>
					<td align="left" nowrap>
						<div id="paging">
						<%
						 int nop=totalLinks;
						 
						 if(nop==0){ // no rows
							 
						 }else if(0<nop  && nop<=1){
							 
							 
						 }else if(nop>1){
						
							 if(nop>10 ){
							  
								 if(pageNum>2){
										%>
										 <a href="#" onclick="getExpenseItemLinks('1','<%=strExpenseItemNumber%>','<%=mName%>','<%=strIval%>');">First</a>
										<% 
									 }
								 if(pageNum>1){
									 
									%>
									 <a href="#" onclick="getExpenseItemLinks('<%=(pageNum-1) %>','<%=strExpenseItemNumber%>','<%=mName%>','<%=strIval%>');"> Prev</a>
									<% 
								 }
								 
								 for(int k=pageNum;k<(pageNum+10) && k<=totalLinks ;k++){
								  %>
								   <a  id="a<%=k %>" href="#" onclick="getExpenseItemLinks('<%=k %>','<%=strExpenseItemNumber%>','<%=mName%>','<%=strIval%>');"><%=k %></a>
						 		  <% 
							     }
								 
								 if( (pageNum+1) <= nop){
									 %>
									  <a href="#" onclick="getExpenseItemLinks('<%= (pageNum+1) %>','<%=strExpenseItemNumber%>','<%=mName%>','<%=strIval%>');">Next</a>
									 <%
								 }
								 %>
								  <a href="#" onclick="getExpenseItemLinks('<%= nop %>','<%=strExpenseItemNumber%>','<%=mName%>','<%=strIval%>');">Last</a>
								 <%
							 }
							 if(nop<=10){
								 for(int k=1;k<=nop;k++){
									%>
									 <a id="a<%=k %>" href="#" onclick="getExpenseItemLinks('<%= k %>','<%=strExpenseItemNumber%>','<%=mName%>','<%=strIval%>');"><%= k %></a>
									<% 
								 }
							 }
						 }else{
							 
						 }
						 
						 %>
						 </div>
					</td>
				</tr>
			 </table>
		 </td>
	</tr>
</table>
 <br>
 <table style= "width:100%"><tr align="right"><td align="right"><a class="btn" style="white-space: nowrap;" href="javascript:void fnCloseMdlDlg('<%=mName%>')">Close</a></td></tr></table>
</form>
