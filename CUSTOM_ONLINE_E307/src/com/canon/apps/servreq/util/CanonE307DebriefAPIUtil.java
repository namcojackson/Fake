package com.canon.apps.servreq.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import parts.common.EZDBizPerformanceCounter;
import parts.common.EZDStringUtil;
import parts.dbcommon.EZDConnectionMgr;
import business.parts.NSZC070001PMsg;
import canon.apps.common.CanonConstants;

import com.canon.apps.servreq.dao.CanonE307DebriefDetailsDAO;
import com.canon.common.CanonCommonUtil;
import com.canon.cusa.s21.api.NSZ.NSZC070001.NSZC070001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.apps.servreq.util.CanonE307ServiceMtrRdsAPIUtil;


public class CanonE307DebriefAPIUtil {
	
	StringBuffer sbParams;
	
	private   String clsName="CanonE307DebriefAPIUtil";
	          String  MODE_CODE_DEBRIEF="D";
	          String  MODE_CODE_CHARGES="C";
	          CanonCommonUtil util;   

	public CanonE307DebriefAPIUtil() {
		util= new CanonCommonUtil();
		sbParams = new StringBuffer("");
	}

	public  NSZC070001PMsg  getMsg(HttpServletRequest req) throws ParseException{
		
		 NSZC070001PMsg pmsg = new NSZC070001PMsg();
		//try{

		 CanonE307ServiceReqSumryAPIUtil utilObj = new CanonE307ServiceReqSumryAPIUtil();
		 CanonE307DebriefDetailsDAO dtlObj = new CanonE307DebriefDetailsDAO();
		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmm");
		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		 String sSvcDt=sdfDt.format(Calendar.getInstance().getTime()); 
		 int lbrSz=0;
		 int prtSz =0;
		 int expSz=0;
		 
		 // Mandatory Params
		 pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
		 String userId    = checkNull(req,"userName");
		// String modeCode    = checkNull(req,"modeCode");
		 
		 pmsg.xxModeCd.setValue(MODE_CODE_DEBRIEF);
		 pmsg.slsDt.setValue(sSysDt);
		 if(userId.length()>0) pmsg.usrId.setValue(userId);
		 
		 sbParams.append("\n"+"glblCmpyCd" + "= "+CanonConstants.CSA_COMPANY_CODE);
		 sbParams.append("\n"+"xxModeCd" + " = "+MODE_CODE_DEBRIEF);
		 sbParams.append("\n"+"userName" + " = "+userId);
		 sbParams.append("\n"+"Future Svc Dt" + " = "+sSvcDt);
		 sbParams.append("\n"+"Task Rcv Dt" + " = "+sSysDt);
		 sbParams.append("\n"+"Task Rcv Tm" + " = "+sSysTm);
		 
		 String fsrNum           	= checkNull(req,"fsr");if(fsrNum.length()>0) pmsg.fsrNum.setValue(fsrNum);
		 String taskNumbr			=checkNull(req, "taskNumbr");if(taskNumbr.length()>0)pmsg.svcTaskNum.setValue(taskNumbr);
		 String svcMachMstrPk   	= checkNull(req,"svcMachMstrPk");if(svcMachMstrPk.length()>0) pmsg.svcMachMstrPk.setValue(new BigDecimal(svcMachMstrPk.trim()));

		 String problemCode 		= checkNull(req,"problemCode");if(problemCode.length()>0) pmsg.svcPblmTpCd.setValue(problemCode.trim());
		 String locCode 			=checkNull(req,"locCode");if(locCode.length()>0) pmsg.svcPblmLocTpCd.setValue(locCode); 
		 String resCode				=checkNull(req,"resCode");if(resCode.length()>0) pmsg.svcPblmRsnTpCd.setValue(resCode);
		 String corrCode			=checkNull(req,"corrCode");if(corrCode.length()>0) pmsg.svcPblmCrctTpCd.setValue(corrCode);
		 String iwrStat				=checkNull(req,"iwrStat");if(iwrStat.length()>0) pmsg.iwrStsCd.setValue(iwrStat);
		 String machineStatus 		=checkNull(req,"machineStatus");if(machineStatus.length()>0)pmsg.machDownFlg.setValue(machineStatus);
		 
		 String sNoteTypeTxt        = checkNull(req,"noteDtls");
		 System.out.println("sNoteTypeTxt: "+ sNoteTypeTxt.length());

		 if(sNoteTypeTxt.length()>0) {
			 pmsg.svcMemoList.no(0).svcCmntTxt.setValue(sNoteTypeTxt);
			 String sNoteTypeCd   = checkNull(req,"sNoteType");
			 if(sNoteTypeCd.length()>0)	 pmsg.svcMemoList.no(0).svcMemoTpCd.setValue(sNoteTypeCd);
			 pmsg.svcMemoList.setValidCount(1);
		 }

		 String labRecSize = checkNull(req,"labRecSize");
		 int lbrCnt = 0;
		 if(!("".equals(labRecSize))&&!("null".equals(labRecSize))){
			 lbrCnt=Integer.parseInt(labRecSize);
		 }
         util.logMsg(false,clsName+".debriefTask", " lbrCnt : "+ lbrCnt);
		 String partRecSize = checkNull(req,"partRecSize");
		 int prtCnt = 0;
		 if(!("".equals(partRecSize))&&!("null".equals(partRecSize))){
			 prtCnt=Integer.parseInt(partRecSize);
		 }
		 util.logMsg(false,clsName+".debriefTask", " prtCnt : "+ prtCnt);
		 String expenseRecSize = checkNull(req,"expenseRecSize");
		 int expCnt = 0;
		 if(!("".equals(expenseRecSize))&&!("null".equals(expenseRecSize))){
			 expCnt=Integer.parseInt(expenseRecSize);
		 }	
		 util.logMsg(false,clsName+".debriefTask", " expCnt : "+ expCnt); 
		 //Labor
		 
		 for(int i=0;i<lbrCnt;i++){
			 String laborItem 	= checkNull(req, "laborItem"+i);

			 if(laborItem.length()>0){
				 lbrSz = lbrSz+1;
				 String fsrVstTmPk 			=checkNull(req,"fsrVstTmPk"+i);if(fsrVstTmPk.length()>0)pmsg.xxLaborList.no(i).fsrVisitTmEventPk.setValue(new BigDecimal(fsrVstTmPk));
				 if(taskNumbr.length()>0)pmsg.xxLaborList.no(i).svcTaskNum.setValue(taskNumbr);
				 
				 util.logMsg(false,clsName+".debriefTask", " laborItem : "+ laborItem);

				 String billTpCd = dtlObj.getBillTpCd(laborItem);
				 
				 util.logMsg(false,clsName+".debriefTask", " billTpCd : "+ billTpCd);
				 
				 if(billTpCd!=null && billTpCd!="null" && billTpCd!="" && billTpCd.length()>0){
					 pmsg.xxLaborList.no(i).mdseItemBillTpCd.setValue(billTpCd);
				 }else{
					 pmsg.xxLaborList.no(i).mdseItemBillTpCd.setValue("*");
				 }
				 pmsg.xxLaborList.no(i).mdseCd.setValue(laborItem);
				// if("068ZZ634".equals(laborItem)){
					// Do nothing 
				// }else{
					 String lbrStrtDt                                   = checkNull(req,"lbrStrtDt"+i);
					 System.out.println("lbrStrtDt : "+ lbrStrtDt);
					 if(lbrStrtDt.length()>0) {
						 lbrStrtDt =util.formatDateString(util.DT_FORMAT5, util.DT_FORMAT3, lbrStrtDt);
						 pmsg.xxLaborList.no(i).svcTmEventFromDt.setValue(lbrStrtDt);
					 } 
					// String lbrStrtTm   	  = checkNull(req, "lbrStrtTm"+i);
					 String lbrStrtHr 		  = checkNull(req, "lbrStrtHr"+i);
					 String lbrStrtMn 		  = checkNull(req, "lbrStrtMn"+i);
					 String lbrStrtAmPm 	  = checkNull(req, "strtAmPm"+i);
					 String strTm = util.format24HrTm(lbrStrtHr+lbrStrtMn+" "+lbrStrtAmPm);
					 
					 System.out.println(" lbrStrtHr : "+ lbrStrtHr+" lbrStrtMn: "+lbrStrtMn+" lbrStrtAmPm: "+lbrStrtAmPm+" strTm: "+strTm);
	
					 if(strTm.trim().length()>0) pmsg.xxLaborList.no(i).svcTmEventFromTm.setValue(strTm+"00");			
					 
					 String lbrEndDt                                   = checkNull(req,"lbrEndDt"+i);
					 if(lbrEndDt.length()>0) {
						 lbrEndDt =util.formatDateString(util.DT_FORMAT5, util.DT_FORMAT3, lbrEndDt);
						 pmsg.xxLaborList.no(i).svcTmEventToDt.setValue(lbrEndDt);
					 } 
					// String lbrEndTm   	  =checkNull(req, "lbrEndTm"+i);
					 String lbrEndHr   	  =checkNull(req, "lbrEndHr"+i);
					 String lbrEndMn   	  =checkNull(req, "lbrEndMn"+i);
					 String lbrEndAmPm 	  = checkNull(req, "endAmPm"+i);
					 String strEndTm = util.format24HrTm(lbrEndHr+lbrEndMn+" "+lbrEndAmPm);
					 System.out.println(" strTmin creation strEndTm : "+strEndTm);
	
					 if(strEndTm.trim().length()>0) pmsg.xxLaborList.no(i).svcTmEventToTm.setValue(strEndTm+"00");
				// }
				 
				 
				 String laborDuration   	  =checkNull(req, "laborDuration"+i);
				 System.out.println("laborDuration "+ laborDuration);
				 if(laborDuration.trim().length()>0) {
					 double lbrDrn = Double.parseDouble(laborDuration);
					 System.out.println("lbrDrn "+ lbrDrn);
					 double durtst = lbrDrn*60;
					 int retVal = (int)Math.round(lbrDrn*60);
					 System.out.println("retVal : "+ retVal+" durtst : "+durtst);
					 pmsg.xxLaborList.no(i).durnTmNum.setValue(retVal);
				 }
				 String laborMod 			=checkNull(req,"laborMod"+i);if(laborMod.length()>0)pmsg.xxLaborList.no(i).svcModPlnId.setValue(laborMod);
				 String laborSerNumber 			= checkNull(req,"laborSerNumber"+i);if(laborSerNumber.length()>0)pmsg.xxLaborList.no(i).serNumTxt.setValue(laborSerNumber);
				 String laborModItem 			= checkNull(req,"laborModItem"+i);if(laborModItem.length()>0)pmsg.xxLaborList.no(i).modItemTxt.setValue(laborModItem);
				 String lbrChrgFlg              = checkNull(req,"lbrChrgFlg"+i);if(lbrChrgFlg.length()>0)pmsg.xxLaborList.no(i).lborChrgFlg.setValue(lbrChrgFlg);
				 String laborComments           = checkNull(req,"laborComments"+i);
		
				 if(laborComments.length()>0) 
					 pmsg.xxLaborList.no(i).svcLborCmntTxt.setValue(laborComments);
				 
			 }
		 }
		 
		 if(lbrSz!=0)
		 pmsg.xxLaborList.setValidCount(lbrSz);

		 //Parts
		 for(int y=0;y<prtCnt;y++){
			 String partItemSel 	=checkNull(req, "partItem"+y);
			 String partItm = checkNull(req, "prtCode"+y);
			 util.logMsg(false, clsName+".debriefTask", " partItemSel: "+partItemSel+" partItm: "+partItm);
			 String partItem="";
			 String partDescription="";
			 if(partItm!="null" && partItm!="" && partItm.length()>0){
				 	if(partItm.indexOf(" - ")>0){
						partItem = partItm.split(" - ")[0].trim();
						partDescription  = partItm.split(" - ")[1].trim();
				 	}else{
				 		partItem = partItm;
				 	}
			 }else if(partItemSel!=null && partItemSel.length()>0){
				 if(partItemSel.indexOf(" - ")>0){
				 	partItem = partItemSel.split(" - ")[0].trim();
				 	partDescription  = partItemSel.split(" - ")[1].trim();
				 }else{
					 partItem = partItemSel;
				 }
			 } 
			 util.logMsg(false, clsName+".debriefTask", " partItem: "+partItem+" partDescription: "+partDescription);
			 if(partItem.length()>0){
				 prtSz = prtSz+1;

				 String fsrUsgPk 	=checkNull(req, "fsrUsgPk"+y);
				 util.logMsg(false, clsName+".debriefTask", "fsrUsgPk: "+ fsrUsgPk);
				 if(fsrUsgPk.length()>0)pmsg.xxPartsUsageList.no(y).fsrUsgPk.setValue(new BigDecimal(fsrUsgPk));
				 if(taskNumbr.length()>0)pmsg.xxPartsUsageList.no(y).svcTaskNum.setValue(taskNumbr);
				 pmsg.xxPartsUsageList.no(y).mdseCd.setValue(partItem);
				 //String partDescription 	=checkNull(req, "partDescription"+y);
				 util.logMsg(false, clsName+".debriefTask", " partItem: "+partItem+" partDescription: "+partDescription);
				 if(partDescription.length()>0)pmsg.xxPartsUsageList.no(y).mdseNm.setValue(partDescription);
				 String partQty = checkNull(req, "partQty"+y);
				 util.logMsg(false, clsName+".debriefTask", " partQty: "+partQty);
				 int pQty = 0;
				 if(partQty.length()>0)
					 pQty = Integer.parseInt(partQty);
				 pmsg.xxPartsUsageList.no(y).svcPrtQty.setValue(pQty);
				 String partsUOM 	=checkNull(req, "partsUOM"+y);
				 if(partsUOM.length()>0)pmsg.xxPartsUsageList.no(y).pkgUomCd.setValue("EA");
				 String partMod 	=checkNull(req, "partMod"+y);if(partMod.length()>0)pmsg.xxPartsUsageList.no(y).svcModPlnId.setValue(partMod);
				 String partSerlNumber 	=checkNull(req, "partSerlNumber"+y);if(partSerlNumber.length()>0)pmsg.xxPartsUsageList.no(y).serNumTxt.setValue(partSerlNumber);
				 String partModItem 	=checkNull(req, "partModItem"+y);if(partModItem.length()>0)pmsg.xxPartsUsageList.no(y).modItemTxt.setValue(partModItem);
				 String prtsChrgFlg     = checkNull(req, "prtsChrgFlg"+y);if(prtsChrgFlg.length()>0)pmsg.xxPartsUsageList.no(y).prtChrgFlg.setValue(prtsChrgFlg);
				 String partServiceDate                                   = checkNull(req,"partServiceDate"+y);
				 util.logMsg(false, clsName+".debriefTask", " partServiceDate: "+partServiceDate);
				 if(partServiceDate.length()>0) {
					 partServiceDate =util.formatDateString(util.DT_FORMAT5, util.DT_FORMAT3, partServiceDate);
					 pmsg.xxPartsUsageList.no(y).svcExecDt.setValue(partServiceDate);
				 }
				 String partComments        = checkNull(req,"partComments"+y);
				 if(partComments.length()>0) 
					 pmsg.xxPartsUsageList.no(y).svcPrtCmntTxt.setValue(partComments);
			}
		 }
		 if(prtSz!=0)
		 pmsg.xxPartsUsageList.setValidCount(prtSz);
		//Expenses
		 for(int x=0;x<expCnt;x++){
			 String expenseItemSel = checkNull(req, "expItemSel"+x);
			 String expenseItem = "";
			 if(expenseItemSel!="null" && expenseItemSel!="" && expenseItemSel.length()>0){
				 util.logMsg(false, clsName+".debriefTask", " expenseItemSel: "+expenseItemSel);
				 expenseItem = expenseItemSel.split("-")[0].trim();
			 }
					 
			 util.logMsg(false, clsName+".debriefTask", "expenseItem : "+ expenseItem+" expenseItemSel: "+expenseItemSel);
			 if(expenseItem.length()>0){
				 expSz = expSz+1;
				 String fsrExpPk 	= checkNull(req, "fsrExpPk"+x);
				 util.logMsg(false, clsName+".debriefTask", "expSz : "+ expSz+" fsrExpPk : "+fsrExpPk);
				 if(fsrExpPk.length()>0)pmsg.xxExpenseList.no(x).fsrExpPk.setValue(new BigDecimal(fsrExpPk));
				 if(taskNumbr.length()>0)pmsg.xxExpenseList.no(x).svcTaskNum.setValue(taskNumbr);
				 pmsg.xxExpenseList.no(x).mdseCd.setValue(expenseItem);
				 String expenseQty = checkNull(req, "expenseQty"+x);
				 util.logMsg(false, clsName+".debriefTask", "expenseQty : "+ expenseQty);
				 int expQty = 0;
				 if(expenseQty.length()>0)
					 expQty = Integer.parseInt(expenseQty);
				 pmsg.xxExpenseList.no(x).expQty.setValue(expQty);
				 util.logMsg(false, clsName+".debriefTask", "expQty: "+ expQty);
				 String expenseUOM 	=checkNull(req, "expenseUOM"+x);if(expenseUOM.length()>0)pmsg.xxExpenseList.no(x).pkgUomCd.setValue(expenseUOM);
				 
				 String expServiceDate                                   = checkNull(req,"expServiceDate"+x);
				 if(expServiceDate.length()>0) {
					 expServiceDate =util.formatDateString(util.DT_FORMAT5, util.DT_FORMAT3, expServiceDate);
					 pmsg.xxExpenseList.no(x).svcExecDt.setValue(expServiceDate);
				 }
				 String expenseAmt = checkNull(req,"expenseAmt"+x);
				 util.logMsg(false, clsName+".debriefTask", "expenseAmt: "+ expenseAmt);
				 if(expenseAmt.length()>0)pmsg.xxExpenseList.no(x).ovrdExpUnitAmt.setValue(new BigDecimal(expenseAmt));
				 double expAmt=0.0;
				 if(expenseAmt.length()>0){
					 expAmt = Double.parseDouble(expenseAmt);
				 }
				 if(expenseQty.length()>0){
					 double dealAmt = expQty*expAmt;
					 util.logMsg(false, clsName+".debriefTask", "dealAmt: "+ dealAmt);
					 pmsg.xxExpenseList.no(x).svcExpDealAmt.setValue(BigDecimal.valueOf(dealAmt));
					 util.logMsg(false, clsName+".debriefTask", "expQty: "+ expQty+" expAmt: "+expAmt+" dealAmt: "+dealAmt);
				 }
			
				 String expenseComments        = checkNull(req,"expenseComments"+x);
				 
				// pmsg.xxExpenseList.no(x).svcExpDealAmt.setValue(val);
	
				 if(expenseComments.length()>0) 
					 pmsg.xxExpenseList.no(x).svcExpCmntTxt.setValue(expenseComments);	
			}
		 }
		 if(expSz!=0)
		 pmsg.xxExpenseList.setValidCount(expSz);
		 
		  util.logMsg(false, clsName+".debriefTask", "debriefTask PARAMETERS");
		  
		  util.logMsg(false, clsName+".debriefTask", sbParams.toString());
		  
	//	}catch(Exception e){
	//		util.logMsg(false, clsName+".debriefTask", "Exception in Getting Message :"+e.getMessage());
		//}
		  
		return pmsg;
	}	
	public String[] debriefTask(HttpServletRequest req){
		
		//Create instance of API 
		String res[] = new String[2];
		NSZC070001 s21Api = new NSZC070001();
		CanonE307ServiceMtrRdsAPIUtil mtrObj = new CanonE307ServiceMtrRdsAPIUtil();
		
		//Create instance of message
		try {
			 String mtrSubmitFlg = checkNull(req, "mtrSubmtFlg");
			 String mtrSz = checkNull(req, "mtrRdsSz");
			 int mtrCnt = 0;
			 if(!("".equals(mtrSz))&&!("null".equals(mtrSz))){
				 mtrCnt=Integer.parseInt(mtrSz);
			 }
			 String inMtrFlg = "N";
			 String outMtrFlg = "N";
			 String fsrNum           	= checkNull(req,"fsr");
			 String taskNumber			=checkNull(req, "taskNumbr");
			 String svcMachMstrPk   	= checkNull(req,"svcMachMstrPk");
			 String inOutRdFlg   	    = checkNull(req, "inOutRdFlg");
			 
			 util.logMsg(false,clsName+".debriefTask", " mtrSubmitFlg: " + mtrSubmitFlg+" mtrSz: "+mtrSz+"inOutRdFlg: "+inOutRdFlg);
			 
			 if(mtrCnt>0 && "Y".equals(mtrSubmitFlg)){
				 String tskSts = checkNull(req, "svcTskSts");
				 String postalCd = checkNull(req, "postalCd");
				 String countryCd = checkNull(req, "countryCd");
				
				 String mtrInReadDate =  checkNull(req, "mtrInReadDate");
				 String mtrOutReadDate =  checkNull(req, "mtrOutReadDate");
				 
				String mtrInReadDt = getCurrentTm(postalCd, countryCd, mtrInReadDate);

				String mtrOutReadDt = getCurrentTm(postalCd, countryCd, mtrOutReadDate);
				
				 System.out.println("mtrInReadDate: "+mtrInReadDate+" mtrOutReadDate: "+mtrOutReadDate+" mtrInReadDt: "+mtrInReadDt+" mtrOutReadDt: "+mtrOutReadDt+" tskSts: "+tskSts);

			/*	 if("ALL".equalsIgnoreCase(inOutRdFlg)){
					 inMtrFlg = "I";
					 outMtrFlg = "O";
				 }else if("IN".equalsIgnoreCase(tskSts)){
					 inMtrFlg = "I";
				 }else if("OUT".equalsIgnoreCase(tskSts)){
					 outMtrFlg = "O";
				 }*/
				 
				/* System.out.println("tskSts: "+ tskSts);
				 for(int y=0;y<mtrCnt;y++){
					 String inMtrRd = checkNull(req, "inMtrRd"+y);
					 String exInMtrRd = checkNull(req, "exInMtrRd"+y);
					 String outMtrRd = checkNull(req, "outMtrRd"+y);
					 String exOutMtrRd = checkNull(req, "exOutMtrRd"+y);
				 }
				*/	 
					 
				 if("Arrived".equals(tskSts)){
						 inMtrFlg = "I";
						 outMtrFlg = "O";
					 }else if("In Route".equals(tskSts)){
						 inMtrFlg = "I";
					 }
				

				if("I".equals(inMtrFlg)){
					res = mtrObj.submitMtrRds(req, inMtrFlg, mtrInReadDt);
					if(res!=null){
						 util.logMsg(false,clsName+".debriefTask", " After In Meter Reads res[0]......." + res[0]);
					}					
				}
				if("O".equals(outMtrFlg) && (res!=null && "Y".equals(res[0]) || "N".equals(inMtrFlg))){
					res = mtrObj.submitMtrRds(req, outMtrFlg, mtrOutReadDt);
					if(res!=null){
						 util.logMsg(false,clsName+".debriefTask", " After Out Meter Reads res[0]......." + res[0]);
					}
				}		 
			}	 
			 if((res!=null && "Y".equals(res[0])) || ("N".equals(inMtrFlg) && "N".equals(outMtrFlg))){
				 	StringBuffer sb=new StringBuffer("");

					NSZC070001PMsg pmsg = new NSZC070001PMsg();
					try{

						pmsg = getMsg(req);	 
					
						String userName=pmsg.usrId.getValue();
						String timeRegion="EST";
						String appId="EXTNE307";
						String pageId="DebriefTask";
						String appPageId="EXTNE307_DEBRIEF";
						
					/*	CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
						EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appId);
						bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/
					
						util.logMsg(false,clsName+".debriefTask", "################## Calling DEBRIEF API #######################");
						util.logMsg(false,clsName+".debriefTask", " Before DEBRIEF API Execute.......");
						util.logMsg(false,clsName+".debriefTask", " Before DEBRIEF API Params....../n"+ pmsg.toString());
						
						//execute API
					    s21Api.execute(pmsg, ONBATCH_TYPE.ONLINE);  
						
						util.logMsg(false,clsName+".debriefTask", " After DEBRIEF API Execute.......");
					/*	bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
						bizPerfCounter.requestQueue();			*/			 
						  
						// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
					
						if (!S21ApiUtil.isXxMsgId(pmsg)) {
							
							// There is no message id, so can do commit the transaction.
							EZDConnectionMgr.getInstance().commit();
							res[0]="Y";
							res[1]=pmsg.fsrNum.getValue();
							// commit	
							util.logMsg(false,clsName+".debriefTask", "Debrief Success, FSR Number :"+ pmsg.fsrNum.getValue());
							
							if(mtrCnt>0 && "Y".equals(mtrSubmitFlg)){
								String res1[] = mtrObj.invalidMeterReads(fsrNum, taskNumber, svcMachMstrPk, inMtrFlg, userName);
							}
							
							util.logMsg(false,clsName+".submitMtrRds", "After invalidReads :");
					
		
						} else {
								// Error Case - S21API set some error message id when got any error while S21API's function.		
								List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
								for (String msg : err) {
									util.logMsg(true,clsName+".debriefTask", "Debrief Task Error :"+msg);
									sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
								}		
								res[0]="DE";
								res[1]=sb.toString();
								// If S21API got error, roll-back the transaction.
									EZDConnectionMgr.getInstance().rollback(); 
								// roll-back		
								// throw new S21AbendException("S21AbendException is thrown...");
						}	
	
					}catch (Exception e) {
						util.logMsg(true,clsName+".debriefTask", "Error while debriefing the task: "+ e.getMessage());	
						res[0]="DE";
							try{
								String str = e.getMessage();
								Pattern p = Pattern.compile("msg=.*");
		
								Matcher m = p.matcher(str);
	
								if (m.find()) {
									res[1] =  m.group(0).trim();
								}else{
									res[1] = e.getMessage();
								}
								util.logMsg(true,clsName+".debriefTask", "Exception message while debriefing the task: "+ res[1]);	
							}catch (Exception ex) {
								util.logMsg(true,clsName+".debriefTask", "Error while parsing error message: "+ ex.getMessage());	
							}
							EZDConnectionMgr.getInstance().rollback();
					}
			 	}
			} catch (Exception e) {
				util.logMsg(true,clsName+".debriefTask", "Error in Debrief Task main exception: "+ e.getMessage());
				res[0]="DE";
				res[1]="Error while debriefing the task";
				EZDConnectionMgr.getInstance().rollback();
			} finally {	// Release DB resource (Close DB Connection)	
				EZDConnectionMgr.getInstance().releaseResource();
			}
		return res;
	}
	public  String checkNull(HttpServletRequest req, String str ){
	     String s="";
		String tmp =  req.getParameter(str)==null?"":req.getParameter(str);
		if (tmp != null ) {
			if ("null".equals(tmp)) {
				return s;
			}else{
				if(tmp.trim().length()>0){
				  s=tmp.trim();
				  sbParams.append("\n" +str +" = "+s );
				}
			}
		}
		return s;  
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
				 util.logMsg(false,clsName+"Meter Read API.getCurrentTm..","postCd : "+postCd+" ctryCd : "+ctryCd+" dtTm: "+dtTm);
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
						
				/*	CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
					EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appId);
					bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/		
					
						if(dtTm!=null && dtTm!="null" && dtTm.length()>0){
							numCheck = isNumber(dtTm);
						}

						System.out.println("dtTm : "+dtTm);
				/*	bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
					bizPerfCounter.requestQueue(); */
					  
					

					//tmUtil.convertTime(arg0, arg1, arg2, arg3)
					
					if("".equals(dtTm)){
						tmZone = info.getTimeZone();
					}else{
						if(numCheck){
							info = tmUtil.convertTime(2, dtTm, ctryCd, postCd);
							convertTm = info.getDateTime().substring(0, 8);
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
