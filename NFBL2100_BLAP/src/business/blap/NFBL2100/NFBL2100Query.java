/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFBL2100;

import static business.blap.NFBL2100.constant.NFBL2100Constant.*;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Lease Buyout Approve List
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/25   Hitachi         K.Kojima        Create          N/A
 * 2016/03/10   Hitachi         K.Kojima        Update          CSA QC#4998
 *</pre>
 */
public final class NFBL2100Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFBL2100Query INSTANCE = new NFBL2100Query();

    /**
     * Constructor.
     */
    private NFBL2100Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFBL2100Query
     */
    public static NFBL2100Query getInstance() {
        return INSTANCE;
    }

    /**
     * getStrgyNm
     * @param glblCmpyCd String
     * @param vndCd String
     * @return String
     */
    public String getLocNm(String glblCmpyCd, String vndCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("vndCd", vndCd);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getLocNm", params);
        if (rs.isCodeNotFound()) {
            return null;
        }
        return (String) rs.getResultObject();
    }

    /**
     * getSearchData
     * @param cMsg NFBL2100CMsg
     * @param sMsg NFBL2100SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NFBL2100CMsg cMsg, NFBL2100SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.apDsWfStsCd_SV.getValue())) {
            params.put("apDsWfStsCd", cMsg.apDsWfStsCd_SV.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.vndCd.getValue())) {
            params.put("vndCd", cMsg.vndCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxFromDt.getValue())) {
            // START 2016/03/10 K.Kojima [QC#4998,MOD]
            // params.put("cpoOrdTsFrom", cMsg.xxFromDt.getValue());
            params.put("cpoOrdTsFrom", cMsg.xxFromDt.getValue() + SRCH_COND_FROM_TIME);
            // END 2016/03/10 K.Kojima [QC#4998,MOD]
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxToDt.getValue())) {
            // START 2016/03/10 K.Kojima [QC#4998,MOD]
            // params.put("cpoOrdTsTo", cMsg.xxToDt.getValue());
            params.put("cpoOrdTsTo", cMsg.xxToDt.getValue() + SRCH_COND_THRU_TIME);
            // END 2016/03/10 K.Kojima [QC#4998,MOD]
        }
        if (ZYPCommonFunc.hasValue(cMsg.cpoOrdNum.getValue())) {
            params.put("cpoOrdNum", cMsg.cpoOrdNum.getValue());
        }
        params.put("rowNum", cnt);
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", params, sMsg.A);
    }

}
