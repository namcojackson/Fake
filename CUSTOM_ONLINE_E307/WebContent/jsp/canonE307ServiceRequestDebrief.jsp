
<%@ page import="com.canon.apps.servreq.dao.*" %>  
<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %> 
<%@page import="com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil"%> 
<%@page import="com.canon.apps.servreq.util.CanonE307DebriefAPIUtil"%> 


<%
String pageTitle="Debrief";

ArrayList<String> menuList = new ArrayList<String>();

 menuList.add("sp:btn:Scratch Pad:opnSCPad();");
%>

<%@ include file="canonE307ServReqHeader.jsp" %> 
<link rel="stylesheet" href="<%=ctxPath%>/e307/css/canonE307ServiceReqCreate.css" type="text/css">
<%-- <script src='<%=ctxPath%>/e307/js/canonE307AttachmentUpload.js' type='text/javascript'></script>  --%>

<% 
	CanonE307ServiceReqSumryAPIUtil utilObj = new CanonE307ServiceReqSumryAPIUtil();
	CanonE307DebriefDetailsDAO objDeb = new CanonE307DebriefDetailsDAO();
	CanonE307ServiceReqCreateDao crDao = new CanonE307ServiceReqCreateDao();
  	String strTaskNumber =request.getParameter("debriefTsk")==null?"":request.getParameter("debriefTsk");
    String strModel = request.getParameter("model")==null?"":request.getParameter("model");
    String strDbrfFlg = request.getParameter("dbrfFlg");
    String strSerNum= request.getParameter("serialNumber");
    String strTmZn = util.checkNull(request.getParameter("tmZn"));
    String mtrFlg = util.checkNull(request.getParameter("mtrFlg"));
    String rdInOutFlg = util.checkNull(request.getParameter("inOutRdFlg"));
    System.out.println("rdInOutFlg: "+ rdInOutFlg+" mtrFlg: "+mtrFlg+" strDbrfFlg: "+strDbrfFlg);
    
    if(strTmZn!=null && strTmZn.length()>0){
    	//Do Nothing
    }else{
    	String postalCd = util.checkNull(request.getParameter("postalCd"));
    	String cntryCd = util.checkNull(request.getParameter("cntryCd"));   
    	if(postalCd!=null && postalCd.length()>0 && cntryCd!=null && cntryCd.length()>0){
    		strTmZn = utilObj.getTmZone(postalCd, cntryCd,"");
    	}
    }
    System.out.println("strTaskNumber : "+ strTaskNumber);
    String retMsg="";
    String disStyle = "";
    String rFlg ="";
    String strRetVal ="";
    String eMsgId = "eDbrfMsg";
    int failCount=0;
	String  ahsTitle="";
	String  ahsMsg="";
	String errorCode = "";

    if("Y".equals(strDbrfFlg)){
    	CanonE307DebriefAPIUtil dbObj = new CanonE307DebriefAPIUtil();
    	System.out.println("Before calling debriefTask ");
    	String[] resArr = dbObj.debriefTask(request);
    	rFlg= util.checkNull(resArr[0]);
		  if(rFlg.equalsIgnoreCase("Y")){
			  strRetVal = objDeb.checkInstallCall(strTaskNumber); 
		/*	  String tskSts = request.getParameter("svcTskSts");
			  if("In Route".equals(tskSts)){
				  retMsg="Meter Reads are submitted successfully.";
			  }else{
			  	retMsg="Debrief Successful";
			  }*/
			  retMsg="Debrief Successful";
			  failCount=0;
		  }else{
			  rFlg=resArr[0];
			  retMsg=resArr[1];
		//	  System.out.println("Debrief retMsg: "+retMsg);
			  ArrayList errLst = objDeb.getMeterErrorCodes("DEBRIEF","");
			  if(errLst!=null && errLst.size()>0){
				  for(int i=0;i<errLst.size();i++){
					  CanonE307MeterErrorCodesBean errBean = (CanonE307MeterErrorCodesBean)errLst.get(i);
					  String mtrErrCd = errBean.getStrMtrErrCd();
					  System.out.println("mtrErrCd: "+ mtrErrCd);
					  if(retMsg.contains(mtrErrCd)){
						  errorCode = mtrErrCd;
					  }
				  }
			  }
		  }
    } 
 /*  if("Y".equals(mtrFlg)){
		CanonE307ServiceMtrRdsAPIUtil mtrObj = new CanonE307ServiceMtrRdsAPIUtil();
		String res[] = new String[2];
		if("IN".equals(rdInOutFlg)){
			res = mtrObj.submitMtrRds(request, "I","");
			 if(res!=null && "Y".equals(res[0])) {
				  rFlg=util.checkNull(res[0]);
				  retMsg="Meter Reads Submitted Successfully.";					 
			 }else{
				  rFlg=util.checkNull(res[0]);
				  retMsg=util.checkNull(res[1]);					 
			 }			
		}else if("ALL".equals(rdInOutFlg)){
			res = mtrObj.submitMtrRds(request, "I","");
			 if(res!=null && "Y".equals(res[0])) {
				 res = mtrObj.submitMtrRds(request, "O",""); 
				 if(res!=null && "Y".equals(res[0])) {
					  rFlg=util.checkNull(res[0]);
					  retMsg="Meter Reads Submitted Successfully.";					 
				 }else{
					  rFlg=util.checkNull(res[0]);
					  retMsg=util.checkNull(res[1]);					 
				 }
			 }else{
				  rFlg=util.checkNull(res[0]);
				  retMsg=util.checkNull(res[1]);
			 }
		}
		
    }*/
    System.out.println("errorCode: "+errorCode);
   if("ME".equals(rFlg) && errorCode!=null && util.checkNull(errorCode).length()>0){
    		failCount = request.getParameter("mtrFailCount")==null?0:Integer.parseInt(request.getParameter("mtrFailCount"));
    		failCount = failCount+1;
	 }

	System.out.println("strTaskNumber : " + strTaskNumber+" rFlg: "+rFlg);

	Object objs[]= objDeb.getDebriefDetails(strTaskNumber);
	CanonE307DebriefDetailsRec objBean = (CanonE307DebriefDetailsRec)objs[0];
	String strDebriefNumber="";
	ArrayList probList = new ArrayList();
	ArrayList corrList = new ArrayList();
	ArrayList reasonList = new ArrayList();
	ArrayList locList = new ArrayList();
	ArrayList iwrList = new ArrayList();

	if(objBean==null){
		objBean = new CanonE307DebriefDetailsRec();
	}
		 System.out.println(" rFlg: "+rFlg);
		   if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
			  	 objBean.setStrProblemCode(request.getParameter("problemCode"));
			  	 objBean.setStrCorrectionCode(request.getParameter("corrCode"));
			  	 objBean.setStrLocationCode(request.getParameter("locCode"));
			  	 objBean.setStrReasonCode(request.getParameter("resCode"));
			   }
	   CanonE307ServiceReqAPIUtil apiUti= new CanonE307ServiceReqAPIUtil();
	   String[] arrAch = apiUti.getBillCode(objBean.getStrAttribute1(), objBean.getStrFutureDate(), "0", "", "", util.checkNull(objBean.getStrTaskTypeCd()), loginUser, "N");  //UC
	   String bllblFlg =crDao.getBllblFlg(objBean.getStrAttribute10());
	   
	   if(!"Closed".equals(objBean.getStrDebriefStatus())){
		if("Y".equals(util.checkNull(objBean.getStrAhsFlg() ))){
			 if("N".equalsIgnoreCase(bllblFlg)){
				 ahsTitle="AFTER HOURS ENITITLED CALL";
				 ahsMsg="This Machine is Under Contract for After Hours Service <br> "+util.checkNull(objBean.getStrCovTm() ) ;
			 }else if("Y".equalsIgnoreCase(bllblFlg)){
				 ahsTitle ="AFTER HOURS CHARGEABLE CALL";
				 
				 if("X3".equals(util.checkNull(objBean.getStrTaskTypeCd())) && "ESS".equals(util.checkNull(objBean.getStrLnBzTpCd()))){
					 ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour. Charge for Labor and Parts Cost. <br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
				 }else if("X3".equals(util.checkNull(objBean.getStrTaskTypeCd())) ){
					 ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour.";
					 	
					  	if(util.checkNull(arrAch[4]).length()>0)
					  		ahsMsg = ahsMsg+ " Charge for Labor, plus a Flat Travel charge not to exceed $"+ util.checkNull(arrAch[4])+" will be added to the cost of service and Parts Cost.";
					  		//ahsMsg = ahsMsg+ " Charge for Labor plus Travel charge of $"+ util.checkNull(arrAch[4])+" and Parts Cost. ";
					  					
					  		ahsMsg = ahsMsg+ "<br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
				 }else{
					if("3X".equals(objBean.getStrAttribute10()) || "3".equals(objBean.getStrAttribute10())){
							ahsMsg= "Parts being Chargeable.";
					}else{
					 if("ESS".equals(util.checkNull(objBean.getStrLnBzTpCd()))){
						 ahsMsg="The Chargeable labor rate for this call is Estimated at $"+util.checkNull(arrAch[2])+" per hour hours.";
					 }else{
						 ahsMsg = "The Chargeable labor rate for this call is Estimated at $"+util.checkNull(arrAch[2])+" per hour with a Minimum of "+util.checkNull(arrAch[7])+" hours";
						 if(arrAch[4]!=null && util.checkNull(arrAch[4]).length()>0)
							    ahsMsg = ahsMsg+ " and a Flat Travel charge not to exceed $"+ util.checkNull(arrAch[4])+" will be added to the cost of service.";
						 }					 
				 	}
				 }
			 }
		 }else if("3".equals(util.checkNull(objBean.getStrTaskTypeCd())) && "ESS".equals(util.checkNull(objBean.getStrLnBzTpCd())) && "Y".equalsIgnoreCase(bllblFlg)){
				ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour. Charge for Labor and Parts Cost. <br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
				
		 }else if("3".equals(util.checkNull(objBean.getStrTaskTypeCd())) && "Y".equalsIgnoreCase(bllblFlg)){
		 	ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour.";
		 	
		  	if(util.checkNull(arrAch[4]).length()>0)
		  		ahsMsg = ahsMsg+ " Charge for Labor, plus a Flat Travel charge not to exceed $"+ util.checkNull(arrAch[4])+" will be added to the cost of service and Parts Cost.";
		  		//ahsMsg = ahsMsg+ " Charge for Labor plus Travel charge of $"+ util.checkNull(arrAch[4])+" and Parts Cost. ";
		  					
		  		ahsMsg = ahsMsg+ "<br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
		  				
		 }else if("Y".equalsIgnoreCase(bllblFlg)){
			 if("3".equals(objBean.getStrAttribute10())){
					ahsMsg= "Parts being Chargeable.";
			}else{
			 	 if("ESS".equals(util.checkNull(objBean.getStrLnBzTpCd()))){
			 			ahsMsg="This SR is Chargeable at a rate of $"+arrAch[2];
			 	 }else{
			 		 ahsMsg = "The Chargeable labor rate for this call is Estimated at $"+util.checkNull(arrAch[2])+" per hour with a Minimum of "+util.checkNull(arrAch[7])+" hours ";
			 		 if(arrAch[4]!=null && util.checkNull(arrAch[4]).length()>0)
			 			    ahsMsg = ahsMsg+ "and a Flat Travel charge not to exceed $"+ util.checkNull(arrAch[4])+" will be added to the cost of service.";
			  	}
			}
		 }
		
	   System.out.println("ahsMsg: "+ ahsMsg+" lnOfBusiness: "+ objBean.getStrLnBzTpCd()+ "bllblFlg: " + bllblFlg+ "taskTypeCd: "+ objBean.getStrTaskTypeCd()+" machMstrPk: "+objBean.getStrAttribute1());
	   }
	 %>
 <div id="main_content" style="min-height: 924px;">  
    <div id="page_top">
		<h1>Advanced Service Call Center</h1>
	</div>
	<div style="margin:10px 28px;"><h1>Debrief</h1></div>
	<% System.out.println("disStyle : "+ disStyle+" rFlg: "+rFlg+" retMsg : "+retMsg); %>
	<%
	if(ahsMsg!=null && (util.checkNull(ahsMsg)).length()>0){
	%>
		<div id="ahsMsg" style="margin:10px 28px;">
			<p id="ahsMsg" style="color: red;font-size: 15px;font-weight: bold;" align="left"><%=ahsMsg%></p>
		</div>
	<%} %>

	<div id="errorWidget"  style="<%=disStyle%>">
	<% if("Y".equals(rFlg)){%>
		<p id="eDbrfMsg" style="color:green;font-weight:bold;margin:10px 28px;font-size: 15px;"><%=retMsg%></p>
	<%}else if("N".equals(objBean.getStrOnlnValdFlg())){%>
 		<p id="eDbrfMsg" style="font-weight:bold;margin:10px 28px;font-size: 15px;color:red;">You cannot process it during nightly batch processing because inventory discrepancy will occur.</p>
 	<% }else{%>
 		<p id="eDbrfMsg" style="font-weight:bold;margin:10px 28px;font-size: 15px;color:red;"><%=retMsg==null?"":retMsg%></p>
 	<% } %>
	</div> 
	<% System.out.println("disStyle : "+ disStyle+" rFlg: "+rFlg+" retMsg : "+retMsg); %>
	

  <div id='debriefInfoDiv'>
  <form name="debriefFrm" id="debriefFrm" method="post" action="canonE307ServiceRequestDebrief.jsp">
 <!--   <input type="hidden" name="dbrfFlg" id="dbrfFlg" value="<%=strDbrfFlg%>"> -->
  <input type="hidden" name="fsr" id="fsr" value="<%=util.checkNull(objBean.getStrSRNumber())%>">
  <input type="hidden" name="svcMachMstrPk" id="svcMachMstrPk" value="<%=objBean.getStrAttribute1()%>">
  <input type="hidden" name="debriefTsk" id="debriefTsk" value="<%=strTaskNumber%>">
  <input type="hidden" id="userName" name="userName" value="<%=loginUser%>">
  <input type="hidden" id="tmZn" name="tmZn" value="<%=strTmZn%>">
  <input type="hidden" name="scratchPad" id="scratchPad" value="">
  <input type="hidden" name="iwrStatFlg" id="iwrStatFlg" value="<%=objBean.getStrAttribute5()%>">
  <input type="hidden" name="editRole" id="editRole" value="<%=editRole%>">	
  <input type="hidden" name="mtrFailCount" id="mtrFailCount" value="<%=failCount%>">
  <input type="hidden" name="svcTskSts" id="svcTskSts" value="<%=objBean.getStrTaskStatus()%>" >
  <input type="hidden" name="tskReOpenFlg" id="tskReOpenFlg" value="<%=objBean.getStrAttribute9() %>">
  <input type="hidden" id="postalCd" name="postalCd" value="<%=objBean.getStrAttribute6() %>">
  <input type="hidden" id="countryCd" name="countryCd" value="<%=objBean.getStrAttribute7() %>">
  
  	<div class="debrief-information">
		<div class="info-div02">
				<table class="border-no" style="width:100%;height:260px;" cellspacing="0">
					<tr valign="top">
					<td style="width:50%;height:100%;">
						<div id="mach" style="width:100%;height:97%;border:1px solid #CCC;">
							<table style="width:100%;border:0px;" cellspacing="1" >
								<tr>
									<th colspan="4">Machine Information</th>
								</tr>
								<tr>
									<td width="15%"><b>FSR#</b></td>
									<td width="30%"><a href="javascript:fnSummaryPg('<%=ctxPath%>/e307/jsp/canonE307ServiceReqHistory.jsp?servRqstNumber=<%=util.checkNull(objBean.getStrSRNumber())%>')"><%=util.checkNull(objBean.getStrSRNumber()) %></a></td>
									<td width="15%"><b>Serial#</b></td>
									<td width="30%"><input type="text" name="serialNumber" id="serialNumber" value="<%=util.checkNull(objBean.getStrSerialNumber()) %>" class="rdl"></td>
								</tr>
								<tr>
									<td><b>Task#</b></td>
									<td><input type="text" name="taskNumbr" id="taskNumbr" value="<%=util.checkNull(objBean.getStrTaskNumber()) %>" class="rdl"></td>
									<td><b>Tag#</b></td>
									<td ><input type="text" name="tagNumber" id="tagNumber" value="<%=util.checkNull(objBean.getStrTagNumber()) %>" class="rdl"></td>
								</tr>
								<tr>
									<td nowrap><b>Customer Case#</b></td>
									<td><input type="text" name="custCase" id="custCase" value="<%=util.checkNull(objBean.getStrCustomerCase()) %>" class="rdl"></td>
									<td><b>ITT#</b></td>
									<td ><input type="text" name="ITTNumber" id="ITTNumber" value="<%=util.checkNull(objBean.getStrITTNumber()) %>" class="rdl"></td>
								</tr>
								<%
									String strMachineup =  "CHECKED"; 
									String strMachineDown="";
									if(objBean.getStrMachineStatus()!=null && "Y".equalsIgnoreCase(objBean.getStrMachineStatus()))
										strMachineDown = "CHECKED";
								%>								
								<tr>
									<td nowrap><b>Machine Status</b></td>
									<td><input type="radio" name="machineStatus" id="machineStatus" value="N" <%=strMachineup%> style="margin:0px;height:auto;border:0px;"> Up &nbsp;&nbsp; <input type="radio" name="machineStatus" id="machineStatus" value="Y" <%=strMachineDown %> style="margin:0px;height:auto;border:0px;"> Down</td>
									<!-- <td><input type="checkbox" name="machineStatus" value="N" <%=strMachineup%> class="chkGrp"> Up <input type="checkbox" name="machineStatus" value="Y" <%=strMachineDown%> class="chkGrp"> Down</td>  -->
									<td><b>Solution</b></td>
									<td><input type="text" name="solutionName" id="solutionName" value="<%=util.checkNull(objBean.getStrSolutionName()) %>"></td>
								</tr>
							</table>
						</div>
					</td>

					<td style="width:50%;height:100%;">
					 	<div id="res" style="width:100%;height:97%;border:1px solid #CCC;">
							<table style="width:100%;border:0px;" cellspacing="1">
								<tr>
									<th colspan="4">Resolution Information</th>
								</tr>
								<tr>
									<td width="15%" nowrap><b>Problem Code:</b></td>
									<%
									ArrayList<CanonE307ServReqDebriefLovRec> arList = objDeb.getDebriefLovVal("PROBLEMCODE","");
									%>
									<td width="30%">
										<select id="problemCode" name="problemCode" class="requiredd">
										<option value="">Select</option>
										<%
										String strSelect ="";
										if(arList!=null && arList.size()>0){
											 for(CanonE307ServReqDebriefLovRec lovBean : arList){
												if(util.checkNull(lovBean.getStrVal()).equals(objBean.getStrProblemCode())){
													strSelect = "selected";
												}else{
													strSelect = "";
												}
											%>
												<option value="<%=util.checkNull(lovBean.getStrVal()) %>" <%=strSelect%>><%=util.checkNull(lovBean.getStrVal()) %>-<%=util.checkNull(lovBean.getStrValDesc()) %></option>
											<%
											 }
										 }
										%>
										</select>
									</td>
									<td width="15%" nowrap><b>Correction Code:</b></td>
									<%
									arList=new ArrayList();
									arList = objDeb.getDebriefLovVal("CRCTCODE",""); 
									%>
									<td width="30%">
										<select id="corrCode" name="corrCode" class="requiredd">
										<option value="">Select</option>
										<%
										String strCorSelect ="";
										if(arList!=null && arList.size()>0){
											 for(CanonE307ServReqDebriefLovRec lovBean : arList){
												if(util.checkNull(lovBean.getStrVal()).equals(objBean.getStrCorrectionCode() )){
													strCorSelect = "selected";
												}else{
													strCorSelect = "";
												}
											%>
												<option value="<%=util.checkNull(lovBean.getStrVal()) %>" <%=strCorSelect%>><%=util.checkNull(lovBean.getStrVal()) %>-<%=util.checkNull(lovBean.getStrValDesc()) %></option>
											<%
											 }
										 }
										%></select>
									</td>
								</tr>
								<tr>
									<td nowrap><b>Location Code:</b></td>
									<%
									arList=new ArrayList();
									arList = objDeb.getDebriefLovVal("LOCCODE",""); 
									%>
									<td><select id="locCode" name="locCode" class="requiredd">
										<option value="">Select</option>
										<%
										String strLocSelect ="";
										if(arList!=null && arList.size()>0){
											 for(CanonE307ServReqDebriefLovRec lovBean : arList){
												if(util.checkNull(lovBean.getStrVal()).equals(objBean.getStrLocationCode() )){
													strLocSelect = "selected";
												}else{
													strLocSelect = "";
												}
											%>
												<option value="<%=util.checkNull(lovBean.getStrVal()) %>" <%=strLocSelect%>><%=util.checkNull(lovBean.getStrVal()) %>-<%=util.checkNull(lovBean.getStrValDesc()) %></option>
											<%
											 }
										 }
										%>
											</select>
									</td>
									<td><b>Reason Code:</b></td>
									<%
										arList=new ArrayList();
										arList = objDeb.getDebriefLovVal("RSNCODE",""); 
									%>									
									<td><select id="resCode" name="resCode" class="requiredd">
										<option value="">Select</option>
										<%
										String strResSelect ="";
										if(arList!=null && arList.size()>0){
											 for(CanonE307ServReqDebriefLovRec lovBean : arList){
												if(util.checkNull(lovBean.getStrVal()).equals(objBean.getStrReasonCode() )){
													strResSelect = "selected";
												}else{
													strResSelect = "";
												}
											%>
												<option value="<%=util.checkNull(lovBean.getStrVal()) %>" <%=strResSelect%>><%=util.checkNull(lovBean.getStrVal()) %>-<%=util.checkNull(lovBean.getStrValDesc()) %></option>
											<%
											 }
										 }
										%>									
											<option value=""></option>
										</select>
									</td>
								</tr>
								<tr>
									<td><b>Debrief#:</b></td>
									<td><input type="text" value="<%=objBean.getStrDebriefNumber()==null?"":objBean.getStrDebriefNumber()%>" name="debriefNumber" id="debriefNumber" class="rdl">
									</td>
									<td><b>Status:</b></td>
									<td><input type="text" value="<%=objBean.getStrDebriefStatus()==null?"":objBean.getStrDebriefStatus() %>" name="tskSts" id="tskSts" class='rdl'>
									</td>									
								</tr>
								<tr>
 									<%
									if("Y".equals(objBean.getStrAttribute5())){
										%>
									<td><b>IWR Status:</b></td>
										<td>										
											<select id="iwrStat" name="iwrStat" >
											<option value="">Select</option>
											<%
												arList=new ArrayList();
												arList = objDeb.getDebriefLovVal("IWRSTS",""); 
												String strIwrSelect ="";
												if(arList!=null && arList.size()>0){
													 for(CanonE307ServReqDebriefLovRec lovBean : arList){
														 strIwrSelect ="";
														// System.out.println("IWR Check: "+ objBean.getStrIWRCheck()+" strVal: "+lovBean.getStrVal());
															if(util.checkNull(lovBean.getStrVal()).equals(objBean.getStrIWRCheck())){
																strIwrSelect = "selected";
															}else{
																strIwrSelect = "";
															}
															 System.out.println("strIwrSelect: "+ strIwrSelect);
														%>
															<option value="<%=util.checkNull(lovBean.getStrVal()) %>" <%=strIwrSelect%>><%=util.checkNull(lovBean.getStrVal()) %>-<%=util.checkNull(lovBean.getStrValDesc()) %></option>
														<%
														 }
												}
											
											%>
											</select>
										</td>
										<td></td><td></td>	
										<%
									}else{
									%>
									<td>
										<input type="hidden" id="iwrStat" name="iwrStat">
										</td><td></td><td></td><td></td>	
									<%
									}
									%> 
								</tr>
							</table>
						</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="info-div01" style="position: relative; top: -8px;">
			<div class="case-number">
			<%
				if("Y".equals(objBean.getStrAttribute8())){
			%>		
			    <a class="btn" href="javascript:void fnChkLstOpn()">Configuration CheckList</a>  			
			<%
				}
			%>
 
			    <a class="btn" href="javascript:void fnBackSummary()">Back</a>
			   <!--  <a class="btn" href="javascript:void fnOpenMtrRd()">Submit Meter Read</a> -->
			    <%
			    	System.out.println("Attribute4: "+ objBean.getStrAttribute4()+" Attribute9: "+objBean.getStrAttribute9()+" editRole: "+editRole);
			    	if(("Y".equals(objBean.getStrAttribute4())) || ("Y".equals(objBean.getStrAttribute9())  && "E307_DIS".equals(editRole))){
				%>
					<a class="btn disp_mode" href="javascript:void submitDebriefForm('<%=objBean.getStrTaskStatus() %>')">Submit Debrief</a>
				<%
			    	}
			    %>
				
			</div>
			<br>
			<hr />
		</div>
	<%
	List labrepData=new ArrayList();
	ArrayList al = new ArrayList();
	String strMLaborEntryCheck="";
	Object[] labObj= objDeb.getDebriefLaborInfo(strTaskNumber);
	if(labObj!=null && labObj.length>=0){
		String strMLaborEntry=(String)labObj[1];
		System.out.println("strDbrfFlg: "+strDbrfFlg+" rFlg : "+rFlg+" strMLaborEntry: "+strMLaborEntry);
		   if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
				 String labRecSize = request.getParameter("labRecSize");
				 int lbrCnt = 0;
				 if(labRecSize!=null && !("".equals(labRecSize))&&!("null".equals(labRecSize))){
					 lbrCnt=Integer.parseInt(labRecSize);
				 }
				 for(int i=0;i<lbrCnt;i++){
					 System.out.println("Labor Item : "+request.getParameter("laborItem"+i)+" size : "+lbrCnt);
					 CanonE307DebriefLaborDetailsRec lbrRec = new CanonE307DebriefLaborDetailsRec();
					 lbrRec.setStrFVTmEvntPk(request.getParameter("fsrVstTmPk"+i));
					 lbrRec.setStrAttribute1(request.getParameter("billTpCd"+i));
					 lbrRec.setStrSvcLbrChgFlg(request.getParameter("lbrChrgFlg"+i));
					 lbrRec.setStrLbrItmNum(request.getParameter("laborItem"+i));
					 lbrRec.setStrLbrItmDesc(request.getParameter("laborDescription"+i));
				//	 lbrRec.setStrStartDt(request.getParameter("lbrStrtDt"+i));
				//	 lbrRec.setStrEndDt(request.getParameter("lbrEndDt"+i));
					 lbrRec.setStrStartTm(request.getParameter("lStrtTmSel"+i));
					 lbrRec.setStrEndTm(request.getParameter("lEndTmSel"+i));
					 lbrRec.setLbrDuration(request.getParameter("laborDuration"+i)==null?0.0:Double.parseDouble(request.getParameter("laborDuration"+i)));
					 lbrRec.setStrModNum(request.getParameter("laborMod"+i));
					 lbrRec.setStrSerialNbr(request.getParameter("laborSerNumber"+i));
					 lbrRec.setStrModItm(request.getParameter("laborModItem"+i));
					 lbrRec.setStrComments(request.getParameter("laborComments"+i));
					 labrepData.add(lbrRec);
				 }
			   }else{
					labrepData = (List)labObj[0];
			   }
		   if(labrepData!=null && labrepData.size()>0){
			   System.out.println("labrepData: "+ labrepData.size());
		   }
		if("Y".equals(strMLaborEntry)){
			strMLaborEntryCheck="CHECKED=CHECKED";
		}else{
			strMLaborEntryCheck="";
		}
	}else{
	   if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
			 String labRecSize = request.getParameter("labRecSize");
			 int lbrCnt = 0;
			 if(labRecSize!=null && !("".equals(labRecSize))&&!("null".equals(labRecSize))){
				 lbrCnt=Integer.parseInt(labRecSize);
			 }
			 for(int i=0;i<lbrCnt;i++){
				 
			// System.out.println("Labor Item Inside : "+request.getParameter("laborItem"+i)+" size : "+lbrCnt);
				 CanonE307DebriefLaborDetailsRec lbrRec = new CanonE307DebriefLaborDetailsRec();
				 lbrRec.setStrFVTmEvntPk(request.getParameter("fsrVstTmPk"+i));
				 lbrRec.setStrAttribute1(request.getParameter("billTpCd"+i));
				 lbrRec.setStrSvcLbrChgFlg(request.getParameter("lbrChrgFlg"+i));
				 lbrRec.setStrLbrItmNum(request.getParameter("laborItem"+i));
				 lbrRec.setStrLbrItmDesc(request.getParameter("laborDescription"+i));
			//	 lbrRec.setStrStartDt(request.getParameter("lbrStrtDt"+i));
			//	 lbrRec.setStrEndDt(request.getParameter("lbrEndDt"+i));
				 lbrRec.setStrStartTm(request.getParameter("lStrtTmSel"+i));
				 lbrRec.setStrEndTm(request.getParameter("lEndTmSel"+i));
				 lbrRec.setLbrDuration(request.getParameter("laborDuration"+i)==null?0.0:Double.parseDouble(request.getParameter("laborDuration"+i)));
				 lbrRec.setStrModNum(request.getParameter("laborMod"+i));
				 lbrRec.setStrSerialNbr(request.getParameter("laborSerNumber"+i));
				 lbrRec.setStrModItm(request.getParameter("laborModItem"+i));
				 lbrRec.setStrComments(request.getParameter("laborComments"+i));
				 labrepData.add(lbrRec);
			 }
		   }
	}
	%>
	<input type="hidden" id="selTab" name="selTab" value="labor">
	<div class="tab-information">
	<div class="plus-minus"><a id="imgDiv" class="disabled" href="javascript:deleteRow()">-</a> <a href="javascript:addMoreRows()">+</a></div>
		<div class="tab-nav">
		<a id="lbr" class="active" href="javascript:void displayLaborTable(this)"><b>Labor</b></a>
		<a id="prt" href="javascript:void displayPartsTable(this)"><b>Parts</b></a>
		<a id="epns" href="javascript:void displayExpenseTable(this);" style="width:70.5px;height:20px;"><b>Expenses</b></a>
	<!-- 	<span class="right_float"><img src="<%=ctxPath%>/e307/images/plus-sign-simple.png" alt="" height="18" onClick="addMoreRows()"></img></span>
		<span class="right_float" id='mSpan'><img id="mChng" src="<%=ctxPath%>/e307/images/minus-dis.png" alt="" height="18" onClick="deleteRow()"></img></span>	 -->
		<span style="padding-left: 300px;"></span>
		<span style="background-color: #33FF33;font-weight: bold;font-size:15;"><b>Time Zone : <%=strTmZn%></b></span>		
		<span id="labEntr" class="right_float"><input type="checkbox" name="manualLabEntry" id="manualLabEntry"  class="chkGrp"  disabled="disabled" <%=strMLaborEntryCheck%> style="border:0px;" /> Manual Labor Entry Allowed</span>
		</div>
	<%
	String strLaborStyle = "display: none;";
	String pattern = "MMM dd yyyy";
	String dateInString =new SimpleDateFormat(pattern).format(new Date());
	ArrayList<String> lsHr = crDao.getHourVal();
	ArrayList<String> lsMn = crDao.getMnVal();	
	Object[] result = objDeb.getItemDetailsLov("LABOR", "", -1, -1, "");
	List lstItems=new ArrayList();
	if(result != null && result.length>1){
		lstItems=(ArrayList)result[0];
	/*	if(lstItems!=null && lstItems.size()>0){
			System.out.println("labor lov: "+ lstItems.size());
		} */
	}
	Object[] resultParts = objDeb.getItemDetailsLov("PARTS","", -1, -1, objBean.getStrTaskNumber());
	List lstPartItems=new ArrayList();
	if(resultParts != null && resultParts.length>1){
		lstPartItems=(ArrayList)resultParts[0];
	/*	if(lstPartItems!=null && lstPartItems.size()>0){
			System.out.println("parts lov: "+ lstPartItems.size());
		} */
	} 
	Object[] resultExpense = objDeb.getItemDetailsLov("EXPENSE","", -1, -1, "");
	List lstExpItems=new ArrayList();
	if(resultExpense != null && resultExpense.length>1){
		lstExpItems=(ArrayList)resultExpense[0];
	/*	if(lstExpItems!=null && lstExpItems.size()>0){
			System.out.println("parts lov: "+ lstExpItems.size());
		} */
	}
	
	int lbrSize=0;
		if(labrepData!=null && labrepData.size()>5){	
			strLaborStyle="height:  300px;  overflow-y:auto; ";
		}
	%>
 	<div id="labortable" style="<%=strLaborStyle%>">  
		<table id="labor_table" width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<th width="1%"></th>
				<th width='8%'>Labor Item</th>
				<!-- <th width='10%'>Description</th> -->
				<th width='9%'>Start Date</th>
				<th width='4%'>Time</th>
				<th width='9%'>End Date</th>
				<th width='4%'>Time</th>
				<th width='2%'>Duration</th>
				<th width='7%' colspan=2>MOD#</th>
				<th width='7%'>Serial Number</th>
				<th width='7%'>Mod Item</th>
				<th width='10%'>Error Message</th>
			</tr>
			<%
			//ArrayList<CanonDebriefLaborDetailsBean> alRepData = objDet.getDebriefLaborInfo(strTaskNumber);
			
			if(labrepData!=null && labrepData.size()>0){
				System.out.println("Size: " + labrepData.size());
				lbrSize = labrepData.size();
				for(int i=0;i<labrepData.size();i++){
					CanonE307DebriefLaborDetailsRec lbrBeanObj = (CanonE307DebriefLaborDetailsRec)labrepData.get(i);
					al.add("lbrStrtDt"+i);
					al.add("lbrEndDt"+i);
					String startDt = lbrBeanObj.getStrStartDt()==null?"":lbrBeanObj.getStrStartDt();
					String strtTm = lbrBeanObj.getStrStartTm()==null?"":lbrBeanObj.getStrStartTm();
					String endDt = lbrBeanObj.getStrEndDt()==null?"":lbrBeanObj.getStrEndDt();
					String endTm = lbrBeanObj.getStrEndTm()==null?"":lbrBeanObj.getStrEndTm();
					String strtDtTime="";
					String endDtTime="";
					System.out.println("startDt: "+ startDt+" strtTm: "+strtTm+" endDt: "+endDt+" endTm: "+endTm+" Item Num: "+lbrBeanObj.getStrLbrItmNum());
					if(startDt!=null && startDt.length()>0 && strtTm!=null && strtTm.length()>0){
						strtDtTime  = utilObj.getTmZone(objBean.getStrAttribute6(), objBean.getStrAttribute7(),startDt+strtTm);
					}
					if(endDt!=null && endDt.length()>0 && endTm!=null && endTm.length()>0){
						 endDtTime  = utilObj.getTmZone(objBean.getStrAttribute6(), objBean.getStrAttribute7(),endDt+endTm);
					}
			
					String strDate ="";
					String strHr ="";
					String strMin="";
					String strAmPm="";
					String strEndDate ="";
					String strEndHr ="";	
					String strEndMn ="";	
					String strEndAmPm="";
					String strDsblFlg = "";
					String rdOnlyFlg="readOnly";
					
					if(strtDtTime!=null && strtDtTime.length()>0){
						strDate = strtDtTime.substring(0, 11);
						strHr = strtDtTime.substring(12,14);
						strMin = strtDtTime.substring(15,17);
						strAmPm = strtDtTime.substring(18,20);
					}
					if(endDtTime!=null && endDtTime.length()>0){
						strEndDate = endDtTime.substring(0, 11);
						strEndHr = endDtTime.substring(12,14);
						strEndMn = endDtTime.substring(15,17);
						strEndAmPm = endDtTime.substring(18,20);
						//strEndTm = endDtTime.substring(12, 17);
					}					
					System.out.println("dateTime : "+ strtDtTime+" date :"+strDate +"tm :"+strHr+" "+strMin + "lbrBeanObj.getStrLbrItmNum(): "+lbrBeanObj.getStrLbrItmNum());
					System.out.println("End dateTime : "+ endDtTime+" date :"+strEndDate +"tm :"+strEndHr + " "+strEndMn);
					
					//ArrayList<CanonE307ServReqDebriefLovRec> arList = objDeb.getDebriefLovVal("PROBLEMCODE","");
				/*	if("068ZZ634".equals(lbrBeanObj.getStrLbrItmNum())){
						strDsblFlg = "disabled=disabled";
						rdOnlyFlg ="";
					} */
					
			%>
				<tr id='laborRow<%=i%>'>
					<input type="hidden" id="rowId" name="rowId" value="<%=i%>">
					<input type="hidden" id="fsrVstTmPk<%=i%>" name="fsrVstTmPk<%=i%>" value="<%=util.checkNull(lbrBeanObj.getStrFVTmEvntPk()) %>">
					<input type="hidden" id="billTpCd<%=i%>" name="billTpCd<%=i%>" value="<%=util.checkNull(lbrBeanObj.getStrAttribute1()) %>">
					<input type="hidden" id="lbrDrtn<%=i%>" name="lbrDrtn<%=i%>" value="">
					<input type="hidden" id="lbrChrgFlg<%=i%>" name="lbrChrgFlg<%=i%>" value="<%=lbrBeanObj.getStrSvcLbrChgFlg() %>">
					<input type="hidden" id="dtChkFlg" name="dtChkFlg" value="N">
					<input type="hidden" id="laborItmVal<%=i%>" name="laborItmVal<%=i%>" value="<%=lbrBeanObj.getStrLbrItmNum() %>">

					<td><input type=radio name="laborSelect" id="laborSelect<%=i%>" value=<%=i%>  disabled style="border:0px;"></td>
					<td id="lbrItmLovTd<%=i%>" nowrap >
					<%-- <input type="text" id="laborItem<%=i%>" name="laborItem<%=i%>" value="<%=util.checkNull(lbrBeanObj.getStrLbrItmNum()) %>" style="width:97%" readonly></td><td style="padding:0px;padding-top:8px;"><img src="<%=imgSrc1%>" alt='' height='21' onClick="openLaborItemLov('laborItemDiv','<%=i%>')"></td> --%>
					<select id="laborItem<%=i%>" name="laborItem<%=i%>" style="width:250px;" onchange="fnValidateMachModfctn('<%=i%>')"> 
					<option value="">Select</option>
					<%
					if(lstItems!=null && lstItems.size()>0){
						for(int x=0;x<lstItems.size();x++){
							strSelect="";
							CanonE307DebriefItemLov laborLovObj = (CanonE307DebriefItemLov)lstItems.get(x);
							if(util.checkNull(laborLovObj.getStrItmNm()).equals(lbrBeanObj.getStrLbrItmNum())){
								strSelect = "selected";
							}else{
								strSelect = "";
							}
						%>
							<option value="<%=util.checkNull(laborLovObj.getStrItmNm()) %>" <%=strSelect%>><%=util.checkNull(laborLovObj.getStrItmNm()) %>-<%=util.checkNull(laborLovObj.getStrItmDesc()) %></option>
						<%
						 }
					 }
					%>
					</select></td>
					<%
					 if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
						 strDate = request.getParameter("lbrStrtDt"+i)==null?util.checkNull(strDate):request.getParameter("lbrStrtDt"+i);
					}
					%>
					<td><input type="text" id="lbrStrtDt<%=i%>" name="lbrStrtDt<%=i%>" value="<%=util.checkNull(strDate)%>" style="width:98%" readOnly></td>
					<td nowrap>
						<%-- <select id="lbrStrtTm<%=i%>" name="lbrStrtTm<%=i%>" onchange="getLaborDuration('<%=i%>')"></select> --%>
						<select id="lbrStrtHr<%=i%>" name="lbrStrtHr<%=i%>" onchange="getLaborDuration('<%=i%>','R')" <%=strDsblFlg %>>
							<!--  <option value="" selected></option> -->
				 		  	<%
					 		 if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
					 			strHr = request.getParameter("lbrStrtHr"+i)==null?strHr:request.getParameter("lbrStrtHr"+i);
					 		 }
					 		for(String sHr: lsHr){
					 			String selFlg = "";
					 			if(sHr.equals(strHr)){
					 				selFlg = "selected";
					 			}else{
					 				selFlg ="";
					 			}
								%>
								<option value="<%=sHr%>" <%=selFlg %>><%=sHr %></option>
					 	<%	} %>
		             	</select>
				 		<select id="lbrStrtMn<%=i%>" name="lbrStrtMn<%=i%>" onchange="getLaborDuration('<%=i%>','R')" <%=strDsblFlg %>>
				 			<!-- <option value="" selected></option> -->
				 		  <%
					 		 if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
					 			strMin = request.getParameter("lbrStrtMn"+i)==null?strMin:request.getParameter("lbrStrtMn"+i);
					 		 }
				 			 for(String sMn: lsMn){
				 				 String selFlg = "";
				 				if(sMn.equals(strMin)){
				 					selFlg="selected";
				 				}else{
				 					selFlg = "";
				 				}
				 				%>
				 		      <option value="<%=sMn%>" <%=selFlg%>><%=sMn%></option>
				            <%} %>
				 		</select>
						<select id="strtAmPm<%=i%>" name="strtAmPm<%=i%>" onchange="getLaborDuration('<%=i%>','R')" <%=strDsblFlg %>>
						 <!-- <option value="" selected></option> -->
							<%
					 		 if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
					 			strAmPm = request.getParameter("strtAmPm"+i)==null?strAmPm:request.getParameter("strtAmPm"+i);
					 		 }
							if("AM".equals(strAmPm)){ 
							%>	
								<option value="AM" selected>AM</option>
								<option value="PM">PM</option>
							<% }else if("PM".equals(strAmPm)){%>
								<option value="AM">AM</option>
					 		  	<option value="PM" selected>PM</option>
				 		  	<%}else{ %>
				 				<option value="AM">AM</option>
								<option value="PM">PM</option>
				 		  <% }%> 
				 		</select>
					</td>
					<input type="hidden" name="lStrtTmSel<%=i%>" id="lStrtTmSel<%=i%>" value="<%=util.checkNull(lbrBeanObj.getStrStartTm()) %>" />
					<%
					 if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
						 strEndDate = request.getParameter("lbrEndDt"+i)==null?util.checkNull(strEndDate):request.getParameter("lbrEndDt"+i);
					 }
					%>
					<td><input type="text" id="lbrEndDt<%=i%>" name="lbrEndDt<%=i%>" value="<%=util.checkNull(strEndDate)%>" style="width:98%" readOnly></td>
					<td nowrap>
						<select id="lbrEndHr<%=i%>" name="lbrEndHr<%=i%>" onchange="getLaborDuration('<%=i%>','R')" <%=strDsblFlg %>>
							 <!--  <option value="" selected></option>  -->
					 		  <%
					 		 if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
					 			strEndHr = request.getParameter("lbrEndHr"+i)==null?strEndHr:request.getParameter("lbrEndHr"+i);
					 		 }
					 		for(String sHr: lsHr){
					 			String selFlg = "";
					 			if(sHr.equals(strEndHr)){	
					 				selFlg="selected";
					 			}else{
					 				selFlg="";
					 			}
							%>
							 <option value="<%=sHr%>" <%=selFlg %>><%=sHr %></option>
							<%} %>
		             	</select>
				 		<select id="lbrEndMn<%=i%>" name="lbrEndMn<%=i%>" onchange="getLaborDuration('<%=i%>','R')" <%=strDsblFlg %>>
				 		<!-- <option value="" selected></option> -->
				 		  <%
					 		 if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
					 			strEndMn = request.getParameter("lbrEndMn"+i)==null?strEndMn:request.getParameter("lbrEndMn"+i);
					 		 }
				 			 for(String sMn: lsMn){
				 				String selFlg = "";

				 				if(sMn.equals(strEndMn)){
				 					selFlg="selected";
				 				}else{
				 					selFlg="";
				 				}
				 				%>
				 		      	<option value="<%=sMn%>" <%=selFlg %>><%=sMn%></option>
				            <%}%>
				 		</select>					
						<select id="endAmPm<%=i%>" name="endAmPm<%=i%>" onchange="getLaborDuration('<%=i%>','R')" <%=strDsblFlg %>>	
						 	<!-- <option value="" selected></option> -->
							<%
							 if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
								 strEndAmPm = request.getParameter("endAmPm"+i)==null?strEndAmPm:request.getParameter("endAmPm"+i);
							 }
							if("AM".equals(strEndAmPm)){ %>	
								<option value="AM" selected>AM</option>
								<option value="PM">PM</option>
							<% }else if("PM".equals(strEndAmPm)){%>
								<option value="AM">AM</option>
					 		  	<option value="PM" selected>PM</option>
				 		  	<%}else{ %>
				 				<option value="AM">AM</option>
								<option value="PM">PM</option>
				 		  <% }%> 
				 		</select>
					<input type="hidden" id="lEndTmSel<%=i%>" name="lEndTmSel<%=i%>" value="<%=util.checkNull(lbrBeanObj.getStrEndTm())  %>"></td>
					<td><input type="text" id="laborDuration<%=i%>" name="laborDuration<%=i%>" value="<%=lbrBeanObj.getLbrDuration()%>"  style="width:98%" <%=rdOnlyFlg %>></td>
					<td><input type="text" id="laborMod<%=i%>" name="laborMod<%=i%>" value="<%=util.checkNull(lbrBeanObj.getStrModNum())%>"  style="width:97%" readonly></td><td style="padding:0px;padding-top:8px;"><img src="<%=imgSrc1%>" alt='' height='21' onClick="fnOpenModLov('laborItemDiv','<%=i%>','MODNUM','LABOR')"></td>
					<td><input type="text" id="laborSerNumber<%=i%>" name="laborSerNumber<%=i%>" value="<%=util.checkNull(lbrBeanObj.getStrSerialNbr()) %>" style="width:98%" readonly></td>
					<td><input type="text" id="laborModItem<%=i%>" name="laborModItem<%=i%>" value="<%=util.checkNull(lbrBeanObj.getStrModItm())%>" style="width:98%" readonly></td>
					<td><input type="text" id="laborComments<%=i%>" name="laborComments<%=i%>" value="<%=util.checkNull(lbrBeanObj.getStrComments())%>" style="width:98%" readonly></td>
				</tr>
			<%
				}
			}else{
				lbrSize=1;
				al.add("lbrStrtDt0");
				al.add("lbrEndDt0");
			%>
				<tr id='laborRow0'>
					<input type="hidden" id="rowId" name="rowId" value="0">
					<input type="hidden" id="fsrVstTmPk0" name="fsrVstTmPk0" value="">
					<input type="hidden" id="billTpCd0" name="billTpCd0" value="">
					<input type="hidden" id="lbrDrtn0" name="lbrDrtn0" value="">
					<input type="hidden" id="lbrChrgFlg0" name="lbrChrgFlg0" value="">
					<input type="hidden" id="dtChkFlg" name="dtChkFlg" value="Y">
					<input type="hidden" id="laborItmVal0" name="laborItmVal0" value="">
					<td><input type=radio name="laborSelect" id="laborSelect0" value="0" style="border:0px;"></td>
					<td id="lbrItmLovTd0" nowrap width="8%">
					<select id="laborItem0" name="laborItem0" style="width:250px;" onchange="fnValidateMachModfctn('0')"> 
					<option value="">Select</option>
					<%
					if(lstItems!=null && lstItems.size()>0){
						for(int x=0;x<lstItems.size();x++){
							strSelect="";
							CanonE307DebriefItemLov laborLovObj = (CanonE307DebriefItemLov)lstItems.get(x);
						%>
							<option value="<%=util.checkNull(laborLovObj.getStrItmNm()) %>" ><%=util.checkNull(laborLovObj.getStrItmNm()) %> - <%=util.checkNull(laborLovObj.getStrItmDesc()) %></option>
						<%
						 }
					 }
					%>
					</select>
					</td>
					<!-- <td><input type="text" id="laborDescription0" name="laborDescription0" value="" style="width:98%" readonly></td> -->
					<td><input type="text" id="lbrStrtDt0" name="lbrStrtDt0" class="datePicker" value="" style="width:98%"></td>
					<td nowrap>
					<select id="lbrStrtHr0" name="lbrStrtHr0" onchange="getLaborDuration('0','R')">
					 <!-- <option value="" selected></option>  -->
				 		  <%
					 		for(String sHr: lsHr){
				            %>
				             <option value="<%=sHr%>"><%=sHr %></option>
				            <%} %>
		             	</select>
				 		<select id="lbrStrtMn0" name="lbrStrtMn0" onchange="getLaborDuration('0','R')">
				 		 <!-- <option value=""></option> -->
				 		  <%
				 			 for(String sMn: lsMn){
				 		  %>
				 		      <option value="<%=sMn%>"><%=sMn %></option>
				            <%} %>
				 		</select>
						<select id="strtAmPm0" name="strtAmPm0" onchange="getLaborDuration('0','R')">	
						<!-- <option value="" ></option> -->
							<option value="AM">AM</option>
				 		  	<option value="PM">PM</option>
				 		</select>
					</td>
					<input type="hidden" name="lStrtTmSel0" id="lStrtTmSel0" value="" />
					<td><input type="text" id="lbrEndDt0" name="lbrEndDt0" value="" style="width:98%"></td>
					<td nowrap>
					<!-- <select id="lbrEndTm0" name="lbrEndTm0" onchange="getLaborDuration('0')"></select> -->
						<select id="lbrEndHr0" name="lbrEndHr0" onchange="getLaborDuration('0','R')">
						<!-- <option value=""></option> -->
				 		  <%
					 		for(String sHr: lsHr){
				            %>
				             <option value="<%=sHr%>"><%=sHr %></option>
				            <%} %>
		             	</select>
				 		<select id="lbrEndMn0" name="lbrEndMn0" onchange="getLaborDuration('0','R')">
				 		  <%
				 			 for(String sMn: lsMn){
				 		  %>
				 		      <option value="<%=sMn%>"><%=sMn %></option>
				            <%} %>
				 		</select>					
						<select id="endAmPm0" name="endAmPm0" onchange = "getLaborDuration('0','R')">	
							<!-- <option value=""></option> -->
							<option value="AM">AM</option>
				 		  	<option value="PM">PM</option>
				 		</select>
					<input type="hidden" id="lEndTmSel0" name="lEndTmSel0" value=""></td>
					<td><input type="text" id="laborDuration0" name="laborDuration0" value="0.0"  style="width:98%" readonly></td>
					<td><input type="text" id="laborMod0" name="laborMod0" value=""  style="width:97%" readonly></td><td style="padding:0px;padding-top:8px;"><img src="<%=imgSrc1%>" alt='' height='21' onClick="fnOpenModLov('laborItemDiv','0','MODNUM','LABOR')"></td>
					<td><input type="text" id="laborSerNumber0" name="laborSerNumber0" value="" style="width:98%" readonly></td>
					<td><input type="text" id="laborModItem0" name="laborModItem0" value="" style="width:98%" readonly></td>
					<td><input type="text" id="laborComments0" name="laborComments0" value="" style="width:98%" readonly></td>
				</tr>			
			<%
			}
			%>
			<input type="hidden" name="labRecSize" id="labRecSize" value="<%=lbrSize%>">
		</table>
 </div> 
	<%
	   ArrayList partsRepData = new ArrayList();
	   if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
		   		int prtCnt = 0;
			    String partRecSize = request.getParameter("partRecSize");
			    if(partRecSize!=null && !("".equals(partRecSize))&&!("null".equals(partRecSize))){
				 	prtCnt=Integer.parseInt(partRecSize);
			    }
			   System.out.println("prtCnt: "+ prtCnt); 
			 for(int i=0;i<prtCnt;i++){
				 CanonE307DebriefPartsRec prtObj = new CanonE307DebriefPartsRec();
				 prtObj.setStrFsrUsgPk(request.getParameter("fsrUsgPk"+i));
				 prtObj.setStrSvcPrtChrgFlg(request.getParameter("prtsChrgFlg"+i));
 				 String prtCd = util.checkNull(request.getParameter("prtCode"+i));
				 String itmNum ="";
				 if(prtCd!=null && prtCd.length()>0){
					itmNum = prtCd.split(" - ")[0].trim();
				 }
				 System.out.println("itmNum: "+ itmNum + "prtCd : "+prtCd);
				 prtObj.setStrPartItmNum(itmNum);
				 prtObj.setStrPartItmDesc(request.getParameter("prtCode"+i));
				 prtObj.setStrSrvcDt(request.getParameter("partServiceDate"+i));
				 String strPrtQty =request.getParameter("partQty"+i);
				 System.out.println("strPrtQty:"+strPrtQty);
				 int prtQty=0;
				 if(strPrtQty==null || "null".equalsIgnoreCase(strPrtQty) || "".equals(strPrtQty)){
					 //Do Nothing
				 }else{
					 prtQty = Integer.parseInt(strPrtQty);
				 }
				 prtObj.setQuantity(prtQty);
				 prtObj.setStrUom(request.getParameter("partsUOM"+i));
				 prtObj.setStrMod(request.getParameter("partMod"+i));
				 prtObj.setStrSrlNum(request.getParameter("partSerlNumber"+i));
				 prtObj.setStrModItm(request.getParameter("partModItem"+i));
				 prtObj.setStrPrtComments(request.getParameter("partComments"+i));
				 partsRepData.add(prtObj);
			 }
	   }else{
			partsRepData = (ArrayList)objDeb.getDebriefPartsInfo(strTaskNumber);
	   }
		String strPartsStyle = "display: none;";
		int pSize =0;
		/*if(partsRepData!=null && partsRepData.size()>5){	
			strPartsStyle="display: none; height:  300px;  overflow-y:auto; ";
		}*/
	%>	
	<div id="partstable" style="<%=strPartsStyle%>">
		<table id="parts_table" width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<th width="1%"></th>
				<th width="12%">Part Item</th>
				<!-- <th width="12%">Description</th> -->
				<th width="7%">Service Date</th>
				<th width="4%">Quantity</th>
				<th width="4%">UOM</th>
				<th width="10%" colspan=2>MOD#</th>
				<th width="8%">Serial Number</th>
				<th width="8%">Mod Item</th>
				<th width="8%">Mod Model</th>
				<th width="10%">Error Message</th>
			</tr>
	<% 
			if(partsRepData!=null && partsRepData.size()>0){
				pSize = partsRepData.size();
				for(int i=0;i<partsRepData.size();i++){
					CanonE307DebriefPartsRec prtBeanObj = (CanonE307DebriefPartsRec)partsRepData.get(i);
	%>
			<tr id="partsRow<%=i%>">
				<input type="hidden" name="fsrUsgPk<%=i%>" id="fsrUsgPk<%=i%>" value="<%=prtBeanObj.getStrFsrUsgPk() %>">
				<input type="hidden" name="prtsChrgFlg<%=i%>" id="prtsChrgFlg<%=i%>" value="<%=prtBeanObj.getStrSvcPrtChrgFlg() %>">
				<input type="hidden" name="partsItmVal<%=i%>" id="partsItmVal<%=i%>" value="<%=util.checkNull(prtBeanObj.getStrPartItmNum()) %>">
				<input type="hidden" name="partsItmDesc<%=i%>" id="partsItmDesc<%=i%>" value="<%=util.checkNull(prtBeanObj.getStrPartItmDesc()) %>">
				<td><input type=radio name="partsSelect" id="partsSelect<%=i%>" value="<%=i%>" disabled style="border:0px;"></td>
				<td id='partsItmLovTd<%=i%>' nowrap><select id="partItem<%=i%>" name="partItem<%=i%>" onchange="fnSetPrtCnt(<%=i%>)" style="width:250px;"> 
				<option value="<%=util.checkNull(prtBeanObj.getStrPartItmDesc()) %>" selected><%=util.checkNull(prtBeanObj.getStrPartItmDesc()) %></option>
				<%
					String partSel ="";
					if(lstPartItems!=null && lstPartItems.size()>0){
						for(int k=0;k<lstPartItems.size();k++){
							CanonE307DebriefItemLov partBeanObj = (CanonE307DebriefItemLov)lstPartItems.get(k);	
							if(partBeanObj.getStrItmNm().equals(prtBeanObj.getStrPartItmNum())){
								partSel="selected";
							}else{
								partSel="";
							}
				%>
					<option value="<%=util.checkNull(partBeanObj.getStrItmNm()) %> - <%=util.checkNull(partBeanObj.getStrItmDesc()) %>" <%=partSel%>><%=util.checkNull(partBeanObj.getStrItmNm()) %> - <%=util.checkNull(partBeanObj.getStrItmDesc()) %></option>
					
					<%
						}
					}
				%>
				 </select>
				 <input name='prtCode<%=i%>' id='prtCode<%=i%>' type='hidden' value='' />
				</td>
				<%-- <td><input type="text" id="partDescription<%=i%>" name="partDescription<%=i%>" value="<%=util.checkNull(prtBeanObj.getStrPartItmDesc()) %>" style="width:98%" readonly></td> --%>
				<td><input type="text" id="partServiceDate<%=i%>" name="partServiceDate<%=i%>" value="<%=util.checkNull(prtBeanObj.getStrSrvcDt()) %>" class="datePicker" readOnly style="width:98%"></td>
				<td><input type="text" id="partQty<%=i%>" name="partQty<%=i%>" value="<%=prtBeanObj.getQuantity() %>" style="width:98%;" class='autoInteger' onchange='fnShowAddendum(<%=i%>)'></td>
				<td><select id="partsUOM<%=i%>" name="partsUOM<%=i%>" class="dis"></select>
				<input type="hidden" name='partsUOMSel<%=i%>' id='partsUOMSel<%=i%>' value='<%=util.checkNull(prtBeanObj.getStrUom()) %>' style="width:98%"></td>
				<td><input type="text" id="partMod<%=i%>" name="partMod<%=i%>" value="<%=util.checkNull(prtBeanObj.getStrMod()) %>" style="width:98%" readonly></td><td style="padding:0px;padding-top:8px;"><img src="<%=imgSrc1%>" alt='' height='21' onClick="fnOpenModLov('laborItemDiv','<%=i%>','MODNUM','PARTS')"></td>				
				<td><input type="text" id="partSerlNumber<%=i%>" name="partSerlNumber<%=i%>" value="<%=util.checkNull(prtBeanObj.getStrSrlNum()) %>" style="width:98%" readonly></td>
				<td><input type="text" id="partModItem<%=i%>" name="partModItem<%=i%>" value="<%=util.checkNull(prtBeanObj.getStrModItm()) %>" style="width:98%" readonly></td>
				<td><input type="text" id="partModMdl<%=i%>" name="partModMdl<%=i%>" value="" style="width:100%"></td>
				<td><input type="text" name="partComments<%=i%>" id="partComments<%=i%>" value="<%=util.checkNull(prtBeanObj.getStrPrtComments()) %>" style="width:98%" readonly></td>
			</tr>
		<%	}
		}else{
			pSize=1;
		%>
			<tr id="partsRow0">
				<input type="hidden" name="fsrUsgPk0" id="fsrUsgPk0" value="">
				<input type="hidden" name="prtsChrgFlg0" id="prtsChrgFlg0" value="">
				<input type="hidden" name="partsItmVal0" id="partsItmVal0" value="">
				<input type="hidden" name="partsItmDesc0" id="partsItmDesc0" value="">
				<td><input type=radio name="partsSelect" id="partsSelect0" value="0" style="border:0px;"></td>
				<td id='partsItmLovTd0' nowrap><select id="partItem0" name="partItem0" onchange="fnSetPrtCnt(0)" style="width:250px;">  
				<option value="">Select</option>
				<%
					if(lstPartItems!=null && lstPartItems.size()>0){
						for(int k=0;k<lstPartItems.size();k++){
							CanonE307DebriefItemLov partBeanObj = (CanonE307DebriefItemLov)lstPartItems.get(k);	
					%>
					<option value="<%=util.checkNull(partBeanObj.getStrItmNm()) %> - <%=util.checkNull(partBeanObj.getStrItmDesc())%>" ><%=util.checkNull(partBeanObj.getStrItmNm()) %> - <%=util.checkNull(partBeanObj.getStrItmDesc()) %></option>
					
					<%
						}
					}
				%>				
				</select>
				<input name='prtCode0' id='prtCode0' type='hidden' value='' />
				</td>
				<!-- <td><input type="text" id="partDescription0" name="partDescription0" value="" style="width:98%" readonly></td> -->
				<td><input type="text" id="partServiceDate0" name="partServiceDate0" value="<%=dateInString%>" class="datePicker" readOnly style="width:98%"></td>
				<td><input type="text" id="partQty0" name="partQty0" value="" style="width:98%;" class='autoInteger' onchange='fnShowAddendum(0)' /></td>
				<td><select id="partsUOM0" name="partsUOM0" class="dis"></select>
				<input type="hidden" name='partsUOMSel0' id='partsUOMSel0' value='EA' style="width:98%"></td>
				<td><input type="text" id="partMod0" name="partMod0" value="" style="width:98%" readonly></td><td style="padding:0px;padding-top:8px;"><img src="<%=imgSrc1%>" alt='' height='21' onClick="fnOpenModLov('laborItemDiv','0','MODNUM','PARTS')"></td>				
				<td><input type="text" id="partSerlNumber0" name="partSerlNumber0" value="" style="width:98%" readonly></td>
				<td><input type="text" id="partModItem0" name="partModItem0" value="" style="width:98%" readonly></td>
				<td><input type="text" id="partModMdl0" name="partModMdl0" value="" style="width:98%"></td>
				<td><input type="text" name="partComments0" id="partComments0" value="" style="width:98%" readonly></td>
			</tr>		
		<%			
		}
	%>
		<input type="hidden" name="partRecSize" id="partRecSize" value="<%=pSize%>">
	</table>
	</div>
	<%
		List expRepData =new ArrayList();
	   if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
	   		int expCnt = 0;
		    String expenseRecSize = request.getParameter("expenseRecSize");
		    if(expenseRecSize!=null && !("".equals(expenseRecSize))&&!("null".equals(expenseRecSize))){
		    	expCnt=Integer.parseInt(expenseRecSize);
		    }
		    System.out.println("expCnt: "+ expCnt);
		 for(int i=0;i<expCnt;i++){
			 CanonE307DebriefExpensesRec expObj = new CanonE307DebriefExpensesRec();
			 expObj.setStrExpenseLineId(request.getParameter("fsrExpPk"+i));
			 String expenseItemSel = request.getParameter("expItemSel"+i);
			 System.out.println("expenseItemSel: "+ expenseItemSel);
			 if(expenseItemSel!=null && expenseItemSel!="null" && expenseItemSel!=""){
			 	expObj.setStrExpItmNum(expenseItemSel.split("-")[0].trim());
			 }
			 expObj.setStrExpItmDesc(request.getParameter("expenseDescription"+i));
			 expObj.setStrExpSrvcDt(request.getParameter("expServiceDate"+i));
			 String strExpQty =request.getParameter("expenseQty"+i);
			 System.out.println("strExpQty:"+strExpQty);
			 int expQty=0;
			 if(strExpQty==null || "null".equalsIgnoreCase(strExpQty) || "".equals(strExpQty)){
				 //Do Nothing
			 }else{
				 expQty = Integer.parseInt(strExpQty);
			 }
			 System.out.println("expQty :"+expQty);
			 expObj.setExpQty(expQty);
			 expObj.setStrExpUom(request.getParameter("expenseUOM"+i));
			 expObj.setStrAttribute2(request.getParameter("expenseAmt"+i));
			 expObj.setStrExpComments(request.getParameter("expenseComments"+i));
			 expRepData.add(expObj);
		 }
	   }else{
			expRepData = objDeb.getDebriefExpenseInfo(strTaskNumber);
	   }
	int expSize=0;
		String strExpenseStyle = "display: none;";
