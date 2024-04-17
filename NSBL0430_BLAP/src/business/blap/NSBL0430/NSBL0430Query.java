/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0430;

import static business.blap.NSBL0430.constant.NSBL0430Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Mods Serial# Assignment
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/28   Hitachi         O.Okuma         Create          N/A
 * 2016/03/01   Hitachi         O.Okuma         Update          QC4726
 * 2016/04/18   Hitachi         M.Gotou         Update          QC3425
 * 2016/07/11   Hitachi         O.Okuma         Update          QC11646
 * 2017/10/26   Hitachi         U.Kim           Update          QC21797
 *</pre>
 */
public final class NSBL0430Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0430Query INSTANCE = new NSBL0430Query();

    /**
     * Constructor.
     */
    private NSBL0430Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0430Query
     */
    public static NSBL0430Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, cnt), sMsg.A);
    }

    /**
     * getDefaultData
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefaultData(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg, int cnt) {
        Map<String, Object> params = getSsmParam(cMsg, sMsg, cnt);

        params.put("slsDt", cMsg.slsDt.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getDefaultData", params, sMsg.A);
    }

    /**
     * getSsmParam
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcModPk"  , cMsg.svcModPk.getValue());
        params.put("limitCnt"  , cnt);
        // add start 2016/04/18 CSA Defect#3425
        params.put("mdseCd"    , cMsg.mdseCd_F.getValue());
        // add end 2016/04/18 CSA Defect#3425

        return params;
    }

    /**
     * getDetailData
     * @param cMsg NSBL0430CMsg
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailData(NSBL0430CMsg cMsg, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcModPk"  , cMsg.svcModPk.getValue());
        if (mdseCd.length() > MDSE_CODE_LEN_8) {
            params.put("mdseCd_8"    , mdseCd.substring(0, MDSE_CODE_LEN_8));
        }
        params.put("mdseCd"    , mdseCd);
        params.put("slsDt"     , cMsg.slsDt.getValue());

        return getSsmEZDClient().queryObjectList("getDetailData", params);
    }

    /**
     * getCallData
     * @param cMsg NSBL0430CMsg
     * @param svcModSerRngPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCallData(NSBL0430CMsg cMsg, BigDecimal svcModSerRngPk) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcModSerRngPk"  , svcModSerRngPk);

        return getSsmEZDClient().queryObjectList("getCallData", params);
    }

    // START 2017/10/26 U.Kim [QC#21797, ADD]
    /**
     * getAutoCratRfrsTmgCd
     * @param cMsg NSBL0430CMsg
     * @param autoCratRfrsTmgDescTxt String
     * @return String
     */
    public String getAutoCratRfrsTmgCd(NSBL0430CMsg cMsg, String autoCratRfrsTmgDescTxt) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("autoCratRfrsTmgDescTxt"  , autoCratRfrsTmgDescTxt);

        return (String) getSsmEZDClient().queryObject("getAutoCratRfrsTmgCd", params).getResultObject();
    }
    // END 2017/10/26 U.Kim [QC#21797, ADD]
}
