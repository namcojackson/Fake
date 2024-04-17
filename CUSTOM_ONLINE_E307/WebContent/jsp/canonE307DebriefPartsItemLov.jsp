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
function fnSearchPartItem(){
	var url = 'canonE307DebriefPartsItemLov.jsp';
	showPleaseWait();
    $.post(url
            ,$("#partItemLovFrm").serialize()
            ,function(data){
    		 hidePleaseWait();
			 $('#partsItemDiv').html("");
             $('#partsItemDiv').html(data);
            });
} 
$(document).ready(function(){
	$("#partItemLovFrm").submit(function (){
		  $("#partItem").focus();
		  fnSearchPartItem();
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
<form name="partItemLovFrm" id="partItemLovFrm" method="post" action="canonE307DebriefPartsItemLov.jsp">
<%
	String strPartItemNum = request.getParameter("partItem")==null?"":request.getParameter("partItem");
	String mName= request.getParameter("modalName");
	String strIval = request.getParameter("iVal");
	String strFsr = request.getParameter("fsr");
	System.out.println("strFsr: " + strFsr);
%>
<table style="width: 100%;">
<tr>
<td>
<!-- <div class="service">  -->
	<table width="100%" class="border-no" align="center">
		<input type="hidden" id="modalName" name="modalName" value="<%=mName%>">
		<input type="hidden" id="iVal" name="iVal" value="<%=strIval%>">
		<tr>    
		    <td align="right"><b>Part Item</b></td>
		    <td style="width:180px"><input id="partItem" name="partItem" value="<%=strPartItemNum==null?"":strPartItemNum%>"></td>    
			<td><a class="btn" style="white-space: nowrap;" href="javascript:void fnSearchPartItem()">Search</a> </td>
		</tr>
	</table>
</td>
</tr>
<!--  </div> -->
<%
	CanonE307DebriefDetailsDAO objDet = new CanonE307DebriefDetailsDAO();
	List lstItems=new ArrayList();
	result = objDet.getItemDetailsLov("PARTS",strPartItemNum, start, end, strFsr);
	if(result != null && result.length>1){
		lstItems=(ArrayList)result[0];
	   	Integer intObj=(Integer)result[1];
		if(intObj!=null){
			totalRecords = intObj.intValue();
		}
	}
	totalLinks = (totalRecords%ct>0)?((totalRecords/ct)+1):totalRecords/ct;	
%>
<tr>
<td>
<table class="supplies-table" width="100%">
	<tr >
		<th width="10%">Select</th>
		<th width="30%">Part Item</th>
		<th width="60%">Part Description</th>
	</tr>
<%
if(lstItems!=null && lstItems.size()>0){
	for(int i=0;i<lstItems.size();i++){
		CanonE307DebriefItemLov partBeanObj = (CanonE307DebriefItemLov)lstItems.get(i);	
%>
<tr>
	<td align="center"> <input type=radio name="selectedDet" id="selectedDet<%=i%>" value="<%=i%>" onclick="setPartItemDet('<%=mName%>','<%=strIval%>','<%=partBeanObj.getStrItmNm() %>', '<%=partBeanObj.getStrStrtDt() %>','<%=partBeanObj.getStrUom() %>', '<%=partBeanObj.getStrItmCnt() %>', '<%=i%>')" /></td>
	<input type="hidden" id="partItmDesc<%=i%>" name="partItmDesc<%=i%>" value="<%=partBeanObj.getStrItmDesc() %>">
	<td><%=partBeanObj.getStrItmNm() %></td>
	<td><%=partBeanObj.getStrItmDesc() %></td>
</tr>	
<%	
	}
}else{
	%>
<tr>
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
							 <a href="#" onclick="getPartItemLinks('1','<%=strPartItemNum%>','<%=mName%>','<%=strIval%>');">First</a>
							<% 
						 }
					 if(pageNum>1){
						 
						%>
						 <a href="#" onclick="getPartItemLinks('<%=(pageNum-1) %>','<%=strPartItemNum%>','<%=mName%>','<%=strIval%>');"> Prev</a>
						<% 
					 }
					 
					 for(int k=pageNum;k<(pageNum+10) && k<=totalLinks ;k++){
					  %>
					   <a  id="a<%=k %>" href="#" onclick="getPartItemLinks('<%=k %>','<%=strPartItemNum%>','<%=mName%>','<%=strIval%>');"><%=k %></a>
			 		  <% 
				     }
					 
					 if( (pageNum+1) <= nop){
						 %>
						  <a href="#" onclick="getPartItemLinks('<%= (pageNum+1) %>','<%=strPartItemNum%>','<%=mName%>','<%=strIval%>');">Next</a>
						 <%
					 }
					 %>
					  <a href="#" onclick="getPartItemLinks('<%= nop %>','<%=strPartItemNum%>','<%=mName%>','<%=strIval%>');">Last</a>
					 <%
				 }
				 if(nop<=10){
					 for(int k=1;k<=nop;k++){
						%>
						 <a id="a<%=k %>" href="#" onclick="getPartItemLinks('<%= k %>','<%=strPartItemNum%>','<%=mName%>','<%=strIval%>');"><%= k %></a>
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
