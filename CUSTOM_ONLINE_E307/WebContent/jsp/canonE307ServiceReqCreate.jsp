<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil"%>
<%@page import="com.canon.apps.servreq.util.CanonE307FileUploadDownloadAPIUtil"%>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqCallAvdAPIUtil"%>
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqAPIUtil"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307BillToCustCreditAuthBean"%>
<%@page import="com.canon.apps.servreq.beans.*"%>
<%@page import="canon.apps.common.CanonConstants"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceReqCreateDao"%>
<%@ page import="com.canon.apps.*" %>  
<%@ page import="java.text.*"%> 
<%@ page import="java.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="parts.dbcommon.EZDDBCICarrier"%> 
<%@page import="canon.apps.common.CanonCustomProfile"%>
<%!
	public String escape(String s){
		if(s!=null) return s.replace("\"","\\\"");
		return "";
	}
%>
 
 
<%   
String pageTitle="Create";
String pageHdr="Advanced Service Call Center";
  

 ArrayList<String> menuList = new ArrayList<String>();
 menuList.add("MENU2:N:View Escalations:N");
 menuList.add("MENU3:N:Escalate:escalate();"); 
 menuList.add("MENU4:N:View Entitlement:viewEntitlement();");
 menuList.add("sp:btn:Scratch Pad:opnSCPad();");
 
%>  
<%@include file="canonE307ServReqHeader.jsp"%>
<link rel="stylesheet" href="<%=ctxPath%>/e307/css/canonE307ServiceReqCreate.css" type="text/css"> 
<script src='<%=ctxPath%>/e307/js/canonE307ServiceReqCreate.js?v=21' type='text/javascript'></script>

<div id="main_content">  
<div id="page_top">
	<h1>Advanced Service Call Center</h1>
</div>			
<form id="csForm" name="csForm" method="post" action="<%=ctxPath%>/e307/jsp/canonE307ServiceReqCreate.jsp">

<%

 CanonE307ServiceReqCreateDao crDao = new CanonE307ServiceReqCreateDao();
 CanonE307ServiceReqSumryAPIUtil apiUtil = new CanonE307ServiceReqSumryAPIUtil();
 String eolCallTp = util.checkNull(request.getParameter("callTp"));
 String serialNumber  = util.checkNull(request.getParameter("serialNumber"));
 String model  = util.checkNull(request.getParameter("model"));
 String machPk  = util.checkNull(request.getParameter("machPk"));
 String selDate  = util.checkNull(request.getParameter("selDate"));
 String strSrvcBlFlg = util.checkNull(request.getParameter("blFlg")); // Contract is billable based on model
 CanonE307BillToCustCreditAuthBean authBeanObj = (CanonE307BillToCustCreditAuthBean)new CanonE307ServiceReqCreateDao().getCustInfo(util.checkNull(serialNumber), util.checkNull(machPk));
 //System.out.println("machPk : " + machPk +" selDate: "+selDate);
   
 System.out.println("eolCallTp: "+ eolCallTp);
// for copy FSR 
 String fsrNumber  = util.checkNull(request.getParameter("fsrNumber"));
 boolean copyFsr=false;
 if(fsrNumber.trim().length()>0)
	 copyFsr=true;
  String crSRFlg =  util.checkNull(request.getParameter("crSRFlg"));
  String billCdFlg = util.checkNull(request.getParameter("billCdFlg"));
  System.out.println("billCdFlg: "+ billCdFlg);
  String errorMsg="";
  CanonE307ServiceReqAPIUtil apiUti= new CanonE307ServiceReqAPIUtil();
  String rFlg="";
  String urlDetail="";
  String fsrNum = "";
    
  if(crSRFlg.equalsIgnoreCase("Y")){  //create SR
	 //com.canon.apps.util.CanonUtil.printRequestParams(request);
	
	  String[] resArr =  apiUti.createServicerequest(request); //UC
	 // String[] resArr = {"Y","11111"};
	  rFlg= util.checkNull(resArr[0]);
	  if(rFlg.equalsIgnoreCase("Y")){
		  String strFileName = request.getParameter("poUpld")==null?"":request.getParameter("poUpld");
		  fsrNum =resArr[1]; 
		  int fileId = 0;
		  
			if(fsrNum!=null && fsrNum!=""){
			/*	if(!("".equals(strFileName)) && !("null".equals(strFileName))){
					String  strSerNum = request.getParameter("serialNumber");
					String strUserId = request.getParameter("userId");
					System.out.println("strSerNum : " + strSerNum+" strUserId : "+strUserId+" fsrNum : "+fsrNum+" strFileName: "+strFileName);
					fileId = objInvDAO.uploadAttachment(fsrNum, strSerNum, strUserId, strFileName);
					//System.out.println("fileId : " + fileId);
				}*/
				urlDetail = ctxPath+"/e307/jsp/canonE307ServiceRequestSummary.jsp?fsr="+fsrNum+"&crSRFlg="+crSRFlg+"&showVend=Y";
				if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { 
					urlDetail="https://"+ request.getServerName() + urlDetail;
			 	}
		 		response.sendRedirect(urlDetail);
		 	/*	System.out.println("urlDetail: "+ urlDetail);
		 		response.setContentType("text/html;charset=UTF-8");
		 		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(urlDetail);

		 		dispatcher.forward(request, response); */
			}
      }else{
		  errorMsg=resArr[1];
	  }

  }else if(crSRFlg.equalsIgnoreCase("R") || crSRFlg.equalsIgnoreCase("DT") ||crSRFlg.equalsIgnoreCase("NMT")){ // call resolved
	  CanonE307ServiceReqCallAvdAPIUtil clAvdApiUtil = new CanonE307ServiceReqCallAvdAPIUtil();
       String[] avdResArr = clAvdApiUtil.createCallAvdSR(request);
       rFlg= util.checkNull(avdResArr[0]);
  	  if(rFlg.equalsIgnoreCase("Y")){
  		  fsrNum =avdResArr[1]; 
		  urlDetail = ctxPath+"/e307/jsp/canonE307ServiceRequestSummary.jsp?fsr="+fsrNum+"&crSRFlg=Y&showVend=Y";
			if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { 
				urlDetail="https://"+ request.getServerName() + urlDetail;
		 	} 		  
  		  response.sendRedirect(urlDetail);
        }else{
  		  errorMsg=avdResArr[1];
  	  }
  }
  String callTpCd="";

 // API call for ahs, charge, billcode check   -  returns type  String[]
