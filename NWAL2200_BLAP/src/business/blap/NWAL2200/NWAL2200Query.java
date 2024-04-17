/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2200;

import static business.blap.NWAL2200.constant.NWAL2200Constant.BIZ_ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL2200Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2016/08/31   CITS            S.Tanikawa      Update          Unit Test#202
 * 2016/09/16   Fujitsu         S.Ohki          Update          S21_NA#12145
 * 2017/03/09   Fujitsu         S.Ohki          Update          S21_NA#16790
 * 2017/06/06   Fujitsu         R.Nakamura      Update          S21_NA#18583
 * 2017/08/23   Fujitsu         S.Iidaka        Update          S21_NA#20097
 * 2017/09/15   Fujitsu         R.Nakamura      Update          S21_NA#21118
 * 2017/11/28   Fujitsu         M.Ohno          Update          S21_NA#22782
 * 2018/01/23   Fujitsu         T.Aoi           Update          S21_NA#18798
 * 2018/03/15   Fujitsu         S.Ohki          Update          S21_NA#20153
 * 2018/03/20   Fujitsu         A.Kosai         Update          S21_NA#24840
 * 2018/11/05   Fujitsu         Y.Kanefusa      Update          S21_NA#27364
 * 2019/03/26   Fujitsu         K.Ishizuka      Update          S21_NA#30924
 * 2019/03/28   Fujitsu         K.Ishizuka      Update          S21_NA#30765  
 * 2021/03/02   CITS            J.Evangelista   Update          QC#57824   
 *</pre>
 */
