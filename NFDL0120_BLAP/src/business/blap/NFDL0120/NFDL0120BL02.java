/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0120;

import static business.blap.NFDL0120.constant.NFDL0120Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CLT_STRGYTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Hitachi         K.Kojima        Create          N/A
 * 2016/03/11   Hitachi         K.Kojima        Update          CSA QC#5358
 *</pre>
 */
public class NFDL0120BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            String globalCompanyCode = getGlobalCompanyCode();

            if ("NFDL0120_INIT".equals(screenAplID)) {
                // START 2016/03/11 K.Kojima [QC#5358,MOD]
                // doProcess_NFDL0120_INIT((NFDL0120CMsg) cMsg,
                // globalCompanyCode);
                doProcess_NFDL0120_INIT((NFDL0120CMsg) cMsg, globalCompanyCode, true);
                // END 2016/03/11 K.Kojima [QC#5358,MOD]
            } else if ("NFDL0120Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NFDL0120Scrn00_InsertRow((NFDL0120CMsg) cMsg);
            } else if ("NFDL0120Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NFDL0120Scrn00_DeleteRow((NFDL0120CMsg) cMsg);
            } else if ("NFDL0120Scrn00_OnChange_WorkItemCode".equals(screenAplID)) {
                doProcess_NFDL0120Scrn00_OnChange_WorkItemCode((NFDL0120CMsg) cMsg);
            } else if ("NFDL0120Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0120Scrn00_CMN_Submit((NFDL0120CMsg) cMsg, globalCompanyCode);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    // START 2016/03/11 K.Kojima [QC#5358,MOD]
    // private void doProcess_NFDL0120_INIT(NFDL0120CMsg cMsg, String
    // globalCompanyCode) {
    private void doProcess_NFDL0120_INIT(NFDL0120CMsg cMsg, String globalCompanyCode, boolean notFoundMsgFlg) {
        // END 2016/03/11 K.Kojima [QC#5358,MOD]
        cMsg.A.clear();
        cMsg.D.clear();
        cMsg.N.clear();
        cMsg.A.setValidCount(0);
        cMsg.D.setValidCount(0);
        cMsg.N.setValidCount(0);

        setValue(cMsg.glblCmpyCd, globalCompanyCode);

        NFDL0120Query.getInstance().getPulldownList(cMsg);

        CLT_STRGYTMsg inMsg = new CLT_STRGYTMsg();
        inMsg.glblCmpyCd.setValue(globalCompanyCode);
        inMsg.cltStrgyCd.setValue(cMsg.cltStrgyCd.getValue());
        CLT_STRGYTMsg tMsg = (CLT_STRGYTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tMsg != null) {
            setValue(cMsg.cltStrgyNm, tMsg.cltStrgyNm);
        } else {
            cMsg.setMessageInfo(ZZZM9004E);
        }

        S21SsmEZDResult ssmResult = NFDL0120Query.getInstance().getSearchData(cMsg);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > cMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
            setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);

            for (int numC = 0; numC < cMsg.A.getValidCount(); numC++) {
                for (int numP = 0; numP < cMsg.N.getValidCount(); numP++) {
                    setValue(cMsg.A.no(numC).cltWrkItemCd_CD.no(numP), cMsg.N.no(numP).cltWrkItemCd_N);
                    setValue(cMsg.A.no(numC).cltWrkItemCd_SC.no(numP), cMsg.N.no(numP).cltWrkItemCd_N);
                }
            }
            // START 2016/03/11 K.Kojima [QC#5358,MOD]
            // } else {
        } else if (notFoundMsgFlg == true) {
            // END 2016/03/11 K.Kojima [QC#5358,MOD]
            cMsg.setMessageInfo(NZZM0000E);
        }
    }

    private void doProcess_NFDL0120Scrn00_InsertRow(NFDL0120CMsg cMsg) {
        cMsg.A.setValidCount(cMsg.A.getValidCount() + 1);
        cMsg.A.no(cMsg.A.getValidCount() - 1).clear();
        cMsg.A.no(cMsg.A.getValidCount() - 1).xxTpCd_A.setValue(XX_TP_CD_INS);
        for (int numP = 0; numP < cMsg.N.getValidCount(); numP++) {
            setValue(cMsg.A.no(cMsg.A.getValidCount() - 1).cltWrkItemCd_CD.no(numP), cMsg.N.no(numP).cltWrkItemCd_N);
            setValue(cMsg.A.no(cMsg.A.getValidCount() - 1).cltWrkItemCd_SC.no(numP), cMsg.N.no(numP).cltWrkItemCd_N);
        }
        if (cMsg.A.getValidCount() == 1) {
            setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);
        }
    }

    private void doProcess_NFDL0120Scrn00_DeleteRow(NFDL0120CMsg cMsg) {
        int deletePos = cMsg.xxRadioBtn_A.getValueInt();

        if (cMsg.A.no(deletePos).xxTpCd_A.getValue().equals(XX_TP_CD_UPD)) {
            cMsg.D.setValidCount(cMsg.D.getValidCount() + 1);
            setValue(cMsg.D.no(cMsg.D.getValidCount() - 1).cltWrkItemCd_D, cMsg.A.no(deletePos).cltWrkItemCd_SV);
            setValue(cMsg.D.no(cMsg.D.getValidCount() - 1).cltWrkItemNm_D, cMsg.A.no(deletePos).cltWrkItemNm_A);
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

    private void doProcess_NFDL0120Scrn00_OnChange_WorkItemCode(NFDL0120CMsg cMsg) {
        for (int numA = 0; numA < cMsg.A.getValidCount(); numA++) {
            String cltWrkItemCd = cMsg.A.no(numA).cltWrkItemCd_SV.getValue();
            if (cltWrkItemCd == null || cltWrkItemCd.length() == 0) {
                setValue(cMsg.A.no(numA).cltWrkItemNm_A, "");
                continue;
            }
            for (int numN = 0; numN < cMsg.N.getValidCount(); numN++) {
                if (cltWrkItemCd.equals(cMsg.N.no(numN).cltWrkItemCd_N.getValue())) {
                    setValue(cMsg.A.no(numA).cltWrkItemNm_A, cMsg.N.no(numN).cltWrkItemNm_N);
                    break;
                }
            }
        }
    }

    private void doProcess_NFDL0120Scrn00_CMN_Submit(NFDL0120CMsg cMsg, String globalCompanyCode) {
        // START 2016/03/11 K.Kojima [QC#5358,MOD]
        // doProcess_NFDL0120_INIT((NFDL0120CMsg) cMsg,
        // globalCompanyCode);
        doProcess_NFDL0120_INIT((NFDL0120CMsg) cMsg, globalCompanyCode, false);
        // END 2016/03/11 K.Kojima [QC#5358,MOD]
    }
}
