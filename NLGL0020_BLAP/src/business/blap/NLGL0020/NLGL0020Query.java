/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0020;

import java.util.Map;

import business.blap.NLGL0020.constant.NLGL0020Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update      Defect No
 * ------------------------------------------------------------------------------------
 * 08/30/2013     CSAI            N.Sekine          Create             MW Replace Initial
 * 05/24/2017     CITS            S.Endo            Update             RS#3173
 *</pre>
 */
public final class NLGL0020Query extends S21SsmEZDQuerySupport implements NLGL0020Constant {

    /**
     * Singleton instance.
     */
    private static final NLGL0020Query MYINSTANCE = new NLGL0020Query();

    /**
     * Constructor.
     */
    private NLGL0020Query() {

        super();
    }

    /**
     * Singleton instance getter.
     * @return NLGL0020Query
     */
    public static NLGL0020Query getInstance() {

        return MYINSTANCE;
    }

    /**
     * statement id : getWHPullDownList to get the WH code and WH name
     * for pulldouwn list from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHPullDownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWHPullDownList", ssmParam);
    }
    
    /**
     * statement id : getAdditionalWHPullDownList to get the WH code and WH name
     * for pulldouwn list from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAdditionalWHPullDownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getAdditionalWHPullDownList", ssmParam);
    }

    /**
     * statement id : getRelatedRtlWhPullDownList to get the Retail WH code and Retail WH name
     * for pulldouwn list from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedRtlWhPullDownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getRelatedRtlWhPullDownList", ssmParam);
    }

    /**
     * statement id : getRelatedInvtyOwnerCd to get the Invty Owner Code related in Retail WH Cd
     * for pulldouwn list from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedInvtyOwnerCd(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObject("getRelatedInvtyOwnerCd", ssmParam);
    }

    /**
     * statement id : getWHPullDownList to get the WH code and WH name
     * for pulldouwn list from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWhPullDownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getRtlWhPullDownList", ssmParam);
    }

    /**
     * statement id : getOrderTypePulldownList to get OrderType Data
     * for pulldouwn list from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrderTypePulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getOrderTypePulldownList", ssmParam);
    }

    /**
     * statement id : getRwsHdrKey to get RWS_HDR Primary Keys
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRwsHdrKey(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObject("getRwsHdrKey", ssmParam);
    }

    /**
     * statement id : getWHPullDownList to get the WH code and WH name
     * for pulldouwn list from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPackCdPullDownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getPackCdPullDownList", ssmParam);
    }

    /**
     * statement id : getPoList to get the PO List Data from DB
     * @param globalMsg ALGL020SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoList(NLGL0020SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getPoList", ssmParam, globalMsg.A);
    }

    /**
     * statement id : getTranslateStatus to get the Translate Status
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTranslateStatus(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getTranslateStatus", ssmParam);
    }

    /**
     * statement id : getOrderHeader to get the PO Status/Download
     * Edit Header) Data from DB
     * @param globalMsg ALGL020SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrderHeader(NLGL0020SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getOrderHeader", ssmParam, globalMsg.B);
    }

    /**
     * statement id : getOrderDetail to get the PO Status/Download
     * Edit Data(Order Detail) from DB
     * @param globalMsg ALGL020SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrderDetail(NLGL0020SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getOrderDetail", ssmParam, globalMsg.C);
    }

    /**
     * statement id : getSTSUPDTransaction to get the PO Status
     * Data(Upload Transaction) from DB
     * @param globalMsg ALGL020SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSTSUPDTransaction(NLGL0020SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getSTSUPDTransaction", ssmParam, globalMsg.D);
    }

    /**
     * statement id : getUPDDetail to get the PO Status Data(Order
     * Detail) from DB
     * @param globalMsg ALGL020SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUPDDetail(NLGL0020SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getUPDDetail", ssmParam, globalMsg.I);
    }

    /**
     * statement id : getRowId to get the ROWID from DB
     * (WMS_INBD_PO_HDR)
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRowId(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getRowId", ssmParam);
    }

    /**
     * statement id : getPoHeaderData to get the all information from
     * DB (WMS_INBD_PO_HDR)
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoHeaderData(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getPoHeaderData", ssmParam);
    }

    /**
     * statement id : getOrderDetailData to get the Order Detail
     * information from DB (WMS_INBD_PO_DTL)
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrderDetailData(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getOrderDetailData", ssmParam);
    }

    /**
     * statement id : getMDSECount to get the MDSE SQ # from DB
     * (WMS_INBD_MDSE)
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMDSECount(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getMDSECount", ssmParam);
    }

    /**
     * statement id : getMDSEData to get the MDSE information from DB
     * (WMS_INBD_MDSE)
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMDSEData(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getMDSEData", ssmParam);
    }

    /**
     * statement id : VenderData to get the Vender information from DB
     * (WMS_INBD_PO_VND)
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVenderData(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getVenderData", ssmParam);
    }

    /**
     * statement id : VenderData to get the Vender information from DB
     * (WMS_INBD_PO_VND)
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWmsInbdPoHdr(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWmsInbdPoHdr", ssmParam);
    }

    /**
     * statement id : Retail WH Data to get the Retail WH information from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWhNm(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRtlWhNm", ssmParam);
    }
}
