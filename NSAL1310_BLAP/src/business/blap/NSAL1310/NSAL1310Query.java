/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1310;

import static business.blap.NSAL1310.constant.NSAL1310Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.SVC_TERM_COND_ATTRB_MAPTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/31   Hitachi         K.Kojima        Create          QC#15136
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 *</pre>
 */
public final class NSAL1310Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1310Query INSTANCE = new NSAL1310Query();

    /**
     * Constructor.
     */
    private NSAL1310Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1310Query
     */
    public static NSAL1310Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL1310CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.svcCovTmplTpCd_SV)) {
            params.put("svcCovTmplTpCd", cMsg.svcCovTmplTpCd_SV.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.svcTermAttrbDescTxt_H)) {
            params.put("svcTermAttrbDescTxt", cMsg.svcTermAttrbDescTxt_H.getValue().toUpperCase());
        }
        params.put("archived", cMsg.xxChkBox_H.getValue());
        params.put("salesDate", cMsg.slsDt.getValue());
        params.put("flagY", ZYPConstant.FLG_ON_Y);
        params.put("maxDate", MAX_DATE);
        params.put("rowNum", rowNum);
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", params, sMsg.A);
    }

    /**
     * getDropDownData
     * @param glblCmpyCd String
     * @param svcTermCondDataSrcCd String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getDropDownData(String glblCmpyCd, String svcTermCondDataSrcCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcTermCondDataSrcCd", svcTermCondDataSrcCd);
        params.put("rowNum", 99);
        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getDropDownData", params);
        if (result.isCodeNormal()) {
            return (List<Map<String, Object>>) result.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * getLookupData
     * @param inTMsg SVC_TERM_COND_ATTRB_MAPTMsg
     * @return S21SsmEZDResult
     */
    public List<Map<String, Object>> getLookupData(SVC_TERM_COND_ATTRB_MAPTMsg inTMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", inTMsg.glblCmpyCd.getValue());
        params.put("physMaintTrgtTblNm", inTMsg.physMaintTrgtTblNm.getValue());
        params.put("physMaintTrgtColNm", inTMsg.physMaintTrgtColNm.getValue());
        params.put("physDplyTrgtColNm", inTMsg.physDplyTrgtColNm.getValue());
        params.put("keyLen", PULLDOWN_KEY_LENGTH);
        params.put("valLen", PULLDOWN_VAL_LENGTH);
        params.put("rowNum", PULLDOWN_ROW_NUM);
        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getLookupData", params);
        if (result.isCodeNormal()) {
            return (List<Map<String, Object>>) result.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * getDuplicateData
     * @param glblCmpyCd String
     * @param svcCovTmplTpCd String
     * @param svcTermCondAttrbPk BigDecimal
     * @param effFromDt String
     * @param effThruDt String
     * @param pkList List<BigDecimal>
     * @return BigDecimal
     */
    public BigDecimal getDuplicateData(String glblCmpyCd, String svcCovTmplTpCd, BigDecimal svcTermCondAttrbPk, String effFromDt, String effThruDt, List<BigDecimal> pkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcCovTmplTpCd", svcCovTmplTpCd);
        params.put("svcTermCondAttrbPk", svcTermCondAttrbPk);
        params.put("effFromDt", effFromDt);
        if (effThruDt == null || effThruDt.length() == 0) {
            effThruDt = MAX_DATE;
        }
        params.put("effThruDt", effThruDt);
        params.put("maxDate", MAX_DATE);
        params.put("pkList", pkList);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getDuplicateData", params);
        if (result.isCodeNormal()) {
            return (BigDecimal) result.getResultObject();
        } else {
            return BigDecimal.ZERO;
        }
    }

    // add start 2018/06/25 QC#17369
    /**
     * getLookupPopupData
     * @param inTMsg SVC_TERM_COND_ATTRB_MAPTMsg
     * @param valTxt String
     * @return Map<String, Object>
     */
    public Map<String, Object> getLookupPopupData(SVC_TERM_COND_ATTRB_MAPTMsg inTMsg, String valTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", inTMsg.glblCmpyCd.getValue());
        params.put("physMaintTrgtTblNm", inTMsg.physMaintTrgtTblNm.getValue());
        params.put("physMaintTrgtColNm", inTMsg.physMaintTrgtColNm.getValue());
        params.put("physDplyTrgtColNm", inTMsg.physDplyTrgtColNm.getValue());
        params.put("keyLen", PULLDOWN_KEY_LENGTH);
        params.put("valLen", PULLDOWN_VAL_LENGTH);
        params.put("rowNum", PULLDOWN_ROW_NUM);
        params.put("valTxt", valTxt);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getLookupData", params);
        if (result.isCodeNormal()) {
            return (Map<String, Object>) result.getResultObject();
        } else {
            return null;
        }
    }
    // add end 2018/06/25 QC#17369
}
