/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0010.NSAL0010CMsg;
import business.servlet.NSAL0010.common.NSAL0010CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/30   Hitachi         T.Tomita        Create          QC#5398
 * 2018/08/23   Hitachi         K.Kitachi       Update          QC#27773
 *</pre>
 */
public class NSAL0010Scrn00_OnBlur_MdseCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CMsg bizMsg = new NSAL0010CMsg();

        if (!hasValue(scrnMsg.mdseCd_H1)) {
            // START 2018/08/23 K.Kitachi [QC#27773, ADD]
            scrnMsg.stdWtyFlg_H1.clear();
            // END 2018/08/23 K.Kitachi [QC#27773, ADD]
            return null;
        }

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;

        // START 2018/08/23 K.Kitachi [QC#27773, MOD]
//        if (!hasValue(scrnMsg.mdseCd_H1)) {
//            return;
//        }
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        NSAL0010CommonLogic.addCheckItem(scrnMsg);
//        scrnMsg.putErrorScreen();
        if (hasValue(scrnMsg.mdseCd_H1)) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            NSAL0010CommonLogic.addCheckItem(scrnMsg);
            scrnMsg.putErrorScreen();
        }
        NSAL0010CommonLogic.controlScreenFields(this, scrnMsg, null, true, getUserProfileService());
        // END 2018/08/23 K.Kitachi [QC#27773, MOD]
    }
}
