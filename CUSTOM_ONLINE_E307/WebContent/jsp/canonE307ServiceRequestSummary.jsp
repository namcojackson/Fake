<%@ page import="com.canon.apps.servreq.dao.*" %>  
<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<%@page import="canon.apps.common.CanonCustomProfile"%>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil"%>
<%!
	public String escape(String s){
		if(s!=null) return s.replace("\"","\\\"");
		return "";
	}

%>
<%
	String pageTitle="Summary";
	ArrayList<String> menuList = new ArrayList<String>();
	// menuList.add("MENU1:N:Charges:fnCharges();");
/* 	menuList.add("MENU2:N:Billing:N");
	menuList.add("MENU3:N:Machine History:fnMachHistory();");
	menuList.add("MENU4:N:Add Task:fnAddTask();");	
	menuList.add("navEscalate:menuToggleEscalate:Escalations:N");	 */ 
	menuList.add("sp:btn:Scratch Pad:opnSCPad();");
%>
<%@ include file="canonE307ServReqHeader.jsp" %> 
<script src='<%=ctxPath%>/e307/js/collapse.js' type='text/javascript'></script>
<link rel="stylesheet" href="<%=ctxPath%>/e307/css/canonE307ServiceReqCreate.css" type="text/css">

<% 
    String strAhsMsg="";
    String strSerNum ="";
	String retMsg="";
	String sucMsg = "";
	String eMsgId = "eMsg";
	String disStyle = "display: none;padding-bottom: 5px;padding-left: 0px;padding-top:5px;font-size: 15px;font-weight: bold;";
	CanonE307ServiceRequestDetailsDao detailObj = new CanonE307ServiceRequestDetailsDao();
	CanonE307ServiceReqCreateDao crDao = new CanonE307ServiceReqCreateDao();
	CanonE307ServiceReqUpdateAPIUtil objApi= new CanonE307ServiceReqUpdateAPIUtil();
	CanonE307ServiceReqSumryAPIUtil utilObj = new CanonE307ServiceReqSumryAPIUtil();
	String strFSRNumber=request.getParameter("fsr")==null?"":request.getParameter("fsr");
	String strTskNum = request.getParameter("taskNum")==null?"":request.getParameter("taskNum");
	String strUpdFlag=request.getParameter("updFlg");
	String strModeCd = request.getParameter("modeCode");
	String tskSucc  = request.getParameter("tskSucc");
	String addTsk  = request.getParameter("addTsk");
	String crSRFlg = request.getParameter("crSRFlg");
	String strShowVend = request.getParameter("showVend")==null?"":request.getParameter("showVend");
	
	String tNumHis = request.getParameter("tNum")==null?"0":request.getParameter("tNum");
	System.out.println("strModeCd : " +strModeCd+" tskSucc : "+tskSucc+" addTsk : "+addTsk+"strTskNum: "+strTskNum+" strFSRNumber: "+strFSRNumber);

	if("Y".equals(strUpdFlag)){
		int tskCount = request.getParameter("tskSize")==null?0:Integer.parseInt(request.getParameter("tskSize"));
	//	 String[] resArr =  objApi.updateServicerequest(request); //UC
         String rFlg = "";
		 List resAl = new ArrayList();
		 if("03".equals(strModeCd)){
			 String[] canArr = objApi.cancelServicerequest(request);
			 rFlg= util.checkNull(canArr[0]);
			 System.out.println("rFlg: "+ rFlg+" Msg : "+canArr[1]);
			 if(rFlg.equalsIgnoreCase("Y")){
				 	 eMsgId="sucMsg";
				 	 disStyle = "padding-bottom: 5px;padding-left: 0px;padding-top:5px;font-size: 15px;font-weight: bold;";
					 sucMsg="FSR Cancelled...";
				}else{
					retMsg=canArr[1];
				}
		 }else{
			 resAl= objApi.updateServicerequest(request); 
			if(resAl!=null && resAl.size()>0){
				 for(int i=0;i<resAl.size();i++){
					// System.out.println("Error size: "+ resAl.size());
					 String[] resArr = (String[])resAl.get(i);
					 rFlg= util.checkNull(resArr[0]);
					 disStyle = "padding-bottom: 5px;padding-left: 0px;padding-top:5px;font-size: 15px;font-weight: bold;";
					 if(rFlg.equalsIgnoreCase("Y")){
							sucMsg="Updated FSR Successfully...";
							eMsgId="sucMsg";
					 }else{
						 if(i==resAl.size()-1){
							 retMsg= retMsg+resArr[1]; 
						 }else{
						 	retMsg= retMsg+resArr[1]+"<BR>";
						 }
					 }
				 }
		 	}
		 }
		 if("null".equals(retMsg) || "".equals(retMsg) || retMsg.length()<0){
			 retMsg =sucMsg; 
		 }
		//  String rFlg= util.checkNull(resArr[0]);
		// response.sendRedirect(ctxPath+"/e307/jsp/canonE307ServiceRequestSummary.jsp?fsr="+strFSRNumber);
		strUpdFlag="N";
	}
	String strCnclFlg = request.getParameter("cnclFlg");
	String strCnclTsk = request.getParameter("cnclTsk");
	String schdDisptEmlFlg = request.getParameter("schdDisptEmlFlg");
	String slsDt = request.getParameter("slsDt");
	String shwHdFlg = request.getParameter("shwHdFlg");
	String shwHidHdr="Show Details";
	String shwHdStl = "display: none;padding: 2px 10px;";
	if("Y".equals(shwHdFlg)){
		shwHidHdr="Hide Details";
		shwHdStl="padding: 2px 10px;";
	}
	System.out.println("strFSRNumber: " + strFSRNumber+" strCnclTsk : "+strCnclTsk+" loginUser : "+loginUser+" schdDisptEmlFlg : "+schdDisptEmlFlg);
/* 	if("Y".equals(strCnclFlg)){
		String[] resArr = utilObj.servReqCancelTask(strFSRNumber, strCnclTsk, loginUser, schdDisptEmlFlg, slsDt);
		  String rFlg= util.checkNull(resArr[0]);
		  if(rFlg.equalsIgnoreCase("Y")){
			// retMsg=resArr[1];
			 eMsgId="sucMsg";
			 retMsg="Cancelled Task Successfully...";
			 disStyle = "padding-bottom: 5px;padding-left: 0px;padding-top:5px;font-size: 15px;font-weight: bold;";
	      }else{
			  retMsg=resArr[1];
			  disStyle = "padding-bottom: 5px;padding-left: 0px;padding-top:5px;font-size: 15px;font-weight: bold;";
		  }
		  strCnclFlg="N";
	}  */
	Object[] obj = detailObj.getServiceRequestDetails(strFSRNumber, strTskNum);
	CanonE307MachineCustSearchResultsRec machObj =new CanonE307MachineCustSearchResultsRec();
	CanonE307ServiceReqContractRec contObj = new CanonE307ServiceReqContractRec();
	CanonE307ServiceReqInfoRec servObj = new CanonE307ServiceReqInfoRec();
	CanonE307DebriefViewNotesRec vNoteRec = new CanonE307DebriefViewNotesRec();
	String tmZone="";
	if(obj[0] !=null) {
		machObj =		 (CanonE307MachineCustSearchResultsRec)obj[0] ;
	}
	if(machObj!=null)
		strSerNum =		machObj.getSerNum();
	

	if(obj[1] !=null) {
		servObj = (CanonE307ServiceReqInfoRec)obj[1] ;
	} 
	if(obj[2] !=null) {
		contObj = (CanonE307ServiceReqContractRec)obj[2] ;
	} 
	if(servObj!=null){
		String postalCd = util.checkNull(servObj.getStrCurPostCd());
		String cntryCd = util.checkNull(servObj.getStrCurCtryCd());
		tmZone = utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(),"");
	}
	System.out.println("Mach Status: "+ servObj.getStrMachStatus());
	String strMachStat = "";
	if("Y".equals(servObj.getStrMachStatus())){
		strMachStat="N";
	}else{
		strMachStat="Y";
	}
	String slaRespTime = "";
	if(machObj.getSvcMachMstrPk()!=null && machObj.getSvcMachMstrPk().length()>0){
		slaRespTime = crDao.getRspTmMnAot(machObj.getSvcMachMstrPk(), strMachStat, machObj.getModel());
	}
	
	CanonE307TaskDetailsRec  tskObj = new CanonE307TaskDetailsRec();
	List<CanonE307TaskDetailsRec> taskList =new ArrayList<CanonE307TaskDetailsRec>();
	String lblVal="Estimated";
	if(obj[3] !=null) {
		   taskList =(ArrayList<CanonE307TaskDetailsRec>) obj[3];
		   String dispFlag = "N";
		   if(taskList!=null && taskList.size()>0){
			   for(int k=0;k<taskList.size();k++){
				   tskObj = (CanonE307TaskDetailsRec)taskList.get(k);
				   System.out.println("Task Status: "+ tskObj.getStrTaskStatus());
				   if(!("Cancelled".equals(tskObj.getStrTaskStatus()))){
					   dispFlag = "Y";
					   break;
				    }
			   	}
			    if("N".equals(dispFlag)){
				   tskObj = (CanonE307TaskDetailsRec)taskList.get(0);
			    }
			}
		   if(("Closed".equals(tskObj.getStrTaskStatus()))|| ("Cancelled".equals(tskObj.getStrTaskStatus())) || ("Completed".equals(tskObj.getStrTaskStatus()))){
			   lblVal="Actual";
		   }
	 }

	CanonE307BillToCustCreditAuthBean authBeanObj = (CanonE307BillToCustCreditAuthBean)new CanonE307ServiceReqCreateDao().getCustInfo(util.checkNull(machObj.getSerNum()), util.checkNull(machObj.getSvcMachMstrPk()));
	CanonE307ServiceReqAPIUtil apiUti= new CanonE307ServiceReqAPIUtil();
	String[] arrAch = apiUti.getBillCode(machObj.getSvcMachMstrPk(), servObj.getStrFutureServDate(), "0", "", "", util.checkNull(tskObj.getStrTaskTypeCd()), loginUser,"N");  //UC		 

	String BILL_CODE="";
	String ahsFlg = "";
	String hrlyRt ="";
	String dsContrPk="";
	String ahsEnblFlg = "";
	String minHrs = "";
	if(arrAch!=null && arrAch.length>0){
		BILL_CODE = arrAch[0];
		ahsFlg = util.checkNull(arrAch[1]).trim();
		hrlyRt = arrAch[2];
		dsContrPk =  arrAch[9];
		minHrs = arrAch[7];
		ahsEnblFlg = util.checkNull(arrAch[10]).trim();
	}
	 
	 String checkAHS="";
	 String  ahsTitle="";
	 String  ahsMsg="";
	 String srvcHrs="";

	Object[] contObjs = detailObj.getContDetails(machObj.getSvcMachMstrPk(),machObj.getCurrLocAcctNo(), dsContrPk);
	if(contObjs[0]!=null)
	srvcHrs = ((String)contObjs[0]).toString();
	
	if(contObjs[1]!=null)
	contObj = (CanonE307ServiceReqContractRec)contObjs[1] ;
	

	//System.out.println("tskObj.getStrAttribute2: "+tskObj.getStrAttribute2() );
	
	String bllblFlg =crDao.getBllblFlg(util.checkNull(servObj.getStrSvcBillTpCd()));

	if(!"Closed".equals(util.checkNull(servObj.getStrSrSts()))){
		if("Y".equals(util.checkNull(tskObj.getStrAttribute2() ))){
			 if(bllblFlg.equalsIgnoreCase("N")){
				 ahsTitle="AFTER HOURS ENITITLED CALL";
				 ahsMsg="This Machine is Under Contract for After Hours Service <br> "+util.checkNull(servObj.getStrAttribute3()) ;
				 checkAHS="Y";
			 }else if(bllblFlg.equalsIgnoreCase("Y")){
				 ahsTitle ="AFTER HOURS CHARGEABLE CALL";
					 if("X3".equals(util.checkNull(tskObj.getStrTaskTypeCd())) && "ESS".equals(util.checkNull(servObj.getStrLineBizTpCd()))) {
						 ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour. Charge for Labor and Parts Cost. <br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
					 }else if("X3".equals(util.checkNull(tskObj.getStrTaskTypeCd()))){
						 ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour.";
							
						 	if(util.checkNull(arrAch[4]).length()>0)
						 		ahsMsg = ahsMsg+ " Charge for Labor, plus a Flat Travel charge not to exceed $"+ util.checkNull(arrAch[4])+" will be added to the cost of service and Parts Cost.";
						 		//ahsMsg = ahsMsg+ " Charge for Labor plus Travel charge of $"+ util.checkNull(arrAch[4])+" and Parts Cost. ";
						 					
						 		ahsMsg = ahsMsg+ "<br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
					 }else{
						 if("3".equals(servObj.getStrSvcBillTpCd())){
								ahsMsg= "Parts being Chargeable.";
						}else{
							 if("ESS".equals(util.checkNull(servObj.getStrLineBizTpCd()))){
								 ahsMsg="The Chargeable labor rate for this call is Estimated at $"+util.checkNull(arrAch[2])+" per hour hours.";
							 }else{
								 ahsMsg = "The Chargeable labor rate for this call is Estimated at $"+util.checkNull(arrAch[2])+" per hour with a Minimum of "+util.checkNull(arrAch[7])+" hours";
								 if(arrAch[4]!=null && util.checkNull(arrAch[4]).length()>0)
									    ahsMsg = ahsMsg+ " and a Flat Travel charge not to exceed $"+ util.checkNull(arrAch[4])+" will be added to the cost of service.";
								 }					 
						 	}
					 	}
			 		}
		 }else if("3".equals(util.checkNull(tskObj.getStrTaskTypeCd())) && "ESS".equals(util.checkNull(servObj.getStrLineBizTpCd())) && "Y".equalsIgnoreCase(bllblFlg)){
				ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour. Charge for Labor and Parts Cost. <br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
				
		 }else if("3".equals(util.checkNull(tskObj.getStrTaskTypeCd())) && "Y".equalsIgnoreCase(bllblFlg)){
		 	ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour.";
		 	
		  	if(util.checkNull(arrAch[4]).length()>0)
		  		ahsMsg = ahsMsg+ " Charge for Labor, plus a Flat Travel charge not to exceed $"+ util.checkNull(arrAch[4])+" will be added to the cost of service and Parts Cost.";
		  		//ahsMsg = ahsMsg+ " Charge for Labor plus Travel charge of $"+ util.checkNull(arrAch[4])+" and Parts Cost. ";
		  					
		  		ahsMsg = ahsMsg+ "<br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
		  				
		 }else if("Y".equalsIgnoreCase(bllblFlg)){
			 if("3".equals(servObj.getStrSvcBillTpCd())){
					ahsMsg= "Parts being Chargeable.";
				}else{
				 	 if("ESS".equals(util.checkNull(servObj.getStrLineBizTpCd()))){
				 			ahsMsg="This SR is Chargeable at a rate of $"+arrAch[2];
				 	 }else{
				 		 ahsMsg = "The Chargeable labor rate for this call is Estimated at $"+util.checkNull(arrAch[2])+" per hour with a Minimum of "+util.checkNull(arrAch[7])+" hours ";
				 		 if(arrAch[4]!=null && util.checkNull(arrAch[4]).length()>0)
				 			    ahsMsg = ahsMsg+ "and a Flat Travel charge not to exceed $"+ util.checkNull(arrAch[4])+" will be added to the cost of service.";
				  	}
				 }
		 	}
	}
	 String strAdTsk = util.checkNull(request.getParameter("addTsk"));
	 System.out.println(" strAdTsk : "+ strAdTsk+" crSRFlg: "+crSRFlg);
	 if("Y".equals(strAdTsk)){
		 retMsg ="Task was successfully created!";
		 disStyle = "padding-bottom: 5px;padding-left: 0px;padding-top:5px;font-size: 15px;font-weight: bold;";
		 eMsgId="sucMsg";
	 }
	 if("Y".equalsIgnoreCase(crSRFlg)){
		 retMsg ="Call Successfully Created!";
		 disStyle = "padding-bottom: 5px;padding-left: 0px;padding-top:5px;font-size: 15px;font-weight: bold;";
		 eMsgId="sucMsg";		 
	 }
