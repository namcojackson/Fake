/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/06/06   Fujitsu         S.Takami        Update          S21_NA#18459
 * 2017/11/16   Fujitsu         T.Aoi           Update          S21_NA#22620
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItemArray;

import com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * NWXC150001DsCheck
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/08/21   Fujitsu         S.Takami        Create          S21_NA#26767
 * </pre>
 */
public final class NWXC150001CommonReturnReason {

    /**
     * <pre>
     * Get Return Reason Pulldown data related to dsOrdTpCd
     * @param glblCmpyCd Global Company Code
     * @param dsOrdTpCd DS Order Type Code
     * @param cdArray pulldown code array
     * @param descArray pulldown description array
     * </pre>
     */
    public static void getRelatedReturnReasonPulldown(String glblCmpyCd, String dsOrdTpCd, EZDCStringItemArray cdArray, EZDCStringItemArray descArray) {

        List<Map<String, String>> returnReasonMapList = getRelatedReturnReasonList(glblCmpyCd, dsOrdTpCd);
        if (returnReasonMapList == null || returnReasonMapList.isEmpty()) {
            return;
        }
        cdArray.clear();
        descArray.clear();
        int maxIdx = returnReasonMapList.size();
        if (maxIdx > cdArray.length()) {
            maxIdx = cdArray.length();
        }
        for (int i = 0; i < maxIdx; i++) {
            Map<String, String> rsltMap = returnReasonMapList.get(i);
            ZYPEZDItemValueSetter.setValue(cdArray.no(i), (String) rsltMap.get("RTRN_RSN_CD"));
            ZYPEZDItemValueSetter.setValue(descArray.no(i), (String) rsltMap.get("RTRN_RSN_DESC_TXT"));
        }
    }
    /**
     * <pre>
     * Get Return Reason Pulldown data related to dsOrdTpCd
     * @param glblCmpyCd Global Company Code
     * @param dsOrdTpCd DS Order Type Code
     * @return Map<String, String> RTRN_RSN_CD, RTRN_RSN_DESC_TXT
     * </pre>
     */
    public static List<Map<String, String>> getRelatedReturnReasonList(String glblCmpyCd, String dsOrdTpCd) {

        return NWXC150001QueryForReturnReason.getInstance().getRelatedReturnReasonList(glblCmpyCd, dsOrdTpCd);
    }

    /**
     * <pre>
     * Get Default Return Reason Code from IB and IB_TRX_ORD_MAP.
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master PK
     * @return Default Return Reason Code (null available)
     * </pre>
     */
    public static String getDefaultRtrnRsnByIB(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd) //
                || svcMachMstrPk == null) {
            return null;
        }
        Map<String, Object> rsltMap = NWXC150001QueryForReturnReason.getInstance().getDefaultRtrnRsnLineCatgByIb(glblCmpyCd, svcMachMstrPk);
        if (rsltMap == null) {
            return null;
        }
        String ownrAcctNum = (String) rsltMap.get("OWNR_ACCT_NUM");
        String curLocAcctNum = (String) rsltMap.get("CUR_LOC_ACCT_NUM");
        String defRtrnRsnCd = (String) rsltMap.get("SCD_BIZ_CTX_ATTRB_TXT");

        if (ZYPCommonFunc.hasValue(defRtrnRsnCd)) {
            return defRtrnRsnCd;
        }
        if (S21StringUtil.isEquals(ownrAcctNum, curLocAcctNum)) {
            return RTRN_RSN.CUSTOMER_TRADE_IN;
        }
        return null;
    }

    /**
     * <pre>
     * Get Default Line Category Code from IB and IB_TRX_ORD_MAP.
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master PK
     * @return Default Line Category Code (null available)
     * </pre>
     */
    public static String getDefaultLineCatgIB(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd) //
                || svcMachMstrPk == null) {
            return null;
        }
        Map<String, Object> rsltMap = NWXC150001QueryForReturnReason.getInstance().getDefaultRtrnRsnLineCatgByIb(glblCmpyCd, svcMachMstrPk);
        if (rsltMap == null) {
            return null;
        }
        String defLineCatgCd = (String) rsltMap.get("THIRD_BIZ_CTX_ATTRB_TXT");

        if (ZYPCommonFunc.hasValue(defLineCatgCd)) {
            return defLineCatgCd;
        }
        return null;
    }
    /**
     * <pre>
     * Check Parameter return reason code allows CSA return.
     * Use this method, if IB is lease IB.
     * @param glblCmpyCd Global Company Code.
     * @param rtrnRsnCd Return Reason Code.
     * @return true: CSA Return Available false: CSA Return disable.
     * </pre>
     */
    public static boolean avalLeaseCsaReturnRtrnRsn(String glblCmpyCd, String rtrnRsnCd) {

        List<String> avalCsaReturnRsnList = NWXC150001QueryForReturnReason.getInstance().getVarCharConstValue(NWXC150001Constant.CONST_NM_NWA_LEASE_AVAL_CSA_RTRN_RSN, glblCmpyCd);
        if (avalCsaReturnRsnList == null || avalCsaReturnRsnList.isEmpty()) {
            return false;
        }

        return avalCsaReturnRsnList.contains(rtrnRsnCd);
    }

    /**
     * <pre>
     * get related config type.
     * @param glblCmpyCd Global Company Code (mandatory)
     * @param cpoSrcTpCd CPO Source Type Code (mandatory)
     * @param dsOrdTpCd DS Order Type Code (mandatory)
     * @param configCatgCd Config Category Code (not mandatory)
     * @return List Of Map. Config Type Map (Key: CONFIG_TP_CD, CONFIG_TP_DESC_TXT, CONFIG_CATG_CD)
     * </pre>
     */
    public static List<Map<String, String>> getRelatedConfigTpList(String glblCmpyCd, String cpoSrcTpCd, String dsOrdTpCd, String configCatgCd) {

        return NWXC150001QueryForReturnReason.getInstance().getRelatedConfigTpList(glblCmpyCd, cpoSrcTpCd, dsOrdTpCd, configCatgCd);
    }

    /**
     * <pre>
     * Get Service Machine Master PK and Config ID by Item Code and Serial Number
     * @param glblCmpyCd Global Company Code
     * @param slsDt Sales Date
     * @param serNum Serial Number
     * @param mdseCd Item Code
     * @return Map: Keis are....
     *      SVC_MACH_MSTR_PK
     *      SVC_CONFIG_MSTR_PK
     *      MDL_ID
     *      MDL_DESC_TXT
     *      T_MDL_NM
     * </pre>
     */
    public static Map<String, Object> getSvcMachMstrPkAndConfigIdBySerNumAndMdseCd(String glblCmpyCd, String slsDt, String serNum, String mdseCd) {

        return NWXC150001Query.getInstance().getSvcMachMstrPkAndConfigIdBySerNumAndMdseCd(glblCmpyCd, slsDt, serNum, mdseCd);
    }

    /**
     * <pre>
     * Get List Of SVC_MACH_MSTR_PK by Item COde And Config ID
     * @param glblCmpyCd Global Company Code
     * @param slsDt Sales Date
     * @param svcConfigMstrPk Config ID
     * @param mdseCd Item Code
     * @return List Of SVC_MACH_MSTR_PK
     * </pre>
     */
    public static List<BigDecimal> getSvcMachMstrPkByConfigIdAndMdseCd(String glblCmpyCd, String slsDt, BigDecimal svcConfigMstrPk, String mdseCd) {

        return NWXC150001Query.getInstance().getSvcMachMstrPkByConfigIdAndMdseCd(glblCmpyCd, slsDt,  svcConfigMstrPk,  mdseCd);
    }
}
