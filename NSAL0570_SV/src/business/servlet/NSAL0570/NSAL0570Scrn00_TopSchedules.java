/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0570;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0570.NSAL0570CMsg;
//import business.servlet.NSAL0570.constant.NSAL0570Constant;

import business.servlet.NSAL0570.common.NSAL0570CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Hitachi         K.Kasai         Create          N/A
 * 2015/12/10   Hitachi         A.Kohinata      Update          QC1718
 * 2015/12/23   Hitachi         K.Yamada        Update          CSA QC#1983
 * 2016/02/16   Hitachi         K.Kishimoto     Update          QC#3846
 *</pre>
 */
public class NSAL0570Scrn00_TopSchedules extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;
        NSAL0570CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        // add start 2015/12/23 CSA Defect#1983
        int targetRow = scrnMsg.xxRadioBtn_A.getValueInt();
        if (targetRow < 0) {
            scrnMsg.setMessageInfo("NSAM0034E");
            throw new EZDFieldErrorException();
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(targetRow).dsContrPrcEffPk_A1)) {
            scrnMsg.setMessageInfo("NSAM0135E", new String[]{"The selected pricing effectivity"});
            throw new EZDFieldErrorException();
        }
        // add end 2015/12/23 CSA Defect#1983

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // mod start 2015/12/23 CSA Defect#1983
        //NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;

        //NSAL0570CMsg bizMsg = new NSAL0570CMsg();
        //bizMsg.setBusinessID("NSAL0570");
        //bizMsg.setFunctionCode("40");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;
        return null;
        // mod end 2015/12/23 CSA Defect#1983
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;
        // mod start 2015/12/23 CSA Defect#1983
        //NSAL0570CMsg bizMsg  = (NSAL0570CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //Del Start 02/16/2016 <QC#3846>
//      NSAL0570CommonLogic.copyAMsgToBMsg(scrnMsg);
        //Del End   02/16/2016 <QC#3846>

        // START 2015/12/10 [QC1718, ADD]
        //NSAL0570CommonLogic.addCheckItem(scrnMsg);
        //scrnMsg.putErrorScreen();
        // END 2015/12/10 [QC1718, ADD]

        this.setResult("yes");
        Object[] params = new Object[3];
        int targetRow = scrnMsg.xxRadioBtn_A.getValueInt();
        params[0] = scrnMsg.dsContrDtlPk_H1;
        params[1] = scrnMsg.A.no(targetRow).dsContrPrcEffPk_A1;
        params[2] = scrnMsg.xxModeCd_H1;
        setArgForSubScreen(params);
        // mod end 2015/12/25 CSA Defect#1983

    }
}
