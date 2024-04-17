package com.canon.apps.servreq.util;

import com.canon.cusa.s21.framework.log.S21SyslogInfoContext;
import com.canon.cusa.s21.framework.log.S21SyslogInfoContextHolder;
import parts.dbcommon.EZDDBCICarrier;
import parts.common.EZDBizPerformanceCounter;
import parts.common.EZDStringUtil;
import java.util.Date;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21SyslogInfoFactory;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class CanonE307ServiceReqAPILogUtil {

	public EZDBizPerformanceCounter getBizPerformanceCounter(String userName, String timeRegion, String appId, String pageId,  String appPageId){
		
		String glblcmpycode = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
		//Set global company code for inserting ABED_LOG table
		S21SyslogInfoFactory.setGlblCmpyCd(glblcmpycode);
		
		// Online initialize for S21
		EZDDBCICarrier.initOnline(userName, EZDStringUtil.getCurrentDate(), timeRegion, glblcmpycode);
		EZDDBCICarrier.setProgID(appId);
		
		EZDBizPerformanceCounter bizPerfCounter = new EZDBizPerformanceCounter();
		// This info can be obtained from Filter function
		S21SyslogInfoContext context = (S21SyslogInfoContext) S21SyslogInfoContextHolder.getInstance().getContext();
		// Operation Date (yyyyMMddHHmmssSSS)
		bizPerfCounter.setOperationDate(EZDStringUtil.getCurrentDate()); 		// OPS_TS	VARCHAR2(17 CHAR)
		// User ID
		bizPerfCounter.setUserId(userName);										// OPS_USR_ID	VARCHAR2(16 CHAR)
		// Host Name
		bizPerfCounter.setHostName(context.getHostName());						// ONL_HOST_NM	VARCHAR2(30 CHAR)
		// JVM(Server) Name
		bizPerfCounter.setJvmName(context.getJvnName());						// JVM_NM	VARCHAR2(20 CHAR)
		// Business ID
		bizPerfCounter.setBizId(appId); 									// BIZ_ID	VARCHAR2(10 CHAR)
		// Screen ID
		bizPerfCounter.setScreenId(pageId); 							// SCR_ID	VARCHAR2(14 CHAR)
		// Event Name (e.i. Initilization, Search, Save, Submit, Delete etc.)
		bizPerfCounter.setScreenAplId(appPageId);						// SCR_APP_ID	VARCHAR2(64 CHAR)
		
		// Timer Start
		//bizPerfCounter.setBizStartTime(EZDStringUtil.getCurrentDate());			// BIZ_START_TS	 VARCHAR2(17 CHAR)
			// Event process (e.i. Initilization, Search, Save, Submit, Delete etc.)
			// [START Event]
		
		return bizPerfCounter;
		
	}
	
	
	
}
