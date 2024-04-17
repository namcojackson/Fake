<%@page import="com.canon.apps.servreq.beans.CanonE307NoteTypeRec"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceReqCreateDao"%>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307TskTpeRec"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307ServiceReqAssigneeRec"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>
<%@page import="java.util.*"%>
<style>
	 .ui-dialog .ui-dialog-title{
	 	float: none;
	 }
</style>
<script type="text/javascript">
$(document).ready(function() {
	$(".datePicker").datepicker({
		 dateFormat: 'M dd yy',
		 changeMonth: true,
		 changeYear: true
	 });
	//getCurTime();
});
function parseDate(dt) {
	  var months = {jan:0,feb:1,mar:2,apr:3,may:4,jun:5,
	                jul:6,aug:7,sep:8,oct:9,nov:10,dec:11};
	  var p = dt.split(' ');
	  return new Date(p[2], months[p[0].toLowerCase()], p[1]);
}
function fnCheckDt(){
	$('#tskMsgDiv').html("");
	 var dt = $("#fSVcDate").val();
	 var stMin = $('#ftrSrvMn').val();
	 var nwDt =  parseDate(dt);
	 var newHr = convertDateTo24Hour();
	// console.log("newHr : "+newHr);
	 nwDt.setHours(newHr,stMin,0,0);
	 
	 var d = new Date(); 
	 var hor =d.getHours(); 
	 var min = d.getMinutes();
	// console.log("hor : "+ hor+" min : "+min);
	 d.setHours(hor,min,0,0);
	 var day = d.getDate();
	// console.log("day : "+day); 
	 months = new Array('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec');
     curMonth = months[d.getMonth()];
	 var year = d.getFullYear();
	 var curDate = curMonth+" "+day+" "+year;

	 var ftrSysDate =  new Date($('#ftrSysDt').val());
	// console.log("nwDt : "+nwDt+" ftrSysDate : "+ftrSysDate+" d : "+d);
	  if (nwDt < ftrSysDate) { 
			$("#tskMsgDiv").css({"color": "red", "font-size": "12"});
			$('#tskMsgDiv').html("Date & Time must be equal to or greater than current date");
		//  alert("Date & Time must be equal to or greater than current date");
		//  $("#fSVcDate").val(curDate);
		  $('#ftrDtVald').val('N');
		  return false;
	  }else{
		  $('#ftrDtVald').val('Y');
		  return true;
	  }
}
function convertDateTo24Hour(){
	    var stHour = $('#ftrSrvHr').val();
	    var stAmPm = $('#ftrAmPm').val();
	    
	    var newhr = 0;
	    var ampm = '';
	    var newtime = '';
	    
	    if (stAmPm=='PM')
	    { 
	        if (stHour!=12)
	        {
	            stHour=stHour*1+12;
	        }
	       
	    }else if(stAmPm=='AM' && stHour=='12'){
	       stHour = stHour -12;
	    }else{
	        stHour=stHour;
	    }
	    return stHour;
	}
