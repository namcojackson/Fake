/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0270;

import static business.servlet.NFAL0270.common.NFAL0270CommonLogic.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NFAL0270.constant.NFAL0270Constant.*;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/17   Hitachi         G.Quan          Create          QC#61387
 *</pre>
 */
public class NFAL0270Scrn00_OpenWin_mdlGrpId_A extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

         NFAL0270BMsg scrnMsg = (NFAL0270BMsg) bMsg;
         scrnMsg.xxScrEventNm.setValue(BTN_OPEN_WIN_MDL_GRP_ID_A);

         // set param
         setParamMdlGrpA(scrnMsg, getButtonSelectNumber());
         setArgForSubScreen(getParamNMAL6050(scrnMsg));

    }
}
