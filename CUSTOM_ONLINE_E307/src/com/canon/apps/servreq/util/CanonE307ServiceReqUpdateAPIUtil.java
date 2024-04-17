package com.canon.apps.servreq.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import parts.common.EZDBizPerformanceCounter;
import parts.common.EZDStringUtil;
import parts.dbcommon.EZDConnectionMgr;
import business.parts.NSZC043001PMsg;
import canon.apps.common.CanonConstants;

import com.canon.apps.servreq.dao.CanonE307ServiceReqCreateDao;
import com.canon.common.CanonCommonUtil;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

public class CanonE307ServiceReqUpdateAPIUtil {

	StringBuffer sbParams;
	
	private   String clsName="CanonE307ServiceReqUpdateAPIUtil";
	          String  MODE_CODE_SR_CREATE="01";
	          String  MODE_CODE_SR_UPDATE="02";
	          String  MODE_CODE_SR_CANCEL="03";
	          CanonCommonUtil util;       
	          
	public CanonE307ServiceReqUpdateAPIUtil() {
		util= new CanonCommonUtil();
		sbParams = new StringBuffer("");
	}
	public  NSZC043001PMsg  getMsg(HttpServletRequest req, int x){
		
		 NSZC043001PMsg pmsg = new NSZC043001PMsg();
		
		try{
			
		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmm");
		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		 String sSvcDt=sdfDt.format(Calendar.getInstance().getTime()); 
		   
		 
		 // Mandatory Params
		 pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
		 String userId    = checkNull(req,"userName");
		 String modeCode    = checkNull(req,"modeCode");
		 String strTskCount = checkNull(req,"tskSize");
		 int tskCnt = 0;
		 if(!("".equals(strTskCount))&& !("null".equals(strTskCount))){
			tskCnt=Integer.parseInt(strTskCount);
		 }
		 
		 pmsg.xxModeCd.setValue(modeCode);
		 if(userId.length()>0) pmsg.userId.setValue(userId);
		 
		 sbParams.append("\n"+"glblCmpyCd" + "= "+CanonConstants.CSA_COMPANY_CODE);
		 sbParams.append("\n"+"xxModeCd" + " = "+MODE_CODE_SR_UPDATE);
		 sbParams.append("\n"+"userName" + " = "+userId);
		 sbParams.append("\n"+"Future Svc Dt" + " = "+sSvcDt);
		 sbParams.append("\n"+"Task Rcv Dt" + " = "+sSysDt);
		 sbParams.append("\n"+"Task Rcv Tm" + " = "+sSysTm);
		// sbParams.append("\n"+"xVal" + " = "+x);
		 String serNum   			= checkNull(req,"serialNumber");if(serNum.length()>0) pmsg.serNum.setValue(serNum);
		 
		 String svcMachMstrPk   	= checkNull(req,"svcMachMstrPk");if(svcMachMstrPk.length()>0) pmsg.svcMachMstrPk.setValue(new BigDecimal(svcMachMstrPk.trim()));
		 String slsDt               = checkNull(req,"slsDt");if(slsDt.length()>0) pmsg.slsDt.setValue(slsDt);
		 String custMachCtrlNum   	= checkNull(req,"tagNum");if(custMachCtrlNum.length()>0) pmsg.custMachCtrlNum.setValue(custMachCtrlNum);
		 String custTelNum          = checkNull(req,"custPhNum");if(custTelNum.length()>0) pmsg.custTelNum.setValue(custTelNum);
		 String custTelExtnNum      = checkNull(req,"custPhNumExt");if(custTelExtnNum.length()>0) pmsg.custTelExtnNum.setValue(custTelExtnNum);
		 String custEmlAddr         = checkNull(req,"emailAddr");if(custEmlAddr.length()>0) pmsg.custEmlAddr.setValue(custEmlAddr);
		 String fsrNum           	= checkNull(req,"fsr");if(fsrNum.length()>0) pmsg.fsrNum.setValue(fsrNum);

		 String caller           	= checkNull(req,"caller");if(caller.length()>0) pmsg.svcCustCllrTxt.setValue(caller);
		 String contactName 		= checkNull(req,"contactName");if(contactName.length()>0) pmsg.svcCustAttnTxt.setValue(contactName);
		 //String verifiedPO 			= checkNull(req,"verifiedPO");if(verifiedPO.length()>0) pmsg.attachedFileList.no(0).poVerFlg.setValue(verifiedPO);
		 String custPoNumber 		= checkNull(req,"custPoNumber");if(contactName.length()>0) pmsg.custPoNum.setValue(custPoNumber);
		 String custPoDt            = checkNull(req,"custPoDt");if(custPoDt.length()>0) pmsg.custPoDt.setValue(custPoDt);
		 String svcCallSrcTpCd       = checkNull(req,"sCrChannel");if(svcCallSrcTpCd.length()>0) pmsg.svcCallSrcTpCd.setValue(svcCallSrcTpCd);
		 String svcPblmTpCd          = checkNull(req,"sPbCode");if(svcPblmTpCd.length()>0) pmsg.svcPblmTpCd.setValue(svcPblmTpCd);
		 String custCaseNum          = checkNull(req,"custCaseNum");if(custCaseNum.length()>0) pmsg.custCseNum.setValue(custCaseNum);
		 String taskSts             = checkNull(req,"taskStatDet"+x);if(taskSts.length()>0)  pmsg.svcTaskStsCd.setValue(taskSts);
		 String verifiedPO 		    = checkNull(req,"verifiedPO");
		 System.out.println("verifiedPO: "+ verifiedPO);
		 if(verifiedPO.length()>0) {
			 pmsg.attachedFileList.no(0).poVerFlg.setValue("Y");
		 }else{
			 pmsg.attachedFileList.no(0).poVerFlg.setValue("N");
		 }
		 
		 pmsg.attachedFileList.setValidCount(1);
		 String fileNm                                  		 = checkNull(req,"fileNm");
		 if(fileNm.length()>0) pmsg.attachedFileList.no(0).attDataNm.setValue(fileNm);	
		 String profileId										 = checkNull(req,"profileId"); if(profileId.length()>0)pmsg.crCardCustRefNum.setValue(profileId);
		 String holderName										 = checkNull(req,"holderName"); if(holderName.length()>0)pmsg.crCardHldNm.setValue(holderName);	
		 String authAmnt										 = checkNull(req,"authAmnt"); if(authAmnt.length()>0)pmsg.crCardAuthAmt.setValue(new BigDecimal(authAmnt));
		 String cardType										 = checkNull(req,"cardType"); 
		 System.out.println("cardType: "+ cardType);
		 if(cardType.length()>0){
			 String crTpDesc="";
			 if(cardType.length()<3){
			 CanonE307ServiceReqCreateDao crtObj = new CanonE307ServiceReqCreateDao();
			 crTpDesc = crtObj.getCRCardTp(cardType);
			 System.out.println("crTpDesc : "+ crTpDesc);
			 }else{
				 crTpDesc =cardType;
			 }
			 if(crTpDesc.length()>0) pmsg.crCardTpCd.setValue(crTpDesc);

		 }
		 String cardExpr										 = checkNull(req,"cardExpr"); 
		 if(cardExpr.length()>0)pmsg.crCardExprYrMth.setValue(cardExpr);
		 String txRefNum 										 = checkNull(req,"txRefNum"); if(txRefNum.length()>0)pmsg.crCardAuthRefNum.setValue(txRefNum);
		 String strCcNum            = checkNull(req,"ccNum");if(strCcNum.length()>0) pmsg.crCardLastDigitNum.setValue(strCcNum);
		 
		 String ittNum              = checkNull(req,"ittCallNumber"); if(ittNum.length()>0) pmsg.ittNum.setValue(ittNum);
		 String machMgrCode         = checkNull(req,"machMgrCode");if(machMgrCode.length()>0)pmsg.svcCallRqstOwnrTocCd.setValue(machMgrCode);		 

			 String taskNum             = checkNull(req,"tskNum"+x);
			 System.out.println("Task det taskNum : " + taskNum);
			 if(taskNum.length()>0)  pmsg.taskList.no(0).svcTaskNum.setValue(taskNum);		 
			 String schdDisptEmlFlg    	= checkNull(req,"schdDisptEmlFlg");if(schdDisptEmlFlg.length()>0)    pmsg.taskList.no(0).schdDisptEmlFlg.setValue(schdDisptEmlFlg);//N		 
			 String mblIntfcFlg        	= checkNull(req,"mblIntfcFlg");if(mblIntfcFlg.length()>0)pmsg.taskList.no(0).mblIntfcFlg.setValue(mblIntfcFlg); // send to Click Software . ALWAYS "Y"  
			 String assDetCode          = checkNull(req,"assDetCode"+x);if(assDetCode.length()>0)pmsg.taskList.no(0).techCd.setValue(assDetCode);
			 String assignTypeDet       = checkNull(req,"assignTypeDet"+x);if(assignTypeDet.length()>0)pmsg.taskList.no(0).svcAsgTpCd.setValue(assignTypeDet);
			 String cllrPhNum			= checkNull(req,"callerPhn");
			 String cllrPhNumExt		= checkNull(req,"callerExtn");
			 String mobileNum 		  	= checkNull(req,"mobileNum");
			 if(cllrPhNum.length()>0)pmsg.taskList.no(0).svcCustCllrTelNum.setValue(cllrPhNum);
			 if(cllrPhNumExt.length()>0)pmsg.taskList.no(0).svcCustCllrTelExtnNum.setValue(cllrPhNumExt);
			 if(mobileNum.length()>0)pmsg.taskList.no(0).cellPhoNum.setValue(mobileNum);
			 
		if(tskCnt>0)
			pmsg.taskList.setValidCount(1);

		 String sNoteTypeTxt        = checkNull(req,"sNoteTypeTxt");
		 int memoCnt=0;
		 util.logMsg(false, clsName+".updateServicerequest", "sNoteTypeTxt length(): "+sNoteTypeTxt.length());
		 if(sNoteTypeTxt.length()>0) {
			 pmsg.svcMemoList.no(memoCnt).svcCmntTxt.setValue(sNoteTypeTxt);
			 String sNoteTypeCd   = checkNull(req,"sNoteType");
			 System.out.println("sNoteTypeCd : "+sNoteTypeCd);
			 if(sNoteTypeCd.length()>0) pmsg.svcMemoList.no(memoCnt).svcMemoTpCd.setValue(sNoteTypeCd);
			 memoCnt++;
		 }

		 // spl msg 
	/*	 String splMsg         = checkNull(req,"noteDetails");
		 System.out.println("splMsg : "+ splMsg);
		 if(splMsg.length()>0){
			 pmsg.svcMemoList.no(memoCnt).svcCmntTxt.setValue(splMsg);
			 String splMsgNtCd   = checkNull(req,"splMsgNtCd");
			 System.out.println("splMsgNtCd : "+ splMsgNtCd);
			 if(splMsgNtCd.length()>0) pmsg.svcMemoList.no(memoCnt).svcMemoTpCd.setValue(splMsgNtCd);
			 memoCnt++;
		 }*/
		 
		if(memoCnt!=0)
		 pmsg.svcMemoList.setValidCount(memoCnt);
		
		  
		  util.logMsg(false, clsName+".updateServicerequest", "UPDATE SR CALL PARAMETERS");
		  
		  util.logMsg(false, clsName+".updateServicerequest", sbParams.toString());
		  
		}catch(Exception e){
			util.logMsg(false, clsName+".updateServicerequest", "Exception in Getting Message :"+e.getMessage());
		}
		  
		return pmsg;
	}

