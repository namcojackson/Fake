/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1310;

import static business.blap.NSAL1310.constant.NSAL1310Constant.*;
import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import business.blap.NSAL1310.common.NSAL1310CommonLogic;
import business.db.DEF_COV_TERM_CONDTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/31   Hitachi         K.Kojima        Create          QC#15136
 * 2016/12/08   Hitachi         K.Kojima        Update          QC#14204
 *</pre>
 */
public class NSAL1310BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1310CMsg cMsg = (NSAL1310CMsg) arg0;
        NSAL1310SMsg sMsg = (NSAL1310SMsg) arg1;
        super.preDoProcess(cMsg, arg1);

        try {
            String screenAplID = cMsg.getScreenAplID();
            cMsg.setCommitSMsg(true);
            if ("NSAL1310Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1310Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL1310Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            } else if ("NSAL1310Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NSAL1310Scrn00_CMN_Submit
     * @param cMsg NSAL1310CMsg
     * @param sMsg NSAL1310SMsg
     */
    private void doProcess_NSAL1310Scrn00_CMN_Submit(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg) {
        // check
        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0456E);
            return;
        }

        if (!NSAL1310CommonLogic.checkDate(cMsg)) {
            return;
        }

        NSAL1310CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        if (!NSAL1310CommonLogic.checkDuplicate(cMsg, sMsg)) {
            return;
        }

        // insert/update
        DEF_COV_TERM_CONDTMsg tMsg = null;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL1310_ASMsg asMsg = sMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(asMsg.defCovTermCondPk_A1)) {
                // insert
                tMsg = new DEF_COV_TERM_CONDTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.defCovTermCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DEF_COV_TERM_COND_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.svcCovTmplTpCd, asMsg.svcCovTmplTpCd_A1);
                ZYPEZDItemValueSetter.setValue(tMsg.svcTermCondAttrbPk, asMsg.svcTermCondAttrbPk_A1);
                NSAL1310CommonLogic.setDefCovTermCondTMsg(asMsg, tMsg);
                S21FastTBLAccessor.insert(tMsg);
                // START 2016/12/08 K.Kojima [QC#14204,ADD]
                if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSAM0032E, new String[] {"DEF_COV_TERM_COND" });
                    return;
                }
                // END 2016/12/08 K.Kojima [QC#14204,ADD]
            } else {
                // update
                if (!NSAL1310CommonLogic.changeCheck(asMsg)) {
                    continue;
                }
                tMsg = new DEF_COV_TERM_CONDTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.defCovTermCondPk, asMsg.defCovTermCondPk_A1);
                S21FastTBLAccessor.findByKeyForUpdate(tMsg);
                if (!ZYPDateUtil.isSameTimeStamp(asMsg.ezUpTime_A1.getValue(), asMsg.ezUpTimeZone_A1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }
                NSAL1310CommonLogic.setDefCovTermCondTMsg(asMsg, tMsg);
                S21FastTBLAccessor.update(tMsg);
                // START 2016/12/08 K.Kojima [QC#14204,ADD]
                if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {"DEF_COV_TERM_COND" });
                    return;
                }
                // END 2016/12/08 K.Kojima [QC#14204,ADD]
            }
        }

        cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
    }
}
