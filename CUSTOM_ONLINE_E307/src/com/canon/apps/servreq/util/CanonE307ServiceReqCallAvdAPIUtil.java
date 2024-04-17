package com.canon.apps.servreq.util;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import parts.common.EZDBizPerformanceCounter;
import parts.common.EZDStringUtil;
import parts.dbcommon.EZDConnectionMgr;
import business.parts.NSZC044001PMsg;
import canon.apps.common.CanonConstants;

import com.canon.apps.servreq.dao.CanonE307ServiceReqCreateDao;
import com.canon.common.CanonCommonUtil;
import com.canon.cusa.s21.api.NSZ.NSZC044001.NSZC044001;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

public class CanonE307ServiceReqCallAvdAPIUtil {
	 String clsName="CanonE307ServiceReqCallAvdAPIUtil";  
	 CanonCommonUtil util;
	 String  MD_CL_RESOVE="01";
	 String  MD_CL_DISP_TECH="02";
	 String  MD_CL_NEED_MORE_TM="03";
	 String  CALL_AVOID_CD="20";
	 String  MOBILE_INF_FLG="N";
	 
	 
			 
	public CanonE307ServiceReqCallAvdAPIUtil(){
			util= new CanonCommonUtil();
	}  
	
	public   NSZC044001PMsg  getMsg(HttpServletRequest req){

		 util.logMsg(false, clsName+".createCallAvdSR","CALL AVOIDANCE : in getMsg ");
		 
		 
		NSZC044001PMsg  pmsg = new NSZC044001PMsg();
		CanonE307ServiceReqCreateDao crtObj = new CanonE307ServiceReqCreateDao();

		 String csaCompanyCode = CanonConstants.CSA_COMPANY_CODE;
		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmmss");
		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		  
		 
		    pmsg.glblCmpyCd.setValue(csaCompanyCode);
	
		    String md= checkNull(req, "crSRFlg");
		    if(md.equalsIgnoreCase("R")){
		    	pmsg.xxModeCd.setValue(MD_CL_RESOVE);
		    }else if(md.equalsIgnoreCase("DT")){
		    	pmsg.xxModeCd.setValue(MD_CL_DISP_TECH);
		    }else if(md.equalsIgnoreCase("NMT")){
		    	pmsg.xxModeCd.setValue(MD_CL_NEED_MORE_TM);
		    } 
		    String userId                                           = checkNull(req,"userId");if(userId.length()>0) pmsg.userId.setValue(userId);
		    String svcMachMstrPk                                    = checkNull(req,"machPk");if(svcMachMstrPk.length()>0) pmsg.svcMachMstrPk.setValue(new BigDecimal(svcMachMstrPk));
		    String dsSvcCallTpCd                                  = checkNull(req,"sTaskType");if(dsSvcCallTpCd.length()>0) pmsg.dsSvcCallTpCd.setValue(dsSvcCallTpCd);
		   // String dsSvcCallTpCd                                    = checkNull(req,"sTaskType");if(dsSvcCallTpCd.length()>0) pmsg.dsSvcCallTpCd.setValue("PD");
	        
		    pmsg.svcTaskRcvDt.setValue(sSysDt);
			pmsg.svcTaskRcvTm.setValue(sSysTm);
			pmsg.slsDt.setValue(sSysDt); 
			
			//String svcPblmTpCd                                      = checkNull(req,"sPbCode");
			 String pCode                                      = checkNull(req,"pCode");
			 
			 String svcPblmTpCd = pCode.split("-")[0].trim();
			 System.out.println("svcPblmTpCd: "+ svcPblmTpCd+ " pCode: "+ pCode);
			if(svcPblmTpCd.length()>0) pmsg.svcPblmTpCd.setValue(svcPblmTpCd);
			
		    String svcCallSrcTpCd                                   = checkNull(req,"sCrChannel");if(svcCallSrcTpCd.length()>0) pmsg.svcCallSrcTpCd.setValue(svcCallSrcTpCd);
		    String custEmlAddr                                      = checkNull(req,"emailAddr");if(custEmlAddr.length()>0) pmsg.custEmlAddr.setValue(custEmlAddr);
		    String svcCustAttnTxt                                   = checkNull(req,"contactName");if(svcCustAttnTxt.length()>0) pmsg.svcCustAttnTxt.setValue(svcCustAttnTxt);
			String custTelNum                                       = checkNull(req,"custTelNum");if(custTelNum.length()>0) pmsg.custTelNum.setValue(custTelNum);
			String custTelExtnNum                                   = checkNull(req,"custTelExtnNum");if(custTelExtnNum.length()>0) pmsg.custTelExtnNum.setValue(custTelExtnNum);
			String svcCustCllrTxt                                   = checkNull(req,"caller");if(svcCustCllrTxt.length()>0) pmsg.svcCustCllrTxt.setValue(svcCustCllrTxt);
			String cllrPhNum										= checkNull(req,"cllrPhNum");if(cllrPhNum.length()>0) pmsg.taskList.no(0).svcCustCllrTelNum.setValue(cllrPhNum);
			String cllrPhNumExt									    = checkNull(req,"cllrPhNumExt");if(cllrPhNumExt.length()>0) pmsg.taskList.no(0).svcCustCllrTelExtnNum.setValue(cllrPhNumExt);
			String mobileNum										 = checkNull(req, "mobileNum");if(mobileNum.length()>0) pmsg.taskList.no(0).cellPhoNum.setValue(mobileNum);
			
			String custPoNum                                        = checkNull(req,"custPo");if(custPoNum.length()>0) pmsg.custPoNum.setValue(custPoNum);
			String machDownFlg                                      = checkNull(req,"mchStatus");if(machDownFlg.length()>0) pmsg.machDownFlg.setValue(machDownFlg);
			String svcBillTpCd                                      = checkNull(req,"sBillCode");if(svcBillTpCd.length()>0) pmsg.svcBillTpCd.setValue(svcBillTpCd);
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
			 
	    	
			pmsg.svcCallAvoidCd.setValue(CALL_AVOID_CD);
	        
	        pmsg.svcCallCpltTpCd.setValue("1"); // Resolution Code
	        
	        if(cllrPhNum.length()>0)pmsg.taskList.no(0).svcCustCllrTelNum.setValue(cllrPhNum);
	         
	        // task list 
	        pmsg.taskList.no(0).mblIntfcFlg.setValue(MOBILE_INF_FLG);
	        String ccaChrg 										= checkNull(req,"ccaChrg");
			 
				 if(ccaChrg.length()>0) {
					 pmsg.taskList.no(0).custAwareChrgFlg.setValue("Y");
				 }else{
					 pmsg.taskList.no(0).custAwareChrgFlg.setValue("N");
				 }
	        
			 pmsg.attachedFileList.setValidCount(1);
			 String poUpld                                  		 = checkNull(req,"poUpld");
			 if(poUpld.length()>0){
				 pmsg.attachedFileList.no(0).attDataNm.setValue(poUpld);
				 pmsg.attachedFileList.no(0).poVerFlg.setValue("Y");
			 }else{
				 pmsg.attachedFileList.no(0).poVerFlg.setValue("N");
			 }
	        
	        if(md.equalsIgnoreCase("R")){ 
	        	//pmsg.svcCallRqstOwnrTocCd.setValue("K00514"); // Territory Code
	        	
	        	pmsg.xxVisitTaskList.no(0).svcPblmTpCd.setValue(svcPblmTpCd);
	        	
	  	        
	  	        pmsg.xxVisitTaskList.setValidCount(1);
	  	        
	        }
	        /*else{
	        	pmsg.svcCallRqstOwnrTocCd.setValue("K00514"); 
	        }*/
	        
	        pmsg.taskList.setValidCount(1);
	  
	        
	        int memoCnt=0;
			 // sNoteType sNoteTypeTxt
			 String sNoteTypeTxt     = checkNull(req,"sNoteTypeTxt");
			 if(sNoteTypeTxt.length()>0) {
				 pmsg.svcMemoList.no(memoCnt).svcCmntTxt.setValue(sNoteTypeTxt);
				 String sNoteTypeCd   = checkNull(req,"sNoteType");
				 if(sNoteTypeCd.length()>0) pmsg.svcMemoList.no(memoCnt).svcMemoTpCd.setValue(sNoteTypeCd);
				 memoCnt++;
			 }
			 pmsg.svcMemoList.setValidCount(memoCnt);
	        
		
		return pmsg;
	}
	     
	
	public   NSZC044001PMsg  getDispatchTecMsg(HttpServletRequest req){

		util.logMsg(false, clsName+".createCallAvdSR","CALL AVOIDANCE : in getDispatchTecMsg ");
		
		NSZC044001PMsg  pmsg = new NSZC044001PMsg();
		CanonE307ServiceReqCreateDao crtObj = new CanonE307ServiceReqCreateDao();

		 String csaCompanyCode = CanonConstants.CSA_COMPANY_CODE;
		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmmss");
		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
		  
		 
		    pmsg.glblCmpyCd.setValue(csaCompanyCode);
	
		    String md= checkNull(req, "crSRFlg");
		    if(md.equalsIgnoreCase("R")){
		    	pmsg.xxModeCd.setValue(MD_CL_RESOVE);
		    }else if(md.equalsIgnoreCase("DT")){
		    	pmsg.xxModeCd.setValue(MD_CL_DISP_TECH);
		    }else if(md.equalsIgnoreCase("NMT")){
		    	pmsg.xxModeCd.setValue(MD_CL_NEED_MORE_TM);
		    } 
		    String userId                                           = checkNull(req,"userId");if(userId.length()>0) pmsg.userId.setValue(userId);
		    String svcMachMstrPk                                    = checkNull(req,"machPk");if(svcMachMstrPk.length()>0) pmsg.svcMachMstrPk.setValue(new BigDecimal(svcMachMstrPk));
		    String dsSvcCallTpCd                                    = checkNull(req,"sTaskType");if(dsSvcCallTpCd.length()>0) pmsg.dsSvcCallTpCd.setValue(dsSvcCallTpCd);
		    //String dsSvcCallTpCd                                    = checkNull(req,"sTaskType");if(dsSvcCallTpCd.length()>0) pmsg.dsSvcCallTpCd.setValue("PD");
	        
		    pmsg.svcTaskRcvDt.setValue(sSysDt);
			pmsg.svcTaskRcvTm.setValue(sSysTm);
			pmsg.slsDt.setValue(sSysDt); 
		    String custEmlAddr                                      = checkNull(req,"emailAddr");if(custEmlAddr.length()>0) pmsg.custEmlAddr.setValue(custEmlAddr);
		    String svcCustAttnTxt                                   = checkNull(req,"contactName");if(svcCustAttnTxt.length()>0) pmsg.svcCustAttnTxt.setValue(svcCustAttnTxt);
			String custTelNum                                       = checkNull(req,"custTelNum");if(custTelNum.length()>0) pmsg.custTelNum.setValue(custTelNum);
			String custTelExtnNum                                   = checkNull(req,"custTelExtnNum");if(custTelExtnNum.length()>0) pmsg.custTelExtnNum.setValue(custTelExtnNum);
			String svcCustCllrTxt                                   = checkNull(req,"caller");if(svcCustCllrTxt.length()>0) pmsg.svcCustCllrTxt.setValue(svcCustCllrTxt);
			String cllrPhNum										= checkNull(req,"cllrPhNum");if(cllrPhNum.length()>0) pmsg.taskList.no(0).svcCustCllrTelNum.setValue(cllrPhNum);
			String cllrPhNumExt									    = checkNull(req,"cllrPhNumExt");if(cllrPhNumExt.length()>0) pmsg.taskList.no(0).svcCustCllrTelExtnNum.setValue(cllrPhNumExt);
			String mobileNum										= checkNull(req, "mobileNum");if(mobileNum.length()>0) pmsg.taskList.no(0).cellPhoNum.setValue(mobileNum);
			
			//String svcPblmTpCd                                      = checkNull(req,"sPbCode");
			 String pCode                                      = checkNull(req,"pCode");
			 
			 String svcPblmTpCd = pCode.split("-")[0].trim();
			 System.out.println("svcPblmTpCd: "+ svcPblmTpCd+ " pCode: "+ pCode);
			if(svcPblmTpCd.length()>0) pmsg.svcPblmTpCd.setValue(svcPblmTpCd); 
			
	    	String svcCallSrcTpCd                                   = checkNull(req,"sCrChannel");if(svcCallSrcTpCd.length()>0) pmsg.svcCallSrcTpCd.setValue(svcCallSrcTpCd);
	    	
			String custPoNum                                        = checkNull(req,"custPo");if(custPoNum.length()>0) pmsg.custPoNum.setValue(custPoNum);
			String machDownFlg                                      = checkNull(req,"mchStatus");if(machDownFlg.length()>0) pmsg.machDownFlg.setValue(machDownFlg);
			String svcBillTpCd                                      = checkNull(req,"sBillCode");if(svcBillTpCd.length()>0) pmsg.svcBillTpCd.setValue(svcBillTpCd);			
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
			
	        pmsg.svcCallAvoidCd.setValue(CALL_AVOID_CD);
	        
	        pmsg.svcCallCpltTpCd.setValue("1"); // Resolution Code
	         
	        String ccaChrg 										= checkNull(req,"ccaChrg");
			 
				 if(ccaChrg.length()>0) {
					 pmsg.taskList.no(0).custAwareChrgFlg.setValue("Y");
				 }else{
					 pmsg.taskList.no(0).custAwareChrgFlg.setValue("N");
				 }   
	        
	        // task list
	        pmsg.taskList.no(0).mblIntfcFlg.setValue(MOBILE_INF_FLG);
	    	//pmsg.svcCallRqstOwnrTocCd.setValue("K00514"); // Territory Code
	        
	     
	   
	    
	        //	pmsg.taskList.no(0).techCd.setValue("Q05350");
	        	
	        	String callPrtyCd                                   = checkNull(req,"callPrtyCd");
	        	if(callPrtyCd.length()>0)
	            pmsg.taskList.no(0).svcCallPrtyCd.setValue(callPrtyCd);
	      
	        
	        pmsg.taskList.setValidCount(1);
	        
	        int memoCnt=0;
			 // sNoteType sNoteTypeTxt
			 String sNoteTypeTxt     = checkNull(req,"sNoteTypeTxt");
			 if(sNoteTypeTxt.length()>0) {
				 pmsg.svcMemoList.no(memoCnt).svcCmntTxt.setValue(sNoteTypeTxt);
				 String sNoteTypeCd   = checkNull(req,"sNoteType");
				 if(sNoteTypeCd.length()>0) pmsg.svcMemoList.no(memoCnt).svcMemoTpCd.setValue(sNoteTypeCd);
				 memoCnt++;
			 }
			 pmsg.svcMemoList.setValidCount(memoCnt);
	        

			// visit task list
			pmsg.xxVisitTaskList.no(0).svcPblmTpCd.setValue(svcPblmTpCd);
         	
	        pmsg.xxVisitTaskList.setValidCount(1);
	        
	
	        
			 pmsg.attachedFileList.setValidCount(1);
			 String poUpld                                  		 = checkNull(req,"poUpld");
			 if(poUpld.length()>0){
				 pmsg.attachedFileList.no(0).attDataNm.setValue(poUpld);
				 pmsg.attachedFileList.no(0).poVerFlg.setValue("Y");
			 }else{
				 pmsg.attachedFileList.no(0).poVerFlg.setValue("N");
			 }
	        
	
	        
	    	util.logMsg(false, clsName+".createCallAvdSR","CALL AVOIDANCE : ************ getDispatchTecMsg [PARAMS] **************\n\n\n\n");
	    	
	    	util.logMsg(false, clsName+".createCallAvdSR","CALL AVOIDANCE :"+pmsg.toString());
	    	
	    	
	    	util.logMsg(false, clsName+".createCallAvdSR","\n\n\n\nCALL AVOIDANCE : ************ getDispatchTecMsg [PARAMS] **************");
		
		return pmsg;
		
	}
	
	
	 
