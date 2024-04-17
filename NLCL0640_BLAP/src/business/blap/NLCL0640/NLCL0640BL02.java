/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0640;

import static business.blap.NLCL0640.constant.NLCL0640Constant.CONST_PAGE_SIZE;
import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_NLCL0640_ADD_COUNT_ITEM;
import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_NLCL0640_CMN_RESET;
import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_NLCL0640_CMN_SUBMIT;
import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_NLCL0640_IMPORT_COUNT_ITEMS;
import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_NLCL0640_INIT;
import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_NLCL0640_NLCL0650;
import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_NLCL0640_SEARCH_MDSE_NAME;
import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_NLCL0640_TEMPLATE_FILE_FOR_UPLOAD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_PAGE_NEXT;
import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_PAGE_PREV;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLCM0025E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLCM0145E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLCM0149W;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLCM0224E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLCM0239E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.TEMPLATE_CSV_HEADER;
import static business.blap.NLCL0640.constant.NLCL0640Constant.TEMP_FILE_EXT;
import static business.blap.NLCL0640.constant.NLCL0640Constant.TEMP_FILE_NAME;
import static business.blap.NLCL0640.constant.NLCL0640Constant.ZYEM0004E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.ZZM9000E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.ZZM9014E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.MAX_UPLOAD_COUNT;

import java.math.BigDecimal;
import java.util.Arrays;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.NLCL0640.common.NLCL0640CommonLogic;
import business.file.NLCL0640F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLCL0640 Tech PI Count
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/10/2016   CITS         Makoto Okigami     Create          N/A
 * 05/09/2018   CITS            Y.Iwasaki       Update          QC#10572
 * 08/03/2018   CITS            Y.Iwasaki       Update          QC#26948
 * 10/26/2018   CITS            T.Wada          Update          QC#26948-2
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 * 01/07/2020   Fujitsu         T.Ogura         Update          QC#50672
 * 05/15/2023   Hitachi         TZ.Win          Update          QC#61427
 *</pre>
 */