System.out.println("FSRCratDt: "+ servObj.getStrFSRCratDt()+" post Cd: "+servObj.getStrCurPostCd()+" Country: "+servObj.getStrCurCtryCd());
	//String dtTm =  utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), servObj.getStrFSRCratDt());
  %>

<div id="main_content" style="min-height: 924px;">  

  	<div id="page_top">
		<h1>Advanced Service Call Center</h1>
	</div>
  <div style="margin:10px 0px 0px 0px;margin-left:25px;"><h1> Call Detail </h1></div>
  <table style="border:0px;margin:5px 0px 0px 0px;margin-left:25px;">
  	<%if(ahsMsg!=null && util.checkNull(ahsMsg).length()>0){ %>
		<tr>
			<td style="color: red;font-size: 15px;font-weight: bold;" align="left">
			   <%=ahsMsg %>
			</td>
		</tr>  
		<% } %>
	<tr>
	    <td>
			<div id="errorWidget"  style="<%=disStyle%>">
		 		<p id="<%=eMsgId%>"><%=retMsg%></p>
			</div>
	   </td>
	</tr>
	<%if(servObj.getStrCovidVacMsg()!=null && util.checkNull(servObj.getStrCovidVacMsg()).length()>0){ %>
		<tr>
			<td style="color: red;font-size: 15px;font-weight: bold;" align="left">
			   <%=util.checkNull(servObj.getStrCovidVacMsg()) %>
			</td>
		</tr>  
	<% } %>
</table>

  <form name="frmFunc" id="frmFunc" method="post">
 	 <input type="hidden" name="fsrNumCopy" id="fsrNumCopy" value="<%=strFSRNumber%>">
 	 <input type="hidden" name="serNum" id="serNum" value="<%=strSerNum%> ">
  </form>
<form name="frmSummary" id="frmSummary" action="canonE307ServiceRequestSummary.jsp" method="post">
	<input type="hidden" id="updFlg" name="updFlg" val="<%=strUpdFlag%>">
	<input type="hidden" id="cnclFlg" name="cnclFlg" val="<%=strCnclFlg %>">
	<input type="hidden" id="userName" name="userName" value="<%=loginUser%>">
	<input type="hidden" id="fsr" name="fsr" value="<%=util.checkNull(servObj.getStrFSRNum())%>">
	<input type='hidden' id="taskNum" name="taskNum" value="<%=strTskNum %>">
	<input type="hidden" id="schdDisptEmlFlg" name="schdDisptEmlFlg" value="N">
	<input type="hidden" id="mblIntfcFlg" name="mblIntfcFlg" value="Y">
	<input type="hidden" name="svcMachMstrPk" id="svcMachMstrPk" value="<%=machObj.getSvcMachMstrPk() %>" />
	<input type="hidden" name="modeCode" id="modeCode" value="" />
	<input type="hidden" name="serNum" id="serNum" value="<%=strSerNum%> ">
	<input type="hidden" name="sReqTpCd" id="sReqTpCd" value="<%=util.checkNull(servObj.getStrSrTpCd())%> ">
	<input type="hidden" name="billableFlag" id="billableFlag" value="<%=util.checkNull(servObj.getStrBilbleFlg()) %>">
	<input type="hidden" name="scratchPad" id="scratchPad" value="">
	<input type="hidden" name="slsDt" id="slsDt" value="<%=util.checkNull(servObj.getStrSlsDt())%>">
	<input type="hidden" name="ccFlag" id="ccFlag" value="<%=util.checkNull(servObj.getStrCCFlag()) %>">
	<input type="hidden" name="contActFlg" id="contActFlg" value="<%=util.checkNull(servObj.getStrContActvFlg()) %>">
	<input type="hidden" name="tmZn" id="tmZn" value="<%=tmZone%>">
	<input type="hidden" name="postalCd" id="postalCd" value="<%=servObj.getStrCurPostCd() %>">
	<input type="hidden" name="cntryCd" id="cntryCd" value="<%=servObj.getStrCurCtryCd() %>">
	<input type="hidden" name="tskSucc" id="tskSucc" val="" />
	<input type="hidden" name="shwHdFlg" id="shwHdFlg" val="" />
	<input type="hidden" name="editRole" id="editRole" value="<%=editRole%>">
	<input type="hidden" name="tNumHis" id="tNumHis" value="<%=util.checkNull(tNumHis)%>">
	<input type="hidden" name="vendName" id="vendName" value="<%=util.checkNull(servObj.getStrVendName()) %>">
	<input type="hidden" name="vendContact" id="vendContact" value="<%=util.checkNull(servObj.getStrVendContact()) %>">
	<input type="hidden" name="vendPhone" id="vendPhone" value="<%=util.checkNull(servObj.getStrVendPhone() ) %>">
	<input type="hidden" name="vendEmailAddrs" id="vendEmailAddrs" value="<%=util.checkNull(servObj.getStrVendEmailAddr() ) %>">
	<input type="hidden" name="covidVacMsg" id="covidVacMsg" value="<%=util.checkNull(servObj.getStrCovidVacMsg()) %>">
	
	
	<!-- <div style="padding:0px 0px 30px 10px;"> -->
<div class="table-wrapper"> 
  <table class="service-detail" style="width:100%;height:290px;" cellspacing="5" cellpadding="0">
 	<tr>
 		  <td style="width:25%;height:100%;" valign=top>
 		 	 <div class="machine-information" style="width:100%;height:99%;border:1px solid #CCC;"> 
	            <table cellspacing="5" cellpadding="0" style="width:100%;border:0px;">
					<tr>
						<th colspan=3 class="hd">Service Request Information</th></tr>
						<tr class="trEmpty"><td colspan=3>&nbsp;</td></tr>
						<tr><td style="width:5%" nowrap>Service Request#</td>
							<td nowrap><input type="text" id="fsrNum" name="fsrNum" value="<%=util.checkNull(servObj.getStrFSRNum())%>" class="rdl" style="width:130px;"/> </td>
							<td width="1%"><img title="Copy SR" src="<%=ctxPath%>/common/images/copy.jpg"  height='21' onClick="fnCopySr('<%=servObj.getStrFSRNum()%>');"/></td>
							</tr>
							<tr><td>Status</td>
							<td><input type="text" name="srSts" id="srSts" value="<%=util.checkNull(servObj.getStrSrSts())%>" class="rdl" style="width:130px"/></td></tr>
						<tr><td style="width:5%" nowrap>Vendor Call#</td>
							<td><input type="text" id="ittCallNumber" name="ittCallNumber" value="<%=util.checkNull(servObj.getStrITTNum())%>" maxlength="25" style="width:130px;"></td>
						</tr>	
						<tr><td style="width:5%" nowrap>Creation Date</td>
							<td><input type="text" id="fsrCreationDate" name="fsrCreationDate" value="<%=util.checkNull(utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), servObj.getStrFSRCratDt()))%>" class="rdl" style="width:130px;"></td>
							</tr>
						<tr><td style="width:5%" nowrap>Service Request Type</td>
							<td><input type="text" id="srRqstTpe" name="srRqstTpe" value="<%=util.checkNull(servObj.getStrSrTpNm())%>" class="rdl" style="width:130px;"></td>
							</tr>	
						<!-- <tr style="line-height: 22px;"><td colspan=2>&nbsp;</td></tr> -->	
				</table> 
			 </div> 
		</td>
				
			<td style="width:25%;height:100%;" valign=top>
				<div class="machine-information" style="width:100%;height:99%;border:1px solid #CCC;"> 
					<table cellspacing="5" cellpadding="0" style="width:100%;border:0px;">
						<input type="hidden" name="tskSts" id="tskSts" value="<%=util.checkNull(tskObj.getStrTaskStatus())%>">
						<input type="hidden" name="tskNmbr" id="tskNmbr" value="<%=util.checkNull(tskObj.getStrTaskNum())%>" />
						<tr>
							<th colspan=2 class="hd">Task Information</th></tr>
							<tr class="trEmpty"><td colspan=2>&nbsp;</td></tr>
							<tr><td nowrap >Task#</td>
								<td nowrap><input type="text" id="taskNumber" name="taskNumber" value="<%=util.checkNull(tskObj.getStrTaskNum())%>&nbsp;-&nbsp;<%=util.checkNull(tskObj.getStrTaskStatus())%>" class="rdl"/></td>
							</tr>
								<input type="hidden" name="svcCallTpCd" id="svcCallTpCd" value="<%=tskObj.getStrTaskTypeCd() %>">
							<tr><td nowrap >Task Type</td>
								<td><input type="text" id="tskTpe" name="tskTpe" value="<%=tskObj.getStrTaskTypeCd() %>-<%=util.checkNull(tskObj.getStrTaskTypeNm())%>" class="rdl"></td>
							</tr>
							<tr><td nowrap >Assignee</td>
								<td><input type="text" id="assignee" name="assignee" value="<%=util.checkNull(tskObj.getStrAssignee()) %>" class="rdl"></td>
							</tr>
	<%-- 						<tr><td nowrap >Assignee Type</td>
								<td><input type="text" id="assignTpe" name="assignTpe" value="<%=util.checkNull(tskObj.getStrAssigneeTp())%>" class="rdl"></td>
							</tr> --%>
							<tr><td>Resource Manager</td>
								<td><input type="text" id="resMngr" name="resMngr" value="<%=util.checkNull(tskObj.getStrResManager())%>" class="rdl"></td>
							</tr>
							<tr><td>Duty Manager</td>
								<td><input type="text" id="dtyMngr" name="dtyMngr" value="<%=util.checkNull(tskObj.getStrDutyMngr())%>" class="rdl"></td>
							</tr>	
							<tr><td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>	
							<%
							if(taskList!=null && taskList.size()>1){
							%>
								<tr><td style="color: red;font-size: 15px;font-weight: bold;text-align:center" colspan=2 nowrap>*Additonal tasks exist for this service request</td>
								</tr>							
							<%
							}
							%>

							
							<!-- <tr style="line-height: 45px;"><td colspan=2>&nbsp;</td></tr>	 -->		
					</table>
				</div>
			</td>
			<td style="width:25%;height:100%;" valign='top'>
				<div class="machine-information" style="width:100%;height:99%;border:1px solid #CCC;"> 
					<table style="width:100%;border:0px;" cellspacing="5" cellpadding="0">
						<tr><th class="hd" colspan="3">Machine Information</th></tr>
						<tr class="trEmpty"><td colspan=2>&nbsp;</td></tr>
						<tr><td>Serial# </td><td><input type="text" name="serialNumber" id="serialNumber" value="<%=util.checkNull(machObj.getSerNum())%>" class="rdl" /></td>
						</tr>
						<tr><td>Model </td><td><input type="text" name="model" id="model" value="<%=util.checkNull(machObj.getModel())%>" class="rdl"/></td>
						</tr>
						<%-- <tr><td nowrap>Solution Name </td><td ><input type="text" name="solName" id="solName" value="<%=util.checkNull(machObj.getSolutionName())%>" class="rdl" /></td>
						</tr> --%>
						<tr><td>Contact Name </td><td><input type="text" name="contactName" id="contactName" value="<%=util.checkNull(machObj.getContact())%>" /></td>
						
						</tr>
						<tr><td nowrap>Phone#</td>
						   <td  nowrap>
	<!-- 					   		<input type="text" value="" id="custPhNum1" name="custPhNum1" class="numeric" size=1 maxlength="3" onkeyup="autoTab(this, callerPhnNum2)" style="width:20px">
								<input type="text" value="" id="custPhNum2" name="custPhNum2" class="numeric" size=1 maxlength="3" onkeyup="autoTab(this, callerPhnNum3)" style="width:20px">
								<input type="text" value="" id="custPhNum3" name="custPhNum3" class="numeric" size=1 maxlength="4" style="width:25px"> - 
								<input type="text" value="" id="callerExtn" name="callerExtn" class="numeric" size=1 maxlength="4" style="width:20px">  -->
								 <input type="text"  size="12" maxlength="20" name="custPhNum" id="custPhNum" value="<%=util.checkNull(machObj.getCustTelNumber())%>" style="width:90px"/> - <input type="text"  size="4" name="custPhNumExt" id="custPhNumExt"  maxlength="10" value="<%=util.checkNull(machObj.getCustTelExtn())%>" style="width:45px"/>
						   </td>
						</tr>
						<tr><td nowrap>Mobile#</td>
						   <td  nowrap>
								 <input type="text"  size="12" maxlength="20" name="mobileNum" id="mobileNum" value="<%=util.checkNull(machObj.getMobileNum())%>" style="width:90px"/>
						   </td>
						</tr>							
						<tr><td nowrap>Service Hours</td><td><input type="text" name="custHrs" id="custHrs" value="<%=util.checkNull(srvcHrs)%>" class="rdl" /></td>
						</tr>
						<tr><td nowrap>Line Of Business</td><td><input type="text" name="lBizTp" id="lBizTp" value="<%=util.checkNull(servObj.getStrLineBizTpCd())%>" class="rdl" style="background-color: #33FF33;font-weight: bold;font-size: 15px !important;"/></td>
						</tr> 
						 <tr><td nowrap>Branch</td><td><input type="text" name="branch" id="branch" value="<%=util.checkNull(tskObj.getStrBranch())%>" size="12" maxlength="12" class="rdl" /></td>
						</tr> 
						<!-- <tr style="line-height: 23px;"><td colspan=2>&nbsp;</td></tr> -->
					</table>
				</div>				
			</td>
		<td style="width:25%;height:100%;" valign='top'>
			 <div class="machine-information" style="width:100%;height:99%;border:1px solid #CCC;"> 
				<table cellspacing="5" cellpadding="0" style="width:100%;border:0px;"><tr>
						<th class="hd" colspan="5" nowrap>Customer Handling </th></tr>
						<tr class="trEmpty"><td colspan=5>&nbsp;</td></tr>
						<tr><td nowrap>SLA Response Time </td><td><input type="text" name="slaRespTime" id="slaRespTime" value="<%=util.checkNull(slaRespTime) %>" class="rdl" style="width:130px;"/>
							</td></tr>
						<tr><td rowspan=2 nowrap>Arrival Window </td><td><input type="text" name="svcFrom" id="svcFrom" value="<%=util.checkNull(utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), tskObj.getStrEarlyStart())) %>" class="rdl" style="width:130px;"/></td>
							</tr>
						<tr><td><input type="text" name="svcTo" id="svcTo" value="<%=util.checkNull(utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), tskObj.getStrLateStart())) %>" class="rdl" style="width:130px;"/></td>
							</tr>
		<%-- 				<tr><td nowrap>ETA Time(Local) </td>
							<td nowrap >
			 		  		<input type="text"  class="datePicker" value="<%=util.checkNull(servObj.getStrETA()) %>" class="rdl" style="width:110px;"/></td>
			 			</tr> --%>
					<tr><td nowrap id="estArvl"><%=lblVal%> Arrival </td><td><input type="text" name="estmtArrival" id="estmtArrival" value="<%=util.checkNull(utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), tskObj.getStrEstFrom())) %>" class="rdl" style="width:130px;"/>
							</td></tr>			 			
					 <tr><td nowrap>Future Service Date</td>
						<td>
			 		  		<input type="text" id="ftrSrv" name="ftrSrv" value="<%=util.checkNull(utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), servObj.getStrFutureServDate())) %>" style="width:130px" class="rdl"/>
			 		  		<input type="hidden" id="futSvcTm" name="futSvcTm" value="<%=util.checkNull(servObj.getStrFutureServTime()) %>" />
			 		  		<input type="hidden" id="ftrSrvDt" name="ftrSrvDt" value="<%=util.checkNull(servObj.getStrFutureServDate()) %>" />
						</td>
					</tr>
					<tr>
						<td><b>Time Zone</b></td>
						<td>
				 		  <input type="text" id="tmZone" name="tmZone" value="<%=tmZone%>" style='width:130px;background-color: #33FF33;font-weight: bold;font-color:#000000;font-size: 15px !important;' disabled/> 
						</td>
					</tr>
				</table>
			 </div> 
		</td>	
	  </tr>

  </table>
</div>
	  
