/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1030;

import static business.servlet.NLCL1030.constant.NLCL1030Constant.BIZ_APP_ID;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1030.NLCL1030CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NLCL1030 Inventory ABC Analysis Search
 * Function Name : Change Search Option
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/21/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NLCL1030Scrn00_OnChangeSavedSearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1030BMsg scrnMsg = (NLCL1030BMsg) bMsg;

        NLCL1030CMsg bizMsg = new NLCL1030CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1030BMsg scrnMsg = (NLCL1030BMsg) bMsg;
        NLCL1030CMsg bizMsg = (NLCL1030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        if (scrnMsg.srchOptPk_S.isError()) {
            scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
            scrnMsg.putErrorScreen();
        } else {
            scrnMsg.setFocusItem(scrnMsg.srchOptPk_S);
        }

    }
}