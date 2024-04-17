/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6740;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/05   SRAA            Y.Chen          Create          QC#6382
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 *</pre>
 */
public class NMAL6740Scrn00_GetInterCompanyNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
// QC#9448
//        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;
//
//        if (!ZYPCommonFunc.hasValue(scrnMsg.coaAfflCd)) {
//            scrnMsg.coaAfflCd.setErrorInfo(1, NMAL6740Constant.ZZM9000E, new String[] {scrnMsg.coaAfflCd.getNameForMessage() });
//            scrnMsg.addCheckItem(scrnMsg.coaAfflCd);
//            scrnMsg.putErrorScreen();
//        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
// QC#9448
//        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;
//        NMAL6740CMsg bizMsg = new NMAL6740CMsg();
//
//        bizMsg.setBusinessID(NMAL6740Constant.BUSINESS_ID);
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//        return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
// QC#9448
//        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;
//        NMAL6740CMsg bizMsg = (NMAL6740CMsg) cMsg;
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        scrnMsg.addCheckItem(scrnMsg.coaAfflCd);
//        scrnMsg.putErrorScreen();
//        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
//            throw new EZDFieldErrorException();
//        }
//        scrnMsg.setFocusItem(scrnMsg.coaAfflCd);
    }
}
