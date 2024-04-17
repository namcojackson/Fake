/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL4500;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL4500.common.NMAL4500CommonLogic;
import business.servlet.NMAL4500.constant.NMAL4500Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/09/19   Fujitsu         N.Sugiura       Create          MEX-LC004
 *</pre>
 */

public class NMAL4500Scrn00_OpenWin_Currency_Code extends S21CommonHandler implements NMAL4500Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
        // Set original event id
        scrnMsg.xxScrEventNm_01.setValue(POPUP_ACTION_CURRENCY_CODE);

        // Init param
        NMAL4500CommonLogic.setInitParam_Currency(scrnMsg);

        // Pass param
        Object[] params = NMAL4500CommonLogic.getParamOpenWin_NMAL6050(scrnMsg);

        setArgForSubScreen(params);
    }
}
