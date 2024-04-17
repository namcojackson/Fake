
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307TaskCancelRsnRec"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307CancelTaskRsnInfoRec"%>
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

/* $(document).ready(function() {
	setRsnSelects();
}); */
/*	function fnCancelTask(){
		var recSz = $('#recSz').val();
		$('#tskMsgDiv').html('');
		var validChk ="";
		for(i=0;i<recSz;i++){
			var chkChekd=	$('#cnclFlg'+i).is(':checked'); 
			console.log("chkChekd: "+chkChekd);
			if(chkChekd){
				validChk=true;
				var cnclRsn = $('#cnclRsn'+i).val();
				if(cnclRsn == -1){
					$('#tskMsgDiv').html("Please select Cancel Reason for selected value");
					$('#tskMsgDiv').css({"color": "red", "font-size": "12"});
					validChk=false;
				}
			}
		}
		console.log("validChk: "+validChk);
		if(validChk){
			var url = 'canonE307ServReqCancelRsn.jsp?action=cancel';
			show();
			$.post(url
	            ,$("#cnclRsnFrm").serialize()
	            ,function(data){
				hide();
				 $('#cnclTaskDiv').html("");
	             $('#cnclTaskDiv').html(data);
	            });		
		}else if(!validChk){
			$('#tskMsgDiv').html("Please select atleast one task to cancel");
			$('#tskMsgDiv').css({"color": "red", "font-size": "12"});
		}
	}	*/
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
	String ctxPath = request.getContextPath();
	String imgSrc=ctxPath+"/common/images/download.png";
	String fsrNum = request.getParameter("fsr");
	String slsDt = request.getParameter("slsDt");
	String userNm = request.getParameter("userName");
	CanonE307ServiceRequestDetailsDao objDao = new CanonE307ServiceRequestDetailsDao();
    CanonCommonUtil util = new CanonCommonUtil();
    String strAction = request.getParameter("action");
    System.out.println("strAction: "+ strAction);
    
	String retMsg="";
	String eMsgId = "eMsg";
	//String disStyle = "display: none;padding-bottom: 5px;padding-left: 0px;padding-top:5px;font-size: 15px;font-weight: bold;";	
    if("cancel".equalsIgnoreCase(strAction)){
    	CanonE307ServiceReqSumryAPIUtil utilObj = new CanonE307ServiceReqSumryAPIUtil();

    	String recSz = request.getParameter("recSz")==null?"":request.getParameter("recSz");
    	if(recSz!=null && recSz.length()>0){
 			int size = Integer.parseInt(recSz);
 			for(int i=0;i<size;i++){
				String taskNum = request.getParameter("tskNum"+i);
				String cnclFlg = request.getParameter("cnclFlg"+i)==null?"":request.getParameter("cnclFlg"+i);
				System.out.println("cnclFlg: "+ cnclFlg);
				 if(cnclFlg.length()>0) {
					 System.out.println("cnclFlg: "+ cnclFlg);
					 String memoTpcd="";
					 String rsnTpCd="";
					 String cnclRsn = request.getParameter("cnclRsn"+i);
					 if(cnclRsn!=null && cnclRsn.length()>0){
						 memoTpcd = cnclRsn.split("-")[0];
						 rsnTpCd = cnclRsn.split("-")[1];
					 }
					 String cnclNt = request.getParameter("cancelNt"+i);
				     System.out.println("Inside cancel: "+fsrNum+" strCnclTsk: "+taskNum+" userNm: "+userNm+" slsDt: "+slsDt+" cnclRsn: "+cnclRsn+" cnclNt: "+cnclNt);
				    	String[] resArr = utilObj.servReqCancelTask(fsrNum, taskNum, userNm, "N", slsDt, memoTpcd, rsnTpCd, cnclNt);
						  String rFlg= util.checkNull(resArr[0]);
						  if("Y".equalsIgnoreCase(rFlg)){
							 retMsg=resArr[1];
							 eMsgId="sucMsg";
							 retMsg="Cancelled Task Successfully...";
				 			}else if("E".equalsIgnoreCase(rFlg)){
								 retMsg=resArr[1];
				 			}
 					}
    		}
    	}
    }
   System.out.println("retMsg: "+ retMsg+" eMsgId: "+eMsgId); 
