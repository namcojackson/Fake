/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770;

import static business.blap.NWAL1770.constant.NWAL1770Constant.BIZ_ID;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.blap.NWAL1770.constant.NWAL1770Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MAN_TRMN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   Fujitsu         T.Yoshida       Create          N/A
 * 2016/08/03   Fujitsu         M.Hara          Update          
 * 2016/09/26   Fujitsu         H.Ikeda         Update          S21_NA#13516
 * 2017/11/01   Fujitsu         H.Sugawara      Update          QC#18787(Sol#232)
 * 2019/02/13   Fujitsu         W.Honda         Update          S21_NA#30287
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * 2024/04/01   Hitachi         T.Fukuta        Update          QC#63548
 * </pre>
 */
public final class NWAL1770Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1770Query MY_INSTANCE = new NWAL1770Query();

    /**
     * Constructor.
     */
    private NWAL1770Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1770Query
     */
    public static NWAL1770Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get DS Order Category List
     * @param bizMsg NWAL1770CMsg
     * @return DS Order Category List
     */
    public S21SsmEZDResult getDsOrdCatgList(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgDescTxt", bizMsg.dsOrdCatgDescTxt.getValue());
        params.put("bizId", BIZ_ID);

        return getSsmEZDClient().queryObjectList("getDsOrdCatgList", params);
    }

    /**
     * Get DS Order Type List
     * @param bizMsg NWAL1770CMsg
     * @return DS Order Type List
     */
    public S21SsmEZDResult getDsOrdTpList(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgCd", bizMsg.splyQuoteCatgCd.getValue());
        params.put("bizId", BIZ_ID);

        return getSsmEZDClient().queryObjectList("getDsOrdTpList", params);
    }

    /**
     * Get Shipping Service Level List
     * @param bizMsg NWAL1770CMsg
     * @return Shipping Service Level List
     */
    public S21SsmEZDResult getShpgSvcLvlList(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("lineBizTpCd", bizMsg.lineBizTpCd.getValue());
        params.put("frtCondCd", bizMsg.frtCondCd.getValue());

        return getSsmEZDClient().queryObjectList("getShpgSvcLvlList", params);
    }

    /**
     * Get DS Order Line Category List
     * @param bizMsg NWAL1770CMsg
     * @param effDt Effective Date (YYYYMMDD)
     * @return DS Order Line Category List
     */
    public S21SsmEZDResult getDsOrdLineCatgList(NWAL1770CMsg bizMsg, String effDt) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("effDt", effDt);
        params.put("dsOrdLineDrctnCd", DS_ORD_LINE_DRCTN.OUTBOUND);

        return getSsmEZDClient().queryObjectList("getDsOrdLineCatgList", params);
    }

    // S21_NA#7306
    /**
     * getCtacPsnTpPulldown
     * @param bizMsg NWAL1770CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCtacPsnTpList(NWAL1770CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getCtacPsnTpList", ssmParam);
    }

    /**
     * Get Order Line Source List
     * @param bizMsg NWAL1770CMsg
     * @return Order Line Source List
     */
    public S21SsmEZDResult getOrdLineSrcList(NWAL1770CMsg bizMsg) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getOrdLineSrcList", params);
    }

    /**
     * Get Customer And Additional Data
     * @param bizMsg NWAL1770CMsg
     * @return Customer And Additional Data
     */
    public S21SsmEZDResult getCustAddlInfo(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rgtnSts", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getCustAddlInfo", params);
    }

    /**
     * Get Payment Term Cash Discount Code
     * @param bizMsg NWAL1770CMsg
     * @return Payment Term Cash Discount Code
     */
    public S21SsmEZDResult getPmtTermCashDiscCd(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("pmtTermCashDiscDescTxt", bizMsg.pmtTermCashDiscDescTxt.getValue());

        return getSsmEZDClient().queryObject("getPmtTermCashDiscCd", params);
    }

    /**
     * Get Payment Term Cash Discount Code For Bill To Customer
     * @param bizMsg NWAL1770CMsg
     * @param billToCustCd Bill To Customer Code
     * @return Payment Term Cash Discount Code For Bill To Customer
     */
    public S21SsmEZDResult getPmtTermCashDiscCdForBillToCust(NWAL1770CMsg bizMsg, String billToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("billToCustCd", billToCustCd);

        return getSsmEZDClient().queryObject("getPmtTermCashDiscCdForBillToCust", params);
    }

    /**
     * Get Carrier SerVice Level Code
     * @param bizMsg NWAL1770CMsg
     * @return Carrier SerVice Level Code
     */
    public S21SsmEZDResult getCarrSvcLvlCd(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("carrSvcLvlDescTxt", bizMsg.carrSvcLvlDescTxt.getValue());

        return getSsmEZDClient().queryObject("getCarrSvcLvlCd", params);
    }

    /**
     * Get Carrier SerVice Level Description Text
     * @param bizMsg NWAL1770CMsg
     * @param carrCd Carrier Code
     * @return Carrier SerVice Level Description Text
     */
    public S21SsmEZDResult getCarrSvcLvlTxt(NWAL1770CMsg bizMsg, String carrCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shpgSvcLvlCd", bizMsg.shpgSvcLvlCd.getValue());
        params.put("carrCd", carrCd);

        return getSsmEZDClient().queryObject("getCarrSvcLvlTxt", params);
    }

    /**
     * Get Package UOM Information List
     * @param bizMsg NWAL1770CMsg
     * @param mdseCd MDSE Code
     * @return Package UOM Information List
     */
    public S21SsmEZDResult getPkgUomInfoList(NWAL1770CMsg bizMsg, String mdseCd) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObjectList("getPkgUomInfoList", params);
    }

    /**
     * Get Freight Condition Code
     * @param bizMsg NWAL1770CMsg
     * @return Freight Condition Code
     */
    public S21SsmEZDResult getFrtCondCd(NWAL1770CMsg bizMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("frtCondDescTxt", bizMsg.frtCondDescTxt.getValue());

        return getSsmEZDClient().queryObject("getFrtCondCd", queryParam);
    }

    /**
     * Get Retail Warehouse Code
     * @param bizMsg NWAL1770CMsg
     * @param rtlWhNm Retail WH Name
     * @return Retail Warehouse Code
     */
    public S21SsmEZDResult getRtlWhCd(NWAL1770CMsg bizMsg, String rtlWhNm) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("rtlWhNm", rtlWhNm);

        return getSsmEZDClient().queryObject("getRtlWhCd", queryParam);
    }

    /**
     * Get Retail Sub Warehouse Code
     * @param bizMsg NWAL1770CMsg
     * @@param rtlWhCd Retail WH Code
     * @param rtlSwhNm Retail Sub WH Name
     * @return Retail Sub Warehouse Code
     */
    public S21SsmEZDResult getRtlSubWhCd(NWAL1770CMsg bizMsg, String rtlWhCd, String rtlSwhNm) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("rtlWhCd", rtlWhCd);
        queryParam.put("rtlSwhNm", rtlSwhNm);

        return getSsmEZDClient().queryObject("getRtlSubWhCd", queryParam);
    }

    /**
     * Get Report Output Log List
     * @param bizMsg NWAL1770CMsg
     * @return DS Order Type List
     */
    public S21SsmEZDResult getReportOutputLogList(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("splyQuoteNum", bizMsg.splyQuoteNum.getValue());

        return getSsmEZDClient().queryObjectList("getReportOutputLogList", params);
    }

    // Add Start 2016/09/26 S21_NA#13516
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
    // Add End 2016/09/26 S21_NA#13516

    // Add Start 2017/09/28 S21_NA#21121
    /**
     * Get Payment Term Cash Disc Code List
     * @param bizMsg NWAL1770CMsg
     * @return Payment Term Cash Disc Code List
     */
    public S21SsmEZDResult getPmtTermList(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("pmtTermCashDiscDescTxt", bizMsg.pmtTermCashDiscDescTxt.getValue());

        return getSsmEZDClient().queryObjectList("getPmtTermList", params);
    }

    /**
     * Get Payment Term Cash Discount Code
     * @param bizMsg NWAL1770CMsg
     * @return Payment Term Cash Discount Code
     */
    public S21SsmEZDResult getPmtTerm(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("pmtTermCashDiscDescTxt", bizMsg.pmtTermCashDiscDescTxt.getValue());

        return getSsmEZDClient().queryObject("getPmtTerm", params);
    }
    // Add End 2017/09/28 S21_NA#21121

    // Add Start 2017/11/01 QC#18787(Sol#232)
    /**
     * Get Default value of Days Valid
     * @param bizMsg NWAL1770CMsg
     * @return Default value of Days Valid
     */
    public S21SsmEZDResult getDefaultDayValid(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("ordCatgCtxTpCd", NWAL1770Constant.ORD_CATG_CTX_TP_CD_DAYS_VALID);
        params.put("dsOrdCatgCd", bizMsg.splyQuoteCatgCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());

        return getSsmEZDClient().queryObjectList("getDefaultDaysValid", params);
    }
    // Add End 2017/11/01 QC#18787(Sol#232)
    //QC#22965 Add Start
    /**
     * <pre>
     * @param glblCmpyCd    String
     * @param mdseCd        String
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getInPondWt(String glblCmpyCd, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getInPondWt", params);
    }
    //QC#22965 Add End
    // 2019/02/13 S21_NA#30287 Add Start
    /**
     * <pre>
     * @param glblCmpyCd    String
     * @param mdseCd        String
     * @return  S21SsmEZDResult
     * </pre>
     */
    public String getMaxLineNum(String glblCmpyCd, String splyQuoteNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("splyQuoteNum", splyQuoteNum);

        return (String) getSsmEZDClient().queryObject("getMaxLineNum", params).getResultObject();
    }
    // 2019/02/13 S21_NA#30287 Add End

    // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    public S21SsmEZDResult getOrderContractByShipToCust(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", bizMsg.shipToCustCd.getValue());
        params.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        params.put("slsDt", bizMsg.slsDt.getValue());
        // START 2024/04/01 T.Fukuta [QC#63548,ADD]
        params.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2024/04/01 T.Fukuta [QC#63548,ADD]

        return getSsmEZDClient().queryObjectList("getOrderContractByShipToCust", params);
    }

    public S21SsmEZDResult getMdseCdBySvcMachMstrPk(NWAL1770CMsg bizMsg, BigDecimal svcMachMstrPk) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("svcMachMstrPk", svcMachMstrPk);

        return getSsmEZDClient().queryObjectList("getMdseCdBySvcMachMstrPk", params);
    }
    // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
}
