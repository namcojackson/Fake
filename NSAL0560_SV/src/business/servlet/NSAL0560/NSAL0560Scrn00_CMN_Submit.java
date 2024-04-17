/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0560;

import static business.servlet.NSAL0560.common.NSAL0560CommonLogic.initialControlScreen;
import static business.servlet.NSAL0560.constant.NSAL0560Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0560.NSAL0560CMsg;

import business.servlet.NSAL0560.common.NSAL0560CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2015/12/10   Hitachi         T.Kanasaka      Update          QC#1815
 * 2016/02/08   Hitachi         K.Kishimoto     Update          QC#3884, QC#3891, QC#3898
 *</pre>
 */
public class NSAL0560Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        NSAL0560CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        NSAL0560CMsg bizMsg = new NSAL0560CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

	//Mod Start 02/08/2016 <QC#3884, QC#3891, QC#3898>
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        NSAL0560CMsg bizMsg  = (NSAL0560CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2015/12/10 T.Kanasaka [QC#1815, ADD]
        NSAL0560CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        // END 2015/12/10 T.Kanasaka [QC#1815, ADD]
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        NSAL0560CommonLogic.copyAMsgToBMsg(scrnMsg);
        initialControlScreen(this, scrnMsg);
    }
    //Mod Start 02/08/2016 <QC#3884, QC#3891, QC#3898>
}
