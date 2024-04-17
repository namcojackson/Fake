package oracle.apps.e580.itt.workbench;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.canon.cusa.s21.api.NWZ.NWZC033001.NWZC033001;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21AbendException;

import business.parts.NWZC033001PMsg;
import canon.apps.common.CanonConstants;
import canon.apps.common.CanonS21SessionValidate;

public class CanonE580HoldReleaseApi implements CanonE580ITTWorkbenchS21Api {
	final static String VALIDATION_ERR_MSG="Hold Release API: A validation error occurred";
	final CanonE580ITTWorkbenchCallback<Object> s21ApiCallBack;

	public CanonE580HoldReleaseApi(CanonE580ITTWorkbenchCallback<Object> cb) {
		this.s21ApiCallBack = cb;
	}

	public String[] createServicerequest(HttpServletRequest request) {

		String[] resArr = new String[4];

		SimpleDateFormat format = new SimpleDateFormat("z");
		SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String timezone = format.format(new Date());
		String invokeTimestamp = lsDateFmt.format(new Date());
		String loginUser = CanonS21SessionValidate.getUserName();

		// Initialize S21DB-Carrier (It should be done. It leads NullPointer
		// exception when didn't initialize.)
		// These contents are used as a default data in S21-Standard table.
		// (Update user, time, time-zone, program id, company code)
		// EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone,
		// CanonConstants.CSA_COMPANY_CODE);
		// EZDDBCICarrier.setProgID("S21EXTN_E580");
		s21ApiCallBack.init(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE, "S21EXTN_E580");

		NWZC033001 s21Api = new NWZC033001();
		String p_itt_number = request.getParameter("itt_number");

		String[] dealer_install_confirm_line_selectors = request
				.getParameterValues("dealer_install_confirm_line_selector");
		String[] so_numbers = request.getParameterValues("so_number");// CPO
																		// number
		String[] line_numbers = request.getParameterValues("line_number");// CPO
																			// detail
																			// line
																			// number
		String[] hold_pks = request.getParameterValues("hold_pk");
		String[] hold_rsn_cds = request.getParameterValues("hold_rsn_cd");
		String[] so_sub_line_numbers = request.getParameterValues("so_sub_line_number");// CPO Detail Line Sub
																						// Number
		String dealer_install_confirm_release_comment = request.getParameter("dealer_install_confirm_release_comment");
		List<String[]> tmpList=new ArrayList<String[]>();
		List<NWZC033001PMsg> pMsgList=new ArrayList<NWZC033001PMsg>();
		boolean validationError=false;
		Exception exception=null;
		try {

			for (int i = 0; line_numbers != null && i < line_numbers.length; i++) {
				String dealer_install_confirm_line_selector = dealer_install_confirm_line_selectors[i];
				if ("Y".equals(dealer_install_confirm_line_selector)) {
					String so_number = so_numbers[i];
					String line_number = line_numbers[i];
					String hold_pk = hold_pks[i];
					String hold_rsn_cd = hold_rsn_cds[i];
					String so_sub_line_number = so_sub_line_numbers[i];
					System.out.println("All values prior to call Hold Release:" + hold_pk + "," + so_number + ","
							+ line_number + "," + so_sub_line_number + "," + hold_rsn_cd + ","
							+ dealer_install_confirm_release_comment);
					NWZC033001PMsg pmsg = new NWZC033001PMsg();
	
					pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
					pmsg.hldPk.setValue(new BigDecimal(hold_pk));
					pmsg.cpoOrdNum.setValue(so_number);
					pmsg.cpoDtlLineNum.setValue(line_number);
					pmsg.cpoDtlLineSubNum.setValue(so_sub_line_number);
					lsDateFmt = new SimpleDateFormat("yyyyMMdd");
					String currentDate = lsDateFmt.format(new Date());
					pmsg.slsDt.setValue(currentDate);
					pmsg.hldRelRsnCd.setValue(hold_rsn_cd);
					pmsg.relMemoTxt.setValue(dealer_install_confirm_release_comment);
					pMsgList.add(pmsg);
					tmpList.add(new String[] {p_itt_number, so_number, line_number+"."+so_sub_line_number, loginUser});
				}
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
		
			logMsg("####################### Hold Release API  #############");
			for(NWZC033001PMsg pmsg: pMsgList) {
				printMsg(pmsg);
			}
			s21Api.execute(pMsgList, ONBATCH_TYPE.ONLINE); // execute API
			String err=CanonE580ITTWorkbenchPOHelper.getEZDError(pMsgList);
			if(err!=null) {
				throw new Exception(err);
			}
			logMsg(" No error, before commit");
			s21ApiCallBack.onSuccess(tmpList);
			resArr[0] = "Success";
			}
		} catch (Exception e) {
			exception=e;
			String err=e.getMessage();
			logMsg("ERROR MESSAGE : " + err);
			e.printStackTrace();
			resArr[0] = "ERROR : ";
			resArr[1] = err;
			s21ApiCallBack.onError("Hold Release API: "+err);
			
		} finally {
			s21ApiCallBack.onFinally();
			if(exception!=null) {
				logException(exception,"Hold Release API: "+exception.getMessage(), pMsgList);
			}
		}

		return resArr;
	}

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void logMsg(String str, PrintStream ps) {
		ps.println("[e580: Hold Release API ] [" + sdf.format(Calendar.getInstance().getTime()) + "] : " + str);
	}
	
	public static void logMsg(String str) {
		logMsg(str,System.out);
	}
	
	private void printMsg(NWZC033001PMsg pmsg) {
		printMsg(pmsg,System.out);
	}
	
	private static void printMsg(NWZC033001PMsg pmsg, PrintStream ps ) {
		logMsg("glblCmpyCd        :" + pmsg.glblCmpyCd.getValue(), ps);
		logMsg("hldPk             :" + pmsg.hldPk.getValue(), ps);
		logMsg("cpoOrdNum         :" + pmsg.cpoOrdNum.getValue(), ps);
		logMsg("cpoDtlLineNum     :" + pmsg.cpoDtlLineNum.getValue(), ps);
		logMsg("cpoDtlLineSubNum  :" + pmsg.cpoDtlLineSubNum.getValue(), ps);
		logMsg("slsDt             :" + pmsg.slsDt.getValue(), ps);
		logMsg("hold_rsn_cd       :" + pmsg.hldRelRsnCd.getValue(), ps);
		logMsg("relMemoTxt       :" + pmsg.relMemoTxt.getValue(), ps);
	}
	
	
	private static String pmsgToString(NWZC033001PMsg pmsg) {
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
	
	private static void logException(Exception ex, String subject, List<NWZC033001PMsg> pMsgList){
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
