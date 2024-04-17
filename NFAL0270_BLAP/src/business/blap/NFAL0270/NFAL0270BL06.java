/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFAL0270;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFAL0270.common.NFAL0270CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import static business.blap.NFAL0270.constant.NFAL0270Constant.*;

/**
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/17   Hitachi         G.Quan          Create          QC#61387
 *</pre>
 */
public class NFAL0270BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        String screenAplId = cMsg.getScreenAplID();

        try {
            // NFAL0270Scrn00_CMN_Submit
            if (NFAL0270SCRN00_CMN_SUBMIT.equals(screenAplId)) {
                doProcess_NFAL0270Scrn00_CMN_Submit((NFAL0270CMsg) cMsg, (NFAL0270SMsg) sMsg);

                // NFAL0270Scrn00_CMN_ColClear
            } else if (NFAL0270SCRN00_CMN_COLCLEAR.equals(screenAplId)) {
                ZYPGUITableColumn.clearColData((NFAL0270CMsg) cMsg, (NFAL0270SMsg) sMsg);

                // NFAL0270Scrn00_CMN_ColSave
            } else if (NFAL0270SCRN00_CMN_COLSAVE.equals(screenAplId)) {
                ZYPGUITableColumn.setColData((NFAL0270CMsg) cMsg, (NFAL0270SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
            return;

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NFAL0270Scrn00_CMN_Submit(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        NFAL0270CommonLogic.copyPage(cMsg, cMsg.A, sMsg.A);
        cMsg.setCommitSMsg(true);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // Check existence of Model Group ID in table
            if (NFAL0270CommonLogic.checkMdlGrpIdMaster(glblCmpyCd, sMsg.A.no(i))) {
                cMsg.setMessageInfo(ZZZM9006E, new String[] {"Model Group ID"});
                return;
            }
            // blank check as Charge Type
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).svcInvChrgTpCd_A)) {
                cMsg.setMessageInfo(ZZZM9025E, new String[] {"Charge Type"});
                return;
            }
             // blank check as Allocation Type
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).svcAllocTpCd_A)) {
                cMsg.setMessageInfo(ZZZM9025E, new String[] {"Allocation Type"});
                return;
            }
            // Check AllocationTotal
            if (NFAL0270CommonLogic.checkPercent(sMsg.A.no(i))) {
                cMsg.setMessageInfo(NFAM0209E);
                return;
            }
        }

        NFAL0270CommonLogic.updateSvcModDtl(glblCmpyCd, cMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }
}
