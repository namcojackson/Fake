package oracle.apps.e580.itt.workbench;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import parts.common.EZDPStringItem;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;
import canon.apps.common.CanonConstants;
import canon.apps.common.CanonS21SessionValidate;
import com.canon.cusa.s21.api.NLZ.NLZC405001.*;

import business.parts.NLZC405001PMsg;

import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;




public class CanonE580DeliveryConfirmationApi {
	public String checkNull(HttpServletRequest req, String str) {
		String s = "";

		String tmp = req.getParameter(str);
		if (tmp != null) {
			logMsg(str + ": " + tmp);
			if (tmp.trim().length() > 0) {
				s = tmp.trim();
			}

		}
		return s;

	}

	public BigDecimal checkNull(HttpServletRequest req, BigDecimal str) {
		BigDecimal s = null;

		if (req.getParameter(str.toString()) != null) {
			BigDecimal tmp = new BigDecimal(req.getParameter(str.toString()));
			s = tmp;
		} else
			s = BigDecimal.ZERO;
		return s;

	}
	
	public void logMsg(String str) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		System.out.println("[e580: eCarrier API ] ["
				+ sdf.format(Calendar.getInstance().getTime()) + "] : " + str);
	}
	
	private void logMsg(EZDPStringItem mdseCd) {
		
		System.out.println("Error Code:"+mdseCd);
		// TODO Auto-generated method stub
		
	}
	
	
	public NLZC405001PMsg getMsg(HttpServletRequest request) {
		NLZC405001PMsg  pmsg = new NLZC405001PMsg();	
		
		return pmsg;

	}
	