public final class NWAL2200Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL2200Query MY_INSTANCE = new NWAL2200Query();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());


    /**
     * Private constructor
     */
    private NWAL2200Query() {
        super();
    }

    /**
     * Get the NWAL2200Query instance.
     * @return NWAL2200Query instance
     */
    public static NWAL2200Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * get ORD_LINE_SRC data (All Column)
     * </pre>
     * @param queryParam query condition glblCmpyCd: Mandatory
     * ordLineSrcCatgCd: option srcPrntVndCd: option
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getOrdLineSrcDataList(Map<String, String> queryParam) {
        return getSsmEZDClient().queryObjectList("getOrdLineSrcDataList", queryParam);
    }

    /**
     * getDsImptOrdViewInfo
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getDsImptOrdViewInfo(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getDsImptOrdViewInfo", queryParam);
    }

    /**
     * getDsImptOrdConfigInfo
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getDsImptOrdConfigInfo(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getDsImptOrdConfigInfo", queryParam);
    }

    /**
     * getDsImptOrdDtlInfo
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getDsImptOrdDtlInfo(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getDsImptOrdDtlInfo", queryParam);
    }

    /**
     * getDsImptOrdRtrnDtlInfo
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getDsImptOrdRtrnDtlInfo(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getDsImptOrdRtrnDtlInfo", queryParam);
    }

    /**
     * getDsImptOrdSlsCrInfo
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getDsImptOrdSlsCrInfo(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getDsImptOrdSlsCrInfo", queryParam);
    }

    /**
     * getDsImptPrcCalcBaseInfo
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getDsImptPrcCalcBaseInfo(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getDsImptPrcCalcBaseInfo", queryParam);
    }

    /**
     * getDsImptOrdErrInfo
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getDsImptOrdErrInfo(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getDsImptOrdErrInfo", queryParam);
    }

    /**
     * getSlsRepEmlAddr
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getSlsRepEmlAddr(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getSlsRepEmlAddr", queryParam);
    }

    /**
     * getSlsRepBranchAdminEmlAddr
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getSlsRepBranchAdminEmlAddr(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getSlsRepBranchAdminEmlAddr", queryParam);
    }

    /**
     * getSlsAdminEmlAddr
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getSlsAdminEmlAddr(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getSlsAdminEmlAddr", queryParam);
    }

    /**
     * getCoaMdseTpList
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getCoaMdseTpList(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getCoaMdseTpList", queryParam);
    }

    /**
     * get DS Order Category Code List
     * @param bizMsg NWAL2200CMsg
     * @return DS Order Category Code List
     */
    public S21SsmEZDResult getDsOrdCatgCdList(NWAL2200CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgDescTxt", bizMsg.dsOrdCatgDescTxt.getValue());
        params.put("bizId", BIZ_ID);

        return getSsmEZDClient().queryObjectList("getDsOrdCatgCdList", params);
    }

    /**
     * get DS Order Type Code
     * @param bizMsg NWAL2200CMsg
     * @return DS Order Type Code
     */
    public S21SsmEZDResult getDsOrdTpCd(NWAL2200CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());

        return getSsmEZDClient().queryObject("getDsOrdTpCd", params);
    }

    /**
     * get Customer And Additional Data
     * @param bizMsg NWAL2200CMsg
     * @param effDt Effective Date (YYYYMMDD)
     * @return Customer And Additional Data
     */
    public S21SsmEZDResult getCustAddlInfo(NWAL2200CMsg bizMsg, String effDt) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("effDt", effDt);
        params.put("rgtnSts", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getCustAddlInfo", params);
    }

    /**
     * get DS Order Type List
     * @param bizMsg NWAL2200CMsg
     * @return DS Order Type List
     */
    public S21SsmEZDResult getDsOrdTpList(NWAL2200CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
        // START 2021/03/02 J.Evangelista [QC#57824,ADD]
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        // END   2021/03/02 J.Evangelista [QC#57824,ADD]

        return getSsmEZDClient().queryObjectList("getDsOrdTpList", params);
    }

    /**
     * get DS Order Reason List
     * @param bizMsg NWAL2200CMsg
     * @param effDt Effective Date (YYYYMMDD)
     * @return DS Order Reason List
     */
    public S21SsmEZDResult getDsOrdRsnList(NWAL2200CMsg bizMsg, String effDt) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("effDt", effDt);

        return getSsmEZDClient().queryObjectList("getDsOrdRsnList", params);
    }

    /**
     * get DS Order Line Category List
     * @param bizMsg NWAL2200CMsg
     * @param effDt Effective Date (YYYYMMDD)
     * @param dsOrdLineDrctnCd DS Order Direction Code
     * @return DS Order Line Category List
     */
    public S21SsmEZDResult getDsOrdLineCatgList(NWAL2200CMsg bizMsg, String effDt, String dsOrdLineDrctnCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("effDt", effDt);
        params.put("dsOrdLineDrctnCd", dsOrdLineDrctnCd);

        return getSsmEZDClient().queryObjectList("getDsOrdLineCatgList", params);
    }

    /**
     * get Bill To Customer Address
     * @param bizMsg NWAL2200CMsg
     * @param billToCustCd Bill To Customer Code
     * @return Bill To Customer Address
     */
    public S21SsmEZDResult getBillToCustAddr(NWAL2200CMsg bizMsg, String billToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("billToCustCd", billToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getBillToCustAddr", params);
    }

    /**
     * get Ship To Customer Address
     * @param bizMsg NWAL2200CMsg
     * @param shipToCustCd Ship To Customer Code
     * @return Ship To Customer Address
     */
    public S21SsmEZDResult getShipToCustAddr(NWAL2200CMsg bizMsg, String shipToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", shipToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getShipToCustAddr", params);
    }

    /**
     * get Bill To Customer Info
     * @param bizMsg NWAL2200CMsg
     * @param custLocNum Customer location Number
     * @return Bill To Customer Info
     */
    public S21SsmEZDResult getBillToCustInfo(NWAL2200CMsg bizMsg, String custLocNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("custCd", custLocNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getBillToCustInfo", params);
    }

    /**
     * get Bill To Customer Info List
     * @param bizMsg NWAL2200CMsg
     * @param custLocNum Customer location Number
     * @return Bill To Customer Info List
     */
    public S21SsmEZDResult getBillToCustInfoList(NWAL2200CMsg bizMsg, String custLocNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("custCd", custLocNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getBillToCustInfoList", params);
    }

    /**
     * get Ship To Customer Info List
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return Ship To Customer Info List
     */
    public S21SsmEZDResult getShipToCustInfoList(String glblCmpyCd, String shipToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        // Mod Start 2017/09/15 QC#21118
//        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        params.put("shipToCustCd", bizMsg.shipToCustCd.getValue());
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("shipToCustCd", shipToCustCd);
        // Mod End 2017/09/15 QC#21118
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getShipToCustInfoList", params);
    }

    // 09/16/2016 S21_NA#14393 add Start
    /**
     * get Ship To Customer Info List
     * @param bizMsg NWAL2200CMsg
     * @param shipToCustCd String
     * @return Ship To Customer Info List
     */
    public S21SsmEZDResult getShipToCustInfoList(NWAL2200CMsg bizMsg, String shipToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", shipToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getShipToCustInfoList", params);
    }
    // 09/16/2016 S21_NA#14393 add End

    /**
     * Get Payment Term Cash Discount Code
     * @param bizMsg NWAL2200CMsg
     * @return Payment Term Cash Discount Code
     */
    public S21SsmEZDResult getPmtTermCashDiscCd(NWAL2200CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("billToCustCd", bizMsg.billToCustCd.getValue());

        return getSsmEZDClient().queryObject("getPmtTermCashDiscCd", params);
    }

    /**
     * get Primary Sales Rep Info
     * @param bizMsg NWAL2200CMsg
     * @param primRepTocCd Primary Rep TOC Code
     * @return Primary Sales Rep Info
     */
    public S21SsmEZDResult getPrimSlsRepInfo(NWAL2200CMsg bizMsg, String primRepTocCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("primRepTocCd", primRepTocCd);
        params.put("rgtnStsCd", RGTN_STS.TERMINATED);

        return getSsmEZDClient().queryObject("getPrimSlsRepInfo", params);
    }

    /**
     * get Carrier SerVice Level Description Text
     * @param bizMsg NWAL2200CMsg
     * @param carrCd Carrier Code
     * @return Carrier SerVice Level Description Text
     */
    public S21SsmEZDResult getCarrSvcLvlDescTxt(NWAL2200CMsg bizMsg, String carrCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shpgSvcLvlCd", bizMsg.shpgSvcLvlCd.getValue());
        params.put("carrCd", carrCd);

        return getSsmEZDClient().queryObject("getCarrSvcLvlDescTxt", params);
    }

    /**
     * check Exist Warehouse
     * @param bizMsg NWAL2200CMsg
     * @param lineMsg NWAL2200_BCMsg
     * @return true: exist
     */
    public boolean isExistWhWithDsOrdTp(NWAL2200CMsg bizMsg, NWAL2200_BCMsg lineMsg) {

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
     * @param bizMsg NWAL2200CMsg
     * @param lineMsg NWAL2200_BCMsg
     * @return true: exist
     */
    public boolean isExistWhWithOutDsOrdTp(NWAL2200CMsg bizMsg, NWAL2200_BCMsg lineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("rgtnStsReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rtlWhNm", lineMsg.rtlWhNm_LL.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistWhWithOutDsOrdTp", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * get Warehouse Information With Category
     * @param bizMsg NWAL2200CMsg
     * @param rmaLineMsg NWAL2200_DCMsg
     * @return Warehouse Information
     */
    public S21SsmEZDResult getWhInfoWithRsn(NWAL2200CMsg bizMsg, NWAL2200_DCMsg rmaLineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("rtlWhNm", rmaLineMsg.rtlWhNm_RL.getValue());

        return getSsmEZDClient().queryObjectList("getWhInfoWithRsn", params);
    }

    /**
     * get Warehouse Information
     * @param bizMsg NWAL2200CMsg
     * @param rmaLineMsg NWAL2200_DCMsg
     * @return Warehouse Information
     */
    public S21SsmEZDResult getWhInfo(NWAL2200CMsg bizMsg, NWAL2200_DCMsg rmaLineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rtlWhNm", rmaLineMsg.rtlWhNm_RL.getValue());

        return getSsmEZDClient().queryObjectList("getWhInfo", params);
    }

    /**
     * checkPriceCatgNm
     * @param bizMsg NWAL2200CMsg
     * @param prcCatgNm String
     * @return PriceCatgNm
     */
    public S21SsmEZDResult checkPriceCatgNm(NWAL2200CMsg bizMsg, String prcCatgNm) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("prcCatgNm", prcCatgNm);
        return getSsmEZDClient().queryObjectList("checkPriceCatgNm", queryParam);
    }

    /**
     * getSlsRepInfoList
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getSlsRepInfoList(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getSlsRepInfoList", queryParam);
    }

    /**
     * getMdseInfo
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getMdseInfo(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getMdseInfo", queryParam);
    }

    /**
     * check Exist Order Category
     * @param bizMsg NWAL2200CMsg
     * @param ordCatgCtxTpCd Order Category Context Type Code
     * @param isCatgOnly Order Category Only
     * @return true: exist
     */
    public boolean isExistOrdCatg(NWAL2200CMsg bizMsg, String ordCatgCtxTpCd, boolean isCatgOnly) {

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
     * get Base MDSE Code From Manufacturer Item Code
     * @param bizMsg NWAL2200CMsg
     * @param mnfItemCd Manufacturer Item Code
     * @return Base MDSE Code
     */
    public S21SsmEZDResult getBaseMdseCdFromMnfItemCd(NWAL2200CMsg bizMsg, String mnfItemCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mnfItemCd", mnfItemCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getBaseMdseCdFromMnfItemCd", params);
    }

    // -------------------------------------------------------------------------
    // for CUSA retail
    // -------------------------------------------------------------------------

    /**
     * getCsaOrdHdr
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getCsaOrdHdr(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getCsaOrdHdr", queryParam);
    }

    /**
     * getCsaOrdItem
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getCsaOrdItem(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getCsaOrdItem", queryParam);
    }

    /**
     * getFndgDlrCompAmt
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getFndgDlrCompAmt(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getFndgDlrCompAmt", queryParam);
    }

    /**
     * getSlsRepTocCdByNameAndResourceId
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getSlsRepTocCdByNameAndResourceId(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getSlsRepTocCdByNameAndResourceId", queryParam);
    }

    /**
     * getFrtCondCdByName
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getFrtCondCdByName(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getFrtCondCdByName", queryParam);
    }

    /**
     * getCarrSvcLvlCdByName
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getCarrSvcLvlCdByName(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getCarrSvcLvlCdByName", queryParam);
    }

    /**
     * getPmtTermCashDiscCdByName
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getPmtTermCashDiscCdByName(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getPmtTermCashDiscCdByName", queryParam);
    }

    /**
     * getRtlWhCdByName
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getRtlWhCdByName(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getRtlWhCdByName", queryParam);
    }

    /**
     * getRtlSwhCdByName
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getRtlSwhCdByName(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getRtlSwhCdByName", queryParam);
    }

    /**
     * getDsImptOrdDelyInfo
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object (List<Map<String,
     * Object>))
     */
    public S21SsmEZDResult getDsImptOrdDelyInfo(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getDsImptOrdDelyInfo", queryParam);
    }

    // UPDATE START 2016/08/31 Unit Test#202
    /**
     * isProspect
     * @param bizMsg NWAL2200CMsg
     * @param dsAcctNum String
     * @return boolean true when Account is Prospect
     */
    public boolean isProspect(NWAL2200CMsg bizMsg, String dsAcctNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsAcctNum", dsAcctNum);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getDsAcctProsCnt", params);

        return 0 < (Integer) result.getResultObject();
    }
 // UPDATE END 2016/08/31 Unit Test#202

    // 09/16/2016 S21_NA#12145 add Start
    /**
     * isItrlOrdProcFlgOn
     * @param bizMsg NWAL2200CMsg
     * @param dsAcctNum String
     * @return boolean true when Internal Order Process Flag Y
     */
    public boolean isItrlOrdProcFlgOn(NWAL2200CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getItrlOrdProcFlg", params);
        String itrlOrdProcFlg = (String) result.getResultObject();

        if (ZYPConstant.FLG_ON_Y.equals(itrlOrdProcFlg)) {
            return true;
        }
        return false;
    }
    // 09/16/2016 S21_NA#12145 add Start
    // QC#13688 2017/02/24 Add Start
    /**
     * get Freight Term Information List
     * @param bizMsg NWAL2200CMsg
     * @return Freight Term Information List
     */
    public S21SsmEZDResult getFreightTermInfoList(NWAL2200CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("frtCondDescTxt", bizMsg.frtCondDescTxt.getValue());
        params.put("lineBizTpCd", bizMsg.lineBizTpCd.getValue());

        return getSsmEZDClient().queryObjectList("getFreightTermInfoList", params);

    }

    /**
     * <pre>
     * get Service Level Pulldown Data
     * @param glblCmpyCd Global Company Code
     * @param lineBizTpCd Line of Business Code
     * @param frtCondCd Freight Condition COde (Option)
     * @return S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getShpgSvcLvlDataList(String glblCmpyCd, String lineBizTpCd, String frtCondCd) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("lineBizTpCd", lineBizTpCd);
        if (ZYPCommonFunc.hasValue(frtCondCd)) {
            params.put("frtCondCd", frtCondCd);
        } else {
            params.remove("frtCondCd");
        }
        return getSsmEZDClient().queryObjectList("getShpgSvcLvlDataList", params);
    }

    // QC#13688 2017/02/24 Add End

    // 2017/03/09 S21_NA#16790 Add Start
    /**
     * getOrigCpoOrdNum
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult query result object
     */
    public S21SsmEZDResult getOrigCpoOrdNum(Map<String, Object> queryParam) {
        // return getSsmEZDClient().queryObject("getOrigCpoOrdNum", queryParam); // 2019/03/28 S21_NA#30765 Mod
        return getSsmEZDClient().queryObjectList("getOrigCpoOrdNum", queryParam);
    }
    // 2017/03/09 S21_NA#16790 Add End

    // Add Start 2017/06/06 QC#18583
    public S21SsmEZDResult getSvcMachMstrPk(String glblCmpyCd, String mdseCd, String serNum) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("serNum", serNum);
        return getSsmEZDClient().queryObject("getSvcMachMstrPk", params);
    }
    // Add End 2017/06/06 QC#18583

    // 2017/08/23 S21_NA#20097 Add Start
    /**
     * query map
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return List<Map<String, Object>>
     */
    public Map<String, Object> queryMap(String queryId, Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(queryId, ssmParam);

        if (result == null || result.isEmpty()) {

            return null;
        }
        return result;
    }
    // 2017/08/23 S21_NA#20097 Add End

    // 2018/03/15 QC#20153 Mod Start
    // 2017/11/28 S21_NA#22782 add start
//    public S21SsmEZDResult getOrigCpoNumFromConfig(NWAL2200CMsg bizMsg) {
    public S21SsmEZDResult getOrigCpoNumFromConfig(String glblCmpyCd, NWAL2200SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        ArrayList<BigDecimal> svcConfigMstrPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            // if (!svcConfigMstrPkList.contains(glblMsg.A.no(i).svcConfigMstrPk_LC.getValue())) { // 2019/03/25 S21_NA#30924 Mod
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).svcConfigMstrPk_LC) && !svcConfigMstrPkList.contains(glblMsg.A.no(i).svcConfigMstrPk_LC.getValue())) {
                svcConfigMstrPkList.add(glblMsg.A.no(i).svcConfigMstrPk_LC.getValue());
            }
        }
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            // if (!svcConfigMstrPkList.contains(glblMsg.C.no(i).svcConfigMstrPk_RC.getValue())) { // 2019/03/25 S21_NA#30924 Mod
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).svcConfigMstrPk_RC) && !svcConfigMstrPkList.contains(glblMsg.C.no(i).svcConfigMstrPk_RC.getValue())) {
                svcConfigMstrPkList.add(glblMsg.C.no(i).svcConfigMstrPk_RC.getValue());
            }
        }

        if (svcConfigMstrPkList.size() > 0) {
            params.put("svcConfigMstrPkList", svcConfigMstrPkList);
        } else {
            return null;
        }

        return getSsmEZDClient().queryObjectList("getOrigCpoOrdNumFromConfig", params);
    }
    // 2017/11/28 S21_NA#22782 add end
    // 2018/03/15 QC#20153 Mod End

    // 2018/01/23 S21_NA#18798 Add Start
    public String getOrdCatgBizCtx(String glblCmpyCd, String ordCatgCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdCatgCd)) {
            return "";
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
        params.put("dsOrdCatgCd", dsOrdCatgCd);
        params.put("dsOrdTpCd", dsOrdTpCd);
        params.put("flgY", ZYPConstant.FLG_ON_Y);

        return (String) getSsmEZDClient().queryObject("getOrdCatgBizCtx", params).getResultObject();
    }

    public  S21SsmEZDResult getSalesRepList(String glblCmpyCd, String slsRepTocCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsRepTocCd", slsRepTocCd);
        return getSsmEZDClient().queryObjectList("getSalesRepList", ssmParam);
    }

    public String getOrdCatgBizCtxFstAttbTxt(String glblCmpyCd, String ordCatgCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdCatgCd)) {
            return "";
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
        params.put("dsOrdCatgCd", dsOrdCatgCd);
        params.put("dsOrdTpCd", dsOrdTpCd);

        return (String) getSsmEZDClient().queryObject("getOrdCatgBizCtxFstAttbTxt", params).getResultObject();
    }

    public S21SsmEZDResult getRmaConfigPkList(NWAL2200CMsg bizMsg, BigDecimal dsImptOrdConfigPk, String inboundConfigCatg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd);
        queryParam.put("dsImptOrdPk", bizMsg.dsImptOrdPk);
        queryParam.put("dsImptOrdConfigPk", dsImptOrdConfigPk);
        queryParam.put("inbondConfigCatgCd", inboundConfigCatg);

        return getSsmEZDClient().queryObjectList("getRmaConfigPkList", queryParam);
    }

    // 2018/01/23 S21_NA#18798 Add End
    // 2018/03/20 S21_NA#24840 Add Start
    public String getOrdCatgBizCtxScdAttbTxt(String glblCmpyCd, String ordCatgCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdCatgCd)) {
            return "";
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
        params.put("dsOrdCatgCd", dsOrdCatgCd);
        params.put("dsOrdTpCd", dsOrdTpCd);

        return (String) getSsmEZDClient().queryObject("getOrdCatgBizCtxScdAttbTxt", params).getResultObject();
    }
    // 2018/03/20 S21_NA#24840 Add End
    // QC#27364 2018/11/05 Add Start

    /**
     * getImptSvcForBand
     * @param queryParam
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getImptSvcForBand(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getImptSvcForBand", queryParam);
    }

    /**
     * getPrcListBandCdPrcBookMdseCd
     * @param queryParam
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListBandCdPrcBookMdseCd(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getPrcListBandCdPrcBookMdseCd", queryParam);
    }
    // QC#27364 2018/11/05 Add End
}
