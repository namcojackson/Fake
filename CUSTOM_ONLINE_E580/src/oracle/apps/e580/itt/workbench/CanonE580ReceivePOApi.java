package oracle.apps.e580.itt.workbench;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;
import business.parts.NPZC131001PMsg;
import canon.apps.common.CanonConstants;
import canon.apps.common.CanonS21SessionValidate;

import com.canon.cusa.s21.api.NPZ.NPZC131001.NPZC131001;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;


public class CanonE580ReceivePOApi {

	public String checkNull(HttpServletRequest req, String str) {
		String s = "";

		String tmp = req.getParameter(str);
		if (tmp != null) {			
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

	public NPZC131001PMsg  getMsg(HttpServletRequest request) {
		NPZC131001PMsg  pmsg = new NPZC131001PMsg();
		System.out
				.println("####################### Receive PO  #############");
		System.out.println("\n\n\n");
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

		NPZC131001 s21Api=new NPZC131001();
		NPZC131001PMsg pmsg = null;
		
		pmsg = getMsg(request);
		String p_itt_number = request.getParameter("itt_number");	
		/*	
		String[] itt_line_nums = request.getParameterValues("itt_line_num");
		String[] po_numbers = request.getParameterValues("po_number");
		String[] po_line_nums = request.getParameterValues("po_line_num");																			
		String[] item_names = request.getParameterValues("item_name");
		String[] item_descs = request.getParameterValues("item_desc");
		String[] receive_poqties = request.getParameterValues("receive_po-qty");
		String[] inv_orgs = request.getParameterValues("inv_org");
		String[] lot_nums = request.getParameterValues("receive_po-lot_number");
		String[] serial_nums = request.getParameterValues("receive_po-serial_number");
		String[] license_numbers = request.getParameterValues("receive_po-license_number");
		String[] receive_po_line_selectors = request.getParameterValues("receive_po_line_selector");*/
		String  poOrdDtlLineNum=(String)request.getAttribute("rcvline_number");  	
		String po_number=(String)request.getAttribute("rcvpo_number"); 
		String item_code=(String)request.getAttribute("rcvitem_code");
		String ship_qty=(String)request.getAttribute("rcvitem_qty");
		String etadt=(String)request.getAttribute("rcveta_dt");
		String stkStscd=(String)request.getAttribute("rcvstk_sts_cd");			
		String serial_number=(String)request.getAttribute("rcvserial_number");
		String rwsrefnum=(String)request.getAttribute("rws_ref_num");
		
		System.out.println("[E580: Receive PO API ]  : ");
		System.out.println("poOrdDtlLineNum : " + poOrdDtlLineNum);
		System.out.println("po_number : " + po_number);
		System.out.println("item_code : " + item_code);
		System.out.println("ship_qty : " + ship_qty);
		System.out.println("etadt : " + etadt);
		System.out.println("stkStscd : " + stkStscd);
		System.out.println("serial_number : " + serial_number);
		System.out.println("rws_ref_num : " + rwsrefnum);
		
		String so_line_num=(String)request.getAttribute("so_line_number");
		lsDateFmt = new SimpleDateFormat("yyyyMMdd");
		String currentDate = lsDateFmt.format(new Date()); 
		
		
		pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
		pmsg.xxModeCd.setValue("1");
		pmsg.slsDt.setValue(currentDate);
		
		pmsg.poOrdNum.setValue(po_number);
		pmsg.rwsRefNum.setValue(po_number);
		pmsg.vndInvNum.setValue(po_number);  //6000151
		pmsg.etaDt.setValue(etadt);	
		pmsg.shipDt.setValue(currentDate);	
		pmsg.rwsRefNum.setValue(rwsrefnum);
		pmsg.ordDtlInfo.setValidCount(1);
		pmsg.ordDtlInfo.no(0).poOrdDtlLineNum.setValue(poOrdDtlLineNum);
		pmsg.ordDtlInfo.no(0).mdseCd.setValue(item_code);
		pmsg.ordDtlInfo.no(0).stkStsCd.setValue(stkStscd);
		pmsg.ordDtlInfo.no(0).shipQty.setValue(new BigDecimal(ship_qty));
		
		if(serial_number!=null && !"".equalsIgnoreCase(serial_number))
		{
		pmsg.serNumList.setValidCount(1);
		pmsg.serNumList.no(0).serNum.setValue(serial_number);
		pmsg.serNumList.no(0).mdseCd.setValue(item_code);
		pmsg.serNumList.no(0).poOrdDtlLineNum.setValue(poOrdDtlLineNum);
		}
		
			
	logMsg("Before execute  ");
		
		try {
			// Normal Case. (No error msg) - S21API set some error message id
			// when got any error while S21API's function.
			s21Api.execute(pmsg,ONBATCH_TYPE.ONLINE); // execute API
			System.out.println("After execute, isXxMsgId=");
			if (!S21ApiUtil.isXxMsgId(pmsg)) {
				// There is no message id, so can do commit the transaction.
				logMsg("No error, before commit");
				EZDConnectionMgr.getInstance().commit(); 
				// commit	
				logMsg("After commit"); 
				resArr[0] = "Success";
				resArr[1]="Canon Create Receipts success for selected lines!";
			}
			else {
				StringBuffer sb = new StringBuffer("");
				// Error Case - S21API set some error message id when got any
				// error while S21API's function.
				List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
				String s21ApiError="";
				sb.append("ERROR : " + "\n");
				for (String msg : err) {
					logMsg("ERROR MESSAGE in S21 API Call : "
							+ S21MessageFunc.clspGetMessage(msg));					
					
					s21ApiError=s21ApiError+S21MessageFunc.clspGetMessage(msg);
					sb.append(s21ApiError);
				}

				CanonE580ITTWorkbenchDAO.updatePorError(p_itt_number, so_line_num==null?"":so_line_num, po_number, s21ApiError, loginUser);
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
			CanonE580ITTWorkbenchDAO.updatePorError(p_itt_number, so_line_num==null?"":so_line_num, po_number, e.getMessage(), loginUser);
			resArr[0] = "ERROR : ";
			resArr[1] = " ";
		} finally { // Release DB resource (Close DB Connection)
			EZDConnectionMgr.getInstance().releaseResource();
		}
		
	
		return resArr;
	}

	public void logMsg(String str) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		System.out.println("[E580: Receive PO API ] ["
				+ sdf.format(Calendar.getInstance().getTime()) + "] : " + str);
	}
}
