/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFB.NFBC000104;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import business.parts.NFBC000104PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>    
 * Invoice Order Number Acquisition
 *  
 * Date             Company         Name          Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 06/17/2009       CSAI            N.Sasaki      Create          N/A 
 *</pre>
 */
public class NFBC000104 extends S21ApiCommonBase {

    /** SQL Access object */
    private S21SsmBatchClient ssmBatchClient = null;

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** inputDateFormat */
    private final String inputDateFormat = "yyyyMMdd";

    /** SQL Source Name */
    private final String getVndPmtTermByCode = "getVndPmtTermByCode";

    /** Message Map */
    private S21ApiMessageMap msgMap = null;

    /** NFBC000104PMsg */
    private NFBC000104PMsg localMsg = null;

    /** Output */
    /** Record is not found */
    private final String strNFBM0069E = "NFBM0069E";

    /** Invalid input */
    private final String strNFBM0093E = "NFBM0093E";

    /**
     * Initializaion
     */
    public NFBC000104() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        this.localMsg = new NFBC000104PMsg();
    }

    /**
     * Method name: Execute
     * <dd>The method explanation: Acquire new Invoice Order Sequence
     * Number
     * <dd>Remarks:
     * @param paramMsg NFBC000104PMsg
     */
    public void execute(final NFBC000104PMsg paramMsg) {

        Boolean bRet = true;
        msgMap = new S21ApiMessageMap(paramMsg);
        if (!isValidInput(paramMsg)) {
            msgMap.flush();
            return;
        } else {
            Map<String, String> map = new HashMap<String, String>();
            map.put("glblCmpyCd", glblCmpyCd);
            map.put("vndPmtTermCd", paramMsg.vndPmtTermCd.getValue());
            map.put("inputDt", paramMsg.pmtDueDt_I.getValue().substring(6, 8));

            bRet = (Boolean) this.ssmBatchClient.queryObject(getVndPmtTermByCode, map, new GetResult());

            if (bRet == Boolean.TRUE) {
                // input
                Calendar inputDate = strToCalendar(paramMsg.pmtDueDt_I.getValue());
                if (localMsg.payDtFixFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                    inputDate = getFixedDueDate(inputDate);
                } else {
                    inputDate.add(Calendar.DATE, Integer.parseInt(checkNull(localMsg.payDayAot.getValue()).toString()));
                }
                // output
                paramMsg.pmtDueDt_O.setValue(calendarToStr(inputDate));
            } else {
                msgMap.addXxMsgId(strNFBM0069E);
            }
            msgMap.flush();
        }
    }

    /**
     * Method name: getFixedDueDate
     * @param inputDate
     * @return
     */
    private Calendar getFixedDueDate(Calendar inputDate) {

        if (inputDate == null) {
            return null;
        } else {
            int cloFromDay = Integer.parseInt(checkNull(localMsg.cloFromDay.getValue()).toString());
            int cloThruDay = Integer.parseInt(checkNull(localMsg.cloThruDay.getValue()).toString());
            int payDay = Integer.parseInt(checkNull(localMsg.payDay.getValue()).toString());
            int payMthAot = Integer.parseInt(checkNull(localMsg.payMthAot.getValue()).toString());

            Calendar fromDate = (Calendar) inputDate.clone();
            Calendar toDate = (Calendar) inputDate.clone();

            // Set fromDate as a date of cloFromDay ( bet 1 and
            // month's last date)
            fromDate = setDateInTheSameMonth(fromDate, cloFromDay);
            // Set toDate as a date of cloThruDay
            toDate = setDateInTheSameMonth(toDate, cloThruDay);

            if (fromDate.after(toDate)) {
                if (fromDate.after(inputDate)) {
                    // Set fromDate to previous month
                    fromDate.add(Calendar.MONTH, -1);
                } else {
                    // Set toDate to next month
                    toDate.add(Calendar.MONTH, 1);
                }
            }
            // Set toDate as a date of payDay
            toDate = setDateInTheSameMonth(toDate, payDay);
            // Add toDate by a value of payMthAot
            toDate.add(Calendar.MONTH, payMthAot);

            return toDate;
        }
    }

    /**
     * Method name: setDateInTheSameMonth
     * <dd>The method explanation: set the date in Calendar object.
     * <dd>Remarks: If the date is less than 1, it will be 1. If the
     * date is greater than the month's last date, the date will be
     * set as the last date.
     * @param cal original date
     * @param inputDate date will be set
     * @return Calendar
     */
    private Calendar setDateInTheSameMonth(Calendar cal, int inputDate) {

        int last = cal.getActualMaximum(Calendar.DATE);
        if (inputDate > last) {
            // Input date is greater than the last date
            // of the month, date will be set as a last date
            cal.set(Calendar.DATE, last);
        } else if (inputDate < 1) {
            // Input date is less than 1
            cal.set(Calendar.DATE, 1);
        } else {
            cal.set(Calendar.DATE, inputDate);
        }
        return cal;
    }

    /**
     * Class name: GetResult
     * @author q02703
     */
    private class GetResult extends S21SsmBooleanResultSetHandlerSupport {

        /**
         * Method name: doProcessQueryResult
         * <dd>The method explanation: Get max INV_ORD_NUM from
         * CM_INV_ACTL_DTL.
         * <dd>Remarks:
         */
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            Boolean bRet = Boolean.FALSE;
            try {
                if (rs.next()) {
                    localMsg.vndPmtTermCd.setValue(checkNull(rs.getString("VND_PMT_TERM_CD")));
                    localMsg.cloFromDay.setValue(checkNull(rs.getBigDecimal("CLO_FROM_DAY")));
                    localMsg.cloThruDay.setValue(checkNull(rs.getBigDecimal("CLO_THRU_DAY")));
                    localMsg.vndPmtTermNm.setValue(checkNull(rs.getString("VND_PMT_TERM_NM")));
                    localMsg.payDtFixFlg.setValue(checkNull(rs.getString("PAY_DT_FIX_FLG")));

                    localMsg.payDay.setValue(checkNull(rs.getBigDecimal("PAY_DAY")));
                    localMsg.payMthAot.setValue(checkNull(rs.getBigDecimal("PAY_MTH_AOT")));
                    localMsg.payDayAot.setValue(checkNull(rs.getBigDecimal("PAY_DAY_AOT")));

                    bRet = Boolean.TRUE;
                }
            } catch (SQLException e) {
                throw new S21AbendException(e);
            }
            return bRet;
        }

    } // class GetResult

    private Calendar strToCalendar(String yyyyMMdd) {

        if (!isValidDateFormat(yyyyMMdd)) {
            return null;
        } else {
            Calendar cal = Calendar.getInstance();

            int year = Integer.parseInt(yyyyMMdd.substring(0, 4));
            int month = Integer.parseInt(yyyyMMdd.substring(4, 6));
            int date = Integer.parseInt(yyyyMMdd.substring(6, 8));

            cal.set(year, month - 1, date);
            return cal;
        }
    }

    private String calendarToStr(Calendar cal) {
        if (cal == null) {
            return "";
        } else {
            return new SimpleDateFormat(inputDateFormat).format(cal.getTime());
        }
    }

    private boolean isValidInput(NFBC000104PMsg paramMsg) {

        if (paramMsg.pmtDueDt_I.isClear() || paramMsg.vndPmtTermCd.isClear()) {
            msgMap.addXxMsgId(strNFBM0093E);
            return false;
        }
        if (!isValidDateFormat(paramMsg.pmtDueDt_I.getValue())) {
            msgMap.addXxMsgId(strNFBM0093E);
            return false;
        }
        return true;
    }

    private boolean isValidDateFormat(String date) {

        SimpleDateFormat df = (SimpleDateFormat) DateFormat.getDateInstance();
        df.applyPattern(inputDateFormat);

        df.setLenient(false);
        try {
            df.parse(date.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    } // isValidDateFormat

    private String checkNull(String vndCd) {
        if (!ZYPCommonFunc.hasValue(vndCd)) {
            return "";
        } else {
            return vndCd.trim();
        }
    }

    private BigDecimal checkNull(BigDecimal number) {

        BigDecimal newNum = new BigDecimal("0");
        if (!ZYPCommonFunc.hasValue(number)) {
            return newNum;
        } else {
            return number;
        }
    }
}