<!-- 	 <div class="hLineLg"></div> -->
	<table width="95%" style="align:left">
		<tr><td><div id="srShowDiv" style="padding: 5px 20px 0">
			<a href="javascript:void fnShowSrDet()" class="btn" style="white-space: nowrap;"><%=shwHidHdr %></a>
		</div></td>
		    <td align="right"><div id="srShowDiv" style="padding: 5px 20px 0"><a href="javascript:void fnRefresh()" class="btn disp_mode" style="white-space: nowrap;">Refresh</a></div></td></tr></table>
	
	<div id="srDetDiv" style="<%=shwHdStl %>">	
	<div class="hLineLg"></div>
	
	<div class="table-wrapper">
		<table class="service-detail" style="width:100%;height:340px;" cellspacing="5" cellpadding="0">
		<tr>
			<td style="width:55%;height:100%;" valign=top>
			 <div class="machine-information" style="width:100%;height:97%;border:1px solid #CCC;">
				<table cellspacing="5" cellpadding="0" style="width:100%;border:0px;">
					<tr>
						<th colspan=5 class="hd">Service Request Information</th></tr>
						<tr class="trEmpty"><td colspan=5>&nbsp;</td></tr>
						<tr><td >Bill Code</td>
							<td><input type="text" id="billCode" name="billCode" value="<%=util.checkNull(servObj.getStrSvcBillTpCd()) %>-<%=util.checkNull(servObj.getStrSvcBillTpNm()) %>" class="rdl"></td>
							<input type="hidden" id="bllTpCd" name="bllTpCd" value="<%=servObj.getStrSvcBillTpCd() %>">
<%-- 							<td >Respond By Date</td>
							<td><input type="text" id="respByDate" name="respByDate" value="<%=util.checkNull(servObj.getStrRspndByDt())%>" class="rdl" style="width:135px;"></td> --%>
							<td>Last Update Date</td>
							<td><input type="text" id="lastUpdate" name="lastUpdate" value="<%=util.checkNull(utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), servObj.getStrLastUptDt()))%>" class="rdl" style="width:135px;"></td>								
						</tr>		
						<tr>
							<td>Problem</td>
							<td><input type="text" id="problem" name="problem" value="<%=util.checkNull(servObj.getStrPblmTpNm()) %>" class="rdl"></td>
							<input type="hidden" id="sPbCode" name="sPbCode" value="<%=util.checkNull(servObj.getStrPblmTpCd())  %>">
							<td>Last Updated By</td>
							<td><input type="text" id="lastUpdatedBy" name="lastUpdatedBy" value="<%=util.checkNull(servObj.getStrLastUpdtBy())%>" class="rdl" style="width:135px;"></td>
						</tr>
						
						<tr>
							<td>Creation Channel</td>
							<input type="hidden" name="sCrChannel" id="sCrChannel" value="<%=servObj.getStrCallSrcTpCd()%>">
							<td><input type="text" name="creationChannel" id="creationChannel" value="<%=util.checkNull(servObj.getStrCallSrcTpNm())%>" class="rdl"></td>
							<td>Creation Date</td>
							<td ><input type="text" id="creationDt" name="creationDt" value="<%=util.checkNull(utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), servObj.getStrFSRCratDt()))%>" class="rdl" style="width:135px;"></td>	
						</tr>
						<tr>
							<td>Time Zone</td>
							<td><input type="text" name="timeZone" id="timeZone" value="<%=tmZone%>" class="rdl"></td>
							<td>Created By</td>
							<td ><input type="text" id="createdBy" name="createdBy" value="<%=util.checkNull(servObj.getStrCreatedBy())%>" class="rdl" style="width:135px;"></td>
						</tr>
						<tr>
							<td>Customer P.O.#</td>
							<td><input type="text" name="custPoNumber" id="custPoNumber" maxlength="35" value="<%=util.checkNull(servObj.getStrCustPONum())%>" ></td>
							<input type="hidden" name="custPoDt" id="custPoDt" value="<%=util.checkNull(servObj.getStrCustPoDt()) %>">
							<td>Machine Manager</td>
							<input type="hidden" id="machMgrCd" name="machMgrCd" value="<%=util.checkNull(servObj.getStrMachMngrId()) %>">
							<td><input type="text" id="machineManager" name="machineManager" value="<%=util.checkNull(servObj.getStrMachMngrNm())%>" class="rdl" style="width:135px;">
						 	</td>
						 	<input type="hidden" id="machMgrCode" name="machMgrCode" value="<%=util.checkNull(servObj.getStrMachMngrId()) %>" />								
						</tr>
							<%
								String poVerifyFlag= "";
							if("Y".equals(servObj.getStrPoVerFlag())){
								poVerifyFlag ="checked";
							}
							%>
						<tr><td>Verified Uploaded PO</td>
						<td><input type="checkbox" name="verifiedPO" id="verifiedPO" <%=poVerifyFlag%> value="Y" class="" style="border:0px;"></td>
						<td>Payment Terms</td> 
						<td><input type="text" name="paymentTerm" id="paymentTerm" value="<%=util.checkNull(servObj.getStrPmtTerm())%>" class="rdl" style="width:135px;"></td>							
					</tr>
					<tr>
						<td >PO Upload Required&nbsp;&nbsp;<%-- <input type="checkbox" id="poUploadCheck" name="poUploadCheck" value="" disabled="disabled" style="border:0px;"> --%></td>
						<td nowrap>
						<!-- <input  type="file"  name="poUpld" id="poUpld" value="" onchange="uploadPOAttachment()"/> -->
						<input type="text" name="fileNm" id="fileNm" class="rdl" value="<%=util.checkNull(servObj.getStrPOFileName()) %>">
						<input type="hidden" name="attDataPk" id="attDataPk" value="<%=util.checkNull(servObj.getStrPOAttPk()) %>">
						<%
							if("Y".equals(servObj.getStrFsrUpdFlag())){
							%>
							<img src="<%=imgSrc1%>"  height='18' onClick="fnUploadPOAttach();"/>
							<%
							}
						%>
							</td>
						</tr>	
<!-- 						 <tr style="line-height: 54px;"><td colspan=4>&nbsp;</td></tr>  -->
				</table>	
				</div>											
			</td>
			<td style="width:25%;height:100%;" valign=top align=left>
			<div class="machine-information" style="width:100%;height:97%;border:1px solid #CCC;">
	            <table cellspacing="5" cellpadding="0" style="width:100%;border:0px;">
					<tr><th class="hd" colspan="2">Machine Information</th></tr>
					<tr class="trEmpty"><td colspan=2>&nbsp;</td></tr>	
					<tr> <td>Tag# </td><td ><input type="text" name="tagNum" id="tagNum" value="<%=util.checkNull(machObj.getCustMachCtrlNum())%>" class="rdl"/></td></tr>
					<tr> <td>Customer Case# </td><td ><input type="text" name="custCaseNum" id="custCaseNum" value="<%=util.checkNull(servObj.getStrCustCaseNo()) %>" class="rdl" /></td></tr>
					<tr> <td>Email Address </td><td ><input type="text" name="emailAddr" id="emailAddr" value="<%=util.checkNull(machObj.getEmailAddress())%>" /></td></tr>
					<tr> <td>Caller </td><td><input type="text" name="caller" id="caller" value="<%=util.checkNull(machObj.getCaller())%>" /></td></tr>
					<tr> <td>Phone# </td>
					<td nowrap>
<!--  						<input type="text" value="" id="callerPhnNum1" name="callerPhnNum1" class="numeric" size=1 maxlength="3" onkeyup="autoTab(this, callerPhnNum2)" style="width:20px">
						<input type="text" value="" id="callerPhnNum2" name="callerPhnNum2" class="numeric" size=1 maxlength="3" onkeyup="autoTab(this, callerPhnNum3)" style="width:20px">
						<input type="text" value="" id="callerPhnNum3" name="callerPhnNum3" class="numeric" size=1 maxlength="4" style="width:25px"> - 
						<input type="text" value="" id="callerExtn" name="callerExtn" class="numeric" size=1 maxlength="4" style="width:20px">  -->
				 	  <input type="text" name="callerPhn" id="callerPhn" value="<%=util.checkNull(machObj.getCallrTelNumber())%>" size="12" maxlength="20" style="width:90px"/>&nbsp;-&nbsp;<input type="text" value="<%=util.checkNull(machObj.getCallrTelExtn()) %>" id="callerExtn" name="callerExtn" class="numeric" size=4 maxlength="10" style="width:45px"> </td></td><td  style="width:1px" nowrap>&nbsp;
				 	  
					</tr>
					<tr><td nowrap>Solution Name </td><td ><input type="text" name="solName" id="solName" value="<%=util.checkNull(machObj.getSolutionName())%>" class="rdl" /></td>
					<tr> <td>Special Message </td>
					     <td>
					        <%--  <input type="text" name="splMsg" id="splMsg" value="<%=util.checkNull(machObj.getSerSpecialMsg())%>" /> --%>
					        <textarea name="noteDetails" id="noteDetails" cols="18" rows="2" style="margin:5px;" class="rdl"><%=util.checkNull(machObj.getSerSpecialMsg())%></textarea>
					         <input type="hidden" name="splMsgNtCd" id="splMsgNtCd" value="<%=util.checkNull(machObj.getSerSpecialMsgTyp())%>"/> 	</td>
					</tr>
					<%
						String machSts = servObj.getStrMachStatus();
						String strCheckUp ="";
						String strCheckDown ="";
						System.out.println("machSts : " + machSts);
						if("N".equals(machSts)){
							strCheckUp="checked";
						}else{
							strCheckDown="checked";
						}
					%>
					<tr><td>Machine Status</td>
						<td>
						<table style="align:left; width:100%;border:0px;"><tr><td>&nbsp;&nbsp;Up </td><td><input type="radio"  name="mchStatus"  value="up" style="margin:5px;border:0px;" <%=strCheckUp%>/></td>
						             <td>Down </td><td><input type="radio" name="mchStatus"  value="down" <%=strCheckDown%> style="border:0px;"/></td><td style="width:30%">&nbsp;</td></tr></table>
						</td>
						</tr>	
				</table>
				</div>
			</td>	
			<td style="width:20%;height:100%;" valign=top>
			 <div class="machine-information" style="width:100%;height:97%;border:1px solid #CCC;">
			 <%
			 System.out.println("attribute1: "+ servObj.getStrAttribute1() + "Attribute2: " + servObj.getStrAttribute2()+" SR Update flg: "+servObj.getStrFsrUpdFlag());
			 if("Y".equals(util.checkNull(servObj.getStrBilbleFlg())) && "".equals(util.checkNull(servObj.getStrAttribute1()))){ // || "FAILED".equals(util.checkNull(servObj.getStrAttribute1()))){
			 %>	 
				  <table cellspacing="5" cellpadding="0"  style="width:100%;border:0px;">
						<tr><th class="hd" colspan="2">Credit Card Authorization</th></tr>
						<tr class="trEmpty"><td colspan=2>&nbsp;</td></tr>
						<tr><td>Profile Id</td><td><input type="text" size="23" name="profileId" id="profileId" value="<%=util.checkNull(servObj.getStrProfileId()) %>" class="rdl" /></td>
						</tr>
						<tr><td>Holder Name</td><td><input type="text" size="23" name="holderName" id="holderName" value="<%=util.checkNull(servObj.getStrHolderName()) %>" /></td>
						</tr>
						<tr><td>Auth. Amt.</td><td><input type="text" size="23" name="authAmnt" id="authAmnt" value="<%=util.checkNull(servObj.getStrAuthAmt()) %>" /></td>
						</tr>
						<input type="hidden" name="cardType" id="cardType" value="<%=util.checkNull(servObj.getStrCardType()) %>">
						<input type="hidden" name="cardExpr" id="cardExpr" value="<%=util.checkNull(servObj.getStrExprDt()) %>">
						<input type="hidden" name="txRefNum" id="txRefNum" value="<%=util.checkNull(servObj.getStrAttribute1()) %>">
						<input type="hidden" name="ccNum" id="ccNum" value="<%=util.checkNull(servObj.getStrAttribute2()) %>">
						<%					
							if("Y".equalsIgnoreCase(servObj.getStrFsrUpdFlag())){
						%>
					<!-- 	<tr align="right">
							<td align="center" colspan=2>
								<table style="align:center;border:0px;" width="70%" >
									<tr><td><a href="#"  class="btn enter_cc" onclick="enterCC();">Enter Credit Card</a></td></tr>
							</table>
							</td>
						</tr> -->
						<tr><td>&nbsp;</td><td><a id="crBtn" href="#"  class="btn enter_cc" style="margin: 0 25px !important;" onclick="enterCC();">Enter Credit Card</a></td></tr>
						<%} %>
				  </table> 
				  <%
				  }else{
				  %>
					  <table cellspacing="5" cellpadding="0"  style="width:100%;border:0px;">
							<tr><th class="hd" colspan="2">Credit Card Authorization</th></tr>
							<tr class="trEmpty"><td colspan=2>&nbsp;</td></tr>
							<tr><td>Profile Id</td><td><input type="text" size="23" name="profileId" id="profileId" value="<%=util.checkNull(servObj.getStrProfileId()) %>" class="rdl" /></td>
							</tr>
							<tr><td>Holder Name</td><td><input type="text" size="23" name="holderName" id="holderName" value="<%=util.checkNull(servObj.getStrHolderName()) %>" class="rdl" /></td>
							</tr>
							<tr><td>Auth. Amt.</td><td><input type="text" size="23" name="authAmnt" id="authAmnt" value="<%=util.checkNull(servObj.getStrAuthAmt()) %>" class="rdl" /></td>
							</tr>
							<input type="hidden" name="cardType" id="cardType" value="<%=util.checkNull(servObj.getStrCardType()) %>">
							<input type="hidden" name="cardExpr" id="cardExpr" value="<%=util.checkNull(servObj.getStrExprDt()) %>">
							<input type="hidden" name="txRefNum" id="txRefNum" value="<%=util.checkNull(servObj.getStrAttribute1()) %>">
							<input type="hidden" name="ccNum" id="ccNum" value=""<%=util.checkNull(servObj.getStrAttribute2()) %>"">
					  </table> 
				 <%					  
				  }
				  %>
			</div>
			</td>
		</tr>
		</table>
		</div>
		<div class="hLineLg"></div>
		<table class="service-detail" style="width:100%;height:225px;" cellspacing="5" cellpadding="0">
				<tr>
					<td valign="top" style="width:55%;height:100%;">
						<div class="machine-information" style="width:100%;height:92%;border:1px solid #CCC;">
						  <table cellspacing="5" cellpadding="0" style="width:100%;border:0px;">
								<tr><th class="hd" colspan="6">Contract  Information</th></tr>
								<tr class="trEmpty"><td colspan=6>&nbsp;</td></tr>
								<tr><td>Contract#</td><td nowrap><input type="text" name="ctrNum" id="ctrNum" value="<%=util.checkNull(contObj.getContractNumber())%>" class="rdl" style="width: 145px"/></td>
								<td>Contract Type </td><td> <input type="text" name="ctrTpe" id="ctrTpe" value="<%=util.checkNull(contObj.getContractType())%>" class="rdl" style="width: 145px"/></td>
								<td>Contract Status</td><td nowrap><input type="text" name="ctrSts" id="ctrSts" value="<%=util.checkNull(contObj.getHeaderStatus()) %>" class="rdl" style="width: 145px"/> </td>
								</tr>
								<tr>
									<td>Contract Start</td><td nowrap><input type="text"  name="ctrStrt" id="ctrStrt" value="<%=util.checkNull( contObj.getHeaderStartDate())%>" class="rdl" style="width: 145px"/> </td>
									<td>Line Start </td><td nowrap><input type="text" name="ctrLnStrt" id="ctrLnStrt" value="<%=util.checkNull(contObj.getLineStartDate())%>" class="rdl" style="width: 145px"/> </td>
									<td>Line Status</td><td nowrap><input type="text" name="ctrLnSts" id="ctrLnSts" value="<%=util.checkNull(contObj.getLineStatus())%>" class="rdl" style="width: 145px"/> </td>
								</tr>
								<tr>
									<td>Contract End</td><td nowrap><input type="text" name="ctrEnd" id="ctrEnd" value="<%=util.checkNull( contObj.getHeaderEndDate())%>" class="rdl" style="width: 145px"/> </td>
									<td>Line End </td><td nowrap><input type="text" name="ctrLnEnd" id="ctrLnEnd" value="<%=util.checkNull(contObj.getLineEndDate())%>" class="rdl" style="width: 145px"/> </td>
								</tr>
								<!-- <tr style="line-height: 41px;"><td colspan=2>&nbsp;</td></tr>	 -->
							</table>
						</div>
					</td>
					<td style="width:45%;height:100%;" valign=top>
					<div class="machine-information" style="width:100%;height:92%;border:1px solid #CCC;">
				       <table cellspacing="5" cellpadding="0" style="width:100%;border:0px;">
				        <tr><th colspan="2" class="hd">Customer Address</th></tr>
				        <tr class="trEmpty"><td colspan="2">&nbsp;</td></tr>
				        <tr><td class="sub_hd" style="width:50%;">Installed At</td><td  class="sub_hd" style="width:50%;">Bill To</td></tr>
				        <tr> <td style="width:50%;"><span id="csCustNum"><%=util.checkNull(servObj.getStrCurCustCd())%></span></td>
				         	<td style="width:50%;"><span id="bsCustNum"><%=util.checkNull(servObj.getStrBllCustCd())%></span></td>
					    </tr>			              
					    <tr>
					       <td style="width:50%;"><span id="csCustName"><%=util.checkNull(servObj.getStrCurCustNm())%></span></td>
					       <td style="width:50%;"><span id="bsCustName"><%=util.checkNull(servObj.getStrBllCustNm())%></span></td>
					    </tr>
						<tr> 
						   <td style="width:50%;"><span id="csAddr"><%=util.checkNull(servObj.getStrCurAddrLine())%></span></td>
						   <td style="width:50%;"><span id="bsAddr"><%=util.checkNull(servObj.getStrBillAddrLine())%></span></td>
						</tr>
					    <tr>
					    	<td><span id="csCity"><%=util.checkNull(servObj.getStrCurCity())%></span></td>
					    	<td><span id="bsCity"><%=util.checkNull(servObj.getStrBillCity())%></span></td>
					    </tr>
		                <tr> <td><span id="csState"><%=util.checkNull(servObj.getStrCurStCd())%></span>
			                 	<span id="csPostalCode" class="marg-s"><%=util.checkNull(servObj.getStrCurPostCd())%></span></td>
			                 <td><span id="bsState"><%=util.checkNull(servObj.getStrBillStCd())%></span>
					            <span id="bsPostalCode" class="marg-s"><%=util.checkNull(servObj.getStrBillPostCd())%></span>
					         </td>
			            </tr>
				     </table>  
				     </div>
  				</td>
   			</tr>
 		</table>
