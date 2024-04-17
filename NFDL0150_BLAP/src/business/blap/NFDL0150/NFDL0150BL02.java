/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0150;

import static business.blap.NFDL0150.constant.NFDL0150Constant.*;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/03   Hitachi         K.Kojima        Create          N/A
 * 2016/03/11   Hitachi         K.Kojima        Update          CSA QC#5358
 *</pre>
 */
public class NFDL0150BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            String globalCompanyCode = getGlobalCompanyCode();

            if ("NFDL0150_INIT".equals(screenAplID)) {
                // START 2016/03/11 K.Kojima [QC#5358,MOD]
                // doProcess_NFDL0150_INIT((NFDL0150CMsg) cMsg,
                // globalCompanyCode);
                doProcess_NFDL0150_INIT((NFDL0150CMsg) cMsg, globalCompanyCode, true);
                // END 2016/03/11 K.Kojima [QC#5358,MOD]
            } else if ("NFDL0150Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NFDL0150Scrn00_InsertRow((NFDL0150CMsg) cMsg);
            } else if ("NFDL0150Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NFDL0150Scrn00_DeleteRow((NFDL0150CMsg) cMsg);
            } else if ("NFDL0150Scrn00_MoveWin_WorkItem".equals(screenAplID)) {
                doProcess_NFDL0150Scrn00_MoveWin_WorkItem((NFDL0150CMsg) cMsg);
            } else if ("NFDL0150Scrn00_MoveWin_CustomerType".equals(screenAplID)) {
                doProcess_NFDL0150Scrn00_MoveWin_CustomerType((NFDL0150CMsg) cMsg);
            } else if ("NFDL0150Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0150Scrn00_CMN_Submit((NFDL0150CMsg) cMsg, globalCompanyCode);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    // START 2016/03/11 K.Kojima [QC#5358,MOD]
    // private void doProcess_NFDL0150_INIT(NFDL0150CMsg cMsg, String
    // globalCompanyCode) {
    private void doProcess_NFDL0150_INIT(NFDL0150CMsg cMsg, String globalCompanyCode, boolean notFoundMsgFlg) {
        // END 2016/03/11 K.Kojima [QC#5358,MOD]
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.D.clear();
        cMsg.D.setValidCount(0);
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, globalCompanyCode);
        S21SsmEZDResult ssmResult = NFDL0150Query.getInstance().getSearchData(cMsg);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > cMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);
            // START 2016/03/11 K.Kojima [QC#5358,MOD]
            // } else {
        } else if (notFoundMsgFlg == true) {
            // END 2016/03/11 K.Kojima [QC#5358,MOD]
            cMsg.setMessageInfo(NZZM0000E);
        }
    }

    private void doProcess_NFDL0150Scrn00_InsertRow(NFDL0150CMsg cMsg) {
        cMsg.A.setValidCount(cMsg.A.getValidCount() + 1);
        cMsg.A.no(cMsg.A.getValidCount() - 1).clear();
        cMsg.A.no(cMsg.A.getValidCount() - 1).xxTpCd_A.setValue(XX_TP_CD_INS);
        if (cMsg.A.getValidCount() == 1) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);
        }
    }

    private void doProcess_NFDL0150Scrn00_DeleteRow(NFDL0150CMsg cMsg) {
        int deletePos = cMsg.xxRadioBtn_A.getValueInt();

        if (cMsg.A.no(deletePos).xxTpCd_A.getValue().equals(XX_TP_CD_UPD)) {
            cMsg.D.setValidCount(cMsg.D.getValidCount() + 1);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(cMsg.D.getValidCount() - 1).cltStrgyCd_D, cMsg.A.no(deletePos).cltStrgyCd_A);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(cMsg.D.getValidCount() - 1).cltStrgyNm_D, cMsg.A.no(deletePos).cltStrgyNm_A);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(cMsg.D.getValidCount() - 1).ezUpTime_D, cMsg.A.no(deletePos).ezUpTime_A);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(cMsg.D.getValidCount() - 1).ezUpTimeZone_D, cMsg.A.no(deletePos).ezUpTimeZone_A);
            cMsg.D.no(cMsg.D.getValidCount() - 1).xxTpCd_D.setValue(XX_TP_CD_DEL);
        }

        for (int num = deletePos + 1; num < cMsg.A.getValidCount(); num++) {
            EZDMsg.copy(cMsg.A.no(num), null, cMsg.A.no(num - 1), null);
        }
        cMsg.A.no(cMsg.A.getValidCount() - 1).clear();
        cMsg.A.setValidCount(cMsg.A.getValidCount() - 1);
        if (cMsg.A.getValidCount() == deletePos) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_A, new BigDecimal(deletePos - 1));
        }
    }

    private void doProcess_NFDL0150Scrn00_MoveWin_WorkItem(NFDL0150CMsg cMsg) {
        int radioPos = cMsg.xxRadioBtn_A.getValueInt();
        if (cMsg.A.no(radioPos).xxTpCd_A.getValue().equals(XX_TP_CD_INS)) {
            cMsg.setMessageInfo(NFDM0018E, new String[] {"Work Item" });
        }
    }

    private void doProcess_NFDL0150Scrn00_MoveWin_CustomerType(NFDL0150CMsg cMsg) {
        int radioPos = cMsg.xxRadioBtn_A.getValueInt();
        if (cMsg.A.no(radioPos).xxTpCd_A.getValue().equals(XX_TP_CD_INS)) {
            cMsg.setMessageInfo(NFDM0018E, new String[] {"Customer Type" });
        }
    }

    private void doProcess_NFDL0150Scrn00_CMN_Submit(NFDL0150CMsg cMsg, String globalCompanyCode) {
        // START 2016/03/11 K.Kojima [QC#5358,MOD]
        // doProcess_NFDL0150_INIT((NFDL0150CMsg) cMsg,
        // globalCompanyCode);
        doProcess_NFDL0150_INIT((NFDL0150CMsg) cMsg, globalCompanyCode, false);
        // END 2016/03/11 K.Kojima [QC#5358,MOD]
    }
}
