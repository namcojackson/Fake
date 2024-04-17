/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0490.NSAL0490CMsg;
import business.servlet.NSAL0490.constant.NSAL0490Constant;

import business.servlet.NSAL0490.common.NSAL0490CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/19   Hitachi         K.Kasai         Create          QC#447
 * 2017/07/25   CITS            S.Endo          Update          Sol#072(QC#18386)
 *</pre>
 */
public class NSAL0490Scrn00_TAB_EndOfLife extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;

        NSAL0490CMsg bizMsg = new NSAL0490CMsg();
        bizMsg.setBusinessID(NSAL0490Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSAL0490Constant.FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
        NSAL0490CMsg bizMsg  = (NSAL0490CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.clearAllGUIAttribute(NSAL0490Constant.SCREEN_ID_00);

        if (NSAL0490CommonLogic.hasUpdateFuncId()) {
            NSAL0490CommonLogic.controlEndOfLifeField(scrnMsg, true);
        } else {
            NSAL0490CommonLogic.controlEndOfLifeField(scrnMsg, false);
        }

        NSAL0490CommonLogic.controlHdrField(this, scrnMsg, false);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
        NSAL0490CommonLogic.controlCommonTabBtn(scrnMsg, true, true, true, false, true);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
        scrnMsg.setFocusItem(scrnMsg.dsMdlEolStsCd_D1);
        scrnMsg.xxDplyTab.setValue(NSAL0490Constant.TAB_END_OF_LIFE);

    }
}
