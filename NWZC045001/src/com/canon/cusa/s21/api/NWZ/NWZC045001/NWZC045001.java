/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 * 
 * <pre>
 * Derive Default Payment Term Cash Discount API.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2009   Canon           H.Sakamoto      Create          N/A
 * 10/12/2009   Canon           H.Sakmaoto      Update          389
 * 11/10/2009   Canon           S.Sugino        Update          N/A. Use of cache.
 * 11/18/2009   Canon           T.Kaneda        Update          N/A  Not Create customer data
 * 12/04/2009   Canon           K.Sato          Update          2413
 * 01/20/2010   Canon           M.Nakamura      Update          N/A
 * 02/03/2010   Canon           T.Kaneda        Update          N/A
 * 05/27/2010   Fujitsu         A.Suda          Update          6758
 * </pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC045001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static parts.common.EZDDebugOutput.isDebug;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.parts.NWZC045001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * Derive Default Payment Term Cash Discount API
 */
public class NWZC045001 extends S21ApiCommonBase {

    public static final String NWZM0001E = "NWZM0001E";
    public static final String NWZM0403E = "NWZM0403E";
    public static final String NWZM0404E = "NWZM0404E";
    public static final String NWZM0405E = "NWZM0405E";
    public static final String NWZM0406E = "NWZM0406E";
    public static final String NWZM0407E = "NWZM0407E";
    public static final String NWZM0408E = "NWZM0408E";
    public static final String NWZM0409E = "NWZM0409E";
    public static final String NWZM0410E = "NWZM0410E";
    public static final String NWZM0411E = "NWZM0411E";

    private static final int ASG_PROD_CTRL_LAYER_MAX_NUM = 8;

    private static final String Y = ZYPConstant.FLG_ON_Y;
    
    private final S21SsmBatchClient ssmClient;

