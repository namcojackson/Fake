/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1550;

import static business.servlet.NWAL1550.constant.NWAL1550Constant.BIZ_ID;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.NWAM0014E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1550.NWAL1550CMsg;
import business.servlet.NWAL1550.common.NWAL1550CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1550Scrn00_Search_Order
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         S.Ohki          Create          N/A
 * 2017/02/27   Fujitsu         T.Yoshida       Update          S21_NA#16035
 *</pre>
 */
public class NWAL1550Scrn00_Search_Order extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1550BMsg scrnMsg = (NWAL1550BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
            scrnMsg.setMessageInfo(NWAM0014E);
        }

        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1550BMsg scrnMsg = (NWAL1550BMsg) bMsg;

        NWAL1550CMsg bizMsg = new NWAL1550CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1550BMsg scrnMsg = (NWAL1550BMsg) bMsg;
        NWAL1550CMsg bizMsg = (NWAL1550CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1550CommonLogic.initControlFields(scrnMsg);
        NWAL1550CommonLogic.setIncorrectBackGroundColor(scrnMsg);
        NWAL1550CommonLogic.setControlFields(this, scrnMsg);
        NWAL1550CommonLogic.setProtectByAuthority(this, scrnMsg); // S21_NA#16035 Add

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
        
        if (ZYPCommonFunc.hasValue(scrnMsg.diChkVrsnNum_SL)) {
            scrnMsg.cpoOrdNum.setInputProtected(true);
        }
        
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
    }
}
