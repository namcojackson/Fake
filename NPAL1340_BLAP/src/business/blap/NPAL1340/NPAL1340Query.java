/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */

package business.blap.NPAL1340;

import java.util.Map;

import business.blap.NPAL1340.NPAL1340CMsg;
import business.blap.NPAL1340.NPAL1340Query;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * NPAL1340 Drop Ship Release
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/29   CSAI            K.Lee           Create          N/A
 * 09/20/2017   CITS            T.Tokutomi      Update          QC#21191
 *</pre>
 */
public final class NPAL1340Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPAL1340Query MYINSTANCE = new NPAL1340Query();

    /**
     * Constructor.
     */
    private NPAL1340Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1340Query
     */
    public static NPAL1340Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * searchPoNumber
     * @param ssmParam Map<String, Object>
     * @param bizMsg NPAL1340CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPoNumber(Map<String, Object> ssmParam, NPAL1340SMsg globalMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchPoNumber", ssmParam, globalMsg.A);
    }

    /**
     * Search searchPoNumberList
     * @param ssmParam Map<String, Object>
     * @param bizMsg NPAL1340CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPoNumberList(Map<String, Object> ssmParam, NPAL1340CMsg bizMsg) {
        return getSsmEZDClient().queryObjectList("searchPoNumberList", ssmParam);
    }

    /**
     * searchPoSerialList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPoSerialList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchPoSerialList", ssmParam);
    }

    /**
     * countCarrier
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countCarrier(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("countCarrier", ssmParam);
    }

    /**
     * getCarrier
     * QC#21191 Add Method.
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrier(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getCarrier", ssmParam);
    }
}
