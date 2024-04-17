/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2011/10/27   CSAI           N.Sasaki         Create          362045
 *</pre>
 */
package business.blap.NPAL0100;

import java.util.HashMap;
import java.util.Map;

import business.blap.NPAL0100.constant.NPAL0100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NPAL0100Query extends S21SsmEZDQuerySupport implements NPAL0100Constant {

    /**
     * Singleton instance.
     */
    private static final NPAL0100Query myInstance = new NPAL0100Query();

    /**
     * Constructor.NPAL0100Query
     */
    private NPAL0100Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL0100Query
     */
    public static NPAL0100Query getInstance() {
        return myInstance;
    }

    /**
     * Search getOriginalRecord
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOriginalRecord(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObjectList("getOriginalRecord", ssmParam);
    }

    /**
     * Search getOldSerNum
     * @param ssmParam Map
     * @return getOldSerNum
     */
    public String getOldSerNum(Map<String, String> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getOldSerNum", ssmParam);
        return (String) result.getResultObject();
    }

    /**
     * Search checkIntangibleItem
     * @param bizMsg NPAL0100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkIntangibleItem(NPAL0100CMsg bizMsg) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        map.put("mdseCd", bizMsg.mdseCd.getValue());
        map.put("flgN", ZYPConstant.FLG_OFF_N);
        map.put("rgtnStsCd", "P20");

        return getSsmEZDClient().queryEZDMsg("checkIntangibleItem", map, bizMsg);
    }

    /**
     * Search SHPG_PLN
     * @param cMsg NPAL0100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShpgPlnInfo(NPAL0100CMsg bizMsg) {
        return getSsmEZDClient().queryEZDMsg("getShpgPlnInfo", bizMsg, bizMsg);
    }

    /**
     * Search PO_DTL
     * @param cMsg NPAL0100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoDtlInfo(NPAL0100CMsg bizMsg) {
        return getSsmEZDClient().queryEZDMsg("getPoDtlInfo", bizMsg, bizMsg);
    }

    /**
     * Search getRownums
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRownums(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRownums", ssmParam);
    }

}
