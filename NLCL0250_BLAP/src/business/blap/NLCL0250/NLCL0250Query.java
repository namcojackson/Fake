/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL0250;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import business.blap.NLCL0250.constant.NLCL0250Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Onhand Inventory Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3134
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3137
 * 10/20/2016   CSAI            Y.Imazu         Update          QC#14081
 * 12/18/2017   CITS            S.Katsuma       Update          QC#22469
 * 02/22/2018   CITS            T.Wada          Update          QC#21830
 * 03/20/2018   CITS            S.Katsuma       Update          QC#24715
 *</pre>
 */
public final class NLCL0250Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL0250Query myInstance = new NLCL0250Query();

    /**
     * Constructor.
     */
    private NLCL0250Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLCL0250Query
     */
    public static NLCL0250Query getInstance() {
        return myInstance;
    }

    /**
     * getSavedSearchOptionList
     * @param cMsg NLCL0250CMsg
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(NLCL0250CMsg cMsg, String usrId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("srchOptAplId", NLCL0250Constant.BUSINESS_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * getPullDownList
     * @param cMsg NLCL0250CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPullDownList(NLCL0250CMsg cMsg, String ssmId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList(ssmId, params);
    }

    /**
     * getLocStsList
     * @param cMsg NLCL0250CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocStsList(NLCL0250CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsCondConstGrpId", NLCL0250Constant.LOC_STS_COND_CONST_GRP_ID);

        return getSsmEZDClient().queryObjectList("getLocStsList", params);
    }

    /**
     * getAllLocStsList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAllLocStsList(String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        return getSsmEZDClient().queryObjectList("getAllLocStsList", params);
    }

    /**
     * getOrdTakeMdseList
     * @param glblCmpyCd String
     * @param itemList ArrayList<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdTakeMdseList(String glblCmpyCd, ArrayList<String> itemList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("itemList", itemList);

        return getSsmEZDClient().queryObjectList("getOrdTakeMdseList", params);
    }

    /**
     * getRtlWhList
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWhList(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObjectList("getRtlWhList", queryParams);
    }

    /**
     * getSwhList
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSwhList(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObjectList("getSwhList", queryParams);
    }

    /**
     * getShipToLocList
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToLocList(Map<String, Object> queryParams) {

//        return getSsmEZDClient().queryObject("getShipToLocList", queryParams);
        return getSsmEZDClient().queryObjectList("getShipToLocList", queryParams);
    }

    /**
     * getInvtyLocCnt
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyLocCnt(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObject("getInvtyLocCnt", queryParams);
    }

    /**
     * searchSmry
     * @param queryParams Map<String, Object>
     * @param sMsg NLCL0250SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchSmry(Map<String, Object> queryParams, NLCL0250SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("searchSmry", queryParams, sMsg.A);
    }

    /**
     * searchDtl
     * @param queryParams Map<String, Object>
     * @param sMsg NLCL0250SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchDtl(Map<String, Object> queryParams, NLCL0250SMsg sMsg) {

        return getSsmEZDClient().queryObjectList("searchDtl", queryParams);
    }

    // START 2017/12/18 S.Katsuma [QC#22469,ADD]
    /**
     * getRtrnCtrlTp
     * @param queryParams Map<String, Object>
     * @param sMsg NLCL0250SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtrnCtrlTp(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObject("getRtrnCtrlTp", queryParams);
    }
    // END 2017/12/18 S.Katsuma [QC#22469,ADD]

    // QC#21830
    /**
     * getMdseCd
     */
    public S21SsmEZDResult getMdseCd(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObjectList("getMdseCd", queryParams);
    }

    // START 2018/03/20 S.Katsuma [QC#24715,ADD]
    /**
     * getMrpInfoForOrdTakeMdse
     * @param queryParams Map<String, Object>
     * @param sMsg NLCL0250SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMrpInfoForOrdTakeMdse(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObjectList("getMrpInfoForOrdTakeMdse", queryParams);
    }
    // END 2018/03/20 S.Katsuma [QC#24715,ADD]
}
