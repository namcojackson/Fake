package com.canon.apps.servreq.util;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import parts.common.EZDBizPerformanceCounter;
import parts.common.EZDStringUtil;
import parts.dbcommon.EZDConnectionMgr;
import business.parts.NMZC610001PMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC061001PMsg;
import canon.apps.common.CanonConstants;














import com.canon.apps.servreq.dao.CanonE307ServiceReqCreateDao;
import com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao;
import com.canon.common.CanonCommonUtil;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC061001.NSZC061001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SvcChrg;
import com.canon.cusa.s21.common.NSX.NSXC002001.SvcPgmBean;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;


public class CanonE307ServiceReqAPIUtil { 

	private   String clsName="CanonE307ServiceReqAPIUtil";
	          String  MODE_CODE_SR_CREATE="01";
	          String  MODE_CODE_SR_UPDATE="02";
	          CanonCommonUtil util;         
	
	public CanonE307ServiceReqAPIUtil(){
		util= new CanonCommonUtil();
	}
	  //NSZC0430
	public   NSZC043001PMsg  getMsg(HttpServletRequest req) throws ParseException{
		

	 	 NSZC043001PMsg pmsg = new NSZC043001PMsg();

	 	CanonE307ServiceReqCreateDao crtObj = new CanonE307ServiceReqCreateDao();
	 	
		
		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmmss");
		 SimpleDateFormat sdfCurHr = new SimpleDateFormat("HH");
		 SimpleDateFormat sdfCurMn = new SimpleDateFormat("mm");
		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
		 Date compSysDt=sdfDt.parse(sSysDt);
		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		 String sSysHr=sdfCurHr.format(Calendar.getInstance().getTime());
		 String sSysMm=sdfCurMn.format(Calendar.getInstance().getTime());
		 System.out.println("sSysTm : " + sSysTm+" sSysHr : "+sSysHr+" sSysMm : "+sSysMm);
		   
		 util.logMsg(false, clsName+".createServicerequest", "CREATE SR CALL PARAMETERS sSysDt : "+sSysDt+" sSysTm: "+sSysTm); 
		 
		 // Mandatory Params
		 pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
		 pmsg.xxModeCd.setValue(MODE_CODE_SR_CREATE);

		 String userId                                           = checkNull(req,"userId");if(userId.length()>0) pmsg.userId.setValue(userId);
		 String dsSvcCallTpCd                                    = checkNull(req,"sTaskType");if(dsSvcCallTpCd.length()>0) pmsg.dsSvcCallTpCd.setValue(dsSvcCallTpCd);
		 pmsg.svcTaskRcvDt.setValue(sSysDt);
		 pmsg.svcTaskRcvTm.setValue(sSysTm);

		 
		 String slsDt                                            = checkNull(req,"slsDt");if(slsDt.length()>0) pmsg.slsDt.setValue(slsDt);
		 String svcPblTpCd                                      = checkNull(req,"sPbCode");
		 String pCode                                      = checkNull(req,"pCode");
		 
		 String svcPblmTpCd = pCode.split("-")[0].trim();
		 System.out.println("svcPblmTpCd: "+ svcPblmTpCd+ " pCode: "+ pCode);
		 if(svcPblmTpCd.length()>0) pmsg.svcPblmTpCd.setValue(svcPblmTpCd);
		 String svcCallSrcTpCd                                   = checkNull(req,"sCrChannel");if(svcCallSrcTpCd.length()>0) pmsg.svcCallSrcTpCd.setValue(svcCallSrcTpCd);
		 String svcMachMstrPk                                    = checkNull(req,"machPk");if(svcMachMstrPk.length()>0) pmsg.svcMachMstrPk.setValue(new BigDecimal(svcMachMstrPk));
		  
		 //opt
		 String custMachCtrlNum                                  = checkNull(req,"tagNum");if(custMachCtrlNum.length()>0) pmsg.custMachCtrlNum.setValue(custMachCtrlNum);
		 String serNum                                           = checkNull(req,"serialNumber");if(serNum.length()>0) pmsg.serNum.setValue(serNum);
		 String mdlNm                                            = checkNull(req,"model");if(mdlNm.length()>0) pmsg.mdlNm.setValue(mdlNm);

		 String cllrPhNum										 = checkNull(req,"cllrPhNum");
		 String cllrPhNumExt									 = checkNull(req,"cllrPhNumExt");

		 if(cllrPhNum.length()>0)pmsg.taskList.no(0).svcCustCllrTelNum.setValue(cllrPhNum);
		 if(cllrPhNumExt.length()>0)pmsg.taskList.no(0).svcCustCllrTelExtnNum.setValue(cllrPhNumExt);
		 String custTelNum                                       = checkNull(req,"custTelNum");if(custTelNum.length()>0) pmsg.custTelNum.setValue(custTelNum);
		 String custTelExtnNum                                   = checkNull(req,"custTelExtnNum");if(custTelExtnNum.length()>0) pmsg.custTelExtnNum.setValue(custTelExtnNum);
		 String mobileNum										 = checkNull(req, "mobileNum");
		 String custEmlAddr                                      = checkNull(req,"emailAddr");if(custEmlAddr.length()>0) pmsg.custEmlAddr.setValue(custEmlAddr);
		 String custPoNum                                        = checkNull(req,"custPo");if(custPoNum.length()>0) pmsg.custPoNum.setValue(custPoNum);
		// String svcCallAvoidCd                                   = checkNull(req,"assist");if(svcCallAvoidCd.length()>0) pmsg.svcCallAvoidCd.setValue(svcCallAvoidCd);
		 String svcCustCllrTxt                                   = checkNull(req,"caller");if(svcCustCllrTxt.length()>0) pmsg.svcCustCllrTxt.setValue(svcCustCllrTxt);
		 String svcCustAttnTxt                                   = checkNull(req,"contactName");if(svcCustAttnTxt.length()>0) pmsg.svcCustAttnTxt.setValue(svcCustAttnTxt);
		 String svcBillTpCd                                      = checkNull(req,"sBillCode");if(svcBillTpCd.length()>0) pmsg.svcBillTpCd.setValue(svcBillTpCd);
		 String machDownFlg                                      = checkNull(req,"mchStatus");if(machDownFlg.length()>0) pmsg.machDownFlg.setValue(machDownFlg);
		 String lbrChrgFlg 										 = checkNull(req,"lbrChrgFlg");if(lbrChrgFlg.length()>0) pmsg.svcLborChrgFlg.setValue(lbrChrgFlg);
		 String prtChrgFlg										 = checkNull(req,"prtChrgFlg");if(prtChrgFlg.length()>0) pmsg.prtChrgFlg.setValue(prtChrgFlg);
		 String profileId										 = checkNull(req,"profileId"); if(profileId.length()>0)pmsg.crCardCustRefNum.setValue(profileId);
		 String holderName										 = checkNull(req,"holderName"); if(holderName.length()>0)pmsg.crCardHldNm.setValue(holderName);	
		 String authAmnt										 = checkNull(req,"authAmnt"); if(authAmnt.length()>0)pmsg.crCardAuthAmt.setValue(new BigDecimal(authAmnt));
		 String cardType										 = checkNull(req,"cardType"); 
		 if(cardType.length()>0){
			 String crTpDesc = crtObj.getCRCardTp(cardType);
			// System.out.println("crTpDesc : "+ crTpDesc);
			 if(crTpDesc.length()>0) pmsg.crCardTpCd.setValue(crTpDesc);
		 }
		 String cardExpr										 = checkNull(req,"cardExpr"); 
		 if(cardExpr.length()>0)pmsg.crCardExprYrMth.setValue(cardExpr);
		 String txRefNum 										 = checkNull(req,"txRefNum"); if(txRefNum.length()>0)pmsg.crCardAuthRefNum.setValue(txRefNum);
		 String strCcNum 										 = checkNull(req, "ccNum");if(strCcNum.length()>0)pmsg.crCardLastDigitNum.setValue(strCcNum);
		 String ftrSrvDt                                  		 = checkNull(req,"ftrSrvDt");
		 if(ftrSrvDt.length()>0) {
			 ftrSrvDt =util.formatDateString(util.DT_FORMAT5, util.DT_FORMAT3, ftrSrvDt);
			 System.out.println("sdfDt : " + sSysDt+ " ftrSrvDt : " + ftrSrvDt);
			 String ftrSrvHr 	= checkNull(req,"ftrSrvHr");
			 String ftrSrvMn 	= checkNull(req,"ftrSrvMn");
			 String ftrAmPm		= checkNull(req, "ftrAmPm");
			 System.out.println("ftrSrvHr: "+ ftrSrvHr+" ftrSrvMn: "+ftrSrvMn+" ftrAmPm: "+ftrAmPm);
			 String strTm = util.format24HrTm(ftrSrvHr+ftrSrvMn+" "+ftrAmPm);
			 System.out.println(" strTm: "+strTm);
			 String strExistFutureDt = ftrSrvDt+strTm;
			 String postalCd										 = checkNull(req,"postalCd");
			 String countryCd										 = checkNull(req,"countryCd");
			 java.util.Date sysDate = new java.util.Date();
			 String strCurrentTm = getCurrentTm(postalCd, countryCd, new SimpleDateFormat("yyyyMMddHHmmss").format(sysDate).toString());
			 System.out.println("strExistFutureDt: "+strExistFutureDt+" strCurrentTm: "+strCurrentTm);
		 
			 	DateFormat formatter=new SimpleDateFormat("yyyyMMddHHmm");
			 	DateFormat formatter1=new SimpleDateFormat("yyyyMMddHHmmss");
		        Date currentTm =formatter1.parse(strCurrentTm);
		        Date existFutureDt=formatter.parse(strExistFutureDt);
		        System.out.println("currentTm: "+ currentTm+ "existFutureDt: "+ existFutureDt);

		        if (existFutureDt.compareTo(currentTm) > 0) {
		            pmsg.taskList.no(0).futSvcDt.setValue(ftrSrvDt);
					 if(strTm.trim().length()>0) pmsg.taskList.no(0).futSvcTm.setValue(strTm);
		        }else{
		        	 if(strCurrentTm!=null && strCurrentTm.length()>0){
							String strDate = strCurrentTm.substring(0, 8);
							String strCurTm = strCurrentTm.substring(8,14);
							System.out.println("Else strDate: " + strDate+" strCurTm: "+strCurTm);
							 pmsg.taskList.no(0).futSvcDt.setValue(strDate);
							 if(strTm.trim().length()>0) pmsg.taskList.no(0).futSvcTm.setValue(strCurTm);
		        	 }
		        }
			 
		//	 pmsg.taskList.no(0).futSvcDt.setValue(ftrSrvDt);
		//	 if(strTm.trim().length()>0) pmsg.taskList.no(0).futSvcTm.setValue(strTm);			 

		 }
		 pmsg.xxTmZnConvtFlg.setValue("Y");
		 pmsg.taskList.no(0).cellPhoNum.setValue(mobileNum);
		 
		 pmsg.taskList.setValidCount(1);
		 pmsg.taskList.no(0).mblIntfcFlg.setValue("Y");
		 String cvuPO 											= checkNull(req,"cvuPO");
		 String ccaChrg 										= checkNull(req,"ccaChrg");
		 
		 if(ccaChrg.length()>0) {
			 pmsg.taskList.no(0).custAwareChrgFlg.setValue("Y");
		 }else{
			 pmsg.taskList.no(0).custAwareChrgFlg.setValue("N");
		 }
		 
		 String sChngReason = checkNull(req,"sChngReason");
		 util.logMsg(false,clsName+".createServicerequest", "sChngReason : "+sChngReason);
		 if(sChngReason.length()>0) pmsg.taskList.no(0).svcBillChngRsnCd.setValue(sChngReason);
		 
		 pmsg.attachedFileList.setValidCount(1);
		 String poUpld                                  		 = checkNull(req,"poUpld");
		 if(poUpld.length()>0){
			 pmsg.attachedFileList.no(0).attDataNm.setValue(poUpld);
			 pmsg.attachedFileList.no(0).poVerFlg.setValue("Y");
		 }else{
			 pmsg.attachedFileList.no(0).poVerFlg.setValue("N");
		 }
		 
		 int memoCnt=0;
		 // sNoteType sNoteTypeTxt
		 String sNoteTypeTxt     = checkNull(req,"sNoteTypeTxt");
		 if(sNoteTypeTxt.length()>0) {
			 pmsg.svcMemoList.no(memoCnt).svcCmntTxt.setValue(sNoteTypeTxt);
			 String sNoteTypeCd   = checkNull(req,"sNoteType");
			 System.out.println("sNoteTypeCd: "+ sNoteTypeCd+ " memoCnt: "+memoCnt);
			 sNoteTypeCd = sNoteTypeCd==null?"19":sNoteTypeCd;
			pmsg.svcMemoList.no(memoCnt).svcMemoTpCd.setValue(sNoteTypeCd);
			 memoCnt++;
		 }
		
		 // spl msg 
		 String splMsg         = checkNull(req,"splMsg");
		 if(splMsg.length()>0){
			 pmsg.svcMemoList.no(memoCnt).svcCmntTxt.setValue(splMsg);
			// String splMsgNtCd   = checkNull(req,"splMsgNtCd");
			// if(splMsgNtCd.length()>0) 
				 pmsg.svcMemoList.no(memoCnt).svcMemoTpCd.setValue("13");
			 
			 memoCnt++;
		 }
		 System.out.println(" svcBillTpCd: "+ svcBillTpCd+" svcMachMstrPk: "+svcMachMstrPk+ "slsDt: "+slsDt);
		 if("3".equals(svcBillTpCd) || "3X".equals(svcBillTpCd) ){
			 ArrayList<String> lsParts = crtObj.getExclusionParts(svcMachMstrPk, slsDt);
			 String partsMsg = "The following part numbers are chargeable for this customer.\n";
			 if(lsParts!=null && lsParts.size()>0){
				 for(String sPart: lsParts){
					 partsMsg = partsMsg + sPart;
				 }
				 pmsg.svcMemoList.no(memoCnt).svcCmntTxt.setValue(partsMsg);
				 pmsg.svcMemoList.no(memoCnt).svcMemoTpCd.setValue("16");
			 }
		 memoCnt++;
		 }
		 
	
		if(memoCnt!=0)
		 pmsg.svcMemoList.setValidCount(memoCnt);
		 
		
		String custCaseNum                                      = checkNull(req,"custCaseNum");if(custCaseNum.length()>0) pmsg.custCseNum.setValue(custCaseNum);
		
		
		String uInstBase                                   = checkNull(req,"uInstBase");
		
	//	if(uInstBase.equalsIgnoreCase("Y")){
			
			String billToCustCd                                     = checkNull(req,"bCustCode");
			
			if(billToCustCd.length()>0) {
				pmsg.billToUpdCustCd.setValue(billToCustCd);
				if(uInstBase.equalsIgnoreCase("Y")){
					pmsg.billToCustUpdFlg.setValue("Y");
				}else{
					pmsg.billToCustUpdFlg.setValue("N");
				}
			}
			
			
			String shipToCustCd                                     = checkNull(req,"cCustCode");
			if(shipToCustCd.length()>0) {
				pmsg.shipToUpdCustCd.setValue(shipToCustCd);
				if(uInstBase.equalsIgnoreCase("Y")){
					pmsg.shipToCustUpdFlg.setValue("Y");
				}else{
					pmsg.shipToCustUpdFlg.setValue("N");
				}
			}
	//	}
		
		 //util.logMsg(false, clsName+".createServicerequest", " &&&&PARAMS : " +" = "+pmsg.toString());
		return pmsg;
		
	}
	