String modeCode="0"; 
ArrayList<CanonE307ServiceReqCallAvdRec> alCallAvd = new ArrayList();
CanonE307MachineCustSearchResultsRec csBean = new CanonE307MachineCustSearchResultsRec();
String ahsFlg ="";
String ahsEnblFlg="";
String  ahsTitle="";
String  ahsMsg="";
String checkAHS="";
String[] arrAch = {"","",""};//{"","",""}; //{"1 - Charge for Parts and Labor","Y","$100"};
//if(!copyFsr)
if(("".equals(fsrNum)) || ("null".equals(fsrNum)) || (fsrNum == null)){
	if("DE_INSTALL".equals(eolCallTp)){
		callTpCd = "F";
	} 
	String crossSrvcFlg = crDao.getCrossSrvcFlg(serialNumber, machPk);
	if("Y".equals(crossSrvcFlg)){
		 	callTpCd = "A";
	} 
arrAch = apiUti.getBillCode(machPk, selDate, modeCode, serialNumber, model, callTpCd, loginUser, billCdFlg);  //UC		 
 String BILL_CODE =arrAch[0];

 ahsFlg= util.checkNull(arrAch[1]).trim();
 ahsEnblFlg = util.checkNull(arrAch[10]).trim();

 String dsContrPk = arrAch[9];
 String lbrMsg ="";
 String prtMsg = "";
 /* String bllblFlg = crDao.getBllblFlg(BILL_CODE);
if(ahsFlg.equalsIgnoreCase("Y")){ 
	 if(bllblFlg.equalsIgnoreCase("N")){
		 ahsTitle="AFTER HOURS ENITITLED CALL";
		 ahsMsg="This Machine is Under Contract for After Hours Service <br> Midnight-Midnight Mon-Sun ";
		 checkAHS="Y";
	 }else if(bllblFlg.equalsIgnoreCase("Y")){
		 ahsTitle ="AFTER HOURS CHARGEABLE CALL";
		 ahsMsg="This Machine is Eligible for After Hours Service at Chargeable Rate of $"+arrAch[2]+" Per Hour";
		 checkAHS="Y";
	 }
}else if(bllblFlg.equalsIgnoreCase("Y")){
	ahsMsg="This SR is Chargeable at a rate of $"+arrAch[2];
}*/
 String oFsr = "";
// if(!copyFsr)			  
	oFsr=	 crDao.getOpenSR(serialNumber, machPk);
 
 List lNoteType = new ArrayList();
 ArrayList<String> alProb =  crDao.getProbDetails("problemType", model, "", ""); 
 alCallAvd = crDao.getCallAvoidanceDtls();
 
 Object[] obj = null;
 if(!copyFsr)			  
	 obj = crDao.getCallDetails(serialNumber, model, dsContrPk);
 else
	 obj = crDao.getCallDetailsByFsr(fsrNumber, dsContrPk);
 
 
 if(obj[2] !=null) {
     csBean =		 (CanonE307MachineCustSearchResultsRec)obj[2] ;
 }   
 
 //Jam Codes
String ec0=""; String ec1="";String ec2="";String ec3="";
List<CanonE307ServiceReqErrorCodesRec> alEc =new ArrayList<CanonE307ServiceReqErrorCodesRec>();
if(obj[3] !=null) {
	alEc =(ArrayList<CanonE307ServiceReqErrorCodesRec>) obj[3];
}
CanonE307ServiceReqProbRec probBean = new CanonE307ServiceReqProbRec();
if(copyFsr)
	if(obj[4]!=null){
		probBean = (CanonE307ServiceReqProbRec)obj[4];
	}

CanonE307ServiceReqCallInfoRec ciBean= new CanonE307ServiceReqCallInfoRec();
if(obj[5] !=null) {
	ciBean =(CanonE307ServiceReqCallInfoRec) obj[5]; 
}	 

//Contract Info
CanonE307ServiceReqContractRec ctrBean = new CanonE307ServiceReqContractRec();
if(obj[6] !=null) {
	ctrBean =(CanonE307ServiceReqContractRec) obj[6]; 
 }	 
String machDwnFlg = "Y";
String slaRespTime="";
if(util.checkNull(csBean.getSvcMachMstrPk())!="" &&  util.checkNull(csBean.getSvcMachMstrPk()).length()>0){
	slaRespTime = crDao.getRspTmMnAot(util.checkNull(csBean.getSvcMachMstrPk()), machDwnFlg,util.checkNull(csBean.getModel()));
}
//Current Location
CanonE307ServiceReqLocRec cLocBean = new CanonE307ServiceReqLocRec();
if(obj[7] !=null) {
	cLocBean =(CanonE307ServiceReqLocRec) obj[7]; 
 }	 

//Bill To Location
CanonE307ServiceReqLocRec bLocBean = new CanonE307ServiceReqLocRec();
if(obj[8] !=null) {
	bLocBean =(CanonE307ServiceReqLocRec) obj[8]; 
}	

// notes
 if(obj[9] !=null) {
   lNoteType =(ArrayList) obj[9]; 
 }
 
 String poReqFlg = apiUti.getPOReqFlg(bLocBean.getCustCode());
 
 System.out.println("Call Creation BILL_CODE  : " + BILL_CODE+" Bill Code 3: "+ciBean.getBillCode());
 if(util.checkNull(ciBean.getBillCode())!="" && util.checkNull(ciBean.getBillCode()).length()>0){
	 	BILL_CODE = ciBean.getBillCode();
 }
 String bllblFlg = crDao.getBllblFlg(BILL_CODE);
 DecimalFormat df = new DecimalFormat("####0.00");	
 
if("Y".equals(ahsFlg) && "Y".equals(ahsEnblFlg)){ 
	 if(bllblFlg.equalsIgnoreCase("N")){
		 ahsTitle="After Hours Entitled Call";
		 ahsMsg="This Machine is Under Contract for After Hours Service <br> "+util.checkNull(ciBean.getAttribute5()) ;
		 checkAHS="Y";
	 }else if(bllblFlg.equalsIgnoreCase("Y")){
		 ahsTitle ="After Hours Chargeable Call";
			 if("X3".equals(util.checkNull(ciBean.getTaskTypeCode())) && "ESS".equals(util.checkNull(ciBean.getLineOfBusiness()))) {
				 ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour. Charge for Labor and Parts Cost. <br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
			 }else if("X3".equals(util.checkNull(ciBean.getTaskTypeCode()))){
				 ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour.";
					
				 	if(util.checkNull(arrAch[4]).length()>0)
				 		ahsMsg = ahsMsg+ " Charge for Labor, plus a Flat Travel charge not to exceed $"+ util.checkNull(arrAch[4])+" will be added to the cost of service and Parts Cost.";
				 		//ahsMsg = ahsMsg+ " Charge for Labor plus Travel charge of $"+ util.checkNull(arrAch[4])+" and Parts Cost. ";
				 					
				 		ahsMsg = ahsMsg+ "<br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
			 }else{
				 if("3".equals(BILL_CODE)){
						ahsMsg= "Parts being Chargeable.";
				 }else{
					 if("ESS".equals(util.checkNull(ciBean.getLineOfBusiness()))){
						 ahsMsg="The Chargeable labor rate for this call is Estimated at $"+util.checkNull(arrAch[2])+" per hour hours.";
					 }else{
						 ahsMsg = "The Chargeable labor rate for this call is Estimated at $"+util.checkNull(arrAch[2])+" per hour with a Minimum of "+util.checkNull(arrAch[7])+" hours";
						 if(arrAch[4]!=null && util.checkNull(arrAch[4]).length()>0)
							    ahsMsg = ahsMsg+ " and a Flat Travel charge not to exceed $"+ util.checkNull(arrAch[4])+" will be added to the cost of service.";
						 }					 
				 	}
			 	}
			 }
		 checkAHS="Y";
}else if("3".equals(util.checkNull(ciBean.getTaskTypeCode())) && "ESS".equals(util.checkNull(ciBean.getLineOfBusiness())) && "Y".equalsIgnoreCase(bllblFlg)){
	ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour. Charge for Labor and Parts Cost. <br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
			
}else if("3".equals(util.checkNull(ciBean.getTaskTypeCode())) && "Y".equalsIgnoreCase(bllblFlg)){
	ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour.";
	
 	if(util.checkNull(arrAch[4]).length()>0)
 		ahsMsg = ahsMsg+ " Charge for Labor, plus a Flat Travel charge not to exceed $"+ util.checkNull(arrAch[4])+" will be added to the cost of service and Parts Cost.";
 		//ahsMsg = ahsMsg+ " Charge for Labor plus Travel charge of $"+ util.checkNull(arrAch[4])+" and Parts Cost. ";
 					
 		ahsMsg = ahsMsg+ "<br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
 				
}else if("Y".equalsIgnoreCase(bllblFlg)){
	if("3".equals(BILL_CODE)){
		ahsMsg= "Parts being Chargeable.";
	}else{
		 if("ESS".equals(util.checkNull(ciBean.getLineOfBusiness()))){
				ahsMsg="This SR is Chargeable at a rate of $"+arrAch[2];
		 }else{
			 ahsMsg = "The Chargeable labor rate for this call is Estimated at $"+util.checkNull(arrAch[2])+" per hour with a Minimum of "+util.checkNull(arrAch[7])+" hours ";
			 if(arrAch[4]!=null && util.checkNull(arrAch[4]).length()>0)
				    ahsMsg = ahsMsg+ "and a Flat Travel charge not to exceed $"+ util.checkNull(arrAch[4])+" will be added to the cost of service.";
	 	}
	}
}
 
 if(crSRFlg.equalsIgnoreCase("Y") ||crSRFlg.equalsIgnoreCase("R") || crSRFlg.equalsIgnoreCase("DT") ||crSRFlg.equalsIgnoreCase("NMT") && "E".equals(rFlg)){
	 csBean.setEmailAddress(request.getParameter("emailAddr"));
	 csBean.setContact(request.getParameter("contactName"));	
	 csBean.setCustTelNumber(request.getParameter("custTelNum"));
	 csBean.setCustTelExtn(request.getParameter("custTelExtnNum"));
	 csBean.setSerSpecialMsg(request.getParameter("splMsg"));
	 csBean.setCaller(request.getParameter("caller"));
	 csBean.setCallrTelNumber(request.getParameter("cllrPhNum"));
	 csBean.setCallrTelExtn(request.getParameter("cllrPhNumExt"));
	 csBean.setMobileNum(request.getParameter("mobileNum"));
	 
	 ciBean.setCustomerPoNum(request.getParameter("custPo"));
	 
 }

	java.util.Date sysDate = new java.util.Date();
	//String ftrDate = new SimpleDateFormat("MMM dd yyyy").format(sysDate).toString();
	System.out.println("Call Creation Postal Code : "+ csBean.getShipToPostalCd()+" Country Code: "+csBean.getCountryCd());
	String tmZone = apiUtil.getTmZone(csBean.getShipToPostalCd(), csBean.getCountryCd(),"");
 	System.out.println("Call Creation tmZone : "+ tmZone);
 	
 	//String ftrDt = new SimpleDateFormat("yyyyMMddHHmmss").format(sysDate).toString();
 	
 	
 	String futrDt = "";
 	String ftrDate="";
 	String strFtrHr ="";
 	String strFtrMin="";
 	String strFtrAmPm="";
 	
 	if(csBean.getShipToPostalCd()!=null && csBean.getShipToPostalCd()!="null" && csBean.getShipToPostalCd().length()>0){
 		futrDt = apiUtil.getTmZone(csBean.getShipToPostalCd(), csBean.getCountryCd(),new SimpleDateFormat("yyyyMMddHHmmss").format(sysDate).toString());
	 	if(futrDt!=null && futrDt.length()>0){
			ftrDate = futrDt.substring(0, 11);
			strFtrHr = futrDt.substring(12,14);
			strFtrMin = futrDt.substring(15,17);
			strFtrAmPm = futrDt.substring(18,20);
	 	}
 	}

	System.out.println("strDate: "+ ftrDate+" strFtrHr: "+strFtrHr+" strFtrMin: "+strFtrMin+" strFtrAmPm: "+strFtrAmPm);


%>
<input type="hidden" name="crSRFlg" id="crSRFlg" value=""/>
<input type="hidden" name="billCdFlg" id="billCdFlg" value=""/>
<input type="hidden" name="cAcctNum" id="cAcctNum" value="<%=cLocBean.getCustCode()%>"/>
<input type="hidden" name="bAcctNum" id="bAcctNum" value="<%=cLocBean.getCustCode()%>"/>
<input type="hidden" name="cCustCode" id="cCustCode" value=""/>
<input type="hidden" name="bCustCode" id="bCustCode" value=""/>
<input type="hidden" name="callPrtyCd" id="callPrtyCd" value=""/>
<input type="hidden" name="userId" id="userId" value="<%=loginUser%>"/>
<input type="hidden" name="selDate" id="selDate" value="<%=selDate%>"/>
<input type="hidden" name="machPk" id="machPk" value="<%=machPk%>"/>
<input type="hidden" name="bllbleFlg" id="bllbleFlg" value="<%=bllblFlg%>">
<input type="hidden" name="lbrChrgFlg" id="lbrChrgFlg" value="">
<input type="hidden" name="prtChrgFlg" id="prtChrgFlg" value="">
<input type="hidden" name="cardType" id="cardType" value="">
<input type="hidden" name="cardExpr" id="cardExpr" value="">
<input type="hidden" name="ccNum" id="ccNum" value="">
<input type="hidden" name="slsDt" id="slsDt" value="<%=ciBean.getSlsDate() %>">
<input type="hidden" name="ccReqFlag" id="ccReqFlag" value="<%=ciBean.getCcReqFlag() %>">
<input type="hidden" name="corrCodeSel" id="corrCodeSel" value="<%=request.getParameter("corrCode") %>">
<input type="hidden" name="locCodeSel" id="locCodeSel" value="<%=request.getParameter("locCode") %>">
<input type="hidden" name="resCodeSel" id="resCodeSel" value="<%=request.getParameter("resCode") %>">
<input type="hidden" name="ftrSysDt" id="ftrSysDt" value="<%=futrDt%>">
<input type="hidden" name="reqPrefTech" id="reqPrefTech" value="<%=util.checkNull(csBean.getPrefTechCd()) %>">
<input type="hidden" name="origBilCd" id="origBilCd" value="<%=BILL_CODE%>">
<input type="hidden" name="lob" id="lob" value="<%=ciBean.getLineOfBusiness()%>"> 
<input type="hidden" name="pOReqFlg" id="pOReqFlg" value="<%=poReqFlg %>"> 
<input type="hidden" name="ccReqFlg" id="ccReqFlg" value="<%=ciBean.getCcReqFlag() %>">
<input type="hidden" name="calAvdFlg" id="calAvdFlg" value="<%=ciBean.getCallAvoidFlg()%>"> 
<input type="hidden" name="txRefNum" id="txRefNum" value="">
<input type="hidden" name="termCd" id="termCd" value="<%=ciBean.getAttribute1() %>">
<input type="hidden" name="termDesc" id="termDesc" value="<%=ciBean.getAttribute2() %>">
<input type="hidden" name="editRole" id="editRole" value="<%=editRole %>">
<input type="hidden" name="oFsr" id="oFsr" value="<%=util.checkNull(oFsr)%>">
<input type="hidden" name="cCustPk" id="cCustPk" value="<%=util.checkNull(cLocBean.getCustPk())%>">
<input type="hidden" name="bCustPk" id="bCustPk" value="<%=util.checkNull(bLocBean.getCustPk())%>">
<input type="hidden" name="srvcBlFlg" id="srvcBlFlg" value="<%=util.checkNull(strSrvcBlFlg)%>">
<input type="hidden" name="eolCallTp" id="eolCallTp" value="<%=eolCallTp %>">
<input type="hidden" name="chngBlRsn" id="chngBlRsn" value="<%=util.checkNull(request.getParameter("sChngReason")) %>" >
<input type="hidden" name="ccaChrgVal" id="ccaChrgVal" value="<%=util.checkNull(request.getParameter("ccaChrg")) %>" >
<input type="hidden" name="probDescSelVal" id="probDescSelVal" value="<%=util.checkNull(request.getParameter("sPbDescription"))%>" >
<input type="hidden" name="probCdSelVal" id="probCdSelVal" value="<%=util.checkNull(request.getParameter("pCode"))%>" >
<input type="hidden" name="lbrChrg" id="lbrChrg" value="<%=util.checkNull(arrAch[2])%>">
<input type="hidden" name="trvlChrg" id="trvlChrg" value="<%=util.checkNull(arrAch[4])%>">
<input type="hidden" name="postalCd" id="postalCd" value="<%=csBean.getShipToPostalCd()%>">
<input type="hidden" name="countryCd" id="countryCd" value="<%=csBean.getCountryCd()%>">



<div style="margin:0px 5px;"><h1>Call Creation</h1></div>
 <table  style="font-weight: bold;margin:0px 5px;" class="whdr" >
	<tr>
		<td style="color: red;font-size: 15px;font-weight: bold;" align="left">
		   <div id='ahsMsgDiv'><%=ahsMsg %></div>
		   <%if(oFsr.length()>0) { %>
		   	<br> Open Call already exists for Serial Number. FSR	
		   
		 	<%  
			 	String[] arr_sr = oFsr.split(",");
				for (int i = 0; i < arr_sr.length; i++) {
				%>
				<span style="text-decoration: underline;">
				 <a style="color:red;" target="_blank" href="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?showVend=Y&fsr=<%=arr_sr[i]%>"><%=arr_sr[i] %></a>
				 </span>&emsp;
				<%	
				}
				%>
				
		   <%} %>	
		</td>
		<td align="right">
		</td>
	</tr>
	<%if(csBean.getAttribute1()!=null && csBean.getAttribute1().length()>0){ %>
	<tr>
	<td style="color: red;font-size: 15px;font-weight: bold;" align="left">
	<%=util.checkNull(csBean.getAttribute1())%>
	</td>
	<td align="right">
		</td>
	</tr>
	<%} %>	
  </table>
 <table style="border:0px;margin-left:5px;width:850px;">
	<tr>
	    <td>
	       <div id="errorWidget"  style="display: none;padding-bottom: 5px;">
				<p id="eMsg"><%=errorMsg %></p>
		   </div>
	   </td>
	</tr>
</table>
  <table  class="whdr">
	<tr>
		  <td style="width: 56%;vertical-align: top;">
	           
	           <table class="whdr">
	           <tr><td class="hd" colspan="2">Machine Information</td></tr>
	           <tr class="trEmpty"><td colspan="2">&nbsp;</td></tr>
	            <tr> 
	            <td style="vertical-align:top;">
	            <table class="tbl">
					<tr>
					   <td>Serial# </td>
					   <td><input type="text" class="rdl" size="21" name="serialNumber" id="serialNumber" value="<%=serialNumber%>"/></td>
					</tr>
					<tr>
					  <td>Tag # </td>
					  <td><input type="text" class="rdl" size="21" name="tagNum" id="tagNum" value="<%=util.checkNull(csBean.getCustMachCtrlNum())%>"/></td>
					</tr>
					<tr>
					 <td>Model </td>
					 <td><input type="text" class="rdl" size="21" name="model" id="model" value="<%=model%>"/></td>
					</tr>
					<tr>
					 <td>Solution Name </td>
					 <td><input type="text" class="rdl" size="21" name="solName" id="solName" value="<%=util.checkNull(csBean.getSolutionName())%>"/></td>
					</tr>
					<tr>
			          <td nowrap="nowrap">Line Of Business</td>
			          <td><input type="text" size="3" class="requireds rdl" name="lnOfBsns" id="lnOfBsns" value="<%=util.checkNull(ciBean.getLineOfBusiness())%>" style="background-color: #33FF33;font-weight: bold;font-size: 15px !important;"/></td>
			        </tr>
			        <tr>  
			          <td>Branch</td>
			          <td>
			             <input type="text"  class="requireds rdl" name="branch" id="branch"  size="21"  value="<%=util.checkNull(ciBean.getBranch())%>"/>
			             <input type="hidden"  name="branchCd" id="branchCd" value="<%=util.checkNull(ciBean.getBranchCd())%>"/>
			           </td>
			       </tr>
					
				</table>
				
				</td>
				<td style="vertical-align:top;">
				<table class="tbl">
					<tr> 
					  <td>Customer Case# </td>
					  <td><input type="text" size="21" name="custCaseNum" id="custCaseNum" value="<%=request.getParameter("custCaseNum")==null?"":request.getParameter("custCaseNum")%>"/></td>
					</tr>
					<tr> 
					  <td>Email Address </td>
					  <td><input type="text" size="21" name="emailAddr" id="emailAddr" class="required" value="<%=util.checkNull(csBean.getEmailAddress())%>" /></td>
					</tr>
					<tr>
					  <td>Contact Name </td>
					  <td><input type="text" size="21" name="contactName" id="contactName" class="required" value="<%=util.checkNull(csBean.getContact())%>" onchange="fnUpdateCallerNm()"/></td>
					</tr>
					<tr>
					  <td>Contact # </td>
					  <td><%-- <input type="text" size="1" name="contactNum" id="contactNum" onKeyDown="tabNext(this,'down',3)" onKeyUp="tabNext(this,'up',3,this.form.contactNum2)" maxlength="3" value="<%=util.checkNull(csBean.getCustTelNum1())%>"  class="autoInteger" />
					  	  <input type="text" size="1" name="contactNum2" id="contactNum2" onKeyDown="tabNext(this,'down',3)" onKeyUp="tabNext(this,'up',3,this.form.contactNum3)" maxlength="3" value="<%=util.checkNull(csBean.getCustTelNum2())%>" class="autoInteger" />
						  <input type="text" size="1" name="contactNum3" id="contactNum3" onKeyDown="tabNext(this,'down',4)" onKeyUp="tabNext(this,'up',4,this.form.contactNumExt)" maxlength="4" value="<%=util.checkNull(csBean.getCustTelNum3())%>" class="autoInteger" />-
					      <input type="text" size="1" name="contactNumExt" id="contactNumExt" value="" maxlength="4" class="autoInteger" /> --%>
					      <input type="text"  size="11" name="custTelNum" id="custTelNum" maxlength="20" class="required" value="<%=util.checkNull(csBean.getCustTelNumber())%>" onchange="fnUpdateCalrPhn()"/>&nbsp;-&nbsp;
						  <input type="text"  size="3" name="custTelExtnNum" id="custTelExtnNum" maxlength="10" value="<%=util.checkNull(csBean.getCustTelExtn())%>" onchange="fnUpdateCalrPhn()"/>					      
					  </td>
					</tr>
					<tr>
						 <td>Mobile # </td>
						 <td><input type="text"  size="11" name="mobileNum" id="mobileNum" maxlength="20" class="required" value="<%=util.checkNull(csBean.getMobileNum())%>" />&nbsp;</td>
					</tr>
					<tr>
					  <td>Caller Name </td>
					  <td><input type="text" size="21" name="caller" id="caller" class="required" value="<%=util.checkNull(csBean.getCaller()) %>"/></td>
					</tr>
				    <tr>
				    	<!-- Changed caller phone number to 3-3-4 format -->
					   <td>Caller #</td>
					   <td>
<%-- 					<input type="text"  size="1" name="cllrPhNum" id="cllrPhNum" onKeyDown="tabNext(this,'down',3)" onKeyUp="tabNext(this,'up',3,this.form.custPhNum2)" value="<%=util.checkNull(csBean.getCustTelNum1())%>" maxlength="3" class="autoInteger" />
						<input type="text"  size="1" name="cllrPhNum2" id="cllrPhNum2" onKeyDown="tabNext(this,'down',3)" onKeyUp="tabNext(this,'up',3,this.form.custPhNum3)"  value="<%=util.checkNull(csBean.getCustTelNum2())%>" maxlength="3" class="autoInteger" />
						<input type="text"  size="1" name="cllrPhNum3" id="cllrPhNum3" onKeyDown="tabNext(this,'down',4)" onKeyUp="tabNext(this,'up',4,this.form.custPhNumExt)" maxlength="4" value="<%=util.checkNull(csBean.getCustTelNum3())%>" class="autoInteger" />- 

						<input type="text"  size="1" name="cllrPhNumExt" id="cllrPhNumExt" value="<%=util.checkNull(csBean.getCustTelExtn())%>" maxlength="4" /> --%>

						<input type="text"  size="11" name="cllrPhNum" id="cllrPhNum" class="required" maxlength="20" value="<%=util.checkNull(csBean.getCallrTelNumber()) %>"/>&nbsp;-&nbsp;
						<input type="text"  size="3" name="cllrPhNumExt" id="cllrPhNumExt" maxlength="10" value="<%=util.checkNull(csBean.getCallrTelExtn()) %>"/>
						</td>						
					</tr>
				</table>
			    </td>
			  </tr>
			  </table>	
			  <table>
			  <tr>
			   <td>Special Message&nbsp;</td>
				     
				     <td>  <textarea name="splMsg" id="splMsg" rows="5" cols="72" class="inTxt" style="height: 80px;"><%=util.checkNull(csBean.getSerSpecialMsg())%></textarea>
				        <input type="hidden" size="21" name="splMsgNtCd" id="splMsgNtCd" value="<%=util.checkNull(csBean.getSerSpecialMsgTyp())%>"/>
				    </td>
			  </tr>
			 </table>
			  
			</td>
			<td class="tdw1">
			   <div class="vLine"></div>
			</td>
			<td style="width :44%;vertical-align:top;">
			 <table class="tbl whdr" style="padding-left:30x;" id="custHndl">
					<tr><td class="hd" colspan="2">Customer Handling </td></tr>
					<tr class="trEmpty"><td colspan="2">&nbsp;</td></tr>
					<tr><td>SLA Response Time</td><td><input type="text" size="21" class="rdl" name="slaRespTime" id="slaRespTime" value="<%=slaRespTime%>"/></td></tr>
					<tr>
						<td>Service Hours</td>
						<td><input type="text" size="21" class="rdl" name="serviceHrs" id="serviceHrs" class="rdl" value="<%=util.checkNull(csBean.getBizHrsWeekdays())%>"/></td>
					</tr>
					<tr>
						<td>Future Service Date</td>
						<td>
				 		  <input type="text"   id="ftrSrvDt" name="ftrSrvDt" size="14" value="<%=ftrDate%>" class="datePicker rdl" onchange="fnCheckDt()"/> - 
				 		  <select id="ftrSrvHr" name="ftrSrvHr" onchange="fnCheckDt()">
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
				 		  <select id="ftrSrvMn" name="ftrSrvMn" onchange="fnCheckDt()">
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
				 		  <select id="ftrAmPm" name="ftrAmPm" onchange="fnCheckDt()">
				 		  <% 
				 		  		String amSelect="";
				 		  		String pmSelect="";
				 		  		if("AM".equals(strFtrAmPm)){
				 		  			amSelect="SELECTED";
				 		  		}else{
				 		  			pmSelect="SELECTED";
				 		  		}
				 		  		%>
				 		  		<option value="AM" <%=amSelect %>>AM</option>
				 		  		<option value="PM" <%=pmSelect %>>PM</option>	
				 		  </select>
				 		   <!-- -<input type="text"  class="timePicker" id="ftrSrvTm" name="ftrSrvTm" size="8" value=""/>  -->
						</td>
					</tr>
					<tr>
						<td><b>Time Zone</b></td>
						<td>
				 		  <input type="text"   id="tmZone" name="tmZone" size="18" value="<%=util.checkNull(tmZone)%>" class="rdl" style="background-color: #33FF33;font-weight: bold;font-size: 15px !important;" /> 
						</td>
					</tr>
					
			  </table>
			  <div class="hLine"></div>
			  <table class="tbl whdr" style="margin-top:10px;" id="contInfo">
					<tr><td class="hd" colspan="4">Contract  Information</td></tr>
					<tr class="trEmpty"><td colspan="2">&nbsp;</td></tr>
					<tr>
						<td>Customer#</td>
						<td><input type="text" size="15" name="custNum" id="custNum" value="<%=util.checkNull(ctrBean.getCustNum()) %>" class="rdl" /></td>
						<td>Customer Name</td>
						<td><input type="text" size="15" name="custName" id="" value="<%=util.checkNull(ctrBean.getCustName())%>" class="rdl"/></td>
					</tr>
					<tr>
					   <td>Contract#</td>
					   <td><input type="text" size="15" name="ctrNum" id="ctrNum" value="<%=util.checkNull(ctrBean.getContractNumber()) %>" class="rdl"/></td>
					   <td>Contract Type</td>
					   <td><input type="text" size="15" name="ctrType" id="ctrType" value="<%=util.checkNull(ctrBean.getContractType()) %>" class="rdl"/></td>
					</tr>
					<tr>
					  <td>Contract Status</td>
					  <td><input type="text" size="15" name="ctrHdrSts" id="ctrHdrSts" value="<%=util.checkNull(ctrBean.getHeaderStatus())%>" class="rdl"/></td>
					  <td>Line Status</td>
					  <td><input type="text" size="15" name="ctrLnSts" id="ctrLnSts" value="<%=util.checkNull(ctrBean.getLineStatus())%>" class="rdl"/></td>
				    </tr>
					<tr>
					   <td>Contract Start</td>
					   <td><input type="text" size="15" name="ctrHdrStDt" id="ctrHdrStDt" value="<%=util.checkNull(ctrBean.getHeaderStartDate())%>" class="rdl"/></td>
			           <td>Contract End</td>		 
					   <td><input type="text" size="15" name="ctrHdrEndDt" id="ctrHdrEndDt" value="<%=util.checkNull(ctrBean.getHeaderEndDate())%>" class="rdl"/></td>
					</tr>
					<tr>
					  <td>Line Start</td>
					  <td><input type="text" size="15" name="ctrLnStDt" id="ctrLnStDt" value="<%=util.checkNull( ctrBean.getLineStartDate())%>" class="rdl"/></td>
					  <td>Line End</td>
					  <td><input type="text" size="15" name="ctrLnEndDt" id="ctrLnEndDt" value="<%=util.checkNull( ctrBean.getLineEndDate())%>" class="rdl"/></td>
					</tr>
			  </table>		
			</td>
	  </tr>
	</table>
	
 <div class="hLineLg"></div>
 
  <table class="whdr">
	     <tr>
	       <td  style="vertical-align: top;width: 55%;">
		      
		       <table class="tbs whdr">
		        <tr><td colspan="2" class="hd">Customer Address</td></tr>
		        <tr class="trEmpty"><td colspan="2">&nbsp;</td></tr>
		        <tr><td class="sub_hd">Installed At:</td><td  class="sub_hd">Bill To:</td></tr>
		        <tr>
		           <td style="width:50%;">
		              <table style="width:100%;">
			              <tr>
			                <td style="width:70%;"><span id="csCustName"><%=util.checkNull(cLocBean.getCustName())%></span></td>
			                <td> 
				               <%if(util.checkNull(cLocBean.getCustName()).length()>0){ %>
				                <img height="18" onclick="getCustomer('c');" src="/s21extn/common/images/download.png">
				               <%} %>
			                </td>
			              </tr>
				           <tr> 
				              <td style="width:70%;"><span id="csAddr"><%=util.checkNull(cLocBean.getAddr())%></span></td>
				              <td><%if(util.checkNull(cLocBean.getAddr()).length()>0){ %>
				                <img height="18" onclick="getCustomerLoc('c','CURLOC');" src="/s21extn/common/images/download.png">
				               <%} %>
				              </td>
			              </tr>
			              <tr><td colspan="2"><span id="csCity"><%=util.checkNull(cLocBean.getCity())%></span></td></tr>
			              <tr> <td colspan="2"><span id="csState"><%=util.checkNull(cLocBean.getState())%></span>
			                  <span id="csPostalCode" class="marg-s"><%=util.checkNull(cLocBean.getPostalCode())%></span>
			               </td></tr>
			               <tr><td colspan="2">
			              <%if(util.checkNull(cLocBean.getAddr()).length()>0){ %>
			              <span id="sInstBase" style="display:none;">Update Install Base &nbsp;&nbsp; <input type="checkbox" name="uInstBase" id="uInstBase"  value="Y" style="border:0px;"/></span>
			              <%} %>
			              <input type="hidden" name="cCustName" id="cCustName" value="<%=util.checkNull(cLocBean.getCustName())%>"/>  
			              <input type="hidden" name="cAddr" id="cAddr" value="<%=util.checkNull(cLocBean.getAddr())%>"/>
			              </td></tr>
		            </table>  
		           </td>
		           <td style="width:50%;">
		              <table style="width:100%;">
			              <tr>
			                <td style="width:70%;"><span id="bsCustName"><%=util.checkNull(bLocBean.getCustName())%></span></td>
			                <td> 
				               <%if(util.checkNull(bLocBean.getCustName()).length()>0){ %>
				                <img height="18" onclick="getCustomer('b');" src="/s21extn/common/images/download.png">
				               <%} %>
			                </td>
			              </tr>
				           <tr> 
				              <td style="width:70%;"><span id="bsAddr"><%=util.checkNull(bLocBean.getAddr())%></span></td>
				              <td><%if(util.checkNull(bLocBean.getAddr()).length()>0){ %>
				                <img height="18" onclick="getCustomerLoc('b','BILLTO');" src="/s21extn/common/images/download.png">
				               <%} %>
				              </td>
			              </tr>
			              <tr><td colspan="2"><span id="bsCity"><%=util.checkNull(bLocBean.getCity())%></span></td></tr>
			              <tr> <td colspan="2"><span id="bsState"><%=util.checkNull(bLocBean.getState())%></span>
			                  <span id="bsPostalCode" class="marg-s"><%=util.checkNull(bLocBean.getPostalCode())%></span>
			               </td></tr>
			               <tr><td colspan="2">
			              <input type="hidden" name="bCustName" id="bCustName" value="<%=util.checkNull(bLocBean.getCustName())%>"/>  
			              <input type="hidden" name="bAddr" id="bAddr" value="<%=util.checkNull(bLocBean.getAddr())%>"/>
			              </td></tr>
		            </table>  
		           </td>
		         </tr>
		       
		       </table>
		    </td>
	        
	        <td class="tdw1">
			   <div class="vLine2"></div>
			</td>
	            <% 
	            String poRequired="";
	            String poUpload="";
	            if("Y".equals(util.checkNull(poReqFlg)) && "Y".equals(bllblFlg)){
	            	if("ESS".equals(util.checkNull(ciBean.getLineOfBusiness()))){
	            		//poRequired="requiredr"; do nothing
	            	}else{
	            		poRequired="requiredr";
	            		poUpload="requiredr";
	            	}
	            }
	            %>
	          <td style="vertical-align: top;width: 25%;">
                 <table class="tbs whdr">
                   <tr><td class="hd" colspan="2">PO Information</td></tr>
                   <tr class="trEmpty"><td colspan="2">&nbsp;</td></tr>
		        <tr>
		          <td nowrap="nowrap" style="width: 40%;">Customer PO </td>
		          <td><input type="text" size="18" class="<%=poRequired%>" name="custPo" id="custPo" maxlength="35" value="<%=util.checkNull(ciBean.getCustomerPoNum())%>"/></td>
		        </tr>
		       
		       <tr>
		         <td nowrap="nowrap" style="width: 40%;">Upload PO</td>
		         <td nowrap><input type="text" size="18" class="<%=poUpload%>" name="poUpld" id="poUpld" value="<%=util.checkNull(request.getParameter("poUpld"))%>" readonly="readonly" /> 
		         <img src="<%=imgSrc1%>"  height='18' onClick="uploadPO();"/>
		         </td>
		        </tr>
		        <tr>
		          <td nowrap="nowrap" style="width: 40%;">Pending PO </td>
		          <td><input type="checkbox" name="pendPo" id="pendPo" value="Y" style="border:0px;" onclick="fnChngPoSts()"></td>
		        </tr>		        
<%--  		        <tr> 
		        <%
			        String poFlg = util.checkNull(request.getParameter("cvuPO"));
			        if(poFlg.length()>0) {
			        	poFlg="checked";
					}else{
						poFlg="";
					}
		        %>
		          <td nowrap><label id="vupLbl" class="<%=poUpload %>">Verified Uploaded PO</label></td>
		           <td> <input  type="checkbox" name="cvuPO" id="cvuPO" value="Y" <%=poFlg%> class="<%=poUpload %>" style="border:0px;"/></td>
		        </tr>  --%>
		        <tr><td>Payment Term</td><td><input type="text" size="18" name="pmtTrm" id="pmtTrm" value="<%=util.checkNull(ciBean.getAttribute2())%>" readonly="readonly"/></td></tr>
		     </table>
          </td>
	      <td class="tdw1">
			   <div class="vLine2"></div>
		  </td>
	      <td style="width: 20%;vertical-align:top;" >
                 <table class="tbl whdr" >
		        <tr><td class="hd" colspan="2">Credit Card Authorization</td></tr>
		        <tr class="trEmpty"><td colspan="2">&nbsp;</td></tr>
		        
		        <tr><td nowrap="nowrap">Profile ID</td><td><input  type="text" size="25" name="profileId" id="profileId" class="rdl" value="<%=util.checkNull(request.getParameter("profileId"))%>"/></td></tr>
		        <tr><td nowrap="nowrap">Holder Name</td><td><input  type="text" size="25" name="holderName" id="holderName" value="<%=util.checkNull(request.getParameter("holderName"))%>"/></td></tr>
		        <tr><td nowrap="nowrap">Auth. Amt </td><td><input  type="text" size="8" name="authAmnt" id="authAmnt" value="<%=util.checkNull(request.getParameter("authAmnt"))%>"/></td></tr>
		        <tr><td>&nbsp;</td><td align="center" id="crdtBtn"><br><a href="#"  class="btn enter_cc" style="margin: 0 25px !important;" onclick="enterCC();">Enter Credit Card</a></td></tr>
	          </table>
            </td>
	     </tr>
   </table>
  <div class="hLineLg"></div>
  <table class="whdr">
	     <tr>
	     <td align="left"  style="width: 40%;vertical-align:top;">
	        <table class="tbs whdr">
		        <tr><td class="hd" colspan="2">Problem</td></tr>
		        <tr class="trEmpty"><td colspan="2">&nbsp;</td></tr>
		        <tr><td>Type</td>
		            <td> &nbsp;
		              <select id="sPbType" name="sPbType" class="required" style="width: 80%;"  onchange="setProb('sPbType','PBLM');" >
		                <option value="">Select</option>
			            <% 
			            String selVal = request.getParameter("sPbType");

			            String strSel = "";
			            for(String sProb: alProb){
			            	if(sProb.equals(selVal)){
			            		strSel = "SELECTED";
			            	}else{
			            		strSel ="";
			            	}
			            %>
			             <option value="<%=sProb%>" <%=strSel%>><%=sProb %></option>
			            <%} %>
		             </select> 
		            </td>
		         </tr> 
		         <%
		        		String sPbDescSelVal = util.checkNull(request.getParameter("sPbDescription")); 
		         		String clsVal = "rdl required";
		         		String dsblVal = "disabled=disabled";
		        		if(!("".equals(sPbDescSelVal))&&!("null".equals(sPbDescSelVal))){
		        			clsVal = "required";
		        			dsblVal = "";
		        		}
		        %>
		         <tr><td>Description</td><td>&nbsp;&nbsp;<select id="sPbDescription" name="sPbDescription" class="rdl required" disabled="disabled" onchange="setProb('sPbDescription','PBLM');">
        			<option value="">Select</option> 
		        	</select> </td></tr>
  
		        <tr><td>Code</td>
				   <%
			        String sPbCodeSelVal = util.checkNull(request.getParameter("sPbCode")); 
		            String pCodeSelVal = util.checkNull(request.getParameter("pCode")); 
		            String cdClsVal = "rdl required";
	         		String cdDsblVal = "disabled=disabled";
	        		if(!("".equals(pCodeSelVal))&&!("null".equals(pCodeSelVal))){
	        			clsVal = "required";
	        			dsblVal = "";
	        		}
				   %> 
				   
		        <td id="pbCdTd"> 
		        &nbsp;&nbsp;<select id="sPbCode" name="sPbCode"  class="rdl required" disabled="disabled" onchange="getRemedy();">
	        			<option value="">Select</option> 
		        	</select> 
		        	<!-- <div id=srchDiv style="display: none;"> <input type="text" id="realtxt" onkeyup="searchSel()"> </div> -->
		        	 <input name='pCode' id='pCode' type='hidden' value='<%=pCodeSelVal%>' />
		        	</td></tr>
        	
		        <tr>
		          <td nowrap="nowrap">&nbsp;</td>
		          <%
			        String mStatUp ="";
			        String mStatDwn="";
			        //System.out.println("mach stat: "+util.checkNull(request.getParameter("mchStatus")));
			        if("".equals(util.checkNull(request.getParameter("mchStatus"))) || "Y".equals(util.checkNull(request.getParameter("mchStatus")))){
			        	mStatDwn = "checked=checked";
			        }else{
			        	mStatUp = "checked=checked";
			        }
		          %>
		          <%-- <td>&nbsp;&nbsp;<input type="checkbox"  name="cOther" id="cOther" value="Y" onclick="enblNotes(); <%=cOtherFlg%>" style="border:0px;"/> --%>
		          <td>
		          <span style="float: right;">Machine Status &nbsp; UP &nbsp;&nbsp;<input type="radio"  name="mchStatus"  value="N" <%=mStatUp%> style="margin:0px;height:auto;border:0px;" />
		               DOWN &nbsp;&nbsp;<input type="radio" name="mchStatus"  value="Y" <%=mStatDwn%> style="margin:0px;height:auto;border:0px;" /> </span></td>
		       </tr>
		   </table>
	     </td>
	     
	    <td class="tdw1">
			   <div class="vLine2"></div>
		 </td> 
		   <%
/* 		   String strResStyle ="";
		   if(crSRFlg.equalsIgnoreCase("R") || crSRFlg.equalsIgnoreCase("DT") ||crSRFlg.equalsIgnoreCase("NMT")){
			   strResStyle = "width: 22%;vertical-align:top;";
		   }else{
			   strResStyle = "width: 22%;vertical-align:top;display: none;";
		   } */
		   %>
	     <td align="left" style="width: 30%;vertical-align:top;">
			      <%
			          String taskTypeName= util.checkNull(ciBean.getTaskTypeName());
			          String taskTypeCode=util.checkNull(ciBean.getTaskTypeCode());;
			          String sTaskType=taskTypeCode+"-"+taskTypeName;
			          String crChannel = util.checkNull(ciBean.getCreationChannel());
			          String crChannelCd = util.checkNull(ciBean.getCreationChannelCd());
			          String billCode =  util.checkNull(ciBean.getBillCode());
			          //String billCodeCd =  util.checkNull(ciBean.getBillCodeCd());
			         
			        %>
                  <table class="tbl whdr">
			        <tr><td class="hd" colspan="2">Call Information</td></tr>
			        <tr class="trEmpty"><td colspan="2">&nbsp;</td></tr>
			        <tr>
			           <td>Creation Channel</td>
			           <td> <select id="sCrChannel" name="sCrChannel">
			                   <option value="<%=crChannelCd %>"><%=crChannel %></option> </select> 
			           </td>
			        </tr>
			        <tr>
			           <td>Task Type</td>
			           <td>
			           <%
		           			String strStyle ="";
			           		String sTskTpeSelVal = util.checkNull(request.getParameter("sTaskType")); 
			          		if("DE_INSTALL".equals(eolCallTp)){
			           			strStyle = "class='rdl' style='color:#000000;'";
			           		 	taskTypeCode = "F";
					          	sTaskType="F - REMOVAL";
			           		} 

			          		if("Y".equals(crossSrvcFlg)){
			           			strStyle = "class='rdl' style='color:#000000;'";
			           		 	taskTypeCode = "A";
					          	sTaskType="A-INSTALL";
			           		} 
			           	%>

			          	<select id="sTaskType" name="sTaskType" onchange="setCallPrty('Y');" <%=strStyle %> >
			        	<%
			        		
			        		if(!("".equals(sTskTpeSelVal))&&!("null".equals(sTskTpeSelVal))){
			        			%>
			        			<option value="<%=sTskTpeSelVal %>"><%=sTskTpeSelVal %></option> 
			        			<%
			        		}else{
			        			%>
				               <option value="<%=taskTypeCode%>"><%=sTaskType %></option>
			        			<%
			        		}
			        	%>			           
                         </select></td>
			        </tr>
			        <tr>
			         <td>Bill Code</td>
			         <td><select id="sBillCode" name="sBillCode" onchange="fnBillCode();">
			        	<%
			        	 ArrayList alBc = (ArrayList<CanonE307BillCodeRec>) crDao.getBillCodes();
			             if(alBc!=null && alBc.size()>0){
			             	for(int i=0;i<alBc.size();i++){
			             		CanonE307BillCodeRec bcRec = (CanonE307BillCodeRec)alBc.get(i);
			        			String sBillCodeSelVal = request.getParameter("sBillCode")==null?BILL_CODE:request.getParameter("sBillCode");
			        			String selBcVal = "";
			        			if(bcRec.getBillTpCd().equals(sBillCodeSelVal)){
			        				selBcVal="selected";
			        				//System.out.println("tpCd: "+bcRec.getBillTpCd()+" selVal: "+sBillCodeSelVal);
			        			}
			        			%>
			        			  <option value='<%=bcRec.getBillTpCd()%>' <%=bcRec.getBillTpVal()%> <%=selBcVal%>><%=bcRec.getBillTpDesc() %></option>
			        			<%
			             	}
			             }
			        	%>			    			         
			            </select>
			          </td>
			        </tr>
			        <%
			        	System.out.println("sChngReason: "+ request.getParameter("sChngReason"));
			         
			        %>
			        <tr id="trChngRsn"><td>&nbsp;&nbsp;Change Reason</td><td><select id="sChngReason" name="sChngReason"><option value="">Select a Reason</option> </select> </td></tr>
			        <tr>
			         <td colspan="2" style="padding-top:5px;" nowrap><label id="cacLbl"> Customer Aware of Charge</label> &nbsp;&nbsp;<input type="checkbox" name="ccaChrg" id="ccaChrg" value="Y" style="border:0px;"></td>
			        </tr>
			      </table>
		  </td>
		  <td class="tdw1">
			   <div class="vLine2"></div>
		  </td> 
		  <td align="left" style="width: 30%;vertical-align:top;">
		      <table class="tbl whdr">
		         <tr><td class="hd" align="left" style="margin-bottom:5px;">Last 4 Jam/Error Codes</td></tr>
		         <tr class="trEmpty"><td colspan="2">&nbsp;</td></tr>
		         <%
		         int erCnt=0;
		         for(CanonE307ServiceReqErrorCodesRec erRec: alEc){
		        	 erCnt++;
		          %>
		          <tr><td align="center"><input type="text" class="inTxt"  size="25" readonly="readonly" name="errCode<%=erCnt %>" id="errCode<%=erCnt %>" value="<%=util.checkNull(erRec.getUgwErrCode() )%>"/></td></tr>
		        <%} 
		          int ec=3-erCnt;
		          for(int e=0;e<=ec;e++){
		         %>
		         <tr><td align="center"><input type="text" class="inTxt" size="25" readonly="readonly" ></td></tr>
		        <%}%>  
		      </table>
	       </td>
		  
	     </tr>
  </table>
  
 <div class="hLineLg"></div>
 
 <table  class="whdr">
  <tr>
     <td style="width: 45%;vertical-align:top;">
        <table class="whdr">
           <tr>
              <td style="vertical-align: top;">
              <table class="tbs whdr">
			        <tr><td class="hd" colspan="2">Create Note</td></tr>
			        <tr class="trEmpty"><td colspan="2">&nbsp;</td></tr>
			        <tr><td style="text-align:left;"> Type</td>
			            <td>
			             <select id="sNoteType" name="sNoteType" class="rdl" style="color:#000000;">
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
			        </tr>
			        <tr>
			            <td  style="text-align:left;">Message</td>
			            <td><textarea name='sNoteTypeTxt' id="sNoteTypeTxt"  rows='5' cols="50" class="inTxt required" style="height: 80px;"><%=util.checkNull(request.getParameter("sNoteTypeTxt")) %></textarea></td>
			        </tr>
			    </table>  
			    <%} %> 

		
              </td>
           </tr>
        </table>
    
      </td>
     <td class="tdw1">
		  <div class="vLine2" id="cAvoidLn"></div>
	 </td> 
    
     <td style="width: 55%;vertical-align:top;"> 
       <div id="cAvoid">
        <table class="whdr" id="callAvoid">

           <tr>
              <td style="vertical-align:top;">
	               <table  class="whdr">
	                    <tr><td class="hd" colspan="2">Call Avoidance</td></tr>
	                    <tr class="trEmpty"><td colspan="2">&nbsp;</td></tr>
	                    <tr>
	                      <td style="width: 12%;">Narrative</td>
	                      <td  style="width: 88%;"> 
	                         <textarea name="narrative" id="narrative" rows="2" class="inTxt" readonly="readonly" ></textarea>
	                         <input type="hidden" value="" id="remedy" name="remedy"/>
	                      </td>
	                    </tr>
	                    <tr style="line-height:40px;">
	                       <td colspan="2"><table><tr><td>
	                         Email Resolution Instructions to Customer</td><td><input type="checkbox" name="emailInst" id="emailInst" class="callAvd" style="margin-left:15px;border:0px;" onclick="chkEmail(this);" value=""/></td>
	                         <td style="display: none;" id="trTrblLnk" nowrap>Troubleshooting Link &nbsp;&nbsp;  </td><td><div id="trblLnk"><div><td></tr></table>
	                      </td>
	                    </tr>
	                    <tr style="display: none;" id="trEmailInst">
	                       <td colspan="2" nowrap="nowrap">Email Address <input type="text" size="21" name="emailAddrInst" id="emailAddrInst" onchange="fnValidateEmail(this)" value="<%=util.checkNull(csBean.getEmailAddress())%>"/>
	                         <a href="#" style="padding:1px 10px;margin-top: -2px;" class="btn" onclick="emailCustomer();">Send Email</a>
	                       <span id="emailMsg"></span>
	                     </td>
	                    </tr>
	                </table>   
	               
	                <div class="hLine"></div>
	               
	                <table  class="whdr" style="margin-top:2px;">
	                   <tr>
				          <td> 
				             <% 
				                for(CanonE307ServiceReqCallAvdRec  caRec  : alCallAvd) {
				             %>
				               <input type="radio" name="assist" class="chkAsst callAvd" onclick="$('.tdBtns').hide();$('.tdUnAsst').show(); $('#chkRslvd').prop('checked', false);"  value="<%=util.checkNull(caRec.getSvcCallAvoidCd())%>" style="border:0px;"/><%=util.checkNull(caRec.getSvcCallAvoidNm()) %> &nbsp;&nbsp;&nbsp;
				             <%} %>
				             	<input type="radio" name="rslvd" id="chkRslvd" class="callAvd"  onclick="$('.tdBtns').hide();$('.tdRslvd').show(); $('.chkAsst').prop('checked', false);"  value="" style="border:0px;" /> Call Resolved </td>
				          </tr>
				          
				    </table>
				   
              </td>
    
           </tr>
     </table>
     </div>
    </td>
  </tr>
