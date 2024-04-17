<%@page import="com.canon.common.*" %>  
<%@ page import="java.text.*"%> 
<%@ page import="java.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="parts.dbcommon.EZDDBCICarrier"%>
<%@page import="java.util.Enumeration"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="business.parts.NSZC045001PMsg"%>
<%@page import="com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001"%>
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="canon.apps.common.CanonConstants"%>
<%@page import="parts.dbcommon.EZDDBCICarrier"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.canon.cusa.s21.framework.security.S21AuthenticationException"%>
<%@page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileService"%>
<%@page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory"%>
<%@page import="com.canon.cusa.s21.framework.security.S21SecurityException"%>
<%@page import="com.canon.cusa.s21.framework.security.S21Authentication"%>
<%@page import="com.canon.cusa.s21.framework.security.S21AuthorizationAction"%>
<%@ page language="java" import="parts.common.EZDCommonFunc" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContext" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextFactory" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.config.S21ConfigurationException" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.helpers.S21AuthenticationHelper" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.authentication.token.S21UserPasswordAuthenticationToken" %>
<%@ page language="java" import="com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SvcChrg"%>
<%@ page import="static canon.apps.common.CanonS21SessionValidate.commonRoot"%>

<%@ page import="com.canon.apps.*" %>  
<%@ page import="java.text.*"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.canon.apps.servreq.dao.*" %>  
<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<%@ page import="com.canon.apps.servreq.dao.CanonE307DebriefDetailsDAO" %>  
<%@page import="com.canon.common.CanonCommonUtil"%>	

<script>
function fnSubmitLst(){
	$("#eMsg").html("");
	$("#errorWidget").hide();
	showPleaseWait();
	var retFlg ="";
	$.ajax({
		url: 'canonE307DebriefSubmitValidation.jsp',
		cache: false,
		dataType : "text",
		async:false,
		success : function(result)
		{
			hidePleaseWait();
			if(result!=null){
				retFlg = $.trim(result);
			}
		}
	});	
	if(retFlg == 'Y'){
		$("#chkIstlTbl input").each(function() {
		    var input = $(this);
		    if (input.val() == input.attr('placeholder')) {
		      input.val('');
		    }
		});
		var url = 'canonE307DebriefInstldChkLst.jsp?action=submit';
		   showPleaseWait();
	    $.post(url
	            ,$("#chkLstLovFrm").serialize()
	            ,function(data){
	    		hidePleaseWait();
				 $('#laborItemDiv').html("");
	             $('#laborItemDiv').html(data);
	            });
	}else{
		$('#eMsg').attr('style', 'padding-left: 27px;');
		$("#eMsg").html("You cannot process it during nightly batch proccessing because inventory discrepancy will occur.");
		$("#eMsg").css({"color": "red", "font-size": "15"}); 
		$("#errorWidget").show();	
	}
}

$(document).ready(function(){
	var taskSts = $('#tskSts').val();
	if(taskSts == 'Closed'){
		 $('#chkIstlTbl input').each(function (){
			 $(this).attr('disabled', true);	
		 });
		 $("input.chkGrp").attr("disabled", true);
	}	
		$('#chkInstComp').click(function(){
	        if($(this).is(':checked')) {
	            $(".chkGrp").prop("checked", true);
	        } else {
	            $(".chkGrp").prop("checked", false);
	        }                
	    });
	

});

