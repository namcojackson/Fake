/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.ZZPL0110;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZPL0110.common.ZZPL0110CommonLogic;
import business.blap.ZZPL0110.constant.ZZPL0110Constant;
import business.db.EIP_RPT_PROC_LOGTMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/20   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0110BL06 extends S21BusinessHandler {
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (ZZPL0110Constant.EVENT_CMN_SUBMIT_SCRN00.equals(screenAplID)) {
                doProcess_ZZPL0110Scrn00_Submit((ZZPL0110CMsg) cMsg, (ZZPL0110SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_ZZPL0110Scrn00_Submit(ZZPL0110CMsg cMsg, ZZPL0110SMsg sMsg) {
        int tableIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;

        String rptJobSts = null;
        String changeSts = null;

        EIP_RPT_PROC_LOGTMsg conditionTMsg = new EIP_RPT_PROC_LOGTMsg();
        EIP_RPT_PROC_LOGTMsg resultTMsg = new EIP_RPT_PROC_LOGTMsg();

        BigDecimal eipRptProcLogPk = null;
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        // check existence of Global Company Code
        if (!ZZPL0110CommonLogic.checkGlblCmpCd(cMsg)) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {"Global Company Code" });
            return;
        }

        for (int i = tableIndex; i < tableIndex + cMsg.A.getValidCount(); i++) {
            if (i < sMsg.A.getValidCount()) {
                rptJobSts = sMsg.A.no(i).rptJobStsTxt_A.getValue();
                changeSts = cMsg.A.no(i - tableIndex).rptJobStsTxt_CS.getValue();

                // if report job status is not changed, skip.
                if (changeSts.equals(ZZPL0110Constant.RPT_JOB_STS_BLANK) || changeSts.equals(rptJobSts)) {
                    continue;
                }

                eipRptProcLogPk = cMsg.A.no(i - tableIndex).eipRptProcLogPk_A.getValue();
                conditionTMsg.eipRptProcLogPk.setValue(eipRptProcLogPk);
                conditionTMsg.glblCmpyCd.setValue(glblCmpyCd);

                resultTMsg = (EIP_RPT_PROC_LOGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(conditionTMsg);

                if (resultTMsg == null) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    break;
                }

                resultTMsg.rptJobStsTxt.setValue(changeSts);
                EZDTBLAccessor.update(resultTMsg);

                if (!resultTMsg.getReturnCode().equals("0000")) {
                    cMsg.setMessageInfo("ZZZM9013E", new String[] {resultTMsg.getReturnCode() });
                    return;
                }

                sMsg.A.no(i).rptJobStsTxt_A.setValue(changeSts);
                cMsg.A.no(i - tableIndex).rptJobStsTxt_A.setValue(changeSts);

                cMsg.setMessageInfo("ZZZM9003I", new String[] {"Update" });

            } else {
                break;
            }
        }
    }

}