</table>    

<div class="hLineLg"></div>

<table class="whdr disp_mode" style="margin-top:10px;">
	           <tr>
	              <td style="width:30%;"></td>
	              <td class="tdRslvd tdBtns" style="display: none;float: right;">
	                <a onclick="callResolved();" class="btn" href="#">Close Call</a>
	               <!--  <a onclick="createSR();"  class="btn" href="#">Create SR</a> -->
	                <a onclick="opnSrchPg();" class="btn" href="#" style="margin-left:10px;" >SR Not Required</a>
	               </td>
	              <td class="tdUnAsst tdBtns" style="display: none;float: right;">
	                 <a onclick="dispatchTech();" class="btn" href="#">Dispatch Technician</a>
	                 <a onclick="needMoreTime();" class="btn" href="#" style="margin-left:10px;">Need More Time</a>
	                 <a onclick="createSR();"  class="btn" href="#">Create SR</a>
	                 <a onclick="opnSrchPg();" class="btn" href="#" style="margin-left:10px;" >SR Not Required</a>
	              </td>
	              <td class="tdCrSr tdBtns" style="float: right;">
	                <a onclick="createSR();"  class="btn" href="#">Create SR</a>
                    <a onclick="opnSrchPg();" class="btn" href="#" style="margin-left:10px;" >SR Not Required</a>
                   </td>
                 </tr>
                 <tr>
                 <td colspan=4>&nbsp;</td>
                 </tr>
                <tr>
                 <td colspan=4>&nbsp;</td>
                 </tr>                 
