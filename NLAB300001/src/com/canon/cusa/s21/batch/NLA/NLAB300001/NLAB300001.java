/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB300001;

import static com.canon.cusa.s21.batch.NLA.NLAB300001.constant.NLAB300001Constant.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.POTMsg;
import business.db.PO_DTLTMsg;
import business.db.PRNT_CMPY_VNDTMsg;
import business.db.VND_INV_BOL_WRKTMsg;
import business.db.VND_INV_BOL_WRKTMsgArray;
import business.db.VND_INV_ERR_WRKTMsg;
import business.db.VND_INV_LIC_ACCS_WRKTMsg;
import business.db.VND_INV_LIC_ACCS_WRKTMsgArray;
import business.db.VND_INV_LINE_WRKTMsg;
import business.db.VND_INV_LINE_WRKTMsgArray;
import business.db.VND_INV_WRKTMsg;
import business.db.VND_INV_WRKTMsgArray;
import business.parts.NPZC004001PMsg;
import business.parts.NPZC110001PMsg;
import business.parts.NPZC110001_poDetailOutListPMsg;

import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.api.NPZ.NPZC110001.NPZC110001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MATCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Receive Invoice Information Batch 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/09/2016   CITS            T.Wada          Create
 * 07/06/2016   CITS            T.Wada          Update          QC11496
 * 08/22/2016   CITS            M.Okigami       Update          PO to WMS without ASN
 * 08/25/2016   CITS            T.Wada          Update          QC7698
 * 09/27/2016   CITS            R.Shimamoto     Update          QC#14714
 * 11/09/2016   CITS            R.Shimamoto     Update          QC#15121
 * 01/16/2017   CITS            T.Hakodate      Update          QC#7523
 * 08/01/2017   CITS            R.Shimamoto     Update          QC#18671
 * 12/22/2017   CITS            K.Kameoka       Update          QC#14858(Sol#060)
 * 04/02/2018   CITS            K.Kameoka       Update          QC#15977
 * 10/17/2018   Fujitsu         S.Ohki          Update          QC#28801
 * 11/14/2018   CITS            M.Naito         Update          QC#29047
 * 01/28/2019   CITS            T.haokdate      Update          QC#30042
 * 02/25/2019   Hitachi         H.Umeda         Update          QC#30499
 * 02/27/2019   Hitachi         H.Umeda         Update          QC#30499
 * 2019/03/25   Hitachi         Y.Takeno        Update          QC#30850
 * 2019/04/16   Hitachi         Y.Takeno        Update          QC#31071
 * 2019/05/16   Hitachi         Y.Takeno        Update          QC#50204
 * 2019/08/20   Hitachi         Y.Takeno        Update          QC#52280
 * 2019/09/02   Hitachi         Y.Takeno        Update          QC#53078
 * 2022/11/24   Hitachi         S.Nakatani      Update          QC#60402
 * 2023/12/22   CITS            K.Iwamoto       Update          QC#63381
 *</pre>
 */
public class NLAB300001 extends S21BatchMain {

	/** Mail Template ID for Invoice */
	private String mailTemplateIdForInv = null;

	/** Mail System Error Message */
	private StringBuilder mailSystemErrorMessage;

	/** Mail Business Error Message */
	private StringBuilder mailBusinessErrorMessage;

	/** Mail System Error Message Detail */
	private StringBuilder mailSystemErrorMessageDetail;

	/** Mail Business Error Message Detail */
	private StringBuilder mailBusinessErrorMessageDetail;

	/** Global Company Code */
	private String glblCmpyCd = null;

	/** CUSA Global Company Code */
	private String cusaGlblCmpyCd = null;

	/** Standard Currency Code */
	private String stdCcyCd = null;

	/** Sales Date */
	private String slsDt;

	/** Interface ID */
	private String interfaceId = null;

	/** termination code */
	private TERM_CD termCd = TERM_CD.NORMAL_END;

	/** success count */
	private int successCount = 0;

	/** error count */
	private int errorCount = 0;

	/** total count */
	private int totalCount = 0;

	/** Error Flag : Whether error occurred or not in process */
	boolean allErrorFlg = false;

	/** EDI Line# Length */
	int ediLineNumLength;

	/** PO Line# Length */
	int poLineNumLength;

	/** SQL Access Parts */
	private S21SsmBatchClient ssmBatchClient = null;

	/** vndCdList */
	private ArrayList<String> vndCdList = new ArrayList<String>();

	/** vndPmtTermCdList */
	private ArrayList<String> vndPmtTermCdList = new ArrayList<String>();

    // START 2019/04/16 [QC#31071, ADD]
	private List<String> skipCrDrRsnCdList = new ArrayList();
    // END   2019/04/16 [QC#31071, ADD]

	@Override
	protected void initRoutine() {
		ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

		glblCmpyCd = getGlobalCompanyCode();
		if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
			throw new S21AbendException(MSG_ID_NLAM1118E,
					new String[] { MSG_TXT_GLBL_CMPY_CD });
		}
		GLBL_CMPYTMsg glblCmpy = getGlblCmpyInfo();
		this.stdCcyCd = glblCmpy.stdCcyCd.getValue();
		this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
		this.interfaceId = getInterfaceID();

		VND_INV_LINE_WRKTMsg invLineWrk = new VND_INV_LINE_WRKTMsg();
		this.ediLineNumLength = invLineWrk.getAttr("ediPoOrdDtlLineNum")
				.getDigit();
		this.poLineNumLength = invLineWrk.getAttr("poOrdDtlLineNum").getDigit();

		termCd = TERM_CD.NORMAL_END;

		mailTemplateIdForInv = getUserVariable1();
		if (!ZYPCommonFunc.hasValue(mailTemplateIdForInv)) {
			throw new S21AbendException(MSG_ID_NLAM1118E,
					new String[] { MAIL_TEMPLATE_INV });
		}

		mailSystemErrorMessage = new StringBuilder();
		mailBusinessErrorMessage = new StringBuilder();
		mailSystemErrorMessageDetail = new StringBuilder();
		mailBusinessErrorMessageDetail = new StringBuilder();

		// Get CUSA Global Company Code
		cusaGlblCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(
				CUSA_GLBL_CMPY_CD, glblCmpyCd);
		if (!ZYPCommonFunc.hasValue(cusaGlblCmpyCd)) {
			throw new S21AbendException(MSG_ID_NLAM1118E,
					new String[] { MSG_TXT_CUSA_GLBL_CMPY_CD });
		}

