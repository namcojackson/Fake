/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0610;

import static business.servlet.NLCL0610.constant.NLCL0610Constant.DISPLAY_NM_RTL_WH_CD;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.SCRN_ID;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.ZZZM9007E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0610.NLCL0610CMsg;
import business.servlet.NLCL0610.common.NLCL0610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * PI Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NLCL0610Scrn00_OnClick_SetWhNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0610BMsg scrnMsg = (NLCL0610BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
            scrnMsg.rtlWhNm.clear();
            scrnMsg.rtlWhCd.setErrorInfo(1, ZZZM9007E, new String[] {DISPLAY_NM_RTL_WH_CD});
        }

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0610BMsg scrnMsg = (NLCL0610BMsg) bMsg;

        return NLCL0610CommonLogic.copyScrnMsgToBizMsgForSearch(scrnMsg);
    }
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0610BMsg scrnMsg = (NLCL0610BMsg) bMsg;
        NLCL0610CMsg bizMsg  = (NLCL0610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        NLCL0610CommonLogic.setRadioButtonAttribute(scrnMsg);

        NLCL0610CommonLogic.setTableScreen(scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.putErrorScreen();
    }
}
