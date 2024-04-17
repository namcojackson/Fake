
<%@page import="com.canon.apps.servreq.beans.CanonE307MeterErrorCodesBean"%>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceMtrRdsAPIUtil"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307MtrRdTpeRec"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307SvcMtrRdsRec"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307DebriefDetailsDAO"%>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307TaskCancelRsnRec"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307CancelTaskRsnInfoRec"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="parts.dbcommon.EZDDBCICarrier"%> 

<%@page import="java.util.*"%>
<style>
	 .ui-dialog .ui-dialog-title{
	 	float: none;
	 }
</style>
<script>
$(function(){
   $("input.autoInteger").autoNumeric({aSep: '', mDec: 0, mNum: 10});	
   
   $(".datePicker").datepicker({
		 dateFormat: 'M dd yy',
		 changeMonth: true,
		 changeYear: true
	 });
});	

	function fnSubmitCrctnRds(){
		var url = 'canonE307SRCorctnMeterReads.jsp?crnctRdSubmit=Y';
		show();
		$("#mtrRdTbl input").each(function() {
			$(this).removeAttr("disabled");
	   		$(this).removeAttr("readonly");
		  });
		var errorCode = $('#errorCd').val();
		if(errorCode =='NSZM0541E'){
			var invalidata='';
			var mtrSz = $('#mtrRdsSz').val();
	    	$("#origDate").val($('#curMtrDate0').val());
	    	for(i=0;i<mtrSz;i++){
	    		var mtrRd = $('#mtrRd'+i).val();
	    		var svcPhysMtrPk = $('#svcPhysMtrPk'+i).val();
	    		if(i==0){
	    			invalidata = svcPhysMtrPk+":"+mtrRd;
	    		}else{
	    			invalidata = invalidata+", "+svcPhysMtrPk+":"+mtrRd;
	    		}
	    	}
	    	$('#invalidData').val(invalidata);
	    	
		}else{
			$('#invalidData').val('');
		}
		
		$.post(url
            ,$("#crctnRdsFrm").serialize()
            ,function(data){
			hide();
			 $('#mtrrCrctnDiv').html("");
             $('#mtrrCrctnDiv').html(data);
            });		
	/*	 var url="canonE307SRCorctnMeterReads.jsp?crnctRdSubmit=Y";
		 $("#crctnRdsFrm").attr("action",url);
		 $("#crctnRdsFrm").submit();	
		 */
	}
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

