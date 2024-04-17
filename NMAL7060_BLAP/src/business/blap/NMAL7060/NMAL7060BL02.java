/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7060;

import static business.blap.NMAL7060.constant.NMAL7060Constant.EVENT_NM_OPENWIN_MODEL;
import static business.blap.NMAL7060.constant.NMAL7060Constant.EVENT_NM_OPENWIN_PRCMTRPKGBLLGMTR;
import static business.blap.NMAL7060.constant.NMAL7060Constant.NMAM0007I;
import static business.blap.NMAL7060.constant.NMAL7060Constant.NMAM0192E;
import static business.blap.NMAL7060.constant.NMAL7060Constant.NZZM0001W;
import static business.blap.NMAL7060.constant.NMAL7060Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7060.common.NMAL7060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7060BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         W.Honda         Create          N/A
 * 2017/02/10   Fujitsu         R.Nakamura      Update          QC#17529
 *</pre>
 */
public class NMAL7060BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7060CMsg bizMsg = (NMAL7060CMsg) cMsg;
            NMAL7060SMsg glblMsg = (NMAL7060SMsg) sMsg;

            if ("NMAL7060_INIT".equals(screenAplID)) {
                // Mod Start 2017/02/16 QC#17529
//                doProcess_NMAL7060_INIT(bizMsg, glblMsg);
                doProcess_NMAL7060_INIT(bizMsg, glblMsg, false);
                // Mod End 2017/02/16 QC#17529

            } else if ("NMAL7060Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7060Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL7060Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7060Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7060_NWAL1130".equals(screenAplID)) {
                doProcess_NMAL7060_NWAL1130(bizMsg, glblMsg);

            } else if ("NMAL7060Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NMAL7060Scrn00_DeleteRow(bizMsg, glblMsg);

            } else if ("NMAL7060Scrn00_DeleteRow_Bllg".equals(screenAplID)) {
                doProcess_NMAL7060Scrn00_DeleteRow_Bllg(bizMsg, glblMsg);

            } else if ("NMAL7060Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NMAL7060Scrn00_InsertRow(bizMsg, glblMsg);

            } else if ("NMAL7060Scrn00_InsertRow_Bllg".equals(screenAplID)) {
                doProcess_NMAL7060Scrn00_InsertRow_Bllg(bizMsg, glblMsg);

            } else if ("NMAL7060Scrn00_Search".equals(screenAplID)) {
                // Mod Start 2017/02/14 QC#17529
//                doProcess_NMAL7060Scrn00_Search(bizMsg, glblMsg);
                doProcess_NMAL7060Scrn00_Search(bizMsg, glblMsg, false);
                // Mod End 2017/02/14 QC#17529

            } else if ("NMAL7060Scrn00_OnChange_RadioBtn_Bllg".equals(screenAplID)) {
                doProcess_NMAL7060Scrn00_OnChange_RadioBtn_Bllg(bizMsg, glblMsg);

            // Add Start 2017/02/10 QC#17529
            } else if ("NMAL7060Scrn00_FilterSearch".equals(screenAplID)) {
                doProcess_NMAL7060Scrn00_Search(bizMsg, glblMsg, true);
            // Add End 2017/02/10 QC#17529

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param filterSearchFlg boolean
     */
    private void doProcess_NMAL7060_INIT(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg, boolean filterSearchFlg) {
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        bizMsg.A.setValidCount(0);
        glblMsg.A.setValidCount(0);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);
        bizMsg.B.setValidCount(0);
        glblMsg.B.setValidCount(0);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(glblMsg.C);
        bizMsg.C.setValidCount(0);
        glblMsg.C.setValidCount(0);
        ZYPTableUtil.clear(glblMsg.N);
        ZYPTableUtil.clear(glblMsg.M);
        glblMsg.N.setValidCount(0);
        glblMsg.M.setValidCount(0);

        if (ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgPk.getValue())) {
            // Mod Start 2017/02/14 QC#17529
//            doProcess_NMAL7060Scrn00_Search(bizMsg, glblMsg);
            doProcess_NMAL7060Scrn00_Search(bizMsg, glblMsg, filterSearchFlg);
            // Mod End 2017/02/14 QC#17529
        } else {
            bizMsg.clear();
            bizMsg.effFromDt.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            // Add Start 2017/02/17 QC#17529
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt, BigDecimal.ZERO);
            // Add End 2017/02/17 QC#17529
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7060Scrn00_CMN_Clear(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
        // Mod Start 2017/02/16 QC#17529
//        doProcess_NMAL7060_INIT(bizMsg, glblMsg);
        doProcess_NMAL7060_INIT(bizMsg, glblMsg, false);
        // Mod End 2017/02/16 QC#17529
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7060Scrn00_CMN_Submit(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
            // Mod Start 2017/02/16 QC#17529
//            doProcess_NMAL7060_INIT(bizMsg, glblMsg);
            doProcess_NMAL7060_INIT(bizMsg, glblMsg, ZYPCommonFunc.hasValue(bizMsg.xxRqstFlg));
            // Mod End 2017/02/16 QC#17529

            if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
                bizMsg.setMessageInfo(ZZZM9003I, new String[]{"Submit"});
            }
        }
    }

    /**
     * NWAL1130 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7060_NWAL1130(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
        // QC#24307 2018/08/20 Mod start
        String eventNm = bizMsg.xxMntEventNm.getValue();
        if (EVENT_NM_OPENWIN_PRCMTRPKGBLLGMTR.equals(eventNm)) {
            doProcess_NMAL7060Scrn00_OnChange_RadioBtn_Bllg(bizMsg, glblMsg);
        } else if (EVENT_NM_OPENWIN_MODEL.equals(eventNm)) {
            searchCountersFromModel(bizMsg, glblMsg);
        }
        // QC#24307 2018/08/20 Mod end
    }

    /**
     * Delete_Row Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7060Scrn00_DeleteRow(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
        // QC#24307 2018/08/20 Add start
        int index = bizMsg.xxRadioBtn.getValueInt();

        BigDecimal mdlId = null;
        if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).mdlId_A1)){
            mdlId = bizMsg.A.no(index).mdlId_A1.getValue();
        }

        List<Integer> selectedRows = new ArrayList<Integer>();
        selectedRows.add(index);
        ZYPTableUtil.deleteRows(bizMsg.A, selectedRows);
        // QC#24307 2018/08/20 Add end
        // Mod Start 2017/02/10 QC#17529
//        NMAL7060CommonLogic.saveCurrentPageToSMsgA(bizMsg, glblMsg);
        NMAL7060CommonLogic.saveCurrentPageToSMsgA(bizMsg, glblMsg, getGlobalCompanyCode());
        // Mod End 2017/02/10 QC#17529

        // QC#24307 2018/08/20 Add start
        if (mdlId != null) {
            NMAL7060CommonLogic.refreshCountersFromMdl(bizMsg, glblMsg, mdlId, getGlobalCompanyCode());

            if (ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_B1)
                    && bizMsg.B.getValidCount() > 0) {
                doProcess_NMAL7060Scrn00_OnChange_RadioBtn_Bllg(bizMsg, glblMsg);
            } else {
                ZYPTableUtil.clear(bizMsg.C);
            }
        }
        // QC#24307 2018/08/20 Add end
    }

    /**
     * Delete_Row For Billing Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7060Scrn00_DeleteRow_Bllg(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
        int index = bizMsg.xxRadioBtn_B1.getValueInt();

        if (ZYPCommonFunc.hasValue(bizMsg.B.no(index).prcMtrPkgBllgMtrPk_B1)) {
            NMAL7060CommonLogic.saveToSMsgForDelete(bizMsg, glblMsg);
        } else {
            NMAL7060CommonLogic.saveToSMsgForDeleteNewRow(bizMsg, glblMsg);
        }
        bizMsg.xxRadioBtn_B1.clear();

        // index is not exist index.
        NMAL7060CommonLogic.setToBizMsgFromGlblMsg(glblMsg, bizMsg, getGlobalCompanyCode(), -1);
    }

    /**
     * Insert_Row Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7060Scrn00_InsertRow(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
        // Mod Start 2017/02/10 QC#17529
//        NMAL7060CommonLogic.saveCurrentPageToSMsgA(bizMsg, glblMsg);
        NMAL7060CommonLogic.saveCurrentPageToSMsgA(bizMsg, glblMsg, getGlobalCompanyCode());
        // Mod End 2017/02/10 QC#17529
    }

    /**
     * Insert_Row For Billing Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7060Scrn00_InsertRow_Bllg(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
        NMAL7060CommonLogic.saveCurrentPageToSMsgB(bizMsg, glblMsg);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param filterSearchFlg boolean
     */
    private void doProcess_NMAL7060Scrn00_Search(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg, boolean filterSearchFlg) {
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        ZYPTableUtil.clear(glblMsg.N);
        ZYPTableUtil.clear(glblMsg.M);
        glblMsg.prcMtrPkgPk.clear();
        glblMsg.prcMtrPkgDescTxt.clear();
        ZYPEZDItemValueSetter.setValue(glblMsg.effFromDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        glblMsg.effThruDt.clear();
        glblMsg.ezInTime.clear();
        glblMsg.ezInTimeZone.clear();
        glblMsg.ezUpTime.clear();
        glblMsg.ezUpTimeZone.clear();
        glblMsg.xxRadioBtn.clear();
        // Add Start 2017/02/10 QC#17529
        bizMsg.xxEventFlgTxt_F1.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_0);
        if (filterSearchFlg) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
        } else {
            bizMsg.xxRqstFlg.clear();
            bizMsg.mdlNm_F1.clear();
        }
        // Add End 2017/02/10 QC#17529

        // Mod Start 2017/02/14 QC#17529
