/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0240;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
// business.blap.NSBL0240.NSBL0240CMsg;
// import business.servlet.NSBL0240.constant.NSBL0240Constant;

import business.blap.NSBL0240.NSBL0240CMsg;
import business.servlet.NSBL0240.common.NSBL0240CommonLogic;
import business.servlet.NSBL0240.constant.NSBL0240Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/04/26   Hitachi         Y.Takeno        Update          NA#CSA
 *</pre>
 */
public class NSBL0240Scrn00_CopyRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NSBL0240BMsg scrnMsg = (NSBL0240BMsg) bMsg;
//        NSBL0240CMsg bizMsg = new NSBL0240CMsg();
//
//        bizMsg.setBusinessID(NSBL0240Constant.BUSINESS_ID);
//        bizMsg.setFunctionCode("20");
//
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        NSBL0240BMsg scrnMsg = (NSBL0240BMsg) bMsg;
//        NSBL0240CMsg bizMsg = (NSBL0240CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        NSBL0240CommonLogic.controlScreenFields(getUserProfileService(), this, scrnMsg);
//
//        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
//
//            // Search do not set error message on fields.
//            throw new EZDFieldErrorException();
//        }
//
//        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
//            scrnMsg.setMessageInfo(NSBL0240Constant.NSBM0005I);
//        }
    }
}
