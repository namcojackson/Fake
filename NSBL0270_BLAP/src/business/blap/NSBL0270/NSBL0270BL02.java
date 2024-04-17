/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0270;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSBL0270.constant.NSBL0270Constant.*;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0270.common.NSBL0270CommonLogic;
import business.db.SVC_PRC_SHIFTTMsgArray;
import business.file.NSBL0270F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * Service Pricing Shift Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBL0270BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0270CMsg cMsg = (NSBL0270CMsg) arg0;
        NSBL0270SMsg sMsg = (NSBL0270SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0270_INIT".equals(screenAplID)) {
                doProcess_NSBL0270_INIT(cMsg, sMsg);
            } else if ("NSBL0270Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSBL0270Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NSBL0270Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSBL0270Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSBL0270Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0270Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSBL0270Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NSBL0270Scrn00_DeleteRow(cMsg, sMsg);
            } else if ("NSBL0270Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NSBL0270Scrn00_InsertRow(cMsg, sMsg);
            } else if ("NSBL0270Scrn00_TemplateDownload".equals(screenAplID)) {
                doProcess_NSBL0270Scrn00_TemplateDownload(cMsg, sMsg);
            } else if ("NSBL0270Scrn00_Upload".equals(screenAplID)) {
                doProcess_NSBL0270Scrn00_Upload(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0270_INIT(NSBL0270CMsg cMsg, NSBL0270SMsg sMsg) {

        NSBL0270CommonLogic.clearMsg(cMsg, sMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        NSBL0270CommonLogic.createPullDown(cMsg);
        SVC_PRC_SHIFTTMsgArray resultArray = NSBL0270CommonLogic.getSvcPrcShiftInfo(cMsg);
        NSBL0270CommonLogic.setDetailInfo(cMsg, resultArray);
        EZDMsg.copy(cMsg, null, sMsg, null);
        sMsg.A.setValidCount(cMsg.A.getValidCount());
    }

    private void doProcess_NSBL0270Scrn00_CMN_Download(NSBL0270CMsg cMsg, NSBL0270SMsg sMsg) {

        // create csv file
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        SVC_PRC_SHIFTTMsgArray resultArray = NSBL0270CommonLogic.getSvcPrcShiftInfo(cMsg);
        NSBL0270F00FMsg fMsg = new NSBL0270F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
        writeCsvFileForSvcPrcShift(cMsg, resultArray, fMsg, csvOutFile);

    }

    /**
     * Write csv file
     * @param bizMsg NSBL0270CMsg
     * @param tMsgArray SVC_PRC_SHIFTTMsgArray
     * @param fMsg NSBL0270F00FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @throws SQLException
     */
    private void writeCsvFileForSvcPrcShift(NSBL0270CMsg bizMsg, SVC_PRC_SHIFTTMsgArray tMsgArray, NSBL0270F00FMsg fMsg, ZYPCSVOutFile csvOutFile) {

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, bizMsg);

        // write contents
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            if (tMsgArray.getValidCount() > bizMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }
            // resultset -> fMsg
            setValue(fMsg.svcPrcShiftNum_A, tMsgArray.no(i).svcPrcShiftNum);
            setValue(fMsg.xxChkBox_A1, tMsgArray.no(i).svcPrcShiftActvFlg);
            setValue(fMsg.xxChkBox_A2, tMsgArray.no(i).svcPrcShiftAhsFlg);
            setValue(fMsg.svcLineBizCd_A, tMsgArray.no(i).svcLineBizCd);
            setValue(fMsg.svcPrcShiftDescTxt_A, tMsgArray.no(i).svcPrcShiftDescTxt);
            setValue(fMsg.xxStartDplyTmTxt_A1, NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcMonStartValTxt));
            setValue(fMsg.xxEndDplyTmTxt_A1,   NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcMonEndValTxt));
            setValue(fMsg.xxStartDplyTmTxt_A2, NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcTueStartValTxt));
            setValue(fMsg.xxEndDplyTmTxt_A2,   NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcTueEndValTxt));
            setValue(fMsg.xxStartDplyTmTxt_A3, NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcWedStartValTxt));
            setValue(fMsg.xxEndDplyTmTxt_A3,   NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcWedEndValTxt));
            setValue(fMsg.xxStartDplyTmTxt_A4, NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcThuStartValTxt));
            setValue(fMsg.xxEndDplyTmTxt_A4,   NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcThuEndValTxt));
            setValue(fMsg.xxStartDplyTmTxt_A5, NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcFriStartValTxt));
            setValue(fMsg.xxEndDplyTmTxt_A5,   NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcFriEndValTxt));
            setValue(fMsg.xxStartDplyTmTxt_A6, NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcSatStartValTxt));
            setValue(fMsg.xxEndDplyTmTxt_A6,   NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcSatEndValTxt));
            setValue(fMsg.xxStartDplyTmTxt_A7, NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcSunStartValTxt));
            setValue(fMsg.xxEndDplyTmTxt_A7,   NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcSunEndValTxt));
            setValue(fMsg.xxStartDplyTmTxt_A8, NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcHolStartValTxt));
            setValue(fMsg.xxEndDplyTmTxt_A8,   NSBL0270CommonLogic.formatHHmmss(tMsgArray.no(i).svcPrcHolEndValTxt));

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private void doProcess_NSBL0270Scrn00_CMN_Reset(NSBL0270CMsg cMsg, NSBL0270SMsg sMsg) {

        doProcess_NSBL0270_INIT(cMsg, sMsg);
    }

    private void doProcess_NSBL0270Scrn00_CMN_Submit(NSBL0270CMsg cMsg, NSBL0270SMsg sMsg) {

        doProcess_NSBL0270_INIT(cMsg, sMsg);
    }

    private void doProcess_NSBL0270Scrn00_DeleteRow(NSBL0270CMsg cMsg, NSBL0270SMsg sMsg) {

        List<Integer> deleteRows = new ArrayList<Integer>();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_AD.getValue())) {
                deleteRows.add(i);
            }
        }

        ZYPTableUtil.deleteRows(cMsg.A, deleteRows);
    }

    private void doProcess_NSBL0270Scrn00_InsertRow(NSBL0270CMsg cMsg, NSBL0270SMsg sMsg) {

        int addRowIndex = cMsg.A.getValidCount();
        setValue(cMsg.A.no(cMsg.A.getValidCount()).xxChkBox_A1, ZYPConstant.FLG_ON_Y);
        cMsg.A.setValidCount(addRowIndex + 1);

    }

    private void doProcess_NSBL0270Scrn00_TemplateDownload(NSBL0270CMsg cMsg, NSBL0270SMsg sMsg) {

        // create csv file
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSBL0270F00FMsg fMsg = new NSBL0270F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        csvOutFile.close();
    }

    /**
     * @param writeCsvFileHeader
     * @param fMsg
     * @param cMsg
     */
    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSBL0270F00FMsg fMsg, NSBL0270CMsg cMsg) {
        final String[] csvHeader = new String[] {"Shift#", "Active", "AHS", "Line of Business", "Description"
                , "Monday Start", "Monday End", "Tuesday Start", "Tuesday End", "Wednesday Start", "Wednesday End"
                , "Thursday Start", "Thursday End", "Friday Start", "Friday End", "Saturday Start", "Saturday End"
                , "Sunday Start", "Sunday End", "Holiday Start", "Holiday End"};
        csvOutFile.writeHeader(csvHeader);
    }

    private void doProcess_NSBL0270Scrn00_Upload(NSBL0270CMsg cMsg, NSBL0270SMsg sMsg) {

        String path = cMsg.xxFileData.getTempFilePath();
        NSBL0270F00FMsg fMsg = new NSBL0270F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        String csvFilePath = ZYPExcelUtil.excelToCsvFile(path);
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvFilePath, fMsg, option);

        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
            }

            //do basic check and load to screen(for all csv data)
            while ((mappedFile.read()) != 1) {

                //SVC_PRC_SHIFT duplication check
                if (ZYPCommonFunc.hasValue(fMsg.svcPrcShiftNum_A)) {
                    for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                        if (cMsg.B.no(i).svcPrcShiftNum_B.getValue().equals(fMsg.svcPrcShiftNum_A.getValue())) {
                            //error message duplication
                            cMsg.setMessageInfo(NSAM0465E, new String[] {fMsg.svcPrcShiftNum_A.getValue()});
                            return;
                        }
                    }
                    setValue(cMsg.B.no(cMsg.B.getValidCount()).svcPrcShiftNum_B, fMsg.svcPrcShiftNum_A);
                    cMsg.B.setValidCount(cMsg.B.getValidCount() + 1);
                }
                boolean isNewSvcPrcShiftRow = false;
                int updateSvcPrcShiftRow = -1;
                if (!ZYPCommonFunc.hasValue(fMsg.svcPrcShiftNum_A)) {
                    isNewSvcPrcShiftRow = true;
                } else {
                    for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                        if (fMsg.svcPrcShiftNum_A.getValue().equals(cMsg.A.no(i).svcPrcShiftNum_A.getValue())) {
                            updateSvcPrcShiftRow = i;
                            break;
                        }
                    }
                    if (updateSvcPrcShiftRow == -1) {
                        isNewSvcPrcShiftRow = true;
                    } else {
                        isNewSvcPrcShiftRow = false;
                    }
                }
                if (isNewSvcPrcShiftRow) {
                    if (cMsg.A.getValidCount() == cMsg.A.length()) {
                        //error message 200 Over
                        cMsg.setMessageInfo(NSBM0058E, new String[] {"Shift Num:".concat(fMsg.svcPrcShiftNum_A.getValue()), String.valueOf(cMsg.A.length())});
                        return;
                    }
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxChkBox_A1, fMsg.xxChkBox_A1);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxChkBox_A2, fMsg.xxChkBox_A2);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).svcLineBizCd_A, fMsg.svcLineBizCd_A);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).svcPrcShiftDescTxt_A, fMsg.svcPrcShiftDescTxt_A);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxStartDplyTmTxt_A1, fMsg.xxStartDplyTmTxt_A1);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxEndDplyTmTxt_A1, fMsg.xxEndDplyTmTxt_A1);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxStartDplyTmTxt_A2, fMsg.xxStartDplyTmTxt_A2);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxEndDplyTmTxt_A2, fMsg.xxEndDplyTmTxt_A2);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxStartDplyTmTxt_A3, fMsg.xxStartDplyTmTxt_A3);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxEndDplyTmTxt_A3, fMsg.xxEndDplyTmTxt_A3);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxStartDplyTmTxt_A4, fMsg.xxStartDplyTmTxt_A4);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxEndDplyTmTxt_A4, fMsg.xxEndDplyTmTxt_A4);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxStartDplyTmTxt_A5, fMsg.xxStartDplyTmTxt_A5);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxEndDplyTmTxt_A5, fMsg.xxEndDplyTmTxt_A5);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxStartDplyTmTxt_A6, fMsg.xxStartDplyTmTxt_A6);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxEndDplyTmTxt_A6, fMsg.xxEndDplyTmTxt_A6);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxStartDplyTmTxt_A7, fMsg.xxStartDplyTmTxt_A7);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxEndDplyTmTxt_A7, fMsg.xxEndDplyTmTxt_A7);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxStartDplyTmTxt_A8, fMsg.xxStartDplyTmTxt_A8);
                    setValue(cMsg.A.no(cMsg.A.getValidCount()).xxEndDplyTmTxt_A8, fMsg.xxEndDplyTmTxt_A8);
                    cMsg.A.setValidCount(cMsg.A.getValidCount() + 1);
                } else {
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxChkBox_A1, fMsg.xxChkBox_A1);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxChkBox_A2, fMsg.xxChkBox_A2);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).svcLineBizCd_A, fMsg.svcLineBizCd_A);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).svcPrcShiftDescTxt_A, fMsg.svcPrcShiftDescTxt_A);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxStartDplyTmTxt_A1, fMsg.xxStartDplyTmTxt_A1);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxEndDplyTmTxt_A1, fMsg.xxEndDplyTmTxt_A1);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxStartDplyTmTxt_A2, fMsg.xxStartDplyTmTxt_A2);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxEndDplyTmTxt_A2, fMsg.xxEndDplyTmTxt_A2);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxStartDplyTmTxt_A3, fMsg.xxStartDplyTmTxt_A3);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxEndDplyTmTxt_A3, fMsg.xxEndDplyTmTxt_A3);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxStartDplyTmTxt_A4, fMsg.xxStartDplyTmTxt_A4);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxEndDplyTmTxt_A4, fMsg.xxEndDplyTmTxt_A4);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxStartDplyTmTxt_A5, fMsg.xxStartDplyTmTxt_A5);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxEndDplyTmTxt_A5, fMsg.xxEndDplyTmTxt_A5);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxStartDplyTmTxt_A6, fMsg.xxStartDplyTmTxt_A6);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxEndDplyTmTxt_A6, fMsg.xxEndDplyTmTxt_A6);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxStartDplyTmTxt_A7, fMsg.xxStartDplyTmTxt_A7);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxEndDplyTmTxt_A7, fMsg.xxEndDplyTmTxt_A7);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxStartDplyTmTxt_A8, fMsg.xxStartDplyTmTxt_A8);
                    setValue(cMsg.A.no(updateSvcPrcShiftRow).xxEndDplyTmTxt_A8, fMsg.xxEndDplyTmTxt_A8);
                }
                fMsg.clear();
            }
            if (mappedFile.getReadRecCount() <= 1) {
                cMsg.setMessageInfo(ZYEM0004E);
            }
        } finally {

            mappedFile.close();
            cMsg.xxFileData.deleteTempFile();
            ZYPExcelUtil.deleteFile(csvFilePath);
        }

    }

}
