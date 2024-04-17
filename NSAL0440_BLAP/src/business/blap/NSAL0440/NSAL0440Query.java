/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0440;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;

import static business.blap.NSAL0440.constant.NSAL0440Constant.*;

import business.db.SVC_TERM_COND_ATTRB_MAPTMsg;
import business.db.SVC_TERM_COND_CATGTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Terms & Conditions
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Hitachi         T.Iwamoto       Create          N/A
 * 2016/02/17   Hitachi         K.Kasai         Update          QC#3018
 * 2016/06/03   Hitachi         T.Tomita        Update          QC#5489
 * 2016/09/29   Hitachi         A.Kohinata      Update          QC#12898
 * 2016/11/01   Hitachi         T.Kanasaka      Update          QC#15136
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 * 2018/07/31   Hitachi         A.Kohinata      Update          QC#26659
 * 2022/08/03   Hitachi         N.Takatsu       Update          QC#60077
 *</pre>
 */
public final class NSAL0440Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0440Query INSTANCE = new NSAL0440Query();

    /**
     * Private constructor
     */
    private NSAL0440Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0440Query singleton instance
     */
    public static NSAL0440Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSvcTermCategoryPulldownList
     * @param cMsg NSAL0440CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcTermCategoryPulldownList(NSAL0440CMsg cMsg) {
        SVC_TERM_COND_CATGTMsgArray tMsgArray = new SVC_TERM_COND_CATGTMsgArray();
        // START 2022/08/03 N.Takatsu [QC#60077, MOD]
        // tMsgArray.setMsgList(new EZDTMsg[cMsg.svcTermCondCatgNm_PC.length()]);
        tMsgArray.setMsgList(new EZDTMsg[cMsg.X.length()]);
        // END 2022/08/03 N.Takatsu [QC#60077, MOD]
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("slsDt", (String) cMsg.slsDt.getValue());
        params.put("maxDt", MAX_DATE);
        return getSsmEZDClient().queryEZDMsgArray("getSvcTermCategoryPulldownList", params, tMsgArray);
    }

    /**
     * getContractData
     * @param cMsg NSAL0440CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContractData(NSAL0440CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", (BigDecimal) cMsg.dsContrPk.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk_P)) {
            params.put("dsContrDtlPk", (BigDecimal) cMsg.dsContrDtlPk_P.getValue());
        }
        return getSsmEZDClient().queryEZDMsg("getContractData", params, cMsg);
    }

    /**
     * getSearchData
     * @param cMsg NSAL0440CMsg
     * @param sMsg NSAL0440SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0440CMsg cMsg, NSAL0440SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", (BigDecimal) cMsg.dsContrPk.getValue());
        params.put("limitNum", sMsg.B.length() + 1);
        params.put("slsDt", (String) cMsg.slsDt.getValue());
        params.put("limitNum", sMsg.B.length() + 1);
        params.put("maxDt", MAX_DATE);
        params.put("tpTextbox", SVC_TERM_ATTRB_DATA_TP.TEXTBOX);
        params.put("tpDropdown", SVC_TERM_ATTRB_DATA_TP.DROPDOWN);
        params.put("tpNumber", SVC_TERM_ATTRB_DATA_TP.NUMBER);
        params.put("tpDate", SVC_TERM_ATTRB_DATA_TP.DATE);
        // START 2016/06/03 T.Tomita [QC#5489, ADD]
        params.put("tpLookup", SVC_TERM_ATTRB_DATA_TP.LOOKUP);
        // END 2016/06/03 T.Tomita [QC#5489, ADD]
        // add start 2018/06/25 QC#17369
        params.put("tpLookupPopup", SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP);
        // add end 2018/06/25 QC#17369
        // add start 2018/07/31 QC#26659
        params.put("tpTextNumeric", SVC_TERM_ATTRB_DATA_TP.TEXT_NUMERIC);
        // add end 2018/07/31 QC#26659
        if (ZYPCommonFunc.hasValue(cMsg.serNum)) {
            params.put("xxBtnFlg", ZYPConstant.FLG_ON_Y);
        } else {
            params.put("xxBtnFlg", ZYPConstant.FLG_OFF_N);
        }
        // mod start 2016/02/17 CSA Defect#3018
        List<BigDecimal> dsContrDtlList = new ArrayList<BigDecimal>();
        for (int i = 0; i < cMsg.R.getValidCount(); i++) {
            dsContrDtlList.add(cMsg.R.no(i).dsContrDtlPk_R.getValue());
        }
        params.put("dsContrDtlList", dsContrDtlList);
        List<String> serNumList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(cMsg.serNum)) {
            serNumList.add(cMsg.serNum.getValue());
            params.put("isExistSerNumList", ZYPConstant.FLG_ON_Y);
        }
        params.put("serNumList", serNumList);
        // mod end 2016/02/17 CSA Defect#3018
        // START 2022/08/03 N.Takatsu [QC#60077, MOD]        
//        if (ZYPCommonFunc.hasValue(cMsg.svcTermCondCatgNm_PS) && !ALL_VALUE.equals(cMsg.svcTermCondCatgNm_PS.getValue())) {
//            BigDecimal svcTermCondCatgPk = new BigDecimal((String) cMsg.svcTermCondCatgNm_PS.getValue());
//            params.put("svcTermCondCatgPk", svcTermCondCatgPk);
//        }
        List<BigDecimal> svcTermCondCatgPkList = new ArrayList<BigDecimal>();
        if (!ZYPConstant.CHKBOX_ON_Y.equals(cMsg.X.no(0).xxChkBox_X.getValue())) {
            for (int i = 1; i < cMsg.X.getValidCount(); i++) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.X.no(i).xxChkBox_X.getValue())) {
                    svcTermCondCatgPkList.add(cMsg.X.no(i).svcTermCondCatgPk_X.getValue());
                }
            }
            if (svcTermCondCatgPkList.size() != 0) {
                params.put("svcTermCondCatgPkList", svcTermCondCatgPkList);
            }
        }
        
        ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt, IMG_OPEN_ALL);
        // END 2022/08/03 N.Takatsu [QC#60077, MOD]

        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
            params.put("xxChkBox_H1", ZYPConstant.CHKBOX_ON_Y);
        }
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_H2.getValue())) {
            params.put("xxChkBox_H2", ZYPConstant.CHKBOX_ON_Y);
        }
        List<String> dsContrDtlStsList = new ArrayList<String>();
        dsContrDtlStsList.add(DS_CONTR_DTL_STS.CANCELLED);
        dsContrDtlStsList.add(DS_CONTR_DTL_STS.EXPIRED);
        dsContrDtlStsList.add(DS_CONTR_DTL_STS.TERMINATED);
        params.put("dsContrDtlStsList", dsContrDtlStsList);
        // START 2016/11/01 T.Kanasaka [QC#15136, ADD]
        params.put("dsContrCatgCd_Fleet", DS_CONTR_CATG.FLEET);
        params.put("dsContrDtlTpCd_Fleet", DS_CONTR_DTL_TP.FLEET);
        // END 2016/11/01 T.Kanasaka [QC#15136, ADD]

        return getSsmEZDClient().queryEZDMsgArray("getMainData", params, sMsg.B);
    }

    // START 2016/06/03 T.Tomita [QC#5489, ADD]
    /**
     * getPhysMaintTrgtTblPulldownList
     * @param inTMsg SVC_TERM_COND_ATTRB_MAPTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPhysMaintTrgtTblPulldownList(SVC_TERM_COND_ATTRB_MAPTMsg inTMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", inTMsg.glblCmpyCd.getValue());
        params.put("physMaintTrgtTblNm", inTMsg.physMaintTrgtTblNm.getValue());
        params.put("physMaintTrgtColNm", inTMsg.physMaintTrgtColNm.getValue());
        params.put("physDplyTrgtColNm", inTMsg.physDplyTrgtColNm.getValue());
        params.put("keyLen", PULLDOWN_KEY_LENGTH);
        params.put("valLen", PULLDOWN_VAL_LENGTH);
        params.put("rowNum", PULLDOWN_ROW_NUM);
        return getSsmEZDClient().queryObjectList("getPhysMaintTrgtTblPulldownList", params);
    }
    // END 2016/06/03 T.Tomita [QC#5489, ADD]

    // START 2016/09/29 A.Kohinata [QC#12898, ADD]
    /**
     * getSvcTermCondAttrbPkList
     * @param cMsg NSAL0440CMsg
     * @param svcTermCondAttrbNmList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcTermCondAttrbPkList(NSAL0440CMsg cMsg, List<String> svcTermCondAttrbNmList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("slsDt", cMsg.slsDt.getValue());
        params.put("maxDt", MAX_DATE);
        params.put("svcTermCondAttrbNmList", svcTermCondAttrbNmList);
        return getSsmEZDClient().queryEZDMsgArray("getSvcTermCondAttrbPkList", params, cMsg.Y);
    }
    // END 2016/09/29 A.Kohinata [QC#12898, ADD]

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
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getPhysMaintTrgtTblPulldownList", params);
        if (result.isCodeNormal()) {
            return (Map<String, Object>) result.getResultObject();
        } else {
            return null;
        }
    }

    // START 2022/08/03 N.Takatsu [QC#60077, ADD]
    /**
     * getSerNumByDsContrDtlPk
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return String
     */
    public String getSerNumByDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", dsContrDtlPk);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getSerNumByDsContrDtlPk", params);
        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        }
        return null;
    }
    // START 2022/08/03 N.Takatsu [QC#60077, ADD]
}
