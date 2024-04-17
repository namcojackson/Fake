package business.blap.NWAL2320;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDFMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2320.common.NWAL2320CommonLogic;
import business.blap.NWAL2320.common.NWAL2320CommonLogicForUpload;
import business.blap.NWAL2320.constant.NWAL2320Constant;
import business.blap.NWAL2320.constant.NWAL2320Constant.TMPL_TP;
import business.file.NWAL2320F00FMsg;
import business.file.NWAL2320F01FMsg;
import business.file.NWAL2320F02FMsg;
import business.file.NWAL2320F03FMsg;
import business.file.NWAL2320F10FMsg;
import business.file.NWAL2320F11FMsg;
import business.file.NWAL2320F12FMsg;
import business.file.NWAL2320F13FMsg;

import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Util;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_UPLD_TMPL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NWAL2320BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2320CMsg bizMsg = (NWAL2320CMsg) cMsg;
            NWAL2320SMsg glblMsg = (NWAL2320SMsg) sMsg;

            if ("NWAL2320_INIT".equals(screenAplID)) {
                doProcess_NWAL2320_INIT(bizMsg, glblMsg);
            } else if ("NWAL2320Scrn00_Download_NewOrd".equals(screenAplID)) {
                doProcess_NWAL2320Scrn00_TemplateDownload(bizMsg, NWAL2320F00FMsg.class, TMPL_TP.NEW_SALES, NWAL2320Constant.TMPL_DL_HEADER_FOR_NEW_ORDER);
            } else if ("NWAL2320Scrn00_Download_ExsOrd".equals(screenAplID)) {
                doProcess_NWAL2320Scrn00_TemplateDownload(bizMsg, NWAL2320F01FMsg.class, TMPL_TP.EXIST_SALES, NWAL2320Constant.TMPL_DL_HEADER_FOR_EXS_ORDER);
            } else if ("NWAL2320Scrn00_Download_NewRma".equals(screenAplID)) {
                doProcess_NWAL2320Scrn00_TemplateDownload(bizMsg, NWAL2320F02FMsg.class, TMPL_TP.NEW_RMA, NWAL2320Constant.TMPL_DL_HEADER_FOR_NEW_RMA);
            } else if ("NWAL2320Scrn00_Download_ExsRma".equals(screenAplID)) {
                doProcess_NWAL2320Scrn00_TemplateDownload(bizMsg, NWAL2320F03FMsg.class, TMPL_TP.EXIST_RMA, NWAL2320Constant.TMPL_DL_HEADER_FOR_EXS_RMA);
            } else if ("NWAL2320Scrn00_Download".equals(screenAplID)) {
                doProcess_NWAL2320Scrn00_Download(bizMsg, glblMsg);
            } else if ("NWAL2320Scrn00_Upload".equals(screenAplID)) {
                doProcess_NWAL2320Scrn00_Upload(bizMsg, glblMsg);
            } else if ("NWAL2320Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL2320Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NWAL2320Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL2320Scrn00_PagePrev(bizMsg, glblMsg);
            } else if ("NWAL2320Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NWAL2320Scrn00_PageJump(bizMsg, glblMsg);
            } else if ("NWAL2320Scrn00_OnChange_RowsPerPage".equals(screenAplID)) {
                doProcess_NWAL2320Scrn00_OnChange_RowsPerPage(bizMsg, glblMsg);
            } else if ("NWAL2320Scrn00_CancelUpload".equals(screenAplID)) {
                doProcess_NWAL2320Scrn00_CancelUpload(bizMsg, glblMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }


    private void doProcess_NWAL2320_INIT(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {

        initialize(bizMsg, glblMsg);
    }

    private void initialize(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {

        clearSMsg(glblMsg);

        ZYPCodeDataUtil.createPulldownList("ORD_UPLD_TMPL_TP", bizMsg.ordUpldTmplTpCd_L1, bizMsg.ordUpldTmplTpDescTxt_L1);
        ZYPEZDItemValueSetter.setValue(bizMsg.ordUpldTmplTpCd, ORD_UPLD_TMPL_TP.NEW_SALES_ORDER);

        String maxRowCnt = ZYPCodeDataUtil.getVarCharConstValue(NWAL2320Constant.NWAL2320_MAX_ROW_CNT, bizMsg.glblCmpyCd.getValue());

        if (maxRowCnt == null) {
//          throw new S21AbendException("[Error]Not Found 'VAR_CHAR_CONST' : varCharConstNm=" + NWAL2320Constant.NWAL2320_MAX_ROW_CNT);
            bizMsg.setMessageInfo("NMAM8432E", new String[] {NWAL2320Constant.NWAL2320_MAX_ROW_CNT});
            
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.maxLgNum, NWXC220001Util.convToBigDecimal(maxRowCnt));
        }

        String rowPerPage = ZYPCodeDataUtil.getVarCharConstValue(NWAL2320Constant.NWAL2320_ROW_PER_PAGE, bizMsg.glblCmpyCd.getValue());

        if (rowPerPage == null) {
//            throw new S21AbendException("[Error]Not Found 'VAR_CHAR_CONST' : varCharConstNm=" + NWAL2320Constant.NWAL2320_ROW_PER_PAGE);
            bizMsg.setMessageInfo("NMAM8432E", new String[] {NWAL2320Constant.NWAL2320_ROW_PER_PAGE});
        } else {
            String[] rowPerPageAry =  rowPerPage.split(",");

            for (int i = 0; i < rowPerPageAry.length; i++) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPageCd_LC.no(i), rowPerPageAry[i]);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPageCd_LT.no(i), rowPerPageAry[i]);
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowTotNum_CM, BigDecimal.ZERO);
        }

        EZDMsg.copy(bizMsg, null, glblMsg, null);
    }

    private <T extends EZDFMsg> void doProcess_NWAL2320Scrn00_TemplateDownload(NWAL2320CMsg bizMsg, Class<T> fMsg, TMPL_TP tmplTp, String[] headerNm) {

        try {
            String fileNm = String.format("%s_%s", NWAL2320Constant.BIZ_ID, tmplTp.toString());
            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(fileNm), NWAL2320Constant.EXT_CSV);

            T msg = fMsg.newInstance();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), msg);
            csvOutFile.writeHeader(headerNm);
            csvOutFile.close();
        } catch (Exception e) { }

    }

    private void doProcess_NWAL2320Scrn00_Download(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {

        String ordUpldTmplTpCd = bizMsg.ordUpldTmplTpCd.getValue();

        if (ORD_UPLD_TMPL_TP.NEW_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            doProcess_NWAL2320Scrn00_Download(bizMsg, glblMsg, NWAL2320F10FMsg.class, TMPL_TP.NEW_SALES, NWAL2320Constant.TMPL_DL_HEADER_FOR_NEW_ORDER);
        } else if (ORD_UPLD_TMPL_TP.EXISTING_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            doProcess_NWAL2320Scrn00_Download(bizMsg, glblMsg, NWAL2320F11FMsg.class, TMPL_TP.EXIST_SALES, NWAL2320Constant.TMPL_DL_HEADER_FOR_EXS_ORDER);
        } else if (ORD_UPLD_TMPL_TP.NEW_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            doProcess_NWAL2320Scrn00_Download(bizMsg, glblMsg, NWAL2320F12FMsg.class, TMPL_TP.NEW_RMA, NWAL2320Constant.TMPL_DL_HEADER_FOR_NEW_RMA);
        } else if (ORD_UPLD_TMPL_TP.EXISTING_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            doProcess_NWAL2320Scrn00_Download(bizMsg, glblMsg, NWAL2320F13FMsg.class, TMPL_TP.EXIST_RMA, NWAL2320Constant.TMPL_DL_HEADER_FOR_EXS_RMA);
        }
    }

    private <T extends EZDFMsg> void doProcess_NWAL2320Scrn00_Download(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, Class<T> fMsg, TMPL_TP tmplTp, String[] headerNm) {

        try {
            String fileNm = String.format("%s_%s", NWAL2320Constant.BIZ_ID, tmplTp.toString());
            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(fileNm), NWAL2320Constant.EXT_CSV);

            T lineFMsg = fMsg.newInstance();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), lineFMsg);

            // Add Header Item
            String[] outHeadeNm = new String[headerNm.length + NWAL2320Constant.DL_ADD_HEADER.length];
            System.arraycopy(headerNm, 0, outHeadeNm, 0, headerNm.length);
            System.arraycopy(NWAL2320Constant.DL_ADD_HEADER, 0, outHeadeNm, headerNm.length, NWAL2320Constant.DL_ADD_HEADER.length);

            csvOutFile.writeHeader(outHeadeNm);

            if (TMPL_TP.NEW_SALES.equals(tmplTp)) {
                for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                    EZDMsg.copy(glblMsg.A.no(i), "CO", lineFMsg, "");

                    csvOutFile.write();
                }
            } else if (TMPL_TP.EXIST_SALES.equals(tmplTp)) {
                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    EZDMsg.copy(glblMsg.B.no(i), "EO", lineFMsg, "");
                    csvOutFile.write();
                }
            } else if (TMPL_TP.NEW_RMA.equals(tmplTp)) {
                for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                    EZDMsg.copy(glblMsg.C.no(i), "NR", lineFMsg, "");
                    csvOutFile.write();
                }
            } else if (TMPL_TP.EXIST_RMA.equals(tmplTp)) {
                for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                    EZDMsg.copy(glblMsg.D.no(i), "ER", lineFMsg, "");
                    csvOutFile.write();
                }
            }

            csvOutFile.close();
        } catch (Exception e) { }

    }


    private void doProcess_NWAL2320Scrn00_Upload(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {

        if (NWAL2320CommonLogicForUpload.readCsvFile(bizMsg, glblMsg)) {
            NWAL2320CommonLogicForUpload.checkUpload(bizMsg, glblMsg);
            NWAL2320CommonLogic.pagenation(bizMsg, glblMsg, 1);
        }
    }

    private void doProcess_NWAL2320Scrn00_PageNext(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        int rowsPerPage = NWAL2320CommonLogic.getRowsPerPage(bizMsg);
        int pagenationFrom = bizMsg.xxPageShowFromNum_CM.getValueInt() + rowsPerPage - 1;
        NWAL2320CommonLogic.pagenation(bizMsg, glblMsg, pagenationFrom);
    }

    private void doProcess_NWAL2320Scrn00_PagePrev(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        int rowsPerPage = NWAL2320CommonLogic.getRowsPerPage(bizMsg);
        int pagenationFrom = bizMsg.xxPageShowFromNum_CM.getValueInt() - rowsPerPage - 1;
        NWAL2320CommonLogic.pagenation(bizMsg, glblMsg, pagenationFrom);
    }

    private void doProcess_NWAL2320Scrn00_PageJump(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        int rowsPerPage = NWAL2320CommonLogic.getRowsPerPage(bizMsg);
        int pagenationFrom = rowsPerPage * (bizMsg.xxPageShowCurNum_CM.getValueInt() - 1) + 1;
        NWAL2320CommonLogic.pagenation(bizMsg, glblMsg, pagenationFrom);
    }

    private void doProcess_NWAL2320Scrn00_OnChange_RowsPerPage(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        NWAL2320CommonLogic.pagenation(bizMsg, glblMsg, 1);
    }

    private void doProcess_NWAL2320Scrn00_CancelUpload(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        clearSMsg(glblMsg);
    }

    private void clearSMsg(NWAL2320SMsg glblMsg) {

        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        ZYPTableUtil.clear(glblMsg.D);
    }
}
