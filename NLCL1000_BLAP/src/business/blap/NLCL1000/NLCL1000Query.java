/**
 *<pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.blap.NLCL1000;

import java.util.Map;

import business.blap.NLCL1000.constant.NLCL1000Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Stock Over View Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/07/17   Fujitsu         Y.Mori          Create          N/A
 * 2016/05/20   CSAI            K.Lee           Update          QC#7441
 *</pre>
 */
public final class NLCL1000Query extends S21SsmEZDQuerySupport implements NLCL1000Constant {

    /**
     * Singleton instance.
     */
    private static final NLCL1000Query MY_INSTANCE = new NLCL1000Query();

    /**
     * Constructor.
     */
    private NLCL1000Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NABL0020Query
     */
    public static NLCL1000Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * execute SSM id="getDsWhSrcTpCd" in [NLCL1000Query.xml]
     * <pre>
     * 
     * @param bizMsg Business Msg
     * @return S21SsmEZDResult
     */
//    public S21SsmEZDResult getDsWhSrcTpCd(NLCL1000CMsg bizMsg) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put(BIND_INVTY_LOC_CD, bizMsg.locCd.getValue());
//        ssmParam.put(BIND_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
//        ssmParam.put(BIND_DS_WH_SRC_TP, DS_WH_SRC_TP.TECHNICIAN);
//
//        return getSsmEZDClient().queryObject("getDsWhSrcTpCd", ssmParam);
//    }

    /**
     * execute SSM id="getSupdMdseCd" in [NLCL1000Query.xml]
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupdMdseCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSupdMdseCd", ssmParam);
    }

    /**
     * execute SSM id="getInfoByInvtyLocCd" in [NLCL1000Query.xml]
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInfoByInvtyLocCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getInfoByInvtyLocCd", ssmParam);
    }

    /**
     * execute SSM id="getCusaInvtyLocName" in [NLCL1000Query.xml]
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCusaInvtyLocName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getCusaInvtyLocName", ssmParam);
    }

    /**
     * execute SSM id="checkCsaInvty" in [NLCL1000Query.xml]
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkCsaInvty(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkCsaInvty", ssmParam);
    }

    /**
     * execute SSM id="checkTecLoc" in [NLCL1000Query.xml]
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkTechLoc(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkTechLoc", ssmParam);
    }

    // 12/07/2015 add start
    /**
     * execute SSM id="checkOrdTakeMdseInfo" in [NLCL1000Query.xml]
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkOrdTakeMdseInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkOrdTakeMdseInfo", ssmParam);
    }

    /**
     * execute SSM id="getMdseCdListFromEightDigitMdseCd" in [NLCL1000Query.xml]
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseCdListFromEightDigitMdseCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getMdseCdListFromEightDigitMdseCd", ssmParam);
    }
    // 12/07/2015 add end

}
