/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL2640;

import static business.blap.NFCL2640.constant.NFCL2640Constant.ADD_MONTH_SIZE;
import static business.blap.NFCL2640.constant.NFCL2640Constant.NFCM0580E;
import static business.blap.NFCL2640.constant.NFCL2640Constant.NFCM0798E;
import static business.blap.NFCL2640.constant.NFCL2640Constant.NFCM0801E;
import static business.blap.NFCL2640.constant.NFCL2640Constant.NFCM0802W;
import static business.blap.NFCL2640.constant.NFCL2640Constant.NZZM0000E;
import static business.blap.NFCL2640.constant.NFCL2640Constant.STMT_DT;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL2640.common.NFCL2640CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_ISS_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Statement Schedule Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         M.Ohno          Create          N/A
 * 2016/07/12   Fujitsu         S.Yoshida       Update          QC#11461
 * 2019/02/26   Fujitsu         Y.Matsui        Update          QC#30302
 *</pre>
 */
public class NFCL2640BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL2640CMsg bizMsg = (NFCL2640CMsg) cMsg;
            NFCL2640SMsg glblMsg = (NFCL2640SMsg) sMsg;

            if ("NFCL2640_INIT".equals(screenAplID)) {
                doProcess_NFCL2640_INIT(bizMsg, glblMsg);
            } else if ("NFCL2640Scrn00_Search".equals(screenAplID)) {
                doProcess_NFCL2640Scrn00_Search(bizMsg, glblMsg);
            } else if ("NFCL2640Scrn00_AddRow".equals(screenAplID)) {
                doProcess_NFCL2640Scrn00_AddRow(bizMsg, glblMsg);
            } else if ("NFCL2640Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL2640Scrn00_CMN_Clear(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NFCL2640_INIT(NFCL2640CMsg bizMsg, NFCL2640SMsg glblMsg) {
        ZYPCodeDataUtil.createPulldownList(AR_STMT_ISS_CYCLE.class, bizMsg.arStmtIssCycleCd_P, bizMsg.arStmtIssCycleNm_P);
        ZYPCodeDataUtil.createPulldownList(AR_STMT_STS.class, bizMsg.arStmtStsCd_P, bizMsg.arStmtStsNm_P);
        String acctDt = NFCL2640CommonLogic.findArAcctDtInfoForAcctDt(bizMsg, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.arStmtDt_NW, acctDt);
    }

    private void doProcess_NFCL2640Scrn00_Search(NFCL2640CMsg bizMsg, NFCL2640SMsg glblMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        NFCL2640CommonLogic.searchArStmtCtrl(bizMsg, glblMsg);

    }

    private void doProcess_NFCL2640Scrn00_AddRow(NFCL2640CMsg bizMsg, NFCL2640SMsg glblMsg) {

        String acctDt = NFCL2640CommonLogic.findArAcctDtInfoForAcctDt(bizMsg, getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(acctDt)) {
            return;
        }

        if (ZYPDateUtil.compare(bizMsg.arStmtDt_AD.getValue(), acctDt) < 0) {
            bizMsg.arStmtDt_AD.setErrorInfo(1, NFCM0801E, new String[] {STMT_DT });
            return;
        }

        if (!bizMsg.xxChkBox_BC.isClear()) {
            addLineOfBulk(bizMsg, glblMsg, acctDt);
        } else if (ZYPCommonFunc.hasValue(bizMsg.arStmtDt_AD)) {
            addLineOfStmtDt(bizMsg, glblMsg, acctDt);
        }
    }

    private void doProcess_NFCL2640Scrn00_CMN_Clear(NFCL2640CMsg bizMsg, NFCL2640SMsg glblMsg) {
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);
        doProcess_NFCL2640_INIT(bizMsg, glblMsg);
    }

    private void addLineOfBulk(NFCL2640CMsg bizMsg, NFCL2640SMsg glblMsg, String acctDt) {

        if ((bizMsg.A.getValidCount() + ADD_MONTH_SIZE) > bizMsg.A.length()) {
            bizMsg.setMessageInfo(NFCM0798E, new String[] {Integer.toString(bizMsg.A.length()) });
            return;
        }

        String maxStmtDt = null;
        S21SsmEZDResult ssmResult = NFCL2640Query.getInstance().maxStmtDt(bizMsg, glblMsg);

        if (ssmResult.isCodeNormal()) {
            if (ssmResult.getResultObject() == null) {
                maxStmtDt = NFCL2640CommonLogic.createNextStmtDate(bizMsg, getGlobalCompanyCode());
            } else {
                maxStmtDt = (String) ssmResult.getResultObject();
                maxStmtDt = NFCL2640CommonLogic.addMonths(bizMsg, maxStmtDt, 1); // 2019/2/26 Y.Matsui [QC#30302, MOD]
            }
        } else {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        int detailSize = bizMsg.A.getValidCount();
        int overlapCount = 0;

        List<String> scrnStmtDtList = new ArrayList<String>();
        for (int i = 0; i <= detailSize; i++) {
            scrnStmtDtList.add(bizMsg.A.no(i).arStmtDt_A1.getValue());
        }

        for (int i = 0; i < ADD_MONTH_SIZE; i++) {
            if (scrnStmtDtList.contains(maxStmtDt)) {
                overlapCount++;
                maxStmtDt = NFCL2640CommonLogic.addMonths(bizMsg, maxStmtDt, 1); // 2019/2/26 Y.Matsui [QC#30302, MOD]
                continue;
            }
            NFCL2640_ACMsg bizMsgA = bizMsg.A.no(detailSize + i - overlapCount);
            ZYPEZDItemValueSetter.setValue(bizMsgA.arStmtDt_A1, maxStmtDt);
            ZYPEZDItemValueSetter.setValue(bizMsgA.arStmtStsCd_A1, AR_STMT_STS.PENDING);
            ZYPEZDItemValueSetter.setValue(bizMsgA.lateFeeCalcFlg_A1, bizMsg.lateFeeCalcFlg.getValue());
            maxStmtDt = NFCL2640CommonLogic.addMonths(bizMsg, maxStmtDt, 1);  // 2019/2/26 Y.Matsui [QC#30302, MOD]
        }

        bizMsg.A.setValidCount(detailSize + ADD_MONTH_SIZE - overlapCount);
        String lastRowArStmtDt = bizMsg.A.no(bizMsg.A.getValidCount() - 1).arStmtDt_A1.getValue();
        if (NFCL2640CommonLogic.addYear(acctDt, 2).compareTo(lastRowArStmtDt) <= 0) {
            bizMsg.setMessageInfo(NFCM0802W, null);
        }
    }

    private void addLineOfStmtDt(NFCL2640CMsg bizMsg, NFCL2640SMsg glblMsg, String acctDt) {

        S21SsmEZDResult ssmResult = NFCL2640Query.getInstance().countStmtDt(bizMsg, glblMsg);
        if (ssmResult.isCodeNormal()) {
            if ((Integer) ssmResult.getResultObject() > 0) {
                bizMsg.setMessageInfo(NFCM0580E, new String[] {STMT_DT });
                return;
            }
        } else {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        if (bizMsg.A.getValidCount() >= bizMsg.A.length()) {
            bizMsg.setMessageInfo(NFCM0798E, new String[] {Integer.toString(bizMsg.A.length())});
            return;
        }

        NFCL2640_ACMsg bizMsgA = bizMsg.A.no(bizMsg.A.getValidCount());
        ZYPEZDItemValueSetter.setValue(bizMsgA.arStmtDt_A1, bizMsg.arStmtDt_AD.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsgA.arStmtStsCd_A1, AR_STMT_STS.PENDING);
        ZYPEZDItemValueSetter.setValue(bizMsgA.lateFeeCalcFlg_A1, bizMsg.lateFeeCalcFlg.getValue());
        bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 1);
        if (NFCL2640CommonLogic.addYear(acctDt, 2).compareTo(bizMsg.arStmtDt_AD.getValue()) <= 0) {
            bizMsg.setMessageInfo(NFCM0802W, null);
        }
    }

}
