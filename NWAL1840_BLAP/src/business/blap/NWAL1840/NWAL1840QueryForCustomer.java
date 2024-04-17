/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         T.Murai         Create          N/A
 * </pre>
 */
public final class NWAL1840QueryForCustomer extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1840QueryForCustomer MY_INSTANCE = new NWAL1840QueryForCustomer();

    /**
     * Constructor.
     */
    private NWAL1840QueryForCustomer() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1840Query
     */
    public static NWAL1840QueryForCustomer getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Bill To Customer Address
     * @param bizMsg NWAL1840CMsg
     * @param billToCustCd Bill To Customer Code
     * @return Bill To Customer Address
     */
    public S21SsmEZDResult getBillToCustAddr(NWAL1840CMsg bizMsg, String billToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("billToCustCd", billToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getBillToCustAddr", params);
    }

    /**
     * Get Ship To Customer Address
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
     * Get Bill To Customer Info List
     * @param bizMsg NWAL1840CMsg
     * @param custLocNum Customer location Number
     * @return Bill To Customer Info List
     */
    public S21SsmEZDResult getBillToCustInfoList(NWAL1840CMsg bizMsg, String custLocNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("custCd", custLocNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getBillToCustInfoList", params);
    }

    /**
     * Get Ship To Customer Info List
     * @param bizMsg NWAL1840CMsg
     * @return Ship To Customer Info List
     */
    public S21SsmEZDResult getShipToCustInfoList(NWAL1840CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", bizMsg.shipToCustLocCd.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getShipToCustInfoList", params);
    }

    /**
     * Get Ds Account Reference Attribute List
     * @param bizMsg NWAL1840CMsg
     * @param dsAcctNum Account Number
     * @return Ds Account Reference Attribute List
     */
    public S21SsmEZDResult getDsAcctRefAttrb(NWAL1840CMsg bizMsg, String dsAcctNum) {

        if (!ZYPCommonFunc.hasValue(dsAcctNum)) {
            return null;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObjectList("getDsAcctRefAttrb", params);
    }
}
