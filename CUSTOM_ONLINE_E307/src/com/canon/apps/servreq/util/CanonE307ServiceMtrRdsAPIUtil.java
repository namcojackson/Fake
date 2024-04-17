package com.canon.apps.servreq.util;

import java.math.BigDecimal;
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
import parts.dbcommon.EZDDBCICarrier;
import business.parts.NSZC005001PMsg;
import business.parts.NSZC010001PMsg;
import business.parts.NSZC010001_APMsg;
import business.parts.NSZC010001_APMsgArray;
import business.parts.NSZC010001_xxMsgIdListPMsg;
import business.parts.NSZC107001PMsg;
import canon.apps.common.CanonConstants;

import com.canon.apps.servreq.beans.CanonE307MeterErrorCodesBean;
import com.canon.apps.servreq.beans.CanonE307SvcMtrRdsRec;
import com.canon.apps.servreq.dao.CanonE307DebriefDetailsDAO;
import com.canon.common.CanonCommonUtil;
import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.api.NSZ.NSZC107001.NSZC107001;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

//import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;

public class CanonE307ServiceMtrRdsAPIUtil {

	private   String clsName="CanonE307ServiceMtrRdsAPIUtil";
	StringBuffer sbParams;
	
    CanonCommonUtil util;   
	
	public CanonE307ServiceMtrRdsAPIUtil() {
		util= new CanonCommonUtil();
		sbParams = new StringBuffer("");
	}
	
