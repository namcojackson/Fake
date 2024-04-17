/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFDL0140;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         K.Kojima        Create          N/A
 * 2016/02/17   Hitachi         K.Kojima        Update          CSA QC#4285
 *</pre>
 */
public final class NFDL0140Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFDL0140Query INSTANCE = new NFDL0140Query();

    /**
     * Constructor.
     */
    private NFDL0140Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0140Query
     */
    public static NFDL0140Query getInstance() {
        return INSTANCE;
    }

    // del start 2016/02/17 CSA Defect#4285
    // /**
    // * getStrgyNm
    // * @param glblCmpyCd String
    // * @param cltStrgyCd String
    // * @return String
    // */
    // public String getStrgyNm(String glblCmpyCd, String cltStrgyCd)
    // {
    // Map<String, Object> params = new HashMap<String, Object>();
    // params.put("glblCmpyCd", glblCmpyCd);
    // params.put("cltStrgyCd", cltStrgyCd);
    // S21SsmEZDResult rs =
    // getSsmEZDClient().queryObject("getStrgyNm", params);
    // if (rs.isCodeNotFound()) {
    // return null;
    // }
    // return (String) rs.getResultObject();
    // }
    // del end 2016/02/17 CSA Defect#4285

    /**
     * getPulldownList
     * @param cMsg NFDL0140CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPulldownList(NFDL0140CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("rowNum", cMsg.N.length());
        return getSsmEZDClient().queryEZDMsgArray("getPulldownList", params, cMsg.N);
    }

    /**
     * getCltOverDueRangeAmtSameData
     * @param glblCmpyCd String
     * @param cltStrgyCd String
     * @return String
     */
    public String getCltOverDueRangeAmtSameData(String glblCmpyCd, String cltStrgyCd, String cltCustTpCd, BigDecimal cltOverDueRangeLowAmt, BigDecimal cltOverDueRangeHighAmt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cltStrgyCd", cltStrgyCd);
        params.put("cltCustTpCd", cltCustTpCd);
        params.put("cltOverDueRangeLowAmt", cltOverDueRangeLowAmt);
        params.put("cltOverDueRangeHighAmt", cltOverDueRangeHighAmt);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getCltOverDueRangeAmtSameData", params);
        if (rs.isCodeNotFound()) {
            return null;
        }
        return (String) rs.getResultObject();
    }

}