function parseDate(dt) {
	  var months = {jan:0,feb:1,mar:2,apr:3,may:4,jun:5,
	                jul:6,aug:7,sep:8,oct:9,nov:10,dec:11};
	  var p = dt.split(' ');
	  return new Date(p[2], months[p[0].toLowerCase()], p[1]);
}
function fnCheckDate(iVal){
	var dt = $("#curMtrDate"+iVal).val();
	var mtrSz = $('#mtrRdsSz').val();
    var nwDt =  parseDate(dt);
   

    var d = new Date(); 
    d.setHours(0);
    d.setMinutes(0);
    d.setSeconds(0);

    if(nwDt> d){
    	alert("Correction Date can't be future date.");
    	$("#curMtrDate"+iVal).val($('#origDate').val());
    }else{
    	var mtrSz = $('#mtrRdsSz').val();
    	$("#origDate").val($('#curMtrDate'+iVal).val());
    	for(i=0;i<mtrSz;i++){
  
    			$('#curMtrDate'+i).val(dt);

    	}
    }
}
</script>
<% 	
	String ctxPath = request.getContextPath();
	String imgSrc=ctxPath+"/common/images/download.png";
	String fsrNum = request.getParameter("fsr");
	String taskNumber = request.getParameter("crctnTaskNumber");
	String userNm = request.getParameter("userName");
	String svcMachMstrPk = request.getParameter("svcMachMstrPk");
	String tskStsUpdFlg = request.getParameter("tskStsUpdFlg");
	String iVal = request.getParameter("iVal");
	CanonE307ServiceRequestDetailsDao objDao = new CanonE307ServiceRequestDetailsDao();
    CanonCommonUtil util = new CanonCommonUtil();
    String strAction = request.getParameter("crnctRdSubmit");
    System.out.println("Corection Form strAction: "+ strAction+" svcMachMstrPk: "+svcMachMstrPk+" taskNumber: "+taskNumber);
    
    int rdFailCnt = 0;
	String retMsg="";
    String rFlg ="";
	String eMsgId = "eMsg";
	CanonE307ServiceMtrRdsAPIUtil rdsApi = new CanonE307ServiceMtrRdsAPIUtil ();
	CanonE307DebriefDetailsDAO objDeb = new CanonE307DebriefDetailsDAO();
	String res[] = new String[2];
	String invalidReadsFlg = "";
	String errorCode = "";
	String errorFlg = "";
	String invalidRdErrFlg="";

    if("Y".equals(strAction)){
    	System.out.println("Before calling meter reads API..");
    	ArrayList arList  =objDeb.getOpenTasksLst(fsrNum);
    	if(arList!=null && arList.size()>0){
    		for(int x=0;x<arList.size();x++){
				String strTskNumber = (String)arList.get(x);
   				res = rdsApi.submitMtrRds(request, "",strTskNumber,"","");
	    	 if(res!=null && "Y".equals(res[0])) {
				  rFlg=util.checkNull(res[0]);
				  retMsg="Meter Reads Submitted Successfully.";	
				 // rdFailCnt = 0;
			 }else{
				  rFlg=util.checkNull(res[0]);
				  retMsg=util.checkNull(res[1]);
				
				  ArrayList errLst = objDeb.getMeterErrorCodes("CORRECTION","");
				  if(errLst!=null && errLst.size()>0){
					  for(int i=0;i<errLst.size();i++){
						  CanonE307MeterErrorCodesBean errBean = (CanonE307MeterErrorCodesBean)errLst.get(i);
						  String mtrErrCd = errBean.getStrMtrErrCd();
						  String invldFlg = errBean.getStrLowerRdFlg();
						  System.out.println("invldFlg: "+ invldFlg);
						  if(res[1].contains(mtrErrCd)) {
							  errorFlg="Y";
							  if("Y".equals(invldFlg)){
								  invalidRdErrFlg="Y"; 
								  errorCode =  mtrErrCd;
							  }else{
								  errorCode = mtrErrCd;
							  }
					  	}
						
					  }
				  }
				  System.out.println("Correction Form errorCode: "+ errorCode);
					if(errorCode!=null && util.checkNull(errorCode).length()>0){
						  rdFailCnt = request.getParameter("mtrRdFailCnt")==null?0:Integer.parseInt(request.getParameter("mtrRdFailCnt"));
						  rdFailCnt = rdFailCnt+1;
					  }
				  }
   	 
    		}
    	}

    }
%>
<script>
<%
System.out.println("rFlg: "+ rFlg+" tskStsUpdFlg: "+tskStsUpdFlg+ "errorCode: "+ errorCode);
if("Y".equals(rFlg) && "Y".equals(tskStsUpdFlg)){
%>
	$("#mtrSubmitFlg"+'<%=iVal%>').val('<%=rFlg%>'); 
<%	
}
%>
</script>
<div id="mtrrCrctnDiv"> 
<div id="tskMsgDiv">
<% if("Y".equals(rFlg)){ rdFailCnt = 0; %>
		<p id="eMsgRd" style="color:green;font-weight:bold;margin:5px;;font-size: 15px;"><%=retMsg%></p>
<% }else{ %>
	<p id="<%=eMsgId%>"><%=retMsg%></p>
<%} %>
</div>
	<%
	if("Y".equals(tskStsUpdFlg) && "".equals(rFlg)){
	%>
		<div id="tskMsg"><p id="eMsg">Please enter 'In Meters' before changing status to 'Arrived'. </p></div>
	<%} %>
