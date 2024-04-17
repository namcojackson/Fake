
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
	var url = 'canonE307ChargeItemLov.jsp';
	showPleaseWait();
    $.post(url
            ,$("#itemLovFrm").serialize()
            ,function(data){
    		hidePleaseWait();	
			 $('#chargeItemDiv').html("");
             $('#chargeItemDiv').html(data);
            });
} 
$(document).ready(function(){
	$("#itemLovFrm").submit(function (){
		  $("#search").focus();
		  searchItem();
		   return false;
	});
});
</script>

<form name="itemLovFrm" id="itemLovFrm" method="post" action="canonE307ChargeItemLov.jsp">
<%
	String ctxPath = request.getContextPath();
	Object[] result = null;
	int pageNum = (request.getParameter("pageNum")==null)?1:Integer.parseInt(request.getParameter("pageNum")); 
	int ct=25;
	int start = ((pageNum-1)*ct) + 1 ;
	int end= pageNum*ct;
	int totalRecords=0;
	int totalLinks =0;
	String strItemNumber = request.getParameter("chargeItem");
	String strBilTpe = request.getParameter("billTpe");
	String svcInvChrgTpCd = request.getParameter("svcInvChrgTpCd")==null?"":request.getParameter("svcInvChrgTpCd");
	String itmTp="";
	if("LC".equals(svcInvChrgTpCd)){
		itmTp="LABOR";
	}else if("PC".equals(svcInvChrgTpCd)){
		itmTp="PARTS";
	}else{
		itmTp="EXPENSE";
	}
	System.out.println("strItemNumber in LOV : " + strItemNumber);
	String strIval = request.getParameter("iVal");
	String mName= request.getParameter("modalName");
	SimpleDateFormat sdf = new SimpleDateFormat("dd'-'MMM'-'yyyy");
%>

	<table width="100%" class="border-no" align="center">
	<input type="hidden" id="modalName" name="modalName" value="<%=mName%>">
	<input type="hidden" id="iVal" name="iVal" value="<%=strIval%>">
	<input type="hidden" id="svcInvChrgTpCd" name="svcInvChrgTpCd" value="<%=svcInvChrgTpCd%>">
	  <tr>    
	    <td align="right"><b>Item&nbsp;</b></td>
	    <td style="width:180px" class="search"><input id="chargeItem" name="chargeItem" value="<%=strItemNumber==null?"":strItemNumber%>"></td>  
	    <td><a class="btn" style="white-space: nowrap;" href="javascript:void fnSearchItem()">Search</a></td>
	  </tr>
	</table>
<br>
<%
	CanonE307DebriefDetailsDAO objDet = new CanonE307DebriefDetailsDAO();
	List lstItems=new ArrayList();
	result=	objDet.getItemDetailsLov(itmTp, strItemNumber, start, end, "");
	if(result != null && result.length>1){
		lstItems=(ArrayList)result[0];
	   	Integer intObj=(Integer)result[1];
		if(intObj!=null){
			totalRecords = intObj.intValue();
			System.out.println("total Records: "+ totalRecords);
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
if(lstItems!=null && lstItems.size()>0){
	for(int i=0;i<lstItems.size();i++){
		CanonE307DebriefItemLov laborBeanObj = (CanonE307DebriefItemLov)lstItems.get(i);	
%>
<tr>
<td align="center"> <input type=radio name="selectedDet" id="selectedDet<%=i%>" value="<%=i%>" onclick="setItemDet('<%=mName%>','<%=strIval%>','<%=laborBeanObj.getStrItmNm() %>','<%=laborBeanObj.getStrItmDesc() %>')" /></td>
<td><%=laborBeanObj.getStrItmNm() %></td>
<td><%=laborBeanObj.getStrItmDesc() %></td>
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
				 <a href="#" onclick="getChargeItemLinks('1','<%=strItemNumber%>''<%=mName%>','<%=strIval%>','<%=strBilTpe%>', '<%=svcInvChrgTpCd %>');">First</a>
				<% 
			 }
		 if(pageNum>1){
			 
			%>
			 <a href="#" onclick="getChargeItemLinks('<%=(pageNum-1) %>','<%=strItemNumber%>','<%=mName%>','<%=strIval%>','<%=strBilTpe%>','<%=svcInvChrgTpCd %>');"> Prev</a>
			<% 
		 }
		 
		 for(int k=pageNum;k<(pageNum+10) && k<=totalLinks ;k++){
		  %>
		   <a  id="a<%=k %>" href="#" onclick="getChargeItemLinks('<%=k %>','<%=strItemNumber%>','<%=mName%>','<%=strIval%>','<%=strBilTpe%>','<%=svcInvChrgTpCd %>');"><%=k %></a>
 		  <% 
	     }
		 
		 if( (pageNum+1) <= nop){
		 %>
			  <a href="#" onclick="getChargeItemLinks('<%= (pageNum+1) %>','<%=strItemNumber%>','<%=mName%>','<%=strIval%>','<%=strBilTpe%>','<%=svcInvChrgTpCd %>');">Next</a>
			 <%
		 }
		 %>
		  <a href="#" onclick="getChargeItemLinks('<%= nop %>','<%=strItemNumber%>''<%=mName%>','<%=strIval%>','<%=strBilTpe%>','<%=svcInvChrgTpCd %>');">Last</a>
		 <%
	 }
	 if(nop<=10){
		 for(int k=1;k<=nop;k++){
			%>
			 <a id="a<%=k %>" href="#" onclick="getChargeItemLinks('<%= k %>','<%=strItemNumber%>','<%=mName%>','<%=strIval%>','<%=strBilTpe%>','<%=svcInvChrgTpCd %>');"><%= k %></a>
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
