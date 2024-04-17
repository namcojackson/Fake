/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0510;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL0510.constant.NSAL0510Constant.QUERY_PRM_ID;
import business.db.DS_CONTR_CTRL_STSTMsg;
import business.db.DS_CONTR_CTRL_STSTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;

/**
 *<pre>
 * Sub Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/01/08   Hitachi         T.Tsuchida      Update          QC#2844
 * 2016/01/08   Hitachi         T.Tomita        Update          QC#3710
 * 2017/03/06   Hitachi         K.Kojima        Update          QC#17894
 * 2017/11/16   Hitachi         K.Kojima        Update          QC#21886
 *</pre>
 */
public final class NSAL0510Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0510Query INSTANCE = new NSAL0510Query();

    /**
     * Private constructor
     */
    private NSAL0510Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0510Query singleton instance
     */
    public static NSAL0510Query getInstance() {
        return INSTANCE;
    }


    /**
     * getSearchData
     * @param cMsg NSAL0510CMsg
     * @param sMsg NSAL0510SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0510CMsg cMsg, NSAL0510SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg), sMsg.A);

    }

    /**
     * getSsmParam
     * @param cMsg NSAL0510CMsg
     * @param sMsg NSAL0510SMsg
     * @return Map<String, Object>
     */
    public Map<String, Object> getSsmParam(NSAL0510CMsg cMsg, NSAL0510SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), cMsg.glblCmpyCd.getValue());
        params.put(QUERY_PRM_ID.SLS_DT.getQueryPrm(), cMsg.slsDt.getValue());
        params.put(QUERY_PRM_ID.VND_CD.getQueryPrm(), cMsg.vndCd_H.getValue());
        params.put(QUERY_PRM_ID.DS_ACCT_NUM.getQueryPrm(), cMsg.dsAcctNum_H.getValue());
        params.put(QUERY_PRM_ID.DS_CONTR_NUM.getQueryPrm(), cMsg.dsContrNum_H.getValue());
        // START 2017/11/16 K.Kojima [QC#21886,MOD]
        // params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS.getQueryPrm(), cMsg.dsContrCtrlStsNm_H.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.dsContrCtrlStsNm_H)) {
            List<String> statusList = getSameNameCtrlSts(cMsg.glblCmpyCd.getValue(), cMsg.dsContrCtrlStsNm_H.getValue());
            if (statusList.size() > 1) {
                params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS_LIST.getQueryPrm(), statusList);
            } else {
                params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS.getQueryPrm(), cMsg.dsContrCtrlStsNm_H.getValue());
            }
        }
        // END 2017/11/14 K.Kojima [QC#21886,MOD]
        // END 2017/11/16 K.Kojima [QC#21886,MOD]
        params.put(QUERY_PRM_ID.DLR_FLEET_NUM.getQueryPrm(), cMsg.dlrFleetNum_H.getValue());
        params.put(QUERY_PRM_ID.SER_NUM.getQueryPrm(), cMsg.serNum_H.getValue());
        params.put(QUERY_PRM_ID.MDL_ACTV_FLG.getQueryPrm(), cMsg.mdlActvFlg_H.getValue());
        params.put(QUERY_PRM_ID.T_MDL_NM.getQueryPrm(), cMsg.t_MdlNm_H.getValue());
        params.put(QUERY_PRM_ID.CONTR_TRMN_FLG.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        // START 2016/03/03 T.Tomita [QC#3710, DEL]
//        params.put(QUERY_PRM_ID.VND_TP_CD_IS_SVC_DEALER.getQueryPrm(), VND_TP.SERVICE_DEALER);
        // END 2016/03/03 T.Tomita [QC#3710, DEL]
        // START 2017/03/06 K.Kojima [QC#17894,ADD]
        params.put(QUERY_PRM_ID.VND_TP_CD_IS_SVC_DEALER.getQueryPrm(), VND_TP.SERVICE_DEALER);
        // END 2017/03/06 K.Kojima [QC#17894,ADD]
        return params;

    }

    // START 2017/11/16 K.Kojima [QC#21886,DEL]
    // /**
    //  * Find Contract Control Status Code PulldownList
    //  * @param glblCmpyCd String
    //  * @return DS_CONTR_CTRL_STSTMsgArray
    //  */
    // public DS_CONTR_CTRL_STSTMsgArray findContrCtrlStsCdPulldownList(String glblCmpyCd) {
    //     DS_CONTR_CTRL_STSTMsg inMsg = new DS_CONTR_CTRL_STSTMsg();
    //     inMsg.setSQLID("201");
    //     inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
    //     inMsg.setConditionValue("dsContrDtlStsCd01", DS_CONTR_DTL_STS.ORDER);
    //     return (DS_CONTR_CTRL_STSTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    // }
    // END 2017/11/16 K.Kojima [QC#21886,DEL]

    // START 2017/11/16 K.Kojima [QC#21886,ADD]
    /**
     * getContrCtrlStsCdPulldownList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrCtrlStsCdPulldownList(String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrCtrlStsOrdr", DS_CONTR_CTRL_STS.ORDER);
        return getSsmEZDClient().queryObjectList("getContrCtrlStsCdPulldownList", params);
    }

    /**
     * getSameNameCtrlSts
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public List<String> getSameNameCtrlSts(String glblCmpyCd, String dsContrCtrlStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrCtrlStsCd", dsContrCtrlStsCd);
        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getSameNameCtrlSts", params);
        if (!result.isCodeNormal()) {
            return new ArrayList<String>();
        }
        return (List<String>) result.getResultObject();
    }

    // END 2017/11/16 K.Kojima [QC#21886,ADD]

}
