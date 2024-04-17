/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/15   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */

package business.blap.NWAL0300;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * NWAL0300Query
 */
public final class NWAL0300Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL0300Query MY_INSTANCE = new NWAL0300Query();

    /**
     * Constructor.
     */
    private NWAL0300Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL0300Query
     */
    public static NWAL0300Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getHld
     * @param cMsg NWAL0300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHld(NWAL0300CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("getHld", cMsg, cMsg);
    }

    /**
     * getCPOOrderInfo
     * @param cMsg NWAL0300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCPOOrderInfo(NWAL0300CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("getCPOOrderInfo", cMsg, cMsg);
    }

    /**
     * getCPODtlOrderInfo
     * @param cMsg NWAL0300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCPODtlOrderInfo(NWAL0300CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("getCPODtlOrderInfo", cMsg, cMsg);
    }

    /**
     * getCPOOrdTp
     * @param cMsg NWAL0300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCPOOrdTp(NWAL0300CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("getCPOOrdTp", cMsg, cMsg);
    }

    /**
     * getSellToCust
     * @param cMsg NWAL0300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSellToCust(NWAL0300CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("getSellToCust", cMsg, cMsg);
    }

    /**
     * getBillToCust
     * @param cMsg NWAL0300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToCust(NWAL0300CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("getBillToCust", cMsg, cMsg);
    }

    /**
     * getShipToCust
     * @param cMsg NWAL0300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCust(NWAL0300CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("getShipToCust", cMsg, cMsg);
    }

    /**
     * getMdse
     * @param ssmParam Map
     * @param cMsg NWAL0300CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdse(Map ssmParam, NWAL0300CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("getMdse", ssmParam, cMsg);
    }

}