//        S21SsmEZDResult ssmResultForMdl  = NMAL7060Query.getInstance().getPrcMtrPkgMdl(bizMsg, glblMsg);
        S21SsmEZDResult ssmResultForMdl  = NMAL7060Query.getInstance().getPrcMtrPkgMdl(bizMsg, glblMsg, filterSearchFlg);
        // Mod End 2017/02/14 QC#17529
        // Add Start 2017/02/14 QC#17529
        boolean mdlNoSrchFlg = true;
        // Add End 2017/02/14 QC#17529
        if (ssmResultForMdl.isCodeNormal()) {
            NMAL7060CommonLogic.setResultForMdlToGlblMsg(ssmResultForMdl, glblMsg, bizMsg, getGlobalCompanyCode());

            int queryResCnt = ssmResultForMdl.getQueryResultCount();

            if (queryResCnt > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = glblMsg.A.length();
            }
            // Add Start 2017/02/10 QC#17529
            if (queryResCnt >= glblMsg.A.length()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxEventFlgTxt_F1, ZYPConstant.FLG_ON_Y);
            }
            // Add End 2017/02/10 QC#17529

            //Copy from SMsg to CMsg
            EZDMsg.copy(glblMsg, null, bizMsg, null);

            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);
            // Add Start 2017/02/14 QC#17529
            mdlNoSrchFlg = false;
            // Add End 2017/02/14 QC#17529
        } else {
            // Mod Start 2017/02/14 QC#17529
//            bizMsg.setMessageInfo(NMAM0007I);
            mdlNoSrchFlg = true;
            // Mod End 2017/02/14 QC#17529
        }
        // Add Start 2017/02/17 QC#17529
        if (!filterSearchFlg) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt, BigDecimal.valueOf(bizMsg.A.getValidCount()));
        }
        // Add End 2017/02/17 QC#17529

        S21SsmEZDResult ssmResultForBllg = NMAL7060Query.getInstance().getPrcMtrPkgBllgMtr(bizMsg, glblMsg);
        // Add Start 2017/02/14 QC#17529
        boolean bllgNoSrchFlg = true;
        // Add End 2017/02/14 QC#17529
        if (ssmResultForBllg.isCodeNormal()) {
            NMAL7060CommonLogic.setResultForBllgToGlblMsg(ssmResultForBllg, glblMsg, bizMsg, getGlobalCompanyCode());

            int queryResCnt = ssmResultForBllg.getQueryResultCount();

            if (queryResCnt > glblMsg.B.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = glblMsg.B.length();
            }

            //Copy from SMsg to CMsg
            EZDMsg.copy(glblMsg, null, bizMsg, null);

            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == bizMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(i), null);
            }
            bizMsg.B.setValidCount(i);
            // Add Start 2017/02/14 QC#17529
            bllgNoSrchFlg = false;
            // Add End 2017/02/14 QC#17529
        } else {
            // Mod Start 2017/02/14 QC#17529
//            bizMsg.setMessageInfo(NMAM0007I);
            bllgNoSrchFlg = true;
            // Mod End 2017/02/14 QC#17529
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.getMessageKind())) {
            // Mod Start 2017/02/14 QC#17529
            if (mdlNoSrchFlg && bllgNoSrchFlg) {
                bizMsg.setMessageInfo(NMAM0007I);
            } else {
                bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Search" });
            }
            // Mod End 2017/02/14 QC#17529
        }
    }

    /**
     * Select_Service_Model Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7060Scrn00_OnChange_RadioBtn_Bllg(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
        int index = bizMsg.xxRadioBtn_B1.getValueInt();
        if (!ZYPCommonFunc.hasValue(bizMsg.B.no(index).mtrLbDescTxt_B1.getValue())) {
            NMAL7060CommonLogic.saveCurrentCMsgToSMsgALL(bizMsg, glblMsg);
            ZYPTableUtil.clear(bizMsg.C);
            bizMsg.setMessageInfo(NMAM0192E);
            bizMsg.setCommitSMsg(true);
        } else {
            NMAL7060CommonLogic.searchCounters(bizMsg, glblMsg, getGlobalCompanyCode(), index);
        }
    }

    // QC#24307 2018/08/20 Add start
    /**
     * searchCountersFromModel
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void searchCountersFromModel(NMAL7060CMsg bizMsg, NMAL7060SMsg glblMsg) {
        int mdlIdx;
        for (mdlIdx = 0 ; mdlIdx < bizMsg.A.getValidCount(); mdlIdx++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(mdlIdx).xxInsUpdDelFlg_A1.getValue())) {
                break;
            }
        }

        NMAL7060CommonLogic.refreshCountersFromMdl(bizMsg, glblMsg, bizMsg.A.no(mdlIdx).mdlId_A1.getValue(), getGlobalCompanyCode());

        if (ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_B1)
                && bizMsg.B.getValidCount() > 0) {
            doProcess_NMAL7060Scrn00_OnChange_RadioBtn_Bllg(bizMsg, glblMsg);
        } else {
            ZYPTableUtil.clear(bizMsg.C);
        }
    }
    // QC#24307 2018/08/20 Add end
}

