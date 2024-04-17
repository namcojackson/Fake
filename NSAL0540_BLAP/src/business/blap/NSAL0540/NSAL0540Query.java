/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0540;

import static business.blap.NSAL0540.constant.NSAL0540Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MEMOTMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Solution Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public final class NSAL0540Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0540Query INSTANCE = new NSAL0540Query();

    /**
     * Constructor.
     */
    private NSAL0540Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0540Query
     */
    public static NSAL0540Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSvcConfigInfo
     * @param cMsg NSAL0540CMsg
     * @param sMsg NSAL0540SMsg
     * @param svcConfigMstrPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getSvcConfigInfo(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg, BigDecimal svcConfigMstrPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);
        ssmParam.put("rowNum", sMsg.A.length() + 1);

        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcConfigInfo", ssmParam).getResultObject();
    }

    /**
     * getSvcMemoTxt
     * @param cMsg NSAL0540CMsg
     * @return List<String>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getSvcMemo(NSAL0540CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("svcMemoCatgCd", SVC_MEMO_CATG.SOLUTION_MEMO);
        ssmParam.put("svcMemoTpCd", SVC_MEMO_TP.SOLUTION);
        ssmParam.put("svcMemoTrxNm", SVC_SLN_SQ);

        return (Map<String, Object>) getSsmEZDClient().queryObject("getSvcMemo", ssmParam).getResultObject();
    }

    /**
     * Get SVC_CONFIG_MSTRTMsg for update
     * @param cMsg NSAL0540CMsg
     * @param svcConfigMstrPk BigDecimal
     * @return SVC_CONFIG_MSTRTMsg
     */
    public SVC_CONFIG_MSTRTMsg getSvcConfigMstrForUpdate(NSAL0540CMsg cMsg, BigDecimal svcConfigMstrPk) {

        SVC_CONFIG_MSTRTMsg inMsg = new SVC_CONFIG_MSTRTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.svcConfigMstrPk, svcConfigMstrPk);
        try {
            return (SVC_CONFIG_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        } catch (EZDDBRecordLockedException e) {
            cMsg.setMessageInfo(NZZM0003E);
            return null;
        }
    }

    /**
     * Get SVC_MEMOTMsg for update
     * @param cMsg NSAL0540CMsg
     * @return SVC_MEMOTMsg
     */
    public SVC_MEMOTMsg getSvcMemoForUpdate(NSAL0540CMsg cMsg) {

        SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.svcMemoPk, cMsg.svcMemoPk);
        try {
            return (SVC_MEMOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        } catch (EZDDBRecordLockedException e) {
            cMsg.setMessageInfo(NZZM0003E);
            return null;
        }
    }
}

