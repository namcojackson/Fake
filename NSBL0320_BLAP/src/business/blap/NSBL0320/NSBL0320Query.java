/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0320;

import static business.blap.NSBL0320.constant.NSBL0320Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.SVC_RQST_DOWN_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_RQST_CRIT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_RQST_DOWN_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Select SR Summary Criteria
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public final class NSBL0320Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0320Query INSTANCE = new NSBL0320Query();

    /**
     * Constructor.
     */
    private NSBL0320Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0320Query
     */
    public static NSBL0320Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSBL0320CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSBL0320CMsg cMsg) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());

        String pulldownVal = cMsg.svcRqstCritTpCd.getValue();

        S21SsmEZDResult result = null;
        if (pulldownVal.equals(SVC_RQST_CRIT_TP.TASK_TYPES)) {
            result = getSsmEZDClient().queryEZDMsgArray("getSearchDataCLTP", getSsmParamCom(cMsg), cMsg.A);

        } else if (pulldownVal.equals(SVC_RQST_CRIT_TP.SR_OR_TASK_STATUS)) {
            result = getSsmEZDClient().queryObjectList("getSearchDataSTS", getSsmParam(cMsg, pulldownVal));

        } else if (pulldownVal.equals(SVC_RQST_CRIT_TP.ASSIGNEE)) {
            result = getSsmEZDClient().queryObjectList("getSearchDataASGN", getSsmParam(cMsg, pulldownVal));

        } else if (pulldownVal.equals(SVC_RQST_CRIT_TP.CANNEL)) {
            result = getSsmEZDClient().queryObjectList("getSearchDataCNNL", getSsmParamCom(cMsg));

        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
            return result;
        }
        return result;
    }

    /**
     * getSsmParam
     * @param cMsg NSBL0320CMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParamCom(NSBL0320CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orgCd", cMsg.orgCd.getValue());
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("orgLayerNum", cMsg.orgLayerNum.getValue());
        params.put("svcMgrTpCd", cMsg.svcMgrTpCd.getValue());
        params.put("svcRqstDownTpCd", cMsg.svcRqstDownTpCd.getValue());
        params.put("svcMgrPsnCd", cMsg.svcMgrPsnCd.getValue());
        params.put("limitCnt", cMsg.A.length() + 1);

        return params;
    }

    /**
     * getSsmParam
     * @param cMsg NSBL0320CMsg
     * @param val String
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSBL0320CMsg cMsg, String val) {
        Map<String, Object> params = new HashMap<String, Object>();

        BigDecimal orgLayerNum = cMsg.orgLayerNum.getValue();

        params.put("orgCd", cMsg.orgCd.getValue());
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("orgLayerNum", cMsg.orgLayerNum.getValue());
        params.put("svcMgrTpCd", cMsg.svcMgrTpCd.getValue());
        params.put("svcRqstDownTpCd", cMsg.svcRqstDownTpCd.getValue());
        params.put("svcMgrPsnCd", cMsg.svcMgrPsnCd.getValue());
        params.put("limitCnt", cMsg.A.length() + 1);

        if (val.equals(SVC_RQST_CRIT_TP.SR_OR_TASK_STATUS)) {

            SVC_RQST_DOWN_TPTMsg svcRqsFlgColNmTMsg = (SVC_RQST_DOWN_TPTMsg) ZYPCodeDataUtil.findByCode(SVC_RQST_DOWN_TP.class, cMsg.glblCmpyCd.getValue(), cMsg.svcRqstDownTpCd.getValue());
            params.put("colNm", svcRqsFlgColNmTMsg.svcRqstDownFlgColNm.getValue());

        } else if (val.equals(SVC_RQST_CRIT_TP.ASSIGNEE)) {
            if (ORG_LAYER_NUM_1.equals(orgLayerNum)) {
                params.put("firstOrgCd", NOT_NULL);
            } else if (ORG_LAYER_NUM_2.equals(orgLayerNum)) {
                params.put("scdOrgCd", NOT_NULL);
            } else if (ORG_LAYER_NUM_3.equals(orgLayerNum)) {
                params.put("thirdOrgCd", NOT_NULL);
            } else if (ORG_LAYER_NUM_4.equals(orgLayerNum)) {
                params.put("frthOrgCd", NOT_NULL);
            } else if (ORG_LAYER_NUM_5.equals(orgLayerNum)) {
                params.put("fifthOrgCd", NOT_NULL);
            } else if (ORG_LAYER_NUM_6.equals(orgLayerNum)) {
                params.put("sixthOrgCd", NOT_NULL);
            } else if (ORG_LAYER_NUM_7.equals(orgLayerNum)) {
                params.put("svnthOrgCd", NOT_NULL);
            } else if (ORG_LAYER_NUM_8.equals(orgLayerNum)) {
                params.put("eightOrgCd", NOT_NULL);
            } else if (ORG_LAYER_NUM_9.equals(orgLayerNum)) {
                params.put("ninthOrgCd", NOT_NULL);
            } else if (ORG_LAYER_NUM_10.equals(orgLayerNum)) {
                params.put("tenthOrgCd", NOT_NULL);
            } else if (ORG_LAYER_NUM_11.equals(orgLayerNum)) {
                params.put("elvthOrgCd", NOT_NULL);
            }
        }
        return params;
    }
}
