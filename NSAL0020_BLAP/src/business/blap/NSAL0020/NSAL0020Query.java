/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0020;

import static business.blap.NSAL0020.constant.NSAL0020Constant.*;

import java.util.HashMap;
import java.util.Map;

//import business.blap.NSAL0020.constant.NSAL0020Constant.QUERY_PRM_ID;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi        T.Yonekura         Create          N/A
 * 2015/10/19   Hitachi        Y.Tsuchimoto       Update          NA(No Mark up comment)
 * 2018/03/05   Hitachi         K.Kojima        Update          QC#24477
 *</pre>
 */
public final class NSAL0020Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance
     */
    private static final NSAL0020Query INSTANCE = new NSAL0020Query();

    /**
     * Private constructor
     */
    private NSAL0020Query() {
        super();
    }

    /**
     * Singleton instance getter
     * @return NSAL0020Query
     */
    public static NSAL0020Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSvcMachMstrStsTpList
     * @param bizMsg NSAL0020CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachMstrStsTpList(NSAL0020CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getSvcMachMstrStsTpList", ssmParam);
    }

    /**
     * getCoaMdseTpCdList
     * @param bizMsg NSAL0020CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCoaMdseTpCdList(NSAL0020CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getCoaMdseTpCdList", ssmParam);
    }

    /**
     * getSvcCtacTpCdList
     * @param bizMsg NSAL0020CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcCtacTpCdList(NSAL0020CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getSvcCtacTpCdList", ssmParam);
    }

    /**
     * getSavedSearchOptionList
     * @param userId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        params.put(PARAM_SRCH_OPT_APL_ID, APL_ID);
        params.put(PARAM_SRCH_OPT_USR_ID, userId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }
    // Old NSAB0020 Start----------------------------------------
    /**
     * getBizTpPulldown
     * @param bizMsg NSAL0020CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBizTpPulldown(NSAL0020CMsg bizMsg) {
        return getSsmEZDClient().queryObjectList("getBizTpPulldown", bizMsg);
    }

    // START 2018/03/05 K.Kojima [QC#24477,DEL]
    // /**
    //  * getMachMstrInfoList
    //  * @param params HashMap<String, Object>
    //  * @param globalMsg NWBL1020SMsg
    //  * @return S21SsmEZDResult
    //  */
    // public S21SsmEZDResult getMachMstrInfoList(HashMap<String, Object> params, NSAL0020SMsg globalMsg) {
    //     return getSsmEZDClient().queryEZDMsgArray("getMachMstrInfoList", params, globalMsg.A);
    // }
    // END 2018/03/05 K.Kojima [QC#24477,DEL]

    // START 2018/03/05 K.Kojima [QC#24477,ADD]
    /**
     * getMachMstrInfoListForScreen
     * @param params HashMap<String, Object>
     * @param globalMsg NWBL1020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMachMstrInfoListForScreen(HashMap<String, Object> params, NSAL0020SMsg globalMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getMachMstrInfoListForScreen", params, globalMsg.A);
    }
    // END 2018/03/05 K.Kojima [QC#24477,ADD]
}
