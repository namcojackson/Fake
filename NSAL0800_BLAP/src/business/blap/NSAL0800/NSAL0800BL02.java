/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0800;

import static business.blap.NSAL0800.constant.NSAL0800Constant.DOWNLOAD_FILE_NAME;
import static business.blap.NSAL0800.constant.NSAL0800Constant.LAST_DAY;
import static business.blap.NSAL0800.constant.NSAL0800Constant.LAST_DAY_OF_A_MONTH;
import static business.blap.NSAL0800.constant.NSAL0800Constant.LIMIT_DOWNLOAD;
import static business.blap.NSAL0800.constant.NSAL0800Constant.NFAM0066E;
import static business.blap.NSAL0800.constant.NSAL0800Constant.NSAM0013E;
import static business.blap.NSAL0800.constant.NSAL0800Constant.NSAM0200I;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0800.common.NSAL0800CommonLogic;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.file.NSAL0800F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_THRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_SLS_CR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TGTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_EST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.START_READ_VLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STUB_PREP_FROM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STUB_PREP_THRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/05/09   Hitachi         Y.Tsuchimoto    Update          QC#8065
 * 2016/11/28   Hitachi         K.Kojima        Update          QC#14204
 * 2017/05/26   Hitachi         Y.Osawa         Update          QC#18560
 * 2017/06/16   Hitachi         K.Kojima        Update          QC#19230
 * 2017/08/15   CITS            T.Kikuhara      Update          QC#18799(L3)
 * 2017/08/31   CITS            T.Kikuhara      Update          QC#20872(L3)
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 *</pre>
 */
