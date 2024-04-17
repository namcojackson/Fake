/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0950;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.blap.NSAL0950.common.NSAL0950CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Contract Validation Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/07/07   Hitachi         O.Okuma         Update          QC#10991
 *</pre>
 */
public final class NSAL0950Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0950Query INSTANCE = new NSAL0950Query();

    /**
     * Private constructor
     */
    private NSAL0950Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0950Query singleton instance
     */
    public static NSAL0950Query getInstance() {
        return INSTANCE;
    }

    /**
     * getDsContrVldList
     * @param cMsg NSAL0950CMsg
     * @param sMsg NSAL0950SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrVldList(NSAL0950CMsg cMsg, NSAL0950SMsg sMsg) {
        setValue(cMsg.glblCmpyCd_S, getGlobalCompanyCode());
        Map<String, Object> params = NSAL0950CommonLogic.getSearchCriteriaMap(cMsg);

        return getSsmEZDClient().queryObjectList("getDsContrVldList", params);
    }

    // START 2016/07/07 [QC#10991, ADD]
    /**
     * cheakDsContrVldNm
     * @param cMsg NSAL0950CMsg
     * @param dsContrVldPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult cheakDsContrVldNm(NSAL0950CMsg cMsg, BigDecimal dsContrVldPk) {

        Map<String, Object> params  = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd_D.getValue());
        params.put("dsContrVldCatgCd", cMsg.dsContrVldCatgCd_DS.getValue());
        params.put("dsContrVldNm", cMsg.dsContrVldNm_D.getValue());
        params.put("effFromDt", cMsg.effFromDt_D.getValue());

        if (ZYPCommonFunc.hasValue(dsContrVldPk)) {
            params.put("dsContrVldPk", dsContrVldPk);
        }

        if (ZYPCommonFunc.hasValue(cMsg.effToDt_D)) {
            params.put("effToDt", cMsg.effToDt_D.getValue());
        }

        return getSsmEZDClient().queryObjectList("cheakDsContrVldNm", params);
    }
    // END 2016/07/07 [QC#10991, ADD]
}
