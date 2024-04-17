/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7190;

import static business.blap.NMAL7190.constant.NMAL7190Constant.CHK_ATTRB_NM_LIST;
import static business.blap.NMAL7190.constant.NMAL7190Constant.CHK_ATTRB_NM_LIST_UPLOAD;
import static business.blap.NMAL7190.constant.NMAL7190Constant.CSV_EXTENSION;
import static business.blap.NMAL7190.constant.NMAL7190Constant.DTL_EFF_FROM_DT_A1;
import static business.blap.NMAL7190.constant.NMAL7190Constant.DTL_EFF_THRU_DT_A1;
import static business.blap.NMAL7190.constant.NMAL7190Constant.GRP_ATTRB_NM_LIST;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM0072E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM8054E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM8187E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM8193E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM8723E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NZZM0000E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.PRICE_GROUP_CSV_ID;
import static business.blap.NMAL7190.constant.NMAL7190Constant.PRICE_GROUP_DETAIL;
import static business.blap.NMAL7190.constant.NMAL7190Constant.SORT_BY_ASC;
import static business.blap.NMAL7190.constant.NMAL7190Constant.XX_CHKBOX_NAME;
import static business.blap.NMAL7190.constant.NMAL7190Constant.XX_ORIG_QTY_A1;
import static business.blap.NMAL7190.constant.NMAL7190Constant.ZZZM9003I;
import static business.blap.NMAL7190.constant.NMAL7190Constant.UPLD_DATA_DUP;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7190.common.NMAL7190CommonLogic;
import business.file.NMAL7190F00FMsg;
import business.parts.ZYEC023001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_OP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYECSVUploadTemplateHeader;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NMAL7190BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 * 2016/09/02   Fujitsu         R.Nakamura      Update          QC#8213
 * 2016/11/22   Fujitsu         M.Ohno          Update          S21_NA#16082
 * 2017/08/21   Fujitsu         M.Yamada        Update          QC#18785(L3)
 * 2018/08/01   Fujitsu         M.Ishii         Update          QC#26297
 * 2018/12/05   Fujitsu         T.Noguchi       Update          QC#29324
 * 2018/12/18   Fujitsu         T.Noguchi       Update          QC#29661
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public class NMAL7190BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7190CMsg bizMsg = (NMAL7190CMsg) cMsg;
            NMAL7190SMsg glblMsg = (NMAL7190SMsg) sMsg;

            if ("NMAL7190_INIT".equals(screenAplID)) {
                doProcess_NMAL7190_INIT(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_DeleteRow(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_InsertRow(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_OnChange_AttributeName".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_OnChange_AttributeName(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_OnChange_TargetContext".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_OnChange_TargetContext(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_OnChange_PricingGroupType".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_OnChange_PricingGroupType(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_Search(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_TemplateDownload".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_TemplateDownload(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_Upload".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_Upload(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_PagePrev(bizMsg, glblMsg);

            // 2018/12/05 QC#29324 Add Start
            } else if ("NMAL7190_NMAL7440".equals(screenAplID)) {
                doProcess_NMAL7190_NMAL7440(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_TBLColumnSort(bizMsg, glblMsg);
            // 2018/12/05 QC#29324 Add End

            // 2018/12/18 QC#29661 Add Start
            } else if ("NMAL7190Scrn00_Select_All".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_Select_All(bizMsg, glblMsg);

            } else if ("NMAL7190Scrn00_UnSelect_All".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_UnSelect_All(bizMsg, glblMsg);
            // 2018/12/18 QC#29661 Add End

            // 2023/04/20 QC#61200 Add Start
            } else if ("NMAL7190Scrn00_MassUpdate".equals(screenAplID)) {
                doProcess_NMAL7190Scrn00_MassUpdate(bizMsg, glblMsg);
            // 2023/04/20 QC#61200 Add End

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
    private void doProcess_NMAL7190_INIT(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        ZYPCodeDataUtil.createPulldownList(PRC_GRP_TP.class, bizMsg.prcGrpTpCd_P, bizMsg.prcGrpTpDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(PRC_GRP_OP.class, bizMsg.prcGrpOpCd_BK, bizMsg.prcGrpOpDescTxt_BK);
        ZYPCodeDataUtil.createPulldownList(PRC_GRP_TRX_TP.class, bizMsg.prcGrpTrxTpCd_P, bizMsg.prcGrpTrxTpDescTxt_P);

        ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        // Add Start 2016/09/02 QC#8213
        ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg, ZYPConstant.CHKBOX_ON_Y);
        // Add End 2016/09/02 QC#8213

        if (ZYPCommonFunc.hasValue(bizMsg.prcGrpPk)) {
            // 2018/12/05 QC#29324 Mod Start
            //doProcess_NMAL7190Scrn00_Search(bizMsg, glblMsg);
            if (!"NMAL7190Scrn00_CMN_Submit".equals(bizMsg.getScreenAplID())) {
                bizMsg.F.clear();
                glblMsg.F.clear();
            }
            search(bizMsg, glblMsg);
            // 2018/12/05 QC#29324 Mod End
        }
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_CMN_Download(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        NMAL7190CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        NMAL7190CommonLogic.writeCsvFileContInfo(bizMsg, glblMsg, getGlobalCompanyCode());
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_CMN_Clear(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        ZYPTableUtil.clear(glblMsg.A);
        glblMsg.clear();
        doProcess_NMAL7190_INIT(bizMsg, glblMsg);
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_CMN_Reset(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        ZYPTableUtil.clear(glblMsg.A);
        glblMsg.clear();
        doProcess_NMAL7190_INIT(bizMsg, glblMsg);

    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_CMN_Submit(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
            BigDecimal tmpPk = bizMsg.prcGrpPk_BK.getValue();

            // 2018/12/05 QC#29324 Add Start
            NMAL7190CMsg bkCMsg = new NMAL7190CMsg();
            EZDMsg.copy(bizMsg, null, bkCMsg, null);
            // 2018/12/05 QC#29324 Add End

            bizMsg.clear();
            glblMsg.clear();
            ZYPEZDItemValueSetter.setValue(bizMsg.prcGrpPk, tmpPk);

            // 2018/12/05 QC#29324 Add Start
            EZDMsg.copy(bkCMsg.F, null, bizMsg.F, null);
            EZDMsg.copy(bizMsg.F, null, glblMsg.F, null);
            // 2018/12/05 QC#29324 Add End

            doProcess_NMAL7190_INIT(bizMsg, glblMsg);
            if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
                bizMsg.setMessageInfo(ZZZM9003I, new String[]{"Submit"});
            }
        }
    }

    /**
     * DeleteRow Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_DeleteRow(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        NMAL7190CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        List<Integer> delIdx = ZYPTableUtil.getSelectedRows(glblMsg.A, XX_CHKBOX_NAME, ZYPConstant.CHKBOX_ON_Y);

        // 2018/12/18 QC#29661 Add Start
        if (delIdx.isEmpty()) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAM8054E);
            }
            return;
        }
        // 2018/12/18 QC#29661 Add End
        // 2023/04/20 QC#61200 Add Start
        List <Integer> delTargetIdx = new ArrayList<Integer>();
        List <Integer> errTargetIdx = new ArrayList<Integer>();
        int errIdx = -1;
        boolean isErr = false;
        for (int idx : delIdx) {
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(idx).prcGrpDtlPk_A1)) {
                errTargetIdx.add(idx);
                isErr = true;
            } else {
                delTargetIdx.add(idx);
            }
        }
        if (isErr) {
            ZYPTableUtil.deleteRows(glblMsg.A, delTargetIdx);
            for (int idx : errTargetIdx) {
                glblMsg.A.no(idx).xxChkBox_A1.setErrorInfo(1, NMAM8723E);
            }
            bizMsg.xxPageShowFromNum.setValue(errIdx);
            NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
            return;
        }
        // 2023/04/20 QC#61200 Add End

        // 202304/20 QC#61200 Mod Start
//        ZYPTableUtil.deleteRows(glblMsg.A, delIdx);
        ZYPTableUtil.deleteRows(glblMsg.A, delTargetIdx);
        // 202304/20 QC#61200 Mod End
        if (bizMsg.xxPageShowFromNum.getValueInt() > glblMsg.A.getValidCount()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(glblMsg.A.getValidCount() - 1));
        }
        NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * InsertRow Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_InsertRow(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        if (glblMsg.A.getValidCount() + 1 > glblMsg.A.length()) {
            bizMsg.setMessageInfo(NMAM8187E, new String[] {PRICE_GROUP_DETAIL, String.valueOf(glblMsg.A.length())});
            return;
        }

        NMAL7190CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(glblMsg.A.getValidCount()).effFromDt_A1, ZYPDateUtil.getSalesDate());
        bizMsg.xxPageShowFromNum.setValue(glblMsg.A.getValidCount());
        glblMsg.A.setValidCount(glblMsg.A.getValidCount() + 1);
        NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * OnChange_TargetContext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_OnChange_AttributeName(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        NMAL7190CommonLogic.clearPrcGrpTrgtAttrbFlg(bizMsg.A.no(idx));
        NMAL7190CommonLogic.clearPrcGrpOpPulldown(bizMsg.A.no(idx));
        NMAL7190CommonLogic.clearPrcGrpTxt(bizMsg.A.no(idx));
        NMAL7190CommonLogic.getPrcGrpTrgtAttrb(bizMsg.A.no(idx), getGlobalCompanyCode());
        NMAL7190CommonLogic.setPulldownForPrcGrpOp(bizMsg.A.no(idx), bizMsg);
    }

    /**
     * OnChange_TargetContext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_OnChange_TargetContext(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();

        NMAL7190CommonLogic.clearPrcGrpTrgtAttrbPulldown(bizMsg.A.no(idx));
        NMAL7190CommonLogic.createPulldownForPrcGrpTrgtAttrb(bizMsg.A.no(idx));
    }

    /**
     * OnChange_TargetContext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_OnChange_PricingGroupType(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        NMAL7190CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            glblMsg.A.no(i).prcGrpTrgtTpCd_A1.clear();
        }
        NMAL7190CommonLogic.createPulldownForPrcGrpTrgtTp(bizMsg, glblMsg);
        NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_Search(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {

        // 2018/12/05 QC#29324 Mod Start
        bizMsg.F.clear();
        glblMsg.F.clear();
        // 2018/12/05 QC#29324 Mod End

        // search
        search(bizMsg, glblMsg);
    }

    /**
     * Upload Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_Upload(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        NMAL7190CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        String path = bizMsg.xxFileData_UP.getTempFilePath();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;

        int length = glblMsg.A.length();
        int idx = glblMsg.A.getValidCount();
        
        // Mod Start 2016/11/22 M.Ohno S21_NA#16082
        String csvFilePath = ZYPExcelUtil.excelToCsvFile(path);

        NMAL7190F00FMsg fMsg = new NMAL7190F00FMsg();

        // EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvFilePath, fMsg, option);
        // Mod End   2016/11/22 M.Ohno S21_NA#16082

        try {

            if (!NMAL7190CommonLogic.readHeaderCsvFile(mappedFile, bizMsg)) {
                return;
            }
            fMsg.clear();

            int status = -1;
            int upCnt = 0;

            while ((status = mappedFile.read()) != 1) {

                upCnt++;

                if (idx >= length) {
                    bizMsg.setMessageInfo(NMAM8187E, new String[] {PRICE_GROUP_DETAIL, String.valueOf(glblMsg.A.length())});
                    idx = length;
                    break;
                }

                if (NMAL7190CommonLogic.validateGlblMsg_UPLOAD(status, idx, upCnt, glblMsg.A, fMsg, bizMsg)) {
                    return;
                }

                if (NMAL7190CommonLogic.copyToGlblMsg_UPLOAD(idx, upCnt, glblMsg.A, fMsg, bizMsg)) {
                    return;
                }

                idx++;
                fMsg.clear();
            }

            if ((idx - glblMsg.A.getValidCount()) == 0) {
                bizMsg.setMessageInfo(NMAM8193E);
            }

            glblMsg.A.setValidCount(idx);

            if (!checkDetailTermDup(bizMsg, glblMsg, glblMsg.A.getValidCount() - upCnt)) {
                return;
            }

            bizMsg.xxPageShowFromNum.setValue(glblMsg.A.getValidCount() - upCnt);
            NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);

        } finally {

            mappedFile.close();
            bizMsg.xxFileData_UP.deleteTempFile();
            // Add Start 2016/11/22 M.Ohno S21_NA#16082\
            ZYPExcelUtil.deleteFile(csvFilePath);
            // Add End   2016/11/22 M.Ohno S21_NA#16082
        }
    }

    /**
     * checkDetailDup
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param origCount int
     */
    private Boolean checkDetailTermDup(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg, int origCount) {

        Integer[] errIdxList = NMAL7190CommonLogic.checkDupAttrb(glblMsg.A, CHK_ATTRB_NM_LIST);
        if (errIdxList.length > 0) {
            StringBuilder sb = new StringBuilder();
            // 2018/08/01 Add Start QC#26297
            Arrays.sort(errIdxList);
            // 2018/08/01 Add End QC#26297
            for (int errIdx : errIdxList) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                if (errIdx >= origCount) {
                    // because Index is 0 start.
                    sb.append(String.valueOf((errIdx - origCount) + 1));
                }
            }
            // 2018/08/01 Mod Start QC#26297
//            bizMsg.setMessageInfo(NMAM0072E, new String[]{COMBI_START_END + ": row at " + sb});
            bizMsg.setMessageInfo(NMAM0072E, new String[]{UPLD_DATA_DUP + ": row# at " + sb});
            // 2018/08/01 Mod End QC#26297
            return false;
        }

        Integer[] errIdxList2 = NMAL7190CommonLogic.checkDupAttrb(glblMsg.A, CHK_ATTRB_NM_LIST_UPLOAD);
        if (errIdxList2.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int errIdx : errIdxList) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(String.valueOf(errIdx));
            }
            // 2018/08/01 Mod Start QC#26297
//            bizMsg.setMessageInfo(NMAM0072E, new String[]{COMBI_START_END + ": row at" + sb});
            bizMsg.setMessageInfo(NMAM0072E, new String[]{UPLD_DATA_DUP + ": row# at" + sb});
            // 2018/08/01 Mod End QC#26297
            return false;
        }

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxOrigQty_A1, new BigDecimal(i + 1));
        }

        // execute column sort function
        S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(DTL_EFF_FROM_DT_A1, SORT_BY_ASC);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

        NMAL7190CommonLogic.changeDupTermByGrp(glblMsg.A, DTL_EFF_FROM_DT_A1, DTL_EFF_THRU_DT_A1, GRP_ATTRB_NM_LIST);

        // execute column sort function
        S21SortTarget resortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
        S21SortKey resortKey = new S21SortKey();
        resortKey.add(XX_ORIG_QTY_A1, SORT_BY_ASC);
        S21EZDMsgArraySort.sort(resortTarget, resortKey, 0, glblMsg.A.getValidCount());

        return true;
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_PageJump(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        NMAL7190CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_PageNext(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        NMAL7190CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7190Scrn00_PagePrev(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        NMAL7190CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);

    }

    /**
     * Template Download Event process.
     * @param cMsg
     * @param sMsg
     */
    protected void doProcess_NMAL7190Scrn00_TemplateDownload(NMAL7190CMsg bizMsg, EZDSMsg glblMsg) {

        // 1.get Global Company Code from user profile Info.
        String glblCmpyCd = getGlobalCompanyCode();

        // 2.
        ZYEC023001PMsg inoutMsg = new ZYEC023001PMsg();
        inoutMsg.glblCmpyCd.setValue(glblCmpyCd);

        String upldCsvId = PRICE_GROUP_CSV_ID;

        inoutMsg.upldCsvId.setValue(upldCsvId);
        inoutMsg.xxUpldCsvTempDirTxt.clear();
        inoutMsg.xxUpldCsvTempBaseTxt.setValue(ZYPCSVOutFile.createCSVOutFileNm("UploadTemplate"));
        inoutMsg.xxUpldCsvTempExtnTxt.setValue(CSV_EXTENSION);
        ZYECSVUploadTemplateHeader header = ZYECSVUploadTemplateHeader.getInstance();
        header.exec(inoutMsg);

        // 3.
        ZYECSVUploadTemplateHeader.RETURN_CODE returnCode = ZYECSVUploadTemplateHeader.RETURN_CODE.valueFromCode(inoutMsg.getReturnCode());
        if (ZYECSVUploadTemplateHeader.RETURN_CODE.SUCCESS == returnCode) {
            EZDMsg.copy(inoutMsg, null, bizMsg, null);
        } else {
            bizMsg.setMessageInfo(inoutMsg.xxMsgIdList.no(0).xxMsgId.getValue());
        }
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NMAL7190Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.prcGrpPk_BK.clear();
            bizMsg.prcGrpNm.clear();
            bizMsg.prcGrpDescTxt.clear();
            bizMsg.prcGrpTpCd.clear();
            bizMsg.effFromDt.clear();
            bizMsg.effThruDt.clear();
            bizMsg.prcGrpTrxTpCd.clear();
            bizMsg.actvFlg.clear();
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NZZM0000E);

        } else {

            NMAL7190CommonLogic.setPrcGrp(ssmResult, glblMsg, bizMsg, getGlobalCompanyCode());
            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
        }
    }

    // 2018/12/05 QC#29324 Add Start
    /**
     * NMAL7190_NMAL7440
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void doProcess_NMAL7190_NMAL7440(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {

        EZDMsg.copy(bizMsg.F, null, glblMsg.F, null);

        search(bizMsg, glblMsg);

        bizMsg.setCommitSMsg(true);
    }

    /**
     * TBLColumnSort
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void doProcess_NMAL7190Scrn00_TBLColumnSort(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {

        NMAL7190CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
        }
    }
    // 2018/12/05 QC#29324 Add End

    // 2018/12/18 QC#29661 Add Start
    /**
     * Select_All
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void doProcess_NMAL7190Scrn00_Select_All(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {

        NMAL7190CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            // 2023/04/20 QC#61200 Mod Start
//            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcGrpDtlPk_A1)) {
//                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
//            }
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
            // 2023/04/20 QC#61200 Mod End
        }
        NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * UnSelect_All
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void doProcess_NMAL7190Scrn00_UnSelect_All(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {

        NMAL7190CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            glblMsg.A.no(i).xxChkBox_A1.clear();
        }
        NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }
    // 2018/12/18 QC#29661 Add End

    // 2023/04/20 QC#61200 Add Start
    /**
     * MassUpdate
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void doProcess_NMAL7190Scrn00_MassUpdate(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        NMAL7190CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo("NMAM8190E");
            return;
        }
        List<Integer> selIdx = ZYPTableUtil.getSelectedRows(glblMsg.A, XX_CHKBOX_NAME, ZYPConstant.CHKBOX_ON_Y);
        if (selIdx.isEmpty()) {
            bizMsg.setMessageInfo("NMAM8054E");
            return;
        }
        for (int idx : selIdx) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).effThruDt_A1, bizMsg.effThruDt_MU);
        }
        NMAL7190CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }
    // 2023/04/20 QC#61200 Add End
}
