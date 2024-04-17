/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_SRC_RTL_WH_CD;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.ZZZM9025E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Open Return to Config Search Popup(NSAL1240)
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            Makoto Okigami  Create          N/A
 * 09/13/2016   CITS            K.Ogino         Update          QC#11540
 *</pre>
 */
public class NPBL0020Scrn00_OpenWin_ConfigID extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd)) {
            scrnMsg.srcRtlWhCd.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_SRC_RTL_WH_CD });
        }
        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        NPBL0020CommonLogic.setInitParamForConfigPopup(scrnMsg);

        // Making of subscreen delivery information
        Object[] params = NPBL0020CommonLogic.setParamForConfigPopup(scrnMsg);

        // Subscreen transition
        setArgForSubScreen(params);

    }
}