	public   NSZC010001PMsg  getMsg(HttpServletRequest req, String mtrFlg, String warnSkipFlg, String meterReadDate) throws ParseException{

		NSZC010001PMsg pMsg = new NSZC010001PMsg();

		 String mtrGrpCd="S";
		 String mtrSrcTpCd="SVC";
		 
		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmm");
		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		 String sSvcDt=sdfDt.format(Calendar.getInstance().getTime()); 
		

		 pMsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
		 String userId    = checkNull(req,"userName");
	
		 String mtrSz = checkNull(req, "mtrRdsSz");
		 int mtrCnt = 0;
		 if(!("".equals(mtrSz))&&!("null".equals(mtrSz))){
			 mtrCnt=Integer.parseInt(mtrSz);
		 }
		 
		 sbParams.append("\n"+"glblCmpyCd" + "= "+CanonConstants.CSA_COMPANY_CODE);
		 sbParams.append("\n"+"mtrCnt" + " = "+mtrCnt);
		 sbParams.append("\n"+"userName" + " = "+userId);
		 sbParams.append("\n"+"Future Svc Dt" + " = "+sSvcDt);
		 sbParams.append("\n"+"Task Rcv Dt" + " = "+sSysDt);
		 sbParams.append("\n"+"Task Rcv Tm" + " = "+sSysTm);
		 
		 
		 pMsg.slsDt.setValue(sSysDt);
		 pMsg.mtrReadSrcTpCd.setValue(mtrSrcTpCd);
		 
		 pMsg.dsMtrReadTpGrpCd.setValue(mtrGrpCd);
		 if("I".equals(mtrFlg)){
			 pMsg.dsTestCopyClsCd.setValue("I");

			 
		 }else{
			 pMsg.dsTestCopyClsCd.setValue("O");
		 }
		 
		 String fsr    = checkNull(req,"fsr");
		 if(fsr.length()>0) pMsg.fsrNum.setValue(fsr);
		 String svcTaskNum    = checkNull(req,"debriefTsk");
		 if(svcTaskNum.length()>0) pMsg.svcTaskNum.setValue(svcTaskNum);
		 String svcMachMstrPk = checkNull(req, "svcMachMstrPk");
		 if(svcMachMstrPk.length()>0) pMsg.svcMachMstrPk.setValue(new BigDecimal(svcMachMstrPk.trim()));

		 
		 if(mtrCnt>0){
			 for(int i=0;i<mtrCnt;i++){
				
				String svcPhysMtrPk = checkNull(req, "svcPhysMtrPk"+i);
				
				//pMsg.A.no(i).mtrReadDt.setValue(sSysDt);
				pMsg.A.no(i).rgtnMtrDt.setValue(sSysDt);
				pMsg.A.no(i).rgtnUsrId.setValue(userId);
				if("I".equals(mtrFlg)){
					
					 pMsg.A.no(i).mtrReadDt.setValue(meterReadDate);
					 String inRdTp = checkNull(req, "inRdTp"+i);
					 String failCnt =  checkNull(req, "mtrFailCount");
					 int cntFail = 0;
					 if(failCnt!=null && failCnt.length()>0){
						 cntFail = Integer.parseInt(failCnt);
					 }
					 
				/*	 if(cntFail>=3){
						 inRdTp = "ADJ";
					 }*/
					 System.out.println(" inRdTp: "+inRdTp+" warnSkipFlg: "+warnSkipFlg);

				 	 if(("RO".equals(inRdTp)) || ("EXCH_TO".equals(inRdTp)) ||  (cntFail>=3) || ("Y".equals(warnSkipFlg))){
				 		 pMsg.xxWrnSkipFlg.setValue("Y");
					 }
				 	 
					 if(inRdTp.length()>0) //pMsg.dsMtrReadTpCd.setValue(inRdTp);
					 pMsg.A.no(i).dsMtrReadTpCd.setValue(inRdTp);
					 String inMtrRd = checkNull(req, "inMtrRd"+i);
					 if(inMtrRd.length()>0) pMsg.A.no(i).readMtrCnt.setValue(new BigDecimal(inMtrRd.trim()));
					 if(i==0){
						 if(inRdTp.length()>0) pMsg.dsMtrReadTpCd.setValue(inRdTp);
					 }
				 }else{
					
					 pMsg.A.no(i).mtrReadDt.setValue(meterReadDate);
					 String outRdTp = checkNull(req, "outRdTp"+i);
					 String failCnt =  checkNull(req, "mtrFailCount");
					 System.out.println("failCnt: "+ failCnt+" outRdTp: "+outRdTp);
					 int cntFail = 0;
					 if(failCnt!=null && failCnt.length()>0){
						 cntFail = Integer.parseInt(failCnt);
					 }
					/* if(cntFail>=3){
						 outRdTp = "ADJ";
					 } */
			 
					 if(("RO".equals(outRdTp)) || ("EXCH_TO".equals(outRdTp)) || (cntFail>=3) || ("Y".equals(warnSkipFlg))){
				 		 pMsg.xxWrnSkipFlg.setValue("Y");
					 } 
					 if(outRdTp.length()>0) //pMsg.dsMtrReadTpCd.setValue(outRdTp);
					 pMsg.A.no(i).dsMtrReadTpCd.setValue(outRdTp);
					 
					 String outMtrRd = checkNull(req, "outMtrRd"+i);
					 if(outMtrRd.length()>0) pMsg.A.no(i).readMtrCnt.setValue(new BigDecimal(outMtrRd.trim()));		
					 if(i==0){
						 if(outRdTp.length()>0) pMsg.dsMtrReadTpCd.setValue(outRdTp);
					 }
				 }
				if(svcPhysMtrPk.length()>0) pMsg.A.no(i).svcPhysMtrPk.setValue(new BigDecimal(svcPhysMtrPk.trim()));
				pMsg.A.no(i).vldMtrFlg.setValue("Y");
			   
			 }
			 pMsg.A.setValidCount(mtrCnt);
		 }
	 
		
		return pMsg;
	}
	public String[] submitMtrRds(HttpServletRequest req, String mtrFlg, String meterReadDate) throws ParseException{

		String res[] = new String[2];
		
		res = submitMtrRds(req, mtrFlg, "","", meterReadDate);
		
		return res;
	}
	
	
	public String[] submitMtrRds(HttpServletRequest req, String mtrFlg, String taskNumber, String warnSkipFlg, String meterReadDate) throws ParseException{
		String res[] = new String[2];
		String csaCompanyCode = "ADB";
		String userName = checkNull(req,"userName");
		SimpleDateFormat format = new SimpleDateFormat("z");
        SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timezone = format.format(new Date());
        String invokeTimestamp = lsDateFmt.format(new Date());
        EZDDBCICarrier.initOnline(userName, invokeTimestamp, timezone, csaCompanyCode);
        EZDDBCICarrier.setProgID("EXTNE307");
		
		NSZC010001 s21Api = new NSZC010001();
		StringBuffer sb=new StringBuffer("");
		
		//Create instance of message
		NSZC010001PMsg pMsg = new NSZC010001PMsg();

	
		
		util.logMsg(false,clsName+".submitMtrRds", "Before execute");
		try{
			String fsrNum =req.getParameter("fsr")==null?"":req.getParameter("fsr");
			//String taskNumber = req.getParameter("crctnTaskNumber")==null?"":req.getParameter("crctnTaskNumber");
			String svcMachMstrPk = req.getParameter("svcMachMstrPk")==null?"":req.getParameter("svcMachMstrPk");
			String invalidData = req.getParameter("invalidData")==null?"":req.getParameter("invalidData");
			String meterDate = req.getParameter("curMtrDate0")==null?"":req.getParameter("curMtrDate0");
			util.logMsg(false,clsName+".submitMtrRds", "invalidData: "+ invalidData);
			 String failCnt =  checkNull(req, "mtrRdFailCnt");
			 int cntFail = 0;
			 if(failCnt!=null && failCnt.length()>0){
				 cntFail = Integer.parseInt(failCnt);
			 }
			

			ArrayList rdLst = new ArrayList();
			String invaldGrpSeq = "";
			if("".equals(mtrFlg)){
				CanonE307DebriefDetailsDAO objDeb = new CanonE307DebriefDetailsDAO();
				if(invalidData!=null && invalidData!="" && invalidData.length()>0 && cntFail>=3){
					meterDate = util.formatDateString(util.DT_FORMAT5, util.DT_FORMAT3, meterDate);
					//rdLst = objDeb.getInvalidAllSvcMtrDet(invalidData, svcMachMstrPk, meterDate);
					invaldGrpSeq = objDeb.getMtrInvGrpSeq(invalidData, svcMachMstrPk, meterDate);
				}
			}
			
			
			if("".equals(mtrFlg)){
				pMsg = getCrctnMtrMsg(req, taskNumber, warnSkipFlg);
			}else{
				pMsg = getMsg(req, mtrFlg, warnSkipFlg, meterReadDate);
			}
			
			util.logMsg(false,clsName+".submitMtrRds", " Before Meter Read API Params....../n"+ pMsg.toString());

			String timeRegion="EST";
			String appId="EXTNE307";
			String pageId="MeterReads";
			String appPageId="EXTNE307_MeterReads";
			
		/*	CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
			EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appPageId);
			bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/
	
			s21Api.execute(pMsg, ONBATCH_TYPE.ONLINE);
			
			util.logMsg(false,clsName+".submitMtrRds", "After execute");
		
		/*	bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
			bizPerfCounter.requestQueue();		*/
			
			if (!S21ApiUtil.isXxMsgId(pMsg)) {
				
				// There is no message id, so can do commit the transaction.
				res[0]="Y";
				res[1]=pMsg.getReturnCode();
				// commit	
				util.logMsg(false,clsName+".submitMtrRds", "Meter Reads Success");
				//EZDConnectionMgr.getInstance().commit();
				if("".equals(mtrFlg)){
					
					EZDConnectionMgr.getInstance().commit();
					
					
					util.logMsg(false,clsName+".submitMtrRds", "Meter Reads Success invalidData: "+ invalidData);
					
					if(invaldGrpSeq!=null && invaldGrpSeq.length()>0 && invalidData!=null && invalidData!="" && invalidData.length()>0){
						try{
							util.logMsg(false,clsName+".submitMtrRds", "Meter Reads Success invalidData: "+ invalidData);
							String res1[] = invalidateHigherMtrRds(fsrNum, taskNumber, svcMachMstrPk, mtrFlg, userName, invaldGrpSeq);
						}catch (Exception ex) {
							 util.logMsg(true,clsName+".invalidateHigherMtrRds", ex.getMessage());
						}
					}else{
					
						try{
							String res1[] = invalidMeterReads(fsrNum, taskNumber, svcMachMstrPk, "", userName);
						}catch (Exception ex) {
							 util.logMsg(true,clsName+".invalidMeterReads", ex.getMessage());
						}
					}
					
					try{
						String res2[] = sendTaskAPI(taskNumber);
					}catch (Exception ex) {
						 util.logMsg(true,clsName+".sendTaskAPI Exception: ", ex.getMessage());
					}
				}

			} else {
				// Error Case - S21API set some error message id when got any error while S21API's function.		
				List<String> err = S21ApiUtil.getXxMsgIdList(pMsg);
				String validFlg = "Y";
				String errorMessage="";
				res[0]="ME";
				
				for (String msg : err) {
					
					util.logMsg(true,clsName+".Meter Reads", "Meter Reads Error :"+msg);
					sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
					CanonE307DebriefDetailsDAO objDeb = new CanonE307DebriefDetailsDAO();
					ArrayList errLst = new ArrayList();
					System.out.println("mtrFlg: "+ mtrFlg+" S21 Error COde "+msg);
					
					if("".equals(mtrFlg)){
						  errLst = objDeb.getMeterErrorCodes("CORRECTION", msg);
					}else{
						  errLst = objDeb.getMeterErrorCodes("DEBRIEF", msg);
					}
					
					if(errLst!=null && errLst.size()>0){
					  for(int i=0;i<errLst.size();i++){
						  CanonE307MeterErrorCodesBean errBean = (CanonE307MeterErrorCodesBean)errLst.get(i);
						  String mtrErrCd = errBean.getStrMtrErrCd();
						  String mtrErrMsg = util.checkNull(errBean.getStrMtrErrMsg());
						  System.out.println("mtrErrCd: "+ mtrErrCd+ "mtrErrMsg: "+mtrErrMsg);
						  if("Y".equals(errBean.getStrMtrSoftWarnFlg())){
							  validFlg="Y";
						  }else if(mtrErrMsg!=null && mtrErrMsg.length()>0){
							  System.out.println("Inside else "+mtrErrMsg);
								  if("".equals(errorMessage) || "".equals(errorMessage)){
									  errorMessage = msg+" "+mtrErrMsg;
								  }else{
									  errorMessage = errorMessage+". "+msg+" "+mtrErrMsg;
								  }
								  System.out.println("errorMessage1:"+ errorMessage);
						  }else{
								  if("".equals(errorMessage) || "".equals(errorMessage)){
									  errorMessage = S21MessageFunc.clspGetMessage(msg)+"\n";
								  }else{
									  errorMessage =errorMessage+". "+S21MessageFunc.clspGetMessage(msg)+"\n"; 
								  }
							  }
					  }
					}else{
						 if("".equals(errorMessage) || "".equals(errorMessage)){
							  errorMessage = S21MessageFunc.clspGetMessage(msg)+"\n";
						  }else{
							  errorMessage =errorMessage+". "+S21MessageFunc.clspGetMessage(msg)+"\n"; 
						  }
						 System.out.println("else errorMessage2:"+ errorMessage);
					}

				}	
				
				
				 System.out.println("Meter Reads erroMessage: "+errorMessage+" validFlg: "+validFlg);
			        if(errorMessage!=null && errorMessage.length()>0){
			        	res[1] =  errorMessage;
			        	System.out.println("Meter Reads erroMessagebefoe roll back : "+errorMessage+" validFlg: "+validFlg);
			        	// If S21API got error, roll-back the transaction.
						EZDConnectionMgr.getInstance().rollback(); 
						// roll-back		
						// throw new S21AbendException("S21AbendException is thrown...");
			        }else{
			        	res = submitMtrRds(req, mtrFlg, taskNumber,"Y", meterReadDate);
			        }
				//res[0]="ME";
				//res[1]=sb.toString();
				res[1]=errorMessage;
				
			}
		
		}catch (Exception e) {
			util.logMsg(true,clsName+".submitMtrRds", "Error while setting meter read bean: "+ e.getMessage());	
			res[0]="ME";
				try{
					String str = e.getMessage();
					Pattern p = Pattern.compile("msg=.*");
	
					Matcher m = p.matcher(str);

					if (m.find()) {
						res[1] =  m.group(0).trim();
					}else{
						res[1] = e.getMessage();
					}

				}catch (Exception ex) {
					util.logMsg(true,clsName+".debriefTask", "Error while parsing error message: "+ ex.getMessage());	
				}
				EZDConnectionMgr.getInstance().rollback();
		}
		
		return res;
	}
	public String[] invalidMeterReads(String fsrNum, String taskNumber, String svcMachMstrPk, String mtrFlg, String userName){
		String res[] = new String[2];
		CanonE307DebriefDetailsDAO objDeb = new CanonE307DebriefDetailsDAO();
		ArrayList rdLst = objDeb.getExistSvcMtrDet(fsrNum, taskNumber, svcMachMstrPk, mtrFlg);
		if(rdLst!=null && rdLst.size()>0){

			NSZC010001 s21Api = new NSZC010001();
			StringBuffer sb=new StringBuffer("");
			
			//Create instance of message
			NSZC010001PMsg pMsg = new NSZC010001PMsg();

			
			util.logMsg(false,clsName+".invalidMeterReads", "Before execute");
			try{
				
				pMsg = getReadsMsg(rdLst, mtrFlg, userName, fsrNum, taskNumber, svcMachMstrPk);
				
				util.logMsg(false,clsName+".invalidMeterReads", " Before Meter Read API Params....../n"+ pMsg.toString());
				
				

				String timeRegion="EST";
				String appId="EXTNE307";
				String pageId="MeterReads";
				String appPageId="EXTNE307_MeterReads";	
				
				
				s21Api.execute(pMsg, ONBATCH_TYPE.ONLINE);
				
				util.logMsg(false,clsName+".invalidMeterReads", "After execute");
				
				/*	bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
					bizPerfCounter.requestQueue();		*/
					
					if (!S21ApiUtil.isXxMsgId(pMsg)) {
						
						// There is no message id, so can do commit the transaction.
						EZDConnectionMgr.getInstance().commit();
						res[0]="Y";
						res[1]=pMsg.getReturnCode();
						// commit	
						util.logMsg(false,clsName+".invalidMeterReads", "Meter Reads Ret Val :"+ pMsg.getReturnCode());
						
		
					} else {
							// Error Case - S21API set some error message id when got any error while S21API's function.		
							List<String> err = S21ApiUtil.getXxMsgIdList(pMsg);
							for (String msg : err) {
								util.logMsg(true,clsName+".Meter invalidMeterReads", "Invalid Meter Reads Error :"+msg);
								sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
							}		
							res[0]="IR";
							res[1]=sb.toString();
							// If S21API got error, roll-back the transaction.
								EZDConnectionMgr.getInstance().rollback(); 
							// roll-back		
							// throw new S21AbendException("S21AbendException is thrown...");
					}
				
				}catch (Exception e) {
					util.logMsg(true,clsName+".invalidMeterReads", "Error while setting meter read bean: "+ e.getMessage());	
					res[0]="ME";
						try{
							String str = e.getMessage();
							Pattern p = Pattern.compile("msg=.*");
			
							Matcher m = p.matcher(str);

							if (m.find()) {
								res[1] =  m.group(0).trim();
							}else{
								res[1] = e.getMessage();
							}

						}catch (Exception ex) {
							util.logMsg(true,clsName+".debriefTask", "Error while parsing error message: "+ ex.getMessage());	
						}
						EZDConnectionMgr.getInstance().rollback();
				}


		}
		return res;
	}
	public   NSZC010001PMsg  getReadsMsg(ArrayList rdLst, String mtrFlg, String userName, String fsrNum, String taskNumber, String svcMachMstrPk) throws ParseException{

		NSZC010001PMsg pMsg = new NSZC010001PMsg();

		 String mtrGrpCd="S";
		 String mtrSrcTpCd="SVC";
		 
		 System.out.println(" getReadsMsg mtrFlg: "+mtrFlg+" userName: "+userName+" fsrNum: "+fsrNum+" taskNumber: "+taskNumber+" svcMachMstrPk: "+svcMachMstrPk);
		 
		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmm");
		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		 String sSvcDt=sdfDt.format(Calendar.getInstance().getTime()); 
		

		 pMsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
		 String userId    = userName;
	

		 int mtrCnt = rdLst.size();
				 
		 sbParams.append("\n"+"glblCmpyCd" + "= "+CanonConstants.CSA_COMPANY_CODE);
		 sbParams.append("\n"+"mtrCnt" + " = "+mtrCnt);
		 sbParams.append("\n"+"userName" + " = "+userId);
		 sbParams.append("\n"+"Future Svc Dt" + " = "+sSvcDt);
		 sbParams.append("\n"+"Task Rcv Dt" + " = "+sSysDt);
		 sbParams.append("\n"+"Task Rcv Tm" + " = "+sSysTm);
		 
		 
		 pMsg.slsDt.setValue(sSysDt);
		 pMsg.mtrReadSrcTpCd.setValue(mtrSrcTpCd);
		 
		 pMsg.dsMtrReadTpGrpCd.setValue(mtrGrpCd);
		
		
		 
		 String fsr    = fsrNum;
		 if(fsr.length()>0) pMsg.fsrNum.setValue(fsr);
		 String svcTaskNum    = taskNumber;
		 if(svcTaskNum.length()>0) pMsg.svcTaskNum.setValue(svcTaskNum);
		
		 if(svcMachMstrPk.length()>0) pMsg.svcMachMstrPk.setValue(new BigDecimal(svcMachMstrPk.trim()));

		 
		 if(mtrCnt>0){
			 for(int i=0;i<mtrCnt;i++){
				 CanonE307SvcMtrRdsRec rdObj = (CanonE307SvcMtrRdsRec)rdLst.get(i);	 

				 System.out.println("CanonE307SvcMtrRdsRec bean : "+rdObj.toString());
				 
				String svcPhysMtrPk = rdObj.getSvcPhysMtrPk();
				
				 if("I".equals(mtrFlg) || ("IN".equals(mtrFlg))){
					 pMsg.dsTestCopyClsCd.setValue("I");
				 }else{
					 pMsg.dsTestCopyClsCd.setValue("O");
				 }
				
				mtrFlg = rdObj.getAttribute2();

				 if("I".equals(mtrFlg) || ("IN".equals(mtrFlg))){
					 pMsg.dsTestCopyClsCd.setValue("I");
				 }else{
					 pMsg.dsTestCopyClsCd.setValue("O");
				 }
				String mtrDate = rdObj.getAttribute4();
				System.out.println("getReadsMsg mtrDate: "+mtrDate);
				//mtrDate = util.formatDateString(util.DT_FORMAT5, util.DT_FORMAT3, mtrDate);
				pMsg.A.no(i).mtrReadDt.setValue(mtrDate);
				pMsg.A.no(i).rgtnMtrDt.setValue(sSysDt);
				pMsg.A.no(i).rgtnUsrId.setValue(userId);
				//System.out.println("Before setting mtrEntryCmntTxt: "+rdObj.getMtrEntryCmntTxt());
				String mtrEntryCmntTxt = checkStrNull(rdObj.getMtrEntryCmntTxt()==null?"":rdObj.getMtrEntryCmntTxt());
				if(mtrEntryCmntTxt.length()>0) pMsg.A.no(i).mtrEntryCmntTxt.setValue(mtrEntryCmntTxt);
				
				if("I".equals(mtrFlg) || ("IN".equals(mtrFlg))){
					String inRdTp = rdObj.getAttribute3();

					 System.out.println(" inRdTp: "+inRdTp);
					 if(i==0){
						 if(inRdTp.length()>0) pMsg.dsMtrReadTpCd.setValue(inRdTp);
					 }
					 String inMtrRd = rdObj.getInMtrRdCnt();
					 if(inMtrRd.length()>0) pMsg.A.no(i).readMtrCnt.setValue(new BigDecimal(inMtrRd.trim()));
					 if(inRdTp.length()>0) pMsg.A.no(i).dsMtrReadTpCd.setValue(inRdTp);
						 
				 }else{
					 String outRdTp = rdObj.getDsMtrRdTpCd();
					
					 //System.out.println(" outRdTp: "+outRdTp);
					 if(i==0){
						 if(outRdTp.length()>0) pMsg.dsMtrReadTpCd.setValue(outRdTp);	
					 }
					 String outMtrRd = rdObj.getOutMtrRdCnt();
					 if(outMtrRd.length()>0) pMsg.A.no(i).readMtrCnt.setValue(new BigDecimal(outMtrRd.trim()));
					 if(outRdTp.length()>0) pMsg.A.no(i).dsMtrReadTpCd.setValue(outRdTp);
				 }
				
				 String svcPhysMtrRead = rdObj.getAttribute1();
				 pMsg.A.no(i).svcPhysMtrReadPk.setValue(new BigDecimal(svcPhysMtrRead.trim()));
				if(svcPhysMtrPk.length()>0) pMsg.A.no(i).svcPhysMtrPk.setValue(new BigDecimal(svcPhysMtrPk.trim()));
				pMsg.A.no(i).vldMtrFlg.setValue("N");
			   
			 }
			 pMsg.A.setValidCount(mtrCnt);
		 }
	 
		
		return pMsg;
	}
	public   NSZC010001PMsg  getCrctnMtrMsg(HttpServletRequest req, String svcTaskNumber, String warnSkipFlg) throws ParseException{

		NSZC010001PMsg pMsg = new NSZC010001PMsg();
		CanonE307DebriefDetailsDAO obj = new CanonE307DebriefDetailsDAO();

		 String mtrGrpCd="S";
		 String mtrSrcTpCd="SVC";
		 
		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmm");
		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		 String sSvcDt=sdfDt.format(Calendar.getInstance().getTime()); 
		

		 pMsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
		 String userId    = checkNull(req,"userName");
	
		 String mtrSz = checkNull(req, "mtrRdsSz");
		 int mtrCnt = 0;
		 if(!("".equals(mtrSz))&&!("null".equals(mtrSz))){
			 mtrCnt=Integer.parseInt(mtrSz);
		 }
		 
		 sbParams.append("\n"+"glblCmpyCd" + "= "+CanonConstants.CSA_COMPANY_CODE);
		 sbParams.append("\n"+"mtrCnt" + " = "+mtrCnt);
		 sbParams.append("\n"+"userName" + " = "+userId);
		 sbParams.append("\n"+"Future Svc Dt" + " = "+sSvcDt);
		 sbParams.append("\n"+"Task Rcv Dt" + " = "+sSysDt);
		 sbParams.append("\n"+"Task Rcv Tm" + " = "+sSysTm);
		 
		 
		 pMsg.slsDt.setValue(sSysDt);
		 pMsg.mtrReadSrcTpCd.setValue(mtrSrcTpCd);
		 
		 pMsg.dsMtrReadTpGrpCd.setValue(mtrGrpCd);
		 
/*		 if("I".equals(mtrFlg)){
			 pMsg.dsTestCopyClsCd.setValue("I");
		 }else{
			 pMsg.dsTestCopyClsCd.setValue("O");
		 } */
		 
		 String fsr    = checkNull(req,"fsr");
		 if(fsr.length()>0) pMsg.fsrNum.setValue(fsr);
		 String svcTaskNum  = svcTaskNumber ; //checkNull(req,"crctnTaskNumber");
		 if(svcTaskNum.length()>0) pMsg.svcTaskNum.setValue(svcTaskNum);
		 String svcMachMstrPk = checkNull(req, "svcMachMstrPk");
		 if(svcMachMstrPk.length()>0) pMsg.svcMachMstrPk.setValue(new BigDecimal(svcMachMstrPk.trim()));

		 
		 if(mtrCnt>0){
			 for(int i=0;i<mtrCnt;i++){
				
				String svcPhysMtrPk = checkNull(req, "svcPhysMtrPk"+i);

				
				String curMtrDate = checkNull(req, "curMtrDate"+i);
				String parseCurMtrDate = util.formatDateString(util.DT_FORMAT5, util.DT_FORMAT3, curMtrDate);
				pMsg.A.no(i).mtrReadDt.setValue(parseCurMtrDate);
				pMsg.A.no(i).rgtnMtrDt.setValue(sSysDt);
				pMsg.A.no(i).rgtnUsrId.setValue(userId);

					 String mtrRdTp = checkNull(req, "mtrRdTp"+i);
				//	 String failCnt =  checkNull(req, "mtrFailCount");
					 System.out.println(" mtrRdTp: "+mtrRdTp);
					/* if(("RO".equals(mtrRdTp)) || ("EXCH_TO".equals(mtrRdTp))){
				 		 pMsg.xxWrnSkipFlg.setValue("Y");
					 }	*/
					 String failCnt =  checkNull(req, "mtrRdFailCnt");
					 String errorFlg =  checkNull(req, "errorFlg");
					 
					 System.out.println("Correction meter failCnt: "+ failCnt+" inRdTp: "+mtrRdTp);
					 int cntFail = 0;
					 if(failCnt!=null && failCnt.length()>0){
						 cntFail = Integer.parseInt(failCnt);
					 }
					 
					/* if(failCnt!=null && failCnt.indexOf("3")>=0){
						 mtrRdTp = "ADJ";
					 }*/
					 if(cntFail>=3){
						 mtrRdTp = "ADJ";
						 if("Y".equals(errorFlg)){
							 pMsg.xxWrnSkipFlg.setValue("Y");
						 }
						 
					 }
					 
				 	 if(("RO".equals(mtrRdTp)) || ("EXCH_TO".equals(mtrRdTp))){
				 		 pMsg.xxWrnSkipFlg.setValue("Y");
					 }
				 	 if("Y".equals(warnSkipFlg)){
				 		pMsg.xxWrnSkipFlg.setValue("Y");
				 		 mtrRdTp = "ADJ";
				 	 }
				 	 
					 if(mtrRdTp.length()>0) //pMsg.dsMtrReadTpCd.setValue(outRdTp);
					 pMsg.A.no(i).dsMtrReadTpCd.setValue(mtrRdTp);
					 
					 if(i==0){
						 if(mtrRdTp.length()>0) pMsg.dsMtrReadTpCd.setValue(mtrRdTp);
					 }
					 pMsg.dsTestCopyClsCd.setValue("I");
					 
					 String mtrRd = checkNull(req, "mtrRd"+i);
					 if(mtrRd.length()>0){
						 pMsg.A.no(i).readMtrCnt.setValue(new BigDecimal(mtrRd.trim()));						 
					 }else{
						 mtrRd = checkStrNull(obj.getMtrLatestRead(svcMachMstrPk.trim(), svcPhysMtrPk.trim()));
						 if(mtrRd.length()>0) pMsg.A.no(i).readMtrCnt.setValue(new BigDecimal(mtrRd.trim()));
					 }

				if(svcPhysMtrPk.length()>0) pMsg.A.no(i).svcPhysMtrPk.setValue(new BigDecimal(svcPhysMtrPk.trim()));
				pMsg.A.no(i).vldMtrFlg.setValue("Y");
			   
			 }
			 pMsg.A.setValidCount(mtrCnt);
		 }
		 
		return pMsg;
	}
	public String[] sendTaskAPI(String svcTaskNum){

		String res[] = new String[2];
		
		NSZC107001 taskApi = new NSZC107001();
		NSZC107001PMsg pMsg = new NSZC107001PMsg();
		try{
			SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");

			String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
			pMsg.glblCmpyCd.setValue("ADB");
			pMsg.svcTaskNum.setValue(svcTaskNum);
			pMsg.slsDt.setValue(sSysDt);
			pMsg.forceSendTaskFlg.setValue("Y");
			
			util.logMsg(false,clsName+".sendTaskAPI", "Before execute svcTaskNum: "+svcTaskNum+" sSysDt: "+sSysDt);
			
			taskApi.execute(pMsg, ONBATCH_TYPE.ONLINE);
			
			util.logMsg(false,clsName+".sendTaskAPI", "After execute");
			
			try {
				
				// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
				
					if (!S21ApiUtil.isXxMsgId(pMsg)) {
						
						// There is no message id, so can do commit the transaction.
						EZDConnectionMgr.getInstance().commit();
						res[0]="Y";
						res[1]=pMsg.getReturnCode();
	
					} else {
						StringBuffer sb=new StringBuffer("");
							// Error Case - S21API set some error message id when got any error while S21API's function.		
							List<String> err = S21ApiUtil.getXxMsgIdList(pMsg);
							for (String msg : err) {
								util.logMsg(true,clsName+".sendTaskAPI", " Error :"+msg);
								sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
							}		
							res[0]="E";
							res[1]=sb.toString();
							// If S21API got error, roll-back the transaction.
								EZDConnectionMgr.getInstance().rollback(); 
					}
				} catch (Exception e) {
					util.logMsg(true,clsName+".sendTaskAPI", "Error while calling sendTaskAPI : "+ e.getMessage());
				} finally {	// Release DB resource (Close DB Connection)
					EZDConnectionMgr.getInstance().releaseResource();
				} 
		}catch (Exception e) {
			util.logMsg(true,clsName+".sendTaskAPI", "Error while setting Task Update API: "+ e.getMessage());	
			res[0]="E";
				try{
					String str = e.getMessage();
					Pattern p = Pattern.compile("msg=.*");
	
					Matcher m = p.matcher(str);
	
					if (m.find()) {
						res[1] =  m.group(0).trim();
					}else{
						res[1] = e.getMessage();
					}
	
				}catch (Exception ex) {
					util.logMsg(true,clsName+".sendTaskAPI", "Error while parsing error message: "+ ex.getMessage());	
				}
				EZDConnectionMgr.getInstance().rollback();
		}
		
		return res;
	}
	public String[] invalidateHigherMtrRds(String fsrNum, String taskNumber, String svcMachMstrPk, String mtrFlg, String userName, String invldMtrGrpSeq){
		String res[] = new String[2];
		CanonE307DebriefDetailsDAO objDeb = new CanonE307DebriefDetailsDAO();
		ArrayList rdLst = new ArrayList();
		if(invldMtrGrpSeq!=null && invldMtrGrpSeq.length()>0){

	        String[] arrOfStr = invldMtrGrpSeq.split(",", -1); 

	        for (String aSeq : arrOfStr) {
	        	System.out.println("invalidateHigherMtrRds aSeq "+ aSeq+" svcMachMstrPk: "+svcMachMstrPk);
	        	rdLst = 	objDeb.getInvalidSvcMtrSeq(aSeq, svcMachMstrPk);
	        	if(rdLst!=null && rdLst.size()>0){
			NSZC010001 s21Api = new NSZC010001();
			StringBuffer sb=new StringBuffer("");
			
			//Create instance of message
			NSZC010001PMsg pMsg = new NSZC010001PMsg();

			 
			util.logMsg(false,clsName+".invalidateHigherMtrRds", "Before API execute fsrNum: "+fsrNum+" taskNumber: "+taskNumber+" svcMachMstrPk: "+svcMachMstrPk);
			try{
				
				pMsg = getInvldReadsMsg(rdLst, mtrFlg, userName, fsrNum, taskNumber, svcMachMstrPk);
				
				util.logMsg(false,clsName+".invalidateHigherMtrRds", " Before Meter Read API Params....../n"+ pMsg.toString());
				
				

				String timeRegion="EST";
				String appId="EXTNE307";
				String pageId="MeterReads";
				String appPageId="EXTNE307_MeterReads";	
				
				
				s21Api.execute(pMsg, ONBATCH_TYPE.ONLINE);
				
				util.logMsg(false,clsName+".invalidateHigherMtrRds", "After execute");
				
				/*	bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
					bizPerfCounter.requestQueue();		*/
					
					if (!S21ApiUtil.isXxMsgId(pMsg)) {
						
						// There is no message id, so can do commit the transaction.
						EZDConnectionMgr.getInstance().commit();
						res[0]="Y";
						res[1]=pMsg.getReturnCode();
						// commit	
						util.logMsg(false,clsName+".invalidateHigherMtrRds", "Meter Reads Ret Val :"+ pMsg.getReturnCode());
						
		
					} else {
							// Error Case - S21API set some error message id when got any error while S21API's function.		
							List<String> err = S21ApiUtil.getXxMsgIdList(pMsg);
							for (String msg : err) {
								util.logMsg(true,clsName+".Meter invalidateHigherMtrRds", "Invalid Meter Reads Error :"+msg);
								sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
							}		
							res[0]="IR";
							res[1]=sb.toString();
							// If S21API got error, roll-back the transaction.
								EZDConnectionMgr.getInstance().rollback(); 
							// roll-back		
							// throw new S21AbendException("S21AbendException is thrown...");
					}
				
				}catch (Exception e) {
					util.logMsg(true,clsName+".invalidateHigherMtrRds", "Error while setting meter read bean: "+ e.getMessage());	
					res[0]="ME";
						try{
							String str = e.getMessage();
							Pattern p = Pattern.compile("msg=.*");
			
							Matcher m = p.matcher(str);

							if (m.find()) {
								res[1] =  m.group(0).trim();
							}else{
								res[1] = e.getMessage();
							}

						}catch (Exception ex) {
							util.logMsg(true,clsName+".invalidateHigherMtrRds", "Error while parsing error message: "+ ex.getMessage());	
						}
						EZDConnectionMgr.getInstance().rollback();
				}
	        }

	        }
		}
		return res;
	}
	public   NSZC010001PMsg  getInvldReadsMsg(ArrayList rdLst, String mtrFlg, String userName, String fsrNum, String taskNumber, String svcMachMstrPk) throws ParseException{

		NSZC010001PMsg pMsg = new NSZC010001PMsg();

		 String mtrGrpCd="S";
		 String mtrSrcTpCd="SVC";
		 
		 System.out.println(" getInvldReadsMsg mtrFlg: "+mtrFlg+" userName: "+userName+" fsrNum: "+fsrNum+" taskNumber: "+taskNumber+" svcMachMstrPk: "+svcMachMstrPk);
		 
		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmm");
		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		 String sSvcDt=sdfDt.format(Calendar.getInstance().getTime()); 
		

		 pMsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
		 String userId    = userName;
	

		 int mtrCnt = rdLst.size();
				 
		 sbParams.append("\n"+"glblCmpyCd" + "= "+CanonConstants.CSA_COMPANY_CODE);
		 sbParams.append("\n"+"mtrCnt" + " = "+mtrCnt);
		 sbParams.append("\n"+"userName" + " = "+userId);
		 sbParams.append("\n"+"Future Svc Dt" + " = "+sSvcDt);
		 sbParams.append("\n"+"Task Rcv Dt" + " = "+sSysDt);
		 sbParams.append("\n"+"Task Rcv Tm" + " = "+sSysTm);
		 
		 
		 pMsg.slsDt.setValue(sSysDt);
		 pMsg.mtrReadSrcTpCd.setValue(mtrSrcTpCd);
		 
		 pMsg.dsMtrReadTpGrpCd.setValue(mtrGrpCd);
		
		
		 
		 String fsr    = fsrNum;
		 if(fsr.length()>0) pMsg.fsrNum.setValue(fsr);
		 String svcTaskNum    = taskNumber;
		 if(svcTaskNum.length()>0) pMsg.svcTaskNum.setValue(svcTaskNum);
		
		 if(svcMachMstrPk.length()>0) pMsg.svcMachMstrPk.setValue(new BigDecimal(svcMachMstrPk.trim()));

		 
		 if(mtrCnt>0){
			 for(int i=0;i<mtrCnt;i++){
				 CanonE307SvcMtrRdsRec rdObj = (CanonE307SvcMtrRdsRec)rdLst.get(i);	 

				 System.out.println("CanonE307SvcMtrRdsRec bean : "+rdObj.toString());
				 
				String svcPhysMtrPk = rdObj.getSvcPhysMtrPk();
				
				mtrFlg = rdObj.getAttribute2();

				 if("I".equals(mtrFlg) || ("IN".equals(mtrFlg))){
					 pMsg.dsTestCopyClsCd.setValue("I");
				 }else{
					 pMsg.dsTestCopyClsCd.setValue("O");
				 }
				 String mtrDate = rdObj.getAttribute4();
				 mtrDate = util.formatDateString(util.DT_FORMAT5, util.DT_FORMAT3, mtrDate);
				 System.out.println("invalidate Meter Date: "+mtrDate+" sSysDt: "+sSysDt);
				pMsg.A.no(i).mtrReadDt.setValue(mtrDate);
				pMsg.A.no(i).rgtnMtrDt.setValue(sSysDt);
				pMsg.A.no(i).rgtnUsrId.setValue(userId);
				//System.out.println(" Before mtrEntryCmntTxt: "+rdObj.getMtrEntryCmntTxt());
				String mtrEntryCmntTxt = checkStrNull(rdObj.getMtrEntryCmntTxt());
				if(mtrEntryCmntTxt.length()>0) pMsg.A.no(i).mtrEntryCmntTxt.setValue(mtrEntryCmntTxt);
				if("I".equals(mtrFlg) || ("IN".equals(mtrFlg))){
					String inRdTp = rdObj.getAttribute3();

					// System.out.println(" inRdTp: "+inRdTp);
					 if(i==0){
						 if(inRdTp.length()>0) pMsg.dsMtrReadTpCd.setValue(inRdTp);
					 }
					 String inMtrRd = rdObj.getInMtrRdCnt();
					 if(inMtrRd.length()>0) pMsg.A.no(i).readMtrCnt.setValue(new BigDecimal(inMtrRd.trim()));
					 if(inRdTp.length()>0) pMsg.A.no(i).dsMtrReadTpCd.setValue(inRdTp);
						 
				 }else{
					 String outRdTp = rdObj.getAttribute3();
					
					 //System.out.println(" outRdTp: "+outRdTp);
					 if(i==0){
						 if(outRdTp.length()>0) pMsg.dsMtrReadTpCd.setValue(outRdTp);	
					 }
					 String outMtrRd = rdObj.getInMtrRdCnt();
					 if(outMtrRd.length()>0) pMsg.A.no(i).readMtrCnt.setValue(new BigDecimal(outMtrRd.trim()));
					 if(outRdTp.length()>0) pMsg.A.no(i).dsMtrReadTpCd.setValue(outRdTp);
				 }
				
				 String svcPhysMtrRead = rdObj.getAttribute1();
				 pMsg.A.no(i).svcPhysMtrReadPk.setValue(new BigDecimal(svcPhysMtrRead.trim()));
				if(svcPhysMtrPk.length()>0) pMsg.A.no(i).svcPhysMtrPk.setValue(new BigDecimal(svcPhysMtrPk.trim()));
				pMsg.A.no(i).vldMtrFlg.setValue("N");
			   
			 }
			// System.out.println(" mtrCnt: "+mtrCnt);
			 pMsg.A.setValidCount(mtrCnt);
		 }
	 
		
		return pMsg;
	}
	public  String checkNull(HttpServletRequest req, String str ){
	     String s="";
		String tmp =  req.getParameter(str)==null?"":req.getParameter(str);
		if (tmp != null) {
			if(tmp.trim().length()>0){
			  s=tmp.trim();
			  sbParams.append("\n" +str +" = "+s );
			  util.logMsg(false, clsName+".Meter Reads", str +" = "+s);
			}
		}
		return s;  
	 }	
	  public  String checkStrNull(String str){
			if ("null".equals(str) || str == null) {
				return "";
			}else {
				return str;
			} 
	  }  

}
