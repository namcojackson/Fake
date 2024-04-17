/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFAL0210;

import static business.blap.NFAL0210.constant.NFAL0210Constant.*;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFAL0210.common.NFAL0210CommonLogic;
import business.file.NFAL0210F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Manual Journal Entry Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/13   Hitachi         K.Kojima        Create          N/A
 * 2016/08/09   Hitachi         J.Kim           Update          QC#12851
 *</pre>
 */
public class NFAL0210BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NFAL0210CMsg cMsg = (NFAL0210CMsg) arg0;
        NFAL0210SMsg sMsg = (NFAL0210SMsg) arg1;
        super.preDoProcess(arg0, arg1);

        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFAL0210_INIT".equals(screenAplID)) {
                doProcess_NFAL0210_INIT(cMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NFAL0210Scrn00_CatgSearch".equals(screenAplID)) {
                doProcess_NFAL0210Scrn00_CatgSearch(cMsg);
            } else if ("NFAL0210Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFAL0210Scrn00_CMN_Clear(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
                xxComnColOrdTxt = null;
            } else if ("NFAL0210Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFAL0210Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NFAL0210Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFAL0210Scrn00_PageJump(cMsg, sMsg);
            } else if ("NFAL0210Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFAL0210Scrn00_PageNext(cMsg, sMsg);
            } else if ("NFAL0210Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFAL0210Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NFAL0210Scrn00_Search".equals(screenAplID)) {
                doProcess_NFAL0210Scrn00_Search(cMsg, sMsg);
            } else if ("NFAL0210Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFAL0210Scrn00_TBLColumnSort(cMsg, sMsg);
            }
        } finally {
            sMsg.xxComnColOrdTxt.clear();
            ZYPEZDItemValueSetter.setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(arg0, arg1);
        }
    }

    private void doProcess_NFAL0210_INIT(NFAL0210CMsg cMsg) {
        cMsg.clear();
        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        cMsg.glSendCpltFlg_SV.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.glSendCpltFlg_CD.no(0), ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.glSendCpltFlg_CD.no(1), ZYPConstant.FLG_OFF_N);
        // START 2016/08/09 J.Kim [QC#12851,MOD]
        // ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem20Txt_D1.no(0), "Send");
        // ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem20Txt_D1.no(1), "Not Send");
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem20Txt_D1.no(0), "Sent");
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem20Txt_D1.no(1), "Not Sent");
        // END 2016/08/09 J.Kim [QC#12851,MOD]

        cMsg.rvslCpltFlg_SV.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.rvslCpltFlg_CD.no(0), ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.rvslCpltFlg_CD.no(1), ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem20Txt_D2.no(0), "Reversed");
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem20Txt_D2.no(1), "Not Reversed");

        cMsg.manJrnlCpltFlg_SV.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlCpltFlg_CD.no(0), ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.manJrnlCpltFlg_CD.no(1), ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem20Txt_D3.no(0), "Completed");
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem20Txt_D3.no(1), "Not Completed");

        cMsg.autoRvslFlg_SV.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.autoRvslFlg_CD.no(0), ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.autoRvslFlg_CD.no(1), ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem20Txt_D4.no(0), "Auto Reversal");
        // START 2016/08/09 J.Kim [QC#12851,MOD]
        // ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem20Txt_D4.no(1), "Not Auto Reversal");
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem20Txt_D4.no(1), "No Auto Reversal");
        // END 2016/08/09 J.Kim [QC#12851,MOD]

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
    }

    private void doProcess_NFAL0210Scrn00_CatgSearch(NFAL0210CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.jrnlCatgCd)) {
            if (!NFAL0210CommonLogic.jrnlCatgSearch(cMsg)) {
                cMsg.jrnlCatgCd.setErrorInfo(1, NFAM0024E, new String[] {cMsg.jrnlCatgCd.getValue(), "Category" });
            }
        } else {
            cMsg.jrnlCatgDescTxt.clear();
        }
    }

    private void doProcess_NFAL0210Scrn00_CMN_Clear(NFAL0210CMsg cMsg, NFAL0210SMsg sMsg) {
        cMsg.A.clear();
        sMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.setValidCount(0);
        cMsg.clear();
        sMsg.clear();
        doProcess_NFAL0210_INIT(cMsg);
    }

    private void doProcess_NFAL0210Scrn00_CMN_Download(NFAL0210CMsg cMsg, NFAL0210SMsg sMsg) {
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("Manual Journal Entry Inquiry"), ".csv");

        NFAL0210F00FMsg fMsg = new NFAL0210F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
        fMsg.addExclusionItem("xxRadioBtn_A");

        csvOutFile.writeHeader(new String[] {"xxRadioBtn_A", "Journal Name", "Category", "GL Period", "Acct Date", "GL Send Status", "Rvsl Status", "Rvsl Date", "Comp", "Auto Rvsl" }, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (cMsg.A.getValidCount() > 0) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(fMsg.manJrnlNm_A, sMsg.A.no(i).manJrnlNm_A);
                ZYPEZDItemValueSetter.setValue(fMsg.jrnlCatgDescTxt_A, sMsg.A.no(i).jrnlCatgDescTxt_A);
                ZYPEZDItemValueSetter.setValue(fMsg.glPerNm_A, sMsg.A.no(i).glPerNm_A);
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A1, ZYPDateUtil.formatEzd8ToDisp(sMsg.A.no(i).glDt_A.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.glSendCpltFlg_A, sMsg.A.no(i).glSendCpltFlg_A);
                ZYPEZDItemValueSetter.setValue(fMsg.rvslCpltFlg_A, sMsg.A.no(i).rvslCpltFlg_A);
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A2, ZYPDateUtil.formatEzd8ToDisp(sMsg.A.no(i).actlRvslDt_A.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.manJrnlCpltFlg_A, sMsg.A.no(i).manJrnlCpltFlg_A);
                ZYPEZDItemValueSetter.setValue(fMsg.autoRvslFlg_A, sMsg.A.no(i).autoRvslFlg_A);
                csvOutFile.write();
            }
        }
        csvOutFile.close();
    }

    private void doProcess_NFAL0210Scrn00_PageJump(NFAL0210CMsg cMsg, NFAL0210SMsg sMsg) {
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        NFAL0210CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NFAL0210Scrn00_PageNext(NFAL0210CMsg cMsg, NFAL0210SMsg sMsg) {
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NFAL0210CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NFAL0210Scrn00_PagePrev(NFAL0210CMsg cMsg, NFAL0210SMsg sMsg) {
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NFAL0210CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NFAL0210Scrn00_Search(NFAL0210CMsg cMsg, NFAL0210SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.jrnlCatgCd)) {
            NFAL0210CommonLogic.jrnlCatgSearch(cMsg);
        } else {
            cMsg.jrnlCatgDescTxt.clear();
        }

        if (!NFAL0210CommonLogic.hasSearchCondition(cMsg)) {
            cMsg.manJrnlNm.setErrorInfo(1, NFAM0171E);
            cMsg.glPerNm.setErrorInfo(1, NFAM0171E);
            cMsg.xxFromDt.setErrorInfo(1, NFAM0171E);
            cMsg.xxToDt.setErrorInfo(1, NFAM0171E);
            cMsg.jrnlCatgCd.setErrorInfo(1, NFAM0171E);
            cMsg.glSendCpltFlg_SV.setErrorInfo(1, NFAM0171E);
            cMsg.rvslCpltFlg_SV.setErrorInfo(1, NFAM0171E);
            cMsg.manJrnlCpltFlg_SV.setErrorInfo(1, NFAM0171E);
            cMsg.autoRvslFlg_SV.setErrorInfo(1, NFAM0171E);
            return;
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxFromDt) && ZYPCommonFunc.hasValue(cMsg.xxToDt)) {
            if (ZYPDateUtil.compare(cMsg.xxFromDt.getValue(), cMsg.xxToDt.getValue()) > 0) {
                cMsg.xxFromDt.setErrorInfo(1, NZZM0014E, new String[] {"Accounting Date" });
                cMsg.xxToDt.setErrorInfo(1, NZZM0014E, new String[] {"Accounting Date" });
                return;
            }
        }

        NFAL0210CommonLogic.getSearchData(cMsg, sMsg);
        NFAL0210CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NFAL0210Scrn00_TBLColumnSort(NFAL0210CMsg cMsg, NFAL0210SMsg sMsg) {
        NFAL0210CommonLogic.clearPageNum(cMsg);

        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

        EZDMsg.copy(sMsg.A, null, cMsg.A, null);
        NFAL0210CommonLogic.setPageNum(cMsg, 1, cMsg.A.getValidCount(), sMsg.A.getValidCount());
    }

}