public String[] createServicerequest(HttpServletRequest request) {
		
		SimpleDateFormat format = new SimpleDateFormat("z");		
		SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		String timezone = format.format(new Date());
		String invokeTimestamp = lsDateFmt.format(new Date()); 
		String loginUser= CanonS21SessionValidate.getUserName();		

		
		//Initialize S21DB-Carrier (It should be done. It leads NullPointer exception when didn't initialize.)
		//These contents are used as a default data in S21-Standard table. (Update user, time, time-zone, program id, company code)
		EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
		EZDDBCICarrier.setProgID("S21EXTN_E580");

		String[] resArr = new String[4];

		NLZC405001 s21Api=new NLZC405001();
		
		String [] line_numbers=request.getParameterValues("line_number");
		String order_number=request.getParameter("Order");
		lsDateFmt = new SimpleDateFormat("yyyyMMdd");
		String currentDate = lsDateFmt.format(new Date()); 
		String [] delivery_scheduled_dates=request.getParameterValues("delivery_scheduled_date");
		String [] reason_codes=request.getParameterValues("reason_code");
		
		for(int line=0;line<line_numbers.length;line++)
		{
			String reason_code=reason_codes[line];
			String delivery_scheduled_date=delivery_scheduled_dates[line];
			
			if("DELIVERED".equalsIgnoreCase(reason_code)||"NOT DELIVERED".equalsIgnoreCase(reason_code))
			{
			NLZC405001PMsg pmsg = getMsg(request);
			
			pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
			pmsg.slsDt.setValue(currentDate);
			pmsg.inbdOtbdCd.setValue("2");	
			pmsg.cpoOrdNum.setValue(order_number);//Order Number
			String line_number=line_numbers[line];
			int dotIndexInLine=line_number.indexOf(".");
			String cpoDtlLineNum=line_number.substring(0, dotIndexInLine);
			String cpoDtlLineSubNum=line_number.substring(dotIndexInLine+1,line_number.length());
			pmsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
			pmsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);
			//pmsg.trxHdrNum.setValue("SH037308");		
			//pmsg.trxLineNum.setValue("001");
			//pmsg.proNum.setValue("101");
			if("DELIVERED".equalsIgnoreCase(reason_code))
			{
			pmsg.carrRsnCd.setValue("00");
			pmsg.carrCmntTxt.setValue("Delivered");
			}
			else if("NOT DELIVERED".equalsIgnoreCase(reason_code))
			{
				pmsg.carrRsnCd.setValue("01");	
				pmsg.carrCmntTxt.setValue("Not Delivered");
			}
			pmsg.updUsrId.setValue(loginUser);
			//String xxRqstDtTimestamp = lsDateFmt.format(new Date(System.currentTimeMillis()));
			pmsg.updTs.setValue(invokeTimestamp);	
			String formateddelivery_scheduled_date="";
			String []splitedDeliverySchDt=delivery_scheduled_date.split("/");
			formateddelivery_scheduled_date=formateddelivery_scheduled_date+splitedDeliverySchDt[2]+splitedDeliverySchDt[0]+splitedDeliverySchDt[1];
			System.out.println(" formateddelivery_scheduled_date:"+formateddelivery_scheduled_date);
			pmsg.xxRqstDt.setValue(formateddelivery_scheduled_date);	
			
			logMsg("glblCmpyCd        :" + pmsg.glblCmpyCd.getValue());
			logMsg("slsDt             :" + pmsg.slsDt.getValue());
			logMsg("inbdOtbdCd             :" + pmsg.inbdOtbdCd.getValue());
			logMsg("cpoOrdNum         :" + pmsg.cpoOrdNum.getValue());
			logMsg("cpoDtlLineNum     :" + pmsg.cpoDtlLineNum.getValue());
			logMsg("cpoDtlLineSubNum  :" + pmsg.cpoDtlLineSubNum.getValue());
			logMsg("carrRsnCd  		  :" + pmsg.carrRsnCd.getValue());
			logMsg("carrCmntTxt  	  :" + pmsg.carrCmntTxt.getValue());
			logMsg("updTs   		  :" + pmsg.updTs.getValue());
			logMsg("updUsrId    	  :" + pmsg.updUsrId.getValue());
			logMsg("trxHdrNum    	  :" + pmsg.trxHdrNum.getValue());
			logMsg("trxLineNum    	  :" + pmsg.trxLineNum.getValue());
			
			logMsg("Before execute  ");
			try {
				
		        System.out.println("####################### DeliveryConfirmationApi API called #############");
				s21Api.execute(pmsg,ONBATCH_TYPE.ONLINE); // execute API
				if (!S21ApiUtil.isXxMsgId(pmsg)) {
					// There is no message id, so can do commit the transaction.
					logMsg(" No error, before commit");
					EZDConnectionMgr.getInstance().commit(); 
					// commit	
					logMsg("After commit"); 
					resArr[0] = "Success";					
				}
				else {
					StringBuffer sb = new StringBuffer("");
					// Error Case - S21API set some error message id when got any
					// error while S21API's function.
					List<String> msgList = S21ApiUtil.getXxMsgIdList(pmsg);
					for (String msg : msgList) {
						logMsg("ERROR MESSAGE : "
								+ S21MessageFunc.clspGetMessage(msg));					
						sb.append("ERROR : " + "\n");
					}
					//List<String> errList= S21ApiUtil.getXxMsgIdList(pmsg.xxErrItemList.no(0));
					
									
					
					
					resArr[0] = "ERROR : ";
					resArr[1] = sb.toString();
					// If S21API got error, roll-back the transaction.
					EZDConnectionMgr.getInstance().rollback();
					// roll-back
					// throw new
					// S21AbendException("S21AbendException is thrown...");
				}						
				
			} catch (Exception e) {
				logMsg("ERROR MESSAGE : " + e.getMessage());
				e.printStackTrace();
			} finally { // Release DB resource (Close DB Connection)
				EZDConnectionMgr.getInstance().releaseResource();
			}
			}
		}
				
	//	pmsg.trxHdrNum.setValue("SHM48836");
	//	pmsg.trxLineNum.setValue("006");
	//	pmsg.proNum.setValue("PRO1");
			
		
		
		return resArr;
}



}
