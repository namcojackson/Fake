/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840;

import static business.blap.NWAL1840.constant.NWAL1840Constant.BIZ_ID;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Fujitsu         T.Murai         Create          N/A
 * 2016/05/13   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/08/03   Fujitsu         M.Hara          Update          S21_NA#7306
 * 2016/08/30   Fujitsu         M.Yamada        Update          QC#10754
 * 2016/10/24   Fujitsu         Y.Kanefusa      Update          S21_NA#14604
 * 2016/11/16   Fujitsu         H.Ikeda         Update          S21_NA#15875
 * 2017/08/04   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2018/04/16   Fujitsu         H.Nagashima     Update          QC#22965
 * </pre>
 */
public final class NWAL1840Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1840Query MY_INSTANCE = new NWAL1840Query();

    /**
     * Constructor.
     */
    private NWAL1840Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1840Query
     */
    public static NWAL1840Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get DS Order Category List
     * @param bizMsg NWAL1840CMsg
     * @return DS Order Category List
     */
    public S21SsmEZDResult getDsOrdCatgList(NWAL1840CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgDescTxt", bizMsg.dsOrdCatgDescTxt.getValue());
        params.put("bizId", BIZ_ID);

        return getSsmEZDClient().queryObjectList("getDsOrdCatgList", params);
    }

    // S21_NA#7306
    /**
     * getCtacPsnTpPulldown
     * @param cMsg NWAL1840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCtacPsnTpList(NWAL1840CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getCtacPsnTpList", ssmParam);
    }

    /**
     * Get DS Order Type List
     * @param bizMsg NWAL1840CMsg
     * @return DS Order Type List
     */
    public S21SsmEZDResult getDsOrdTpList(NWAL1840CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
        params.put("bizId", BIZ_ID);

        return getSsmEZDClient().queryObjectList("getDsOrdTpList", params);
    }

    /**
     * Get Customer And Additional Data
     * @param bizMsg NWAL1840CMsg
     * @return Customer And Additional Data
     */
    public S21SsmEZDResult getCustAddlInfo(NWAL1840CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("effDt", bizMsg.slsDt.getValue());
        params.put("rgtnSts", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getCustAddlInfo", params);
    }

    /**
     * Get Payment Term Cash Discount Code For Bill To Customer
     * @param bizMsg NWAL1840CMsg
     * @param billToCustCd Bill To Customer Code
     * @return Payment Term Cash Discount Code For Bill To Customer
     */
    public S21SsmEZDResult getPmtTermCashDiscCdForBillToCust(NWAL1840CMsg bizMsg, String billToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("billToCustCd", billToCustCd);

        return getSsmEZDClient().queryObject("getPmtTermCashDiscCdForBillToCust", params);
    }

    /**
     * get Sales Rep Info
     * @param bizMsg NWAL1840CMsg
     * @param slsRepTocCd Sales Rep TOC Code
     * @return Sales Rep Info
     */
    public S21SsmEZDResult getSlsRepInfo(NWAL1840CMsg bizMsg, String slsRepTocCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsRepTocCd", slsRepTocCd);
        params.put("rgtnStsCd", RGTN_STS.TERMINATED); // QC#14604 2016/10/24 Add

        return getSsmEZDClient().queryObject("getSlsRepInfo", params);
    }

    /**
     * Get Carrier SerVice Level Description Text
     * @param bizMsg NWAL1840CMsg
     * @param carrCd Carrier Code
     * @return Carrier SerVice Level Description Text
     */
    public S21SsmEZDResult getCarrSvcLvlTxt(NWAL1840CMsg bizMsg, String carrCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shpgSvcLvlCd", bizMsg.shpgSvcLvlCd.getValue());
        params.put("carrCd", carrCd);

        return getSsmEZDClient().queryObject("getCarrSvcLvlTxt", params);
    }

    /**
     * Get Carrier SerVice Level Code
     * @param bizMsg NWAL1840CMsg
     * @return Carrier SerVice Level Code
     */
    public S21SsmEZDResult getCarrSvcLvlCd(NWAL1840CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("carrSvcLvlDescTxt", bizMsg.carrSvcLvlDescTxt.getValue());

        return getSsmEZDClient().queryObject("getCarrSvcLvlCd", params);
    }

    /**
     * Get Payment Term Cash Discount Code
     * @param bizMsg NWAL1840CMsg
     * @return Payment Term Cash Discount Code
     */
    public S21SsmEZDResult getPmtTermCashDiscCd(NWAL1840CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("pmtTermCashDiscDescTxt", bizMsg.pmtTermCashDiscDescTxt.getValue());

        return getSsmEZDClient().queryObject("getPmtTermCashDiscCd", params);
    }

    /**
     * get Ship To Customer Address
     * @param bizMsg NWAL1840CMsg
     * @param shipToCustCd Ship To Customer Code
     * @return Ship To Customer Address
     */
    public S21SsmEZDResult getShipToCustAddr(NWAL1840CMsg bizMsg, String shipToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", shipToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getShipToCustAddr", params);
    }

    /**
     * Get Freight Condition Code
     * @param bizMsg NWAL1840CMsg
     * @return Freight Condition Code
     */
    public S21SsmEZDResult getFrtCondCd(NWAL1840CMsg bizMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("frtCondDescTxt", bizMsg.frtCondDescTxt.getValue());

        return getSsmEZDClient().queryObject("getFrtCondCd", queryParam);
    }

    /**
     * get Sales Rep Info List
     * @param bizMsg NWAL1840CMsg
     * @param isCallName Called Name Field
     * @return Sales Rep Info List
     */
    public S21SsmEZDResult getSlsRepInfoList(NWAL1840CMsg bizMsg, boolean isCallName) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        params.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        params.put("rgtnStsCd", RGTN_STS.TERMINATED); // QC#14604 2016/10/24 Add

        if (isCallName) {
            params.put("slsRepTocNm", bizMsg.slsRepTocNm.getValue());
        } else {
            params.put("psnNum", bizMsg.psnNum.getValue()); // S21_NA#7861 Mod slsRepPsnCd -> psnNum
        }

        return getSsmEZDClient().queryObjectList("getSlsRepInfoList", params);
    }

    /**
     * get Ship To Customer Address
     * @param bizMsg NWAL1840CMsg
     * @param shipToCustCd Ship To Customer Code
     * @return Ship To Customer Address
     */
    public S21SsmEZDResult getLocNum(NWAL1840CMsg bizMsg, String shipToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", shipToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getShipToCustAddr", params);
    }

    /**
     * get Ship Info by ship To Cust location
     * @param bizMsg NWAL1840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToInfo(NWAL1840CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("shipToCustCd", bizMsg.shipToCustLocCd.getValue());
        ssmParam.put("rgtnSts", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rowNum", 1);

        return getSsmEZDClient().queryObject("getShipToInfo", ssmParam);
    }

    /**
     * get Default Ship Info
     * @param bizMsg NWAL1840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefCust(NWAL1840CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("serNum", bizMsg.serNum.getValue());
        //ssmParam.put("svcConfigMstrPk", bizMsg.svcConfigMstrPk.getValue());

        return getSsmEZDClient().queryObjectList("getDefCust", ssmParam);
    }

    /**
     * get Default Sold Info
     * @param bizMsg NWAL1840CMsg
     * @return S21SsmEZDResult
     */
