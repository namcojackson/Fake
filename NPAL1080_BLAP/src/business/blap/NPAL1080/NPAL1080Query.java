package business.blap.NPAL1080;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.RTL_WHTMsg;
import business.db.SWHTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS            Yasushi Nomura  Create          N/A
 * 05/06/2016   CSAI            D.Fukaya        Update          QC#7628
 * 11/22/2016   CITS            K.Ogino         Create          QC#8295
 * 12/19/2016   CITS            K.Ogino         Update          QC#15324
 * 12/19/2016   CITS            K.Ogino         Update          QC#15825
 * 31/08/2017   CITS            K.Kameoka       Update          Sol#369(QC#19243)
 * 10/25/2017   CITS            S.Katsuma       Update          QC#21896
 * 08/17/2018   CITS            T.Tokutomi      Update          QC#26581
 * 12/18/2018   CITS            T.Tokutomi      Update          QC#29299
 * 01/24/2019   CITS            K.Ogino         Update          QC#29988
 * 06/09/2022   CITS            A.Cullano       Update          QC#60154
 * 08/03/2023   Hitachi         T.Kuroda        Update          QC#61648
 *</pre>
 */
public final class NPAL1080Query extends S21SsmEZDQuerySupport {
    /** Singleton instance. */
    private static final NPAL1080Query MY_INSTANCE = new NPAL1080Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NPAL1080Query() {
        super();
    }

    /**
     * <pre>
     * Get the NPAL1080Query instance.
     * </pre>
     * @return NPAL1080Query instance
     */
    public static NPAL1080Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Pulldown list of RequisitionType
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRequisitionTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRequisitionTypePulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of LineType
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getLineTypePulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of getRequestReceivingDateAndTimePulldownListByRequisitionType
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRequestReceivingDateAndTimePulldownListByRequisitionType(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRequestReceivingDateAndTimePulldownListByRequisitionType", ssmParam);
    }