<!-- 	</div>  -->
	<div class="hLineLg"></div>
<!-- <div id="tskShowDiv" style="padding: 5px 10px 0"><a href="javascript:void fnShowTskDet()" class="btn" style="white-space: nowrap;">Show Task Details</a></div>  -->
<div id="taskDiv">		
		<div class="table-wrapper-task">
	<!-- 	<div id="taskDv" style="width:99.1%;align:center;position: relative; bottom: 2px;" class="hd" >Task Information</div>   -->
			<div class="table-inner" id="tasksTable">
				<table id="taskTable" class="model-table" cellspacing="1">
					<tbody>	
					<tr><th colspan=11>Task Information</th></tr>
						<tr>
							<th width="9%">Task#</th>
							<th width="5%">Task Type</th>
							<th width="5%">Task Status</th>
							<th width="10%">Creation Date</th>
							<th width="10%" colspan=2>Arrival Window</th>
							<!-- <th width="12%" nowrap>Late Start</th> -->
							<th width="12%" nowrap>Arrival Time</th>
							<th width="12%" >Completion Time</th>
							<th width="10%" >Resource Manager</th>
							<th width="10%" >Assignee</th>	
							<th width="10%" >Branch</th>
						</tr>
					<%
					String strCrctnFlg = "";
					if(obj[3] !=null) {
						  // List<CanonE307TaskDetailsRec> taskList =new ArrayList<CanonE307TaskDetailsRec>();
						   taskList =(ArrayList<CanonE307TaskDetailsRec>) obj[3];
						   if(taskList!=null && taskList.size()>0){
							   int taskCnt=0;
							   int clspCnt =1;
							   for(CanonE307TaskDetailsRec taskObj: taskList){
								  if("Y".equals(taskObj.getStrAttribute4())){
									  strCrctnFlg="Y";
								  }

									if(taskCnt!=0){
										%>
									<table id="taskTable" class="model-table" cellspacing="1">
										<tbody>		
										<%
									}
								   %>
										   	<tr id='taskRow<%=taskCnt%>' data-tasknum="<%=util.checkNull(taskObj.getStrTaskNum())%>" data-taskidx="<%=taskCnt%>">
											  	<td id="task<%=taskCnt%>" width="9%">
											  	<a class="btn_red_sm" data-toggle="collapse" href="#collapse<%=clspCnt%>">+</a> <%=util.checkNull(taskObj.getStrTaskNum())%> <br /></td>
												<td id="taskType<%=taskCnt%>" width="5%" ><%=util.checkNull(taskObj.getStrTaskTypeCd())%>-<%=util.checkNull(taskObj.getStrTaskTypeNm())%></td>
												<td id="taskStat<%=taskCnt%>" width="5%" ><%=util.checkNull(taskObj.getStrTaskStatus()) %></td>
												<td id="creDate<%=taskCnt%>" width="10%" ><%=util.checkNull(utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), taskObj.getStrCreatDt())) %></td>
												<td id="earlyStart<%=taskCnt%>" width="10%" ><%=util.checkNull(utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), taskObj.getStrEarlyStart())) %></td>
												<td id="lateStart<%=taskCnt%>" width="12%" ><%=util.checkNull(utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), taskObj.getStrLateStart())) %></td>
												<td id="schStart<%=taskCnt%>" width="12%" ><%=util.checkNull(utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), taskObj.getStrSchdStart()))%></td>
												<td id="schlEnd<%=taskCnt%>" width="12%" ><%=util.checkNull(utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), taskObj.getStrSchdEnd()))%></td>
												<td id="resManager<%=taskCnt%>" width="10%" nowrap><%=util.checkNull(taskObj.getStrResManager())%></td>
												<td id="assignee<%=taskCnt%>" width="10%" nowrap><%=util.checkNull(taskObj.getStrAssignee())%></td>
												<td id="branch<%=taskCnt%>" width="10%" nowrap><%=util.checkNull(taskObj.getStrBranch())%></td>
												<input type="hidden" id="resMgrCd<%=taskCnt%>" name="resMgrCd<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrResManagerCd()) %>">
												<input type="hidden" id="schEnd<%=taskCnt%>" name="schEnd<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrSchdEnd())%>">
												<input type="hidden" id="actStart<%=taskCnt%>" name="actStart<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrActaulStart())%>">
												<input type="hidden" id="actEnd<%=taskCnt%>" name="actEnd<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrActualEnd())%>">
												<input type="hidden" id="assignType<%=taskCnt%>" name="assignType<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrAssigneeTp())%>">
												<input type="hidden" id="lUpdatedBy<%=taskCnt%>" name="lUpdatedBy<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrLastUptBy())%>">
												<input type="hidden" id="tskStsCd<%=taskCnt%>" name="tskStsCd<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrTaskStsCd())%>">
												<input type="hidden" id="assTpCd<%=taskCnt%>" name="assTpCd<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrAssignTpcd())%>">
											<%-- 	<input type="hidden" id="tskVstNum<%=taskCnt%>" name="tskVstNum<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrVisitNum())%>"> --%>
												<input type="hidden" id="tskNum<%=taskCnt%>" name="tskNum<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrTaskNum())%>">
												<input type="hidden" id="lastUpdateDt<%=taskCnt%>" name="lastUpdateDt<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrAttribute3())%>">
												<input type="hidden" name="mtrSubmitFlg<%=taskCnt%>" id="mtrSubmitFlg<%=taskCnt%>" value="">
											</tr>	
										   </tbody>
										</table>
										<div id="collapse<%=clspCnt%>" class="collapse" >
										<table width="98%" class="model-table" cellspacing="1">
											<%
											//String strDisabled ="";
											String strCls = "";
											String strStyle="style='width:95%;margin-left: 5px;'";
											if("N".equals(taskObj.getStrTskUpdFlg())){
												//strDisabled ="disabled='disabled'";
												strCls = "class='rdl'";
												strStyle = "style='background-color:#cccccc;width:182px;margin-left: 5px;'";
											}
											%>
											<tr>
											<td style="width:35%">
												<table width="97%" cellspacing="1" class="meter-read-date">
													<tr><td width=5%>Task Status </td>
														<td>
														<%
														ArrayList stsTrnLst = (ArrayList)detailObj.getTaskStsByTrnstn(util.checkNull(taskObj.getStrTaskStsCd()), loginUser);
														%>
															<select id="taskStatDet<%=taskCnt%>" name="taskStatDet<%=taskCnt%>" <%=strCls%> <%=strStyle%> onchange="fnCheckTskSts('<%=taskCnt%>')" >
															 <option value="<%=taskObj.getStrTaskStsCd() %>" selected><%=taskObj.getStrTaskStatus() %></option>
															<%
															
															if(stsTrnLst!=null && stsTrnLst.size()>0){

																//for(CanonE307TskTpeRec trnsObj: stsTrnLst){
																	for(int i=0;i<stsTrnLst.size();i++){
																		CanonE307TskTpeRec transObj = (CanonE307TskTpeRec)stsTrnLst.get(i);
															%>
																<option value="<%=transObj.getTskTpCd() %>"><%=transObj.getTskTpNm() %></option>
															<%
																}
																%>
															<!-- 	<option value="" selected></option> -->
																<%		
															}
															 %>
															 </select> 
														</td>
													</tr>
													<tr><td width=5%>Assignee</td><td>
													<input type="text" name="assigneeDet<%=taskCnt%>" id="assigneeDet<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrAssignee()) %>" size='20' class="rdl" style="background-color: #cccccc;"></td>
													<td width="20%" border=0 align="left">
													<%
														if("Y".equals(taskObj.getStrTskUpdFlg())){
													%>
														<img src="<%=imgSrc1%>"  height='18' onClick="fnGetAssignee('<%=taskCnt%>');"/>
														<%
														}
														%>
													</td>
													</tr>
													<input type="hidden" name="assDetCode<%=taskCnt%>" id="assDetCode<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrAssgnCd()) %>">
													<tr><td  nowrap>Assignee Type</td><td width="5%"><select id="assignTypeDet<%=taskCnt%>" name="assignTypeDet<%=taskCnt%>" <%=strCls%> <%=strStyle%>>
														<option value=""></option> </select> </td>
														</tr>
													<tr> <td nowrap>Skills</td><td><input type="text" name="skills<%=taskCnt%>" id="skills<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrMdlSkills())%>" size='20' class="rdl" style='background-color:#cccccc'></td>
														</tr>														
												</table>
											</td>
						     				<td style="width:35%">
												<table width="97%" cellspacing="1" class="meter-read-date">
													<tr><td nowrap width="15%"><b>Time Zone</b></td><td width="40%" style="margin-bottom: 8px;margin-left: 8px;"><input type="text" name="tmZn<%=taskCnt%>" id="tmZn<%=taskCnt%>" value="<%=util.checkNull(tmZone) %>" size='25' class="rdl"  style="background-color: #33FF33;font-weight: bold;"></td></tr>
													<tr><td nowrap width="15%">Last Updated By</td><td width="40%" style="margin-bottom: 8px;margin-left: 8px;"><input type="text" name="lUpdatedByDet<%=taskCnt%>" id="lUpdatedByDet<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrLastUptBy()) %>" size='25' class="rdl" style="background-color: #cccccc;"></td></tr>
													<%-- <tr><td width="20%" nowrap>Scheduled End</td><td width="40%"><input type="text" name="scheduleEndDet<%=taskCnt%>" id="scheduleEndDet<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrSchdEnd()) %>" size='25' class="rdl" style="background-color: #cccccc;"></td></tr> --%>
													<tr><td width="20%" nowrap>Actual Arrival</td><td width="40%" style="margin-bottom: 8px;margin-left: 8px;"><input type="text" name="actualStartDet<%=taskCnt%>" id="actualStartDet<%=taskCnt%>" value="<%=util.checkNull(utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), taskObj.getStrActaulStart())) %>" size='25' class="rdl" style="background-color: #cccccc;"></td></tr>
													<tr><td width="20%" nowrap>Recall Type</td><td width="40%" style="margin-bottom: 8px;margin-left: 8px;"><input type="text" name="recallType<%=taskCnt%>" id="recallType<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrRecallType()) %>" size='25' class="rdl" style="background-color: #cccccc;"></td></tr>
												</table>
											</td>
						     				<td style="width:35%">
												<table width="97%" cellspacing="1" class="meter-read-date">
													<tr><td nowrap width="15%">Cancel Reason</td><td width="30%" style="margin-bottom: 8px;margin-left: 8px;"><input type="text" name="cnclRsn<%=taskCnt%>" id="cnclRsn<%=taskCnt%>" value="<%=util.checkNull(taskObj.getStrCnclrsn()) %>" class="rdl"  style="background-color: #cccccc;width:95%;"></td></tr>
													<tr><td nowrap width="15%">Cancel Note</td><td width="30%" style="margin-bottom: 8px;margin-left: 8px;"><textarea name="cnclNtTxt" id="cnclNtTxt" cols="10" rows="2" class="rdl" style="border: solid 1px #cccccc;width:95%;background-color: #cccccc;margin: 0 5px !important;font-size: 13px;" readonly><%=util.checkNull(taskObj.getStrCnclNote()) %></textarea></td></tr>
													<tr><td width="15%">&nbsp;</td>
												<%-- 	<%
														if("Y".equals(taskObj.getStrAttribute4() )){
													%>
														<td width="15%"><a href="javascript:void fnCrctnMeterReads('<%=util.checkNull(taskObj.getStrTaskNum())%>','<%=taskCnt%>')" class="btn disp_mode srm_mode" style="white-space: nowrap;">Correction Meter Reads</a></td>
													<%	}else{%>
														<td width="15%">&nbsp;</td>
													<% 	} %> --%>

													<td><table><tr>
	    											<%
														if(!"TBO".equals(taskObj.getStrTaskStatus())){
													%>
															<td align="right" width="5%"><a href="javascript:void fnDebriefTask('<%=util.checkNull(taskObj.getStrTaskNum())%>','<%=taskCnt%>')"  class="btn" style="white-space: nowrap;">Debrief Task</a></td>
													<%														
														}
													if("Y".equals(taskObj.getStrTskUpdFlg())){
													%>
														<td align="left" width="5%"><a href="javascript:void fnCancelTsks()" class="btn disp_mode srm_mode" style="white-space: nowrap;">Cancel Task</a></td>
													<%
													}
													%>
														<td align="left" width="5%"><a href="javascript:void fnCharges('<%=util.checkNull(taskObj.getStrTaskNum())%>')" class="btn" style="white-space: nowrap;">Task Charges</a></td>													
													</tr>
													</table>
													</td>	
												</table>
											</td>											
											</tr>
											</table>
									</div>
								<%
								taskCnt++;
								clspCnt++;
							   }
							   %>
							   <input type="hidden" name="tskSize" id="tskSize" value="<%=taskList.size()%>">
							   <%
						   }
					}
					%>
				</tbody>
			</table>
		</div>
		</div>
	</div>

	<div class="hLineLg"></div>
	<div id="search">	
	<table class="service" cellpadding="0" cellspacing="5">
	<tr>
		<td style="vertical-align: top;width:35%">
			<table style='margin: 12px 0px 0px 0px; padding: 0px 0px;border-bottom: 1px sold #000;'>
				<tr>
					<th colspan="2" class="hd">Create Notes</th>
				</tr>
				<tr>
					<td width=20%><b>Type</b></td>
					<td><b>Message</b></td>
				</tr>
				<tr>
					<td> <!-- <select id="sNoteType" name="sNoteType" class="search_request">
				           <option value=""></option>       
				         </select> -->
			             <select id="sNoteType" name="sNoteType" class="search_request" style="height: 25%;padding: 3px 3px;">
				             <%
				             ArrayList alNt = (ArrayList<CanonE307NoteTypeRec>) crDao.getNoteTypes();
				             if(alNt!=null && alNt.size()>0){
				             	for(int i=0;i<alNt.size();i++){
				             		CanonE307NoteTypeRec ntRec = (CanonE307NoteTypeRec)alNt.get(i);
				             		if("Y".equals(ntRec.getAsccDefFlg())){
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
						<textarea name="sNoteTypeTxt" id="sNoteTypeTxt" cols="26" rows="2" style="border: solid 1px #336699"></textarea> 
					</td>
				</tr>
			</table>
		</td>
		<td style="vertical-align: top;width:45%;min-height:50px;"> 
		 
			<table width="100%" id='viewNoteTable' style='margin: 12px 0px 0px 0px; padding: 0px 0px;border-bottom: 1px sold #000;'>
				<tr>
					<th colspan="4" class="hd">View Notes</th>
				</tr>
				<tr>
					<td  style="width:25%"><b>Type</b></td>
					<td  style="width:15%"><b>Date</b></td>
					<td  style="width:25%" nowrap><b>Created By</b></td>
					<td  style="width:35%"><b>Message</b></td>
					
				</tr>
				</table>
			 <div  style="width:100%;height:  96px; overflow-y:auto;margin: 0px 0px; padding: 0px 0px;">
			   <table width="100%" id='viewNoteTable1' style='margin: 0px 0px; padding: 0px 0px;border-top: 0px;'>
				<%
			String strTaskNumber="";
			if(obj[4] !=null) {
				List<CanonE307DebriefViewNotesRec> viewNotesRepData =new ArrayList<CanonE307DebriefViewNotesRec>();
				viewNotesRepData =(ArrayList<CanonE307DebriefViewNotesRec>) obj[4];
				if(viewNotesRepData	!=null && viewNotesRepData.size()>0){ 
					int cnt=0;
					for(CanonE307DebriefViewNotesRec notesBeanObj: viewNotesRepData){
					%>
					<tr>
						<td   style="width:25%"><%=notesBeanObj.getStrNoteType()==null?"":notesBeanObj.getStrNoteType() %></td>
						<td   style="width:15%"><%=notesBeanObj.getNoteDate()==null?"":utilObj.getTmZone(servObj.getStrCurPostCd(), servObj.getStrCurCtryCd(), notesBeanObj.getNoteDate())%></td>
						<td   style="width:25%"><%=notesBeanObj.getStrCreatedBy()==null?"":notesBeanObj.getStrCreatedBy()%></td>
						<td   style="width:35%"><%=notesBeanObj.getStrNoteText()==null?"":notesBeanObj.getStrNoteText() %></td>
						<input type="hidden" name="noteSourceId<%=cnt%>" id="noteSourceId<%=cnt%>" value="<%=notesBeanObj.getStrNoteSourceId()%>">
					</tr>
				<%
				cnt++;
					}
				}
			}
			%>
			</table>
			</div>	
		</td>
		<%
			CanonE307FileUploadDownloadAPIUtil downloadObj = new CanonE307FileUploadDownloadAPIUtil();
			ArrayList<CanonE307AttFileRec> attList = new ArrayList<CanonE307AttFileRec>();
			//System.out.println("strFSRNumber Before ATT fileId: " + strFSRNumber+" strSerNum "+ strSerNum);
			attList = detailObj.getAttFileIds(servObj.getStrFSRNum(), strSerNum,"");
			ArrayList<CanonE307AttFileRec> fsrReportList = new ArrayList<CanonE307AttFileRec>();
			fsrReportList = detailObj.getFSRReportInfo(servObj.getStrFSRNum());
			System.out.println("strFSRNumber fsrReportList: " + servObj.getStrFSRNum()+" strSerNum "+ strSerNum);
		%>
		<td style="vertical-align: top;width:20%">
			<table width="100%" style='margin: 12px 0px 0px 0px; padding: 0px 0px;border-bottom: 1px sold #000;'>
				<tr>
					<th class="hd">View Attachments</th>
				</tr>
			<% 
			if((attList!=null && attList.size()>0)||(fsrReportList!=null && fsrReportList.size()>0)){
			if(attList!=null && attList.size()>0){
				for(CanonE307AttFileRec attObj: attList){
					System.out.println("file Id : "+attObj.getFileId());
					if(attObj.getFileId()>0){
					//	String fileName="";
						String fileName = downloadObj.downLoadAttachment(strFSRNumber, strSerNum, loginUser, attObj.getFileId());
						%>
						<tr>
							<td><a href="javascript:void fnDownAttach('<%=fileName %>')"><%=attObj.getStrfileName()%></a></td>
						</tr>
						<%
					}
				}
			}
			if(fsrReportList!=null && fsrReportList.size()>0){
				for(CanonE307AttFileRec reportObj: fsrReportList){
					System.out.println("reportObj file Id : "+reportObj.getFileId());
					if(reportObj.getFileId()>0){
											%>
						<tr>
							<td><a href="javascript:void fnDownAttachReport('<%=reportObj.getFileId()%>','<%=reportObj.getStrfileName()%>')"><%=reportObj.getStrfileName()%>.pdf</a></td>
						</tr>
						<%
					}
				}
			}
			}else{
			%>
						<tr>
							<td>There are no attachments.</td>
						</tr>			
			<%
			}
			%>
			</table>
		</td>
		</tr>
	</table>
</div>
</div>	
	<div class="hLineLg"></div>
	<table width="100%">
		<tr>
			<td width="84%"></td>
			<td><a href="javascript:void fnAuditInfo()" class="btn" style="white-space: nowrap;">Audit Log</a></td>
			<%
			if("Y".equals(strCrctnFlg)){
			%>
				<td><a href="javascript:void fnCrctnMeterReads('','')" class="btn" style="white-space: nowrap;">Correction Meter Reads</a>	  </td>
			<%	
			}else{
			%>
			<td>&nbsp;</td>
			<%}%>

			<td><a href="javascript:void fnMachHistory()" class="btn" style="white-space: nowrap;">Machine History</a>	  </td>
			<td><a href="javascript:void fnViewEscalations()" class="btn" style="white-space: nowrap;">View Escalations</a>	  </td>
			<td><a href="javascript:void fnEscalate()" class="btn" style="white-space: nowrap;">Escalations</a>	  </td>
			<%					
				if("Y".equalsIgnoreCase(servObj.getStrFsrUpdFlag())){
			%>
			<% if("Y".equalsIgnoreCase(servObj.getStrAttribute2())){ %>
				<td><a href="javascript:void fnAddTask('')" class="btn disp_mode" style="white-space: nowrap;">Add Task</a>	  </td>	
			<%}else{ %>	
				<td>&nbsp;</td>	
			<%} %>	
				<td style="align:right">
					<a href="javascript:void fnCharges('')" class="btn" style="white-space: nowrap;">Charges</a>	  </td>
				<td style="align:right"><a href="javascript:void fnUpdateSummary()" class="btn disp_mode" style="white-space: nowrap;">Save Changes</a>	</td>
<!-- 				<td style="align:right">
				<a href="javascript:void fnCancelSr();" class="btn disp_mode srm_mode" style="white-space: nowrap;">Cancel Service Request</a></td> -->
				<td style="align:right">
				<a href="javascript:void fnCancelTsks();" class="btn disp_mode srm_mode" style="white-space: nowrap;">Cancel Task</a></td>				
			<%
				}else{
			%>
				<td align="right" colspan="4">
					<a href="javascript:void fnCharges('')" class="btn" style="white-space: nowrap;align:right">Charges</a>	  </td>
			<%
				}
			%>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr><td colspan=5>&nbsp;&nbsp;</td></tr>
		<tr><td colspan=5>&nbsp;&nbsp;</td></tr>
		<tr><td colspan=5>&nbsp;&nbsp;</td></tr>
	</table>	
</div>
 </form>
<script type="text/javascript">
var chckFlg="N";
function fnRdOnly(){
	$('input[type="text"]').each(function (){
		 $(this).addClass("rdl").attr("readonly","readonly");
	});

	$('#sNoteType').addClass("rdl").attr("disabled","true");
//	$('#noteDetails').attr("disabled","disabled");
	$('#noteDetails').prop('disabled', true);
	
	 $("#tasksTable select").each(function (i, e){
 			 $(this).prop("disabled","true");  
	  });	
}
function fnCancelTsk(tskNum){
	//$('#frmSummary #scratchPad').val( $("#toolTip textarea").val());
	$("#cnclFlg").val("Y");
	var url="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?cnclFlg=Y&cnclTsk="+tskNum;
	<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
		url="<%="https://"+ request.getServerName()%>"+url;
	<% } %>
	$("#frmSummary").attr("action",url);	 
	$('#frmSummary').submit();	
}

function fnCancelTsks(){
   // var modelName ="#servInfoDiv";
    var modelName = "#cnclTaskDiv";
    var fsr = $('#fsr').val();
    var userName = $('#userName').val();
    var slsDt = $('#slsDt').val();
    showPleaseWait();
 	   $(modelName).dialog({
					height: 500,
					title: "Cancel Reasons", 
					modal: true ,
					zIndex:1005,
					width: 700,		
	                resizable: false     
				});
	       $.ajax({
			url: "canonE307ServReqCancelRsn.jsp?fsr="+fsr+"&slsDt="+slsDt+"&userName="+userName,
			type:"GET",
	        cache: false,
			success: function(data){     
					  hidePleaseWait();     
					  $(modelName).html("");					
				      $(modelName).html(data);
				      // setRowStyles(".lovTbl");
				     //  $("#a1").css({"color":"white","background-color":"#A10304"});
		 	        }             
		 });
		  
	     $( modelName ).dialog("open");
	 //    $(".ui-dialog").css({"top":"500px"});
	     $(".ui-dialog-titlebar").addClass("hd");
}
function fnDebriefTask(tskNum, iVal){
	var tmZn = $('#tmZn').val();
	var futureDate = $('#ftrSrvDt').val();
    showPleaseWait();
	var url="canonE307ServiceRequestDebrief.jsp?debriefTsk="+tskNum+'&tmZn='+tmZn+'&futureDate='+futureDate;
	$("#frmSummary").attr("action",url);	 
	$('#frmSummary').submit();	
}
$(document).ready(function() {
	 $(".datePicker").datepicker({
		 dateFormat: 'dd-M-yy',
		 changeMonth: true,
		 changeYear: true
	 });
	 $(".timePicker").timepicker({
		 timeFormat: 'HH:mm',
		 interval: 15
	 });
	 $("#tasksTable").css('display', 'block');	 
	 fnDisableCol();
	 var tskSze = $('#tskSize').val();
	 tskSze = parseInt(tskSze);
	 if(tskSze>0){
		 for(i=0;i<tskSze;i++){
			 var taskSel = $('#tskStsCd'+i).val();
			 var assignTypeSel = $('#assTpCd'+i).val();
			// setCallSelects('ts',taskSel, i);
			 setCallSelects('at',assignTypeSel,i);		 
		 }		 
	 }
	 <%
	 if("Y".equals(strShowVend)){
	 %>
	 	fnVendInfo();
	 <%
	 }
	 %>
	 <%
	 if("N".equals(servObj.getStrFsrUpdFlag())){
	 	%>
	 	fnRdOnly();
	 	<%
	 }else if("E307_OTH".equals(editRole)){
	 %>
	 	fnRdOnly();
	 	$('.disp_mode').hide();
	 <%
	 }else if("E307_SRM".equals(editRole)){
	 %>
	 	$('.srm_mode').hide();
	 	$('select').children('option[value="99"]').attr('disabled', true)
	 <%	
	 }
	 %>	 
	// setCallSelects("nt",'','');
	 var taskSts = $('#tskSts').val();
	 if(taskSts != 'Completed'){
		 setTimeout("fnCheckUpdates()",10000);
	 }
	 var tNumHis = parseInt($('#tNumHis').val());
	 if(tNumHis>0){
		// fnShowSrDet();
		 $('#srDetDiv').show();
		 tNumHis = tNumHis-1;
		 $('#taskRow'+tNumHis+ " .btn_red_sm").click();
		// $('#taskStatDet'+tNumHis).focus();
		// $('#sNoteTypeTxt').focus();
		 $('#taskDiv').find(' .model-table').scrollTo('#taskRow'+tNumHis, 500, {offset: $('#taskDiv').offset().top - $('#taskDiv').height() - $('#taskRow'+tNumHis).height() })		 
	 }

	 setTimeout("fnCheckTaskUpdates()",10000);
	
});

function customEncodeURIComponent(str) {
    return encodeURIComponent(str).replace(/'/g, '%60');
}

function fnVendInfo(){
	<% if((!"Closed".equals(servObj.getStrSrSts())) && (!"Cancelled".equals(servObj.getStrSrSts()))){%>
		var vendName = $('#vendName').val();
		var vendContact = $('#vendContact').val();
		var vendPhone = $('#vendPhone').val();
		var vendEmailAddrs = $('#vendEmailAddrs').val();
		var covidVacMsg = $('#covidVacMsg').val();
		
		vendName = customEncodeURIComponent(vendName);
		covidVacMsg = customEncodeURIComponent(covidVacMsg);
	
		if(vendName!=null && vendName.length!=0 ){
			var url="canonE307ThirdPartyVendInfo.jsp";
			var qryStr="?vendName="+vendName+"&vendContact="+vendContact+"&vendPhone="+vendPhone+"&vendEmailAddrs="+vendEmailAddrs+"&covidVacMsg="+covidVacMsg;
	
			url +=qryStr;
			var l_newWindow = window.open(url, "","scrollbars,width=600,height=450,left=400,top=300");
			l_newWindow.focus();
			 
		}
<%	} %>
}

function fnDisableCol(){
	 $('#contractInfoDiv :input').attr('disabled', true);	
	 $('#srDisDiv :input').attr('disabled', true);
	 $('input[type="button"]').attr('disabled', false);
	 $("input.verifiedPO").removeAttr("disabled");
	 $("#ittCallNumber").removeAttr("disabled");
	 $("#ittCallNumber").prop( "disabled", false );
	 $('input[type="checkbox"]').attr('disabled', false);
	 var taskStat = $('#taskStat0').html();
	 if(taskStat !='TBO'){
		 $("#etaBtn").prop("disabled", true);
	 }
	 $(".rdl").each(function (){
		 
		  var ele=$(this);
		  var tp = $(ele).attr("type");
		  if(tp=="text")
			  $(ele).addClass("rdl").attr("readonly","readonly"); 
		 // else   
		//	  $(ele).addClass("rdl").attr("disabled","true");	  
		  
	 });
}

function fnGetAssignee(iVal){
    var modelName ="#techDiv";
    $(modelName).html("");	
    var pageNum=1;
    var assignName = '';//$("#assigneeDet"+iVal).val();
	   $(modelName).dialog({
					height: 580,
					title: "Assignee Details", 
					modal: true ,
					zIndex:1005,
					width: 650,		
					resizable: false    
				});
	   showPleaseWait();
	   var qryStr="pageNum=1&assignName="+encodeURIComponent(assignName)+"&iVal="+iVal;
	       $.ajax({
			url: "canonE307ServReqAssigneeLov.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){     
					  hidePleaseWait();     
					  $(modelName).html("");					
				      $(modelName).html(data);
				       setRowStyles(".lovTbl");
				       $("#a"+pageNum).css({"color":"white","background-color":"#A10304"});
     				   hidePleaseWait();
		 	        }             
		 });
		  
	     $( modelName ).dialog("open");
	   //  $(".ui-dialog").css({"top":"990px"}); 
	     $(".ui-dialog-titlebar").addClass("hd");
 }
function searchAssignee(pageNum, iVal) {
	var modelName = "#techDiv";
	var assignName = $("#assignName").val();
	var branchNm = $('#branchNm').val();
	//assignName= "test";
	assignName = encodeURIComponent(assignName);
	branchNm = encodeURIComponent(branchNm);
    showPleaseWait();
	var qryStr="pageNum="+pageNum+"&assignName="+assignName+"&iVal="+iVal+"&branchNm="+branchNm;
	 $.ajax({
			url:"canonE307ServReqAssigneeLov.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){     
					  hidePleaseWait();     
					  $(modelName).html("");					
				      $(modelName).html(data);
				       setRowStyles(".lovTbl");
				       $("#a"+pageNum).css({"color":"white","background-color":"#A10304"});
				       hidePleaseWait();
		 	        }             
	});
}
function fnGetMachManager(){
    var modelName ="#servInfoDiv";
    var machManager = $("#machineManager").val();
	   $(modelName).dialog({
					height: 500,
					title: "Manager Details", 
					modal: true ,
					zIndex:1005,
					width: 700,		
	                resizable: false     
				});
	   showPleaseWait();
	   var qryStr="pageNum=1&machManager="+encodeURIComponent(machManager);
	       $.ajax({
			url: "canonE307ServReqMachMgrLov.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){     
					  hidePleaseWait();     
					  $(modelName).html("");					
				      $(modelName).html(data);
				       setRowStyles(".lovTbl");
				       $("#a1").css({"color":"white","background-color":"#A10304"});
				       hidePleaseWait();
		 	        }             
		 });
		  
	     $( modelName ).dialog("open");
	     $(".ui-dialog").css({"top":"500px"});
	     $(".ui-dialog-titlebar").addClass("hd");
 }
