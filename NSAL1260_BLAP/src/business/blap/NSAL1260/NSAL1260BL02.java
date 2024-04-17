/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1260;

import static business.blap.NSAL1260.constant.NSAL1260Constant.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDSMsg;
import business.file.NSAL1260F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import business.blap.NSAL1260.common.NSAL1260CommonLogic;

/**
 *<pre>
 * Contract Machine Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         A.Kohinata      Create          N/A
 * 2016/05/30   Hitachi         Y.Takeno        Update          QC#447
 * 2018/08/24   CITS            M.Naito         Update          QC#24021
 *</pre>
 */
public class NSAL1260BL02 extends S21BusinessHandler {

    // START 2018/08/24 M.Naito [QC#24021, ADD]
    /** Bill to Account Map */
    private Map<String, Boolean>  billEligibleMap = new HashMap<String, Boolean>();
    // END 2018/08/24 M.Naito [QC#24021, ADD]

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1260CMsg cMsg = (NSAL1260CMsg) arg0;
        NSAL1260SMsg sMsg = (NSAL1260SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1260_INIT".equals(screenAplID)) {
                doProcess_NSAL1260_INIT(cMsg, sMsg);
            } else if ("NSAL1260Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1260Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSAL1260Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NSAL1260Scrn00_CMN_Close(cMsg, sMsg);
            } else if ("NSAL1260Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL1260Scrn00_PageJump(cMsg, sMsg);
            } else if ("NSAL1260Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1260Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1260Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1260Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL1260Scrn00_Upload".equals(screenAplID)) {
                doProcess_NSAL1260Scrn00_Upload(cMsg, sMsg);
            } else if ("NSAL1260Scrn00_Add_Machines".equals(screenAplID)) {
                doProcess_NSAL1260Scrn00_Add_Machines(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1260_INIT(NSAL1260CMsg cMsg, NSAL1260SMsg sMsg) {

        NSAL1260CommonLogic.clearMsg(cMsg, sMsg);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        NSAL1260CommonLogic.createPullDown(cMsg);

        if (!ZYPCommonFunc.hasValue(cMsg.dsContrPk)) {
            cMsg.setMessageInfo(NSAM0131E, new String[] {"DS_CONTR_PK" });
            setValue(cMsg.xxErrFlg, ZYPConstant.FLG_ON_Y);
            return;
        }

        S21SsmEZDResult result = NSAL1260Query.getInstance().getHeaderInfo(cMsg);
        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"Contract" });
            setValue(cMsg.xxErrFlg, ZYPConstant.FLG_ON_Y);
        }
    }

    private void doProcess_NSAL1260Scrn00_CMN_Clear(NSAL1260CMsg cMsg, NSAL1260SMsg sMsg) {

        NSAL1260CommonLogic.clearMsg(cMsg, sMsg);
    }

    private void doProcess_NSAL1260Scrn00_CMN_Close(NSAL1260CMsg cMsg, NSAL1260SMsg sMsg) {
    }

    private void doProcess_NSAL1260Scrn00_PageJump(NSAL1260CMsg cMsg, NSAL1260SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1260CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = (cMsg.xxPageShowCurNum.getValue().subtract(BigDecimal.ONE)).multiply(new BigDecimal(cMsg.A.length())).intValue();
        NSAL1260CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1260Scrn00_PageNext(NSAL1260CMsg cMsg, NSAL1260SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1260CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL1260CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1260Scrn00_PagePrev(NSAL1260CMsg cMsg, NSAL1260SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1260CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL1260CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1260Scrn00_Upload(NSAL1260CMsg cMsg, NSAL1260SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        String path = cMsg.xxFileData.getTempFilePath();
        NSAL1260F00FMsg fMsg = new NSAL1260F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
            }
            int status = -1;
            int idx = 0;

            // do basic check and load to screen(for all csv data)
            while ((status = mappedFile.read()) != 1) {
                if (!validateAndCopyToSMsg_UPLOAD(status, idx, sMsg.A, fMsg, cMsg)) {
                    if (NSAM0214E.equals(cMsg.getMessageCode())) {
                        break;
                    }
                }
                idx++;
                fMsg.clear();
            }
            sMsg.A.setValidCount(idx);
            cMsg.setCommitSMsg(true);

            if (!hasValue(cMsg.getMessageCode())) {
                cMsg.setMessageInfo(NZZM0002I);
            }

        } finally {
            // set Paging Data
            NSAL1260CommonLogic.pagenation(cMsg, sMsg, 0);

            mappedFile.close();
            cMsg.xxFileData.deleteTempFile();
        }
    }

    private boolean validateAndCopyToSMsg_UPLOAD(int status, int idx, NSAL1260_ASMsgArray asMsgArray, NSAL1260F00FMsg fMsg, NSAL1260CMsg cMsg) {
        if (asMsgArray.length() <= idx) {
            cMsg.setMessageInfo(NSAM0214E);
            return false;
        }
        NSAL1260_ASMsg sMsgLine = asMsgArray.no(idx);

        if (status == 1000) {
            sMsgLine.xxChkBox_AA.setErrorInfo(1, NSAM0208E);
            setValue(sMsgLine.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
            return false;
        } else if (status > 1000 && status < 2000) {
            setErrorInfo(sMsgLine, NSAM0209E, (status - 1000));
            setValue(sMsgLine.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
            return false;
        } else if (status >= 2000) {
            setErrorInfo(sMsgLine, NSAM0210E, (status - 2000));
            setValue(sMsgLine.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
            return false;
        }

        // IB ID, Serial#
        setValue(sMsgLine.svcMachMstrPk_A, fMsg.svcMachMstrPk);
        setValue(sMsgLine.serNum_A, fMsg.serNum);
        if (!hasValue(sMsgLine.svcMachMstrPk_A) && !hasValue(sMsgLine.serNum_A)) {
            sMsgLine.svcMachMstrPk_A.setErrorInfo(1, NSAM0216E, new String[] {"IB ID", "Serial#" });
            sMsgLine.serNum_A.setErrorInfo(1, NSAM0216E, new String[] {"IB ID", "Serial#" });
            setValue(sMsgLine.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
        }

        // Price
        if (hasValue(fMsg.basePrcDealAmt)) {
            setValue(sMsgLine.basePrcDealAmt_A, fMsg.basePrcDealAmt.getValue().setScale(2, RoundingMode.FLOOR));
        } else {
            sMsgLine.basePrcDealAmt_A.setErrorInfo(1, ZZM9000E, new String[] {"Price" });
            setValue(sMsgLine.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
        }

        // Machine Frequency
        if (hasValue(fMsg.bllgCycleDescTxt)) {
            String bllgCycleCd = NSAL1260CommonLogic.getBllgCycleCd(cMsg.glblCmpyCd.getValue(), fMsg.bllgCycleDescTxt.getValue());
            if (hasValue(bllgCycleCd)) {
                setValue(sMsgLine.bllgCycleCd_A, bllgCycleCd);
            } else {
                sMsgLine.bllgCycleCd_A.setErrorInfo(1, NSAM0432E, new String[] {"Machine Frequency" });
                setValue(sMsgLine.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
            }
        } else {
            setValue(sMsgLine.bllgCycleCd_A, cMsg.bllgCycleCd_H);
        }

        // Meter Read Method
        if (hasValue(fMsg.mtrReadMethDescTxt)) {
            String mtrReadMethCd = NSAL1260CommonLogic.getMtrReadMethCd(cMsg.glblCmpyCd.getValue(), fMsg.mtrReadMethDescTxt.getValue());
            if (hasValue(mtrReadMethCd)) {
                setValue(sMsgLine.mtrReadMethCd_A, mtrReadMethCd);
            } else {
                sMsgLine.mtrReadMethCd_A.setErrorInfo(1, NSAM0432E, new String[] {"Meter Read Method" });
                setValue(sMsgLine.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
            }
        }

        if (hasValue(sMsgLine.svcMachMstrPk_A) || hasValue(sMsgLine.serNum_A)) {
            validateMachineInfo(cMsg, sMsgLine);
        }

        if (!ZYPConstant.FLG_ON_Y.equals(sMsgLine.xxErrFlg_A.getValue())) {
            setValue(sMsgLine.xxChkBox_AA, ZYPConstant.CHKBOX_ON_Y);
            setValue(sMsgLine.contrVrsnEffFromDt_A, cMsg.contrVrsnEffFromDt_H);
            setValue(sMsgLine.contrVrsnEffThruDt_A, cMsg.contrVrsnEffThruDt_H);
            setValue(sMsgLine.xxChkBox_AB, ZYPConstant.CHKBOX_ON_Y);
        }

        return true;
    }

    private void validateMachineInfo(NSAL1260CMsg cMsg, NSAL1260_ASMsg sMsgLine) {

        NSAL1260Query query = NSAL1260Query.getInstance();
        S21SsmEZDResult rslt = query.getSvcMachMstr(cMsg, sMsgLine);
        if (!rslt.isCodeNormal()) {
            sMsgLine.svcMachMstrPk_A.setErrorInfo(1, NSAM0045E, new String[] {"Machine" });
            sMsgLine.serNum_A.setErrorInfo(1, NSAM0045E, new String[] {"Machine" });
            setValue(sMsgLine.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
            return;
        }

        Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();
        setValue(sMsgLine.svcMachMstrPk_A, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
        setValue(sMsgLine.serNum_A, (String) rsltMap.get("SER_NUM"));
        setValue(sMsgLine.billToAcctNum_A, (String) rsltMap.get("BILL_TO_ACCT_NUM"));
        setValue(sMsgLine.dsAcctNm_A, (String) rsltMap.get("DS_ACCT_NM"));
        setValue(sMsgLine.svcConfigMstrPk_A, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));

        String svcMachMstrStsCd = (String) rsltMap.get("SVC_MACH_MSTR_STS_CD");
        if (!SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd)) {
            sMsgLine.svcMachMstrPk_A.setErrorInfo(1, NSAM0065E);
            setValue(sMsgLine.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
        }

        // START 2018/08/24 M.Naito [QC#24021, MOD]
//        if (!cMsg.dsAcctNum_H.getValue().equals(sMsgLine.billToAcctNum_A.getValue())) {
        String billToAcctNum = sMsgLine.billToAcctNum_A.getValue();
        if (this.billEligibleMap.containsKey(billToAcctNum)) {
            if (!this.billEligibleMap.get(billToAcctNum)) {
                sMsgLine.billToAcctNum_A.setErrorInfo(1, NSAM0433E, new String[] {"Account#", "Account# of Contract" });
                setValue(sMsgLine.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
            }
        } else {
            if (!NSAL1260CommonLogic.checkAcctReln(cMsg, sMsgLine)) {
                sMsgLine.billToAcctNum_A.setErrorInfo(1, NSAM0433E, new String[] {"Account#", "Account# of Contract" });
                setValue(sMsgLine.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
                this.billEligibleMap.put(billToAcctNum, false);
            } else {
                this.billEligibleMap.put(billToAcctNum, true);
            }
        }
        // END 2018/08/24 M.Naito [QC#24021, MOD]

        if (NSAL1260CommonLogic.existsContr(cMsg, sMsgLine)) {
            sMsgLine.svcMachMstrPk_A.setErrorInfo(1, NSAM0079E, new String[] {"Machine" });
            setValue(sMsgLine.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
        }

        // START 2016/05/30 [QC#447, ADD]
        if (!NSAL1260CommonLogic.checkEOL(cMsg, sMsgLine)) {
            setValue(sMsgLine.xxErrFlg_A, ZYPConstant.FLG_ON_Y);
        }
        // END   2016/05/30 [QC#447, ADD]
    }

    private void setErrorInfo(NSAL1260_ASMsg sMsgLine, String messageId, int errorItemColumn) {

        switch (errorItemColumn) {
            case 1:
                sMsgLine.svcMachMstrPk_A.setErrorInfo(1, messageId, new String[] {"IB ID" });
                break;
            case 2:
                sMsgLine.serNum_A.setErrorInfo(1, messageId, new String[] {"Serial#" });
                break;
            case 3:
                sMsgLine.bllgCycleCd_A.setErrorInfo(1, messageId, new String[] {"Frequency" });
                break;
            case 4:
                sMsgLine.basePrcDealAmt_A.setErrorInfo(1, messageId, new String[] {"Price" });
                break;
            case 5:
                sMsgLine.mtrReadMethCd_A.setErrorInfo(1, messageId, new String[] {"Meter Read Method" });
                break;
            default:
                break;
        }
    }

    private void doProcess_NSAL1260Scrn00_Add_Machines(NSAL1260CMsg cMsg, NSAL1260SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1260CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        NSAL1260CommonLogic.setOutputParam(cMsg, sMsg);
    }
}
