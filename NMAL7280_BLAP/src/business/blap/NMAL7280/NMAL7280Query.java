/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7280;

import java.util.HashMap;
import java.util.Map;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import static business.blap.NMAL7280.constant.NMAL7280Constant.GLOBAL_CMPY_CODE;
import static business.blap.NMAL7280.constant.NMAL7280Constant.RULE_ID;
import static business.blap.NMAL7280.constant.NMAL7280Constant.GROUP_PK;

/**
 *<pre>
 * NMAL7280Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public final class NMAL7280Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7280Query MY_INSTANCE = new NMAL7280Query();

    /**
     * Private constructor
     */
    private NMAL7280Query() {
        super();
    }

    /**
     * Get the NMAL7280Query instance.
     * @return NMAL7280Query instance
     */
    public static NMAL7280Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Price Header.
     * @param bizMsg NMAL7280CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceHeader(NMAL7280CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        params.put(RULE_ID, bizMsg.prcRuleHdrPk.getValue());
        return getSsmEZDClient().queryObject("getPriceHeader", params);
    }

    /**
     * Get Condition Group.
     * @param bizMsg NMAL7280CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConditionGroup(NMAL7280CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        params.put(RULE_ID, bizMsg.prcRuleHdrPk.getValue());
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryEZDMsgArray("getConditionGrp", params, bizMsg.A);
        return ssmResult;
    }

    /**
     * Get Transaction Condition.
     * @param bizMsg NMAL7280CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrxCond(NMAL7280CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        params.put(RULE_ID, bizMsg.prcRuleHdrPk);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryEZDMsgArray("getTrxCond", params, bizMsg.B);
        return ssmResult;
    }

    /**
     * Get Detail.
     * @param bizMsg NMAL7280CMsg
     * @param index index
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDtl(NMAL7280CMsg bizMsg, int index) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        params.put(RULE_ID, bizMsg.prcRuleHdrPk.getValue());
        params.put(GROUP_PK, bizMsg.A.no(index).prcRuleCondHdrPk_A.getValue());
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getDtl", params);
        return ssmResult;
    }

}
