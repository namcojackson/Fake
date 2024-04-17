/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0190;

import static business.servlet.NMAL0190.constant.NMAL0190Constant.BIZ_ID;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.BTN_CMN_SUB;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.NZZM0002I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0190.NMAL0190CMsg;
import business.servlet.NMAL0190.common.NMAL0190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;


/**
 *<pre>
 * NMAL0190Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         T.Arima         Create          N/A
 * 2016/03/07   CITS            S.Tanikawa      UPDATE          QC#2669
 *</pre>
 */
public class NMAL0190Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    /**
     * Check Input Event
     * - Check input field, and addCheckItem.
     * - If has ErrorMessage, show them.
     */
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0190BMsg scrnMsg = (NMAL0190BMsg) bMsg;
        // UPDATE START 2016/03/07 QC#2669
        NMAL0190CommonLogic.checkCompatibleDelete(scrnMsg);
        NMAL0190CommonLogic.addCheckAllInput(scrnMsg);
        scrnMsg.putErrorScreen();

        // List<Integer> selectedRows =
        // ZYPTableUtil.getSelectedRows(scrnMsg.A,
        // TBL_ITEM_XX_CHECKBOX, CHKBOX_ON_Y);
        // if (selectedRows.size() == 0) {
        // scrnMsg.setMessageInfo(NMAM8122E);
        // throw new EZDFieldErrorException();
        // }
        // UPDATE END 2016/03/07 QC#2669
    }

    @Override
    /**
     * Set Request Date Event
     * - do submit action.
     */
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0190BMsg scrnMsg = (NMAL0190BMsg) bMsg;

        NMAL0190CMsg bizMsg = new NMAL0190CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    /**
     * Do Process Event
     * - If has ErrorMessage, Show them.
     * - Set Focus Item - Forward.
     */
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0190BMsg scrnMsg = (NMAL0190BMsg) bMsg;
        NMAL0190CMsg bizMsg = (NMAL0190CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // UPDATE START 2016/03/07 QC#2669
        NMAL0190CommonLogic.addCheckAllInput(scrnMsg);
        scrnMsg.putErrorScreen();

        // Display Error Message
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        // Display Success Message
        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
        this.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        scrnMsg.setFocusItem(scrnMsg.B.no(0).xxSupdFlg_FW);
        // UPDATE END 2016/03/07 QC#2669
    }
}
