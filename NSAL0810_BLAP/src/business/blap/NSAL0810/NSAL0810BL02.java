/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0810;

import static business.blap.NSAL0810.constant.NSAL0810Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSItem;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0810.common.NSAL0810CommonLogic;
import business.db.DS_ACTL_CNT_INTFCTMsg;
import business.db.DS_ADDL_CHRG_INTFCTMsg;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.DS_XS_COPY_INTFCTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDL_NMTMsgArray;
import business.db.MTR_LBTMsg;
import business.db.MTR_LBTMsgArray;
import business.db.PRC_ALLOC_INTFCTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.file.NSAL0810F00FMsg;
import business.parts.NSXC001001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsActlCntIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsAddlChrgIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsContrIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsXsCopyIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationPrcAllocIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.ValidationReturnBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CHRG_LVL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_ACT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TGTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_EST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Ds Contract Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/02/15   Hitachi         Y.Takeno        Update          QC#3314
 * 2016/03/21   Hitachi         K.Yamada        Update          CSA QC#5541
 * 2016/03/25   Hitachi         T.Iwamoto       Create          QC#5542
 * 2016/03/29   Hitachi         T.Iwamoto       Update          QC#5541
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/05/25   Hitachi         T.Tomita        Update          QC#8898
 * 2016/06/30   Hitachi         T.Iwamoto       Update          QC#10661
 * 2016/07/06   Hitachi         O.Okuma         Update          QC#11355
 * 2016/07/14   Hitachi         Y.Takeno        Update          QC#11844
 * 2016/07/14   Hitachi         Y.Takeno        Update          QC#11918
 * 2016/07/25   Hitachi         Y.Takeno        Update          QC#11829
 * 2016/08/31   Hitachi         T.Mizuki        Update          QC#12566
 * 2016/09/26   Hitachi         K.Kishimoto     Update          QC#14428
 * 2016/10/28   Hitachi         T.Tomita        Update          QC#15468
 * 2016/11/28   Hitachi         K.Kojima        Update          QC#14204
 * 2016/11/28   Hitachi         T.Tomita        Update          QC#12077
 * 2017/01/18   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/05/26   Hitachi         Y.Osawa         Update          QC#18560
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 *</pre>
 */
