/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0640;

import static business.servlet.NLCL0640.constant.NLCL0640Constant.BIZ_APP_ID;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0640.NLCL0640CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0640 Tech PI Count
 * Function Name : Search Item
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/09/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NLCL0640Scrn00_Search_MdseName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;

        NLCL0640CMsg bizMsg = new NLCL0640CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;
        NLCL0640CMsg bizMsg  = (NLCL0640CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            scrnMsg.addCheckItem(scrnMsg.mdseCd);
            scrnMsg.putErrorScreen();
        }

    }
}
