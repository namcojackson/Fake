/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0560;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0560.common.NSAL0560CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2015/12/03   Hitachi         A.Kohinata      Update          QC1427
 * 2015/12/07   Hitachi         T.Kanasaka      Update          QC#1464
 * 2015/12/15   Hitachi         K.Yamada        Update          CSA QC#1983
 *</pre>
 */
public class NSAL0560Scrn00_TopSchedules extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        NSAL0560CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        // add start 2015/12/15 CSA Defect#1983
        int targetRow = scrnMsg.xxRadioBtn_A.getValueInt();
        if (targetRow < 0) {
            scrnMsg.setMessageInfo("NSAM0034E");
            throw new EZDFieldErrorException();
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(targetRow).dsContrPrcEffPk_A1)) {
            scrnMsg.setMessageInfo("NSAM0135E", new String[]{"The selected pricing effectivity"});
            throw new EZDFieldErrorException();
        }
        // add end 2015/12/15 CSA Defect#1983
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // mod start 2015/12/15 CSA Defect#1983
        //NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        //NSAL0560CMsg bizMsg = new NSAL0560CMsg();
        //bizMsg.setBusinessID(BUSINESS_ID);
        //bizMsg.setFunctionCode("40");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;
        return null;
        // mod end 2015/12/15 CSA Defect#1983
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        // mod start 2015/12/15 CSA Defect#1983
        //NSAL0560CMsg bizMsg  = (NSAL0560CMsg) cMsg;
        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2015/12/07 T.Kanasaka [QC#1464, DEL]
//        NSAL0560CommonLogic.copyAMsgToBMsg(scrnMsg);
        // END 2015/12/07 T.Kanasaka [QC#1464, DEL]

        this.setResult("yes");
        // START 2015/12/07 T.Kanasaka [QC#1464, ADD]
        NSAL0560CommonLogic.copyAMsgToBMsg(scrnMsg);
        // END 2015/12/07 T.Kanasaka [QC#1464, ADD]
        Object[] params = new Object[3];
        int targetRow = scrnMsg.xxRadioBtn_A.getValueInt();
        params[0] = scrnMsg.dsContrDtlPk_H1;
        params[1] = scrnMsg.A.no(targetRow).dsContrPrcEffPk_A1;
        params[2] = scrnMsg.xxModeCd_H1;
        setArgForSubScreen(params);
        // mod end 2015/12/15 CSA Defect#1983
    }
}