<form name="crctnRdsFrm" id="crctnRdsFrm" method="post" action="canonE307SRCorctnMeterReads.jsp">
	<input type="hidden" name="fsr" id="fsr" value="<%=fsrNum%>">
	<input type="hidden" name="userName" id="userName" value="<%=userNm %>">
	<input type="hidden" name="crctnTaskNumber" id="crctnTaskNumber" value="<%=taskNumber%>">
	<input type="hidden" name="svcMachMstrPk" id="svcMachMstrPk" value="<%=svcMachMstrPk%>">
	<input type="hidden" name="mtrRdFailCnt" id="mtrRdFailCnt" value="<%=rdFailCnt%>">
	<input type="hidden" name="tskStsUpdFlg" id="tskStsUpdFlg" value="<%=tskStsUpdFlg%>">
	<input type="hidden" name="errorFlg" id="errorFlg" value="<%=errorFlg %>">
	<input type="hidden" name="iVal" id="iVal" value="<%=iVal%>">
	<table id="mtrRdTbl" class="lov-table" style="width: 98%;"  cellpadding="1" cellspacing="1" style='background-color:#cccc99'>
	
		<tr>
			<th colspan="4">Meters</th>
		</tr>
		<tr>
			<th width="25%">Counter Name</th>
			<th nowrap width="20%">Meter Type</th>
			<th width="20%">Correction Date</th>	
			<th width="10%">Current Meter Read</th>
		</tr>
		<tbody>
		<%
				ArrayList mtrLst = objDeb.getCrctnSvcMtrDet(svcMachMstrPk, fsrNum, taskNumber);
				ArrayList tpLst =  objDeb.getMtrRdType("");
				
				java.util.Date sysDate = new java.util.Date();
				String curDate = new SimpleDateFormat("MMM dd yyyy").format(sysDate).toString();
				String meterDate = request.getParameter("curMtrDate0")==null?util.checkNull(curDate):request.getParameter("curMtrDate0");
				meterDate =util.formatDateString(util.DT_FORMAT5, util.DT_FORMAT3, meterDate);

				String invalidData = "";

				if(mtrLst!=null && mtrLst.size()>0){
					for(int y=0;y<mtrLst.size();y++){
						CanonE307SvcMtrRdsRec rdObj = (CanonE307SvcMtrRdsRec)mtrLst.get(y);
						
						if(invalidData==null || "".equals(invalidData) ){
							invalidData = rdObj.getSvcPhysMtrPk()+":"+util.checkNull(request.getParameter("mtrRd"+y));
						}else{
							invalidData = invalidData+ ", "+rdObj.getSvcPhysMtrPk()+":"+util.checkNull(request.getParameter("mtrRd"+y));
						}
						
						//System.out.println("Correction Meter Read Tp: "+ rdObj.getAttribute3());
						String inRdTp  = request.getParameter("mtrRdTp"+y)==null?"":request.getParameter("mtrRdTp"+y);
					%>
					<input type="hidden" id="svcPhysMtrPk<%=y%>" name="svcPhysMtrPk<%=y%>" value="<%=rdObj.getSvcPhysMtrPk() %>">
					<tr>
						<td width="25%"><%=rdObj.getMtrLbDescTxt() %></td>
					
					<td nowrap>
					<select id="mtrRdTp<%=y%>" name="mtrRdTp<%=y%>" style="width:155px;margin:0 5px;">
						<%
						String inMtrSelect ="";
						
						if(tpLst!=null && tpLst.size()>0){
							for(int x=0;x<tpLst.size();x++){
								CanonE307MtrRdTpeRec rdTpObj = (CanonE307MtrRdTpeRec)tpLst.get(x);
									if(rdTpObj.getStrMtrRdTpCd().equals(inRdTp)){
										inMtrSelect = "selected";
									}else{
										inMtrSelect = "";
									}
							%>
							<option value="<%=rdTpObj.getStrMtrRdTpCd() %>" <%=inMtrSelect %>><%=rdTpObj.getStrMtrRdTpNm() %></option>													
						<%
								}
							}
						%>
						</select></td>
						
							<input type="hidden" id="origDate" name="origDate" value="<%=request.getParameter("curMtrDate"+y)==null?util.checkNull(curDate):request.getParameter("curMtrDate"+y)%>">
							<td><input type="text" id="curMtrDate<%=y%>" name="curMtrDate<%=y%>" value="<%=request.getParameter("curMtrDate"+y)==null?util.checkNull(curDate):request.getParameter("curMtrDate"+y)%>" class="datePicker rdl" style="width:98%" onchange="fnCheckDate('<%=y%>')" readonly></td>
						
						<td width="20%"><input type="text" name="mtrRd<%=y%>" id="mtrRd<%=y%>" maxlength="10" class="outRds autoInteger" value="<%=request.getParameter("mtrRd"+y)==null?"":util.checkNull(request.getParameter("mtrRd"+y)) %>" style="width:98%;"  size="20"></td>
						</tr>
					<%
					}
					%>
					<input type="hidden" id='mtrRdsSz' name='mtrRdsSz' value="<%=mtrLst.size() %>">
					
				<%	
				}
			%>
		</tbody>
	</table>

