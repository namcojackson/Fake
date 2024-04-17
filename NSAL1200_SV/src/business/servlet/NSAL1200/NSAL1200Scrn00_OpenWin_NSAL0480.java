/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1200;

import static business.servlet.NSAL1200.constant.NSAL1200Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1200Scrn00_OpenWin_NSAL0480 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;
        scrnMsg.xxPopPrm_00.clear();

        Object[] params = new Object[PRM_LENGTH_NSAL0480];
        params[PRM_INDEX_NSAL0480_MDL_NM] = scrnMsg.xxPopPrm_00;
        params[PRM_INDEX_NSAL0480_ITEM_CD] = scrnMsg.xxPopPrm_00;
        params[PRM_INDEX_NSAL0480_MTR_GRP_PK] = scrnMsg.mtrGrpPk_H;

        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}