%>
<div id="cnclTaskDiv"> 
<div id="tskMsgDiv">
<p id="<%=eMsgId%>"><%=retMsg%></p>
</div>
<form name="cnclRsnFrm" id="cnclRsnFrm" method="post" action="canonE307ServReqCancelRsn.jsp">
	<input type="hidden" name="fsr" id="fsr" value="<%=fsrNum %>">
	<input type="hidden" name="userName" id="userName" value="<%=userNm %>">
	<input type="hidden" name="slsDt" id="slsDt" value="<%=slsDt%>">
	<table class="lov-table" style="width: 98%;"  cellpadding="1" cellspacing="1" style='background-color:#cccc99'>
	<!-- 	<tr>
			<td>Please select a reason to proceed with cancellation of the service request and click Submit OR Click Cancel to Quit</td>
		</tr> -->
		<thead>
		<tr>
			<th>Select</th>
			<th>Task Number</th>
			<th>Task Type</th>	
			<th>Task Status</th>	
			<th>Reason</th>		
			<th>Note</th>				
		</tr>
		</thead>
		<tbody>
		<%
			String strDisp = "";
			ArrayList<CanonE307CancelTaskRsnInfoRec> rsnInfoLst = objDao.getTaskCancelRsnsInfo(fsrNum);
			ArrayList<CanonE307TaskCancelRsnRec> cnclRsnsLst = objDao.getTaskCancelRsns();
			if(rsnInfoLst!=null && rsnInfoLst.size()>0){
				int i=0;
				 for(CanonE307CancelTaskRsnInfoRec rsnBean : rsnInfoLst){
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
	
					<input type="hidden" name="tskNum<%=i%>" id="tskNum<%=i%>" value="<%=util.checkNull(rsnBean.getTaskNum()) %>">
	   <%			if("Cancelled".equals(rsnBean.getSvcTaskSts())){
	   %>
						<td> &nbsp;<%-- <input  type="checkbox" name="cnclFlg<%=i%>" id="cnclFlg<%=i%>" disabled style="border:0px;"/> --%></td>
						<td> <%=util.checkNull(rsnBean.getTaskNum() )%></td>
						<td> <%=util.checkNull(rsnBean.getSvcCallTpCd()+"-"+rsnBean.getSvcCallTpNm() )%></td>
						<td> <%=util.checkNull(rsnBean.getSvcTaskSts() )%></td>
		
						<td><%=util.checkNull(rsnBean.getCancelRsn() )%></td>
		<%
					}else{
						strDisp="Yes";
		%>
					<td> <input  type="checkbox" name="cnclFlg<%=i%>" id="cnclFlg<%=i%>" value="Y" style="border:0px;"/></td>
					<td> <%=util.checkNull(rsnBean.getTaskNum() )%></td>
					<td> <%=util.checkNull(rsnBean.getSvcCallTpCd()+"-"+rsnBean.getSvcCallTpNm() )%></td>
					<td> <%=util.checkNull(rsnBean.getSvcTaskSts() )%></td>	
					<td nowrap>
						<select id="cnclRsn<%=i%>" name="cnclRsn<%=i%>" style="width:155px;margin:0 5px;">
							<option value="-1">Select a Reason</option>
							<%
							if(cnclRsnsLst!=null && cnclRsnsLst.size()>0){
								for(CanonE307TaskCancelRsnRec cnclRsnBean : cnclRsnsLst){
								%>
									<option value='<%=cnclRsnBean.getRsnTpCd()%>-<%=cnclRsnBean.getRsnTpVal()%>'><%=cnclRsnBean.getRsnTpDesc() %></option>
								<%
								}
							}
							%>
						</select>
					</td>
		<%				
					}
	   			if("Cancelled".equals(rsnBean.getSvcTaskSts())){
			%>
					<td><%=util.checkNull(rsnBean.getRsnNote())%></td>
			<%			 
					 }else{
			%>
					<td><input type="text" id="cancelNt<%=i%>" name="cancelNt<%=i%>" value=""></td>
			<%			 
					 }
				i++;
				 }
			%>
				<input type="hidden" id="recSz" name="recSz" value="<%=rsnInfoLst.size() %>">

				</tr>
			<%
			}else{
				%>
				<tr><td colspan=6>There is no eligible tasks to cancel.</td></tr>
				<%
			}
			%>
	
	<!-- 	<tr>
			<td><textarea name="addlNts" id="addlNts" rows="5" cols="72" class="inTxt" style="height: 80px;"></textarea></td>
		</tr> -->
		</tbody>
	</table>
</form>
	<table width="100%">
	<tr align="right">
		<td align="right">
		<%if("Yes".equals(strDisp)){ %>
			<a class="btn" href="javascript:void fnCancelTask()">Submit</a>
	    <%} %>
			<a class="btn" href="javascript:void fnCloseTskDlg('cnclTaskDiv')">Close</a>
		</td>
	</tr>
</table>
</div>