public class NSAL0800BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            NSAL0800CMsg bizMsg = (NSAL0800CMsg) cMsg;
            NSAL0800SMsg glblMsg = (NSAL0800SMsg) sMsg;
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0800_INIT".equals(screenAplID)) {
                doProcess_NSAL0800_INIT(bizMsg, glblMsg);
            // QC#18799 ADD START
            } else if ("NSAL0800Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0800_Search(bizMsg, glblMsg);
            } else if ("NSAL0800Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL0800Scrn00_CMN_Download(bizMsg, glblMsg);
            // QC#18799 ADD END
            } else if ("NSAL0800Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0800Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else if ("NSAL0800Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0800Scrn00_CMN_Reset(bizMsg, glblMsg);
            } else if ("NSAL0800Scrn00_GetAcctNm".equals(screenAplID)) {
                doProcess_NSAL0800Scrn00_GetAcctNm(bizMsg, glblMsg);
            } else if ("NSAL0800Scrn00_OnChangeContrIntfcSrcTpCd".equals(screenAplID)) {
                doProcess_NSAL0800Scrn00_OnChangeContrIntfcSrcTpCd(bizMsg, glblMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL0800CMsg
     * @param sMsg NSAL0800SMsg
     */
    private void doProcess_NSAL0800_INIT(NSAL0800CMsg cMsg, NSAL0800SMsg sMsg) {
        // init
        createPullDown(cMsg);

        // Search
        searchDsContrIntfcDefRule(cMsg, sMsg);

        // GetAcctNm
        doProcess_NSAL0800Scrn00_GetAcctNm(cMsg, sMsg);
    }

    // QC#18799 ADD START
    /**
     * do process (Search)
     * @param cMsg NSAL0800CMsg
     * @param sMsg NSAL0800SMsg
     */
    private void doProcess_NSAL0800_Search(NSAL0800CMsg cMsg, NSAL0800SMsg sMsg) {
        // Search By Search Button Push
        searchDsContrIntfcDefRuleBySearchBtn(cMsg, sMsg);

        // init
        createPullDown(cMsg);

        // GetAcctNm
        doProcess_NSAL0800Scrn00_GetAcctNm(cMsg, sMsg);
    }
    // QC#18799 ADD END

    /**
     * search DsContrIntfcDefRule
     * @param cMsg NSAL0800CMsg
     * @param sMsg NSAL0800SMsg
     */
    private void searchDsContrIntfcDefRule(NSAL0800CMsg cMsg, NSAL0800SMsg sMsg) {

        // get DsContrIntfcDefRule
        S21SsmEZDResult ssmFirstResult = NSAL0800Query.getInstance().getFirstContrIntfcSrcTpCd();
        if (ssmFirstResult.isCodeNotFound()) {
            return;
        }
        Map<String, Object> firstMap = (Map<String, Object>) ssmFirstResult.getResultObject();
        if (firstMap == null) {
            return;
        }

        S21SsmEZDResult ssmDsContrIntfcDefRuleResult = NSAL0800Query.getInstance().getDsContrIntfcDefRule((String) firstMap.get("CONTR_INTFC_SRC_TP_CD"));

        if (ssmDsContrIntfcDefRuleResult.isCodeNotFound()) {
            return;
        }
        Map<String, Object> ruleMap = (Map<String, Object>) ssmDsContrIntfcDefRuleResult.getResultObject();
        S21SsmEZDResult ssmResult = NSAL0800Query.getInstance().getDsContrIntfcDefRuleByContrIntfcSrcTpCd(ruleMap);
        // START 2016/05/09 [QC#8065, ADD]
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        // END   2016/05/09 [QC#8065, ADD]
        Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
        setDsContrIntfcDefRule(cMsg, map);
    }

    // QC#18799 ADD START
    /**
     * search DsContrIntfcDefRule By Search Button Push
     * @param cMsg NSAL0800CMsg
     * @param sMsg NSAL0800SMsg
     */
    private void searchDsContrIntfcDefRuleBySearchBtn(NSAL0800CMsg cMsg, NSAL0800SMsg sMsg) {

        Map<String, Object> ruleMap = new HashMap<String, Object>();
        ruleMap.put("CONTR_INTFC_SRC_TP_CD", cMsg.contrIntfcSrcTpCd_HS.getValue());
        ruleMap.put("DS_CONTR_CLS_CD2", cMsg.dsContrClsCd_HS.getValue());
        ruleMap.put("SVC_LINE_BIZ_CD", cMsg.svcLineBizCd_HS.getValue());
        S21SsmEZDResult ssmResult = NSAL0800Query.getInstance().getDsContrIntfcDefRuleByContrIntfcSrcTpCd(ruleMap);
        if (ssmResult.isCodeNotFound()) {
            String contrIntfcSrcTpCd_HS = cMsg.contrIntfcSrcTpCd_HS.getValue();
            String dsContrClsCd_HS = cMsg.dsContrClsCd_HS.getValue();
            String svcLineBizCd_HS = cMsg.svcLineBizCd_HS.getValue();
            cMsg.clear();
            ZYPEZDItemValueSetter.setValue(cMsg.contrIntfcSrcTpCd_HS, contrIntfcSrcTpCd_HS);
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrClsCd_HS, dsContrClsCd_HS);
            ZYPEZDItemValueSetter.setValue(cMsg.svcLineBizCd_HS, svcLineBizCd_HS);
            cMsg.setMessageInfo(NSAM0013E);
            return;
        }

        Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
        setDsContrIntfcDefRule(cMsg, map);
        cMsg.setMessageInfo(NSAM0200I);

    }
    // QC#18799 ADD END

    private void setDsContrIntfcDefRule(NSAL0800CMsg cMsg, Map<String, Object> map) {
        ZYPEZDItemValueSetter.setValue(cMsg.contrIntfcSrcTpCd_HS, (String) map.get("CONTR_INTFC_SRC_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_H1, (String) map.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(cMsg.enblFlg_H1, (String) map.get("ENBL_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.mtrEstTpCd_ES, (String) map.get("MTR_EST_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.bllgCycleCd_E2, (String) map.get("BASE_BLLG_CYCLE_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.bllgCycleCd_E4, (String) map.get("MTR_BLLG_CYCLE_CD"));
        // START 2017/06/16 K.Kojima [QC#19230,MOD]
        // ZYPEZDItemValueSetter.setValue(cMsg.invSeptBaseUsgFlg_E1, (String) map.get("INV_SEPT_BASE_USG_FLG"));
        // START 2022/03/22 [QC#59683, DEL]
//        ZYPEZDItemValueSetter.setValue(cMsg.invSeptBaseUsgFlg_E1, NSAL0800CommonLogic.switchFlg((String) map.get("INV_SEPT_BASE_USG_FLG")));
        // END   2022/03/22 [QC#59683, DEL]
        // END 2017/06/16 K.Kojima [QC#19230,MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.prcAllocByMachQtyFlg_E1, (String) map.get("PRC_ALLOC_BY_MACH_QTY_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.contrUplftTpCd_FS, (String) map.get("CONTR_UPLFT_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.uplftPrcMethCd_FS, (String) map.get("UPLFT_PRC_METH_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.uplftBasePrcUpRatio_F1, (BigDecimal) map.get("UPLFT_BASE_PRC_UP_RATIO"));
        ZYPEZDItemValueSetter.setValue(cMsg.uplftMtrPrcUpRatio_F1, (BigDecimal) map.get("UPLFT_MTR_PRC_UP_RATIO"));
        ZYPEZDItemValueSetter.setValue(cMsg.bllgThruTpCd_GS, (String) map.get("BLLG_THRU_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.bllgThruDt_G1, (String) map.get("BLLG_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(cMsg.stubPrepFromTpCd_IS, (String) map.get("STUB_PREP_FROM_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.stubPrepThruTpCd_IS, (String) map.get("STUB_PREP_THRU_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.autoEffFleetFlg_I1, (String) map.get("AUTO_EFF_FLEET_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.autoEffAggrFlg_I1, (String) map.get("AUTO_EFF_AGGR_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.leaseCmpyCd_A1, (String) map.get("LEASE_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.mtrReadMethCd_AS, (String) map.get("MTR_READ_METH_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrSlsCrTpCd_AS, (String) map.get("DS_CONTR_SLS_CR_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.contrAutoRnwTpCd_BS, (String) map.get("CONTR_AUTO_RNW_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.rnwPrcMethCd_BS, (String) map.get("RNW_PRC_METH_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.befEndRnwDaysAot_B1, (BigDecimal) map.get("BEF_END_RNW_DAYS_AOT"));
        ZYPEZDItemValueSetter.setValue(cMsg.basePrcUpRatio_B1, (BigDecimal) map.get("BASE_PRC_UP_RATIO"));
        ZYPEZDItemValueSetter.setValue(cMsg.mtrPrcUpRatio_B1, (BigDecimal) map.get("MTR_PRC_UP_RATIO"));
        ZYPEZDItemValueSetter.setValue(cMsg.startReadVldTpCd_CS, (String) map.get("START_READ_VLD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.mtrReadWinDaysAot_C1, (BigDecimal) map.get("MTR_READ_WIN_DAYS_AOT"));
        ZYPEZDItemValueSetter.setValue(cMsg.allwDataCrctFlg_D1, (String) map.get("ALLW_DATA_CRCT_FLG"));
        // QC#18799 UPD START
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrClsCd_HS, (String) map.get("DS_CONTR_CLS_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.contrCloDay_GS, (String) map.get("CONTR_CLO_DAY"));
        ZYPEZDItemValueSetter.setValue(cMsg.contrBllgDay_GS, (String) map.get("CONTR_BLLG_DAY"));
        // QC#18799 UPD END
        ZYPEZDItemValueSetter.setValue(cMsg.bllgRollOverRatio_C1, (BigDecimal) map.get("BLLG_ROLL_OVER_RATIO"));
        ZYPEZDItemValueSetter.setValue(cMsg.printDtlFlg_E1, (String) map.get("PRINT_DTL_FLG"));
        // START 2016/11/28 K.Kojima [QC#14204,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratTs, (String) map.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratByNm, (String) map.get("XX_REC_HIST_CRAT_BY_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdTs, (String) map.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdByNm, (String) map.get("XX_REC_HIST_UPD_BY_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistTblNm, (String) map.get("XX_REC_HIST_TBL_NM"));
        // END 2016/11/28 K.Kojima [QC#14204,ADD]
        // QC#18799 ADD START
        ZYPEZDItemValueSetter.setValue(cMsg.svcLineBizCd_HS, (String) map.get("SVC_LINE_BIZ_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.bllgLimitAmt_C1, (BigDecimal) map.get("BLLG_LIMIT_AMT"));
        ZYPEZDItemValueSetter.setValue(cMsg.mtrReadWinMlyDaysAot_C1, (BigDecimal) map.get("MTR_READ_WIN_MLY_DAYS_AOT"));
        ZYPEZDItemValueSetter.setValue(cMsg.mtrReadWinOthDaysAot_C1, (BigDecimal) map.get("MTR_READ_WIN_OTH_DAYS_AOT"));
        ZYPEZDItemValueSetter.setValue(cMsg.befEndUplftDaysAot_F1, (BigDecimal) map.get("BEF_END_UPLFT_DAYS_AOT"));
        ZYPEZDItemValueSetter.setValue(cMsg.bllgTmgTpCd_GS, (String) map.get("BASE_BLLG_TMG_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.istlCallMtrReadUseFlg_C1, (String) map.get("ISTL_CALL_MTR_READ_USE_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.invSeptBaseUsgMstrFlg_E1, (String) map.get("INV_SEPT_BASE_USG_MSTR_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.rnwExtMthAot_B1, (BigDecimal) map.get("RNW_EXT_MTH_AOT"));
        // QC#18799 ADD END
        // START 2022/03/22 [QC#59683, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.dsInvTgtrTpCd_E1, (String) map.get("DS_INV_TGTR_TP_CD"));
        // END   2022/03/22 [QC#59683, ADD]
    }

    /**
     * do process (Reset)
     * @param cMsg NSAL0800CMsg
     * @param sMsg NSAL0800SMsg
     */
    private void doProcess_NSAL0800Scrn00_CMN_Reset(NSAL0800CMsg cMsg, NSAL0800SMsg sMsg) {
        cMsg.clear();
        doProcess_NSAL0800_INIT(cMsg, sMsg);
    }

    /**
     * create PullDown
     * @param cMsg NSAL0800CMsg
     */
    private void createPullDown(NSAL0800CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(CONTR_INTFC_SRC_TP.class, cMsg.contrIntfcSrcTpCd_H1, cMsg.contrIntfcSrcTpDescTxt_H1);
        ZYPCodeDataUtil.createPulldownList(MTR_EST_TP.class, cMsg.mtrEstTpCd_E1, cMsg.mtrEstTpDescTxt_E1);
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, cMsg.bllgCycleCd_E1, cMsg.bllgCycleDescTxt_E1);
        // START 2017/05/25 Y.Osawa [QC#18560, ADD]
        NSAL0800CommonLogic.deletePulldownList(cMsg.bllgCycleCd_E1, cMsg.bllgCycleDescTxt_E1, BLLG_CYCLE.DAILY);
        // END   2017/05/25 Y.Osawa [QC#18560, ADD]
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, cMsg.bllgCycleCd_E3, cMsg.bllgCycleDescTxt_E2);
        // START 2017/05/25 Y.Osawa [QC#18560, ADD]
        NSAL0800CommonLogic.deletePulldownList(cMsg.bllgCycleCd_E3, cMsg.bllgCycleDescTxt_E2, BLLG_CYCLE.DAILY);
        // END   2017/05/25 Y.Osawa [QC#18560, ADD]
        ZYPCodeDataUtil.createPulldownList(CONTR_UPLFT_TP.class, cMsg.contrUplftTpCd_F1, cMsg.contrUplftTpDescTxt_F1);
        ZYPCodeDataUtil.createPulldownList(UPLFT_PRC_METH.class, cMsg.uplftPrcMethCd_F1, cMsg.uplftPrcMethDescTxt_F1);
        ZYPCodeDataUtil.createPulldownList(BLLG_THRU_TP.class, cMsg.bllgThruTpCd_G1, cMsg.bllgThruTpDescTxt_G1);
        ZYPCodeDataUtil.createPulldownList(STUB_PREP_FROM_TP.class, cMsg.stubPrepFromTpCd_I1, cMsg.stubPrepFromTpDescTxt_I1);
        ZYPCodeDataUtil.createPulldownList(STUB_PREP_THRU_TP.class, cMsg.stubPrepThruTpCd_I1, cMsg.stubPrepThruTpDescTxt_I1);
        ZYPCodeDataUtil.createPulldownList(MTR_READ_METH.class, cMsg.mtrReadMethCd_A1, cMsg.mtrReadMethDescTxt_A1);
        ZYPCodeDataUtil.createPulldownList(DS_CONTR_SLS_CR_TP.class, cMsg.dsContrSlsCrTpCd_A1, cMsg.dsContrSlsCrTpDescTxt_A1);
        // QC#18799 UPD START
        ZYPCodeDataUtil.createPulldownList(DS_CONTR_CLS.class, cMsg.dsContrClsCd_H1, cMsg.dsContrClsDescTxt_H1);
        // QC#18799 UPD END
        ZYPCodeDataUtil.createPulldownList(CONTR_AUTO_RNW_TP.class, cMsg.contrAutoRnwTpCd_B1, cMsg.contrAutoRnwTpDescTxt_B1);
        ZYPCodeDataUtil.createPulldownList(RNW_PRC_METH.class, cMsg.rnwPrcMethCd_B1, cMsg.rnwPrcMethDescTxt_B1);
        ZYPCodeDataUtil.createPulldownList(START_READ_VLD_TP.class, cMsg.startReadVldTpCd_C1, cMsg.startReadVldTpDescTxt_C1);
        // QC#18799 ADD START
        ZYPCodeDataUtil.createPulldownList(BLLG_TMG_TP.class, cMsg.bllgTmgTpCd_G1, cMsg.bllgTmgTpDescTxt_G1);
        NSAL0800CommonLogic.createSvcLineBizPullDownList(cMsg.svcLineBizCd_H1, cMsg.svcLineBizDescTxt_H1);
        NSAL0800CommonLogic.createDayPullDownList(cMsg.contrCloDay_G1, cMsg.xxEdtDescTxt_G1);
        NSAL0800CommonLogic.createDayPullDownList(cMsg.contrBllgDay_G1, cMsg.xxEdtDescTxt_G2);
        // QC#18799 ADD END
        // START 2022/03/22 [QC#59683, ADD]
        ZYPCodeDataUtil.createPulldownList(DS_INV_TGTR_TP.class, cMsg.dsInvTgtrTpCd_E2, cMsg.dsInvTgtrTpDescTxt_E1);
        // END   2022/03/22 [QC#59683, ADD]
    }

    /**
     * do process (GetAcctNm)
     * @param cMsg NSAL0800CMsg
     * @param sMsg NSAL0800SMsg
     */
    private void doProcess_NSAL0800Scrn00_GetAcctNm(NSAL0800CMsg cMsg, NSAL0800SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd_A1)) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_A1, "");
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        if (!NSAL0800CommonLogic.isExistsSellToCust(glblCmpyCd, cMsg)) {
            cMsg.leaseCmpyCd_A1.setErrorInfo(1, NFAM0066E, new String[] {"Default Lease Company Code", "Sell To Customer Master" });
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_A1, "");
            return;
        }

        SELL_TO_CUSTTMsgArray list = NSAL0800CommonLogic.getSellToCustList(glblCmpyCd, cMsg);
        if (list.getValidCount() > 0) {
            SELL_TO_CUSTTMsg tmsg = (SELL_TO_CUSTTMsg) list.get(0);
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_A1, tmsg.dsAcctNm);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_A1, "");
        }
    }

    /**
     * do process (Submit)
     * @param cMsg NSAL0800CMsg
     * @param sMsg NSAL0800SMsg
     */
    private void doProcess_NSAL0800Scrn00_CMN_Submit(NSAL0800CMsg cMsg, NSAL0800SMsg sMsg) {
        doProcess_NSAL0800Scrn00_OnChangeContrIntfcSrcTpCd(cMsg, sMsg);
    }

    /**
     * do process (OnChange ContrIntfcSrcTpCd)
     * @param cMsg NSAL0800CMsg
     * @param sMsg NSAL0800SMsg
     */
    private void doProcess_NSAL0800Scrn00_OnChangeContrIntfcSrcTpCd(NSAL0800CMsg cMsg, NSAL0800SMsg sMsg) {

        // get DsContrIntfcDefRule
        // QC#20872 UPD START
        Map<String, Object> ruleMap = new HashMap<String, Object>();
        ruleMap.put("CONTR_INTFC_SRC_TP_CD", cMsg.contrIntfcSrcTpCd_HS.getValue());
        ruleMap.put("DS_CONTR_CLS_CD2", cMsg.dsContrClsCd_HS.getValue());
        ruleMap.put("SVC_LINE_BIZ_CD", cMsg.svcLineBizCd_HS.getValue());

        S21SsmEZDResult ssmResult = NSAL0800Query.getInstance().getDsContrIntfcDefRuleByContrIntfcSrcTpCd(ruleMap);
        if (ssmResult.isCodeNotFound()) {
            String contrIntfcSrcTpCd_HS = cMsg.contrIntfcSrcTpCd_HS.getValue();
            String dsContrClsCd_HS = cMsg.dsContrClsCd_HS.getValue();
            String svcLineBizCd_HS = cMsg.svcLineBizCd_HS.getValue();
            cMsg.clear();
            cMsg.clear();
            createPullDown(cMsg);
            ZYPEZDItemValueSetter.setValue(cMsg.contrIntfcSrcTpCd_HS, contrIntfcSrcTpCd_HS);
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrClsCd_HS, dsContrClsCd_HS);
            ZYPEZDItemValueSetter.setValue(cMsg.svcLineBizCd_HS, svcLineBizCd_HS);
            cMsg.setMessageInfo(NSAM0013E);
            return;
        }
        Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
        setDsContrIntfcDefRule(cMsg, map);
        // QC#20872 UPD END

        // GetAcctNm
        doProcess_NSAL0800Scrn00_GetAcctNm(cMsg, sMsg);
    }

    // QC#18799 ADD START
    /**
    * <pre>
    * do process (Download)
    * </pre>
    * @param cMsg NSAL0800CMsg
    * @param sMsg NSAL0800SMsg
    */
    private void doProcess_NSAL0800Scrn00_CMN_Download(NSAL0800CMsg cMsg, NSAL0800SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(LIMIT_DOWNLOAD);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NSAL0800Query.getInstance().getClass());

            cMsg.xxFileData_X1.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(DOWNLOAD_FILE_NAME), ".csv");

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
            ssmParam.put("lastDayOfaMonth", LAST_DAY_OF_A_MONTH);
            ssmParam.put("lastDay", LAST_DAY);

            ps = ssmLLClient.createPreparedStatement("getDsContrIntfcDefRuleForDownload", ssmParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * Write CSV File
     * @param cMsg      NSAL0800CMsg
     * @param rs        ResultSet
     */
    private void writeCsvFile(NSAL0800CMsg cMsg, ResultSet rs) throws SQLException {

        NSAL0800F00FMsg fMsg = new NSAL0800F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_X1.getTempFilePath(), fMsg);

        //write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        if (!rs.next()) {
            cMsg.setMessageInfo(NSAM0013E, null);
            csvOutFile.close();
            return;
        }

        //write contents
        do {

            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.contrIntfcSrcTpDescTxt_U1, rs.getString("CONTR_INTFC_SRC_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsContrClsDescTxt_U1, rs.getString("DS_CONTR_CLS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcLineBizCd_U1, rs.getString("SVC_LINE_BIZ_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.effFromDt_U1, rs.getString("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.enblFlg_U1, rs.getString("ENBL_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_U1, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.mtrReadMethDescTxt_U1, rs.getString("MTR_READ_METH_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsContrSlsCrTpDescTxt_U1, rs.getString("DS_CONTR_SLS_CR_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.mtrEstTpDescTxt_U1, rs.getString("MTR_EST_TP_DESC_TXT"));
            // START 2022/03/22 [QC#59683, DEL]
//            ZYPEZDItemValueSetter.setValue(fMsg.invSeptBaseUsgFlg_U1, NSAL0800CommonLogic.switchFlg(rs.getString("INV_SEPT_BASE_USG_FLG")));
            // END   2022/03/22 [QC#59683, DEL]
            ZYPEZDItemValueSetter.setValue(fMsg.invSeptBaseUsgMstrFlg_U1, rs.getString("INV_SEPT_BASE_USG_MSTR_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.prcAllocByMachQtyFlg_U1, rs.getString("PRC_ALLOC_BY_MACH_QTY_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgCycleDescTxt_U1, rs.getString("BASE_BLLG_CYCLE_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.printDtlFlg_U1, rs.getString("PRINT_DTL_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgCycleDescTxt_U2, rs.getString("MTR_BLLG_CYCLE_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.contrAutoRnwTpDescTxt_U1, rs.getString("CONTR_AUTO_RNW_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.befEndRnwDaysAot_U1, rs.getBigDecimal("BEF_END_RNW_DAYS_AOT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rnwPrcMethDescTxt_U1, rs.getString("RNW_PRC_METH_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.basePrcUpRatio_U1, rs.getBigDecimal("BASE_PRC_UP_RATIO"));
            ZYPEZDItemValueSetter.setValue(fMsg.mtrPrcUpRatio_U1, rs.getBigDecimal("MTR_PRC_UP_RATIO"));
            ZYPEZDItemValueSetter.setValue(fMsg.rnwExtMthAot_U1, rs.getBigDecimal("RNW_EXT_MTH_AOT"));
            ZYPEZDItemValueSetter.setValue(fMsg.contrUplftTpDescTxt_U1, rs.getString("CONTR_UPLFT_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.befEndUplftDaysAot_U1, rs.getBigDecimal("BEF_END_UPLFT_DAYS_AOT"));
            ZYPEZDItemValueSetter.setValue(fMsg.uplftPrcMethDescTxt_U1, rs.getString("UPLFT_PRC_METH_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.uplftBasePrcUpRatio_U1, rs.getBigDecimal("UPLFT_BASE_PRC_UP_RATIO"));
            ZYPEZDItemValueSetter.setValue(fMsg.uplftMtrPrcUpRatio_U1, rs.getBigDecimal("UPLFT_MTR_PRC_UP_RATIO"));
            ZYPEZDItemValueSetter.setValue(fMsg.startReadVldTpDescTxt_U1, rs.getString("START_READ_VLD_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.mtrReadWinDaysAot_U1, rs.getBigDecimal("MTR_READ_WIN_DAYS_AOT"));
            ZYPEZDItemValueSetter.setValue(fMsg.istlCallMtrReadUseFlg_U1, rs.getString("ISTL_CALL_MTR_READ_USE_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgRollOverRatio_U1, rs.getBigDecimal("BLLG_ROLL_OVER_RATIO"));
            ZYPEZDItemValueSetter.setValue(fMsg.mtrReadWinMlyDaysAot_U1, rs.getBigDecimal("MTR_READ_WIN_MLY_DAYS_AOT"));
            ZYPEZDItemValueSetter.setValue(fMsg.mtrReadWinOthDaysAot_U1, rs.getBigDecimal("MTR_READ_WIN_OTH_DAYS_AOT"));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgLimitAmt_U1, rs.getBigDecimal("BLLG_LIMIT_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgThruTpDescTxt_U1, rs.getString("BLLG_THRU_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgThruDt_U1, rs.getString("BLLG_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_U1, rs.getString("CONTR_CLO_DAY"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_U2, rs.getString("CONTR_BLLG_DAY"));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgTmgTpDescTxt_U1, rs.getString("BLLG_TMG_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.allwDataCrctFlg_U1, rs.getString("ALLW_DATA_CRCT_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.stubPrepFromTpDescTxt_U1, rs.getString("STUB_PREP_FROM_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.stubPrepThruTpDescTxt_U1, rs.getString("STUB_PREP_THRU_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.autoEffFleetFlg_U1, rs.getString("AUTO_EFF_FLEET_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.autoEffAggrFlg_U1, rs.getString("AUTO_EFF_AGGR_FLG"));
            // START 2022/03/22 [QC#59683, ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.dsInvTgtrTpDescTxt_U1, rs.getString("DS_INV_TGTR_TP_DESC_TXT"));
            // END   2022/03/22 [QC#59683, ADD]
            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * Write CSV Header
     * @param csvOutFile  ZYPCSVOutFile
     * @param fMsg        NSAL0800F00FMsg
     * @param cMsg        NSAL0800CMsg
     */
    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL0800F00FMsg fMsg, NSAL0800CMsg cMsg) {
       final String[] csvHeader = new String[] {
                 "Source Type"
               , "Category"
               , "Line Of Business"
               , "Effect Start Date"
               , "Enable Flag"
               , "Default Lease Company"
               , "Mtr Method Default"
               , "Sales Credit"
               , "Auto Estimation"
               // START 2022/03/22 [QC#59683, DEL]
//               , "Bill All Base & Usage Together"
               // END   2022/03/22 [QC#59683, DEL]
               , "Use Master Setting"
               // START 2022/03/22 [QC#59683, ADD]
               , "Invoicing Option"
               // END   2022/03/22 [QC#59683, ADD]
               , "Allocate Across All Machines"
               , "Base Frequency"
               , "Print On Invoice"
               , "Overage Frequency"
               , "Renewal Defaults Type"
               , "Renewal Defaults #Days Before"
               , "Renewal Defaults Method"
               , "Renewal Defaults %Base"
               , "Renewal Defaults %Overage"
               , "Extended Month"
               , "Upliftment Defaults Type"
               , "Upliftment Defaults #Days Before"
               , "Upliftment Defaults Method"
               , "Upliftment Defaults %Base"
               , "Upliftment Defaults %Overage"
               , "Start Read Validation"
               , "Reading Window Days"
               , "Use Install Call Meter Read"
               , "Counter Rollover %"
               , "Window Periods for Monthly"
               , "Window Periods for Other Frequency"
               , "Preview Amount"
               , "Mark As Billed Schedule end date Prior To"
               , "Specific Date"
               , "Period End Date Ctrl"
               , "Invoice Date Ctrl"
               , "Base Billing Timing"
               , "Allow Data Correction"
               , "Stub Prevention For Start"
               , "Stub Prevention For End"
               , "Auto Derive Effectivity For Add To Fleet"
               , "Auto Derive Effectivity For Add To Aggregate" };
       csvOutFile.writeHeader(csvHeader);
    }
    // QC#18799 ADD END

}
