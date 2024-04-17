/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0920;

import static business.blap.NSAL0920.constant.NSAL0920Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.S21_PSNTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_CONTR_BRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Contract Billing Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         K.Kasai         Create          N/A
 * 2016/01/06   Hitachi         T.Tomita        Update          QC#1029
 * 2016/04/11   Hitachi         T.Kanasaka      Update          QC#6657
 * 2016/08/01   Hitachi         K.Yamada        Update          CSA QC#12504
 * 2016/09/01   Hitachi         T.Tomita        Update          QC#13976
 * 2017/01/23   Hitachi         K.Kitachi       Update          QC#17219
 * 2018/10/02   Hitachi         K.Kojima        Update          QC#28408
 * 2019/09/26   Hitachi         K.Kim           Update          QC#53722
 *</pre>
 */
public final class NSAL0920Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0920Query INSTANCE = new NSAL0920Query();

    /**
     * Private constructor
     */
    private NSAL0920Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0920Query singleton instance
     */
    public static NSAL0920Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL0920CMsg
     * @param sMsg NSAL0920SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, SEARCH), sMsg.A);

    }

    // START 2016/09/01 T.Tomita [QC#13976, ADD]
    /**
     * getSvcRgPulldownList
     * @param param Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcRgPulldownList(Map<String, Object> param) {
        return getSsmEZDClient().queryObjectList("getSvcRgPulldownList", param);
    }

    /**
     * getSvcContrBrPulldownList
     * @param param Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcContrBrPulldownList(Map<String, Object> param) {
        return getSsmEZDClient().queryObjectList("getSvcContrBrPulldownList", param);
    }
    // END 2016/09/01 T.Tomita [QC#13976, ADD]

    // START 2017/01/23 K.Kitachi [QC#17219, DEL]
//    /**
//     * getPsnData
//     * @param param Map<String, Object>
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getPsnData(Map<String, Object> param) {
//
//        return getSsmEZDClient().queryObjectList("getPsnNm", param);
//
//    }
    // END 2017/01/23 K.Kitachi [QC#17219, DEL]

    /**
     * getSsmParam
     * @param cMsg NSAL0920CMsg
     * @param sMsg NSAL0920SMsg
     * @param eventName String
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg, String eventName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("salesDt", ZYPDateUtil.getSalesDate());
        // START 2017/01/23 K.Kitachi [QC#17219, MOD]
//        String supervisor = ZYPCodeDataUtil.getVarCharConstValue(SVC_ORG_FUNC_ROLE_TP_SUPERVR, cMsg.glblCmpyCd.getValue());
//        params.put("svcOrgFuncRoleTpCd", supervisor);
        params.put("svcOrgFuncRoleTpCd", cMsg.svcOrgFuncRoleTpCd_VC.getValue());
        // END 2017/01/23 K.Kitachi [QC#17219, MOD]
        if (hasValue(cMsg.svcContrBrCd_H3)) {
            params.put("svcContrBrCd", cMsg.svcContrBrCd_H3.getValue());
        }
        if (hasValue(cMsg.svcRgPk_H3)) {
            params.put("svcRgPk", cMsg.svcRgPk_H3.getValue());
        }
        if (hasValue(cMsg.psnCd_H3)) {
            params.put("psnCd", cMsg.psnCd_H3.getValue());
        }
        if (hasValue(cMsg.xxChkBox_HY)) {
            params.put("apvlReqYes", cMsg.xxChkBox_HY.getValue());
        }
        if (hasValue(cMsg.xxChkBox_HN)) {
            params.put("apvlReqNo", cMsg.xxChkBox_HN.getValue());
        }
        if (hasValue(cMsg.xxFromDt)) {
            params.put("fromDt", cMsg.xxFromDt.getValue());
        }
        if (hasValue(cMsg.xxThruDt)) {
            params.put("thruDt", cMsg.xxThruDt.getValue());
        }
        if (hasValue(cMsg.dsContrNum)) {
            params.put("dsContrNum", cMsg.dsContrNum.getValue());
        }
        if (hasValue(cMsg.dsAcctNum)) {
            params.put("dsAcctNum", cMsg.dsAcctNum.getValue());
        }
        if (hasValue(cMsg.dsAcctNm)) {
            params.put("dsAcctNm", cMsg.dsAcctNm.getValue());
        }
        if (hasValue(cMsg.locNum)) {
            params.put("billToLocNum", cMsg.locNum.getValue());
        }
        if (hasValue(cMsg.locNm)) {
            params.put("billToLocNm", cMsg.locNm.getValue());
        }
        if (SEARCH.equals(eventName)) {
            params.put("rowNum", SEARCH_LIMIT_CNT);
        } else if (DOWNLOAD.equals(eventName)) {
            params.put("rowNum", DOWNLOAD_LIMIT_CNT);
        }
        // START 04/11/2016 T.Kanasaka [QC#6657, ADD]
        params.put("dsContrCatgCd_Fleet", DS_CONTR_CATG.FLEET);
        // END 04/11/2016 T.Kanasaka [QC#6657, ADD]
        // add start 2016/08/01 CSA Defect#12504
        params.put("base", BASE);
        // add end 2016/08/01 CSA Defect#12504
        // START 2017/01/23 K.Kitachi [QC#17219, ADD]
        String isExistsFlg = ZYPConstant.FLG_OFF_N;
        if (hasValue(cMsg.svcRgPk_H3) || hasValue(cMsg.svcContrBrCd_H3) || hasValue(cMsg.psnCd_H3)) {
            isExistsFlg = ZYPConstant.FLG_ON_Y;
        }
        params.put("isExistsFlg", isExistsFlg);
        // END 2017/01/23 K.Kitachi [QC#17219, ADD]
        // START 2018/10/02 K.Kojima [QC#28408,ADD]
        if (hasValue(cMsg.dsContrNum) || hasValue(cMsg.dsAcctNum) || hasValue(cMsg.dsAcctNm) || hasValue(cMsg.locNum) || hasValue(cMsg.locNm) || hasValue(cMsg.svcContrBrCd_H3) || hasValue(cMsg.svcRgPk_H3) || hasValue(cMsg.psnCd_H3)) {
            params.put("dsContrSearchFlag", ZYPConstant.FLG_ON_Y);
        }
        if (hasValue(cMsg.dsAcctNum) || hasValue(cMsg.dsAcctNm)) {
            params.put("sellToCustSearchFlag", ZYPConstant.FLG_ON_Y);
        }
        if (hasValue(cMsg.locNum) || hasValue(cMsg.locNm)) {
            params.put("billToCustSearchFlag", ZYPConstant.FLG_ON_Y);
        }
        if (hasValue(cMsg.svcContrBrCd_H3) || hasValue(cMsg.svcRgPk_H3) || hasValue(cMsg.psnCd_H3)) {
            params.put("svcContrBrSearchFlag", ZYPConstant.FLG_ON_Y);
        }
        // END 2018/10/02 K.Kojima [QC#28408,ADD]
        // START 2019/09/26 [QC#53722, ADD]
        params.put("creditMemo", INV_TP.CREDIT_MEMO);
        // END 2019/09/26 [QC#53722, ADD]
        return params;

    }

    // START 2016/01/06 T.Tomita [QC#1029, ADD]
    /**
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @return BILL_TO_CUSTTMsg
     */
    public BILL_TO_CUSTTMsg getBillToCust(String glblCmpyCd, String billToCustCd) {
        BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();
        tMsg.setSQLID("019");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("billToCustCd01", billToCustCd);
        BILL_TO_CUSTTMsgArray tMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        }
        return null;
    }
    // END 2016/01/06 T.Tomita [QC#1029, ADD]

    // add start 2016/08/09 CSA Defect#13149
    /**
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return SHIP_TO_CUSTTMsg
     */
    public SHIP_TO_CUSTTMsg getShipToCust(String glblCmpyCd, String shipToCustCd) {
        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            return null;
        }
        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        }
        return null;
    }
    // add end 2016/08/09 CSA Defect#13149

    // START 2017/01/23 K.Kitachi [QC#17219, ADD]
    /**
     * @param glblCmpyCd String
     * @param svcContrBrCd String
     * @return SVC_CONTR_BRTMsg
     */
    public SVC_CONTR_BRTMsg getSvcContrBr(String glblCmpyCd, String svcContrBrCd) {
        if (!hasValue(svcContrBrCd)) {
            return null;
        }
        SVC_CONTR_BRTMsg inMsg = new SVC_CONTR_BRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcContrBrCd, svcContrBrCd);
        return (SVC_CONTR_BRTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param psnCd String
     * @return S21_PSNTMsg
     */
    public S21_PSNTMsg getS21Psn(String glblCmpyCd, String psnCd) {
        if (!hasValue(psnCd)) {
            return null;
        }
        S21_PSNTMsg inMsg = new S21_PSNTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.psnCd, psnCd);
        return (S21_PSNTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // END 2017/01/23 K.Kitachi [QC#17219, ADD]
}