function searchMachMgr(pageNum) {
	var modelName = "#servInfoDiv";
	var machineManager = $("#machManagerLov").val();
	machineManager = encodeURIComponent(machineManager);
    showPleaseWait();
	var qryStr="pageNum="+pageNum+"&machManager="+machineManager;
	 $.ajax({
			url:"canonE307ServReqMachMgrLov.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){     
					  hidePleaseWait();     
					  $(modelName).html("");					
				      $(modelName).html(data);
				       setRowStyles(".lovTbl");
				       $("#a"+pageNum).css({"color":"white","background-color":"#A10304"});
				       hidePleaseWait();
		 	        }             
	});
	  $(".ui-dialog").css({"top":"500px"});
	 $('#machineManager').focus();
}
function setRowStyles(tbl) {
	$(tbl + " tbody tr:odd").each(function() {
		$(this).addClass("oddRow");
	});
}

function fnGetTaskDet(selColor, iVal){
	var task=$('#task'+iVal).html();
	var taskNumber=$('#taskNumberDet'+iVal).val();
	var tStatSelVal=$('#tskStsCd'+iVal).val();
	var assignSelVal=$('#assTpCd'+iVal).val();
	$('#taskNumberDet').val($('#task'+iVal).html());
	$('#creationDateDet').val($('#creDate'+iVal).html());
	$('#taskTypeDet').val($('#taskType'+iVal).html());
	$('#scheduleStartDet').val($('#schStart'+iVal).html());	
	$('#scheduleEndDet').val($('#schEnd'+iVal).val());
	$('#assigneeDet').val($('#assignee'+iVal).html());	
	$('#assDetCode').val($('#assTpCd').val());
	$('#actualStartDet').val($('#actStart'+iVal).val());
	$('#actualEndDet').val($('#actEnd'+iVal).val());
	$('#lUpdatedByDet').val($('#lUpdatedBy'+iVal).val());
	
//	$("#taskStatDet"+' option[value="' + tStatSelVal + '"]').prop('selected', true);
	$("#assignTypeDet"+' option[value="' + assignSelVal + '"]').prop('selected', true);
	

}
function setCallSelects(s,selVal,iVal){
	showPleaseWait();
    var eo="";
    var sc="";
    var optTxt="";
    var optVal="";
    var sId="";
    var le="ListEntry";
    var wc="";
	
 /*   if(s=="ts"){
      sId="#taskStatDet"+iVal;
      eo="taskStatus";	
      sc="taskStatusName;taskStatusCode";
      optTxt="taskStatusName";
      optVal="taskStatusCode";
      //selVal=$('#tskStsCd0').val();
    }else*/
    if(s=="at"){
      sId="#assignTypeDet"+iVal;
      eo="assigneeType";	
      sc="assigneeTypeName;assigneeTypeCode";
      optTxt="assigneeTypeName";
      optVal="assigneeTypeCode";	
    }else if(s=="nt"){
   	 sId="#sNoteType";
	 sc="noteTypename;noteTypeCd;ntASCCDefFlg";
     eo="noteType";	
     wc="ntASCCSelFlg:Y"
     optTxt="noteTypename";
     optVal="noteTypeCd";
 	}else if(s=="ats"){
      sId="#taskTpe"+iVal;
      eo="taskStatus";	
      sc="taskStatusName;taskStatusCode";
      wc = "taskTypeSelFlag:Y";
      optTxt="taskStatusName";
      optVal="taskStatusCode";	
 	}else if(s=='rt'){
	  sId = "#resType";
      eo="assigneeType";	
      sc="assigneeTypeName;assigneeTypeCode";
      optTxt="assigneeTypeName";
      optVal="assigneeTypeCode";		  
 	}
    var defaultVal="";
	$(sId).html("");
	//var qryStr="reqType=xml&eo="+eo+"&sc="+sc;
	var qryStr="reqType=xml&eo="+eo+"&sc="+sc+"&wc="+wc;
	var urlSrUtil  = "canonE307ServiceReqCrUtil.jsp";
	$.ajax({
	    url:urlSrUtil,
	    data:qryStr,
	    type:"POST",
	    success: function(data){
	           $xml = $( $.parseXML( data ) );
	             $xml.find(le).each(function(){
	            	 
                        var ot = $(this).find(optTxt).text();
                        var ol = $(this).find(optVal).text();
                        $(sId).append($("<option></option>").attr("value",ol).text(ot)); 
                        if(s=="nt"){
                       		 var ntDefVal= $(this).find("ntASCCDefFlg").text();
                       	 	//console.log(" Note defVal : "+ ntDefVal +  ot + ol);
                       	 	if(ntDefVal=="Y"){
                       		 	defaultVal=ol;
                       		 	//console.log("note val : "+ ot + ol+" defaultVal : "+defaultVal);
                       	 	}
                        }                         
	             });
	           if((s=='rt')|| (s=='at' && selVal=='')){
	        	   $(sId).append($("<option></option>").attr("value",'').prop('selected', true));
	           } else if(s=="nt"){
	        	   $(sId+' option[value="' + defaultVal + '"]').prop('selected', true); 
	           }else if(s=='at' && selVal!=null && selVal!=""){
	           		$(sId+' option[value="' + selVal + '"]').prop('selected', true);
	           }else{
	        	   $(sId+' option[value="' + selVal + '"]').prop('selected', true);
	           }
	             hidePleaseWait(); 
	   },
	    error: function(data){
	        //console.log('Error loading XML data : '+sId);
	        hidePleaseWait() ;
	    }
	});
	
}