<table width="100%">
	<tr align="right">
		<td align="right">
			<a class="btn" href="javascript:void fnSubmitCrctnRds()">Submit</a>
			<a class="btn" href="javascript:void fnCloseMtrDlg('mtrrCrctnDiv')">Close</a>
		</td>
	</tr>
</table>
<%
System.out.println("errorCode: "+ errorCode+ " invalidData: "+invalidData+" mtrDate: "+meterDate);
String meterReadDate =util.checkNull(request.getParameter("curMtrDate0"));
ArrayList invldMtrLst = new ArrayList();
	if("Y".equals(invalidRdErrFlg)){
		invldMtrLst = objDeb.getInvalidSvcMtrDet(invalidData, svcMachMstrPk, meterDate);
		errorCode="NSZM0541E";
	}else if(errorCode!=null && errorCode.length()>0){
		invldMtrLst = objDeb.getInvalidSvcDispDet(fsrNum, taskNumber, svcMachMstrPk);
		//errorCode = "NSZM1300W";
		invalidData="";
	}
		if(invldMtrLst!=null && invldMtrLst.size()>0){
			//System.out.println("invldMtrLst.size(): "+ invldMtrLst.size());
%>
<br><br>
<div id="tskMsg"><p id="eMsg">The following meter reads will be invalidated if you submit the above meter reads. </p>
						<!-- <p>Please correct these meter reads in S21 Meter Entry after submitted</p> --></div>
	<table id="invldMtrRdTbl" class="lov-table" style="width: 98%;"  cellpadding="1" cellspacing="1" style='background-color:#cccc99'>
	<input type="hidden" id='invalidData' name='invalidData' value="<%=invalidData %>">
	<input type="hidden" id='errorCd' name='errorCd' value="<%=errorCode %>">
	
		<tr>
			<th colspan="6">Meters</th>
		</tr>
		<tr>
		<th width="20%">Meter Read Date</th>	
			<th width="25%">Counter Name</th>
			<th nowrap width="20%">Meter Type</th>
			<th width="10%">Read Count</th>
			<th width="10%">Task#</th>
			<th width="10%">In/Out</th>
		</tr>
<%			
			for(int x=0;x<invldMtrLst.size();x++){
				CanonE307SvcMtrRdsRec rdObj = (CanonE307SvcMtrRdsRec)invldMtrLst.get(x);
%>
			<tr>
				<td width="25%"><%=rdObj.getAttribute4()%></td>
				<td width="25%"><%=rdObj.getMtrLbDescTxt() %></td>
				<td width="25%"><%=rdObj.getAttribute5() %></td>
				<td width="25%"><%=rdObj.getInMtrRdCnt() %></td>
				<td width="25%"><%=rdObj.getSvcTaskNum() %></td>
				<td width="25%"><%=rdObj.getAttribute2() %></td>
			</tr>		
<%			
			}
			
		}else{
	%>
		<input type="hidden" id='invalidData' name='invalidData' value="<%=invalidData %>">
		<input type="hidden" id='errorCd' name='errorCd' value="<%=errorCode %>">
	<%		
		}

%>
</form>
</div>

