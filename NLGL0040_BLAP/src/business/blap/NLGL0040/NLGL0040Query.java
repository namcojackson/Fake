/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLGL0040;

import java.util.Map;

import business.blap.NLGL0040.constant.NLGL0040Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Ship Via Mapping from HOST to WMS
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/12/2013     CSAI            N.Sekine          Create             MW Replace Initial
 *</pre>
 */
public final class NLGL0040Query extends S21SsmEZDQuerySupport implements NLGL0040Constant {

    /**
     * Singleton instance.
     */
    private static final NLGL0040Query MY_INSTANCE = new NLGL0040Query();

    /**
     * Constructor.
     */
    private NLGL0040Query() {

        super();
    }

    /**
     * Singleton instance getter.
     * @return NLGL0040Query
     */
    public static NLGL0040Query getInstance() {

        return MY_INSTANCE;
    }

    /**
     * statement id : getWHPullDownList
     * to get the WH code and WH name for pulldouwn list from DB
     * @param bizMsg ALGL040CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHPullDownList(NLGL0040CMsg bizMsg, Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWHPullDownList", ssmParam);

    }

    /**
     * statement id : getSHIPVIAList
     * to get the Ship Via List's data for ship via list screen from DB
     * @param bizMsg ALGL040CMsg
     * @param ssmParam Map<String, Object>
     * @param globalMsg NLGL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSHIPVIAList(NLGL0040CMsg bizMsg, Map<String, Object> ssmParam, NLGL0040SMsg globalMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getSHIPVIAList", ssmParam, globalMsg.A);
    }

    /**
     * statement id : getSHIPVIAEdit
     * to get the Ship Via List's specific data for ship via edit screen from DB
     * @param bizMsg ALGL040CMsg
     * @param ssmParam Map<String, Object>
     * @param globalMsg NLGL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSHIPVIAEdit(NLGL0040CMsg bizMsg, Map<String, Object> ssmParam, NLGL0040SMsg globalMsg) {

        return getSsmEZDClient().queryEZDMsg("getSHIPVIAEdit", ssmParam, globalMsg);
    }
}