function  scrollToPgTop(ele){
	 $('html, body').animate({
	        scrollTop: $(ele).offset().top-60
	    }, 100);
}
function fnUpdateSummary(){
	 $("#eMsg").html("");
	 $("#errorWidget").hide();
	 
	 if(fnCheckTskStsBeforeUpdate()){
		return false;
	 }else if(fnGetTaskLstUpdDt()){
			alert("The data has been updated by another user. Please refresh this page and submit the changes.");
	 }else{
		  emailChk = true; 
		 if(checkEmail($('#emailAddr').val()) || $('#emailAddr').val().toUpperCase().indexOf("REFUSED")==0){
			 emailChk = true; 
		 }else{
			emailChk = false;
		 }
		 
		 var custPhNum = $('#custPhNum').val();
		 var custPhNumExt = $('#custPhNumExt').val();
		 var totTelNum = custPhNum+custPhNumExt;
		 if(totTelNum.length>20){
			$("#eMsg").html("Contact Telephone Number & Extn combined can't be more than 20 characters.");
	  		$("#errorWidget").show();
	  		scrollToPgTop($("#errorWidget"));
			return false;
			
		 }
		 
		 var callerPhn = $('#callerPhn').val();
		 var callerExtn = $('#callerExtn').val();
		 var totCllrTelNum = callerPhn+callerExtn;
		 if(totCllrTelNum.length>20){
			 $("#eMsg").html("Caller Telephone Number & Extn combined can't be more than 20 characters.");
	  		 $("#errorWidget").show();
		  	 scrollToPgTop($("#errorWidget"));
				return false;
		 }
		   	
	   	 if(!emailChk){
	   		 $("#eMsg").html("Please enter email address in following format - xxxxxx@xxxx.xxx .");
	   		 $("#errorWidget").show();
	   		scrollToPgTop($("#errorWidget"));
	   	 }else{
	   		var crBtn = $('#crBtn').text();
	   		if(crBtn == 'Reauthorize'){
	   		 if($("#profileId").val()!=null && $("#profileId").val()!=""){
				 	$.creditCard("creditCardRequestS21CSA_ASCC.jsp",{
			 			action:"auth",
			 			profileId:$("#profileId").val(),
			 			amount:$("#authAmnt").val(),
			 			orderID:"CANON"+new Date().getTime()
			 		}
			 		
			 	).done(function(data){
					//alert(data.procStatusMessage +" txRefNum="+data.txRefNum);
					// $('#csForm #scratchPad').val( $("#toolTip textarea").val() );
					
			 		if($.trim(data.approvalStatus)==1){
				    	 $('#txRefNum').val(data.txRefNum);
				    	 showPleaseWait();
						 $("#updFlg").val("Y");
						 $('#modeCode').val('');
						 $('#modeCode').val('02');
						 var fsrNum =  $('#fsr').val();
						 var url="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?fsr="+fsrNum;
						 <% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
						 	url="<%="https://"+ request.getServerName()%>"+url;
						<% } %>
						 showPleaseWait();
						 $("#frmSummary").attr("action",url);	 
						 $('#frmSummary').submit();			 			
			 		}else{
			 			var termCdVal = $('#termCd').val();
			 			 //alert("Proc Status : "+ data.procStatus+" respCode: "+respCode);
			 			if($.trim(termCdVal) =='CC'){
			 				 $("#eMsg").html("Credit Card Authorization Failed! - Reason: "+ data.procStatusMessage + " \n - Review Credit Card Information and resubmit.");
			 				 $("#errorWidget").show();
			 				return false;
			 			}else{
			 				var authdecision= confirm("Credit Card Authorization Failed! - Reason: "+data.procStatusMessage+" \n Click OK to Proceed , Cancel to Retry!");
			 				if(authdecision == true)
			                {
						 		 $('#txRefNum').val('');
								 $("#updFlg").val("Y");
								 $('#modeCode').val('');
								 $('#modeCode').val('02');
								 var fsrNum =  $('#fsr').val();
								 var url="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?fsr="+fsrNum;
								 <% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
								 	url="<%="https://"+ request.getServerName()%>"+url;
								<% } %>
								 showPleaseWait();
								 $("#frmSummary").attr("action",url);	 
								 $('#frmSummary').submit();
			                }else{
			                	return false;
			                }
			 			}
			 		}
					
			 	/*	 $('#txRefNum').val(data.txRefNum);
					 $("#updFlg").val("Y");
					 $('#modeCode').val('');
					 $('#modeCode').val('02');
					 var fsrNum =  $('#fsr').val();
					 var url="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?fsr="+fsrNum;
					 <% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
					 	url="<%="https://"+ request.getServerName()%>"+url;
					<% } %>
					 showPleaseWait();
					 $("#frmSummary").attr("action",url);	 
					 $('#frmSummary').submit();	*/
			 	});
				 }
	   		}else{
	   		 showPleaseWait();
			 $("#updFlg").val("Y");
			 $('#modeCode').val('');
			 $('#modeCode').val('02');
			 var fsrNum =  $('#fsr').val();
			 var url="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?fsr="+fsrNum;
			 <% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
			 	url="<%="https://"+ request.getServerName()%>"+url;
			<% } %>
			 $("#frmSummary").attr("action",url);	 
			 $('#frmSummary').submit();	
	   	 	}
	   	 }
	 }
}
function fnCancelSr(){
	$("#updFlg").val("Y");
	$('#modeCode').val('');
	var fsrNum =  $('#fsr').val();
	showPleaseWait();
	 var url="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?updFlg=Y&modeCode=03&fsr="+fsrNum;
	 <% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
	 	url="<%="https://"+ request.getServerName()%>"+url;
	<% } %>
	 $("#frmSummary").attr("action",url);	 
	 $('#frmSummary').submit();	
}

function fnSelAssign(techName, techCode, iVal){
	var modelName ="#techDiv";
	$('#assDetCode'+iVal).val(techCode);
	$('#assigneeDet'+iVal).val(techName);
	$(modelName).html("");
    $(modelName).dialog("close");
    $(modelName).dialog("destroy");
}
function fnSelManager(mgrName, mgrCode){
 	var modelName ="#servInfoDiv";
	$('#machMgrCode').val(mgrCode);
	$('#machineManager').val(mgrName);
	$(modelName).html("");
    $(modelName).dialog("close");
    $(modelName).dialog("destroy");
}
function fnAddTask(){
	 $('#eMsg').html('');
	 $('#errorWidget').hide();
	  var srStatus = $('#srSts').val();
	  var contActFlg = $('#contActFlg').val();
	  var svcMachMstrPk = $('#svcMachMstrPk').val();
	  if((srStatus=='Completed')||(srStatus=='Pending Charge')||(srStatus=='Closed')||(srStatus=='Cancelled')||(srStatus=='Debrief Error')){
		  $('#eMsg').html("Add Task is not avialable for "+srStatus+" status.");
		  $('#errorWidget').show();
	  }else{
 	  var modelName = 'servAddTaskDiv';
 	  var tmZn = $('#tmZn').val();
 	  var postalCd = $('#postalCd').val();
 	  var cntryCd = $('#cntryCd').val();
	  var urlDetail = 'canonE307AddTaskLov.jsp?action=open&contActFlg='+contActFlg+'&tmZn='+tmZn+"&svcMachMstrPk="+svcMachMstrPk+"&postalCd="+postalCd+"&cntryCd="+cntryCd;
			modelName = "#"+modelName;
	   showPleaseWait();
	   $(modelName).dialog({
					height: 500,
					title: "Add Task",
					modal: true ,
			autoOpen :false,
					 width: 550,		
			 resizable: false
				}); 
	   
		  $.ajax({
					url: urlDetail,
			cache: false,
					success: function(data){ 
					    hidePleaseWait();
					   $(modelName).html("");       
					   $(modelName).html(data);
					}             
				});
		//  setTmSelects('ft');
		  $(modelName).dialog("open");
		//  $(".ui-dialog").css({"top":"150px"});
		  $(".ui-dialog-titlebar").addClass("hd"); 
	  }
	}

function fnCopySr(fsrNumber){
	var mschMstrPk= $('#svcMachMstrPk').val();
	var model = $('#model').val();
	var serNum = $('#serialNumber').val();
	showPleaseWait();	
	var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqCreate.jsp?fsrNumber="+encodeURIComponent(fsrNumber);
	urlDetail +="&machPk="+mschMstrPk;
	urlDetail +="&model="+encodeURIComponent(model);
	urlDetail +="&serialNumber="+encodeURIComponent(serNum);
	document.frmFunc.action = urlDetail;
	document.frmFunc.submit();		
}
function fnMachHistory(){
	var serNum= $('#serNum').val();
	var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceReqHistory.jsp?serialNumber="+encodeURIComponent(serNum);
    var l_newWindow = window.open(urlDetail);
    l_newWindow.focus(); 
	//document.frmFunc.action = urlDetail;
	//document.frmFunc.submit();	
}
function fnCharges(taskNum){
	var serNum= $('#serNum').val();
	var fsrNum =  $('#fsrNum').val();
	var ftrSrvDt = $('#ftrSrvDt').val();
	var postalCd = $('#postalCd').val();
	var cntryCd = $('#cntryCd').val();
	showPleaseWait();
	var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceRequestCharges.jsp?fsrNum="+encodeURIComponent(fsrNum)+"&tskNum="+taskNum+"&ftrSrvDt="+ftrSrvDt+"&postalCd="+postalCd+"&cntryCd="+cntryCd;
	<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
		urlDetail="<%="https://"+ request.getServerName()%>"+urlDetail;
	<% } %>
	document.frmFunc.action = urlDetail;
	document.frmFunc.submit();		
}

