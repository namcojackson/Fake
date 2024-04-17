/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7210;

import static business.blap.NMAL7210.constant.NMAL7210Constant.CSV_DOWNLOAD_HEADER;
import static business.blap.NMAL7210.constant.NMAL7210Constant.CSV_FILE_EXTENSION;
import static business.blap.NMAL7210.constant.NMAL7210Constant.CSV_FILE_NM;
import static business.blap.NMAL7210.constant.NMAL7210Constant.NMAM0007I;
import static business.blap.NMAL7210.constant.NMAL7210Constant.NZZM0007E;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.file.NMAL7210F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FMLA_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7210BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7210BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7210CMsg bizMsg = (NMAL7210CMsg) cMsg;

            if ("NMAL7210_INIT".equals(screenAplID)) {
                doProcess_NMAL7210_INIT(bizMsg);

            } else if ("NMAL7210Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7210Scrn00_CMN_Download(bizMsg);

            } else if ("NMAL7210Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL7210Scrn00_CMN_Reset(bizMsg);

            } else if ("NMAL7210Scrn00_NewFormula".equals(screenAplID)) {
                doProcess_NMAL7210Scrn00_NewFormula(bizMsg);

            } else if ("NMAL7210Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7210Scrn00_Search(bizMsg);

            } else if ("NMAL7210Scrn00_ViewFormula".equals(screenAplID)) {
                doProcess_NMAL7210Scrn00_ViewFormula(bizMsg);

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
     */
    private void doProcess_NMAL7210_INIT(NMAL7210CMsg bizMsg) {

        // Table Clear
        ZYPTableUtil.clear(bizMsg.A);

        // Pull down
        ZYPCodeDataUtil.createPulldownList(PRC_FMLA_TP.class, bizMsg.prcFmlaTpCd_L1, bizMsg.prcFmlaTpNm_L1);

        // Initial Set.
        ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg_H1, ZYPConstant.FLG_ON_Y);
    }

    /**
     * CMN_Download Event
     * @param bizMsg NMAL7210CMsg
     */
    private void doProcess_NMAL7210Scrn00_CMN_Download(NMAL7210CMsg bizMsg) {

        bizMsg.xxFileData.clear();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM), CSV_FILE_EXTENSION);
        NMAL7210F00FMsg fMsg = new NMAL7210F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, fMsg, null);
            csvOutFile.write();
        }

        csvOutFile.close();
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7210Scrn00_CMN_Reset(NMAL7210CMsg bizMsg) {
        doProcess_NMAL7210_INIT(bizMsg);

    }

    /**
     * New Formula Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7210Scrn00_NewFormula(NMAL7210CMsg bizMsg) {
        //
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7210Scrn00_Search(NMAL7210CMsg bizMsg) {
        // search
        search(bizMsg);
    }

    /**
     * View Formula Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7210Scrn00_ViewFormula(NMAL7210CMsg bizMsg) {
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     */
    private void search(NMAL7210CMsg bizMsg) {

        ZYPTableUtil.clear(bizMsg.A);

        S21SsmEZDResult ssmResult = NMAL7210Query.getInstance().search(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            // CNT 0
            bizMsg.setMessageInfo(NMAM0007I);
        } else {
            if (ssmResult.getQueryResultCount() > bizMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0007E);
            }

            NMAL7210_ACMsgArray msgArray = (NMAL7210_ACMsgArray) ssmResult.getResultObject();
            for (int i = 0; i < msgArray.getValidCount(); i++) {
                NMAL7210_ACMsg msg = (NMAL7210_ACMsg) msgArray.no(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).prcFmlaPk_A1, msg.prcFmlaPk_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).prcFmlaNm_A1, msg.prcFmlaNm_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).prcFmlaTpNm_A1, msg.prcFmlaTpNm_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).prcFmlaDescTxt_A1, msg.prcFmlaDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).effFromDt_A1, msg.effFromDt_A1);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).effThruDt_A1, msg.effThruDt_A1);
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, BigDecimal.ZERO);
        }
    }

}