public class NLCL0640BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL0640_INIT.equals(screenAplID)) {
                doProcess_NLCL0640_INIT((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            } else if (EVENT_NM_NLCL0640_ADD_COUNT_ITEM.equals(screenAplID)) {
                doProcess_Add_ItemCount((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            } else if (EVENT_NM_NLCL0640_IMPORT_COUNT_ITEMS.equals(screenAplID)) {
                doProcess_Import_ItemCount((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            } else if (EVENT_NM_NLCL0640_TEMPLATE_FILE_FOR_UPLOAD.equals(screenAplID)) {
                doProcess_TemplateFileForUpload((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            } else if (EVENT_NM_NLCL0640_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_CMN_Submit((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            } else if (EVENT_NM_NLCL0640_CMN_RESET.equals(screenAplID)) {
                doProcess_CMN_Reset((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            } else if (EVENT_NM_NLCL0640_SEARCH_MDSE_NAME.equals(screenAplID)) {
                doProcess_SearchMdseName((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            } else if (EVENT_NM_NLCL0640_NLCL0650.equals(screenAplID)) {
                doProcess_NLCL0650((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            } else if (EVENT_NM_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            } else if (EVENT_NM_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            }
            // START 2023/05/15 TZ.Win [QC#61427, ADD]
            if (EVENT_NM_NLCL0640_IMPORT_COUNT_ITEMS.equals(screenAplID) || EVENT_NM_NLCL0640_CMN_RESET.equals(screenAplID)) {
                NLCL0640CMsg bizMsg = (NLCL0640CMsg) cMsg;
                bizMsg.xxWrnSkipFlg_A.clear();
            }
            // END 2023/05/15 TZ.Win [QC#61427, ADD]

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Init
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    private void doProcess_NLCL0640_INIT(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        if (PHYS_INVTY_CNT_STS.SCHEDULED.equals(cMsg.physInvtyCntStsCd.getValue())) {

            NLCL0640CommonLogic.getTechLocInfoFromTechLocation(cMsg, sMsg, glblCmpyCd);

        } else if (PHYS_INVTY_CNT_STS.COUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {

            NLCL0640CommonLogic.getTechLocInfoFromTechLocation(cMsg, sMsg, glblCmpyCd);

        } else if (PHYS_INVTY_CNT_STS.WAITRECOUNT.equals(cMsg.physInvtyCntStsCd.getValue())) {

            // There is no processing.
            EZDDebugOutput.println(1, "NLCL0640BL02#doProcess_NLCL0640_INIT [status:WAITRECOUNT]", null);

        } else if (PHYS_INVTY_CNT_STS.RECOUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {

            NLCL0640CommonLogic.getTechLocInfoFromPICountHeader(cMsg, sMsg, glblCmpyCd);
            NLCL0640CommonLogic.prepreReCount(cMsg, sMsg, glblCmpyCd);

        } else if (PHYS_INVTY_CNT_STS.WAITAPPROVAL.equals(cMsg.physInvtyCntStsCd.getValue())) {

            cMsg.setMessageInfo(NLCM0149W);

        } else {

            cMsg.setMessageInfo(NLCM0145E, new String[] {cMsg.physInvtyCntStsCd.getValue()});

        }

        NLCL0640CommonLogic.setPagination(cMsg, sMsg, 0);
        NLCL0640CommonLogic.getCountStatus(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * doProcess_Add_ItemCount
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    private void doProcess_Add_ItemCount(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0640CommonLogic.getCountStatus(cMsg, sMsg, glblCmpyCd);

        // Error unless COUNTING or RECOUNTING
        if (!Arrays.asList(new String[] {PHYS_INVTY_CNT_STS.COUNTING, PHYS_INVTY_CNT_STS.RECOUNTING }).contains(cMsg.physInvtyCntStsCd.getValue())) {
            cMsg.setMessageInfo(NLCM0145E, new String[] {cMsg.physInvtyCntStsCd.getValue() });
            return;
        }

        if (!NLCL0640CommonLogic.addCountItem(cMsg, sMsg, glblCmpyCd)) {
            return;
        }

        // Pagination
        NLCL0640CommonLogic.lastPage(cMsg, sMsg);

        cMsg.mdseCd.clear();
        cMsg.mdseDescShortTxt.clear();
        cMsg.rcvSerTakeFlg.clear();
        cMsg.cntInpQty.clear();
        cMsg.serNum.clear();

        if (PHYS_INVTY_CNT_STS.RECOUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {
            NLCL0640CommonLogic.setReCountHeader(cMsg, sMsg, glblCmpyCd, sMsg.xxRowNum.getValue().intValue() + 1);
        }
    }

    /**
     * doProcess_Import_ItemCount
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    private void doProcess_Import_ItemCount(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0640CommonLogic.getCountStatus(cMsg, sMsg, glblCmpyCd);

        // Error unless COUNTING
        // START 2018/12/11 T.Ogura [QC#28755,MOD]
//        if (!PHYS_INVTY_CNT_STS.COUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {
        if (!Arrays.asList(new String[] {PHYS_INVTY_CNT_STS.COUNTING, PHYS_INVTY_CNT_STS.RECOUNTING }).contains(cMsg.physInvtyCntStsCd.getValue())) {
        // END   2018/12/11 T.Ogura [QC#28755,MOD]
            cMsg.setMessageInfo(NLCM0145E, new String[] {cMsg.physInvtyCntStsCd.getValue() });
            return;
        }

        String path = cMsg.xxFileData_UP.getTempFilePath();
        if (!ZYPCommonFunc.hasValue(path)) {
            cMsg.xxFileData_UP.setErrorInfo(1, ZYEM0004E);
            cMsg.setMessageInfo(ZYEM0004E);
            return;
        }
        String csvPath = ZYPExcelUtil.excelToCsvFile(path);

        NLCL0640F00FMsg fMsg = new NLCL0640F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvPath, fMsg, option);

        int recCount = 0;
        try {
            // Check Header Record Only
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.xxFileData_UP.setErrorInfo(1, ZYEM0004E);
                cMsg.setMessageInfo(ZYEM0004E);
                return;
            }

            // START 01/07/2020 T.Ogura [QC#50672,MOD]
//            while (mappedFile.read() != 1) {
            int retCd = 0;
            while ((retCd = mappedFile.read()) != 1) {
            // END   01/07/2020 T.Ogura [QC#50672,MOD]

                // Check size limit.
                // QC#26948-2 Mod Start
//              if (sMsg.A.getValidCount() >= sMsg.A.length()) {
                if (recCount >= MAX_UPLOAD_COUNT) {
                // QC#26948-2 Mod End
                    cMsg.setMessageInfo(NLCM0025E);
                    return;
                }

                // START 01/07/2020 T.Ogura [QC#50672,ADD]
                if (retCd != 0) {
                    String msgArg = "";
                    if (retCd == 1000) {
                        msgArg = "Invalid number of data items";
                    } else if (retCd > 1000 && retCd < 2000) {
                        msgArg = "Digits over of data items";
                    } else if (retCd >= 2000) {
                        msgArg = "Type of data items";
                    }
                    cMsg.setMessageInfo(NLCM0239E, new String[] {msgArg, String.valueOf(recCount + 2) });
                    return;
                }
                // END   01/07/2020 T.Ogura [QC#50672,ADD]

                // Check Mandatory
                if (!ZYPCommonFunc.hasValue(fMsg.rtlWhCd_U)) {
                    cMsg.setMessageInfo(ZZM9000E, new String[] {TEMPLATE_CSV_HEADER[0] });
                    return;
                }
                if (!ZYPCommonFunc.hasValue(fMsg.rtlSwhCd_U)) {
                    cMsg.setMessageInfo(ZZM9000E, new String[] {TEMPLATE_CSV_HEADER[1] });
                    return;
                }
                if (!ZYPCommonFunc.hasValue(fMsg.mdseCd_U)) {
                    cMsg.setMessageInfo(ZZM9000E, new String[] {TEMPLATE_CSV_HEADER[2] });
                    return;
                }
                if (!ZYPCommonFunc.hasValue(fMsg.cntInpQty_U)) {
                    cMsg.setMessageInfo(ZZM9000E, new String[] {TEMPLATE_CSV_HEADER[3] });
                    return;
                }
                String techLocCd = null;
                for (int n = 0; n < cMsg.T.getValidCount(); ++n) {
                    if (fMsg.rtlWhCd_U.getValue().equals(cMsg.T.no(n).rtlWhCd_T1.getValue()) && fMsg.rtlSwhCd_U.getValue().equals(cMsg.T.no(n).rtlSwhCd_T1.getValue())) {
                        techLocCd = cMsg.T.no(n).techLocCd_T1.getValue();
                        break;
                    }
                }
                if (!ZYPCommonFunc.hasValue(techLocCd)) {
                    cMsg.setMessageInfo(NLCM0224E, new String[] {TEMPLATE_CSV_HEADER[0] + "," + TEMPLATE_CSV_HEADER[1], fMsg.rtlWhCd_U.getValue() + "," + fMsg.rtlSwhCd_U.getValue() });
                    return;
                }

                // Add count items
                ZYPEZDItemValueSetter.setValue(cMsg.techLocCd_SL, techLocCd);
                ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, fMsg.mdseCd_U);
                ZYPEZDItemValueSetter.setValue(cMsg.cntInpQty, fMsg.cntInpQty_U);
                ZYPEZDItemValueSetter.setValue(cMsg.serNum, fMsg.serNum_U);
                if (!NLCL0640CommonLogic.addCountItem(cMsg, sMsg, glblCmpyCd)) {
                    cMsg.techLocCd_SL.clear();
                    cMsg.mdseCd.clear();
                    cMsg.mdseDescShortTxt.clear();
                    cMsg.rcvSerTakeFlg.clear();
                    cMsg.cntInpQty.clear();
                    cMsg.serNum.clear();

                    return;
                }

                cMsg.techLocCd_SL.clear();
                cMsg.mdseCd.clear();
                cMsg.mdseDescShortTxt.clear();
                cMsg.rcvSerTakeFlg.clear();
                cMsg.cntInpQty.clear();
                cMsg.serNum.clear();

                ++recCount;
            }

            if (recCount <= 0) {
                cMsg.setMessageInfo(ZZM9014E, new String[] {"Import" });
                return;
            }

        } finally {
            mappedFile.close();
            cMsg.xxFileData_UP.deleteTempFile();
            ZYPExcelUtil.deleteFile(csvPath);
        }

        // Pagination
        NLCL0640CommonLogic.lastPage(cMsg, sMsg);
    }

    /**
     * doProcess_TemplateFileForUpload
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    private void doProcess_TemplateFileForUpload(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {

        NLCL0640F00FMsg fMsg = new NLCL0640F00FMsg();
        cMsg.xxFileData_UP.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(TEMP_FILE_NAME), TEMP_FILE_EXT);

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_UP.getTempFilePath(), fMsg);
        csvOutFile.writeHeader(TEMPLATE_CSV_HEADER);
        csvOutFile.close();
    }

    /**
     * doProcess_CMN_Submit
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    private void doProcess_CMN_Submit(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        /*
        // Error unless COUNTING or RECOUNTING
        if (!Arrays.asList(new String[] {PHYS_INVTY_CNT_STS.COUNTING, PHYS_INVTY_CNT_STS.RECOUNTING }).contains(cMsg.physInvtyCntStsCd.getValue())) {
            cMsg.setMessageInfo(NLCM0145E, new String[] {cMsg.physInvtyCntStsCd.getValue() });
            return;
        }
        */

        NLCL0640CommonLogic.getCountStatus(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * doProcess_CMN_Reset
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    private void doProcess_CMN_Reset(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {
        doProcess_NLCL0640_INIT(cMsg, sMsg);
    }

    /**
     * Search MDSE Name
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    private void doProcess_SearchMdseName(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0640CommonLogic.getMerchandiseName(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * NLCL0650
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    private void doProcess_NLCL0650(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        if (PHYS_INVTY_CNT_STS.WAITRECOUNT.equals(cMsg.physInvtyCntStsCd.getValue())) {

            // There is no processing.
            EZDDebugOutput.println(1, "NLCL0640BL02#doProcess_NLCL0650 [status:WAITRECOUNT]", null);

        }

        NLCL0640CommonLogic.getCountStatus(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * doProcess_PagePrev
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    private void doProcess_PagePrev(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {

        int fromNum = 1;
        BigDecimal xxFromNum = cMsg.xxPageShowFromNum.getValue();
        if (xxFromNum != null) {
            fromNum = xxFromNum.intValue() - CONST_PAGE_SIZE;
        }

        NLCL0640CommonLogic.setPagination(cMsg, sMsg, fromNum);
    }

    /**
     * doProcess_PageNext
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    private void doProcess_PageNext(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {

        int fromNum = 1;
        BigDecimal xxFromNum = cMsg.xxPageShowFromNum.getValue();
        if (xxFromNum != null) {
            fromNum = xxFromNum.intValue() + CONST_PAGE_SIZE;
        }

        NLCL0640CommonLogic.setPagination(cMsg, sMsg, fromNum);
    }
}