</table>

<div id="divAhs" style="display: none;">
	<table id="tblAhs" style="width: 430x;font-weight: bold;">
	<%
	if("Y".equals(ahsFlg) && "Y".equals(ahsEnblFlg)){ 
	%>
	  <tr>
	     <td style="width: 40%;"><a onclick="selCallType('AHS');" class="btn" href="#">CREATE</a></td>
	     <td style="width: 60%;">AFTER HOURS CALL</td>
	  </tr>	
	<%
		}
	%>
	  <tr>
	     <td style="width: 40%;"><a onclick="selCallType('NORMAL');" class="btn" href="#">CREATE</a></td>
	     <td style="width: 60%;">NORMAL BUSINESS HOURS</td>
	  </tr>
	  <tr>
	     <td style="width: 40%;"><a onclick="selCallType('CANCEL');" class="btn" href="#">CANCEL</a></td>
	     <td style="width: 60%;">NO CALL NEEDED</td>
	  </tr>
	</table>
	<div class="ahsMsg" >
	  <%=ahsMsg %>  
	</div>
</div>

<div id="dlg"></div>



<script type="text/javascript">
  
$(document).ready(function() {
	
	 $("input.autoInteger").autoNumeric({aSep:'' , mDec: 0});
	 
	// $('select.sPbCode').filterByText($('input.realtxt'));
	
	 <% if("E".equals(rFlg)) { %>
	 $('#errorWidget').show();
	<%}%>

	 var selectedOpt = $("#sBillCode").find('option:selected');
     var lcf = selectedOpt.data('lchrgflg');
     var pcf = selectedOpt.data('pchrgflg');
     var bf = selectedOpt.data('blbleflg');
 	 $("#lbrChrgFlg").val(lcf);
 	 $("#prtChrgFlg").val(pcf);
 	 $("#bllbleFlg").val(bf); 
//	 fnDisblEnblCrdt(bf);
//	getCurTime();
	
 		fnBillCodeSelVal();
		var editRole = $('#editRole').val();
		if(editRole=='E307_SRM' || editRole=='E307_OTH'){
			 $('.tdBtns').hide();
			 $('.enter_cc').hide();
			 
		   	 $(".callAvd").each(function (){
			   		$(this).addClass("rdl1").attr("disabled","true");
			 }); 
		   	$("#sPbType").attr("disabled","disabled").addClass("rdl");
		   	$("#sPbDescription").attr("disabled","disabled").addClass("rdl");
		   	$("#sPbCode").attr("disabled","disabled").addClass("rdl");
			$('input[type="text"]').each(function (){
				 $(this).addClass("rdl").attr("readonly","readonly");
			});
		}else{
		    var cFlg = $('#calAvdFlg').val();
		    var oFsr = $('#oFsr').val();
			if(cFlg=='Y' && oFsr!='null' && oFsr.length<=0){
			   	 $('.tdBtns').hide();
		    }else{
			   	 $(".callAvd").each(function (){
			   		$(this).addClass("rdl1").attr("disabled","true");
			   	 }); 
		    }	
		}
		
		var chngBlRsn=$("#chngBlRsn").val();
		if(chngBlRsn!='' && chngBlRsn!='null'){
			setCallSelects("cr");
			$("#trChngRsn").show();
	 		$("#sChngReason").addClass("required"); 
	 		$('#sChngReason'+' option[value="' + chngBlRsn + '"]').prop('selected', true);
		}
		var ccaChrgVal=$("#ccaChrgVal").val();
		if(ccaChrgVal!='' && ccaChrgVal!='null'){
			$('#ccaChrg').prop('checked', true);
			$('#cacLbl').removeClass('required');
		}
		var probDescSelVal = $('#probDescSelVal').val();
		if(probDescSelVal!='' && probDescSelVal!='null'){
			setProb('sPbType','');
		}
});


