/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0570;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0570.NSAL0570CMsg;
import business.servlet.NSAL0570.common.NSAL0570CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2016/02/16   Hitachi         K.Kishimoto     Update          QC#3846
 *</pre>
 */
public class NSAL0570Scrn00_DeleteRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;

        NSAL0570CMsg bizMsg = new NSAL0570CMsg();
        bizMsg.setBusinessID("NSAL0570");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;
        NSAL0570CMsg bizMsg  = (NSAL0570CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
        scrnMsg.xxRadioBtn_A.setValue(0);
        scrnMsg.setFocusItem(scrnMsg.A.no(0).contrPrcEffFromDt_A1);
        
        //Del Start 02/16/2016 <QC#3846>
//        NSAL0570CommonLogic.copyAMsgToBMsg(scrnMsg);
        //Del End   02/16/2016 <QC#3846>

    }
}
