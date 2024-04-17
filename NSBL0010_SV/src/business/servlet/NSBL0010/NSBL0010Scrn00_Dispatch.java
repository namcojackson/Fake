/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0010.NSBL0010CMsg;
import business.servlet.NSBL0010.common.NSBL0010CommonLogic;
import business.servlet.NSBL0010.constant.NSBL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 *</pre>
 */
public class NSBL0010Scrn00_Dispatch extends S21CommonHandler implements NSBL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

        NSBL0010CommonLogic.inputCheck(scrnMsg);

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
        NSBL0010CMsg bizMsg = new NSBL0010CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_40);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
        NSBL0010CMsg bizMsg = (NSBL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0010CommonLogic.addCheckItems(scrnMsg);

        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            // Search do not set error message on fields.
            throw new EZDFieldErrorException();
        }

        NSBL0010CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
//            NSBL0010CommonLogic.setSelectedColor(scrnMsg);
            scrnMsg.setMessageInfo(NSBM0005I);
        }
    }
}