	public List<String[]> updateServicerequest(HttpServletRequest req){
		//Create instance of API 
		int tskCnt=0;
		String res[] = new String[2];
		List lstMsg = new ArrayList();
		NSZC043001 s21Api = new NSZC043001();
		CanonE307ServiceReqCreateDao objCreate = new CanonE307ServiceReqCreateDao();
		//Create instance of message
		try {
			StringBuffer sb=new StringBuffer("");
			String strTskCount = checkNull(req,"tskSize");
			util.logMsg(false,clsName+".updateServicerequest1 ", "strTskCount: "+ strTskCount);
			 if(!("".equals(strTskCount))&& !("null".equals(strTskCount))){
				tskCnt=Integer.parseInt(strTskCount);
			 }
			 for(int i=0;i<tskCnt;i++){
				NSZC043001PMsg pmsg = new NSZC043001PMsg();
				String taskStat = checkNull(req,"taskStatDet"+i);
				util.logMsg(false,clsName+".updateServicerequest", "taskStat: "+ taskStat + "iVal : "+i);
				
				String tskupdFlg = objCreate.getTskUpdFlag(taskStat);
				if("Y".equals(tskupdFlg)){
					util.logMsg(false,clsName+".updateServicerequest", "taskStat INSIDE: "+ taskStat);
					pmsg = getMsg(req, i);		
				
					String userName=pmsg.userId.getValue();
					String timeRegion="EST";
					String appId="EXTNE307";
					String pageId="UpdateSR";
					String appPageId="EXTNE307_UpdateSR";
						
			/*		CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
			    	EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appPageId);
					bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/
					
					util.logMsg(false,clsName+".updateServicerequest", "################## Calling UPDATE SR API #######################");
					util.logMsg(false,clsName+".updateServicerequest", " Before UPDATE SR API Execute.......");
					util.logMsg(false,clsName+".updateServicerequest", " Before UPDATE SR API Params....../n"+ pmsg.toString());
					//execute API
				    s21Api.execute(pmsg, ONBATCH_TYPE.ONLINE);  
					
					util.logMsg(false,clsName+".updateServicerequest", " After UPDATE SR API Execute.......");
					 
			/*		bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
					bizPerfCounter.requestQueue();	*/				 
					  
					// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
					
						if (!S21ApiUtil.isXxMsgId(pmsg)) {
							
							// There is no message id, so can do commit the transaction.
							EZDConnectionMgr.getInstance().commit();
							res[0]="Y";
							res[1]=pmsg.fsrNum.getValue();
							// commit	
							util.logMsg(false,clsName+".updateServicerequest", "FSR Updated , FSR Number :"+ pmsg.fsrNum.getValue());
							lstMsg.add(res);
						} else {
								// Error Case - S21API set some error message id when got any error while S21API's function.		
								List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
								for (String msg : err) {
									util.logMsg(true,clsName+".updateServicerequest", "FSR Update Error :"+msg);
									sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
								}		
								res[0]="E";
								res[1]=sb.toString();
								// If S21API got error, roll-back the transaction.
									EZDConnectionMgr.getInstance().rollback(); 
								// roll-back		
								// throw new S21AbendException("S21AbendException is thrown...");
								//	break;
								lstMsg.add(res);
						}
					}
				 }
			} catch (Exception e) {
				util.logMsg(true,clsName+".updateServicerequest", "Error in FSR Update : "+ e.getMessage());
			} finally {	// Release DB resource (Close DB Connection)	
				EZDConnectionMgr.getInstance().releaseResource();
			}
		return lstMsg;
	}
	public  NSZC043001PMsg  cancelGetMsg(HttpServletRequest req){
		
		 NSZC043001PMsg pmsg = new NSZC043001PMsg();
		 int tskCnt=0;
		
		try{
		String strTskCount = checkNull(req,"tskSize");
		util.logMsg(false,clsName+".cancelServicerequest", "strTskCount: "+ strTskCount);
		 if(!("".equals(strTskCount))&& !("null".equals(strTskCount))){
			tskCnt=Integer.parseInt(strTskCount);
		 }			
		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmm");
		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		 String sSvcDt=sdfDt.format(Calendar.getInstance().getTime()); 
		   
		 
		 // Mandatory Params
		 pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
		 String userId    = checkNull(req,"userName");
		 String modeCode    = checkNull(req,"modeCode");

		 if(!("".equals(strTskCount))&& !("null".equals(strTskCount))){
			tskCnt=Integer.parseInt(strTskCount);
		 }
		 
		 pmsg.xxModeCd.setValue("03");
		 if(userId.length()>0) pmsg.userId.setValue(userId);
		 
		 sbParams.append("\n"+"glblCmpyCd" + "= "+CanonConstants.CSA_COMPANY_CODE);
		 sbParams.append("\n"+"xxModeCd" + " = "+MODE_CODE_SR_UPDATE);
		 sbParams.append("\n"+"userName" + " = "+userId);
		 sbParams.append("\n"+"Future Svc Dt" + " = "+sSvcDt);
		 sbParams.append("\n"+"Task Rcv Dt" + " = "+sSysDt);
		 sbParams.append("\n"+"Task Rcv Tm" + " = "+sSysTm);
		// sbParams.append("\n"+"xVal" + " = "+x);
		 String serNum   			= checkNull(req,"serialNumber");if(serNum.length()>0) pmsg.serNum.setValue(serNum);
		 
		 String svcMachMstrPk   	= checkNull(req,"svcMachMstrPk");if(svcMachMstrPk.length()>0) pmsg.svcMachMstrPk.setValue(new BigDecimal(svcMachMstrPk.trim()));
		 String slsDt               = checkNull(req,"slsDt");if(slsDt.length()>0) pmsg.slsDt.setValue(slsDt);
		 String custMachCtrlNum   	= checkNull(req,"tagNum");if(custMachCtrlNum.length()>0) pmsg.custMachCtrlNum.setValue(custMachCtrlNum);
		 String custTelNum          = checkNull(req,"custPhNum");if(custTelNum.length()>0) pmsg.custTelNum.setValue(custTelNum);
		 String custTelExtnNum      = checkNull(req,"custPhNumExt");if(custTelExtnNum.length()>0) pmsg.custTelExtnNum.setValue(custTelExtnNum);
		 String custEmlAddr         = checkNull(req,"emailAddr");if(custEmlAddr.length()>0) pmsg.custEmlAddr.setValue(custEmlAddr);
		 String fsrNum           	= checkNull(req,"fsr");if(fsrNum.length()>0) pmsg.fsrNum.setValue(fsrNum);
		 String caller           	= checkNull(req,"caller");if(caller.length()>0) pmsg.svcCustCllrTxt.setValue(caller);
		 String contactName 		= checkNull(req,"contactName");if(contactName.length()>0) pmsg.svcCustAttnTxt.setValue(contactName);

		 
		 pmsg.attachedFileList.setValidCount(1);
		 
		 String custPoNumber 		= checkNull(req,"custPoNumber");if(contactName.length()>0) pmsg.custPoNum.setValue(custPoNumber);
		 String custPoDt            = checkNull(req,"custPoDt");if(custPoDt.length()>0) pmsg.custPoDt.setValue(custPoDt);
		 String svcCallSrcTpCd       = checkNull(req,"sCrChannel");if(svcCallSrcTpCd.length()>0) pmsg.svcCallSrcTpCd.setValue(svcCallSrcTpCd);
		 String svcPblmTpCd          = checkNull(req,"sPbCode");if(svcPblmTpCd.length()>0) pmsg.svcPblmTpCd.setValue(svcPblmTpCd);
		 String custCaseNum          = checkNull(req,"custCaseNum");if(custCaseNum.length()>0) pmsg.custCseNum.setValue(custCaseNum);
		
		
		 pmsg.svcTaskRcvDt.setValue(sSysDt);
		 pmsg.svcTaskRcvTm.setValue(sSysTm+"00");

		 String ittNum              = checkNull(req,"ittCallNumber"); if(ittNum.length()>0) pmsg.ittNum.setValue(ittNum);
		 String machMgrCode         = checkNull(req,"machMgrCode");if(machMgrCode.length()>0)pmsg.svcCallRqstOwnrTocCd.setValue(machMgrCode);	
		 for(int x=0;x<tskCnt;x++){
			 String taskNum             = checkNull(req,"tskNum"+x);if(taskNum.length()>0)  pmsg.taskList.no(x).svcTaskNum.setValue(taskNum);	
			 util.logMsg(false,clsName+".cancelServicerequest", "taskNum: "+ taskNum);
			 String schdDisptEmlFlg    	= checkNull(req,"schdDisptEmlFlg");if(schdDisptEmlFlg.length()>0)    pmsg.taskList.no(x).schdDisptEmlFlg.setValue(schdDisptEmlFlg);//N
			 String mblIntfcFlg        	= checkNull(req,"mblIntfcFlg");if(mblIntfcFlg.length()>0)pmsg.taskList.no(x).mblIntfcFlg.setValue(mblIntfcFlg); // send to Click Software . ALWAYS "Y"
			 String assDetCode          = checkNull(req,"assDetCode"+x);if(assDetCode.length()>0)pmsg.taskList.no(x).techCd.setValue(assDetCode);
			 String assignTypeDet       = checkNull(req,"assignTypeDet"+x);if(assignTypeDet.length()>0)pmsg.taskList.no(x).svcAsgTpCd.setValue(assignTypeDet);
			 String taskSts             = checkNull(req,"taskStatDet"+x);if(taskSts.length()>0)  pmsg.svcTaskStsCd.setValue(taskSts);
		 }
		if(tskCnt>0)
			pmsg.taskList.setValidCount(tskCnt);

		 String sNoteTypeTxt        = checkNull(req,"sNoteTypeTxt");
		 int memoCnt=0;
		 if(sNoteTypeTxt.length()>0) {
			 pmsg.svcMemoList.no(memoCnt).svcCmntTxt.setValue(sNoteTypeTxt);
			 String sNoteTypeCd   = checkNull(req,"sNoteType");
			 util.logMsg(false,clsName+".cancelServicerequest", "sNoteTypeCd: "+ sNoteTypeCd);
			 if(sNoteTypeCd.length()>0) pmsg.svcMemoList.no(memoCnt).svcMemoTpCd.setValue(sNoteTypeCd);
			 memoCnt++;
		 }

		 
		if(memoCnt!=0)
		 pmsg.svcMemoList.setValidCount(memoCnt);
		
		  
		  util.logMsg(false, clsName+".cancelServicerequest", "CANCEL SR");
		  
		  util.logMsg(false, clsName+".cancelServicerequest", sbParams.toString());
		  
		}catch(Exception e){
			util.logMsg(true, clsName+".cancelServicerequest", "Exception in Getting Message :"+e.getMessage());
		}
		  
		return pmsg;
	}	
	public String[] cancelServicerequest(HttpServletRequest req) throws ParseException{
		
		
		//Create instance of API 
		NSZC043001 s21Api = new NSZC043001();
		
		String res[] = new String[2];
		
		
		//Create instance of message
		NSZC043001PMsg pmsg = new NSZC043001PMsg();
		pmsg = cancelGetMsg(req);
		
		String userName=pmsg.userId.getValue();
		String timeRegion="EST";
		String appId="EXTNE307";
		String pageId="CancelSR";
		String appPageId="EXTNE307_CancelSR";
			
	/*	CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
		EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appId);
		bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/
		
		util.logMsg(false,clsName+".cancelServicerequest", pmsg.toString());
		util.logMsg(false,clsName+".cancelServicerequest", "CANCEL SR API");
		 
		//execute API
		s21Api.execute(pmsg, ONBATCH_TYPE.ONLINE); 
		
	/*	bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
		bizPerfCounter.requestQueue();		*/
		
		try {
			
			// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
			
				if (!S21ApiUtil.isXxMsgId(pmsg)) {
					
					// There is no message id, so can do commit the transaction.
					EZDConnectionMgr.getInstance().commit();
					res[0]="Y";
					res[1]=pmsg.fsrNum.getValue();
					// commit	
					util.logMsg(false,clsName+".cancelServicerequest", "FSR Cancelled , FSR Number :"+ pmsg.fsrNum.getValue());
				} else {
					StringBuffer sb=new StringBuffer("");
						// Error Case - S21API set some error message id when got any error while S21API's function.		
						List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
						for (String msg : err) {
							util.logMsg(true,clsName+".cancelServicerequest", "Call Cancellation Error :"+msg);
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
				res[1]=e.getMessage();
				util.logMsg(true,clsName+".cancelServicerequest", "Error in FSR Cancel : "+ e.getMessage());
			} finally {	// Release DB resource (Close DB Connection)	
				EZDConnectionMgr.getInstance().releaseResource();
			}
		return res;
	}	
	public  String checkNull(HttpServletRequest req, String str ){
	     String s="";
		String tmp =  req.getParameter(str);
		if (tmp != null) {
			if(tmp.trim().length()>0){
			  s=tmp.trim();
			  sbParams.append("\n" +str +" = "+s );
			}
		}
		return s;  
	 }
}

