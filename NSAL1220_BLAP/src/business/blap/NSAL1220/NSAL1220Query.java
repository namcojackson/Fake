/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1220;

import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.COA_BRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Contract Branch Revenue Distribution
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public final class NSAL1220Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1220Query INSTANCE = new NSAL1220Query();

    /**
     * Constructor.
     */
    private NSAL1220Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1220Query
     */
    public static NSAL1220Query getInstance() {
        return INSTANCE;
    }

    /**
     * get Coa branch code
     * @param glblCmpyCd String
     * @param coaBrCd String
     * @return COA_BRTMsg
     */
    public static COA_BRTMsg getCoaBrCd(String glblCmpyCd, String coaBrCd) {
        COA_BRTMsg tMsg = new COA_BRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd, coaBrCd);
        COA_BRTMsg result = (COA_BRTMsg) EZDTBLAccessor.findByKey(tMsg);
        return result;
    }

    /**
     * get Contract Info
     * @param cMsg NSAL1220CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrInfo(NSAL1220CMsg cMsg) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("dsContrPk", cMsg.dsContrPk.getValue());
        queryMap.put("dsContrDtlPk", cMsg.dsContrDtlPk.getValue());
        queryMap.put("svcInvChrgTpCd", cMsg.svcInvChrgTpCd.getValue());
        queryMap.put("limitCnt", cMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getContrInfo", queryMap, cMsg.A);
    }
}
