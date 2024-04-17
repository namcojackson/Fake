/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0440;

import static business.blap.NSBL0440.constant.NSBL0440Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil.isSameTimeStamp;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSBL0440.common.NSBL0440CommonLogic;
import business.db.SVC_MOD_STSTMsg;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MOD_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Mods Machine Level Status
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Hitachi         O.Okuma         Create          N/A
 * 2016/07/14   Hitachi         O.Okuma         Update          QC#11647
 *</pre>
 */
public class NSBL0440BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0440CMsg cMsg = (NSBL0440CMsg) arg0;
        NSBL0440SMsg sMsg = (NSBL0440SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0440Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0440Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0440Scrn00_CMN_Submit(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {

        int rowIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0440CommonLogic.setPagenation(cMsg, sMsg, rowIndex);

        if (!checkInput(sMsg)) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        if (!NSBL0440CommonLogic.checkData(sMsg)) {
            int errPageFromNum = NSBL0440CommonLogic.getErrPageFromNum(cMsg, sMsg);
            NSBL0440CommonLogic.pagenation(cMsg, sMsg, errPageFromNum);
            return;
        }

        // mod start 2016/07/14 CSA Defect#11647
        doSubmit(cMsg, sMsg);
        // mod end 2016/07/14 CSA Defect#11647

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NSBM0005I);
            return;
        }
    }

    private boolean checkInput(NSBL0440SMsg sMsg) {
        if (sMsg.A.getValidCount() == 0) {
            return false;
        }
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                return true;
            }
        }
        return false;
    }

    private SVC_MOD_STSTMsg getsvcModSts(String glblCmpyCd, BigDecimal svcModStsPk) {
        SVC_MOD_STSTMsg prmTMsg = new SVC_MOD_STSTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.svcModStsPk, svcModStsPk);
        return (SVC_MOD_STSTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    private void doSubmit(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                // mod start 2016/07/14 CSA Defect#11647
                if (hasValue(sMsg.A.no(i).svcModStsPk_A)) {
                    updateSvcModSts(cMsg, sMsg.A.no(i));
                } else {
                    if (hasValue(sMsg.A.no(i).svcModOptCd_A) || hasValue(sMsg.A.no(i).svcModOptDt_A) || hasValue(sMsg.A.no(i).svcModNoteTxt_A)) {
                        createSvcModSts(cMsg, sMsg.A.no(i));
                    }
                }
                // mod end 2016/07/14 CSA Defect#11647
            }
        }
    }

    // add start 2016/07/14 CSA Defect#11647
    private void updateSvcModSts(NSBL0440CMsg cMsg, NSBL0440_ASMsg asMsg) {
        SVC_MOD_STSTMsg tMsg = getsvcModSts(cMsg.glblCmpyCd.getValue(), asMsg.svcModStsPk_A.getValue());
        if (tMsg == null) {
            cMsg.setMessageInfo(NSBM0075E, new String[] {TBL_SVCMODSTS, FIELD_SVCMODSTS, asMsg.svcModStsPk_A.getValue().toString() });
            return;
        } else {
            if (!isSameTimeStamp(asMsg.ezUpTime_A.getValue(), asMsg.ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NSBM0075E, new String[] {TBL_SVCMODSTS, FIELD_SVCMODSTS, asMsg.svcModStsPk_A.getValue().toString() });
                return;
            }
            setValue(tMsg.svcModOptCd, asMsg.svcModOptCd_A.getValue());
            setValue(tMsg.svcModOptDt, asMsg.svcModOptDt_A.getValue());
            setValue(tMsg.svcModNoteTxt, asMsg.svcModNoteTxt_A.getValue());

            S21FastTBLAccessor.update(tMsg);

            if (!hasValue(asMsg.svcTaskNum_A) && !hasValue(asMsg.svcModOptCd_A) && !hasValue(asMsg.svcModOptDt_A) && !hasValue(asMsg.svcModNoteTxt_A)) {
                EZDTBLAccessor.logicalRemove(tMsg);
            }

            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {"TBL_SVCMODSTS" });
                return;
            }
        }
    }

    private void createSvcModSts(NSBL0440CMsg cMsg, NSBL0440_ASMsg asMsg) {
        SVC_MOD_STSTMsg tMsg = new SVC_MOD_STSTMsg();

        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(tMsg.svcModStsPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MOD_STS_SQ));
        setValue(tMsg.svcModPk, cMsg.svcModPk.getValue());
        setValue(tMsg.svcModDtlPk, asMsg.svcModDtlPk_A.getValue());
        setValue(tMsg.svcMachMstrPk, asMsg.svcMachMstrPk_A.getValue());
        setValue(tMsg.svcModProcStsCd, SVC_MOD_PROC_STS.OPEN);
        setValue(tMsg.svcModOptCd, asMsg.svcModOptCd_A.getValue());
        setValue(tMsg.svcModOptDt, asMsg.svcModOptDt_A.getValue());
        setValue(tMsg.svcModNoteTxt, asMsg.svcModNoteTxt_A.getValue());

        S21FastTBLAccessor.insert(tMsg);
        if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSBM0164E, new String[] {"TBL_SVCMODSTS" });
            return;
        }
    }
    // add end 2016/07/14 CSA Defect#11647
}
