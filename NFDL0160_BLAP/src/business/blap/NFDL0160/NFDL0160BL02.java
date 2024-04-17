/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0160;

import static business.blap.NFDL0160.constant.NFDL0160Constant.*;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

import business.blap.NFDL0160.common.NFDL0160CommonLogic;
import business.file.NFDL0160F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Naito         Create          N/A
 * 2017/01/05   Fujitsu         T.Murai         Update          QC#16295
 * 2017/09/06   Hitachi         T.Tsuchida      Update          QC#20970
 * 2018/02/28   Hitachi         J.Kim           Update          QC#20944
 * 2018/08/03   Fujitsu         T.Ogura         Update          QC#25899
 *</pre>
 */
public class NFDL0160BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFDL0160CMsg bizMsg = (NFDL0160CMsg) cMsg;
            NFDL0160SMsg glblMsg = (NFDL0160SMsg) sMsg;

            if ("NFDL0160Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0160Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NFDL0160Scrn00_Search".equals(screenAplID)) {
                doProcess_NFDL0160Scrn00_Search(bizMsg, glblMsg);

            } else if ("NFDL0160_NWAL1130".equals(screenAplID)) {
                doProcess_NFDL0160_NWAL1130(bizMsg, glblMsg);

            // START 2017/01/05 [QC#16295,ADD]
            } else if ("NFDL0160Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NFDL0160Scrn00_InsertRow(bizMsg, glblMsg);
            // END   2017/01/05 [QC#16295,ADD]
            // START 2017/09/06 T.Tsuchida [QC#20970,ADD]
            } else if ("NFDL0160Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFDL0160Scrn00_CMN_Download(bizMsg, glblMsg);
            // END 2017/09/06 T.Tsuchida [QC#20970,ADD]
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFDL0160Scrn00_CMN_Submit(NFDL0160CMsg bizMsg, NFDL0160SMsg glblMsg) {
        boolean isSubmit = true;
        search(bizMsg, glblMsg, isSubmit);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFDL0160Scrn00_Search(NFDL0160CMsg bizMsg, NFDL0160SMsg glblMsg) {
        boolean isSubmit = false;
        search(bizMsg, glblMsg, isSubmit);
    }

    /**
     * NWAL1130 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFDL0160_NWAL1130(NFDL0160CMsg bizMsg, NFDL0160SMsg glblMsg) {
        //
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     * @param isSubmit Submit Flag
     */
    private void search(NFDL0160CMsg bizMsg, NFDL0160SMsg glblMsg, boolean isSubmit) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NFDL0160Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {
            if (!isSubmit) {
                bizMsg.setMessageInfo(NZZM0000E);
            }

        } else {

            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());

            } else {

                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }
            EZDMsg.copy(glblMsg, null, bizMsg, null);
        }
    }

    // START 2017/01/05 [QC#16925,ADD]
    /**
     * doProcess_NFDL0160Scrn00_InsertRow
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void doProcess_NFDL0160Scrn00_InsertRow(NFDL0160CMsg bizMsg, NFDL0160SMsg glblMsg) {

        int rowNum = bizMsg.A.getValidCount();

        String defaultArAdjTpCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_NM_NFDL0160_AR_ADJ_TP_CD, getGlobalCompanyCode());
        String defaultArAdjTpDescTxt = NFDL0160CommonLogic.getArAdjTpDescTxtByCd(defaultArAdjTpCd, getGlobalCompanyCode());

        if (ZYPCommonFunc.hasValue(defaultArAdjTpDescTxt)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(rowNum).arAdjTpCd_A, defaultArAdjTpCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(rowNum).arAdjTpDescTxt_A, defaultArAdjTpDescTxt);
        }
        bizMsg.A.setValidCount(rowNum + 1);
    }
    // END   2017/01/05 [QC#16925,ADD]

    // START 2017/09/06 T.Tsuchida [QC#20970,ADD]
    private void doProcess_NFDL0160Scrn00_CMN_Download(NFDL0160CMsg cMsg, NFDL0160SMsg sMsg) {
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");
        writeCsvFile(cMsg, sMsg);
    }

    /**
     * writeCsvFile
     * @param cMsg NFDL0160CMsg
     * @param sMsg NFDL0160SMsg
     */
    private static void writeCsvFile(NFDL0160CMsg cMsg, NFDL0160SMsg sMsg) {

        NFDL0160F00FMsg fMsg = new NFDL0160F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
        // Set don't display column
        fMsg.addExclusionItem("xxChkBox_A");

        //write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        if (sMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            //resultSet -> fMsg
            EZDMsg.copy(sMsg.A.no(i), null, fMsg, null);
            csvOutFile.write();
        }

        csvOutFile.close();
    }

    /**
     * writeCsvFileHeader
     * @param csvOutFile ZYPCSVOutFile
     * @param fMsg NFDL0160F00FMsg
     * @param cMsg NFDL0160CMsg
     */
    private static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NFDL0160F00FMsg fMsg, NFDL0160CMsg cMsg) {
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        final String[] csvHeader = new String[] {
                // START 2018/08/03 T.Ogura [QC#25899,MOD]
//                labelConv.convLabel2i18nLabel(BUSINESS_ID, "Portfolio Pk")
                labelConv.convLabel2i18nLabel(BUSINESS_ID, "Portfolio Code")
                // END   2018/08/03 T.Ogura [QC#25899,MOD]
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Portfolio Name")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Description")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Correspondence Name")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Collector")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Telephone Number")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Fax Number")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "AR WriteOff Activity")
                // START 2018/02/27 J.Kim [QC#20944,DEL]
                // , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Write Off Approval Limit")
                // END 2018/02/27 J.Kim [QC#20944,DEL]
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Credit Analyst Name(Equipment)")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Credit Analyst Name(Service)")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Credit Analyst Name(Supplies)")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Portfolio Next Level")
                , labelConv.convLabel2i18nLabel(BUSINESS_ID, "Status")
        };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
    }
    // END 2017/09/06 T.Tsuchida [QC#20970,ADD]
}