	public String[] createServicerequest(HttpServletRequest req) throws ParseException{
		
		
		//Create instance of API 
		NSZC043001 s21Api = new NSZC043001();
		
		String res[] = new String[2];
		String upldRes[] = new String[2];
		
		//Create instance of message
		NSZC043001PMsg pmsg = new NSZC043001PMsg();
		util.logMsg(false,clsName+".createServicerequest", "Before Create Service Request");
		try{
			pmsg = getMsg(req);
		} catch (Exception e) {
			res[0]="E";
			//res[1]=e.getMessage();
			
			try{
				String str = e.getMessage();
				Pattern p = Pattern.compile("msg=.*");

				Matcher m = p.matcher(str);

				if (m.find()) {
					res[1] =  m.group(0).trim();
				}else{
					res[1] = e.getMessage();
				}
				util.logMsg(true,clsName+".createServicerequest", "Exception message while setting parameters: "+ res[1]);	
			}catch (Exception ex) {
				util.logMsg(true,clsName+".createServicerequest", "Error while parsing error message: "+ e.getMessage());
			}
			EZDConnectionMgr.getInstance().rollback();
			
			
		} finally {	// Release DB resource (Close DB Connection)	
			EZDConnectionMgr.getInstance().releaseResource();
		}
		util.logMsg(false,clsName+".createServicerequest", pmsg.toString());
		util.logMsg(false,clsName+".createServicerequest", "Calling CREATE SR API");
		
		CanonE307FileUploadDownloadAPIUtil objInvDAO = new CanonE307FileUploadDownloadAPIUtil();
		
		// CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
		
		String userName=pmsg.userId.getValue();
		String timeRegion="EST";
		String appId="EXTNE307";
		String pageId="CreateSR";
		String appPageId="EXTNE307_CreateSR";
		
	/*	EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appId);
		bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/
		
		int fileId = 0;
		String fileExists = "Y";
		String strSerNum = checkNull(req,"serialNumber");
		String strUserId = checkNull(req,"userId");
		String strFileName =  checkNull(req,"poUpld");
		if(strFileName!=null && strFileName.length()>0){
		   fileExists = "N";
		   CanonE307ServiceRequestDetailsDao detObj = new CanonE307ServiceRequestDetailsDao();
		    String folderPath = detObj.getUploadFilePath();
	        String filePath = "";
	        if("Credit Card Payment instead of PO.pdf".equals(strFileName)){
		        filePath = "/WebSphere/apps/filebox/dummyPO/Credit Card Payment instead of PO.pdf";
	        }else{
	        	filePath = folderPath+strFileName;
	        }
	        File file = new File(filePath);
			
			if(file.exists()){
				fileExists="Y";
			}
			System.out.println("Call Creation filePath: "+ filePath+" fileExists: "+fileExists);
		}
		if("Y".equals(fileExists)){
			//execute API
			s21Api.execute(pmsg, ONBATCH_TYPE.ONLINE);  
			util.logMsg(false,clsName+".createServicerequest", "After Execute");

	/*		bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
			bizPerfCounter.requestQueue();	*/
			
		try {
			
			// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
			
				if (!S21ApiUtil.isXxMsgId(pmsg)) {
					
					res[0]="Y";
					res[1]=pmsg.fsrNum.getValue();
					// commit	
					util.logMsg(false,clsName+".createServicerequest", "FSR Created , FSR Number :"+ pmsg.fsrNum.getValue());					
					if(strFileName.length()>0){
						upldRes = objInvDAO.uploadAttachment(res[1], strSerNum, strUserId, strFileName);
						util.logMsg(false,clsName+".createServicerequest", "Upload document , upldRes[0] :"+ upldRes[0]);	
						if("E".equals(upldRes[0])){
							 EZDConnectionMgr.getInstance().rollback(); 
							 res = upldRes;
						}else{
							EZDConnectionMgr.getInstance().commit();
							res[0]="Y";
							res[1]=pmsg.fsrNum.getValue();
						}
						
					}else{
					// There is no message id, so can do commit the transaction.
						EZDConnectionMgr.getInstance().commit();
					}

				} else {
					StringBuffer sb=new StringBuffer("");
						// Error Case - S21API set some error message id when got any error while S21API's function.		
						List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
						for (String msg : err) {
							util.logMsg(true,clsName+".createServicerequest", "FSR Creation Error :"+msg);
							sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
						}		
						res[0]="E";
						res[1]=sb.toString();
						
						// If S21API got error, roll-back the transaction.
							EZDConnectionMgr.getInstance().rollback(); 
						// roll-back		
						// throw new S21AbendException("S21AbendException is thrown...");
				}
			} catch (Exception e) {
				res[0]="E";
				//res[1]=e.getMessage();
				
				try{
					String str = e.getMessage();
					Pattern p = Pattern.compile("msg=.*");

					Matcher m = p.matcher(str);

					if (m.find()) {
						res[1] =  m.group(0).trim();
					}else{
						res[1] = e.getMessage();
					}
					util.logMsg(true,clsName+".createServicerequest", "Exception message while Creating FSR: "+ res[1]);	
				}catch (Exception ex) {
					util.logMsg(true,clsName+".createServicerequest", "Error while parsing error message: "+ e.getMessage());
				}
				EZDConnectionMgr.getInstance().rollback();
				
				
			} finally {	// Release DB resource (Close DB Connection)	
				EZDConnectionMgr.getInstance().releaseResource();
			}
		}else{
			res[0]="E";
			res[1]="Not able to upload the "+ strFileName+" . Please try again.";
		}
		return res;
	}
	
		
	public String[] getBillCode(String machPk, String slsdate, String modeCd, String serialNumber, String model, String callTpCd, String userName, String strCallType)
    {
		
	        String[] sArr = new String[11];
	        util.logMsg(false, clsName+".getBillCode", "Before BillCode API call: "+machPk+" modeCd: "+modeCd+" serialNumber: "+serialNumber+" callTpCd: "+callTpCd + "slsdate: "+slsdate+" strCallType: "+strCallType);
	        CanonE307ServiceReqCreateDao servObj = new CanonE307ServiceReqCreateDao();

	        try
	        {
	        	if("".equals(callTpCd) || "null".equals(callTpCd) && serialNumber!=null && serialNumber.length()>0){
	        		callTpCd = servObj.getCallType(serialNumber, model);
	        	}
	        	String sSysDt="";
	        	String sSysTm="";
	       	 	SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
	       	 	SimpleDateFormat sdfTm = new SimpleDateFormat("HHmmss");
	       	 	if("".equals(slsdate) || "null".equals(slsdate)){
		        	if("R".equals(strCallType)){
		        		int i=1;
		        		CanonE307ServiceRequestDetailsDao detObj = new CanonE307ServiceRequestDetailsDao();
		        		Calendar calendar = Calendar.getInstance();
						 Date sysDate = new Date();
						 String strHolidayFlg="";
						// String dateString = sysDate.toString();
						 //System.out.println("dateString: "+ dateString);
						 calendar.setTime(sysDate);
						 do{
							 calendar.add(Calendar.DAY_OF_YEAR, i);
							 sSysDt = sdfDt.format(calendar.getTime());
							 strHolidayFlg = detObj.getHolidayFlag(sSysDt);
							 System.out.println("dateString in loop: "+ sSysDt+" strHolidayFlg: "+ strHolidayFlg+" iVal: "+i);
							 i=1;
						 }while(strHolidayFlg.equals("Y"));
						 
						 sSysTm = "140000";
		        	}else{
				   
		        		sSysDt=sdfDt.format(Calendar.getInstance().getTime());
					    sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		        	}
	       	 	}else{
	       	 		if(slsdate!=null && slsdate.length()>0){
			       	 	sSysDt = slsdate.substring(0, 8);
			       	    sSysTm =  slsdate.substring(8,14);
						//System.out.println("Sales Date sSysDt: "+ sSysDt+"sSysTm: "+ sSysTm);
	       	 		}
	       	 	}
				 				 
				// System.out.println("strDate: "+ sSysDt);

				 util.logMsg(false, clsName+".getBillCode", "sSysDt: "+ sSysDt+" sSysTm : "+sSysTm+" callTpCd: "+callTpCd);
				   
			    NSZC061001 billCodeApi = new NSZC061001();
				
				NSZC061001PMsg  pmsg = new NSZC061001PMsg();
				pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
				pmsg.xxModeCd.setValue(modeCd); //0:Call Creation, 1:Call Close								
				pmsg.startDt.setValue(sSysDt);
				pmsg.startTm.setValue(sSysTm);
				pmsg.svcMachMstrPk.setValue( new BigDecimal( machPk ) );
				pmsg.dsSvcCallTpCd.setValue(callTpCd);

				String timeRegion="EST";
				String appId="EXTNE307";
				String pageId="CreateSR";
				String appPageId="EXTNE307_CreateSR";
				
			//	CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
			/*	EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appId);
				bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/
		        
		      //  util.logMsg(false, clsName+".getBillCode", " BillCode API IN PARAMS  : " +" = "+pmsg.toString());
				
		        billCodeApi.execute(pmsg, ONBATCH_TYPE.ONLINE);

		/*		bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
				bizPerfCounter.requestQueue();	*/
		        
		        try {
					// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
	
						if (!S21ApiUtil.isXxMsgId(pmsg)) {
							 sArr[0]= util.checkNull(pmsg.svcBillTpCd.getValue());
					        // sArr[1]= util.checkNull(pmsg.ahsCdEnblFlg.getValue());
							 sArr[1]= util.checkNull(pmsg.xxAftHrsPopDplyFlg.getValue());
						     sArr[2]= (pmsg.svcLborUnitAmt.getValue()!=null)?pmsg.svcLborUnitAmt.getValue().toString():"";
						     sArr[3]=(pmsg.svcLborDealAmt.getValue()!=null)?pmsg.svcLborDealAmt.getValue().toString():"";
						     sArr[4]=(pmsg.svcTrvlUnitAmt.getValue()!=null)?pmsg.svcTrvlUnitAmt.getValue().toString():"";
						     sArr[5]=(pmsg.svcTrvlDealAmt.getValue()!=null)?pmsg.svcTrvlDealAmt.getValue().toString():"";
						     sArr[6]=(pmsg.svcPrtDealAmt.getValue()!=null)?pmsg.svcPrtDealAmt.getValue().toString():"";
						     sArr[7]=(pmsg.svcMinChrgHrsAot.getValue()!=null)?pmsg.svcMinChrgHrsAot.getValue().toString():"";
						     sArr[8] =(pmsg.xxSvcTaskChrgList.no(0).svcLborDiscRate.getValue()!=null)?pmsg.xxSvcTaskChrgList.no(0).svcLborDiscRate.getValue().toString():"";
						     sArr[9]=(pmsg.dsContrPk.getValue()!=null)?pmsg.dsContrPk.getValue().toString():"";
						     sArr[10]=(pmsg.ahsCdEnblFlg.getValue()!=null)?pmsg.ahsCdEnblFlg.getValue().toString():"";
						     
						     double minChrgHrs = 0.0;
						     double laborRPH = 0.0;
							 double lborUnitAmt = 0.0;
							 double partsChrg = 0.0;
							 
							 util.logMsg(false,clsName+".getBillCode", "Before labor rate calculationsArr[7] : "+ sArr[7]+" sArr[2]: "+sArr[2]);
						     
						     if(sArr[7]!=null && sArr[7].length()>0){
						    	 minChrgHrs = toDecimal(sArr[7]);
						    	 lborUnitAmt = toDecimal(sArr[2]);
						    	 util.logMsg(false,clsName+".getBillCode", "minChrgHrs : "+ minChrgHrs+" lborUnitAmt: "+lborUnitAmt);
						    	 if(minChrgHrs>0 && lborUnitAmt>0){
						    		 laborRPH =  lborUnitAmt/minChrgHrs;
						    	 }
						    	 System.out.println("laborRPH: "+ laborRPH);
						    	 sArr[2] = String.format("%.2f", laborRPH);//String.valueOf(laborRPH);
						     }else{
						    	 sArr[2] = "0";
						     }
						     
					     
						     partsChrg = toDecimal(sArr[4]);

						     if(partsChrg>0){
						    	 sArr[4] = String.format("%.2f", partsChrg);
						     }else{
						    	 sArr[4] = "";
						     }

						     util.logMsg(false,clsName+".getBillCode", " svcBillTpCd="+ sArr[0] + " , ahspopupdispFlg="+sArr[1] + " , svcLborUnitAmt= "+sArr[2]
						    		 + " , svcLborDealAmt= "+sArr[3]+ " , svcTrvlUnitAmt= "+sArr[4]+ " , svcTrvlDealAmt= "+sArr[5]
						    		 + " , svcPrtDealAmt= "+sArr[6]	+ " , svcMinChrgHrsAot= "+sArr[7]+" dsContrPk: "+sArr[9]+" AHS flg : "+sArr[10]);
						     
						} else {
							StringBuffer sb=new StringBuffer("");
								// Error Case - S21API set some error message id when got any error while S21API's function.		
								List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
								for (String msg : err) {
									util.logMsg(true,clsName+".getBillCode", ""+msg);
									sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
								}		
							
								// If S21API got error, roll-back the transaction.
									EZDConnectionMgr.getInstance().rollback(); 
								// roll-back		
								// throw new S21AbendException("S21AbendException is thrown...");
						}
					} catch (Exception e) {
						util.logMsg(true,clsName+".getBillCode", "Error in getBillCode : "+ e.getMessage());
					} finally {	// Release DB resource (Close DB Connection)	
						EZDConnectionMgr.getInstance().releaseResource();
					}

		         
		       // END NEW API 
                 
		    }
	        catch(Exception e)
	        {
	        	util.logMsg(true,clsName+".getBillCode", e.getMessage());
	        }

	        return sArr;
     }
	public String getPOReqFlg(String strBillLocNum){
		String poReqFlg="";
		util.logMsg(false, clsName+".getPOReqFlg", "strBillLocNum: "+ strBillLocNum);
		try{
			if ((strBillLocNum == null) || "null".equals(strBillLocNum) ||  "".equals(strBillLocNum)){
				poReqFlg = "N";
			}else{
			 util.logMsg(false, clsName+".getPOReqFlg else", "strBillLocNum: "+ strBillLocNum);
			 NMZC610001 poReqAPI = new NMZC610001();
			 NMZC610001PMsg pmsg = new  NMZC610001PMsg();
			 pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
			 pmsg.xxModeCd.setValue("01"); 						
			 pmsg.dsTrxRuleTpCd.setValue("30");
			 pmsg.billToCustCd.setValue(strBillLocNum);
			 
			String userName=""; //pmsg.userId.getValue();
			String timeRegion="EST";
			String appId="EXTNE307";
			String pageId="CreateSR";
			String appPageId="EXTNE307_CreateSR";
			
		//	CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
		/*	EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appId);
			bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/
			 
			poReqAPI.execute(pmsg, ONBATCH_TYPE.ONLINE);
			 
		/*	bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
			bizPerfCounter.requestQueue();		*/		 
			 
				try {
					
					// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
					
						if (!S21ApiUtil.isXxMsgId(pmsg)) {
							
							// There is no message id, so can do commit the transaction.
							EZDConnectionMgr.getInstance().commit();
							poReqFlg = pmsg.TransactionRuleList.no(0).dsPoReqFlg.getValue();
							// commit	
							util.logMsg(false,clsName+".getPOReqFlg", "PO Req Flag :"+ poReqFlg);
						} else {
							StringBuffer sb=new StringBuffer("");
								// Error Case - S21API set some error message id when got any error while S21API's function.		
								List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
								for (String msg : err) {
									util.logMsg(true,clsName+".getPOReqFlg", "PO Req Error :"+msg);
									sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
								}		
								poReqFlg ="";
								
								// If S21API got error, roll-back the transaction.
									EZDConnectionMgr.getInstance().rollback(); 
						}
					} catch (Exception e) {
						poReqFlg="";
						util.logMsg(true,clsName+".getPOReqFlg", "Error in getting poreq : "+ e.getMessage());
					} finally {	// Release DB resource (Close DB Connection)
						EZDConnectionMgr.getInstance().releaseResource();
					} 
			}
			
		}
		  catch(Exception e)
        {
        	util.logMsg(true,clsName+".getPOReqFlg", e.getMessage());
        }
		
		return poReqFlg;
	}
	
	public  String checkNull(HttpServletRequest req, String str ){
	     String s="";
		String tmp =  req.getParameter(str);
		if (tmp != null) {
			if(tmp.trim().length()>0){
			  s=tmp.trim();
			  util.logMsg(false, clsName+".createServicerequest", str +" = "+s);
			}
		}
		return s;  
	 }
	public double toDecimal(String s) throws Exception {
		try{
			if ("null".equals(s) || s == null || s.trim().length() == 0){
				return 0.0;
			}else{
				return Double.parseDouble(s);
			}
		}catch(Exception e){
			util.logMsg(true,clsName+".getPOReqFlg", e.getMessage());
			return 0.0;
		}

	
	}
	   public String getCurrentTm(String postCd, String ctryCd, String dtTm){
			CanonCommonUtil util = new CanonCommonUtil();
			String tmZone ="";
			String convertTm="";
			boolean numCheck =false;
			 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
			 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmmss");
			 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
			 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
			 util.logMsg(false,clsName+".getCurrentTm..","postCd : "+postCd+" ctryCd : "+ctryCd+" dtTm: "+dtTm);
			 util.logMsg(false,clsName+".getCurrentTm..","sSysDt : "+sSysDt+" sSysTm : "+sSysTm);
			try{
				if(!("null".equals(postCd)) && !("".equals(postCd))){
					postCd = postCd.substring(0, 5);
				}
				NSXC001001SvcTimeZone tmUtil = new NSXC001001SvcTimeZone();
				SvcTimeZoneInfo info = new SvcTimeZoneInfo();
				
				String userName="";
				String timeRegion="EST";
				String appId="EXTNE307";
				String pageId="getTmZn";
				String appPageId="EXTNE307_getTmZn";
					
				CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
			/*	EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appId);
				bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/		
				
				if("".equals(dtTm) || "null".equals(dtTm)){
					info = tmUtil.convertTime(1, sSysDt, ctryCd, postCd);
				}else{
					numCheck = isNumber(dtTm);
					//System.out.println("numCheck: "+ numCheck);
					if(numCheck){
						info = tmUtil.convertTime(1, dtTm, ctryCd, postCd);
					}
				}
				
			/*	bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
				bizPerfCounter.requestQueue(); */
				  
				System.out.println("dtTm : "+dtTm);

				//tmUtil.convertTime(arg0, arg1, arg2, arg3)
				
				if("".equals(dtTm)){
					tmZone = info.getTimeZone();
				}else{
					if(numCheck){
						convertTm = info.getDateTime().substring(0, 14);
						System.out.println("getCurrentTm convertTm : "+convertTm);
					}
				}
				
				//String time = info.getDateTime();
				util.logMsg(false,clsName+".getCurrentTm..", "tmzone : "+ tmZone +" postCd: "+postCd + " ctryCd: " +ctryCd+" time : "+dtTm);
			} catch (Exception e) {
				util.logMsg(true,clsName+".getCurrentTm..", "Error timezone : "+ e.getMessage());
			} finally {	// Release DB resource (Close DB Connection)	
				EZDConnectionMgr.getInstance().releaseResource();
			}
			return convertTm;
		}
	   public static boolean isEmpty(String str) {
	        return str == null || str.length() == 0;
	    }

	    static Pattern PARRETN_DEIMAL_NUM = Pattern.compile("[+-]?(?:(?:0)|(?:[123456789][\\d,]*\\d*)|(?:[\\d,]*\\.\\d*))");

	    public static boolean isNumber(String str) {
	        if(isEmpty(str))return false;
	        Matcher matcher = PARRETN_DEIMAL_NUM.matcher(str);
	        return matcher.matches();
	    }
	
}

