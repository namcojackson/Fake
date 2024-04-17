/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1540;

import static business.servlet.NPAL1540.constant.NPAL1540Constant.BIZ_APP_ID;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1540.NPAL1540CMsg;
import business.servlet.NPAL1540.common.NPAL1540CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 * <pre>
 * Business ID : NPAL1540 Manual ASN Entry
 * Function Name : Init
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/19   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPAL1540_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length == 1) {
            EZDBStringItem param01 = (EZDBStringItem) params[0];
            ZYPEZDItemValueSetter.setValue(scrnMsg.asnSoNum, param01);
        }

        NPAL1540CMsg bizMsg = new NPAL1540CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;
        NPAL1540CMsg bizMsg  = (NPAL1540CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1540CommonLogic.ctrlScrnItemDispInit(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.asnSoNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {
        NPAL1540CommonLogic.setNameForMessage((NPAL1540BMsg) scrnMsg);
    }

}
