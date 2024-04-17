/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0220;

import static business.blap.NFAL0220.constant.NFAL0220Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFAL0220.common.NFAL0220CommonLogic;
import business.file.NFAL0220F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NFAL0220BL02
 * Manual Journal Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/02   Fujitsu         S.Fujita        Create          N/A
 * 2016/06/21   Hitachi         J.Kim           Update          QC#10325
 * 2016/07/05   Hitachi         J.Kim           Update          QC#10325
 * 2016/07/13   Hitachi         J.Kim           Update          QC#10324
 * 2016/08/05   Hitachi         J.Kim           Update          QC#12851
 * 2016/08/22   Hitachi         J.Kim           Update          QC#13537
 * 2016/10/20   Hitachi         J.Kim           Update          QC#13460
 * 2016/12/19   Hitachi         E.Kameishi      Update          QC#16492
 *</pre>
 */
public class NFAL0220BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NFAL0220CMsg cMsg = (NFAL0220CMsg) arg0;
        NFAL0220SMsg sMsg = (NFAL0220SMsg) arg1;
        super.preDoProcess(arg0, arg1);
        try {

            String screenAplID = cMsg.getScreenAplID();
            if ("NFAL0220_INIT".equals(screenAplID)) {
                doProcess_NFAL0220_INIT(cMsg, sMsg);
            } else if ("NFAL0220Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NFAL0220Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NFAL0220Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NFAL0220Scrn00_CopyJournal".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_CopyJournal(cMsg, sMsg);
            } else if ("NFAL0220Scrn00_Reverse".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_Reverse(cMsg, sMsg);
            } else if ("NFAL0220Scrn00_Delete".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_Delete(cMsg, sMsg);
            } else if ("NFAL0220Scrn00_UploadFile".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_UploadFile(cMsg, sMsg);
            } else if ("NFAL0220Scrn00_AddLine".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_AddLine(cMsg, sMsg);
            } else if ("NFAL0220Scrn00_CatgSearchBtn".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_CatgSearchBtn(cMsg, sMsg);
            } else if ("NFAL0220Scrn00_TemplateDownload".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_TemplateDownload(cMsg, sMsg);
            } else if ("NFAL0220Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_PageNext(cMsg, sMsg);
            } else if ("NFAL0220Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NFAL0220Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_PageJump(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NFAL0220_INIT(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        doProcessInit(cMsg, sMsg);
    }

    private void doProcess_NFAL0220Scrn00_AddLine(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFAL0220CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        if (sMsg.A.getValidCount() >= sMsg.A.length()) {
            cMsg.setMessageInfo(NFAM0072E, new String[] {"Record", String.valueOf(sMsg.A.length()) });
            return;
        }

        BigDecimal lastNum = BigDecimal.ZERO;
        int sMsgCount = sMsg.A.getValidCount();
        if (sMsgCount > 0) {
            lastNum = sMsg.A.no(sMsgCount - 1).xxNum_A.getValue();
        }

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgCount).xxNum_A, lastNum.add(BigDecimal.ONE));
        sMsg.A.setValidCount(sMsgCount + 1);

        int rowIndex = NFAL0220CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), sMsgCount);
        NFAL0220CommonLogic.pagenation(cMsg, sMsg, rowIndex);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NFAL0220Scrn00_CMN_Clear(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        cMsg.manJrnlEntryHdrPk.clear();
        doProcessInit(cMsg, sMsg);
    }

    private void doProcess_NFAL0220Scrn00_CMN_Download(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        // create csv file
        // START 2016/07/13 J.Kim [QC#10324,MOD]
        // cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BIZ_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");
        // END 2016/07/13 J.Kim [QC#10324,MOD]
        NFAL0220F00FMsg fMsg = new NFAL0220F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
        NFAL0220CommonLogic.writeCsvFileForSvcPrcShift(cMsg, sMsg, fMsg, csvOutFile);
    }

    private void doProcess_NFAL0220Scrn00_CMN_Submit(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        doProcessInit(cMsg, sMsg);
    }

    private void doProcess_NFAL0220Scrn00_CopyJournal(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFAL0220CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        // START 2016/07/05 J.Kim [QC#10325,MOD]
        if (!NFAL0220CommonLogic.copyJournal(cMsg, sMsg)) {
            return;
        }
        // END 2016/07/05 J.Kim [QC#10325,MOD]

        NFAL0220CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NFAL0220Scrn00_Reverse(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFAL0220CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        if (!NFAL0220CommonLogic.reverse(cMsg, sMsg)) {
            return;
        }

        NFAL0220CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NFAL0220Scrn00_Delete(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFAL0220CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        if (!NFAL0220CommonLogic.checkSelect(cMsg, sMsg)) {
            return;
        }

        int beforePageNum = NFAL0220CommonLogic.getPageNum(cMsg.A.length(), cMsg.xxPageShowFromNum.getValueInt());
        int afterMaxPageNum = NFAL0220CommonLogic.getPageNum(cMsg.A.length(), sMsg.A.getValidCount());
        if (afterMaxPageNum < beforePageNum) {
            pageFromNum = cMsg.A.length() * (afterMaxPageNum - 1);
        }
        NFAL0220CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NFAL0220Scrn00_UploadFile(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        // START 2016/12/19 E.Kameishi [QC#16492,ADD]
        int dValCnt = sMsg.D.getValidCount();
        int aValCnt = sMsg.A.getValidCount();
        for (int index = 0; index < aValCnt; index++) {
            ZYPEZDItemValueSetter.setValue(sMsg.D.no(dValCnt + index).manJrnlEntryDtlPk, sMsg.A.no(index).manJrnlEntryDtlPk_A);
        }
        sMsg.D.setValidCount(dValCnt + aValCnt);
        // END 2016/12/19 E.Kameishi [QC#16492,ADD]
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        NFAL0220CommonLogic.createPullDown(cMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.rsvdRvslDt, NFAL0220CommonLogic.getRsvdRvslDt());
        // START 2016/12/19 E.Kameishi [QC#16492,MOD]
        if (!ZYPCommonFunc.hasValue(cMsg.manJrnlEntryHdrPk.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd_N, NEW);
        }
        // END 2016/12/19 E.Kameishi [QC#16492,MOD]

        if (!NFAL0220CommonLogic.uploadFile(cMsg, sMsg)) {
            return;
        }

        NFAL0220CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        cMsg.setCommitSMsg(true);
    }

    private void doProcess_NFAL0220Scrn00_CatgSearchBtn(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.jrnlCatgCd)) {
            cMsg.jrnlCatgDescTxt.clear();
            return;
        }

        if (!NFAL0220CommonLogic.jrnlCatgSearch(cMsg)) {
            cMsg.jrnlCatgDescTxt.clear();
            cMsg.jrnlCatgCd.setErrorInfo(1, NFAM0024E, new String[] {cMsg.jrnlCatgCd.getValue(), "Journal Category Code" });
            return;
        }
    }

    private void doProcess_NFAL0220Scrn00_TemplateDownload(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        // create csv file
        // START 2016/07/13 J.Kim [QC#10324,MOD]
        // cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BIZ_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");
        // END 2016/07/13 J.Kim [QC#10324,MOD]
        NFAL0220F00FMsg fMsg = new NFAL0220F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
        NFAL0220CommonLogic.writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        csvOutFile.close();
    }

    private void doProcess_NFAL0220Scrn00_PageNext(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFAL0220CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NFAL0220CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NFAL0220Scrn00_PagePrev(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFAL0220CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NFAL0220CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    // START 2016/08/05 J.Kim [QC#12851,MOD]
    private void doProcess_NFAL0220Scrn00_PageJump(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg){

        int rowIndex = NFAL0220CommonLogic.convertPageNum(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NFAL0220CommonLogic.pagenation(cMsg, sMsg, rowIndex);

    }
    // END 2016/08/05 J.Kim [QC#12851,MOD]

    private void doProcessInit(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.D);
        ZYPTableUtil.clear(sMsg.E);

        if (ZYPCommonFunc.hasValue(cMsg.manJrnlEntryHdrPk)) {
            setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
            setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

            NFAL0220CommonLogic.searchManJrnlEntry(cMsg, sMsg);
            ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd_N, EDIT);
        } else {
            NFAL0220CommonLogic.clearMsg(cMsg, sMsg);
            setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
            setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

            NFAL0220CommonLogic.createPullDown(cMsg);
            ZYPEZDItemValueSetter.setValue(cMsg.rsvdRvslDt, NFAL0220CommonLogic.getRsvdRvslDt());
            // START 2016/10/20 J.Kim [QC#13460,DEL]
            // ZYPEZDItemValueSetter.setValue(cMsg.autoRvslFlg, ZYPConstant.CHKBOX_ON_Y);
            // END 2016/10/20 J.Kim [QC#13460,DEL]
            ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd_N, NEW);
            // START 2016/08/04 J.Kim [QC#12851,ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.glDt, ZYPDateUtil.getSalesDate());
            // END 2016/08/04 J.Kim [QC#12851,ADD]
            // START 2016/08/22 J.Kim [QC#13537,ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.glPerNm, NFAL0220CommonLogic.convertGlPeriod(cMsg));
            // END 2016/08/22 J.Kim [QC#1357,ADD]
            NFAL0220CommonLogic.setInitLine(sMsg);
        }
        NFAL0220CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }
}
