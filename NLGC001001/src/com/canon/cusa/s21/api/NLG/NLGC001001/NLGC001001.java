/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLG.NLGC001001;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.WMS_TASKTMsg;
import business.db.WMS_TRXTMsg;
import business.db.WMS_WHTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Common Program of ALG (MW Replace)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2013   CSAI            K.Kondo         Create          MW Replace Initial
 * 06/05/2018   CITS            Y.Iwasaki       Update          QC#24273
 *</pre>
 */
public class NLGC001001 {

    /** M/W date time format length */
    private static final int LG_MW_DT_TM_TS = 14;

    /** M/W DT_TM_TS Format */
    private static final String MW_DT_TM_TS_FMT = "yyyyMMddHHmmss";

    /** S21 Fixed length Data DT_TM_TS Format */
    private static final String S21_DT_TM_TS_FMT = "yyyy/MM/ddHH:mm:ss";

    /** M/W date time format length */
    private static final int LG_MW_DT = 8;

    /** M/W DT Format */
    private static final String MW_DT_FMT = "yyyyMMdd";

    /** S21 Fixed length Data DT Format */
    private static final String S21_DT_FMT = "yyyy/MM/dd";

    /** WMS_INBD_OTBD_CD : Inbound */
    public static final String WMS_INBD_OTBD_CD_INBD = "1";

    /** WMS_INBD_OTBD_CD : Outbound */
    public static final String WMS_INBD_OTBD_CD_OTBD = "2";

    /** WMS_INBD_OTBD_CD : Inbound and Outbound */
    public static final String WMS_INBD_OTBD_CD_BOTH = "3";

    /**
     * Get Target Warehouse Code List
     * @param glblCmpyCd Grobal Company Code
     * @param whGpCd Warehouse Group Code
     * @return Warehouse Code List
     */
    public static String[] getTrgtWhCd(String glblCmpyCd, String whGpCd) {
        return getTrgtWhCd(glblCmpyCd, whGpCd, null);
    }

    /**
     * getTrgtWhCd
     * @param glblCmpyCd
     * @param whGpCd
     * @param prefixWhCd
     * @return
     */
    public static String[] getTrgtWhCd(String glblCmpyCd, String whGpCd, String prefixWhCd) {

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NLGC001001.class);
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("mwReplTrgtGrpCd", whGpCd);
        queryParam.put("prefixWhCd", prefixWhCd);
        List trgtWhList = ssmBatchClient.queryObjectList("getTrgtWhCd", queryParam);

        String[] trgtWhCdAryTmp = null;

