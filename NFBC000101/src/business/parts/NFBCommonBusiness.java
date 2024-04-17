/*
 * Copyright (c) 2008 Canon USA Inc. All rights reserved. Original
 * Author: Akira Hosono Company: Fujitsu Limited Date: august 08, 2008
 */
package business.parts;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import parts.common.EZDBDateItem;
import parts.common.EZDCMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.CCYTMsgArray;
import business.db.CM_ACRL_WRITE_OFFTMsg;
import business.db.CM_INV_ACCT_DISTTMsg;
import business.db.CM_INV_ACTL_DTLTMsg;
import business.db.CM_INV_ACTL_DTLTMsgArray;
import business.db.CM_INV_ACTL_LGSC_DTLTMsg;
import business.db.CM_INV_ACTL_LGSC_DTLTMsgArray;
import business.db.CM_INV_ACTL_PO_HDRTMsg;
import business.db.CM_INV_HDRTMsg;
import business.db.CM_INV_IMPT_TRKTMsg;
import business.db.CM_STK_INTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * </pre>
 * 
 * @author $Author: Keiichi Onae $
 * @version $Revision: 1.1 $ $Date: 2008/12/12 16:17:45 $
 */
public class NFBCommonBusiness {

    /** yyyyMMdd */
    private static String YYYYMMDD = "yyyyMMdd";

    /** ACCT_SHPG_TERM_CD_01 */
    private static String ACCT_SHPG_TERM_CD_01 = "01";

    /** ACCT_SHPG_TERM_CD_02 */
    private static String ACCT_SHPG_TERM_CD_02 = "02";

    /** ACCT_SHPG_TERM_CD_03 */
    private static String ACCT_SHPG_TERM_CD_03 = "03";

    /** ACCT_SHPG_TERM_CD_04 */
    private static String ACCT_SHPG_TERM_CD_04 = "04";

    /** Invoice allocated Cntainer/BOL */
    static final String INV_BOL_O = "O"; //BOL Ocean
    static final String INV_BOL_A = "A"; //BOL Air
    static final String INV_CON   = "C"; //Container

    /** VND_INV_SQ_NUM */
    static final String VND_INV_SQ_NUM_00 = "00";

    /** FRT_PRCH_PMT_TERM_CD */
    /** P */
    static final String FRT_PRCH_PMT_TERM_CD_P = "P";

    /** sql statement name */
    static final String GET_OTHER_CARRIER_INVOICE = "getOtherCarrierInvoice";

    /** DB Item Column Name */
    static final String CM_COST_ELMNT_CD = "CM_COST_ELMNT_CD";
    /** DB Item Column Name */
    static final String AC_OC_INTL_FRT_COST_AMT = "AC_OC_INTL_FRT_COST_AMT";
    /** DB Item Column Name */
    static final String AC_OC_DELY_COST_AMT = "AC_OC_DELY_COST_AMT";

    /** Warning Message */
    static final String NFBM0170W = "NFBM0170W";
    /** Warning Message */
    static final String NFBM0171W = "NFBM0171W";
    /** Warning Message */
    static final String NFBM0148W = "NFBM0148W";
    /** Warning Message */
    static final String NFBM0183W = "NFBM0183W";
    /** Warning Message */
    static final String NFBM0146E = "NFBM0146E";
    /** Invoice # : @ is not found in Preset Invoice */
    static final String NFBM0003E = "NFBM0003E";

    /** Error Message **/
    static final String NFBM0028E = "NFBM0028E";
    /** Error Message (The mail template cannot be acquired.  <Template ID: [@]>) **/
    static final String NFBM0184E = "NFBM0184E";
    /** Error Message (The field of [@] is illegal.) **/
    static final String ZZMM0007E = "ZZMM0007E";

    /** EXIST */
    static final String strNone = "";

    /** Cost Element Value */
    static final int COST_ELEMENT = 23;

    /** Invoice Sequence Number */
    /** 00 */
    static final String VND_INV_SEQ_NUM_00 = "00";

//  fixed CM_ACTL_INV_TP_CD
    /** D */
    static final String CM_ACTL_INV_TP_CD_D = "D";

    /** E */
    static final String CM_ACTL_INV_TP_CD_E = "E";

    /** AP Vendor Code */
    /** 17071 */
    static final String AP_VND_CD_17071 = "17071";

    /** CM_AP_PNT_CD */
    /** B */
    static final String CM_AP_PNT_CD_B = "B";

//  fixed ACCT_BANK_CD
    /** 11 */
    static final String ACCT_BANK_CD_01 = "01";

//  fixed ACCT_BANK_ACCT_PAY_TP_CD
    /** 0 */
    static final String ACCT_BANK_ACCT_PAY_TP_CD_1 = "1";

    /** CM_ACTL_INV_ENTRY_STS_CD */
    /** E */
    static final String CM_ACTL_ENTRY_STS_CD_E = "E";

    /** N */
    static final String FLAG_N = ZYPConstant.FLG_OFF_N;

    /** vendor type code */
    static final String VND_TP_CD_01 = "01";
    /** vendor type code */
    static final String VND_TP_CD_02 = "02";
    /** vendor type code */
    static final String VND_TP_CD_03 = "03";
    /** vendor type code */
    static final String VND_TP_CD_04 = "04";
    /** vendor type code */
    static final String VND_TP_CD_05 = "05";
    /** vendor type code */
    static final String VND_TP_CD_06 = "06";
    /** vendor type code */
    static final String VND_TP_CD_07 = "07";
    /** vendor type code */
    static final String VND_TP_CD_08 = "08";
    /** vendor type code */
    static final String VND_TP_CD_09 = "09";
    /** vendor type code */
    static final String VND_TP_CD_10 = "10";
    
    /** Mail Group ID (From) */
    static final String MAIL_GRP_ID_FROM = "FROM0003";
    /** Mail Key (From) */
    static final String MAIL_KEY_1_FROM = "NFB";

    // START 2016/12/09 [QC#16132, ADD]
    /** Default COA Value*/
    static final String AJE_COA_DEF_VALUES = "AJE_COA_DEF_VALUES";
    // END 2016/12/09 [QC#16132, ADD]

    /**
     * Method name: setZeroIntoCmInvHdr
     * <dd>The method explanation: Set all zero value into invoice
     * header.
     * <dd>Remarks:
     * @param outRecord Invoice Header Output Record
     */
    public static void setZeroIntoCmInvHdr(CM_INV_HDRTMsg outRecord) {
        outRecord.invScFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.invScFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.invScInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.invOcFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.invOcFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.invOcInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcDtyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcOthCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcLmthFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcLmthFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcLmthInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcLmthDtyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcLmthOthCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcTmthFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcTmthFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcTmthInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcTmthDtyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcTmthOthCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcNmthFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcNmthFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcNmthInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcNmthDtyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcNmthOthCostAmt.setValue(BigDecimal.ZERO);

        outRecord.ccyVarFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.ccyVarFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.ccyVarInsCostAmt.setValue(BigDecimal.ZERO);
    }

    /**
     * Method name: setZeroIntoCmStkIn
     * <dd>The method explanation: Set all zero value into Stock-in
     * record.
     * <dd>Remarks:
     * @param outRecord Invoice detail Output Record
     */
    public static void setZeroIntoCmStkIn(CM_STK_INTMsg outRecord) {

        outRecord.invQty.setValue(BigDecimal.ZERO);
        outRecord.rcvQty.setValue(BigDecimal.ZERO);
        outRecord.slsPrmoQty.setValue(BigDecimal.ZERO);
        outRecord.invScFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.invScFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.invScInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.invOcFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.invOcFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.invOcInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acMdseFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.plnScFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.plnScFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.plnScInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.plnScDtyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.plnScOthCostAmt.setValue(BigDecimal.ZERO);
        outRecord.stkInScFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.stkInScFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.stkInScInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.stkInScDtyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.stkInScOthCostAmt.setValue(BigDecimal.ZERO);
        outRecord.slsPrmoScFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.slsPrmoScFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.slsPrmoScInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.slsPrmoScDtyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.slsPrmoScOthCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acctRecCnt.setValue(BigDecimal.ZERO);

    }

    /**
     * Method name: setZeroIntoCmInvActlDtl
     * <dd>The method explanation: Set all zero value into
     * CM_INV_ACTL_DTL.
     * <dd>Remarks:
     * @param outRecord CM_INV_ACTL_DTLTMsg
     */
    public static void setZeroIntoCmInvActlDtl(CM_INV_ACTL_DTLTMsg outRecord) {
        outRecord.invOcOrigCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acScFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acScFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acScInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acScDtyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acScOthCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acScTotCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcDtyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcOthCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcTotCostAmt.setValue(BigDecimal.ZERO);
    }

    /**
     * Method name: setZeroIntoCmInvActlLgscDtl
     * <dd>The method explanation: Set all zero value into
     * CM_INV_ACTL_LGSC_DTL.
     * <dd>Remarks:
     * @param outRecord CM_INV_ACTL_LGSC_DTLTMsg
     */
    public static void setZeroIntoCmInvActlLgscDtl(CM_INV_ACTL_LGSC_DTLTMsg outRecord) {
        outRecord.acOcIntlFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcDtyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcDetnCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcDemgCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcXdOpsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcXdDelyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcDomFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcHmfCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcMpfCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcIsfCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcIsfHdlgCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcTmfCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcCtpCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcCtpHdlgCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcEntryCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcAntiDumpCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcAddLineCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcHdlgCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcTrmCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcStoreCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcDelyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcOthCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcTotCostAmt.setValue(BigDecimal.ZERO);
    }


    /**
     * Method name: setZeroIntoCmInvActlPoHdr
     * <dd>The method explanation: Set all zero value into
     * CM_INV_ACTL_PO_HDR.
     * <dd>Remarks:
     * @param outRecord CM_INV_ACTL_PO_HDRTMsg
     */
    public static void setZeroIntoCmInvActlPoHdr(CM_INV_ACTL_PO_HDRTMsg outRecord) {
        outRecord.acScFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acScFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acScInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acScDtyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acScOthCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acScTotCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcFobCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcFrtCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcInsCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcDtyCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcOthCostAmt.setValue(BigDecimal.ZERO);
        outRecord.acOcTotCostAmt.setValue(BigDecimal.ZERO);
        outRecord.stdExchRate.setValue(BigDecimal.ZERO);
    }

    /**
     * Method name: setShpgTermCd
     * <dd>The method explanation: Set shipping term code.
     * <dd>Remarks:
     * @param invOcFobCostAmt FOB Cost Amount
     * @param invOcFrtCostAmt FRT Cost Amount
     * @param invOcInsCostAmt Insurance Cost Amount
     * @return shipping term code
     */
    public static String setShpgTermCd(BigDecimal invOcFobCostAmt, BigDecimal invOcFrtCostAmt, BigDecimal invOcInsCostAmt) {
        String strAcctShpgTermCd;
        BigDecimal bdZero = BigDecimal.ZERO;

        if (!ZYPCommonFunc.hasValue(invOcFobCostAmt)) {
            invOcFobCostAmt = BigDecimal.ZERO;
        }

        if (!ZYPCommonFunc.hasValue(invOcFrtCostAmt)) {
            invOcFrtCostAmt = BigDecimal.ZERO;
        }

        if (!ZYPCommonFunc.hasValue(invOcInsCostAmt)) {
            invOcInsCostAmt = BigDecimal.ZERO;
        }

        if ((bdZero.compareTo(invOcFobCostAmt) != 0) & (bdZero.compareTo(invOcFrtCostAmt) != 0) & (bdZero.compareTo(invOcInsCostAmt) != 0)) {
            strAcctShpgTermCd = ACCT_SHPG_TERM_CD_02;
        } else if ((bdZero.compareTo(invOcFobCostAmt) != 0) & (bdZero.compareTo(invOcFrtCostAmt) != 0) & (bdZero.compareTo(invOcInsCostAmt) == 0)) {
            strAcctShpgTermCd = ACCT_SHPG_TERM_CD_03;
        } else if ((bdZero.compareTo(invOcFobCostAmt) != 0) & (bdZero.compareTo(invOcFrtCostAmt) == 0) & (bdZero.compareTo(invOcInsCostAmt) != 0)) {
            strAcctShpgTermCd = ACCT_SHPG_TERM_CD_04;
        } else if ((bdZero.compareTo(invOcFobCostAmt) != 0) & (bdZero.compareTo(invOcFrtCostAmt) == 0) & (bdZero.compareTo(invOcInsCostAmt) == 0)) {
            strAcctShpgTermCd = ACCT_SHPG_TERM_CD_01;
        } else {
            strAcctShpgTermCd = ACCT_SHPG_TERM_CD_02;
        }

        return strAcctShpgTermCd;
    }

