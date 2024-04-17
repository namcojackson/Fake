/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL2640;

import static business.blap.NFCL2640.constant.NFCL2640Constant.NFCM0799E;
import static business.blap.NFCL2640.constant.NFCL2640Constant.NZZM0002I;
import static business.blap.NFCL2640.constant.NFCL2640Constant.ZZZM9004E;
import static business.blap.NFCL2640.constant.NFCL2640Constant.ZZZM9012E;
import static business.blap.NFCL2640.constant.NFCL2640Constant.ZZZM9013E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL2640.common.NFCL2640CommonLogic;
import business.db.AR_STMT_CTRLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Statement Schedule Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         M.Ohno          Create          N/A
 * 2019/05/15   Fujitsu         H.Ikeda         Update          QC#50140
 *</pre>
 */
public class NFCL2640BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL2640CMsg bizMsg = (NFCL2640CMsg) cMsg;
            NFCL2640SMsg glblMsg = (NFCL2640SMsg) sMsg;

            if ("NFCL2640Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL2640Scrn00_CMN_Submit(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NFCL2640Scrn00_CMN_Submit(NFCL2640CMsg bizMsg, NFCL2640SMsg glblMsg) {

        List<AR_STMT_CTRLTMsg> updateTMsgAry = new ArrayList<AR_STMT_CTRLTMsg>();
        boolean errFlag = false;
        String glblCmpyCd = getGlobalCompanyCode();

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

            AR_STMT_CTRLTMsg updateTMsg = new AR_STMT_CTRLTMsg();
            NFCL2640_ACMsg bizAMsg = bizMsg.A.no(i);
            NFCL2640_BSMsg glblBMsg = glblMsg.B.no(i);
            String arStmtStsCdA = bizAMsg.arStmtStsCd_A1.getValue();
            String arStmtStsCdB = glblBMsg.arStmtStsCd_B1.getValue();
            String lateFeeCalcFlgA = bizAMsg.lateFeeCalcFlg_A1.getValue();
            String lateFeeCalcFlgB = glblBMsg.lateFeeCalcFlg_B1.getValue();

            if (!lateFeeCalcFlgA.equals(lateFeeCalcFlgB)) {
                ZYPEZDItemValueSetter.setValue(updateTMsg.arStmtDt, bizAMsg.arStmtDt_A1);
                ZYPEZDItemValueSetter.setValue(updateTMsg.arStmtStsCd, bizAMsg.arStmtStsCd_A1);
                if (bizAMsg.lateFeeCalcFlg_A1.isClear()) {
                    ZYPEZDItemValueSetter.setValue(updateTMsg.lateFeeCalcFlg, ZYPConstant.FLG_OFF_N);
                } else {
                    ZYPEZDItemValueSetter.setValue(updateTMsg.lateFeeCalcFlg, ZYPConstant.FLG_ON_Y);
                }
                ZYPEZDItemValueSetter.setValue(updateTMsg.arStmtCtrlPk, bizAMsg.arStmtCtrlPk_A1);
                ZYPEZDItemValueSetter.setValue(updateTMsg.ezUpTime, bizAMsg.ezUpTime_A1);
                ZYPEZDItemValueSetter.setValue(updateTMsg.ezUpTimeZone, bizAMsg.ezUpTimeZone_A1);
                ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, glblCmpyCd);
                updateTMsgAry.add(updateTMsg);
                continue;
            }

            if (!arStmtStsCdA.equals(arStmtStsCdB)) {
                if (arStmtStsCdA.equals(AR_STMT_STS.PRINTED)) {
                    bizAMsg.arStmtStsCd_A1.setErrorInfo(1, NFCM0799E);
                    errFlag = true;
                } else {
                    ZYPEZDItemValueSetter.setValue(updateTMsg.arStmtDt, bizAMsg.arStmtDt_A1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.arStmtStsCd, bizAMsg.arStmtStsCd_A1);
                    if (bizAMsg.lateFeeCalcFlg_A1.isClear()) {
                        ZYPEZDItemValueSetter.setValue(updateTMsg.lateFeeCalcFlg, ZYPConstant.FLG_OFF_N);
                    } else {
                        ZYPEZDItemValueSetter.setValue(updateTMsg.lateFeeCalcFlg, ZYPConstant.FLG_ON_Y);
                    }
                    ZYPEZDItemValueSetter.setValue(updateTMsg.arStmtCtrlPk, bizAMsg.arStmtCtrlPk_A1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.ezUpTime, bizAMsg.ezUpTime_A1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.ezUpTimeZone, bizAMsg.ezUpTimeZone_A1);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, glblCmpyCd);
                    updateTMsgAry.add(updateTMsg);
                }
            }
        }

        if (errFlag) {
            return;
        }

        if (updateTMsgAry.size() > 0) {
            List<AR_STMT_CTRLTMsg> outTMsgAry = new ArrayList<AR_STMT_CTRLTMsg>();
            for (AR_STMT_CTRLTMsg inTMsgForUpdate : updateTMsgAry) {
                String updateArStmtStsCd = inTMsgForUpdate.arStmtStsCd.getValue();
                String updateLateFeeCalcFlg = inTMsgForUpdate.lateFeeCalcFlg.getValue();
                String ezUpTime = inTMsgForUpdate.ezUpTime.getValue();
                String ezUpTimeZone = inTMsgForUpdate.ezUpTimeZone.getValue();
                AR_STMT_CTRLTMsg outTMsgForUpdate = (AR_STMT_CTRLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inTMsgForUpdate);
                if (outTMsgForUpdate == null
                        || !ZYPDateUtil.isSameTimeStamp(outTMsgForUpdate.ezUpTime.getValue(), outTMsgForUpdate.ezUpTimeZone.getValue(), ezUpTime, ezUpTimeZone)) {
                    bizMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                ZYPEZDItemValueSetter.setValue(outTMsgForUpdate.arStmtStsCd, updateArStmtStsCd);
                ZYPEZDItemValueSetter.setValue(outTMsgForUpdate.lateFeeCalcFlg, updateLateFeeCalcFlg);
                outTMsgAry.add(outTMsgForUpdate);
            }
            int res = S21FastTBLAccessor.update((AR_STMT_CTRLTMsg[]) outTMsgAry.toArray(new AR_STMT_CTRLTMsg[0]));
            if (res != outTMsgAry.size()) {
                bizMsg.setMessageInfo(ZZZM9013E, new String[] {String.valueOf(res)});
                return;
            }
        }

        if (bizMsg.A.getValidCount() > glblMsg.B.getValidCount()) {

            List<AR_STMT_CTRLTMsg> insertTMsgAry = new ArrayList<AR_STMT_CTRLTMsg>();
            for (int i = glblMsg.B.getValidCount(); i < bizMsg.A.getValidCount(); i++) {
                AR_STMT_CTRLTMsg insertTMsg = new AR_STMT_CTRLTMsg();
                NFCL2640_ACMsg bizAMsgForInsert = bizMsg.A.no(i);
                BigDecimal arStmtCtrlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_STMT_CTRL_SQ);
                ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(insertTMsg.arStmtCtrlPk, arStmtCtrlPk);
                ZYPEZDItemValueSetter.setValue(insertTMsg.arStmtIssCycleCd, bizMsg.arStmtIssCycleCd);
                ZYPEZDItemValueSetter.setValue(insertTMsg.arStmtDt, bizAMsgForInsert.arStmtDt_A1);
                ZYPEZDItemValueSetter.setValue(insertTMsg.arStmtStsCd, AR_STMT_STS.PENDING);
                ZYPEZDItemValueSetter.setValue(insertTMsg.arStmtFnlzFlg, ZYPConstant.FLG_OFF_N);
                if (bizAMsgForInsert.lateFeeCalcFlg_A1.isClear()) {
                    ZYPEZDItemValueSetter.setValue(insertTMsg.lateFeeCalcFlg, ZYPConstant.FLG_OFF_N);
                } else {
                    ZYPEZDItemValueSetter.setValue(insertTMsg.lateFeeCalcFlg, ZYPConstant.FLG_ON_Y);
                }
                // START 2019/05/10 H.Ikeda [QC#50140, ADD]
                ZYPEZDItemValueSetter.setValue(insertTMsg.lateFeeFnlzFlg, ZYPConstant.FLG_OFF_N);
                // END   2019/05/10 H.Ikeda [QC#50140, ADD]
                insertTMsgAry.add(insertTMsg);
            }
            if (insertTMsgAry.size() > 0) {
                int res = S21FastTBLAccessor.insert((AR_STMT_CTRLTMsg[]) insertTMsgAry.toArray(new AR_STMT_CTRLTMsg[0]));
                if (res != insertTMsgAry.size()) {
                    bizMsg.setMessageInfo(ZZZM9012E, new String[] {String.valueOf(res)});
                    return;
                }
            }
        }
        NFCL2640CommonLogic.searchArStmtCtrl(bizMsg, glblMsg);
        bizMsg.setMessageInfo(NZZM0002I);

    }

}
