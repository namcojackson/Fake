/*
 * <pre>Copyright (c) 20153 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0630E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1330.NSAL1330CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_Del_Accessory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        boolean checkFlg = false;
        for (int i = 0; i < scrnMsg.J.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.J.no(i).xxChkBox_J.getValue())) {
                checkFlg = true;
            }
        }
        if (!checkFlg) {
            for (int i = 0; i < scrnMsg.J.getValidCount(); i++) {
                scrnMsg.J.no(i).xxChkBox_J.setErrorInfo(1, NSAM0630E, new String[] {"Accessory Charge CheckBox" });
                scrnMsg.addCheckItem(scrnMsg.J.no(i).xxChkBox_J);
            }
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        NSAL1330CMsg bizMsg = new NSAL1330CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.prcCatgNm_HJ);
    }
}