/*		if(expRepData!=null && expRepData.size()>5){	
			strExpenseStyle="height:  300px;  overflow-y:auto; ";
		} */
	%>		
	<div id="expensetable" style="<%=strExpenseStyle%>">
		<table id="expense_table">
			<tr>
				<th width="1%"></th>
				<th width="15%">Expense Item</th>
				<th width="10%">Service Date</th>
				<th width="2%">Quantity</th>
				<th width="10%">Amount</th>
				<th width="6%">UOM</th>				
				<th width="25%">Error Message</th>
			</tr>
			<%
			if(expRepData!=null && expRepData.size()>0){
				System.out.println("Size expenses: " + expRepData.size());
				expSize = expRepData.size();
				for(int i=0;i<expRepData.size();i++){
					CanonE307DebriefExpensesRec expBeanObj = (CanonE307DebriefExpensesRec)expRepData.get(i);
				%>
				<tr id="expenseRow<%=i%>">
					<input type="hidden" name="fsrExpPk<%=i%>" id="fsrExpPk<%=i%>" value="<%=expBeanObj.getStrExpenseLineId() %>">
					<input type="hidden" name="expenseItemVal<%=i%>" id="expenseItemVal<%=i%>" value="<%=util.checkNull(expBeanObj.getStrExpItmNum()) %>">
					<td><input type=radio name="expenseSelect" id="expenseSelect<%=i%>" value="<%=i%>" disabled style="border:0px;"></td>
<%-- 					<td nowrap><input type="text" id="expenseItem<%=i%>" name="expenseItem<%=i%>" value="<%=util.checkNull(expBeanObj.getStrExpItmNum()) %>" readOnly  style="width:90%"></td><td><img src="<%=imgSrc1%>" alt='' height='21' onClick="openExpenseItemLov('expenseItemDiv','<%=i%>')"></td> --%>
					<td id="expnsItmTd<%=i%>">
					<select id="expItemSel<%=i%>" name="expItemSel<%=i%>" onchange="fnSetExpUOMCnt('<%=i%>')" >
					<option value="">Select</option>
					<%
						String expSel="";
						if(lstExpItems!=null && lstExpItems.size()>0){
							for(int y=0;y<lstExpItems.size();y++){
								CanonE307DebriefItemLov expBnObj = (CanonE307DebriefItemLov)lstExpItems.get(y);	
								//System.out.println("Item1: "+ expBnObj.getStrItmNm()+" iTem2: "+expBeanObj.getStrExpItmNum());
								if(expBnObj.getStrItmNm().equals(expBeanObj.getStrExpItmNum())){
									expSel = "selected";
								}else{
									expSel = "";
								} 
						%>
							<option value="<%=util.checkNull(expBnObj.getStrItmNm()) %>-<%=util.checkNull(expBnObj.getStrUom()) %>-<%=util.checkNull(expBnObj.getStrItmCnt())%>" <%=expSel%>><%=util.checkNull(expBnObj.getStrItmNm()) %> - <%=util.checkNull(expBnObj.getStrItmDesc()) %></option>							
						<%
							}
						}
					%>
					</select></td>
					<%-- <td><input type="text" id="expenseDescription<%=i%>" name="expenseDescription<%=i%>" value="<%=util.checkNull(expBeanObj.getStrExpItmDesc()) %>" style="width:98%"></td> --%>
					<td><input type="text" id="expServiceDate<%=i%>" name="expServiceDate<%=i%>" value="<%=util.checkNull(expBeanObj.getStrExpSrvcDt()) %>" class="datePicker" readOnly style="width:98%"></td>
					<td><input type="text" id="expenseQty<%=i%>" name="expenseQty<%=i%>" value="<%=expBeanObj.getExpQty() %>" class='autoInteger' style="width:98%"></td>
					<input type="hidden" name='expenseUOMSel<%=i%>' id='expenseUOMSel<%=i%>' value="<%=util.checkNull(expBeanObj.getStrExpUom()) %>" ></td>
					<td><input type="text" name="expenseAmt<%=i%>" id="expenseAmt<%=i%>" value="<%=util.checkNull(expBeanObj.getStrAttribute2()) %>" style="width:98%;" class='autoDecimal'></td>
					<%--<td> <select id="expenseUOM<%=i%>" name="expenseUOM<%=i%>" style="width:98%"></select> --%>
					<td><input type="text" id="expenseUOM<%=i%>" name="expenseUOM<%=i%>" style="width:98%" value="<%=util.checkNull(expBeanObj.getStrExpUom()) %>" readonly class='dis'></td>
					<td><input type="text" id="expenseComments<%=i%>" name="expenseComments<%=i%>" value="<%=util.checkNull(expBeanObj.getStrExpComments())%>" style="width:98%" readonly></td>
				</tr>
			<%
				}
			}else{
				expSize=1;
			%>
				<tr id="expenseRow0">
					<input type="hidden" name="fsrExpPk0" id="fsrExpPk0" value="">
					<input type="hidden" name="expenseItemVal0" id="expenseItemVal0" value="">
					<td><input type=radio name="expenseSelect" id="expenseSelect0" value="0" style="border:0px;"></td>
					<%-- <td nowrap><input type="text" id="expenseItem0" name="expenseItem0" value="" readOnly style="width:98%"></td><td><img src="<%=imgSrc1%>" alt='' height='21' onClick="openExpenseItemLov('expenseItemDiv','0')"></td> --%>
					<td id="expnsItmTd0">
					<select id="expItemSel0" name="expItemSel0" onchange="fnSetExpUOMCnt('0')" >
					<option value="">Select</option>
					<%
						if(lstExpItems!=null && lstExpItems.size()>0){
							for(int y=0;y<lstExpItems.size();y++){
								CanonE307DebriefItemLov expBnObj = (CanonE307DebriefItemLov)lstExpItems.get(y);	
	
						%>
							<option value='<%=expBnObj.getStrItmNm() %>-<%=expBnObj.getStrUom()%>-<%=expBnObj.getStrItmCnt()%>'><%=expBnObj.getStrItmNm() %> - <%=expBnObj.getStrItmDesc()%></option>							
						<%
							}
						}
					%>
					</select></td>
					<!-- <td><input type="text" id="expenseDescription0" name="expenseDescription0" value="" style="width:98%"></td> -->
					<td><input type="text" id="expServiceDate0" name="expServiceDate0" value="<%=dateInString%>" class="datePicker" readOnly style="width:98%"></td>
					<td><input type="text" id="expenseQty0" name="expenseQty0" value="" class='autoInteger' style="width:98%"></td>
					<input type="hidden" name='expenseUOMSel0' id='expenseUOMSel0' value="EA"  ></td>
					<td><input type="text" name="expenseAmt0" id="expenseAmt0" value="" style="width:98%;" class='autoDecimal'></td>
					<!-- <td><select id="expenseUOM0" name="expenseUOM0" style="width:98%"></select></td> -->
					<td><input type="text" id="expenseUOM0" name="expenseUOM0" style="width:98%" value="" readonly class='dis'></td>
					<td><input type="text" id="expenseComments0" name="expenseComments0" value="" style="width:98%" readonly></td>
				</tr>			
			<%				
			}
			%>
			<input type="hidden" name="expenseRecSize" id="expenseRecSize" value="<%=expSize%>">			
		</table>
	</div>
	</div>
		<div class="notes">
			<div class="info-div02">
				<table class="border-no" width="100%" cellspacing="0">
					<tr valign="top">
						<td width="50%">
							<table width="100%" cellspacing="1">
								<tr>
									<th colspan="3">View Notes</th>
								</tr>
								<tr>
									<td width="15%"><b>Type:</b></td>
									<td width="23%"><b>Date:</b></td>
									<td width="62%"><b>Message:</b></td>
								</tr>
								<%
								ArrayList<CanonE307DebriefNotesDetailsRec> viewNotesRepData = objDeb.getDebriefNotesInfo(strTaskNumber);
								if(viewNotesRepData!=null && viewNotesRepData.size()>0){
									System.out.println("Size View Notes: " + viewNotesRepData.size());
									for(int i=0;i<viewNotesRepData.size();i++){
										CanonE307DebriefNotesDetailsRec notesBeanObj = (CanonE307DebriefNotesDetailsRec)viewNotesRepData.get(i);
								%>								
								<tr>
									<td><%=util.checkNull(notesBeanObj.getStrNoteTp()) %></td>
									<%
									String ntDt = "";
									if(notesBeanObj.getStrNoteDt()!=null && notesBeanObj.getStrNoteDt()!="null" && notesBeanObj.getStrNoteDt().length()>0){
										ntDt = utilObj.getTmZone(objBean.getStrAttribute6(), objBean.getStrAttribute7(), notesBeanObj.getStrNoteDt());
									}
										
									%>
									<td nowrap><%=util.checkNull(ntDt) %></td>
									<td><%=util.checkNull(notesBeanObj.getStrNoteText()) %></td>								
								</tr>
								<%
									}
								}
								%>
						</table>
						</br>

								<%
									ArrayList rdLst = objDeb.getSvcMtrDet(objBean.getStrSRNumber(), objBean.getStrTaskNumber(), objBean.getStrTaskStatus(), objBean.getStrAttribute1());
								    ArrayList tpLst =  objDeb.getMtrRdType("");
								    String strBgInClr ="";
								    String strBgOutCLr="";
									if(rdLst!=null && rdLst.size()>0){
										String mtrSubmitFlg="";
								%>
								<table id="mtrRdTbl" width="100%" cellspacing="1">
								<input type="hidden" id='mtrInReadDate' name='mtrInReadDate' value=''>
								<input type="hidden" id='mtrOutReadDate' name='mtrOutReadDate' value=''>
									<tr>
										<th colspan="5">Meters</th>
									</tr>
									<tr>
										<td width="25%">Name</td>
										<td nowrap width="20%">In Meter</td>
										<td width="15%">Type</td>	
										<td nowrap width="20%">Out Meter</td>
										<td width="15%">Type</td>																													
									</tr>
								<%									
										for(int y=0;y<rdLst.size();y++){
											strBgInClr="";
											strBgOutCLr="";
											CanonE307SvcMtrRdsRec rdObj = (CanonE307SvcMtrRdsRec)rdLst.get(y);
											System.out.println("mandatorycntr: "+rdObj.getMtrMndtrFlg()+" MtrSubmitFlg: "+rdObj.getMtrSubmitFlg());
											if(("Y".equals(rdObj.getMtrMndtrFlg()))&&("Y".equals(rdObj.getMtrSubmitFlg()))){
												if("In Route".equals(objBean.getStrTaskStatus())){
													strBgInClr = "background-color:#FFFFB2;";
													strBgOutCLr ="background-color:#cccccc;";
													System.out.println("Task Sts: " + objBean.getStrTaskStatus());
												}else{
													strBgInClr = "background-color:#FFFFB2;";
													strBgOutCLr = "background-color:#FFFFB2;";
												}
											}else if("N".equals(rdObj.getMtrSubmitFlg())){
												strBgInClr="background-color:#cccccc;";
												strBgOutCLr ="background-color:#cccccc;";
											}else{
												if("In Route".equals(objBean.getStrTaskStatus())){
													strBgOutCLr="background-color:#cccccc;";
												}else{
													strBgInClr="";
													strBgOutCLr="";
												}
											}

											String outMtrRd = request.getParameter("outMtrRd"+y)==null?util.checkNull(rdObj.getOutMtrRdCnt()):request.getParameter("outMtrRd"+y);
											if(outMtrRd!=null && outMtrRd.length()<=0){
												outMtrRd ="0";
											}
											String inMtrRd = request.getParameter("inMtrRd"+y)==null?util.checkNull(rdObj.getInMtrRdCnt()):request.getParameter("inMtrRd"+y);
											if(inMtrRd!=null && inMtrRd.length()<=0){
												inMtrRd ="0";
											}	
											String inRdTp  = request.getParameter("inRdTp"+y)==null?util.checkNull(rdObj.getAttribute3()):request.getParameter("inRdTp"+y);
											String outRdTp  = request.getParameter("outRdTp"+y)==null?util.checkNull(rdObj.getDsMtrRdTpCd()):request.getParameter("outRdTp"+y);
											System.out.println("Task Sts: "+ objBean.getStrTaskStatus()+" strBgInClr: "+strBgInClr+" strBgOutCLr: "+strBgOutCLr+" Meter : "+rdObj.getMtrLbDescTxt());
											mtrSubmitFlg = rdObj.getMtrSubmitFlg();
								%>
									<tr>
									    <input type="hidden" id="mtrSubmtFlg" name="mtrSubmtFlg" value="<%=rdObj.getMtrSubmitFlg() %>">
									    <input type="hidden" id="inOutRdFlg" name="inOutRdFlg" value="<%=rdObj.getAttribute2() %>" >									
										<input type="hidden" id="svcPhysMtrPk<%=y%>" name="svcPhysMtrPk<%=y%>" value="<%=rdObj.getSvcPhysMtrPk() %>" />	
										<input type="hidden" id="machMstrPk<%=y%>" name="machMstrPk<%=y%>" value="<%=rdObj.getSvcMachMstrPk() %>" />
										<input type="hidden" id="exInMtrRd<%=y%>" name="exInMtrRd<%=y%>" value="<%=rdObj.getInMtrRdCnt()==null?"0":rdObj.getInMtrRdCnt() %>" />
										<input type="hidden" id="exOutMtrRd<%=y%>" name="exOutMtrRd<%=y%>" value="<%=rdObj.getOutMtrRdCnt()==null?"0":rdObj.getOutMtrRdCnt() %>" />
										<input type="hidden" id="mndtrFlg<%=y%>" name="mndtrFlg<%=y%>" value="<%=rdObj.getMtrMndtrFlg() %>>">
																					
										<td width="25%"><%=rdObj.getMtrLbDescTxt() %></td>
										<td width="20%"><input type="text" name="inMtrRd<%=y%>" id="inMtrRd<%=y%>"  style="width:98%;<%=strBgInClr%>" value="<%=inMtrRd %>" size="20" class="autoInteger" maxlength="10"></td>
										<td width="15%"><select id="inRdTp<%=y%>" name="inRdTp<%=y%>" style="width: 100px;<%=strBgInClr %>"  <%-- onChange="fnSetReadTypes('IN', '<%=y%>');" --%>>
										<option value="AC">ACTUAL</option>
									 <%-- 	<%
										String inMtrSelect ="";
										if(tpLst!=null && tpLst.size()>0){
											for(int x=0;x<tpLst.size();x++){
												CanonE307MtrRdTpeRec rdTpObj = (CanonE307MtrRdTpeRec)tpLst.get(x);
												if(rdTpObj.getStrMtrRdTpCd().equals(inRdTp)){
													inMtrSelect="selected";
												}else{
													inMtrSelect="";
												}
										%>
											<option value="<%=rdTpObj.getStrMtrRdTpCd() %>" <%=inMtrSelect%>><%=rdTpObj.getStrMtrRdTpNm() %></option>													
										<%
												}
											}
										%> --%>
										</select></td>
										<td width="20%"><input type="text" name="outMtrRd<%=y%>" id="outMtrRd<%=y%>" class="outRds autoInteger" value="<%=outMtrRd%>" style="width:98%;<%=strBgOutCLr%>"  size="20" maxlength="10"></td>
										<td width="15%"><select id="outRdTp<%=y%>" name="outRdTp<%=y%>"  class ="outRds" style="width: 100px;<%=strBgOutCLr %>" <%-- onChange="fnSetReadTypes('OUT', '<%=y%>'); --%>">
										<option value="AC">ACTUAL</option>
										<%-- <%
										String outMtrSelect = "";
										if(tpLst!=null && tpLst.size()>0){
											for(int k=0;k<tpLst.size();k++){
												CanonE307MtrRdTpeRec rdTpObj = (CanonE307MtrRdTpeRec)tpLst.get(k);
												if(rdTpObj.getStrMtrRdTpCd().equals(outRdTp)){
													outMtrSelect="selected";
												}else{
													outMtrSelect="";
												}
										%>
											<option value="<%=rdTpObj.getStrMtrRdTpCd() %>" <%=outMtrSelect%>><%=rdTpObj.getStrMtrRdTpNm() %></option>													
										<%
												}
											}
										%> --%>
										</select></td>
									</tr>			
								<%
										}
								%>
									<input type="hidden" id='mtrRdsSz' name='mtrRdsSz' value="<%=rdLst.size() %>">
									<!--  <tr><td colspan=5>&nbsp;</td></tr>  -->
									<%--  <%if("Y".equals(mtrSubmitFlg)){ %>
									<tr><td colspan=4>&nbsp;</td>
									<td nowrap><a class="btn disp_mode" href="javascript:void saveMtrRdsFrm()">Save Meter Reads</a></td></tr>
									<% }%>  --%>
								</table>
								<%
									}else{
										%>
											<input type="hidden" id="mtrSubmtFlg" name="mtrSubmtFlg" value="N">
										<%
									}
								%>
						</td>
						<td width="50%">
							<table width="100%" cellspacing="1">
								<tr>
									<th colspan="2">Create Notes</th>
								</tr>
								<tr>
									<td width="20%"><b>Type:</b></td>
									<td width="80%"><b>Message:</b></td>
								</tr>
								<tr>
									<td>
										<select name='sNoteType' id='sNoteType'>
										<%
					             		 ArrayList alNt = (ArrayList<CanonE307NoteTypeRec>) crDao.getNoteTypes();
							             if(alNt!=null && alNt.size()>0){
							             	for(int i=0;i<alNt.size();i++){
							             		CanonE307NoteTypeRec ntRec = (CanonE307NoteTypeRec)alNt.get(i);
							             		 String selFlg = "";
							             		 if(("Y".equals(strDbrfFlg)) && ("DE".equals(rFlg) || "ME".equals(rFlg))){
							             			String noteTpe = util.checkNull(request.getParameter("sNoteType"));
							             			//System.out.println("noteTpe: "+ noteTpe+" ntRec.getNoteTpCd(): "+ntRec.getNoteTpCd());
							             			if(noteTpe.length()>0 && noteTpe.equals(ntRec.getNoteTpCd())){
							             				selFlg ="selected";
							             			}
							             		 }else if("Y".equals(ntRec.getAsccDefFlg())){
							             			selFlg ="selected";
							             		 }
							             		%>	
							             			<option value="<%=ntRec.getNoteTpCd()%>" <%=selFlg%>><%=ntRec.getNoteTpDesc() %></option>   
							             		<%	
												}}%> 
							              
										</select>
									</td>
									<td><textarea id="noteDtls" name="noteDtls" rows="" cols=""><%=util.checkNull(request.getParameter("noteDtls")) %></textarea></td>
								</tr>
							</table>
						<br />
						<%
						CanonE307ServiceRequestDetailsDao detailObj = new CanonE307ServiceRequestDetailsDao();
						CanonE307FileUploadDownloadAPIUtil downloadObj = new CanonE307FileUploadDownloadAPIUtil();
						ArrayList<CanonE307AttFileRec> attList = new ArrayList<CanonE307AttFileRec>();
						attList = detailObj.getAttFileIds(util.checkNull(objBean.getStrSRNumber()), strSerNum,"");
						%>
							<table width="100%" cellspacing="1">
								<tr>
									<th>Attachments</th>
								</tr>
								<%
									if(attList!=null && attList.size()>0){
										for(CanonE307AttFileRec attObj: attList){
											System.out.println("file Id : "+attObj.getFileId());
											if(attObj.getFileId()>0){
												String fileName = downloadObj.downLoadAttachment(util.checkNull(objBean.getStrSRNumber()), strSerNum, loginUser, attObj.getFileId());
												%>
												<tr>
													<td><a href="javascript:void fnDownAttach('<%=fileName %>')"><%=attObj.getStrfileName()%></a></td>
												</tr>
												<%
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
								<tr>
									<td><b>File Upload:</b> <input type="text" name="fileNm" id="fileNm" value=""/>
									<img src="<%=imgSrc1%>"  height='18' onClick="fnUploadPOAttach();"/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<br/>
		<br/>
		<br/>
</form>
</div>	
</div>
<div id="laborItemDiv">
</div>
<div id="expenseItemDiv">
</div>
<div id="partsItemDiv">
</div>
</body>
 <script type="text/javascript">
 $(function() {
	 <% if(al!=null && al.size()>0){
		 for(int k=0;k<al.size();k++) { %>
	     	setDtPicker("<%=al.get(k)%>");
	 	<%}
		}
	 %>
	 if (isIE () && isIE () < 10) {
		 //Do Nothing
	 }else{
		  var partsRecSz = $('#partRecSize').val();
		  partsRecSz = parseInt(partsRecSz);
		  if(partsRecSz!=null && partsRecSz>0){
				 for( l=0;l<partsRecSz;l++) { 
					// console.log('Inside for loop: '+partsRecSz);
					 fnGetPartsLovSrch(l);
			 		}
				}
		 }

	 });

  function setDtPicker(eleId){
	  $("#"+eleId).datepicker({
		 // dateFormat: 'dd-M-yy',changeMonth: true, changeYear: true,
		 dateFormat: 'M dd yy',changeMonth: true, changeYear: true,
		  onSelect: function(dateText) {
		     var rowIdVal=  $($(this).closest("tr").find("input#rowId")).val() ;
		     getLaborDuration(rowIdVal, 'R');
		  }
		});
  }
 
 <%if("Y".equals(strRetVal)){  %>
		fnChkLstOpn();
 <%	}	%>
 function fnChkLstOpn(){
		var mName ="laborItemDiv";
		var serNum = $('#serialNumber').val();
		var tskNum = $('#debriefTsk').val();
		var fsrNum = $('#fsr').val();
		var userName = $('#userName').val();
		var tskSts = $('#tskSts').val();
	//	var urlDetail = 'canonE307DebriefInstlChkLstLov.jsp?Action=Open';
		var urlDetail = 'canonE307DebriefInstldChkLst.jsp?Action=Open';	
	
		var qStr = 'modalName='+ mName;
		qStr = qStr + '&serNum='+ encodeURIComponent(serNum);	
		qStr = qStr + '&tskNum='+ encodeURIComponent(tskNum);
		qStr = qStr + '&fsrNum='+ encodeURIComponent(fsrNum);
		qStr = qStr + '&userName='+ encodeURIComponent(userName);
		qStr = qStr + '&tskSts='+ encodeURIComponent(tskSts);
		
		   modelName = "#"+mName;
		   showPleaseWait();
	   $(modelName).dialog({
					height: 550,
					title: "CONFIGURATION CHECKLIST",
					modal: true ,
			autoOpen :false,
					 width: 650,		
			 resizable: true     
				});

		  $.ajax({
			url: urlDetail,
			cache: false,
			data: qStr,
			//type: "POST",
				success: function(data){    
				hidePleaseWait();
			  $(modelName).html("");          
			  $(modelName).html(data);
					}             
				});
		  $(modelName).dialog("open");
		  $(".ui-dialog-titlebar").addClass("hd");	
 }
 
 function fnChngImg(){
//	 var img = "<%=ctxPath%>/e307/images/Minus-enabl.png";
//	 $('#mChng').attr('src', img);
	 $('#imgDiv').removeClass('disabled');

 }
 function setCallSelects(s){
		//showPleaseWait();
	    var eo="";
	    var sc="";
	    var optTxt="";
	    var optVal="";
	    var sId="";
	    var le="ListEntry";
	    var wc="";
	    var selVal="";
		
	    if(s=="pc"){
	      sId="#problemCode";
	      eo="pblmType";	
	      sc="pblmCode";
	      optTxt="pblmCode";
	      optVal="pblmCode";
	      selVal=$('#pbCd').val();
	    }else if(s=="cc"){
	      sId="#corrCode";
	      eo="correctionType";	
	      sc="correctionCode";
	      optTxt="correctionCode";
	      optVal="correctionCode";	
	      selVal=$('#cCode').val();
		}else if(s=="rc"){
	      sId="#resCode";
	      eo="reasonType";	
	      sc="reasonCode;reasonCodeDesc";
	      optTxt="reasonCode";
	      optVal="reasonCode";
	      selVal=$('#rCode').val();
	    }else if(s=="lc"){
	      sId="#locCode";
	      eo="pblmlocType";	
	      sc="pblmlocCode;pblmlocDesc";
	      optTxt="pblmlocCode";
	      optVal="pblmlocCode";	
	      selVal=$('#lCode').val();
	    }else if(s=="ic"){
	   	 sId="#iwrStat";
		 sc="iwrStsCode;iwrStsNm";
	     eo="iwrStatus";	
	     optTxt="iwrStsNm";
	     optVal="iwrStsCode";
	     selVal=$('#iwrStat').val();
	 	}
	    
		$(sId).html("");
		//var qryStr="reqType=xml&eo="+eo+"&sc="+sc;
		var qryStr="reqType=xml&eo="+eo+"&sc="+sc+"&wc="+wc;
		var urlSrUtil  = "canonE307ServiceReqCrUtil.jsp";
		showPleaseWait();
		$.ajax({
		    url:urlSrUtil,
		    data:qryStr,
		    type:"POST",
		    success: function(data){
		           $xml = $( $.parseXML( data ) );
		           $(sId).append($("<option></option>").attr("value","").text("Select"));
		             $xml.find(le).each(function(){
	                         var ot = $(this).find(optTxt).text();
	                         var ol = $(this).find(optVal).text();
	                        // console.log('ol : '+ol+" ot: "+ot);
	                         $(sId).append($("<option></option>").attr("value",ol).text(ot)); 
	                         if(s=="nt"){
	                       		 var ntDefVal= $(this).find("ntASCCDefFlg").text();
	                       	 	//console.log(" Note defVal : "+ ntDefVal +  ot + ol);
	                       	 	if(ntDefVal=="Y"){
	                       	 	selVal=ol;
	                       		 	//console.log("note val : "+ ot + ol+" selVal : "+selVal);
	                       	 	}
	                        } 	                         
		             });
		             
		           $(sId+' option[value="' + selVal + '"]').prop('selected', true);
		             hidePleaseWait(); 
		   },
		    error: function(data){
		       // console.log('Error loading XML data : '+sId);
		        hidePleaseWait() ;
		    }
		});
		
	}
 function setUMSelects(s){
		//showPleaseWait();
	    var eo="";
	    var sc="";
	    var optTxt="";
	    var optVal="";
	    var sId="";
	    var le="ListEntry";
	    var wc="";
	    var selVal="";
	    var pSize = $('#partRecSize').val();
	    pSize = parseInt(pSize);
	    var expSize = $('#expenseRecSize').val();
	    expSize = parseInt(expSize);	 
		
	    if(s=="ump"){
	      eo="uomType";	
	      sc="uomCode";
	      optTxt="uomCode";
	      optVal="uomCode";
	    //  selVal=$('#pbCd').val();
	
	    }else if (s=='ume'){
	      eo="uomType";	
	      sc="uomCode";
	      optTxt="uomCode";
	      optVal="uomCode";
	   //   selVal=$('#pbCd').val();
   	
	    }
	    selVal ="EA";
		$(sId).html("");
		//var qryStr="reqType=xml&eo="+eo+"&sc="+sc;
		var qryStr="reqType=xml&eo="+eo+"&sc="+sc+"&wc="+wc;
		var urlSrUtil  = "canonE307ServiceReqCrUtil.jsp";
		showPleaseWait();
		$.ajax({
		    url:urlSrUtil,
		    data:qryStr,
		    type:"POST",
		    success: function(data){
		           $xml = $( $.parseXML( data ) );
		           if(s=="ump"){
			    		for(i=0;i<pSize;i++){
			    			var selVal= $('#partsUOMSel'+i).val();
			    			sId="#partsUOM"+i;
				             $xml.find(le).each(function(){
		                         var ot = $(this).find(optTxt).text();
		                         var ol = $(this).find(optVal).text();
		                         $(sId).append($("<option></option>").attr("value",ol).text(ot)); 
			             	});
				    		 $(sId+' option[value="' + selVal + '"]').prop('selected', true);				             
			    		}
	  
		           }
		             hidePleaseWait(); 
		   },
		    error: function(data){
		        //console.log('Error loading XML data : '+sId);
		        hidePleaseWait() ;
		    }
		});
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
	      eom="minutePicker";
	      scm="minList";
	      optTxtm="minList";
	      optValm="minList";
	      ordM="minValOrd";
	      ordH="hourValOrd";
	      
		   var qryStr="reqType=xml&eo="+eom+"&sc="+scm+"&wc="+wc+"&ord="+ordM;
		   var urlSrUtil  = "canonE307ServiceReqCrUtil.jsp";
			$.ajax({
			    url:urlSrUtil,
			    data:qryStr,
			    type:"POST",
			    async:false,
			    success: function(data){
			       $xmlMin = $( $.parseXML( data ) );
	   			 }
			});

	    
		$(sId).html("");
		var selVal="";
		//var qryStr="reqType=xml&eo="+eo+"&sc="+sc;
		var qryStr="reqType=xml&eo="+eo+"&sc="+sc+"&wc="+wc+"&ord="+ordH;
		var urlSrUtil  = "canonE307ServiceReqCrUtil.jsp";
		showPleaseWait();
		$.ajax({
		    url:urlSrUtil,
		    data:qryStr,
		    type:"POST",
		    success: function(data){
		           $xml = $( $.parseXML( data ) );
			    		for(i=0;i<pSize;i++){
			    			if(s=='et'){
			    				selVal= $('#lEndTmSel'+i).val();
				    			sId="#lbrEndTm"+i;
			    			}else if(s=='at'){
				    			sId="#fSvcTime";
			    			}else{
				    			selVal= $('#lStrtTmSel'+i).val();
				    			sId="#lbrStrtTm"+i;			    				
			    			}
				             $xml.find(le).each(function(){
		                         var ot = $(this).find(optTxt).text();
		                         var ol = $(this).find(optVal).text();
				            	 $xmlMin.find(le).each(function(){
				            		 var otm = $(this).find(optTxtm).text();
			                         var olm = $(this).find(optValm).text();
			                         //console.log('loading XML data : '+ ol+":"+olm);
			                         var totVal=ol+":"+olm;
			                         $(sId).append($("<option></option>").attr("value",totVal).text(totVal)); 
				            	 });//min each
				             }); // hour each
			    		} // for loop
			           $(sId+' option[value="' + selVal + '"]').prop('selected', true);	    			
	 		             hidePleaseWait(); 
		   },
		    error: function(data){
		       // console.log('Error loading XML data : '+sId);
		        hidePleaseWait() ;
		    }
		}); 
		var labrRecCnt = $('#labRecSize').val();

	} 
	$(document).ready(function() {
		$("#labor").css('background-color', '#87CEEB');
		$("#parts").css('background-color', '#FFFFFF');
		$("#expenses").css('background-color', '#FFFFFF');
		$("#manualLaborEntry").css('display', 'inline-block','background-color', '#87CEEB');
		$("#parts").css('border-left', '2px solid');
		$("#parts").css('border-bottom', '2px solid');
		$("#parts").css('border-right', 'none');
		$("#expenses").css('border-bottom', '2px solid');
		$("#expenses").css('border-right', '2px solid');
		$("#expenses").css('border-left', '2px solid');
		$("#labor").css('border-style', 'none');
		$("#manualLaborEntry").css('border-bottom', '2px solid');
	//	$("#partstable").css('display', 'none');
	//	$("#expensetable").css('display', 'none');
		$("#manualLaborEntry").css('border-right', 'none');
	    $("#delete").css('border-left', 'none');
	    $("#delete").css('border-bottom', '2px solid');
	    $("#addMore").css('border-bottom', '2px solid');
	    $("#addMore").css('border-right', '2px solid');
		$("#labortable").css('display', 'block');
		$("#mach input").each(function() {
		    var input = $(this);
		   $(input).addClass("rdl").attr("readonly","readonly"); 
		});
		$("#res input").each(function() {
		    var input = $(this);
		   $(input).addClass("rdl").attr("readonly","readonly"); 
		});		

		setUMSelects('ump');

	
		var dtCheckFlg = $('#dtChkFlg').val();
		if(dtCheckFlg=='Y'){
			getCurTime('0');
			var curDate = getCurDt();
			$('#lbrStrtDt0').val(curDate);
			$('#lbrEndDt0').val(curDate);
			
		}
		var labrRecCnt = $('#labRecSize').val();
		labrRecCnt = parseInt(labrRecCnt);
		for(i=0;i<labrRecCnt;i++){
			 getLaborDuration(i,'NR');
		}
		var editMode =$('#editRole').val();
		if(editMode=='E307_OTH'){
			$('.disp_mode').hide();
		}
		fnRdOnly();
	});

	function displayLaborTable(ele) {
		$("#partstable").css('display', 'none');
		$("#expensetable").css('display', 'none');
		$("#labortable").css('display', 'block');
		$('#labEntr').show();
		$(".active").each(function() {
			 $(this).removeClass("active");
		 });
			$('#lbr').addClass("active");
		}
	function displayPartsTable(ele) {
		$("#partstable").css('display', 'block');
		$("#expensetable").css('display', 'none');
		$("#labortable").css('display', 'none');
		$('#labEntr').hide();
		
		$(".active").each(function() {
			 $(this).removeClass("active");
		 });
			$('#prt').addClass("active");
	}

	function displayExpenseTable(ele) {
		$("#partstable").css('display', 'none');
		$("#expensetable").css('display', 'block');
		$("#labortable").css('display', 'none');
		$('#labEntr').hide();
		$(".active").each(function() {
			 $(this).removeClass("active");
		 });
			$('#epns').addClass("active");
	}
	function getCurDt(){
		var currentDate = new Date();
		var day = currentDate.getDate();
		months = new Array('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec');
	    curMonth = months[currentDate.getMonth()];
	    if(day<10)
	    	day = "0"+day;
	    
		var year = currentDate.getFullYear();
		var curDate =curMonth+" "+ day+" "+year;
		return curDate;
	}
	function addMoreRows(){
		var taskReOpenFlg = $('#tskReOpenFlg').val();
		var editMode =$('#editRole').val();

		var curDate = getCurDt();
		//if(taskStatus == 'CLOSED' || taskStatus == 'TERMINATED'){

			var imgPath = "<%=ctxPath%>/common/images/jtfulov.gif"
			if ($('#labortable').css('display')!= 'none') {
				if(taskReOpenFlg == 'Y' && editMode =='E307_DIS'){
					// Do Nothing
				}else{
				//	var count =  $("#labor_table").attr('rows').length;
				var count = $('#labor_table tr').length;
					count = count-1;
					  var newRow = 
						  "<tr id='laborRow"+count+"'> "+
					  	   " <input type='hidden' id='rowId' name='rowId' value='"+count+"' > "+
					  	   " <input type='hidden' id='laborItmVal"+count+"' name='laborItmVal"+count+"' value='' > "+
					       " <input type='hidden' id='fsrVstTmPk"+count+"' name='fsrVstTmPk"+count+"' value=''> "+
					       " <input type='hidden' id='billTpCd"+count+"' name='billTpCd"+count+"' value=''> "+
					       " <input type='hidden' id='lbrChrgFlg"+count+"' name='lbrChrgFlg"+count+"' value=''> "+
					       " <input type='hidden' id='dtChkFlg"+count+"' name='dtChkFlg"+count+"' value='N'> "+
					  	   " <td><input type=radio name='laborSelect' id='laborSelect"+count+"' value='"+count+"' onclick='fnChngImg()' style='border:0px;'> "+
					  	   " <\/td> "+
						   "  <td id='lbrItmLovTd"+count+"' nowrap> "+  
						   " <select id='laborItem"+count+"' name='laborItem"+count+"' style='width:250px;' onchange=fnValidateMachModfctn('"+count+"') > "+
						   " <option value=''>Select</option> "+
						<%
						if(lstItems!=null && lstItems.size()>0){
							for(int x=0;x<lstItems.size();x++){
								strSelect="";
								CanonE307DebriefItemLov laborLovObj = (CanonE307DebriefItemLov)lstItems.get(x);
		
							%>
						"		<option value='<%=util.checkNull(laborLovObj.getStrItmNm()) %>'><%=util.checkNull(laborLovObj.getStrItmNm()) %>-<%=util.checkNull(laborLovObj.getStrItmDesc()) %></option> "+
							<%
							 }
						 }
						%>
						" </select>		"+			   
						   "  <\/td> "+
						   "<td> <input type='text' id='lbrStrtDt"+count+"' name='lbrStrtDt"+count+"' style='width:98%;' value='"+curDate+"' > " +
						   "  <\/td> "+
						   "<td nowrap>  <select id='lbrStrtHr"+count+"' name='lbrStrtHr"+count+"' onchange=getLaborDuration('"+count+"','R')> "+
						  /*  " < option value=''></option> "+ */
						   <%
					 			for(String sHr: lsHr){
				            %>
				            " <option value='<%=sHr%>'><%=sHr%></option> "+
				            <%} %>
						   "</select>" +
						   " <select id='lbrStrtMn"+count+"' name='lbrStrtMn"+count+"' onchange=getLaborDuration('"+count+"','R')> "+
						  /*  " < option value=''></option> "+ */
						   <%
						   		for(String sMn: lsMn){
				            %>
				            " <option value='<%=sMn%>'><%=sMn%></option> "+
				            <%} %>
						   "</select>" +
							" <select id='strtAmPm"+count+"' name='strtAmPm"+count+"' onchange=getLaborDuration('"+count+"','R')>	"+
							/* " <option value=''></option> "+ */
							" <option value='AM'>AM</option> "+
				 		  	" <option value='PM'>PM</option> "+
				 			"</select> "+
						   "  <\/td> "+		
						   "<td> <input type='text' id='lbrEndDt"+count+"' name='lbrEndDt"+count+"' style='width:98%;' value='"+curDate+"' >" +
						   "  <\/td> "+
						   "<td nowrap>  <select id='lbrEndHr"+count+"' name='lbrEndHr"+count+"' onchange=getLaborDuration('"+count+"','R')> "+
						  /*  " <option value=''></option> "+  */
						   <%
				 				for(String sHr: lsHr){
					        %>
					            " <option value='<%=sHr%>'><%=sHr%></option> "+
					           <%} %>
							   "</select>" +
							   " <select id='lbrEndMn"+count+"' name='lbrEndMn"+count+"' onchange=getLaborDuration('"+count+"','R')> "+
							  /*  " <option value=''></option> "+ */
						   <%
						   		for(String sMn: lsMn){
				            %>
					            " <option value='<%=sMn%>'><%=sMn%></option> "+
					            <%} %>
							   "</select>" +
							" <select id='endAmPm"+count+"' name='endAmPm"+count+"' onchange=getLaborDuration('"+count+"','R')>	"+
							 	/* "<option value=''></option> "+ */
								"<option value='AM'>AM</option> "+
				 		  		"<option value='PM'>PM</option> "+
				 			"</select> "+
						   "  <\/td> "+		
						   "<td>  <input type='text' id='laborDuration"+count+"' name='laborDuration"+count+"' readonly style='width:98%;'>" +
						   "  <\/td> "+	
						   "<td>  <input type='text' id='laborMod"+count+"' name='laborMod"+count+"' readonly style='width:97%;'>" +
						   "  <\/td> "+	
						   "<td style='padding:0px;padding-top:8px;''><img src='<%=imgSrc1%>' alt='' height='21' onClick=fnOpenModLov('laborItemDiv','"+count+"','MODNUM','LABOR')></td>" +
						   "<td>  <input type='text' id='laborSerNumber"+count+"' name='laborSerNumber"+count+"'readonly  style='width:98%;'>" +
						   "  <\/td> "+		
						   "<td>  <input type='text' id='laborModItem"+count+"' name='laborModItem"+count+"' readonly style='width:98%'>" +
						   "  <\/td> "+	
						   "<td> <input type='text' id='laborComments"+count+"' name='laborComments"+count+"' readonly style='width:98%'>" +
						   "  <\/td> "+						   
							"<\/tr> ";		
								$('#labor_table').append(newRow); 
								 setDtPicker('lbrStrtDt'+count);
								 setDtPicker('lbrEndDt'+count);
								 getCurTime(count);
								 getLaborDuration(count, 'R');
							//	 fnGetLaborLov(count);
								 $('#labRecSize').val(++count);
						}	
				//alert('labor count : '+count);
				}else if($('#partstable').css('display')!= 'none') {
					if(taskReOpenFlg == 'Y' && editMode =='E307_DIS'){
						var ccdecision= confirm("You are about to modify debrief for a closed task. Any modification to invoice (in needed) will have to made manually \n Click OK to Proceed , Cancel to Retry!");
					    if (ccdecision == false)
					    {
					    	fnBackSummary();
					    }else{
				 			var count = $('#parts_table tr').length;
							count= count-1;
							var newRow = 
							  "<tr id='partsRow"+count+"'> "+
								   " <input type='hidden' name='fsrUsgPk"+count+"' id='fsrUsgPk"+count+"' value=''> "+
								   " <input type='hidden' name='prtsChrgFlg"+count+"' id='prtsChrgFlg"+count+"' value=''> "+
							  	   " <input type='hidden' name='partsItmVal"+count+"' id='partsItmVal"+count+"' value=''> "+
							  	   " <input type='hidden' name='partsItmDesc"+count+"' id='partsItmDesc"+count+"' value=''> "+
							  	   " <td><input type=radio name='partsSelect' id='partsSelect"+count+"' value='"+count+"' onclick='fnChngImg()' style='border:0px;'> "+
							  	   " <\/td> "+
								   "  <td id='partsItmLovTd"+count+"' nowrap> "+  
								   "  <select id='partItem"+count+"' name='partItem"+count+"' style='width:250px;' onchange=fnSetPrtCnt('"+count+"')> "+ 
								   "  </select>	"+
								   " <input name='prtCode"+count+"' id='prtCode"+count+"' type='hidden' value='' /> "+
								   "  <\/td> "+
									   " <td> <input type='text' id='partServiceDate"+count+"' name='partServiceDate"+count+"' value='"+curDate+"' class='datePicker' style='width:98%'>" +
								   "  <\/td> "+	
								   " <td> <input type='text' id='partQty"+count+"' name='partQty"+count+"' class='autoInteger' style='width:98%' onchange=fnShowAddendum('"+count+"')>" +
								   "  <\/td> "+	
								   " <td> <select id='partsUOM"+count+"' name='partsUOM"+count+"' class='dis'></select>" +
								   "  <\/td> "+	
								   " <td> <input type='text' id='partMod"+count+"' name='partMod"+count+"' readonly style='width:98%'>" +
								   "  <\/td> "+	
								   " <td style='padding:0px;padding-top:8px;'><img src='<%=imgSrc1%>' alt='' height='21' onClick=fnOpenModLov('laborItemDiv','"+count+"','MODNUM','PARTS')> </td>"+
								   " <td> <input type='text' id='partSerlNumber"+count+"' name='partSerlNumber"+count+"' readonly style='width:98%'>" +
								   "  <\/td> "+	
								   " <td> <input type='text' id='partModItem"+count+"' name='partModItem"+count+"' readonly style='width:98%'>" +
								   "  <\/td> "+	
								   " <td> <input type='text' id='partModMdl"+count+"' name='partModMdl"+count+"' style='width:98%'>" +
								   "  <\/td> "+							   
								   " <td> <input type='text' id='partComments"+count+"' name='partComments"+count+"' readonly style='width:98%'>" +
								   "  <\/td> "+						   
									"<\/tr> ";		   
									 $('#parts_table').append(newRow);
									 if (isIE () && isIE () < 10) {
										 fnGetPartsLovReg(count);
									 }else{
										 fnGetPartsLovSrch(count);
									 }
									 if(count>0){
										 $('#partsUOM'+count).html($('#partsUOM0').html());
										 $('#partsUOM'+count+' option[value="EA"]').prop('selected', true);
										 $('#partRecSize').val(++count);
									 }else{
										 $('#partRecSize').val(++count);
											setUMSelects('ump');
									 }					    	

					    }
					}else{
		  				var count = $('#parts_table tr').length;
						count= count-1;
						var newRow = 
						  "<tr id='partsRow"+count+"'> "+
						  	   " <input type='hidden' name='fsrUsgPk"+count+"' id='fsrUsgPk"+count+"' value=''> "+
						  	   " <input type='hidden' name='prtsChrgFlg"+count+"' id='prtsChrgFlg"+count+"' value=''> "+
						  	   " <input type='hidden' name='partsItmVal"+count+"' id='partsItmVal"+count+"' value=''> "+
						  	   " <input type='hidden' name='partsItmDesc"+count+"' id='partsItmDesc"+count+"' value=''> "+
						  	   " <td><input type=radio name='partsSelect' id='partsSelect"+count+"' value='"+count+"' onclick='fnChngImg()' style='border:0px;'> "+
						  	   " <\/td> "+
							   "  <td id='partsItmLovTd"+count+"' nowrap> "+  
							   "  <select id='partItem"+count+"' name='partItem"+count+"' style='width:250px;' onchange=fnSetPrtCnt('"+count+"')> "+ 
							   "  </select>	"+
							   " <input name='prtCode"+count+"' id='prtCode"+count+"' type='hidden' value='' /> "+
							   "  <\/td> "+
								   " <td> <input type='text' id='partServiceDate"+count+"' name='partServiceDate"+count+"' value='"+curDate+"' class='datePicker' style='width:98%'>" +
							   "  <\/td> "+	
							   " <td> <input type='text' id='partQty"+count+"' name='partQty"+count+"' class='autoInteger' style='width:98%' onchange=fnShowAddendum('"+count+"')>" +
							   "  <\/td> "+	
							   " <td> <select id='partsUOM"+count+"' name='partsUOM"+count+"' class='dis'></select>" +
							   "  <\/td> "+	
							   " <td> <input type='text' id='partMod"+count+"' name='partMod"+count+"' readonly style='width:98%'>" +
							   "  <\/td> "+	
							   " <td style='padding:0px;padding-top:8px;'><img src='<%=imgSrc1%>' alt='' height='21' onClick=fnOpenModLov('laborItemDiv','"+count+"','MODNUM','PARTS')> </td>"+
							   " <td> <input type='text' id='partSerlNumber"+count+"' name='partSerlNumber"+count+"' readonly style='width:98%'>" +
							   "  <\/td> "+	
							   " <td> <input type='text' id='partModItem"+count+"' name='partModItem"+count+"' readonly style='width:98%'>" +
							   "  <\/td> "+	
							   " <td> <input type='text' id='partModMdl"+count+"' name='partModMdl"+count+"' style='width:98%'>" +
							   "  <\/td> "+							   
							   " <td> <input type='text' id='partComments"+count+"' name='partComments"+count+"' readonly style='width:98%'>" +
							   "  <\/td> "+						   
								"<\/tr> ";		   
								 $('#parts_table').append(newRow);
								 if (isIE () && isIE () < 10) {
									 fnGetPartsLovReg(count);
								 }else{
									 fnGetPartsLovSrch(count);
								 }
								 if(count>0){
									 $('#partsUOM'+count).html($('#partsUOM0').html());
									 $('#partsUOM'+count+' option[value="EA"]').prop('selected', true);
									 $('#partRecSize').val(++count);
								 }else{
									 $('#partRecSize').val(++count);
										setUMSelects('ump');
								 }
						}
					//alert('Parts count : '+count);
				}else if($('#expensetable').css('display')!= 'none') {
					if(taskReOpenFlg == 'Y' && editMode =='E307_DIS'){
						// Do Nothing
					}else{
						var count = $('#expensetable tr').length;
							count=count-1;
						var newRow = 
						  "<tr id='expenseRow"+count+"'> "+
						"	<input type='hidden' name='fsrExpPk"+count+"' id='fsrExpPk"+count+"' value='' > "+
					   "	<input type='hidden' name='expenseItemVal"+count+"' id='expenseItemVal"+count+"' val='' >"+
					   " 	<td><input type=radio name='expenseSelect' id='expenseSelect"+count+"' value='"+count+"' onclick='fnChngImg()' style='border:0px;'> "+
					   " 	<\/td> "+
					   "  	<td id='expnsItmTd"+count+"'> "+  
					   "  <select id='expItemSel"+count+"' name='expItemSel"+count+"' onchange=fnSetExpUOMCnt('"+count+"')> "+ 
					   " <option value=''>Select</option> "+
						<%
							if(lstExpItems!=null && lstExpItems.size()>0){
								for(int y=0;y<lstExpItems.size();y++){
									CanonE307DebriefItemLov expBnObj = (CanonE307DebriefItemLov)lstExpItems.get(y);	
		
							%>
					  "			<option value='<%=util.checkNull(expBnObj.getStrItmNm()) %>-<%=util.checkNull(expBnObj.getStrUom()) %>-<%=util.checkNull(expBnObj.getStrItmCnt() ) %>'><%=util.checkNull(expBnObj.getStrItmNm()) %> - <%=util.checkNull(expBnObj.getStrItmDesc()) %></option>	"+						
							<%
								}
							}
						%>
			   
					   "  </select>	"+
					   "  	<\/td> "+	
					   "	<td> <input type='text' id='expServiceDate"+count+"' name='expServiceDate"+count+"' value='"+curDate+"' class='datePicker' style='width:98%;'>" +
					   "  	<\/td> "+	
					   "	<td> <input type='text' id='expenseQty"+count+"' name='expenseQty"+count+"' class='autoInteger' style='width:98%'>" +
					   "  	<\/td> "+	
					   " 	<td><input type='text' name='expenseAmt"+count+"' id='expenseAmt"+count+"' value='' style='width:98%;' class='autoDecimal'>"+
					   "    </td> "+
					   "	<td> <input type='text' id='expenseUOM"+count+"' name='expenseUOM"+count+"' style='width:98%' readonly class='dis' >" +
					   "  	<\/td> "+
					   "	<td> <input type='text' id='expenseComments"+count+"' name='expenseComments"+count+"' readonly style='width:98%'>" +
					   "  	<\/td> "+	
					   "<\/tr> ";	
					   $('#expense_table').append(newRow); 	
						//alert('expense count : '+count);
						
						if(count>0){
							//$('#expenseUOM'+count).html($('#expenseUOM0').html());
							//$('#expenseUOM'+count+' option[value="EA"]').prop('selected', true);
							$('#expenseRecSize').val(++count);
						}else{
							$('#expenseRecSize').val(++count);
							//setUMSelects('ume');
						}
					}
				}
			addDate();
			fnIntValidate();
	}
function deleteRow(){
	var taskStatus = $('#svcTskSts').val();
	if(taskStatus == 'CLOSED' || taskStatus == 'TERMINATED'){
		//return false;
	}else{
		if ($('#labortable').css('display')!= 'none') {
			var count = $('#labRecSize').val();
			count = parseInt(count);
			var RowNo = $('input:radio[name=laborSelect]:checked').val();
			if(RowNo==null || RowNo=="null"){
				//alert('Please select row to delete');
			}else{
			 $("#labor_table tr[id='laborRow" + RowNo + "']").remove();
		//	 $('#labRecSize').val(--count);
			}
		}else if($('#partstable').css('display')!= 'none') {
			var pCount = $('#partRecSize').val();
			pCount = parseInt(pCount);
			var RowNo = $('input:radio[name=partsSelect]:checked').val();
			if(RowNo==null || RowNo=="null"){
				//alert('Please select row to delete');
			}else{
			 $("#parts_table tr[id='partsRow" + RowNo + "']").remove();
		//	 $('#partRecSize').val(--pCount);
			}
		}else if($('#expensetable').css('display')!= 'none') {
			var eCount = $('#expenseRecSize').val();
			eCount = parseInt(eCount);
			var RowNo = $('input:radio[name=expenseSelect]:checked').val();
			if(RowNo==null || RowNo=="null"){
				//alert('Please select row to delete');
			}else{
			 $("#expensetable tr[id='expenseRow" + RowNo + "']").remove();
			// $('#expenseRecSize').val(--eCount);
		    }
		}
	}
}

function openLaborItemLov(mName, iVal){
	// alert("Opem Labor Item..");
		//var laborItem = $('#laborItem'+iVal).val();
		var urlDetail = 'canonE307DebriefLaborItemLov.jsp?Action=Open';
		var qStr = '&modalName='+ mName;
		//qStr = qStr + '&laborItem='+ laborItem;	
		qStr = qStr + '&iVal='+ iVal;	
		
		   modelName = "#"+mName;
		   showPleaseWait();
	   $(modelName).dialog({
					height: 450,
					title: "Labor Item Search",
					modal: true ,
			autoOpen :false,
					 width: 750,		
			 resizable: true     
				});

		  $.ajax({
			url: urlDetail,
			cache: false,
			data: qStr,
			//type: "POST",
				success: function(data){    
				hidePleaseWait();
			  $(modelName).html("");          
			  $(modelName).html(data);
					}             
				});
		  $(modelName).dialog("open");
		  $(".ui-dialog-titlebar").addClass("hd");
	}
 function setLaborItemDet(mName, iVal, laborItem, laborDesc){
		var modelName = "#"+mName;
		$('#laborItem'+iVal).val(laborItem);
		$('#laborDescription'+iVal).val(laborDesc);
		
	
		$(modelName).html("");
	    $(modelName).dialog("close");
	    $(modelName).dialog("destroy");  	  
 }
 function openPartItemLov(mName, iVal){
		//var partItem = $('#partItem'+iVal).val();
		var fsr = $('#fsr').val();
		var urlDetail = 'canonE307DebriefPartsItemLov.jsp?Action=Open';
		var qStr = '&modalName='+ mName;
		//qStr = qStr + '&partItem='+ partItem;
		qStr = qStr + '&iVal='+ iVal;
		qStr = qStr + '&fsr='+ fsr;

		   modelName = "#"+mName;
		   showPleaseWait();
	   $(modelName).dialog({
					height: 550,
					title: "Part Item Search",
					modal: true ,
			autoOpen :false,
					 width: 750,		
			 resizable: true      
						});
		  $.ajax({
			url: urlDetail,
			cache: false,
			data: qStr,
			//type: "POST",
				success: function(data){    
					hidePleaseWait();
				  	$(modelName).html("");          
				  	$(modelName).html(data);
					}             
				});
		  $(modelName).dialog("open");
		  $(".ui-dialog-titlebar").addClass("hd");
 }
 function setPartItemDet(mName, iVal, partItem, serviceDate, partUom, uomCnt, partVal){
	 var modelName = "#"+mName;
//	 if(qty==null || qty=='null'){
//		 qty='';
//	 }
	var partItemDesc = $('#partItmDesc'+partVal).val();
	$('#partItem'+iVal).val(partItem);
	$('#partDescription'+iVal).val(partItemDesc);
//	$('#partServiceDate'+iVal).val(serviceDate);
	$('#partsUOM'+iVal).val(partUom);	
	if(uomCnt=1){
		$('#partsUOM'+iVal).attr('disabled', 'disabled');
	}
			
	$(modelName).html("");
	$(modelName).dialog("close");
	$(modelName).dialog("destroy");  	  	 
 }
 function openExpenseItemLov(mName, iVal){
	// alert("Open Expense Item..");
		//var expenseItem = $('#expenseItem'+iVal).val();
		var urlDetail = 'canonE307DebriefExpenseItemLov.jsp?Action=Open';
		var qStr = '&modalName='+ mName;
		//qStr = qStr + '&expenseItem='+ expenseItem;	
		qStr = qStr + '&iVal='+ iVal;	

		   modelName = "#"+mName;
		   showPleaseWait();
	   $(modelName).dialog({
					height: 450,
					title: "Expense Item Search",
					modal: true ,
			autoOpen :false,
					 width: 750,		
			 resizable: true     
						});
		  $.ajax({
			url: urlDetail,
			cache: false,
			data: qStr,
			success: function(data){    
				hidePleaseWait();
			  $(modelName).html("");          
			  $(modelName).html(data);
					}             
				});
		  $(modelName).dialog("open");	
		  $(".ui-dialog-titlebar").addClass("hd");
 }
 function setExpenseItemDet(mName, iVal, expenseItem, description, serviceDate, uom, uomCnt){
	 var modelName = "#"+mName;
	$('#expenseItem'+iVal).val(expenseItem);
	$('#expenseDescription'+iVal).val(description);
//	$('#expenseServiceDate'+iVal).val(serviceDate);
	$('#expenseUOM'+iVal).val(uom);
	if(uomCnt=1){
		$('#expenseUOM'+iVal).attr('disabled', 'disabled');
	}
	
	$(modelName).html("");
	$(modelName).dialog("close");
	$(modelName).dialog("destroy");  	  	 
 }
 
function addDate(){ 
	  $(".datePicker").datepicker(
		{dateFormat: 'M dd yy',changeMonth: true, changeYear: true}
	  );
	}
	
function timeHandler(){ 
	$(".timePicker").timepicker({timeFormat: 'HH:mm', interval: 15});
	}

function fnIntValidate(){
	$("input.autoInteger").autoNumeric({aSep: '', mDec: 0, mNum: 10});
	$("input.autoDecimal").autoNumeric({aSep: '', mDec: 2});	
}

$(function(){
	// disableAllInputFields();
	fnIntValidate();
	timeHandler();
	addDate();
   $('#noteDtls').keyup(function() {
        var len = this.value.length;
        if (len >= 500) {
            this.value = this.value.substring(0, 500);
        }
    //    $('#charLeft').text(150 - len);
    });
   $("input.autoInteger").autoNumeric({aSep: '', mDec: 0, mNum: 10});	
});	

function disableAllInputFields(){
	$("input.chkGrp").attr("disabled", true);
	var taskStatus = $('#tskSts').val();
//	if(taskStatus == 'CLOSED' || taskStatus == 'TERMINATED'){
	if(taskStatus!="Completed"){
		$('#debriefInfoDiv :input').attr('disabled', true);
		$('#debriefInfoDiv :input').addClass("rdl").attr("readonly","readonly"); 
	}
	var probCode = $('#problemCode').val();
	if(probCode == null || probCode=="null"){
		$('#noteDiv :input').attr('disabled', true);
	}
//$("div#addMore img").removeAttr('src');
}
function fnCalcDuration(iVal){
	var laborStartDate = $('#lbrStrtDt'+iVal).val();
	//var laborStartTime = $('#lbrStrtTm'+iVal).val();
	var laborStartHr = $('#lbrStrtHr'+iVal).val();
	var laborStartMn = $('#lbrStrtMn'+iVal).val();
	var laborEndDate = $('#lbrEndDt'+iVal).val();
	//var laborEndTime = $('#lbrEndTm'+iVal).val();
	var laborEndHr = $('#lbrEndHr'+iVal).val();
	var laborEndMn = $('#lbrEndMn'+iVal).val();
	var strtAmPm = $('#strtAmPm'+iVal).val();
	var endAmPm = $('#endAmPm'+iVal).val();
	var laborStartTime = laborStartHr+":"+laborStartMn;
	var laborEndTime = laborEndHr+":"+laborEndMn;
	if(laborStartDate!=null && laborStartDate!="" && laborStartTime!=null && laborStartTime!="" && laborEndDate!=null && laborEndDate!="" && laborEndTime!=null && laborEndTime!=""){
		var url = "canonE307DebriefLaborDuration.jsp?laborStartDate="+laborStartDate;
		url = url+"&laborStartTime="+laborStartTime;
		url = url+"&laborEndDate="+laborEndDate;
		url = url+"&laborEndTime="+laborEndTime;
		url = url+"&strtAmPm="+strtAmPm;
		url = url+"&endAmPm="+endAmPm;
//		var modelName = "#"+mName;	
		$.ajax({
			url: url,
			cache: false,
			success : function(result)
			{
				// $(modelName).html(""); 
				if(result!=null){
					var duration = $.trim(result);
					$('#laborDuration'+iVal).val(duration);
				}
			}
		});			
	}
}

function getLaborDuration(iVal, rVal){
	
	if(rVal=='R'){
		$("#eDbrfMsg").html("");
	}
	$('#laborDuration'+iVal).removeClass("error");
	var laborStartDate = $('#lbrStrtDt'+iVal).val();
	//var laborStartTime = $('#lbrStrtTm'+iVal).val();
	var laborStartHr = $('#lbrStrtHr'+iVal).val();
	var laborStartMn = $('#lbrStrtMn'+iVal).val();
	var laborEndDate = $('#lbrEndDt'+iVal).val();
	//var laborEndTime = $('#lbrEndTm'+iVal).val();
	var laborEndHr = $('#lbrEndHr'+iVal).val();
	var laborEndMn = $('#lbrEndMn'+iVal).val();
	var strtAmPm = $('#strtAmPm'+iVal).val();
	var endAmPm = $('#endAmPm'+iVal).val();
	var laborStartTime = laborStartHr+":"+laborStartMn;
	var laborEndTime = laborEndHr+":"+laborEndMn;
	if((fnCheckDt(iVal, rVal))){
		if(laborStartDate!=null && laborStartDate!="" && laborStartTime!=null && laborStartTime!="" && laborEndDate!=null && laborEndDate!="" && laborEndTime!=null && laborEndTime!=""){
			var url = "canonE307DebriefLaborDuration.jsp?laborStartDate="+laborStartDate;
			url = url+"&laborStartTime="+laborStartTime;
			url = url+"&laborEndDate="+laborEndDate;
			url = url+"&laborEndTime="+laborEndTime;
			url = url+"&strtAmPm="+strtAmPm;
			url = url+"&endAmPm="+endAmPm;
	//		var modelName = "#"+mName;	
			showPleaseWait();
			$.ajax({
				url: url,
				cache: false,
				success : function(result)
				{
					// $(modelName).html(""); 
					hidePleaseWait();
					if(result!=null){
						var duration = $.trim(result);
						$('#laborDuration'+iVal).val(duration);
						duration = parseInt(duration);
						if(duration>24){
							 $('#laborDuration'+iVal).addClass("error");
							 $('#eDbrfMsg').attr('style', 'padding-left: 27px;');
							 $("#eDbrfMsg").html("Duration can't be more than 24 hours..");
							 $("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
							 $("#errorWidget").show();
						}
					}
				}
			});			
		}
	}
}
/*function submitDebriefForm(tskSts){
	if(tskSts == 'Completed'){
		var urlDetail = "canonE307ServiceRequestDebriefController.jsp?saveAction=Save";
		$.post(urlDetail, $("#debriefFrm").serialize(),
		function(data1) { 
			if(data1>0){
				$('#feedbackDiv').html("Updated debrief details.");
			}
		});			
	}
}*/
function submitDebriefForm(tskSts){
	$("#eDbrfMsg").html("");
    $("#errorWidget").hide();	
	var taskReOpenFlg = $('#tskReOpenFlg').val();
	var editMode =$('#editRole').val();
	var ptype = 'E';
	//var submitFlg = fnValidFlg();
	//showPleaseWait();
	var submitFlg ="";
	$.ajax({
		url: 'canonE307DebriefSubmitValidation.jsp',
		cache: false,
		dataType : "text",
		async:false,
		success : function(result)
		{
		//	hidePleaseWait();
			if(result!=null){
				submitFlg = $.trim(result);
			}
		}
	});	

	if(submitFlg == 'Y'){
		
			var pSize =  parseInt($('#partRecSize').val());
			
			for(i=0;i<pSize;i++){
				 var pCodeVal=$.trim($("#prtCode"+i).val());
				 var partsItmDesc = $.trim($("#partsItmDesc"+i).val());
				 var retVal=true;
				 if(pCodeVal!=null && pCodeVal.length>0){
					retVal =  fnPartQtyCheck(i);
				 }
				if(retVal){
					if(pCodeVal == partsItmDesc){
						 ptype ='S';
					}else{
						 if(pCodeVal!=null && pCodeVal.length>0){
								 ptype = 'E';
								 var selPCodeVal="#partItem"+i;
								 
								 
								 $(selPCodeVal+' option').each(function()
								  {
							       var dOptVal= $(this).val();
				
							       if(pCodeVal==dOptVal){
							    	   ptype ='S';
							       }
								});
			
							 if(ptype =='E'){
								 $('#eDbrfMsg').attr('style', 'padding-left: 27px;');
								  $("#eDbrfMsg").html("Please select the part item from drop down list, entered part item is not a valid part item: "+pCodeVal);
								  $("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
								  $("#errorWidget").show();
								  $("#prtCode"+i).val('');
								  return;
							 }
						 }else{
							 ptype = 'S';
						 }
					}
				}else{
					return false;
				}
			}
	}else{
		$('#eDbrfMsg').attr('style', 'padding-left: 27px;');
		$("#eDbrfMsg").html("You cannot process it during nightly batch processing because inventory discrepancy will occur.");
		$("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
		$("#errorWidget").show();					
	}
	if(submitFlg == 'Y' && ptype =='S'){
		if(taskReOpenFlg == 'Y' && editMode =='E307_DIS'){
			var ccdecision= confirm("You are about to modify debrief for a closed task. Any modification to invoice (in needed) will have to made manually \n Click OK to Proceed , Cancel to Retry!");
		    if (ccdecision == false)
		    {
		    	fnBackSummary();
		    }else{
			$('#mtrSubmtFlg').val('N');
			 $("#res select").each(function (i, e){
	 			//$(this).prop("disabled","false");  
	 			$(this).removeClass("rdl").removeAttr("disabled").removeAttr("readonly");
		     });
				$('#labor_table input[type="text"]').each(function (){
					$(this).removeClass("rdl").removeAttr("disabled").removeAttr("readonly");
					 
				});
				 $("#labor_table select").each(function (i, e){
					 $(this).removeClass("rdl").removeAttr("disabled").removeAttr("readonly");
			  });
				$('#expensetable input[type="text"]').each(function (){
					$(this).removeClass("rdl").removeAttr("disabled").removeAttr("readonly");
				});
				 $("#expensetable select").each(function (i, e){
					 $(this).removeClass("rdl").removeAttr("disabled").removeAttr("readonly"); 
			     });
				var tskSts = $('#svcTskSts').val();
				var reqSelector=".requiredd";
				if(!validateParams(reqSelector)){
					var lbrRecSz =	$('#labRecSize').val();
					var valid=true;
					for(i=0;i<lbrRecSz;i++){
						var retVal = fnCheckDt(i,'R');
						if(!retVal){
							valid=false;
							break;
						}
						$('#laborDuration'+i).removeClass("error");
						 var lbrDuratn = parseInt($('#laborDuration'+i).val());
						 if(lbrDuratn>24){
							  $('#laborDuration'+i).addClass("error");
							  $('#eDbrfMsg').attr('style', 'padding-left: 27px;');
							  $("#eDbrfMsg").html("Duration can't be more than 24 hours..");
							  $("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
							  $("#errorWidget").show();
							  valid=false;
								break;
						 }
					}
					if(valid){
						// $("#dbrfFlg").val("Y");
						getMtrReadDate();
						 var debriefTask =  $('#debriefTsk').val();
						 showPleaseWait();
						 $(".dis").each(function (){
						   		$(this).removeAttr("disabled");
						   		$(this).removeAttr("readonly");
						  }); 				 
						 var url="canonE307ServiceRequestDebrief.jsp?dbrfFlg=Y&debriefTsk="+debriefTask;
						 $("#debriefFrm").attr("action",url);
						 $("#debriefFrm").submit();		
					}
				}
		    }
				 		 
		}else{
			var tskSts = $('#svcTskSts').val();
			var reqSelector=".requiredd";
			if(!validateParams(reqSelector)){
				var lbrRecSz =	$('#labRecSize').val();
				var valid=true;
				for(i=0;i<lbrRecSz;i++){
					var retVal = fnCheckDt(i,'R');
					if(!retVal){
						valid=false;
						break;
					}
					$('#laborDuration'+i).removeClass("error");
					var lbrDuratn = parseInt($('#laborDuration'+i).val());
					if(lbrDuratn>24){
						  $('#laborDuration'+i).addClass("error");
						  $('#eDbrfMsg').attr('style', 'padding-left: 27px;');
						  $("#eDbrfMsg").html("Duration can't be more than 24 hours..");
						  $("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
						  $("#errorWidget").show();					
	
						valid=false;
						break;
					 }
				}
				if(valid){
					// $("#dbrfFlg").val("Y");
					//hidePleaseWait();
					getMtrReadDate();
					 var debriefTask =  $('#debriefTsk').val();
					 showPleaseWait();
					 $(".dis").each(function (){
					   		$(this).removeAttr("disabled");
					   		$(this).removeAttr("readonly");
					  }); 				 
					 var url="canonE307ServiceRequestDebrief.jsp?dbrfFlg=Y&debriefTsk="+debriefTask;
					 $("#debriefFrm").attr("action",url);
					 $("#debriefFrm").submit();		
				}
			}
		}
	}
}
function getMtrReadDate(){
	
	var mtrSubmtFlg = $('#mtrSubmtFlg').val();
	var lbrRecSz =	$('#labRecSize').val();
	var mtrInRdDt = "";
	var mtrOutRdDt = "";
	var nwEndDt = "";
	var nwStrtDt = "";
	if(mtrSubmtFlg == 'Y'){
		for(i=0;i<lbrRecSz;i++){
			var laborItm = $('#laborItem'+i).val();
			if(laborItm == '011ZZ003'){
				var lbrEndDt = $('#lbrEndDt'+i).val();
				var laborEndHr = $('#lbrEndHr'+i).val();
				var laborEndMn = $('#lbrEndMn'+i).val();
				var endAmPm = $('#endAmPm'+i).val();
				
				if(lbrEndDt!=null && lbrEndDt!='' && lbrEndDt!='null'){
					nwEndDt =  parseDate(lbrEndDt);
				}
				if(laborEndHr!=null && laborEndHr.length>0){
					 var newEndHr = convertDateTo24Hour(laborEndHr, endAmPm);
					 nwEndDt.setHours(newEndHr,laborEndMn,0,0);
				} 
				//console.log('nwEndDt: '+nwEndDt);
				mtrInRdDt = nwEndDt;
				var dd = mtrInRdDt.getDate();
				var mm= (mtrInRdDt.getMonth() + 1);
				var hor =mtrInRdDt.getHours(); 
				var min = mtrInRdDt.getMinutes();

				if(dd<10) 
				{
				    dd='0'+dd;
				} 

				if(mm<10) 
				{
				    mm='0'+mm;
				} 
				if(hor<10) 
				{
					hor='0'+hor;
				} 
				if(min<10) 
				{
					min='0'+min;
				} 
				
				var frmtdMtrInRdDt = mtrInRdDt.getFullYear() +""+ mm +""+dd+""+hor+""+min+"00";//mtrInRdDt.getFullYear() + "-" + mtrInRdDt.getDate().toString().padStart(2, '0');
				//console.log("frmtdMtrInRdDt: "+frmtdMtrInRdDt);
				$('#mtrInReadDate').val(frmtdMtrInRdDt);
			}
		}
		if(mtrInRdDt == null || mtrInRdDt == '' || mtrInRdDt=='null'){
			for(i=0;i<lbrRecSz;i++){
				var laborItm = $('#laborItem'+i).val();
				var lbrStrtDt  = $('#lbrStrtDt'+i).val();
				var lbrStrtHr =  $('#lbrStrtHr'+i).val();
				var lbrStrtMn =  $('#lbrStrtMn'+i).val();
				var strtAmPm =   $('#strtAmPm'+i).val();
				
				var laborItm = $('#laborItem'+i).val();
				var laborEndDt = $('#lbrEndDt'+i).val();
				var laborEndHr = $('#lbrEndHr'+i).val();
				var laborEndMn = $('#lbrEndMn'+i).val();
				var endAmPm = $('#endAmPm'+i).val();
				
				
				if(laborItm == '011ZZ002'){
					if(lbrStrtDt!=null && lbrStrtDt!='null' && lbrStrtDt!=''){
						nwStrtDt =  parseDate(lbrStrtDt);
					}
					if(lbrStrtHr!=null && lbrStrtHr.length>0){
						 var newStrtHr = convertDateTo24Hour(lbrStrtHr, strtAmPm);
						 nwStrtDt.setHours(newStrtHr,lbrStrtMn,0,0);
					} 
					if(mtrInRdDt == null || mtrInRdDt == '' || mtrInRdDt == 'null'){
						mtrInRdDt = nwStrtDt;
					}
					if(nwStrtDt < mtrInRdDt){
						mtrInRdDt = nwStrtDt;
					}
					
					if(laborEndDt!=null && laborEndDt!='null' && laborEndDt!=''){
						nwEndDt =  parseDate(laborEndDt);
						/* if(mtrOutRdDt=='' || mtrOutRdDt==null){
							 mtrOutRdDt = nwEndDt;
						 }*/
					}
					if(laborEndHr!=null && laborEndHr.length>0){
						 var newEndHr = convertDateTo24Hour(laborEndHr, endAmPm);
						 nwEndDt.setHours(newEndHr,laborEndMn,0,0);
						 if(mtrOutRdDt=='' || mtrOutRdDt==null){
							 mtrOutRdDt = nwEndDt;
						 }
					}
					if(nwEndDt > mtrOutRdDt){
						mtrOutRdDt = nwEndDt;
					}
					
					
				}
			}
			//console.log("mtrInRdDt.length: "+mtrInRdDt.length);
			if(mtrInRdDt!=null &&  mtrInRdDt!=''){
				var ddIn = mtrInRdDt.getDate();
				var mmIn= (mtrInRdDt.getMonth() + 1);
				var horIn =mtrInRdDt.getHours(); 
				var minIn = mtrInRdDt.getMinutes();
				
				if(ddIn<10) 
				{
					ddIn='0'+ddIn;
				} 
	
				if(mmIn<10) 
				{
					mmIn='0'+mmIn;
				}
				if(horIn<10) 
				{
					horIn='0'+horIn;
				} 
				if(minIn<10) 
				{
					minIn='0'+minIn;
				} 

				
				var frmtdMtrInRdDt = mtrInRdDt.getFullYear() +""+ mmIn +""+ddIn+""+horIn+""+minIn+"00";
				$('#mtrInReadDate').val(frmtdMtrInRdDt);
				//console.log("frmtdMtrInRdDt: "+ frmtdMtrInRdDt);
			}
			
			if(mtrOutRdDt!=null &&  mtrOutRdDt!=''){

				var ddOut = mtrOutRdDt.getDate();
				var mmOut= (mtrOutRdDt.getMonth() + 1);
				var horOut =mtrOutRdDt.getHours(); 
				var minOut = mtrOutRdDt.getMinutes();
				var secOut = mtrOutRdDt.getSeconds();
				if(ddOut<10) 
				{
					ddOut='0'+ddOut;
				} 
	
				if(mmOut<10) 
				{
					mmOut='0'+mmOut;
				} 
				if(horOut<10) 
				{
					horOut='0'+horOut;
				} 
				if(minOut<10) 
				{
					minOut='0'+minOut;
				} 

				
				var frmtdMtrOutRdDt = mtrOutRdDt.getFullYear()+""+mmOut +""+ddOut+""+horOut+""+minOut+"00";
				$('#mtrOutReadDate').val(frmtdMtrOutRdDt);
				//console.log("frmtdMtrOutRdDt: "+ frmtdMtrOutRdDt);
			}
				
				
		}else{
			
			for(i=0;i<lbrRecSz;i++){
				var laborItm = $('#laborItem'+i).val();
				var laborEndDt = $('#lbrEndDt'+i).val();
				var laborEndHr = $('#lbrEndHr'+i).val();
				var laborEndMn = $('#lbrEndMn'+i).val();
				var endAmPm = $('#endAmPm'+i).val();
				if(laborItm == '011ZZ002'){
					if(laborEndDt!=null && laborEndDt!='null' && laborEndDt!=''){
						nwEndDt =  parseDate(laborEndDt);
						/* if(mtrOutRdDt==''){
							 mtrOutRdDt = nwEndDt;
						 }*/
					}
					if(laborEndHr!=null && laborEndHr.length>0){
						 var newEndHr = convertDateTo24Hour(laborEndHr, endAmPm);
						 nwEndDt.setHours(newEndHr,laborEndMn,0,0);
						 if(mtrOutRdDt==''){
							 mtrOutRdDt = nwEndDt;
						 }
					} 
					//console.log("mtrOutRdDt: "+ mtrOutRdDt+" nwEndDt: "+nwEndDt);
					if(nwEndDt > mtrOutRdDt){
						mtrOutRdDt = nwEndDt;
					}
					
				}
			}
			//console.log("mtrOutRdDt1: "+mtrOutRdDt+" Length: "+mtrOutRdDt.length);
			if(mtrOutRdDt!=null &&  mtrOutRdDt!=''){

				var ddO = mtrOutRdDt.getDate();
				var mmO= (mtrOutRdDt.getMonth() + 1);
				var horO =mtrOutRdDt.getHours(); 
				var minO = mtrOutRdDt.getMinutes();

				if(ddO<10) 
				{
					ddO='0'+ddO;
				} 
	
				if(mmO<10) 
				{
					mmO='0'+mmO;
				} 
				if(horO<10) 
				{
					horO='0'+horO;
				} 
				if(minO<10) 
				{
					minO='0'+minO;
				} 

				
				var frmtdMtrOutRdDt1 = mtrOutRdDt.getFullYear() +""+ mmO +""+ddO+""+horO+""+minO+"00";
				$('#mtrOutReadDate').val(frmtdMtrOutRdDt1);

			}
		}
	}
}
function validateMtrRds(){
	$("#eDbrfMsg").html("");
	var taskReOpenFlg = $('#tskReOpenFlg').val();
	var editMode =$('#editRole').val();
	if(taskReOpenFlg != 'Y'){
		var mtrRdsSz = $('#mtrRdsSz').val();	
		mtrRdsSz = parseInt(mtrRdsSz);
		var taskSts = $('#svcTskSts').val();
		var mtrSubmtFlg = $('#mtrSubmtFlg').val();
		var mtrFailCount = $('#mtrFailCount').val();
		mtrFailCount = parseInt(mtrFailCount);
		if(taskSts != 'In Route'){
			for(i=0;i<mtrRdsSz;i++){
				var inMtrRd = $('#inMtrRd'+i).val();
				var outMtrRd = $('#outMtrRd'+i).val();
				var mndtrFlg = $('#mndtrFlg'+i).val();
				inMtrRd = parseInt(inMtrRd);
				outMtrRd = parseInt(outMtrRd);
				var inRdTp = $('#inRdTp'+i).val();
				var outRdTp = $('#outRdTp'+i).val();
				if(outMtrRd<inMtrRd && inRdTp =='AC' && outRdTp=='AC' && mtrSubmtFlg =='Y' && mtrFailCount<3){
					 $('#eDbrfMsg').attr('style', 'padding-left: 27px;');
					  $("#eDbrfMsg").html("In Meter can't be greater than Out Meter.");
					  $("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
					  $("#errorWidget").show();				
					return false;
				}
			}
		}
	}

 	return true;
}
function saveMtrRdsFrm(){
	var taskSts = $('#svcTskSts').val();
	 var debriefTask =  $('#debriefTsk').val();
	if(taskSts != 'In Route'){
		if(validateMtrRds()){
			 showPleaseWait();
			 var url="canonE307ServiceRequestDebrief.jsp?mtrFlg=Y&debriefTsk="+debriefTask;
			 $("#debriefFrm").attr("action",url);
			 $("#debriefFrm").submit();			
		}
	}else{
		 showPleaseWait();
		 var url="canonE307ServiceRequestDebrief.jsp?mtrFlg=Y&debriefTsk="+debriefTask;
		 $("#debriefFrm").attr("action",url);
		 $("#debriefFrm").submit();		
	}
}
function fnSummaryPg(url){
	 $("#debriefFrm").attr("action",url);
	 showPleaseWait();
	 $("#debriefFrm").submit();	
}
function getLaborItemLinks(pageNum, laborItemNum, mName, iVal){
	laborItemNum = encodeURIComponent(laborItemNum);
	var urlDetail = 'canonE307DebriefLaborItemLov.jsp?modalName='+mName;
	var	qStr="&laborItem="+ laborItemNum;
		qStr=qStr+"&pageNum="+pageNum;	
		qStr=qStr+"&modalName="+mName;
		qStr=qStr+"&iVal="+iVal;
		
	var modelName = "#"+mName;
	showPleaseWait();
	 $.ajax({
			url: urlDetail,
			cache: false,
			data: qStr,
			type: "POST",
			success: function(data){
			hidePleaseWait();
			$(modelName).html("");
			$(modelName).html(data);
			$("#a"+pageNum).css({"color":"white","background-color":"#A10304"});
			}
		}); 
}
function getPartItemLinks(pageNum, partItemNum, mName, iVal){
	partItemNum = encodeURIComponent(partItemNum);
	var fsr = $('#fsr').val();
	var urlDetail = 'canonE307DebriefPartsItemLov.jsp?modalName='+mName;
	var	qStr="&partItem="+ partItemNum;
		qStr=qStr+"&pageNum="+pageNum;	
		qStr=qStr+"&iVal="+iVal
		qStr=qStr+"&fsr="+fsr;
		
	var modelName = "#"+mName;
	showPleaseWait();
	 $.ajax({
			url: urlDetail,
			cache: false,
			data: qStr,
			type: "POST",
			success: function(data){
			hidePleaseWait();
			$(modelName).html("");
			$(modelName).html(data);
			//setRowStyles(".lovTbl");
		    $("#a"+pageNum).css({"color":"white","background-color":"#A10304"});
		    hidePleaseWait();
			}
		}); 
}
function getExpenseItemLinks(pageNum, expenseItemNum, mName, iVal){
	expenseItemNum = encodeURIComponent(expenseItemNum);
	var urlDetail = 'canonE307DebriefExpenseItemLov.jsp?modalName='+mName;
	var	qStr="&expenseItem="+ expenseItemNum;
		qStr=qStr+"&pageNum="+pageNum;	
		qStr=qStr+"&modalName="+mName;
		qStr=qStr+"&iVal="+iVal;
		
	var modelName = "#"+mName;
	showPleaseWait();
	 $.ajax({
			url: urlDetail,
			cache: false,
			data: qStr,
			type: "POST",
			success: function(data){
			hidePleaseWait();				
			$(modelName).html("");
			$(modelName).html(data);
			$("#a"+pageNum).css({"color":"white","background-color":"#A10304"});
			}
		}); 
}
function fnOpenModLov(mName, iVal, source, tabSrc){
	//var partItem = $('#partItem'+iVal).val();
	var srchVal="";
	if(tabSrc=='PARTS'){
		srchVal = $('#partMod'+iVal).val();
	}else{
		srchVal = $('#laborMod'+iVal).val();
	}
	var urlDetail = 'canonE307DebriefLov.jsp?Action=Open';
	var qStr = '&modalName='+ mName;
	//qStr = qStr + '&partItem='+ partItem;
	qStr = qStr + '&iVal='+ iVal;
	qStr = qStr + '&source='+ source;
	qStr = qStr + '&tabSrc='+ tabSrc;
	qStr = qStr + '&searchVal='+ srchVal;
	

	   modelName = "#"+mName;
	   showPleaseWait();
   $(modelName).dialog({
				height: 450,
				title: "MOD Numbers",
				modal: true ,
		autoOpen :false,
				 width: 450,		
		 resizable: true    
					});
	  $.ajax({
		url: urlDetail,
		cache: false,
		data: qStr,
		//type: "POST",
			success: function(data){    
		  hidePleaseWait();
		  $(modelName).html("");          
		  $(modelName).html(data);
				}             
			});
	  $(modelName).dialog("open");	
	  $(".ui-dialog-titlebar").addClass("hd");
}
function setModNum(mName, iVal, selVal, tabSrc, modItem){
	if(tabSrc == 'LABOR'){
		$('#laborMod'+iVal).val(selVal);
		$('#laborModItem'+iVal).val(modItem);
		$('#laborSerNumber'+iVal).val($('#serialNumber').val());
	}else{
		$('#partMod'+iVal).val(selVal);
		$('#partModItem'+iVal).val(modItem);
		$('#partSerlNumber'+iVal).val($('#serialNumber').val());
	}
	
	var modelName = "#"+mName;
	$(modelName).html("");
	$(modelName).dialog("close");
	$(modelName).dialog("destroy");  		
}
function fnCloseMdlDlg(dId){
	var dlgId="#"+dId;
	$(dlgId).html("");
	$(dlgId).dialog("close");
	$(dlgId).dialog("destroy");
	var debriefTask =  $('#debriefTsk').val();
	<%if(!"Closed".equals(objBean.getStrDebriefStatus())){%>
	 var url="canonE307ServiceRequestDebrief.jsp?debriefTsk="+debriefTask;
	 $("#debriefFrm").attr("action",url);
	 $("#debriefFrm").submit();		
	 <%}%>
}

function fnBackSummary(){
//	$('#debriefFrm #scratchPad').val( $("#toolTip textarea").val() );
	var fsr = $('#fsr').val();
	showPleaseWait();
	var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?fsr="+encodeURIComponent(fsr);
	document.debriefFrm.action = urlDetail;
	document.debriefFrm.submit();		
}
 function fnUploadPOAttach(){
	var userName = $('#userName').val();
	var fsrNum = $('#fsr').val();
	var serNum = $('#serialNumber').val();
	var url = "canonE307FileUploadToTemp.jsp?source=summary&userName="+encodeURIComponent(userName)+"&fsrNum="+fsrNum+"&serial="+serNum;
	window.open(url,'newWin','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=450,height=250');
}
function uploadItemImage(fileName){
	$("#eDbrfMsg").html("");
	$('#fileNm').val(fileName);
    var userName = $('#userName').val();
	var fsrNum = $('#fsr').val();
	var serNum = $('#serialNumber').val();
	if(fileName!=''){
		var url = "canonE307AttachmentUpload.jsp";
		var qryStr="source=summary&userName="+encodeURIComponent(userName)+"&fsrNum="+fsrNum+"&serial="+serNum+"&fileName="+fileName;
		showPleaseWait();
		$.ajax({
			url: url,
			data: qryStr,
			type:"POST",
			cache: false,
			success : function(result)
			{
				hidePleaseWait();
				var reslt = $.trim(result);
				if(reslt!='null' && reslt!=''){
					 var retFlg = $.trim(reslt.split("|")[0]);
					 var retMsg = $.trim(reslt.split("|")[1]);
					if($.trim(retFlg)=='E'){
						$("#eDbrfMsg").css({"color": "red", "font-size": "15"});
						$('#eDbrfMsg').html(retMsg);
						$('#eDbrfMsg').attr('style', 'padding-left: 27px;');
						$('#errorWidget').show();
					}else{
						$("#eDbrfMsg").css({"color": "green", "font-size": "15"}); 
						$('#eDbrfMsg').attr('style', 'padding-left: 27px;');
						$('#eDbrfMsg').html("PO is uploaded Successsfully..");
						$('#errorWidget').show();
					}						 				
				}				
			}
		});	
	} 
}
function fnDownAttach(fileName){
	var url="canonE307DownloadAttachment.jsp?fileName="+encodeURIComponent(fileName);
	$("#debriefFrm").attr("action",url);	 
	$('#debriefFrm').submit();	
}
$('#noteDtls').keyup(function() {
    var len = this.value.length;
    if (len >= 500) {
        this.value = this.value.substring(0, 500);
    }
});
function getCurTime(iVal){
	var d = new Date(); 
	var hor =d.getHours(); 
	var min = d.getMinutes();
	min = parseInt(min);
	var _time = (hor > 12) ? (hor-12 +' PM') : (hor +' AM');
	var dataTm = _time.split(' ');
	//console.log("data : "+dataTm[0]+":"+dataTm[1]);
	var _hr = (dataTm[0]<10)?('0'+dataTm[0]):(dataTm[0]);
    $('#lbrStrtHr'+iVal+' option[value="' + $.trim(_hr) + '"]').prop('selected', true);	
    $('#strtAmPm'+iVal+' option[value="' + $.trim(dataTm[1]) + '"]').prop('selected', true);
    $('#lbrStrtMn'+iVal + ' option[value="' + min + '"]').prop('selected', true);
    $('#lbrEndHr'+iVal+' option[value="' + $.trim(_hr) + '"]').prop('selected', true);	
    $('#endAmPm'+iVal+' option[value="' + $.trim(dataTm[1]) + '"]').prop('selected', true);
    $('#lbrEndMn'+iVal + ' option[value="' + min + '"]').prop('selected', true);    
}
function validateParams(reqSelector){
	var error=false;
	var p =[];
	$(reqSelector).each(function (){
		 p[p.length]=$(this).attr("id"); 
	});
	
	var e =[];
	 $("#eDbrfMsg").html(""); 
	 $("#errorWidget").hide();	
	
	for(k=0;k<p.length;k++){
		$("#"+p[k]).removeClass("error");
		var pVal = $("#"+p[k]).val();
		if ($.trim(pVal).length < 1) {
              error=true;
             e[e.length]=p[k]; 
		}else{
			$("#"+e[k]).removeClass("error");
		}
	}
	if(error){
		 for(k=0;k<e.length;k++){
			 $("#"+e[k]).addClass("error");
			 $("#eDbrfMsg").html("Please enter highlighted parameter.");
			 $('#eDbrfMsg').attr('style', 'padding-left: 27px;');
			 $("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
			 $("#errorWidget").show();
		 }
	}
	return error;
}
function convertDateTo24Hour(stHour, stAmPm){
  
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
function parseDate(dt) {
	  var months = {jan:0,feb:1,mar:2,apr:3,may:4,jun:5,
	                jul:6,aug:7,sep:8,oct:9,nov:10,dec:11};
	  if(dt!=null && dt.length>0){
		var  p = dt.split(' ');
		 return new Date(p[2], months[p[0].toLowerCase()], p[1]);
	  }

	 
}
function fnCheckDt(iVal, rVal){
	//$("#eMsg").html("");

	if(rVal=='R'){
		$("#eDbrfMsg").html("");
		var laborStartDate = $('#lbrStrtDt'+iVal).val();
		var laborStartHr = $('#lbrStrtHr'+iVal).val();
		var laborStartMn = $('#lbrStrtMn'+iVal).val();
		var laborEndDate = $('#lbrEndDt'+iVal).val();
		var laborEndHr = $('#lbrEndHr'+iVal).val();
		var laborEndMn = $('#lbrEndMn'+iVal).val();
		var strtAmPm = $('#strtAmPm'+iVal).val();
		var endAmPm = $('#endAmPm'+iVal).val();
		var laborItem = $('#laborItem'+iVal).val();
		var nwStrtDt = "";
		var nwEdDt = "";
		//if((laborStartDate=="" || laborStartDate=="null") && (laborItem!='068ZZ634')){
			if(laborStartDate=="" || laborStartDate=="null"){
			 $('#eDbrfMsg').attr('style', 'padding-left: 27px;');
			  $("#eDbrfMsg").html("Start Date can't be null.");
			  $("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
			  $("#errorWidget").show();			
			 return false;
			}else{
			 	nwStrtDt =  parseDate(laborStartDate);
			}
		//if((laborEndDate=="" || laborEndDate=="null") && (laborItem!='068ZZ634')){
			if(laborEndDate=="" || laborEndDate=="null"){
				 $('#eDbrfMsg').attr('style', 'padding-left: 27px;');
				  $("#eDbrfMsg").html("End Date can't be null.");
				  $("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
				  $("#errorWidget").show();			
			 return false;
			}else{
				nwEdDt = parseDate(laborEndDate);
			}

		 if(laborStartHr!=null && laborStartHr.length>0){
			 var newStrtHr = convertDateTo24Hour(laborStartHr, strtAmPm);
			 nwStrtDt.setHours(newStrtHr,laborStartMn,0,0);
		 }
		if(laborEndHr!=null && laborEndHr.length>0){
			 var newEndHr = convertDateTo24Hour(laborEndHr, endAmPm);
			 nwEdDt.setHours(newEndHr,laborEndMn,0,0);
		}

		 
		 
		//  if (nwStrtDt > nwEdDt && laborItem!='068ZZ634') { 
			if (nwStrtDt > nwEdDt){
			  $('#eDbrfMsg').attr('style', 'padding-left: 27px;');
			  $("#eDbrfMsg").html("Date & Time must be equal to or greater than start date and time.");
			  $("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
			  $("#errorWidget").show();
			  $('#laborDuration'+iVal).val('');
			  return false;
		  }else{
			  $('#eDbrfMsg').html('');
		//	  $("#errorWidget").hide();
			  return true;
		  }
	}
}
function fnChkStrtDup(rVal){
	var lbrRecSz =	$('#labRecSize').val();
	var valid=true;
	if(lbrRecSz>1 && rVal=='R'){
		for(i=0;i<lbrRecSz;i++){
			var laborStartDate = $('#lbrStrtDt'+i).val();
			var laborStartHr = $('#lbrStrtHr'+i).val();
			var laborStartMn = $('#lbrStrtMn'+i).val();
			var strtAmPm = $('#strtAmPm'+i).val();
			var newDt = $.trim(laborStartDate)+$.trim(laborStartHr)+$.trim(laborStartMn)+$.trim(strtAmPm);	
			for(x=0;x<lbrRecSz;x++){
				var laborStartDate = $('#lbrStrtDt'+x).val();
				var laborStartHr = $('#lbrStrtHr'+x).val();
				var laborStartMn = $('#lbrStrtMn'+x).val();
				var strtAmPm = $('#strtAmPm'+x).val();
				var newDate = $.trim(laborStartDate)+$.trim(laborStartHr)+$.trim(laborStartMn)+$.trim(strtAmPm);	
				if(i!=x && newDt==newDate){
					  $('#eDbrfMsg').attr('style', 'padding-left: 27px;');
					  $("#eDbrfMsg").html("Date & Time must be unique.");
					  $("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
					  $("#errorWidget").show();
					  return false;	
				}
			}
			
		}
	}
	return true;
}
function fnChkEndDup(rVal){
	var lbrRecSz =	$('#labRecSize').val();
	var valid=true;
	if(lbrRecSz>1 && rVal=='R'){
		for(i=0;i<lbrRecSz;i++){
			var laborEndDate = $('#lbrEndDt'+i).val();
			var laborEndHr = $('#lbrEndHr'+i).val();
			var laborEndMn = $('#lbrEndMn'+i).val();
			var endAmPm = $('#endAmPm'+i).val();		
			
			var newDt = $.trim(laborEndDate)+$.trim(laborEndHr)+$.trim(laborEndMn)+$.trim(endAmPm);	
			for(x=0;x<lbrRecSz;x++){
				var laborEndDate = $('#lbrEndDt'+x).val();
				var laborEndHr = $('#lbrEndHr'+x).val();
				var laborEndMn = $('#lbrEndMn'+x).val();
				var endAmPm = $('#endAmPm'+x).val();		
				var newDate = $.trim(laborEndDate)+$.trim(laborEndHr)+$.trim(laborEndMn)+$.trim(endAmPm);	
				if(i!=x && newDt==newDate){
					  $('#eDbrfMsg').attr('style', 'padding-left: 27px;');
					  $("#eDbrfMsg").html("Date & Time must be unique.");
					  $("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
					  $("#errorWidget").show();
					  return false;	
				}
			}
			
		}
	}
	return true;	
}
function fnRdOnly(){
	var mtrSubmtFlg = $('#mtrSubmtFlg').val();	
	var taskSts = $('#svcTskSts').val();
	if(mtrSubmtFlg == 'N'){
		$('#mtrRdTbl input[type="text"]').each(function (){
			 $(this).addClass("rdl").attr("readonly","readonly");
		});
		 $("#mtrRdTbl select").each(function (i, e){
 			 $(this).prop("disabled","true");  
	  });	
	}else{
		if(taskSts == 'In Route'){
		 	 $(".outRds").each(function (){
			   		$(this).addClass("rdl").attr("readonly","readonly");
			   		$(this).attr('disabled', true);
			 }); 			
		}
	}
	var tskReOpenFlg = $('#tskReOpenFlg').val();
	var editMode =$('#editRole').val();
	if(tskReOpenFlg=='Y' && editMode =='E307_DIS'){
		
		$('#labor_table input[type="text"]').each(function (){
			//$(this).removeClass('hasDatepicker').attr("readonly","readonly");
			 $(this).addClass("rdl").attr("readonly","readonly");
			 $(this).prop("disabled","true"); 
			 
		});
		 $("#labor_table select").each(function (i, e){
			 $(this).addClass("rdl").attr("readonly","readonly");			 
 			 $(this).prop("disabled","true");  
	  });
		$('#expensetable input[type="text"]').each(function (){
			 $(this).addClass("rdl").attr("readonly","readonly");
			 $(this).prop("disabled","true"); 
		});
		 $("#expensetable select").each(function (i, e){
			 $(this).addClass("rdl").attr("readonly","readonly");
 			 $(this).prop("disabled","true");  
	     });
		 
		 $("#res select").each(function (i, e){
			 //$(this).addClass("rdl").attr("readonly","readonly");
 			$(this).prop("disabled","true");  
	     });
		 
			$("#res input").each(function() {
			    var input = $(this);
			   $(input).addClass("rdl").attr("readonly","readonly"); 
			});		
		 
	}
}
function fnGetLaborLov(iVal){
	
	var lbrVal = " <select id='laborItem"+iVal+"' name='laborItem"+iVal+"'>  "+
				" <option value=''>Select</option> "+
	<%
	if(lstItems!=null && lstItems.size()>0){
		for(int x=0;x<lstItems.size();x++){
			CanonE307DebriefItemLov laborLovObj = (CanonE307DebriefItemLov)lstItems.get(x);	
	%>
		"	<option value='<%=util.checkNull(laborLovObj.getStrItmNm()) %>' - <%=util.checkNull(laborLovObj.getStrItmDesc()) %>><%=util.checkNull(laborLovObj.getStrItmNm()) %> - <%=util.checkNull(laborLovObj.getStrItmDesc()) %> </option> "+
		<%
		 }
	 }
		%>
	   "</select>" ;
	   
	  // console.log("lbrVal: "+lbrVal);
	   $('#lbrItmLovTd'+iVal).html(lbrVal);
	   
	  $('#laborItem'+iVal).val($('#laborItmVal'+iVal).val());
}

function fnGetPartsLovReg(iVal){

		 var taskNumber = $('#debriefTsk').val();
		 var qryStr=""; 
		 var selId="";
		 
		
		// console.log("Inside parts: ");
			 qryStr ="reqType=partsReg&taskNumber="+taskNumber;
			 selId="partItem"+iVal;

		
		 showPleaseWait();
			$.ajax( {
				url : "canonE307ServiceReqCrUtil.jsp",
				cache : false,
				type : "POST",
				data : qryStr,
				success : function(data) {
					hidePleaseWait();
					//console.log("codes: "+ data);

						$("#"+selId).html(data);
					/*	var partSelVal = $('#partsItmVal'+iVal).val();
						if(partSelVal!='' && partSelVal!=null && partSelVal!='null'){
							 $('#partItem'+iVal).val(partSelVal);
						}else{
							$('#partItem'+iVal).val(' ');
						}*/
				}
					
	  	});
			
	}
function fnGetPartsLovSrch(iVal){
	
	 var taskNumber = $('#debriefTsk').val();
	 var qryStr=""; 
	 var selId="";
	 var partItem = $('#partsItmVal'+iVal).val();

	
	// console.log("Inside parts: ");
		 qryStr ="reqType=partsSrch&taskNumber="+taskNumber+"&partItem="+partItem;
		 selId="partItem"+iVal;

	
	 showPleaseWait();
		$.ajax( {
			url : "canonE307ServiceReqCrUtil.jsp",
			cache : false,
			type : "POST",
			data : qryStr,
			success : function(data) {
				hidePleaseWait();
				//console.log("codes: "+ data);
	    		// alert("is IE 10 and later or not IE");
			    	var prtsData = "<input name='prtCode"+iVal+"' id='prtCode"+iVal+"' type='text' value='' placeholder='Select' list='partItem"+iVal+"' onchange=fnSetUomCnt('"+iVal+"') style='width:250px;' autofocus/> " +
			    	 		  "<datalist id='partItem"+iVal+"' name='partItem"+iVal+"' onchange=fnSetUomCnt('"+iVal+"') >" +
			    	 		  "</datalist>" ;
			    	// console.log("prtsData: "+prtsData);
			    	 $('#partsItmLovTd'+iVal).html(prtsData);	
			    	 $("#"+selId).html(data);
						var partSelVal = $('#partsItmDesc'+iVal).val();
						if(partSelVal!='' && partSelVal!=null && partSelVal!='null'){
							 $('#prtCode'+iVal).val(partSelVal);
						}else{
							$('#prtCode'+iVal).val('');
						} 
				}
 	});
		
}	
/*function fnSetPartsUom(iVal){
	var partItmVal = $('#partItem'+iVal).val();
	var partDet = partItmVal.split("*");
	if(partDet!=null && partDet.length>0){
		var partUom = partDet[1];
		var itemCnt = partDet[2];
		$('#partsUOM'+iVal).val(partUom);	
		if(itemCnt=1){
			$('#partsUOM'+iVal).attr('disabled', 'disabled');
		}
	}
}*/

function fnGetExpenseLov(iVal){

	
	// console.log("Inside expense: ");
	 var qryStr ="reqType=expense";
	 var selId ="expItemSel"+iVal;

	
	 showPleaseWait();
		$.ajax( {
			url : "canonE307ServiceReqCrUtil.jsp",
			cache : false,
			type : "POST",
			data : qryStr,
			success : function(data) {
				hidePleaseWait();
			//	console.log("codes: "+ data);
				$("#"+selId).html(data);
				var expSelVal = $('#expenseItemVal'+iVal).val();
				if(expSelVal!='' && expSelVal!=null && expSelVal!='null'){
				  $('#expItemSel'+iVal).val(expSelVal);
				}else{
					$('#expItemSel'+iVal).val(' ');
				}
			}
				
 	});
}
function fnSetUomCnt(iVal){
	
	 var selVal = $('#partItem'+iVal).val();
	// console.log("selVal: "+ selVal);
	 var mdseCd = $.trim(selVal.split("-")[0]);
	 var taskNumber = $('#debriefTsk').val();
	 var qryStr ="reqType=dbUom&mdseCd="+mdseCd+"&taskNumber="+taskNumber+"&source=PARTS";

		$.ajax( {
			url : "canonE307ServiceReqCrUtil.jsp",
			cache : false,
			type : "POST",
			data : qryStr,
			success : function(data) {
				//console.log("codes: "+ data);
				var res= jQuery.parseJSON(data);
				//console.log("Item Cnt: "+res.itmCnt);

					$('#partsUOM'+iVal).val(res.strUom);	
					if(res.itmCnt==1){
						$('#partsUOM'+iVal).attr('disabled', 'disabled');
					}


			}
	});
}
function isIE () {
	  var myNav = navigator.userAgent.toLowerCase();
	  return (myNav.indexOf('msie') != -1) ? parseInt(myNav.split('msie')[1]) : false;
}

function fnSetExpUOMCnt(iVal){
	var expItem = $('#expItemSel'+iVal).val();
	var uom = $.trim(expItem.split("-")[1]);
	var itmCnt = $.trim(expItem.split("-")[2]);
	$('#expenseUOM'+iVal).val(uom);	
	if(itmCnt==1){
		$('#expenseUOM'+iVal).attr('disabled', 'disabled');
	}
}

function fnSetPrtCnt(iVal){
	var partItemSel = $('#partItem'+iVal).val();
	//console.log("partItemSel: "+partItemSel);
	var partUom = $.trim(partItemSel.split(" - ")[1]);
	var partCnt = $.trim(partItemSel.split(" - ")[2]);
	$('#partsUOM'+iVal).val(partUom);
	if(partCnt==1){
		$('#partsUOM'+iVal).attr('disabled', 'disabled');
	}

	$('#prtCode'+iVal).val(partItemSel);
}
function fnSetReadTypes(readTp, iVal){
	var mtrRdsSz = $('#mtrRdsSz').val();	
	mtrRdsSz = parseInt(mtrRdsSz);
	if(readTp == 'IN'){
		var inRdTp = $('#inRdTp'+iVal).val();
		for(i=0;i<mtrRdsSz;i++){
			$('#inRdTp'+i).val(inRdTp);
		}	
	}else{
		var outRdTp = $('#outRdTp'+iVal).val();
		for(i=0;i<mtrRdsSz;i++){
			$('#outRdTp'+i).val(outRdTp);
		}	
	}
}
function fnValidateMachModfctn(iVal){
	
	 var laborItem = $('#laborItem'+iVal).val();
	/* if(laborItem=='068ZZ634'){
		 $('#lbrStrtDt'+iVal).val('');
		 $('#lbrStrtHr'+iVal).val('');
		 $('#lbrStrtMn'+iVal).val('');
		 $('#strtAmPm'+iVal).val('');
		 $('#lbrEndDt'+iVal).val('');
		 $('#lbrEndHr'+iVal).val('');
		 $('#lbrEndMn'+iVal).val('');
		 $('#endAmPm'+iVal).val('');
		 $('#lbrStrtHr'+iVal).attr('disabled', 'disabled');
		 $('#lbrStrtMn'+iVal).attr('disabled', 'disabled');
		 $('#strtAmPm'+iVal).attr('disabled', 'disabled');
		 $('#lbrEndHr'+iVal).attr('disabled', 'disabled');
		 $('#lbrEndMn'+iVal).attr('disabled', 'disabled');
		 $('#endAmPm'+iVal).attr('disabled', 'disabled');
		 $('#laborDuration'+iVal).val('');
		 $('#laborDuration'+iVal).removeAttr("readonly");
		 
	 }else{ */
		 var curDate = getCurDt();
		 if($('#lbrStrtDt'+iVal).val() == 'null' || $('#lbrStrtDt'+iVal).val() == ""){
			 $('#lbrStrtHr'+iVal).removeAttr("disabled");
			 $('#lbrStrtMn'+iVal).removeAttr("disabled");
			 $('#strtAmPm'+iVal).removeAttr("disabled");
			 $('#lbrEndHr'+iVal).removeAttr("disabled");
			 $('#lbrEndMn'+iVal).removeAttr("disabled");
			 $('#endAmPm'+iVal).removeAttr("disabled");		
			 $('#laborDuration'+iVal).attr('readonly', 'readonly');
			 
			 $('#lbrStrtDt'+iVal).val(curDate);
			 $('#lbrEndDt'+iVal).val(curDate);
			 getCurTime(iVal);
			 getLaborDuration(iVal,'R');
		 }
	// }
}
function fnShowAddendum(iVal){
	var taskReOpenFlg = $('#tskReOpenFlg').val();
	var editMode =$('#editRole').val();
	
	if(taskReOpenFlg == 'Y' && editMode =='E307_DIS'){
		var ccdecision= confirm("You are about to modify debrief for a closed task. Any modification to invoice (in needed) will have to made manually \n Click OK to Proceed , Cancel to Retry!");
	    if (ccdecision == false)
	    {
	    	fnBackSummary();
	    }
	}else{
		fnPartQtyCheck(iVal);
	}
}
function fnPartQtyCheck(iVal){
	 $("#eDbrfMsg").html("");
	 $("#errorWidget").hide();
	 $('#partQty'+iVal).removeClass("error");
	var partQty = $('#partQty'+iVal).val();
	if(partQty!=null && partQty.length>0){
		partQty = parseInt(partQty);
		if(partQty<=0){
			 $('#eDbrfMsg').attr('style', 'padding-left: 27px;');
			 $("#eDbrfMsg").html("Part quantity has to be greater than 0.");
			 $("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
			 $("#errorWidget").show();
			 $('#partQty'+iVal).addClass("error");
			 return false;
		}else{
			return true;
		}
	}else{
		 $('#eDbrfMsg').attr('style', 'padding-left: 27px;');
		 $("#eDbrfMsg").html("Part quantity has to be greater than 0.");
		 $("#eDbrfMsg").css({"color": "red", "font-size": "15"}); 
		 $("#errorWidget").show();
		 $('#partQty'+iVal).addClass("error");
		 return false;
	}
}

function fnValidFlg(){
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
			//console.log("result: "+ result);
			if(result!=null){
				retFlg = $.trim(result);
				//console.log("retFlg: "+ retFlg);
				return retFlg;
			}else{
				return 'Y';
			}
		}
	});	
	
	
}


</script>
</html>