package business.blap.NFCL3080;

import static business.blap.NFCL3080.constant.NFCL3080Constant.DS_INV_MTR_DTL;
import static business.blap.NFCL3080.constant.NFCL3080Constant.NFCM0780E;
import static business.blap.NFCL3080.constant.NFCL3080Constant.NFCM0782E;
import static business.blap.NFCL3080.constant.NFCL3080Constant.NFCM0783E;
import static business.blap.NFCL3080.constant.NFCL3080Constant.NFCM0785E;
import static business.blap.NFCL3080.constant.NFCL3080Constant.NZZM0002I;
import static business.blap.NFCL3080.constant.NFCL3080Constant.NZZM0003E;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_INV_MTR_DTLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NFCL3080BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Fujita        Create          N/A
 * 2017/03/01   Fujitsu         S.Fujita        Update          QC#17132
 * 2018/06/27   Hitachi         E.Kameishi      Update          QC#26896
 * 2020/05/18   Fujitsu         H.Mizukami      Update          QC#56117
 *</pre>
 */
public class NFCL3080BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL3080CMsg bizMsg = (NFCL3080CMsg) cMsg;
            NFCL3080SMsg glblMsg = (NFCL3080SMsg) sMsg;

            if ("NFCL3080Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3080Scrn01_CMN_Submit(bizMsg, glblMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NFCL3080Scrn01_CMN_Submit(NFCL3080CMsg bizMsg, NFCL3080SMsg glblMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());

        if (checkItemValue(bizMsg)) {
            if (!updateMeterDetails(bizMsg)) {
                return;
            }
            if (!insertMeterDetails(bizMsg)) {
                return;
            }
            // The process has been successfully completed.
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    private boolean updateMeterDetails(NFCL3080CMsg bizMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            NFCL3080_ACMsg aCMsg = bizMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aCMsg.dsInvMtrDtlPk_A)) {
                continue;
            }

            DS_INV_MTR_DTLTMsg tMsg = new DS_INV_MTR_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsInvMtrDtlPk, aCMsg.dsInvMtrDtlPk_A.getValue());

            DS_INV_MTR_DTLTMsg dimResult = (DS_INV_MTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

            if (dimResult == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(dimResult.getReturnCode())) {
                bizMsg.setMessageInfo(NFCM0783E, new String[] {DS_INV_MTR_DTL});
                return false;
            }

            if (!ZYPDateUtil.isSameTimeStamp(aCMsg.ezUpTime_A.getValue(), aCMsg.ezUpTimeZone_A.getValue(), dimResult.ezUpTime.getValue(), dimResult.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            BigDecimal amtMtrChrg = calAmtMtrChrg(aCMsg, bizMsg);
            // Start Reading
            ZYPEZDItemValueSetter.setValue(dimResult.prevTotCopyQty, aCMsg.prevTotCopyQty_A);
            // End Reading
            ZYPEZDItemValueSetter.setValue(dimResult.totCopyQty, aCMsg.totCopyQty_A);
            // Test Copies
            ZYPEZDItemValueSetter.setValue(dimResult.testCopyQty, aCMsg.testCopyQty_A);
            // Allowance
            ZYPEZDItemValueSetter.setValue(dimResult.copyInclQty, aCMsg.copyInclQty_A);
            // Rate
            ZYPEZDItemValueSetter.setValue(dimResult.xsMtrAmtRate, aCMsg.xsMtrAmtRate_A);
            // Deal Amount
            ZYPEZDItemValueSetter.setValue(dimResult.mtrChrgDealAmt, amtMtrChrg);
            // Func Amount
            ZYPEZDItemValueSetter.setValue(dimResult.mtrChrgFuncAmt, amtMtrChrg);

            EZDTBLAccessor.update(dimResult);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dimResult.getReturnCode())) {
                bizMsg.setMessageInfo(NFCM0783E, new String[] {DS_INV_MTR_DTL});
                return false;
            }
        }
        return true;
    }

    private boolean insertMeterDetails(NFCL3080CMsg bizMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NFCL3080_ACMsg aCMsg = bizMsg.A.no(i);

            if (!ZYPCommonFunc.hasValue(aCMsg.dsInvMtrDtlPk_A)) {
                DS_INV_MTR_DTLTMsg tMsg = new DS_INV_MTR_DTLTMsg();
                BigDecimal amtMtrChrg = calAmtMtrChrg(aCMsg, bizMsg);

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.dsInvMtrDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_INV_MTR_DTL_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.invNum, aCMsg.invNum_A);
                ZYPEZDItemValueSetter.setValue(tMsg.invBolLineNum, aCMsg.invBolLineNum_A);
                ZYPEZDItemValueSetter.setValue(tMsg.invLineNum, aCMsg.invLineNum_A);
                ZYPEZDItemValueSetter.setValue(tMsg.invLineSubNum, aCMsg.invLineSubNum_A);
                ZYPEZDItemValueSetter.setValue(tMsg.invLineSubTrxNum, aCMsg.invLineSubTrxNum_A);
                ZYPEZDItemValueSetter.setValue(tMsg.bllgPerFromDt, aCMsg.bllgPerFromDt_A);
                ZYPEZDItemValueSetter.setValue(tMsg.bllgPerToDt, aCMsg.bllgPerThruDt_A);
                ZYPEZDItemValueSetter.setValue(tMsg.serNum, aCMsg.serNum_A);
                ZYPEZDItemValueSetter.setValue(tMsg.mtrLbDescTxt, aCMsg.mtrLbDescTxt_A);
                ZYPEZDItemValueSetter.setValue(tMsg.prevTotCopyQty, aCMsg.prevTotCopyQty_A);
                ZYPEZDItemValueSetter.setValue(tMsg.totCopyQty, aCMsg.totCopyQty_A);
                ZYPEZDItemValueSetter.setValue(tMsg.testCopyQty, aCMsg.testCopyQty_A);
                ZYPEZDItemValueSetter.setValue(tMsg.contrMtrMultRate, aCMsg.contrMtrMultRate_A);
                ZYPEZDItemValueSetter.setValue(tMsg.copyInclQty, aCMsg.copyInclQty_A);
                ZYPEZDItemValueSetter.setValue(tMsg.bllgCopyQty, aCMsg.bllgCopyQty_A);
                ZYPEZDItemValueSetter.setValue(tMsg.xsMtrAmtRate, aCMsg.xsMtrAmtRate_A);
                ZYPEZDItemValueSetter.setValue(tMsg.mtrChrgDealAmt, amtMtrChrg);
                ZYPEZDItemValueSetter.setValue(tMsg.mtrChrgFuncAmt, amtMtrChrg);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, aCMsg.shipToCustCd_A);
                ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, aCMsg.ccyCd_A);
                // START 2018/06/27 E.Kameishi [QC#26896,ADD]
                ZYPEZDItemValueSetter.setValue(tMsg.svcContrMtrBllgPk, aCMsg.svcContrMtrBllgPk_A);
                ZYPEZDItemValueSetter.setValue(tMsg.mtrLbCd, aCMsg.mtrLbCd_A);
                // END 2018/06/27 E.Kameishi [QC#26896,ADD]

                EZDTBLAccessor.insert(tMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NFCM0782E, new String[] {DS_INV_MTR_DTL});
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkItemValue(NFCL3080CMsg bizMsg) {

        if (!checkEndReading(bizMsg)) {
            return false;
        }
        if (!checkTestCopies(bizMsg)) {
            return false;
        }
        return true;
    }

    private boolean checkEndReading(NFCL3080CMsg bizMsg) {

        boolean result = true;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            NFCL3080_ACMsg aCMsg = bizMsg.A.no(i);
            if (aCMsg.totCopyQty_A.getValue().compareTo(aCMsg.prevTotCopyQty_A.getValue()) < 0) {
                aCMsg.totCopyQty_A.setErrorInfo(1, NFCM0780E, new String[]{"End Reading", "Start Reading"});
                result = false;
            }
        }
        return result;
    }

    private boolean checkTestCopies(NFCL3080CMsg bizMsg) {

        boolean result = true;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            NFCL3080_ACMsg aCMsg = bizMsg.A.no(i);
            BigDecimal calCopMade = (aCMsg.totCopyQty_A.getValue().subtract(aCMsg.prevTotCopyQty_A.getValue()).subtract(aCMsg.testCopyQty_A.getValue()));

            if (calCopMade.compareTo(BigDecimal.ZERO) < 0) {
                aCMsg.testCopyQty_A.setErrorInfo(1, NFCM0785E);
                result = false;
            }
        }
        return result;
    }

    private BigDecimal calAmtMtrChrg(NFCL3080_ACMsg aCMsg, NFCL3080CMsg bizMsg) {

        BigDecimal calMtrChrg = (aCMsg.totCopyQty_A.getValue().subtract(aCMsg.prevTotCopyQty_A.getValue()).subtract(aCMsg.testCopyQty_A.getValue()).subtract(aCMsg.copyInclQty_A.getValue()));
        // START 2017/03/01 S.Fujita [QC#17132,MOD]
//        BigDecimal calAmtMtrChrg = calMtrChrg.multiply(aCMsg.contrMtrMultRate_A.getValue());
//        BigDecimal calAmtXsMtrChrg = calAmtMtrChrg.multiply(aCMsg.xsMtrAmtRate_A.getValue());
        // START 2020/05/18 [QC#56117,MOD]
        BigDecimal calAmtXsMtrChrg = calMtrChrg.multiply(aCMsg.xsMtrAmtRate_A.getValue()).multiply(aCMsg.contrMtrMultRate_A.getValue());
        // END   2020/05/18 [QC#56117,MOD]
        // END   2017/03/01 S.Fujita [QC#17132,MOD]

        if (calMtrChrg.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        } else {
            return calAmtXsMtrChrg.setScale(2, BigDecimal.ROUND_HALF_UP);

        }
    }
}
