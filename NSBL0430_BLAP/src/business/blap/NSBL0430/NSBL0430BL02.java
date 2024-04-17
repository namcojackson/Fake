/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0430;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import static business.blap.NSBL0430.constant.NSBL0430Constant.*;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0430.common.NSBL0430CommonLogic;
import business.file.NSBL0430F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
/**
 *<pre>
 * Mods Serial# Assignment
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/28   Hitachi         O.Okuma         Create          N/A
 * 2016/04/18   Hitachi         M.Gotou         Update          QC#3425
 * 2016/07/12   Hitachi         O.Okuma         Update          QC#11678
 * 2017/10/26   Hitachi         U.Kim           update          QC#21797
 *</pre>
 */
public class NSBL0430BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0430CMsg cMsg = (NSBL0430CMsg) arg0;
        NSBL0430SMsg sMsg = (NSBL0430SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0430_INIT".equals(screenAplID)) {
                doProcess_NSBL0430_INIT(cMsg, sMsg);
            // add start 2016/04/18 CSA Defect#3425
            } else if ("NSBL0430Scrn00_Filter".equals(screenAplID)) {
                doProcess_NSBL0430Scrn00_Filter(cMsg, sMsg);
            // add end 2016/04/18 CSA Defect#3425
            // add start 2016/07/12 CSA Defect#11678
            } else if ("NSBL0430Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSBL0430_INIT(cMsg, sMsg);
            // add start 2016/07/12 CSA Defect#11678
            } else if ("NSBL0430Scrn00_Add".equals(screenAplID)) {
                doProcess_NSBL0430Scrn00_Add(cMsg, sMsg);
            } else if ("NSBL0430Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0430Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSBL0430Scrn00_Delete".equals(screenAplID)) {
                doProcess_NSBL0430Scrn00_Delete(cMsg, sMsg);
            } else if ("NSBL0430Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0430Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSBL0430Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0430Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSBL0430Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSBL0430Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NSBL0430Scrn00_TemplateDownload".equals(screenAplID)) {
                doProcess_NSBL0430Scrn00_TemplateDownload(cMsg, sMsg);
            } else if ("NSBL0430Scrn00_Upload".equals(screenAplID)) {
                doProcess_NSBL0430Scrn00_Upload(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0430_INIT(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        NSBL0430CommonLogic.clearMsg(cMsg, sMsg);

        NSBL0430CommonLogic.createPullDown(cMsg);
        NSBL0430CommonLogic.setInitParams(cMsg, sMsg);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        NSBL0430CommonLogic.getSearchData(cMsg, sMsg);

        NSBL0430CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    // add start 2016/04/18 CSA Defect#3425
    private void doProcess_NSBL0430Scrn00_Filter(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        String mdseCd_F = cMsg.mdseCd_F.getValue();
        NSBL0430CommonLogic.clearMsg(cMsg, sMsg);
        cMsg.mdseCd_F.setValue(mdseCd_F);

        NSBL0430CommonLogic.createPullDown(cMsg);
        NSBL0430CommonLogic.setInitParams(cMsg, sMsg);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        NSBL0430CommonLogic.getSearchData(cMsg, sMsg);

        NSBL0430CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }
    // add end 2016/04/18 CSA Defect#3425

    private void doProcess_NSBL0430Scrn00_Add(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        cMsg.setCommitSMsg(true);
        NSBL0430CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        if (!NSBL0430CommonLogic.addNewMdseCdRow(cMsg, sMsg)) {
            return;
        }
        cMsg.mdseCd.clear();
        NSBL0430CommonLogic.findPage(cMsg, sMsg);
    }

    private void doProcess_NSBL0430Scrn00_CMN_Submit(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        doProcess_NSBL0430_INIT(cMsg, sMsg);
    }

    private void doProcess_NSBL0430Scrn00_Delete(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        cMsg.setCommitSMsg(true);
        NSBL0430CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSBL0430CommonLogic.deleteRows(cMsg, sMsg);
    }

    private void doProcess_NSBL0430Scrn00_PageNext(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        cMsg.setCommitSMsg(true);

        if (!NSBL0430CommonLogic.checkMdseCd(cMsg)) {
            return;
        }

        NSBL0430CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSBL0430CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0430Scrn00_PagePrev(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        cMsg.setCommitSMsg(true);

        if (!NSBL0430CommonLogic.checkMdseCd(cMsg)) {
            return;
        }

        NSBL0430CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSBL0430CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0430Scrn00_TBLColumnSort(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        cMsg.setCommitSMsg(true);
        if (!NSBL0430CommonLogic.checkMdseCd(cMsg)) {
            return;
        }

        NSBL0430CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Show 1st page
            setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum, new BigDecimal(cMsg.A.getValidCount()));
        }
    }

    private void doProcess_NSBL0430Scrn00_TemplateDownload(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        // create csv file
        cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSBL0430F00FMsg fMsg = new NSBL0430F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        csvOutFile.close();

    }

    private void doProcess_NSBL0430Scrn00_Upload(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        cMsg.setCommitSMsg(true);

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0430CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        String path = cMsg.xxFileData_U.getTempFilePath();
        NSBL0430F00FMsg fMsg = new NSBL0430F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);

        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
            }
            int status =  -1;
            int count = sMsg.A.getValidCount();
            int errLineNum = 0;
            String errCode;

            // do basic check and load to screen(for all csv data)
            while ((status = mappedFile.read()) != 1) {

                errLineNum++;

                errCode = NSBL0430CommonLogic.addNewMdseCdRowUpload(status, cMsg, sMsg, fMsg);
                if (NSBM0058E.equals(errCode)) {
                    break;
                }
                count = sMsg.A.getValidCount();
                setValue(sMsg.A.no(count).mdseCd_A, fMsg.mdseCd);
                setValue(sMsg.A.no(count).svcModFromSerNum_A, fMsg.svcModFromSerNum);
                setValue(sMsg.A.no(count).svcModToSerNum_A, fMsg.svcModToSerNum);

                if (sMsg.A.no(count).autoCratCallFlg_A.isClear()) {
                    setValue(sMsg.A.no(count).autoCratCallFlg_A, fMsg.autoCratCallFlg);
                }
                // START 2017/10/26 U.Kim [QC#21797, MOD]
                // setValue(sMsg.A.no(count).autoCratRfrsTmgCd_A, fMsg.autoCratRfrsTmgCd);
                setValue(sMsg.A.no(count).autoCratRfrsTmgCd_A, NSBL0430CommonLogic.getAutoCratRfrsTmgCd(cMsg, fMsg));
                // START 2017/10/26 U.Kim [QC#21797, MOD]
                sMsg.A.setValidCount(count + 1);
                fMsg.clear();
            }
            if (mappedFile.getReadRecCount() <= 1) {
                cMsg.setMessageInfo(ZYEM0004E);
            }
        } finally {
            // set Paging Data
            NSBL0430CommonLogic.pagenation(cMsg, sMsg, 0);
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

            mappedFile.close();
            cMsg.xxFileData_U.deleteTempFile();
        }

    }

    /**
     * @param writeCsvFileHeader
     * @param fMsg
     * @param cMsg
     */
    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSBL0430F00FMsg fMsg, NSBL0430CMsg cMsg) {
        final String[] csvHeader = new String[] {MDSE_UPD, SER_FROM_UPD, SER_TO_UPD, AUTO_CRE_CALLS_UPD, AUTO_CRE_REF_RATE_UPD };
        csvOutFile.writeHeader(csvHeader);
    }
}