	public String[] createCallAvdSR(HttpServletRequest req){
		String strMsg="Success";
		
	  //Create instance of API for Call Avoidance
		
		NSZC044001  callAvdApi = new NSZC044001();
		
	  // Call Avoidance , Create instance of message
		NSZC044001PMsg  msg  = new NSZC044001PMsg();
		
	  String md= checkNull(req, "crSRFlg");
	  
	  
	    if(md.equalsIgnoreCase("R")){
	    	msg  = getMsg(req);
	    }else if(md.equalsIgnoreCase("DT")){
	    	msg  = getDispatchTecMsg(req);
	    }else if(md.equalsIgnoreCase("NMT")){
	    	msg  = getMsg(req);
	    } 
	 
				 
			  	
	  String res[] = new String[2];	
	  String userName=msg.userId.getValue();
	  String timeRegion="EST";
	  String appId="EXTNE307";
	  String pageId="CallAvoidSR";
	  String appPageId="EXTNE307_CallAvoidSR";
		
	  /*  CanonE307ServiceReqAPILogUtil apiLogUtil= new CanonE307ServiceReqAPILogUtil();
	  EZDBizPerformanceCounter bizPerfCounter = apiLogUtil.getBizPerformanceCounter(userName,timeRegion,appId,pageId,appId);
	  bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());	*/
		
	  util.logMsg(false, clsName+".createCallAvdSR","CALL AVOIDANCE : Create API Call  ");
	  util.logMsg(false, clsName+".createCallAvdSR"," CALL AVOIDANCE : MODE :  "+msg.xxModeCd.getValue());
	//  util.logMsg(false, clsName+".createCallAvdSR", "CALL AVOIDANCE [ PARAMS ] : " +" = "+msg.toString());
	  
		//execute API
	  callAvdApi.execute(msg, ONBATCH_TYPE.ONLINE);  
	  
	  util.logMsg(false, clsName+".createCallAvdSR","CALL AVOIDANCE : After execute  ");

	/*  bizPerfCounter.setBizEndTime(EZDStringUtil.getCurrentDate());	
	  bizPerfCounter.requestQueue();	*/
	  
		try {
			// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
			
				if (!S21ApiUtil.isXxMsgId(msg)) {
					// There is no message id, so can do commit the transaction.
					EZDConnectionMgr.getInstance().commit(); 
					// commit	
					res[0]="Y";
					res[1]=msg.fsrNum.getValue();
					util.logMsg(false, clsName+".createCallAvdSR","CALL AVOIDANCE : Create API Call RESPONSE : FSR NUM : "+msg.fsrNum.getValue());

				} else {
						// Error Case - S21API set some error message id when got any error while S21API's function.		
						List<String> errList = S21ApiUtil.getXxMsgIdList(msg);
						StringBuffer sb=new StringBuffer("");
						for (String err : errList) {			
						    
							util.logMsg(true, clsName+".createCallAvdSR",S21MessageFunc.clspGetMessage(err));
							
							strMsg= S21MessageFunc.clspGetMessage(err);
							sb.append(S21MessageFunc.clspGetMessage(err)+"\n");
						}		
							// If S21API got error, roll-back the transaction.
							EZDConnectionMgr.getInstance().rollback();
						    // roll-back		
							// throw new S21AbendException("S21AbendException is thrown...");
							res[0]="E";
							res[1]=sb.toString();		
				}
			} catch (Exception e) {
				
				util.logMsg(true, "[ ERROR ] "+clsName+".createCallAvdSR",e.getMessage()); 
				e.printStackTrace();
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
			  util.logMsg(false, clsName+".Call Avoidance SR", str +" = "+s);
			}
		}
		return s;  
	 }
}
