package oracle.apps.e580.itt.workbench;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21AbendException;

import business.parts.NSZC001001PMsg;
import canon.apps.common.CanonConstants;
import canon.apps.common.CanonS21SessionValidate;

public class CanonE580MachineMasterUpdateApi implements CanonE580ITTWorkbenchS21Api {
	final static String VALIDATION_ERR_MSG="Machine Master Update API: A validation error occurred";
	
	final CanonE580ITTWorkbenchCallback<Object> s21ApiCallBack;

	public CanonE580MachineMasterUpdateApi(CanonE580ITTWorkbenchCallback<Object> cb) {
		this.s21ApiCallBack = cb;
	}

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void logMsg(String str, PrintStream ps) {
		ps.println(
				"[e580: Machine Master Update API ] [" + sdf.format(Calendar.getInstance().getTime()) + "] : " + str);
	}

	public static void logMsg(String str) {
		logMsg(str,System.out);
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

		NSZC001001 s21Api = new NSZC001001();

		String[] line_numbers = request.getParameterValues("line_number");
		String[] dealer_install_confirm_line_selectors = request
				.getParameterValues("dealer_install_confirm_line_selector");

		lsDateFmt = new SimpleDateFormat("yyyyMMdd");
		String currentDate = lsDateFmt.format(new Date());

		boolean checked_ = false;
		for (int line = 0; line < line_numbers.length; line++) {
			String dealer_install_confirm_line_selector = dealer_install_confirm_line_selectors[line];
			if ("Y".equals(dealer_install_confirm_line_selector)) {
				checked_ = true;
				break;
			}
		}
		if (!checked_) {
			resArr[0] = "Success";
			return resArr;
		}
		
		NSZC001001PMsg pmsg = new NSZC001001PMsg();
		boolean validationError=false;
		Exception exception=null;
		try {
	
			BigDecimal svc_config_mstr_pk = CanonE580ITTWorkbenchUtil
					.toBigDecimal(request.getParameter("svc_config_mstr_pk"), false);
			BigDecimal svc_mach_mstr_pk = CanonE580ITTWorkbenchUtil.toBigDecimal(request.getParameter("svc_mach_mstr_pk"),
					false);
			String stk_sts_cd = request.getParameter("stk_sts_cd");
			String svc_mach_mstr_loc_sts_cd = request.getParameter("svc_mach_mstr_loc_sts_cd");
	
			pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
			pmsg.slsDt.setValue(currentDate);
			pmsg.xxModeCd.setValue(ProcessMode.INSTALLATION.code); // static value
			pmsg.svcConfigMstrPk.setValue(svc_config_mstr_pk);
			pmsg.svcMachMstrPk.setValue(svc_mach_mstr_pk);
	
			pmsg.svcMachMstrStsCd.setValue(SVC_MACH_MSTR_STS.INSTALLED); // static value
			pmsg.stkStsCd.setValue(stk_sts_cd);
			pmsg.istlDt.setValue(currentDate);
			pmsg.svcMachUsgStsCd.setValue(SVC_MACH_USG_STS.AT_CUSTOMER); // static value
			pmsg.svcMachMstrLocStsCd.setValue(svc_mach_mstr_loc_sts_cd);
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
				logException(exception,VALIDATION_ERR_MSG, pmsg);
				exception=null;
			}
		}
		
		try {
			if(!validationError) {
				logMsg("####################### Machine Master Update API #############");
				printMsg(pmsg);
				s21Api.execute(pmsg, ONBATCH_TYPE.ONLINE); // execute API
				String err = CanonE580ITTWorkbenchPOHelper.getEZDError(pmsg);
				if (err != null) {
					throw new Exception(err);
				}
				logMsg(" No error, before commit");
				s21ApiCallBack.onSuccess();
				resArr[0] = "Success";
			}
		} catch (Exception e) {
			exception=e;
			String err = e.getMessage();
			logMsg("ERROR MESSAGE : " + err);
			e.printStackTrace();
			resArr[0] = "ERROR : ";
			resArr[1] = err;
			s21ApiCallBack.onError("Machine Master Update API: " +err);
		} finally {
			/*
			* This is necessary
			*/
			s21ApiCallBack.onFinally();
			if(exception!=null) {
				logException(exception,"Machine Master Update API: "+exception.getMessage(), pmsg);
			}
		}

		return resArr;
	}

	private void printMsg(NSZC001001PMsg pmsg ) {
		printMsg(pmsg,System.out);
	}
	
	private static void printMsg(NSZC001001PMsg pmsg, PrintStream ps) {
		logMsg("glblCmpyCd        :" + pmsg.glblCmpyCd.getValue(), ps);
		logMsg("slsDt             :" + pmsg.slsDt.getValue(), ps);
		logMsg("xxModeCd             :" + pmsg.xxModeCd.getValue(), ps);
		logMsg("svcConfigMstrPk         :" + pmsg.svcConfigMstrPk.getValue(), ps);
		logMsg("svcMachMstrPk     :" + pmsg.svcMachMstrPk.getValue(), ps);

		logMsg("svcMachMstrStsCd  :" + pmsg.svcMachMstrStsCd.getValue(), ps);
		logMsg("stkStsCd  		  :" + pmsg.stkStsCd.getValue(), ps);
		logMsg("istlDt  	  :" + pmsg.istlDt.getValue(), ps);
		logMsg("svcMachUsgStsCd   		  :" + pmsg.svcMachUsgStsCd.getValue(), ps);
		logMsg("svcMachMstrLocStsCd    	  :" + pmsg.svcMachMstrLocStsCd.getValue(), ps);

	}
	
	//com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS
	public abstract interface SVC_MACH_MSTR_STS
	{
	  public static final String WAITING_FOR_INSTALLATION = "W4I";
	  public static final String INSTALLED = "INSTL";
	  public static final String WAITING_FOR_REMOVAL = "W4R";
	  public static final String REMOVED = "RMV";
	  public static final String CREATED = "CRAT";
	  public static final String TERMINATED = "TRMN";
	  public static final String DUPLICATE = "DUP";
	  public static final String DEALER_SERVICE = "DLRSV";
	}
	
	//com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS
	public abstract interface SVC_MACH_USG_STS
	{
	  public static final String IN_INVENTORY = "10";
	  public static final String IN_TRANSIT = "20";
	  public static final String AT_CUSTOMER = "30";
	  public static final String RETURNED = "40";
	  public static final String GONE = "90";
	}

	private static String pmsgToString(NSZC001001PMsg pmsg) {
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
	
	private static void logException(Exception ex, String subject,NSZC001001PMsg pMsg) {
		logMsg("in logException "+ex);
		if(ex instanceof S21AbendException){
			return;
		}
		String []list= {pmsgToString(pMsg)};
		new S21AbendException(ex,subject,list);
	}
	
}
