/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL3000.common.NFCL3000CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/10/24   Fujitsu         S.Takami        Create          QC#27069
 *</pre>
 */
public class NFCL3000Scrn00_SerNumSearch_A extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //do Nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        int slctLineIdx = getButtonSelectNumber();
        if (slctLineIdx >= 0) {
            scrnMsg.xxCellIdx.setValue(slctLineIdx);
        }

//        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
//        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        setArgForSubScreen(NFCL3000CommonLogic.getParamNSAL1240(scrnMsg, scrnMsg.A.no(slctLineIdx)));
    }
}
