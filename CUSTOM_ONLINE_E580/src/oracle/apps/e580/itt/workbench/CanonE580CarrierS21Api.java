package oracle.apps.e580.itt.workbench;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletRequest;

import com.canon.cusa.s21.api.NLZ.NLZC405001.NLZC405001;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21AbendException;

import business.parts.NLZC405001PMsg;
import canon.apps.common.CanonConstants;
import canon.apps.common.CanonS21SessionValidate;

public class CanonE580CarrierS21Api implements CanonE580ITTWorkbenchS21Api {
	final static String VALIDATION_ERR_MSG="Delivery Confirmation API: A validation error occurred";

	final CanonE580ITTWorkbenchCallback<Object> s21ApiCallBack;

	public CanonE580CarrierS21Api(CanonE580ITTWorkbenchCallback<Object> cb) {
		this.s21ApiCallBack = cb;
	}

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void logMsg(String str) {
		logMsg(str,System.out);
	}
	
	public static void logMsg(String str, PrintStream ps) {
		ps.println("[e580: eCarrier API ] [" + sdf.format(Calendar.getInstance().getTime()) + "] : " + str);
	}

	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

	private String today() {
		return fmt.format(new Date());
	}

	public String[] createServicerequest(HttpServletRequest request) {

		SimpleDateFormat format = new SimpleDateFormat("z");
		SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String timezone = format.format(new Date());
		String invokeTimestamp = lsDateFmt.format(new Date());
		String loginUser = CanonS21SessionValidate.getUserName();

		// Initialize S21DB-Carrier (It should be done. It leads NullPointer exception
		// when didn't initialize.)
		// These contents are used as a default data in S21-Standard table. (Update
		// user, time, time-zone, program id, company code)
		// EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone,
		// CanonConstants.CSA_COMPANY_CODE);
		// EZDDBCICarrier.setProgID("S21EXTN_E580");
		s21ApiCallBack.init(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE, "S21EXTN_E580");

		String[] resArr = new String[4];

		NLZC405001 s21Api = new NLZC405001();

		String[] line_numbers = request.getParameterValues("line_number");
		String[] so_sub_line_numbers = request.getParameterValues("so_sub_line_number");
		String[] so_numbers = request.getParameterValues("so_number");
		lsDateFmt = new SimpleDateFormat("yyyyMMdd");
		String currentDate = lsDateFmt.format(new Date());
		// String []
		// delivery_scheduled_dates=request.getParameterValues("delivery_scheduled_date");
		// String [] reason_codes=request.getParameterValues("reason_code");
		String[] dealer_install_confirm_line_selectors = request
				.getParameterValues("dealer_install_confirm_line_selector");
		String[] deliv_conf_flags = request
				.getParameterValues("deliv_conf_flag");
		
		List<NLZC405001PMsg> pMsgList=new ArrayList<NLZC405001PMsg>();
		boolean validationError=false;
		Exception exception=null;
		try {
		
			for (int line = 0; line < line_numbers.length; line++) {
				String dealer_install_confirm_line_selector = dealer_install_confirm_line_selectors[line];
				String deliv_conf_flag = deliv_conf_flags[line];
				if (!"Y".equals(dealer_install_confirm_line_selector) || !"Y".equals(deliv_conf_flag)) {
					continue;
				}
				String so_number = so_numbers[line];

				NLZC405001PMsg pmsg = new NLZC405001PMsg();
	
				pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
				pmsg.slsDt.setValue(currentDate);
				pmsg.inbdOtbdCd.setValue("2");
				pmsg.cpoOrdNum.setValue(so_number);// Order Number
				String line_number = line_numbers[line];
				String so_sub_line_number = so_sub_line_numbers[line];
				pmsg.cpoDtlLineNum.setValue(line_number);
				pmsg.cpoDtlLineSubNum.setValue(so_sub_line_number);
				pmsg.carrRsnCd.setValue("00");
				pmsg.carrCmntTxt.setValue("Delivered");
				pmsg.updUsrId.setValue(loginUser);
				pmsg.updTs.setValue(invokeTimestamp);
				pmsg.xxRqstDt.setValue(today());
				pMsgList.add(pmsg);
			}
		} catch (Exception e) {
			exception=e;
			validationError=true;
			logMsg("found s21 api validation error.");
			e.printStackTrace();
			String err = e.getMessage();
			logMsg("ERROR MESSAGE : " + err);
			e.printStackTrace();
			resArr[0] = "ERROR : ";
			resArr[1] = "A validation error occurred";
			s21ApiCallBack.onError(VALIDATION_ERR_MSG);
		}finally {
			if(exception!=null) {
				logException(exception,VALIDATION_ERR_MSG,pMsgList);
				exception=null;
			}
		}
		
		try {	
			if(!validationError) {
			
			if(pMsgList.isEmpty()) {
				logMsg("No messages!");
				resArr[0] = "Success";
				return resArr;
			}
		
			logMsg("####################### Delivery Confirmation API #############");
			for(NLZC405001PMsg pmsg: pMsgList) {
				printMsg(pmsg);
			}	
			s21Api.execute(pMsgList, ONBATCH_TYPE.ONLINE); // execute API
			String err=CanonE580ITTWorkbenchPOHelper.getEZDError(pMsgList);
			if(err!=null) {
				throw new Exception(err);
			}
			logMsg(" No error, before commit");
			s21ApiCallBack.onSuccess();
			resArr[0] = "Success";
			}
		} catch (Exception e) {
			exception=e;
			String err=e.getMessage();
			logMsg("ERROR MESSAGE : " + err);
			e.printStackTrace();
			resArr[0] = "ERROR : ";
			resArr[1] = err;
			s21ApiCallBack.onError("Delivery Confirmation API: " +err);
		} finally {
			s21ApiCallBack.onFinally();
			if(exception!=null) {
				logException(exception,"Delivery Confirmation API: "+exception.getMessage(),pMsgList);
			}
		}
		
		return resArr;
	}
	
