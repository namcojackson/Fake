package business.blap.NLBL3150;

/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_WH_COND_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NLBL3150Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/12   Fujitsu         K.Ishizuka      Create          N/A
 * 2017/08/25   Fujitsu         M.Ohno          Update          QC#20772
 *</pre>
 */
public final class NLBL3150Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NLBL3150Query MY_INSTANCE = new NLBL3150Query();

    /**
     * Private constructor
     */
    private NLBL3150Query() {
        super();
    }

    /**
     * Get the NLBL3150Query instance.
     * @return NLBL3150Query instance
     */
    public static NLBL3150Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * execute DB access to get item information list
     * @param bizMsg NLBL3150CMsg
     * @param glblMsg NLBL3150SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NLBL3150CMsg bizMsg, NLBL3150SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("condTpCd", MDSE_WH_COND_TP.MANUAL);
        params.put("rtlWhCd", bizMsg.rtlWhCd.getValue());
        params.put("rtlSwhCd", bizMsg.rtlSwhCd.getValue());
        params.put("mdseCd", bizMsg.mdseCd.getValue());
        params.put("rowNum", glblMsg.A.length());

        return getSsmEZDClient().queryEZDMsgArray("searchResultList", params, glblMsg.A);
    }

    /**
     * execute DB access to get specified item information
     * @param mdseCd Merchandise Code
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemInfo(String mdseCd) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getItemInfo", params);
    }

    /**
     * execute DB access to check registererd item
     * @param acMsg NLBL3150_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkRegisteredRule(NLBL3150_ASMsg acMsg) {
        String mdseCd = acMsg.mdseCd_A.getValue();
        // 2017/08/14 QC#20556 DEL BEGIN
//        // 2017/08/10 QC#20556 MOD BEGIN
////        if (acMsg.mdseCd_A.getValue().length() == 10) {
////            mdseCd = acMsg.mdseCd_A.getValue().substring(0, 8);
//        if (!ZYPCommonFunc.hasValue(acMsg.mdseWhCondPk_A)) {
//            mdseCd = acMsg.mdseCd_A.getValue().substring(0, 8) + "%";
//            // 2017/08/10 QC#20556 MOD END
//        }
        // 2017/08/14 QC#20556 DEL BEGIN
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        // 2017/08/14 QC#20556 MOD BEGIN
//        params.put("mdseCd", mdseCd);
        // 2017/08/25 QC#20772 MOD BEGIN
        if (mdseCd.length() > 8) {
            params.put("mdseCd8", mdseCd.substring(0, 8));
        } else {
            params.put("mdseCd8", mdseCd);
        }
//        params.put("mdseCd8", mdseCd.substring(0, 8));
        // 2017/08/25 QC#20772 MOD END
        params.put("mdseCd10", mdseCd);
        // 2017/08/14 QC#20556 MOD BEGIN
        params.put("rtlWhCd", acMsg.rtlWhCd_A.getValue());
        params.put("rtlSwhCd", acMsg.rtlSwhCd_A.getValue());
        params.put("mdseWhCondTpCd", MDSE_WH_COND_TP.MANUAL);
        // 2017/08/14 QC#20556 ADD BEGIN
        params.put("mdseWhCondPk", acMsg.mdseWhCondPk_A.getValue());
        params.put("asterisk", "*");
        // 2017/08/14 QC#20556 ADD END

        return getSsmEZDClient().queryObject("checkRegisteredRule", params);
    }

}
