/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1670;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_TEAM_ATTRB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Order Team Set up
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public final class NWAL1670Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1670Query INSTANCE = new NWAL1670Query();

    /**
     * Constructor.
     */
    private NWAL1670Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1670Query
     */
    public static NWAL1670Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NWAL1670CMsg
     * @param sMsg NWAL1670SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("ordTeamMstrNm", cMsg.ordTeamMstrNm.getValue());
        params.put("ordZnCd", cMsg.ordZnCd.getValue());
        params.put("xxChkBox", cMsg.xxChkBox.getValue());
        params.put("slsDt", cMsg.slsDt.getValue());
        params.put("limitCnt", cnt);

        return getSsmEZDClient().queryEZDMsgArray("getSearchData", params, sMsg.A);
    }

    /**
     * getMemberData
     * @param cMsg NWAL1670CMsg
     * @param rowNum int
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMemberData(NWAL1670CMsg cMsg, int rowNum, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("ordTeamMstrHdrPk", cMsg.A.no(rowNum).ordTeamMstrHdrPk_A.getValue());
        params.put("ordTeamAttrbTp_UserId", ORD_TEAM_ATTRB_TP.USER_ID);
        params.put("limitCnt", cnt);

        return getSsmEZDClient().queryEZDMsgArray("getMemberData", params, cMsg.B);
    }

    /**
     * getAttributeData
     * @param cMsg NWAL1670CMsg
     * @param sMsg NWAL1670SMsg
     * @param rowNum int
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAttributeData(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, int rowNum, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("ordTeamMstrHdrPk", cMsg.A.no(rowNum).ordTeamMstrHdrPk_A.getValue());
        params.put("ordTeamAttrbTp_UserId", ORD_TEAM_ATTRB_TP.USER_ID);
        params.put("ordTeamAttrbTp_CustNum", ORD_TEAM_ATTRB_TP.CUSTOMER_NUMBER);
        params.put("ordTeamAttrbTp_LineBiz", ORD_TEAM_ATTRB_TP.LINE_OF_BUSINESS);
        params.put("ordTeamAttrbTp_branch", ORD_TEAM_ATTRB_TP.BRANCH);
        params.put("rgtnStsCd_ReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("slsDt", cMsg.slsDt.getValue());
        params.put("limitCnt", cnt);

        return getSsmEZDClient().queryEZDMsgArray("getAttributeData", params, sMsg.C);
    }

    /**
     * getDsAcctCust
     * @param cMsg NWAL1670CMsg
     * @param sMsg NWAL1670SMsg
     * @param rowNum int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctCust(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsAcctNum", sMsg.C.no(rowNum).ordTeamAttrbValTxt_C.getValue());
        params.put("rgtnStsCd_ReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("slsDt", cMsg.slsDt.getValue());

        return getSsmEZDClient().queryObjectList("getDsAcctCust", params);
    }

    /**
     * getSsmParam
     * @param cMsg NWAL1670CMsg
     * @param sMsg NWAL1670SMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();

        // TODO
        return params;
    }

}
