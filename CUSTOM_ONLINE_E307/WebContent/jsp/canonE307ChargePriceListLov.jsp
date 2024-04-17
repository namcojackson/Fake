
<%@ page import="com.canon.apps.*" %>  
<%@ page import="java.text.*"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.canon.apps.servreq.dao.*" %>  
<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<%@ page import="com.canon.apps.servreq.dao.CanonE307DebriefDetailsDAO" %>  	
<style>
	 .ui-dialog .ui-dialog-title{
	 	float: none;
	 }
</style>
<script>
function fnSearchItem(){
	var url = 'canonE307ChargePriceListLov.jsp';
    $.post(url
            ,$("#prcLovFrm").serialize()
            ,function(data){
			 $('#chargeItemDiv').html("");
             $('#chargeItemDiv').html(data);
            });
} 
$(document).ready(function(){
	$("#prcLovFrm").submit(function (){
		  $("#search").focus();
		  searchItem();
		   return false;
	});
});
</script>

<form name="prcLovFrm" id="prcLovFrm" method="post" action="canonE307ChargePriceListLov.jsp">
<%
	String ctxPath = request.getContextPath();
	Object[] result = null;
	int pageNum = (request.getParameter("pageNum")==null)?1:Integer.parseInt(request.getParameter("pageNum"));
	String prcCatgNm = request.getParameter("prcCatgNm")==null?"":request.getParameter("prcCatgNm");
	int ct=25;
	int start = ((pageNum-1)*ct) + 1 ;
	int end= pageNum*ct;
	int totalRecords=0;
	int totalLinks =0;

	String mName= request.getParameter("modalName");
	SimpleDateFormat sdf = new SimpleDateFormat("dd'-'MMM'-'yyyy");
%>

	<table width="100%" class="border-no" align="center">
		<input type="hidden" id="modalName" name="modalName" value="<%=mName%>">
	  <tr>    
	    <td align="right"><b>Price List&nbsp;</b></td>
	    <td style="width:180px" class="search"><input id="prcCatgNm" name="prcCatgNm" value="<%=prcCatgNm%>"></td>  
	    <td><a class="btn" style="white-space: nowrap;" href="javascript:void fnSearchItem()">Search</a></td>
	  </tr>
	</table>
<br>
<%
    CanonE307ChargesDetailsDAO dtlObj = new CanonE307ChargesDetailsDAO();
	List lstPrcItems=new ArrayList();
	System.out.println("prcCatgNm LOV : "+ prcCatgNm);
	result=	dtlObj.getPriceList(start, end, prcCatgNm);
	if(result != null && result.length>1){
		lstPrcItems=(ArrayList)result[0];
	   	Integer intObj=(Integer)result[1];
		if(intObj!=null){
			totalRecords = intObj.intValue();
			System.out.println("total Records in Price List: "+ totalRecords);
		}
	}
	totalLinks = (totalRecords%ct>0)?((totalRecords/ct)+1):totalRecords/ct;
%>
<table class="supplies-table" style="width:100%">
<tr>
<th width="10%">Select</th>
<th width="30%">Item</th>
<th width="60%">Description</th>
</tr>
<%
if(lstPrcItems!=null && lstPrcItems.size()>0){
	for(int i=0;i<lstPrcItems.size();i++){
		CanonE307ChargesChngRsnRec prcBeanObj = (CanonE307ChargesChngRsnRec)lstPrcItems.get(i);	
%>
<tr>
<td align="center"> <input type=radio name="selectedDet" id="selectedDet<%=i%>" value="<%=i%>" onclick="setPrcLstDet('<%=mName%>','<%=prcBeanObj.getStrReasonCode() %>','<%=prcBeanObj.getStrReasonNm() %>')" /></td>
<td><%=prcBeanObj.getStrReasonCode() %></td>
<td><%=prcBeanObj.getStrReasonNm() %></td>
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
<br>
<br>
<table align="center">
<div id="paging">
<%
 
 int nop=totalLinks;
 
 
 if(nop==0){ // no rows
	 
 }else if(0<nop  && nop<=1){
	 
	 
 }else if(nop>1){

	 if(nop>10 ){
	  
		 if(pageNum>2){
				%>
				 <a href="#" onclick="getPrcCatgLinks('1','<%=mName%>','<%=prcCatgNm %>');">First</a>
				<% 
			 }
		 if(pageNum>1){
			 
			%>
			 <a href="#" onclick="getPrcCatgLinks('<%=(pageNum-1) %>','<%=mName%>','<%=prcCatgNm %>');"> Prev</a>
			<% 
		 }
		 
		 for(int k=pageNum;k<(pageNum+10) && k<=totalLinks ;k++){
		  %>
		   <a  id="a<%=k %>" href="#" onclick="getPrcCatgLinks('<%=k %>','<%=mName%>','<%=prcCatgNm %>');"><%=k %></a>
 		  <% 
	     }
		 
		 if( (pageNum+1) <= nop){
		 %>
			  <a href="#" onclick="getPrcCatgLinks('<%= (pageNum+1) %>','<%=mName%>','<%=prcCatgNm %>');">Next</a>
			 <%
		 }
		 %>
		  <a href="#" onclick="getPrcCatgLinks('<%= nop %>','<%=mName%>','<%=prcCatgNm %>');">Last</a>
		 <%
	 }
	 if(nop<=10){
		 for(int k=1;k<=nop;k++){
			%>
			 <a id="a<%=k %>" href="#" onclick="getPrcCatgLinks('<%= k %>','<%=mName%>','<%=prcCatgNm %>');"><%= k %></a>
			<% 
		 }
	 }
 }else{
	 
 }
 
 %>
 </div>
 </table>
 <br>
 <table style= "width:100%"><tr align="right"><td align="right"><a class="btn" style="white-space: nowrap;" href="javascript:void fnCloseMdlDlg('<%=mName%>')">Close</a></td></tr></table>
</form>
