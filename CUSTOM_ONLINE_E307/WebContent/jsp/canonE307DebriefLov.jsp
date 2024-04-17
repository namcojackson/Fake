
<%@ page import="com.canon.apps.*" %>  
<%@ page import="java.text.*"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.canon.apps.servreq.dao.*" %>  
<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<%@ page import="com.canon.apps.servreq.dao.CanonE307DebriefDetailsDAO" %>  
<%@page import="com.canon.common.CanonCommonUtil"%>	
<style>
	 .ui-dialog .ui-dialog-title{
	 	float: none;
	 }
</style>
<script>
function searchItem(){
	var url = 'canonE307DebriefLov.jsp';
    $.post(url
            ,$("#modItmLovFrm").serialize()
            ,function(data){
			 $('#laborItemDiv').html("");
             $('#laborItemDiv').html(data);
            });
} 
$(document).ready(function(){
	$("#modItmLovFrm").submit(function (){
		  $("#search").focus();
		  searchItem();
		   return false;
	});
});
</script>

<form name="modItmLovFrm" id="modItmLovFrm" method="post" action="canonE307DebriefLov.jsp">
<%
	CanonCommonUtil util = new CanonCommonUtil();
	String ctxPath = request.getContextPath();
	Object[] result = null;
	int pageNum = (request.getParameter("pageNum")==null)?1:Integer.parseInt(request.getParameter("pageNum")); 
	int ct=25;
	int start = ((pageNum-1)*ct) + 1 ;
	int end= pageNum*ct;
	int totalRecords=0;
	int totalLinks =0;
//	String strItemNumber = request.getParameter("laborItem");

	String strSource = request.getParameter("source");
	String strIval = request.getParameter("iVal");
	String mName= request.getParameter("modalName");
	String strTSrc = request.getParameter("tabSrc");
	String strSearchVal = request.getParameter("searchVal");
	System.out.println("strItemNumber in LOV : " + strSource);
%>
<table align="center" width="99%" valign="center">
  <tr>    
    <td class="ePartners_text" align="right"><b>Mod Item&nbsp;</b></td>
    <td colspan="2" class="ePartners_text">
    <input size="20" id="searchVal" name="searchVal" value="<%=util.checkNull(strSearchVal) %>">    
     <a href="javascript:searchItem()" class="btn" style="white-space: nowrap;">Search</a>   
    </td>
  </tr>
</table> 
<%
	CanonE307DebriefDetailsDAO objDet = new CanonE307DebriefDetailsDAO();

	ArrayList<CanonE307ServReqDebriefLovRec> lstItems =	objDet.getDebriefLovVal(strSource, util.checkNull(strSearchVal));
%>
<table style="width: 100%;" class="supplies-table">
<input type="hidden" id="modalName" name="modalName" value="<%=mName%>">
<input type="hidden" id="iVal" name="iVal" value="<%=strIval%>">
<input type="hidden" id="tabSrc" name="tabSrc" value="<%=strTSrc%>">
<input type="hidden" id="source" name="source" value="<%=strSource %>">
<tr>
<th width="20%">Select</th>
<th width="80%">Mod#</th>
<th width="80%">Mod Item</th>
</tr>
<%
if(lstItems!=null && lstItems.size()>0){
	for(int i=0;i<lstItems.size();i++){
		CanonE307ServReqDebriefLovRec beanObj = (CanonE307ServReqDebriefLovRec)lstItems.get(i);	
%>
<tr>
<td align="center"> <input type=radio name="selectedDet" id="selectedDet<%=i%>" value="<%=i%>" onclick="setModNum('<%=mName%>','<%=strIval%>','<%=beanObj.getStrVal()%>','<%=strTSrc%>','<%=beanObj.getStrValDesc() %>')" /></td>
<td><%=beanObj.getStrVal() %></td>
<td><%=beanObj.getStrValDesc() %></td>
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
<table style= "width:100%"><tr align="right"><td align="right"><a class="btn" style="white-space: nowrap;" href="javascript:void fnCloseMdlDlg('<%=mName%>')">Close</a></td></tr></table>
</form>
