/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1680;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1680Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         M.Ohno          Create          N/A
 * 2017/07/10   Fujitsu         Y.Kanefusa      Update          S21_NA#19826
 *</pre>
 */
public final class NWAL1680Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1680Query MY_INSTANCE = new NWAL1680Query();

    /**
     * Private constructor
     */
    private NWAL1680Query() {
        super();
    }

    /**
     * Get the NWAL1680Query instance.
     * @return NWAL1680Query instance
     */
    public static NWAL1680Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * getLineConfig
     * @param glblCmpyCd    glblCmpyCd
     * @param cpoOrdNum     cpoOrdNum
     * @return S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getLineConfig(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("configCatgCd", CONFIG_CATG.OUTBOUND);

        return getSsmEZDClient().queryObject("getLineOrRmaConfig", params);
    }

    /**
     * <pre>
     * getMachineLineConfig
     * @param glblCmpyCd    glblCmpyCd
     * @param cpoOrdNum     cpoOrdNum
     * @return S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getMachineLineConfig(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("mdseTpCd", COA_MDSE_TP.MACHINE);
        params.put("stsCd", ORD_LINE_STS.CANCELLED);

        return getSsmEZDClient().queryObject("getMachineLineConfig", params);
    }

    /**
     * <pre>
     * getRmaConfig
     * @param glblCmpyCd    glblCmpyCd
     * @param cpoOrdNum     cpoOrdNum
     * @return S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getRmaConfig(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("configCatgCd", CONFIG_CATG.INBOUND);

        return getSsmEZDClient().queryObject("getLineOrRmaConfig", params);
    }

    /**
     * <pre>
     * getMachineRmaConfig
     * @param glblCmpyCd    glblCmpyCd
     * @param cpoOrdNum     cpoOrdNum
     * @return S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getMachineRmaConfig(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("mdseTpCd", COA_MDSE_TP.MACHINE);
        params.put("stsCd", RTRN_LINE_STS.CANCELLED);
        return getSsmEZDClient().queryObject("getMachineRmaConfig", params);
    }

    /**
     * <pre>
     * getTotalShells
     * @param glblCmpyCd    glblCmpyCd
     * @param cpoOrdNum     cpoOrdNum
     * @return S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getTotalShells(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("warranty", DS_CONTR_CATG.WARRANTY);// QC#19826 2017/07/10 Add

        return getSsmEZDClient().queryObject("getTotalShells", params);
    }

    /**
     * <pre>
     * getTotalMachinesCoveredByShell
     * @param glblCmpyCd    glblCmpyCd
     * @param cpoOrdNum     cpoOrdNum
     * @return S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getTotalMachinesCoveredByShell(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("mdseTpCd", COA_MDSE_TP.MACHINE);
        params.put("warranty", DS_CONTR_CATG.WARRANTY);// QC#19826 2017/07/10 Add

        return getSsmEZDClient().queryObject("getTotalMachinesCoveredByShell", params);
    }

    /**
     * <pre>
     * getTotalMachinesCoveredByShell
     * @param glblCmpyCd    glblCmpyCd
     * @param cpoOrdNum     cpoOrdNum
     * @return S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getSummaryByModel(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("inbound", CONFIG_CATG.INBOUND);
        params.put("outbound", CONFIG_CATG.OUTBOUND);
        params.put("stsCd", ORD_LINE_STS.CANCELLED);
        params.put("warranty", DS_CONTR_CATG.WARRANTY);// QC#19826 2017/07/10 Add

        return getSsmEZDClient().queryObjectList("getSummaryByModel", params);
    }

}
