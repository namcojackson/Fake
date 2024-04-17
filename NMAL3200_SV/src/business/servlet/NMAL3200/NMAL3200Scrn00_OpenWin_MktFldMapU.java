/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL3200.common.NMAL3200CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * Function Name : OpenWin_MktFldMapU
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 */
public class NMAL3200Scrn00_OpenWin_MktFldMapU extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        Object[] params = NMAL3200CommonLogic.createNWAL1130Params(scrnMsg, scrnMsg.mktgFldMapNm_DB);
        setArgForSubScreen(params);
    }
}