function uploadPO(){
	
	var url = "canonE307FileUploadToTemp.jsp?source=summary";
	window.open(url,'newWin','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=450,height=250');
}     

function uploadItemImage(fileName){
	$('#poUpld').val(fileName);
//	$('#cvuPO').prop('checked', true);

  
/*	if(fileName!=''){
		var url = "canonE307AttachmentUpload.jsp";
		var qryStr="source=summary&fileName="+fileName;
		
		$.ajax({
			url: url,
			data: qryStr,
			type:"POST",
			cache: false,
			success : function(result)
			{
				if($.trim(result)=='0'){
					$("#eMsg").css({"color": "red", "font-size": "12"});
					$('#eMsg').html('PO is not Uploaded successfully');
					$('#errorWidget').show();
				}else{
					$('#eMsg').html("PO is uploaded Successsfully..");
					$("#eMsg").css({"color": "green", "font-size": "12"}); 
					$('#errorWidget').show();
				}
			}
		});	
	} */
}
     
     
     
var phone_field_length=0;
function tabNext(obj,event,len,next_field) {
	
  if (event == "down") {
    phone_field_length=obj.value.length;
    }
  else if (event == "up") {
    if (obj.value.length != phone_field_length) {
      phone_field_length=obj.value.length;
      if (phone_field_length == len) {
        next_field.focus();
        }
      }
    }
  }

  
  <% if((checkAHS.equals("Y")) && ("".equals(crSRFlg))) { %>
       checkAHS('<%=ahsTitle%>');  
  <%}%>
  
  function opnSrchPg(){
	  
	showPleaseWait();
	
	var srchUrl =  '<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSearch.jsp';
	document.forms['csForm'].action = srchUrl;
	document.forms['csForm'].submit();	
//	 window.location.href=srchUrl;
//	window.history.back();
	   
  }
  function getCurTime(){
		var d = new Date(); 
		var hor =d.getHours(); 
		var min = d.getMinutes();
	//	console.log("hor: "+hor+" min: "+min);

		var _time="";
		if(hor >=13){
			_time = hor-12 +' PM';
		}else if(hor==12){
			_time = hor +' PM';
		}else{
			_time = hor +' AM';
		}
		var dataTm = _time.split(' ');
		var _hr = (dataTm[0]<10)?('0'+dataTm[0]):(dataTm[0]);
		var min = (min<10)?('0'+min):(min);

        $('#ftrSrvHr'+' option[value="' + $.trim(_hr) + '"]').prop('selected', true);	
        $('#ftrAmPm'+' option[value="' + $.trim(dataTm[1]) + '"]').prop('selected', true);
        $('#ftrSrvMn' + ' option[value="' + min + '"]').prop('selected', true); 
        
        getSysDate();
  }

  function fnUpdateCallerNm(){
	  $('#caller').val($('#contactName').val());
  }
  function fnUpdateCalrPhn(){
	  $('#cllrPhNum').val($('#custTelNum').val());
	  $('#cllrPhNumExt').val($('#custTelExtnNum').val());
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
			$('#cardType').val(data.cardBrand);
			$('#cardExpr').val(data.ccExp);
			$("#profileId").val(data.customerRefNum);
			if(data.customerName){
				$("#holderName").val(data.customerName);
			}
			if(data.ccAccountNum){
				//$("#ccNum").val("************"+data.ccAccountNum);
				$("#ccNum").val(data.ccAccountNum);
			}
			$("#authAmnt").val("500");
 		});
 	}


