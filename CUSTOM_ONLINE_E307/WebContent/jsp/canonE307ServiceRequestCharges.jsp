
<%@ page import="com.canon.apps.servreq.dao.*" %>  
<%@ page import="com.canon.apps.servreq.beans.*" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>  
<%@ page import="com.canon.apps.servreq.beans.CanonE307ChargesChngRsnRec" %>
<%@ page import="canon.apps.common.CanonCustomProfile"%>
<%!
	public String escape(String s){
		if(s!=null) return s.replace("\"","\\\"");
		return "";
	}
%>
<%
String pageTitle="Charges";
ArrayList<String> menuList = new ArrayList<String>();
 menuList.add("sp:btn:Billing Estimate:fnBillEstmt()");
// menuList.add("MENU3:N:Machine History:fnMachHistory();");
// menuList.add("MENU4:N:Add Task:fnAddTask();");	
// menuList.add("navEscalate:menuToggleEscalate:Escalations:N");	 
 menuList.add("sp:btn:Scratch Pad:opnSCPad();");
%>
<%@ include file="canonE307ServReqHeader.jsp" %> 
<link rel="stylesheet" href="<%=ctxPath%>/e307/css/canonE307ServiceReqCreate.css" type="text/css">

<div id="main_content" style="min-height: 924px;">  
		<div id="page_top"><h1>Advanced Service Call Center</h1></div>
		<div style="margin:10px 13px;"><h1>Charges</h1></div>		
