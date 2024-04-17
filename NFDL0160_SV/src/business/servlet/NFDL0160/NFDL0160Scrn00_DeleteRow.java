/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0160;

import static business.servlet.NFDL0160.constant.NFDL0160Constant.NFDM0024E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFDL0160.common.NFDL0160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   Fujitsu         C.Naito         Create          N/A
 *</pre>
 */
public class NFDL0160Scrn00_DeleteRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;

        // spread no data
        if (scrnMsg.A.getValidCount() == 0) {
            // for inActiveForm error clear
            NFDL0160CommonLogic.inActiveFormErrorClear(scrnMsg);
            // set ErrorMessage
            scrnMsg.setMessageInfo(NFDM0024E);
            throw new EZDFieldErrorException();

        } else {

            // no select data
            List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);

            if (selectedRows.isEmpty()) {
                // for inActiveForm error clear
                NFDL0160CommonLogic.inActiveFormErrorClear(scrnMsg);

                boolean allChkboxProtected = true;
                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    if (!scrnMsg.A.no(i).xxChkBox_A.isInputProtected()) {
                        scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NFDM0024E);
                        allChkboxProtected = false;
                    }
                }
                if (allChkboxProtected) {
                    // set ErrorMessage
                    scrnMsg.setMessageInfo(NFDM0024E);
                    throw new EZDFieldErrorException();
                }

                scrnMsg.A.setCheckParam(new String[] {NFDL0160Bean.xxChkBox_A }, 1);
                scrnMsg.addCheckItem(scrnMsg.A);
                scrnMsg.putErrorScreen();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        ZYPTableUtil.deleteRows(scrnMsg.A, selectedRows);

    }
}