    /**
     * Get Pulldown list of SourceType
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSourceTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSourceTypePulldownList", ssmParam);
    }

    /**
     * Find WH code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findWhCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findWhCd", ssmParam);
    }

    /**
     * Find SWH code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findSwhCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findSwhCd", ssmParam);
    }

    /**
     * Find VND code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findVndCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findVndCd", ssmParam);
    }

    /**
     * Find Site code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findSiteCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findSiteCd", ssmParam);
    }

    /**
     * Find PO VND code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findPoVnd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findPoVnd", ssmParam);
    }

    /**
     * Find WH Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findCarrNm(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findCarrNm", ssmParam);
    }

    /**
     * Find CustomerName
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findCustomerName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findCustomerName", ssmParam);
    }

    /**
     * Find RequisitionRecordTypeCode
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRequisitionRecordTypeCode(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findRequisitionRecordTypeCode", ssmParam);
    }

    /**
     * Find TechTocCode
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findTechTocCode(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findTechTocCode", ssmParam);
    }

    /**
     * Find TechTocCode and Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findTechTocCodeAndName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findTechTocCodeAndName", ssmParam);
    }

    /**
     * search
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("search", ssmParam);
    }

    /**
     * checkTechnician
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkTechnician(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkTechnician", ssmParam);
    }

    /**
     * Find findTechWhCode
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findTechWhCode(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findTechWhCode", ssmParam);
    }

    /**
     * Get MDSE
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdse(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getMdse", params);
    }

    /**
     * Get MDSE Name 
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseNm(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObjectList("getMdseNm", params);
    }

    /**
     * Get Parent Vendor Name 
     * @param glblCmpyCd String
     * @param prntVndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrntVndNm(String glblCmpyCd, String prntVndCd) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prntVndCd", prntVndCd);

        return getSsmEZDClient().queryObjectList("getPrntVndNm", params);
    }

    /**
     * Get Warehouse Name 
     * @param glblCmpyCd String
     * @param whCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWhNm(String glblCmpyCd, String whCd) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("whCd", whCd);
        params.put("prtyLocFlgY", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getWhNm", params);
    }

    /**
     * Find Default Warehouse Code from RTL_WH
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findDefaultWh(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findDefaultWh", ssmParam);
    }

    /**
     * Check WH Code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkWhCode(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkWhCode", ssmParam);
    }

    /**
     * SSM Query
     * @param ssmParam Map<String, Object>
     * @param statementId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult execQuery(Map<String, Object> ssmParam, String statementId) {
        return getSsmEZDClient().queryObjectList(statementId, ssmParam);
    }

    /**
     * getDocumentSourceTypeName Add QC#15324
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDocumentSourceTypeName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getDocumentSourceTypeName", ssmParam);
    }

    /**
     * getSplyItemNumFromAsl Add QC#8195
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSplyItemNumFromAsl(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getSplyItemNumFromAsl", ssmParam);
    }

    // START 2017/08/31 K.Kameoka [Sol#369(QC#19243),ADD]
    /**
     * getInsrcCtrlInfo Add QC#8195
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInsrcCtrlInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getInsrcCtrlInfo", ssmParam);
    }
    // END 2017/08/31 K.Kameoka [Sol#369(QC#19243),ADD]


    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTechCustomerWH(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getTechCustomerWH", ssmParam);
    }

    /**
     * isPoDtlCancel
     * QC#26581 Update. QC#29988 Update.
     * @param glblCmpyCd
     * @param prchReqNum
     * @param prchReqLineNum
     * @return boolean
     */
    public boolean isPoDtlCancel(String glblCmpyCd, String prchReqNum, String prchReqLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prchReqNum", prchReqNum);
        params.put("prchReqLineNum", prchReqLineNum);
        params.put("cancel", PO_LINE_STS.CANCELLED);
        params.put("close", PO_LINE_STS.CLOSED);

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("isPoDtlCancel", params);

        if (rs.isCodeNormal()) {
            BigDecimal cnt = (BigDecimal) rs.getResultObject();
            if (BigDecimal.ZERO.compareTo(cnt) == 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * isPrDtlCancel
     * QC#26581 Update. QC#29988 Update.
     * @param glblCmpyCd
     * @param prchReqNum
     * @param prchReqLineNum
     * @return boolean
     */
    public boolean isPrDtlCancel(String glblCmpyCd, String prchReqNum, String prchReqLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prchReqNum", prchReqNum);
        params.put("prchReqLineNum", prchReqLineNum);
        params.put("cancel", PRCH_REQ_LINE_STS.CANCELLED);
        params.put("close", PRCH_REQ_LINE_STS.CLOSED);

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("isPrDtlCancel", params);

        if (rs.isCodeNormal()) {
            BigDecimal cnt = (BigDecimal) rs.getResultObject();
            if (BigDecimal.ZERO.compareTo(cnt) == 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * isRwsDtlCancel
     * QC#26581 Update. QC#29988 Update.
     * @param glblCmpyCd
     * @param prchReqNum
     * @param prchReqLineNum
     * @return boolean
     */
    public boolean isRwsDtlCancel(String glblCmpyCd, String prchReqNum, String prchReqLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prchReqNum", prchReqNum);
        params.put("prchReqLineNum", prchReqLineNum);
        params.put("cancel", RWS_STS.CANCELED);
        params.put("received", RWS_STS.RECEIVED);

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("isRwsDtlCancel", params);

        if (rs.isCodeNormal()) {
            BigDecimal cnt = (BigDecimal) rs.getResultObject();
            if (BigDecimal.ZERO.compareTo(cnt) == 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * isSoDtlCancel QC#26581 Update. QC#29988 Update.
     * @param glblCmpyCd
     * @param prchReqNum
     * @param prchReqLineNum
     * @return boolean
     */
    public boolean isSoDtlCancel(String glblCmpyCd, String prchReqNum, String prchReqLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prchReqNum", prchReqNum);
        params.put("prchReqLineNum", prchReqLineNum);
        params.put("shipped", SHPG_STS.SHIPPED);

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("isSoDtlCancel", params);

        if (rs.isCodeNormal()) {
            BigDecimal cnt = (BigDecimal) rs.getResultObject();
            if (BigDecimal.ZERO.compareTo(cnt) == 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * getPrchReqDtl
     * QC#26662 Update.
     * @param glblCmpyCd
     * @param prchOrdNum
     * @param prchReqLineNum
     * @param prchReqLineSubNum
     * @return PRCH_REQ_DTLTMsg
     */
    public PRCH_REQ_DTLTMsg getPrchReqDtl(String glblCmpyCd, String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {

        PRCH_REQ_DTLTMsg cond = new PRCH_REQ_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cond.prchReqNum, prchReqNum);
        ZYPEZDItemValueSetter.setValue(cond.prchReqLineNum, prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(cond.prchReqLineSubNum, prchReqLineSubNum);

        return (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKey(cond);
    }

    // QC#29299 Add method.
    /**
     * getSourceCondition
     * @param glblCmpyCd String
     * @param prchReqTpCd String
     * @return String source WH (if error, return null)
     */
    public String getSourceCondition(String glblCmpyCd, String prchReqTpCd) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("grpId", "NPZC1170");
        params.put("prchReqTpCd", prchReqTpCd);

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getSourceCondition", params);

        if (rs.isCodeNormal()) {
            return (String) rs.getResultObject();
        } else {
            return null;
        }
    }

    // QC#29299 Add method.
    /**
     * getRtlWH
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return RTL_WHTMsg (if error, return null)
     */
    public RTL_WHTMsg getRtlWH(String glblCmpyCd, String rtlWhCd) {

        RTL_WHTMsg cond = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cond.rtlWhCd, rtlWhCd);

        return (RTL_WHTMsg) EZDTBLAccessor.findByKey(cond);
    }

    // START 2022/06/09 A.Cullano [QC#60154,ADD]
    /**
     * getSwh
     * @param glblCmpyCd String
     * @param rtlSwhCd String
     * @return RTL_WHTMsg (if error, return null)
     */
    public SWHTMsg getSwh(String glblCmpyCd, String rtlSwhCd) {

        SWHTMsg cond = new SWHTMsg();
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cond.rtlSwhCd, rtlSwhCd);

        return (SWHTMsg) EZDTBLAccessor.findByKey(cond);
    }
    // END 2022/06/09 A.Cullano [QC#60154,ADD]

    // START 2023/08/03 T.Kuroda [QC#61648, ADD]
    /**
     * getInvtyLocCd
     * @param glblCmpyCd String
     * @param destRtlWhCd String
     * @param slsDt String
     * @return String EmerSrcInvtyLocCd (if error, return null)
     */
    public String getInvtyLocCd(String glblCmpyCd, String destRtlWhCd, String slsDt) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("destRtlWhCd", destRtlWhCd);
        params.put("slsDt", slsDt);

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getInvtyLocCd", params);

        if (rs.isCodeNormal()) {
            return (String) rs.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * getOpenTrOrdQty
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param srcInvtyLocCd String
     * @return BigDecimal OpenTrOrdQty (if error, return null)
     */
    public BigDecimal getOpenTrOrdQty(String glblCmpyCd, String mdseCd, String srcInvtyLocCd) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("srcInvtyLocCd", srcInvtyLocCd);
        params.put("prchReqApvlFlg", ZYPConstant.FLG_ON_Y);
        params.put("prchReqStsCd", PRCH_REQ_STS.OPEN);
        params.put("prchReqLineStsCd", new String[] {
                PRCH_REQ_LINE_STS.OPEN
                , PRCH_REQ_LINE_STS.AWAITING_SHIPPING
                , PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED
                , PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED});
        params.put("prchReqRelStsCd", new String[] {
                PRCH_REQ_REL_STS.IN_COMPLETED
                , PRCH_REQ_REL_STS.ERROR});

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getOpenTrOrdQty", params);

        if (rs.isCodeNormal()) {
            return (BigDecimal) rs.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * getAvaQty
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param invtyLocCd String
     * @return BigDecimal OpenTrOrdQty (if error, return null)
     */
    public BigDecimal getAvaQty(String glblCmpyCd, String mdseCd, String invtyLocCd) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("invtyLocCd", invtyLocCd);
        params.put("locStsCd", LOC_STS.DC_STOCK);
        params.put("stkStsCd", STK_STS.GOOD);

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getAvaQty", params);

        if (rs.isCodeNormal()) {
            return (BigDecimal) rs.getResultObject();
        } else {
            return null;
        }
    }
    // END 2023/08/03 T.Kuroda [QC#61648, ADD]
}