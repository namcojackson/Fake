/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0410;

import static business.servlet.NSAL0410.constant.NSAL0410Constant.BIZ_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0410.NSAL0410CMsg;
import business.servlet.NSAL0410.common.NSAL0410CommonLogic;
import business.servlet.NSAL0410.constant.NSAL0410Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Additional Charge
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/24   Fujitsu         C.Naito         Create          N/A
 *</pre>
 */
public class NSAL0410Scrn00_Delete extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        // clear scroll position
        scrnMsg.xxListNum_AX.clear();
        scrnMsg.xxListNum_AY.clear();

        // spread no data, no select data
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);

        if (scrnMsg.A.getValidCount() == 0 || selectedRows.isEmpty()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (!scrnMsg.A.no(i).xxChkBox_A.isInputProtected()) {
                    scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NSAL0410Constant.NSAM0034E);
                }
            }
            scrnMsg.A.setCheckParam(new String[] {NSAL0410Bean.xxChkBox_A }, 1);
            scrnMsg.addCheckItem(scrnMsg.A);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        // for Delete Event 2nd time
        if (!ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxPgFlg_DE.getValue())) {
            // Warning Message
            for (int i = 0; i < selectedRows.size(); i++) {
                int checkedRowNum = selectedRows.get(i);
                scrnMsg.A.no(checkedRowNum).xxChkBox_A.setErrorInfo(2, NSAL0410Constant.NSAM0340W);
            }
            scrnMsg.A.setCheckParam(new String[] {NSAL0410Bean.xxChkBox_A }, 1);
            scrnMsg.addCheckItem(scrnMsg.A);
            scrnMsg.putErrorScreen();
            // for Delete
            scrnMsg.xxPgFlg_DE.setValue(ZYPConstant.CHKBOX_ON_Y);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        NSAL0410CMsg bizMsg = new NSAL0410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;
        NSAL0410CMsg bizMsg = (NSAL0410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0410CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID(), getGlobalCompanyCode(), false);
        // set Focus
        scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A);

        // for Delete
        scrnMsg.xxPgFlg_DE.clear();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            // Search do not set error message on fields.
            throw new EZDFieldErrorException();
        }
        scrnMsg.setMessageInfo(NSAL0410Constant.NZZM0002I);
    }
}
