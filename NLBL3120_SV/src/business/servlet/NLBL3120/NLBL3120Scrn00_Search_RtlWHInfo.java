/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3120;

import static business.servlet.NLBL3120.constant.NLBL3120Constant.ZZM9000E;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3120.NLBL3120CMsg;
import business.servlet.NLBL3120.common.NLBL3120CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         S.Yoshida       Create          N/A
 *</pre>
 */
public class NLBL3120Scrn00_Search_RtlWHInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
            scrnMsg.rtlWhCd.setErrorInfo(1, ZZM9000E
                    , new String[]{scrnMsg.rtlWhCd.getNameForMessage()});
        }
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;
        NLBL3120CMsg bizMsg = new NLBL3120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;
        NLBL3120CMsg bizMsg = (NLBL3120CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3120CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(scrnMsg.rtlWhCd);

    }
}
