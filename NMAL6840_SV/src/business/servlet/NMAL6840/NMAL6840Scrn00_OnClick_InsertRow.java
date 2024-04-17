/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6840;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6840.NMAL6840CMsg;
import business.servlet.NMAL6840.common.NMAL6840CommonLogic;
import business.servlet.NMAL6840.constant.NMAL6840Constant.BTN_APP;
import business.servlet.NMAL6840.constant.NMAL6840Constant.BTN_CMN;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <p>
 * Business ID : NMAL6840 Sub WH Setup<br>
 * Function Name : The business process for OnClick Insert Row<br>
 * </p>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/27/2015   CITS            M.Ito           Create          N/A
 * </pre>
 */
public class NMAL6840Scrn00_OnClick_InsertRow extends S21CommonHandler {

    /*
     * (non-Javadoc)
     * @see
     * com.canon.cusa.s21.framework.online.servlet.S21CommonHandler
     * #checkInput(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg)
     */
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do Nothing.
    }

    /*
     * (non-Javadoc)
     * @see
     * com.canon.cusa.s21.framework.online.servlet.S21CommonHandler
     * #setRequestData(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg)
     */
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return NMAL6840CommonLogic.copyScrnMsgToBizMsgForSearch((NMAL6840BMsg) bMsg);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.canon.cusa.s21.framework.online.servlet.S21CommonHandler
     * #doProcess(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg, parts.common.EZDCMsg)
     */
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6840BMsg scrnMsg = (NMAL6840BMsg) bMsg;
        EZDMsg.copy((NMAL6840CMsg) cMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR != scrnMsg.getMessageType()) {
            // Gets the added row.
            NMAL6840_ABMsg lastRow = scrnMsg.A.no(scrnMsg.A.getValidCount() - 1);
            // enable/disable.
            NMAL6840CommonLogic.enableInputFields(lastRow);
            // display name.
            NMAL6840CommonLogic.setDisplayName(lastRow);
            // digit.
            NMAL6840CommonLogic.setFractionDigit(lastRow);
            // focus on Sub WH Code
            scrnMsg.setFocusItem(lastRow.rtlSwhCd_A1);
        }

        // button.
        NMAL6840CommonLogic.activateButton(BTN_CMN.SUBMIT, this);
        NMAL6840CommonLogic.activateButton(BTN_APP.DELETE_ROW, this);
    }
}
