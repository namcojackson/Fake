/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6850;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * NMAL6850 Supplier Search
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/26   CITS            M.Ouchi         Create          N/A
 * 2020/02/28   Fujitsu         C.Hara          Update          QC#55971
 * </pre>
 */
public class NMAL6850Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6850Query INSTANCE = new NMAL6850Query();

    /**
     * Constructor.
     */
    public NMAL6850Query() {
        super();
    }

    /**
     * <p>
     * Gets the singleton instance.
     * </p>
     * @return the instance of NMAL6850Query
     */
    public static NMAL6850Query getInstance() {
        return INSTANCE;
    }

    /**
     * <p>
     * Searches Suppliers.
     * </p>
     * @param cMsg scrnMsg
     * @param sMsg glblMsg
     * @return result
     */
    public S21SsmEZDResult searchSuppliers(NMAL6850CMsg cMsg, NMAL6850SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd);
        params.put("prntVndCd", cMsg.prntVndCd_H);
        params.put("splyNm", cMsg.splyNm_H);
        // 2020/02/28 QC#55971 Mod Start
        // params.put("splyTpCd", cMsg.splyTpCd_H);
        params.put("prntVndTpDescTxt", cMsg.prntVndTpDescTxt_H);
        // 2020/002/28 QC#55971 Mod End

        return getSsmEZDClient().queryEZDMsgArray("searchSuppliers", params, sMsg.A);
    }
}
