/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

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
public class NMAL6730Scrn00_GetInterCompanyNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
// QC#9448
//        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
//
//        if (!ZYPCommonFunc.hasValue(scrnMsg.coaAfflCd_H1)) {
//            scrnMsg.coaAfflCd_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.coaAfflCd_H1.getNameForMessage() });
//            scrnMsg.addCheckItem(scrnMsg.coaAfflCd_H1);
//            scrnMsg.putErrorScreen();
//        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
// QC#9448
//        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
//        NMAL6730CMsg bizMsg = new NMAL6730CMsg();
//
//        bizMsg.setBusinessID(BUSINESS_ID);
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//        return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
// QC#9448
//        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
//        NMAL6730CMsg bizMsg = (NMAL6730CMsg) cMsg;
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        scrnMsg.addCheckItem(scrnMsg.coaAfflCd_H1);
//        scrnMsg.putErrorScreen();
//        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
//            throw new EZDFieldErrorException();
//        }
//        scrnMsg.setFocusItem(scrnMsg.coaAfflCd_H1);
    }
}
