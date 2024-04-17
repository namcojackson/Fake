/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1300;

import static business.blap.NSAL1300.constant.NSAL1300Constant.NZZM0001W;
import static business.blap.NSAL1300.constant.NSAL1300Constant.ZZZM9001E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import business.blap.NSAL1300.common.NSAL1300CommonLogic;
import business.db.SVC_CONTR_BLLGTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * Usage Meter Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/01   Hitachi         N.Arai          Create          N/A
 * 2017/06/21   Hitachi         K.Kishimoto     Update          QC#19423
 * 2018/03/07   Hitachi         K.Kojima        Update          QC#24671
 * 2018/03/26   Hitachi         K.Kojima        Update          QC#24772
 *</pre>
 */
public class NSAL1300BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1300CMsg cMsg = (NSAL1300CMsg) arg0;
        NSAL1300SMsg sMsg = (NSAL1300SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1300_INIT".equals(screenAplID)) {
                doProcess_NSAL1300_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSAL1300Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL1300Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSAL1300Scrn00_CMN_Submit".equals(screenAplID)) {
                // START 2018/03/26 K.Kojima [QC#24772,MOD]
                // doProcess_NSAL1300Scrn00_CMN_Submit(cMsg, sMsg);
                if (cMsg.getMessageCode().endsWith("W")) {
                    cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                } else {
                    doProcess_NSAL1300Scrn00_CMN_Submit(cMsg, sMsg);
                }
                // END 2018/03/26 K.Kojima [QC#24772,MOD]
            } else if ("NSAL1300Scrn00_Filter".equals(screenAplID)) {
                doProcess_NSAL1300Scrn00_Filter(cMsg, sMsg);
            } else if ("NSAL1300Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL1300Scrn00_PageJump(cMsg, sMsg);
            } else if ("NSAL1300Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1300Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1300Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1300Scrn00_PagePrev(cMsg, sMsg);
            }
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1300_INIT(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg) {

        NSAL1300CommonLogic.clearMsg(cMsg, sMsg);

        NSAL1300CommonLogic.initDisplay(cMsg, sMsg);

    }

    private void doProcess_NSAL1300Scrn00_CMN_Reset(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg) {

        doProcess_NSAL1300_INIT(cMsg, sMsg);
    }

    private void doProcess_NSAL1300Scrn00_CMN_Submit(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg) {
        // START 2018/03/07 K.Kojima [QC#24671,MOD]
        // doProcess_NSAL1300_INIT(cMsg, sMsg);
        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind())) {
            doProcess_NSAL1300_INIT(cMsg, sMsg);
        }
        // END 2018/03/07 K.Kojima [QC#24671,MOD]
    }

    private void doProcess_NSAL1300Scrn00_Filter(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg) {

        SVC_CONTR_BLLGTMsg svcContrBllgTMsg = NSAL1300Query.getInstance().getSvcCntrBllg(cMsg);
        S21SsmEZDResult ssmResultDtlB =null;
        // Mod Start 06/21/2017 <QC#19423>
        if (DS_CONTR_CATG.FLEET.equals(svcContrBllgTMsg.dsContrCatgCd.getValue())) {
             ssmResultDtlB = NSAL1300Query.getInstance().getFleetUsageMeterDetail(cMsg,sMsg,sMsg.B.length() + 1);
        } else {
             ssmResultDtlB = NSAL1300Query.getInstance().getNotFleetUsageMeterDetail(cMsg,sMsg,sMsg.B.length() + 1);
        }
        // Mod End   06/21/2017 <QC#19423>

        if (ssmResultDtlB.isCodeNormal()) {

            NSAL1300CommonLogic.getTotalAdcvRatio(sMsg);

            // Result > 1000
            int queryResCnt = ssmResultDtlB.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
        } else {

            NSAL1300CommonLogic.pagenation(cMsg, sMsg, 0);
            cMsg.xxPageShowOfNum.setValue(sMsg.B.getValidCount());
            cMsg.setCommitSMsg(true);
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
            return;
        }

        NSAL1300CommonLogic.pagenation(cMsg, sMsg, 0);

    }

    private void doProcess_NSAL1300Scrn00_PageJump(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1300CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = NSAL1300CommonLogic.convertPageNumToFirstRowIndex(cMsg);
        NSAL1300CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);

    }

    private void doProcess_NSAL1300Scrn00_PageNext(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1300CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.B.length() - 1;
        NSAL1300CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1300Scrn00_PagePrev(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1300CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.B.length() - 1;
        NSAL1300CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

}