function fnGetTech(){
    var modelName ="#techDiv";
    var pageNum=1;
    var prefTech = $("#prefTech").val();
	   $(modelName).dialog({
					height: 550,
					title: "Technician Details", 
					modal: true ,
					zIndex:1005,
					width: 750,		
	                resizable: false    
				});
	   
	   var qryStr="pageNum=1&prefTech="+encodeURIComponent(prefTech);
	       $.ajax({
			url: "canonE307ServReqPrefTechLov.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){     
					  hidePleaseWait();     
					  $(modelName).html("");					
				      $(modelName).html(data);
				       setRowStyles(".lovTbl");
				       $("#a"+pageNum).css({"color":"white","background-color":"#A10304"});
				       hidePleaseWait();
		 	        }             
		 });
		  
	     $( modelName ).dialog("open");
	    // $(".ui-dialog").css({"top":"100px"}); 
	    // $(".ui-dialog").css({"top":"400px"});
	     $(".ui-dialog-titlebar").addClass("hd");
}
function searchPrefTech(pageNum) {
	var modelName = "#techDiv";
	var prefTech = $("#prefTechNm").val();
	var branch = $('#brnchNm').val();
	//assignName= "test";
	var prefTechNm = encodeURIComponent(prefTech);
    showPleaseWait();
	var qryStr="pageNum="+pageNum+"&prefTechNm="+prefTechNm+"&brnchNm="+branch;
	 $.ajax({
			url:"canonE307ServReqPrefTechLov.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){     
					  hidePleaseWait();     
					  $(modelName).html("");					
				      $(modelName).html(data);
				       setRowStyles(".lovTbl");
				       $("#a"+pageNum).css({"color":"white","background-color":"#A10304"});
				       hidePleaseWait();
		 	        }             
	});
}
function fnSelTech(techName, techCode){
	var modelName ="#techDiv";
	$('#prefTechCode').val(techCode);
	$('#prefTech').val(techName);
	$(modelName).html("");
    $(modelName).dialog("close");
    $(modelName).dialog("destroy");
}
function fnCreateTask(){
	$('#eMsg').html("");
	$('#errorWidget').hide();
	$('#tskMsgDiv').html("");
	var checkFlag="Y";
	var ftrDtValid = $('#ftrDtVald').val();
	var taskType = $('#taskTpe').val();
	//var uniqueTech = $('#uniqueTech').val();
	var defltTech = $('#defltTechCd').val();
	var preftech = $('#prefTech').val();
	var resType= $('#resTpCd').val();
	var fSvcDate = $('#fSVcDate').val();
	var ftrSrvMn = $('#ftrSrvMn').val();
	var ftrSrvHr = $('#ftrSrvHr').val();
	var fsrNum = $('#fsr').val();
	var machMstrPk = $('#svcMachMstrPk').val();
	var svcCallTpCd =$('#svcCallTpCd').val();
	var modelName = '#servAddTaskDiv';
	var billTpCd = $('#bllTpCd').val();
	var techCd =$('#prefTechCode').val();
	var slsDt = $('#slsDt').val();
	var ftrAmPm = $('#ftrAmPm').val();
	var notes = $('#notesDescription').val();
	var noteType = $('#noteType').val();
	var postalCd = $('#postalCd').val();
	var cntryCd = $('#cntryCd').val();
	
	var urlDetail ="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?fsr="+fsrNum+"&addTsk=Y";
	 <% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
	 	urlDetail="<%="https://"+ request.getServerName()%>"+urlDetail;
	<% } %>
	var prtyCd="";
	if(taskType.indexOf(':')>-1){
		tskTpCd =  $.trim(taskType.split(":")[0]);
		prtyCd = $.trim(taskType.split(":")[1]);
	}
	if(ftrDtValid=='N'){
		$("#tskMsgDiv").css({"color": "red", "font-size": "12"});
		$('#tskMsgDiv').html("Date & Time must be equal to or greater than current date");
		checkFlag="N";		
	}
	if(fSvcDate=="null" || fSvcDate==""){
		$("#tskMsgDiv").css({"color": "red", "font-size": "12"});
		$('#tskMsgDiv').html("Future Service Date is compulsory");
		checkFlag="N";
	}
	if(ftrSrvHr=="null" || ftrSrvHr==""){
		$("#tskMsgDiv").css({"color": "red", "font-size": "12"});
		$('#tskMsgDiv').html("Future Service Time is compulsory");
		checkFlag="N";
	}
	if(notes=="null" || notes==""){
		$("#tskMsgDiv").css({"color": "red", "font-size": "12"});
		$('#tskMsgDiv').html("Task Notes is compulsory");
		checkFlag="N";
	}	
	
	if(checkFlag=='Y'){
		var url = "canonE307ServReqAddTask.jsp";
		var qryStr= "&fsrNum="+fsrNum+"&tskTpCd="+tskTpCd+"&prtyCd="+prtyCd;
		qryStr = qryStr+"&preftech="+encodeURIComponent(preftech)+"&defltTech="+encodeURIComponent(defltTech);
		qryStr = qryStr+"&resType="+encodeURIComponent(resType)+"&fSvcDate="+encodeURIComponent(fSvcDate);
		qryStr = qryStr+"&ftrSrvMn="+encodeURIComponent(ftrSrvMn)+"&machMstrPk="+machMstrPk+"&ftrSrvHr="+encodeURIComponent(ftrSrvHr);
		qryStr = qryStr+"&svcCallTpCd="+svcCallTpCd+"&billTpCd="+billTpCd+"&techCd="+techCd+"&slsDt="+slsDt+"&ftrAmPm="+ftrAmPm;
		qryStr = qryStr+"&noteType="+noteType+"&tskNts="+encodeURIComponent(notes);
		qryStr = qryStr+"&postalCd="+postalCd+"&cntryCd="+cntryCd;
		
		showPleaseWait();
		$.ajax({
			url: url,
			data: qryStr,
			type:"POST",
			cache: false,
			success : function(result)
			{
				hidePleaseWait();
				var res = result.split(":");
				if($.trim(res[0])=='E'){
					$("#eMsg").css({"color": "red", "font-size": "12"});
					$('#eMsg').html($.trim(res[1]));
					$('#errorWidget').show();
					$(modelName).html("");
				    $(modelName).dialog("close");
				    $(modelName).dialog("destroy");
				}else{
					$('#eMsg').html("Task is added succesfully.");
					$("#eMsg").css({"color": "green", "font-size": "12"}); 
					$('#errorWidget').show();
					$('#tskSucc').val("Y");
					$(modelName).html("");
				    $(modelName).dialog("close");
				    $(modelName).dialog("destroy");
					document.frmSummary.action = urlDetail;
					document.frmSummary.submit();						
				}
			}
		});
	}
}

function fnShowTskDet(){
	if (false == $('#taskDiv').is(':visible')) {
        $('#taskDiv').show(250);
        $('#tskShowDiv').html('<a href="javascript:void fnShowTskDet()" class="btn" style="white-space: nowrap;">Hide Task Details</a>');
    }
    else {
        $('#taskDiv').hide(250);
        $('#tskShowDiv').html('<a href="javascript:void fnShowTskDet()" class="btn" style="white-space: nowrap;">Show Task Details</a>');
    }
}
function fnShowSrDet(){
	if (false == $('#srDetDiv').is(':visible')) {
		//$('#shwHdFlg').val('Y');
		 var fsr = $('#fsr').val();
		 var url="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?shwHdFlg=Y&fsr="+fsr;
		<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
			url="<%="https://"+ request.getServerName()%>"+url;
		<% } %>
		 $("#frmSummary").attr("action",url);	 
		 $('#frmSummary').submit();	
		
   //     $('#srDetDiv').show(250);
    //    $('#srShowDiv').html('<a href="javascript:void fnShowSrDet()" class="btn" style="white-space: nowrap;">Hide Details</a>');
    }else {
        $('#srDetDiv').hide(250);
        $('#srShowDiv').html('<a href="javascript:void fnShowSrDet()" class="btn" style="white-space: nowrap;">Show Details</a>');
    }
}
function fnCloseTskDlg(dId){
	//var dlgId="#"+dId;
	//var modelName = '#cnclTaskDiv';
	var modelName = "#"+dId;
	$(modelName).html("");
    $(modelName).dialog("close");
    $(modelName).dialog("destroy");
	var url="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp";
	<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
		url="<%="https://"+ request.getServerName()%>"+url;
	<% } %>
	$("#frmSummary").attr("action",url);	 
	$('#frmSummary').submit();	    
}
function fnCloseMtrDlg(dId){
	//var dlgId="#"+dId;
	//var modelName = '#cnclTaskDiv';
	var modelName = "#"+dId;
	$(modelName).html("");
    $(modelName).dialog("close");
    $(modelName).dialog("destroy");
}

function fnCloseSearchDlg(dId){
	var modelName="#"+dId;
	$(modelName).html("");
    $(modelName).dialog("close");
    $(modelName).dialog("destroy");
}
function setTmSelects(s){
	//showPleaseWait();
    var eo="";
    var sc="";
    var optTxt="";
    var optVal="";
    var sId="";
    var le="ListEntry";
    var wc="";
    
    var eom="";
    var scm="";
    var optTxtm="";
    var optValm="";
    var pSize="";
    
    var $xmlMin="";
	
    if(s=="st" || "et"){
      pSize = $('#labRecSize').val();
      pSize = parseInt(pSize);
    }else{
    	pSize=parseInt('1');
    } 
      eo="hourPicker";	
      sc="hourList";
      optTxt="hourList";
      optVal="hourList";
      ordH="hourValOrd";
      
      eom="minutePicker";
      scm="minList";
      optTxtm="minList";
      optValm="minList";
      ordM="minValOrd";
     
      
	   var qryStr="reqType=xml&eo="+eom+"&sc="+scm+"&wc="+wc+"&ord="+ordM;;
	   var urlSrUtil  = "canonE307ServiceReqCrUtil.jsp";
		$.ajax({
		    url:urlSrUtil,
		    data:qryStr,
		    type:"POST",
		    async: false,
		    success: function(data){
		       $xmlMin = $( $.parseXML( data ) );
   			 }
		});
	sId="#fSvcTime";   
	var d = new Date(); 
	var hor =d.getHours(); 
	var min = d.getMinutes(); 
	var time = hor+":"+min;
	$(sId).html("");
	var selVal="";
	//var qryStr="reqType=xml&eo="+eo+"&sc="+sc;
	var qryStr="reqType=xml&eo="+eo+"&sc="+sc+"&wc="+wc+"&ord="+ordH;;
	var urlSrUtil  = "canonE307ServiceReqCrUtil.jsp";
	$.ajax({
	    url:urlSrUtil,
	    data:qryStr,
	    type:"POST",
	    success: function(data){
	           $xml = $( $.parseXML( data ) );
			             $xml.find(le).each(function(){
	                         var ot = $(this).find(optTxt).text();
	                         var ol = $(this).find(optVal).text();
	                        // console.log('loading XML data1 : '+ ot+":"+ol);
			            	 $xmlMin.find(le).each(function(){
			            		 var otm = $(this).find(optTxtm).text();
		                         var olm = $(this).find(optValm).text();
		                        // console.log('loading XML data2 : '+ ol+":"+olm);
		                         var totVal=ol+":"+olm;
		                         $(sId).append($("<option></option>").attr("value",totVal).text(totVal)); 
			            	 });//min each
			             }); // hour each
		           $(sId+' option[value="' + time + '"]').prop('selected', true);	    			
 		        //     hidePleaseWait(); 
	   },
	    error: function(data){
	       // console.log('Error loading XML data : '+sId);
	       // hidePleaseWait() ;
	    }
	}); 
}

function uploadPOAttachment()
{
    var fileNm = $('#poUpld').val();
    var userName = $('#userName').val();
	if(fileNm!=''){
		var url = "canonE307AttachmentUpload.jsp";
		var qryStr= "&fileNm="+fileNm+"&userName="+userName;
		
		$.ajax({
			url: url,
			data: qryStr,
			type:"POST",
			cache: false,
			success : function(result)
			{
					var reslt = $.trim(result);
					if(reslt!='null' && reslt!=''){
						 var retFlg = $.trim(reslt.split("|")[0]);
						 var retMsg = $.trim(reslt.split("|")[1]);
						if($.trim(retFlg)=='E'){
							$("#eMsg").css({"color": "red", "font-size": "12"});
							$('#eMsg').html(retMsg);
							$('#errorWidget').show();
						}else{
							$('#eMsg').html("PO is uploaded Successsfully..");
							$("#eMsg").css({"color": "green", "font-size": "12"}); 
							$('#errorWidget').show();

						}						 
					}


			}
		});	
	}
}
function fnUploadPOAttach1(){
//	var url = "canonE307FileUploadToTemp.jsp?source=summary";
//	window.open(url,'newWin','width=550,height=200,left=350,top=200,scrollbars=1');
    var modelName ="#fileDlg";
    var pageNum=1;
    var userName = $('#userName').val();
	var fsrNum = $('#fsr').val();
	var serNum = $('#serialNumber').val();
	   $(modelName).dialog({
					height: 450,
					title: "PO Upload", 
					modal: true ,
					zIndex:1005,
					width: 550,		
					resizable: false    
				});
	   
	   var qryStr="source=summary&userName="+encodeURIComponent(userName)+"&fsrNum="+fsrNum+"&serial="+serNum;
	       $.ajax({
			url: "canonE307FileUploadToTemp.jsp",
			data:qryStr,
			type:"POST",
	        cache: false,
			success: function(data){     
					  $(modelName).html("");					
				      $(modelName).html(data);
		 	        }             
		 });
	     $(modelName).dialog("open");
	     $(".ui-dialog").css({"top":"800px"}); 
	     $(".ui-dialog-titlebar").addClass("hd");
}

function fnUploadPOAttach(){
	var userName = $('#userName').val();
	var fsrNum = $('#fsr').val();
	var serNum = $('#serialNumber').val();
	var url = "canonE307FileUploadToTemp.jsp?source=summary&userName="+encodeURIComponent(userName)+"&fsrNum="+fsrNum+"&serial="+serNum;
	window.open(url,'newWin','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=450,height=250');
}
function uploadItemImage(fileName){
	$('#fileNm').val(fileName);
//	$('#verifiedPO').attr('checked', true);
//	$(".verifiedPO").prop('checked', true);
	$('#verifiedPO').prop('checked', true);
    var userName = $('#userName').val();
	var fsrNum = $('#fsr').val();
	var serNum = $('#serialNumber').val();
	if(fileName!=''){
		var url = "canonE307AttachmentUpload.jsp";
		var qryStr="source=summary&userName="+encodeURIComponent(userName)+"&fsrNum="+fsrNum+"&serial="+serNum+"&fileName="+encodeURIComponent(fileName);
		
		$.ajax({
			url: url,
			data: qryStr,
			type:"POST",
			cache: false,
			success : function(result)
			{
				var retVal = $.trim(result.split("|")[0]);

				if($.trim(retVal)=='E'){
					$("#eMsg").css({"color": "red", "font-size": "15"});
					$('#eMsg').html ($.trim(result.split("|")[1]));
					$('#fileNm').val('');
					$('#errorWidget').show();
					scrollToPgTop($("#errorWidget"));
				}else{
					$('#eMsg').html("PO is uploaded Successsfully..");
					$("#eMsg").css({"color": "green", "font-size": "15"}); 
					$('#errorWidget').show();
					fnUpdateSummary();
				}
			}
		});	
	} 
}
function getCurTime(){
	var d = new Date(); 
	var hor =d.getHours(); 
	var min = d.getMinutes();
	var _time = (hor > 12) ? (hor-12 +' PM') : (hor +' AM');
	var dataTm = _time.split(' ');
	var _hr = (dataTm[0]<10)?('0'+dataTm[0]):(dataTm[0]);
    $('#ftrSrvHr'+' option[value="' + $.trim(_hr) + '"]').prop('selected', true);	
    $('#ftrAmPm'+' option[value="' + $.trim(dataTm[1]) + '"]').prop('selected', true);
    $('#ftrSrvMn' + ' option[value="' + min + '"]').prop('selected', true);
    //getSysDate();
}
function fnEscalate(){
	var url = '<%=ctxPath%>/e193/jsp/canon_E193_csEIHome.jsp';
	window.open(url,"Customer Care","toolbar=no, menubar=no,location=no,directories=no,status=yes, scrollbars=yes,resizable=yes,target=_blank");
	window.moveTo(0,0);
	window.resizeTo(screen.width,screen.height-100);
}
function fnViewEscalations(){
	var serNum = $('#serialNumber').val();
	var url = '<%=ctxPath%>/e193/jsp/canon_E193_csTicketHistoryController.jsp?searchType=serialNo&searchValue='+serNum;
	window.open(url,"Ticket History","toolbar=no, menubar=no,location=no,directories=no,status=yes, scrollbars=yes,resizable=yes,target=_blank");
	window.moveTo(0,0);
	window.resizeTo(screen.width,screen.height-100);
}