//    public S21SsmEZDResult getDefSold(NWAL1840CMsg bizMsg) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        ssmParam.put("serNum", bizMsg.serNum.getValue());
//
//        return getSsmEZDClient().queryObjectList("getDefSold", ssmParam);
//    }

    /**
     * get Default Bill Info
     * @param bizMsg NWAL1840CMsg
     * @return S21SsmEZDResult
     */
//    public S21SsmEZDResult getDefBill(NWAL1840CMsg bizMsg) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        ssmParam.put("contrNum", bizMsg.dsContrNum.getValue());
//
//        return getSsmEZDClient().queryObjectList("getDefBill", ssmParam);
//    }
//
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

    /**
     * get Freight Term Information List
     * @param bizMsg NWAL1840CMsg
     * @return Freight Term Information List
     */
    public S21SsmEZDResult getFreightTermInfoList(NWAL1840CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("frtCondDescTxt", bizMsg.frtCondDescTxt.getValue());
        params.put("lineBizTpCd", bizMsg.lineBizTpCd.getValue());

        return getSsmEZDClient().queryObjectList("getFreightTermInfoList", params);
    }

    // Add Start 2016/11/16 S21_NA#15875
    /**
     * get Machine Info
     * @param bizMsg NWAL1840CMsg
     * @return Machine Info
     */
    public S21SsmEZDResult getMachineInfo(NWAL1840CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd",   bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk",    bizMsg.dsContrPk.getValue());
        params.put("dsContrDtlPk", bizMsg.dsContrDtlPk.getValue());

        return getSsmEZDClient().queryObjectList("getMachineInfo", params);
    }
    // Add End   2016/11/16 S21_NA#15875

    // QC#16452 add Start
    /**
     * get Loction Number
     * @param bizMsg NWAL1840CMsg
     * @param ctacMsg NWAL1840_DCMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocationNumber(NWAL1840CMsg bizMsg, NWAL1840_DCMsg ctacMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        String query = null;
        String ctacCustRefTpCd = ctacMsg.ctacCustRefTpCd_D.getValue();
        if (CTAC_CUST_REF_TP.BILL_TO.equals(ctacCustRefTpCd)) {
            ssmParam.put("custCd", bizMsg.billToCustLocCd.getValue());
            query = "getBillToLocationNumber";

        } else if (CTAC_CUST_REF_TP.SHIP_TO.equals(ctacCustRefTpCd)) {
            ssmParam.put("custCd", bizMsg.shipToCustLocCd.getValue());
            query = "getShipToLocationNumber";

        } else if (CTAC_CUST_REF_TP.SOLD_TO.equals(ctacCustRefTpCd)) {
            ssmParam.put("custCd", bizMsg.soldToCustLocCd.getValue());
            query = "getBillToLocationNumber";
        } else {
            ssmParam.put("custCd", bizMsg.shipToCustLocCd.getValue());
            query = "getShipToLocationNumber";
        }
        ssmParam.put("rgtnSts", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rowNum", 1);

        return getSsmEZDClient().queryObject(query, ssmParam);
    }
    // QC#16452 add End
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

}
