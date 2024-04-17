/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL1040;

import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_ABC_ANLS_CLS_NUM;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_ABC_ASG_PK;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_CMSG;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_GLBL_CMPY_CD;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_MDSE_CD;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NLCL1040 Inventory ABC Analysis Setup
 * Function Name : NLCL1040Query
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public final class NLCL1040Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL1040Query MY_INSTANCE = new NLCL1040Query();

    /**
     * Constructor.
     */
    private NLCL1040Query() {
        super();
    }

    /**
     * getInstance
     * @return NLCL1040Query
     */
    public static NLCL1040Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getPullDownList
     * @param params Map<String, Object>
     * @param ssmId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPullDownList(Map<String, Object> params, String ssmId) {

        return getSsmEZDClient().queryObjectList(ssmId, params);
    }

    /**
     * getAbcHeader
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAbcHeader(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put(BIND_CMSG, cMsg);

        return getSsmEZDClient().queryEZDMsg("getAbcHeader", params, sMsg);
    }

    /**
     * getHeaderInfo
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     * @param ssmId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderInfo(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg, String ssmId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put(BIND_ABC_ASG_PK, sMsg.abcAsgPk);

        return getSsmEZDClient().queryObjectList(ssmId, params);
    }

    /**
     * getLogicalRemoveList
     * @param cMsg NLCL1040CMsg
     * @param ssmId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLogicalRemoveList(NLCL1040CMsg cMsg, String ssmId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put(BIND_ABC_ASG_PK, cMsg.abcAsgPk);

        return getSsmEZDClient().queryObjectList(ssmId, params);

    }

    /**
     * getAbcDetail
     * @param ssmParam Map<String, Object>
     * @param sMsg NLCL1040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAbcDetail(Map<String, Object> ssmParam, NLCL1040SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getAbcDetail", ssmParam, sMsg.A);

    }

    /**
     * getItemDescription
     * @param cMsg NLCL1040CMsg
     * @param selectNum int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemDescription(NLCL1040CMsg cMsg, int selectNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put(BIND_MDSE_CD, cMsg.A.no(selectNum).mdseCd_A);

        return getSsmEZDClient().queryObject("getItemDescription", params);

    }

    /**
     * getWarehouseInfoList
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWarehouseInfoList(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObjectList("getWarehouseInfoList", queryParams);

    }

    /**
     * getAllRtlSwhList
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAllRtlSwhList(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObjectList("getAllRtlSwhList", queryParams);

    }

    /**
     * getSubWarehouseInfoList
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSubWarehouseInfoList(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObjectList("getSubWarehouseInfoList", queryParams);

    }

    /**
     * getabcClassTag
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getabcClassTag(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put(BIND_ABC_ANLS_CLS_NUM, cMsg.abcAnlsClsNum_H1);

        return getSsmEZDClient().queryObjectList("getabcClassTag", params);
    }

    /**
     * getAbcName
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAbcName(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObjectList("getAbcName", queryParams);

    }

    /**
     * historicaCheckt
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult historicaCheckt(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObjectList("historicaCheckt", queryParams);

    }

    /**
     * whDuplicate
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult whDuplicate(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObjectList("whDuplicate", queryParams);

    }

    /**
     * getRequestStatus
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRequestStatus(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObjectList("getRequestStatus", queryParams);

    }

    /**
     * checkMdseInvtyCtrl
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkMdseInvtyCtrl(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObject("checkMdseInvtyCtrl", queryParams);

    }

    /**
     * duplicateCheckWithAnlsDtl
     * @param queryParams Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult duplicateCheckWithAnlsDtl(Map<String, Object> queryParams) {

        return getSsmEZDClient().queryObjectList("duplicateCheckWithAnlsDtl", queryParams);

    }

    /**
     * getLatestAnlsRequest
     * @param queryParams Map<String, Object>
     * @param sMsg NLCL1040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLatestAnlsRequest(Map<String, Object> queryParams, NLCL1040SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsg("getLatestAnlsRequest", queryParams, sMsg);

    }

    /**
     * getAbcAnlsRslt
     * @param queryParams Map<String, Object>
     * @param sMsg NLCL1040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAbcAnlsRslt(Map<String, Object> queryParams, NLCL1040SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsg("getAbcAnlsRslt", queryParams, sMsg);

    }

}
