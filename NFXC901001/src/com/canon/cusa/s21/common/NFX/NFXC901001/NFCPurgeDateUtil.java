/**
 * <pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/20/2011   Fujitsu         I.Kondo         Create          N/A
 * </pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC901001;

import business.db.AR_ACCT_DTTMsg;

import com.canon.cusa.s21.common.NFX.NFXC901001.NFCPurgeConst.SRC_RETEN_NUM_TP_CD;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;

/**
 * For Data Purge Util.
 */
public final class NFCPurgeDateUtil {

    /** FIRST_DAY */
    private static final String FIRST_DAY = "01";

    /** YEAR_POS_START */
    private static final int YEAR_POS_START = 0;

    /** YEAR_POS_END */
    private static final int YEAR_POS_END = 4;

    /** MONTH_POS_START */
    private static final int MONTH_POS_START = 4;

    /** MONTH_POS_END */
    private static final int MONTH_POS_END = 6;

    /** LAST_MONTH */
    private static final int LAST_MONTH = 12;

    /** LAST_TWO_DIGIT_MONTH */
    private static final int LAST_TWO_DIGIT_MONTH = 10;

    private NFCPurgeDateUtil() {
    }

    /**
     * This method will return Year Month string for
     * recRetenStartYrMth column.
     * @return String
     */
    public static String getRecRetenStartYrMth() {
        return ZYPDateUtil.getBatProcDate().substring(YEAR_POS_START, MONTH_POS_END);
    }

    /**
     * This method will retern Year Month string (YYYYMM) that is not
     * delete start YearMonth.
     * @param glblCmpyCd String
     * @param retenMonthNum int
     * @return String
     */
    static String getNotDeleteStartMonth(String glblCmpyCd, int retenMonthNum) {
        return getNotDeleteStartCommon(glblCmpyCd, SRC_RETEN_NUM_TP_CD.MONTH, retenMonthNum);
    }

    /**
     * This method will retern Date string (YYYYMMDD) that is not
     * delete start Date.<br>
     * Day is the first day of the month(="01").
     * @param glblCmpyCd String
     * @param retenMonthNum int
     * @return String
     */
    public static String getNotDeleteStartFirstDay(String glblCmpyCd, int retenMonthNum) {
        return getNotDeleteStartCommon(glblCmpyCd, SRC_RETEN_NUM_TP_CD.MONTH, retenMonthNum) + FIRST_DAY;
    }

    /**
     * @param glblCmpyCd String
     * @param tpCd SRC_RETEN_NUM_TP_CD
     * @param retenNum int
     * @return String
     */
    public static String getNotDeleteStartCommon(String glblCmpyCd, SRC_RETEN_NUM_TP_CD tpCd, int retenNum) {

        if (SRC_RETEN_NUM_TP_CD.YEAR == tpCd) {
            String ym = getArBatchYearMonth(glblCmpyCd);

            int yInt = Integer.valueOf(ym.substring(YEAR_POS_START, YEAR_POS_END));

            return String.valueOf(yInt - retenNum);

        } else if (SRC_RETEN_NUM_TP_CD.MONTH == tpCd) {

            String ym = getArBatchYearMonth(glblCmpyCd);

            int yInt = Integer.valueOf(ym.substring(YEAR_POS_START, YEAR_POS_END));
            int mInt = Integer.valueOf(ym.substring(MONTH_POS_START, MONTH_POS_END));

            int yOverCnt = 0;

            for (int i = 0; i < retenNum; i++) {
                if (1 == mInt) {
                    mInt = LAST_MONTH;
                    yOverCnt++;
                } else {
                    mInt--;
                }
            }

            yInt = yInt - yOverCnt;

            StringBuffer sb = new StringBuffer();
            sb.append(yInt);
            if (mInt < LAST_TWO_DIGIT_MONTH) {
                sb.append("0");
            }
            sb.append(mInt);

            return sb.toString();
        } else if (SRC_RETEN_NUM_TP_CD.DAY == tpCd) {
            String day = getArBatchDay(glblCmpyCd);
            return S21CalendarUtil.addDay(day, (-1) * retenNum);
        } else {
            throw new IllegalArgumentException("Error! SRC_RETEN_NUM_TP_CD is Illegal input");
        }

    }

    /**
     * @param tpCd String
     * @return SRC_RETEN_NUM_TP_CD
     */
    public static SRC_RETEN_NUM_TP_CD getSrcRetenNumTpCd(String tpCd) {
        if (null == tpCd || 1 != tpCd.length()) {
            return null;
        } else {
            if (SRC_RETEN_NUM_TP_CD.YEAR.getValue().equals(tpCd)) {
                return SRC_RETEN_NUM_TP_CD.YEAR;
            } else if (SRC_RETEN_NUM_TP_CD.MONTH.getValue().equals(tpCd)) {
                return SRC_RETEN_NUM_TP_CD.MONTH;
            } else if (SRC_RETEN_NUM_TP_CD.DAY.getValue().equals(tpCd)) {
                return SRC_RETEN_NUM_TP_CD.DAY;
            } else {
                return null;
            }
        }
    }

    private static String getArBatchYearMonth(String glblCmpyCd) {
        AR_ACCT_DTTMsg cond = new AR_ACCT_DTTMsg();
        cond.glblCmpyCd.setValue(glblCmpyCd);
        cond.subSysCd.setValue("NFC");
        cond.onlBatTpCd.setValue("2"); // Batch

        AR_ACCT_DTTMsg result = (AR_ACCT_DTTMsg) S21CacheTBLAccessor.findByKey(cond);
        return result.acctYrMth.getValue();
    }

    private static String getArBatchDay(String glblCmpyCd) {
        AR_ACCT_DTTMsg cond = new AR_ACCT_DTTMsg();
        cond.glblCmpyCd.setValue(glblCmpyCd);
        cond.subSysCd.setValue("NFC");
        cond.onlBatTpCd.setValue("2"); // Batch

        AR_ACCT_DTTMsg result = (AR_ACCT_DTTMsg) S21CacheTBLAccessor.findByKey(cond);
        return result.acctDt.getValue();

    }
}
