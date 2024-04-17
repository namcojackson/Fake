/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2015/11/04   Hitachi         T.Kanasaka      Update          N/A
 * 2016/02/17   Hitachi         T.Kanasaka      Update          QC2879
 * 2017/11/16   Hitachi         M.Kidokoro      Update          QC#22294
 * 2018/06/18   Fujitsu         T.Murai         Update          QC#26567
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_Add_Detail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        // START 2016/02/17 T.Kanasaka [QC2879, MOD]
//        NSAL0300CommonLogic.addCheckItem(scrnMsg);
//        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsContrStsCd)) {
            scrnMsg.setMessageInfo(NSAM0426E);
            throw new EZDFieldErrorException();
        }
        // END 2016/02/17 T.Kanasaka [QC2879, MOD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);
        // START 2018/06/18 T.Murai [QC#26567, MOD]
//        // START 2017/11/16 M.Kidokoro [QC#22294, MOD]
////        Object[] prm = new Object[2];
//        Object[] prm = new Object[5];
//        // END 2017/11/16 M.Kidokoro [QC#22294, MOD]
//        prm[0] = scrnMsg.dsContrPk;
//        prm[1] = scrnMsg.P;
//        // START 2017/11/16 M.Kidokoro [QC#22294, ADD]
//        prm[2] = scrnMsg.contrVrsnEffFromDt;
//        prm[3] = scrnMsg.contrVrsnEffThruDt;
//        prm[4] = scrnMsg.dsAcctNum;
//        // END 2017/11/16 M.Kidokoro [QC#22294, ADD]
        Object[] prm = new Object[6];
        prm[0] = scrnMsg.dsContrPk;
        prm[1] = scrnMsg.P;
        prm[2] = scrnMsg.xxPopPrm_00; // Serial Number
        prm[3] = scrnMsg.contrVrsnEffFromDt;
        prm[4] = scrnMsg.contrVrsnEffThruDt;
        prm[5] = scrnMsg.dsAcctNum;
        // END 2018/06/18 T.Murai [QC#26567, MOD]
        setArgForSubScreen(prm);
    }
}
