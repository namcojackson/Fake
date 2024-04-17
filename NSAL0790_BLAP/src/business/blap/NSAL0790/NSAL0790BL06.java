/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0790;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.parts.NSZC054001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC054001.NSZC054001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Fleet Rollup Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Hitachi         A.Kohinata      Create          N/A
 * 2016/01/21   Hitachi         T.Kanasaka      Update          QC3461
 * 2016/01/22   Hitachi         T.Kanasaka      Update          QC3475
 * 2017/03/01   Hitachi         K.Kitachi       Update          QC#17752
 * 2017/03/10   Hitachi         K.Kitachi       Update          QC#17752
 * 2017/06/06   Hitachi         A.Kohinata      Update          QC#18770
 *</pre>
 */
public class NSAL0790BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0790CMsg cMsg = (NSAL0790CMsg) arg0;
        NSAL0790SMsg sMsg = (NSAL0790SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0790Scrn00_ResubmitRollup".equals(screenAplID)) {
                doProcess_NSAL0790Scrn00_ResubmitRollup(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // START 2017/03/10 K.Kitachi [QC#17752, MOD]
    private void doProcess_NSAL0790Scrn00_ResubmitRollup(NSAL0790CMsg cMsg, NSAL0790SMsg sMsg) {

        // add start 2017/06/06 CSA Defect#18770
        BigDecimal notEntryCnt = NSAL0790Query.getInstance().getNotEntryCnt(cMsg);
        if (BigDecimal.ZERO.compareTo(notEntryCnt) != 0) {
            return;
        }
        // add end 2017/06/06 CSA Defect#18770

        List<String> mtrReadDtList = new ArrayList<String>();

        for (int idx = 0; idx < sMsg.A.getValidCount(); idx++) {

            if (!hasValue(sMsg.A.no(idx).fleetCalcProcCd_A)) {
                continue;
            }
            if (mtrReadDtList.contains(sMsg.A.no(idx).mtrReadDt_A.getValue())) {
                continue;
            }
            mtrReadDtList.add(sMsg.A.no(idx).mtrReadDt_A.getValue());

            // Call Preview Billing API
            NSZC054001PMsg pMsg = createNSZC054001PMsg(cMsg, sMsg, idx);
            NSZC054001 api = new NSZC054001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(pMsg)) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                return;
            }

            BigDecimal newFleetReadRollUpPk = null;
            for (int pMsgIdx = 0; pMsgIdx < pMsg.fltRollUpList.getValidCount(); pMsgIdx++) {
                for (int aSMsgIdx = 0; aSMsgIdx < sMsg.A.getValidCount(); aSMsgIdx++) {
                    if (sMsg.A.no(aSMsgIdx).dsContrBllgMtrPk_A.getValue().compareTo(pMsg.fltRollUpList.no(pMsgIdx).dsContrBllgMtrPk.getValue()) != 0) {
                        continue;
                    }
                    newFleetReadRollUpPk = pMsg.fltRollUpList.no(pMsgIdx).fleetReadRollUpPk.getValue();
                    if (!hasValue(newFleetReadRollUpPk)) {
                        continue;
                    }
                    for (int pCMsgIdx = 0; pCMsgIdx < cMsg.P.getValidCount(); pCMsgIdx++) {
                        if (sMsg.A.no(aSMsgIdx).fleetReadRollUpPk_A.getValue().compareTo(cMsg.P.no(pCMsgIdx).fleetReadRollUpPk_P.getValue()) == 0) {
                            setValue(cMsg.P.no(pCMsgIdx).fleetReadRollUpPk_P, newFleetReadRollUpPk);
                        }
                    }
                }
            }
        }
    }

    private NSZC054001PMsg createNSZC054001PMsg(NSAL0790CMsg cMsg, NSAL0790SMsg sMsg, int aSMsgIdx) {
        NSZC054001PMsg pMsg = new NSZC054001PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        setValue(pMsg.mtrReadDt, sMsg.A.no(aSMsgIdx).mtrReadDt_A);
        setValue(pMsg.dsContrPk, cMsg.dsContrPk_H);
        setValue(pMsg.dsContrNum, cMsg.dsContrNum_H);
        return pMsg;
    }
    // END 2017/03/10 K.Kitachi [QC#17752, MOD]
}
