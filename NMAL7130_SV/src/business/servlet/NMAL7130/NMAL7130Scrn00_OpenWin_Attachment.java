/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130;

import static business.servlet.NMAL7130.constant.NMAL7130Constant.BIZ_ID;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.DISPLAY_MODE_MODIFICATION;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.DISPLAY_MODE_READ_ONLY;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.NMAL7130_ATT_LIMIT;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.NMAL7130_AUTHORIZE_FILE_EXT;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.NMAL7130_AUTHORIZE_FILE_VOL;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.PRC_CONTR_ATT_TP;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.SCRN_ATT_NM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7130.common.NMAL7130CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7130Scrn00_OpenWin_Attachment
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/17   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/22   Fujitsu         W.Honda         Update          CSA-QC#3636
 *</pre>
 */
public class NMAL7130Scrn00_OpenWin_Attachment extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2016/02/22 W.Honda [S21 CSA QC#3636 ADD]
        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        NMAL7130CommonLogic.addCheckScreenItem(scrnMsg);

        scrnMsg.putErrorScreen();
        // END 2016/02/22 W.Honda [S21 CSA QC#3636 ADD]

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        // Set Params
        Object[] params = new Object[9];

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxYesNoCd.getValue())) {
            // Edit
            params[0] = DISPLAY_MODE_MODIFICATION;
        } else {
            // Read Only
            params[0] = DISPLAY_MODE_READ_ONLY;
        }
        params[1] = BIZ_ID;
        params[2] = scrnMsg.prcContrPk_BK.getValue().toString();
        params[3] = SCRN_ATT_NM;
        params[4] = scrnMsg.prcContrPk_H1.getNameForMessage();
        //params[5] = PRC_CONTR_ATT_TP;
        params[5] = null;
        params[6] = NMAL7130_ATT_LIMIT;
        params[7] = NMAL7130_AUTHORIZE_FILE_EXT;
        params[8] = NMAL7130_AUTHORIZE_FILE_VOL;
        setArgForSubScreen(params);
    }
}