        if (trgtWhList != null && trgtWhList.size() > 0) {
            trgtWhCdAryTmp = new String[trgtWhList.size()];
            for (int i = 0; i < trgtWhList.size(); i++) {
                WMS_WHTMsg wmsWhTMsg = (WMS_WHTMsg) trgtWhList.get(i);
                trgtWhCdAryTmp[i] = wmsWhTMsg.whCd.getValue();
            }
        }
        return trgtWhCdAryTmp;
    }

    /**
     * Convert fixed length left align
     * @param str String
     * @param lg FixedLength
     * @return converted data String
     */
    public static String convFixedLgLeftAlign(String str, int lg) {

        if (!ZYPCommonFunc.hasValue(str)) {
            str = "";
        }
        if (str.length() >= lg) {
            return str.substring(0, lg);
        } else {
            return String.format("%-" + Integer.toString(lg) + "s", str);
        }
    }

    /**
     * Convert fixed length right align
     * @param str String
     * @param lg FixedLength
     * @return converted data String
     */
    public static String convFixedLgRightAlign(String str, int lg) {

        if (!ZYPCommonFunc.hasValue(str)) {
            str = "";
        }
        if (str.length() >= lg) {
            return str.substring(0, lg);
        } else {
            return String.format("%" + Integer.toString(lg) + "s", str);
        }
    }

    /**
     * Converted to Date Time of the S21 interface format for the
     * middleware format. (yyyyMMddHHmmss to yyyy/MM/ddHH:mm:ss)
     * @param dtTmTs Middleware Format Date Time
     * @return converted data String
     */
    public static String confIntfcDtTmTsFmt(String dtTmTs) {

        if (!ZYPCommonFunc.hasValue(dtTmTs) || dtTmTs.length() != LG_MW_DT_TM_TS) {
            return "";
        }
        return ZYPDateUtil.DateFormatter(dtTmTs, MW_DT_TM_TS_FMT, S21_DT_TM_TS_FMT);
    }

    /**
     * Converted to Date of the S21 interface format for the
     * middleware format. (yyyyMMdd to yyyy/MM/dd)
     * @param date Middleware Format Date Time
     * @return converted data String
     */
    public static String confIntfcDtFmt(String date) {

        if (!ZYPCommonFunc.hasValue(date) || date.length() != LG_MW_DT) {
            return "";
        }
        return ZYPDateUtil.DateFormatter(date, MW_DT_FMT, S21_DT_FMT);
    }

    /**
     * BigDecimal is changed into a character string.
     * @param decimal BigDecimal data
     * @return String String data
     */
    public static String bigDecimalToString(BigDecimal decimal) {

        if (!ZYPCommonFunc.hasValue(decimal)) {
            return "";
        }
        return decimal.toString();
    }

    /**
     * String is changed into a BigDecimal.
     * @param str in String
     * @return BigDecimal BigDecimal data
     */
    public static BigDecimal stringToBigDecimal(String str) {

        if (!ZYPCommonFunc.hasValue(str)) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(str);
    }

    /**
     * null is changed into a blank.
     * @param str in Data
     * @return String out Data
     */
    public static String nullToBlank(String str) {

        if (!ZYPCommonFunc.hasValue(str)) {
            return "";
        }
        return str;
    }

    /**
     * Decimal format.
     * @param decimal target
     * @param outFormat output format
     * @return formatted string
     */
    public static String decimalFormat(BigDecimal decimal, String outFormat) {

        if (!ZYPCommonFunc.hasValue(decimal)) {
            return outFormat;
        }
        DecimalFormat df = new DecimalFormat(outFormat);
        return df.format(decimal);
    }

    /**
     * Check where dateStr is date format or not. If dateStr is null,
     * return true.
     * @param dateStr date string
     * @param fmt format
     * @return true / dateStr is date format, false / dateStr is not
     * date format
     */
    public static boolean isDateFormat(String dateStr, String fmt) {

        if (!ZYPCommonFunc.hasValue(dateStr)) {
            return true;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        sdf.setLenient(false);
        try {
            Date convDt = sdf.parse(dateStr);
            if (!dateStr.equals(sdf.format(convDt))) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Get WMS_TASK String array.<BR>
     * Array[0] : Code value. Array[1] : Name value.
     * @param glblCmpyCd Global Company Code
     * @param wmsInbdOtbdCd Wms Inbound Outbound Code
     * @return List of code value and name value
     */
    public static String[][] getWmsTaskList(String glblCmpyCd, String wmsInbdOtbdCd) {

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NLGC001001.class);
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
//        if (WMS_INBD_OTBD_CD_BOTH.equals(wmsInbdOtbdCd)) {
//            queryParam.put("wmsInbdOtbdCd", new String[] {WMS_INBD_OTBD_CD_INBD, WMS_INBD_OTBD_CD_OTBD, WMS_INBD_OTBD_CD_BOTH });
//        } else {
//            queryParam.put("wmsInbdOtbdCd", new String[] {wmsInbdOtbdCd, WMS_INBD_OTBD_CD_BOTH });
//        }
        List wmsTaskList = ssmBatchClient.queryObjectList("getWmsTaskList", queryParam);

        String[][] wmsTaskArr = null;
        if (wmsTaskList != null && !wmsTaskList.isEmpty()) {
            wmsTaskArr = new String[wmsTaskList.size()][2];
            for (int i = 0; i < wmsTaskList.size(); i++) {
                WMS_TASKTMsg wmsTaskT = (WMS_TASKTMsg) wmsTaskList.get(i);
                wmsTaskArr[i][0] = wmsTaskT.wmsTaskCd.getValue();
                wmsTaskArr[i][1] = wmsTaskT.wmsTaskNm.getValue();
            }
        }
        return wmsTaskArr;
    }

    /**
     * Get WMS_TRX String array.<BR>
     * Array[0] : Code value. Array[1] : Name value.
     * @param glblCmpyCd Global Company Code
     * @param wmsInbdOtbdCd Wms Inbound Outbound Code
     * @return List of code value and name value
     */
    public static String[][] getWmsTrxList(String glblCmpyCd, String wmsInbdOtbdCd) {

        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NLGC001001.class);
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("wmsInbdOtbdCd", new String[] {wmsInbdOtbdCd, WMS_INBD_OTBD_CD_BOTH });
        List wmsTrxList = ssmBatchClient.queryObjectList("getWmsTrxList", queryParam);

        String[][] wmsTrxArr = null;
        if (wmsTrxList != null && !wmsTrxList.isEmpty()) {
            wmsTrxArr = new String[wmsTrxList.size()][2];
            for (int i = 0; i < wmsTrxList.size(); i++) {
                WMS_TRXTMsg wmsTrxT = (WMS_TRXTMsg) wmsTrxList.get(i);
                wmsTrxArr[i][0] = wmsTrxT.wmsTrxCd.getValue();
                wmsTrxArr[i][1] = wmsTrxT.wmsTrxNm.getValue();
            }
        }
        return wmsTrxArr;
    }
}
