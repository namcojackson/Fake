/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7200;
import static business.blap.NMAL7200.constant.NMAL7200Constant.CSV;
import static business.blap.NMAL7200.constant.NMAL7200Constant.CSV_FILE_NAME;
import static business.blap.NMAL7200.constant.NMAL7200Constant.CSV_HDR;
import static business.blap.NMAL7200.constant.NMAL7200Constant.FIRST_SELECTED_ROW_INDEX;
import static business.blap.NMAL7200.constant.NMAL7200Constant.NMAM0007I;
import static business.blap.NMAL7200.constant.NMAL7200Constant.NZZM0001W;
import static business.blap.NMAL7200.constant.NMAL7200Constant.SCREEN_MAX_ROW_SIZE;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.file.NMAL7200F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7200BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Fujitsu         M.Suzuki        Create          N/A
 * 2016/09/05   Fujitsu         R.Nakamura      Update          QC#8222
 *</pre>
 */
public class NMAL7200BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        // Mod Start 2016/09/05 QC#8222
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7200CMsg bizMsg = (NMAL7200CMsg) cMsg;

            if ("NMAL7200_INIT".equals(screenAplID)) {
                doProcess_NMAL7200_INIT(bizMsg);

            } else if ("NMAL7200Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL7200Scrn00_CMN_Reset(bizMsg);

            } else if ("NMAL7200Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7200Scrn00_CMN_Download(bizMsg);

            } else if ("NMAL7200Scrn00_Search_PrcGrpUge".equals(screenAplID)) {
                doProcess_NMAL7200Scrn00_Search_PrcGrpUge(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
        // Mod End 2016/09/05 QC#8222
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7200_INIT(NMAL7200CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem29Txt)) {
            doProcess_NMAL7200Scrn00_Search_PrcGrpUge(bizMsg);
        }
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7200Scrn00_CMN_Reset(NMAL7200CMsg bizMsg) {
        doProcess_NMAL7200_INIT(bizMsg);
    }

    // Mod Start 2016/09/05 QC#8222
    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7200Scrn00_CMN_Download(NMAL7200CMsg bizMsg) {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV);
        NMAL7200F00FMsg fMsg = new NMAL7200F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        //write header
        csvOutFile.writeHeader(CSV_HDR, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));


        //write contents
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            //sMsg -> fMsg
            EZDMsg.copy(bizMsg.A.no(i), null, fMsg, null);
            csvOutFile.write();
        }

        csvOutFile.close();
    }
    // Mod End 2016/09/05 QC#8222

    /**
     * Search_PrcGrpUge Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7200Scrn00_Search_PrcGrpUge(NMAL7200CMsg bizMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        NMAL7200Query query = NMAL7200Query.getInstance();
        S21SsmEZDResult ssmResult = query.getPriceGroupUsage(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0007I);
            return;
        }


        // Mod Start 2016/09/05 QC#8222
        if (ssmResult.getQueryResultCount() > SCREEN_MAX_ROW_SIZE) {
            bizMsg.setMessageInfo(NZZM0001W);
            bizMsg.A.setValidCount(SCREEN_MAX_ROW_SIZE);
        } else {
            bizMsg.A.setValidCount(ssmResult.getQueryResultCount());
        }
        // Mod End 2016/09/05 QC#8222

        bizMsg.xxRadioBtn.setValue(FIRST_SELECTED_ROW_INDEX);
    }

}
