/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0140;

import static business.blap.NFDL0140.constant.NFDL0140Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0140.common.NFDL0140CommonLogic;
import business.db.CLT_STRGYTMsg;
import business.db.CLT_STRGY_RELN_CUST_TPTMsgArray;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         K.Kojima        Create          N/A
 * 2016/03/11   Hitachi         K.Kojima        Update          CSA QC#5358
 * 2016/08/01   Hitachi         K.Kojima        Update          QC#12493
 *</pre>
 */
public class NFDL0140BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            String globalCompanyCode = getGlobalCompanyCode();

            if ("NFDL0140_INIT".equals(screenAplID)) {
                // START 2016/03/11 K.Kojima [QC#5358,MOD]
                // doProcess_NFDL0140_INIT((NFDL0140CMsg) cMsg,
                // globalCompanyCode);
                doProcess_NFDL0140_INIT((NFDL0140CMsg) cMsg, globalCompanyCode, true);
                // END 2016/03/11 K.Kojima [QC#5358,MOD]
            } else if ("NFDL0140Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NFDL0140Scrn00_InsertRow((NFDL0140CMsg) cMsg);
            } else if ("NFDL0140Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NFDL0140Scrn00_DeleteRow((NFDL0140CMsg) cMsg);
            } else if ("NFDL0140Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0140Scrn00_CMN_Submit((NFDL0140CMsg) cMsg, globalCompanyCode);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    // START 2016/03/11 K.Kojima [QC#5358,MOD]
    // private void doProcess_NFDL0140_INIT(NFDL0140CMsg cMsg, String
    // globalCompanyCode) {
    private void doProcess_NFDL0140_INIT(NFDL0140CMsg cMsg, String globalCompanyCode, boolean notFoundMsgFlg) {
        // END 2016/03/11 K.Kojima [QC#5358,MOD]
        cMsg.A.clear();
        cMsg.D.clear();
        cMsg.N.clear();
        cMsg.A.setValidCount(0);
        cMsg.D.setValidCount(0);
        cMsg.N.setValidCount(0);

        setValue(cMsg.glblCmpyCd, globalCompanyCode);

        NFDL0140Query.getInstance().getPulldownList(cMsg);

        CLT_STRGYTMsg inMsg = new CLT_STRGYTMsg();
        inMsg.glblCmpyCd.setValue(globalCompanyCode);
        inMsg.cltStrgyCd.setValue(cMsg.cltStrgyCd.getValue());
        CLT_STRGYTMsg tMsg = (CLT_STRGYTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tMsg != null) {
            setValue(cMsg.cltStrgyNm, tMsg.cltStrgyNm);
        } else {
            cMsg.setMessageInfo(ZZZM9004E);
        }

        CLT_STRGY_RELN_CUST_TPTMsgArray tMsgResult = NFDL0140CommonLogic.getSearchData(globalCompanyCode, cMsg.cltStrgyCd.getValue());
        if (tMsgResult.length() != 0) {
            int queryResCnt = tMsgResult.length();
            if (queryResCnt > cMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
            setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);

            for (int numC = 0; numC < tMsgResult.length(); numC++) {
                for (int numP = 0; numP < cMsg.N.getValidCount(); numP++) {
                    setValue(cMsg.A.no(numC).cltCustTpCd_CD.no(numP), cMsg.N.no(numP).cltCustTpCd_N);
                    setValue(cMsg.A.no(numC).cltCustTpNm_SC.no(numP), cMsg.N.no(numP).cltCustTpNm_N);
                }
                cMsg.A.setValidCount(cMsg.A.getValidCount() + 1);
                setValue(cMsg.A.no(numC).cltCustTpCd_SV, tMsgResult.no(numC).cltCustTpCd);
                // START 2016/08/01 K.Kojima [QC#12493,ADD]
                setValue(cMsg.A.no(numC).cltOverDueRangeLowAmt_BK, tMsgResult.no(numC).cltOverDueRangeLowAmt);
                // END 2016/08/01 K.Kojima [QC#12493,ADD]
                setValue(cMsg.A.no(numC).cltOverDueRangeLowAmt_A, tMsgResult.no(numC).cltOverDueRangeLowAmt);
                setValue(cMsg.A.no(numC).cltOverDueRangeHighAmt_A, tMsgResult.no(numC).cltOverDueRangeHighAmt);
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

    private void doProcess_NFDL0140Scrn00_InsertRow(NFDL0140CMsg cMsg) {
        cMsg.A.setValidCount(cMsg.A.getValidCount() + 1);
        cMsg.A.no(cMsg.A.getValidCount() - 1).clear();
        cMsg.A.no(cMsg.A.getValidCount() - 1).xxTpCd_A.setValue(XX_TP_CD_INS);
        for (int numP = 0; numP < cMsg.N.getValidCount(); numP++) {
            setValue(cMsg.A.no(cMsg.A.getValidCount() - 1).cltCustTpCd_CD.no(numP), cMsg.N.no(numP).cltCustTpCd_N);
            setValue(cMsg.A.no(cMsg.A.getValidCount() - 1).cltCustTpNm_SC.no(numP), cMsg.N.no(numP).cltCustTpNm_N);
        }
        if (cMsg.A.getValidCount() == 1) {
            setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);
        }
    }

    private void doProcess_NFDL0140Scrn00_DeleteRow(NFDL0140CMsg cMsg) {
        int deletePos = cMsg.xxRadioBtn_A.getValueInt();

        if (cMsg.A.no(deletePos).xxTpCd_A.getValue().equals(XX_TP_CD_UPD)) {
            cMsg.D.setValidCount(cMsg.D.getValidCount() + 1);
            setValue(cMsg.D.no(cMsg.D.getValidCount() - 1).cltCustTpCd_D, cMsg.A.no(deletePos).cltCustTpCd_SV);
            // START 2016/08/01 K.Kojima [QC#12493,MOD]
            // setValue(cMsg.D.no(cMsg.D.getValidCount() -
            // 1).cltOverDueRangeLowAmt_D,
            // cMsg.A.no(deletePos).cltOverDueRangeLowAmt_A);
            setValue(cMsg.D.no(cMsg.D.getValidCount() - 1).cltOverDueRangeLowAmt_D, cMsg.A.no(deletePos).cltOverDueRangeLowAmt_BK);
            // END 2016/08/01 K.Kojima [QC#12493,MOD]
            setValue(cMsg.D.no(cMsg.D.getValidCount() - 1).cltOverDueRangeHighAmt_D, cMsg.A.no(deletePos).cltOverDueRangeHighAmt_A);
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

    private void doProcess_NFDL0140Scrn00_CMN_Submit(NFDL0140CMsg cMsg, String globalCompanyCode) {
        // START 2016/03/11 K.Kojima [QC#5358,MOD]
        // doProcess_NFDL0140_INIT((NFDL0140CMsg) cMsg,
        // globalCompanyCode);
        doProcess_NFDL0140_INIT((NFDL0140CMsg) cMsg, globalCompanyCode, false);
        // END 2016/03/11 K.Kojima [QC#5358,MOD]
    }
}
