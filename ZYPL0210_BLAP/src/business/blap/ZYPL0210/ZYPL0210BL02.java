package business.blap.ZYPL0210;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZYPL0210.common.ZYPL0210CommonLogic;
import business.blap.ZYPL0210.constant.ZYPL0210Constant;
import business.db.MENU_PROCTMsg;
import business.parts.ZYEC021001PMsg;
import business.parts.ZYEC022001PMsg;
import business.parts.ZYEC023001PMsg;
import business.parts.ZYEC025001PMsg;

import com.canon.cusa.s21.framework.ZYP.csvupload.ZYECSVUploadAuthority;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYECSVUploadConfiguration;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYECSVUploadDownloadFile;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYECSVUploadTemplateHeader;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYECSVUploadDownloadFile.RETURN_CODE;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * ZYPL0210BL02
 * @author A.Hosono CommonTeam
 */
public class ZYPL0210BL02 extends S21BusinessHandler implements ZYPL0210Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        // Dispatch event
        try {
            String screenAplID = cMsg.getScreenAplID();

            if (SCRN_INIT.equals(screenAplID)) {
                // 1.INIT Event
                doProcess_ZYPL0210Scrn00_INIT((ZYPL0210CMsg) cMsg, sMsg);

            } else if (SCRN_SEARCH.equals(screenAplID)) {
                // 2.Search Event
                doProcess_ZYPL0210Scrn00_Search((ZYPL0210CMsg) cMsg, sMsg);

            } else if (SCRN_TEMPLATEDOWNLOAD.equals(screenAplID)) {
                // 3.Download Template Event
                doProcess_ZYPL0210Scrn00_TemplateDownload((ZYPL0210CMsg) cMsg, sMsg);

            } else if (SCRN_PAGENEXT.equals(screenAplID)) {
                // 5.Page Next Event
                doProcess_ZYPL0210Scrn00_PageNext((ZYPL0210CMsg) cMsg, sMsg);

            } else if (SCRN_PAGEPREV.equals(screenAplID)) {
                // 6.Page Prev Event
                doProcess_ZYPL0210Scrn00_PagePrev((ZYPL0210CMsg) cMsg, sMsg);

            } else if (SCRN_DOWNLOAD_ROW.equals(screenAplID)) {
                // 7.Download_Row Event
                doProcess_ZYPL0210Scrn00_Download_Row((ZYPL0210CMsg) cMsg, sMsg, false);

            } else if (SCRN_DOWNLOAD_ERROR_ROW.equals(screenAplID)) {
                // 8.Download_ERROR_Row Event
                doProcess_ZYPL0210Scrn00_Download_Error_Row((ZYPL0210CMsg) cMsg, sMsg);
                
            } else if (SCRN_REFRESH.equals(screenAplID)) {
                // 9.Refresh Event
                doProcess_ZYPL0210Scrn00_Refresh((ZYPL0210CMsg) cMsg, sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);

            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event process.
     * @param cMsg
     * @param sMsg
     */
    protected void doProcess_ZYPL0210Scrn00_INIT(ZYPL0210CMsg cMsg, EZDSMsg sMsg) {
        // 2. get global company code
        String glblCmpyCd = getGlobalCompanyCode();

        // 3.call common business component "ZYEC021001 Upload
        // Authority Acuqision".
        ZYEC021001PMsg inoutMsg = new ZYEC021001PMsg();
        inoutMsg.glblCmpyCd.setValue(glblCmpyCd);
        if (!cMsg.upldCsvId_0H.isClear()) {
            inoutMsg.upldCsvId_0H.setValue(cMsg.upldCsvId_0H.getValue());
        }
        inoutMsg.xxUpldCsvRstCd.setValue(cMsg.xxUpldCsvRstCd.getValue());
        inoutMsg.upldCsvRstBizAppId.setValue(cMsg.upldCsvRstBizAppId.getValue());

        //inoutMsg.upldCsvRstProcNm.setValue(cMsg.upldCsvRstProcNm.getValue());
        if (cMsg.menuProcId.getValue().length() > 0) {
            MENU_PROCTMsg tmsg = new MENU_PROCTMsg();
            tmsg.glblCmpyCd.setValue(glblCmpyCd);
            tmsg.menuProcId.setValue(cMsg.menuProcId.getValue());
            tmsg = (MENU_PROCTMsg) S21FastTBLAccessor.findByKey(tmsg);
            if (tmsg != null && !tmsg.menuProcNm.isClear()) {
                inoutMsg.upldCsvRstProcNm.setValue(tmsg.menuProcNm.getValue());
            }
        }

        ZYECSVUploadAuthority upldAuthor = ZYECSVUploadAuthority.getInstance();
        upldAuthor.exec(inoutMsg);

        EZDMsg.copy(inoutMsg, null, cMsg, null);

        // 4.check result
        if (ZYECSVUploadAuthority.RETURN_CODE.SUCCESS != upldAuthor.convReturnCode(inoutMsg)) {
            String msgId = inoutMsg.xxMsgIdList.no(0).xxMsgId.getValue();

            if ("E".equals(msgId.substring(msgId.length() - 1))) {
                cMsg.upldCsvId_0H.setErrorInfo(1, inoutMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            } else {
                cMsg.upldCsvId_0H.setErrorInfo(2, inoutMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            }
            cMsg.setMessageInfo(inoutMsg.xxMsgIdList.no(0).xxMsgId.getValue());
        }
    }

    /**
     * Search Event process.
     * @param cMsg
     * @param sMsg
     */
    protected void doProcess_ZYPL0210Scrn00_Search(ZYPL0210CMsg cMsg, EZDSMsg sMsg) {

        ZYPL0210CommonLogic.doSearch(cMsg, sMsg);
    }

    /**
     * Template Download Event process.
     * @param cMsg
     * @param sMsg
     */
    protected void doProcess_ZYPL0210Scrn00_TemplateDownload(ZYPL0210CMsg cMsg, EZDSMsg sMsg) {

        // 1.get Global Company Code from user profile Info.
        String glblCmpyCd = getGlobalCompanyCode();

        // 2.
        ZYEC023001PMsg inoutMsg = new ZYEC023001PMsg();
        inoutMsg.glblCmpyCd.setValue(glblCmpyCd);

        String upldCsvId = null;
        if (cMsg.upldCsvId_CT.isClear()) {
            upldCsvId = cMsg.upldCsvId_0H.getValue();
        } else {
            upldCsvId = cMsg.upldCsvId_CT.getValue();
        }

        inoutMsg.upldCsvId.setValue(upldCsvId);
        inoutMsg.xxUpldCsvTempDirTxt.clear();
        inoutMsg.xxUpldCsvTempBaseTxt.setValue(ZYPCSVOutFile.createCSVOutFileNm("UploadTemplate"));
        inoutMsg.xxUpldCsvTempExtnTxt.setValue(TMPL_FILE_EXTENSION);
        ZYECSVUploadTemplateHeader header = ZYECSVUploadTemplateHeader.getInstance();
        header.exec(inoutMsg);

        // 3.
        ZYECSVUploadTemplateHeader.RETURN_CODE returnCode = ZYECSVUploadTemplateHeader.RETURN_CODE.valueFromCode(inoutMsg.getReturnCode());
        if (ZYECSVUploadTemplateHeader.RETURN_CODE.SUCCESS == returnCode) {
            // ------------------------------------------------------------------
            // get Download CSV File Class Name.
            // ------------------------------------------------------------------
            ZYEC022001PMsg configMsg = new ZYEC022001PMsg();
            configMsg.glblCmpyCd.setValue(glblCmpyCd);
            configMsg.upldCsvId.setValue(inoutMsg.upldCsvId.getValue());
            ZYECSVUploadConfiguration config = ZYECSVUploadConfiguration.getInstance();
            config.exec(configMsg);
            if (ZYECSVUploadConfiguration.RETURN_CODE.ERR_NONE_UPLD_CSV_MSTR == config.convReturnCode(configMsg)) {
                cMsg.setMessageInfo(configMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                return;
            }
            cMsg.xxUpldCsvFilePathTxt.setValue(configMsg.dnldCsvFileClsNm.getValue());
            EZDMsg.copy(inoutMsg, null, cMsg, null);
        } else {
            cMsg.setMessageInfo(inoutMsg.xxMsgIdList.no(0).xxMsgId.getValue());
        }

    }

    /**
     * Page Next Event process.
     * @param cMsg
     * @param sMsg
     */
    protected void doProcess_ZYPL0210Scrn00_PageNext(ZYPL0210CMsg cMsg, EZDSMsg sMsg) {
        ZYPL0210CommonLogic.doSearch(cMsg, sMsg);

    }

    /**
     * Page Prev Event process.
     * @param cMsg
     * @param sMsg
     */
    protected void doProcess_ZYPL0210Scrn00_PagePrev(ZYPL0210CMsg cMsg, EZDSMsg sMsg) {
        ZYPL0210CommonLogic.doSearch(cMsg, sMsg);

    }

    /**
     * Download_Row Event process.
     * @param cMsg
     * @param sMsg
     */
    protected void doProcess_ZYPL0210Scrn00_Download_Row(ZYPL0210CMsg cMsg, EZDSMsg sMsg, boolean isDownloadErrorOnly) {
        // 1.get Global Company Code from user profile Info.
        String glblCmpyCd = getGlobalCompanyCode();

        // 2.
        ZYEC025001PMsg inoutMsg = new ZYEC025001PMsg();
        inoutMsg.glblCmpyCd.setValue(glblCmpyCd);
        inoutMsg.upldCsvId.setValue(cMsg.upldCsvId_CT.getValue());
        inoutMsg.upldCsvRqstPk.setValue(cMsg.upldCsvRqstPk_CT.getValue());
        inoutMsg.xxUpldCsvTempDirTxt.clear();
        if(isDownloadErrorOnly){
            inoutMsg.xxDoDnldFlg.setValue("Y");
            inoutMsg.xxUpldCsvTempBaseTxt.setValue(ZYPCSVOutFile.createCSVOutFileNm("Error_UploadFile"));
        }else{
            inoutMsg.xxDoDnldFlg.setValue("N");
            inoutMsg.xxUpldCsvTempBaseTxt.setValue(ZYPCSVOutFile.createCSVOutFileNm("UploadFile"));
        }
        inoutMsg.xxUpldCsvTempExtnTxt.setValue(DNLD_UPLD_FILE_EXTENSION);

        ZYECSVUploadDownloadFile header = ZYECSVUploadDownloadFile.getInstance();
        header.exec(inoutMsg);
        // 3.
        ZYECSVUploadDownloadFile.RETURN_CODE returnCode = ZYECSVUploadDownloadFile.RETURN_CODE.valueFromCode(inoutMsg.getReturnCode());
        if (ZYECSVUploadDownloadFile.RETURN_CODE.SUCCESS == returnCode) {
            EZDMsg.copy(inoutMsg, null, cMsg, null);
            cMsg.xxUpldCsvFilePathTxt.setValue(header.getFileClsName());
//            String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(cMsg.xxFileData_CD.getTempFilePath(), (parts.common.EZDFMsg) header.getFileClsName().getClass().newInstance());
//            cMsg.xxFileData_CD.setTempFilePath(dirName, fileName, fileExtension);
        } else if (ZYECSVUploadDownloadFile.RETURN_CODE.ERR_NOT_FOUND_UPLD_CSV_RQST == returnCode) {
            cMsg.setMessageInfo("ZYEM0008E");
        } else {
            cMsg.setMessageInfo(inoutMsg.xxMsgIdList.no(0).xxMsgId.getValue());
        }

    }

    /**
     * Download_Row Event process.
     * @param cMsg
     * @param sMsg
     */
    protected void doProcess_ZYPL0210Scrn00_Download_Error_Row(ZYPL0210CMsg cMsg, EZDSMsg sMsg) {
        doProcess_ZYPL0210Scrn00_Download_Row(cMsg, sMsg, true);
    }

    /**
     * Refresh Event process.
     * @param cMsg
     * @param sMsg
     */
    protected void doProcess_ZYPL0210Scrn00_Refresh(ZYPL0210CMsg cMsg, EZDSMsg sMsg) {
        ZYPL0210CommonLogic.doSearch(cMsg, sMsg);

    }
}
