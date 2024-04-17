/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1230;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/10   CITS            T.Wada          Create          N/A
 *</pre>
 */
public class NSAL1230Query extends S21SsmEZDQuerySupport {

    private static final NSAL1230Query INSTANCE = new NSAL1230Query();

    private NSAL1230Query() {
    }

    public static NSAL1230Query getInstance() {
        return INSTANCE;
    }

    /**
     * findContractSegmentAllocationList
     * @param glblCmpyCd
     * @param dsContrPk
     * @param dsContrDtlPk
     * @param svcInvChrgTpCd
     * @param effDt
     * @return
     */
    public S21SsmEZDResult findContractSegmentAllocationList(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String svcInvChrgTpCd) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            params.put("dsContrDtlPk", dsContrDtlPk);
        }
        if (ZYPCommonFunc.hasValue(svcInvChrgTpCd)) {
            params.put("svcInvChrgTpCd", svcInvChrgTpCd);
        }

        return getSsmEZDClient().queryObjectList("findContractSegmentAllocationList", params);

    }
    /**
     * getSvcAcctDistCatgNm
     * @param glblCmpyCd
     * @param coaAcctCd
     * @return
     */
    public String getSvcAcctDistCatgNm(String glblCmpyCd, String coaAcctCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("coaAcctCd", coaAcctCd);
        return (String) getSsmEZDClient().queryObject("getSvcAcctDistCatgNm", ssmPrm).getResultObject();
}
}