function getSysDate(){
 	 var sd = new Date(); 
	 var hor =sd.getHours(); 
	 var min = sd.getMinutes();
	// console.log("hor : "+ hor+" min : "+min);
	 sd.setHours(hor,min,0,0);
	 $('#ftrSysDt').val(sd);
}
function getCurTime(){
	var d = new Date(); 
	//console.log("d: "+ d);
	var hor =d.getHours(); 
	var min = d.getMinutes();
	var _time = (hor > 12) ? (hor-12 +' PM') : (hor +' AM');
	//console.log("_time: "+_time);
	var dataTm = _time.split(' ');
	//console.log("data : "+dataTm[0]+":"+dataTm[1]);
	var _hr = (dataTm[0]<10)?('0'+dataTm[0]):(dataTm[0]);
    $('#ftrSrvHr'+' option[value="' + $.trim(_hr) + '"]').prop('selected', true);	
    $('#ftrAmPm'+' option[value="' + $.trim(dataTm[1]) + '"]').prop('selected', true);
    $('#ftrSrvMn' + ' option[value="' + min + '"]').prop('selected', true);
    getSysDate();
}
</script>
<% 	
	String ctxPath = request.getContextPath();
	String imgSrc=ctxPath+"/common/images/download.png";
	String contActFlg =request.getParameter("contActFlg");
	String strTmZn = request.getParameter("tmZn")==null?"":request.getParameter("tmZn");
	CanonE307ServiceRequestDetailsDao obj = new CanonE307ServiceRequestDetailsDao();
	com.canon.common.CanonCommonUtil util = new com.canon.common.CanonCommonUtil();
	String machMstrPk = request.getParameter("svcMachMstrPk");
	String postalCd = util.checkNull(request.getParameter("postalCd"));
	String cntryCd = util.checkNull(request.getParameter("cntryCd"));
	
	String[] techDet= obj.getTechDtls(machMstrPk);
	CanonE307ServiceReqCreateDao crDao = new CanonE307ServiceReqCreateDao();
	CanonE307ServiceReqSumryAPIUtil apiUtil = new CanonE307ServiceReqSumryAPIUtil();
	String futrDt = "";
 	String ftrDate="";
 	String strFtrHr ="";
 	String strFtrMin="";
 	String strFtrAmPm="";
	java.util.Date sysDate = new java.util.Date();
	//String ftrDate = new SimpleDateFormat("MMM dd yyyy").format(sysDate).toString();
 	if(postalCd.length()>0){
 		futrDt = apiUtil.getTmZone(postalCd, cntryCd,new SimpleDateFormat("yyyyMMddHHmmss").format(sysDate).toString());
	 	if(futrDt!=null && futrDt.length()>0){
			ftrDate = futrDt.substring(0, 11);
			strFtrHr = futrDt.substring(12,14);
			strFtrMin = futrDt.substring(15,17);
			strFtrAmPm = futrDt.substring(18,20);
	 	}
 	}
%>
<div id="tskMsgDiv"></div>
<div class="service">
<table style="width:100%;" align="center">
<input type="hidden" id="ftrSysDt" name="ftrSysDt" value="<%=futrDt%>">
<input type="hidden" id="ftrDtVald" name="ftrDtVald" value="">
	<tr>
		<td style="width:30%" nowrap>Select Task Type</td>
		<td style="width:35%" nowrap><select id="taskTpe" name="taskTpe" style="width:155px;margin:0 5px;">
		<%
		
			//Calendar cal = Calendar.getInstance();
		    //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			String bilSel ="";
			
			List tskRepData = obj.getTaskType();
			if(tskRepData!=null && tskRepData.size()>0){	
				for(int i=0;i<tskRepData.size();i++){
					CanonE307TskTpeRec tskObj = (CanonE307TskTpeRec)tskRepData.get(i);
			%>
					<option value="<%=tskObj.getTskTpCd()%>:<%=tskObj.getTskPrtyCd()%>"><%=tskObj.getTskTpCd()%>-<%=tskObj.getTskTpNm() %></option>  
			<%
				}
			}
		%>
		</select>
		</td>		
	</tr>
	<tr>
		<td style="width:30%" nowrap>Default Technician</td>
		<td style="width:35%;margin-bottom:8px;margin-left:8px;" nowrap>
		<input type="text" name="defltTech" id="defltTech" value="<%=util.checkNull(techDet[0])%>" class="rdl">
		<input type="hidden" name="defltTechCd" id="defltTechCd" value="<%=util.checkNull(techDet[1])%>">
			<!-- <select id="uniqueTech" name="uniqueTech" style="width:155px;margin:0 5px;">
				<option value="Y">Yes</option> 
				<option value="N" selected>No</option></select>  -->
		</td>				
	</tr>
	<tr>
		<td style="width:30%" nowrap>Type</td>
		<td style="width:35%" nowrap>
			<input type="text" name="resType" id="resType" value="<%=util.checkNull(techDet[2])%>" class="rdl">
			<input type="hidden" name="resTpCd" id="resTpCd" value="<%=util.checkNull(techDet[3])%>">
	<!-- 		<select id="resType" name="resType" style="width:155px;margin:0 5px;">
			<option value=""></option>
			</select>  -->
		</td>				
	</tr>	
	<tr>
		<td style="width:30%" nowrap>Assign Technician</td>
		<td style="width:35%" nowrap><input type="text" name="prefTech" id="prefTech" size="20" value="" style="margin:0px;"></td><td style="width:15%;align:right"><img src="<%=imgSrc%>"  height='21' onClick="fnGetTech();"></td>
		<input type="hidden" name="prefTechCode" id="prefTechCode" value="">				
	</tr> 	
	<tr>
		<td style="width:30%" nowrap>Future Service Date</td>
		<td style="width:35%" nowrap><input type="text" name="fSVcDate" id="fSVcDate" value="<%=ftrDate%>" size="8"  class="datePicker" onchange=fnCheckDt() /></td>
		<td style="width:15%;align:right" nowrap><select id="ftrSrvHr" name="ftrSrvHr" onchange=fnCheckDt()>
		<%
 		 	ArrayList<String> lsHr = crDao.getHourVal();
			for(String sHr: lsHr){
				String ftrHrSelect = "";
				if(sHr.equals(strFtrHr)){
	 				ftrHrSelect ="SELECTED";
	 			}
		 %>
		    <option value="<%=sHr%>" <%=ftrHrSelect %>><%=sHr %></option>
		 <%} %>
		</select>
		<select id="ftrSrvMn" name="ftrSrvMn" onchange=fnCheckDt()>
		<%
			ArrayList<String> lsMn = crDao.getMnVal();
			for(String sMn: lsMn){
				String ftrSelect ="";
				 if(sMn.equals(strFtrMin)){
					ftrSelect ="SELECTED";
				 }
			%>
			    <option value="<%=sMn%>" <%=ftrSelect %>><%=sMn %></option>
			<%} %>		
		</select>
		   <select id="ftrAmPm" name="ftrAmPm" onchange="fnCheckDt()" >
		   <%					
		   		String amSelect="";
		  		String pmSelect="";
		  		if("AM".equals(strFtrAmPm)){
		  			amSelect="SELECTED";
		  		}else{
		  			pmSelect="SELECTED";
		  		}
			%>
	 		  	<option value="AM" <%=amSelect%>>AM</option>
	 		  	<option value="PM" <%=pmSelect%>>PM</option>
 		   </select>
		</td>				
	</tr>
	<tr>
		<td style="width:30%" nowrap><b></>Time Zone</b></td>
		<td style="width:35%" nowrap><input type="text" name="tmZn" id="tmZn" value="<%=strTmZn%>" size="8"  style='background-color: #33FF33;font-weight: bold;' disabled/></td>
	</tr>
	</table>
