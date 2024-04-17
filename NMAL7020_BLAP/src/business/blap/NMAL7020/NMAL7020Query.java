/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7020;

import static business.blap.NMAL7020.constant.NMAL7020Constant.GLOBAL_CMPY_CODE;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRC_CATG_NM;
import static business.blap.NMAL7020.constant.NMAL7020Constant.PRICE_LIST_TABLE_NM;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COPY_RSLT_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7020Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   Fujitsu         M.Suzuki        Create          N/A
 * 2018/08/24   Fujitsu         Mz.Takahashi    Update          QC#27932
 *</pre>
 */
public final class NMAL7020Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7020Query MY_INSTANCE = new NMAL7020Query();

    /**
     * Private constructor
     */
    private NMAL7020Query() {
        super();
    }

    /**
     * Get the NMAL7020Query instance.
     * @return NMAL7020Query instance
     */
    public static NMAL7020Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getPriceListType
     * @param bizMsg NMAL7020CMsg
     * @return String
     */
    public String getPriceListType(NMAL7020CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(PRC_CATG_NM, bizMsg.prcCatgNm_O.getValue());

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getPriceListType", ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return "";
        }
        return (String) ssmResult.getResultObject();
    }

    /**
     * checkExistPrcCatgNm
     * @param prcCatgNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkExistPrcCatgNm(String prcCatgNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(PRC_CATG_NM, prcCatgNm);
        return getSsmEZDClient().queryObject("checkExistPrcCatgNm", ssmParam);
    }

    /**
     * checkExistRqst 
     * @param prcCatgNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkExistRqst(String prcCatgNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(PRC_CATG_NM, prcCatgNm);
        // QC#27932 2018/08/24 Add Start
        ssmParam.put("copyRsltTpCd", COPY_RSLT_TP.ERROR);
        // QC#27932 2018/08/24 Add End
        return getSsmEZDClient().queryObject("checkExistRqst", ssmParam);
    }

    /**
     * getPriceListPeriod
     * @param prcCatgNm String
     * @param tableNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceListPeriod(String prcCatgNm, String tableNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(PRC_CATG_NM, prcCatgNm);
        ssmParam.put(PRICE_LIST_TABLE_NM, tableNm);

        return getSsmEZDClient().queryObject("getPriceListPeriod", ssmParam);
    }
}