    /**
     * Method name: setCCyCdTo3
     * <dd>The method explanation: Convert Currency Code from 2 Byte
     * on NL800 to 3 Byte.
     * <dd>Remarks:
     * @param strCcyCd2Byte Currenct Code(2 Byte)
     * @return Currenct Code(3 Byte)
     */
    public static String setCcyCdTo3(String strCcyCd2Byte) {
        String strCcyCd3Byte;

        if (strCcyCd2Byte.equals("UD")) {
            strCcyCd3Byte = "USD";
        } else if (strCcyCd2Byte.equals("CD")) {
            strCcyCd3Byte = "CAD";
        } else if (strCcyCd2Byte.equals("YN")) {
            strCcyCd3Byte = "JPN";
            /* 2010.7.1 K.Onae added for currency code EU and AU */
        } else if (strCcyCd2Byte.equals("EU")) {
            strCcyCd3Byte = "EUR";
        } else if (strCcyCd2Byte.equals("AU")) {
            strCcyCd3Byte = "AUD";
            /* 2010.7.1 K.Onae added end */
        } else {
            // No convert if parametre is not "UD", "CD", "YN", "EU" and "AU"
            strCcyCd3Byte = strCcyCd2Byte;
        }

        return strCcyCd3Byte;
    }

    /**
     * Method name: checkInvoiceDateRange
     * <dd>The method explanation: Check if Invoice date is before
     * today. Today's date is included.
     * <dd>Remarks:
     * @param dt EZDBDateItem
     * @return boolean
     */
    public static boolean checkInvoiceDateRange(EZDBDateItem dt) {

        int intToday = Integer.parseInt(ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
        int intInputDate = Integer.parseInt(dt.getValue());

        if (intInputDate <= intToday) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method name: checkInvoiceDateRange for String
     * <dd>The method explanation: Check if Invoice date is before
     * today. Today's date is included.
     * <dd>Remarks:
     * @param dt EZDBDateItem
     * @return boolean
     */
    public static boolean checkInvoiceDateRange(String dt) {

        int intToday = Integer.parseInt(ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
        int intInputDate = Integer.parseInt(dt);

        if (intInputDate <= intToday) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method name: checkPaymentDueDateRange
     * <dd>The method explanation: Check if Payment Due Date is after
     * today. Today's date is excluded.
     * <dd>Remarks:
     * @param dt EZDBDateItem
     * @return boolean
     */
    public static boolean checkPaymentDueDateRange(EZDBDateItem dt) {

        int intToday = Integer.parseInt(ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
        int intInputDate = Integer.parseInt(dt.getValue());

        if (intInputDate > intToday) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method name: checkPaymentDueDateRange
     * <dd>The method explanation: Check if Payment Due Date is after
     * today. Today's date is excluded.
     * <dd>Remarks:
     * @param dt EZDBDateItem
     * @return boolean
     */
    public static boolean checkPaymentDueDateRange(String dt) {

        int intToday = Integer.parseInt(ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
        int intInputDate = Integer.parseInt(dt);

        if (intInputDate > intToday) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method name: checkS21YearRange
     * <dd>The method explanation: Check if year range is from 2000
     * to 2099.
     * <dd>Remarks:
     * @param dt EZDBDateItem
     * @return boolean
     */
    public static boolean checkS21YearRange(EZDBDateItem dt) {

        int year = Integer.parseInt(dt.getValue().substring(0, 4));

        if (year < 2000) {
            return false;
        } else if (year > 2099) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method name: checkS21YearRange for String
     * <dd>The method explanation: Check if year range is from 2000
     * to 2099.
     * <dd>Remarks:
     * @param dt EZDBDateItem
     * @return boolean
     */
    public static boolean checkS21YearRange(String dt) {

        int year = Integer.parseInt(dt.substring(0, 4));

        if (year < 2000) {
            return false;
        } else if (year > 2099) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method name: getOnlStdCst
     * <dd>The method explanation: Calculate Standard Amount for
     * online screens.
     * <dd>Remarks:
     * @param queryMap Map<String, String>
     * @param bdPrchAmt Purchase Amount
     * @return BigDecimal
     */
    @SuppressWarnings("unchecked")
    public BigDecimal getOnlStdCst(Map<String, String> queryMap, BigDecimal bdPrchAmt) {

        String strAcctArthTpCd = "";
        BigDecimal bdRetValue = new BigDecimal(0.00);
        BigDecimal bdStdExchAmt = new BigDecimal(0.00);

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", queryMap.get("glblCmpyCd"));
        queryParam.put("ccyCd", queryMap.get("ccyCd"));
        queryParam.put("applYrMth", queryMap.get("applYrMth"));

        S21SsmEZDClient ssmEzdClient = S21SsmEZDClient.getClient(this.getClass());

        S21SsmEZDResult ssmResult = ssmEzdClient.queryObjectList("getCcy", queryParam, -1, -1);

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                strAcctArthTpCd = (String) map.get("ACCT_ARTH_TP_CD");
                bdStdExchAmt = (BigDecimal) map.get("STD_EXCH_RATE");

                if (strAcctArthTpCd.equals("*")) {
                    // Standard Currency Amount = Original Currency
                    // Amount *
                    // Standard Currency Rate
                    bdRetValue = bdPrchAmt.multiply(bdStdExchAmt);
                    bdRetValue = bdRetValue.setScale(2, BigDecimal.ROUND_HALF_UP);

                } else if (strAcctArthTpCd.equals("/")) {
                    // Standard Currency Amount = Original Currency
                    // Amount /
                    // Standard Currency Rate

                    if (bdStdExchAmt.compareTo(BigDecimal.ZERO) != 0) {
                        bdRetValue = bdPrchAmt.divide(bdStdExchAmt, 2, BigDecimal.ROUND_HALF_UP);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }

        return bdRetValue;

    }


    /**
     * Method name: getOnlStdCst
     * <dd>The method explanation: Obtain Standard Excange Rate
     * <dd>Remarks:
     * @param queryMap Map<String, String>
     * @return BigDecimal
     */
    @SuppressWarnings("unchecked")
    public BigDecimal getOnlStdRate(Map<String, String> queryMap) {

        BigDecimal bdStdExchAmt = new BigDecimal(0.00);

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", queryMap.get("glblCmpyCd"));
        queryParam.put("ccyCd", queryMap.get("ccyCd"));
        queryParam.put("applYrMth", queryMap.get("applYrMth"));

        S21SsmEZDClient ssmEzdClient = S21SsmEZDClient.getClient(this.getClass());

        S21SsmEZDResult ssmResult = ssmEzdClient.queryObjectList("getCcy", queryParam, -1, -1);

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bdStdExchAmt = (BigDecimal) map.get("STD_EXCH_RATE");
            }
        } else {
            return null;
        }

        return bdStdExchAmt;

    }

    /**
     * 
     * @param queryMap
     * @param bdPrchAmt
     * @return
     */
    public BigDecimal getOnlStdCst2(Map<String, String> queryMap, BigDecimal stdCostAmt, BigDecimal stdExchRate) {

        String strAcctArthTpCd = "";
        BigDecimal bdRetValue = new BigDecimal(0.00);

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", queryMap.get("glblCmpyCd"));
        queryParam.put("ccyCd", queryMap.get("ccyCd"));
        queryParam.put("applYrMth", queryMap.get("applYrMth"));

        CCYTMsg ccyTMsg = new CCYTMsg();
        ccyTMsg.setSQLID("800");
        ccyTMsg.setConditionValue("glblCmpyCd01", queryMap.get("glblCmpyCd"));
        ccyTMsg.setConditionValue("ccyCd01", queryMap.get("ccyCd"));
        CCYTMsgArray outMsgArray = (CCYTMsgArray) EZDTBLAccessor.findByCondition(ccyTMsg);
        if (outMsgArray == null) {
            return null;
        }
        strAcctArthTpCd = outMsgArray.no(0).acctArthTpCd.getValue();
        if (strAcctArthTpCd.equals("*")) {
            // Standard Currency Amount = Original Currency
            // Amount *
            // Standard Currency Rate
            bdRetValue = stdCostAmt.multiply(stdExchRate);
            bdRetValue = bdRetValue.setScale(2, BigDecimal.ROUND_HALF_UP);

        } else if (strAcctArthTpCd.equals("/")) {
            // Standard Currency Amount = Original Currency
            // Amount /
            // Standard Currency Rate

            if (stdExchRate.compareTo(BigDecimal.ZERO) != 0) {
                bdRetValue = stdCostAmt.divide(stdExchRate, 2, BigDecimal.ROUND_HALF_UP);
            } else {
                return null;
            }
        } else {
            return null;
        }
        return bdRetValue;
    }

    /**
     * Method name: getOnlAvgCst
     * <dd>The method explanation: Obtain Standard Excange Rate
     * <dd>Remarks:
     * @param queryMap Map<String, String>
     * @return BigDecimal
     */
    @SuppressWarnings("unchecked")
    public BigDecimal getOnlAvgRate(Map<String, String> queryMap) {

        BigDecimal bdStdExchAmt = new BigDecimal(0.00);

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", queryMap.get("glblCmpyCd"));
        queryParam.put("ccyCd", queryMap.get("ccyCd"));
        queryParam.put("applYrMth", queryMap.get("applYrMth"));

        S21SsmEZDClient ssmEzdClient = S21SsmEZDClient.getClient(this.getClass());

        S21SsmEZDResult ssmResult = ssmEzdClient.queryObjectList("getCcy", queryParam, -1, -1);

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bdStdExchAmt = (BigDecimal) map.get("AVG_EXCH_RATE");
            }
        } else {
            return null;
        }

        return bdStdExchAmt;

    }


    /**
     * Method name: getCmCostelCd
     * <dd>The method explanation: Get CM_COSTEL_CD from
     * CM_LGSC_COSTEL table. table.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message. For batch, it should be set to null.
     * @param messanger ZYPCSVUploadMessenger. For screen, it should be set to null.
     * @param glblCmpyCd String
     * @return CostMngmntInfo[] Array of CostMngmntInfo
     */
    @SuppressWarnings("unchecked")
    public static CostMngmntInfo[] getCmCostelCd(EZDCMsg cMsg, ZYPCSVUploadMessenger messanger, String glblCmpyCd) {

        String[] cmLgscCostelCd = {"acOcIntlFrtCostAmt", "acOcInsCostAmt", "acOcDtyCostAmt", "acOcDetnCostAmt", "acOcDemgCostAmt", "acOcXdOpsCostAmt", "acOcXdDelyCostAmt", "acOcDomFrtCostAmt", "acOcHmfCostAmt", "acOcMpfCostAmt",
                "acOcIsfCostAmt", "acOcIsfHdlgCostAmt", "acOcTmfCostAmt", "acOcCtpCostAmt", "acOcCtpHdlgCostAmt", "acOcEntryCostAmt", "acOcAntiDumpCostAmt", "acOcAddLineCostAmt", "acOcHdlgCostAmt", "acOcTrmCostAmt", "acOcDelyCostAmt",
                "acOcStoreCostAmt", "acOcOthCostAmt" };

        CostMngmntInfo[] cmCostelCdArr = new CostMngmntInfo[23];

        for (int i = 0; i < cmLgscCostelCd.length; i++) {
            CostMngmntInfo item = new CostMngmntInfo();
            item.fieldNm = cmLgscCostelCd[i];
            cmCostelCdArr[i] = item;
        }


        Map<String, String> queryMap = new HashMap<String, String>();
        String cmCostelCd = null;

        for (int i = 0; i < cmLgscCostelCd.length; i++) {
            queryMap.put("glblCmpyCd", glblCmpyCd);
            queryMap.put("cmLgscCostElmntCd", cmLgscCostelCd[i]);
            queryMap.put("cmLgscCostElmntFromDt", ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
            queryMap.put("cmLgscCostElmntThruDt", ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));

            S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NFBCommonBusiness.class);

            List<Map> ssmResult = (List<Map>) ssmBatchClient.queryObjectList("getCostManagementInfo", queryMap);

            if (ssmResult.size() > 1) {
                if (cMsg != null) {
                    cMsg.setMessageInfo(NFBM0146E);
                } else {
                    messanger.addMessageForFile(NFBM0146E, strNone);
                }

                return null;
            }

            for (int j = 0; j < ssmResult.size(); j++) {
                Map map = (Map) ssmResult.get(j);
                cmCostelCd = (String) map.get("CM_COST_ELMNT_CD");
            }

            if (!cmCostelCd.equals("acOcFobCostAmt") && !cmCostelCd.equals("acOcFrtCostAmt") && !cmCostelCd.equals("acOcInsCostAmt") && !cmCostelCd.equals("acOcDtyCostAmt") && !cmCostelCd.equals("acOcOthCostAmt")) {
                if (cMsg != null) {
                    cMsg.setMessageInfo(NFBM0028E);
                } else {
                    messanger.addMessageForFile(NFBM0028E, strNone);
                }
                return null;
            } else {
                CostMngmntInfo item = cmCostelCdArr[i];
                item.cmFieldNm = cmCostelCd;

                cmCostelCdArr[i] = item;
            }

        }
        return cmCostelCdArr;

    }

    /**
     * Class name: CostMngmntInfo
     */
    public static class CostMngmntInfo{

        private String fieldNm;
        private String cmFieldNm;

        public CostMngmntInfo() {
            super();
            this.fieldNm = strNone;
            this.cmFieldNm = strNone;

        }

        public String getCmFieldNm() {
            return cmFieldNm;
        }
        public void setCmFieldNm(String cmFieldNm) {
            this.cmFieldNm = cmFieldNm;
        }
        public String getFieldNm() {
            return fieldNm;
        }
        public void setFieldNm(String fieldNm) {
            this.fieldNm = fieldNm;
        }

    }
    
    /**
     * Class name: ErrorMsgArg
     */
    public static class ErrorMsgArg{
        private String apVndCd;
        private String apVndInvNum;
        private String invDt;
        private String cmCntnrBolNum;
        private BigDecimal invOcOrigCostAmt;

        public String getApVndCd() {
            return apVndCd;
        }
        public void setApVndCd(String apVndCd) {
            this.apVndCd = chkNull(apVndCd);
        }
        public String getApVndInvNum() {
            return chkNull(apVndInvNum);
        }
        public void setApVndInvNum(String apVndInvNum) {
            this.apVndInvNum = chkNull(apVndInvNum);
        }
        public String getInvDt() {
            return chkNull(invDt);
        }
        public void setInvDt(String invDt) {
            this.invDt = chkNull(invDt);
        }
        public BigDecimal getInvOcOrigCostAmt() {
            return invOcOrigCostAmt;
        }
        public void setInvOcOrigCostAmt(BigDecimal invOcOrigCostAmt) {
            this.invOcOrigCostAmt = chkNull(invOcOrigCostAmt);
        }
        public String getCmCntnrBolNum() {
            return chkNull(cmCntnrBolNum);
        }
        public void setCmCntnrBolNum(String cmCntnrBolNum) {
            this.cmCntnrBolNum = chkNull(cmCntnrBolNum);
        }

    }

    /**
     * Class name: AcOcAmt
     */
    public static class AcOcAmt {
        /** AC_OC_INTL_FRT_COST_AMT */
        private BigDecimal aAcOcIntlFrtCostAmt;

        /** AC_OC_INS_COST_AMT */
        private BigDecimal aAcOcInsCostAmt;

        /** AC_OC_DTY_COST_AMT */
        private BigDecimal aAcOcDtyCostAmt;

        /** AC_OC_DETN_COST_AMT */
        private BigDecimal aAcOcDetnCostAmt;

        /** AC_OC_DEMG_COST_AMT */
        private BigDecimal aAcOcDemgCostAmt;

        /** AC_OC_XD_OPS_COST_AMT */
        private BigDecimal aAcOcXdOpsCostAmt;

        /** AC_OC_XD_DELY_COST_AMT */
        private BigDecimal aAcOcXdDelyCostAmt;

        /** AC_OC_DOM_FRT_COST_AMT */
        private BigDecimal aAcOcDomFrtCostAmt;

        /** AC_OC_HMF_COST_AMT */
        private BigDecimal aAcOcHmfCostAmt;

        /** AC_OC_MPF_COST_AMT */
        private BigDecimal aAcOcMpfCostAmt;

        /** AC_OC_ISF_COST_AMT */
        private BigDecimal aAcOcIsfCostAmt;

        /** AC_OC_ISF_HDLG_COST_AMT */
        private BigDecimal aAcOcIsfHdlgCostAmt;

        /** AC_OC_TMF_COST_AMT */
        private BigDecimal aAcOcTmfCostAmt;

        /** AC_OC_CTP_COST_AMT */
        private BigDecimal aAcOcCtpCostAmt;

        /** AC_OC_CTP_HDLG_COST_AMT */
        private BigDecimal aAcOcCtpHdlgCostAmt;

        /** AC_OC_ENTRY_COST_AMT */
        private BigDecimal aAcOcEntryCostAmt;

        /** AC_OC_ANTI_DUMP_COST_AMT */
        private BigDecimal aAcOcAntiDumpCostAmt;

        /** AC_OC_ADD_LINE_COST_AMT */
        private BigDecimal aAcOcAddLineCostAmt;

        /** AC_OC_HDLG_COST_AMT */
        private BigDecimal aAcOcHdlgCostAmt;

        /** AC_OC_TRM_COST_AMT */
        private BigDecimal aAcOcTrmCostAmt;

        /** AC_OC_STORE_COST_AMT */
        private BigDecimal aAcOcStoreCostAmt;

        /** AC_OC_DELY_COST_AMT */
        private BigDecimal aAcOcDelyCostAmt;

        /** AC_OC_OTH_COST_AMT */
        private BigDecimal aAcOcOthCostAmt;

        /** AC_OC_TOT_COST_AMT */
        private BigDecimal aAcOcTotCostAmt;

        public AcOcAmt() {
            super();
            aAcOcIntlFrtCostAmt = new BigDecimal(0.00);
            aAcOcInsCostAmt = new BigDecimal(0.00);
            aAcOcDtyCostAmt = new BigDecimal(0.00);
            aAcOcDetnCostAmt = new BigDecimal(0.00);
            aAcOcDemgCostAmt = new BigDecimal(0.00);
            aAcOcXdOpsCostAmt = new BigDecimal(0.00);
            aAcOcXdDelyCostAmt = new BigDecimal(0.00);
            aAcOcDomFrtCostAmt = new BigDecimal(0.00);
            aAcOcHmfCostAmt = new BigDecimal(0.00);
            aAcOcMpfCostAmt = new BigDecimal(0.00);
            aAcOcIsfCostAmt = new BigDecimal(0.00);
            aAcOcIsfHdlgCostAmt = new BigDecimal(0.00);
            aAcOcTmfCostAmt = new BigDecimal(0.00);
            aAcOcCtpCostAmt = new BigDecimal(0.00);
            aAcOcCtpHdlgCostAmt = new BigDecimal(0.00);
            aAcOcEntryCostAmt = new BigDecimal(0.00);
            aAcOcAntiDumpCostAmt = new BigDecimal(0.00);
            aAcOcAddLineCostAmt = new BigDecimal(0.00);
            aAcOcHdlgCostAmt = new BigDecimal(0.00);
            aAcOcTrmCostAmt = new BigDecimal(0.00);
            aAcOcStoreCostAmt = new BigDecimal(0.00);
            aAcOcDelyCostAmt = new BigDecimal(0.00);
            aAcOcOthCostAmt = new BigDecimal(0.00);
            aAcOcTotCostAmt = new BigDecimal(0.00);
        }

        /**
         * Method name: accumulate
         * <dd>The method explanation: sum up amount
         * <dd>Remarks:
         * @param elm AcOcAmt to be added up. 
         */
        public void accumulate(AcOcAmt elm) {

            this.aAcOcIntlFrtCostAmt = this.aAcOcIntlFrtCostAmt.add(elm.aAcOcIntlFrtCostAmt);
            this.aAcOcInsCostAmt = this.aAcOcInsCostAmt.add(elm.aAcOcInsCostAmt);
            this.aAcOcDtyCostAmt = this.aAcOcDtyCostAmt.add(elm.aAcOcDtyCostAmt);
            this.aAcOcDetnCostAmt = this.aAcOcDetnCostAmt.add(elm.aAcOcDetnCostAmt);
            this.aAcOcDemgCostAmt = this.aAcOcDemgCostAmt.add(elm.aAcOcDemgCostAmt);
            this.aAcOcXdOpsCostAmt = this.aAcOcXdOpsCostAmt.add(elm.aAcOcXdOpsCostAmt);
            this.aAcOcXdDelyCostAmt = this.aAcOcXdDelyCostAmt.add(elm.aAcOcXdDelyCostAmt);
            this.aAcOcDomFrtCostAmt = this.aAcOcDomFrtCostAmt.add(elm.aAcOcDomFrtCostAmt);
            this.aAcOcHmfCostAmt = this.aAcOcHmfCostAmt.add(elm.aAcOcHmfCostAmt);
            this.aAcOcMpfCostAmt = this.aAcOcMpfCostAmt.add(elm.aAcOcMpfCostAmt);
            this.aAcOcIsfCostAmt = this.aAcOcIsfCostAmt.add(elm.aAcOcIsfCostAmt);
            this.aAcOcIsfHdlgCostAmt = this.aAcOcIsfHdlgCostAmt.add(elm.aAcOcIsfHdlgCostAmt);
            this.aAcOcTmfCostAmt = this.aAcOcTmfCostAmt.add(elm.aAcOcTmfCostAmt);
            this.aAcOcCtpCostAmt = this.aAcOcCtpCostAmt.add(elm.aAcOcCtpCostAmt);
            this.aAcOcCtpHdlgCostAmt = this.aAcOcCtpHdlgCostAmt.add(elm.aAcOcCtpHdlgCostAmt);
            this.aAcOcEntryCostAmt = this.aAcOcEntryCostAmt.add(elm.aAcOcEntryCostAmt);
            this.aAcOcAntiDumpCostAmt = this.aAcOcAntiDumpCostAmt.add(elm.aAcOcAntiDumpCostAmt);
            this.aAcOcAddLineCostAmt = this.aAcOcAddLineCostAmt.add(elm.aAcOcAddLineCostAmt);
            this.aAcOcHdlgCostAmt = this.aAcOcHdlgCostAmt.add(elm.aAcOcHdlgCostAmt);
            this.aAcOcTrmCostAmt = this.aAcOcTrmCostAmt.add(elm.aAcOcTrmCostAmt);
            this.aAcOcStoreCostAmt = this.aAcOcStoreCostAmt.add(elm.aAcOcStoreCostAmt);
            this.aAcOcDelyCostAmt = this.aAcOcDelyCostAmt.add(elm.aAcOcDelyCostAmt);
            this.aAcOcOthCostAmt = this.aAcOcOthCostAmt.add(elm.aAcOcOthCostAmt);
            this.aAcOcTotCostAmt = this.aAcOcTotCostAmt.add(elm.aAcOcTotCostAmt);

        }
        
        /**
         * Method name: settAmoutForTheColumn
         * <dd>The method explanation: set amount to the passed field name.
         * <dd>Remarks:
         * @param clmNm column name
         * @oaram val BigDecimal
         */
        public void setAmoutForTheColumn(String clmNm, BigDecimal val) {

            if (clmNm.equals("acOcIntlFrtCostAmt")) {
                this.aAcOcIntlFrtCostAmt = val;
            } else if (clmNm.equals("acOcInsCostAmt")) {
                this.aAcOcInsCostAmt = val;
            } else if (clmNm.equals("acOcDtyCostAmt")) {
                this.aAcOcDtyCostAmt = val;
            } else if (clmNm.equals("acOcDetnCostAmt")) {
                this.aAcOcDetnCostAmt = val;
            } else if (clmNm.equals("acOcDemgCostAmt")) {
                this.aAcOcDemgCostAmt = val;
            } else if (clmNm.equals("acOcXdOpsCostAmt")) {
               this.aAcOcXdOpsCostAmt = val;
            } else if (clmNm.equals("acOcXdDelyCostAmt")) {
                this.aAcOcXdDelyCostAmt = val;
            } else if (clmNm.equals("acOcDomFrtCostAmt")) {
                this.aAcOcDomFrtCostAmt = val;
            } else if (clmNm.equals("acOcHmfCostAmt")) {
                this.aAcOcHmfCostAmt = val;
            } else if (clmNm.equals("acOcMpfCostAmt")) {
                this.aAcOcMpfCostAmt = val;
            } else if (clmNm.equals("acOcIsfCostAmt")) {
                this.aAcOcIsfCostAmt = val;
            } else if (clmNm.equals("acOcIsfHdlgCostAmt")) {
                this.aAcOcIsfHdlgCostAmt = val;
            } else if (clmNm.equals("acOcTmfCostAmt")) {
                this.aAcOcTmfCostAmt = val;
            } else if (clmNm.equals("acOcCtpCostAmt")) {
                this.aAcOcCtpCostAmt = val;
            } else if (clmNm.equals("acOcCtpHdlgCostAmt")) {
                this.aAcOcCtpHdlgCostAmt = val;
            } else if (clmNm.equals("acOcEntryCostAmt")) {
                this.aAcOcEntryCostAmt = val;
            } else if (clmNm.equals("acOcAntiDumpCostAmt")) {
                this.aAcOcAntiDumpCostAmt = val;
            } else if (clmNm.equals("acOcAddLineCostAmt")) {
                this.aAcOcAddLineCostAmt = val;
            } else if (clmNm.equals("acOcHdlgCostAmt")) {
                this.aAcOcHdlgCostAmt = val;
            } else if (clmNm.equals("acOcTrmCostAmt")) {
                this.aAcOcTrmCostAmt = val;
            } else if (clmNm.equals("acOcDelyCostAmt")) {
                this.aAcOcDelyCostAmt = val;
            } else if (clmNm.equals("acOcStoreCostAmt")) {
                this.aAcOcStoreCostAmt = val;
            } else if (clmNm.equals("acOcOthCostAmt")) {
                this.aAcOcOthCostAmt = val;
            }
        }

        /**
         * Method name: getAmoutForTheColumn
         * <dd>The method explanation: Return the amount of passed field name.
         * <dd>Remarks:
         * @param clmNm column name
         * @return  BigDecimal
         */
        public BigDecimal getAmoutForTheColumn(String clmNm) {

            if (clmNm.equals("acOcIntlFrtCostAmt")) {
                return this.aAcOcIntlFrtCostAmt;
            } else if (clmNm.equals("acOcInsCostAmt")) {
                return this.aAcOcInsCostAmt;
            } else if (clmNm.equals("acOcDtyCostAmt")) {
                return this.aAcOcDtyCostAmt;
            } else if (clmNm.equals("acOcDetnCostAmt")) {
                return this.aAcOcDetnCostAmt;
            } else if (clmNm.equals("acOcDemgCostAmt")) {
                return this.aAcOcDemgCostAmt;
            } else if (clmNm.equals("acOcXdOpsCostAmt")) {
               return this.aAcOcXdOpsCostAmt;
            } else if (clmNm.equals("acOcXdDelyCostAmt")) {
                return this.aAcOcXdDelyCostAmt;
            } else if (clmNm.equals("acOcDomFrtCostAmt")) {
                return this.aAcOcDomFrtCostAmt;
            } else if (clmNm.equals("acOcHmfCostAmt")) {
                return this.aAcOcHmfCostAmt;
            } else if (clmNm.equals("acOcMpfCostAmt")) {
                return this.aAcOcMpfCostAmt;
            } else if (clmNm.equals("acOcIsfCostAmt")) {
                return this.aAcOcIsfCostAmt;
            } else if (clmNm.equals("acOcIsfHdlgCostAmt")) {
                return this.aAcOcIsfHdlgCostAmt;
            } else if (clmNm.equals("acOcTmfCostAmt")) {
                return this.aAcOcTmfCostAmt;
            } else if (clmNm.equals("acOcCtpCostAmt")) {
                return this.aAcOcCtpCostAmt;
            } else if (clmNm.equals("acOcCtpHdlgCostAmt")) {
                return this.aAcOcCtpHdlgCostAmt;
            } else if (clmNm.equals("acOcEntryCostAmt")) {
                return this.aAcOcEntryCostAmt;
            } else if (clmNm.equals("acOcAntiDumpCostAmt")) {
                return this.aAcOcAntiDumpCostAmt;
            } else if (clmNm.equals("acOcAddLineCostAmt")) {
                return this.aAcOcAddLineCostAmt;
            } else if (clmNm.equals("acOcHdlgCostAmt")) {
                return this.aAcOcHdlgCostAmt;
            } else if (clmNm.equals("acOcTrmCostAmt")) {
                return this.aAcOcTrmCostAmt;
            } else if (clmNm.equals("acOcDelyCostAmt")) {
                return this.aAcOcDelyCostAmt;
            } else if (clmNm.equals("acOcStoreCostAmt")) {
                return this.aAcOcStoreCostAmt;
            } else if (clmNm.equals("acOcOthCostAmt")) {
                return this.aAcOcOthCostAmt;
            } else {
                return null;
            }
        }

        /**
         * Method name: getAmoutForTheColumn
         * <dd>The method explanation: Return the amount of passed field name.
         * <dd>Remarks:
         * @param clmNm column name
         * @return  BigDecimal
         */
        public BigDecimal getSum() {

            BigDecimal sum = this.aAcOcIntlFrtCostAmt;
            sum = sum.add(this.aAcOcInsCostAmt);
            sum = sum.add(this.aAcOcDtyCostAmt);
            sum = sum.add(this.aAcOcDetnCostAmt);
            sum = sum.add(this.aAcOcDemgCostAmt);
            sum = sum.add(this.aAcOcXdOpsCostAmt);
            sum = sum.add(this.aAcOcXdDelyCostAmt);
            sum = sum.add(this.aAcOcDomFrtCostAmt);
            sum = sum.add(this.aAcOcHmfCostAmt);
            sum = sum.add(this.aAcOcMpfCostAmt);
            sum = sum.add(this.aAcOcIsfCostAmt);
            sum = sum.add(this.aAcOcIsfHdlgCostAmt);
            sum = sum.add(this.aAcOcTmfCostAmt);
            sum = sum.add(this.aAcOcCtpCostAmt);
            sum = sum.add(this.aAcOcCtpHdlgCostAmt);
            sum = sum.add(this.aAcOcEntryCostAmt);
            sum = sum.add(this.aAcOcAntiDumpCostAmt);
            sum = sum.add(this.aAcOcAddLineCostAmt);
            sum = sum.add(this.aAcOcHdlgCostAmt);
            sum = sum.add(this.aAcOcTrmCostAmt);
            sum = sum.add(this.aAcOcDelyCostAmt);
            sum = sum.add(this.aAcOcStoreCostAmt);
            sum = sum.add(this.aAcOcOthCostAmt);
            
            return sum;
            
        }
        
        /**
         * Method name: isAmountAllZero
         * <dd>The method explanation: Check if amount of fields is all zero or not
         * <dd>Remarks:
         * @param list List<String> list of field names
         * @return  boolean
         */
        public boolean isAmountAllZero(List<String>list) {
            boolean hasValue = false;

            for (int i = 0; i < list.size(); i++) {
                if (BigDecimal.ZERO.compareTo(getAmoutForTheColumn((String) list.get(i))) != 0) {
                    hasValue = true;
                    break;
                }

            }

            return !hasValue;

        }

        public BigDecimal getAAcOcAddLineCostAmt() {
            return aAcOcAddLineCostAmt;
        }

        public void setAAcOcAddLineCostAmt(BigDecimal acOcAddLineCostAmt) {

            aAcOcAddLineCostAmt = chkNull(acOcAddLineCostAmt);
        }

        public BigDecimal getAAcOcAntiDumpCostAmt() {
            return aAcOcAntiDumpCostAmt;
        }

        public void setAAcOcAntiDumpCostAmt(BigDecimal acOcAntiDumpCostAmt) {
            aAcOcAntiDumpCostAmt = chkNull(acOcAntiDumpCostAmt);
        }

        public BigDecimal getAAcOcCtpCostAmt() {
            return aAcOcCtpCostAmt;
        }

        public void setAAcOcCtpCostAmt(BigDecimal acOcCtpCostAmt) {
            aAcOcCtpCostAmt = chkNull(acOcCtpCostAmt);
        }

        public BigDecimal getAAcOcCtpHdlgCostAmt() {
            return aAcOcCtpHdlgCostAmt;
        }

        public void setAAcOcCtpHdlgCostAmt(BigDecimal acOcCtpHdlgCostAmt) {
            aAcOcCtpHdlgCostAmt = chkNull(acOcCtpHdlgCostAmt);
        }

        public BigDecimal getAAcOcDelyCostAmt() {
            return aAcOcDelyCostAmt;
        }

        public void setAAcOcDelyCostAmt(BigDecimal acOcDelyCostAmt) {
            aAcOcDelyCostAmt = chkNull(acOcDelyCostAmt);
        }

        public BigDecimal getAAcOcDemgCostAmt() {
            return aAcOcDemgCostAmt;
        }

        public void setAAcOcDemgCostAmt(BigDecimal acOcDemgCostAmt) {
            aAcOcDemgCostAmt = chkNull(acOcDemgCostAmt);
        }

        public BigDecimal getAAcOcDetnCostAmt() {
            return aAcOcDetnCostAmt;
        }

        public void setAAcOcDetnCostAmt(BigDecimal acOcDetnCostAmt) {
            aAcOcDetnCostAmt = chkNull(acOcDetnCostAmt);
        }

        public BigDecimal getAAcOcDomFrtCostAmt() {
            return aAcOcDomFrtCostAmt;
        }

        public void setAAcOcDomFrtCostAmt(BigDecimal acOcDomFrtCostAmt) {
            aAcOcDomFrtCostAmt = chkNull(acOcDomFrtCostAmt);
        }

        public BigDecimal getAAcOcDtyCostAmt() {
            return aAcOcDtyCostAmt;
        }

        public void setAAcOcDtyCostAmt(BigDecimal acOcDtyCostAmt) {
            aAcOcDtyCostAmt = chkNull(acOcDtyCostAmt);
        }

        public BigDecimal getAAcOcEntryCostAmt() {
            return aAcOcEntryCostAmt;
        }

        public void setAAcOcEntryCostAmt(BigDecimal acOcEntryCostAmt) {
            aAcOcEntryCostAmt = chkNull(acOcEntryCostAmt);
        }

        public BigDecimal getAAcOcHdlgCostAmt() {
            return aAcOcHdlgCostAmt;
        }

        public void setAAcOcHdlgCostAmt(BigDecimal acOcHdlgCostAmt) {
            aAcOcHdlgCostAmt = chkNull(acOcHdlgCostAmt);
        }

        public BigDecimal getAAcOcHmfCostAmt() {
            return aAcOcHmfCostAmt;
        }

        public void setAAcOcHmfCostAmt(BigDecimal acOcHmfCostAmt) {
            aAcOcHmfCostAmt = chkNull(acOcHmfCostAmt);
        }

        public BigDecimal getAAcOcInsCostAmt() {
            return aAcOcInsCostAmt;
        }

        public void setAAcOcInsCostAmt(BigDecimal acOcInsCostAmt) {
            aAcOcInsCostAmt = chkNull(acOcInsCostAmt);
        }

        public BigDecimal getAAcOcIntlFrtCostAmt() {
            return aAcOcIntlFrtCostAmt;
        }

        public void setAAcOcIntlFrtCostAmt(BigDecimal acOcIntlFrtCostAmt) {
            aAcOcIntlFrtCostAmt = chkNull(acOcIntlFrtCostAmt);
        }

        public BigDecimal getAAcOcIsfCostAmt() {
            return aAcOcIsfCostAmt;
        }

        public void setAAcOcIsfCostAmt(BigDecimal acOcIsfCostAmt) {
            aAcOcIsfCostAmt = chkNull(acOcIsfCostAmt);
        }

        public BigDecimal getAAcOcIsfHdlgCostAmt() {
            return aAcOcIsfHdlgCostAmt;
        }

        public void setAAcOcIsfHdlgCostAmt(BigDecimal acOcIsfHdlgCostAmt) {
            aAcOcIsfHdlgCostAmt = chkNull(acOcIsfHdlgCostAmt);
        }

        public BigDecimal getAAcOcMpfCostAmt() {
            return aAcOcMpfCostAmt;
        }

        public void setAAcOcMpfCostAmt(BigDecimal acOcMpfCostAmt) {
            aAcOcMpfCostAmt = chkNull(acOcMpfCostAmt);
        }

        public BigDecimal getAAcOcOthCostAmt() {
            return aAcOcOthCostAmt;
        }

        public void setAAcOcOthCostAmt(BigDecimal acOcOthCostAmt) {
            aAcOcOthCostAmt = chkNull(acOcOthCostAmt);
        }

        public BigDecimal getAAcOcStoreCostAmt() {
            return aAcOcStoreCostAmt;
        }

        public void setAAcOcStoreCostAmt(BigDecimal acOcStoreCostAmt) {
            aAcOcStoreCostAmt = chkNull(acOcStoreCostAmt);
        }

        public BigDecimal getAAcOcTmfCostAmt() {
            return aAcOcTmfCostAmt;
        }

        public void setAAcOcTmfCostAmt(BigDecimal acOcTmfCostAmt) {
            aAcOcTmfCostAmt = chkNull(acOcTmfCostAmt);
        }

        public BigDecimal getAAcOcTotCostAmt() {
            return aAcOcTotCostAmt;
        }

        public void setAAcOcTotCostAmt(BigDecimal acOcTotCostAmt) {
            aAcOcTotCostAmt = chkNull(acOcTotCostAmt);
        }

        public BigDecimal getAAcOcTrmCostAmt() {
            return aAcOcTrmCostAmt;
        }

        public void setAAcOcTrmCostAmt(BigDecimal acOcTrmCostAmt) {
            aAcOcTrmCostAmt = chkNull(acOcTrmCostAmt);
        }

        public BigDecimal getAAcOcXdDelyCostAmt() {
            return aAcOcXdDelyCostAmt;
        }

        public void setAAcOcXdDelyCostAmt(BigDecimal acOcXdDelyCostAmt) {
            aAcOcXdDelyCostAmt = chkNull(acOcXdDelyCostAmt);
        }

        public BigDecimal getAAcOcXdOpsCostAmt() {
            return aAcOcXdOpsCostAmt;
        }

        public void setAAcOcXdOpsCostAmt(BigDecimal acOcXdOpsCostAmt) {
            aAcOcXdOpsCostAmt = chkNull(acOcXdOpsCostAmt);
        }
    }

    /**
     * Class name: CntnrOrBol
     */
    public static class CntnrOrBol{

        private String glblCmpyCd;
        private String apVndCd;
        private String apVndInvNum;
        private String apVndInvSqNum;
        private String remSqNum;
        private String cmCntnrBolNum;
        private String cmCntnrBolCd;
        private String invDt;
        private String ccyCd;
        private String pmtDueDt;
        private BigDecimal invOcOrigCostAmt;
        private String acctInvStsCd;
        private BigDecimal rowNum;
        private int commitCntDtl;
        private int commitCntLgscDtl;
        private AcOcAmt acOcAmt;
        private boolean isError;

        /** AC_OC_TOT_COST_AMT */
        private BigDecimal aAcOcTotCostAmt;

        public AcOcAmt getAcOcAmt() {
            return acOcAmt;
        }

        public void setAcOcAmt(AcOcAmt acOcAmt) {
            this.acOcAmt = acOcAmt;
        }

        public int getCommitCntDtl() {
            return commitCntDtl;
        }

        public void setCommitCntDtl(int commitCntDtl) {
            this.commitCntDtl = commitCntDtl;
        }

        public int getCommitCntLgscDtl() {
            return commitCntLgscDtl;
        }

        public void setCommitCntLgscDtl(int commitCntLgscDtl) {
            this.commitCntLgscDtl = commitCntLgscDtl;
        }

        public BigDecimal getRowNum() {
            return rowNum;
        }

        public void setRowNum(BigDecimal rowNum) {
            this.rowNum = rowNum;
        }

        public String getAcctInvStsCd() {
            return acctInvStsCd;
        }

        public void setAcctInvStsCd(String acctInvStsCd) {
            this.acctInvStsCd = acctInvStsCd;
        }


        public BigDecimal getAAcOcTotCostAmt() {
            return aAcOcTotCostAmt;
        }

        public void setAAcOcTotCostAmt(BigDecimal acOcTotCostAmt) {
            aAcOcTotCostAmt = chkNull(acOcTotCostAmt);
        }

        public String getGlblCmpyCd() {
            return glblCmpyCd;
        }

        public void setGlblCmpyCd(String glblCmpyCd) {

            this.glblCmpyCd = glblCmpyCd;
        }

        public String getApVndCd() {
            return apVndCd;
        }

        public void setApVndCd(String apVndCd) {

            this.apVndCd = apVndCd;
        }

        public String getApVndInvNum() {
            return apVndInvNum;
        }

        public void setApVndInvNum(String apVndInvNum) {

            this.apVndInvNum = apVndInvNum;
        }

        public String getApVndInvSqNum() {
            return apVndInvSqNum;
        }

        public void setApVndInvSqNum(String apVndInvSqNum) {

            this.apVndInvSqNum = apVndInvSqNum;
        }

        public String getRemSqNum() {
            return remSqNum;
        }

        public void setRemSqNum(String remSqNum) {

            this.remSqNum = remSqNum;
        }

        public String getCmCntnrBolNum() {
            return cmCntnrBolNum;
        }

        public void setCmCntnrBolNum(String cmCntnrBolNum) {

            this.cmCntnrBolNum = cmCntnrBolNum;
        }

        public String getCmCntnrBolCd() {
            return cmCntnrBolCd;
        }

        public void setCmCntnrBolCd(String cmCntnrBolCd) {

            this.cmCntnrBolCd = cmCntnrBolCd;
        }

        public String getInvDt() {
            return invDt;
        }

        public void setInvDt(String invDt) {

            this.invDt = invDt;
        }

        public String getCcyCd() {
            return ccyCd;
        }

        public void setCcyCd(String ccyCd) {

            this.ccyCd = ccyCd;
        }

        public String getPmtDueDt() {
            return pmtDueDt;
        }

        public void setPmtDueDt(String pmtDueDt) {

            this.pmtDueDt = pmtDueDt;
        }

        public BigDecimal getInvOcOrigCostAmt() {
            return invOcOrigCostAmt;
        }

        public void setInvOcOrigCostAmt(BigDecimal invOcOrigCostAmt) {

            this.invOcOrigCostAmt = chkNull(invOcOrigCostAmt);
        }

        public CntnrOrBol() {
            this.commitCntDtl = 0;
            this.commitCntLgscDtl = 0;
            this.isError = false;
        }

        public boolean isError() {
            return isError;
        }

        public void setError(boolean isError) {
            this.isError = isError;
        }

    }
    
    /**
     * Class name: ApInv
     */
    public static class ApInv {
        private String apVndCd;
        private String apVndInvNum;
        
        public String getApVndCd() {
            return apVndCd;
        }
        public void setApVndCd(String apVndCd) {
            this.apVndCd = apVndCd;
        }
        public String getApVndInvNum() {
            return apVndInvNum;
        }
        public void setApVndInvNum(String apVndInvNum) {
            this.apVndInvNum = apVndInvNum;
        }
        
        
        @Override
        public int hashCode() {
            final int PRIME = 31;
            int result = 1;
            result = PRIME * result + ((apVndCd == null) ? 0 : apVndCd.hashCode());
            result = PRIME * result + ((apVndInvNum == null) ? 0 : apVndInvNum.hashCode());
            return result;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            //if (!super.equals(obj))
            //    return false;
            if (getClass() != obj.getClass())
                return false;
            final ApInv other = (ApInv) obj;
            if (apVndCd == null) {
                if (other.apVndCd != null)
                    return false;
            } else if (!apVndCd.equals(other.apVndCd))
                return false;
            if (apVndInvNum == null) {
                if (other.apVndInvNum != null)
                    return false;
            } else if (!apVndInvNum.equals(other.apVndInvNum))
                return false;
            return true;
        }    
        
    }

    /**
     * Method name: getApVendorCd
     * <dd>The method explanation: get AP Vendor information.
     * <dd>Remarks:
     * @param glblCmpyCd String
     * @param strVndCd String
     * @return  List<Map> result set
     */
    @SuppressWarnings("unchecked")
    public static List<Map> getApVendorCd(String glblCmpyCd, String strVndCd) {
        Map<String, String> queryMap = new HashMap<String, String>();

        queryMap.put("glblCmpyCd", glblCmpyCd);
        queryMap.put("vndCd", strVndCd);
        queryMap.put("vndTpCd06", VND_TP_CD_06);
        queryMap.put("vndTpCd08", VND_TP_CD_08);
        queryMap.put("vndTpCd09", VND_TP_CD_09);
        queryMap.put("vndTpCd10", VND_TP_CD_10);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NFBCommonBusiness.class);

        List<Map> ssmResult = (List<Map>) ssmBatchClient.queryObjectList("getVendorInfo", queryMap);

        return ssmResult;
    }

    /**
     * Method name: getContOrBOLInfo
     * <dd>The method explanation: get preset invoice information depends on container or BOL code.
     * <dd>Remarks:
     * @param row CntnrOrBol
     * @return  List<Map> result set
     */
    @SuppressWarnings("unchecked")
    public static List<Map> getContOrBOLInfo(CntnrOrBol row) {
        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", row.glblCmpyCd);
        queryParam.put("cmCntnrBolNum", row.cmCntnrBolNum);

        List<Map> ssmResult;
        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NFBCommonBusiness.class);

        if (INV_CON.equals(row.cmCntnrBolCd)) {

            // In case of Container
            ssmResult = (List<Map>) ssmBatchClient.queryObjectList("getContainerInfo", queryParam);

        } else if (INV_BOL_A.equals(row.cmCntnrBolCd)) {

            // In case of Air BOL
            ssmResult = (List<Map>) ssmBatchClient.queryObjectList("getAirBolInfo", queryParam);

        } else {

            // In case of BOL
            ssmResult = (List<Map>) ssmBatchClient.queryObjectList("getBolInfo", queryParam);

        }

        return ssmResult;
    }

    /**
     * Method name: checkFreightRelatedAmount
     * <dd>The method explanation: Check if Freight-related amount is
     * input when FRT_PRCH_PMT_TERM_CD of preset invoice is "P".
     * <dd>Remarks:
     * @param vndCd String
     * @param vndInvNum String
     * @param vndInvSqNum String
     * @param frtPrchPmtTermCd String
     * @param row CntnrOrBol
     * @param cmCostelCdArr Array of CostMngmntInfo
     * @return int  0: success   1: warning  -9: Error
     */
    public static int checkFreightRelatedAmount(String vndCd, String vndInvNum, String vndInvSqNum, String frtPrchPmtTermCd, CntnrOrBol row, CostMngmntInfo[] cmCostelCdArr) {
        String prmTrmCd = frtPrchPmtTermCd;

        if (prmTrmCd == null || strNone.equals(prmTrmCd)) {
            prmTrmCd = checkRecordExistenceInCmInvHdr(vndCd, vndInvNum, vndInvSqNum, row.glblCmpyCd);
        }

        if (FRT_PRCH_PMT_TERM_CD_P.equals(prmTrmCd)) {

            for (int i = 0; i < cmCostelCdArr.length; i++) {

                if (cmCostelCdArr[i].cmFieldNm.equals("acOcFrtCostAmt")) {

                    if (row.acOcAmt.getAmoutForTheColumn(cmCostelCdArr[i].fieldNm).compareTo(BigDecimal.ZERO) != 0) {
                        return 1;
                    }


                } else if (!cmCostelCdArr[i].cmFieldNm.equals("acOcFobCostAmt") && !cmCostelCdArr[i].cmFieldNm.equals("acOcInsCostAmt")
                        && !cmCostelCdArr[i].cmFieldNm.equals("acOcDtyCostAmt") && !cmCostelCdArr[i].cmFieldNm.equals("acOcOthCostAmt")) {
                    return -9;
                }

            }

            return 0;

        } else {
            return 0;
        }

    }

    /**
     * Method name: checkRecordExistenceInCmInvHdr
     * <dd>The method explanation: Clear Business message.
     * <dd>Remarks:
     * @param vndCd String
     * @param vndInvNum String
     * @param vndInvSqNum String
     * @param glblCmpyCd String
     * @return frtPrchPmtTermCd String
     */
    private static String checkRecordExistenceInCmInvHdr(String vndCd, String vndInvNum, String vndInvSqNum, String glblCmpyCd) {

        CM_INV_HDRTMsg resultCmInvHdr = new CM_INV_HDRTMsg();

        resultCmInvHdr.glblCmpyCd.setValue(glblCmpyCd);
        resultCmInvHdr.vndCd.setValue(vndCd);
        resultCmInvHdr.vndInvNum.setValue(vndInvNum);
        resultCmInvHdr.vndInvSqNum.setValue(vndInvSqNum);

        resultCmInvHdr = (CM_INV_HDRTMsg) EZDTBLAccessor.findByKey(resultCmInvHdr);

        if (resultCmInvHdr == null) {
            return null;
        } else {
            return resultCmInvHdr.frtPrchPmtTermCd.getValue();
        }

    }

    /**
     * Method name: checkOtherCarrierInvoice
     * <dd>The method explanation: Check if there are other carrier
     * invoices. If there are other carrier invoices and the invoice
     * pay for Intl Frt or Dely, check if there are duplicated input
     * fields among other invoices.
     * <dd>Remarks:
     * @param row CntnrOrBol
     * @param vndCd String
     * @param vndInvNum String
     * @param vndInvSqNum String
     * @return ErrorMsgArg
     */
    @SuppressWarnings("unchecked")
    public static ErrorMsgArg checkOtherCarrierInvoice(CntnrOrBol row, String vndCd, String vndInvNum, String vndInvSqNum) {

        Map<String, String> queryMap = new HashMap<String, String>();

        queryMap.put("glblCmpyCd", row.glblCmpyCd);
        queryMap.put("apVndCd", row.apVndCd);
        queryMap.put("apVndInvNum", row.apVndInvNum);
        queryMap.put("apVndInvSqNum", row.apVndInvSqNum);
        queryMap.put("remSqNum", row.remSqNum);
        queryMap.put("vndCd", vndCd);
        queryMap.put("vndInvNum", vndInvNum);
        queryMap.put("vndInvSqNum", vndInvSqNum);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NFBCommonBusiness.class);
        List<Map> ssmResult = (List<Map>) ssmBatchClient.queryObjectList("getOtherCarrierInvoice", queryMap);

        if (ssmResult.size() > 0) {

            for (int i = 0; i < ssmResult.size(); i++) {
                Map map = (Map) ssmResult.get(i);

                boolean isWarning = false;

                /*
                 * Check Warning only for International Freight and Delivery
                 */
                BigDecimal acOcIntlFrtCostAmt = (BigDecimal) map.get("AC_OC_INTL_FRT_COST_AMT");
                BigDecimal acOcDelyCostAmt = (BigDecimal) map.get("AC_OC_DELY_COST_AMT");

                if (row.acOcAmt.aAcOcIntlFrtCostAmt.compareTo(BigDecimal.ZERO) != 0) {
                    if (acOcIntlFrtCostAmt.compareTo(BigDecimal.ZERO) != 0) {
                        isWarning = true;
                    }
                }

                if (row.acOcAmt.aAcOcDelyCostAmt.compareTo(BigDecimal.ZERO) != 0) {
                    if (acOcDelyCostAmt.compareTo(BigDecimal.ZERO) != 0) {
                        isWarning = true;
                    }
                }

                if (isWarning == true) {
                    ErrorMsgArg msgArg = new ErrorMsgArg();
                    if (ZYPCommonFunc.hasValue((String) map.get("AP_VND_CD"))) {
                        // For NFBM0171W Message Argument
                        msgArg.apVndCd = (String) map.get("AP_VND_CD");
                    }
                    if (ZYPCommonFunc.hasValue((String) map.get("AP_VND_INV_NUM"))) {
                        // For NFBM0171W Message Argument
                        msgArg.apVndInvNum = (String) map.get("AP_VND_INV_NUM");
                    }
                    if (ZYPCommonFunc.hasValue((String) map.get("INV_DT"))) {
                        // For NFBM0171W Message Argument
                        msgArg.invDt = (String) map.get("INV_DT");
                    }
                    if (ZYPCommonFunc.hasValue((BigDecimal) map.get("INV_OC_ORIG_COST_AMT"))) {
                        // For NFBM0171W Message Argument
                        msgArg.invOcOrigCostAmt = (BigDecimal) map.get("INV_OC_ORIG_COST_AMT");
                    }

                    return msgArg;
                }
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * Method name: checkOtherCarrierInvoice (for CSV Download)
     * <dd>The method explanation: Check if there are other carrier
     * invoices. If there are other carrier invoices and the invoice
     * pay for Intl Frt or Dely, check if there are duplicated input
     * fields among other invoices.
     * <dd>Remarks:
     * @param row CntnrOrBol
     * @param vndCd String
     * @param vndInvNum String
     * @param vndInvSqNum String
     * @return ErrorMsgArg
     */
    @SuppressWarnings("unchecked")
    public static List<Map> checkOtherCarrierInvoice(CntnrOrBol row, String vndCd, String vndInvNum, String vndInvSqNum, int type) {

        Map<String, String> queryMap = new HashMap<String, String>();

        queryMap.put("glblCmpyCd", row.glblCmpyCd);
        queryMap.put("apVndCd", row.apVndCd);
        queryMap.put("apVndInvNum", row.apVndInvNum);
        queryMap.put("apVndInvSqNum", row.apVndInvSqNum);
        queryMap.put("remSqNum", row.remSqNum);
        queryMap.put("vndCd", vndCd);
        queryMap.put("vndInvNum", vndInvNum);
        queryMap.put("vndInvSqNum", vndInvSqNum);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NFBCommonBusiness.class);
        List<Map> ssmResult = (List<Map>) ssmBatchClient.queryObjectList("getOtherCarrierInvoice", queryMap);

        return ssmResult;
    }
    
    /**
     * chkNull
     * If the passed value is null then return BigDecimal.ZERO.
     * @param val BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal chkNull(BigDecimal val) {

        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return BigDecimal.ZERO;
        }
    }
    
    /**
     * chkNull
     * If the passed value is null then return "".
     * @param val String
     * @return String
     */
    public static String chkNull(String val) {

        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return strNone;
        }
    }

    /**
     * Method name: checkInlandWorkOrder
     * <dd>The method explanation: Check Inland Work Order
     * exsistence.
     * <dd>Remarks:
     * @param vndCd String
     * @param vndInvNum String
     * @param glblCmpyCd String
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean checkInlandWorkOrder(String vndCd, String vndInvNum, String glblCmpyCd) {

        Map<String, String> queryMap = new HashMap<String, String>();

        queryMap.put("glblCmpyCd", glblCmpyCd);
        queryMap.put("vndCd", vndCd);
        queryMap.put("vndInvNum", vndInvNum);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NFBCommonBusiness.class);
        List<Map> ssmResult = (List<Map>) ssmBatchClient.queryObjectList("getInlandWorkOrder", queryMap);

        if (ssmResult != null && ssmResult.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method name: formatEmailQtyString
     * <dd>Method explanation: Change amount format to "#,###".
     * @author Y.Aikawa
     * @param qty BigDecimal
     * @return result String
     */
    public static String formatEmailQtyString(BigDecimal qty) {

        DecimalFormat df = new DecimalFormat();
        df.applyPattern("#,###");
        df.setMaximumFractionDigits(0);
        df.setMinimumFractionDigits(0);
        String result = df.format(qty);

        return result;
    }

    /**
     * Method name: formatEmailAmountString
     * <dd>Method explanation: Change amount format to "$#,###.##".
     * @author Y.Aikawa
     * @param amount BigDecimal
     * @return result String
     */
    public static String formatEmailAmountString(BigDecimal amount) {
        
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("$#,###.##");
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        String result = df.format(amount);
        
        return result;
    }

    /**
     * Method name: changeEZDTMsgArrayLength
     * <dd>Method explanation: Change EZDTMsg array length.
     * @author Y.Aikawa
     * @param arrEZDTMsg EZDTMsg[]
     * @param iNewArrLen int
     * @return arrEZDTMsg EZDTMsg[]
     */
    public static EZDTMsg[] changeEZDTMsgArrayLength(EZDTMsg[] arrEZDTMsg, int iNewArrLen) {

        EZDTMsg arrEZDTMsgBk[] = arrEZDTMsg.clone();
        arrEZDTMsg = new EZDTMsg[iNewArrLen];
        for (int i = 0; i < iNewArrLen; i++) {
            arrEZDTMsg[i] = arrEZDTMsgBk[i];
        }
        return arrEZDTMsg;
    }
    
    /**
     * Method name: bulkInsertRestOfRecords
     * <dd>Method explanation: Bulk insert the rest of the records in TMsg array.
     * @author Y.Aikawa
     * @param arrEZDTMsg EZDTMsg[]
     * @param iNewArrLen int
     * @return int
     */
    public static int bulkInsertRestOfRecords(EZDTMsg[] arrEZDTMsg, int iNewArrLen) {
        arrEZDTMsg = changeEZDTMsgArrayLength(arrEZDTMsg, iNewArrLen);
        return S21FastTBLAccessor.insert(arrEZDTMsg);
    }
    
    /**
     * Method name: bulkUpdateRestOfRecords
     * <dd>Method explanation: Bulk update the rest of the records in TMsg array.
     * @author Y.Aikawa
     * @param arrEZDTMsg EZDTMsg[]
     * @param iNewArrLen int
     * @return int
     */
    public static int bulkUpdateRestOfRecords(EZDTMsg[] arrEZDTMsg, int iNewArrLen) {
        arrEZDTMsg = changeEZDTMsgArrayLength(arrEZDTMsg, iNewArrLen);
        return S21FastTBLAccessor.update(arrEZDTMsg);
    }
    
    /**
     * Method name: bulkPhysicalRemoveRestOfRecords
     * <dd>Method explanation: Bulk update the rest of the records in TMsg array.
     * @author Y.Aikawa
     * @param arrEZDTMsg EZDTMsg[]
     * @param iNewArrLen int
     * @return int
     */
    public static int bulkPhysicalRemoveRestOfRecords(EZDTMsg[] arrEZDTMsg, int iNewArrLen) {
        arrEZDTMsg = changeEZDTMsgArrayLength(arrEZDTMsg, iNewArrLen);
        return S21FastTBLAccessor.removePhysical(arrEZDTMsg);
    }

    /**
     * Method name: bulkLogicalRemoveRestOfRecords
     * <dd>Method explanation: Bulk update the rest of the records in TMsg array.
     * @author Y.Aikawa
     * @param arrEZDTMsg EZDTMsg[]
     * @param iNewArrLen int
     * @return int
     */
    public static int bulkLogicalRemoveRestOfRecords(EZDTMsg[] arrEZDTMsg, int iNewArrLen) {
        arrEZDTMsg = changeEZDTMsgArrayLength(arrEZDTMsg, iNewArrLen);
        return S21FastTBLAccessor.removeLogical(arrEZDTMsg);
    }

    /**
     * 
     * sendMail
     * 
     * @param mailParamMap Map<String, String>
     * @param template S21MailTemplate
     * @author Y.Aikawa
     */
    public static void sendMail(Map<String, String> mailParamMap, S21MailTemplate template){
        
        /****************************
         * Get Parameter
         ****************************/
        String glblCmpyCd = (String)mailParamMap.get("glblCmpyCd");
        String groupIdTo = (String)mailParamMap.get("groupIdTo");
        String groupToMailKey1 = (String)mailParamMap.get("groupToMailKey1");
        String groupToMailKey2 = (String)mailParamMap.get("groupToMailKey2");
        String groupToMailKey3 = (String)mailParamMap.get("groupToMailKey3");
        String groupIdCc = (String)mailParamMap.get("groupIdCc");
        String groupCcMailKey1 = (String)mailParamMap.get("groupCcMailKey1");
        String groupCcMailKey2 = (String)mailParamMap.get("groupCcMailKey2");
        String groupCcMailKey3 = (String)mailParamMap.get("groupCcMailKey3");
        String groupIdBcc = (String)mailParamMap.get("groupIdBcc");
        String groupBccMailKey1 = (String)mailParamMap.get("groupBccMailKey1");
        String groupBccMailKey2 = (String)mailParamMap.get("groupBccMailKey2");
        String groupBccMailKey3 = (String)mailParamMap.get("groupBccMailKey3");
        /****************************
         * Mandatory Parameter Check
         ****************************/
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"Global Company Code" });
        }
        if (!ZYPCommonFunc.hasValue(groupIdTo)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"To Group ID" });
        }
        if (!ZYPCommonFunc.hasValue(groupToMailKey1)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"To Mail key 1" });
        }
        if (template == null) {
            throw new S21AbendException(ZZMM0007E, new String[] {"Mail Template" });
        }
        /*******************************
         * Set "From" email address to
         * "S21_NFB@cusa.canon.com"
         *******************************/
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GRP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY_1_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        S21MailAddress from = null;
        if (addrFromList.size() <= 0) {
            throw new S21AbendException(ZZMM0007E, new String[] {"From Address" });
        } else {
            from = addrFromList.get(0);
        }
        /*******************************
         * Get "To" email address
         *******************************/
        S21MailGroup groupTo;
        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>();
        if (ZYPCommonFunc.hasValue(groupIdTo)) {
            groupTo = new S21MailGroup(glblCmpyCd, groupIdTo);
            groupTo.setMailKey1(groupToMailKey1);
            if (ZYPCommonFunc.hasValue(groupToMailKey2)) {
                groupTo.setMailKey2(groupToMailKey2);
            }
            if (ZYPCommonFunc.hasValue(groupToMailKey3)) {
                groupTo.setMailKey3(groupToMailKey3);
            }
            addrToList = groupTo.getMailAddress();
        }
        /*******************************
         * Get "CC" email address
         *******************************/
        S21MailGroup groupCc;
        List<S21MailAddress> addrCcList = new ArrayList<S21MailAddress>();
        if (ZYPCommonFunc.hasValue(groupIdCc)) {
            groupCc = new S21MailGroup(glblCmpyCd, groupIdCc);
            groupCc.setMailKey1(groupCcMailKey1);
            if (ZYPCommonFunc.hasValue(groupCcMailKey2)) {
                groupCc.setMailKey2(groupCcMailKey2);
            }
            if (ZYPCommonFunc.hasValue(groupCcMailKey3)) {
                groupCc.setMailKey3(groupCcMailKey3);
            }
            addrCcList = groupCc.getMailAddress();
        }
        /*******************************
         * Get "BCC" email address
         *******************************/
        S21MailGroup groupBcc;
        List<S21MailAddress> addrBccList = new ArrayList<S21MailAddress>();
        if (ZYPCommonFunc.hasValue(groupIdBcc)) {
            groupBcc = new S21MailGroup(glblCmpyCd, groupIdBcc);
            groupBcc.setMailKey1(groupBccMailKey1);
            if (ZYPCommonFunc.hasValue(groupBccMailKey2)) {
                groupBcc.setMailKey2(groupBccMailKey2);
            }
            if (ZYPCommonFunc.hasValue(groupBccMailKey3)) {
                groupBcc.setMailKey3(groupBccMailKey3);
            }
            addrBccList = groupBcc.getMailAddress();
        }
        /*******************************
         * Send mail.
         *******************************/
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setMailTemplate(template);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setCcAddressList(addrCcList);
        mail.setBccAddressList(addrBccList);
        mail.setSubject(template.getSubject("en"), "ISO-8859-1");
        if (mailParamMap.containsKey("attachmentId")) {
            mail.setAttachmentId(Integer.parseInt((String)mailParamMap.get("attachmentId")));
        }
        // START 2019/02/28 S.Ohki [QC#30584, MOD]
