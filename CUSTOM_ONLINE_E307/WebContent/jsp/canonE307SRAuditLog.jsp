
<%@page import="com.canon.apps.servreq.beans.CanonE307SRAuditLogRec"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.canon.common.CanonCommonUtil"%>

<%@page import="java.util.*"%>
<style>
	 .ui-dialog .ui-dialog-title{
	 	float: none;
	 }
</style>
<script>

function show() {
	$.blockUI( {
		css : {
			border : 'none',
			padding : '15px',
			backgroundColor : '#000',
			'-webkit-border-radius' : '10px',
			'-moz-border-radius' : '10px',
			opacity : .5,
			color : '#fff',
			cursor : 'default'
		},
		overlayCSS : {
			cursor : 'default'
		},
		baseZ : 9000
	});
}

function hide() {
	$.unblockUI();
}
</script>
<% 	
//	String ctxPath = request.getContextPath();
//	String imgSrc=ctxPath+"/common/images/download.png";
	String fsrNum = request.getParameter("fsr");

	CanonE307ServiceRequestDetailsDao objDao = new CanonE307ServiceRequestDetailsDao();
    CanonCommonUtil util = new CanonCommonUtil();
  //  String strAction = request.getParameter("action");
    System.out.println("fsrNum: "+ fsrNum);
    
%>
<div id="srAuditLogDiv"> 

<form name="auditFrm" id="auditFrm" method="post" action="canonE307SRAuditLog.jsp">
	<input type="hidden" name="fsr" id="fsr" value="<%=fsrNum %>">
	<table class="lov-table" style="width: 98%;"  cellpadding="1" cellspacing="1" style='background-color:#cccc99'>
	<!-- 	<tr>
			<td>Please select a reason to proceed with cancellation of the service request and click Submit OR Click Cancel to Quit</td>
		</tr> -->
		<thead>
		<tr>
			<th>FSR</th>
			<th>Task</th>
			<th>Time</th>	
			<th>User Id</th>	
			<th>Update</th>		
			<th>Old Value</th>	
			<th>New Value</th>			
		</tr>
		</thead>
		<tbody>
		<%
			String strDisp = "";
			List<CanonE307SRAuditLogRec> auditInfoLst = objDao.getAuditLogInfo(fsrNum);

			if(auditInfoLst!=null && auditInfoLst.size()>0){
				int i=0;
				 for(CanonE307SRAuditLogRec auditBean : auditInfoLst){
	/* 				String bclr  = "";
					if((i%2) == 0)
					{
						bclr   = "eventableDataCell";
					}
					else
					{
					    bclr = "oddtableDataCell";
					} */
		%>
			
			<tr>
					<td> <%=util.checkNull(auditBean.getStrFSR()) %></td>
					<td> <%=util.checkNull(auditBean.getStrTask()) %> </td>
					<td> <%=util.checkNull(auditBean.getStrTime())  %></td>
					<td> <%=util.checkNull(auditBean.getStrUserId() )%></td>	
					<td> <%=util.checkNull(auditBean.getStrUpdate() )%></td>
					<td> <%=util.checkNull(auditBean.getStrOldValue() )%></td>
					<td> <%=util.checkNull(auditBean.getStrNewValue() )%></td>
			</tr>
		<%				
					}
			}else{
		%>
				<tr><td colspan=7></td></tr>
				<tr><td colspan=7>There is no Audit Log exists for this FSR.</td></tr>
		<%		
			}
	  	%>
		</tbody>
	</table>
</form>
	<table width="100%">
	<tr align="right">
		<td align="right">
			<a class="btn" href="javascript:void fnCloseSearchDlg('srAuditLogDiv')">Close</a>
		</td>
	</tr>
</table>
</div>