</div>
			<table style='margin: 12px 0px 0px 0px; padding: 0px 0px;border-bottom: 1px sold #000;'>
				<tr>
					<th colspan="2" class="hd">Create Notes</th>
				</tr>
				<tr>
					<td width=20%><b>Type</b></td>
					<td><b>Message</b></td>
				</tr>
				<tr>
					<td> 
			             <select id="noteType" name="noteType" class="search_request" style="height: 25%;padding: 3px 3px;">
				             <%
				             ArrayList alNt = (ArrayList<CanonE307NoteTypeRec>) crDao.getNoteTypes();
				             if(alNt!=null && alNt.size()>0){
				             	for(int i=0;i<alNt.size();i++){
				             		CanonE307NoteTypeRec ntRec = (CanonE307NoteTypeRec)alNt.get(i);
				             		if("17".equals(ntRec.getNoteTpCd())){
				             		%>	
				             		<option value="<%=ntRec.getNoteTpCd() %>" selected><%=ntRec.getNoteTpDesc() %></option>   
				             		<%	
				             		}else{
				            		 %>
				              		<option value="<%=ntRec.getNoteTpCd() %>"><%=ntRec.getNoteTpDesc() %></option>    
				              <%} }
				              }%> 
				            </select>				         
					</td>
					<td> 
						<textarea name="notesDescription" id="notesDescription" cols="42" rows="2" style="border: solid 1px #336699"></textarea> 
					</td>
				</tr>
			</table>
			<br>
	<table width="100%">
	<tr align="right">
		<td align="right">
			<a class="btn" href="javascript:void fnCreateTask()">Create Task</a>
			<a class="btn" href="javascript:void fnCloseSearchDlg('servAddTaskDiv')">Close</a>
		</td>
	</tr>
</table>
