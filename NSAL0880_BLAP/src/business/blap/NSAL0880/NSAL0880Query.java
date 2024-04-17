/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0880;

import java.util.HashMap;
import java.util.Map;

import business.blap.NSAL0880.constant.NSAL0880Constant;
import business.blap.NSAL0880.constant.NSAL0880Constant.QUERY_PRM_ID;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public final class NSAL0880Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0880Query INSTANCE = new NSAL0880Query();

    /**
     * Private constructor
     */
    private NSAL0880Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0880Query singleton instance
     */
    public static NSAL0880Query getInstance() {
        return INSTANCE;
    }


    /**
     * getSearchData
     * @param cMsg NSAL0880CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMtrIntfc(NSAL0880CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getMtrIntfc", getSsmParam(cMsg), cMsg.A);

    }

    /**
     * getSsmParam
     * @param cMsg NSAL0880CMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSAL0880CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), cMsg.glblCmpyCd.getValue());
        params.put(QUERY_PRM_ID.SVC_MACH_MSTR_PK.getQueryPrm(), cMsg.svcMachMstrPk.getValue());
        params.put(QUERY_PRM_ID.SVC_PHYS_MTR_READ_GRP_SQ.getQueryPrm(), cMsg.svcPhysMtrReadGrpSq.getValue());
        params.put(QUERY_PRM_ID.DS_MSG_TRX_NM.getQueryPrm(), NSAL0880Constant.DS_MTR_INTFC_PK);
        params.put(QUERY_PRM_ID.ROW_NUM.getQueryPrm(), NSAL0880Constant.SEARCH_LIMIT_CNT);

        return params;

    }
}
