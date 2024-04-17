/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * 
 * <pre>
 *
 * Date         Company    Name         Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/10/2010   Canon      K.Usui       Create          N/A
 * 02/24/2010   Canon      I.Kondo      Update          N/A
 * 03/10/2010   Canon      I.Kondo      Update          N/A
 * 08/02/2010   Canon      I.Kondo      Update          Merge.
 *</pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC308001;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_ACCT_DTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * Common method for CSV
 */
public class NFCCsvCmnMethod {

    /** Upload CSV ID For Receipt */
    public static final String CST_UPLOAD_CSV_RECEIPT = "NFC0010001";

    /** Upload CSV ID For Paymentech1 */
    public static final String CST_UPLOAD_CSV_PAYMENTECH_1 = "NFC0040001";

    /** Upload CSV ID For Paymentech2 */
    public static final String CST_UPLOAD_CSV_PAYMENTECH_2 = "NFC0060001";

    /** SLASH */
    private static final String SLASH = "/";

    /** ZERO */
    private static final String ZERO = "0";

    /** day amount 2 */
    private static final int DAY_AMOUNT_2 = 2;

    /** day amount 3 */
    private static final int DAY_AMOUNT_3 = 3;

    /* For checkGlDt */

    /** Failed to obtain sub-system ID. */
    protected static final String NFCM0632E = "NFCM0632E";

    /** Failed to obtain GL date from the Master. */
    protected static final String NFCM0650E = "NFCM0650E";

    /** 00 */
    private static final String CST_MONTH_FORMAT_00 = "00";

    /** int:0 */
    private static final int CST_INT_NUM_0 = 0;

    /** int:1 */
    private static final int CST_INT_NUM_1 = 1;

    /** int:4 */
    private static final int CST_INT_NUM_4 = 4;

    /** int:6 */
    private static final int CST_INT_NUM_6 = 6;

    /** int:12 */
    private static final int CST_INT_NUM_12 = 12;

    /** DT_MONTH_LENGTH */
    private static final int DT_MONTH_LENGTH = 2;

    /** DT_DAY_LENGTH */
    private static final int DT_DAY_LENGTH = 2;

    /** DT_YEAR_LENGTH */
    private static final int DT_YEAR_LENGTH = 4;

    /**
     * Return new Date String.<br>
     * ex) NFCConst.CST_FLAG_ON) 5/1/2010 -> 05/01/2010 <br>
     * ex) NFCConst.CST_FLAG_OFF) 5/1/2010 -> 20100501 <br>
     * @param origDateStr String (like MM/dd/yyyy or M/d/yyyy)
     * @param dateStrCreatePtn String
     * @return String
     */
    public static String getDateStr(String origDateStr, String dateStrCreatePtn) {
        String[] dateStrArray = origDateStr.split(SLASH);

        String mStr = ZYPCommonFunc.leftPad(dateStrArray[0], DT_MONTH_LENGTH, ZERO);
        String dStr = ZYPCommonFunc.leftPad(dateStrArray[1], DT_DAY_LENGTH, ZERO);
        String yStr = ZYPCommonFunc.leftPad(dateStrArray[2], DT_YEAR_LENGTH, ZERO);

        if (NFCConst.CST_FLAG_ON.equals(dateStrCreatePtn)) {
            StringBuffer sb = new StringBuffer();
            sb.append(mStr).append(SLASH).append(dStr).append(SLASH).append(yStr);
            return sb.toString();
        } else if (NFCConst.CST_FLAG_OFF.equals(dateStrCreatePtn)) {
            StringBuffer sb = new StringBuffer();
            sb.append(yStr).append(mStr).append(dStr);
            return sb.toString();
        } else {
            return null;
        }
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param date date
     * @return true:businessDay,false:not businessDay
     */
    public static String isBusinessDay(String glblCmpyCd, String date) {

        String rtnDate = NFCConst.CST_DB_INIT_VAL_STR;

        if (ZYPDateUtil.isBusinessDay(glblCmpyCd, date)) {
            rtnDate = ZYPDateUtil.addBusinessDay(glblCmpyCd, date, DAY_AMOUNT_2);
        } else {
            rtnDate = ZYPDateUtil.addBusinessDay(glblCmpyCd, date, DAY_AMOUNT_3);
        }
        return rtnDate;
    }

    /**
     * Acquiring the accounting years
     * @param glblCmpyCd String
     * @return ACCT_YR_MTH
     */
    public static String findAcctYrMth(String glblCmpyCd) {

        final String subSysId = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_SUB_SYS_ID, glblCmpyCd);

        if (S21StringUtil.isEmpty(subSysId)) {
            throw new S21AbendException(NFCM0632E);
        } else {

            final AR_ACCT_DTTMsg inTMsg = new AR_ACCT_DTTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.subSysCd, subSysId);
            ZYPEZDItemValueSetter.setValue(inTMsg.onlBatTpCd, NFCConst.CST_ONL_BAT_TP_CD_ONL);

            final AR_ACCT_DTTMsg resultTMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inTMsg);

