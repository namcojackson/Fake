/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0330;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0330.NSAL0330CMsg;
import business.servlet.NSAL0330.common.NSAL0330CommonLogic;
import business.servlet.NSAL0330.constant.NSAL0330Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/02/18   Hitachi         T.Tomita        Update          QC#3892
 * 2017/08/03   Hitachi         E.Kameishi      Update          QC#18586
 *</pre>
 */
public class NSAL0330Scrn00_OnChangePeriods extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0330BMsg scrnMsg = (NSAL0330BMsg) bMsg;
        // START 2016/02/18 T.Tomita [QC#3892]
        NSAL0330_ABMsg abMsg = scrnMsg.A.no(getButtonSelectNumber());
        if (hasValue(abMsg.perSchdNum_A1)) {
            scrnMsg.addCheckItem(abMsg.perSchdNum_A1);
        }
        if (hasValue(abMsg.perBllgCycleCd_A1)) {
            scrnMsg.addCheckItem(abMsg.perBllgCycleCd_A1);
        }
        if (hasValue(abMsg.bllgSchdFromDt_A1)) {
            scrnMsg.addCheckItem(abMsg.bllgSchdFromDt_A1);
        }
        if (hasValue(abMsg.bllgSchdThruDt_A1)) {
            scrnMsg.addCheckItem(abMsg.bllgSchdThruDt_A1);
        }
        // END 2016/02/18 T.Tomita [QC#3892]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0330BMsg scrnMsg = (NSAL0330BMsg) bMsg;

        scrnMsg.xxSelNum_H1.setValue(String.valueOf(getButtonSelectNumber()));

        NSAL0330CMsg bizMsg = new NSAL0330CMsg();
        bizMsg.setBusinessID(NSAL0330Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0330BMsg scrnMsg = (NSAL0330BMsg) bMsg;
        NSAL0330CMsg bizMsg = (NSAL0330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2017/08/03 E.Kameishi [QC#18586,ADD]
        NSAL0330CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        // END 2017/08/03 E.Kameishi [QC#18586,ADD]

        NSAL0330CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
        scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).perSchdNum_A1);
    }
}