<!-- 		<div class="table-inner">
			<div class="charges-information"> -->
			<%
				String strFsrNum = request.getParameter("fsrNum");
				String strTskNum = request.getParameter("tskNum");
				String strFtrSrvDt = util.checkNull(request.getParameter("ftrSrvDt"));
				String strPostalCd = util.checkNull(request.getParameter("postalCd"));
				String strCntryCd = util.checkNull(request.getParameter("cntryCd"));
				
				//System.out.println("Charges strFsrNum : " + strFsrNum+ "strTskNum: "+strTskNum);
				CanonE307ChargesDetailsDAO dtlObj = new CanonE307ChargesDetailsDAO();
				CanonE307ServiceReqChargesAPIUtil objApi= new CanonE307ServiceReqChargesAPIUtil();
	
				String strSaveChrgFlg=request.getParameter("saveChrgFlg");
				String retMsg="";
				String disStyle="";
				String errFlg = "N";
				String eMsgId = "eMsg";
				String rFlg="";
				System.out.println("strSaveChrgFlg : "+ strSaveChrgFlg);
				if("Y".equals(strSaveChrgFlg)){
					int tskCount = request.getParameter("tskSize")==null?0:Integer.parseInt(request.getParameter("tskSize"));
				//	String[] resArr = new String[2];
					 String[] resArr =  objApi.saveChargesDetails(request); //UC
			
					 rFlg=util.checkNull(resArr[0]);
						 if("Y".equalsIgnoreCase(rFlg)){
							 System.out.println("Charges Success..");
							// retMsg=resArr[1];
							 retMsg="Charges submitted successfully...";
							 disStyle = "padding-bottom: 5px;padding-left: 20px;padding-top:5px;font-size: 15px;";
							 eMsgId="sucMsg";
							// response.sendRedirect(ctxPath+"/e307/jsp/canonE307ServiceRequestCharges.jsp?fsrNum="+strFsrNum);
					      }else{
							  retMsg=resArr[1];
							  System.out.println("errorMsg : "+ retMsg);
							  disStyle = "padding-bottom: 5px;padding-left: 20px;padding-top:5px;font-size: 15px;";
							  errFlg="Y";
						  }
						//  response.sendRedirect(ctxPath+"/e307/jsp/canonE307ServiceRequestCharges.jsp?fsrNum="+strFsrNum);
				}
				strSaveChrgFlg="N";
				Object obj[] = dtlObj.getChrgHdrDtls(strFsrNum, strTskNum);
				CanonE307ServReqChargesHdrRec objBean = new CanonE307ServReqChargesHdrRec();
				if(obj!=null){
					 objBean =(CanonE307ServReqChargesHdrRec)obj[0];
				}
				CanonE307BillToCustCreditAuthBean authBeanObj = (CanonE307BillToCustCreditAuthBean)new CanonE307ServiceReqCreateDao().getCustInfo(util.checkNull(objBean.getStrSerNum()), util.checkNull(objBean.getSvcMachMstrPk()));
				
				CanonE307ServiceReqCreateDao crDao = new CanonE307ServiceReqCreateDao();
				CanonE307ServiceReqAPIUtil apiUti= new CanonE307ServiceReqAPIUtil();
				String[] arrAch = apiUti.getBillCode(objBean.getSvcMachMstrPk(), strFtrSrvDt, "0", "", "", util.checkNull(objBean.getStrTaskTypeCd()), loginUser, "N");  //UC
				
				String bllblFlg =crDao.getBllblFlg(objBean.getStrAttr3());
				   
				String  ahsTitle="";
				String  ahsMsg="";
				if(!"95".equals(objBean.getStrFsrStsCd())){
					if("Y".equals(util.checkNull(objBean.getStrAhsFlg()))){
						 if(bllblFlg.equalsIgnoreCase("N")){
							 ahsTitle="AFTER HOURS ENITITLED CALL";
							 ahsMsg="This Machine is Under Contract for After Hours Service <br> "+util.checkNull(objBean.getStrCovTm() ) ;
						 }else if(bllblFlg.equalsIgnoreCase("Y")){
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
								if("3".equals(objBean.getStrAttr3()) || "3X".equals(objBean.getStrAttr3())){
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
					 }else if("3".equals( util.checkNull(objBean.getStrTaskTypeCd())) && "ESS".equals(util.checkNull(objBean.getStrLnBzTpCd())) && "Y".equalsIgnoreCase(bllblFlg)){
							ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour. Charge for Labor and Parts Cost. <br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
							
					 }else if("3".equals(util.checkNull(objBean.getStrTaskTypeCd())) && "Y".equalsIgnoreCase(bllblFlg)){
					 	ahsMsg="The labor billing rate is $"+util.checkNull(arrAch[2])+" / hour.";
					 	
					  	if(util.checkNull(arrAch[4]).length()>0)
					  		ahsMsg = ahsMsg+ " Charge for Labor, plus a Flat Travel charge not to exceed $"+ util.checkNull(arrAch[4])+" will be added to the cost of service and Parts Cost.";
					  		//ahsMsg = ahsMsg+ " Charge for Labor plus Travel charge of $"+ util.checkNull(arrAch[4])+" and Parts Cost. ";
					  					
					  		ahsMsg = ahsMsg+ "<br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge." ;
					  				
					 }else if("Y".equalsIgnoreCase(bllblFlg)){
						if("3".equals(objBean.getStrAttr3())){
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
				}
				//System.out.println("bllblFlg: "+ bllblFlg+ "Line Of Business: " + objBean.getStrLnBzTpCd());
				

			if(ahsMsg!=null && (util.checkNull(ahsMsg)).length()>0){
			%>
				<div id="ahsMsg" style="margin:10px 13px;">
					<p id="ahsMsg" style="color: red;font-size: 15px;font-weight: bold;" align="left"><%=ahsMsg%></p>
				</div>
			<%}
			  if("Y".equalsIgnoreCase(rFlg)){ %>
				<div id="errorWidget"  style="<%=disStyle%>">
					<p id="<%=eMsgId%>"><%=retMsg%></p>
				</div>
			<%
			}else if("N".equalsIgnoreCase(objBean.getStrOnlnValdFlg() )){ %>
				<div id="errorWidget"  style="margin:10px 13px;">
					<p id="errMsg" style="color:red;font-weight:bold;font-size: 15px;">You cannot process it during nightly batch processing because inventory discrepancy will occur.</p>
				</div>
			<%}else{ %>
				<div id="errorWidget"  style="<%=disStyle%>">
					<p id="errMsg" style="color:red;font-weight:bold;font-size: 15px;"><%=retMsg%></p>
				</div>
			<%} %>
			<form name="chrgFrm" id="chrgFrm" action="canonE307ServiceRequestCharges.jsp" method="post">
				<input type="hidden" id="saveChrgFlg" name="saveChrgFlg" value="<%=strSaveChrgFlg%>">
				<input type="hidden" id="svcMachMstrPk" name="svcMachMstrPk" value="<%=objBean.getSvcMachMstrPk() %>">
				<input type="hidden" id="fsrNum" name="fsrNum" value="<%=strFsrNum%>">
				<input type="hidden" id="userName" name="userName" value="<%=loginUser%>">
				<input type="hidden" name="scratchPad" id="scratchPad" value="">
				<input type="hidden" name="txRefNum" id="txRefNum" value="<%=util.checkNull(objBean.getStrAttr1()) %>">
				<input type="hidden" name="editRole" id="editRole" value="<%=editRole%>">	
				 <input type="hidden" name="callTpCd" id="callTpCd" value="<%=util.checkNull(objBean.getStrTaskTypeCd()) %>"> 
				<input type="hidden" id="ftrSrvDt" name="ftrSrvDt" value="<%=strFtrSrvDt%>">
				<input type="hidden" id="postalCd" name="postalCd" value="<%=strPostalCd%>">
				<input type="hidden" id="cntryCd" name="cntryCd" value="<%=strCntryCd%>"> 
				
			<div class="charges-information">
				<table width="100%" cellspacing="5">
					<tr>
						<th colspan="8">Charges Information</th>
					</tr>
					<tr >
						<td width="10%">Service Request#</td>
						<td width="15%"><input type="text" name="srvRqst" id="srvRqst" value="<%=util.checkNull(objBean.getStrFsrNum()) %>" class="rdl"></td>
						<td width="10%">Serial#</td>
						<td width="15%"><input type="text" name="srlNum" id="srlNum" value="<%=util.checkNull(objBean.getStrSerNum()) %>" class="rdl"></td>
						<td width="10%">Charges#</td>
						<td width="15%"><input type="text" name="chrgNum" id="chrgNum" value="<%=util.checkNull(objBean.getStrChrgNum()) %>" class="rdl"></td>
						<td width="10%">Invoice Date</td>
						<td width="15%"><input type="text" name="invDt" id="invDt" value="<%=util.checkNull(objBean.getStrInvcDate()) %>" class="rdl"></td>
					</tr>
					<tr>
						<td>Task#</td>
						<td><input type="text" name="tskNum" id="tskNum" value="<%=util.checkNull(objBean.getStrTskNum()) %>" class="rdl"></td>
						<td>Model</td>
						<td><input type="text" name="mdl" id="mdl" value="<%=util.checkNull(objBean.getStrModel()) %>" class="rdl"></td>
						<td>Invoice#</td>
						<td><input type="text" name="invNum" id="invNum" value="<%=util.checkNull(objBean.getStrInvcNum()) %>" class="rdl"></td>
						<td>Invoice Status</td>
						<td><input type="text" name="invSts" id="invSts" value="<%=util.checkNull(objBean.getStrInvcSts()) %>" class="rdl"></td>
					</tr>
					<tr>
						<td>Contract#</td>
						<td><input type="text" name="cntrctNum" id="cntrctNum" value="<%=util.checkNull(objBean.getStrContractNum()) %>" class="rdl"></td>
						<td>Coverage Type</td>
						<td><input type="text" name="cvrgTpe" id="cvrgTpe" value="<%=util.checkNull(objBean.getStrCovrgTpe()) %>" class="rdl"></td>
						<td>Currency</td>
						<td><input type="text" name="invCurncy" id="invCurncy" value="<%=util.checkNull(objBean.getStrInvCurncy()) %>" class="rdl"></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
	 <div class="table-wrapper"> 
		  <table class="service-new" style="width:100%" cellspacing="5" cellpadding="0">
		 	<tr>
				<td style="width:30%;" valign=top>
					<table cellspacing="5" width="100%">
						<tr>
							<th style="width:230px;">Bill To</th>
						</tr>
								<tr valign="top"><td ><%=util.checkNull(objBean.getStrBilCustCd()) %></td></tr>
								<tr><td style="width:230px;"><%=util.checkNull(objBean.getStrBilCustNm()) %></td></tr>
								<tr><td style="width:230px;"><%=util.checkNull(objBean.getStrBilAddr1()) %> <%=util.checkNull(objBean.getStrBilAddr2()) %>,</td></tr>
								<tr><td style="width:230px;"><%=util.checkNull(objBean.getStrBilCity()) %>,<%=util.checkNull(objBean.getStrBilState()) %>, <%=util.checkNull(objBean.getStrBilZipCd()) %></td></tr>
					 	</table>
					</td>
					<td style="width:30%;" valign='top'>
						<table style="width:100%;" cellspacing="5">
							<tr>
								<th >Ship To</th>
							</tr>
						 	    <tr valign="top"><td ><%=util.checkNull(objBean.getStrShpCustCd()) %></td></tr>
								<tr><td ><%=util.checkNull(objBean.getStrShpCustNm()) %></td></tr>
								<tr><td ><%=util.checkNull(objBean.getStrShpAddr1()) %> <%=util.checkNull(objBean.getStrShpAddr2()) %>,</td></tr>
								<tr><td ><%=util.checkNull(objBean.getStrShpCity()) %>,<%=util.checkNull(objBean.getStrShpState()) %>, <%=util.checkNull(objBean.getStrShpZipCd()) %></td></tr>
						</table>
					</td>	
					<td style="width:30%;" valign=top>
		                 <table cellspacing="5" width="100%">
					        <tr><th class="hd" colspan="3">Credit Card Authorization</th></tr>
					        <tr><td nowrap>Profile ID</td><td><input  type="text"  name="profileId" id="profileId" value="<%=util.checkNull(objBean.getStrProfileId()) %>" class="rdl" /></td><td>&nbsp;</td></tr>
					        <tr><td nowrap>Holder Name</td><td><input  type="text"  name="holderName" id="holderName" value="<%=util.checkNull(objBean.getStrHldrNm()) %>"></td><td>&nbsp;</td></tr>
					        <tr><td nowrap>Auth. Amt </td><td><input  type="text"  name="authAmnt" id="authAmnt" value="<%=util.checkNull(objBean.getStrAuthAmt()) %>" onchange="chngCRLbl()">
					        <%if("Y".equals(objBean.getStrUpdFlg())){  %>
					       		<td align="center" nowrap><a id="crBtn" href="#"  class="btn enter_cc disp_mode" onclick="enterCC();">Enter Credit Card</a></td>
					        <%} %>
					        </tr>
					        <input type="hidden" name="cardType" id="cardType" value="<%=util.checkNull(objBean.getStrCardTp()) %>">
						    <input type="hidden" name="cardExpr" id="cardExpr" value="<%=util.checkNull(objBean.getStrExprDt()) %>">
						    <input type="hidden" name="ccNum" id="ccNum" value="<%=util.checkNull(objBean.getStrAttr2() ) %>">
			          	</table>
		            </td>				  
			  </tr>
		  </table>
	</div>
	  <div class="hLineLg"></div>
<%-- 	  	    <div id="errorWidget"  style="<%=disStyle%>">
				<p id="errMsg" style="color:red;font-weight:bold;"></p>
			</div> --%>
			 <%if("Y".equals(objBean.getStrUpdFlg())){  %>
				<div class="plus-minus"><a id="imgDiv" class="disabled" href="javascript:fnDeleteChargeRow()">-</a> <a href="javascript:fnAddMoreCharges()">+</a></div>
			 <%}  %> 
				<table class="charges-table" cellspacing="1" id="charge_table">				
					<tr>
						<th width="1%"></th>
						<th width="10%;">Billing Type</th>
						<th width="8%">Item#</th>
						<th width="14%">Item Description</th>
						<th width="6%">Qty</th>
						<th width="4%">UOM</th>
						<th width="7%">Unit List Price</th>
						<th width="7%">Override Price</th>
						<th width="7%">Extended Amount</th>
						<th width="7%">Net Price</th>
						<th width="5%">No Charge Flag</th>
						<th width="18%">Change Reason</th>		
					</tr>
					<%
					String strChrgFlg="";
					String strTransType="";
					String strChrgSrc="";
					String strCreationDt="";
					String strPriceList="";
					String strSrcNum="";
					String bclr="";
					String bclr1="";
					double contPrice=0.0;
					int totRecords=0;
					int i=0;
					String strCheck ="";
					DecimalFormat df = new DecimalFormat("0.00");
					String rdCls="";
					if("Y".equals(objBean.getStrUpdFlg())){
						rdCls="";
					}
					else{
						rdCls="upd";
					}
					ArrayList<CanonE307ServReqChrgDtlsRec> dtlLst = new ArrayList<CanonE307ServReqChrgDtlsRec>();
					if("Y".equals(errFlg)){
						String strRecSize = util.checkNull(request.getParameter("recSize"));
						int recSz = 0;
						 if(!("".equals(strRecSize))&&!("null".equals(strRecSize))){
							 recSz=Integer.parseInt(strRecSize);
						 }	
						// System.out.println("Inside err flg recSz : "+recSz);
						 for(int x=0;x<recSz;x++){
							 CanonE307ServReqChrgDtlsRec dtlRec = new CanonE307ServReqChrgDtlsRec();
							 String strDtlPk = util.checkNull(request.getParameter("fsrChrgDtlPk"+x));
							 if(strDtlPk!=null && strDtlPk.length()>0)
								 dtlRec.setFsrChrgDtlPk(Integer.parseInt(strDtlPk));
							 dtlRec.setStrFsrVstNum(util.checkNull(request.getParameter("fsrVstNum"+x)));
							 dtlRec.setStrChrgTrxTpCd(util.checkNull(request.getParameter("svcChrgTrxTpCd"+x)));
							 dtlRec.setStrItmNum(util.checkNull(request.getParameter("itmNum"+x)));
							 dtlRec.setStrUomCd(util.checkNull(request.getParameter("uomCd"+x)));
							// String strovrdPrc = util.checkNull(request.getParameter("ovrRdPrc"+x));
							// if(strovrdPrc!=null && strovrdPrc.length()>0)
							 	dtlRec.setUnitOvrRidPrice(util.checkNull(request.getParameter("ovrRdPrc"+x)));
							 dtlRec.setStrNoChrgFlg(util.checkNull(request.getParameter("chrgFlg"+x)));
							 dtlRec.setStrChangRsn(util.checkNull(request.getParameter("chngRsn"+x)));
							 String strConDscRt = util.checkNull(request.getParameter("contDiscRt"+x));
							 if(strConDscRt!=null && strConDscRt.length()>0)
							 dtlRec.setContractPrice(Double.parseDouble(strConDscRt));
							 dtlRec.setStrPrcCatgCd(request.getParameter("prcCatgCd"+x));
							 dtlRec.setStrSvcChrgTpCd(request.getParameter("svcInvChrgTpCd"+x));
							 dtlRec.setStrLnNum(request.getParameter("lnNum"+x));
							 dtlRec.setStrBilngTpe(request.getParameter("bilTpe"+x));
							// dtlRec.setStrItmNum(request.getParameter("mdseCd"+x));
							 String strQty = util.checkNull(request.getParameter("qty"+x));
							 if(strQty!=null && strQty.length()>0)
							 	dtlRec.setQty(Integer.parseInt(strQty));
							 else
								 dtlRec.setQty(0);
							 String strntPrc = util.checkNull(request.getParameter("netPrice"+x));
							 if(strntPrc!=null && strntPrc.length()>0)
							 	dtlRec.setNetPrice(Double.parseDouble(strntPrc));
							 else
								 dtlRec.setNetPrice(0.0);
							 String strExtnAmt = util.checkNull(request.getParameter("extndAmt"+x));
							 if(strExtnAmt!=null && strExtnAmt.length()>0)
								 dtlRec.setExtendedAmt(Double.parseDouble(strExtnAmt));
							 else
								 dtlRec.setExtendedAmt(0);
							 dtlRec.setStrTrxTpe(request.getParameter("trxTpe"+x));
							 dtlRec.setStrTrxPriceLst(request.getParameter("priceLst"+x));
							 dtlRec.setStrTrxSrc(request.getParameter("trxSrc"+x));
							 dtlRec.setStrCreationDt(request.getParameter("creationDt"+x));
							 dtlRec.setStrTrxSrcRef(request.getParameter("trxSrcRef"+x));
							 dtlRec.setStrItmDesc(request.getParameter("itmDesc"+x));
							 String strUntLstPrc = util.checkNull(request.getParameter("untLstPrice"+x));
							 if(strUntLstPrc!=null && strUntLstPrc.length()>0)
							 	dtlRec.setUnitLstPrice(Double.parseDouble(strUntLstPrc));
							 else
								 dtlRec.setUnitLstPrice(0.0); 
							 String strContPrc = util.checkNull(request.getParameter("contPrc"+x));
							 if(strContPrc!=null && strContPrc.length()>0)
							 	dtlRec.setContractPrice(Double.parseDouble(strContPrc));
							 else
								 dtlRec.setContractPrice(0.0); 
							 dtlRec.setStrSvcChrgDiscRt(request.getParameter("contDiscRt"+x));
							 dtlLst.add(dtlRec);
						 }
						 
					}else{
						dtlLst = dtlObj.getChargeDetails(strFsrNum, "");
					}
					ArrayList<CanonE307ChargesChngRsnRec> chngRsnLst = dtlObj.getChangRsn();
					//ArrayList<CanonE307ChargesChngRsnRec> chrgPrcLst = dtlObj.getPriceList();
					ArrayList<CanonE307ChargesChngRsnRec> blngTpeLst = dtlObj.getBlngTypes();
					
					if(dtlLst!=null && dtlLst.size()>0){
						System.out.println("rec size: "+dtlLst.size());
						totRecords=dtlLst.size();
						
						for(CanonE307ServReqChrgDtlsRec dtlLstBean : dtlLst){
							if((i%2) == 0)
							   {
							     bclr   = "evenRow";
							   }
							   else
							   {
							     bclr   = "oddRow";
							  }

							if("N".equals(dtlLstBean.getStrNoChrgFlg())){
								strChrgFlg="checked";
							}else{
								strChrgFlg="";
							}
							if(i==0){
								strTransType=dtlLstBean.getStrTrxTpe();
								strChrgSrc=dtlLstBean.getStrTrxSrc();
								strCreationDt=dtlLstBean.getStrCreationDt();
								strPriceList=dtlLstBean.getStrTrxPriceLst();
								strSrcNum=dtlLstBean.getStrTrxSrcRef();
								contPrice = dtlLstBean.getContractPrice();
								strCheck = "checked";
								//System.out.println("strTransType : " + strTransType+" strChrgSrc : "+strChrgSrc+" strCreationDt : "+strCreationDt+" strPriceList: "+strPriceList+" strSrcNum: "+strSrcNum);
							}else{
								strCheck = "";
							}
							String recVal = request.getParameter("recVal"+i)==null?"E":request.getParameter("recVal"+i);
					%>
					<tr id='chrgRow<%=i %>' class="<%=bclr%>">
						<input type="hidden" name="chrgTskNum<%=i%>" id="chrgTskNum<%=i%>" value="<%=dtlLstBean.getStrTskNum() %>">
						<input type="hidden" name="fsrVstNum<%=i%>" id="fsrVstNum<%=i%>" value="<%=dtlLstBean.getStrFsrVstNum() %>">
						<input type="hidden" name="svcChrgTrxTpCd<%=i%>" id="svcChrgTrxTpCd<%=i%>" value="<%=dtlLstBean.getStrChrgTrxTpCd() %>">
						<input type="hidden" name="prcCatgCd<%=i%>" id="prcCatgCd<%=i%>" value="<%=dtlLstBean.getStrPrcCatgCd() %>">
						<td><input type="radio" id="chrgSel<%=i%>" name="chrgSel" value="<%=i%>" <%=strCheck%> style="border:0px;" onclick="fnPopulateInfo('<%=i%>','<%=dtlLstBean.getStrLnNum()%>');fnRmvImg();"></td>
					    <input type="hidden" name="lnNum<%=i%>" id="lnNum<%=i%>" value="<%=util.checkNull(dtlLstBean.getStrLnNum()) %>" >
					    <input type="hidden" name="chngRsnFlg<%=i%>" id="chngRsnFlg<%=i%>" value="">
					    <%
					    System.out.println("recVal: "+recVal);
					    if("E".equals(recVal)){
					    %>
					    	<td><%=util.checkNull(dtlLstBean.getStrBilngTpe()) %></td>
					    	<input type="hidden" name="fsrChrgDtlPk<%=i%>" id="fsrChrgDtlPk<%=i%>" value="<%=dtlLstBean.getFsrChrgDtlPk() %>">
					    	<input type="hidden" name="recVal<%=i%>" id="recVal<%=i%>" value="<%=recVal%>">
					    	<input type="hidden" name="svcInvChrgTpCd<%=i%>" id="svcInvChrgTpCd<%=i%>" value="<%=dtlLstBean.getStrSvcChrgTpCd() %>">
					    <%
					    }else{
					    	%>
					    	<td><select id="svcInvChrgTpCd<%=i%>" onchange="fnUpdatePrcCatgCd('<%=i%>')" style="width: 62px;" name="svcInvChrgTpCd<%=i%>">
					    	<%
							   if(blngTpeLst!=null && blngTpeLst.size()>0){
								   for(CanonE307ChargesChngRsnRec bTpBean : blngTpeLst){
								   String blTpSel = "";  
								   if(util.checkNull(dtlLstBean.getStrSvcChrgTpCd()).equals(util.checkNull(bTpBean.getStrReasonCode()))){
								  	 blTpSel="selected";
							   		} 
							 %>
								<option value="<%=bTpBean.getStrReasonCode() %>" <%=blTpSel %>><%=bTpBean.getStrReasonNm() %></option> 
							 <% 
								   }
							   }
					    	%>
					    	</select></td>
					    	<input type="hidden" name="fsrChrgDtlPk<%=i%>" id="fsrChrgDtlPk<%=i%>" value="">
					    	<input type="hidden" name="recVal<%=i%>" id="recVal<%=i%>" value="NE">
					    	<%
					    }
					    %>
						<input type="hidden" name="bilTpe<%=i%>" id="bilTpe<%=i%>" value="<%=util.checkNull(dtlLstBean.getStrBilngTpe()) %>" >
						<%
					    if("E".equals(recVal)){
					    %>
							<td><%=util.checkNull(dtlLstBean.getStrItmNum()) %>
							<input type="hidden" name="itmNum<%=i%>" id="itmNum<%=i%>" value="<%=util.checkNull(dtlLstBean.getStrItmNum()) %>"></td>
						<%
					    }else{
					    %>
					   		<td nowrap><input type="text" name="itmNum<%=i%>" id="itmNum<%=i%>" style="width:70%;" value="<%=util.checkNull(dtlLstBean.getStrItmNum()) %>"><img height="21" onclick="openItemLov('chargeItemDiv','<%=i%>')" alt="" src="/s21extn/common/images/download.png"></td>
					    <%
					    }
						%>
						<input type="hidden" name="mdseCd<%=i%>" id="mdseCd<%=i%>" value="<%=util.checkNull(dtlLstBean.getStrItmNum()) %>">
					<!-- 	<td style="padding:0px;padding-top:8px;"><img src="<%=imgSrc1%>" alt='' height='21' onClick="openItemLov('chargeItemDiv','<%=i%>')" class='rdl'></td>  -->
						<td><%=util.checkNull(dtlLstBean.getStrItmDesc()) %></td>
						<td><input type="text" name="qty<%=i%>" id="qty<%=i%>" value="<%=dtlLstBean.getQty() %>" style="width:85%;align:right" class="autoInteger <%=rdCls %>" onchange="fnCalcExtdAmt('<%=i%>')"></td>
						<input type="hidden" name="exQty<%=i%>" id="exQty<%=i%>" value="<%=dtlLstBean.getQty() %>" >
						<%
					    if("E".equals(recVal)){
					    %>
							<td><%=util.checkNull(dtlLstBean.getStrUomCd()) %></td>
							<input type="hidden" id="uomCd<%=i%>" name="uomCd<%=i%>" value="<%=util.checkNull(dtlLstBean.getStrUomCd()) %>">
						<%
				    	}else{
				    	%>
				    		<td><input type="text" id="uomCd<%=i%>" name="uomCd<%=i%>" value="<%=util.checkNull(dtlLstBean.getStrUomCd()) %>" style="width:23px;"></td>
				    	<%
				    	}
						%>
						
						<%
					    if("E".equals(recVal)){
					    %>
							<td><%=df.format(dtlLstBean.getUnitLstPrice()) %></td>
							<input type="hidden" name="untLstPrice<%=i%>" id="untLstPrice<%=i%>" value="<%=df.format(dtlLstBean.getUnitLstPrice()) %>" >
						<%}else{
							%>
							<td><input type="text" name="untLstPrice<%=i%>" id="untLstPrice<%=i%>" value="<%=df.format(dtlLstBean.getUnitLstPrice()) %>" onchange="fnCalcExtdAmt('<%=i%>')"></td>	
						<%
						} %>

						<td><input type="text" name="ovrRdPrc<%=i%>" id="ovrRdPrc<%=i%>" value="<%=util.checkNull(dtlLstBean.getUnitOvrRidPrice()) %>" style="width:85%;align:right;" class="<%=rdCls %>" ></td>
						<input type="hidden" name="exOvrRdPrc<%=i%>" id="exOvrRdPrc<%=i%>" value="<%=util.checkNull(dtlLstBean.getUnitOvrRidPrice()) %>">
						<td id="extdAmt<%=i%>"><%=df.format(dtlLstBean.getExtendedAmt()) %></td>
						<td id="netPrc<%=i%>"><%=df.format(dtlLstBean.getNetPrice())%></td>
						<td><input type="checkbox" id="chrgFlg<%=i%>" name="chrgFlg<%=i%>" <%=strChrgFlg%> style="border:0px;" class="<%=rdCls %>"></td>
						<input type="hidden" name="exChrgFlg<%=i%>" id="exChrgFlg<%=i%>" value="<%=dtlLstBean.getStrNoChrgFlg() %>">
						<td><select name="chngRsn<%=i%>" id="chngRsn<%=i%>" style="width:95%;align:right;" class="<%=rdCls %>">
						<option value="">Select</option>
							<%
							String rsnCd = util.checkNull(dtlLstBean.getStrChangRsn());
							System.out.println("rsnCd: "+ rsnCd);
							if(chngRsnLst!=null && chngRsnLst.size()>0){
								for(CanonE307ChargesChngRsnRec rsnBean : chngRsnLst){
									String selOption= "";
									if(rsnBean.getStrReasonCode().equals(rsnCd)){
										selOption="selected";
									}
							%>
								<option value="<%=rsnBean.getStrReasonCode()%>" <%=selOption %>><%=rsnBean.getStrReasonDesc() %></option>
							<%
								}
							}
							%>
							</select>
						</td>
						<input type="hidden" name="netPrice<%=i%>" id="netPrice<%=i%>" value="<%=dtlLstBean.getNetPrice() %>" >
						<input type="hidden" name="extndAmt<%=i%>" id="extndAmt<%=i%>" value="<%=dtlLstBean.getExtendedAmt() %>">
						<input type="hidden" id="trxTpe<%=i %>" name="trxTpe<%=i %>" value="<%=util.checkNull(dtlLstBean.getStrTrxTpe())%>">
						<input type="hidden" id="priceLst<%=i%>" name="priceLst<%=i%>" value="<%=util.checkNull(dtlLstBean.getStrTrxPriceLst()) %>">
						<input type="hidden" id="trxSrc<%=i%>" name="trxSrc<%=i%>" value="<%=util.checkNull(dtlLstBean.getStrTrxSrc())%>">
						<input type="hidden" id="creationDt<%=i %>" name="creationDt<%=i %>" value="<%=util.checkNull(dtlLstBean.getStrCreationDt())%>">
						<input type="hidden" id="trxSrcRef<%=i%>" name="trxSrcRef<%=i%>" value="<%=util.checkNull(dtlLstBean.getStrTrxSrcRef())%>">
						<input type="hidden" name="itmDesc<%=i%>" id="itmDesc<%=i%>" value="<%=util.checkNull(dtlLstBean.getStrItmDesc()) %>">
						<%-- <input type="hidden" name="uomCd<%=i%>" id="uomCd<%=i%>" value="<%=util.checkNull(dtlLstBean.getStrUomCd()) %>"> --%>
						
						<input type="hidden" name="contPrc<%=i %>" id="contPrc<%=i%>" value="<%=dtlLstBean.getContractPrice() %>">
						<input type="hidden" name="contDiscRt<%=i %>" id="contDiscRt<%=i%>" value="<%=dtlLstBean.getStrSvcChrgDiscRt() %>">

					</tr>
					<%	
					i++;
						}
					}
					%>
					
					<input type="hidden" id="recSize" name="recSize" value="<%=totRecords%>">
					
				</table>
			</form>	
			
<!-- 		</div>
	</div> -->
			<div class="transaction-details" id="transDetDiv">
				<table cellspacing="5" >
					<tr>
						<th colspan="4">Transaction Details</th>
					</tr>
					<tr valign="top">
						<td width="20%">Transaction Type:</td>
						<td width="30%" id="trsTpsTd"><input type="text" name="trnsTpe" id="trnsTpe" value="<%=util.checkNull(strTransType)%>" class='rdl' onchange="fnGetTrnsTpeVal()"></td>
						<td width="20%">Price List:</td>
						<td width="30%" id="prsLstTd" nowrap><input type="text" name="prcLst" id="prcLst" value="<%=util.checkNull(strPriceList)%>" class='rdl'></td>
					</tr>
					
					<tr>
						<td>Charge Source:</td>
						<td><input type="text" id="chrgSrc" name="chrgSrc" value="<%=util.checkNull(strChrgSrc) %>" class='rdl'></td>
						<td>Source Number:</td>
						<td><input type="text" id="srcNum" name="srcNum" value="<%=util.checkNull(strSrcNum)%>" class='rdl'></td>
					</tr>
					
					<tr>
						<td>Creation Date:</td>
						<td><input type="text" id="crtionDt" name="crtionDt" value="<%=util.checkNull(strCreationDt)%>" class='rdl'></td>
						<td>Contract Price:</td>
						<td><input type="text" id="contPrice" name="contPrice" value="<%=contPrice%>" class='rdl'></td>
					</tr>
				</table>
			</div>

			 <div class="right clear" style="padding: 10px 0 20px 0;"><!-- <a href="javascript:void fnUpdateChrgs()" class="btn" >Update Changes</a>&nbsp;&nbsp; -->
			    <a class="btn" href="javascript:void fnBackSummary()">Back</a>  
			  <!--  <a class="btn" href="javascript:void fnCheckVal()">Back</a>  -->
			  
			<%
			if("Y".equals(objBean.getStrUpdFlg())){
			%>			 
			 <a href="javascript:void fnSaveChrgs()" class="btn disp_mode">Submit Charges</a>
			<%
			}
			%>			 
			<!--  <div class="right clear" style="padding: 10px 0 20px 0;"><input type="submit" tabindex="7" name="CompositeSearch" id="CompositeSearch" value="Submit Changes" class="btn"></div> -->
			</div>			

		

	</div>
</body>
<script>

function fnPopulateInfo(iVal, lineVal){
	var srcVal = $('#trxSrc'+iVal).val();
//	if(srcVal =='Manual'){
		fnAddRemoveCls(lineVal);
//	}
	var recVal = $('#recVal'+iVal).val();
	if(recVal=='E'){
		$('#trsTpsTd').html("<input type='text' id='trnsTpe' name='trnsTpe' value='' class='rdl'>");
		$('#prsLstTd').html("<input type='text' id='prcLst' name='prcLst' value='' class='rdl'>");
		$('#trnsTpe').val($('#trxTpe'+iVal).val());
		$('#prcLst').val($('#priceLst'+iVal).val());
		$('#chrgSrc').val($('#trxSrc'+iVal).val());
		$('#crtionDt').val($('#creationDt'+iVal).val());
		$('#srcNum').val($('#trxSrcRef'+iVal).val());
		fnReadOnly();		
	}else{
		 $('#trsTpsTd').html("<select id='trnsTpe' name='trnsTpe' onchange='fnGetTrnsTpeVal()' style='margin-bottom: 8px;margin-left: 8px;'></select>");
		// $('#prsLstTd').html("<select id='prcLst' name='prcLst' onchange='fnGetPrcLstVal()' style='margin-bottom: 8px;margin-left: 8px;'></select>");
		$('#prsLstTd').html("<input type='text' id='prcLst' name='prcLst' onchange='fnGetPrcLstVal()' style='margin-bottom: 8px;margin-left: 8px;width: 182px;'> <img  src='<%=imgSrc1%>' alt='' height='21' onClick=openPrcLstLov('chargeItemDiv') > ");
		 setCallSelects('tt',''); 
		// setCallSelects('pl','');
		 
		 var ttSelVal = $('#trxTpe'+iVal).val();
		 var plSelVal = $('#priceLst'+iVal).val();
		 $('#trnsTpe').val($('#trxTpe'+iVal).val());
		 $('#prcLst').val($('#priceLst'+iVal).val());
		 $('#chrgSrc').val($('#trxSrc'+iVal).val());
		 $('#crtionDt').val($('#creationDt'+iVal).val());
		 $('#srcNum').val($('#trxSrcRef'+iVal).val());	
		 $('#trnsTpe'+' option[value="' + ttSelVal + '"]').prop('selected', true);
		 $('#prcLst'+' option[value="' + plSelVal + '"]').prop('selected', true);
	}
}
function fnReadOnly(){
	$(".rdl").each(function (){
		  var ele=$(this);
		  var tp = $(ele).attr("type");
		  if(tp=="text"){
			  $(ele).addClass("rdl").attr("readonly","readonly"); 
		 	 $(ele).addClass('disabled');
		  }else {
			  $(ele).addClass("rdl").attr("disabled","true");	  
		  } 
	});
}
function fnupdateOnly(){
	$(".upd").each(function (){
		  var ele=$(this);
		  var tp = $(ele).attr("type");
		  if(tp=="text"){
			  $(ele).addClass("rdl").attr("readonly","readonly"); 
		  }else {
			  $(ele).attr("disabled","true");	  
		  } 
	});
}
function fnAddRemoveCls(lineVal){
	if(lineVal == -1){
		$('#transDetDiv :input').attr('disabled', false);
		$('#transDetDiv :input').removeClass("rdl");
		$('#transDetDiv :input').addClass("required_field");
		$('#transDetDiv :input').css({"background-color":"#ffff00"});
	}else{
	//	$('#transDetDiv :input').removeClass("required_field");
		$('#transDetDiv :input').attr('disabled', true);
	//	$('#transDetDiv :input').attr("readonly","readonly"); 
	//	$('#transDetDiv :input').addClass("rdl");
		$('#transDetDiv :input').css({"background-color":"#cccccc"});
		
	}
}

function fnIntValidate(){
	$("input.autoInteger").autoNumeric({aSep: '', mDec: 0});
	$("input.autoDecimal").autoNumeric({aSep: '', mDec: 2});	
}

$(function(){
	fnIntValidate();
	fnReadOnly();
	fnupdateOnly();
});
function fnChngImg(){
	 $('#imgDiv').removeClass('disabled');
}
function fnRmvImg(){
	 $('#imgDiv').addClass('disabled');
}

function fnAddMoreCharges(){
	<%
	if("Y".equals(objBean.getStrUpdFlg())){
	%>
    $("input[name='chrgSel']").each(function (){

     //   var tId=$(this).attr("id");
     //    if(tId!=rId)
           $(this).prop("checked",false);
     });
    var bgColor="";
	var countTbl = $('#charge_table tr').length;
	var rwCnt = countTbl-2;
	var bgClr = $('#chrgRow'+rwCnt).attr("class");
	if(bgClr=='evenRow'){
		bgColor="oddRow";
	}else{
		bgColor="evenRow";
	}
/* 	var bg = $('#chrgRow'+rwCnt).css("background-color");
	if(bg=='#FFFFFF'){
		bgColor="#ebebeb";
	}else{
		bgColor='#FFFFFF';
	} */
	var count = countTbl-1;
	 var newRow = 
		  "<tr id='chrgRow"+count+"' class='"+bgColor+"'> "+
		  	   " <td><input type=radio name='chrgSel' id='chrgSel"+count+"' value='"+count+"' checked onclick=fnChngImg();fnPopulateInfo('"+count+"','-1')> "+
		  	   " <\/td> "+
			//   "<td> <input type='text' id='bilTpe"+count+"' name='bilTpe"+count+"' style='width:80px'>" +
				"<td> <select id='svcInvChrgTpCd"+count+"' name='svcInvChrgTpCd"+count+"' style='width: 95%;' onchange=fnUpdatePrcCatgCd('"+count+"')> "
				   <%
				   if(blngTpeLst!=null && blngTpeLst.size()>0){
					   for(CanonE307ChargesChngRsnRec bTpBean : blngTpeLst){
						  %>
							var bTpNm = '<%=bTpBean.getStrReasonNm()%>';
							var bTpCd = '<%=bTpBean.getStrReasonCode() %>';
							newRow = newRow + 
							" <option value='"+bTpCd+"'>"+bTpNm+"<\/option> " ;
						  <% 
					   }
				   }
				   %>
					newRow = newRow + 				
					"<\/select>	"+
				
			   " </select> " +
			   "  <\/td> "+
			   "<td nowrap> <input type='text' id='itmNum"+count+"' name='itmNum"+count+"' style='width:70%;height:23px;'>" +
			   " <img  src='<%=imgSrc1%>' alt='' height='21' onClick=openItemLov('chargeItemDiv','"+count+"') >" +
			   "  <\/td> "+
			   "<td id='itmDsc"+count+"'> " +
			   "  <\/td> "+
			   "<td> <input type='text' id='qty"+count+"' name='qty"+count+"' class='autoInteger'  value='0' style='width:85%;height:23px;' onchange=fnCalcExtdAmt('"+count+"') >" +
			   "  <\/td> "+
			   "<td> <input type='text' id='uomCd"+count+"' name='uomCd"+count+"' style='width:98%;height:23px;'>" +
			   "  <\/td> "+	
			   "<td> <input type='text' id='untLstPrice"+count+"' name='untLstPrice"+count+"' value='0.00' class='autoDecimal' style='width:98%;height:23px;' onchange=fnCalcExtdAmt('"+count+"')>" +
			   "  <\/td> "+	
			   "<td> <input type='text' id='ovrRdPrc"+count+"' name='ovrRdPrc"+count+"' value=''  style='width:85%;height:23px;'>" +
			   "  <\/td> "+	
			   "<td id='extdAmt"+count+"'> 0.00" +
			   "  <\/td> "+	
			   "<td id='netPrc"+count+"'> 0.00" +
			   "  <\/td> "+				   
			   "<td> <input type='checkbox' id='chrgFlg"+count+"' name='chrgFlg"+count+"' style='border:0px;'>" +
			   "  <\/td> "+	
			   "<td> "+
			   " <select id='chngRsn"+count+"' name='chngRsn"+count+"' style='width:98%'>"  +
			   " <option value=''>Select</option>";
			   <%
			   if(chngRsnLst!=null && chngRsnLst.size()>0){
				   for(CanonE307ChargesChngRsnRec rsnBean : chngRsnLst){
					  %>
						var rsnNm = '<%=rsnBean.getStrReasonNm()%>';
						var rsnCd = '<%=rsnBean.getStrReasonCode() %>';
						newRow = newRow + 
						" <option value='"+rsnCd+"'>"+rsnNm+"<\/option> " ;
					  <% 
				   }
			   }
			   %>
				newRow = newRow + 				
				"<\/select>	"+
			   "  <\/td> "+
			   " <input type='hidden' name='netPrice"+count+"' id='netPrice'"+count+" value=''> "+
			   " <input type='hidden' name='extndAmt"+count+"' id='extndAmt"+count+"' value=''> "+
			    " <input type='hidden' id='trxTpe"+count+"' name='trxTpe"+count+"' value=''> "+
			   " <input type='hidden' id='priceLst"+count+"' name='priceLst"+count+"' value=''> "+
			   " <input type='hidden' id='trxSrc"+count+"' name='trxSrc"+count+"' value=''> "+
			   " <input type='hidden' id='creationDt"+count+"' name='creationDt"+count+"' value=''> "+
			   " <input type='hidden' id='trxSrcRef"+count+"' name='trxSrcRef"+count+"' value=''> "+
			   " <input type='hidden' id='itmDesc"+count+"' name='itmDesc"+count+"' value=''> "+
			   " <input type='hidden' name='contPrc"+count+"' id='contPrc"+count+"' value=''> "+ 
			   " <input type='hidden' name='svcChrgTrxTpCd"+count+"' id='svcChrgTrxTpCd"+count+"' value=''> "+ 
			   " <input type='hidden' name='fsrChrgDtlPk"+count+"' id='fsrChrgDtlPk"+count+"' value=''> "+
			   " <input type='hidden' name='recVal"+count+"' id='recVal"+count+"' value='NE'> "+
			   " <input type='hidden' name='contDiscRt"+count+"' id='contDiscRt"+count+"' value=''> "+
			   " <input type='hidden' name='prcCatgCd"+count+"' id='prcCatgCd"+count+"' value=''> "+			   
			   " <\/tr> ";		   
	 $('#charge_table').append(newRow);
//	 $('#chrgRow'+count).css("background-color",bgColor);
	 $('#trsTpsTd').html("<select id='trnsTpe' name='trnsTpe' onchange='fnGetTrnsTpeVal()' style='margin-bottom: 8px;margin-left: 8px;width: 182px;'></select>"); 
	/*  $('#prsLstTd').html("<select id='prcLst' name='prcLst' onchange='fnGetPrcLstVal()' style='margin-bottom: 8px;margin-left: 8px;width: 182px;'> </select>"); */
	 $('#prsLstTd').html("<input type='text' id='prcLst' name='prcLst' onchange='fnGetPrcLstVal()' style='margin-bottom: 8px;margin-left: 8px;width: 182px;'> <img  src='<%=imgSrc1%>' alt='' height='21' onClick=openPrcLstLov('chargeItemDiv') > ");
	 $('#bilTpe'+count).val($('#svcInvChrgTpCd'+count).val());
	// setCallSelects('bc',count);
	 $('#creationDt'+count).val(GetTodayDate);
	
	 $('#crtionDt').val(GetTodayDate);
	 $('#contPrice').val('');
	 fnIntValidate();
//	 fnPopulateInfo(count,-1);
//	 fnRmvCls();
//	 fnAddCls();
 	// tblRowCls();
	 fnAddEvent();
	 setCallSelects('tt',count); 
	// setCallSelects('pl',''); 
	 fnChngImg();
	// var trnsTpeVal = $('#trnsTpe').val();
	// $('#svcChrgTrxTpCd'+count).val($('#trnsTpe').val());
	 
	 $('#recSize').val(++count);
<%
	}
%>
}
function GetTodayDate() {
	var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
	                  "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
	                ];
	   var tdate = new Date();
	   var dd = tdate.getDate(); //yields day
	   var MM = monthNames[tdate.getMonth()]; //yields month
	   var yyyy = tdate.getFullYear(); //yields year
	   var tdDate = MM + " " +dd + " " + yyyy;
	 //  var tdDate = dd + "-" +( MM) + "-" + yyyy;

	   return tdDate;
	}
function fnAddEvent(){
	var rId="";
	$("input[name='chrgSel']").each(function(){
	   $(this).click(function (){
	       rId=$(this).attr("id"); 
	      $(this).prop("checked",true);
	     $("input[name='chrgSel']").each(function (){

	        var tId=$(this).attr("id");
	         if(tId!=rId)
	           $(this).prop("checked",false);
	     });
	   });
	});
}

function fnAddCls(){
	 $('#trnsTpe').addClass("required_field");
	 $('#prcLst').addClass("required_field");
	 $('#chrgSrc').addClass("required_field");
	 $('#crtionDt').addClass("required_field");
	 $('#srcNum').addClass("required_field");
	 
	 $('#trnsTpe').css({"background-color":"#ffff00"});
	 $('#prcLst').css({"background-color":"#ffff00"});
	 $('#chrgSrc').css({"background-color":"#ffff00"});
	 $('#crtionDt').css({"background-color":"#ffff00"});
	 $('#srcNum').css({"background-color":"#ffff00"});
	 $('#contPrice').css({"background-color":"#ffff00"})
	 $("#taskSearchDiv input").each(function() {
	      this.value = "";
	  });
}
function fnRmvCls(){
	$("#taskSearchDiv input").each(function() {
	      this.value = "";
	  });
	 $('#trnsTpe').removeClass("rdl");
	 $('#prcLst').removeClass("rdl");
	 $('#chrgSrc').removeClass("rdl");
	 $('#crtionDt').removeClass("rdl");
	 $('#srcNum').removeClass("rdl");
	 
	 $('#trnsTpe').removeClass("disabled");
	 $('#prcLst').removeClass("disabled");
	 $('#chrgSrc').removeClass("disabled");
	 $('#crtionDt').removeClass("disabled");
	 $('#srcNum').removeClass("disabled");
	 
	 $('#trnsTpe').removeAttr("readonly"); 
	 $('#prcLst').removeAttr("readonly"); 
	 $('#chrgSrc').removeAttr("readonly"); 
	 $('#crtionDt').removeAttr("readonly"); 
	 $('#srcNum').removeAttr("readonly"); 
}
function fnDeleteChargeRow(){
	var RowNo = $('input:radio[name=chrgSel]:checked').val();
	var recVal = $('#recVal'+ RowNo).val();
	
		if(RowNo==null || RowNo=="null"){
			alert('Please select row to delete');
		}else{
			if(recVal == "NE"){
			 	$("#charge_table tr[id='chrgRow" + RowNo + "']").remove();
			}
		}
}	
function fnBillEstmt(){
	var fsrNum = $('#srvRqst').val();
	 var url = '<%=ctxPath%>/e307/jsp/canonE307SRBillingEstimate.jsp?fsrNum='+fsrNum; 
	//    url += "&taskNum=" +tskNum;
	    var l_newWindow = window.open(url);
	    l_newWindow.focus();
}

function fnSaveChrgs(){
	 var fsrNum =  $('#fsrNum').val();
	 $("#errMsg").html("");
	 $("#errorWidget").hide();
	 
	 var submitFlg ="";
	 $.ajax({
		url: 'canonE307DebriefSubmitValidation.jsp',
		cache: false,
		dataType : "text",
		async:false,
		success : function(result)
		{
		//	hidePleaseWait();
			//console.log("result: "+ result);
			if(result!=null){
				submitFlg = $.trim(result);
			}
		}
	 });	
	 if(submitFlg=='Y'){
		 var z1 = /^[+-]?(?=.)(?:\d+,)*\d*(?:\.\d+)?$/;
			var recSize = parseInt($('#recSize').val());
			var retVal ="true";
			if(recSize>0){
				for(i=0;i<recSize;i++){
					 $("#ovrRdPrc"+i).css({"background-color": "#FFFFFF", "border":"1px #cccccc solid"}); 
					 $("#chngRsn"+i).removeClass("error"); 
					var prcVal =  $.trim($('#ovrRdPrc'+i).val());
					 if (prcVal!=null && prcVal!='null' && prcVal!='' && !z1.test(prcVal)) {
						 $("#ovrRdPrc"+i).addClass("error");
						 $("#ovrRdPrc"+i).css({"background-color": "#FFFF00", "border":"1px solid red"}); 
						 $("#errMsg").html("Please enter only numbers.");
						 $("#errorWidget").show();	
						 $("#errMsg").css({"color": "red", "font-size": "15"});					 
						 return false;
					 }
				}
			}
	
			 if(fnCheckVal()){
				 if($('#crBtn').text() =='Reauthorize'){
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
						    	 var url="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestCharges.jsp?saveChrgFlg=Y&fsrNum="+fsrNum;
								<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
									url="<%="https://"+ request.getServerName()%>"+url;
								<% } %>
			 			 		 $("#chrgFrm").attr("action",url);	
			 	    	 		 $('#chrgFrm').submit();		 			
					 		}else{
					 			var termCdVal = $('#termCd').val();
					 			var authdecision= confirm("Credit Card Authorization Failed! - Reason: "+data.procStatusMessage+" \n Click OK to Proceed , Cancel to Retry!");
					 				if(authdecision == true)
					                {
					 					showPleaseWait();
					 			 		 $('#txRefNum').val('');
					 			 		 var url="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestCharges.jsp?saveChrgFlg=Y&fsrNum="+fsrNum;
					 					<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
					 						url="<%="https://"+ request.getServerName()%>"+url;
					 					<% } %>
					 			 		 $("#chrgFrm").attr("action",url);	
					 	    	 		 $('#chrgFrm').submit();	
					                }else{
					                	return false;
					                }
					 			}
					 	});
					} 
				 }else{
					 showPleaseWait();
						 var url="<%=ctxPath%>/e307/jsp/canonE307ServiceRequestCharges.jsp?saveChrgFlg=Y&fsrNum="+fsrNum;
							<% if("Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"))) { %>
								url="<%="https://"+ request.getServerName()%>"+url;
							<% } %>
					 		 $("#chrgFrm").attr("action",url);	
					 		 $('#chrgFrm').submit();				 
				}
	
		 	}
	 }else{
		 $("#errMsg").html("You cannot process it during nightly batch proccessing because inventory discrepancy will occur.");
		 $("#errorWidget").show();	
		 $("#errMsg").css({"color": "red", "font-size": "15"});	
	 }
}
function fnCheckVal(){
	var recSize = parseInt($('#recSize').val());
	var retVal ="true";
	if(recSize>0){
		for(i=0;i<recSize;i++){
			var recVal = $('#recVal'+i).val();
			if(recVal=='E'){
				var qty = $('#qty'+i).val();	
				var exQty = $('#exQty'+i).val();
				var ovrRdPrc = $('#ovrRdPrc'+i).val();
				var exOvrRdPrc = $('#exOvrRdPrc'+i).val();
				var chkChekd =	$('#chrgFlg'+i).is(':checked'); 
				var exChrgFlg = $('#exChrgFlg'+i).val();
				if(qty>0){
					qty = parseInt(qty);
					exQty = parseInt(exQty);
				}
			
				if(qty!=exQty || ovrRdPrc != exOvrRdPrc || (chkChekd && exChrgFlg=='Y') || (!chkChekd && exChrgFlg=='N')){
				     if( $("#chngRsn"+i).val()==''){
						 $("#chngRsn"+i).addClass("error");
						 $("#errMsg").html("Please select Change Reason.");
						 $("#errorWidget").show();	
						 $("#errMsg").css({"color": "red", "font-size": "15"});
						 retVal=false;
				     }else{
				    	 $("#chngRsn"+i).removeClass("error"); 
				     }
				}else{
			    	 $("#chngRsn"+i).removeClass("error"); 
			     }						
			}else{
				if( $("#chngRsn"+i).val()==''){
					 $("#chngRsn"+i).addClass("error");
					 $("#errMsg").html("Please select Change Reason.");
					 $("#errorWidget").show();	
					 $("#errMsg").css({"color": "red", "font-size": "15"});
					 retVal=false;
			     }else{
			    	 $("#chngRsn"+i).removeClass("error"); 
			     }
			}

		}

	}
	return retVal;
}
function fnUpdTrxDet(){
	var RowNo = $('input:radio[name=chrgSel]:checked').val();
	//alert("RowNo : "+ RowNo);
}
function getChargeItemLinks(pageNum, itemNum, mName, iVal, billTpe, svcInvChrgTpCd){
	itemNum = encodeURIComponent(itemNum);
	var urlDetail = 'canonE307ChargeItemLov.jsp?modalName='+mName;
	var	qStr="chargeItem="+ itemNum;
		qStr=qStr+"&pageNum="+pageNum;	
		qStr=qStr+"&modalName="+mName;
		qStr=qStr+"&iVal="+iVal;
		qStr = qStr + '&billTpe='+ billTpe;
		qStr = qStr + '&svcInvChrgTpCd='+ svcInvChrgTpCd;
		
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
function getPrcCatgLinks(pageNum, mName, prcCatgNm){
	prcCatgNm = encodeURIComponent(prcCatgNm);
	var urlDetail = 'canonE307ChargePriceListLov.jsp?modalName='+mName;
	var	qStr="prcCatgNm="+ prcCatgNm;
		qStr=qStr+"&pageNum="+pageNum;	
		qStr=qStr+"&modalName="+mName;

		
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
function setItemDet(mName, iVal, item, itmDesc){
	 var modelName = "#"+mName;
		$('#itmNum'+iVal).val(item);
		$('#itmDesc'+iVal).val(itmDesc);
		$('#itmDsc'+iVal).html(itmDesc);
		$(modelName).html("");
		$(modelName).dialog("close");
		$(modelName).dialog("destroy");  	  	
}
function openItemLov(mName, iVal){
	var chargeItem = $('#itmNum'+iVal).val();
	var billTpe = $('#bilTpe'+iVal).val();
	var svcInvChrgTpCd = $('#svcInvChrgTpCd'+iVal).val();
	var urlDetail = 'canonE307ChargeItemLov.jsp?Action=Open';
	var qStr = 'modalName='+ mName;
	qStr = qStr + '&chargeItem='+ chargeItem;
	qStr = qStr + '&iVal='+ iVal;
	qStr = qStr + '&billTpe='+ billTpe;
	qStr = qStr + '&svcInvChrgTpCd='+ svcInvChrgTpCd;

	modelName = "#"+mName;
	showPleaseWait();
   $(modelName).dialog({
				height: 550,
				title: "Item Search",
				modal: true ,
				zIndex:1005,
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
	//  $(".ui-dialog-titlebar").addClass("hd");
}
function openPrcLstLov(mName){
	var urlDetail = 'canonE307ChargePriceListLov.jsp?Action=Open';
	var qStr = 'modalName='+ mName;

	modelName = "#"+mName;
	showPleaseWait();
   $(modelName).dialog({
				height: 550,
				title: "Price List",
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
function setPrcLstDet(mName, prcLstCd, prcLstNm){
		var RowNo = $('input:radio[name=chrgSel]:checked').val();
		$('#prcCatgCd'+RowNo).val();
		$('#priceLst'+RowNo).val(prcLstNm);
		$('#prcLst').val(prcLstNm);
		fnCloseMdlDlg(mName);	
}
function fnCloseMdlDlg(dId){
	var dlgId="#"+dId;
	$(dlgId).html("");
	$(dlgId).dialog("close");
	$(dlgId).dialog("destroy");
}
function getTransactionDet(iVal){
	var serReq = $('#srvRqst').val();
	var tskNum = $('#tskNum').val();
    var billTpe = $('#bilTpe'+iVal).val();
	if(billTpe!=null && billTpe!="" ){
		var url = "canonE307ChargeTransactionDet.jsp?serReq="+serReq;
		url = url+"&tskNum="+tskNum;
		url = url+"&billTpe="+billTpe;

		$.ajax({
			url: url,
			cache: false,
			success : function(result)
			{
				// $(modelName).html(""); 
				if(result!=null){
					var chrg = $.trim(result);
					var chrgNos = chrg.split("<br>");
					$('#trnsTpe').val(chrgNos[0]);
					$('#chrgSrc').val(chrgNos[1]);
					$('#crtionDt').val(chrgNos[2]);
					$('#prcLst').val(chrgNos[3]);
					$('#srcNum').val(chrgNos[4]);
					$('#contPrice').val(chrgNos[5]);
					
					$('#trxTpe'+iVal).val(chrgNos[0]);
					$('#priceLst'+iVal).val(chrgNos[3]);
					$('#trxSrc'+iVal).val(chrgNos[1]);
					$('#creationDt'+iVal).val(chrgNos[2]);
					$('#trxSrcRef'+iVal).val(chrgNos[4]);
				}
			}
		});			
	}
}
function setCallSelects1(iVal){
	showPleaseWait();
    var eo="";
    var sc="";
    var optTxt="";
    var optVal="";
    var sId="";
    var le="ListEntry";
    var wc="";
	
      sId="#bilTpe"+iVal;
      eo="billType";
      sc="billtpNm;billtpCode";
      optTxt="billtpNm";
      optVal="billtpCode";
 	
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
	             });
	             
	        //   $(sId+' option[value="' + selVal + '"]').prop('selected', true);
	             hidePleaseWait(); 
	   },
	    error: function(data){
	       // console.log('Error loading XML data : '+sId);
	        hidePleaseWait() ;
	    }
	});
}
function fnCalcExtdAmt(iVal){
	var qty = $('#qty'+iVal).val();	
	var unitLstPrc = $('#untLstPrice'+iVal).val();
	var discRate = $('#contDiscRt'+iVal).val();
	var exQty = $('#exQty'+iVal).val();
	//discRate =10;
	if(qty>0 && unitLstPrc>0){
		qty = parseInt(qty);
		var totPrice = qty*unitLstPrc;
		var discount = totPrice * (discRate / 100)
		var netPrc = totPrice - discount
		totPrice = Number(Math.round(totPrice+'e2')+'e-2').toFixed(2); 
		netPrc = Number(Math.round(netPrc+'e2')+'e-2').toFixed(2); 
		if(totPrice>0){
			$('#extndAmt'+iVal).val(totPrice);
			$('#extdAmt'+iVal).html(totPrice);
			$('#netPrc'+iVal).html(netPrc);
			$('#netPrice'+iVal).val(netPrc);
		}
	}else{
		$('#extndAmt'+iVal).val('0.00');
		$('#extdAmt'+iVal).html('0.00');
		$('#netPrc'+iVal).html('0.00');
		$('#netPrice'+iVal).val('0.00');
	}
/* 	if(qty!=exQty){
		$('#chngRsnFlg'+iVal).val('Y'); 
	}else{
		$('#chngRsnFlg'+iVal).val('N'); 
	} */
}
function setCallSelects(s, iVal){
	showPleaseWait();
    var eo="";
    var sc="";
    var optTxt="";
    var optVal="";
    var sId="";
    var le="ListEntry";
    var wc="";
	
  if(s=="pl"){
   	 sId="#prcLst";
	 sc="prcLstNm;prcLstCd";
     eo="prcList";	
     optTxt="prcLstNm";
     optVal="prcLstCd";
 	}else if(s=="tt"){
      sId="#trnsTpe";
      eo="trsnTpe";	
      sc="trsnTpNm;trsnTpCd";
      optTxt="trsnTpNm";
      optVal="trsnTpCd";	
 	}else if(s=='bc'){
	  sId="#bilTpe"+iVal;
	  eo="billType";
	  sc="billtpNm;billtpCode";
	  optTxt="billtpNm";
	  optVal="billtpCode"; 
	}
	
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
	             });
	             if(s=="tt"){
	        	 	var trnsTpeVal = $('#trnsTpe').val();
	        	 	$('#svcChrgTrxTpCd'+iVal).val($('#trnsTpe').val());
	             }
	        //   $(sId+' option[value="' + selVal + '"]').prop('selected', true);
	             hidePleaseWait(); 
	   },
	    error: function(data){
	        //console.log('Error loading XML data : '+sId);
	        hidePleaseWait() ;
	    }
	});
}
function fnGetTrnsTpeVal(){
	var trnsTpe = $('#trnsTpe').val();
	var rowNo = $('input:radio[name=chrgSel]:checked').val();
	$('#trxTpe'+rowNo).val(trnsTpe);
	$('#svcChrgTrxTpCd'+rowNo).val(trnsTpe);
}
function fnGetPrcLstVal(){
	var prcLst = $('#prcLst').val();
	var rowNo = $('input:radio[name=chrgSel]:checked').val();
	$('#priceLst'+rowNo).val(prcLst);
}
function tblRowCls(){
	$('#charge_table'+" tbody tr:even").addClass("evenRow");
	$('#charge_table'+" tbody tr:odd").addClass("oddRow");
}
function fnBackSummary(){
//	$('#chrgFrm #scratchPad').val( $("#toolTip textarea").val() );
	showPleaseWait();
	var fsr = $('#fsrNum').val();
	var urlDetail = "<%=ctxPath%>/e307/jsp/canonE307ServiceRequestSummary.jsp?fsr="+encodeURIComponent(fsr);
	document.chrgFrm.action = urlDetail;
	document.chrgFrm.submit();		
}
  $(document).ready(function() {
	 var recSz = $('#recSize').val();
	 
	 if(recSz==0){
		 fnAddMoreCharges();
		 $('#chrgSrc').val('Service Request');
		 $('#srcNum').val($('#fsrNum').val());		 
	 }
	 var editRole = $('#editRole').val();
	if(editRole == 'E307_OTH'){
		$('.disp_mode').hide();
	}
	
});  
function fnUpdatePrcCatgCd(iVal){
	var ctgCd = $('#svcInvChrgTpCd'+iVal).val();
	//$('#prcCatgCd'+iVal).val($('#svcInvChrgTpCd'+iVal).val());
	$('#bilTpe'+iVal).val(ctgCd);
	/*if(ctgCd=='PC'){
		$('#prcCatgCd'+iVal).val('0000004');
	}  else if(ctgCd=='LC'){
		$('#prcCatgCd'+iVal).val('LBOR_CHRG');
	}else if(ctgCd=='TC'){
		$('#prcCatgCd'+iVal).val('TRVL_CHRG');
	} else if(ctgCd=='XC'){
		$('#prcCatgCd'+iVal).val('LBOR_CHRG');
	}  */
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
 		var crBtn = $('#crBtn').text();
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
 	 		if($.trim(crBtn) == 'Enter Credit Card'){
 	 			$('#crBtn').text('Reauthorize');
 	 		}			
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
		var profileId = $('#rofileId').val();
		if(profileId!=null && profileId!='null' && profileId.length>0 && $.trim(crBtn) == 'Enter Credit Card'){
 			$('#crBtn').text('Reauthorize');
 		} 		
 	}
 /*	function showPleaseWait() {
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
 	} 	*/
</script>
<div id="chargeItemDiv"></div>
<div id="divEnterCC"></div>	
</html>