            if (resultTMsg == null) {
                // Failed to obtain GL date from the Master.
                throw new S21AbendException(NFCM0650E);
            } else {
                String acctYrMth = resultTMsg.acctYrMth.getValue();
                debugLog("ACCT_YR_MTH = < " + acctYrMth + " >");
                return acctYrMth;
            }
        }
    }

    /**
     * Get Prev YearMon String.
     * @param date String(6digit, yyyyMM)
     * @return String(6digit, yyyyMM
     */
    public static String getPrevYearMon(String date) {
        if (date.length() != CST_INT_NUM_6) {
            return "";
        }
        String prevYeanMon = "";
        int year = Integer.parseInt(date.substring(CST_INT_NUM_0, CST_INT_NUM_4));
        int month = Integer.parseInt(date.substring(CST_INT_NUM_4, CST_INT_NUM_6));
        month = month - CST_INT_NUM_1;
        if (month < CST_INT_NUM_1) {
            month = CST_INT_NUM_12;
            year = year - CST_INT_NUM_1;
        }
        DecimalFormat monthFormat = new DecimalFormat(CST_MONTH_FORMAT_00);

        prevYeanMon = String.valueOf(year) + monthFormat.format(month);
        return prevYeanMon;
    }

    /**
     * The date when dt1 is larger than dt2 is judged. <br />
     * Right side 0 is buried to become eight digits when the digit
     * number for the input value doesn't come up to eight digits and
     * it judges. <br />
     * @param date1 Date 1 of object of comparison
     * @param date2 Date 2 of object of comparison
     * @return true: Dt1 is larger. false: Dt1 is not larger.
     */
    public static boolean isAfter(final String date1, final String date2) {
        final String yyyyMMdd1 = ZYPCommonFunc.rightPad(date1, ZYPDateUtil.TYPE_YYYYMMDD.length(), BigDecimal.ZERO.toString());
        final String yyyyMMdd2 = ZYPCommonFunc.rightPad(date2, ZYPDateUtil.TYPE_YYYYMMDD.length(), BigDecimal.ZERO.toString());
        return ZYPDateUtil.compare(yyyyMMdd1, yyyyMMdd2) > 0;
    }

    /**
     * The date when dt1 is smaller than dt2 is judged. <br />
     * Right side 0 is buried to become eight digits when the digit
     * number for the input value doesn't come up to eight digits and
     * it judges. <br />
     * @param date1 Date 1 of object of comparison
     * @param date2 Date 2 of object of comparison
     * @return true: Dt1 is smaller. false: Dt1 is not smaller.
     */
    public static boolean isBefore(final String date1, final String date2) {
        final String yyyyMMdd1 = ZYPCommonFunc.rightPad(date1, ZYPDateUtil.TYPE_YYYYMMDD.length(), BigDecimal.ZERO.toString());
        final String yyyyMMdd2 = ZYPCommonFunc.rightPad(date2, ZYPDateUtil.TYPE_YYYYMMDD.length(), BigDecimal.ZERO.toString());
        return ZYPDateUtil.compare(yyyyMMdd1, yyyyMMdd2) < 0;
    }

    /**
     * The date when dt1 and dt2 are the same is judged. Right side 0
     * is buried to become eight digits when the digit number for the
     * input value doesn't come up to eight digits and it judges.
     * <br />
     * @param date1 Date 1 of object of comparison
     * @param date2 Date 2 of object of comparison
     * @return true: Date when dt1 and dt2 are the same. false: Date
     * when dt1 and dt2 are different
     */
    public static boolean isSameDate(final String date1, final String date2) {
        final String yyyyMMdd1 = ZYPCommonFunc.rightPad(date1, ZYPDateUtil.TYPE_YYYYMMDD.length(), BigDecimal.ZERO.toString());
        final String yyyyMMdd2 = ZYPCommonFunc.rightPad(date2, ZYPDateUtil.TYPE_YYYYMMDD.length(), BigDecimal.ZERO.toString());
        return ZYPDateUtil.compare(yyyyMMdd1, yyyyMMdd2) == 0;
    }

    /**
     * Debug Log Print
     * @param logmsg String
     */
    private static void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, "ACCT_YR_MTH = < " + logmsg + " >", NFCCsvCmnMethod.class);
    }

}
