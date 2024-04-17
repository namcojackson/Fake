/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3150;

import static business.servlet.NLBL3150.constant.NLBL3150Constant.BIZ_ID;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_LINE_CANCEL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_SELECT_ALL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_UN_SELECT_ALL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.CHKBOX_ON_A_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.FUNC_CD_SRCH;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.NMAM8054E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3150.NLBL3150CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NLBL3150Scrn00_LineCancel extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3150BMsg scrnMsg = (NLBL3150BMsg) bMsg;

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, CHKBOX_ON_A_NM, ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.size() == 0) {
            scrnMsg.setMessageInfo(NMAM8054E);
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3150BMsg scrnMsg = (NLBL3150BMsg) bMsg;

        NLBL3150CMsg bizMsg = new NLBL3150CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3150BMsg scrnMsg = (NLBL3150BMsg) bMsg;
        NLBL3150CMsg bizMsg = (NLBL3150CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (scrnMsg.A.getValidCount() == 0) {
            this.setButtonEnabled(BTN_SELECT_ALL, false);
            this.setButtonEnabled(BTN_UN_SELECT_ALL, false);
            this.setButtonEnabled(BTN_LINE_CANCEL, false);
        } else {
            this.setButtonEnabled(BTN_SELECT_ALL, true);
            this.setButtonEnabled(BTN_UN_SELECT_ALL, true);
            this.setButtonEnabled(BTN_LINE_CANCEL, true);
        }

    }
}
