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
	String strFsrNum = request.getParameter("fsrNumber");	
	String strSource = request.getParameter("source");
	String strPostalCd = request.getParameter("postalCd");
	String strCntryCd = request.getParameter("cntryCd");
	String strTaskNum = request.getParameter("taskNum");
	
	System.out.println("Inside EOL strSrNum : " + strFsrNum);


    CanonCommonUtil utl = new CanonCommonUtil();
    CanonE307ServiceRequestSearchDao objCount = new CanonE307ServiceRequestSearchDao();
    String svcContrCmntTxt = "";
    String svcTmMatCmntTxt="";
    String svcTechSprtCmntTxt="";
    String svcOthCmntTxt="";
    String svcDisptCmntTxt="";
 //   retVal = objCount.geMachineEol(strSrNum);
    ArrayList<CanonE307EOLRec> arLst = new ArrayList<CanonE307EOLRec>();
     List<CanonE307EOLRec> lst = (List)objCount.getMachineEOLDtls(strFsrNum);
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
				System.out.println("svcContrCmntTxt: "+ svcContrCmntTxt+ " eolBean.getSrvTp(): " + eolBean.getSrvTp());
				%>
	
		 			<tr>
		 				<td align="right"><input type="text" name="eolVal" value="<%=eolBean.getSrvTp() %>"></td><td><input type="text" name="eolEndDt" value="<%=eolBean.getStpSrvDt() %>" class="rdl"></td>
		 			</tr>
		 		
		 		<%
		 		} 
		 		%>
		 			</table>
	 					</td>
		 				<td align="left" style="width:5%">
							<a class="btn" href="javascript:void fnCloseSearchDlg('eolDlg','<%=strSource %>', '<%=strFsrNum %>', '<%=strTaskNum %>', '<%=strPostalCd %>', '<%=strCntryCd %>')">OK</a>
						</td>
		 			</tr>
		 		</table>
		 	<%
 			}
		 	%>	
				<br>
				<table style="width:100%;" align="center" >
				
		 			<tr>
		 				<td style="align:right">&nbsp;</td>
		 				<td align="center"><b>Service Comments</b></td>
		 			</tr>
				
		 			<tr>
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
		 				<td nowrap>Other Comments:  </td><td> <textarea name='dispComments' id="dispComments"  rows='3' cols="55" style="height: 80px;font-size: 10pt" readonly> <%=utl.checkNull(svcOthCmntTxt) %></textarea></td>
		 			</tr>		 						 						
 				 			 			
		 		</table>
			
				
 