/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLEL0070;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Asset Book Control
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public final class NLEL0070Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NLEL0070Query MY_INSTANCE = new NLEL0070Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NLEL0070Query() {
        super();
    }

    /**
     * <pre>
     * Get the DWAL010Query instance.
     * </pre>
     * @return NLAL0070Query instance
     */
    public static NLEL0070Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * Get TocCode. 
     * </pre>
     * @param glblCmpyCd String
     * @param cMsg NLEL0070CMsg
     * @param slsDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAssetBookCtrl(String glblCmpyCd, NLEL0070CMsg cMsg, String slsDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("assetTpCd", cMsg.assetTpCd_H1.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.effFromDt_H1)) {
            ssmParam.put("effFromDt", cMsg.effFromDt_H1.getValue());
        }
        return getSsmEZDClient().queryEZDMsg("getAssetBookCtrl", ssmParam, cMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param assetTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEffFrom(String glblCmpyCd, String assetTpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("assetTpCd", assetTpCd);
        return getSsmEZDClient().queryObjectList("getEffFrom", ssmParam);
    }

    // START 2016/06/27 K.Kojima [QC#10041,ADD]
    /**
     * getLastDepcPeriod
     * @param glblCmpyCd String
     * @param assetTpCd String
     * @return boolean
     */
    public String getLastDepcPeriod(String glblCmpyCd, String assetTpCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("assetTpCd", assetTpCd);
        queryParam.put("trxCd", TRX.DEPRECIATION);
        queryParam.put("trxRsnCd", TRX_RSN.DEPRECIATION);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getLastDepcPeriod", queryParam);
        if (rslt != null && rslt.isCodeNormal()) {
            return (String) rslt.getResultObject();
        }
        return null;
    }
    // END 2016/06/27 K.Kojima [QC#10041,ADD]
}
