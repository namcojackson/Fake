/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.copyScrnMsgToBizMsgForSearch;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setDisplayNameForMessageOfContactInfo;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6860.NMAL6860CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID   : NMAL6860 Supplier Entry
 * Function Name : The business process for On Click Add Contact Info.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/06   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NMAL6860Scrn00_OnClick_AddContactInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return copyScrnMsgToBizMsgForSearch((NMAL6860BMsg) bMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        EZDMsg.copy((NMAL6860CMsg) cMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            return;
        }

        NMAL6860_BBMsg lastRow = scrnMsg.B.no(scrnMsg.B.getValidCount() - 1);

        // sets the display name.
        setDisplayNameForMessageOfContactInfo(lastRow);

        // focus on Site Number
        scrnMsg.setFocusItem(lastRow.ctacPsnLastNm_B);
    }
}
