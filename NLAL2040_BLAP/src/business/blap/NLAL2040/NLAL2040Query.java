/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2040;

import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Business ID : NLAL2040 MODELS-CLICKS Inventory Valuation Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            T.Kikuhara      Create          N/A
 * 12/07/2016   CITS            R.Shimamoto     Update          QC#13056
 * 12/27/2016   CITS            T.Kikuhara      Update          QC#13056-2
 *</pre>
 */
public final class NLAL2040Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance.
     */
    private static final NLAL2040Query MY_INSTANCE = new NLAL2040Query();

    /**
     * Constructor.
     */
    private NLAL2040Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return  NLAL2040Query
     */
    public static NLAL2040Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Pulldown list of LineType
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWhCatgSwPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRtlWhCatgSwPulldownList", ssmParam);
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @param sMsg NLAL2040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NLAL2040SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }


    /**
     * checkModelExist
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkModelExist(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkModelExist", ssmParam);
    }

    /**
     * getSwfByMdl
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSwfByMdl(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSwfByMdl", ssmParam);
    }

    /**
     * getNewSqId
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNewSqId(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getNewSqId", ssmParam);
    }

    /**QC#13056-2 Add.
     * getInvtyOwnerCd
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyOwnerCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getInvtyOwnerCd", ssmParam);
    }

    /**
     * getThirdPtyDspTpCd
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getThirdPtyDspTpCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getThirdPtyDspTpCd", ssmParam);
    }

    /**
     * getMtrReqMdlFlg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMtrReqMdlFlg(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getMtrReqMdlFlg", ssmParam);
    }

    /**QC#13056 Add.
     * Get Pulldown list of Owner Code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOwnerCdPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getOwnerCdPulldownList", ssmParam);
    }

    /**
     * checkStartZero
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkStartZero(Map<String, Object> ssmParam) {
    	if (ZYPConstant.FLG_ON_Y.equals(ssmParam.get("MTR_REQ_MDL_FLG").toString())) {
    		return getSsmEZDClient().queryObject("checkFromZeroForMeter", ssmParam);
    	} else {
    		return getSsmEZDClient().queryObject("checkFromZeroForAge", ssmParam);
    	}
    }

}
