/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NWAL1500.common.NWAL1500CommonLogic;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/21   Fujitsu         T.Yoshida       Create          n/a
 * 2016/03/07   Fujitsu         M.Nakamura      Update          S21_NA#5000#78
 * 2016/03/15   Fujitsu         S.Takami        Update          S21_NA#4691
 * 2016/07/04   Fujitsu         S.Takami        Update          S21_NA#7645
 * 2016/07/27   Fujitsu         S.Takami        Update          S21_NA#4691-2
 * 2016/09/28   Fujitsu         Y.Taoka         Update          S21_NA#9761
 * 2016/10/05   Fujitsu         S.Takami        Update          S21_NA#7645-3 (Delete getValidateQtyOnDtlLine())
 * 2016/11/09   Fujitsu         S.Takami        Update          S21_NA#15746
 * 2016/11/11   Fujitsu         S.Takami        Update          S21_NA#15746-3
 * 2016/11/30   Fujitsu         S.Ohki          Update          S21_NA#16098
 * 2017/01/27   Fujitsu         R.Nakamura      Update          S21_NA#17257
 * 2017/11/17   Fujitsu         A.Kosai         Update          S21_NA#22252
 * 2017/11/21   Fujitsu         A.Kosai         Update          S21_NA#22555
 * 2018/02/04   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/20   Fujitsu         S.Takami        Update          S21_NA#24842
 * 2018/07/03   Fujitsu         A.Kosai         Update          S21_NA#26908
 * 2020/03/16   Fujitsu         C.Hara          Update          QC#56132
 * </pre>
 */
