package com.canon.apps.servreq.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import parts.common.EZDBizPerformanceCounter;
import parts.common.EZDStringUtil;
import parts.dbcommon.EZDConnectionMgr;
import business.parts.NSZC070001PMsg;
import canon.apps.common.CanonConstants;

import com.canon.common.CanonCommonUtil;
import com.canon.cusa.s21.api.NSZ.NSZC070001.NSZC070001;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

public class CanonE307ServiceReqInsCheckAPIUtil {
	
	StringBuffer sbParams;
	
	
	private   String clsName="CanonE307ServiceReqInsCheckAPIUtil";
    String  MODE_CODE_DEBRIEF="D";
    String  MODE_CODE_CHARGES="C";
    String  INST_VERIFY_CODE="I";
    CanonCommonUtil util;   
	          
	public CanonE307ServiceReqInsCheckAPIUtil() {
		util= new CanonCommonUtil();
		sbParams = new StringBuffer("");
	}

	public  NSZC070001PMsg  getMsg(HttpServletRequest req){
		
		 NSZC070001PMsg pmsg = new NSZC070001PMsg();
			try{
				 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
				 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmm");
				 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
				 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
				 String sSvcDt=sdfDt.format(Calendar.getInstance().getTime()); 
				   
				 // Mandatory Params
				 pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
				 String userId    = checkNull(req,"userName");
				// String modeCode    = checkNull(req,"modeCode");
				 pmsg.xxModeCd.setValue(INST_VERIFY_CODE);
				 pmsg.slsDt.setValue(sSysDt);
				 if(userId.length()>0) pmsg.usrId.setValue(userId);
				 
				 sbParams.append("\n"+"glblCmpyCd" + "= "+CanonConstants.CSA_COMPANY_CODE);
				 sbParams.append("\n"+"xxModeCd" + " = "+MODE_CODE_CHARGES);
				 sbParams.append("\n"+"userName" + " = "+userId);
				 sbParams.append("\n"+"Future Svc Dt" + " = "+sSvcDt);
				 sbParams.append("\n"+"Task Rcv Dt" + " = "+sSysDt);
				 sbParams.append("\n"+"Task Rcv Tm" + " = "+sSysTm);
				 
				 String strRecSize = checkNull(req,"chkLstSz");
				 System.out.println("strRecSize : "+ strRecSize);
				 int recSz = 0;
				 if(!("".equals(strRecSize))&&!("null".equals(strRecSize))){
					 recSz=Integer.parseInt(strRecSize);
				 }				 
				 
				 String srvRqst           	= checkNull(req,"fsrNum");if(srvRqst.length()>0) pmsg.fsrNum.setValue(srvRqst);
				 String tskNum           	= checkNull(req,"tskNum");if(tskNum.length()>0) pmsg.svcTaskNum.setValue(tskNum);
				 String svcMachMstrPk   	= checkNull(req,"svcMachMstr");if(svcMachMstrPk.length()>0) pmsg.svcMachMstrPk.setValue(new BigDecimal(svcMachMstrPk.trim()));
				 String chkInstComp 		= checkNull(req,"chkInstComp");
				

				 if(chkInstComp.length()>0)
					 pmsg.istlCpltFlg.setValue("Y");
				 else
					 pmsg.istlCpltFlg.setValue("N");
				 
				 for(int i=0;i<recSz;i++){
					String chkLstPk =  checkNull(req,"chkLstPk"+i);if(chkLstPk.length()>0) pmsg.xxFsrIstlChkList.no(i).fsrIstlChkListPk.setValue(new BigDecimal(chkLstPk.trim()));
					System.out.println(" chkLstPk: "+ chkLstPk);
					if(srvRqst.length()>0) pmsg.xxFsrIstlChkList.no(i).fsrNum.setValue(srvRqst);
					if(tskNum.length()>0) pmsg.xxFsrIstlChkList.no(i).svcTaskNum.setValue(tskNum);
					String svcCnfgMstr = checkNull(req,"svcCnfgMstr"+i);
					if(svcCnfgMstr.length()>0) pmsg.xxFsrIstlChkList.no(i).svcConfigMstrPk.setValue(new BigDecimal(svcCnfgMstr.trim()));
					String mdseCd = checkNull(req,"mdseCd"+i);if(mdseCd.length()>0) pmsg.xxFsrIstlChkList.no(i).mdseCd.setValue(mdseCd);
					String serNum = checkNull(req,"serNum"+i);if(serNum.length()>0) pmsg.xxFsrIstlChkList.no(i).serNum.setValue(serNum);
					String modelNm = checkNull(req,"modelNm"+i);if(modelNm.length()>0) pmsg.xxFsrIstlChkList.no(i).mdlNm.setValue(modelNm);
					String modelId = checkNull(req,"modelId"+i);if(modelId.length()>0) pmsg.xxFsrIstlChkList.no(i).mdlId.setValue(new BigDecimal(modelId.trim()));
					String crctdSrl = checkNull(req,"crctdSrl"+i);if(crctdSrl.length()>0) pmsg.xxFsrIstlChkList.no(i).crctSerNum.setValue(crctdSrl);
					String verfdChk 			=checkNull(req, "verfdChk"+i);
					if(verfdChk.length()>0) 
						pmsg.xxFsrIstlChkList.no(i).istlChkVerFlg.setValue("Y");
					else
						pmsg.xxFsrIstlChkList.no(i).istlChkVerFlg.setValue("N");
				 }
				 
				 
				 if(recSz>0)
					 pmsg.xxFsrIstlChkList.setValidCount(recSz);
		}catch(Exception e){
			util.logMsg(false, clsName+".saveChargesDetails", "Exception in Getting Message :"+e.getMessage());
		}
			return pmsg;
		
	}
	public String[] saveInstVerDet(HttpServletRequest req){
		
		//Create instance of API 
		int tskCnt=1;
		String res[] = new String[2];
		NSZC070001 s21Api = new NSZC070001();
		//Create instance of message
		try {
				StringBuffer sb=new StringBuffer("");
				NSZC070001PMsg pmsg = new NSZC070001PMsg();
				pmsg = getMsg(req);		
				
				String userName=pmsg.usrId.getValue();
				String timeRegion="EST";
				String appId="EXTNE307";
				String pageId="InstallChecklist";
				String appPageId="EXTNE307_InstallChecklist";
					
			/*	CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
				EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appId);
				bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());			*/	
			
				util.logMsg(false,clsName+".saveInstVerFlag", "################## Calling Install CheckList API #######################");
				util.logMsg(false,clsName+".saveInstVerFlag", " Before Install CheckList API Execute.......");
				util.logMsg(false,clsName+".saveInstVerFlag", " Before Install CheckList API Params....../n"+ pmsg.toString());
				//execute API
			    s21Api.execute(pmsg, ONBATCH_TYPE.ONLINE);  
				
				 util.logMsg(false,clsName+".saveInstVerFlag", " After Install CheckList API Execute.......");
				 
		/*		bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
				bizPerfCounter.requestQueue();		*/		 
				  
				// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
				
					if (!S21ApiUtil.isXxMsgId(pmsg)) {
						
						// There is no message id, so can do commit the transaction.
						EZDConnectionMgr.getInstance().commit();
						res[0]="Y";
						res[1]=pmsg.fsrNum.getValue();
						// commit	
						util.logMsg(false,clsName+".saveInstVerFlag", "Install CheckList , FSR Number :"+ pmsg.fsrNum.getValue());
	
					} else {
							// Error Case - S21API set some error message id when got any error while S21API's function.		
							List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
							for (String msg : err) {
								util.logMsg(true,clsName+".saveInstVerFlag", "Install CheckList Error :"+msg);
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
				util.logMsg(true,clsName+".saveInstVerFlag", "Error in Install CheckList : "+ e.getMessage());
				res[0]="E";
				res[1]="Exception thrown from NSZC070001 API";
			} finally {	// Release DB resource (Close DB Connection)	
				EZDConnectionMgr.getInstance().releaseResource();
			}
		return res;
	}
	public  String checkNull(HttpServletRequest req, String str ){
	    String s="";
		String tmp =  req.getParameter(str)==null?"":req.getParameter(str);
		if (tmp != null) {
			if(tmp.trim().length()>0){
			  s=tmp.trim();
			  sbParams.append("\n" +str +" = "+s );
			}
		}
		return s;  
	}
}
