package business.blap.NSAL9900;

import static business.blap.NSAL9900.constant.NSAL9900Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL9900.common.NSAL9900CommonLogic;
import business.file.NSAL9900F00FMsg;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.leftPad;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Master Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 * 2016/05/10   Hitachi         K.Yamada        Update          CSA QC#7630
 * 2016/06/14   Hitachi         T.Aoyagi        Update          QC#9682
 * 2020/04/07   Hitachi         K.Sakurai       Update          QC#56253
 *</pre>
 */
public class NSAL9900BL02 extends S21BusinessHandler implements ZYPConstant {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL9900CMsg cMsg = (NSAL9900CMsg) arg0;
        NSAL9900SMsg sMsg = (NSAL9900SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL9900_INIT".equals(screenAplID)) {
                doProcess_NSAL9900_INIT(cMsg, sMsg);
            } else if ("NSAL9900Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL9900Scrn00_Search(cMsg, sMsg);
            } else if ("NSAL9900Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL9900Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL9900Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL9900Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL9900Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL9900Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NSAL9900Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NSAL9900Scrn00_InsertRow(cMsg, sMsg);
            } else if ("NSAL9900Scrn00_CopyRow".equals(screenAplID)) {
                doProcess_NSAL9900Scrn00_CopyRow(cMsg, sMsg);
            } else if ("NSAL9900Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NSAL9900Scrn00_DeleteRow(cMsg, sMsg);
            } else if ("NSAL9900Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL9900Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NSAL9900Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL9900Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSAL9900Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL9900Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL9900Scrn00_DownloadTemplate".equals(screenAplID)) {
                doProcess_NSAL9900Scrn00_DownloadTemplate(cMsg, sMsg);
            } else if ("NSAL9900Scrn00_UploadCsv".equals(screenAplID)) {
                doProcess_NSAL9900Scrn00_CMN_UploadCsv(cMsg, sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL9900_INIT(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(sMsg.D);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        if (hasValue(cMsg.tblCd)) {
            NSAL9900CommonLogic.initData(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL9900Scrn00_Search(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.D);
        NSAL9900CommonLogic.getSearchData(cMsg, sMsg);
        NSAL9900CommonLogic.copySMsgToCMsg(cMsg, sMsg);
    }

    private void doProcess_NSAL9900Scrn00_PageNext(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        NSAL9900CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.B);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt());
        NSAL9900CommonLogic.copySMsgToCMsg(cMsg, sMsg);
    }

    private void doProcess_NSAL9900Scrn00_PagePrev(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        NSAL9900CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.B);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.B.length() - 1);
        NSAL9900CommonLogic.copySMsgToCMsg(cMsg, sMsg);
    }

