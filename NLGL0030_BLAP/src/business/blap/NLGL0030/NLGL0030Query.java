/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0030;

import java.util.Map;

import business.blap.NLGL0030.constant.NLGL0030Constant;

import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * ForcedItem Master download
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/12/2013     CSAI            M.Shimamura       Create            MW Replace Initial
 *</pre>
 */
public final class NLGL0030Query extends S21SsmEZDQuerySupport implements NLGL0030Constant {

    /**
     * Singleton instance.
     */
    private static final NLGL0030Query MYINSTANCE = new NLGL0030Query();

    /**
     * Constructor.
     */
    private NLGL0030Query() {

        super();
    }

    /**
     * Singleton instance getter.
     * @return NLGL0030Query
     */
    public static NLGL0030Query getInstance() {

        return MYINSTANCE;
    }

    /**
     * Get the WH code and WH name for pulldouwn list from DB (With
     * All)
     * @param bizMsg NLGL0030CMsg
     * @param ssmParam NLGL0030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHPullDownListHistScrn(NLGL0030CMsg bizMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWHPullDownListHistScrn", ssmParam);
    }

    /**
     * Get the WH code and WH name for pulldouwn list from DB
     * @param bizMsg NLGL0030CMsg
     * @param ssmParam NLGL0030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHPullDownListDnldScrn(NLGL0030CMsg bizMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWHPullDownListDnldScrn", ssmParam);
    }

    /**
     * Get the WMS_MDSE for Histry Tab
     * @param bizMsg NLGL0030CMsg
     * @param ssmParam Map<String, Object>
     * @param globalMsg NLGL0030SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseHistList(NLGL0030CMsg bizMsg, Map<String, Object> ssmParam, NLGL0030SMsg globalMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getMdseHistList", ssmParam, globalMsg.A);
    }

    /**
     * Get the WMS_MDSE for Download Tab
     * @param bizMsg NLGL0030CMsg
     * @param ssmParam Map<String, Object>
     * @param globalMsg NLGL0030SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseDnldList(NLGL0030CMsg bizMsg, Map<String, Object> ssmParam, NLGL0030SMsg globalMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getMdseDnldList", ssmParam, globalMsg.B);
    }

    /**
     * Count MDSE Number
     * @param ssmParam ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult cntMdse(Map<String, String> ssmParam) {

        return getSsmEZDClient().queryObject("cntMdse", ssmParam);
    }
}
