/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC245001;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <pre>
 * Import Attribute
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/16/2016   CITS            S.Tanikawa      Create          N/A
 * </pre>
 */

public final class NWXC245001 {

    /**
     * getDsRtlDlrInfo
     * @param glblCmpyCd String
     * @param rtlDlrCd String
     * @param rtlDivCd String
     * @param salesDate String
     * @return Map<String,Object>
     */
    public static Map<String, Object> getDsRtlDlrInfo(String glblCmpyCd, String rtlDlrCd, String rtlDivCd, String salesDate) {
        return NWXC245001Query.getInstance().getDsRtlDlrInfo(glblCmpyCd, rtlDlrCd, rtlDivCd, salesDate);
    }

    /**
     * getOrdCatgBizCtx
     * @param glblCmpyCd String
     * @param rtlDivCd String
     * @return Map<String, Object>
     */
    public static Map<String, Object> getOrdCatgBizCtx(String glblCmpyCd, String rtlDivCd) {
        return NWXC245001Query.getInstance().getOrdCatgBizCtx(glblCmpyCd, rtlDivCd);
    }

    /**
     * getDsXrefAcct
     * @param glblCmpyCd String
     * @param instlCd String
     * @param instlSubLocCd String
     * @return Map<String, Object>
     */
    public static Map<String, Object> getDsXrefAcct(String glblCmpyCd, String instlCd, String instlSubLocCd) {
        String dsXrefAcctCd = instlCd + instlSubLocCd;
        return NWXC245001Query.getInstance().getDsXrefAcct(glblCmpyCd, dsXrefAcctCd);
    }

    /**
     * getDsRtlIntgItemMap
     * @param glblCmpyCd String
     * @param rtlDivCd String
     * @param salesDate String
     * @return Map<String, Object>
     */
    public static Map<String, Object> getDsRtlIntgItemMap(String glblCmpyCd, String rtlDivCd, String salesDate) {
        return NWXC245001Query.getInstance().getDsRtlIntgItemMap(glblCmpyCd, rtlDivCd, salesDate);
    }

    /**
     * getSvcConfigMstrPk
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param instlCd String
     * @param instlSubLocCd String
     * @return String SVC_CONFIG_MSTR_PK
     */
    public static BigDecimal getSvcConfigMstrPk(String glblCmpyCd, String cpoOrdNum, String instlCd, String instlSubLocCd) {
        return NWXC245001Query.getInstance().getSvcConfigMstrPk(glblCmpyCd, cpoOrdNum, instlCd, instlSubLocCd);
    }

    /**
     * getDelyAddlCmntTxt
     * @param glblCmpyCd String
     * @param instlCd String
     * @param instlSubLocCd String
     * @return String DELY_ADDL_CMNT_TXT
     */
    public static String getDelyAddlCmntTxt(String glblCmpyCd, String instlCd, String instlSubLocCd) {
        return NWXC245001Query.getInstance().getDelyAddlCmntTxt(glblCmpyCd, instlCd, instlSubLocCd);
    }
}