        // START 2019/04/16 [QC#31071, ADD]
        skipCrDrRsnCdList = splitStringTxt(ZYPCodeDataUtil.getVarCharConstValue(NLAB3000_SKIP_CR_DR_RSN_CD, this.glblCmpyCd));
        // END   2019/04/16 [QC#31071, ADD]
	}

	@Override
	protected void mainRoutine() {
		try {
			// The main processing
			itemDataCheckPoUpdate();
		} catch (SQLException e) {
			sqlExceptionHandler(e);
		}

		if (mailSystemErrorMessage.length() > 0) {
			sendSystemErrorMail();
		}
		if (mailBusinessErrorMessage.length() > 0) {
			sendBusinessErrorMail();
		}

	}

	@Override
	protected void termRoutine() {

		setTermState(this.termCd, this.successCount, this.errorCount,
				this.totalCount);

	}

	/**
	 * main method
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		// Initialize S21BatchMain
		new NLAB300001().executeBatch(NLAB300001.class.getSimpleName());
	}

	/**
	 * itemDataCheckPoUpdate(The main processing)
	 * 
	 * @throws SQLException
	 */
	private void itemDataCheckPoUpdate() throws SQLException {

		// Get [VND_INV_WRK] Data
		VND_INV_WRKTMsgArray vndInvWrkArray = getTargetVndInvWrk();

		int invCount = vndInvWrkArray.getValidCount();

		boolean isErrorVndInvWrk;
		boolean isErrorUnderBol;

		// /////////////////////////////////////////////////////////////////////////////////////////////////////
		// For each obtained the VND_INV_WRK data (per Invoice Number)
		// to repeat the subsequent processing
		// /////////////////////////////////////////////////////////////////////////////////////////////////////
		VND_INV_WRKTMsg vndInvWrk = null;
		for (int i = 0; i < invCount; i++) {
			vndInvWrk = vndInvWrkArray.no(i);

			// INV_TP_CD Check ※Case of INV_TP_CD is not "Invoice",
			// Skip the process
//			if (vndInvWrk.invTpCd == null
//					|| !(vndInvWrk.invTpCd.getValue().equals(INV_TP.INVOICE))) {
			// QC#30042 mod start
            // START 2019/08/20 [QC#52280, MOD]
			// if (vndInvWrk.invTpCd == null
			// 		|| !((vndInvWrk.invTpCd.getValue().equals(INV_TP.INVOICE) || (vndInvWrk.invTpCd
			// 				.getValue().equals(INV_TP.CREDIT_MEMO))))) {

            if (vndInvWrk.invTpCd == null
                    || !((vndInvWrk.invTpCd.getValue().equals(INV_TP.INVOICE) 
                    || (vndInvWrk.invTpCd.getValue().equals(INV_TP.CREDIT_MEMO))
                    || (vndInvWrk.invTpCd.getValue().equals(INV_TP.DEBIT_MEMO)))
                            )) {
			// END   2019/08/20 [QC#52280, MOD]
				//QC#30042 mod start
				// Update VND_INV_WRK (Normal End)
				vndInvWrkStsUpdate(vndInvWrk, VND_INV_PROC_STS.PROCESSED);
				this.successCount++;
				commit();
				continue;
			}

	        // START 2019/04/16 [QC#31071, ADD]
			if (INV_TP.CREDIT_MEMO.equals(vndInvWrk.invTpCd.getValue())
			        && ZYPCommonFunc.hasValue(vndInvWrk.crDrRsnCd) && skipCrDrRsnCdList.contains(vndInvWrk.crDrRsnCd.getValue())) {
                vndInvWrkStsUpdate(vndInvWrk, VND_INV_PROC_STS.PROCESSED);
                this.successCount++;
                commit();
                continue;
			}
	        // END   2019/04/16 [QC#31071, ADD]

            // START 2019/09/02 [QC#53078, DEL]
            // // START 2019/05/16 [QC#50204, ADD]
            // if (INV_TP.CREDIT_MEMO.equals(vndInvWrk.invTpCd.getValue())
            //         && INTERFACE_ID_CUSA_PARTS.equals(vndInvWrk.itrlIntfcId.getValue())) {
            //     vndInvWrkStsUpdate(vndInvWrk, VND_INV_PROC_STS.PROCESSED);
            //     this.successCount++;
            //     commit();
            //    continue;
            /// }
            // END   2019/05/16 [QC#50204, ADD]
            // END   2019/09/02 [QC#53078, DEL]

			String invNum = vndInvWrk.vndInvNum.getValue();
			this.interfaceId = vndInvWrk.itrlIntfcId.getValue();
			this.totalCount++;
			isErrorVndInvWrk = false;
			isErrorUnderBol = false;

			// VND_INV_WRK Data check
			if (!checkVndInvWrk(invNum, vndInvWrk)) {
				isErrorVndInvWrk = true;
			}

			// Get [VND_INV_BOL_WRK] Data
			VND_INV_BOL_WRKTMsgArray vndInvBolWrkArray = getTargetVndInvBolWrk(invNum);

			int bolCount = vndInvBolWrkArray.getValidCount();

			// /////////////////////////////////////////////////////////////////////////////////////////////////////
			// For each obtained the VND_INV_BOL_WRK data (per Invoice
			// BOL Line Number) to repeat the subsequent processing
			// /////////////////////////////////////////////////////////////////////////////////////////////////////
			for (int bolIdx = 0; bolIdx < bolCount; bolIdx++) {
				VND_INV_BOL_WRKTMsg vndInvBolWrk = vndInvBolWrkArray.no(bolIdx);

				if (!itemDateCheckByBolLine(vndInvWrk, vndInvBolWrk,
						isErrorVndInvWrk)) {
					isErrorUnderBol = true;
				}
			}

			// Setting VND_CD
			if (vndCdList.size() > 0) {
				String vndCd = checkVndCd();
				if (ZYPCommonFunc.hasValue(vndCd)) {
					vndInvWrkVndCdUpdate(vndInvWrk, vndCd, null, null);
				} else {
					isErrorUnderBol = true;
				}
				vndCdList.clear();
			}

			// Update VND_PMT_TERM_CD
			if (vndPmtTermCdList.size() > 0) {
				String vndPmtTermCd = vndPmtTermCdList.get(0);
				String vndPmtTermNm = vndPmtTermCdList.get(1);

				if (ZYPCommonFunc.hasValue(vndPmtTermCd)) {
					vndInvWrkVndCdUpdate(vndInvWrk, null, vndPmtTermCd,
							vndPmtTermNm);
				}
				vndPmtTermCdList.clear();
			}

			// Result Check
			if (!isErrorVndInvWrk && !isErrorUnderBol) {
				// Update VND_INV_WRK (Normal End)
				vndInvWrkStsUpdate(vndInvWrk, VND_INV_PROC_STS.PROCESSED);
				this.successCount++;

			} else {
				// Update VND_INV_WRK (Error End)
				vndInvWrkStsUpdate(vndInvWrk, VND_INV_PROC_STS.ERROR);

				if (this.mailSystemErrorMessageDetail.length() > 0) {
					editItemErrorMailMessage(this.mailSystemErrorMessage,
							this.mailSystemErrorMessageDetail, invNum);
				}
				if (this.mailBusinessErrorMessageDetail.length() > 0) {
					editItemErrorMailMessage(this.mailBusinessErrorMessage,
							this.mailBusinessErrorMessageDetail, invNum);
				}
				this.mailSystemErrorMessageDetail = new StringBuilder();
				this.mailBusinessErrorMessageDetail = new StringBuilder();
				this.errorCount++;
			}

			commit();

		}
	}

	/**
	 * itemDateCheckByBolLine
	 * 
	 * @param vndInvWrk
	 * @param vndInvBolWrk
	 * @param isErrorVndInvWrk
	 * @return
	 * @throws SQLException
	 */
	private boolean itemDateCheckByBolLine(VND_INV_WRKTMsg vndInvWrk,
			VND_INV_BOL_WRKTMsg vndInvBolWrk, boolean isErrorVndInvWrk)
			throws SQLException {

		boolean result = true;

		String invNum = vndInvWrk.vndInvNum.getValue();
		String vndCd = vndInvWrk.vndCd.getValue();

		boolean execPoCheckFlg;

		if (isErrorVndInvWrk) {
			execPoCheckFlg = false;
		} else {
			execPoCheckFlg = true;
		}
		boolean checkPoResult = false;

		POTMsg poTMsg = null;

		if (execPoCheckFlg) {

			// A) Get PO From EDI API(NPZC110001) Call
			NPZC110001PMsg pMsg = new NPZC110001PMsg();
			ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
			ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
			ZYPEZDItemValueSetter.setValue(pMsg.ediPoOrdNum,
					vndInvBolWrk.ediPoOrdNum);
			ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum_I,
					vndInvBolWrk.poOrdNum);
			pMsg.poDetailInList.setValidCount(0);
			NPZC110001 apiGetPoLine = new NPZC110001();
			apiGetPoLine.execute(pMsg, ONBATCH_TYPE.BATCH);

			if (!ZYPCommonFunc.hasValue(pMsg.poOrdNum_O)) {
				execPoCheckFlg = false;
			} else {
				// Get [PO] Data
				poTMsg = getPo(pMsg.poOrdNum_O.getValue());
			}

			if (poTMsg == null) {
				execPoCheckFlg = false;
			}

			// Error Check & Update [VND_INV_BOL_WRK]
			checkPoResult = checkPo(vndInvBolWrk, poTMsg);

			// V2.5 QC#14714 Start
			// Get VND_PMT_TERM_CD
			Map<String, Object> vndPmtTermMap = null;
			if (poTMsg != null) {
				if (ZYPCommonFunc.hasValue(poTMsg.vndCd)
						&& ZYPCommonFunc
								.hasValue(vndInvWrk.vndPmtTermCashDiscCd)) {
					String poVndCd = poTMsg.vndCd.getValue();
					String vndPmtTermCashDiscCd = vndInvWrk.vndPmtTermCashDiscCd
							.getValue();

					vndPmtTermMap = getVndPmtTermCdFirst(poVndCd,
							vndPmtTermCashDiscCd);

					if (vndPmtTermMap == null || vndPmtTermMap.isEmpty()) {
						vndPmtTermMap = getVndPmtTermCdSecond(poVndCd,
								vndPmtTermCashDiscCd);
					}
					if (vndPmtTermMap == null || vndPmtTermMap.isEmpty()) {
						vndPmtTermMap = getVndPmtTermCdByPrntVnd(poVndCd);
					}
					if (vndPmtTermMap != null) {
						if (!vndPmtTermMap.isEmpty()) {
							vndPmtTermCdList.add((String) vndPmtTermMap
									.get(VND_PMT_TERM_CD));
							vndPmtTermCdList.add((String) vndPmtTermMap
									.get(VND_PMT_TERM_NM));
						}
					}

				} else if (ZYPCommonFunc.hasValue(poTMsg.vndCd)) {
					String poVndCd = poTMsg.vndCd.getValue();
					vndPmtTermMap = getVndPmtTermCdByPrntVnd(poVndCd);
					if (vndPmtTermMap != null) {
						vndPmtTermCdList.add((String) vndPmtTermMap
								.get(VND_PMT_TERM_CD));
						vndPmtTermCdList.add((String) vndPmtTermMap
								.get(VND_PMT_TERM_NM));
					}
				}
			}
			// V2.5 QC#14714 End
		}

		// ////////////////////////////////////////////////
		// Get [VND_INV_LINE_WRK] Data
		VND_INV_LINE_WRKTMsgArray vndInvLineWrkArray = getTargetVndInvLineWrk(
				invNum, vndInvBolWrk.vndInvBolLineNum.getValue());

		int loopCount = vndInvLineWrkArray.getValidCount();

		boolean isError = false;
		// /////////////////////////////////////////////////////////////////////////////////////////////////////
		// For each obtained the VND_INV_LINE_WRK data (per Invoice
		// Line Number) to repeat the subsequent processing
		VND_INV_LINE_WRKTMsg vndInvLineWrk = null;
		String errMsgId = MSG_ID_NFBM0028E;
		String errMsgTxt = S21MessageFunc.clspGetMessage(MSG_ID_NFBM0028E);
		
        // START 2022/11/24 S.Nakatani [QC#60402,ADD]
        List<String> updatePoNumList = new ArrayList<String>();
        // END 2022/11/24 S.Nakatani [QC#60402,ADD]
        
		for (int i = 0; i < loopCount; i++) {

			vndInvLineWrk = vndInvLineWrkArray.no(i);
			
			// QC#15977 START
			// except for PARENT of SET Item
			if(VND_INV_LINE_SUB_NUM_000.equals(vndInvLineWrk.vndInvLineSubNum.getValue())){
			    continue;
			}
			// QC#15977 END

			if (poTMsg == null) {
				execPoCheckFlg = false;
				isError = true;
				errMsgId = MSG_ID_NLAM1279E;
				errMsgTxt = S21MessageFunc.clspGetMessage(MSG_ID_NLAM1279E,
						new String[] { "Invoice#:" + invNum });
				// START 2018/10/17 [QC#28801, ADD]
				insertVndInvErrWrk(vndInvLineWrk, errMsgId, errMsgTxt);
				// END 2018/10/17 [QC#28801, ADD]
			} else {
				execPoCheckFlg = true;
			}

            // START 2022/11/24 S.Nakatani [QC#60402,ADD]
            boolean isExistPoNum = false;
            if (INTERFACE_ID_DIETZGEN_IF.equals(this.interfaceId)) {
                if (updatePoNumList.contains(vndInvLineWrk.poOrdDtlLineNum.getValue())) {
                    isExistPoNum = true;
                    execPoCheckFlg = false;
                    isError = true;
                    errMsgId = MSG_ID_NLAM1277E;
                    errMsgTxt = S21MessageFunc.clspGetMessage(MSG_ID_NLAM1277E, new String[] {"PO Line#", vndInvLineWrk.poOrdDtlLineNum.getValue() });
                    insertVndInvErrWrk(vndInvLineWrk, errMsgId, errMsgTxt);
                }
            }
            // END 2022/11/24 S.Nakatani [QC#60402,ADD]
			
            // [VND_INV_LINE_WRK] Data Check
            // START 2022/11/24 S.Nakatani [QC#60402,MOD]
//            if (!checkVndInvLineWrk(poTMsg, invNum, vndInvLineWrk)) {
			if (!isExistPoNum && !checkVndInvLineWrk(poTMsg, invNum, vndInvLineWrk)) {
	        // END 2022/11/24 S.Nakatani [QC#60402,MOD]
				execPoCheckFlg = false;
				isError = true;
			} else {
				// Update [VND_INV_LINE_WRK.MDSE_CD]
				vndInvLineWrkUpdate(vndInvLineWrk);
			}

			// Get [VND_INV_LIC_ACCS_WRK] Data & Check
			VND_INV_LIC_ACCS_WRKTMsgArray vndInvLicAccsWrkArray = getTargetVndInvLicAccsWrk(
					invNum, vndInvLineWrk.vndInvBolLineNum.getValue(),
					vndInvLineWrk.vndInvLineNum.getValue());
			if (!checkVndInvLicAccsWrk(vndCd, invNum, vndInvLineWrk,
					vndInvLicAccsWrkArray, poTMsg)) {
				execPoCheckFlg = false;
				isError = true;
			}

			PO_DTLTMsg poDtlTMsg = null;

			if (execPoCheckFlg) {
				// Get [PO Line] Data
				poDtlTMsg = getPoDtl(vndInvWrk, vndInvBolWrk, vndInvLineWrk);
				if (poDtlTMsg != null) {
					// Update [VND_INV_LINE_WRK.PO_ORD_DTL_LINE_NUM]
					vndInvLineWrkUpdate(vndInvLineWrk);
				} else {
					execPoCheckFlg = false;
					isError = true;
				}
			}

			if (execPoCheckFlg) {
				if (ZYPCommonFunc.hasValue(vndInvBolWrk.poOrdNum)
						&& INV_TP.INVOICE.equals(vndInvWrk.invTpCd.getValue())
						&& checkPoResult) {

					// Update PO
					String poOrdNum = vndInvBolWrk.poOrdNum.getValue();
					String poOrdDtlLineNum = vndInvLineWrk.poOrdDtlLineNum
							.getValue();

					PO_DTLTMsg dsPoDtlTMsg = getPoDtlForUpdate(poOrdNum,
							poOrdDtlLineNum);
					MDSETMsg mdseTMsg = getMdse(poDtlTMsg.mdseCd.getValue());
					execPoCheckFlg = poUpdate(vndInvBolWrk, vndInvLineWrk,
							poOrdNum, poOrdDtlLineNum, poDtlTMsg, mdseTMsg);

					if (!execPoCheckFlg) {
						isError = true;
						continue;
					}
					
		            // START 2022/11/24 S.Nakatani [QC#60402,ADD]
					updatePoNumList.add(poOrdDtlLineNum);
			        // END 2022/11/24 S.Nakatani [QC#60402,ADD]

					if (!(ZYPConstant.FLG_ON_Y
							.equals(vndInvLineWrk.vndMdseIntgFlg.getValue())
							&& ZYPConstant.FLG_ON_Y
									.equals(vndInvLineWrk.vndShpgIntgOnlyFlg
											.getValue()) && ZYPConstant.FLG_ON_Y
							.equals(mdseTMsg.invtyCtrlFlg.getValue()))) {
						continue;
					}

					PRNT_CMPY_VNDTMsg prntCmpyVndTMsg = getPrntCmpyVnd(poTMsg.vndCd
							.getValue());
					if (!(prntCmpyVndTMsg != null
							&& ZYPCommonFunc
									.hasValue(prntCmpyVndTMsg.vndSysTpCd) && prntCmpyVndTMsg.vndSysTpCd
							.getValue().equals(TP_CUSA_WS))) {
						continue;
					}

				}

			} else {
				// START 2018/10/17 [QC#28801, DEL]
//				// QC#7698 Regist VND_INV_ERR_WRK
//				insertVndInvErrWrk(vndInvLineWrk, errMsgId, errMsgTxt);
				// END 2018/10/17 [QC#28801, DEL]
			}
		}

		if (isError) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	/**
	 * poUpdate
	 * 
	 * @param vndInvBolWrk
	 * @param vndInvLineWrk
	 * @param poOrdNum
	 * @param poOrdDtlLineNum
	 * @param poDtlMsg
	 * @param mdseTMsg
	 * @return
	 */
	private boolean poUpdate(VND_INV_BOL_WRKTMsg vndInvBolWrk,
			VND_INV_LINE_WRKTMsg vndInvLineWrk, String poOrdNum,
			String poOrdDtlLineNum, PO_DTLTMsg poDtlMsg, MDSETMsg mdseTMsg) {

		if (poDtlMsg == null) {
			return false;
		}

		if (PO_STS.CLOSED.equals(poDtlMsg.poStsCd.getValue())
				|| PO_STS.CANCELLED.equals(poDtlMsg.poStsCd.getValue())
				|| PO_STS.SAVED.equals(poDtlMsg.poStsCd.getValue())
				|| PO_STS.WAITING_FOR_APPROVAL.equals(poDtlMsg.poStsCd
						.getValue())) {
			return true;
		}

		if (mdseTMsg == null) {
			// START 2018/10/17 [QC#28801, ADD]
			StringBuilder sbKey = new StringBuilder();
			sbKey.append(VND_INV_NUM);
			sbKey.append(MSG_TXT_EQUALS);
			sbKey.append(poDtlMsg.poOrdNum.getValue());
			sbKey.append(MSG_TXT_DELIMITER);
			sbKey.append(VND_INV_BOL_LINE_NUM);
			sbKey.append(MSG_TXT_EQUALS);
			sbKey.append(poDtlMsg.poOrdDtlLineNum.getValue());

			String message = S21MessageFunc.clspGetMessage(MSG_ID_NLAM1276E,
					new String[] { poDtlMsg.mdseCd.getValue(),
					poDtlMsg.getTableName(), sbKey.toString() });
			insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1276E, message);
			// END 2018/10/17 [QC#28801, ADD]
			return false;
		}

        //#QC14858(Sol#060) MOD START
        //PO Rcv Qty should not be updated when PO lile Type is Expense w/Receipt.
		if (!ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue()) //
		        || (PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(poDtlMsg.poLineTpCd.getValue()))){
        //#QC14858(Sol#060) MOD END
			return true;
		}

		// in the case of a parent of Set goods of CUSA, processing
		// excluded
		if (poDtlMsg.poMdseCmpsnTpCd != null
				&& VAL_PARENT.equals(poDtlMsg.poMdseCmpsnTpCd.getValue())) {
			return true;
		}

		if (!callPoUpdateApi(vndInvLineWrk, poOrdNum, poOrdDtlLineNum,
				poDtlMsg, PO_STS.RECEIVING)) {
			return false;
		}

		poDtlMsg = getPoDtlForUpdate(poOrdNum, poOrdDtlLineNum);

		// check PO Balance
		if (BigDecimal.ZERO.compareTo(poDtlMsg.poBalQty.getValue()) == 0) {
			if (!callPoUpdateApi(vndInvLineWrk, poOrdNum, poOrdDtlLineNum,
					poDtlMsg, PO_STS.RECEIVING_COMPLETION)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * callPoUpdateApi
	 */
	private boolean callPoUpdateApi(VND_INV_LINE_WRKTMsg vndInvLineWrk,
			String poOrdNum, String poOrdDtlLineNum, PO_DTLTMsg poDtlMsg,
			String poStsCd) {

		NPZC004001PMsg pMsg = new NPZC004001PMsg();
		ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
		ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, poOrdNum);
		ZYPEZDItemValueSetter.setValue(pMsg.poStsCd, poStsCd);
		ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, poDtlMsg.mdseCd);
		ZYPEZDItemValueSetter.setValue(pMsg.poRcvQty, vndInvLineWrk.shipQty);
		ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, poOrdDtlLineNum);
        // START 2023/12/22 [QC#63381, ADD]
		if (PO_MATCH_TP.NO_GOODS_RECEIPT.equals(poDtlMsg.poMatchTpCd.getValue())) {
		    // Intangible Item
		    if (!PO_MDSE_CMPSN_TP.PARENT.equals(poDtlMsg.poMdseCmpsnTpCd.getValue())) {
		        // Intangible Reguler Item
		        return true;
		    }
		}
		// END 2023/12/22 [QC#63381, ADD]
		// API Execute
		NPZC004001 apiPOUpdate = new NPZC004001();
		apiPOUpdate.execute(pMsg, ONBATCH_TYPE.BATCH);

		if (S21ApiUtil.isXxMsgId(pMsg)) {
			StringBuilder sbMsg = new StringBuilder();

			for (int i = 0; i < pMsg.xxMsgIdList.length(); i++) {
				if (!pMsg.xxMsgIdList.no(i).xxMsgId.isClear()) {
					sbMsg.append(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
					sbMsg.append(MSG_TXT_DELIMITER);
				}
			}

			StringBuilder sbKey = new StringBuilder();
			createVndInvLineWrkKeyMessage(sbKey, vndInvLineWrk.vndInvNum
					.getValue(), vndInvLineWrk.vndInvBolLineNum.getValue(),
					vndInvLineWrk.vndInvLineNum.getValue(),
					vndInvLineWrk.vndInvLineSubNum.getValue(),
					vndInvLineWrk.vndInvLineSubTrxNum.getValue());

			sbKey.append(MSG_TXT_DELIMITER);
			sbKey.append(PO_ORD_NUM);
			sbKey.append(MSG_TXT_EQUALS);
			sbKey.append(poDtlMsg.poOrdNum.getValue());
			sbKey.append(MSG_TXT_DELIMITER);

			sbKey.append(PO_ORD_DTL_LINE_NUM);
			sbKey.append(MSG_TXT_EQUALS);
			sbKey.append(poDtlMsg.poOrdDtlLineNum.getValue());

			String message = S21MessageFunc.clspGetMessage(MSG_ID_NLAM1271E,
					new String[] { API_ID_PO_UPDATE, sbMsg.toString(),
							sbKey.toString() });
			editErrorDetailMailMessage(mailSystemErrorMessageDetail, message);

			// START 2018/10/17 [QC#28801, ADD]
			insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1271E, message);
			// END 2018/10/17 [QC#28801, ADD]

			return false;
		}

		return true;
	}

	/**
	 * vndInvBolWrkUpdate
	 * 
	 * @param vndInvBolWrk
	 */
	private void vndInvBolWrkUpdate(VND_INV_BOL_WRKTMsg vndInvBolWrk) {
		EZDTBLAccessor.update(vndInvBolWrk);
	}

	/**
	 * vndInvWrkStsUpdate
	 * 
	 * @param vndInvWrk
	 * @param vndInvProcStsCd
	 */
	private void vndInvWrkStsUpdate(VND_INV_WRKTMsg vndInvWrk,
			String vndInvProcStsCd) {
		ZYPEZDItemValueSetter.setValue(vndInvWrk.vndInvProcStsCd,
				vndInvProcStsCd);
		EZDTBLAccessor.update(vndInvWrk);
	}

	/**
	 * vndInvWrkVndCdUpdate
	 * 
	 * @param vndInvWrk
	 * @param vndCd
	 */
	private void vndInvWrkVndCdUpdate(VND_INV_WRKTMsg vndInvWrk, String vndCd,
			String vndPmtTermCd, String vndPmtTermNm) {
		if (ZYPCommonFunc.hasValue(vndCd)) {
			ZYPEZDItemValueSetter.setValue(vndInvWrk.vndCd, vndCd);
		}
		if (ZYPCommonFunc.hasValue(vndPmtTermCd)) {
			ZYPEZDItemValueSetter
					.setValue(vndInvWrk.vndPmtTermCd, vndPmtTermCd);
		}
		if (ZYPCommonFunc.hasValue(vndPmtTermNm)) {
			ZYPEZDItemValueSetter
					.setValue(vndInvWrk.vndPmtTermNm, vndPmtTermNm);
		}
		EZDTBLAccessor.update(vndInvWrk);
	}

	/**
	 * vndInvLineWrkUpdate
	 * 
	 * @param vndInvLineWrk
	 */
	private void vndInvLineWrkUpdate(VND_INV_LINE_WRKTMsg vndInvLineWrk) {
		EZDTBLAccessor.update(vndInvLineWrk);
	}

	/**
	 * vndInvLicAccsWrkUpdate
	 * 
	 * @param vndInvLicAccsWrk
	 */
	private void vndInvLicAccsWrkUpdate(
			VND_INV_LIC_ACCS_WRKTMsg vndInvLicAccsWrk) {
		EZDTBLAccessor.update(vndInvLicAccsWrk);
	}

	/**
	 * getMdseFromAslDtl
	 * 
	 * @param vndCd
	 * @param vndMdseCd
	 * @return
	 */
	private List<String> getMdseFromAslDtl(String vndCd, String vndMdseCd) {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("glblCmpyCd", this.glblCmpyCd);
		queryParam.put("vndCd", vndCd);
		queryParam.put("splyItemNum", vndMdseCd);
		queryParam.put("slsDt", this.slsDt);
		List aslMdseList = this.ssmBatchClient.queryObjectList(
				"getMdseFromAslDtl", queryParam);
		return aslMdseList;

	}

	/**
	 * getGlblCmpyInfo
	 * 
	 * @return
	 */
	private GLBL_CMPYTMsg getGlblCmpyInfo() {
		GLBL_CMPYTMsg paramMsg = new GLBL_CMPYTMsg();
		paramMsg.glblCmpyCd.setValue(this.glblCmpyCd);
		GLBL_CMPYTMsg out = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(paramMsg);
		return out;
	}

	/**
	 * getTargetVndInvWrk
	 * 
	 * @return
	 */
	private VND_INV_WRKTMsgArray getTargetVndInvWrk() {
		VND_INV_WRKTMsg inMsg = new VND_INV_WRKTMsg();
		if (ZYPCommonFunc.hasValue(this.interfaceId)) {
			inMsg.setSQLID("001");
			inMsg.setConditionValue("itrlIntfcId01", this.interfaceId);
		} else {
			inMsg.setSQLID("003");
		}
		inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
		inMsg.setConditionValue("vndInvProcStsCd01", VND_INV_PROC_STS.SAVED);

		VND_INV_WRKTMsgArray rtrnTMsgArray = (VND_INV_WRKTMsgArray) EZDTBLAccessor
				.findByConditionForUpdateWait(inMsg);

		return rtrnTMsgArray;
	}

	/**
	 * getPrntCmpyVnd
	 * 
	 * @param prntCmpyVndCd01
	 * @return
	 */
	private PRNT_CMPY_VNDTMsg getPrntCmpyVnd(String prntCmpyVndCd) {
		PRNT_CMPY_VNDTMsg paramMsg = new PRNT_CMPY_VNDTMsg();
		paramMsg.glblCmpyCd.setValue(this.glblCmpyCd);
		paramMsg.prntCmpyVndCd.setValue(prntCmpyVndCd);
		PRNT_CMPY_VNDTMsg out = (PRNT_CMPY_VNDTMsg) EZDTBLAccessor
				.findByKey(paramMsg);

		return out;
	}

	/**
	 * getTargetVndInvBolWrk
	 * 
	 * @param invNum
	 * @return
	 */
	private VND_INV_BOL_WRKTMsgArray getTargetVndInvBolWrk(String invNum) {
		VND_INV_BOL_WRKTMsg inMsg = new VND_INV_BOL_WRKTMsg();
		inMsg.setSQLID("002");
		inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
		inMsg.setConditionValue("vndInvNum01", invNum);

		VND_INV_BOL_WRKTMsgArray rtrnTMsgArray = (VND_INV_BOL_WRKTMsgArray) EZDTBLAccessor
				.findByConditionForUpdateWait(inMsg);

		return rtrnTMsgArray;
	}

	/**
	 * getTargetVndInvLineWrk
	 * 
	 * @param invNum
	 * @param invBolNum
	 * @return
	 */
	private VND_INV_LINE_WRKTMsgArray getTargetVndInvLineWrk(String invNum,
			String invBolNum) {
		VND_INV_LINE_WRKTMsg inMsg = new VND_INV_LINE_WRKTMsg();
		inMsg.setSQLID("003");
		inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
		inMsg.setConditionValue("vndInvNum01", invNum);
		inMsg.setConditionValue("vndInvBolLineNum01", invBolNum);
		VND_INV_LINE_WRKTMsgArray rtrnTMsgArray = (VND_INV_LINE_WRKTMsgArray) EZDTBLAccessor
				.findByConditionForUpdateWait(inMsg);

		return rtrnTMsgArray;
	}

	/**
	 * getTargetVndInvLicAccsWrk
	 * 
	 * @param invNum
	 * @param bolNum
	 * @param lineNum
	 * @return
	 */
	private VND_INV_LIC_ACCS_WRKTMsgArray getTargetVndInvLicAccsWrk(
			String invNum, String bolNum, String lineNum) {
		VND_INV_LIC_ACCS_WRKTMsg inMsg = new VND_INV_LIC_ACCS_WRKTMsg();
		inMsg.setSQLID("003");
		inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
		inMsg.setConditionValue("vndInvNum01", invNum);
		inMsg.setConditionValue("vndInvBolLineNum01", bolNum);
		inMsg.setConditionValue("vndInvLineNum01", lineNum);
		VND_INV_LIC_ACCS_WRKTMsgArray rtrnTMsgArray = (VND_INV_LIC_ACCS_WRKTMsgArray) EZDTBLAccessor
				.findByCondition(inMsg);

		return rtrnTMsgArray;
	}

	/**
	 * getMdse
	 * 
	 * @param mdseCd
	 * @return
	 */
	private MDSETMsg getMdse(String mdseCd) {

		if (!ZYPCommonFunc.hasValue(mdseCd)) {
			return null;
		}

		MDSETMsg paramMsg = new MDSETMsg();
		paramMsg.glblCmpyCd.setValue(this.glblCmpyCd);
		paramMsg.mdseCd.setValue(mdseCd);
		MDSETMsg mdseMsg = (MDSETMsg) EZDTBLAccessor.findByKey(paramMsg);

		if (mdseMsg != null) {
			return mdseMsg;
		} else {
			return null;
		}
	}

	/**
	 * getPo
	 * 
	 * @param poOrdNum
	 * @return
	 */
	private POTMsg getPo(String poOrdNum) {
		POTMsg inPoTMsg = new POTMsg();
		if (!ZYPCommonFunc.hasValue(poOrdNum)
				|| inPoTMsg.getAttr("poOrdNum").getDigit() < poOrdNum.length()) {
			return null;
		}
		inPoTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
		inPoTMsg.poOrdNum.setValue(poOrdNum);
		POTMsg result = (POTMsg) EZDTBLAccessor.findByKey(inPoTMsg);
		return result;
	}

	/**
	 * getPoDtlUsePoLine
	 * 
	 * @param poOrdNum
	 * @param poLineNum
	 * @return
	 */
	private PO_DTLTMsg getPoDtlUsePoLine(String poOrdNum, String poLineNum) {
		if (!ZYPCommonFunc.hasValue(poLineNum)) {
			return null;
		}
		PO_DTLTMsg inPoDtlTMsg = new PO_DTLTMsg();
		inPoDtlTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
		inPoDtlTMsg.poOrdNum.setValue(poOrdNum);
		inPoDtlTMsg.poOrdDtlLineNum.setValue(poLineNum);
		PO_DTLTMsg result = (PO_DTLTMsg) EZDTBLAccessor.findByKey(inPoDtlTMsg);
		return result;
	}

	/**
	 * getPoDtlForUpdate
	 * 
	 * @param poOrdNum
	 * @param poDrdDtlLineNum
	 * @return
	 */
	private PO_DTLTMsg getPoDtlForUpdate(String poOrdNum, String poDrdDtlLineNum) {
		PO_DTLTMsg paramMsg = new PO_DTLTMsg();
		paramMsg.glblCmpyCd.setValue(this.glblCmpyCd);
		paramMsg.poOrdNum.setValue(poOrdNum);
		paramMsg.poOrdDtlLineNum.setValue(poDrdDtlLineNum);
		PO_DTLTMsg poDtlMsg = (PO_DTLTMsg) EZDTBLAccessor
				.findByKeyForUpdate(paramMsg);

		return poDtlMsg;
	}

	/**
	 * getPoDtl
	 * 
	 * @param vndInvWrk
	 * @param vndInvBolWrk
	 * @param vndInvLineWrk
	 * @return
	 */
	private PO_DTLTMsg getPoDtl(VND_INV_WRKTMsg vndInvWrk,
			VND_INV_BOL_WRKTMsg vndInvBolWrk, VND_INV_LINE_WRKTMsg vndInvLineWrk) {

		String poLineNum = null;
		String poStsCd = null;
		String poOrdNum = vndInvBolWrk.poOrdNum.getValue();
		String poDtlMdseCd = null;

		// /////////////////////////////////////////////////////////
		// Get PO Line From EDI
		// Call NPZC110001 API
		NPZC110001PMsg pMsg = new NPZC110001PMsg();
		ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
		ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
		ZYPEZDItemValueSetter.setValue(pMsg.ediPoOrdNum,
				vndInvBolWrk.ediPoOrdNum);
		ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum_I, vndInvBolWrk.poOrdNum);

		ZYPEZDItemValueSetter.setValue(
				pMsg.poDetailInList.no(0).ediPoOrdDtlLineNum,
				vndInvLineWrk.ediPoOrdDtlLineNum);
		ZYPEZDItemValueSetter.setValue(
				pMsg.poDetailInList.no(0).poOrdDtlLineNum,
				vndInvLineWrk.poOrdDtlLineNum);
		ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(0).mdseCd,
				vndInvLineWrk.mdseCd);

		pMsg.poDetailInList.setValidCount(1);

		NPZC110001 apiGetPoLine = new NPZC110001();
		apiGetPoLine.execute(pMsg, ONBATCH_TYPE.BATCH);

		// Presence check of PO_DTL
		if (!ZYPCommonFunc.hasValue(pMsg.poOrdNum_O)) {
			// START 2018/10/17 [QC#28801, ADD]
			if (S21ApiUtil.isXxMsgId(pMsg)) {
				List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
				String errMsgId = xxMsgIdList.get(0);
				insertVndInvErrWrk(vndInvLineWrk, errMsgId, S21MessageFunc.clspGetMessage(errMsgId));
			}
			// END 2018/10/17 [QC#28801, ADD]
			return null;
		} else if (pMsg.poDetailOutList.getValidCount() == 0) {
			// START 2018/10/17 [QC#28801, ADD]
			if (S21ApiUtil.isXxMsgId(pMsg)) {
				List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
				String errMsgId = xxMsgIdList.get(0);
				insertVndInvErrWrk(vndInvLineWrk, errMsgId, S21MessageFunc.clspGetMessage(errMsgId));
			}
			// END 2018/10/17 [QC#28801, ADD]
			return null;
		} else if (!ZYPCommonFunc
				.hasValue(pMsg.poDetailOutList.no(0).poOrdDtlLineNum)) {
			// START 2018/10/17 [QC#28801, ADD]
			if (S21ApiUtil.isXxMsgId(pMsg)) {
				List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
				String errMsgId = xxMsgIdList.get(0);
				insertVndInvErrWrk(vndInvLineWrk, errMsgId, S21MessageFunc.clspGetMessage(errMsgId));
			} else {
				for (int i = 0; i < pMsg.poDetailOutList.getValidCount(); i++) {
					NPZC110001_poDetailOutListPMsg poDtlPmg = (NPZC110001_poDetailOutListPMsg) pMsg.poDetailOutList.get(i);
					if (ZYPCommonFunc.hasValue(poDtlPmg.xxMsgId)) {
						String errMsgId = poDtlPmg.xxMsgId.getValue();
						insertVndInvErrWrk(vndInvLineWrk, errMsgId, S21MessageFunc.clspGetMessage(errMsgId));
						break;
					}
				}
			}
			// END 2018/10/17 [QC#28801, ADD]
			return null;
		} else {
			poOrdNum = pMsg.poOrdNum_O.getValue();
			poLineNum = pMsg.poDetailOutList.no(0).poOrdDtlLineNum.getValue();
		}
		// Get PO_DTL
		PO_DTLTMsg poDtlTMsg = getPoDtlUsePoLine(poOrdNum, poLineNum);

		// Error Check
		StringBuilder poDtlKey = new StringBuilder();
		// poDtlKey = new StringBuilder();
		createVndInvLineWrkKeyMessage(poDtlKey, vndInvWrk.vndInvNum.getValue(),
				vndInvLineWrk.vndInvBolLineNum.getValue(),
				vndInvLineWrk.vndInvLineNum.getValue(),
				vndInvLineWrk.vndInvLineSubNum.getValue(),
				vndInvLineWrk.vndInvLineSubTrxNum.getValue());
		poDtlKey.append(MSG_TXT_DELIMITER);
		poDtlKey.append(MSG_TXT_EDI_PO_NUM);
		poDtlKey.append(MSG_TXT_EQUALS);
		poDtlKey.append(vndInvBolWrk.ediPoOrdNum.getValue());
		poDtlKey.append(MSG_TXT_DELIMITER);
		poDtlKey.append(MSG_TXT_EDI_LINE_NUM);
		poDtlKey.append(MSG_TXT_EQUALS);
		poDtlKey.append(getSubMsgItemValue(vndInvLineWrk.ediPoOrdDtlLineNum
				.getValue(), this.ediLineNumLength));
		poDtlKey.append(MSG_TXT_DELIMITER);
		poDtlKey.append(MSG_TXT_PO_NUM);
		poDtlKey.append(MSG_TXT_EQUALS);
		poDtlKey.append(poOrdNum);
		poDtlKey.append(MSG_TXT_DELIMITER);
		poDtlKey.append(MSG_TXT_LINE_NUM);
		poDtlKey.append(MSG_TXT_EQUALS);
		poDtlKey.append(getSubMsgItemValue(poLineNum, this.poLineNumLength));
		poDtlKey.append(MSG_TXT_DELIMITER);
		poDtlKey.append(MSG_TXT_MDSE_CD);
		poDtlKey.append(MSG_TXT_EQUALS);
		poDtlKey.append(vndInvLineWrk.mdseCd.getValue());

		if (poDtlTMsg == null) {
			String message = S21MessageFunc.clspGetMessage(MSG_ID_NLAM1280E,
					new String[] { poDtlKey.toString() });
			editErrorDetailMailMessage(mailBusinessErrorMessageDetail, message);
			// START 2018/10/17 [QC#28801, ADD]
			insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1280E, message);
			// END 2018/10/17 [QC#28801, ADD]
			return null;
		} else {
			poLineNum = poDtlTMsg.poOrdDtlLineNum.getValue();
			ZYPEZDItemValueSetter.setValue(vndInvLineWrk.poOrdDtlLineNum,
					poLineNum);
			poStsCd = poDtlTMsg.poStsCd.getValue();
			poDtlMdseCd = poDtlTMsg.mdseCd.getValue();
		}

		// PO_STS is "Canceled", "Saved", "Waiting for Approval", in
		// the case of blank and error. (However, the process
		// continues)
		if (ZYPCommonFunc.hasValue(poStsCd)) {
			if (PO_STS.CANCELLED.equals(poStsCd)) {
				String message = S21MessageFunc.clspGetMessage(
						MSG_ID_NLAM1282E, new String[] { poDtlKey.toString() });
				editErrorDetailMailMessage(mailBusinessErrorMessageDetail,
						message);
				// START 2018/10/17 [QC#28801, ADD]
				insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1282E, message);
				// END 2018/10/17 [QC#28801, ADD]
				return null;
			} else if (PO_STS.SAVED.equals(poStsCd)) {
				String message = S21MessageFunc.clspGetMessage(
						MSG_ID_NLAM1305E, new String[] { poDtlKey.toString() });
				editErrorDetailMailMessage(mailBusinessErrorMessageDetail,
						message);
				// START 2018/10/17 [QC#28801, ADD]
				insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1305E, message);
				// END 2018/10/17 [QC#28801, ADD]
				return null;
			} else if (PO_STS.WAITING_FOR_APPROVAL.equals(poStsCd)) {
				String message = S21MessageFunc.clspGetMessage(
						MSG_ID_NLAM1306E, new String[] { poDtlKey.toString() });
				editErrorDetailMailMessage(mailBusinessErrorMessageDetail,
						message);
				// START 2018/10/17 [QC#28801, ADD]
				insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1306E, message);
				// END 2018/10/17 [QC#28801, ADD]
				return null;
			} else {
				if (ZYPCommonFunc.hasValue(pMsg.poDetailOutList.no(0).xxMsgId)) {
					poDtlKey.append(MSG_TXT_DELIMITER);
					poDtlKey.append(MSG_TXT_PO_DTL_MDSE_CD);
					poDtlKey.append(MSG_TXT_EQUALS);
					poDtlKey.append(poDtlMdseCd);
					String message = S21MessageFunc.clspGetMessage(
							MSG_ID_NLAM1284E, new String[] { poDtlKey
									.toString() });
					editErrorDetailMailMessage(mailBusinessErrorMessageDetail,
							message);
					// START 2018/10/17 [QC#28801, ADD]
					insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1284E, message);
					// END 2018/10/17 [QC#28801, ADD]
					return null;
				}
			}
		} else {
			String message = S21MessageFunc.clspGetMessage(MSG_ID_NLAM1283E,
					new String[] { poDtlKey.toString() });
			editErrorDetailMailMessage(mailBusinessErrorMessageDetail, message);
			// START 2018/10/17 [QC#28801, ADD]
			insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1283E, message);
			// END 2018/10/17 [QC#28801, ADD]
			return null;
		}

		return poDtlTMsg;
	}

	/**
	 * checkVndInvWrk
	 * 
	 * @param invNum
	 * @param vndInvWrk
	 * @return
	 */
	private boolean checkVndInvWrk(String invNum, VND_INV_WRKTMsg vndInvWrk) {
		boolean execFlg = true;
		StringBuilder sbKey = new StringBuilder();
		sbKey.append(VND_INV_NUM);
		sbKey.append(MSG_TXT_EQUALS);
		sbKey.append(invNum);

		if (!ZYPCommonFunc.hasValue(vndInvWrk.invDt)) {
			String message = S21MessageFunc.clspGetMessage(MSG_ID_NLAM1275E,
					new String[] { INV_DT, VND_INV_WRK, sbKey.toString() });
			editErrorDetailMailMessage(mailSystemErrorMessageDetail, message);
			execFlg = false;
		}
		if (!ZYPCommonFunc.hasValue(vndInvWrk.dealCcyCd)) {
			String message = S21MessageFunc
					.clspGetMessage(MSG_ID_NLAM1275E, new String[] {
							DEAL_CCY_CD, VND_INV_WRK, sbKey.toString() });
			editErrorDetailMailMessage(mailSystemErrorMessageDetail, message);
			execFlg = false;
		}
		return execFlg;
	}

	/**
	 * checkVndInvLineWrk
	 * 
	 * @param vndCd
	 * @param invNum
	 * @param vndInvLineWrk
	 * @return
	 * @throws SQLException
	 */
	private boolean checkVndInvLineWrk(POTMsg poTMsg, String invNum,
			VND_INV_LINE_WRKTMsg vndInvLineWrk) throws SQLException {

		StringBuilder sbKey = new StringBuilder();
		createVndInvLineWrkKeyMessage(sbKey, invNum,
				vndInvLineWrk.vndInvBolLineNum.getValue(),
				vndInvLineWrk.vndInvLineNum.getValue(),
				vndInvLineWrk.vndInvLineSubNum.getValue(),
				vndInvLineWrk.vndInvLineSubTrxNum.getValue());

		// Convert VND_INV_LINE_WRK.VND_MDSE_CD to CSA_MDSE_CD
		String csaMdseCode = null;

		// START 2019/03/25 [QC#30850, ADD]
		if (poTMsg != null && ZYPCommonFunc.hasValue(poTMsg.vndCd)
		        && ZYPCommonFunc.hasValue(vndInvLineWrk.mdseCd) && !ZYPCommonFunc.hasValue(vndInvLineWrk.vndMdseCd)) {

	        if (!ZYPCommonFunc.hasValue(vndInvLineWrk.shipQty)) {
	            String message = S21MessageFunc
	                    .clspGetMessage(MSG_ID_NLAM1275E, new String[] { SHIP_QTY,
	                            VND_INV_LINE_WRK, sbKey.toString() });
	            editErrorDetailMailMessage(mailSystemErrorMessageDetail, message);
	            // START 2018/10/17 [QC#28801, ADD]
	            insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1275E, message);
	            // END 2018/10/17 [QC#28801, ADD]
	            return false;
	        }
	        return true;
		}
        // END   2019/03/25 [QC#30850, ADD]

		if (poTMsg != null && ZYPCommonFunc.hasValue(poTMsg.vndCd)) {
			List aslMdseCd = getMdseFromAslDtl(poTMsg.vndCd.getValue(),
					vndInvLineWrk.vndMdseCd.getValue());
            // START 2018/11/14 M.Naito [QC#29047,ADD]
            String vndSysTpCd = getVndSysTpCd(poTMsg.vndCd.getValue());
            // END 2018/11/14 M.Naito [QC#29047,ADD]
			if (aslMdseCd == null || aslMdseCd.size() == 0) {
				csaMdseCode = vndInvLineWrk.vndMdseCd.getValue();
                // START 2018/11/14 M.Naito [QC#29047,ADD]
                if (ZYPCommonFunc.hasValue(vndSysTpCd) && VND_SYS_TP.TST_IMPRESO.equals(vndSysTpCd)) {
                    csaMdseCode = vndInvLineWrk.mdseCd.getValue();
                }
	            // END 2018/11/14 M.Naito [QC#29047,ADD]
			} else if (aslMdseCd.size() > 1) {

				// START 08/01/2017 R.Shimamoto QC#18671 Mod.
				// // Error
				// return false;
				// get CSA Mdse Code
				csaMdseCode = getCsaMdseCd(aslMdseCd, poTMsg);
				if (!ZYPCommonFunc.hasValue(csaMdseCode)) {
					// START 2018/10/17 [QC#28801, ADD]
					String message = S21MessageFunc.clspGetMessage(MSG_ID_NLAM1276E,
							new String[] { vndInvLineWrk.vndMdseCd.getValue(),
									VND_INV_LINE_WRK, sbKey.toString() });
					insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1276E, message);
					// END 2018/10/17 [QC#28801, ADD]
					// Error
					return false;
				}
				// END 08/01/2017 R.Shimamoto QC#18671 Mod.

			} else if (aslMdseCd.size() == 1) {
				csaMdseCode = aslMdseCd.get(0).toString();
			}
		} else {
			csaMdseCode = vndInvLineWrk.vndMdseCd.getValue();
		}

		// CSA_MDSE_CD Master Check
		if (getMdse(csaMdseCode) == null) {
            // START 2019/02/25 [QC#30499, MOD]
			// String message = S21MessageFunc.clspGetMessage(MSG_ID_NLAM1276E,
			//		new String[] { vndInvLineWrk.mdseCd.getValue(),
			//				VND_INV_LINE_WRK, sbKey.toString() });
            // START 2019/02/27 [QC#30499, MOD]
            // String message = S21MessageFunc.clspGetMessage(MSG_ID_NLAM1276E,
            //      new String[] { vndInvLineWrk.vndMdseCd.getValue(),
            //              VND_INV_LINE_WRK, sbKey.toString() });
            String message = S21MessageFunc.clspGetMessage(MSG_ID_NLAM1276E,
                    new String[] { csaMdseCode,VND_INV_LINE_WRK, sbKey.toString() });
            // END   2019/02/25 [QC#30499, MOD]
            // END   2019/02/25 [QC#30499, MODD]
			editErrorDetailMailMessage(mailBusinessErrorMessageDetail, message);
			// START 2018/10/17 [QC#28801, ADD]
			insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1276E, message);
			// END 2018/10/17 [QC#28801, ADD]
			return false;
		}
		if (!ZYPCommonFunc.hasValue(vndInvLineWrk.shipQty)) {
			String message = S21MessageFunc
					.clspGetMessage(MSG_ID_NLAM1275E, new String[] { SHIP_QTY,
							VND_INV_LINE_WRK, sbKey.toString() });
			editErrorDetailMailMessage(mailSystemErrorMessageDetail, message);
			// START 2018/10/17 [QC#28801, ADD]
			insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1275E, message);
			// END 2018/10/17 [QC#28801, ADD]
			return false;
		}
		ZYPEZDItemValueSetter.setValue(vndInvLineWrk.mdseCd, csaMdseCode);
		return true;
	}

	/**
	 * checkVndInvLicAccsWrk
	 * 
	 * @param vndCd
	 * @param invNum
	 * @param vndInvLineWrk
	 * @return
	 * @throws SQLException
	 */
	private boolean checkVndInvLicAccsWrk(String vndCd, String invNum,
			VND_INV_LINE_WRKTMsg vndInvLineWrk,
			VND_INV_LIC_ACCS_WRKTMsgArray vndInvLicAccsWrkArray, POTMsg poTMsg)
			throws SQLException {
		boolean execFlg = true;

		int loopCount = vndInvLicAccsWrkArray.length();
		VND_INV_LIC_ACCS_WRKTMsg vndInvLicAccsWrk = null;
		for (int i = 0; i < loopCount; i++) {

			vndInvLicAccsWrk = vndInvLicAccsWrkArray.no(i);

			String csaMdseCode = null;
			List aslMdseCd = getMdseFromAslDtl(vndCd,
					vndInvLicAccsWrk.vndMdseCd.getValue());
			if (aslMdseCd == null || aslMdseCd.size() == 0) {
				ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.mdseCd,
						vndInvLicAccsWrk.vndMdseCd.getValue());
			} else if (aslMdseCd.size() > 1) {
				// START 08/01/2017 R.Shimamoto QC#18671 Mod.
//				return false;
				csaMdseCode = getCsaMdseCd(aslMdseCd, poTMsg);
				if (!ZYPCommonFunc.hasValue(csaMdseCode)) {
					// START 2018/10/17 [QC#28801, ADD]
					StringBuilder sbKey = new StringBuilder();
					createVndInvLicAccsWrkKeyMessage(sbKey, invNum, vndInvLicAccsWrk);

					String message = S21MessageFunc.clspGetMessage(
							MSG_ID_NLAM1276E, new String[] {
									vndInvLicAccsWrk.mdseCd.getValue(),
									VND_INV_LIC_ACCS_WRK, sbKey.toString() });
					insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1276E, message);
					// END 2018/10/17 [QC#28801, ADD]
					// Error
					return false;
				}
				ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.mdseCd, csaMdseCode);
				// END 08/01/2017 R.Shimamoto QC#18671 Mod.
			} else if (aslMdseCd.size() == 1) {
				ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.mdseCd,
						aslMdseCd.get(0).toString());
			}

			if (getMdse(vndInvLicAccsWrk.mdseCd.getValue()) == null) {

				StringBuilder sbKey = new StringBuilder();
				// START 2018/10/17 [QC#28801, MOD]
//				sbKey.append(VND_INV_NUM);
//				sbKey.append(MSG_TXT_EQUALS);
//				sbKey.append(invNum);
//				sbKey.append(MSG_TXT_DELIMITER);
//				sbKey.append(VND_INV_BOL_LINE_NUM);
//				sbKey.append(MSG_TXT_EQUALS);
//				sbKey.append(vndInvLicAccsWrk.vndInvBolLineNum.getValue());
//				sbKey.append(MSG_TXT_DELIMITER);
//				sbKey.append(VND_INV_LINE_NUM);
//				sbKey.append(MSG_TXT_EQUALS);
//				sbKey.append(vndInvLicAccsWrk.vndInvLineNum.getValue());
//				sbKey.append(MSG_TXT_DELIMITER);
//				sbKey.append(VND_INV_LINE_SUB_NUM);
//				sbKey.append(MSG_TXT_EQUALS);
//				sbKey.append(vndInvLicAccsWrk.vndInvLineSubNum.getValue());
//				sbKey.append(MSG_TXT_DELIMITER);
//				sbKey.append(VND_INV_LINE_SUB_TRX_NUM);
//				sbKey.append(MSG_TXT_EQUALS);
//				sbKey.append(vndInvLicAccsWrk.vndInvLineSubTrxNum.getValue());
				createVndInvLicAccsWrkKeyMessage(sbKey, invNum, vndInvLicAccsWrk);
				// END 2018/10/17 [QC#28801, MOD]

				String message = S21MessageFunc.clspGetMessage(
						MSG_ID_NLAM1276E, new String[] {
								vndInvLicAccsWrk.mdseCd.getValue(),
								VND_INV_LIC_ACCS_WRK, sbKey.toString() });
				editErrorDetailMailMessage(mailBusinessErrorMessageDetail,
						message);
				// START 2018/10/17 [QC#28801, ADD]
				insertVndInvErrWrk(vndInvLineWrk, MSG_ID_NLAM1276E, message);
				// END 2018/10/17 [QC#28801, ADD]
				execFlg = false;
			} else {
				// Update [VND_INV_LIC_ACCS_WRK.MDSE_CD]
				vndInvLicAccsWrkUpdate(vndInvLicAccsWrk);
			}
		}
		return execFlg;
	}

	/**
	 * checkVndCd
	 * 
	 * @param vndCdLists
	 * @return
	 */
	private String checkVndCd() {

		String cur = null;
		String prev = null;

		for (int ii = 0; ii < this.vndCdList.size(); ii++) {
			cur = this.vndCdList.get(ii);
			if (cur != null && prev != null) {
				if (!cur.equals(prev)) {
					// Check Error
					return null;
				}
			}

			prev = cur;
		}

		return cur;
	}

	/**
	 * editItemErrorMailMessage
	 * 
	 * @param mailMessage
	 * @param mailMessageDetail
	 * @param invNum
	 */
	private void editItemErrorMailMessage(StringBuilder mailMessage,
			StringBuilder mailMessageDetail, String invNum) {
		mailMessage.append(ZYPCommonFunc.leftPad("", STRING_LENGTH_100,
				MSG_TXT_HYPHEN));
		mailMessage.append(ERR_MSG_CRLF);
		mailMessage.append(ZYPCommonFunc.leftPad(MSG_TXT_SPACE,
				STRING_LENGTH_4, MSG_TXT_SPACE));
		mailMessage.append(ZYPCommonFunc.rightPad(this.interfaceId,
				STRING_LENGTH_30, MSG_TXT_SPACE));
		mailMessage.append(ZYPCommonFunc.leftPad(MSG_TXT_SPACE,
				STRING_LENGTH_2, MSG_TXT_SPACE));
		mailMessage.append(ZYPCommonFunc.rightPad(MSG_TXT_SPACE,
				STRING_LENGTH_30, MSG_TXT_SPACE));
		mailMessage.append(ZYPCommonFunc.leftPad(MSG_TXT_SPACE,
				STRING_LENGTH_2, MSG_TXT_SPACE));
		mailMessage.append(ZYPCommonFunc.rightPad(invNum, STRING_LENGTH_15,
				MSG_TXT_SPACE));
		mailMessage.append(ZYPCommonFunc.leftPad(MSG_TXT_SPACE,
				STRING_LENGTH_2, MSG_TXT_SPACE));
		mailMessage.append(ERR_MSG_ITEM);
		mailMessage.append(ERR_MSG_CRLF);
		mailMessage.append(mailMessageDetail);
		mailMessage.append(ERR_MSG_CRLF);
	}

	/**
	 * editErrorDetailMailMessage
	 * 
	 * @param mailMessageDetail
	 * @param message
	 */
	private void editErrorDetailMailMessage(StringBuilder mailMessageDetail,
			String message) {
		S21InfoLogOutput.println(message);
		mailMessageDetail.append(message);
		mailMessageDetail.append(ERR_MSG_CRLF);
	}

	/**
	 * sendSystemErrorMail
	 */
	private void sendSystemErrorMail() {
		// Get To Mail Address
		S21MailGroup groupTo = new S21MailGroup(glblCmpyCd,
				MAIL_GROUP_ID_TO_SYSTEM);
		List<S21MailAddress> addrToList = groupTo.getMailAddress();
		if (addrToList.isEmpty()) {
			throw new S21AbendException(MSG_ID_NLAM1273E,
					new String[] { MAIL_GROUP_ID_TO_SYSTEM });
		}
		sendMail(addrToList, this.mailSystemErrorMessage.toString());
	}

	/**
	 * sendBusinessErrorMail
	 */
	private void sendBusinessErrorMail() {
		// Get To Mail Address
		S21MailGroup groupTo = new S21MailGroup(glblCmpyCd,
				MAIL_GROUP_ID_TO_BUSINESS);
		List<S21MailAddress> addrToList = groupTo.getMailAddress();
		if (addrToList.isEmpty()) {
			throw new S21AbendException(MSG_ID_NLAM1273E,
					new String[] { MAIL_GROUP_ID_TO_BUSINESS });
		}
		sendMail(addrToList, this.mailBusinessErrorMessage.toString());
	}

	/**
	 * Send Error Mail
	 */
	private void sendMail(List<S21MailAddress> addrToList, String mailBody) {

		// Get From Mail Address
		S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd,
				MAIL_GROUP_ID_FROM);
		List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
		S21MailAddress from = null;
		if (!addrFromList.isEmpty()) {
			from = addrFromList.get(0);
		}
		// Get Template
		S21MailTemplate template = new S21MailTemplate(glblCmpyCd,
				MAIL_TEMPLATE_ID_M001);
		if (!ZYPCommonFunc.hasValue(template.getBody())) {
			throw new S21AbendException(MSG_ID_NLAM1272E,
					new String[] { MAIL_TEMPLATE_ID_M001 });
		}

		String inFormat = template.getDefaultDateFormat()
				+ MAIL_TS_FMT.substring(MAIL_LEN_FMT_SUBSTR);
		String currentTs = ZYPDateUtil.getCurrentSystemTime(inFormat);
		ZYPLocalTimeData locTmDatlocal = ZYPLocalTimeUtil.convertTimeSys2Biz(
				currentTs, inFormat);
		currentTs = locTmDatlocal.getTime();
		template.setTemplateParameter(MAIL_FIELD_TIMESTAMP, currentTs);
		template.setTemplateParameter(MAIL_FIELD_MESSAGE, mailBody);

		// Set e-Mail
		S21Mail mail = new S21Mail(this.glblCmpyCd);
		mail.setFromAddress(from);
		mail.setToAddressList(addrToList);
		mail.setMailTemplate(template);
		mail.postMail();
	}

	/**
	 * checkPo
	 * 
	 * @param vndInvBolWrk
	 * @param poTMsg
	 * @return
	 */
	private boolean checkPo(VND_INV_BOL_WRKTMsg vndInvBolWrk, POTMsg poTMsg) {
		// check PO Start
		if (poTMsg == null) {
			return true;
		}
		StringBuilder poKey = new StringBuilder();
		poKey.append(VND_INV_NUM);
		poKey.append(MSG_TXT_EQUALS);
		poKey.append(vndInvBolWrk.vndInvNum.getValue());
		poKey.append(MSG_TXT_DELIMITER);
		poKey.append(MSG_TXT_EDI_PO_NUM);
		poKey.append(MSG_TXT_EQUALS);
		poKey.append(vndInvBolWrk.ediPoOrdNum.getValue());
		poKey.append(MSG_TXT_DELIMITER);
		poKey.append(MSG_TXT_PO_NUM);
		poKey.append(MSG_TXT_EQUALS);
		poKey.append(poTMsg.poOrdNum.getValue());

		// PO_STS is "Canceled", "Saved", "Waiting for Approval", in
		// the case of blank and error. (However, the process
		// continues)
		String poStsCd = poTMsg.poStsCd.getValue();

		if (ZYPCommonFunc.hasValue(poStsCd)) {
			if (PO_STS.CANCELLED.equals(poStsCd)) {
				String message = S21MessageFunc.clspGetMessage(
						MSG_ID_NLAM1282E, new String[] { poKey.toString() });
				editErrorDetailMailMessage(mailBusinessErrorMessageDetail,
						message);
				return false;
			} else if (PO_STS.SAVED.equals(poStsCd)) {
				String message = S21MessageFunc.clspGetMessage(
						MSG_ID_NLAM1305E, new String[] { poKey.toString() });
				editErrorDetailMailMessage(mailBusinessErrorMessageDetail,
						message);
				return false;
			} else if (PO_STS.WAITING_FOR_APPROVAL.equals(poStsCd)) {
				String message = S21MessageFunc.clspGetMessage(
						MSG_ID_NLAM1306E, new String[] { poKey.toString() });
				editErrorDetailMailMessage(mailBusinessErrorMessageDetail,
						message);
				return false;
			}
		} else {
			String message = S21MessageFunc.clspGetMessage(MSG_ID_NLAM1283E,
					new String[] { poKey.toString() });
			editErrorDetailMailMessage(mailBusinessErrorMessageDetail, message);
			return false;
		}

		// Update [VND_INV_BOL_WRK]
		ZYPEZDItemValueSetter.setValue(vndInvBolWrk.poOrdNum, poTMsg.poOrdNum);
		ZYPEZDItemValueSetter.setValue(vndInvBolWrk.poApvlDt, poTMsg.poApvlDt);
		vndInvBolWrkUpdate(vndInvBolWrk);

		// Save VND_CD
		if (ZYPCommonFunc.hasValue(poTMsg.vndCd)) {
			vndCdList.add(poTMsg.vndCd.getValue());
		}

		return true;
	}

	/**
	 * createVndInvLineWrkKeyMessage
	 * 
	 * @param sbKey
	 * @param vndInvNum
	 * @param vndInvBolLineNum
	 * @param vndInvLineNum
	 * @param vndInvLineSubNum
	 * @param vndInvLineSubTrxNum
	 */
	private void createVndInvLineWrkKeyMessage(StringBuilder sbKey,
			String vndInvNum, String vndInvBolLineNum, String vndInvLineNum,
			String vndInvLineSubNum, String vndInvLineSubTrxNum) {
		sbKey.append(INV_NUM);
		sbKey.append(MSG_TXT_EQUALS);
		sbKey.append(vndInvNum);
		sbKey.append(MSG_TXT_DELIMITER);

		sbKey.append(INV_BOL_LINE_NUM);
		sbKey.append(MSG_TXT_EQUALS);
		sbKey.append(vndInvBolLineNum);
		sbKey.append(MSG_TXT_DELIMITER);

		sbKey.append(INV_LINE_NUM);
		sbKey.append(MSG_TXT_EQUALS);
		sbKey.append(vndInvLineNum);
		sbKey.append(MSG_TXT_DELIMITER);

		sbKey.append(INV_LINE_SUB_NUM);
		sbKey.append(MSG_TXT_EQUALS);
		sbKey.append(vndInvLineSubNum);
		sbKey.append(MSG_TXT_DELIMITER);

		sbKey.append(INV_LINE_SUB_TRX_NUM);
		sbKey.append(MSG_TXT_EQUALS);
		sbKey.append(vndInvLineSubTrxNum);
	}

	// START 2018/10/17 [QC#28801, ADD]
	/**
	 * createVndInvLicAccsWrkKeyMessage
	 * 
	 * @param sbKey
	 * @param vndInvNum
	 * @param vndInvLicAccsWrk
	 */
	private void createVndInvLicAccsWrkKeyMessage(StringBuilder sbKey,
			String vndInvNum, VND_INV_LIC_ACCS_WRKTMsg vndInvLicAccsWrk) {
		sbKey.append(VND_INV_NUM);
		sbKey.append(MSG_TXT_EQUALS);
		sbKey.append(vndInvNum);
		sbKey.append(MSG_TXT_DELIMITER);
		sbKey.append(VND_INV_BOL_LINE_NUM);
		sbKey.append(MSG_TXT_EQUALS);
		sbKey.append(vndInvLicAccsWrk.vndInvBolLineNum.getValue());
		sbKey.append(MSG_TXT_DELIMITER);
		sbKey.append(VND_INV_LINE_NUM);
		sbKey.append(MSG_TXT_EQUALS);
		sbKey.append(vndInvLicAccsWrk.vndInvLineNum.getValue());
		sbKey.append(MSG_TXT_DELIMITER);
		sbKey.append(VND_INV_LINE_SUB_NUM);
		sbKey.append(MSG_TXT_EQUALS);
		sbKey.append(vndInvLicAccsWrk.vndInvLineSubNum.getValue());
		sbKey.append(MSG_TXT_DELIMITER);
		sbKey.append(VND_INV_LINE_SUB_TRX_NUM);
		sbKey.append(MSG_TXT_EQUALS);
		sbKey.append(vndInvLicAccsWrk.vndInvLineSubTrxNum.getValue());
	}
	// END 2018/10/17 [QC#28801, ADD]

	/**
	 * getSubMsgItemValue
	 * 
	 * @param value
	 * @param digit
	 * @return
	 */
	private String getSubMsgItemValue(String value, int digit) {
		if (!ZYPCommonFunc.hasValue(value)) {
			value = ZYPCommonFunc.leftPad("", digit, MSG_TXT_HYPHEN);
		}
		return value;
	}

	/**
	 * insertVndInvErrWrk
	 * 
	 * @param vndInvLineWrk
	 * @param errMsgId
	 * @param errMsgTxt
	 */
	private void insertVndInvErrWrk(VND_INV_LINE_WRKTMsg vndInvLineWrk,
			String errMsgId, String errMsgTxt) {

		VND_INV_ERR_WRKTMsg tMsg = new VND_INV_ERR_WRKTMsg();

		ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd,
				vndInvLineWrk.glblCmpyCd);

		BigDecimal vndInvErrWrkPk = ZYPOracleSeqAccessor
				.getNumberBigDecimal(ZYPOracleSeqConstant.VND_INV_ERR_WRK_SQ);
		ZYPEZDItemValueSetter.setValue(tMsg.vndInvErrWrkPk, vndInvErrWrkPk);

		ZYPEZDItemValueSetter.setValue(tMsg.vndInvNum, vndInvLineWrk.vndInvNum);
		ZYPEZDItemValueSetter.setValue(tMsg.vndInvBolLineNum,
				vndInvLineWrk.vndInvBolLineNum);
		ZYPEZDItemValueSetter.setValue(tMsg.vndInvLineNum,
				vndInvLineWrk.vndInvLineNum);
		ZYPEZDItemValueSetter.setValue(tMsg.vndInvLineSubNum,
				vndInvLineWrk.vndInvLineSubNum);
		ZYPEZDItemValueSetter.setValue(tMsg.vndInvLineSubTrxNum,
				vndInvLineWrk.vndInvLineSubTrxNum);
		ZYPEZDItemValueSetter.setValue(tMsg.vndInvErrMsgId, errMsgId);
		ZYPEZDItemValueSetter.setValue(tMsg.batErrMsgTxt, errMsgTxt);

		EZDTBLAccessor.insert(tMsg);
		if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
			throw new S21AbendException(MSG_ID_NLAM1270E, new String[] {
					"VND_INV_ERR_WRK",
					"VND_INV_ERR_WRK_PK=" + vndInvErrWrkPk.toString() });
		}
	}

	/**
	 * getVndPmtTermCdFirst
	 * 
	 * @param vndCd
	 *            String
	 * @param vndPmtTermCashDiscCd
	 *            String
	 * @return Map<String, Object>
	 */
	private Map<String, Object> getVndPmtTermCdFirst(String vndCd,
			String vndPmtTermCashDiscCd) {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("glblCmpyCd", this.glblCmpyCd);
		queryParam.put("vndCd", vndCd);
		queryParam.put("vndPmtTermCashDiscCd", vndPmtTermCashDiscCd);
		Map<String, Object> vndPmtTermMap = (Map<String, Object>) this.ssmBatchClient
				.queryObject("getVndPmtTermCdFirst", queryParam);
		return vndPmtTermMap;
	}

	/**
	 * getVndPmtTermCdSecond
	 * 
	 * @param vndCd
	 *            String
	 * @param vndPmtTermCashDiscCd
	 *            String
	 * @return Map<String, Object>
	 */
	private Map<String, Object> getVndPmtTermCdSecond(String vndCd,
			String vndPmtTermCashDiscCd) {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("glblCmpyCd", this.glblCmpyCd);
		queryParam.put("vndCd", vndCd);
		queryParam.put("vndCdAst", VAL_VND_CD_AST);
		queryParam.put("vndPmtTermCashDiscCd", vndPmtTermCashDiscCd);
		Map<String, Object> vndPmtTermMap = (Map<String, Object>) this.ssmBatchClient
				.queryObject("getVndPmtTermCdSecond", queryParam);
		return vndPmtTermMap;
	}

	/**
	 * getVndPmtTermCdByPrntVnd
	 * 
	 * @param vndCd
	 *            StringString
	 * @return Map<String, Object>
	 */
	private Map<String, Object> getVndPmtTermCdByPrntVnd(String vndCd) {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("glblCmpyCd", this.glblCmpyCd);
		queryParam.put("vndCd", vndCd);
		Map<String, Object> vndPmtTermMap = (Map<String, Object>) this.ssmBatchClient
				.queryObject("getVndPmtTermCdByPrntVnd", queryParam);
		return vndPmtTermMap;
	}

	/**
	 * 08/01/2017 R.Shimamoto QC#18671 Add.
	 * getCsaMdseCd
	 * @param aslMdseCdList
	 * @param poTMsg
	 * @return
	 */
	private String getCsaMdseCd(List aslMdseCdList, POTMsg poTMsg) {

		String csaMdseCd = null;

		if (poTMsg != null && ZYPCommonFunc.hasValue(poTMsg.poOrdNum)) {

			Map<String, Object> queryParam = new HashMap<String, Object>();
			queryParam.put("glblCmpyCd", this.glblCmpyCd);
			queryParam.put("poOrdNum", poTMsg.poOrdNum.getValue());
			List<String> poMdseList = (List<String>) this.ssmBatchClient.queryObjectList("getMdseListFromPoDtl", queryParam);

			for (String poMdseCd : poMdseList) {
				for (String aslMdseCd : (List<String>) aslMdseCdList) {
					if (poMdseCd.equals(aslMdseCd)) {
						csaMdseCd = aslMdseCd;
						break;
					}
				}
			}
		}

		return csaMdseCd;
	}

	// START 2018/11/14 M.Naito [QC#29047,ADD]
    /**
     * getVndSysTpCd
     * @param vndCd
     * @return
     */
    private String getVndSysTpCd(String vndCd) {

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("rcvAsnVndCd", vndCd);

        String vndSysTpCd = (String) ssmBatchClient.queryObject("getVndSysTpCd", paramMap);

        return vndSysTpCd;
    }
    // END 2018/11/14 M.Naito [QC#29047,ADD]

    // START 2019/04/16 [QC#31071, ADD]
    /**
     * get array from search text if it has "," in text field.
     * @param val String
     * @return ArrayList<String>
     */
    private static ArrayList<String> splitStringTxt(String val) {

        ArrayList<String> splitValList = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(val)) {

            if (val.indexOf(",") != -1) {

                String[] splitValArray = val.split(",");

                for (int i = 0; i < splitValArray.length; i++) {

                    if (!splitValArray[i].trim().equals("") && splitValArray[i].length() > 0) {

                        splitValList.add(splitValArray[i].trim());
                    }
                }
            }
        }

        return splitValList;
    }
    // END   2019/04/16 [QC#31071, ADD]
}
