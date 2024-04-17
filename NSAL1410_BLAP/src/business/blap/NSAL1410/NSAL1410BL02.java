/*
 * <Pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1410;

import static business.blap.NSAL1410.constant.NSAL1410Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDSMsg;
import business.blap.NSAL1410.common.NSAL1410CommonLogic;
import business.db.BILL_TO_CUSTTMsg;
import business.file.NSAL1410F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Contract Branch Rep Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/07   Hitachi         A.Kohinata      Create          QC#60080
 *</pre>
 */
public class NSAL1410BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1410CMsg cMsg = (NSAL1410CMsg) arg0;
        NSAL1410SMsg sMsg = (NSAL1410SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1410_INIT".equals(screenAplID)) {
                doProcess_NSAL1410_INIT(cMsg, sMsg);
            } else if ("NSAL1410Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1410Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSAL1410Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL1410Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NSAL1410Scrn00_ImportFile".equals(screenAplID)) {
                doProcess_NSAL1410Scrn00_ImportFile(cMsg, sMsg);
            } else if ("NSAL1410Scrn00_OnChangeRep".equals(screenAplID)) {
                doProcess_NSAL1410Scrn00_OnChangeRep(cMsg, sMsg);
            } else if ("NSAL1410Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL1410Scrn00_PageJump(cMsg, sMsg);
            } else if ("NSAL1410Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1410Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1410Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1410Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL1410Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1410Scrn00_Search(cMsg, sMsg);
            } else if ("NSAL1410Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL1410Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NSAL1410Scrn00_Upload".equals(screenAplID)) {
                doProcess_NSAL1410Scrn00_Upload(cMsg, sMsg);
            } else if ("NSAL1410_NMAL6760".equals(screenAplID)) {
                doProcess_NSAL1410_NMAL6760(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1410_INIT(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        setValue(cMsg.glblCmpyCd, S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
    }

    private void doProcess_NSAL1410Scrn00_CMN_Clear(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        doProcess_NSAL1410_INIT(cMsg, sMsg);
    }

    private void doProcess_NSAL1410Scrn00_CMN_Download(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        NSAL1410CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSAL1410F00FMsg fMsg = new NSAL1410F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        // write detail
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(fMsg.dsContrNum_A, sMsg.A.no(i).dsContrNum_A);
            setValue(fMsg.billToCustCd_A, sMsg.A.no(i).billToCustCd_A);
            setValue(fMsg.locNm_A, sMsg.A.no(i).locNm_A);
            setValue(fMsg.svcContrBrCd_A, sMsg.A.no(i).svcContrBrCd_A);
            setValue(fMsg.xxGenlFldAreaTxt_A, sMsg.A.no(i).xxGenlFldAreaTxt_A);
            setValue(fMsg.psnCd_A1, sMsg.A.no(i).psnCd_A1);
            setValue(fMsg.xxPsnNm_A1, sMsg.A.no(i).xxPsnNm_A1);
            setValue(fMsg.psnCd_A2, sMsg.A.no(i).psnCd_A2);
            setValue(fMsg.xxPsnNm_A2, sMsg.A.no(i).xxPsnNm_A2);
            setValue(fMsg.vldMsgTxt_A, sMsg.A.no(i).vldMsgTxt_A);
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    private void doProcess_NSAL1410Scrn00_ImportFile(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSAL1410F00FMsg fMsg = new NSAL1410F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        csvOutFile.close();
    }

    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL1410F00FMsg fMsg, NSAL1410CMsg cMsg) {
        final String[] csvHeader = new String[] {"Contr#", "Bill To Code", "Bill To Name", "Branch#", "Branch Name", "Branch Rep Code", "Branch Rep Name", "New Branch Rep Code", "New Branch Rep Name", "Process Message" };
        csvOutFile.writeHeader(csvHeader);
    }

    private void doProcess_NSAL1410Scrn00_OnChangeRep(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        int index = cMsg.xxRowNum.getValueInt();
        if (index >= 0) {
            cMsg.A.no(index).xxPsnNm_A2.clear();
            if (hasValue(cMsg.A.no(index).psnCd_A2)) {
                S21SsmEZDResult ssmResult = NSAL1410Query.getInstance().getPsnInfo(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), cMsg.A.no(index).psnCd_A2.getValue());
                if (ssmResult.isCodeNormal()) {
                    Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
                    setValue(cMsg.A.no(index).xxPsnNm_A2, (String) resultMap.get("XX_PSN_NM"));
                }
            }
        }
    }

    private void doProcess_NSAL1410Scrn00_PageJump(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        NSAL1410CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int pageFromIndex = (cMsg.xxPageShowCurNum.getValueInt() - 1) * cMsg.A.length();
        NSAL1410CommonLogic.pagenation(cMsg, sMsg, pageFromIndex);
    }

    private void doProcess_NSAL1410Scrn00_PageNext(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        NSAL1410CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int pageFromIndex = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL1410CommonLogic.pagenation(cMsg, sMsg, pageFromIndex);
    }

    private void doProcess_NSAL1410Scrn00_PagePrev(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        NSAL1410CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int pageFromIndex = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL1410CommonLogic.pagenation(cMsg, sMsg, pageFromIndex);
    }

    private void doProcess_NSAL1410Scrn00_Search(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        cMsg.setCommitSMsg(true);

        NSAL1410CommonLogic.getSearchData(cMsg, sMsg);

        NSAL1410CommonLogic.pagenation(cMsg, sMsg, 0);
    }

    private void doProcess_NSAL1410Scrn00_TBLColumnSort(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        NSAL1410CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            NSAL1410CommonLogic.pagenation(cMsg, sMsg, 0);
        }
    }

    private void doProcess_NSAL1410Scrn00_Upload(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        cMsg.setCommitSMsg(true);

        NSAL1410F00FMsg fMsg = new NSAL1410F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        String excelPath = cMsg.xxFileData_U.getTempFilePath();
        String path = ZYPExcelUtil.excelToCsvFile(excelPath);
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);

        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
            }
            int status = -1;
            int index = 0;

            while ((status = mappedFile.read()) != 1) {
                if (index >= sMsg.A.length()) {
                    cMsg.setMessageInfo(NZZM0001W);
                    break;
                }
                if (!validateAndCopyToSMsg_UPLOAD(cMsg, sMsg, fMsg, status, index)) {
                    return;
                }
                index++;
                fMsg.clear();
            }
            sMsg.A.setValidCount(index);

            if (!hasValue(cMsg.getMessageCode())) {
                if (index == 0) {
                    cMsg.setMessageInfo(ZYEM0004E);
                } else {
                    cMsg.setMessageInfo(NZZM0002I);
                }
            }
            NSAL1410CommonLogic.pagenation(cMsg, sMsg, 0);
        } finally {
            mappedFile.close();
            cMsg.xxFileData_U.deleteTempFile();
            ZYPExcelUtil.deleteFile(path);
        }
    }

    private boolean validateAndCopyToSMsg_UPLOAD(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg, NSAL1410F00FMsg fMsg, int status, int index) {
        int uploadCount = index + 1;
        int errCol1 = status - 1000;
        int errCol2 = status - 2000;

        if (status == 1000) {
            cMsg.setMessageInfo(NSAM0208E);
            return false;
            // Contr#
        } else if (errCol1 == 1) {
            cMsg.setMessageInfo(NSAM0209E, new String[] {"Contr#(Line=" + String.valueOf(uploadCount) + ")" });
            return false;
        } else if (errCol2 == 1) {
            cMsg.setMessageInfo(NSAM0210E, new String[] {"Contr#(Line=" + String.valueOf(uploadCount) + ")" });
            return false;
            // New Branch Rep Code
        } else if (errCol1 == 8) {
            cMsg.setMessageInfo(NSAM0209E, new String[] {"New Branch Rep Code(Line=" + String.valueOf(uploadCount) + ")" });
            return false;
        } else if (errCol2 == 8) {
            cMsg.setMessageInfo(NSAM0210E, new String[] {"New Branch Rep Code(Line=" + String.valueOf(uploadCount) + ")" });
            return false;
        }

        setValue(sMsg.A.no(index).dsContrNum_A, fMsg.dsContrNum_A);
        if (hasValue(fMsg.dsContrNum_A)) {
            S21SsmEZDResult ssmResult = NSAL1410Query.getInstance().getContrInfo(cMsg.glblCmpyCd.getValue(), fMsg.dsContrNum_A.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
                setValue(sMsg.A.no(index).dsContrPk_A, (BigDecimal) resultMap.get("DS_CONTR_PK"));
                setValue(sMsg.A.no(index).billToCustCd_A, (String) resultMap.get("BILL_TO_CUST_CD"));
                setValue(sMsg.A.no(index).locNm_A, (String) resultMap.get("LOC_NM"));
                setValue(sMsg.A.no(index).svcContrBrCd_A, (String) resultMap.get("SVC_CONTR_BR_CD"));
                setValue(sMsg.A.no(index).svcContrBrDescTxt_A, (String) resultMap.get("SVC_CONTR_BR_DESC_TXT"));
                setValue(sMsg.A.no(index).svcLineBizCd_A, (String) resultMap.get("SVC_LINE_BIZ_CD"));
                setValue(sMsg.A.no(index).xxGenlFldAreaTxt_A, (String) resultMap.get("XX_GENL_FLD_AREA_TXT"));
                setValue(sMsg.A.no(index).psnCd_A1, (String) resultMap.get("PSN_CD"));
                setValue(sMsg.A.no(index).xxPsnNm_A1, (String) resultMap.get("XX_PSN_NM"));
                setValue(sMsg.A.no(index).ezUpTime_A, (String) resultMap.get("EZUPTIME"));
                setValue(sMsg.A.no(index).ezUpTimeZone_A, (String) resultMap.get("EZUPTIMEZONE"));
            }
        }
        setValue(sMsg.A.no(index).psnCd_A2, fMsg.psnCd_A2);
        if (hasValue(fMsg.psnCd_A2)) {
            S21SsmEZDResult ssmResult = NSAL1410Query.getInstance().getPsnInfo(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), fMsg.psnCd_A2.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
                setValue(sMsg.A.no(index).xxPsnNm_A2, (String) resultMap.get("XX_PSN_NM"));
            }
        }
        return true;
    }

    private void doProcess_NSAL1410_NMAL6760(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        if (hasValue(cMsg.billToCustCd_H)) {
            BILL_TO_CUSTTMsg tMsg = NSAL1410Query.getInstance().getBillToCust(cMsg.glblCmpyCd.getValue(), cMsg.billToCustCd_H.getValue());
            if (tMsg != null) {
                setValue(cMsg.locNm_H, tMsg.locNm);
            }
        }
    }
}