public class NSAL0810BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0810_INIT".equals(screenAplID)) {
                doProcess_NSAL0810_INIT((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
                // add start 2016/03/21 CSA Defect#5541
                ZYPGUITableColumn.getColData((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
                // add end 2016/03/21 CSA Defect#5541
            } else if ("NSAL0810Scrn00_Search".equals(screenAplID)) {
                // mod start 2016/07/05 CSA Defect#11355
                doProcess_NSAL0810Scrn00_Search((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg, false);
                // mod end 2016/07/05 CSA Defect#11355
            } else if ("NSAL0810Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_PageJump((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_PageNext((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_PagePrev((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            // START 2017/01/18 [QC#16331, MOD]
            } else if ("NSAL0810Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_CMN_Clear((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            // END 2017/01/18 [QC#16331, MOD]
            } else if ("NSAL0810Scrn00_CMN_Save".equals(screenAplID)) {
                // mod start 2016/07/05 CSA Defect#11355
                doProcess_NSAL0810Scrn00_Search((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg, true);
                // mod end 2016/07/05 CSA Defect#11355
            } else if ("NSAL0810Scrn00_CMN_Delete".equals(screenAplID)) {
                return;
            } else if ("NSAL0810Scrn00_ClickCheckbox".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_ClickCheckbox((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_SelectAll((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_UnselectAll".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_UnselectAll((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_TBLColumnSort((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_Download((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_TemplateDownload".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_TemplateDownload((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_Upload".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_Upload((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_ValidateData".equals(screenAplID)) {
                return;
            } else if ("NSAL0810Scrn00_CMN_ColClear".equals(screenAplID)) {
                return;
            } else if ("NSAL0810Scrn00_CMN_ColSave".equals(screenAplID)) {
                return;
            } else if ("NSAL0810Scrn00_SubmitBatValidation".equals(screenAplID)) {
                return;
            } else if ("NSAL0810Scrn00_ResubmitBatInterface".equals(screenAplID)) {
                return;
            } else if ("NSAL0810Scrn00_ValidateData".equals(screenAplID)) {
                return;
            } else if ("NSAL0810Scrn00_ResubmitInterface".equals(screenAplID)) {
                return;
            } else if ("NSAL0810Scrn00_ActualCounters".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_ActualCounters((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_Tiers".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_Tiers((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_AdditionalCharges".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_AdditionalCharges((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else if ("NSAL0810Scrn00_SalesCredits".equals(screenAplID)) {
                doProcess_NSAL0810Scrn00_SalesCredits((NSAL0810CMsg) cMsg, (NSAL0810SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810_INIT(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {

        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.P);
        ZYPTableUtil.clear(sMsg.A);

        cMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

        // Create Pulldown List
        ZYPCodeDataUtil.createPulldownList(CONTR_INTFC_SRC_TP.class, cMsg.contrIntfcSrcTpCd_SC, cMsg.contrIntfcSrcTpDescTxt_SC);
        ZYPCodeDataUtil.createPulldownList(CONTR_INTFC_SRC_TP.class, cMsg.contrIntfcSrcTpCd_AC, cMsg.contrIntfcSrcTpDescTxt_AC);
        ZYPCodeDataUtil.createPulldownList(DS_CONTR_INTFC_ACT.class, cMsg.dsContrIntfcActCd_AC, cMsg.dsContrIntfcActDescTxt_AC);
        ZYPCodeDataUtil.createPulldownList(CONTR_INTFC_DTL_TP.class, cMsg.contrIntfcDtlTpCd_AC, cMsg.contrIntfcDtlTpDescTxt_AC);
        ZYPCodeDataUtil.createPulldownList(DS_CONTR_PROC_STS.class, cMsg.dsContrProcStsCd_AC, cMsg.dsContrProcStsDescTxt_AC);
        ZYPCodeDataUtil.createPulldownList(MTR_EST_TP.class, cMsg.mtrEstTpCd_AC, cMsg.mtrEstTpDescTxt_AC);
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, cMsg.bllgCycleCd_AC, cMsg.bllgCycleDescTxt_AC);
        // START 2017/05/26 Y.Osawa [QC#18560, ADD]
        NSAL0810CommonLogic.deletePulldownList(cMsg.bllgCycleCd_AC, cMsg.bllgCycleDescTxt_AC, BLLG_CYCLE.DAILY);
        // END   2017/05/26 Y.Osawa [QC#18560, ADD]
        ZYPCodeDataUtil.createPulldownList(CONTR_AUTO_RNW_TP.class, cMsg.contrAutoRnwTpCd_AC, cMsg.contrAutoRnwTpDescTxt_AC);
        ZYPCodeDataUtil.createPulldownList(RNW_PRC_METH.class, cMsg.rnwPrcMethCd_AC, cMsg.rnwPrcMethDescTxt_AC);
        ZYPCodeDataUtil.createPulldownList(CONTR_UPLFT_TP.class, cMsg.contrUplftTpCd_AC, cMsg.contrUplftTpDescTxt_AC);
        ZYPCodeDataUtil.createPulldownList(UPLFT_PRC_METH.class, cMsg.uplftPrcMethCd_AC, cMsg.uplftPrcMethDescTxt_AC);
        ZYPCodeDataUtil.createPulldownList(MTR_READ_METH.class, cMsg.mtrReadMethCd_AC, cMsg.mtrReadMethDescTxt_AC);
        // START 2016/05/25 T.Tomita [QC#8898, DEL]
//        createMtrLbPulldownList(cMsg);
        // END 2016/05/25 T.Tomita [QC#8898, DEL]
        ZYPCodeDataUtil.createPulldownList(DS_CONTR_CATG.class, cMsg.dsContrCatgCd_AC, cMsg.dsContrCatgDescTxt_AC);
        ZYPCodeDataUtil.createPulldownList(DS_CONTR_STS.class, cMsg.dsContrStsCd_AC, cMsg.dsContrStsDescTxt_AC);
        ZYPCodeDataUtil.createPulldownList(XS_CHRG_TP.class, cMsg.xsChrgTpCd_AC, cMsg.xsChrgTpDescTxt_AC);
        // Mod Start 2016/09/26 <QC#14428>
//        ZYPCodeDataUtil.createPulldownList(ADDL_CHRG_TP.class, cMsg.addlChrgTpCd_AC, cMsg.addlChrgTpDescTxt_AC);
        List<Map<String, Object>> AddlChrgTpMapList = NSAL0810Query.getInstance().getAddlChrgTpV();
        int addlChrgTpIdx = 0;
        for (Map<String, Object> AddlChrgTpMap : AddlChrgTpMapList) {
            if (addlChrgTpIdx >= cMsg.addlChrgTpCd_AC.length()) {
                break;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.addlChrgTpCd_AC.no(addlChrgTpIdx), (String) AddlChrgTpMap.get("ADDL_CHRG_TP_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.addlChrgTpDescTxt_AC.no(addlChrgTpIdx), (String) AddlChrgTpMap.get("ADDL_CHRG_TP_DESC_TXT"));
            addlChrgTpIdx++;
        }
        // Mod End   2016/09/26 <QC#14428>
        ZYPCodeDataUtil.createPulldownList(CHRG_LVL_TP.class, cMsg.chrgLvlTpCd_AC, cMsg.chrgLvlTpDescTxt_AC);
        ZYPCodeDataUtil.createPulldownList(ADDL_CHRG_INV_TP.class, cMsg.addlChrgInvTpCd_AC, cMsg.addlChrgInvTpDescTxt_AC);
        ZYPCodeDataUtil.createPulldownList(DS_CONTR_CLS.class, cMsg.dsContrClsCd_AC, cMsg.dsContrClsDescTxt_AC);
        createSvcLineBizPulldownList(cMsg);
        ZYPCodeDataUtil.createPulldownList(BLLG_TMG_TP.class, cMsg.bllgTmgTpCd_AC, cMsg.bllgTmgTpDescTxt_AC);
        // START 2022/03/22 [QC#59683, ADD]
        ZYPCodeDataUtil.createPulldownList(DS_INV_TGTR_TP.class, cMsg.dsInvTgtrTpCd_AC, cMsg.dsInvTgtrTpDescTxt_AC);
        // END   2022/03/22 [QC#59683, ADD]

        setValue(cMsg.xxErrFlg_S, ZYPConstant.FLG_ON_Y);
    }

    // START 2016/05/25 T.Tomita [QC#8898, DEL]
//    private static void createMtrLbPulldownList(NSAL0810CMsg cMsg) {
//        S21SsmEZDResult ssmResult = NSAL0810Query.getInstance().getMtrLbList();
//        if (ssmResult.isCodeNotFound()) {
//            return;
//        }
//        List<Map<String, Object>> lsit = (List<Map<String, Object>>) ssmResult.getResultObject();
//        if (lsit.size() == 0) {
//            return;
//        }
//
//        cMsg.mtrLbCd_AC.clear();
//        cMsg.mtrLbDescTxt_AC.clear();
//        for (int i = 0; i < lsit.size(); i++) {
//            Map<String, Object> map = (Map<String, Object>) lsit.get(i);
//            cMsg.mtrLbCd_AC.no(i).setValue((String) map.get("MTR_LB_CD"));
//            cMsg.mtrLbDescTxt_AC.no(i).setValue((String) map.get("MTR_LB_DESC_TXT"));
//        }
//    }
    // END 2016/05/25 T.Tomita [QC#8898, DEL]

    private static void createSvcLineBizPulldownList(NSAL0810CMsg cMsg) {
        S21SsmEZDResult ssmSvcLineBizResult = NSAL0810Query.getInstance().getSvcLineBizList();
        if (ssmSvcLineBizResult.isCodeNotFound()) {
            return;
        }
        List<Map<String, Object>> svcLineBizList = (List<Map<String, Object>>) ssmSvcLineBizResult.getResultObject();
        if (svcLineBizList.size() == 0) {
            return;
        }

        cMsg.lineBizTpCd_AC.clear();
        cMsg.lineBizTpDescTxt_AC.clear();
        for (int i = 0; i < svcLineBizList.size(); i++) {
            Map<String, Object> map = (Map<String, Object>) svcLineBizList.get(i);
            cMsg.lineBizTpCd_AC.no(i).setValue((String) map.get("LINE_BIZ_TP_CD"));
            cMsg.lineBizTpDescTxt_AC.no(i).setValue((String) map.get("LINE_BIZ_TP_DESC_TXT"));
        }
    }

    /**
     * do process (Page Jump)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_PageJump(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
        int rowIndex = NSAL0810CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0810CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Page Next)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_PageNext(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
        int rowIndex = NSAL0810CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() + 1);
        NSAL0810CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Page Prev)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_PagePrev(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
        int rowIndex = NSAL0810CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() - 1);
        NSAL0810CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    // START 2017/01/18 [QC#16331, MOD]
    /**
     * do process (Clear)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_CMN_Clear(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        doProcess_NSAL0810_INIT(cMsg, sMsg);
        setValue(cMsg.xxComnColOrdTxt, xxComnColOrdTxt);
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
        }
    }
    // END 2017/01/18 [QC#16331, MOD]

    /**
     * do process (Search)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     * @param isSave boolean
     */
    private void doProcess_NSAL0810Scrn00_Search(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg, boolean isSave) {

        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        clearSearchResultSummary(cMsg);
        cMsg.setCommitSMsg(true);
        // get Detail Data and Set SMsg
        // mod start 2016/07/05 CSA Defect#11355
        getSearchData(cMsg, sMsg, isSave);
        // mod end 2016/07/05 CSA Defect#11355
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
        }
    }

    /**
     * do process (TBLColumnSort)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_TBLColumnSort(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {

        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            int i = 0;
            // START 2016/07/25 [QC#11829, MOD]
            for (; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxRowNum_A.setValue(i + 1);
            }
            // END 2016/07/25 [QC#11829, MOD]
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // set Paging Data
            NSAL0810CommonLogic.pagenation(cMsg, sMsg, 0);
        }
    }

    /**
     * do process (download)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_Download(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSAL0810Query dsbl0450Query = NSAL0810Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(dsbl0450Query.getClass());

            // create csv file
            cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
            Map<String, Object> params = setDownloadPram(cMsg, sMsg);
            stmtSelect = ssmLLClient.createPreparedStatement("getDsContrIntfcList", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(NZZM0000E, null);
                return;
            }
            NSAL0810F00FMsg fMsg = new NSAL0810F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);
            writeCsvFileForHeaderTab(cMsg, sMsg, rs, fMsg, csvOutFile);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * do process (TemplateDownload)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_TemplateDownload(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {

        // create csv file
        cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSAL0810F00FMsg fMsg = new NSAL0810F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        csvOutFile.close();
    }

    /**
     * do process (ClickCheckbox)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_ClickCheckbox(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {

        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int idx = cMsg.xxRowNum_H.getValueInt();
        String checkFlg = cMsg.A.no(idx).xxChkBox_A.getValue();
        if (!ZYPCommonFunc.hasValue(cMsg.A.no(idx).dsContrSrcRefNum_A)) {
            return;
        }
        String dsContrSrcRefNum = cMsg.A.no(idx).dsContrSrcRefNum_A.getValue();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (dsContrSrcRefNum.equals(sMsg.A.no(i).dsContrSrcRefNum_A.getValue())) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(checkFlg)) {
                    setValue(sMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
                } else {
                    sMsg.A.no(i).xxChkBox_A.clear();
                }
            }
        }
        int rowIndex = NSAL0810CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0810CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (SelectAll)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_SelectAll(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {

        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
        }
        int rowIndex = NSAL0810CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0810CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (UnselectAll)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_UnselectAll(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {

        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            sMsg.A.no(i).xxChkBox_A.clear();
        }
        int rowIndex = NSAL0810CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0810CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Upload)
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    private void doProcess_NSAL0810Scrn00_Upload(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {

        clearSearchResultSummary(cMsg);

        // START 2016/05/20 [QC#4061, MOD]
        String excelPath = cMsg.xxFileData_U.getTempFilePath();
        String path = ZYPExcelUtil.excelToCsvFile(excelPath);
        // END   2016/05/20 [QC#4061, MOD]

        NSAL0810F00FMsg fMsg = new NSAL0810F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
            }
            int status = -1;
            int count = sMsg.A.getValidCount();
            // do basic check and load to screen(for all csv data)
            // START 2016/07/14 [QC#11918, DEL]
            // BigDecimal cntComplete = BigDecimal.ZERO;
            // BigDecimal cntErrorOrReprocess = BigDecimal.ZERO;
            // BigDecimal cntInProcess = BigDecimal.ZERO;
            // BigDecimal cntError = BigDecimal.ZERO;
            // END 2016/07/14 [QC#11918, DEL]
            while ((status = mappedFile.read()) != 1) {
                count++;
                List<String> msgKeys = new ArrayList<String>();
                List<List<String>> msgArgs = new ArrayList<List<String>>();
                List<EZDSItem> msgItems = new ArrayList<EZDSItem>();
// START 2016/07/14 [QC#11918, DEL]
//                if (hasValue(fMsg.dsContrProcStsCd_AS)) {
//                    if (DS_CONTR_PROC_STS.COMPLEATED.equals(fMsg.dsContrProcStsCd_AS.getValue())) {
//                        cntComplete = cntComplete.add(BigDecimal.ONE);
//                    } else if (DS_CONTR_PROC_STS.ERROR.equals(fMsg.dsContrProcStsCd_AS.getValue())) {
//                        cntErrorOrReprocess = cntErrorOrReprocess.add(BigDecimal.ONE);
//                        cntError = cntError.add(BigDecimal.ONE);
//                    } else if (DS_CONTR_PROC_STS.REPROCESS.equals(fMsg.dsContrProcStsCd_AS.getValue())) {
//                        cntErrorOrReprocess = cntErrorOrReprocess.add(BigDecimal.ONE);
//                    } else if (DS_CONTR_PROC_STS.IN_PROCESS.equals(fMsg.dsContrProcStsCd_AS.getValue())) {
//                        cntInProcess = cntInProcess.add(BigDecimal.ONE);
//                    }
//                }
// END 2016/07/14 [QC#11918, DEL]

                if (!validateAndCopyToSMsg_UPLOAD(status, count, sMsg.A, fMsg, cMsg, msgKeys, msgArgs, msgItems)) {
                    // if csvFile data over 200 ->return ErrorMessage
                    if (NSAM0214E.equals(cMsg.getMessageCode())) {
                        return;
                    }
                }
                fMsg.clear();
            }
            sMsg.A.setValidCount(count);

// START 2016/07/14 [QC#11918, DEL]
//            setValue(cMsg.xxSrchCnt_SP, cntComplete);
//            setValue(cMsg.xxSrchCnt_SR, cntErrorOrReprocess);
//            setValue(cMsg.xxSrchCnt_SU, cntInProcess);
//            setValue(cMsg.xxSrchCnt_SI, cntError);
// END   2016/07/14 [QC#11918, DEL]

            // 1.Interface Default Rule
            NSAL0810CommonLogic.setDefaultRule(getGlobalCompanyCode(), cMsg, sMsg, true);

            // 2.Validation Ds Contract Interface Table
            List<DS_CONTR_INTFCTMsg> dsContrIntfcTMsgList = NSAL0810CommonLogic.setDsContrIntfc(cMsg, sMsg, true);
            // mod start 2016/08/31 CSA QC#12566
            String masterKey = null;
            NSXC001001PMsg prmDbErrorPmsg = new NSXC001001PMsg();
            List<DS_CONTR_INTFCTMsg> chckTMsgList = new ArrayList<DS_CONTR_INTFCTMsg>();
            for (int i = 0; i < dsContrIntfcTMsgList.size(); i++) {

                if (i == 0) {
                    masterKey = dsContrIntfcTMsgList.get(i).dsContrIntfcBatNum.getValue() + "," + dsContrIntfcTMsgList.get(i).dsContrIntfcActCd.getValue();
                    chckTMsgList.add(dsContrIntfcTMsgList.get(i));
                }
                if (i > 0) {
                    String targetKey = dsContrIntfcTMsgList.get(i).dsContrIntfcBatNum.getValue() + "," + dsContrIntfcTMsgList.get(i).dsContrIntfcActCd.getValue();

                    if (targetKey.equals(masterKey)) {
                        chckTMsgList.add(dsContrIntfcTMsgList.get(i));
                    } else {
                        NSXC001001PMsg prmPmsg = new NSXC001001PMsg();
                        NSXC001001ValidationDsContrIntfc.validationDsContrIntfc(prmPmsg, chckTMsgList);

                        NSXC001001ValidationDsContrIntfc.updateValidationResult(prmDbErrorPmsg, chckTMsgList);
                        NSAL0810CommonLogic.updateValidationResult(cMsg, sMsg, chckTMsgList, true, true);

                        chckTMsgList.clear();
                        masterKey = targetKey;
                        chckTMsgList.add(dsContrIntfcTMsgList.get(i));
                    }
                }
            }
            NSXC001001PMsg prmPmsg = new NSXC001001PMsg();
            NSXC001001ValidationDsContrIntfc.validationDsContrIntfc(prmPmsg, chckTMsgList);

            NSXC001001ValidationDsContrIntfc.updateValidationResult(prmDbErrorPmsg, chckTMsgList);
            NSAL0810CommonLogic.updateValidationResult(cMsg, sMsg, chckTMsgList, true, true);
            // mod end 2016/08/31 CSA QC#12566
            S21SsmEZDResult ssmResult;
            for (DS_CONTR_INTFCTMsg tMsg : dsContrIntfcTMsgList) {
                if (!hasValue(tMsg.dsContrIntfcPk.getValue())) {
                    continue;
                }
// START 2016/02/15 [QC#3314, MOD]
                // Validation Check(DS_ACTL_CNT_INTFC)
                ssmResult = NSAL0810Query.getInstance().getDsActlCntrIntfc(cMsg.glblCmpyCd.getValue(), tMsg.dsContrIntfcPk.getValue());
                List<DS_ACTL_CNT_INTFCTMsg> dsActlCntrIntfcTMsgList = NSAL0810CommonLogic.getDsActlCntrIntfcList(ssmResult);
                NSXC001001PMsg pMsgDsActlCntIntfc = new NSXC001001PMsg();
                NSXC001001ValidationDsActlCntIntfc.validationDsActlCntIntfc(pMsgDsActlCntIntfc, dsActlCntrIntfcTMsgList);
                NSAL0810CommonLogic.updateCommonValidationResult(cMsg, sMsg, pMsgDsActlCntIntfc, tMsg, true, true);

                // Validation Check(DS_XS_COPY_INTFC)
                ssmResult = NSAL0810Query.getInstance().getDsXsCopyIntfc(cMsg.glblCmpyCd.getValue(), tMsg.dsContrIntfcPk.getValue());
                List<DS_XS_COPY_INTFCTMsg> dsXsCopyIntfcTMsgList = NSAL0810CommonLogic.getDsXsCopyIntfcList(ssmResult);
                ValidationReturnBean vrbDsXsCopyIntfc = NSXC001001ValidationDsXsCopyIntfc.validationDsXsCopyIntfc(dsXsCopyIntfcTMsgList);
                NSAL0810CommonLogic.updateCommonValidationResult(cMsg, sMsg, vrbDsXsCopyIntfc, tMsg, true, true);

                // Validation Check(DS_ADDL_CHRG_INTFC)
                ssmResult = NSAL0810Query.getInstance().getDsAddlChrgIntfc(cMsg.glblCmpyCd.getValue(), tMsg.dsContrIntfcPk.getValue());
                List<DS_ADDL_CHRG_INTFCTMsg> dsAddlChrgIntfcTMsgList = NSAL0810CommonLogic.getDsAddlChrgIntfcList(ssmResult);
                ValidationReturnBean vrbDsAddlChrgIntfc = NSXC001001ValidationDsAddlChrgIntfc.validationDsAddlChrgIntfc(dsAddlChrgIntfcTMsgList);
                NSAL0810CommonLogic.updateCommonValidationResult(cMsg, sMsg, vrbDsAddlChrgIntfc, tMsg, true, true);

                // Validation Check(PRC_ALLOC_INTFC)
                ssmResult = NSAL0810Query.getInstance().getPrcAllocIntfc(cMsg.glblCmpyCd.getValue(), tMsg.dsContrIntfcPk.getValue());
                List<PRC_ALLOC_INTFCTMsg> prcAllocIntfcTMsgList = NSAL0810CommonLogic.getPrcAllocIntfcList(ssmResult);
                ValidationReturnBean vrbPrcAllocIntfc = NSXC001001ValidationPrcAllocIntfc.validationPrcAllocIntfc(prcAllocIntfcTMsgList);
                NSAL0810CommonLogic.updateCommonValidationResult(cMsg, sMsg, vrbPrcAllocIntfc, tMsg, true, true);
// END   2016/02/15 [QC#3314, MOD]
            }

        } finally {
// START 2016/07/14 [QC#11918, ADD]
            // count up Proc Status
            NSAL0810CommonLogic.countupSMsgProcStatus(cMsg, sMsg);
// END   2016/07/14 [QC#11918, ADD]

            // set Paging Data
            NSAL0810CommonLogic.pagenation(cMsg, sMsg, 0);

            mappedFile.close();
            cMsg.xxFileData_U.deleteTempFile();
            // START 2016/05/20 [QC#4061, ADD]
            ZYPExcelUtil.deleteFile(path);
            // END   2016/05/20 [QC#4061, ADD]
        }
    }

    /**
     * get Search Data List
     * @param cMsg NSAL0810CMsg
     * @param isSave boolean
     * @return Data List
     */
    private void getSearchData(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg, boolean isSave) {

        sMsg.clear();
        S21SsmEZDResult ssmResult = NSAL0810Query.getInstance().getSearchData(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            setDsContrIntfcList(ssmResult, sMsg);
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            // mod start 2016/07/05 CSA Defect#11355
            if (queryResCnt > sMsg.A.length() && !isSave) {
            // mod end 2016/07/05 CSA Defect#11355
                cMsg.setMessageInfo(NZZM0001W);
            }
            if (NSAL0810CommonLogic.isAllowDataCrctFlg(getGlobalCompanyCode(), cMsg.contrIntfcSrcTpCd_SS.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd, MODE_AFTER_OPEN);
            }

            // START 2016/07/14 [QC#11918, ADD]
            // count up Proc Status
            NSAL0810CommonLogic.countupSMsgProcStatus(cMsg, sMsg);
            // END   2016/07/14 [QC#11918, ADD]

            // set Paging Data
            NSAL0810CommonLogic.pagenation(cMsg, sMsg, 0);

        } else {
            // mod start 2016/07/05 CSA Defect#11355
            if (!isSave) {
                // No result
                cMsg.setMessageInfo(NZZM0000E);
            }
            // mod end 2016/07/05 CSA Defect#11355
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.xxPageShowCurNum.clear();
            cMsg.xxPageShowTotNum.clear();
        }
    }

    /**
     * Write csv file
     * @param bizMsg NSAL0810CMsg
     * @param globalMsg NSAL0810SMsg
     * @param rs ResultSet
     * @param fMsg NSAL0810F00FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @throws SQLException
     */
    private void writeCsvFileForHeaderTab(NSAL0810CMsg bizMsg, NSAL0810SMsg globalMsg, ResultSet rs, NSAL0810F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, bizMsg);

        // write contents
        do {
            if (rs.getRow() > LIMIT_DOWNLOAD) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }
            // resultset -> fMsg
            setValue(fMsg.dsContrIntfcPk_A, rs.getBigDecimal("DS_CONTR_INTFC_PK"));
            setValue(fMsg.dsContrIntfcBatNum_A, rs.getString("DS_CONTR_INTFC_BAT_NUM"));
            setValue(fMsg.dsContrSrcRefNum_A, rs.getString("DS_CONTR_SRC_REF_NUM"));
            setValue(fMsg.contrIntfcSrcTpCd_AS, rs.getString("CONTR_INTFC_SRC_TP_CD"));
            setValue(fMsg.dsContrNum_A, rs.getString("DS_CONTR_NUM"));
            setValue(fMsg.dsContrIntfcActCd_AS, rs.getString("DS_CONTR_INTFC_ACT_CD"));
            setValue(fMsg.serNum_A, rs.getString("SER_NUM"));
            setValue(fMsg.svcMachMstrPk_A, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
            setValue(fMsg.contrIntfcDtlTpCd_AS, rs.getString("CONTR_INTFC_DTL_TP_CD"));
            setValue(fMsg.dsContrIntfcStsCd_A, rs.getString("DS_CONTR_INTFC_STS_CD"));
            setValue(fMsg.intfcErrMsgTxt_A, rs.getString("INTFC_ERR_MSG_TXT"));
            setValue(fMsg.dsContrProcStsCd_AS, rs.getString("DS_CONTR_PROC_STS_CD"));
            setValue(fMsg.dsAcctNum_A, rs.getString("DS_ACCT_NUM"));
            setValue(fMsg.dsAcctNm_A, rs.getString("DS_ACCT_NM"));
            setValue(fMsg.billToCustCd_A, rs.getString("BILL_TO_CUST_CD"));
            setValue(fMsg.leaseCmpyCd_A, rs.getString("LEASE_CMPY_CD"));
            setValue(fMsg.svcContrBrCd_A, rs.getString("SVC_CONTR_BR_CD"));
            // add start 2016/06/30 CSA Defect#10661
            setValue(fMsg.contrAdminPsnCd_A, rs.getString("CONTR_ADMIN_PSN_CD"));
            // add end 2016/06/30 CSA Defect#10661
            setValue(fMsg.tocCd_A, rs.getString("TOC_CD"));
            setValue(fMsg.custPoNum_A, rs.getString("CUST_PO_NUM"));
            // START 2016/10/28 T.Tomita [QC#15468, MOD]
//            setValue(fMsg.poDt_A, rs.getString("PO_DT"));
            setValue(fMsg.xxDtTxt_PO, convertDateFormat(rs.getString("PO_DT")));
            // END 2016/10/28 T.Tomita [QC#15468, MOD]
            setValue(fMsg.crCardCustRefNum_A, rs.getString("CR_CARD_CUST_REF_NUM"));
            // START 2016/10/28 T.Tomita [QC#15468, MOD]
//            setValue(fMsg.crCardExprYrMth_A, rs.getString("CR_CARD_EXPR_YR_MTH"));
            setValue(fMsg.xxScrItem7Txt_A, convertYrMthFormat(rs.getString("CR_CARD_EXPR_YR_MTH")));
            // END 2016/10/28 T.Tomita [QC#15468, MOD]
            setValue(fMsg.mtrEstTpCd_AS, rs.getString("MTR_EST_TP_CD"));
            setValue(fMsg.svcPgmMdseCd_A, rs.getString("SVC_PGM_MDSE_CD"));
            setValue(fMsg.mdseCd_A, rs.getString("MDSE_CD"));
            setValue(fMsg.mdlId_A, rs.getBigDecimal("MDL_ID"));
            setValue(fMsg.mdlNm_A, rs.getString("MDL_NM"));
            // START 2016/10/28 T.Tomita [QC#15468, MOD]
//            setValue(fMsg.contrFromDt_A, rs.getString("CONTR_FROM_DT"));
//            setValue(fMsg.contrThruDt_A, rs.getString("CONTR_THRU_DT"));
            setValue(fMsg.contrEffFromDtTxt_A, convertDateFormat(rs.getString("CONTR_FROM_DT")));
            setValue(fMsg.contrEffThruDtTxt_A, convertDateFormat(rs.getString("CONTR_THRU_DT")));
            // END 2016/10/28 T.Tomita [QC#15468, MOD]
            setValue(fMsg.bllgCycleCd_AS, rs.getString("BLLG_CYCLE_CD"));
            setValue(fMsg.prcAllocByMachQtyFlg_A, rs.getString("PRC_ALLOC_BY_MACH_QTY_FLG"));
            setValue(fMsg.contrAutoRnwTpCd_AS, rs.getString("CONTR_AUTO_RNW_TP_CD"));
            setValue(fMsg.rnwPrcMethCd_AS, rs.getString("RNW_PRC_METH_CD"));
            setValue(fMsg.befEndRnwDaysAot_A, rs.getBigDecimal("BEF_END_RNW_DAYS_AOT"));
            setValue(fMsg.rnwPrcUpRatio_A, rs.getBigDecimal("RNW_PRC_UP_RATIO"));
            setValue(fMsg.contrUplftTpCd_AS, rs.getString("CONTR_UPLFT_TP_CD"));
            setValue(fMsg.uplftPrcMethCd_AS, rs.getString("UPLFT_PRC_METH_CD"));
            setValue(fMsg.uplftPrcUpRatio_A, rs.getBigDecimal("UPLFT_PRC_UP_RATIO"));
            setValue(fMsg.mtrReadMethCd_AS, rs.getString("MTR_READ_METH_CD"));
            setValue(fMsg.basePrcDealAmt_A, rs.getBigDecimal("BASE_PRC_DEAL_AMT"));
            setValue(fMsg.contrCloDay_A, rs.getString("CONTR_CLO_DAY"));
            setValue(fMsg.contrBllgDay_A, rs.getString("CONTR_BLLG_DAY"));
            // START 2016/10/28 T.Tomita [QC#15468, MOD]
//            setValue(fMsg.bllgThruDt_A, rs.getString("BLLG_THRU_DT"));
            setValue(fMsg.xxDtTxt_BT, convertDateFormat(rs.getString("BLLG_THRU_DT")));
            // END 2016/10/28 T.Tomita [QC#15468, MOD]
            setValue(fMsg.bllgMtrLbCd_A, rs.getString("BLLG_MTR_LB_CD"));
            //setValue(fMsg.mtrLbNm_A, rs.getString("BLLG_MTR_LB_NM"));
            // START 2016/05/25 T.Tomita [QC#8898, ADD]
            setValue(fMsg.mtrLbDescTxt_A, rs.getString("MTR_LB_DESC_TXT"));
            // END 2016/05/25 T.Tomita [QC#8898, ADD]
            setValue(fMsg.startMtrCnt_A, rs.getBigDecimal("START_MTR_CNT"));
            setValue(fMsg.bllgRollOverRatio_A, rs.getBigDecimal("BLLG_ROLL_OVER_RATIO"));
            setValue(fMsg.dsContrCatgCd_AS, rs.getString("DS_CONTR_CATG_CD"));
            setValue(fMsg.dsContrStsCd_AS, rs.getString("DS_CONTR_STS_CD"));
            setValue(fMsg.xsChrgTpCd_AS, rs.getString("XS_CHRG_TP_CD"));
            setValue(fMsg.xsMtrCopyQty_A, rs.getBigDecimal("XS_MTR_COPY_QTY"));
            setValue(fMsg.xsMtrAmtRate_A, rs.getBigDecimal("XS_MTR_AMT_RATE"));
            setValue(fMsg.addlChrgTpCd_AS, rs.getString("ADDL_CHRG_TP_CD"));
            setValue(fMsg.addlChrgFlatDealPrcAmt_A, rs.getBigDecimal("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
            setValue(fMsg.addlChrgAplcPct_A, rs.getBigDecimal("ADDL_CHRG_APLC_PCT"));
            setValue(fMsg.basePrcTermDealAmtRate_A, rs.getBigDecimal("BASE_PRC_TERM_DEAL_AMT_RATE"));
            setValue(fMsg.dsContrClsCd_AS, rs.getString("DS_CONTR_CLS_CD"));
            setValue(fMsg.ctacPsnPk_A, rs.getBigDecimal("CTAC_PSN_PK"));
            setValue(fMsg.ctacPsnNm_A, rs.getString("CTAC_PSN_NM"));
        	// START 2022/03/22 [QC#59683, MOD]
//            setValue(fMsg.invSeptBaseUsgFlg_A, rs.getString("INV_SEPT_BASE_USG_FLG"));
            setValue(fMsg.dsInvTgtrTpCd_A, rs.getString("DS_INV_TGTR_TP_CD"));
            setValue(fMsg.dsInvTgtrTpDescTxt_A, rs.getString("DS_INV_TGTR_TP_DESC_TXT"));
        	// END   2022/03/22 [QC#59683, MOD]
            // START 2016/10/28 T.Tomita [QC#15468, MOD]
//            setValue(fMsg.contrCloDt_A, rs.getString("CONTR_CLO_DT"));
            setValue(fMsg.xxDtTxt_CL, convertDateFormat(rs.getString("CONTR_CLO_DT")));
            // END 2016/10/28 T.Tomita [QC#15468, MOD]
            setValue(fMsg.contrDurnAot_A, rs.getBigDecimal("CONTR_DURN_AOT"));
            setValue(fMsg.pmtTermCashDiscCd_A, rs.getString("PMT_TERM_CASH_DISC_CD"));
            setValue(fMsg.svcLineBizCd_AS, rs.getString("SVC_LINE_BIZ_CD"));
            setValue(fMsg.bllgTmgTpCd_AS, rs.getString("BLLG_TMG_TP_CD"));
            setValue(fMsg.dsContrEdiCd_A, rs.getString("DS_CONTR_EDI_CD"));
            setValue(fMsg.dsContrDescTxt_A, rs.getString("DS_CONTR_DESC_TXT"));
            setValue(fMsg.baseChrgToLeaseCmpyFlg_A, rs.getString("BASE_CHRG_TO_LEASE_CMPY_FLG"));
            setValue(fMsg.usgChrgToLeaseCmpyFlg_A, rs.getString("USG_CHRG_TO_LEASE_CMPY_FLG"));
            setValue(fMsg.intgMdseCd_A, rs.getString("INTG_MDSE_CD"));
            setValue(fMsg.capBwOrigQty_A, rs.getBigDecimal("CAP_BW_ORIG_QTY"));
            setValue(fMsg.capColorOrigQty_A, rs.getBigDecimal("CAP_COLOR_ORIG_QTY"));
            setValue(fMsg.capTotOrigQty_A, rs.getBigDecimal("CAP_TOT_ORIG_QTY"));
            setValue(fMsg.capBwRunQty_A, rs.getBigDecimal("CAP_BW_RUN_QTY"));
            setValue(fMsg.capColorRunQty_A, rs.getBigDecimal("CAP_COLOR_RUN_QTY"));
            setValue(fMsg.capTotRunQty_A, rs.getBigDecimal("CAP_TOT_RUN_QTY"));
            setValue(fMsg.chrgLvlTpCd_AS, rs.getString("CHRG_LVL_TP_CD"));
            setValue(fMsg.addlChrgInvTpCd_AS, rs.getString("ADDL_CHRG_INV_TP_CD"));
            // START 2016/05/20 [QC#4061, ADD]
            setValue(fMsg.manContrOvrdFlg_A, rs.getString("MAN_CONTR_OVRD_FLG"));
            // END   2016/05/20 [QC#4061, ADD]

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL0810F00FMsg fMsg, NSAL0810CMsg cMsg) {
       final String[] csvHeader = new String[] {
                "DS Contract Interface PK"
                , "Interface Bat#"
                , "Source Ref#"
                , "Source Type"
                , "Contract#"
                , "Action"
                , "Serial#"
                , "IB ID"
                , "Line Type"
                , "Proc Flag"
                , "Proc Message"
                , "Proc Status"
                , "Cust Acct Num"
                , "Cust Acct Name"
                , "Bill To Customer Number"
                , "Lease Company Num"
                , "Contract Branch"
                // mod start 2016/06/30 CSA Defect#10661
                , "Contract Rep"
                , "Sales Rep"
                // mod end 2016/06/30 CSA Defect#10661
                , "PO #"
                , "PO Exp Date"
                , "CC Reference#"
                , "CC Exp Date"
                , "Auto Estimation Code"
                , "Service Program"
                , "Mdse Code"
                , "Model ID"
                , "Model Name"
                , "Start Date"
                , "End Date"
                , "Frequency"
                , "Alloc Across Machines"
                , "Renewal Type"
                , "Renewal Method"
                , "# Days Before"
                , "Renewal %"
                , "Annual Escalation Type"
                , "Escalation Method"
                , "Escalation Percent"
                , "Meter Read Method"
                , "Price Per Period"
                , "Period End Date Ctrl"
                , "Invoice Date Ctrl"
                , "Bill Through Date"
                , "Overage Counter Code"
                , "Overage Counter Name"
                , "Start Reading"
                , "Counter Rollover %"
                , "Contract Category"
                , "Contract Status"
                , "Excess Charge Type"
                , "Excess Meter Copy Qty"
                , "Excess Meter Amount Rate"
                , "Additional Charge Type"
                , "Additional Charge Amount"
                , "Additional Charge Applicable %"
                , "Term Amount Rate"
                , "Contract Class"
                , "Contact Person ID"
                , "Contact Person Name"
                // START 2022/03/22 [QC#59683, MOD]
//                , "Invoice Separate"
                , "Invoice Together Type Code"
                , "Invoice Together Type Name"
                // END   2022/03/22 [QC#59683, MOD]
                , "Contract Close Date"
                , "Duration"
                , "Payment Term Discount"
                , "Line of Business"
                , "Billing Timing"
                , "Contract EDI"
                , "Contract Description"
                , "Base Charge To Lease Company"
                , "Usage Charge To Lease Company"
                , "Intangible Merchandise Code"
                , "Cap BW Original Qty"
                , "Cap Color Original Qty"
                , "Cap Total Original Qty"
                , "Cap BW Running Qty"
                , "Cap Color Running Qty"
                , "Cap Total Running Qty"
                , "Charge Level Type"
                , "Additional Charge Invoice Type"
                , "Manual Contract Override" };
       csvOutFile.writeHeader(csvHeader);
    }

    /**
     * setDownloadPram
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     * @return S21SsmEZDResult
     */
    private Map<String, Object> setDownloadPram(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(cMsg.contrIntfcSrcTpCd_SS)) {
            params.put("contrIntfcSrcTpCd", (String) cMsg.contrIntfcSrcTpCd_SS.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsContrSrcRefNum_S)) {
            params.put("dsContrSrcRefNum", (String) cMsg.dsContrSrcRefNum_S.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsContrIntfcBatNum_S)) {
            params.put("dsContrIntfcBatNum", (String) cMsg.dsContrIntfcBatNum_S.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsContrIntfcDt_SF)) {
            params.put("dsContrIntfcDtFrom", (String) cMsg.dsContrIntfcDt_SF.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsContrIntfcDt_ST)) {
            params.put("dsContrIntfcDtThru", (String) cMsg.dsContrIntfcDt_ST.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxErrFlg_S) && ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxErrFlg_S.getValue())) {
            params.put("errorFlg", cMsg.xxErrFlg_S.getValue());
            params.put("error", DS_CONTR_PROC_STS.ERROR);
            params.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
            params.put("contrIntfcStsError", DS_CONTR_INTFC_STS.ERROR);
        } else {
            params.put("errorFlg", null);
        }
        params.put("limitNum", LIMIT_DOWNLOAD + 1);

        return params;
    }

    private void setDsContrIntfcList(S21SsmEZDResult ssmResult, NSAL0810SMsg sMsg) {
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
        int maxCnt = 0;
        if (list.size() > sMsg.A.length()) {
            maxCnt = sMsg.A.length();
        } else {
            maxCnt = list.size();
        }
        for (int i = 0; i < maxCnt; i++) {
            Map<String, Object> map = list.get(i);
            setValue(sMsg.A.no(i).glblCmpyCd_A, (String) map.get("GLBL_CMPY_CD"));
            setValue(sMsg.A.no(i).dsContrIntfcPk_A, (BigDecimal) map.get("DS_CONTR_INTFC_PK"));
            setValue(sMsg.A.no(i).contrIntfcSrcTpCd_AS, (String) map.get("CONTR_INTFC_SRC_TP_CD"));
            setValue(sMsg.A.no(i).dsContrSrcRefNum_A, (String) map.get("DS_CONTR_SRC_REF_NUM"));
            setValue(sMsg.A.no(i).dsContrIntfcBatNum_A, (String) map.get("DS_CONTR_INTFC_BAT_NUM"));
            setValue(sMsg.A.no(i).dsContrNum_A, (String) map.get("DS_CONTR_NUM"));
            setValue(sMsg.A.no(i).dsContrIntfcActCd_AS, (String) map.get("DS_CONTR_INTFC_ACT_CD"));
            setValue(sMsg.A.no(i).serNum_A, (String) map.get("SER_NUM"));
            setValue(sMsg.A.no(i).svcMachMstrPk_A, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
            setValue(sMsg.A.no(i).contrIntfcDtlTpCd_AS, (String) map.get("CONTR_INTFC_DTL_TP_CD"));
            setValue(sMsg.A.no(i).contrIntfcDtlTpCd_A, (String) map.get("CONTR_INTFC_DTL_TP_CD"));
            setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, (String) map.get("DS_CONTR_INTFC_STS_CD"));
            setValue(sMsg.A.no(i).intfcErrMsgTxt_A, (String) map.get("INTFC_ERR_MSG_TXT"));
            setValue(sMsg.A.no(i).dsContrProcStsDescTxt_A, (String) map.get("DS_CONTR_PROC_STS_DESC_TXT"));
            setValue(sMsg.A.no(i).dsContrProcStsCd_AS, (String) map.get("DS_CONTR_PROC_STS_CD"));
            setValue(sMsg.A.no(i).dsAcctNum_A, (String) map.get("DS_ACCT_NUM"));
            setValue(sMsg.A.no(i).dsAcctNm_AC, (String) map.get("DS_ACCT_NM"));
            setValue(sMsg.A.no(i).billToCustCd_A, (String) map.get("BILL_TO_CUST_CD"));
            setValue(sMsg.A.no(i).dsAcctNm_AB, (String) map.get("DS_ACCT_NM"));
            setValue(sMsg.A.no(i).billToLocNum_A, (String) map.get("BILL_TO_LOC_NUM"));
            setValue(sMsg.A.no(i).leaseCmpyCd_A, (String) map.get("LEASE_CMPY_CD"));
            setValue(sMsg.A.no(i).dsAcctNm_AL, (String) map.get("DS_ACCT_NM"));
            setValue(sMsg.A.no(i).svcContrBrCd_A, (String) map.get("SVC_CONTR_BR_CD"));
            // add start 2016/06/30 CSA Defect#10661
            setValue(sMsg.A.no(i).contrAdminPsnCd_A, (String) map.get("CONTR_ADMIN_PSN_CD"));
            // add end 2016/06/30 CSA Defect#10661
            setValue(sMsg.A.no(i).tocCd_A, (String) map.get("TOC_CD"));
            setValue(sMsg.A.no(i).custPoNum_A, (String) map.get("CUST_PO_NUM"));
            setValue(sMsg.A.no(i).poDt_A, (String) map.get("PO_DT"));
            setValue(sMsg.A.no(i).crCardCustRefNum_A, (String) map.get("CR_CARD_CUST_REF_NUM"));
            setValue(sMsg.A.no(i).crCardExprYrMth_A, (String) map.get("CR_CARD_EXPR_YR_MTH"));
            setValue(sMsg.A.no(i).mtrEstTpCd_AS, (String) map.get("MTR_EST_TP_CD"));
            setValue(sMsg.A.no(i).svcPgmMdseCd_A, (String) map.get("SVC_PGM_MDSE_CD"));
            setValue(sMsg.A.no(i).mdseCd_A, (String) map.get("MDSE_CD"));
            setValue(sMsg.A.no(i).mdlId_A, (BigDecimal) map.get("MDL_ID"));
            setValue(sMsg.A.no(i).mdlNm_A, (String) map.get("MDL_NM"));
            setValue(sMsg.A.no(i).contrFromDt_A, (String) map.get("CONTR_FROM_DT"));
            setValue(sMsg.A.no(i).contrThruDt_A, (String) map.get("CONTR_THRU_DT"));
            setValue(sMsg.A.no(i).bllgCycleCd_AS, (String) map.get("BLLG_CYCLE_CD"));
            setValue(sMsg.A.no(i).prcAllocByMachQtyFlg_A, (String) map.get("PRC_ALLOC_BY_MACH_QTY_FLG"));
            setValue(sMsg.A.no(i).contrAutoRnwTpCd_AS, (String) map.get("CONTR_AUTO_RNW_TP_CD"));
            setValue(sMsg.A.no(i).rnwPrcMethCd_AS, (String) map.get("RNW_PRC_METH_CD"));
            setValue(sMsg.A.no(i).befEndRnwDaysAot_A, (BigDecimal) map.get("BEF_END_RNW_DAYS_AOT"));
            setValue(sMsg.A.no(i).rnwPrcUpRatio_A, (BigDecimal) map.get("RNW_PRC_UP_RATIO"));
            setValue(sMsg.A.no(i).contrUplftTpCd_AS, (String) map.get("CONTR_UPLFT_TP_CD"));
            setValue(sMsg.A.no(i).uplftPrcMethCd_AS, (String) map.get("UPLFT_PRC_METH_CD"));
            setValue(sMsg.A.no(i).uplftPrcUpRatio_A, (BigDecimal) map.get("UPLFT_PRC_UP_RATIO"));
            setValue(sMsg.A.no(i).mtrReadMethCd_AS, (String) map.get("MTR_READ_METH_CD"));
            setValue(sMsg.A.no(i).basePrcDealAmt_A, (BigDecimal) map.get("BASE_PRC_DEAL_AMT"));
            setValue(sMsg.A.no(i).contrCloDay_A, (String) map.get("CONTR_CLO_DAY"));
            setValue(sMsg.A.no(i).contrBllgDay_A, (String) map.get("CONTR_BLLG_DAY"));
            setValue(sMsg.A.no(i).bllgThruDt_A, (String) map.get("BLLG_THRU_DT"));
            setValue(sMsg.A.no(i).bllgMtrLbCd_A, (String) map.get("BLLG_MTR_LB_CD"));
            // START 2016/05/25 T.Tomita [QC#8898, MOD]
//            setValue(sMsg.A.no(i).bllgMtrLbCd_AS, (String) map.get("BLLG_MTR_LB_CD"));
            setValue(sMsg.A.no(i).mtrLbDescTxt_A, (String) map.get("MTR_LB_DESC_TXT"));
            // END 2016/05/25 T.Tomita [QC#8898, MOD]
            setValue(sMsg.A.no(i).startMtrCnt_A, (BigDecimal) map.get("START_MTR_CNT"));
            setValue(sMsg.A.no(i).bllgRollOverRatio_A, (BigDecimal) map.get("BLLG_ROLL_OVER_RATIO"));
            setValue(sMsg.A.no(i).dsContrCatgCd_AS, (String) map.get("DS_CONTR_CATG_CD"));
            setValue(sMsg.A.no(i).dsContrStsCd_AS, (String) map.get("DS_CONTR_STS_CD"));
            setValue(sMsg.A.no(i).xsChrgTpCd_AS, (String) map.get("XS_CHRG_TP_CD"));
            setValue(sMsg.A.no(i).xsMtrCopyQty_A, (BigDecimal) map.get("XS_MTR_COPY_QTY"));
            setValue(sMsg.A.no(i).xsMtrAmtRate_A, (BigDecimal) map.get("XS_MTR_AMT_RATE"));
            setValue(sMsg.A.no(i).addlChrgTpCd_AS, (String) map.get("ADDL_CHRG_TP_CD"));
            setValue(sMsg.A.no(i).addlChrgFlatDealPrcAmt_A, (BigDecimal) map.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
            setValue(sMsg.A.no(i).addlChrgAplcPct_A, (BigDecimal) map.get("ADDL_CHRG_APLC_PCT"));
            setValue(sMsg.A.no(i).chrgLvlTpCd_AS, (String) map.get("CHRG_LVL_TP_CD"));
            setValue(sMsg.A.no(i).addlChrgInvTpCd_AS, (String) map.get("ADDL_CHRG_INV_TP_CD"));
            setValue(sMsg.A.no(i).printDtlFlg_A, (String) map.get("PRINT_DTL_FLG"));
            setValue(sMsg.A.no(i).basePrcTermDealAmtRate_A, (BigDecimal) map.get("BASE_PRC_TERM_DEAL_AMT_RATE"));
            setValue(sMsg.A.no(i).dsContrClsCd_AS, (String) map.get("DS_CONTR_CLS_CD"));
            setValue(sMsg.A.no(i).ctacPsnPk_A, (BigDecimal) map.get("CTAC_PSN_PK"));
            setValue(sMsg.A.no(i).ctacPsnNm_A, (String) map.get("CTAC_PSN_NM"));
        	// START 2022/03/22 [QC#59683, MOD]
//            setValue(sMsg.A.no(i).invSeptBaseUsgFlg_A, (String) map.get("INV_SEPT_BASE_USG_FLG"));
            setValue(sMsg.A.no(i).dsInvTgtrTpCd_AS, (String) map.get("DS_INV_TGTR_TP_CD"));
        	// END   2022/03/22 [QC#59683, MOD]
            setValue(sMsg.A.no(i).contrCloDt_A, (String) map.get("CONTR_CLO_DT"));
            setValue(sMsg.A.no(i).contrDurnAot_A, (BigDecimal) map.get("CONTR_DURN_AOT"));
            setValue(sMsg.A.no(i).pmtTermCashDiscCd_A, (String) map.get("PMT_TERM_CASH_DISC_CD"));
            setValue(sMsg.A.no(i).svcLineBizCd_AS, (String) map.get("SVC_LINE_BIZ_CD"));
            setValue(sMsg.A.no(i).bllgTmgTpCd_AS, (String) map.get("BLLG_TMG_TP_CD"));
            setValue(sMsg.A.no(i).dsContrEdiCd_A, (String) map.get("DS_CONTR_EDI_CD"));
            setValue(sMsg.A.no(i).dsContrDescTxt_A, (String) map.get("DS_CONTR_DESC_TXT"));
            setValue(sMsg.A.no(i).baseChrgToLeaseCmpyFlg_A, (String) map.get("BASE_CHRG_TO_LEASE_CMPY_FLG"));
            setValue(sMsg.A.no(i).usgChrgToLeaseCmpyFlg_A, (String) map.get("USG_CHRG_TO_LEASE_CMPY_FLG"));
            setValue(sMsg.A.no(i).intgMdseCd_A, (String) map.get("INTG_MDSE_CD"));
            setValue(sMsg.A.no(i).dsContrIntfcDt_A, (String) map.get("DS_CONTR_INTFC_DT"));
            setValue(sMsg.A.no(i).capBwOrigQty_A, (BigDecimal) map.get("CAP_BW_ORIG_QTY"));
            setValue(sMsg.A.no(i).capColorOrigQty_A, (BigDecimal) map.get("CAP_COLOR_ORIG_QTY"));
            setValue(sMsg.A.no(i).capTotOrigQty_A, (BigDecimal) map.get("CAP_TOT_ORIG_QTY"));
            setValue(sMsg.A.no(i).capBwRunQty_A, (BigDecimal) map.get("CAP_BW_RUN_QTY"));
            setValue(sMsg.A.no(i).capColorRunQty_A, (BigDecimal) map.get("CAP_COLOR_RUN_QTY"));
            setValue(sMsg.A.no(i).capTotRunQty_A, (BigDecimal) map.get("CAP_TOT_RUN_QTY"));
            // START 2016/05/20 [QC#4061, ADD]
            setValue(sMsg.A.no(i).manContrOvrdFlg_A, (String) map.get("MAN_CONTR_OVRD_FLG"));
            // END   2016/05/20 [QC#4061, ADD]
            setValue(sMsg.A.no(i).ezUpTime_A, (String) map.get("EZUPTIME"));
            setValue(sMsg.A.no(i).ezUpTimeZone_A, (String) map.get("EZUPTIMEZONE"));
            setValue(sMsg.A.no(i).xxRowNum_A, (BigDecimal) map.get("XX_ROW_NUM"));
            // START 2016/11/28 K.Kojima [QC#14204,ADD]
            setValue(sMsg.A.no(i).xxRecHistCratTs_A, (String) map.get("XX_REC_HIST_CRAT_TS_A"));
            setValue(sMsg.A.no(i).xxRecHistCratByNm_A, (String) map.get("XX_REC_HIST_CRAT_BY_NM_A"));
            setValue(sMsg.A.no(i).xxRecHistUpdTs_A, (String) map.get("XX_REC_HIST_UPD_TS_A"));
            setValue(sMsg.A.no(i).xxRecHistUpdByNm_A, (String) map.get("XX_REC_HIST_UPD_BY_NM_A"));
            setValue(sMsg.A.no(i).xxRecHistTblNm_A, (String) map.get("XX_REC_HIST_TBL_NM_A"));
            // END 2016/11/28 K.Kojima [QC#14204,ADD]
        }
        sMsg.A.setValidCount(maxCnt);
    }

    private boolean validateAndCopyToSMsg_UPLOAD(int status, int count, NSAL0810_ASMsgArray asMsgArray, NSAL0810F00FMsg fMsg, NSAL0810CMsg cMsg, List<String> msgKeys, List<List<String>> msgArgs, List<EZDSItem> msgItems) {
        if (count > asMsgArray.length()) {
            cMsg.setMessageInfo(NSAM0214E);
            return false;
        }
        int row = count - 1;
        NSAL0810_ASMsg sMsgLine = asMsgArray.no(row);
        setValue(sMsgLine.xxRowNum_A, BigDecimal.valueOf(count));

        if (status == 1000) {
            setValue(sMsgLine.intfcErrMsgTxt_A, new EZDMessageInfo(NSAM0209E).getMessage());
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0209E);
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            return false;
        } else if (status > 1000 && status < 2000) {
            setValue(sMsgLine.intfcErrMsgTxt_A, new EZDMessageInfo(NSAM0209E).getMessage());
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0209E);
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            return false;
        } else if (status >= 2000) {
            setValue(sMsgLine.intfcErrMsgTxt_A, new EZDMessageInfo(NSAM0210E).getMessage());
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0210E);
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            return false;
        }

        setValue(sMsgLine.dsContrIntfcPk_A, fMsg.dsContrIntfcPk_A);
        setValue(sMsgLine.dsContrIntfcBatNum_A, fMsg.dsContrIntfcBatNum_A);
        setValue(sMsgLine.dsContrSrcRefNum_A, fMsg.dsContrSrcRefNum_A);
        setValue(sMsgLine.contrIntfcSrcTpCd_AS, fMsg.contrIntfcSrcTpCd_AS);
        setValue(sMsgLine.dsContrNum_A, fMsg.dsContrNum_A);
        setValue(sMsgLine.dsContrIntfcActCd_AS, fMsg.dsContrIntfcActCd_AS);
        setValue(sMsgLine.serNum_A, fMsg.serNum_A);
        if (hasValue(fMsg.svcMachMstrPk_A)) {
            setValue(sMsgLine.svcMachMstrPk_A, fMsg.svcMachMstrPk_A);
        } else {
            setValue(sMsgLine.svcMachMstrPk_A, getSvcMachMstrPk(getGlobalCompanyCode(), fMsg.serNum_A.getValue()));
        }
        setValue(sMsgLine.contrIntfcDtlTpCd_AS, fMsg.contrIntfcDtlTpCd_AS);

        // START 2016/07/14 [QC#11844, MOD]
        //setValue(sMsgLine.dsContrIntfcStsCd_A, fMsg.dsContrIntfcStsCd_A);
        //setValue(sMsgLine.intfcErrMsgTxt_A, fMsg.intfcErrMsgTxt_A);
        //setValue(sMsgLine.dsContrProcStsCd_AS, fMsg.dsContrProcStsCd_AS);
        setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.NORMAL);
        sMsgLine.intfcErrMsgTxt_A.clear();
        setValue(sMsgLine.dsContrProcStsCd_AS, DS_CONTR_PROC_STS.CREATE);
        // END  2016/07/14 [QC#11844, MOD]

        setValue(sMsgLine.dsAcctNum_A, fMsg.dsAcctNum_A);
        setValue(sMsgLine.dsAcctNm_AC, getDsAcctNm(getGlobalCompanyCode(), fMsg.dsAcctNum_A.getValue()));
        setValue(sMsgLine.billToCustCd_A, fMsg.billToCustCd_A);
        setValue(sMsgLine.leaseCmpyCd_A, fMsg.leaseCmpyCd_A);
        setValue(sMsgLine.svcContrBrCd_A, fMsg.svcContrBrCd_A);
        // add start 2016/06/30 CSA Defect#10661
        setValue(sMsgLine.contrAdminPsnCd_A, fMsg.contrAdminPsnCd_A);
        // add end 2016/06/30 CSA Defect#10661
        setValue(sMsgLine.tocCd_A, fMsg.tocCd_A);
        setValue(sMsgLine.custPoNum_A, fMsg.custPoNum_A);
        // START 2016/10/28 T.Tomita [QC#15468, MOD]
//        setValue(sMsgLine.poDt_A, fMsg.poDt_A);
        setValue(sMsgLine.poDt_A, decodeDateFormat(fMsg.xxDtTxt_PO.getValue()));
        // END 2016/10/28 T.Tomita [QC#15468, MOD]
        setValue(sMsgLine.crCardCustRefNum_A, fMsg.crCardCustRefNum_A);
        // START 2016/10/28 T.Tomita [QC#15468, MOD]
//        setValue(sMsgLine.crCardExprYrMth_A, fMsg.crCardExprYrMth_A);
        setValue(sMsgLine.crCardExprYrMth_A, decodeYrMthFormat(fMsg.xxScrItem7Txt_A.getValue()));
        // END 2016/10/28 T.Tomita [QC#15468, MOD]
        setValue(sMsgLine.mtrEstTpCd_AS, fMsg.mtrEstTpCd_AS);
        setValue(sMsgLine.svcPgmMdseCd_A, fMsg.svcPgmMdseCd_A);
        setValue(sMsgLine.mdseCd_A, fMsg.mdseCd_A);
        if (hasValue(fMsg.mdlId_A)) {
            setValue(sMsgLine.mdlId_A, fMsg.mdlId_A);
        } else {
            setValue(sMsgLine.mdlId_A, getMdlId(getGlobalCompanyCode(), fMsg.mdlNm_A.getValue()));
        }
        setValue(sMsgLine.mdlNm_A, fMsg.mdlNm_A);
        // START 2016/10/28 T.Tomita [QC#15468, MOD]
//        setValue(sMsgLine.contrFromDt_A, fMsg.contrFromDt_A);
//        setValue(sMsgLine.contrThruDt_A, fMsg.contrThruDt_A);
        setValue(sMsgLine.contrFromDt_A, decodeDateFormat(fMsg.contrEffFromDtTxt_A.getValue()));
        setValue(sMsgLine.contrThruDt_A, decodeDateFormat(fMsg.contrEffThruDtTxt_A.getValue()));
        // END 2016/10/28 T.Tomita [QC#15468, MOD]
        setValue(sMsgLine.bllgCycleCd_AS, fMsg.bllgCycleCd_AS);
        setValue(sMsgLine.prcAllocByMachQtyFlg_A, fMsg.prcAllocByMachQtyFlg_A);
        setValue(sMsgLine.contrAutoRnwTpCd_AS, fMsg.contrAutoRnwTpCd_AS);
        setValue(sMsgLine.rnwPrcMethCd_AS, fMsg.rnwPrcMethCd_AS);
        setValue(sMsgLine.befEndRnwDaysAot_A, fMsg.befEndRnwDaysAot_A);
        setValue(sMsgLine.rnwPrcUpRatio_A, fMsg.rnwPrcUpRatio_A);
        setValue(sMsgLine.contrUplftTpCd_AS, fMsg.contrUplftTpCd_AS);
        setValue(sMsgLine.uplftPrcMethCd_AS, fMsg.uplftPrcMethCd_AS);
        setValue(sMsgLine.uplftPrcUpRatio_A, fMsg.uplftPrcUpRatio_A);
        setValue(sMsgLine.mtrReadMethCd_AS, fMsg.mtrReadMethCd_AS);
        setValue(sMsgLine.basePrcDealAmt_A, fMsg.basePrcDealAmt_A);
        setValue(sMsgLine.contrCloDay_A, fMsg.contrCloDay_A);
        setValue(sMsgLine.contrBllgDay_A, fMsg.contrBllgDay_A);
        // START 2016/10/28 T.Tomita [QC#15468, MOD]
//        setValue(sMsgLine.bllgThruDt_A, fMsg.bllgThruDt_A);
        setValue(sMsgLine.bllgThruDt_A, decodeDateFormat(fMsg.xxDtTxt_BT.getValue()));
        // END 2016/10/28 T.Tomita [QC#15468, MOD]
        // mod start 2016/11/28 CSA Defect#12077
        // START 2016/05/25 T.Tomita [QC#8898, MOD]
        if (CONTR_INTFC_DTL_TP.USAGE.equals(sMsgLine.contrIntfcDtlTpCd_AS.getValue())) {
            if (hasValue(fMsg.bllgMtrLbCd_A)) {
                setValue(sMsgLine.bllgMtrLbCd_A, fMsg.bllgMtrLbCd_A);
            } else {
                String bllgMtrLbCd = getBllgMtrLbCd(getGlobalCompanyCode(), fMsg.mtrLbDescTxt_A.getValue());
                setValue(sMsgLine.bllgMtrLbCd_A, bllgMtrLbCd);
            }
            setValue(sMsgLine.mtrLbDescTxt_A, fMsg.mtrLbDescTxt_A);
        }
        // mod end 2016/11/28 CSA Defect#12077
        // END 2016/05/25 T.Tomita [QC#8898, MOD]
        setValue(sMsgLine.startMtrCnt_A, fMsg.startMtrCnt_A);
        setValue(sMsgLine.bllgRollOverRatio_A, fMsg.bllgRollOverRatio_A);
        setValue(sMsgLine.dsContrCatgCd_AS, fMsg.dsContrCatgCd_AS);
        setValue(sMsgLine.dsContrStsCd_AS, fMsg.dsContrStsCd_AS);
        setValue(sMsgLine.xsChrgTpCd_AS, fMsg.xsChrgTpCd_AS);
        setValue(sMsgLine.xsMtrCopyQty_A, fMsg.xsMtrCopyQty_A);
        setValue(sMsgLine.xsMtrAmtRate_A, fMsg.xsMtrAmtRate_A);
        setValue(sMsgLine.addlChrgTpCd_AS, fMsg.addlChrgTpCd_AS);
        setValue(sMsgLine.addlChrgFlatDealPrcAmt_A, fMsg.addlChrgFlatDealPrcAmt_A);
        setValue(sMsgLine.addlChrgAplcPct_A, fMsg.addlChrgAplcPct_A);
        setValue(sMsgLine.basePrcTermDealAmtRate_A, fMsg.basePrcTermDealAmtRate_A);
        setValue(sMsgLine.dsContrClsCd_AS, fMsg.dsContrClsCd_AS);
        if (hasValue(fMsg.ctacPsnPk_A)) {
            setValue(sMsgLine.ctacPsnPk_A, fMsg.ctacPsnPk_A);
        } else {
            setValue(sMsgLine.ctacPsnPk_A, NSAL0810CommonLogic.getCtacPsnPk(getGlobalCompanyCode(), fMsg.ctacPsnNm_A.getValue()));
        }
        setValue(sMsgLine.ctacPsnNm_A, fMsg.ctacPsnNm_A);
        // START 2022/03/22 [QC#59683, MOD]
//        setValue(sMsgLine.invSeptBaseUsgFlg_A, fMsg.invSeptBaseUsgFlg_A);
        setValue(sMsgLine.dsInvTgtrTpCd_AS, fMsg.dsInvTgtrTpCd_A);
        // END   2022/03/22 [QC#59683, MOD]
        // START 2016/10/28 T.Tomita [QC#15468, MOD]
//        setValue(sMsgLine.contrCloDt_A, fMsg.contrCloDt_A);
        setValue(sMsgLine.contrCloDt_A, decodeDateFormat(fMsg.xxDtTxt_CL.getValue()));
        // END 2016/10/28 T.Tomita [QC#15468, MOD]
        setValue(sMsgLine.contrDurnAot_A, fMsg.contrDurnAot_A);
        setValue(sMsgLine.pmtTermCashDiscCd_A, fMsg.pmtTermCashDiscCd_A);
        setValue(sMsgLine.svcLineBizCd_AS, fMsg.svcLineBizCd_AS);
        setValue(sMsgLine.bllgTmgTpCd_AS, fMsg.bllgTmgTpCd_AS);
        setValue(sMsgLine.dsContrEdiCd_A, fMsg.dsContrEdiCd_A);
        setValue(sMsgLine.dsContrDescTxt_A, fMsg.dsContrDescTxt_A);
        setValue(sMsgLine.baseChrgToLeaseCmpyFlg_A, fMsg.baseChrgToLeaseCmpyFlg_A);
        setValue(sMsgLine.usgChrgToLeaseCmpyFlg_A, fMsg.usgChrgToLeaseCmpyFlg_A);
        setValue(sMsgLine.intgMdseCd_A, fMsg.intgMdseCd_A);
        setValue(sMsgLine.capBwOrigQty_A, fMsg.capBwOrigQty_A);
        setValue(sMsgLine.capColorOrigQty_A, fMsg.capColorOrigQty_A);
        setValue(sMsgLine.capTotOrigQty_A, fMsg.capTotOrigQty_A);
        setValue(sMsgLine.capBwRunQty_A, fMsg.capBwRunQty_A);
        setValue(sMsgLine.capColorRunQty_A, fMsg.capColorRunQty_A);
        setValue(sMsgLine.capTotRunQty_A, fMsg.capTotRunQty_A);
        setValue(sMsgLine.chrgLvlTpCd_AS, fMsg.chrgLvlTpCd_AS);
        setValue(sMsgLine.addlChrgInvTpCd_AS, fMsg.addlChrgInvTpCd_AS);
        // START 2016/05/20 [QC#4061, ADD]
        setValue(sMsgLine.manContrOvrdFlg_A, fMsg.manContrOvrdFlg_A);
        // END   2016/05/20 [QC#4061, ADD]

        setDsContrIntfcData(cMsg.glblCmpyCd.getValue(), sMsgLine, fMsg);

        if (msgKeys.size() > 0) {
            return false;
        }
        return true;
    }

    private static String getDsAcctNm(String glblCmpyCd, String dsAcctNum) {
        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("100");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("sellToCustCd01", dsAcctNum);
        SELL_TO_CUSTTMsgArray list = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (list.getValidCount() > 0) {
            SELL_TO_CUSTTMsg tMsg = (SELL_TO_CUSTTMsg) list.get(0);
            if (tMsg != null) {
                return tMsg.dsAcctNm.getValue();
            }
        }
        return null;
    }

    private static BigDecimal getSvcMachMstrPk(String glblCmpyCd, String serNum) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("serNum01", serNum);
        SVC_MACH_MSTRTMsgArray list = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (list.getValidCount() > 0) {
            SVC_MACH_MSTRTMsg tMsg = (SVC_MACH_MSTRTMsg) list.get(0);
            if (tMsg != null) {
                return tMsg.svcMachMstrPk.getValue();
            }
        }
        return null;
    }

    private static BigDecimal getMdlId(String glblCmpyCd, String mdlNm01) {
        MDL_NMTMsg inMsg = new MDL_NMTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("t_MdlNm01", mdlNm01);
        MDL_NMTMsgArray list = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (list.getValidCount() > 0) {
            MDL_NMTMsg tMsg = (MDL_NMTMsg) list.get(0);
            if (tMsg != null) {
                return tMsg.t_MdlId.getValue();
            }
        }
        return null;
    }

    private static String getBllgMtrLbCd(String glblCmpyCd, String mtrLbDescTxt) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        // mod start 2016/11/28 CSA Defect#12077
        inMsg.setSQLID("004");
        // mod end 2016/11/28 CSA Defect#12077
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mtrLbDescTxt01", mtrLbDescTxt);
        MTR_LBTMsgArray list = (MTR_LBTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (list.getValidCount() > 0) {
            MTR_LBTMsg tMsg = (MTR_LBTMsg) list.get(0);
            if (tMsg != null) {
                return tMsg.mtrLbCd.getValue();
            }
        }
        return null;
    }

    private void setDsContrIntfcData(String glblCmpyCd, NSAL0810_ASMsg sMsgLine, NSAL0810F00FMsg fMsg) {
        if (hasValue(sMsgLine.contrIntfcSrcTpCd_AS) && hasValue(sMsgLine.dsContrSrcRefNum_A) && hasValue(sMsgLine.dsContrIntfcBatNum_A) && hasValue(sMsgLine.serNum_A) && hasValue(sMsgLine.mdseCd_A)
                && hasValue(sMsgLine.contrIntfcDtlTpCd_AS) && hasValue(sMsgLine.bllgMtrLbCd_A)) {
            S21SsmEZDResult ssmResult = NSAL0810Query.getInstance().getDsContrIntfcForUpload(glblCmpyCd, sMsgLine);
            if (ssmResult.isCodeNormal()) {
                Map<String, Object> rs = (Map<String, Object>) ssmResult.getResultObject();
                setValue(sMsgLine.dsContrIntfcPk_A, (BigDecimal) rs.get("DS_CONTR_INTFC_PK"));
                setValue(sMsgLine.dsContrIntfcStsCd_A, (String) rs.get("DS_CONTR_INTFC_STS_CD"));
                setValue(sMsgLine.dsContrProcStsCd_AS, (String) rs.get("DS_CONTR_PROC_STS_CD"));
            }
        }
    }

    private void clearSearchResultSummary(NSAL0810CMsg cMsg) {
        cMsg.xxSrchCnt_SP.clear();
        cMsg.xxSrchCnt_SR.clear();
        cMsg.xxSrchCnt_SU.clear();
        cMsg.xxSrchCnt_SI.clear();
    }

    private void doProcess_NSAL0810Scrn00_ActualCounters(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        screenTransition(cMsg, sMsg, SCREEN_NAME_NSAL0820);
    }

    private void doProcess_NSAL0810Scrn00_Tiers(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        screenTransition(cMsg, sMsg, SCREEN_NAME_NSAL0830);
    }

    private void doProcess_NSAL0810Scrn00_AdditionalCharges(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        screenTransition(cMsg, sMsg, SCREEN_NAME_NSAL0840);
    }

    private void doProcess_NSAL0810Scrn00_SalesCredits(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
        NSAL0810CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        screenTransition(cMsg, sMsg, SCREEN_NAME_NSAL0850);
    }

    private void screenTransition(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg, String screenName) {
        ZYPTableUtil.clear(cMsg.T);

        Set<String> selectedDsContrIntfcBatNumSet = NSAL0810CommonLogic.getDsContrIntfcBatNumSet(sMsg);
        // MOD start 2016/03/29 CSA Defect#5541
        if (selectedDsContrIntfcBatNumSet.size() > 2) {
            cMsg.setMessageInfo(NSAM0408E);
            return;
        }
        // MOD end 2016/03/29 CSA Defect#5541
        int index = 0;
        boolean isSave = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // MOD start 2016/03/29 CSA Defect#5541
            if (selectedDsContrIntfcBatNumSet.contains(sMsg.A.no(i).dsContrIntfcBatNum_A.getValue())) {
                if (hasValue(sMsg.A.no(i).dsContrIntfcPk_A)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.T.no(index).dsContrIntfcPk_T, sMsg.A.no(i).dsContrIntfcPk_A);
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxChkBox_A) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.T.no(0).dsContrIntfcBatNum_T, sMsg.A.no(i).dsContrIntfcBatNum_A);
                        ZYPEZDItemValueSetter.setValue(cMsg.T.no(0).dsContrSrcRefNum_T, sMsg.A.no(i).dsContrSrcRefNum_A);
                    }
                    index++;
                } else {
                    isSave = false;
                }
            }
            // MOD end 2016/03/29 CSA Defect#5541
        }
        if (index == 0) {
            cMsg.setMessageInfo(NSAM0015E);
        }
        if (!isSave) {
            cMsg.setMessageInfo(NSAM0409E, new String[] {screenName });
        }
        cMsg.T.setValidCount(index);
    }

    // START 2016/10/28 T.Tomita [QC#15468, ADD]
    private String convertDateFormat(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }

    private String convertYrMthFormat(String yrMth) {
        if (hasValue(yrMth)) {
            yrMth = ZYPDateUtil.formatEzd6ToDisp(yrMth);
        }
        return yrMth;
    }

    private String decodeDateFormat(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatSepDisp8ToEzd(date);
        }
        return date;
    }

    private String decodeYrMthFormat(String yrMth) {
        if (hasValue(yrMth)) {
            yrMth = ZYPDateUtil.formatSepDisp6ToEzd(yrMth);
        }
        return yrMth;
    }
    // END 2016/10/28 T.Tomita [QC#15468, ADD]
}
