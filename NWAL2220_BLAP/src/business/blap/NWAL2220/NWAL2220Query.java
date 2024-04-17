/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL2220;

import static business.blap.NWAL2220.constant.NWAL2220Constant.BUSINESS_ID;
import static business.blap.NWAL2220.constant.NWAL2220Constant.FORMAT_TS;
import static business.blap.NWAL2220.constant.NWAL2220Constant.PARAM_FOURTEEN;
import static business.blap.NWAL2220.constant.NWAL2220Constant.PARAM_ONE;
import static business.blap.NWAL2220.constant.NWAL2220Constant.SRCH_COND_FROM_TIME;
import static business.blap.NWAL2220.constant.NWAL2220Constant.SRCH_COND_THRU_TIME;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/16   Hitachi         T.Tsuchida      Create          N/A
 * 2016/04/26   Hitachi         K.Kojima        Update          QC#6283
 *</pre>
 */
public final class NWAL2220Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL2220Query INSTANCE = new NWAL2220Query();

    /**
     * Constructor.
     */
    private NWAL2220Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL2220Query
     */
    public static NWAL2220Query getInstance() {
        return INSTANCE;
    }

    /**
     * getCpoSrcTpList
     * @param cMsg NWAL2220CMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCpoSrcTpList(NWAL2220CMsg cMsg, int cnt) {
        return getSsmEZDClient().queryObjectList("getCpoSrcTpList", getSsmParam(cMsg, cnt));
    }

    /**
     * getSearchData
     * @param cMsg NWAL2220CMsg
     * @param sMsg NWAL2220SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg.A.length() + 1), sMsg.A);
    }

    /**
     * getSsmParam
     * @param cMsg NWAL2220CMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    public Map<String, Object> getSsmParam(NWAL2220CMsg cMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cMsg", cMsg);
        params.put("limitCnt", cnt);
        params.put("paramOne", PARAM_ONE);
        params.put("paramFourteen", PARAM_FOURTEEN);
        params.put("formatTs", FORMAT_TS);
        if (hasValue(cMsg.ordSrcImptDt_FM)) {
            params.put("ordSrcImptTsFrom", cMsg.ordSrcImptDt_FM.getValue() + SRCH_COND_FROM_TIME);
        }
        if (hasValue(cMsg.ordSrcImptDt_TO)) {
            params.put("ordSrcImptTsTo", cMsg.ordSrcImptDt_TO.getValue() + SRCH_COND_THRU_TIME);
        }
        return params;
    }

    // START 2016/04/26 K.Kojima [QC#6283,ADD]
    /**
     * getSavedSearchOptionList
     * @param glblCmpyCd String
     * @param usrId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String glblCmpyCd, String usrId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("srchOptAplId", BUSINESS_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }
    // END 2016/04/26 K.Kojima [QC#6283,ADD]

}
