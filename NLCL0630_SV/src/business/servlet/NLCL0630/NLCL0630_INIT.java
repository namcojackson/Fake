/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0630;

import static business.servlet.NLCL0630.constant.NLCL0630Constant.BIZ_APP_ID;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0630.NLCL0630CMsg;
import business.servlet.NLCL0630.common.NLCL0630CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0630 Tech PI Inquiry
 * Function Name : Init
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/26/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NLCL0630_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0630BMsg scrnMsg = (NLCL0630BMsg) bMsg;

        NLCL0630CMsg bizMsg = new NLCL0630CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0630BMsg scrnMsg = (NLCL0630BMsg) bMsg;
        NLCL0630CMsg bizMsg  = (NLCL0630CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL0630CommonLogic.ctrlScrnItemDispInit(this, scrnMsg);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.techTocCd);

    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {
        NLCL0630CommonLogic.setNameForMessage((NLCL0630BMsg) scrnMsg);
    }

}
