package com.canon.oracle.beans;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import parts.dbcommon.EZDConnectionMgr;
import business.parts.NSZC043001PMsg;
import canon.apps.common.CanonConstants;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

public class CanonE307ServReqTest {
	
	public String createServicerequest(String loginUser){
		String strMsg="Success";
		
		//Create instance of API 
		NSZC043001 s21Api = new NSZC043001();
		
		
		
		//Create instance of message
		NSZC043001PMsg pmsg = new NSZC043001PMsg();
		
		  String csaCompanyCode = CanonConstants.CSA_COMPANY_CODE;
		  
		  
		  
		//Set  value to the SR
				pmsg.glblCmpyCd.setValue(csaCompanyCode);    
				pmsg.xxModeCd.setValue("01");      
				pmsg.userId.setValue(loginUser); // Q04995  C12945		
		    
		        //pmsg.serNum.setValue("TC00000001");  
				pmsg.shipToCustCd.setValue("431329");
				pmsg.dsSvcCallTpCd.setValue("ZZ");
				pmsg.svcTaskTpCd.setValue("XX");
				pmsg.svcTaskRcvDt.setValue("20150219");
				pmsg.svcTaskRcvTm.setValue("192008"); 
			
				
				
				pmsg.svcCallRqstOwnrTocCd.setValue("X00111");
				pmsg.svcPblmTpCd.setValue("999");
				//pmsg.svcCallSrcTpCd.setValue("01");
				//pmsg.svcCallAvoidCd.setValue("1");
				pmsg.billToCustCd.setValue("431329");
				
				pmsg.svcMachMstrPk.setValue(1);			
		        pmsg.svcBillTpCd.setValue("8");
		        pmsg.svcCallAvoidCd.setValue("YY");
		        pmsg.svcCallSrcTpCd.setValue("XX");
				 
				
				//pmsg.xxbillToCustCd.setValue("431329");
				pmsg.taskList.no(0).svcCallPrtyCd.setValue("4");
				pmsg.taskList.setValidCount(1); 
				
				
		
	    logMsg("Before execute  ");
		//execute API
		s21Api.execute(pmsg, ONBATCH_TYPE.ONLINE);  
		
		try {
			// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
			logMsg("After execute, isXxMsgId=" + S21ApiUtil.isXxMsgId(pmsg));
			
				if (!S21ApiUtil.isXxMsgId(pmsg)) {
					// There is no message id, so can do commit the transaction.
					logMsg("No error, before commit");
					EZDConnectionMgr.getInstance().commit(); 
					// commit	
					logMsg("After commit");

				} else {
						// Error Case - S21API set some error message id when got any error while S21API's function.		
						List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
						for (String msg : err) {			
						    
							logMsg("ERROR MESSAGE : " + S21MessageFunc.clspGetMessage(msg));
							strMsg= S21MessageFunc.clspGetMessage(msg);
						}		
							// If S21API got error, roll-back the transaction.
							EZDConnectionMgr.getInstance().rollback(); 
						// roll-back		
						// throw new S21AbendException("S21AbendException is thrown...");
				}
			} catch (Exception e) {
				logMsg("ERROR MESSAGE : " + e.getMessage());
				e.printStackTrace();
			} finally {	// Release DB resource (Close DB Connection)	
				EZDConnectionMgr.getInstance().releaseResource();
			}
		return strMsg;
	}

	
    public void logMsg(String str){
    	
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		
		System.out.println("[E307] ["+sdf.format(Calendar.getInstance().getTime())+"] : "+str  );
    }
	
}
