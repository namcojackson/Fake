/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0790;

import static business.blap.NSAL0790.constant.NSAL0790Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0790.common.NSAL0790CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Fleet Rollup Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Hitachi         A.Kohinata      Create          N/A
 * 2017/03/01   Hitachi         K.Kitachi       Update          QC#17752
 * 2017/03/10   Hitachi         K.Kitachi       Update          QC#17752
 * 2017/06/06   Hitachi         A.Kohinata      Update          QC#18770
 *</pre>
 */
public class NSAL0790BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0790CMsg cMsg = (NSAL0790CMsg) arg0;
        NSAL0790SMsg sMsg = (NSAL0790SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0790_INIT".equals(screenAplID)) {
                doProcess_NSAL0790_INIT(cMsg, sMsg);
            } else if ("NSAL0790Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL0790Scrn00_PageJump(cMsg, sMsg);
            } else if ("NSAL0790Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0790Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0790Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0790Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0790Scrn00_ResubmitRollup".equals(screenAplID)) {
                doProcess_NSAL0790Scrn00_ResubmitRollup(cMsg, sMsg);
            // add start 2017/06/06 CSA Defect#18770
            } else if ("NSAL0790_NSAL0150".equals(screenAplID)) {
                doProcess_NSAL0790_NSAL0150(cMsg, sMsg);
            // add end 2017/06/06 CSA Defect#18770
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0790_INIT(NSAL0790CMsg cMsg, NSAL0790SMsg sMsg) {

        NSAL0790CommonLogic.clearMsg(cMsg, sMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        // START 2017/03/10 K.Kitachi [QC#17752, MOD]
//        if (!hasValue(cMsg.fleetReadRollUpPk_H)) {
        if (cMsg.P.getValidCount() == 0) {
        // END 2017/03/10 K.Kitachi [QC#17752, MOD]
            cMsg.setMessageInfo(NSAM0131E, new String[] {"FLEET_READ_ROLL_UP_PK" });
            return;
        }

        NSAL0790CommonLogic.getFleetRollupInfo(cMsg, sMsg);
        NSAL0790CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        // START 2017/03/01 K.Kitachi [QC#17752, ADD]
        NSAL0790CommonLogic.setTableLayout(cMsg);
        // END 2017/03/01 K.Kitachi [QC#17752, ADD]
    }

    private void doProcess_NSAL0790Scrn00_PageJump(NSAL0790CMsg cMsg, NSAL0790SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0790CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = (cMsg.xxPageShowCurNum.getValue().subtract(BigDecimal.ONE)).multiply(new BigDecimal(cMsg.A.length())).intValue();
        NSAL0790CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
        // START 2017/03/01 K.Kitachi [QC#17752, ADD]
        NSAL0790CommonLogic.setTableLayout(cMsg);
        // END 2017/03/01 K.Kitachi [QC#17752, ADD]
    }

    private void doProcess_NSAL0790Scrn00_PageNext(NSAL0790CMsg cMsg, NSAL0790SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0790CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL0790CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
        // START 2017/03/01 K.Kitachi [QC#17752, ADD]
        NSAL0790CommonLogic.setTableLayout(cMsg);
        // END 2017/03/01 K.Kitachi [QC#17752, ADD]
    }

    private void doProcess_NSAL0790Scrn00_PagePrev(NSAL0790CMsg cMsg, NSAL0790SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0790CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL0790CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
        // START 2017/03/01 K.Kitachi [QC#17752, ADD]
        NSAL0790CommonLogic.setTableLayout(cMsg);
        // END 2017/03/01 K.Kitachi [QC#17752, ADD]
    }

    private void doProcess_NSAL0790Scrn00_ResubmitRollup(NSAL0790CMsg cMsg, NSAL0790SMsg sMsg) {

        doProcess_NSAL0790_INIT(cMsg, sMsg);
    }

    // add start 2017/06/06 CSA Defect#18770
    private void doProcess_NSAL0790_NSAL0150(NSAL0790CMsg cMsg, NSAL0790SMsg sMsg) {

        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            BigDecimal newFleetReadRollUpPk = NSAL0790Query.getInstance().getNewFleetReadRollUpPk(cMsg, cMsg.P.no(i).fleetReadRollUpPk_P.getValue());
            if (hasValue(newFleetReadRollUpPk)) {
                setValue(cMsg.P.no(i).fleetReadRollUpPk_P, newFleetReadRollUpPk);
            }
        }

        doProcess_NSAL0790_INIT(cMsg, sMsg);
    }
    // add end 2017/06/06 CSA Defect#18770
}