</script>
<%
    SimpleDateFormat sdf = new SimpleDateFormat("dd'-'MMM'-'yyyy");
	System.out.println("Inside checkList");
    SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");  	SimpleDateFormat format = new SimpleDateFormat("z");
	String timezone = format.format(new Date());
	String invokeTimestamp = lsDateFmt.format(new Date()); 
	String loginUser= CanonS21SessionValidate.getUserName();
	
	//Initialize S21DB-Carrier (It should be done. It leads NullPointer exception when didn't initialize.)
	//These contents are used as a default data in S21-Standard table. (Update user, time, time-zone, program id, company code)
	EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
	EZDDBCICarrier.setProgID("EXTNE307");
	
	CanonCommonUtil util = new CanonCommonUtil();
	String ctxPath = request.getContextPath();
	Object[] result = null;

	String strSerNum = request.getParameter("serNum");
	String strTskNum = request.getParameter("tskNum");
	String strAction = request.getParameter("action");
	String strFsrNum = request.getParameter("fsrNum");
	String strUser = request.getParameter("userName");
	String tskSts = request.getParameter("tskSts");
	System.out.println("strSerNum in LOV : " + strSerNum+" strAction : "+strAction);
	String mName= request.getParameter("modalName");

	CanonE307ServiceReqInsCheckAPIUtil chkLstAPI = new CanonE307ServiceReqInsCheckAPIUtil();
	CanonE307DebriefDetailsDAO objDet = new CanonE307DebriefDetailsDAO();
	ArrayList<CanonE307DebriefInstallRec> chkLst = new ArrayList();
	String retMsg ="";
	String errMsg = "";	
	String rFlg = "";

	if("submit".equals(strAction)){
		System.out.println("Inside SUbmit");
 		String[] resArr = chkLstAPI.saveInstVerDet(request);
 		rFlg = util.checkNull(resArr[0]);
		if("Y".equals(rFlg)){
			retMsg = "Debrief successful";
		}else{
			retMsg = util.checkNull(resArr[1]);	
		}
		System.out.println("rFlg : "+ rFlg);
	//	retMsg ="Successfull";
	}
	System.out.println("strSerNum : "+ strSerNum+" strTskNum : "+strTskNum);
	chkLst =	objDet.getCheckListInfo(strSerNum, strTskNum, "WAITING FOR INSTALLATION");	
	String instlChkFlg="";
	String onlineValidFlg = "";
	if(chkLst!=null && chkLst.size()>0){
		int k=0;
		if("E".equals(rFlg)){
			for(CanonE307DebriefInstallRec chkLstBean : chkLst){
				String corctdSrl = request.getParameter("crctdSrl"+k)==null?"":request.getParameter("crctdSrl"+k);
				String verfdChkFlg = request.getParameter("verfdChk"+k)==null?"N":"Y";
				System.out.println("verfdChkFlg: "+verfdChkFlg +" k Val : "+k);
				chkLstBean.setStrCorctdSrl(corctdSrl);
				chkLstBean.setStrIstlChkVerFlg(verfdChkFlg);
				k++;
			}
			
		}
		for(CanonE307DebriefInstallRec chkLstBean : chkLst){
			tskSts = chkLstBean.getStrAttribute1();
			onlineValidFlg = chkLstBean.getStrAttribute2();
			if("Y".equalsIgnoreCase(chkLstBean.getStrIstlCpltFlg())){
				instlChkFlg = "checked=checked";
			}
		}
	}
	
%>
<form name="chkLstLovFrm" id="chkLstLovFrm" method="post" action="canonE307DebriefInstlChkLstLov.jsp">
	<input type="hidden" name="modalName" id="modalName" value="<%=mName%>">
	<input type="hidden" name="fsrNum" id="fsrNum" value="<%=strFsrNum %>">
	<input type="hidden" name="tskNum" id="tskNum" value="<%=strTskNum%>">
	<input type="hidden" name="userName" id="userName" value="<%=strUser%>">
	<input type="hidden" name="serNum" id="serNum" value="<%=strSerNum%>">
	<input type="hidden" name="tskSts" id="tskSts" value="<%=tskSts%>">	
	
	<div id="errorWidget"  style="padding-bottom: 5px;padding-top:5px">
	<% if("Y".equals(rFlg)){%>
		<p id="eMsg" style="color:green;font-weight:bold;margin:10px 28px;font-size: 15px;"><%=retMsg==null?"":retMsg%></p>
	<%}else if("N".equals(onlineValidFlg)){%>
		<p id="eMsg" style="font-weight:bold;margin:10px 28px;font-size: 15px;color:red;">You cannot process it during nightly batch proccessing because inventory discrepancy will occur.</p>
 	<% }else{ %>
 		<p id="eMsg" style="font-weight:bold;margin:10px 28px;font-size: 15px;color:red;"><%=retMsg==null?"":retMsg%></p>
 	<% } %>
	
	
	
		
	</div> 


	<table>
		<tr>
			<td rowspan=2><input type="checkbox" name="chkInstComp" id="chkInstComp" <%=instlChkFlg%> class="chkGrp"></td>
			<td style="font-weight:bold;font-size: 15px;" nowrap rowspan=2>Complete</td>
			<td width="25%">&nbsp;</td>
			<td style="width:50%;align:right;" nowrap><img src="<%=ctxPath%>/common/images/blue_star.jpg"  height='21' />&nbsp;Indicates components to be installed</td>
		</tr>
		<tr>
			<td width="25%">&nbsp;</td>
			<td style="width:50%;align:right;" nowrap><img src="<%=ctxPath%>/common/images/red_star.png"  height='21' />&nbsp;Indicates components to be removed</td>
		</tr>		
	</table>

<%

