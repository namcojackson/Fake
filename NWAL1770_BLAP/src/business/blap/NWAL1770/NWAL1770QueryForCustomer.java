/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import static business.blap.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.blap.NWAL1770.constant.NWAL1770Constant.CTAC_ROLE_MND_SET;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         T.Yoshida       Create          N/A
 * 2017/12/20   Fujitsu         Mz.Takahashi    Update          S21_NA#20164(L3 Sol#356)
 * </pre>
 */
public final class NWAL1770QueryForCustomer extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1770QueryForCustomer MY_INSTANCE = new NWAL1770QueryForCustomer();

    /**
     * Constructor.
     */
    private NWAL1770QueryForCustomer() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1770Query
     */
    public static NWAL1770QueryForCustomer getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Bill To Customer Address
     * @param bizMsg NWAL1770CMsg
     * @param billToCustCd Bill To Customer Code
     * @return Bill To Customer Address
     */
    public S21SsmEZDResult getBillToCustAddr(NWAL1770CMsg bizMsg, String billToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("billToCustCd", billToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getBillToCustAddr", params);
    }

    /**
     * Get Ship To Customer Address
     * @param bizMsg NWAL1770CMsg
     * @param shipToCustCd Ship To Customer Code
     * @return Ship To Customer Address
     */
    public S21SsmEZDResult getShipToCustAddr(NWAL1770CMsg bizMsg, String shipToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", shipToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getShipToCustAddr", params);
    }

    /**
     * Get Bill To Customer Info List
     * @param bizMsg NWAL1770CMsg
     * @param custLocNum Customer location Number
     * @return Bill To Customer Info List
     */
    public S21SsmEZDResult getBillToCustInfoList(NWAL1770CMsg bizMsg, String custLocNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("custCd", custLocNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getBillToCustInfoList", params);
    }

    /**
     * Get Ship To Customer Info List
     * @param bizMsg NWAL1770CMsg
     * @return Ship To Customer Info List
     */
    public S21SsmEZDResult getShipToCustInfoList(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", bizMsg.shipToCustCd.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getShipToCustInfoList", params);
    }

    /**
     * Get Ds Account Reference Attribute List
     * @param bizMsg NWAL1770CMsg
     * @param dsAcctNum Account Number
     * @return Ds Account Reference Attribute List
     */
    public S21SsmEZDResult getDsAcctRefAttrb(NWAL1770CMsg bizMsg, String dsAcctNum) {

        if (!ZYPCommonFunc.hasValue(dsAcctNum)) {
            return null;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObjectList("getDsAcctRefAttrb", params);
    }

    // Add Start 2017/12/20 QC#20164(L3 Sol#356)
    /**
     * Get Contact Type 
     * @param bizMsg NWAL1770CMsg
     * @return Contact Type
     */
    public S21SsmEZDResult getContactType(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("ordCatgCtxTpCd",CTAC_ROLE_MND_SET);
        params.put("dsOrdCatgCd", bizMsg.splyQuoteCatgCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("bizId", BIZ_ID);

        return getSsmEZDClient().queryObject("getContactType", params);
    }
    // Add Enhd 2017/12/20 QC#20164(L3 Sol#356)
}
