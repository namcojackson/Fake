/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         T.Yoshida        Create          n/a
 * 2018/03/08   Fujitsu         S.Takami        Update          S21_NA#19808 (Cache for Mass Update, without any comments)
 * </pre>
 */
public final class NWAL1500QueryForCustomer extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NWAL1500QueryForCustomer MY_INSTANCE = new NWAL1500QueryForCustomer();

    /** Cache for getBillToCustAddr */
    private Map<Object, S21SsmEZDResult> getBillToCustAddrCache = new HashMap<Object, S21SsmEZDResult>();

    /**
     * Constructor.
     */
    private NWAL1500QueryForCustomer() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForCustomer getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get Bill To Customer Address
     * @param bizMsg NWAL1500CMsg
     * @param billToCustCd Bill To Customer Code
     * @return Bill To Customer Address
     */
    public S21SsmEZDResult getBillToCustAddr(NWAL1500CMsg bizMsg, String billToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("billToCustCd", billToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        S21SsmEZDResult ssmRslt = getBillToCustAddrCache.get(params);
        if (ssmRslt == null) {
            ssmRslt = getSsmEZDClient().queryObject("getBillToCustAddr", params);
            getBillToCustAddrCache.put(params, ssmRslt);
        }
        return ssmRslt;
    }

    /**
     * get Ship To Customer Address
     * @param bizMsg NWAL1500CMsg
     * @param shipToCustCd Ship To Customer Code
     * @return Ship To Customer Address
     */
    public S21SsmEZDResult getShipToCustAddr(NWAL1500CMsg bizMsg, String shipToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", shipToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getShipToCustAddr", params);
    }

    /**
     * get Bill To Customer Info List
     * @param bizMsg NWAL1500CMsg
     * @param custLocNum Customer location Number
     * @return Bill To Customer Info List
     */
    public S21SsmEZDResult getBillToCustInfoList(NWAL1500CMsg bizMsg, String custLocNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("custCd", custLocNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getBillToCustInfoList", params);
    }

    /**
     * get Ship To Customer Info List
     * @param bizMsg NWAL1500CMsg
     * @param custLocNum Customer location Number
     * @return Ship To Customer Info List
     */
    public S21SsmEZDResult getShipToCustInfoList(NWAL1500CMsg bizMsg, String custLocNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", custLocNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getShipToCustInfoList", params);
    }

}