function fnCheckUpdates(){
	<%
	 if("Y".equals(servObj.getStrFsrUpdFlag())){
	%>
		var servReqNum = $('#fsr').val();
		var taskNum = $('#tskNmbr').val();
		var cntryCd = $('#cntryCd').val();
		var postalCd = $('#postalCd').val();
		var tskSts = $('#tskSts').val();
		
		if(servReqNum.length != 0){
			var urlDetail = 'canonE307GetSrInfo.jsp';
			var qryStr= "servReqNum="+servReqNum+"&taskNum="+taskNum;
				qryStr = qryStr+"&cntryCd="+cntryCd+"&postalCd="+postalCd;
			var scheduleDate;
		    $.ajax({
					url: urlDetail,
					cache: false,
					data : qryStr,
					async: false,
					type : "POST",			
					success: function(data){
						result = $.trim(data);
						if(result!='null' && result!=''){
							 var tskTpe = $.trim(result.split("|")[0]);
							 if(tskTpe!= tskSts && tskTpe!='' && tskTpe!='null'){
								var taskVal =  taskNum +" - "+ $.trim(result.split("|")[0]);

								$('#taskNumber').val(taskVal);
								$('#tskSts').val($.trim(result.split("|")[0]));
								$('#assignee').val($.trim(result.split("|")[1]));
							//	$('#erlyStrt').val($.trim(result.split("|")[2]));
							//	$('#lateStrt').val($.trim(result.split("|")[3]));
								$('#svcFrom').val($.trim(result.split("|")[2]));
								$('#svcTo').val($.trim(result.split("|")[3]));
								$('#estmtArrival').val($.trim(result.split("|")[6]));
								if($.trim(result.split("|")[0])!='Completed'){
									setTimeout("fnCheckUpdates()",10000);
								}
								// chckFlg="Y";	
							 } else{
								 setTimeout("fnCheckUpdates()",10000);
							 } 
						}
					}
				});		
		}
		if($('#tskSts').val()=='Completed' || $('#tskSts').val()=='Cancelled' || $('#tskSts').val()=='Close'){
			$('#estArvl').html('Actual Arrival');
		}
<%
	}
%>
}
function fnCheckTaskUpdates(){
	<%
	 if("Y".equals(servObj.getStrFsrUpdFlag())){
	%>
		var servReqNum = $('#fsr').val();
		var lastUpdateDate = $('#lastUpdateDt0').val();
		var urlDetail = 'canonE307GetTaskDtlInfo.jsp';
		var qryStr= "servReqNum="+servReqNum+"&lastUpdateDate="+lastUpdateDate;
		var selFlg = "";
	    $.ajax({
			url: urlDetail,
			cache: false,
			data : qryStr,
			async: false,
			type : "POST",	
			dataType: "json",
			success: function(data){
				//result = $.trim(data);
				//console.log("data: "+ data);
				var jd =	JSON.parse(JSON.stringify(data));
				var jdTsk=jd["data"];
				//console.log("jdTsk: "+ jdTsk);
				 for(t in jdTsk ){
					 var objTask=jdTsk[t];
					// var eleTr= $(  $("#taskDiv tr").find("[data-tasknum='" + objTask["strSvcTskNum"] + "']")[0]); 
					var eleTr=$("#taskDiv tr[data-tasknum='" + objTask["strSvcTskNum"] +"']");
					 var idx= parseInt(eleTr.data("taskidx"));
					
					 var strTskStsCd=  $.trim(objTask["strTskStsCd"]);
					 var strTskSTsNm=  $.trim(objTask["strTskSTsNm"]);
					 var strSvcTskNum=  $.trim(objTask["strSvcTskNum"]);
					 var selTaskStatDet="#taskStatDet"+idx;
					 
					 var bOptExists = ($(selTaskStatDet+":has(option[value='"+strTskStsCd+"'])").length > 0)?true:false;
					 if(!bOptExists){
						 $(selTaskStatDet).append($('<option>', {
							    value: strTskStsCd,
							    text: strTskSTsNm
							}));
					 }
					 $(selTaskStatDet+' option[value="'+strTskStsCd+'"]').prop('selected', true); 
					 
					$('#tskStsCd'+idx).val($.trim(objTask["strTskStsCd"]));
					//$('#taskStatDet'+idx).val($.trim(objTask["strTskStsCd"]));
					$('#taskStat'+idx).html($.trim(objTask["strTskSTsNm"]));
					$('#assigneeDet'+idx).val($.trim(objTask["strAssignee"]));
					$('#assignTypeDet'+idx).val($.trim(objTask["strAssigneeTpCd"]));
				//	$('#assTpCd'+idx).val($.trim(objTask["strAssigneeTpCd"]));
					
					$('#assignee'+idx).html($.trim(objTask["strAssignee"])); 
					$('#assDetCode'+idx).val($.trim(objTask["strAssigneeCd"])); 
					$('#lastUpdateDt0').val($.trim(objTask["strLastUpdateDate"])); 

                  	}

			},
			error : function(error) {
				<%
					System.out.println("Error while parsing task details in javascript: ");
				 %> 
			}
		});	
	    setTimeout("fnCheckTaskUpdates()",10000);
	<%
	}
	%>
}
function fnRefresh(){
	 var fsr = $('#fsr').val();
	 var url="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?fsr="+fsr;
	<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
		url="<%="https://"+ request.getServerName()%>"+url;
	<% } %>
	 $("#frmSummary").attr("action",url);	 
	 $('#frmSummary').submit();	
	 // setTimeout("fnCheckUpdates()",10000);
}

function fnDownAttach(fileName){
	var url="canonE307DownloadAttachment.jsp?fileName="+encodeURIComponent(fileName);
    var l_newWindow = window.open(url);
    l_newWindow.focus(); 
	//$("#frmSummary").attr("action",url);	 
	//$('#frmSummary').submit();	
}
function fnDownAttachReport(fileId, fileName){
	var userName = $('#userName').val();
	var url="canonE307AttachmentDownloadReport.jsp?fileId="+encodeURIComponent(fileId)+"&userName="+userName+"&fileName="+encodeURIComponent(fileName);
	var l_newWindow = window.open(url);
	l_newWindow.focus(); 

	}
function autoTab(current,next){
	if (current.getAttribute&&current.value.length==current.getAttribute("maxlength"))
		next.focus();
}
$(".numeric").keypress(function (e) {
	if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
     	return false;
    }
});
$('#sNoteTypeTxt').keyup(function() {
    var len = this.value.length;
    if (len >= 500) {
        this.value = this.value.substring(0, 500);
    }
});
function checkEmail(emailAddress) {
	  var regExp = /(^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)/i;
	  return regExp.test(emailAddress);
}
<% if(authBeanObj!=null){ %>
    var _customerInfo={
    	"customerName":"<%=escape(authBeanObj.getStrCustNm())%>",
    	"customerAddress1":"<%=escape(authBeanObj.getStrAddress1())%>",
    	"customerAddress2":"<%=escape(authBeanObj.getStrAddress2())%>",
    	"customerCity":"<%=escape(authBeanObj.getStrCity())%>",
    	"customerState":"<%=escape(authBeanObj.getStrState())%>",
    	"customerZIP":"<%=escape(authBeanObj.getStrPostlCd())%>",
    	"customerEmail":"<%=escape(authBeanObj.getStrCustEmail())%>",
    	"customerPhone":"<%=escape(authBeanObj.getStrPhoneNum())%>",
    	"customerCountryCode":"<%=escape(authBeanObj.getStrCountry())%>"
    	};
    	
 	<% }else { %>
 		var _customerInfo={};
 	<% } %>
 	
function enterCC(){
	setCCTitle();
	$("#holderName").val("");
	$("#ccNum").val("");
	$('#cardType').val("");
	$('#cardExpr').val("");
	$("#profileId").val("");
	
		$.creditCard("creditCardRequestS21CSA_ASCC.jsp",$.extend({
				action:"open"
				},_customerInfo)
			).done(function(data){
		revertCCTitle();
		chngCRLbl();
		$('#cardType').val(data.cardBrand);
		$('#cardExpr').val(data.ccExp);
		$("#profileId").val(data.customerRefNum);
		if(data.customerName){
			$("#holderName").val(data.customerName);
		}
		if(data.ccAccountNum){
			$("#ccNum").val(data.ccAccountNum);
		} 
		$("#authAmnt").val("500");
		});
	}
	function chngCRLbl(){
		var crBtn = $('#crBtn').text();
 		if($.trim(crBtn) == 'Enter Credit Card'){
 			$('#crBtn').text('Reauthorize');
 		} 		
 	}	
	function fnCancelTask(){
		var recSz = $('#recSz').val();
		$('#tskMsgDiv').html('');
		var validChk ="";
		var vldRsn =true;
		for(i=0;i<recSz;i++){
			var chkChekd=	$('#cnclFlg'+i).is(':checked'); 
			//console.log("chkChekd: "+chkChekd);
			if(chkChekd){
				validChk=true;
				var cnclRsn = $('#cnclRsn'+i).val();
				var cnclNote = $('#cancelNt'+i).val();
				//console.log("cnclRsn: "+cnclRsn);
				if(cnclRsn == '-1'){
					$('#tskMsgDiv').html("Please select Cancel Reason for selected task");
					$('#tskMsgDiv').css({"color": "red", "font-size": "12"});
					vldRsn=false;
				}
				if(cnclNote =='null' || cnclNote ==''){
					$('#tskMsgDiv').html("Please Enter Cancel Note for selected task");
					$('#tskMsgDiv').css({"color": "red", "font-size": "12"});
					vldRsn=false;
				}
			}
		}
		if(validChk && vldRsn){
			var url = 'canonE307ServReqCancelRsn.jsp?action=cancel';
			showPleaseWait();
			$.post(url
	            ,$("#cnclRsnFrm").serialize()
	            ,function(data){
				hidePleaseWait();
				 $('#cnclTaskDiv').html("");
	             $('#cnclTaskDiv').html(data);
	            });		
		}else if(!validChk){
			$('#tskMsgDiv').html("Please select atleast one task to cancel");
			$('#tskMsgDiv').css({"color": "red", "font-size": "12"});
		}
	}
	function fnGetTaskLstUpdDt(){
		var servReqNum = $('#fsr').val();
		var lastUpdateDate = $('#lastUpdateDt0').val();
		var tskUpdFlg = false;	
		if(servReqNum.length != 0){
			var urlDetail = 'canonE307GetTaskLastUpdateDate.jsp';
			var qryStr= "servReqNum="+servReqNum+"&lastUpdateDate="+lastUpdateDate;

		    $.ajax({
					url: urlDetail,
					cache: false,
					data : qryStr,
					async: false,
					type : "POST",			
					success: function(data){
						result = $.trim(data);
						if(result!='null' && result!=''){
							if($.trim(result) =='Y'){
								tskUpdFlg = true;
							}

						}
					}
				});		
		}
		return tskUpdFlg;
	}
	function fnCrctnMeterReads(tskStsUpdFlg, iVal){
		   // var modelName ="#servInfoDiv";
		   var taskNumber = $('#tskNmbr').val();
		    var modelName = "#mtrrCrctnDiv";
		    var fsr = $('#fsr').val();
		    var userName = $('#userName').val();
		    var svcMachMstrPk = $('#svcMachMstrPk').val();
		    showPleaseWait();
		 	   $(modelName).dialog({
							height: 600,
							title: "Correction Meter Reads", 
							modal: true ,
							zIndex:1005,
							width: 800,		
			                resizable: false     
						});
			       $.ajax({
					url: "canonE307SRCorctnMeterReads.jsp?fsr="+fsr+"&crctnTaskNumber="+taskNumber+"&svcMachMstrPk="+svcMachMstrPk+"&userName="+userName+"&tskStsUpdFlg="+tskStsUpdFlg+"&iVal="+iVal,
					type:"GET",
			        cache: false,
					success: function(data){     
							  hidePleaseWait();     
							  $(modelName).html("");					
						      $(modelName).html(data);
						      // setRowStyles(".lovTbl");
						     //  $("#a1").css({"color":"white","background-color":"#A10304"});
				 	        }             
				 });
				  
			     $( modelName ).dialog("open");
			 //    $(".ui-dialog").css({"top":"500px"});
			     $(".ui-dialog-titlebar").addClass("hd");
		}	
	function fnCheckTskSts(iVal){
		$("#eMsg").html("");
		$("#errorWidget").hide();
		var taskSts =  $('#taskStatDet'+iVal).val();
		var strTskStsCd = $('#tskStsCd'+iVal).val();
		if(taskSts == '45' && strTskStsCd!='45'){
			showPleaseWait();
			var svcTaskNum = $('#tskNum'+iVal).val();
			var urlDetail = 'canonE307GetTaskInMeterReads.jsp';
			var qryStr= "svcTaskNum="+svcTaskNum;

		    $.ajax({
					url: urlDetail,
					cache: false,
					data : qryStr,
					async: false,
					type : "POST",			
					success: function(data){
						result = $.trim(data);
						 hidePleaseWait(); 
						if(result!='null' && result!=''){
							if($.trim(result) =='Y'){
								$("#mtrSubmitFlg"+iVal).val('Y'); 
							}else{
								fnCrctnMeterReads("Y", iVal);
							}

						}
					}
				});		
		}
	}
	
	function fnCheckTskSts1(iVal){
		$("#eMsg").html("");
		$("#errorWidget").hide();
		var taskSts =  $('#taskStatDet'+iVal).val();
		var strTskStsCd = $('#tskStsCd'+iVal).val();
		if(taskSts == '45' && strTskStsCd!='45'){
			showPleaseWait();
			var svcTaskNum = $('#tskNum'+iVal).val();
			var urlDetail = 'canonE307GetTaskInMeterReads.jsp';
			var qryStr= "svcTaskNum="+svcTaskNum;

		    $.ajax({
					url: urlDetail,
					cache: false,
					data : qryStr,
					async: false,
					type : "POST",			
					success: function(data){
						result = $.trim(data);
						 hidePleaseWait(); 
						if(result!='null' && result!=''){
							if($.trim(result) =='Y'){
								//DO Nothing 
							}else{
								$("#eMsg").html("Please enter 'In Reads' for this task, before you change task status to 'Arrived' ");
						  		$("#errorWidget").show();
						  		var selTaskStatDet="#taskStatDet"+iVal;
						  		$(selTaskStatDet+' option[value="'+strTskStsCd+'"]').prop('selected', true); 
						  		 scrollToPgTop($("#errorWidget"));
							}

						}
					}
				});		
		}
	}
	function fnCheckTskStsBeforeUpdate(){
		var tskStsUpdFlg = false;	
		 var tskSze = $('#tskSize').val();
		 tskSze = parseInt(tskSze);
		 if(tskSze>0){
			 for(i=0;i<tskSze;i++){
				 var taskSts = $('#taskStatDet'+i).val();
				 var strTskStsCd = $('#tskStsCd'+i).val();
					if(taskSts == '45' && strTskStsCd=='40'){
						var svcTaskNum = $('#tskNum'+i).val();
						var mtrSubmitFlg = $('#mtrSubmitFlg'+i).val();
						if(mtrSubmitFlg=='Y'){
							//DO Nothing
						}else{
							fnCrctnMeterReads("Y", i);
							tskStsUpdFlg=true;
						}
					}
			 }
		 }
		 return tskStsUpdFlg;
	}
	function fnAuditInfo(){
		var fsr = $('#fsr').val();
		var modelName = "#srAuditLogDiv";
	    showPleaseWait();
	 	   $(modelName).dialog({
						height: 500,
						title: "Audit Log", 
						modal: true ,
						zIndex:1005,
						width: 800,		
		                resizable: false     
					});
		       $.ajax({
				url: "canonE307SRAuditLog.jsp?fsr="+fsr,
				type:"GET",
		        cache: false,
				success: function(data){     
						  hidePleaseWait();     
						  $(modelName).html("");					
					      $(modelName).html(data);
			 	        }             
			 });
			  
		     $( modelName ).dialog("open");
		 //    $(".ui-dialog").css({"top":"500px"});
		     $(".ui-dialog-titlebar").addClass("hd");
		     $("button.ui-dialog-titlebar-close").remove();
	}	
</script>
	<div id="dlg"></div>
	<div id="fileDlg"></div>
	<div id="servInfoDiv"></div>
	<div id="techDiv"></div>	
	<div id="servAddTaskDiv"></div>	
	<div id="cnclTaskDiv"></div>
	<div id="vendInfoDiv"></div>
	<div id="mtrrCrctnDiv"></div>
	<div id="srAuditLogDiv"></div>

	<div id="divEnterCC"></div>	
	</body>
</html>