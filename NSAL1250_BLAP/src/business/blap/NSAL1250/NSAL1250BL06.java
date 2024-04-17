/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1250;

import static business.blap.NSAL1250.constant.NSAL1250Constant.*;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_APPROVE;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;

import business.blap.NSAL1250.common.NSAL1250CommonLogic;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.model.impl.S21NwfWorkItemImpl;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.impl.S21NwfTokenImpl;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/03/30   Hitachi         T.Aoyagi        Update          QC#1467
 * 2018/03/23   Hitachi         U.Kim           Update          QC#18884(Sol337)
 * 2019/09/02   Hitachi         K.Kitachi       Update          QC#52695
 *</pre>
 */
public class NSAL1250BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1250Scrn00_CMN_Approve".equals(screenAplID)) {
                doProcess_NSAL1250Scrn00_Approve((NSAL1250CMsg) cMsg, (NSAL1250SMsg) sMsg);
            } else if ("NSAL1250Scrn00_Approve".equals(screenAplID)) {
                doProcess_NSAL1250Scrn00_Approve((NSAL1250CMsg) cMsg, (NSAL1250SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Approve)
     * @param cMsg NSAL1250CMsg
     * @param sMsg NSAL1250SMsg
     */
    private void doProcess_NSAL1250Scrn00_Approve(NSAL1250CMsg bizMsg, NSAL1250SMsg sMsg) {
        S21NwfUtilContextFactory factory = new S21NwfUtilContextFactory();
        List<S21NwfProcess> process;
        String documentId = NSAL1250CommonLogic.getDocumentId(bizMsg.svcContrBllgPk.getValue());
        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            // START 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            // process = context.getProcess(PROCESS_DEF_NSWP0002, documentId);
            process = context.getProcess(bizMsg.wfBizAppId.getValue(), documentId);
            // END 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            // START 2019/09/02 K.Kitachi [QC#52695, ADD]
            if ((process == null) || (process.size() <= 0)) {
                BigDecimal dsContrDtlPk = bizMsg.dsContrDtlPk.getValue();
                documentId = S21StringUtil.concatStrings(documentId, dsContrDtlPk.toPlainString());
                process = context.getProcess(bizMsg.wfBizAppId.getValue(), documentId);
            }
            // END 2019/09/02 K.Kitachi [QC#52695, ADD]
        } catch (S21NwfException e) {
            // START 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            // bizMsg.setMessageInfo(NSAM0436E, new String[] {PROCESS_DEF_NSWP0002, documentId });
            bizMsg.setMessageInfo(NSAM0436E, new String[] {bizMsg.wfBizAppId.getValue(), documentId });
            // END 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            return;
        }

        if ((process == null) || (process.size() <= 0)) {
            // START 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            // bizMsg.setMessageInfo(NSAM0436E, new String[] {PROCESS_DEF_NSWP0002, documentId });
            bizMsg.setMessageInfo(NSAM0436E, new String[] {bizMsg.wfBizAppId.getValue(), documentId });
            // END 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            return;
        }

        S21NwfTokenImpl token = (S21NwfTokenImpl) (process.get(0).getToken());
        if (!(process.get(0).getStatus() == S21NwfProcess.STATUS.CLOSE || process.get(0).getStatus() == S21NwfProcess.STATUS.CANCEL)) {
            S21NwfWorkItemImpl activeTask = token.getActiveWorkItem();
            if (activeTask != null) {
                if (!token.getActiveWorkItem().isApprovable()) {
                    bizMsg.setMessageInfo(NSAM0437E);
                    return;
                }
            }
        }
        try {
            token.signal(SIGNAL_APPROVE);
        } catch (S21NwfException e) {
            // START 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            // bizMsg.setMessageInfo(NSAM0436E, new String[] {PROCESS_DEF_NSWP0002, documentId });
            bizMsg.setMessageInfo(NSAM0436E, new String[] {bizMsg.wfBizAppId.getValue(), documentId });
            // END 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            return;
        }
        // START 03/30/2016 T.Aoyagi [QC#1467, MOD]
        bizMsg.setMessageInfo(NSAM0435I);
        // END 03/30/2016 T.Aoyagi [QC#1467, MOD]
        bizMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
    }
}
