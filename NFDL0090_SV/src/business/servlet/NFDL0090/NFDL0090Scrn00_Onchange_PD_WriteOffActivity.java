/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import static business.servlet.NFDL0090.constant.NFDL0090Constant.*;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/11/24   Hitachi         S.Naya          Create          QC#57252
 *</pre>
 */
public class NFDL0090Scrn00_Onchange_PD_WriteOffActivity extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;

        setDisplay(scrnMsg);
    }

    private void setDisplay(NFDL0090BMsg scrnMsg) {
        if (OTHER_CODE.equals(scrnMsg.arAdjTpCd_FS.getValue())) {
            scrnMsg.xxCmntTxt_FS.setInputProtected(false);
            setButtonEnabled(BTN_A, true);
        } else {
            scrnMsg.xxCmntTxt_FS.setInputProtected(true);
            setButtonEnabled(BTN_A, false);
        }
    }
}
