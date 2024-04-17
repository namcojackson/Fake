/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import static business.servlet.NLCL0280.constant.NLCL0280Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
// business.blap.NLCL0280.NLCL0280CMsg;
// import business.servlet.NLCL0280.constant.NLCL0280Constant;

import business.servlet.NLCL0280.common.NLCL0280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * OpenWin_LocInfo Inventory Transaction Inqiury
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   CITS            T.Tokutomi      Create          N/A
 *</pre>
 */
public class NLCL0280_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        // Screen Protect Ctrl
        NLCL0280CommonLogic.ctrlScrnItemProtection(scrnMsg, this);

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H1, scrnMsg.P.no(6).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, scrnMsg.P.no(7).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_H1, scrnMsg.P.no(8).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_H1, scrnMsg.P.no(9).xxPopPrm);

            // Set Focus
            scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H1);
        }
    }
}
