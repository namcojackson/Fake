/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7250;

import static business.blap.NMAL7250.constant.NMAL7250Constant.CSV;
import static business.blap.NMAL7250.constant.NMAL7250Constant.CSV_FILE_NAME;
import static business.blap.NMAL7250.constant.NMAL7250Constant.CSV_HDR;
import static business.blap.NMAL7250.constant.NMAL7250Constant.NZZM0000E;
import static business.blap.NMAL7250.constant.NMAL7250Constant.NZZM0001W;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7250.common.NMAL7250CommonLogic;
import business.file.NMAL7250F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DISP_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_TRX_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7250BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   Fujitsu         W.Honda         Create          N/A
 * 2018/04/04   Fujitsu         R.Nakamura      Update          S21_NA#25206
 *</pre>
 */
public class NMAL7250BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7250CMsg bizMsg = (NMAL7250CMsg) cMsg;
            NMAL7250SMsg glblMsg = (NMAL7250SMsg) sMsg;

            if ("NMAL7250_INIT".equals(screenAplID)) {
                doProcess_NMAL7250_INIT(bizMsg, glblMsg);

            } else if ("NMAL7250Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7250Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL7250Scrn00_OnChange_SavedSearchOption".equals(screenAplID)) {
                doProcess_NMAL7250_OnChange_SavedSearchOption(bizMsg, glblMsg);

            } else if ("NMAL7250Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL7250Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NMAL7250Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL7250Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL7250Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL7250Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL7250Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7250Scrn00_Search(bizMsg, glblMsg);

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
     */
    private void doProcess_NMAL7250_INIT(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg) {

        // PullDown
        ZYPCodeDataUtil.createPulldownList(PRC_RULE_CATG.class, bizMsg.prcRuleCatgCd_P, bizMsg.prcRuleCatgDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(PRC_RULE_TRX_CATG.class, bizMsg.prcRuleTrxCatgCd_P, bizMsg.prcRuleTrxCatgDescTxt_P);
        // Mod Start 2018/04/04 QC#25206
//        ZYPCodeDataUtil.createPulldownList(PRC_RULE_ATTRB.class, bizMsg.prcRuleAttrbCd_P, bizMsg.prcRuleAttrbDescTxt_P);
        NMAL7250CommonLogic.getPullPrcRuleAttrbList(bizMsg);
        // Mod End 2018/04/04 QC#25206
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_P, bizMsg.lineBizTpDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(PRC_DISP_REC_TP.class, bizMsg.prcDispRecTpCd_P, bizMsg.prcDispRecTpDescTxt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcDispRecTpCd, PRC_DISP_REC_TP.ACTIVE_ONLY);
        ZYPCodeDataUtil.createPulldownList(PRC_RULE_COND_TP.class, bizMsg.prcRuleCondTpCd_P, bizMsg.prcRuleCondTpDescTxt_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleCondTpCd, PRC_RULE_COND_TP.RULES_AND_TABLES);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7250Scrn00_CMN_Download(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg) {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV);
        NMAL7250F00FMsg fMsg = new NMAL7250F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        fMsg.addExclusionItem("xxChkBox_A1");

        //write header
        csvOutFile.writeHeader(CSV_HDR, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        //write contents
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            //sMsg -> fMsg
            EZDMsg.copy(glblMsg.A.no(i), null, fMsg, null);
            csvOutFile.write();
        }

        csvOutFile.close();
    }

    /**
     * OnChange SavedSearchOption.
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcess_NMAL7250_OnChange_SavedSearchOption(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            NMAL7250CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7250Scrn00_PageJump(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg) {
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NMAL7250CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7250Scrn00_PageNext(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg) {
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL7250CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7250Scrn00_PagePrev(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg) {
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL7250CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7250Scrn00_Search(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg) {
        // search
        search(bizMsg, glblMsg);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NMAL7250Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length() - 1) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length() - 1);
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL7250CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }
}

