/**
 * <pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0010;

import java.util.Map;

import business.blap.NLGL0010.constant.NLGL0010Constant;
import business.db.WMS_INBD_SO_HDRTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * SO Mainenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/20/2013   CSAI            Y.Miyauchi      Create          MW Replace Initial
 * 05/31/2017   CITS            S.Endo          Update          RS#3168
 * 07/05/2017   CITS            K.Ogino         Update          QC#18949
 *</pre>
 */
public final class NLGL0010Query extends S21SsmEZDQuerySupport implements NLGL0010Constant {

    /**
     * Singleton instance.
     */
    private static final NLGL0010Query MY_INSTANCE = new NLGL0010Query();

    /**
     * Constructor.
     */
    private NLGL0010Query() {

        super();
    }

    /**
     * Singleton instance getter.
     * @return NLGL0010Query
     */
    public static NLGL0010Query getInstance() {

        return MY_INSTANCE;
    }

    /**
     * statement id : getWHPullDownList gets the WH code and WH name
     * for pulldouwn list from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHPullDownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWHPullDownList", ssmParam);
    }

    /**
     * statement id : getWmsOrdTpPullDownList gets the WMS_ORD_TP code
     * and WMS_ORD_TP_NM for pulldouwn list from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWmsOrdTpPullDownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWmsOrdTpPullDownList", ssmParam);
    }

    /**
     * statement id : getSoList gets the SO List Data from DB
     * @param globalMsg ALGL010SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSOList(NLGL0010SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getSOList", ssmParam, globalMsg.A);
    }

    /**
     * statement id : getSOHdr gets the SO List Data from DB
     * @param globalMsg ALGL010SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSOHdr(NLGL0010SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getSOHdr", ssmParam, globalMsg.B);
    }

    /**
     * statement id : getSODtlAddr to get the SO detail address Data
     * from DB
     * @param globalMsg ALGL010SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSODtlAddr(NLGL0010SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getSODtlAddr", ssmParam, globalMsg.C);
    }

    /**
     * statement id : getSODtlInstn gets the SO detail address Data
     * from DB
     * @param globalMsg ALGL010SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSODtlInstn(NLGL0010SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getSODtlInstn", ssmParam, globalMsg.D);
    }

    /**
     * statement id : getSODtlDtl gets the SO detail Detail Data from
     * DB
     * @param globalMsg ALGL010SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSODtlDtl(NLGL0010SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getSODtlDtl", ssmParam, globalMsg.E);
    }

    /**
     * statement id : getSODtlUpldTrn gets the SO detail Detail Data
     * from DB
     * @param globalMsg ALGL010SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSODtlUpldTrn(NLGL0010SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getSODtlUpldTrn", ssmParam, globalMsg.F);
    }

    /**
     * statement id : getSODtlAsn gets the SO detail ASN Data from DB
     * @param globalMsg ALGL010SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSODtlAsn(NLGL0010SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getSODtlAsn", ssmParam, globalMsg.G);
    }

    /**
     * statement id : getSODtlAsnErr gets the SO ASN Error Detail Data
     * from DB
     * @param globalMsg ALGL010SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSODtlAsnErr(NLGL0010SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getSODtlAsnErr", ssmParam, globalMsg.H);
    }

    /**
     * statement id : getUpdateEdit gets the Update Edit Data from DB
     * @param globalMsg ALGL010SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUpdateEdit(NLGL0010SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getUpdateEdit", ssmParam, globalMsg.O);
    }

    /**
     * statement id : getUpdateEdit gets the Update Edit Data from DB
     * @param tMsg WMS_INBD_SO_HDRTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxSqNumFromMdse(WMS_INBD_SO_HDRTMsg tMsg) {

        return getSsmEZDClient().queryObject("getMaxSqNumFromMdse", tMsg);
    }

    /**
     * statement id : getWHPullDownList gets the WH code and WH name
     * for pulldouwn list from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxSqNumFromMdse(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getMaxSqNumFromMdse", ssmParam);
    }

    /**
     * statement id : getSerFromMdse gets the Merchandise Data
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSerFromMdse(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getSerFromMdse", ssmParam);
    }

    /**
     * statement id : getItemBaseInfo gets Item Data from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemBaseInfo(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getItemBaseInfo", ssmParam);
    }

    /**
     * statement id : getMixPack gets Merchandise Data for MixPack
     * @param globalMsg ALGL010SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMixPack(NLGL0010SMsg globalMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryEZDMsgArray("getMixPack", ssmParam, globalMsg.Z);
    }

    /**
     * statement id : getWmsInbdSoDtlList gets WMS_INBD_SO_DTLTMsg
     * List
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWmsInbdSoDtlList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWmsInbdSoDtlList", ssmParam);
    }

    /**
     * statement id : getWmsInbdSoTextList gets WMS_INBD_SO_TEXTTMsg
     * List
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWmsInbdSoTextList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWmsInbdSoTextList", ssmParam);
    }

    /**
     * statement id : getWmsInbdSoHdr gets WMS_INBD_SO_HDR data and
     * lock the data
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWmsInbdSoHdr(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWmsInbdSoHdr", ssmParam);
    }

    /**
     * statement id : getWmsInbdSoHdr gets WMS_INBD_SO_HDR data and
     * lock the data
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWmsInbdSoHdrWmsOrdTp(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWmsInbdSoHdrWmsOrdTp", ssmParam);
    }

    /**
     * statement id : getRetailWhNm to get the Retail WH name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRetailWhNm(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObject("getRetailWhNm", ssmParam);
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
     * statement id : getWHPullDownList to get the WH code and WH name
     * for pulldouwn list from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPackCdPullDownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPackCdPullDownList", ssmParam);
    }

    /**
     * statement id : get3PLWhCode to get the 3PL WH code and WH name from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult get3PLWhCode(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("get3PLWhCode", ssmParam);
    }

    /**
     * statement id : getShpgOrdKey to get the SHPG_ORD Keys from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShpgOrdKey(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getShpgOrdKey", ssmParam);
    }

    /**
     * statement id : getSoSerPk to get the SO_SER Keys from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSoSerPk(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSoSerPk", ssmParam);
    }

    /**
     * statement id : getShpgOrdMsgPk to get the SHPG_ORD_MSG Keys from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShpgOrdMsgPk(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShpgOrdMsgPk", ssmParam);
    }

    /**
     * statement id : getDsOrdTpProcDfnFlg to get the DS_ORD_TP_PROC_DFN from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsOrdTpProcDfnFlg(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getDsOrdTpProcDfnFlg", ssmParam);
    }

    /**
     * statement id : getOrdProcTpFlg to get the ORD_PROC_TP from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdProcTpFlg(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getOrdProcTpFlg", ssmParam);
    }
    /**
     * statement id : getMdseFlg to get the MDSE from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseFlg(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getMdseFlg", ssmParam);
    }
    /**
     * statement id : getSceOrdTypeSerNumTakeFlg to get the SerNumTakeFlg from SCE_ORD_TYPE
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSceOrdTypeSerNumTakeFlg(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getSceOrdTypeSerNumTakeFlg", ssmParam);
    }

    /**
     * statement id : getMdseSerNumTakeFlg to get the SerNumTakeFlg from MDSE
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseSerNumTakeFlg(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getMdseSerNumTakeFlg", ssmParam);
    }

    /**
     * statement id : getValidSSL to validate SSL
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getValidSSL(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getValidSSL", ssmParam);
    }

    /**
     * statement id : getValid3PLSSLCarrier to validate SSL and Carrier
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getValid3PLSSLCarrier(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getValid3PLSSLCarrier", ssmParam);
    }

    /**
     * statement id : getValidSSLCarrier to validate SSL and Carrier
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getValidSSLCarrier(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getValidSSLCarrier", ssmParam);
    }

    /**
     * statement id : getMaxRowIdOfShpgDrd 
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxRowIdOfShpgDrd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getMaxRowIdOfShpgDrd", ssmParam);
    }

    /**
     * statement id : getMaxRowIdOfShpgDrd 
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShpgOrdKeyFromRowId(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getShpgOrdKeyFromRowId", ssmParam);
    }

    /**
     * statement id : getFlightTermsInfo 
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFlightTermsInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getFlightTermsInfo", ssmParam);
    }

    /**
     * statement id : getShpgOrdCustDtlKey to get the SHPG_ORD_CUST_DTL Keys from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShpgOrdCustDtlKey(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShpgOrdCustDtlKey", ssmParam);
    }

    /**
     * statement id : get3PLWhCode to get the 3PL ShipVia code and name
     * for pulldown from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult get3PLShipVia(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("get3PLShipVia", ssmParam);
    }

    /**
     * statement id : getRelatedSSL to get the Shpg Svc Lvl from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedSSL(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRelatedSSL", ssmParam);
    }

    /**
     * statement id : get3PLRelatedSSLCarrier to get the Shpg Svc Lvl,Carrier from DB
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult get3PLRelatedSSLCarrier(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("get3PLRelatedSSLCarrier", ssmParam);
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
     * statement id : getS80OrdTpCd
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getS80OrdTpCd(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObject("getS80OrdTpCd", ssmParam);
    }
}
