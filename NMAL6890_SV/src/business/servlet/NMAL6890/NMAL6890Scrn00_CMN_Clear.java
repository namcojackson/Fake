/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6890;

import static business.servlet.NMAL6890.constant.NMAL6890Constant.BIZ_APP_ID;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6890.NMAL6890CMsg;
import business.servlet.NMAL6890.common.NMAL6890CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NMAL6890 Warehouse Search
 * Function Name : Clear
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Y.Kuroda        Create          N/A
 *</pre>
 */
public class NMAL6890Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6890BMsg scrnMsg = (NMAL6890BMsg) bMsg;

        NMAL6890CommonLogic.clearScreenMsg(scrnMsg);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6890BMsg scrnMsg = (NMAL6890BMsg) bMsg;

        NMAL6890CMsg bizMsg = new NMAL6890CMsg();

        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6890BMsg scrnMsg = (NMAL6890BMsg) bMsg;
        NMAL6890CMsg bizMsg = (NMAL6890CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        // focus on Warehouse Name.
        scrnMsg.setFocusItem(scrnMsg.rtlWhNm_H1);

    }
}
