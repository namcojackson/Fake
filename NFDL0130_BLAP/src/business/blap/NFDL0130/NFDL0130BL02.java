/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0130;

import static business.blap.NFDL0130.constant.NFDL0130Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFDL0130.common.NFDL0130CommonLogic;
import business.db.CLT_WRK_ITEMTMsgArray;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         K.Kojima        Create          N/A
 * 2016/03/11   Hitachi         K.Kojima        Update          CSA QC#5358
 * 2016/09/12   Hitachi         K.Kojima        Update          QC#12997
 *</pre>
 */
public class NFDL0130BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            String globalCompanyCode = getGlobalCompanyCode();

            if ("NFDL0130_INIT".equals(screenAplID)) {
                // START 2016/03/11 K.Kojima [QC#5358,MOD]
                // doProcess_NFDL0130_INIT((NFDL0130CMsg) cMsg,
                // globalCompanyCode);
                doProcess_NFDL0130_INIT((NFDL0130CMsg) cMsg, globalCompanyCode, true);
                // END 2016/03/11 K.Kojima [QC#5358,MOD]
            } else if ("NFDL0130Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NFDL0130Scrn00_InsertRow((NFDL0130CMsg) cMsg);
            } else if ("NFDL0130Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NFDL0130Scrn00_DeleteRow((NFDL0130CMsg) cMsg);
            } else if ("NFDL0130Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0130Scrn00_CMN_Submit((NFDL0130CMsg) cMsg, globalCompanyCode);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    // START 2016/03/11 K.Kojima [QC#5358,MOD]
    // private void doProcess_NFDL0130_INIT(NFDL0130CMsg cMsg, String
    // globalCompanyCode) {
    private void doProcess_NFDL0130_INIT(NFDL0130CMsg cMsg, String globalCompanyCode, boolean notFoundMsgFlg) {
        // END 2016/03/11 K.Kojima [QC#5358,MOD]
        cMsg.A.clear();
        cMsg.D.clear();
        cMsg.N.clear();
        cMsg.A.setValidCount(0);
        cMsg.D.setValidCount(0);
        cMsg.N.setValidCount(0);

        setValue(cMsg.glblCmpyCd, globalCompanyCode);

        NFDL0130Query.getInstance().getPulldownList(cMsg);

        CLT_WRK_ITEMTMsgArray tMsgResult = NFDL0130CommonLogic.getSearchData(globalCompanyCode);
        if (tMsgResult.length() != 0) {
            int queryResCnt = tMsgResult.length();
            if (queryResCnt > cMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
            setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);

            for (int numC = 0; numC < tMsgResult.length(); numC++) {
                for (int numP = 0; numP < cMsg.N.getValidCount(); numP++) {
                    setValue(cMsg.A.no(numC).cltWrkTpCd_CD.no(numP), cMsg.N.no(numP).cltWrkTpCd_N);
                    setValue(cMsg.A.no(numC).cltWrkTpNm_SC.no(numP), cMsg.N.no(numP).cltWrkTpNm_N);
                }
                cMsg.A.setValidCount(cMsg.A.getValidCount() + 1);
                setValue(cMsg.A.no(numC).cltWrkItemCd_A, tMsgResult.no(numC).cltWrkItemCd);
                setValue(cMsg.A.no(numC).cltWrkItemNm_A, tMsgResult.no(numC).cltWrkItemNm);
                setValue(cMsg.A.no(numC).cltWrkTpCd_SV, tMsgResult.no(numC).cltWrkTpCd);
                setValue(cMsg.A.no(numC).cltWrkCatgCd_A, tMsgResult.no(numC).cltWrkCatgCd);
                setValue(cMsg.A.no(numC).cltWrkCatgNm_A, tMsgResult.no(numC).cltWrkCatgNm);
                // START 2016/09/12 K.Kojima [QC#12997,DEL]
                // setValue(cMsg.A.no(numC).cltWrkPrtyCd_A,
                // tMsgResult.no(numC).cltWrkPrtyCd);
                // setValue(cMsg.A.no(numC).cltWrkPrtyNm_A,
                // tMsgResult.no(numC).cltWrkPrtyNm);
                // END 2016/09/12 K.Kojima [QC#12997,DEL]
                setValue(cMsg.A.no(numC).cltWrkWaitDaysAot_A, tMsgResult.no(numC).cltWrkWaitDaysAot);
                setValue(cMsg.A.no(numC).cltWrkEsclWaitDaysAot_A, tMsgResult.no(numC).cltWrkEsclWaitDaysAot);
                setValue(cMsg.A.no(numC).cltWrkOptItemFlg_A, tMsgResult.no(numC).cltWrkOptItemFlg);
                setValue(cMsg.A.no(numC).cltWrkEsclFlg_A, tMsgResult.no(numC).cltWrkEsclFlg);
                setValue(cMsg.A.no(numC).cltWrkNtfyFlg_A, tMsgResult.no(numC).cltWrkNtfyFlg);
                setValue(cMsg.A.no(numC).cltWrkDescTxt_A, tMsgResult.no(numC).cltWrkDescTxt);
                setValue(cMsg.A.no(numC).ezUpTime_A, tMsgResult.no(numC).ezUpTime);
                setValue(cMsg.A.no(numC).ezUpTimeZone_A, tMsgResult.no(numC).ezUpTimeZone);
                setValue(cMsg.A.no(numC).xxTpCd_A, XX_TP_CD_UPD);
                if (cMsg.A.getValidCount() == cMsg.A.length()) {
                    break;
                }
            }
            // START 2016/03/11 K.Kojima [QC#5358,MOD]
            // } else {
        } else if (notFoundMsgFlg == true) {
            // END 2016/03/11 K.Kojima [QC#5358,MOD]
            cMsg.setMessageInfo(NZZM0000E);
        }
    }

    private void doProcess_NFDL0130Scrn00_InsertRow(NFDL0130CMsg cMsg) {
        cMsg.A.setValidCount(cMsg.A.getValidCount() + 1);
        cMsg.A.no(cMsg.A.getValidCount() - 1).clear();
        cMsg.A.no(cMsg.A.getValidCount() - 1).xxTpCd_A.setValue(XX_TP_CD_INS);
        for (int numP = 0; numP < cMsg.N.getValidCount(); numP++) {
            setValue(cMsg.A.no(cMsg.A.getValidCount() - 1).cltWrkTpCd_CD.no(numP), cMsg.N.no(numP).cltWrkTpCd_N);
            setValue(cMsg.A.no(cMsg.A.getValidCount() - 1).cltWrkTpNm_SC.no(numP), cMsg.N.no(numP).cltWrkTpNm_N);
        }
        if (cMsg.A.getValidCount() == 1) {
            setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);
        }
    }

    private void doProcess_NFDL0130Scrn00_DeleteRow(NFDL0130CMsg cMsg) {
        int deletePos = cMsg.xxRadioBtn_A.getValueInt();

        if (cMsg.A.no(deletePos).xxTpCd_A.getValue().equals(XX_TP_CD_UPD)) {
            cMsg.D.setValidCount(cMsg.D.getValidCount() + 1);
            setValue(cMsg.D.no(cMsg.D.getValidCount() - 1).cltWrkItemCd_D, cMsg.A.no(deletePos).cltWrkItemCd_A);
            setValue(cMsg.D.no(cMsg.D.getValidCount() - 1).ezUpTime_D, cMsg.A.no(deletePos).ezUpTime_A);
            setValue(cMsg.D.no(cMsg.D.getValidCount() - 1).ezUpTimeZone_D, cMsg.A.no(deletePos).ezUpTimeZone_A);
            cMsg.D.no(cMsg.D.getValidCount() - 1).xxTpCd_D.setValue(XX_TP_CD_DEL);
        }

        for (int num = deletePos + 1; num < cMsg.A.getValidCount(); num++) {
            EZDMsg.copy(cMsg.A.no(num), null, cMsg.A.no(num - 1), null);
        }
        cMsg.A.no(cMsg.A.getValidCount() - 1).clear();
        cMsg.A.setValidCount(cMsg.A.getValidCount() - 1);
        if (cMsg.A.getValidCount() == deletePos) {
            setValue(cMsg.xxRadioBtn_A, new BigDecimal(deletePos - 1));
        }
    }

    private void doProcess_NFDL0130Scrn00_CMN_Submit(NFDL0130CMsg cMsg, String globalCompanyCode) {
        // START 2016/03/11 K.Kojima [QC#5358,DEL]
        // doProcess_NFDL0130_INIT((NFDL0130CMsg) cMsg,
        // globalCompanyCode);
        // END 2016/03/11 K.Kojima [QC#5358,DEL]
        doProcess_NFDL0130_INIT((NFDL0130CMsg) cMsg, globalCompanyCode, false);
    }
}