    public NWZC045001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Derive default payment term code and cash discount term code.
     * This method don't use onBatchType.
     * @param param NWZC045001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC045001PMsg param, ONBATCH_TYPE onBatchType) {

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        this.doProcess(msgMap, onBatchType);
        msgMap.flush();
    }

    /**
     * Derive default payment term code and cash discount term code.
     * This method don't use onBatchType.
     * @param param NWZC045001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC045001PMsg> paramList, ONBATCH_TYPE onBatchType) {
        
        for (NWZC045001PMsg param : paramList) {
            execute(param, onBatchType);
        }
    }

    /**
     * Create customer data by customer code (billto or sellto or
     * shipto).<br>
     * Deriving pattern is Upper Default, Lower Overwrite.
     * @param msgMap S21ApiMessageMap
     * @return Customer data
     */
    private CustomerData createCustomerData(S21ApiMessageMap msgMap) {

        NWZC045001PMsg inMsg = (NWZC045001PMsg) msgMap.getPmsg();

        CustomerData cstData = new CustomerData();

        if (hasValue(inMsg.billToCustCd.getValue()) && hasValue(inMsg.sellToCustCd.getValue()) && hasValue(inMsg.shipToCustCd.getValue())) {
            cstData.setBillToCustCd(inMsg.billToCustCd.getValue());
            cstData.setSellToCustCd(inMsg.sellToCustCd.getValue());

            if (hasValue(inMsg.payerCustCd.getValue())) {
                cstData.setPayerCustCd(inMsg.payerCustCd.getValue());
            } else {
                cstData.setPayerCustCd(cstData.getBillToCustCd());
            }

            return cstData;
        }

        // Deriving pattern is Upper Default, Lower Overwrite
        if (hasValue(inMsg.shipToCustCd)) {

            cstData = this.getCustomerDataByShipToCustCd(inMsg.glblCmpyCd.getValue(), inMsg.shipToCustCd.getValue());
            if (cstData == null) {
                this.setMessageId(msgMap, NWZM0408E);
                return null;
            }

        } else if (hasValue(inMsg.sellToCustCd)) {

            cstData = this.getCustomerDataBySellToCustCd(inMsg.glblCmpyCd.getValue(), inMsg.sellToCustCd.getValue());
            if (cstData == null) {
                this.setMessageId(msgMap, NWZM0409E);
                return null;
            }

        } else if (hasValue(inMsg.billToCustCd)) {

            cstData = this.getCustomerDataByBillToCustCd(inMsg.glblCmpyCd.getValue(), inMsg.billToCustCd.getValue());
            if (cstData == null) {
                this.setMessageId(msgMap, NWZM0410E);
                return null;
            }
            // Upper Default, Lower Overwrite
            cstData.setSellToCustCd(null);

        } else {
            this.setMessageId(msgMap, NWZM0403E);
            return null;
        }

        if (hasValue(inMsg.payerCustCd.getValue())) {
            cstData.setPayerCustCd(inMsg.payerCustCd.getValue());
        } else {
            cstData.setPayerCustCd(cstData.getBillToCustCd());
        }

        return cstData;

    }

    /**
     * Create Merchanddise Structure Data by product control code
     * (zeroth or first or second).<br>
     * Deriving pattern is Upper Default, Lower Overwrite.
     * @param msgMap S21ApiMessageMap
     * @return Merchandise structure data
     */
    private MdseStructureData createMdseStructureData(S21ApiMessageMap msgMap) {

        NWZC045001PMsg inMsg = (NWZC045001PMsg) msgMap.getPmsg();

        MdseStructureData msData = null;

        // Deriving pattern is Upper Default, Lower Overwrite
        if (hasValue(inMsg.mdseCd)) {

            msData = this.getMdseStructureData(inMsg.glblCmpyCd.getValue(), inMsg.mdseCd.getValue());
            if (msData == null) {
                this.setMessageId(msgMap, NWZM0406E);
                return null;
            }

        } else if (hasValue(inMsg.scdProdCtrlCd)) {

            msData = this.getMdseStructureData(inMsg.glblCmpyCd.getValue(), null, null, inMsg.scdProdCtrlCd.getValue());
            if (msData == null) {
                this.setMessageId(msgMap, NWZM0407E);
                return null;
            }
            // Upper Default, Lower Overwrite
            msData.setThirdProdCtrlCd(null);
            msData.setFrthProdCtrlCd(null);
            msData.setFifthProdCtrlCd(null);
            msData.setSixthProdCtrlCd(null);
            msData.setSvnthProdCtrlCd(null);
            msData.setMdseCd(null);

        } else if (hasValue(inMsg.firstProdCtrlCd)) {

            msData = this.getMdseStructureData(inMsg.glblCmpyCd.getValue(), null, inMsg.firstProdCtrlCd.getValue(), null);
            if (msData == null) {
                this.setMessageId(msgMap, NWZM0407E);
                return null;
            }
            // Upper Default, Lower Overwrite
            msData.setScdProdCtrlCd(null);
            msData.setThirdProdCtrlCd(null);
            msData.setFrthProdCtrlCd(null);
            msData.setFifthProdCtrlCd(null);
            msData.setSixthProdCtrlCd(null);
            msData.setSvnthProdCtrlCd(null);
            msData.setMdseCd(null);

        } else if (hasValue(inMsg.zerothProdCtrlCd)) {

            msData = this.getMdseStructureData(inMsg.glblCmpyCd.getValue(), inMsg.zerothProdCtrlCd.getValue(), null, null);
            if (msData == null) {
                this.setMessageId(msgMap, NWZM0407E);
                return null;
            }
            // Upper Default, Lower Overwrite
            msData.setScdProdCtrlCd(null);
            msData.setFirstProdCtrlCd(null);
            msData.setThirdProdCtrlCd(null);
            msData.setFrthProdCtrlCd(null);
            msData.setFifthProdCtrlCd(null);
            msData.setSixthProdCtrlCd(null);
            msData.setSvnthProdCtrlCd(null);
            msData.setMdseCd(null);

        } else {
            this.setMessageId(msgMap, NWZM0404E);
            return null;
        }

        return msData;
    }

    private void debug(String str) {
        if (isDebug()) {
            EZDDebugOutput.println(1, str, this);
        }
    }

    /**
     * This method executes following processes.
     * 
     * <pre>
     * (1) Check global company code
     * (2) Create merchandise structure data
     * (3) Create customer data
     * (4) Get payment term and cash discount from Database
     * (5) Set payment term and cash discount to P message
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    private void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NWZC045001PMsg inMsg = (NWZC045001PMsg) msgMap.getPmsg();

        // Check global company code
        if (!hasValue(inMsg.glblCmpyCd)) {
            this.setMessageId(msgMap, NWZM0001E);
            return;
        }
        if (!exsitsGlblCmpyCd(inMsg.glblCmpyCd.getValue())) {
            this.setMessageId(msgMap, NWZM0405E);
            return;
        }

        // Create merchandise structure data
        MdseStructureData msData = this.createMdseStructureData(msgMap);
        if (isDebug()) {
            this.debug("MdseStructureData=" + msData);
        }

        if (msData == null) {
            return;
        }

        // Create customer data
        CustomerData cstData = this.createCustomerData(msgMap);
        if (isDebug()) {
            this.debug("CustomerData=" + cstData);
        }

        if (cstData == null) {
            return;
        }

        // Get payment term and cash discount from Database
        List cptList = this.getCustomerPaymentTermData(inMsg.glblCmpyCd.getValue(), msData, cstData);
        if (cptList.isEmpty()) {
            this.setMessageId(msgMap, NWZM0411E);
            return;
        }

        // Set payment term and cash discount to P message
        Map cptMap = (Map) cptList.get(0);
        setValue(inMsg.pmtTermCd,          (String) cptMap.get("PMT_TERM_CD"));
        setValue(inMsg.cashDiscTermCd,     (String) cptMap.get("CASH_DISC_TERM_CD"));
        setValue(inMsg.pmtTermCashDiscCd, (String) cptMap.get("PMT_TERM_CASH_DISC_CD"));

        return;
    }

    /**
     * Get customer data by bill to customer code.
     * @param glblCmpyCd Global company code
     * @param billToCustCd Bill to customer code
     * @return customer data
     */
    private CustomerData getCustomerDataByBillToCustCd(String glblCmpyCd, String billToCustCd) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",   glblCmpyCd);
        ssmParam.put("rgtnStsCd",    RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("billToCustCd", billToCustCd);

        return (CustomerData) this.ssmClient.queryObject("getCustomerDataByBillToCustCd", ssmParam);
    }

    /**
     * Get customer data by sell to customer code.
     * @param glblCmpyCd Global company code
     * @param sellToCustCd Sell to customer code
     * @return customer data
     */
    private CustomerData getCustomerDataBySellToCustCd(String glblCmpyCd, String sellToCustCd) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",   glblCmpyCd);
        ssmParam.put("rgtnStsCd",    RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("sellToCustCd", sellToCustCd);

        return (CustomerData) this.ssmClient.queryObject("getCustomerDataBySellToCustCd", ssmParam);
    }

    /**
     * Get customer data by ship to customer code.
     * @param glblCmpyCd Global company code
     * @param shipToCustCd Ship to customer code
     * @return customer data
     */
    private CustomerData getCustomerDataByShipToCustCd(String glblCmpyCd, String shipToCustCd) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",   glblCmpyCd);
        ssmParam.put("rgtnStsCd",    RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("shipToCustCd", shipToCustCd);

        return (CustomerData) this.ssmClient.queryObject("getCustomerDataByShipToCustCd", ssmParam);
    }

    /**
     * Get customer payment term data.
     * @param glblCmpyCd Global customer code
     * @param msData Merchandise structure data
     * @param cstData Customer data
     * @return payment term data
     */
    // private List getCustomerPaymentTermData(String glblCmpyCd,
    // MdseStructureData msData, S21ApiMessageMap msgMap) {
    private List getCustomerPaymentTermData(String glblCmpyCd, MdseStructureData msData, CustomerData cstData) {
        Map<String, String> param = new HashMap<String, String>();
        Map<String, String> param1 = new HashMap<String, String>();

        boolean flPlanCmpyFlg = isFloorPlanCompany(glblCmpyCd, cstData);

        // NWZC045001PMsg inMsg = (NWZC045001PMsg) msgMap.getPmsg();

        param.put("glblCmpyCd", glblCmpyCd);
        param.put("zerothProdCtrlCd", msData.getZerothProdCtrlCd());
        param.put("firstProdCtrlCd", msData.getFirstProdCtrlCd());
        param.put("scdProdCtrlCd", msData.getScdProdCtrlCd());
        param.put("billToCustCd", cstData.getPayerCustCd());
        if (!flPlanCmpyFlg) {
            param.put("sellToCustCd", cstData.getSellToCustCd());
        }
        // Defect 6758 add --- start ---
        param.put("thirdProdCtrlCd", msData.getThirdProdCtrlCd());
        param.put("frthProdCtrlCd", msData.getFrthProdCtrlCd());
        param.put("fifthProdCtrlCd", msData.getFifthProdCtrlCd());
        param.put("sixthProdCtrlCd", msData.getSixthProdCtrlCd());
        param.put("svnthProdCtrlCd", msData.getSvnthProdCtrlCd());
        param.put("mdseCd", msData.getMdseCd());
        // Defect 6758 add --- end ---

        // param.put("billToCustCd", inMsg.billToCustCd.getValue());
        // param.put("sellToCustCd", inMsg.sellToCustCd.getValue());
        // AND0
        if (hasValue(msData.getZerothProdCtrlCd()) || hasValue(msData.getFirstProdCtrlCd()) || hasValue(msData.getScdProdCtrlCd()) || hasValue(msData.getThirdProdCtrlCd())
                || hasValue(msData.getFrthProdCtrlCd()) || hasValue(msData.getFifthProdCtrlCd()) || hasValue(msData.getSvnthProdCtrlCd()) || hasValue(msData.getSixthProdCtrlCd())
                || hasValue(msData.getMdseCd())) {
            param.put("AND0", "true");
        }

        // OR0 & OR1
        // this logic is same with NWZC046001
        String[] pccd = new String[ASG_PROD_CTRL_LAYER_MAX_NUM];
        int index = 0;
        pccd[index++] = msData.getZerothProdCtrlCd();
        pccd[index++] = msData.getFirstProdCtrlCd();
        pccd[index++] = msData.getScdProdCtrlCd();
        // Defect 6758 add --- start ---
        pccd[index++] = msData.getThirdProdCtrlCd();
        pccd[index++] = msData.getFrthProdCtrlCd();
        pccd[index++] = msData.getFifthProdCtrlCd();
        pccd[index++] = msData.getSixthProdCtrlCd();
        pccd[index] = msData.getSvnthProdCtrlCd();
        // Defect 6758 add --- end ---

        String prodCtrlFlg = null;

        for (int i = 0; i < pccd.length - 1; i++) {
            if (!hasValue(pccd[i])) {
                continue;
            }

            for (int j = i + 1; j < pccd.length; j++) {
                if (!hasValue(pccd[j])) {
                    continue;
                }

                String orKey = "OR" + String.valueOf(i);
                param.put(orKey, "true");
                param1.put(orKey, "true");
                prodCtrlFlg = Y;
                break;
            }
        }
        if (!hasValue(msData.getMdseCd())) {
            prodCtrlFlg = null;
        }

        param.put("prodCtrlFlg", prodCtrlFlg);

        // AND1
        if (hasValue(cstData.getPayerCustCd()) || hasValue(cstData.getSellToCustCd())) {
            param.put("AND1", "true");
        }

        // OR100
        if (!flPlanCmpyFlg && hasValue(cstData.getPayerCustCd()) && hasValue(cstData.getSellToCustCd())) {
            param.put("OR100", "true");
        }

        List rtnList = this.ssmClient.queryObjectList("getCustomerPaymentTermData", param);

        // Defect 6758 add --- start ---
        if (rtnList == null || rtnList.isEmpty()) {

            param1.put("glblCmpyCd", glblCmpyCd);
            param1.put("zerothProdCtrlCd", msData.getZerothProdCtrlCd());
            param1.put("firstProdCtrlCd", msData.getFirstProdCtrlCd());
            param1.put("scdProdCtrlCd", msData.getScdProdCtrlCd());
            param1.put("thirdProdCtrlCd", msData.getThirdProdCtrlCd());
            param1.put("frthProdCtrlCd", msData.getFrthProdCtrlCd());
            param1.put("fifthProdCtrlCd", msData.getFifthProdCtrlCd());
            param1.put("sixthProdCtrlCd", msData.getSixthProdCtrlCd());
            param1.put("svnthProdCtrlCd", msData.getSvnthProdCtrlCd());
            param1.put("mdseCd", msData.getMdseCd());
            param1.put("prodCtrlFlg", prodCtrlFlg);
            if (hasValue(msData.getZerothProdCtrlCd()) || hasValue(msData.getFirstProdCtrlCd()) || hasValue(msData.getScdProdCtrlCd()) || hasValue(msData.getThirdProdCtrlCd())
                    || hasValue(msData.getFrthProdCtrlCd()) || hasValue(msData.getFifthProdCtrlCd()) || hasValue(msData.getSvnthProdCtrlCd()) || hasValue(msData.getSixthProdCtrlCd())
                    || hasValue(msData.getMdseCd())) {
                param1.put("AND0", "true");
            }
            param1.put("custCd", "*");

            List rtnListByMdse = this.ssmClient.queryObjectList("getCustomerPaymentTermDataByMdse", param1);

            if (isDebug()) {
                this.debug("getCustomerPaymentTermDataByMdse=" + rtnListByMdse);
            }

            return rtnListByMdse;

        } else {

            if (isDebug()) {
                this.debug("getCustomerPaymentTermData=" + rtnList);
            }

            return rtnList;

        }
        // Defect 6758 add --- end ---

    }

    /**
     * Get merchandise structure data.
     * @param glblCmpyCd Global company code
     * @param mdseCd Merchandise code
     * @return Merchandise structure data
     */
    private MdseStructureData getMdseStructureData(String glblCmpyCd, String mdseCd) {

        final MDSETMsg mdseMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseMsg == null || !(RGTN_STS.READY_FOR_ORDER_TAKING.equals(mdseMsg.rgtnStsCd.getValue()) || RGTN_STS.TERMINATED.equals(mdseMsg.rgtnStsCd.getValue()))) {
            return null;
        }

        final MdseStructureData msData = new MdseStructureData();
        msData.setZerothProdCtrlCd(mdseMsg.zerothProdCtrlCd.getValue());
        msData.setFirstProdCtrlCd(mdseMsg.firstProdCtrlCd.getValue());
        msData.setScdProdCtrlCd(mdseMsg.scdProdCtrlCd.getValue());
        // Defect 6758 add --- start ---
        msData.setThirdProdCtrlCd(mdseMsg.thirdProdCtrlCd.getValue());
        msData.setFrthProdCtrlCd(mdseMsg.frthProdCtrlCd.getValue());
        msData.setFifthProdCtrlCd(mdseMsg.fifthProdCtrlCd.getValue());
        msData.setSixthProdCtrlCd(mdseMsg.sixthProdCtrlCd.getValue());
        msData.setSvnthProdCtrlCd(mdseMsg.svnthProdCtrlCd.getValue());
        msData.setMdseCd(mdseMsg.mdseCd.getValue());
        // Defect 6758 add --- end ---

        return msData;
    }

    /**
     * Get merchandise structure data.
     * @param glblCmpyCd Global company code
     * @param zerothProdCtrlCd Zeroth product control code
     * @param firstProdCtrlCd First product control code
     * @param scdProdCtrlCd Second product control code
     * @return Merchandise structure data
     */
    private MdseStructureData getMdseStructureData(String glblCmpyCd, String zerothProdCtrlCd, String firstProdCtrlCd, String scdProdCtrlCd) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",       glblCmpyCd);
        ssmParam.put("mdseStruTp",       MDSE_STRU_TP.PRODUCT_CONTROL_STRUCTURE);
        ssmParam.put("zerothProdCtrlCd", zerothProdCtrlCd);
        ssmParam.put("firstProdCtrlCd",  firstProdCtrlCd);
        ssmParam.put("scdProdCtrlCd",    scdProdCtrlCd);

        return (MdseStructureData) this.ssmClient.queryObject("getMdseStructureData", ssmParam);
    }

    private boolean isFloorPlanCompany(String glblCmpyCd, CustomerData cstData) {
        
        boolean result = false;

        if (!hasValue(cstData.getPayerCustCd())) {
            return result;
        }

        if (!hasValue(cstData.getSellToCustCd())) {
            return result;
        }

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",   glblCmpyCd);
        ssmParam.put("billToCustCd", cstData.getPayerCustCd());
        ssmParam.put("sellToCustCd", cstData.getSellToCustCd());

        Map payerData = (Map) this.ssmClient.queryObject("getAltPayerData", ssmParam);

        if (payerData != null && Y.equals(payerData.get("FL_PLN_CMPY_FLG"))) {
            result = true;
        }

        return result;
    }

    /**
     * Set message id and clear pmtTermCd and cashDiscTermCd
     * properties.
     * @param msgMap S21ApiMessageMap
     * @param msgId message id
     */
    private void setMessageId(S21ApiMessageMap msgMap, String msgId) {
        msgMap.addXxMsgId(msgId);
        NWZC045001PMsg inMsg = (NWZC045001PMsg) msgMap.getPmsg();
        inMsg.pmtTermCd.clear();
        inMsg.cashDiscTermCd.clear();
    }

    private static boolean exsitsGlblCmpyCd(String glblCmpyCd) {

        final GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        setValue(glblCmpyTMsg.glblCmpyCd, glblCmpyCd);
        
        return S21CacheTBLAccessor.findByKey(glblCmpyTMsg) != null;
    }

}
