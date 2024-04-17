/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0570;

import static business.servlet.NSAL0570.constant.NSAL0570Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0570.NSAL0570CMsg;
import business.servlet.NSAL0570.common.NSAL0570CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Hitachi         K.Kasai         Create          N/A
 * 2016/02/16   Hitachi         K.Kishimoto     Update          QC#3846
 *</pre>
 */
public class NSAL0570Scrn00_DeletePrcAllowRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;

        NSAL0570CMsg bizMsg = new NSAL0570CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
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
        scrnMsg.xxRadioBtn_B.setValue(0);
        scrnMsg.setFocusItem(scrnMsg.A.no(0).xsMtrCopyQty_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_A, scrnMsg.rowSqNum_PV);
        //Del Start 02/16/2016 <QC#3846>
//        NSAL0570CommonLogic.copyAMsgToBMsg(scrnMsg);
        //Del End   02/16/2016 <QC#3846>

    }
}
