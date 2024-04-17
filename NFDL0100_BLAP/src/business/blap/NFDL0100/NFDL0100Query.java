package business.blap.NFDL0100;

import java.util.Map;

import business.blap.NFDL0100.constant.NFDL0100Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   CSAI            K.Lee           Create          N/A
 * 2022/08/03   CITS            D.Mamaril       Update          QC#60294
 *</pre>
 */
public class NFDL0100Query extends S21SsmEZDQuerySupport implements NFDL0100Constant {

    /**
     * Singleton instance.
     */
    private static final NFDL0100Query myInstance = new NFDL0100Query();

    /**
     * Constructor
     */
    public NFDL0100Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0100Query
     */
    public static NFDL0100Query getInstance() {
        return myInstance;
    }

    /**
     * @param map Map
     * @param sMsg NFDL0100SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrintInvoiceList(Map map, NFDL0100SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getPrintInvoiceList", map, sMsg.A);
    }

    /**
     * @param map Map
     * @param sMsg NFDL0100SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToCustName(Map map) {
        return getSsmEZDClient().queryObject("getBillToCustName", map);
    }
}
