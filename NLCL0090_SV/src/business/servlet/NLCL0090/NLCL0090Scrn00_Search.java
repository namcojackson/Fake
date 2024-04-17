/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0090.NLCL0090CMsg;
import business.servlet.NLCL0090.common.NLCL0090CommonLogic;
import business.servlet.NLCL0090.constant.NLCL0090Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Item Change Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/03/2016   CSAI            Y.Imazu         Create          CSA
 *</pre>
 */
public class NLCL0090Scrn00_Search extends S21CommonHandler implements NLCL0090Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0090BMsg scrnMsg = (NLCL0090BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.invtyOrdNum)) {

            scrnMsg.invtyOrdNum.setErrorInfo(1, ZZM9000E, new String[]{"Transaction Number"});
        }

        scrnMsg.addCheckItem(scrnMsg.invtyOrdNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0090BMsg scrnMsg = (NLCL0090BMsg) bMsg;
        NLCL0090CMsg bizMsg = new NLCL0090CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0090CMsg bizMsg = (NLCL0090CMsg) cMsg;
        NLCL0090BMsg scrnMsg = (NLCL0090BMsg) bMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL0090CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            throw new EZDFieldErrorException();
        }

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.invtyOrdNum);
    }
}
