/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0770;

import java.util.HashMap;
import java.util.Map;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Change Status Audit
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public final class NSAL0770Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0770Query INSTANCE = new NSAL0770Query();

    /**
     * Constructor.
     */
    private NSAL0770Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0770Query
     */
    public static NSAL0770Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0760CMsg
     * @param sMsg NSAL0760SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0770CMsg cMsg, NSAL0770SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg), sMsg.A);
    }

    private Map<String, Object> getSsmParam(NSAL0770CMsg cMsg, NSAL0770SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrTrkTpCd", cMsg.dsContrTrkTpCd);
        if (hasValue(cMsg.dsContrPk)) {
            params.put("dsContrPk", cMsg.dsContrPk);
        }
        if (hasValue(cMsg.dsContrDtlPk)) {
            params.put("dsContrDtlPk", cMsg.dsContrDtlPk);
        }
        if (hasValue(cMsg.dsContrBllgMtrPk)) {
            params.put("dsContrBllgMtrPk", cMsg.dsContrBllgMtrPk);
        }
        if (hasValue(cMsg.xxFromDt) && (DS_CONTR_TRK_TP.BASE_CHARGE_PE.equals(cMsg.dsContrTrkTpCd.getValue()) || DS_CONTR_TRK_TP.USAGE_CHARGE_PE.equals(cMsg.dsContrTrkTpCd.getValue()))) {
            params.put("contrPrcEffFromDt", cMsg.xxFromDt);
        }
        params.put("maxCnt", sMsg.A.length() + 1);
        return params;
    }
}
