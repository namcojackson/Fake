/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1540;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1540 Manual ASN Entry
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/19/2016   CITS        Makoto Okigami      Create          N/A
 * 
 *</pre>
 */
public final class NPAL1540Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPAL1540Query MY_INSTANCE = new NPAL1540Query();

    /**
     * Constructor.
     */
    private NPAL1540Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1540Query
     */
    public static NPAL1540Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1540SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NPAL1540SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }

    /**
     * Search Serial
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1540SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchSerial(Map<String, Object> ssmParam, NPAL1540SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchSerial", ssmParam, sMsg.S);
    }

    /**
     * Get PO Qty
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoQty(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getPoQty", ssmParam);
    }

    /**
     * Get ASN Carrier
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAsnCarrCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getAsnCarrCd", ssmParam);
    }

    /**
     * Get Supplier Item
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupplierItem(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getSupplierItem", ssmParam);
    }

    /**
     * Count EDI ASN Header
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countEdiAsnHdr(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("countEdiAsnHdr", ssmParam);
    }

    /**
     * Get PO Header
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoHdr(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getPoHdr", ssmParam);
    }

    /**
     * Get PO Detail
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoDtl(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPoDtl", ssmParam);
    }

    /**
     * Get CCY
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCcy(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getCcy", ssmParam);
    }

}
