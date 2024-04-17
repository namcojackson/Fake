/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2180;

import static business.servlet.NWAL2180.constant.NWAL2180Constant.BIZ_ID;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.NWZM1107E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2180.NWAL2180CMsg;
import business.servlet.NWAL2180.common.NWAL2180CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2180Scrn00_CMN_Reset
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   Fujitsu         M.Yamada        Create          N/A
 * 2016/05/30   Fujitsu         M.Yamada        Update          QC#4628
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 *</pre>
 */
public class NWAL2180Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;

        NWAL2180CMsg bizMsg = new NWAL2180CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;

        if (NWZM1107E.equals(scrnMsg.getMessageCode())) {
            NWAL2180CommonLogic.initAppFracDigit(scrnMsg);
            NWAL2180CommonLogic.initCmnBtnProp(this, scrnMsg);
            NWAL2180CommonLogic.initInputProtected(scrnMsg);
            NWAL2180CommonLogic.initButton(this, scrnMsg);
            return;
        }
        NWAL2180CMsg bizMsg = (NWAL2180CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2018/05/07 QC#22482 Del Start
//        NWAL2180CommonLogic.setScrnDspCtrl(scrnMsg);
        // 2018/05/07 QC#22482 Del End
        NWAL2180CommonLogic.setUsgPrcAreaCtrl(scrnMsg, this);

        NWAL2180CommonLogic.initAppFracDigit(scrnMsg);
        NWAL2180CommonLogic.initCmnBtnProp(this, scrnMsg);
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxFuncId_UP)) {
            NWAL2180CommonLogic.protectRefMode(this, scrnMsg);
            return;
        }
        NWAL2180CommonLogic.initInputProtected(scrnMsg);
        NWAL2180CommonLogic.initButton(this, scrnMsg);
        NWAL2180CommonLogic.overrideProtected(this, scrnMsg);
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            NWAL2180CommonLogic.setBandButton(this, scrnMsg, NWAL2180CommonLogic.isParentLine(scrnMsg.Z.no(i)), i);
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL2180CommonLogic.setSupplyButton(this, scrnMsg.A.no(i), i, scrnMsg);
        }
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            NWAL2180CommonLogic.setBandButton(this, scrnMsg, NWAL2180CommonLogic.isParentLine(scrnMsg.U.no(i)), i);
        }
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            NWAL2180CommonLogic.setTierLinkCtrlConfig(this, scrnMsg, i);
            NWAL2180CommonLogic.setSupplyButton(this, scrnMsg.R.no(i), i, scrnMsg);
        }
        scrnMsg.putErrorScreen();
    }
}
