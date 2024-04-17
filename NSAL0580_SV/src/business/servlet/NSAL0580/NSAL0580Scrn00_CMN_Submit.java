/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0580;

import static business.servlet.NSAL0580.constant.NSAL0580Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0580.NSAL0580CMsg;
//import business.servlet.NSAL0580.constant.NSAL0580Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         J.Kim           Create          N/A
 * 2016/02/26   Hitachi         K.Kishimoto     Update          QC#2011
 *</pre>
 */
public class NSAL0580Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0580BMsg scrnMsg = (NSAL0580BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);
        scrnMsg.addCheckItem(scrnMsg.dsContrCtrlStsNm);
        //Mod Start 02/26/2016 <QC#2011>
        scrnMsg.addCheckItem(scrnMsg.dsContrCtrlStsCd_H);
        //Mod End   02/26/2016 <QC#2011>
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0580BMsg scrnMsg = (NSAL0580BMsg) bMsg;

        NSAL0580CMsg bizMsg = new NSAL0580CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0580BMsg scrnMsg = (NSAL0580BMsg) bMsg;
        NSAL0580CMsg bizMsg  = (NSAL0580CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //Mod Start 02/26/2016 <QC#2011>
        scrnMsg.addCheckItem(scrnMsg.dsContrCtrlStsCd_H);
        //Mod End   02/26/2016 <QC#2011>
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }
}
