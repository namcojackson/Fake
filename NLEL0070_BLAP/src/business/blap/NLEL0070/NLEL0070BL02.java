/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLEL0070;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLEL0070.common.NLEL0070CommonLogic;
import business.db.ACCT_MTH_CTRLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_MTH_CTRL;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Asset Book Control
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   CUSA            Fujitsu         Create          N/A
 * 2016/06/08   Hitachi         T.Tsuchida      Update          QC#9637
 * 2016/06/27   Hitachi         K.Kojima        Update          QC#10041
 * 2018/07/31   Hitachi         J.Kim           Update          QC#26333
 *</pre>
 */
public class NLEL0070BL02 extends S21BusinessHandler {
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLEL0070_INIT".equals(screenAplID)) {
                doProcess_NLEL0070_INIT((NLEL0070CMsg) cMsg);
            } else if ("NLEL0070Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLEL0070Scrn00_CMN_Reset((NLEL0070CMsg) cMsg);
            // START 2016/06/08 T.Tsuchida [QC#9637,MOD]
            //} else if ("NLEL0070Scrn00_CMN_Save".equals(screenAplID)) {
            } else if ("NLEL0070Scrn00_CMN_Submit".equals(screenAplID)) {
            // END 2016/06/08 T.Tsuchida [QC#9637,MOD]
                doProcess_NLEL0070Scrn00_CMN_Save((NLEL0070CMsg) cMsg);
            } else if ("NLEL0070Scrn00_Search".equals(screenAplID)) {
                doProcess_NLEL0070Scrn00_Search((NLEL0070CMsg) cMsg);
            } else if ("NLEL0070Scrn00_OnChange_AssetTp".equals(screenAplID)) {
                doProcess_NLEL0070Scrn00_OnChange_AssetTp((NLEL0070CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * doProcess_NLEL0070_INIT
     *  </pre>
     * @param cMsg
     */
    private void doProcess_NLEL0070_INIT(NLEL0070CMsg cMsg) {

        // START 2018/07/31 J.Kim [QC#26333, MOD]
        // String slsDt = ZYPDateUtil.getSalesDate();
        // ZYPEZDItemValueSetter.setValue(cMsg.xxYrMth_M1, slsDt.substring(0, 6));
        ACCT_MTH_CTRLTMsg acctMthCtrlTMsg = NLEL0070CommonLogic.getAcctMthCtrl(getGlobalCompanyCode(), ACCT_MTH_CTRL.DEPRICIATION_CONTROL);
        if (acctMthCtrlTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxYrMth_M1, acctMthCtrlTMsg.acctYrMth);
        }
        // END 2018/07/31 J.Kim [QC#26333, MOD]
        // START 2016/06/27 K.Kojima [QC#10041,DEL]
        // ACCT_MTH_CTRLTMsg acctMthCtrlTMsg =
        // NLEL0070CommonLogic.getAcctMthCtrl(getGlobalCompanyCode(),
        // ACCT_MTH_CTRL.DEPRICIATION_CONTROL);
        // if (acctMthCtrlTMsg != null) {
        // ZYPEZDItemValueSetter.setValue(cMsg.acctYrMth_M1,
        // acctMthCtrlTMsg.acctYrMth);
        // }
        // END 2016/06/27 K.Kojima [QC#10041,DEL]
        NLEL0070CommonLogic.createPulldownList(cMsg);
    }

    /**
     * <pre>
     * doProcess_NLEL0070Scrn00_CMN_Reset
     *  </pre>
     * @param cMsg
     */
    private void doProcess_NLEL0070Scrn00_CMN_Reset(NLEL0070CMsg cMsg) {
        NLEL0070CommonLogic.clearCMsg(cMsg);
    }

    /**
     * <pre>
     * doProcess_NLEL0070Scrn00_CMN_Save
     *  </pre>
     * @param cMsg
     */
    private void doProcess_NLEL0070Scrn00_CMN_Save(NLEL0070CMsg cMsg) {
        NLEL0070CommonLogic.createEffFromDtPulldownList(getGlobalCompanyCode(), cMsg);
    }

    /**
     * <pre>
     * doProcess_NLEL0070Scrn00_Search
     *  </pre>
     * @param cMsg NLEL0070CMsg
     */
    private void doProcess_NLEL0070Scrn00_Search(NLEL0070CMsg cMsg) {
        NLEL0070CommonLogic.clearTargetCMsg(cMsg);

        String slsDt = ZYPDateUtil.getSalesDate();
        NLEL0070CommonLogic.search(getGlobalCompanyCode(), cMsg, slsDt);

        // START 2016/06/27 K.Kojima [QC#10041,ADD]
        String lastDepcPeriod = NLEL0070CommonLogic.getLastDepcPeriod(getGlobalCompanyCode(), cMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.acctYrMth_M1, lastDepcPeriod);
        // END 2016/06/27 K.Kojima [QC#10041,ADD]
    }

    /**
     * <pre>
     * doProcess_NLEL0070Scrn00_OnChange_AssetTp
     *  </pre>
     * @param cMsg NLEL0070CMsg
     */
    private void doProcess_NLEL0070Scrn00_OnChange_AssetTp(NLEL0070CMsg cMsg) {
        NLEL0070CommonLogic.createEffFromDtPulldownList(getGlobalCompanyCode(), cMsg);
    }
}
