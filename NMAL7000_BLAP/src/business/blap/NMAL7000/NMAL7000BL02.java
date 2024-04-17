/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7000;

import static business.blap.NMAL7000.constant.NMAL7000Constant.CSV_FILE_EXTENSION;
import static business.blap.NMAL7000.constant.NMAL7000Constant.CSV_FILE_NM;
import static business.blap.NMAL7000.constant.NMAL7000Constant.CSV_HEADER;
import static business.blap.NMAL7000.constant.NMAL7000Constant.NZZM0000E;
import static business.blap.NMAL7000.constant.NMAL7000Constant.NZZM0001W;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7000.common.NMAL7000CommonLogic;
import business.file.NMAL7000F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DISP_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7000BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Fujitsu         M.Nakamura      Create          N/A
 * 2016/08/01   Fujitsu         R.Nakamura      Update          QC#10928
 * 2018/07/11   Fujitsu         M.Ishii         Update          QC#25557
 *</pre>
 */
public class NMAL7000BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7000CMsg bizMsg = (NMAL7000CMsg) cMsg;
            NMAL7000SMsg glblMsg = (NMAL7000SMsg) sMsg;

            if ("NMAL7000_INIT".equals(screenAplID)) {
                doProcess_NMAL7000_INIT(bizMsg, glblMsg);
                ZYPGUITableColumn.getColData(bizMsg, glblMsg);

            } else if ("NMAL7000Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7000Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL7000Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7000Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL7000Scrn00_OnChange_SavedSearchOption".equals(screenAplID)) {
                doProcess_NMAL7000_OnChange_SavedSearchOption(bizMsg, glblMsg);

            } else if ("NMAL7000Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL7000Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NMAL7000Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL7000Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL7000Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL7000Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL7000Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7000Scrn00_Search(bizMsg, glblMsg);

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
    private void doProcess_NMAL7000_INIT(NMAL7000CMsg bizMsg, NMAL7000SMsg glblMsg) {

        // PullDown
        NMAL7000CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());
        ZYPCodeDataUtil.createPulldownList(PRC_LIST_TP.class, bizMsg.prcListTpCd_L1, bizMsg.prcListTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_LIST_STRU_TP.class, bizMsg.prcListStruTpCd_L1, bizMsg.prcListStruTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_DISP_REC_TP.class, bizMsg.prcDispRecTpCd_L1, bizMsg.prcDispRecTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_LIST_GRP.class, bizMsg.prcListGrpCd_L1, bizMsg.prcListGrpDescTxt_L1);

    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7000Scrn00_CMN_Clear(NMAL7000CMsg bizMsg, NMAL7000SMsg glblMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        bizMsg.srchOptPk_H1.clear();
        bizMsg.srchOptNm_H1.clear();

        bizMsg.prcCatgNm_H1.clear();
        bizMsg.prcListDplyNm_H1.clear();
        bizMsg.prcCatgDescTxt_H1.clear();
        bizMsg.prcContrNum_H1.clear();
        bizMsg.prcContrNm_H1.clear();
        bizMsg.dsAcctNum_H1.clear();
        bizMsg.dsAcctNm_H1.clear();
        bizMsg.csmpNum_H1.clear();
        bizMsg.coaBrCd_H1.clear();
        bizMsg.splyAgmtPlnNm_H1.clear();
        bizMsg.effFromDt_H1.clear();
        bizMsg.effThruDt_H1.clear();
        bizMsg.prcListTpCd_H1.clear();
        bizMsg.prcListStruTpCd_H1.clear();
        bizMsg.prcListGrpCd_H1.clear();

        // Add Start 2016/08/01 QC#10928
        bizMsg.prcListMdseCd_H1.clear();
        bizMsg.mdlNm_H1.clear();
        // Add End 2016/08/01 QC#10928
        ZYPEZDItemValueSetter.setValue(bizMsg.prcDispRecTpCd_H1, PRC_DISP_REC_TP.ACTIVE_ONLY);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7000Scrn00_CMN_Download(NMAL7000CMsg bizMsg, NMAL7000SMsg glblMsg) {

        bizMsg.xxFileData_A1.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM), CSV_FILE_EXTENSION);
        NMAL7000F00FMsg fMsg = new NMAL7000F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_A1.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_HEADER);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            // 2018/07/11 QC#25557 Mod Start
//            EZDMsg.copy(glblMsg.A.get(i), null, fMsg, null);
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgCd_A1, glblMsg.A.no(i).prcCatgCd_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm_A1, glblMsg.A.no(i).prcCatgNm_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt_A1, glblMsg.A.no(i).prcListTpDescTxt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcListStruTpDescTxt_A1, glblMsg.A.no(i).prcListTpDescTxt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcDispRecTpDescTxt_A1, glblMsg.A.no(i).prcDispRecTpDescTxt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNum_A1, glblMsg.A.no(i).prcContrNum_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNm_A1, glblMsg.A.no(i).prcContrNm_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcListGrpDescTxt_A1, glblMsg.A.no(i).prcListGrpDescTxt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FR, NMAL7000CommonLogic.convertDateFormat(glblMsg.A.no(i).effFromDt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, NMAL7000CommonLogic.convertDateFormat(glblMsg.A.no(i).effThruDt_A1.getValue()));
            // 2018/07/11 QC#25557 Mod End
            csvOutFile.write();
        }

        csvOutFile.close();

    }

    /**
     * OnChange SavedSearchOption.
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcess_NMAL7000_OnChange_SavedSearchOption(NMAL7000CMsg bizMsg, NMAL7000SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_H1)) {
            NMAL7000CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7000Scrn00_PageJump(NMAL7000CMsg bizMsg, NMAL7000SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NMAL7000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, BigDecimal.ZERO);
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7000Scrn00_PageNext(NMAL7000CMsg bizMsg, NMAL7000SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL7000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, BigDecimal.ZERO);
    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7000Scrn00_PagePrev(NMAL7000CMsg bizMsg, NMAL7000SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL7000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, BigDecimal.ZERO);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7000Scrn00_Search(NMAL7000CMsg bizMsg, NMAL7000SMsg glblMsg) {
        // search
        search(bizMsg, glblMsg);

    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL7000CMsg bizMsg, NMAL7000SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NMAL7000Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            int j = 0;
            for (; j < glblMsg.A.getValidCount(); j++) {

                NMAL7000_ASMsg glblLineMsg = glblMsg.A.no(j);

                if (j < bizMsg.A.length()) {

                    EZDMsg.copy(glblLineMsg, null, bizMsg.A.no(j), null);
                    bizMsg.A.setValidCount(j + 1);
                }
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());

            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, BigDecimal.ZERO);

        }
    }
}