    private void doProcess_NSAL9900Scrn00_TBLColumnSort(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("B".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.B, sMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.B.getValidCount());

            setValue(cMsg.xxPageShowFromNum, BigDecimal.ZERO);
            NSAL9900CommonLogic.copySMsgToCMsg(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL9900Scrn00_InsertRow(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        NSAL9900CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        int addRowIdx = sMsg.B.getValidCount();
        // START 2020/04/06 K.Sakurai [QC#63253, MOD]
        if (addRowIdx + 1 > sMsg.B.length()) {
            cMsg.setMessageInfo(MSG_ID_NZZM0013E);
            return;
        }
        // END 2020/04/06 K.Sakurai [QC#63253, MOD]
        sMsg.B.setValidCount(addRowIdx + 1);
        // add start 2016/05/10 CSA Defect#7630
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {

            String rowNum = "";
            rowNum = STR_XX_ROW_NUM + STR_UNDER_BAR + leftPad(String.valueOf(i), PAD_NUM, STR_ZERO);
            sMsg.B.no(addRowIdx).setValue(rowNum, -1, BigDecimal.valueOf(i));
        }
        // add end 2016/05/10 CSA Defect#7630
        NSAL9900CommonLogic.setDefaultRowValue(sMsg, addRowIdx);

        int lastPage = addRowIdx / cMsg.B.length();
        ZYPTableUtil.clear(cMsg.B);
        cMsg.xxPageShowFromNum.setValue(lastPage * cMsg.B.length());
        NSAL9900CommonLogic.copySMsgToCMsg(cMsg, sMsg);
    }

    private void doProcess_NSAL9900Scrn00_CopyRow(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        NSAL9900CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        int addRowIdx = sMsg.B.getValidCount();
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.B, "xxChkBox", FLG_ON_Y);
        if (selectedRows == null || selectedRows.isEmpty() || selectedRows.size() > 1) {
            cMsg.setMessageInfo(MSG_ID_NZZM0009E);
            return;
        }
        // START 2020/04/06 K.Sakurai [QC#63253, MOD]
        if (addRowIdx + 1 > sMsg.B.length()) {
            cMsg.setMessageInfo(MSG_ID_NZZM0013E);
            return;
        }
        // END 2020/04/06 K.Sakurai [QC#63253, MOD]

        sMsg.B.setValidCount(addRowIdx + 1);
        EZDMsg.copy(sMsg.B.no(selectedRows.get(0)), null, sMsg.B.no(addRowIdx), null);
        sMsg.B.no(addRowIdx).xxChkBox.clear();
        sMsg.B.no(addRowIdx).updateFlg.clear();
        sMsg.B.no(addRowIdx).ezUpTime.clear();
        sMsg.B.no(addRowIdx).ezUpTimeZone.clear();

        int lastPage = addRowIdx / cMsg.B.length();
        ZYPTableUtil.clear(cMsg.B);
        cMsg.xxPageShowFromNum.setValue(lastPage * cMsg.B.length());
        NSAL9900CommonLogic.copySMsgToCMsg(cMsg, sMsg);
    }

    private void doProcess_NSAL9900Scrn00_DeleteRow(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {
        NSAL9900CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        NSAL9900CommonLogic.deleteRowSMsg(cMsg, sMsg);

        // START 2016/06/14 T.Aoyagi [QC#9682, MOD]
//        setValue(cMsg.xxPageShowFromNum, BigDecimal.ZERO);
        int lastPage = 0;
        if (sMsg.B.getValidCount() > 0) {
            lastPage = (sMsg.B.getValidCount() - 1) / cMsg.B.length();
        }
        cMsg.xxPageShowFromNum.setValue(lastPage * cMsg.B.length());
        // END 2016/06/14 T.Aoyagi [QC#9682, MOD]
        NSAL9900CommonLogic.copySMsgToCMsg(cMsg, sMsg);
    }

    private void doProcess_NSAL9900Scrn00_CMN_Download(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        NSAL9900CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(cMsg.tblCd.getValue()), ".csv");
        NSAL9900F00FMsg fMsg = new NSAL9900F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        String[] headerList = NSAL9900CommonLogic.getCsvHeaderList(cMsg);
        csvOutFile.writeHeader(headerList);

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            fMsg.clear();
            for (int j = 0; j < sMsg.C.getValidCount(); j++) {
                if (!hasValue(sMsg.C.no(j).dplySortNum)) {
                    continue;
                }
                String detailItemNm = sMsg.C.no(j).detailItemNm.getValue();
                if (!hasValue(detailItemNm)) {
                    continue;
                }
                String csvItemNm = STR_XX_STRING + STR_UNDER_BAR  + leftPad(String.valueOf(j), PAD_NUM, STR_ZERO);
                if (COL_TP_NUMBER.equals(sMsg.C.no(j).colTpCd.getValue())) {
                    BigDecimal val = sMsg.B.no(i).getValueBigDecimal(detailItemNm, -1);
                    if (hasValue(val)) {
                        fMsg.setValue(csvItemNm, -1, val.toString());
                    }
                } else {
                    fMsg.setValue(csvItemNm, -1, sMsg.B.no(i).getValueString(detailItemNm, -1));
                }
            }
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    private void doProcess_NSAL9900Scrn00_CMN_Reset(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {
        doProcess_NSAL9900_INIT(cMsg, sMsg);
    }

    private void doProcess_NSAL9900Scrn00_CMN_Submit(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {
        doProcess_NSAL9900Scrn00_Search(cMsg, sMsg);
    }

    private void doProcess_NSAL9900Scrn00_DownloadTemplate(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(cMsg.tblCd.getValue()), ".csv");
        NSAL9900F00FMsg fMsg = new NSAL9900F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        String[] headerList = NSAL9900CommonLogic.getCsvHeaderList(cMsg);
        csvOutFile.writeHeader(headerList);
        csvOutFile.close();
    }

    private void doProcess_NSAL9900Scrn00_CMN_UploadCsv(NSAL9900CMsg cMsg, NSAL9900SMsg sMsg) {

        NSAL9900CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        String path = cMsg.xxFileData.getTempFilePath();
        NSAL9900F00FMsg fMsg = new NSAL9900F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        int fromNum = -1;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);

        List<String[]> keyItemList = new ArrayList<String[]>();
        List<String[]> inacItemList = new ArrayList<String[]>();
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            String detailItemNm = cMsg.C.no(i).detailItemNm.getValue();
            String colTpCd = cMsg.C.no(i).colTpCd.getValue();

            if (hasValue(cMsg.C.no(i).dplySortNum) && FLG_ON_Y.equals(cMsg.C.no(i).uniqKeyFlg.getValue())) {
                keyItemList.add(new String[] {detailItemNm, colTpCd });
            }
            if (hasValue(cMsg.C.no(i).dplySortNum) && FLG_ON_Y.equals(cMsg.C.no(i).itemInacFlg.getValue())) {
                inacItemList.add(new String[] {detailItemNm, colTpCd });
            }
        }

        try {
            int status = -1;
            // header read
            NSAL9900CommonLogic.readCsvHeader(cMsg, fMsg, mappedFile);

            // detail read
            while ((status = mappedFile.read()) != 1) {
                if (UPLOAD_ERR_CD <= status) {
                    continue;
                }
                if (sMsg.B.getValidCount() >= sMsg.B.length()) {
                    cMsg.setMessageInfo(MSG_ID_NSAM0214E);
                    break;
                }
                int errIdx = NSAL9900CommonLogic.validationAndCopyToSMsgCsv(cMsg, sMsg, fMsg, keyItemList, inacItemList);
                if (fromNum == -1 && errIdx != -1) {
                    int maxDisplayRows = cMsg.B.length();
                    fromNum = (errIdx / maxDisplayRows) * maxDisplayRows;
                }
                fMsg.clear();
            }

        } finally {
            if (fromNum == -1) {
                fromNum = 0;
            }
            cMsg.xxPageShowFromNum.setValue(fromNum);
            NSAL9900CommonLogic.copySMsgToCMsg(cMsg, sMsg);

            mappedFile.close();
            cMsg.xxFileData.deleteTempFile();
        }
    }
}