public final class NWAL1500QueryForLineConfig extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NWAL1500QueryForLineConfig MY_INSTANCE = new NWAL1500QueryForLineConfig();

    //2018/03/08 S21_NA#19808 Add Start
    /**  Cache for getWhInfoWithRsn */
    private Map<Object, S21SsmEZDResult> getWhInfoWithRsnCache = new HashMap<Object, S21SsmEZDResult>();

    /** Cache for getWhInfo */
    private Map<Object, S21SsmEZDResult> getWhInfoCache = new HashMap<Object, S21SsmEZDResult>();

    /** Cache for getMdseInfo */
    private Map<Object, S21SsmEZDResult> getMdseInfoCache = new HashMap<Object, S21SsmEZDResult>();
    //2018/03/08 S21_NA#19808 Add End

    /** Cache for getMdlIdByChildMdseCd */
    private Map<Object, S21SsmEZDResult> getMdlIdByChildMdseCdCache = new HashMap<Object, S21SsmEZDResult>();
    /**
     * Constructor.
     */
    private NWAL1500QueryForLineConfig() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForLineConfig getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get Manufacturer Item Code
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd Merchandise Code
     * @return Manufacturer Item Code
     */
    public S21SsmEZDResult getMnfItemCd(NWAL1500CMsg bizMsg, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mdseCd", mdseCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getMnfItemCd", params);
    }

    /**
     * get Base MDSE Code From Manufacturer Item Code
     * @param bizMsg NWAL1500CMsg
     * @param mnfItemCd Manufacturer Item Code
     * @return Base MDSE Code
     */
    public S21SsmEZDResult getBaseMdseCdFromMnfItemCd(NWAL1500CMsg bizMsg, String mnfItemCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mnfItemCd", mnfItemCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getBaseMdseCdFromMnfItemCd", params); // S21_NA#9761 2016/09/28 MOD
    }

    /**
     * check Exist Order Category
     * @param bizMsg NWAL1500CMsg
     * @param ordCatgCtxTpCd Order Category Context Type Code
     * @param isCatgOnly Order Category Only
     * @return true: exist
     */
    public boolean isExistOrdCatg(NWAL1500CMsg bizMsg, String ordCatgCtxTpCd, boolean isCatgOnly) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
        if (!isCatgOnly) {
            params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
            params.put("dsOrdRsnCd", bizMsg.dsOrdRsnCd.getValue());
        }

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistOrdCatg", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * get Merchandise Infomation
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd Merchandise Code
     * @param isCalledMdl Called Model Flag
     * @return Merchandise Infomation
     */
    public S21SsmEZDResult getMdseInfo(NWAL1500CMsg bizMsg, String mdseCd, boolean isCalledMdl) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mdseCd", mdseCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("isCalledMdl", String.valueOf(isCalledMdl));

        S21SsmEZDResult ssmRslt = getMdseInfoCache.get(params);
        if (ssmRslt == null) {
            ssmRslt = getSsmEZDClient().queryObject("getMdseInfo", params);
            getMdseInfoCache.put(params, ssmRslt);
        }
        return ssmRslt;
    }

    /**
     * get Service Configuration Info
     * @param bizMsg NWAL1500CMsg
     * @param confMsg NWAL1500_ASMsg
     * @return Service Configuration Info
     */
    public S21SsmEZDResult getSvcConfInfo(NWAL1500CMsg bizMsg, NWAL1500_ASMsg confMsg) { // 2018/02/04 S21_NA#19808 Mod

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("configId", confMsg.svcConfigMstrPk_LC.getValue());

        return getSsmEZDClient().queryObjectList("getSvcConfInfo", params);
    }

    // 2017/11/21 S21_NA#22555 Add Start
    /**
     * get Service Configuration Info
     * @param bizMsg NWAL1500CMsg
     * @param confMsg NWAL1500_CSMsg
     * @return Service Configuration Info
     */
    public S21SsmEZDResult getSvcConfInfo(NWAL1500CMsg bizMsg, NWAL1500_CSMsg confMsg) { // 2018/02/04 S21_NA#19808 Mod

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("configId", confMsg.svcConfigMstrPk_RC.getValue());

        return getSsmEZDClient().queryObjectList("getSvcConfInfo", params);
    }
    // 2017/11/21 S21_NA#22555 Add End

    /**
     * check Exist Warehouse
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_BCMsg
     * @return true: exist
     */
    public boolean isExistWh(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("rtlWhNm", lineMsg.rtlWhNm_LL.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistWh", params);

        return 0 < (Integer) result.getResultObject();
    }

    // 2015/11/24 S21_NA#541 Add start
    /**
     * check Exist Warehouse
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_BCMsg
     * @return true: exist
     */
    public boolean isExistWhWithDsOrdTp(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("rgtnStsReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rtlWhNm", lineMsg.rtlWhNm_LL.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistWhWithDsOrdTp", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * check Exist Warehouse
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_BCMsg
     * @return true: exist
     */
    public boolean isExistWhWithOutDsOrdTp(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("rgtnStsReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rtlWhNm", lineMsg.rtlWhNm_LL.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistWhWithOutDsOrdTp", params);

        return 0 < (Integer) result.getResultObject();
    }
    // 2015/11/24 S21_NA#541 Add end

    /**
     * get Warehouse Information
     * @param bizMsg NWAL1500CMsg
     * @param rtlWhNm Warehouse Name
     * @return Warehouse Information
     */
    public S21SsmEZDResult getWhInfo(NWAL1500CMsg bizMsg, String rtlWhNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rtlWhNm", rtlWhNm);

        S21SsmEZDResult ssmRslt = getWhInfoCache.get(params);
        if (ssmRslt == null) {
            ssmRslt = getSsmEZDClient().queryObjectList("getWhInfo", params);
            getWhInfoCache.put(params, ssmRslt);
        }
        return ssmRslt;
    }

    /**
     * get Warehouse Information
     * @param bizMsg NWAL1500CMsg
     * @param rmaLineMsg NWAL1500_DCMsg
     * @return Warehouse Information
     */
    public S21SsmEZDResult getWhInfo(NWAL1500CMsg bizMsg, NWAL1500_DCMsg rmaLineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rtlWhNm", rmaLineMsg.rtlWhNm_RL.getValue());

        return getSsmEZDClient().queryObjectList("getWhInfo", params);
    }

    /**
     * get Warehouse Information With Category
     * @param bizMsg NWAL1500CMsg
     * @param rtlWhNm Warehouse Name
     * @return Warehouse Information
     */
    public S21SsmEZDResult getWhInfoWithRsn(NWAL1500CMsg bizMsg, String rtlWhNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("rtlWhNm", rtlWhNm);

        S21SsmEZDResult ssmRslt = getWhInfoWithRsnCache.get(params);
        if (ssmRslt == null) {
            ssmRslt =  getSsmEZDClient().queryObjectList("getWhInfoWithRsn", params);
            getWhInfoWithRsnCache.put(params, ssmRslt);
        }
        return ssmRslt;
    }

    // 2016/08/25 S21_NA#10321 Add Start
    /**
     * get Warehouse Information With Category Of Perfect Matching
     * @param bizMsg NWAL1500CMsg
     * @param rtlWhNm Warehouse Name
     * @return Warehouse Information
     */
    public S21SsmEZDResult getWhInfoWithRsnOfPerfectMatching(NWAL1500CMsg bizMsg, String rtlWhNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("rtlWhNm", rtlWhNm);

        return getSsmEZDClient().queryObjectList("getWhInfoWithRsnOfPerfectMatching", params);
    }
    // 2016/08/25 S21_NA#10321 Add End

    // 2020/03/16 QC#56132 Add Start
    /**
     * get Warehouse Information With Category Of Perfect Matching for RMA
     * @param bizMsg NWAL1500CMsg
     * @param rtlWhNm Warehouse Name
     * @return Warehouse Information
     */
    public S21SsmEZDResult getWhInfoWithRsnOfPerfectMatchingForRMA(NWAL1500CMsg bizMsg, String rtlWhNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("rtlWhNm", rtlWhNm);

        return getSsmEZDClient().queryObjectList("getWhInfoWithRsnOfPerfectMatchingForRMA", params);
    }
    // 2020/03/16 QC#56132 Add End

    /**
     * get Model ID Info List
     * @param bizMsg NWAL1500CMsg
     * @param confMsg NWAL1500_ACMsg
     * @return Model ID Info List
     */
    public S21SsmEZDResult getMdlIdInfoList(NWAL1500CMsg bizMsg, NWAL1500_ACMsg confMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mdlDescTxt", confMsg.mdlDescTxt_LC.getValue());

        return getSsmEZDClient().queryObjectList("getMdlIdInfoList", params);
    }

    /**
     * get Model Config List
     * @param bizMsg NWAL1500CMsg
     *@param confMsg NWAL1500_ACMsg
     * @return Model Config List
     */
    public S21SsmEZDResult getMdlConfList(NWAL1500CMsg bizMsg, NWAL1500_ACMsg confMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mdlId", confMsg.mdlId_LC.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());

        return getSsmEZDClient().queryObjectList("getMdlConfList", params);
    }

    /**
     * get Bill To Customer Info
     * @param bizMsg NWAL1500CMsg
     * @param custLocNum Customer location Number
     * @return Bill To Customer Info
     */
    public S21SsmEZDResult getBillToCustInfo(NWAL1500CMsg bizMsg, String custLocNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("custCd", custLocNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getBillToCustInfo", params);
    }

    /**
     * get Substitute MDSE Code List
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_BCMsg
     * @return Substitute MDSE Code List
     */
    public S21SsmEZDResult getSbstMdseCdList(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("mdseCd", lineMsg.mdseCd_LL.getValue());
        params.put("sbstMdseCd", lineMsg.sbstMdseCd_LL.getValue());

        return getSsmEZDClient().queryObjectList("getSbstMdseCdList", params);
    }

    /**
     * get Sub Warehouse Information List
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_BCMsg
     * @return Sub Warehouse Information List
     */
    public S21SsmEZDResult getRtlSubWhInfoList(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("rtlWhCd", lineMsg.rtlWhCd_LL.getValue());
        params.put("rtlSwhNm", lineMsg.rtlSwhNm_LL.getValue());

        return getSsmEZDClient().queryObjectList("getRtlSubWhInfoList", params);
    }

    // 2017/11/17 S21_NA#22252 Add Start
    /**
     * get Sub Warehouse Information List
     * @param bizMsg NWAL1500CMsg
     * @param rmaLineMsg NWAL1500_DCMsg
     * @return Sub Warehouse Information List
     */
    public S21SsmEZDResult getRtlSubWhInfoList(NWAL1500CMsg bizMsg, NWAL1500_DCMsg rmaLineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("rtlWhCd", rmaLineMsg.rtlWhCd_RL.getValue());
        params.put("rtlSwhNm", rmaLineMsg.rtlSwhNm_RL.getValue());

        return getSsmEZDClient().queryObjectList("getRtlSubWhInfoList", params);
    }
    // 2017/11/17 S21_NA#22252 Add End

    // Add 2016/03/07 S21_NA#5000#78 Start
    // 2018/02/04 S21_NA#19808 Del Start
//    /**
//     * Get Model Information
//     * @param bizMsg NWAL1500CMsg
//     *@param confMsg NWAL1500_ACMsg
//     * @return Model Information
//     */
//    public S21SsmEZDResult getMdlInfo(NWAL1500CMsg bizMsg, NWAL1500_ACMsg confMsg) {
//
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        params.put("mdlId", confMsg.mdlId_LC.getValue());
//
//        return getSsmEZDClient().queryObject("getMdlInfo", params);
//    }
//    // Add 2016/03/07 S21_NA#5000#78 End
//    // 2017/11/21 S21_NA#22555 Add Start
//    /**
//     * Get Model Information
//     * @param bizMsg NWAL1500CMsg
//     *@param confMsg NWAL1500_CCMsg
//     * @return Model Information
//     */
//    public S21SsmEZDResult getMdlInfo(NWAL1500CMsg bizMsg, NWAL1500_CCMsg confMsg) {
//
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        params.put("mdlId", confMsg.mdlId_RC.getValue());
//
//        return getSsmEZDClient().queryObject("getMdlInfo", params);
//    }
    // 2018/02/04 S21_NA#19808 Del End
    // 2018/02/04 S21_NA#19808 Add Start
    /**
     * Get Model Information
     * @param glblCmpyCd Global Company Code
     * @param mdlId Model ID
     * @return Model Information
     */
    public S21SsmEZDResult getMdlInfo(String glblCmpyCd, BigDecimal mdlId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdlId", mdlId);

        return getSsmEZDClient().queryObject("getMdlInfo", params);
    }
    // 2018/02/04 S21_NA#19808 Add End
    // 2017/11/21 S21_NA#22555 Add End

    // 2016/03/15 S21_NA#4691 Add Start
    /**
     * <pre>
     * Get Inventory available qty
     * @param bizMsg Business Message
     * @param lineMsg Line Config Line Message
     * @return S21SsmEZDResult: result of Query.
     * </pre>
     */
    public S21SsmEZDResult getInvtyAvalQty(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {
        Map<String, String> params  = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
        if (null == mdseTMsg) {
            S21SsmEZDResult rslt = new S21SsmEZDResult();
            rslt.setResultCode(S21SsmEZDResult.RESULT_CODE_NOT_FOUND);
            return rslt;
        }
        params.put("mdseCd", mdseTMsg.mdseCd.getValue());
        String invtyLocCd = lineMsg.rtlWhCd_LL.getValue();
        if (ZYPCommonFunc.hasValue(lineMsg.rtlSwhCd_LL)) {
            invtyLocCd = invtyLocCd + lineMsg.rtlSwhCd_LL.getValue();
        }
        params.put("invtyLocCd", invtyLocCd);
//        params.put("whTp", WH_TP.COMMON); 2016/07/04 S21_NA#7645 Del
//        params.put("rtrnTp", RTRN_TP.STOCK_BALANCE_RETURN);
        params.put("locStsCd", LOC_STS.DC_STOCK);
        params.put("stkStsCd", STK_STS.GOOD);

        return getSsmEZDClient().queryObject("getInvtyAvalQty", params);
    }
    // 2016/03/15 S21_NA#4691 Add End

    // 2016/07/04 S21_NA#7645 Add Start
    /**
     * <pre>
     * Get Validated quantity except target order.
     * @param bizMsg Business Message
     * @param lineMsg Line Config Line Message
     * @return validated Quantity.
     * </pre>
     */
    public BigDecimal getValidatedQty(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {

        Map<String, String> params  = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shpgStsValid", SHPG_STS.VALIDATED);
        params.put("mdseCd", lineMsg.mdseCd_LL.getValue() + "%");
        String invtyLocCd = lineMsg.rtlWhCd_LL.getValue();
        if (ZYPCommonFunc.hasValue(lineMsg.rtlSwhCd_LL)) {
            invtyLocCd = invtyLocCd + lineMsg.rtlSwhCd_LL.getValue();
        }
        params.put("invtyLocCd", invtyLocCd);
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());

        S21SsmEZDResult ssmRslt = getSsmEZDClient().queryObject("getValidatedQty", params);
        if (ssmRslt.isCodeNormal()) {
            return (BigDecimal) ssmRslt.getResultObject();
        } else {
            return BigDecimal.ZERO;
        }
    }
    // 2016/07/04 S21_NA#7645 Add End

    // 2016/10/05 S21_NA#7645-3 Del Start
//    // 2016/07/27 S21_NA#4691-2 Add Start
//    /**
//     * <pre>
//     * get Validated Status order qty from SHPG_PLN.
//     * @param bizMsg Business Message
//     * @param lineMsg line config detail message
//     * @return validated status qty.
//     * <pre>
//     */
//    public BigDecimal getValidateQtyOnDtlLine(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {
//
//        Map<String, String> params  = new HashMap<String, String>();
//        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        params.put("trxHdrNum", bizMsg.cpoOrdNum.getValue());
//        params.put("trxLineNum", lineMsg.cpoDtlLineNum_LL.getValue());
//        params.put("trxLineSubNum", lineMsg.cpoDtlLineSubNum_LL.getValue());
//        params.put("shpgStsValid", SHPG_STS.VALIDATED);
//        params.put("hldRsnCdSoCancel", HLD_RSN.S_OR_O_CANCEL);
//        params.put("flgOffN", ZYPConstant.FLG_OFF_N);
//        params.put("hldLvlShpg", HLD_LVL.SHIPPING_PLAN_LEVEL);
//        params.put("hldLvlCpoDtl", HLD_LVL.CPO_DETAIL_LEVEL);
//        params.put("hldLvlCpo", HLD_LVL.CPO_LEVEL);
//
//        S21SsmEZDResult ssmRslt = getSsmEZDClient().queryObject("getValidateQtyOnDtlLine", params);
//        if (ssmRslt.isCodeNormal()) {
//            return (BigDecimal) ssmRslt.getResultObject();
//        } else {
//            return BigDecimal.ZERO;
//        }
//    }
//    // 2016/07/27 S21_NA#4691-2 Add End
    // 2016/10/05 S21_NA#7645-3 Del End
    // 2016/11/09 S21_NA#15746 Add Start
    /**
     * <pre>
     * Get Related Shell Data count
     * @param glblCmpyCd Global Compnay Code
     * @param cpoOrdNum CPO Order Number
     * @param cpoDtlLineNum CPO Detail Line Number
     * @param cpoDtlLineSubNum CPO Detail Line Sub Number
     * @return related Shell record count.
     * </pre>
     */
    public BigDecimal getRelatedShellDataCount(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("cpoDtlLineNum", cpoDtlLineNum);
        // 2016/11/11 S21_NA#15746-3 Modd Start
        // queryParam.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        if ("000".equals(cpoDtlLineSubNum)) {
            queryParam.remove("cpoDtlLineSubNum");
        } else {
            queryParam.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        }
        // 2016/11/11 S21_NA#15746-3 Mod Start

        BigDecimal rsltCnt = BigDecimal.ZERO;
        S21SsmEZDResult ssmRslt = getSsmEZDClient().queryObjectList("getRelatedShellDataCount", queryParam);
        if (ssmRslt.isCodeNormal()) {
            List<BigDecimal> relatedShellCountList =  (List<BigDecimal>) ssmRslt.getResultObject();
            for (BigDecimal relatedShellCount : relatedShellCountList) {
                rsltCnt = rsltCnt.add(relatedShellCount);
            }
        }
        return rsltCnt;
    }
    // 2016/11/09 S21_NA#15746 Add End

    // 2016/11/30 S21_NA#16098 Add Start
    /**
     * <pre>
     * Get Related Shell Data count From Config
     * @param glblCmpyCd Global Compnay Code
     * @param cpoOrdNum CPO Order Number
     * @param dsOrdPosnNum DS Order Position Number
     * @return related Shell record count.
     * </pre>
     */
    public BigDecimal getRelatedShellDataCountFromConfig(String glblCmpyCd, String cpoOrdNum, String dsOrdPosnNum) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("dsOrdPosnNum", dsOrdPosnNum);

        BigDecimal rsltCnt = BigDecimal.ZERO;
        S21SsmEZDResult ssmRslt = getSsmEZDClient().queryObjectList("getRelatedShellDataCountFromConfig", queryParam);
        if (ssmRslt.isCodeNormal()) {
            List<BigDecimal> relatedShellCountList =  (List<BigDecimal>) ssmRslt.getResultObject();
            for (BigDecimal relatedShellCount : relatedShellCountList) {
                rsltCnt = rsltCnt.add(relatedShellCount);
            }
        }
        return rsltCnt;
    }
    // 2016/11/30 S21_NA#16098 Add End

    // Add Start 2017/01/27 QC#17257
    /**
     * <pre>
     * Is Order Retail Equipment
     * @param glblCmpyCd Global Compnay Code
     * @param dsOrdCatgCd DS Order Category Code
     * @param dsOrdTpCd DS Order Type Code
     * @param dsOrdRsnCd DS Order Reason Code
     * @return S21SsmEZDResult: compare result of Query.
     * </pre>
     */
    public boolean isOrderRetailEquipment(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);
        params.put("dsOrdCatgCd", dsOrdCatgCd);
        params.put("dsOrdTpCd", dsOrdTpCd);
        params.put("dsOrdRsnCd", dsOrdRsnCd);

        S21SsmEZDResult ssmRslt = getSsmEZDClient().queryObject("isExistOrdCatg", params);

        int rsltCnt = (Integer) ssmRslt.getResultObject();

        return rsltCnt > 0;
    }

    /**
     * <pre>
     * Get Base Compornent Flag
     * @param glblCmpyCd Global Compnay Code
     * @param mdseCd Merchandise Code
     * @return S21SsmEZDResult: result of Query.
     * </pre>
     */
    public S21SsmEZDResult getBaseCmptFlg(String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("mdseTpCtxTpCd", "MAIN_MACH");

        return getSsmEZDClient().queryObject("getBaseComponentFlag", ssmParam);
    }
    // Add End 2017/01/27 QC#17257

    // 2018/03/20 S21_NA#24842 Add Start
    /**
     * <pre>
     * Get Modl Id Data List by child merchandise code
     * @param glblCmpyCd Global Company Code
     * @param mdseCd Child Merchandise Code of Model
     * @param slsDt Sales Date
     * @return S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getMdlIdByChildMdseCd(String glblCmpyCd, String mdseCd, String slsDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        int ordTakeSize = new ORD_TAKE_MDSETMsg().getAttr("ordTakeMdseCd").getDigit();
        String ordTakeMdseCd = mdseCd;
        if (mdseCd.length() > ordTakeSize) {
            ordTakeMdseCd = mdseCd.substring(0, ordTakeSize);
        }
        ssmParam.put("ordTakeMdseCd", ordTakeMdseCd);
        ssmParam.put("slsDt", slsDt);

        S21SsmEZDResult rslt = getMdlIdByChildMdseCdCache.get(ssmParam);
        if (rslt == null) {
            rslt = getSsmEZDClient().queryObjectList("getMdlIdByChildMdseCd", ssmParam);
        }
        return rslt;
    }
    // 2018/03/20 S21_NA#24842 Add End
    // 2018/07/03 S21_NA#26908 Add Start
    /**
     * <pre>
     * Get Import Order Detail
     * @param glblCmpyCd Global Company Code
     * @param dsImptOrdPk Import Order Primary Key
     * @param dsOrdPosnNum Order Position Number
     * @param ordSrcRefLineNum Order Source Reference Line Number
     * @param ordSrcRefLineSubNum Order Source Reference Line Sub Number
     * @return Import Order Detail Primary Key
     * </pre>
     */
    public BigDecimal getImptOrdDtlPk(String glblCmpyCd, BigDecimal dsImptOrdPk, String dsOrdPosnNum, String ordSrcRefLineNum, String ordSrcRefLineSubNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsImptOrdPk", dsImptOrdPk);
        ssmParam.put("dsOrdPosnNum", dsOrdPosnNum);
        ssmParam.put("ordSrcRefLineNum", ordSrcRefLineNum);
        ssmParam.put("ordSrcRefLineSubNum", ordSrcRefLineSubNum);

        S21SsmEZDResult ssmRslt = getSsmEZDClient().queryObject("getImptOrdDtlPk", ssmParam);
        if (ssmRslt.isCodeNormal()) {
            return (BigDecimal) ssmRslt.getResultObject();
        } else {
            return null;
        }
    }
    // 2018/07/03 S21_NA#26908 Add End
}
