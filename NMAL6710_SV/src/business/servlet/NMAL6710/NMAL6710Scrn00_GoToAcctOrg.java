/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6710.NMAL6710CMsg;
//import business.servlet.NMAL6710.constant.NMAL6710Constant;

import business.servlet.NMAL6710.common.NMAL6710CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Fujitsu         N.Sugiura       Create          N/A
 *</pre>
 */
public class NMAL6710Scrn00_GoToAcctOrg extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();
        NMAL6710_CBMsg aBMsg = scrnMsg.C.no(selectIdx);

        scrnMsg.P.clear();
        scrnMsg.P.no(0).xxPopPrm.setValue(aBMsg.dsAcctNum_C2.getValue());

        Object[] params = NMAL6710CommonLogic.toArrayPopup(scrnMsg.P, 2);

        setArgForSubScreen(params);

    }
}
