/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6820;

import static business.blap.NMAL6820.constant.NMAL6820Constant.DB_PARAM_CMSG;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMZC601001_SUFFIX_FOR_RTRN_TO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_XREF_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NMAL6820 Warehouse Setup
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC#2380
 * 02/11/2016   CSAI            D.Fukaya        Update          QC#1598
 * 02/16/2016   CSAI            D.Fukaya        Update          QC#2368
 * 02/18/2016   CSAI            D.Fukaya        Update          QC#3436
 * 02/22/2016   CSAI            D.Fukaya        Update          QC#2369
 * 04/25/2016   CSAI            D.Fukaya        Update          QC#6406
 * 08/03/2016   CITS            S.Endo          Update          QC#10838
 * 10/25/2016   CITS            Y.IWASAKI       Update          QC#15120
 * 01/19/2018   CITS            T.Tokutomi      Update          QC#21852
 *</pre>
 */
public final class NMAL6820Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6820Query MY_INSTANCE = new NMAL6820Query();

    /**
     * Constructor.
     */
    private NMAL6820Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL0020Query
     */
    public static NMAL6820Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWhCatgPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWhCatgPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyAcctPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getInvtyAcctPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyOwnrPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getInvtyOwnrPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTmZonePulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getTmZonePulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWhOwnPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWhOwnPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWhSysTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWhSysTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReSetupWhOwnPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getReSetupWhOwnPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReSetupWhSysTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getReSetupWhSysTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoRcptRteTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getPoRcptRteTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRmaRcptRteTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getRmaRcptRteTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCntyPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getShipToCntyPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCtryPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getShipToCtryPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtrnToCntyPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getRtrnToCntyPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtrnToCtryPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getRtrnToCtryPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPlnItemInsrcPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getPlnItemInsrcPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSrcZnPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getSrcZnPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefSrcProcrTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getDefSrcProcrTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTchEmrProcrTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getTchEmrProcrTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefRtrnToProcrTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getRtrnToDefSrcProcrTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getGndSvcLvlPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getGndSvcLvlPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFirstOvngtSvcLvlPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getFirstOvngtSvcLvlPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getScdOvngtSvcLvlPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getScdOvngtSvcLvlPulldownList", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getRtlWh" in [NMAL6820Query.xml]
     * </pre>
     * @param cMsg NMAL6820CMsg
     * @param sMsg NMAL6820SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWh(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put("procrTp_Supplier", PROCR_TP.SUPPLIER);
        ssmParam.put("vndXrefTp_CSA_TO_CUSA", VND_XREF_TP.CSA_TO_CUSA);
        ssmParam.put("vndXrefTp_CSA_TO_CUSA_RETURN_TO", VND_XREF_TP.CSA_TO_CUSA_RETURN_TO);

        return getSsmEZDClient().queryEZDMsg("getRtlWh", ssmParam, sMsg);
    }

    /**
     * <pre>
     * execute SSM id="getRtlSwh" in [NMAL6820Query.xml]
     * </pre>
     * @param cMsg NMAL6820CMsg
     * @param sMsg NMAL6820SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlSwh(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put("swhDsblFlgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("swhDsblFlgN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("flgOn", ZYPConstant.FLG_ON_Y);
        ssmParam.put("procrTp_Supplier", PROCR_TP.SUPPLIER);

        return getSsmEZDClient().queryEZDMsgArray("getRtlSwh", ssmParam, sMsg.A);
    }

    /**
     * <pre>
     * execute SSM id="getSwhWhCatgRel" in [NMAL6820Query.xml]
     * </pre>
     * @param cMsg NMAL6820CMsg
     * @param sMsg NMAL6820SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSwhWhCatgRel(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put("swhDsblFlgN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("ProcrTp_Supplier", PROCR_TP.SUPPLIER);

        return getSsmEZDClient().queryEZDMsgArray("getSwhWhCatgRel", ssmParam, sMsg.C);
    }

    /**
     * <pre>
     * execute SSM id="getAltOwnrNm" in [NMAL6820Query.xml]
     * </pre>
     * @param cMsg NMAL6820CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAltOwnrNm(NMAL6820CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_CMSG, cMsg);

        return getSsmEZDClient().queryEZDMsg("getAltOwnrNm", ssmParam, cMsg);
    }

    /**
     * <pre>
     * execute SSM id="getMgrNm" in [NMAL6820Query.xml]
     * </pre>
     * @param cMsg NMAL6820CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMgrNm(NMAL6820CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_CMSG, cMsg);

        return getSsmEZDClient().queryEZDMsg("getMgrNm", ssmParam, cMsg);
    }

    /**
     * <pre>
     * execute SSM id="getInvtyMstr" in [NMAL6820Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyMstr(String glblCmpyCd, String invtyLocCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invtyLocCd", invtyLocCd);

        return getSsmEZDClient().queryObject("getInvtyMstr", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getImportInvoiceConsigneeByPartyCode" in [NMAL6820Query.xml]
     * </pre>
     * @param globalCompanyCode String
     * @param ptyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getImportInvoiceConsigneeByPartyCode(String globalCompanyCode, String ptyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("globalCompanyCode", globalCompanyCode);
        ssmParam.put("ptyCd", ptyCd);

        return getSsmEZDClient().queryObjectList("getImportInvoiceConsigneeByPartyCode", ssmParam);
    }

    /**
     * <p>
     * Search WH and Sub WH.
     * </p>
     * @param cMsg CMsg
     * @param whName whName
     * @param swhName swhName
     * @return search results
     */
    public S21SsmEZDResult searchWhSwh(NMAL6820CMsg cMsg, EZDCStringItem whName, EZDCStringItem swhName) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        params.put("rtlWhNm", whName);
        params.put("rtlSwhNm", swhName);

        return getSsmEZDClient().queryObjectList("searchWhSwh", params);
    }

    /**
     * <p>
     * Search Supplier
     * </p>
     * @param cMsg CMsg
     * @param prntVndNm prntVndNm
     * @param vndNm vndNm
     * @return search results
     */
    public S21SsmEZDResult searchSupplier(NMAL6820CMsg cMsg, EZDCStringItem prntVndNm, EZDCStringItem vndNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        params.put("prntVndNm", prntVndNm);
        params.put("vndNm", vndNm);
        params.put("vndTp_Supplier", VND_TP.SUPPLIER);
        params.put("rgtnSts_ReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("searchSupplier", params);
    }

    /**
     * <pre>
     * execute SSM id="getPR" in [NMAL6820Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPR(String glblCmpyCd, String rtlWhCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("prchReqLineSts_Cancelled", PRCH_REQ_LINE_STS.CANCELLED);

        return getSsmEZDClient().queryObject("getPR", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getPO" in [NMAL6820Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPO(String glblCmpyCd, String rtlWhCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("poHdrSts_Cancelled", PO_HDR_STS.CANCELLED);

        return getSsmEZDClient().queryObject("getPO", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getRWS" in [NMAL6820Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRWS(String glblCmpyCd, String rtlWhCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("rwsSts_Cancelled", RWS_STS.CANCELED);

        return getSsmEZDClient().queryObject("getRWS", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getInvty" in [NMAL6820Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvty(String glblCmpyCd, String rtlWhCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);

        return getSsmEZDClient().queryObject("getInvty", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getDefDsAcctInfo" in [NMAL6820Query.xml]
     * </pre>
     * @param cMsg NMAL6820CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefDsAcctInfo(NMAL6820CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        return getSsmEZDClient().queryObjectList("getDefDsAcctInfo", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getShipToCustInfo" in [NMAL6820Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param locNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCustInfo(String glblCmpyCd, String locNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", locNum);
        return getSsmEZDClient().queryObjectList("getShipToCustInfo", ssmParam);
    }

    /**
     * <pre>
     * Get Suffix for Return-To Code
     * </pre>
     * @param cMsg CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSuffixForRtrnTo(NMAL6820CMsg cMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        queryParam.put("varCharConstNm", NMZC601001_SUFFIX_FOR_RTRN_TO);
        return getSsmEZDClient().queryObject("getSuffixForRtrnTo", queryParam);
    }

    /**
     * <pre>
     * Get Ship-To Cust Code by Loc Num
     * </pre>
     * @param glblCmpyCd glblCmpyCd
     * @param locNum locNum
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCustCdByLocNum(String glblCmpyCd, String locNum) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("locNum", locNum);
        return getSsmEZDClient().queryObject("getShipToCustCdByLocNum", queryParam);
    }

    /**
     * getAddrInfoShipTo
     * @param cMsg cMsg
     * @param globalCompanyCode globalCompanyCode
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddrInfoShipTo(NMAL6820CMsg cMsg, String globalCompanyCode) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", globalCompanyCode);
        params.put("stCd", cMsg.stCd_S1.getValue());
        params.put("cntyNm", cMsg.cntyNm_S1);

        return getSsmEZDClient().queryObject("getAddrInfo", params);
    }

    /**
     * getAddrInfoReturnTo
     * @param cMsg cMsg
     * @param globalCompanyCode globalCompanyCode
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddrInfoReturnTo(NMAL6820CMsg cMsg, String globalCompanyCode) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", globalCompanyCode);
        params.put("stCd", cMsg.rtrnToStCd_R1.getValue());
        params.put("cntyNm", cMsg.cntyNm_R1);

        return getSsmEZDClient().queryObject("getAddrInfo", params);
    }

    /**
     * getAddrByPost
     * @param glblCmpyCd String
     * @param postCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddrByPost(String glblCmpyCd, String postCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("postCd", postCd);

        return getSsmEZDClient().queryObjectList("getAddrByPost", params);
    }

    /**
     * getAddrByPost
     * @param glblCmpyCd String
     * @param cntyPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCntyNm(String glblCmpyCd, BigDecimal cntyPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cntyPk", cntyPk);

        return getSsmEZDClient().queryObject("getCntyNm", params);
    }

    /**
     * 
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param invtyOwnrCd String
     * @param wmsWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countRtlWhByWmsWhCd(String glblCmpyCd, String rtlWhCd, String invtyOwnrCd, String wmsWhCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rtlWhCd);
        params.put("invtyOwnrCd", invtyOwnrCd);
        params.put("wmsWhCd", wmsWhCd);

        return getSsmEZDClient().queryObject("countRtlWhByWmsWhCd", params);
    }

    /**
     * 
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param invtyOwnrCd String
     * @param physWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countRtlWhByPhysWhCd(String glblCmpyCd, String rtlWhCd, String invtyOwnrCd, String physWhCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rtlWhCd);
        params.put("invtyOwnrCd", invtyOwnrCd);
        params.put("physWhCd", physWhCd);

        return getSsmEZDClient().queryObject("countRtlWhByPhysWhCd", params);
    }
    /**
     * getPrtVndNm
     * @param glblCmpyCd
     * @param prtVndCd
     * @return
     */
    public S21SsmEZDResult getPrtVndNm(String glblCmpyCd, String prtVndCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prntVndCd", prtVndCd);
        params.put("salesDate", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObject("getPrtVndNm", params);
    }
    /**
     * getVndNm
     * @param glblCmpyCd
     * @param vndCd
     * @return
     */
    public S21SsmEZDResult getVndNm(String glblCmpyCd, String vndCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("vndCd", vndCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("salesDate", ZYPDateUtil.getSalesDate());
        return getSsmEZDClient().queryObject("getVndNm", params);
    }
    public S21SsmEZDResult getVendorInfo(NMAL6820CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd_G1);
        params.put("vndCd", cMsg.vndCd.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getVendorInfo", params);
    }

    /**
     * getStCdList
     * QC#21852 Add method.
     * @param glblCmpyCd
     * @return List state code
     */
    public List<String> getStCdList(String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getStCdList", params);

        if (result.isCodeNormal()) {
            return (List<String>) result.getResultObject();
        } else {
            return new ArrayList<String>();
        }
    }

    /**
     * getShpgModeCdList
     * QC#21852 Add method.
     * @param glblCmpyCd
     * @return List ship mode code
     */
    public List<String> getShpgModeCdList(String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getShpgModeCdList", params);

        if (result.isCodeNormal()) {
            return (List<String>) result.getResultObject();
        } else {
            return new ArrayList<String>();
        }
    }

    /**
     * getSvcLvlCdList
     * QC#21852 Add method.
     * @param glblCmpyCd
     * @return List shipping service level code
     */
    public List<String> getShpgSvcLvlCdList(String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getShpgSvcLvlCdList", params);

        if (result.isCodeNormal()) {
            return (List<String>) result.getResultObject();
        } else {
            return new ArrayList<String>();
        }
    }
}
