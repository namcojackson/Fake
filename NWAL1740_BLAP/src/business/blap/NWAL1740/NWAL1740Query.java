/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1740;

import static business.blap.NWAL1740.constant.NWAL1740Constant.GLOBAL_CMPY_CODE;
import static business.blap.NWAL1740.constant.NWAL1740Constant.ROW_NUM;
import static business.blap.NWAL1740.constant.NWAL1740Constant.ORD_PROC_NODE_PRFL_CD;
import static business.blap.NWAL1740.constant.NWAL1740Constant.RTL_WH_NM;
import static business.blap.NWAL1740.constant.NWAL1740Constant.RTL_SWH_NM;
import static business.blap.NWAL1740.constant.NWAL1740Constant.ROW_EXIST;
import java.util.HashMap;
import java.util.Map;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1740Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public final class NWAL1740Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1740Query MY_INSTANCE = new NWAL1740Query();

    /**
     * Private constructor
     */
    private NWAL1740Query() {
        super();
    }

    /**
     * Get the NWAL1740Query instance.
     * @return NWAL1740Query instance
     */
    public static NWAL1740Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProfitabilityPulldownList() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        return getSsmEZDClient().queryObjectList("getProfitabilityPulldownList", ssmParam);
    }

    /**
     * @param bizMsg NWAL1740CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseTpRules(NWAL1740CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(ROW_NUM, bizMsg.A.length());
        ssmParam.put(ORD_PROC_NODE_PRFL_CD, bizMsg.ordProcNodePrflCd);

        return getSsmEZDClient().queryObjectList("getMdseTpRules", ssmParam);
    }

    /**
     * @param bizMsg NWAL1740CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineCatgRules(NWAL1740CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(ROW_NUM, bizMsg.B.length());
        ssmParam.put(ORD_PROC_NODE_PRFL_CD, bizMsg.ordProcNodePrflCd);
        return getSsmEZDClient().queryObjectList("getLineCatgRules", ssmParam);
    }

    /**
     * @param bizMsg NWAL1740CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHRules(NWAL1740CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(ROW_NUM, bizMsg.C.length());
        ssmParam.put(ORD_PROC_NODE_PRFL_CD, bizMsg.ordProcNodePrflCd);
        return getSsmEZDClient().queryObjectList("getWHRules", ssmParam);
    }

    /**
     * getValuation
     * @param bizMsg NWAL1740CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getValuation(NWAL1740CMsg bizMsg) {

        String whNm = bizMsg.P.no(0).xxComnScrColValTxt.getValue();
        String swhNm = bizMsg.P.no(1).xxComnScrColValTxt.getValue();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(RTL_WH_NM, whNm);
        ssmParam.put(RTL_SWH_NM, swhNm);
        ssmParam.put(ROW_NUM, ROW_EXIST);
        return getSsmEZDClient().queryObject("getValuation", ssmParam);
    }

}
