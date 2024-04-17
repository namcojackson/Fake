/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0420;

import static business.blap.NSAL0420.constant.NSAL0420Constant.CONTR_BR_FIRST_ORG_CD;
import static business.blap.NSAL0420.constant.NSAL0420Constant.SEARCH_LIMIT_CNT;
import static business.blap.NSAL0420.constant.NSAL0420Constant.THRU_DT_LIMIT;

import java.util.HashMap;
import java.util.Map;

import business.blap.NSAL0420.constant.NSAL0420Constant.QUERY_PRM_ID;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Representative Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Hitachi         T.Tsuchida      Create          N/A
 * 2017/01/24   Hitachi         N.Arai          Update          QC#17228
 * 2019/02/12   Hitachi         S.Kitamura      Update          QC#30264
 *</pre>
 */
public final class NSAL0420Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0420Query INSTANCE = new NSAL0420Query();

    /**
     * Private constructor
     */
    private NSAL0420Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0420Query singleton instance
     */
    public static NSAL0420Query getInstance() {
        return INSTANCE;
    }


    /**
     * getSearchData
     * @param cMsg NSAL0420CMsg
     * @param sMsg NSAL0420SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0420CMsg cMsg, NSAL0420SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg), sMsg.A);

    }

    /**
     * getSsmParam
     * @param cMsg NSAL0420CMsg
     * @param sMsg NSAL0420SMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSAL0420CMsg cMsg, NSAL0420SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), cMsg.glblCmpyCd.getValue());
        params.put(QUERY_PRM_ID.SLS_DT.getQueryPrm(), cMsg.slsDt.getValue());
        params.put(QUERY_PRM_ID.SVC_RG_NM.getQueryPrm(), cMsg.svcRgNm_H.getValue());
        params.put(QUERY_PRM_ID.LINE_BIZ_TP_DESC_TXT.getQueryPrm(), cMsg.lineBizTpDescTxt_H.getValue());
        params.put(QUERY_PRM_ID.SVC_CONTR_BR_CD.getQueryPrm(), cMsg.svcContrBrCd_H.getValue());
        params.put(QUERY_PRM_ID.SVC_CONTR_BR_DESC_TXT.getQueryPrm(), cMsg.svcContrBrDescTxt_H.getValue());
        params.put(QUERY_PRM_ID.XX_GENL_FLD_AREA_TXT.getQueryPrm(), cMsg.xxGenlFldAreaTxt_H.getValue());
        params.put(QUERY_PRM_ID.SEARCH_LIMIT_CNT.getQueryPrm(), SEARCH_LIMIT_CNT);
        params.put(QUERY_PRM_ID.THRU_DT_LIMIT.getQueryPrm(), THRU_DT_LIMIT);
        // START 2019/02/12 S.Kitamura [QC#30264, DEL]
        // START 2017/01/24 N.Arai [QC#17228, MOD]
        // params.put(QUERY_PRM_ID.PSN_TP_CD.getQueryPrm(), PSN_TP.EMPLOYEE);
        // END 2017/01/24 N.Arai [QC#17228, MOD]
        // END 2019/02/12 S.Kitamura [QC#30264, DEL]
        params.put(QUERY_PRM_ID.FIRST_ORG_CD.getQueryPrm(), ZYPCodeDataUtil.getVarCharConstValue(CONTR_BR_FIRST_ORG_CD, cMsg.glblCmpyCd.getValue()));
        params.put(QUERY_PRM_ID.SYS_SRC_CD.getQueryPrm(), SYS_SRC.S21_SERVICE);

        return params;

    }
}
