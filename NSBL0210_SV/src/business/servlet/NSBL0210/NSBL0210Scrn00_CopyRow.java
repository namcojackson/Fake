/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0210;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/28   SRA             E.Inada         Create          N/A
 * 2016/05/18   Hitachi         Y.Takeno        Update          NA#CSA
 *</pre>
 */
public class NSBL0210Scrn00_CopyRow extends S21CommonHandler  {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;
//        NSBL0210CMsg bizMsg = new NSBL0210CMsg();
//
//        bizMsg.setBusinessID(BUSINESS_ID);
//        bizMsg.setFunctionCode(FUNC_CD_20);
//
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;
//        NSBL0210CMsg bizMsg = (NSBL0210CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        NSBL0210CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
//
//        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
//
//            // Search do not set error message on fields.
//            throw new EZDFieldErrorException();
//        }
//
//        if (scrnMsg.A.getValidCount() > 0) {
//            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).svcLborTpCd_A);
//        }
//
//        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
//            scrnMsg.setMessageInfo(NSBM0005I);
//        }
    }
}
