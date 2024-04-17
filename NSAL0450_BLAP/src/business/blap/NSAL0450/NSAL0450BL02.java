package business.blap.NSAL0450;

import static business.blap.NSAL0450.constant.NSAL0450Constant.NSAM0353E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0450.common.NSAL0450CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Hitachi         J.Kim           Create          N/A
 * 2016/07/19   Hitachi         A.Kohinata      Update          QC#11736
 * 2016/08/26   Hitachi         A.Kohinata      Update          QC#13778
 *</pre>
 */
public class NSAL0450BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL0450CMsg cMsg = (NSAL0450CMsg) arg0;
        NSAL0450SMsg sMsg = (NSAL0450SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0450_INIT".equals(screenAplID)) {
                doProcess_NSAL0450_INIT(cMsg, sMsg);
            } else if ("NSAL0450Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0450Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0450Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0450Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0450Scrn00_Contraction".equals(screenAplID)) {
                doProcess_NSAL0450Scrn00_Contraction(cMsg, sMsg);
            } else if ("NSAL0450Scrn00_Expansion".equals(screenAplID)) {
                doProcess_NSAL0450Scrn00_Expansion(cMsg, sMsg);
            // add start 2016/08/26 CSA Defect#13778
            } else if ("NSAL0450_NSAL1220".equals(screenAplID)) {
                doProcess_NSAL0450_ReSearch(cMsg, sMsg);
            } else if ("NSAL0450_NSAL1230".equals(screenAplID)) {
                doProcess_NSAL0450_ReSearch(cMsg, sMsg);
            // add end 2016/08/26 CSA Defect#13778
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Initialize event handler
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     */
    private void doProcess_NSAL0450_INIT(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        // START 2016/07/19 A.Kohinata [QC#11736, ADD]
        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        // END 2016/07/19 A.Kohinata [QC#11736, ADD]

        // get global company code
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        if (!hasValue(cMsg.dsContrPk_H)) {
            cMsg.setMessageInfo(NSAM0353E, new String[] {"No input parameter" });
            return;
        }

        if (!hasValue(cMsg.xxModeCd)) {
            cMsg.setMessageInfo(NSAM0353E, new String[] {"No input parameter" });
            return;
        }

        // search Contract Machine Info
        NSAL0450CommonLogic.searchDSContractInfo(cMsg, sMsg);

        // Set Default Branch And Default Account
        NSAL0450CommonLogic.setDetailList(cMsg, sMsg);
        // Copy sMsg To CMsg
        NSAL0450CommonLogic.pagenation(cMsg, sMsg, 0);
    }

    /**
     * Page next event handler
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     */
    private void doProcess_NSAL0450Scrn00_PageNext(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt());
        cMsg.xxPageShowToNum.clear();

        NSAL0450CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Page prev event handler
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     */
    private void doProcess_NSAL0450Scrn00_PagePrev(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1);
        cMsg.xxPageShowToNum.clear();

        NSAL0450CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Contraction event handler
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     */
    private void doProcess_NSAL0450Scrn00_Contraction(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        // set Expansion Button
        NSAL0450CommonLogic.copyCurrentPageToSMsgContraction(cMsg, sMsg);

        // set Paging Data
        cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - 1 + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(NSAL0450CommonLogic.getTotalPage(cMsg, sMsg));
    }

    /**
     * Expansion event handler
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     */
    private void doProcess_NSAL0450Scrn00_Expansion(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        // set Expansion Button
        NSAL0450CommonLogic.copyCurrentPageToSMsgExpansion(cMsg, sMsg);

        // set Paging Data
        cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - 1 + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(NSAL0450CommonLogic.getTotalPage(cMsg, sMsg));
    }

    // add start 2016/08/26 CSA Defect#13778
    private void doProcess_NSAL0450_ReSearch(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

        // search Contract Machine Info
        NSAL0450CommonLogic.searchDSContractInfo(cMsg, sMsg);
        // Set Default Branch And Default Account
        NSAL0450CommonLogic.setDetailList(cMsg, sMsg);
        // Copy sMsg To CMsg
        NSAL0450CommonLogic.pagenation(cMsg, sMsg, 0);
    }
    // add end 2016/08/26 CSA Defect#13778
}
