/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFDL0150;

import static business.blap.NFDL0150.constant.NFDL0150Constant.*;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/03   Hitachi         T.Kanasaka      Create          N/A
 * 2016/02/17   Hitachi         K.Kojima        Update          CSA QC#4154
 *</pre>
 */
public final class NFDL0150Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFDL0150Query INSTANCE = new NFDL0150Query();

    /**
     * Constructor.
     */
    private NFDL0150Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0150Query
     */
    public static NFDL0150Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NFDL0150CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NFDL0150CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("xxTpCd_Upd", XX_TP_CD_UPD);
        // add start 2016/02/17 CSA Defect#4154
        params.put("rowNum", cMsg.A.length());
        // add end 2016/02/17 CSA Defect#4154
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", params, cMsg.A);
    }

    /**
     * getCltStrgyRelnCustTp
     * @param glblCmpyCd String
     * @param cltStrgyCd String
     * @return String
     */
    public String getCltStrgyRelnCustTp(String glblCmpyCd, String cltStrgyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cltStrgyCd", cltStrgyCd);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getCltStrgyRelnCustTp", params);
        if (rs.isCodeNotFound()) {
            return null;
        }
        return (String) rs.getResultObject();
    }

    /**
     * getCountCltStrgyRelnCustTp
     * @param glblCmpyCd String
     * @param cltStrgyCd String
     * @return String
     */
    public String getCltStrgyRelnWrkItem(String glblCmpyCd, String cltStrgyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cltStrgyCd", cltStrgyCd);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getCltStrgyRelnWrkItem", params);
        if (rs.isCodeNotFound()) {
            return null;
        }
        return (String) rs.getResultObject();
    }
}
