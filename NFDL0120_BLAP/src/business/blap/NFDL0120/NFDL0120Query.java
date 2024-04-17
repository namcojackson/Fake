/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFDL0120;

import static business.blap.NFDL0120.constant.NFDL0120Constant.*;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Hitachi         K.Kojima        Create          N/A
 * 2016/02/17   Hitachi         K.Kojima        Update          CSA QC#4187
 *</pre>
 */
public final class NFDL0120Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFDL0120Query INSTANCE = new NFDL0120Query();

    /**
     * Constructor.
     */
    private NFDL0120Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0120Query
     */
    public static NFDL0120Query getInstance() {
        return INSTANCE;
    }

    /**
     * getStrgyNm
     * @param glblCmpyCd String
     * @param cltStrgyCd String
     * @return String
     */
    public String getStrgyNm(String glblCmpyCd, String cltStrgyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cltStrgyCd", cltStrgyCd);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getStrgyNm", params);
        if (rs.isCodeNotFound()) {
            return null;
        }
        return (String) rs.getResultObject();
    }

    /**
     * getPulldownList
     * @param cMsg NFDL0120CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPulldownList(NFDL0120CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("rowNum", cMsg.N.length());
        return getSsmEZDClient().queryEZDMsgArray("getPulldownList", params, cMsg.N);
    }

    /**
     * getSearchData
     * @param cMsg NFDL0120CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NFDL0120CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("cltStrgyCd", cMsg.cltStrgyCd.getValue());
        params.put("xxTpCd_Upd", XX_TP_CD_UPD);
        // add start 2016/02/17 CSA Defect#4187
        params.put("rowNum", cMsg.A.length());
        // add end 2016/02/17 CSA Defect#4187
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", params, cMsg.A);
    }

}
