/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1310;

import static business.blap.NSAL1310.constant.NSAL1310Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1310.common.NSAL1310CommonLogic;
import business.db.SVC_TERM_COND_ATTRBTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_COV_TMPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/31   Hitachi         K.Kojima        Create          QC#15136
 * 2016/12/02   Hitachi         K.Kojima        Update          QC#15136
 * 2017/01/20   Hitachi         K.Ochiai        Update          QC#16331
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 *</pre>
 */
public class NSAL1310BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1310CMsg cMsg = (NSAL1310CMsg) arg0;
        NSAL1310SMsg sMsg = (NSAL1310SMsg) arg1;
        super.preDoProcess(cMsg, arg1);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1310_INIT".equals(screenAplID)) {
                doProcess_NSAL1310_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSAL1310_NWAL1130".equals(screenAplID)) {
                doProcess_NSAL1310_NWAL1130(cMsg);
            } else if ("NSAL1310Scrn00_AddLine".equals(screenAplID)) {
                doProcess_NSAL1310Scrn00_AddLine(cMsg, sMsg);
            // START 2017/01/20 K.Ochiai [QC#16331,MOD]
            } else if ("NSAL1310Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1310Scrn00_CMN_Clear(cMsg, sMsg);
            // END 2017/01/20 K.Ochiai [QC#16331,MOD]
            } else if ("NSAL1310Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1310Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL1310Scrn00_DeleteLine".equals(screenAplID)) {
                doProcess_NSAL1310Scrn00_DeleteLine(cMsg, sMsg);
            } else if ("NSAL1310Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL1310Scrn00_PageJump(cMsg, sMsg);
            } else if ("NSAL1310Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1310Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1310Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1310Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL1310Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1310Scrn00_Search(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NSAL1310_INIT
     * @param cMsg NSAL1310CMsg
     */
    private void doProcess_NSAL1310_INIT(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxListNum, BigDecimal.valueOf(sMsg.A.length()));
        ZYPCodeDataUtil.createPulldownList(SVC_COV_TMPL_TP.class, cMsg.svcCovTmplTpCd_CD, cMsg.svcCovTmplTpDescTxt_SC, ":");
    }

    /**
     * doProcess_NSAL1310_NWAL1130
     * @param cMsg NSAL1310CMsg
     */
    private void doProcess_NSAL1310_NWAL1130(NSAL1310CMsg cMsg) {
        int selectNumber = cMsg.xxNum.getValueInt();
        NSAL1310_ACMsg acMsg = cMsg.A.no(selectNumber);

        // add start 2018/06/25 QC#17369
        acMsg.svcTermAttrbDefValTxt_A1.clear();
        acMsg.condValNum_A1.clear();
        acMsg.xxTrxDt_A1.clear();
        acMsg.svcTermCondDataValCd_D1.clear();
        acMsg.svcTermCondDataValCd_L1.clear();
        // add end 2018/06/25 QC#17369

        SVC_TERM_COND_ATTRBTMsg tMsg = NSAL1310CommonLogic.getSvcTermCondAttrbTMsg(cMsg.glblCmpyCd.getValue(), acMsg.svcTermCondAttrbPk_A1.getValue());
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(acMsg.svcTermDataTpCd_A1, tMsg.svcTermDataTpCd);
            ZYPEZDItemValueSetter.setValue(acMsg.svcTermCondDataSrcCd_A1, tMsg.svcTermCondDataSrcCd);
            ZYPEZDItemValueSetter.setValue(acMsg.physMaintTrgtTblNm_A1, tMsg.physMaintTrgtTblNm);
        }

        if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(acMsg.svcTermDataTpCd_A1.getValue())) {
            NSAL1310CommonLogic.createDropdown(cMsg.glblCmpyCd.getValue(), acMsg, null);
        } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(acMsg.svcTermDataTpCd_A1.getValue())) {
            NSAL1310CommonLogic.createLookup(cMsg.glblCmpyCd.getValue(), acMsg, null);
        // add start 2018/06/25 QC#17369
        } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(acMsg.svcTermDataTpCd_A1.getValue())) {
            NSAL1310CommonLogic.setLookupPopup(cMsg.glblCmpyCd.getValue(), acMsg);
        // add end 2018/06/25 QC#17369
        }
    }

    /**
     * doProcess_NSAL1310Scrn00_AddLine
     * @param cMsg NSAL1310CMsg
     * @param sMsg NSAL1310SMsg
     */
    private void doProcess_NSAL1310Scrn00_AddLine(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg) {
        if (cMsg.A.getValidCount() == cMsg.A.length()) {
            if (!NSAL1310CommonLogic.checkDate(cMsg)) {
                return;
            }
        }
        NSAL1310CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        sMsg.A.setValidCount(sMsg.A.getValidCount() + 1);
        // START 2016/12/02 K.Kojima [QC#14204,ADD]
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsg.A.getValidCount() - 1).svcCovTmplTpCd_A1, cMsg.svcCovTmplTpCd_SV);
        // END 2016/12/02 K.Kojima [QC#14204,ADD]
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsg.A.getValidCount() - 1).attrbUpdAvalFlg_A1, ZYPConstant.CHKBOX_ON_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsg.A.getValidCount() - 1).asgContrLvlFlg_A1, ZYPConstant.CHKBOX_ON_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsg.A.getValidCount() - 1).asgMachLvlFlg_A1, ZYPConstant.CHKBOX_ON_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsg.A.getValidCount() - 1).covTermCondActvFlg_A1, ZYPConstant.CHKBOX_ON_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsg.A.getValidCount() - 1).effFromDt_A1, cMsg.slsDt);
        NSAL1310CommonLogic.moveViewPage(cMsg, sMsg, sMsg.A.getValidCount());
    }

    // START 2017/01/20 K.Ochiai [QC#16331,MOD]
    /**
     * doProcess_NSAL1310Scrn00_CMN_Reset
     * @param cMsg NSAL1310CMsg
     */
    private void doProcess_NSAL1310Scrn00_CMN_Clear(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg) {
        cMsg.svcCovTmplTpCd_SV.clear();
        cMsg.svcTermAttrbDescTxt_H.clear();
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        doProcess_NSAL1310_INIT(cMsg, sMsg);
    }
    // END 2017/01/20 K.Ochiai [QC#16331,MOD]

    /**
     * doProcess_NSAL1310Scrn00_CMN_Submit
     * @param cMsg NSAL1310CMsg
     * @param sMsg NSAL1310SMsg
     */
    private void doProcess_NSAL1310Scrn00_CMN_Submit(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg) {
        doProcess_NSAL1310Scrn00_Search(cMsg, sMsg);
    }

    /**
     * doProcess_NSAL1310Scrn00_DeleteLine
     * @param cMsg NSAL1310CMsg
     * @param sMsg NSAL1310SMsg
     */
    private void doProcess_NSAL1310Scrn00_DeleteLine(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg) {
        NSAL1310CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        int copyIndex = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxChkBox_A1) && sMsg.A.no(i).xxChkBox_A1.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                sMsg.A.no(i).clear();
                continue;
            }
            if (i != copyIndex) {
                EZDMsg.copy(sMsg.A.no(i), null, sMsg.A.no(copyIndex), null);
                sMsg.A.no(i).clear();
            }
            copyIndex++;
        }
        sMsg.A.setValidCount(copyIndex);
        int viewNumber = cMsg.xxPageShowFromNum.getValueInt();
        if (viewNumber > sMsg.A.getValidCount()) {
            viewNumber = sMsg.A.getValidCount();
        }
        NSAL1310CommonLogic.moveViewPage(cMsg, sMsg, viewNumber);
    }

    /**
     * doProcess_NSAL1310Scrn00_PageJump
     * @param cMsg NSAL1310CMsg
     * @param sMsg NSAL1310SMsg
     */
    private void doProcess_NSAL1310Scrn00_PageJump(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg) {
        if (!NSAL1310CommonLogic.checkDate(cMsg)) {
            return;
        }
        NSAL1310CommonLogic.copyCMsgToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum_BK.getValueInt() - 1);
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        NSAL1310CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    /**
     * doProcess_NSAL1310Scrn00_PageNext
     * @param cMsg NSAL1310CMsg
     * @param sMsg NSAL1310SMsg
     */
    private void doProcess_NSAL1310Scrn00_PageNext(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg) {
        if (!NSAL1310CommonLogic.checkDate(cMsg)) {
            return;
        }
        NSAL1310CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL1310CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    /**
     * doProcess_NSAL1310Scrn00_PagePrev
     * @param cMsg NSAL1310CMsg
     * @param sMsg NSAL1310SMsg
     */
    private void doProcess_NSAL1310Scrn00_PagePrev(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg) {
        if (!NSAL1310CommonLogic.checkDate(cMsg)) {
            return;
        }
        NSAL1310CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL1310CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    /**
     * doProcess_NSAL1310Scrn00_Search
     * @param cMsg NSAL1310CMsg
     * @param sMsg NSAL1310SMsg
     */
    private void doProcess_NSAL1310Scrn00_Search(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg) {
        // START 2016/12/02 K.Kojima [QC#15136,ADD]
        cMsg.setCommitSMsg(true);
        // END 2016/12/02 K.Kojima [QC#15136,ADD]
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        S21SsmEZDResult ssmResult = NSAL1310Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
        } else {
            cMsg.setMessageInfo(ZZZM9001E);
        }
        Map<String, List<Map<String, Object>>> dropdownDataListMap = new HashMap<String, List<Map<String, Object>>>();
        Map<String, List<Map<String, Object>>> lookupDataListMap = new HashMap<String, List<Map<String, Object>>>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL1310_ASMsg asMsg = sMsg.A.no(i);
            if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(asMsg.svcTermDataTpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(asMsg.svcTermCondDataValCd_D1, asMsg.svcTermAttrbDefValTxt_A1);
                NSAL1310CommonLogic.createDropdown(cMsg.glblCmpyCd.getValue(), asMsg, dropdownDataListMap);
            } else if (SVC_TERM_ATTRB_DATA_TP.NUMBER.equals(asMsg.svcTermDataTpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(asMsg.condValNum_A1, new BigDecimal(asMsg.svcTermAttrbDefValTxt_A1.getValue()));
            } else if (SVC_TERM_ATTRB_DATA_TP.DATE.equals(asMsg.svcTermDataTpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(asMsg.xxTrxDt_A1, asMsg.svcTermAttrbDefValTxt_A1.getValue());
            } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(asMsg.svcTermDataTpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(asMsg.svcTermCondDataValCd_L1, asMsg.svcTermAttrbDefValTxt_A1);
                NSAL1310CommonLogic.createLookup(cMsg.glblCmpyCd.getValue(), asMsg, lookupDataListMap);
            // add start 2018/06/25 QC#17369
            } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(asMsg.svcTermDataTpCd_A1.getValue())) {
                NSAL1310CommonLogic.setLookupPopup(cMsg.glblCmpyCd.getValue(), asMsg);
            // add end 2018/06/25 QC#17369
            }
        }

        NSAL1310CommonLogic.pagenation(cMsg, sMsg, 0);
    }
}
