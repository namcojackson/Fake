/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC245001;

import static com.canon.cusa.s21.common.NWX.NWXC245001.constant.NWXC245001Constant.DB_PRM_CPO_ORD_NUM;
import static com.canon.cusa.s21.common.NWX.NWXC245001.constant.NWXC245001Constant.DB_PRM_DS_XREF_ACCT_CD;
import static com.canon.cusa.s21.common.NWX.NWXC245001.constant.NWXC245001Constant.DB_PRM_DS_XREF_ACCT_TP_CD;
import static com.canon.cusa.s21.common.NWX.NWXC245001.constant.NWXC245001Constant.DB_PRM_FIRST_BIZ_CTX_ATTRB_TXT;
import static com.canon.cusa.s21.common.NWX.NWXC245001.constant.NWXC245001Constant.DB_PRM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.common.NWX.NWXC245001.constant.NWXC245001Constant.DB_PRM_GLBL_CMPY_CD_CUSA;
import static com.canon.cusa.s21.common.NWX.NWXC245001.constant.NWXC245001Constant.DB_PRM_INSTL_CD;
import static com.canon.cusa.s21.common.NWX.NWXC245001.constant.NWXC245001Constant.DB_PRM_ISTL_SUB_LOC_CD;
import static com.canon.cusa.s21.common.NWX.NWXC245001.constant.NWXC245001Constant.DB_PRM_ORD_CATG_CTX_TP_CD;
import static com.canon.cusa.s21.common.NWX.NWXC245001.constant.NWXC245001Constant.DB_PRM_RTL_DIV_CD;
import static com.canon.cusa.s21.common.NWX.NWXC245001.constant.NWXC245001Constant.DB_PRM_RTL_DLR_CD;
import static com.canon.cusa.s21.common.NWX.NWXC245001.constant.NWXC245001Constant.DB_PRM_SALES_DATE;
import static com.canon.cusa.s21.common.NWX.NWXC245001.constant.NWXC245001Constant.DB_VAL_GLBL_CMPY_CD;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_XREF_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
  * Import Attribute
  *
  * Date         Company         Name            Create/Update   Defect No
  * ----------------------------------------------------------------------
  * 05/16/2016   CITS            S.Tanikawa      Create          N/A
  * </pre>
 */
public class NWXC245001Query {

    /**
     * Singleton instance.
     */
    private static final NWXC245001Query MY_INSTANCE = new NWXC245001Query();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /**
     * @return MY_INSTANCE
     */
    protected static NWXC245001Query getInstance() {
        return MY_INSTANCE;
    }

    protected Map<String, Object> getDsRtlDlrInfo(String glblCmpyCd, String rtlDlrCd, String rtlDivCd, String salesDate) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PRM_RTL_DLR_CD, rtlDlrCd);
        ssmParam.put(DB_PRM_RTL_DIV_CD, rtlDivCd);
        ssmParam.put(DB_PRM_SALES_DATE, salesDate);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getDsRtlDlrInfo", ssmParam);
        return map;
    }

    protected Map<String, Object> getOrdCatgBizCtx(String glblCmpyCd, String rtlDivCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PRM_ORD_CATG_CTX_TP_CD, ORD_CATG_CTX_TP.RETAIL_ORDER_TYPE_BY_DIVISION);
        ssmParam.put(DB_PRM_FIRST_BIZ_CTX_ATTRB_TXT, rtlDivCd);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getOrdCatgBizCtx", ssmParam);
        return map;
    }

    protected Map<String, Object> getDsXrefAcct(String glblCmpyCd, String dsXrefAcctCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PRM_DS_XREF_ACCT_TP_CD, DS_XREF_ACCT_TP.CUSA);
        ssmParam.put(DB_PRM_DS_XREF_ACCT_CD, dsXrefAcctCd);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getDsXrefAcct", ssmParam);
        return map;
    }

    protected Map<String, Object> getDsRtlIntgItemMap(String glblCmpyCd, String rtlDivCd, String salesDate) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PRM_RTL_DIV_CD, rtlDivCd);
        ssmParam.put(DB_PRM_SALES_DATE, salesDate);

        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getDsRtlIntgItemMap", ssmParam);
        return map;
    }

    protected BigDecimal getSvcConfigMstrPk(String glblCmpyCd, String cpoOrdNum, String instlCd, String instlSubLocCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PRM_GLBL_CMPY_CD_CUSA, DB_VAL_GLBL_CMPY_CD);
        ssmParam.put(DB_PRM_CPO_ORD_NUM, cpoOrdNum);
        ssmParam.put(DB_PRM_INSTL_CD, instlCd);
        ssmParam.put(DB_PRM_ISTL_SUB_LOC_CD, instlSubLocCd);

        Map<String, BigDecimal> map = (Map<String, BigDecimal>) ssmBatchClient.queryObject("getSvcConfigMstrPk", ssmParam);
        if (map == null) {
            return null;
        }
        return map.get("SVC_CONFIG_MSTR_PK");
    }

    protected String getDelyAddlCmntTxt(String glblCmpyCd, String instlCd, String instlSubLocCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD_CUSA, DB_VAL_GLBL_CMPY_CD);
        ssmParam.put(DB_PRM_INSTL_CD, instlCd);
        ssmParam.put(DB_PRM_ISTL_SUB_LOC_CD, instlSubLocCd);

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getDelyAddlCmntTxt", ssmParam);
        if (map == null) {
            return "";
        }
        return map.get("DELY_ADDL_CMNT_TXT");
    }
}
