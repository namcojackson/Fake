<%@page import="com.canon.apps.servreq.beans.CanonE307EOLRec"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestSearchDao"%>
<%@page import="java.util.*" %>
<%@page import="com.canon.common.CanonCommonUtil"%>
<style>
	 .ui-dialog .ui-dialog-title{
	 	float: none;
	 }
</style>
<%
	String strSerialNumber = request.getParameter("serialNumber");	
	String strMachMstrPk = request.getParameter("svcMachMstrPk");
	
	System.out.println("Inside EOL strSerialNumber : " + strSerialNumber+" strMachMstrPk: "+strMachMstrPk);


    CanonCommonUtil utl = new CanonCommonUtil();
    CanonE307ServiceRequestSearchDao objCount = new CanonE307ServiceRequestSearchDao();
    String svcContrCmntTxt = "";
    String svcTmMatCmntTxt="";
    String svcTechSprtCmntTxt="";
    String svcOthCmntTxt="";
    String svcDisptCmntTxt="";
    String callCrtnFlg = "";
 //   retVal = objCount.geMachineEol(strSrNum);
     List<CanonE307EOLRec> lst = (List)objCount.getSrvcEOLDtls(strSerialNumber, strMachMstrPk);
 		if(lst!=null && lst.size()>0){
 			%>
<!--  				<div class="service"> -->
		 		<table style="width:85%;" align="center" >
		 			<tr>
		 			<td style="width:30%">&nbsp;</td>
						<td style="width:60%">
							<table style="align:center;width: 90%">
 			<%
 			for(CanonE307EOLRec eolBean : lst){
				svcContrCmntTxt= eolBean.getEolSvcContrCmnt();
				svcTmMatCmntTxt= eolBean.getEolTmMatCmnt();
				svcTechSprtCmntTxt= eolBean.getEolTechSprtCmnt();
				svcOthCmntTxt= eolBean.getEolOthComnt();
				svcDisptCmntTxt= eolBean.getEolDisptCmntTxt();
				callCrtnFlg = eolBean.getAttribute1();
				System.out.println("svcContrCmntTxt: "+ svcContrCmntTxt+ " eolBean.getSrvTp(): " + eolBean.getSrvTp()+" callCrtnFlg: "+callCrtnFlg);
				%>
	
		 			<tr>
		 				<td align="right"><input type="text" name="eolVal" value="<%=eolBean.getSrvTp() %>"></td><td><input type="text" name="eolEndDt" value="<%=eolBean.getStpSrvDt() %>" class="rdl"></td>
		 			</tr>
		 		
		 		<%
		 	} 
 			if("Y".equals(callCrtnFlg)){
		 		%>
	 			</table>
 					</td>
	 				<td align="left" style="width:5%">
						<a class="btn" href="javascript:void fnCreateCall('eolDlg','<%=strSerialNumber %>', 'REGULAR')">OK</a>
					</td>
	 			</tr>
	 		</table>
 			<% 				
 			} else{
		 	%>
		 			</table>
	 					</td>
		 				<td align="left" nowrap>
							<a class="btn" href="javascript:void fnCreateCall('eolDlg','<%=strSerialNumber %>', 'DE_INSTALL')">DE-INSTALL CALL</a>
						</td>
						<td align="left" style="width:5%" nowrap>
							<a class="btn" href="javascript:void fnCloseDlg('eolDlg','<%=strSerialNumber %>', '<%=callCrtnFlg%>')">Close</a>
						</td>
		 			</tr>
		 		</table>
		 	<%
 			}
		 	%>	
				<br>
				<table style="width:90%;margin: 0 auto;" >
				
		 			<tr>
		 				<td align="center"><b>Service Comments</b></td>
		 				
		 			</tr>
		 		</table>
		 		<br>
				<table style="width:90%;border:1px #cccccc solid;align:center;margin: 0 auto;">
				
					<tr>
		 				<td nowrap>Service Contracts Information:  </td><td><%=utl.checkNull(svcContrCmntTxt) %></td>
		 			</tr>
		 			<tr>
		 				<td nowrap>Time and Material Information:  </td><td><%=utl.checkNull(svcTmMatCmntTxt) %></td>
		 			</tr>	
		 			<tr>
		 				<td nowrap>Technical Support Information:  </td><td><%=utl.checkNull(svcTechSprtCmntTxt) %></td>
		 			</tr>
		 			<tr>
		 				<td nowrap>Dispatcher Comments:  </td><td> <%=utl.checkNull(svcDisptCmntTxt) %></td>
		 			</tr>
		 			<tr>
		 				<td nowrap>Other Comments:  </td><td> <%= utl.checkNull(svcOthCmntTxt) %></td>
		 			</tr>			 			
					
		 		<%-- 	<tr>
		 				<td nowrap>Service Contracts Information:  </td><td><textarea name='dispComments' id="dispComments"  rows='3' cols="55" style="height: 80px;font-size: 10pt" readonly><%=utl.checkNull(svcContrCmntTxt) %></textarea></td>
		 			</tr>
		 			<tr>
		 				<td nowrap>Time and Material Information:  </td><td><textarea name='dispComments' id="dispComments"  rows='3' cols="55" style="height: 80px;font-size: 10pt" readonly> <%=utl.checkNull(svcTmMatCmntTxt) %></textarea></td>
		 			</tr>		 			
		 			<tr>
		 				<td nowrap>Technical Support Information:  </td><td><textarea name='dispComments' id="dispComments"  rows='3' cols="55" style="height: 80px;font-size: 10pt" readonly> <%=utl.checkNull(svcTechSprtCmntTxt) %></textarea></td>
		 			</tr>
		 			<tr>
		 				<td nowrap>Dispatcher Comments:  </td><td><textarea name='dispComments' id="dispComments"  rows='3' cols="55" style="height: 80px;font-size: 10pt" readonly> <%=utl.checkNull(svcDisptCmntTxt) %></textarea></td>
		 			</tr>
		 			<tr>
		 				<td nowrap>Other Comments:  </td><td> <textarea name='dispComments' id="dispComments"  rows='3' cols="55" style="height: 80px;font-size: 10pt" readonly> <%= utl.checkNull(svcOthCmntTxt) %></textarea></td>
		 			</tr>				 --%>		 						
 				 			 			
		 		</table>
			
			<%
 		}else{
 		%>
 		<table>
 			<tr>
 				<td>No Data exists.</td>
 			</tr>
 		</table>
 		<%	
 		}
			%>
 