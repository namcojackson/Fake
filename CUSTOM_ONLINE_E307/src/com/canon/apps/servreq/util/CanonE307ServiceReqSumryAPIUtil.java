package com.canon.apps.servreq.util;

import business.parts.NSZC012001PMsg;
import business.parts.NSZC045001PMsg;
import canon.apps.common.CanonConstants;

import com.canon.cusa.s21.api.NSZ.NSZC012001.NSZC012001;
import com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDBizPerformanceCounter;
import parts.common.EZDStringUtil;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;

import com.canon.common.CanonCommonUtil;

public class CanonE307ServiceReqSumryAPIUtil {
	
	StringBuffer sbParams;
	
	private   String clsName="CanonE307ServiceReqSumryAPIUtil";
	          String  MODE_CODE_ADD_TASK="01";
	          String  MODE_CODE_CANCEL_TASK="02";
	          CanonCommonUtil util;   
	          
	public CanonE307ServiceReqSumryAPIUtil() {
		util= new CanonCommonUtil();
		sbParams = new StringBuffer("");
	}
	public String[] servReqAddTask(String strFsrNum, String strDefTech, String strPrefTech, String strResType, String strFSvcDt, String strFSvcTm, String machMstrPk, String svcCallTpcd, String billTpCd, String userId, String callPrtyCd, String strTechCode, String strSlsDt, String strFtrSrvMn, String strFtrAmPm, String strTskNotes, String noteTpe, String postalCd, String countryCd){
		System.out.println("servReqAddTask strTskNotes: "+ strTskNotes+" strFsrNum: "+strFsrNum);
		String res[] = new String[2];
		NSZC045001 taskApi = new NSZC045001();
		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmmss");
		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		
		NSZC045001PMsg pmsg = new NSZC045001PMsg();
		try{
			BigDecimal bdPk = new BigDecimal(machMstrPk);
			pmsg.glblCmpyCd.setValue("ADB");
			pmsg.xxModeCd.setValue(MODE_CODE_ADD_TASK);			
			pmsg.svcMachMstrPk.setValue(bdPk);
			pmsg.fsrNum.setValue(strFsrNum);
			pmsg.bypsPrfTechFlg.setValue("N");
			pmsg.dsSvcCallTpCd.setValue(svcCallTpcd);
			pmsg.slsDt.setValue(strSlsDt);


			if(strFSvcDt.length()>0) 
				// strFSvcDt =util.formatDateString(util.DT_FORMAT4, util.DT_FORMAT3, strFSvcDt);
				strFSvcDt = util.formatDateString(util.DT_FORMAT5, util.DT_FORMAT3, strFSvcDt);
			util.logMsg(false,clsName+".servReqAddTask", "Add Task , Future Service Date :"+ strFSvcDt+" strFSvcTm : "+strFSvcTm+" strResType : "+strResType+" svcCallTpcd : "+svcCallTpcd+" billTpCd : "+billTpCd+" callPrtyCd : "+callPrtyCd+" strTechCode : "+strTechCode);
			util.logMsg(false,clsName+".servReqAddTask", " strFsrNum : "+strFsrNum+" machMstrPk: "+ machMstrPk + " strPrefTech: " +strPrefTech+" strSlsDt : "+strSlsDt);
			if(strFSvcTm.trim().length()>0) {
				String strTm = util.format24HrTm(strFSvcTm+strFtrSrvMn+" "+strFtrAmPm);
				util.logMsg(false,clsName+".servReqAddTask", " strFSvcDt : "+strFSvcDt+" strTm : "+strTm);
				strFSvcTm = strTm+"00";
				//strFSvcTm = strFSvcTm.split(":")[0]+strFSvcTm.split(":")[1]+"00";
			}
			CanonE307ServiceReqAPIUtil utilAPI = new CanonE307ServiceReqAPIUtil();
			String strExistFutureDt = strFSvcDt+strFSvcTm;
			java.util.Date sysDate = new java.util.Date();
			String strCurrentTm = utilAPI.getCurrentTm(postalCd, countryCd, new SimpleDateFormat("yyyyMMddHHmmss").format(sysDate).toString());
			System.out.println("strExistFutureDt: "+strExistFutureDt+" strCurrentTm: "+strCurrentTm);
			 
		 	DateFormat formatter=new SimpleDateFormat("yyyyMMddHHmmss");
		 	DateFormat formatter1=new SimpleDateFormat("yyyyMMddHHmmss");
	        Date currentTm =formatter1.parse(strCurrentTm);
	        Date existFutureDt=formatter.parse(strExistFutureDt);
	        System.out.println("currentTm: "+ currentTm+ "existFutureDt: "+ existFutureDt);
			 
		        if (existFutureDt.compareTo(currentTm) > 0) {
						pmsg.xxSvcTaskList.no(0).futSvcDt.setValue(strFSvcDt);
						pmsg.xxSvcTaskList.no(0).futSvcTm.setValue(strFSvcTm);
		        }else{
		        	 if(strCurrentTm!=null && strCurrentTm.length()>0){
							String strDate = strCurrentTm.substring(0, 8);
							String strCurTm = strCurrentTm.substring(8,14);
							System.out.println("Else strDate: " + strDate+" strCurTm: "+strCurTm);
							pmsg.xxSvcTaskList.no(0).futSvcDt.setValue(strDate);
							pmsg.xxSvcTaskList.no(0).futSvcTm.setValue(strCurTm);
		        	 }
		        }
			 
		    pmsg.xxTmZnConvtFlg.setValue("Y");
			pmsg.svcTaskRcvDt.setValue(sSysDt);
			pmsg.svcTaskRcvTm.setValue(sSysTm);
			pmsg.svcBillTpCd.setValue(billTpCd);
			pmsg.userId.setValue(userId);
			pmsg.xxSvcTaskList.setValidCount(1);
			pmsg.xxSvcTaskList.no(0).svcCallPrtyCd.setValue(callPrtyCd);
		
			
			if(("null".equals(strTechCode)) || ("".equals(strTechCode))){
				pmsg.xxSvcTaskList.no(0).techCd.setValue(strDefTech);
				pmsg.xxSvcTaskList.no(0).svcAsgTechCd.setValue(strDefTech);
				pmsg.xxSvcTaskList.no(0).svcAsgTpCd.setValue(strResType);
			}else{
				pmsg.xxSvcTaskList.no(0).techCd.setValue(strTechCode);
				pmsg.xxSvcTaskList.no(0).svcAsgTechCd.setValue(strTechCode);
				pmsg.xxSvcTaskList.no(0).svcAsgTpCd.setValue("30");
			}

			if(strTskNotes.length()>0 && noteTpe.length()>0){
				System.out.println("Inside tskNotes..");
				pmsg.xxSvcMemoList.no(0).svcMemoTpCd.setValue(noteTpe);
				pmsg.xxSvcMemoList.no(0).svcCmntTxt.setValue(strTskNotes);
				pmsg.xxSvcMemoList.setValidCount(1);
			}	

			String timeRegion="EST";
			String appId="EXTNE307";
			String pageId="AddTask";
			String appPageId="EXTNE307_AddTask";
				
		/*	CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
			EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userId,timeRegion,appId,pageId,appId);
			bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/		
		
			util.logMsg(false,clsName+".servReqAddTask", "Before Execute"); 	
			taskApi.execute(pmsg, ONBATCH_TYPE.ONLINE);
			util.logMsg(false,clsName+".servReqAddTask", "After Execute"); 
			
		/*	bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
			bizPerfCounter.requestQueue();		*/	
			

			// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
			if (!S21ApiUtil.isXxMsgId(pmsg)) {
				
				// There is no message id, so can do commit the transaction.
				EZDConnectionMgr.getInstance().commit();
				res[0]="Y";
				res[1]=pmsg.fsrNum.getValue();
				// commit	
				util.logMsg(false,clsName+". AddTask ", "Add Task.., FSR Number :"+ pmsg.fsrNum.getValue());
	
			} else {
				StringBuffer sb=new StringBuffer("");
					// Error Case - S21API set some error message id when got any error while S21API's function.		
					List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
					for (String msg : err) {
						util.logMsg(true,clsName+".AddTask", "Add Task Error :"+msg);
						sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
					}		
					res[0]="E";
					res[1]=sb.toString();
					// If S21API got error, roll-back the transaction.
						EZDConnectionMgr.getInstance().rollback(); 
		  }
		} catch (Exception e) {
			util.logMsg(true,clsName+".AddTask..", "Error in Add Task : "+ e.getMessage());
		} finally {	// Release DB resource (Close DB Connection)	
			EZDConnectionMgr.getInstance().releaseResource();
		}
	return res;
	}
	public String[] servReqCancelTask(String strFsrNum, String svcTskNum, String userId, String disptEmlFlg, String slsDt, String memoTpCd, String rsnTpCd, String cnclNt){
		String res[] = new String[2];
		String csaCompanyCode = "ADB";
		SimpleDateFormat format = new SimpleDateFormat("z");
        SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timezone = format.format(new Date());
        String invokeTimestamp = lsDateFmt.format(new Date());
        EZDDBCICarrier.initOnline(userId, invokeTimestamp, timezone, csaCompanyCode);
        EZDDBCICarrier.setProgID("EXTNE307");
        
		NSZC045001 taskApi = new NSZC045001();
		NSZC045001PMsg pmsg = new NSZC045001PMsg();
		util.logMsg(false,clsName+".servReqCancelTask..", " strFsrNum : "+strFsrNum+" svcTskNum: "+ svcTskNum +" userId: "+userId + " disptEmlFlg: " +disptEmlFlg+" slsDt: "+slsDt+" memoTpCd: "+memoTpCd+" rsnTpCd: "+rsnTpCd+" cnclNt: "+cnclNt);
		try{
			pmsg.glblCmpyCd.setValue("ADB");
			pmsg.xxModeCd.setValue(MODE_CODE_CANCEL_TASK);			
			pmsg.fsrNum.setValue(strFsrNum);
			pmsg.userId.setValue(userId);
			pmsg.xxSvcTaskList.no(0).svcTaskNum.setValue(svcTskNum);
			pmsg.xxSvcTaskList.no(0).schdDisptEmlFlg.setValue(disptEmlFlg);
			pmsg.slsDt.setValue(slsDt);
			pmsg.xxSvcMemoList.no(0).svcMemoTpCd.setValue(memoTpCd);
			pmsg.xxSvcMemoList.no(0).svcMemoRsnCd.setValue(rsnTpCd);
			pmsg.xxSvcMemoList.no(0).svcCmntTxt.setValue(cnclNt);
			pmsg.xxSvcMemoList.setValidCount(1);
			pmsg.xxSvcTaskList.setValidCount(1);	

			  String timeRegion="EST";
			  String appId="EXTNE307";
			  String pageId="CancelTask";
			  String appPageId="EXTNE307_CancelTask";
				
		/*	  CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
			  EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userId,timeRegion,appId,pageId,appId);
			  bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/

			  util.logMsg(false,clsName+". servReqCancelTask1 ", "Before Execute.., FSR Number :"+ strFsrNum+" Task Num : "+svcTskNum);
			  taskApi.execute(pmsg, ONBATCH_TYPE.ONLINE);
			  util.logMsg(false,clsName+". servReqCancelTask ", "After Execute.., FSR Number :"+ strFsrNum +" svcTskNum: "+svcTskNum);
			  
		/*	  bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
			  bizPerfCounter.requestQueue();	*/		

			// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
			if (!S21ApiUtil.isXxMsgId(pmsg)) {
				
				// There is no message id, so can do commit the transaction.
				EZDConnectionMgr.getInstance().commit();
				res[0]="Y";
				res[1]=pmsg.fsrNum.getValue();
				// commit	
				util.logMsg(false,clsName+". CancelTask ", "Cancel Task.., FSR Number :"+ pmsg.fsrNum.getValue());
	
			} else {
				StringBuffer sb=new StringBuffer("");
					// Error Case - S21API set some error message id when got any error while S21API's function.		
					List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
					for (String msg : err) {
						util.logMsg(true,clsName+".CancelTask", "CancelTask Error :"+msg);
						sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
					}		
					res[0]="E";
					res[1]=sb.toString();
					// If S21API got error, roll-back the transaction.
						EZDConnectionMgr.getInstance().rollback(); 
		  }
		} catch (Exception e) {
			util.logMsg(true,clsName+".CancelTask..", "Error in Cancel Task : "+ e.getMessage());
		} finally {	// Release DB resource (Close DB Connection)	
			EZDConnectionMgr.getInstance().releaseResource();
		}
	return res;
	}	
	public String[] servReqCancelTaskRsn(String strFsrNum, String svcTskNum, String userId, String disptEmlFlg, String slsDt){
		String res[] = new String[2];
		NSZC045001 taskApi = new NSZC045001();
		NSZC045001PMsg pmsg = new NSZC045001PMsg();
		util.logMsg(false,clsName+".servReqCancelTask..", " strFsrNum : "+strFsrNum+" svcTskNum: "+ svcTskNum +" userId: "+userId + " disptEmlFlg: " +disptEmlFlg+" slsDt: "+slsDt);
		try{
			pmsg.glblCmpyCd.setValue("ADB");
			pmsg.xxModeCd.setValue(MODE_CODE_CANCEL_TASK);			
			pmsg.fsrNum.setValue(strFsrNum);
			pmsg.userId.setValue(userId);
			pmsg.xxSvcTaskList.no(0).svcTaskNum.setValue(svcTskNum);
		//	pmsg.xxSvcTaskList.no(0).svcCallPrtyCd.setValue(callPrtyCd);
			pmsg.xxSvcTaskList.no(0).schdDisptEmlFlg.setValue(disptEmlFlg);
			pmsg.slsDt.setValue(slsDt);
			pmsg.xxSvcTaskList.setValidCount(1);	
			
			String timeRegion="EST";
			String appId="EXTNE307";
			String pageId="CancelReason";
			String appPageId="EXTNE307_CancelReason";
				
		/*	CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
			EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userId,timeRegion,appId,pageId,appId);
			bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());		*/	

			util.logMsg(false,clsName+". servReqCancelTask ", "Before Execute.., FSR Number :"+ strFsrNum+" Task Num : "+svcTskNum);
			taskApi.execute(pmsg, ONBATCH_TYPE.ONLINE);
			util.logMsg(false,clsName+". servReqCancelTask ", "After Execute.., FSR Number :"+ strFsrNum +" svcTskNum: "+svcTskNum);
			
		/*	bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
			bizPerfCounter.requestQueue();		*/	

			// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
			if (!S21ApiUtil.isXxMsgId(pmsg)) {
				
				// There is no message id, so can do commit the transaction.
				EZDConnectionMgr.getInstance().commit();
				res[0]="Y";
				res[1]=pmsg.fsrNum.getValue();
				// commit	
				util.logMsg(false,clsName+". CancelTask ", "Cancel Task.., FSR Number :"+ pmsg.fsrNum.getValue());
	
			} else {
				StringBuffer sb=new StringBuffer("");
					// Error Case - S21API set some error message id when got any error while S21API's function.		
					List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
					for (String msg : err) {
						util.logMsg(true,clsName+".CancelTask", "CancelTask Error :"+msg);
						sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
					}		
					res[0]="E";
					res[1]=sb.toString();
					// If S21API got error, roll-back the transaction.
						EZDConnectionMgr.getInstance().rollback(); 
		  }
		} catch (Exception e) {
			util.logMsg(true,clsName+".CancelTask..", "Error in Cancel Task : "+ e.getMessage());
		} finally {	// Release DB resource (Close DB Connection)	
			EZDConnectionMgr.getInstance().releaseResource();
		}
	return res;
	}	
	public String getTimeZone(String postCd, String ctryCd){
		String tmZn ="";
		try{
			if(!("null".equals(postCd)) && !("".equals(postCd))){
				postCd = postCd.substring(0, 5);
			}
			ZYPLocalTimeUtil tmUtil = new ZYPLocalTimeUtil();
			tmZn = tmUtil.getTZId(ctryCd, postCd);

			util.logMsg(false,clsName+".getTimeZone..", "tmZn : "+ tmZn +" postCd: "+postCd + " ctryCd: " +ctryCd);
		} catch (Exception e) {
			util.logMsg(true,clsName+".getTimeZone..", "Error timezone : "+ e.getMessage());
		} finally {	// Release DB resource (Close DB Connection)	
			EZDConnectionMgr.getInstance().releaseResource();
		}
		return tmZn;
	}
	public String getTmZone(String postCd, String ctryCd, String dtTm){
		CanonCommonUtil util = new CanonCommonUtil();
		String tmZone ="";
		String convertTm="";
		boolean numCheck =false;
		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmmss");
		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		 util.logMsg(false,clsName+".getTimeZone..","postCd : "+postCd+" ctryCd : "+ctryCd+" dtTm: "+dtTm);
		 util.logMsg(false,clsName+".getTimeZone..","sSysDt : "+sSysDt+" sSysTm : "+sSysTm);
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
					convertTm = info.getDateTime().substring(0, 12);
					System.out.println("tmZone: " + convertTm);
					tmZone = util.formatDateTime(convertTm);
					System.out.println("convertTm : "+ tmZone);
					if(tmZone!=null && tmZone.length()>0){
						String strDate = tmZone.substring(0, 11);
						String strHr = tmZone.substring(12,14);
						String strMin = tmZone.substring(15,17);
						String strAmPm = tmZone.substring(18,20);
						System.out.println("length : "+tmZone.length()+" strDate: "+strDate+" strHr : "+strHr+" strMin: "+strMin+" strAmPm: "+strAmPm);
					}
				}
			}
			
			//String time = info.getDateTime();
			util.logMsg(false,clsName+".getTimeZone..", "tmzone : "+ tmZone +" postCd: "+postCd + " ctryCd: " +ctryCd+" time : "+dtTm);
		} catch (Exception e) {
			util.logMsg(true,clsName+".getTmZone..", "Error timezone : "+ e.getMessage());
		} finally {	// Release DB resource (Close DB Connection)	
			EZDConnectionMgr.getInstance().releaseResource();
		}
		return tmZone;
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