//        String mailEvent = mail.sendMail();
        String mailEvent = mail.postMail();
        // END 2019/02/28 S.Ohki [QC#30584, MOD]
        if(!hasValue(mailEvent)){
            return;
        }
    }

    /**
     * Method name: getCmProcDt
     * <dd>The method explanation: get CM_PROC_DT.
     * <dd>Remarks:
     * @param glblCmpyCd String
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String getCmProcDt(String glblCmpyCd) {
        // START 2017/12/12 E.Kameishi [QC#23052, MOD]
//        Map<String, String> queryMap = new HashMap<String, String>();
//
//        queryMap.put("glblCmpyCd", glblCmpyCd);
//
//        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NFBCommonBusiness.class);
//
//        List<String> ssmResult = (List<String>) ssmBatchClient.queryObjectList("getCmProcDt", queryMap);
//
//        if (ssmResult.size() <= 0) {
//            return null;
//        } else {
//            return (String)ssmResult.get(0);
//        }
        return ZYPDateUtil.getBatProcDate(glblCmpyCd);
        // END 2017/12/12 E.Kameishi [QC#23052, MOD]
        
    }

    /**
     * Method name: getCmVndConvCd
     * <dd>The method explanation: get CM_VND_CONV_CD from CM_VND_CONV table.
     * <dd>Remarks:
     * @param glblCmpyCd String
     * @param apVndCd    String
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String getCmVndConvCd(String glblCmpyCd, String apVndCd) {
        Map<String, String> queryMap = new HashMap<String, String>();

        queryMap.put("glblCmpyCd", glblCmpyCd);
        queryMap.put("apVndCd", apVndCd);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NFBCommonBusiness.class);

        List<String> ssmResult = (List<String>) ssmBatchClient.queryObjectList("getCmVndConvCd", queryMap);

        if (ssmResult.size() <= 0 || !ZYPCommonFunc.hasValue((String)ssmResult.get(0))) {
            return apVndCd;
        } else {
            return (String)ssmResult.get(0);
        }
        
    }    

    /**
     * Method name: getCmVndConvCdArr
     * <dd>The method explanation: get CM_VND_CONV_CD array from CM_VND_CONV table.
     * <dd>Remarks:
     * @param glblCmpyCd String
     * @param apVndCd    String
     * @return List<String>
     */
    @SuppressWarnings("unchecked")
    public static List<String> getCmVndConvCdArr(String glblCmpyCd, String apVndCd) {
        Map<String, String> queryMap = new HashMap<String, String>();

        queryMap.put("glblCmpyCd", glblCmpyCd);
        queryMap.put("apVndCd", apVndCd);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NFBCommonBusiness.class);

        List<String> ssmResult = (List<String>) ssmBatchClient.queryObjectList("getCmVndConvCdArr", queryMap);

        if (ssmResult.size() <= 0) {
            List<String> resultList = new ArrayList<String>();
            resultList.add(apVndCd);
            return resultList;
        } else {
            return ssmResult;
        }
        
    }
    
    /**
     * Method name: getMMDDYYYYWithSlash
     * <dd>The method explanation: Convert YYYYMMDD to MM/DD/YYYY.
     * <dd>Remarks:
     * @param strDt String
     * @return String
     */
    public static String getMMDDYYYYWithSlash(String strDt) {
        if (ZYPCommonFunc.hasValue(strDt) 
        &&  strDt.length() == 8) {
            return strDt.substring(4, 6) + "/" + strDt.substring(6, 8) + "/" + strDt.substring(0, 4);
        } else {
            return strNone;
        }
    }    
    
    /**
     * Method name: getMaxCmHistTs
     * <dd>The method explanation: get max CM_HIST_TS value from CM_INV_IMPT_TRK table.
     * <dd>Remarks:
     * @param glblCmpyCd String
     * @param cmPgmId    String
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String getMaxCmHistTs(String glblCmpyCd, String cmPgmId) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("glblCmpyCd", glblCmpyCd);
        queryMap.put("cmPgmId", cmPgmId);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NFBCommonBusiness.class);

        List<String> ssmResult = (List<String>) ssmBatchClient.queryObjectList("getMaxCmHistTs", queryMap);

        if (ssmResult.size() <= 0) {
            return null;
        } else {
            return (String)ssmResult.get(0);
        }
        
    }
    
    /**
     * Method name: insertCmInvImptTrk
     * <dd>The method explanation: Insert a record into CM_INV_IMPT_TRK table.
     * <dd>Remarks:
     * @param glblCmpyCd String
     * @param cmPgmId    String
     * @param cmHistTs   String
     * @param cmProcDt   String
     * @return void
     */
    public static void insertCmInvImptTrk(String glblCmpyCd, String cmPgmId, String cmHistTs, String cmProcDt) {
        
        CM_INV_IMPT_TRKTMsg cmInvImptTrk = new CM_INV_IMPT_TRKTMsg();

        DFBEZDItemValueSetter.setValue(cmInvImptTrk.glblCmpyCd, glblCmpyCd);
        DFBEZDItemValueSetter.setValue(cmInvImptTrk.cmPgmId, cmPgmId);
        DFBEZDItemValueSetter.setValue(cmInvImptTrk.cmHistTs, cmHistTs);
        DFBEZDItemValueSetter.setValue(cmInvImptTrk.cmProcDt, cmProcDt);

        EZDTBLAccessor.insert(cmInvImptTrk);
        
    }

    public static String csvQuote(String str) {
        str = chkNull(str);
        str = "\"" + str + "\"";
        return str;
    }

    /**
     * Method name: getStdCcyCd
     * <dd>The method explanation: get STD_CCY_CD from GLBL_CMPY table.
     * <dd>Remarks:
     * @param glblCmpyCd String
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String getStdCcyCd(String glblCmpyCd) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("glblCmpyCd", glblCmpyCd);
        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NFBCommonBusiness.class);
        List<String> ssmResult = (List<String>) ssmBatchClient.queryObjectList("getStdCcyCd", queryMap);
        if (ssmResult.size() <= 0) {
            return null;
        } else {
            return (String)ssmResult.get(0);
        }
    }
    
    /**
     * 
     * @param glblCmpyCd
     * @param ccyCd
     * @return CCYTMsg
     */
    public static CCYTMsg getCcyInfo(String glblCmpyCd, String ccyCd) {
        
        CCYTMsg ccyTMsg = new CCYTMsg();
        ccyTMsg.glblCmpyCd.setValue(glblCmpyCd);
        ccyTMsg.ccyCd.setValue(ccyCd);
        
        CCYTMsg outTMsg = (CCYTMsg) EZDTBLAccessor.findByKey(ccyTMsg);
        
        return outTMsg;
    }

    /**
     * 
     * @param queryMap
     * @param stdCostAmt
     * @param stdExchRate
     * @return
     */
    public static BigDecimal calcStdAmt(BigDecimal origCostAmt, BigDecimal stdExchRate, String acctArthTpCd, BigDecimal aftDeclPntDigitNum) {

        BigDecimal stdCostAmt = new BigDecimal(0.00);
       int digitNum = aftDeclPntDigitNum.intValue();

        // Map<String, String> queryParam = new HashMap<String, String>();

        if (acctArthTpCd.equals("*")) {
            // Standard Currency Amount = Original Currency Amount *  Standard Currency Rate
            stdCostAmt = origCostAmt.multiply(stdExchRate);
            stdCostAmt = stdCostAmt.setScale(digitNum, BigDecimal.ROUND_HALF_UP);

        } else if (acctArthTpCd.equals("/")) {
            // Standard Currency Amount = Original Currency Amount / Standard Currency Rate

            if (stdExchRate.compareTo(BigDecimal.ZERO) != 0) {
                stdCostAmt = origCostAmt.divide(stdExchRate, digitNum, BigDecimal.ROUND_HALF_UP);
            } else {
                return null;
            }
        } else {
            return null;
        }
        return stdCostAmt;
    }

    /**
     * 
     * @param glblCmpyCd
     * @param ccyCd
     * @param procDt
     * @return
     */
    @SuppressWarnings("unchecked")
    public static BigDecimal getAcctDlyActlExchRate(String glblCmpyCd, String ccyCd, String procDt) {
        Map<String, String> queryMap = new HashMap<String, String>();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)
        ||  !ZYPCommonFunc.hasValue(ccyCd)
        ||  !ZYPCommonFunc.hasValue(procDt)) {
            return null;
        }

        queryMap.put("glblCmpyCd", glblCmpyCd);
        queryMap.put("ccyCd", ccyCd);
        queryMap.put("actlExchRateEntDt", procDt);

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NFBCommonBusiness.class);

        List<BigDecimal> ssmResult = (List<BigDecimal>) ssmBatchClient.queryObjectList("getAcctDlyActlExchRates", queryMap);

        if (ssmResult.size() <= 0) {
            return null;
        } else {
            Map map = (Map) ssmResult.get(0);
            BigDecimal exchRate = (BigDecimal) map.get("ACTL_EXCH_RATE");
            return (BigDecimal)exchRate;
        }
        
    }
    
    /**
     * calc Distribution
     * @param totalAmt
     * @param srcAmt
     * @param destAmt
     * @param scale
     * @return
     */
    public static BigDecimal calcDistribution(BigDecimal totalAmt, BigDecimal srcAmt, BigDecimal dstAmt, int scale) {
     
        BigDecimal resultAmt = new BigDecimal("0.00");
        
        BigDecimal ratio = new BigDecimal("0.00");
        
        if (totalAmt.intValue() == 0) {
            return BigDecimal.ZERO;
        }
        
        // Change ratio to round half up at 7th decimal point
        ratio = srcAmt.divide(totalAmt, 6, BigDecimal.ROUND_HALF_UP);
        
        resultAmt = dstAmt.multiply(ratio);
        
        resultAmt = resultAmt.setScale(scale, BigDecimal.ROUND_HALF_UP);
        
        return resultAmt;
    }

    /**
     * Method name: setZeroIntoCmInvAcctDist
     * <dd>The method explanation: Set zero value to Number items on TMsg
     * header.
     * <dd>Remarks:
     * @param outRecord CM_INV_ACCT_DISTTMsg
     */
    public static void setZeroIntoCmInvAcctDist(CM_INV_ACCT_DISTTMsg outRecord) {
        ZYPEZDItemValueSetter.setValue(outRecord.invQty,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.poQty,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.invRcvQty,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.apBillQty,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.apRejQty,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.thisMthFobCostAmt,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.acInvTaxAmt,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.acInvJrnlCostAmt,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.entDealNetUnitPrcAmt,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.entPoDtlDealNetAmt,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.entFuncNetUnitPrcAmt,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.entPoDtlFuncNetAmt,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.exchRate,BigDecimal.ZERO);
    }

    /**
     * Method name: setZeroIntoCmAcrlWriteOff
     * <dd>The method explanation: Set zero value to Number items on TMsg
     * header.
     * <dd>Remarks:
     * @param outRecord CM_ACRL_WRITE_OFFTMsg
     */
    public static void setZeroIntoCmAcrlWriteOff(CM_ACRL_WRITE_OFFTMsg outRecord) {
        ZYPEZDItemValueSetter.setValue(outRecord.invQty,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.poQty,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.invRcvQty,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.thisMthFobCostAmt,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.acInvTaxAmt,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(outRecord.acInvJrnlCostAmt,BigDecimal.ZERO);
    }

    // START 2016/12/09 [QC#16132, ADD]
    /**
     * <pre>
     *  Get default values of COA
     * </pre>
     * @param glblCmpyCd
     * @return DefCoaValues
     */
    public static DefCoaValues getDefCoaValues(String glblCmpyCd) {

        String defVals = ZYPCodeDataUtil.getVarCharConstValue(AJE_COA_DEF_VALUES, glblCmpyCd);

        StringTokenizer st = new StringTokenizer(defVals, ",");

        DefCoaValues defCoa = new DefCoaValues();

        int cnt = 0;
        String val;
        while (st.hasMoreTokens()) {
            val = st.nextToken();
            cnt++;
            switch (cnt) {
                case 1:
                    defCoa.setDefCoaCmpy(val);
                    break;
                case 2:
                    defCoa.setDefCoaBr(val);
                    break;
                case 3:
                    defCoa.setDefCoaCc(val);
                    break;
                case 4:
                    defCoa.setDefCoaAcct(val);
                    break;
                case 5:
                    defCoa.setDefCoaProd(val);
                    break;
                case 6:
                    defCoa.setDefCoaCh(val);
                    break;
                case 7:
                    defCoa.setDefCoaAffl(val);
                    break;
                case 8:
                    defCoa.setDefCoaProj(val);
                    break;
                case 9:
                    defCoa.setDefCoaExtn(val);
                    break;
                default:
                    break;
            }
        }
        return defCoa;
    }

    public static class DefCoaValues {
        String defCoaCmpy;

        String defCoaBr;

        String defCoaCc;

        String defCoaAcct;

        String defCoaProd;

        String defCoaCh;

        String defCoaAffl;

        String defCoaProj;

        String defCoaExtn;

        public String getDefCoaCmpy() {
            return defCoaCmpy;
        }

        public void setDefCoaCmpy(String defCoaCmpy) {
            this.defCoaCmpy = defCoaCmpy;
        }

        public String getDefCoaBr() {
            return defCoaBr;
        }

        public void setDefCoaBr(String defCoaBr) {
            this.defCoaBr = defCoaBr;
        }

        public String getDefCoaCc() {
            return defCoaCc;
        }

        public void setDefCoaCc(String defCoaCc) {
            this.defCoaCc = defCoaCc;
        }

        public String getDefCoaAcct() {
            return defCoaAcct;
        }

        public void setDefCoaAcct(String defCoaAcct) {
            this.defCoaAcct = defCoaAcct;
        }

        public String getDefCoaProd() {
            return defCoaProd;
        }

        public void setDefCoaProd(String defCoaProd) {
            this.defCoaProd = defCoaProd;
        }

        public String getDefCoaCh() {
            return defCoaCh;
        }

        public void setDefCoaCh(String defCoaCh) {
            this.defCoaCh = defCoaCh;
        }

        public String getDefCoaAffl() {
            return defCoaAffl;
        }

        public void setDefCoaAffl(String defCoaAffl) {
            this.defCoaAffl = defCoaAffl;
        }

        public String getDefCoaProj() {
            return defCoaProj;
        }

        public void setDefCoaProj(String defCoaProj) {
            this.defCoaProj = defCoaProj;
        }

        public String getDefCoaExtn() {
            return defCoaExtn;
        }

        public void setDefCoaExtn(String defCoaExtn) {
            this.defCoaExtn = defCoaExtn;
        }
    }
    // END 2016/12/09 [QC#16132, ADD]
}
