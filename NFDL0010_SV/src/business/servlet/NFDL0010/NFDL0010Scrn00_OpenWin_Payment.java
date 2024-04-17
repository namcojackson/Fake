/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0010;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFDL0010.common.NFDL0010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFDL0010Scrn00_OpenWin_Payment extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;

        List<Integer> chkNoList = new ArrayList<Integer>();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).xxChkBox_A1.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                chkNoList.add(i);
            }
        }

        if (chkNoList.size() == 0) {
            scrnMsg.setMessageInfo("NFAM0075E");
        } else if (chkNoList.size() > 1) {
            scrnMsg.setMessageInfo("NFBM0064E");
        }
        NFDL0010CommonLogic.initialize(this, scrnMsg);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageInfo() != null && scrnMsg.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            // Search do not set error message on fields.
            throw new EZDFieldErrorException(); 
        }

        Object[] params = setPopupParameter(scrnMsg, chkNoList.get(0));
        setArgForSubScreen(params);
    }

    private Object[] setPopupParameter(NFDL0010BMsg scrnMsg, int idx) {

        scrnMsg.xxScrEventNm.setValue("OpenWin_Payment");
        Object[] params = new Object[1];
        params[0] = scrnMsg.A.no(idx).dsAcctNum_A1;
        return params;
    }
}