function searchSel(){
	 var input = document.getElementById('realtxt').value.toLowerCase();
	 if (input == ''){
		   output[0].selected = true;
	 }else{
	     len = input.length;
	     output = document.getElementById('sPbCode').options;
		 for(var i=0; i<output.length; i++){
		     if (output[i].text.toLowerCase().indexOf(input) != -1 ){
		     output[i].selected = true;
		         break;
		     }
		 }
	 }
}
/* $('#combo-031').sPbCode({
    fullMatch: true,
    highlight: false
}); */
function filterByText(textbox){
jQuery.fn.filterByText = function(textbox) {
	  return this.each(function() {
	    var select = this;
	    var options = [];
	    $(select).find('option').each(function() {
	      options.push({
	        value: $(this).val(),
	        text: $(this).text()
	      });
	    });
	    $(select).data('options', options);

	    $(textbox).bind('change keyup', function() {
	      var options = $(select).empty().data('options');
	      var search = $.trim($(this).val());
	      var regex = new RegExp(search, "gi");

	      $.each(options, function(i) {
	        var option = options[i];
	        if (option.text.match(regex) !== null) {
	          $(select).append(
	            $('<option>').text(option.text).val(option.value)
	          );
	        }
	      });
	    });
	  });
	};
	}
</script>
</form>
<div id="divEnterCC"></div>
</div>
<%-- <script src='<%=ctxPath%>/e307/js/datalist-polyfill.min.js?v=1' type='text/javascript'></script> --%>

<%@include file="canonE307ServReqFtr.jsp"%>