if(chkLst!=null && chkLst.size()>0){
	System.out.println("size: "+ chkLst.size());
	%>
	<table id="chkIstlTbl" style="width:98%;align:center;" cellspacing="1" >		
	<%
	int i=0;
	String verFlg="";
	for(CanonE307DebriefInstallRec chkLstBean : chkLst){
		System.out.println("Inst Ver Flg: "+chkLstBean.getStrIstlChkVerFlg()+" SerNum: "+chkLstBean.getStrSerNum());
		if("Y".equalsIgnoreCase(chkLstBean.getStrIstlChkVerFlg())){
			verFlg = "checked=checked";
		}else{
			verFlg="";
		}
%>
<tr><td style="height:100%;padding: 0px 10px 0px 10px;">
		<div id="chkLstDiv" style="width:100%;height:97%;border:1px solid #CCC;">
			<table style="width:100%;border:0px;" cellspacing="1">
				<tr><td width="20%">Model#</td><td><%=util.checkNull(chkLstBean.getStrModel()) %></td>
				<%System.out.println("New Item Flg: "+chkLstBean.getStrNewItmFlg()); %>
				<%if("Y".equals(chkLstBean.getStrNewItmFlg())){
				%>
					<td style="width:5%;align:right"><img src="<%=ctxPath%>/common/images/blue_star.jpg"  height='21' /></td>				
				<%	
				} %>
				<%if("Y".equals(chkLstBean.getStrRmvItmFlg())){
				%>
					<td style="width:5%;align:right"><img src="<%=ctxPath%>/common/images/red_star.png"  height='21' /></td>				
				<%	
				} %>				
				</tr>
				<% 
					if(util.checkNull(chkLstBean.getStrSerNum())!="" && util.checkNull(chkLstBean.getStrSerNum()).length()>0){
				%>
				<tr><td width="20%">Serial#</td><td><%=util.checkNull(chkLstBean.getStrSerNum()) %></td></tr>
				<%
					}
				%>
				<tr><td width="20%">Item</td><td><%=util.checkNull(chkLstBean.getStrItem()) %></td></tr>
				<tr><td width="20%">Desc</td><td><%=util.checkNull(chkLstBean.getStrItemDesc()) %></td></tr>
				<% 
					if(util.checkNull(chkLstBean.getStrSerNum())!="" && util.checkNull(chkLstBean.getStrSerNum()).length()>0){
				%>				
				<tr><td width="20%"><input type="checkbox" name="verfdChk<%=i%>" id="verfdChk<%=i%>" <%=verFlg%>  class="chkGrp" value="Y">	Verified
					</td>
					<td ><input type="text" id="crctdSrl<%=i%>" name="crctdSrl<%=i%>" placeholder="Corrected Serial#" value="<%=util.checkNull(chkLstBean.getStrCorctdSrl()) %>"></td>
				</tr>
				<%
					}
				%>
					<input type="hidden" name="mdseCd<%=i%>" id="mdseCd<%=i%>" value="<%=util.checkNull(chkLstBean.getStrItem())%>">
					<input type="hidden" name="serNum<%=i%>" id="serNum<%=i%>" value="<%=util.checkNull(chkLstBean.getStrSerNum())%>">
					<input type="hidden" name="modelId<%=i%>" id="modelId<%=i%>" value="<%=util.checkNull(chkLstBean.getStrModelId()) %>">
					<input type="hidden" name="modelNm<%=i%>" id="modelNm<%=i%>" value="<%=util.checkNull(chkLstBean.getStrModel())%>">
					<input type="hidden" name="chkLstPk<%=i%>" id="chkLstPk<%=i%>" value="<%=util.checkNull(chkLstBean.getStrChkLstPk()) %>">
					<input type="hidden" name="svcMachMstr" id="svcMachMstr" value="<%=util.checkNull(chkLstBean.getStrSvcMachMstr()) %>">
					<input type="hidden" name="svcCnfgMstr<%=i%>" id="svcCnfgMstr<%=i%>" value="<%=util.checkNull(chkLstBean.getStrSvcConfigMstr()) %>">	
				</table>
		</div>
	</td>
</tr>	
<%	
	i++;
	}
	%>
	<input type="hidden" id="chkLstSz" name="chkLstSz" value="<%=chkLst.size()%>">
	<%
}else{
%>
<table style="width:100%;border:1px solid #CCC;">
<tr>
<td colspan=6> No data found.	
</td>
</tr>
<%	
}
%>
</table>

 		<div class="info-div03" style="position: relative; top: -8px;">
			<div class="case-number">
			<%
				if(!("Closed".equals(tskSts)) && chkLst!=null && chkLst.size()>0){
			%>
				 <a class="btn" href="javascript:void fnSubmitLst()">Submit</a> 
			<%		
				}
			%>
			    <a class="btn" href="javascript:void fnCloseMdlDlg('<%=mName%>')">Close</a>
			 </div>
		</div>
</form>
