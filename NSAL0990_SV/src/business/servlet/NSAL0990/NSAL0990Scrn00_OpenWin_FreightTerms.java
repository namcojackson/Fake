/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0990.NSAL0990CMsg;
//import business.servlet.NSAL0990.constant.NSAL0990Constant;

import business.servlet.NSAL0990.common.NSAL0990CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2018/11/14   Fujitsu         M.Yamada        Update          QC#29121
 *</pre>
 */
public class NSAL0990Scrn00_OpenWin_FreightTerms extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        // QC#29121
        //        Object[] params = NSAL0990CommonLogic.getParamNMAL6050ForFrtTerm(scrnMsg);
        Object[] params = NSAL0990CommonLogic.getParamNWAL1130ForFrtTerm(scrnMsg);

        setArgForSubScreen(params);

    }
}
