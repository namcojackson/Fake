/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1400;

import static business.blap.NSAL1390.constant.NSAL1390Constant.NSAM0031E;
import static business.blap.NSAL1400.common.NSAL1400CommonLogic.*;
import static business.blap.NSAL1400.constant.NSAL1400Constant.*;
import static com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil.isSameTimeStamp;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ACCT_CONTR_ADMIN_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 * NSAL1400 Named Customer Resource setup
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/01   Hitachi         T.Tomita        Create          QC#18362
 *</pre>
 */
public class NSAL1400BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            NSAL1400CMsg bizMsg = (NSAL1400CMsg) cMsg;
            NSAL1400SMsg glblMsg = (NSAL1400SMsg) sMsg;
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1400Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1400Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1400Scrn00_CMN_Submit(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg) {
        int pageFromNum = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        setPagenation(bizMsg, glblMsg, pageFromNum);

        // Delete
        if (!deleteProcess(bizMsg, glblMsg)) {
            return;
        }
        // Inactive
        if (!inactiveProcess(bizMsg, glblMsg)) {
            return;
        }
        // Active
        if (!activeProcess(bizMsg, glblMsg)) {
            return;
        }

        bizMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
    }

    private boolean deleteProcess(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg) {
        ACCT_CONTR_ADMIN_RELNTMsg inMsg;
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            inMsg = NSAL1400Query.getInstance().getAcctContrAdminRelnForPkForUpdate(bizMsg.glblCmpyCd.getValue(), glblMsg.B.no(i).acctContrAdminRelnPk_B.getValue());
            if (inMsg == null || !isSameTimeStamp(glblMsg.B.no(i).ezUpTime_B.getValue(), glblMsg.B.no(i).ezUpTimeZone_B.getValue(), inMsg.ezUpTime.getValue(), inMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(ZZZM9004E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NSAM0033E, new String[] {"Account Contract Admin Relation" });
                return false;
            }
        }
        return true;
    }

    private boolean inactiveProcess(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg) {
        int pageFromNum = 0;
        ACCT_CONTR_ADMIN_RELNTMsg inMsg;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (hasValue(glblMsg.A.no(i).xxChkBox_A2) && FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A2.getValue())) {
                // Active Line
                continue;
            }
            // Inactive Validation
            if (!inactiveValidation(bizMsg, glblMsg.A.no(i))) {
                pageFromNum = (i / bizMsg.A.length()) * bizMsg.A.length();
                pagenation(bizMsg, glblMsg, pageFromNum);
                return false;
            }

            if (hasValue(glblMsg.A.no(i).acctContrAdminRelnPk_A)) {
                // Update
                inMsg = new ACCT_CONTR_ADMIN_RELNTMsg();
                // Change Value
                if (!isChange(bizMsg, glblMsg.A.no(i), inMsg)) {
                    if (inMsg == null) {
                        pageFromNum = (i / bizMsg.A.length()) * bizMsg.A.length();
                        pagenation(bizMsg, glblMsg, pageFromNum);
                        return false;
                    }
                    continue;
                }

                inMsg = NSAL1400Query.getInstance().getAcctContrAdminRelnForPkForUpdate(bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(i).acctContrAdminRelnPk_A.getValue());
                if (inMsg == null || !isSameTimeStamp(glblMsg.A.no(i).ezUpTime_A.getValue(), glblMsg.A.no(i).ezUpTimeZone_A.getValue(), inMsg.ezUpTime.getValue(), inMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(ZZZM9004E);
                    return false;
                }

                // Set Parameters
                setValue(inMsg.svcLineBizCd, glblMsg.A.no(i).svcLineBizCd_A);
                setValue(inMsg.dsAcctNum, glblMsg.A.no(i).dsAcctNum_A);
                setValue(inMsg.contrAdminPsnCd, glblMsg.A.no(i).contrAdminPsnCd_A);
                setValue(inMsg.actvFlg, FLG_OFF_N);
                setValue(inMsg.effFromDt, glblMsg.A.no(i).effFromDt_A);
                setValue(inMsg.effThruDt, glblMsg.A.no(i).effThruDt_A);

                EZDTBLAccessor.update(inMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NSAM0031E, new String[] {"Account Contract Admin Relation" });
                    return false;
                }
            } else {
                // Insert
                inMsg = new ACCT_CONTR_ADMIN_RELNTMsg();
                setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                setValue(inMsg.acctContrAdminRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_CONTR_ADMIN_RELN_SQ));
                setValue(inMsg.svcLineBizCd, glblMsg.A.no(i).svcLineBizCd_A);
                setValue(inMsg.dsAcctNum, glblMsg.A.no(i).dsAcctNum_A);
                setValue(inMsg.contrAdminPsnCd, glblMsg.A.no(i).contrAdminPsnCd_A);
                setValue(inMsg.actvFlg, FLG_OFF_N);
                setValue(inMsg.effFromDt, glblMsg.A.no(i).effFromDt_A);
                setValue(inMsg.effThruDt, glblMsg.A.no(i).effThruDt_A);

                EZDTBLAccessor.insert(inMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NSAM0032E, new String[] {"Account Contract Admin Relation" });
                    return false;
                }
            }
        }
        return true;
    }

    // TODO
    private boolean activeProcess(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg) {
        int pageFromNum = 0;
        ACCT_CONTR_ADMIN_RELNTMsg inMsg;
        // Update
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!hasValue(glblMsg.A.no(i).xxChkBox_A2) || !FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A2.getValue())) {
                // Inactive Line
                continue;
            }
            if (!hasValue(glblMsg.A.no(i).acctContrAdminRelnPk_A)) {
                continue;
            }

            // Active Validation
            if (!activeValidation(bizMsg, glblMsg, i)) {
                pageFromNum = (i / bizMsg.A.length()) * bizMsg.A.length();
                pagenation(bizMsg, glblMsg, pageFromNum);
                return false;
            }

            inMsg = new ACCT_CONTR_ADMIN_RELNTMsg();
            // Change Value
            if (!isChange(bizMsg, glblMsg.A.no(i), inMsg)) {
                if (inMsg == null) {
                    pageFromNum = (i / bizMsg.A.length()) * bizMsg.A.length();
                    pagenation(bizMsg, glblMsg, pageFromNum);
                    return false;
                }
                continue;
            }

            if (!periodValidationToDb(bizMsg, glblMsg.A.no(i))) {
                pageFromNum = (i / bizMsg.A.length()) * bizMsg.A.length();
                pagenation(bizMsg, glblMsg, pageFromNum);
                return false;
            }

            inMsg = NSAL1400Query.getInstance().getAcctContrAdminRelnForPkForUpdate(bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(i).acctContrAdminRelnPk_A.getValue());
            if (inMsg == null || !isSameTimeStamp(glblMsg.A.no(i).ezUpTime_A.getValue(), glblMsg.A.no(i).ezUpTimeZone_A.getValue(), inMsg.ezUpTime.getValue(), inMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(ZZZM9004E);
                return false;
            }

            // Set Parameters
            setValue(inMsg.svcLineBizCd, glblMsg.A.no(i).svcLineBizCd_A);
            setValue(inMsg.dsAcctNum, glblMsg.A.no(i).dsAcctNum_A);
            setValue(inMsg.contrAdminPsnCd, glblMsg.A.no(i).contrAdminPsnCd_A);
            setValue(inMsg.actvFlg, glblMsg.A.no(i).xxChkBox_A2);
            setValue(inMsg.effFromDt, glblMsg.A.no(i).effFromDt_A);
            setValue(inMsg.effThruDt, glblMsg.A.no(i).effThruDt_A);

            EZDTBLAccessor.update(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NSAM0031E, new String[] {"Account Contract Admin Relation" });
                return false;
            }
        }

        // Insert
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!hasValue(glblMsg.A.no(i).xxChkBox_A2) || !FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A2.getValue())) {
                // Inactive Line
                continue;
            }
            if (hasValue(glblMsg.A.no(i).acctContrAdminRelnPk_A)) {
                continue;
            }

            // Active Validation
            if (!activeValidation(bizMsg, glblMsg, i)) {
                pageFromNum = (i / bizMsg.A.length()) * bizMsg.A.length();
                pagenation(bizMsg, glblMsg, pageFromNum);
                return false;
            }

            if (!periodValidationToDb(bizMsg, glblMsg.A.no(i))) {
                pageFromNum = (i / bizMsg.A.length()) * bizMsg.A.length();
                pagenation(bizMsg, glblMsg, pageFromNum);
                return false;
            }

            // Insert
            inMsg = new ACCT_CONTR_ADMIN_RELNTMsg();
            setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            setValue(inMsg.acctContrAdminRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_CONTR_ADMIN_RELN_SQ));
            setValue(inMsg.svcLineBizCd, glblMsg.A.no(i).svcLineBizCd_A);
            setValue(inMsg.dsAcctNum, glblMsg.A.no(i).dsAcctNum_A);
            setValue(inMsg.contrAdminPsnCd, glblMsg.A.no(i).contrAdminPsnCd_A);
            setValue(inMsg.actvFlg, glblMsg.A.no(i).xxChkBox_A2);
            setValue(inMsg.effFromDt, glblMsg.A.no(i).effFromDt_A);
            setValue(inMsg.effThruDt, glblMsg.A.no(i).effThruDt_A);

            EZDTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NSAM0032E, new String[] {"Account Contract Admin Relation" });
                return false;
            }
        }
        return true;
    }

    private boolean inactiveValidation(NSAL1400CMsg bizMsg, NSAL1400_ASMsg lineSMsg) {
        if (!mandatoryValidation(lineSMsg)) {
            return false;
        }

        if (!masterValidation(bizMsg, lineSMsg)) {
            return false;
        }
        return true;
    }

    private boolean activeValidation(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg, int lineNum) {
        if (!mandatoryValidation(glblMsg.A.no(lineNum))) {
            return false;
        }   

        if (!masterValidation(bizMsg, glblMsg.A.no(lineNum))) {
            return false;
        }

        if (!periodValidationToScreen(bizMsg, glblMsg, lineNum)) {
            return false;
        }
        return true;
    }

    private boolean mandatoryValidation(NSAL1400_ASMsg lineSMsg) {
        // LOB
        if (!hasValue(lineSMsg.svcLineBizCd_A)) {
            lineSMsg.svcLineBizCd_A.setErrorInfo(1, NSAM0645E, new String[]{"LOB" });
            return false;
        }
        // Customer
        if (!hasValue(lineSMsg.dsAcctNum_A)) {
            lineSMsg.dsAcctNum_A.setErrorInfo(1, NSAM0645E, new String[]{"Customer" });
            return false;
        }
        // Resource
        if (!hasValue(lineSMsg.contrAdminPsnCd_A)) {
            lineSMsg.contrAdminPsnCd_A.setErrorInfo(1, NSAM0645E, new String[]{"Resource" });
            return false;
        }
        // Start Date
        if (!hasValue(lineSMsg.effFromDt_A)) {
            lineSMsg.effFromDt_A.setErrorInfo(1, NSAM0645E, new String[]{"Start Date" });
            return false;
        }
        return true;
    }

    private boolean masterValidation(NSAL1400CMsg bizMsg, NSAL1400_ASMsg lineSMsg) {
        BigDecimal count = BigDecimal.ZERO;
        // Customer
        count = NSAL1400Query.getInstance().countCustomer(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), lineSMsg.dsAcctNum_A.getValue());
        if (BigDecimal.ZERO.compareTo(count) >= 0) {
            lineSMsg.dsAcctNum_A.setErrorInfo(1, NSAM0011E, new String[] {"Customer" });
            return false;
        }

        // Resource
        count = NSAL1400Query.getInstance().countResource(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), lineSMsg.svcLineBizCd_A.getValue(), lineSMsg.contrAdminPsnCd_A.getValue());
        if (BigDecimal.ZERO.compareTo(count) >= 0) {
            lineSMsg.contrAdminPsnCd_A.setErrorInfo(1, NSAM0011E, new String[] {"Resource" });
            return false;
        }
        return true;
    }

    private boolean periodValidationToScreen(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg, int lineNum) {
        NSAL1400_ASMsg targetLine = glblMsg.A.no(lineNum);
        String targetFrom = targetLine.effFromDt_A.getValue();
        String targetThru = convEffThruDt(targetLine.effThruDt_A.getValue(), bizMsg.slsDt.getValue());
        String chkFrom;
        String chkThru;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (lineNum == i || !hasValue(glblMsg.A.no(i).xxChkBox_A2) || !FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A2.getValue())) {
                continue;
            }

            if (!targetLine.dsAcctNum_A.getValue().equals(glblMsg.A.no(i).dsAcctNm_A.getValue())) {
                continue;
            }

            if (!targetLine.svcLineBizCd_A.getValue().equals(glblMsg.A.no(i).svcLineBizCd_A.getValue())) {
                continue;
            }

            // Period Check
            chkFrom = glblMsg.A.no(i).effFromDt_A.getValue();
            chkThru = convEffThruDt(glblMsg.A.no(i).effThruDt_A.getValue(), bizMsg.slsDt.getValue());
            if (ZYPDateUtil.compare(targetFrom, chkThru) <= 0 && ZYPDateUtil.compare(targetThru, chkFrom) >= 0) {
                targetLine.effFromDt_A.setErrorInfo(1, NSAM0446E);
                targetLine.effThruDt_A.setErrorInfo(1, NSAM0446E);
                return false;
            }
        }
        return true;
    }

    private boolean periodValidationToDb(NSAL1400CMsg bizMsg, NSAL1400_ASMsg lineSMsg) {
        BigDecimal count = NSAL1400Query.getInstance().countDuplicatePeriod(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), lineSMsg);
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            lineSMsg.effFromDt_A.setErrorInfo(1, NSAM0446E);
            lineSMsg.effThruDt_A.setErrorInfo(1, NSAM0446E);
            return false;
        }
        return true;
    }

    private boolean isChange(NSAL1400CMsg bizMsg, NSAL1400_ASMsg lineSMsg, ACCT_CONTR_ADMIN_RELNTMsg inMsg) {
        inMsg = NSAL1400Query.getInstance().getAcctContrAdminRelnForPk(bizMsg.glblCmpyCd.getValue(), lineSMsg.acctContrAdminRelnPk_A.getValue());
        if (inMsg == null) {
            lineSMsg.svcLineBizCd_A.setErrorInfo(1, NSAM0020E);
            lineSMsg.dsAcctNum_A.setErrorInfo(1, NSAM0020E);
            lineSMsg.contrAdminPsnCd_A.setErrorInfo(1, NSAM0020E);
            lineSMsg.effFromDt_A.setErrorInfo(1, NSAM0020E);
            lineSMsg.effThruDt_A.setErrorInfo(1, NSAM0020E);
            return false;
        }

        // SVC_LINE_BIZ_CD
        if (!inMsg.svcLineBizCd.getValue().equals(lineSMsg.svcLineBizCd_A.getValue())) {
            return true;
        }
        // DS_ACCT_NUM
        if (!inMsg.dsAcctNum.getValue().equals(lineSMsg.dsAcctNum_A.getValue())) {
            return true;
        }
        // CONTR_ADMIN_PSN_CD
        if (!inMsg.contrAdminPsnCd.getValue().equals(lineSMsg.contrAdminPsnCd_A.getValue())) {
            return true;
        }
        // ACTV_FLG
        String actvFlg = lineSMsg.xxChkBox_A2.getValue();
        if (!hasValue(actvFlg)) {
            actvFlg = FLG_OFF_N;
        }
        if (!inMsg.actvFlg.getValue().equals(actvFlg)) {
            return true;
        }
        // EFF_FROM_DT
        if (!inMsg.effFromDt.getValue().equals(lineSMsg.effFromDt_A.getValue())) {
            return true;
        }
        // EFF_THRU_DT
        if (!inMsg.effThruDt.getValue().equals(lineSMsg.effThruDt_A.getValue())) {
            return true;
        }
        return false;
    }

    private String convEffThruDt(String effThruDt, String slsDt) {
        if (hasValue(effThruDt)) {
            return effThruDt;
        }
        return slsDt;
    }
}
