/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0330;

import static business.blap.NSBL0330.constant.NSBL0330Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_RQST_CRIT_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Service Request By Summary Criteria
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/02   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public final class NSBL0330Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NSBL0330Query MY_INSTANCE = new NSBL0330Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NSBL0330Query() {
        super();
    }

    /**
     * <pre>
     * Get the NSBL0330Query instance.
     * </pre>
     * @return NSBL0330Query instance
     */
    public static NSBL0330Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getMgrList
     * @param cMsg NSBL0330CMsg
     * @param sMsg NSBL0330SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMgrList(NSBL0330CMsg cMsg, NSBL0330SMsg sMsg) {
        Map<String, Object> queryParam = createFindCondMap(cMsg);
        queryParam.put("rowNum", sMsg.A.length() + 1);
        return getSsmEZDClient().queryEZDMsgArray("getMgrList", queryParam, sMsg.A);
    }

    /**
     * getDrillDownList
     * @param cMsg NSBL0330CMsg
     * @param svcMgrPsnCdList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDrillDownList(NSBL0330CMsg cMsg, List<String> svcMgrPsnCdList) {
        Map<String, Object> queryParam = createFindCondMap(cMsg);
        queryParam.put("svcMgrPsnCdList", svcMgrPsnCdList);
        return getSsmEZDClient().queryObjectList("getDrillDownList", queryParam);
    }

    /**
     * getSearchList
     * @param cMsg NSBL0330CMsg
     * @param svcMgrPsnCdList List<String>
     * @param dsSvcCallTpCdList List<String>
     * @param fsrSvcTaskStsRelnPkList List<BigDecimal>
     * @param techCdList List<String>
     * @param svcCallSrcTpCdList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchList(NSBL0330CMsg cMsg, List<String> svcMgrPsnCdList, List<String> dsSvcCallTpCdList, List<BigDecimal> fsrSvcTaskStsRelnPkList, List<String> techCdList, List<String> svcCallSrcTpCdList) {
        Map<String, Object> queryParam = createFindCondMap(cMsg);
        queryParam.put("svcMgrPsnCdList", svcMgrPsnCdList);
        queryParam.put("dsSvcCallTpCdList", dsSvcCallTpCdList);
        queryParam.put("fsrSvcTaskStsRelnPkList", fsrSvcTaskStsRelnPkList);
        queryParam.put("techCdList", techCdList);
        queryParam.put("svcCallSrcTpCdList", svcCallSrcTpCdList);
        queryParam.put("comma", COMMA);
        queryParam.put("hyphen", HYPHEN);
        queryParam.put("slash", SLASH);
        return getSsmEZDClient().queryObjectList("getSearchList", queryParam);
    }

    private Map<String, Object> createFindCondMap(NSBL0330CMsg cMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("orgCd", cMsg.orgCd_I.getValue());
        queryParam.put("mgrOrgLayerNum", cMsg.orgLayerNum_I.getValue());
        queryParam.put("svcMgrTpCd", cMsg.svcMgrTpCd_I.getValue());
        queryParam.put("svcMgrPsnCd", cMsg.svcMgrPsnCd_I.getValue());
        queryParam.put("svcRqstCritTpCd", cMsg.svcRqstCritTpCd_I.getValue());
        queryParam.put("svcRqstDownTpCd", cMsg.svcRqstDownTpCd_I.getValue());
        String svcRqstCritTpCd = cMsg.svcRqstCritTpCd_I.getValue();
        if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.TASK_TYPES)) {
            List<String> dsSvcCallTpCdList = new ArrayList<String>(cMsg.I.getValidCount());
            for (int i = 0; i < cMsg.I.getValidCount(); i++) {
                dsSvcCallTpCdList.add(cMsg.I.no(i).dsSvcCallTpCd_I.getValue());
            }
            queryParam.put("dsSvcCallTpCdList", dsSvcCallTpCdList);
        } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.SR_OR_TASK_STATUS)) {
            List<BigDecimal> fsrSvcTaskStsRelnPkList = new ArrayList<BigDecimal>(cMsg.I.getValidCount());
            for (int i = 0; i < cMsg.I.getValidCount(); i++) {
                fsrSvcTaskStsRelnPkList.add(cMsg.I.no(i).fsrSvcTaskStsRelnPk_I.getValue());
            }
            queryParam.put("fsrSvcTaskStsRelnPkList", fsrSvcTaskStsRelnPkList);
        } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.ASSIGNEE)) {
            List<String> techCdList = new ArrayList<String>(cMsg.I.getValidCount());
            for (int i = 0; i < cMsg.I.getValidCount(); i++) {
                techCdList.add(cMsg.I.no(i).techCd_I.getValue());
            }
            queryParam.put("techCdList", techCdList);
        } else if (svcRqstCritTpCd.equals(SVC_RQST_CRIT_TP.CANNEL)) {
            List<String> svcCallSrcTpCdList = new ArrayList<String>(cMsg.I.getValidCount());
            for (int i = 0; i < cMsg.I.getValidCount(); i++) {
                svcCallSrcTpCdList.add(cMsg.I.no(i).svcCallSrcTpCd_I.getValue());
            }
            queryParam.put("svcCallSrcTpCdList", svcCallSrcTpCdList);
        }
        return queryParam;
    }
}