	private void printMsg(NLZC405001PMsg pmsg ) {
		printMsg(pmsg,System.out);
	}
	
	private static void printMsg(NLZC405001PMsg pmsg, PrintStream ps ) {
		
		logMsg("glblCmpyCd        :" + pmsg.glblCmpyCd.getValue(), ps);
		logMsg("slsDt             :" + pmsg.slsDt.getValue(), ps);
		logMsg("inbdOtbdCd             :" + pmsg.inbdOtbdCd.getValue(), ps);
		logMsg("cpoOrdNum         :" + pmsg.cpoOrdNum.getValue(), ps);
		logMsg("cpoDtlLineNum     :" + pmsg.cpoDtlLineNum.getValue(), ps);
		logMsg("cpoDtlLineSubNum  :" + pmsg.cpoDtlLineSubNum.getValue(), ps);
		logMsg("carrRsnCd  		  :" + pmsg.carrRsnCd.getValue(), ps);
		logMsg("carrCmntTxt  	  :" + pmsg.carrCmntTxt.getValue(), ps);
		logMsg("updTs   		  :" + pmsg.updTs.getValue(), ps);
		logMsg("updUsrId    	  :" + pmsg.updUsrId.getValue(), ps);
		logMsg("xxRqstDt    	  :" + pmsg.xxRqstDt.getValue(), ps);
	}

	private static String pmsgToString(NLZC405001PMsg pmsg) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps;
		try {
			ps = new PrintStream(baos);
			printMsg(pmsg, ps);
			String content = new String(baos.toByteArray());
			ps.close();		
			return content;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void logException(Exception ex,String subject,List<NLZC405001PMsg> pMsgList){
		logMsg("in logException "+ex);
		if(ex instanceof S21AbendException){
			return;
		}
		String []list=new String[pMsgList.size()];
		for(int i=0;i<pMsgList.size();i++) {
			list[i]=pmsgToString(pMsgList.get(i));
		}
		new S21AbendException(ex,subject,list);
	}
	
}
