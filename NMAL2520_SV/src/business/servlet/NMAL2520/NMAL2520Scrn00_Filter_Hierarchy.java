/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/10   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/08/31   SRAA            Y.Chen          Update          QC#7966
 *</pre>
 */
public class NMAL2520Scrn00_Filter_Hierarchy extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
// QC#7966
//
//        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
//
//        if (!ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H2.getValue())) {
//            scrnMsg.xxChkBox_H2.setErrorInfo(1, NMAL2520Constant.ZZZM9007E, new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_ACTIVE_ASSIGNMENTS_ONLY });
//            scrnMsg.addCheckItem(scrnMsg.xxChkBox_H2);
//        }
//
//        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
// QC#7966
//
//        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
//
//        NMAL2520CMsg bizMsg = new NMAL2520CMsg();
//        bizMsg.setBusinessID(NMAL2520Constant.BIZ_ID);
//        bizMsg.setFunctionCode(NMAL2520Constant.FUNCTION_CD);
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
// QC#7966
//
//        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
//        NMAL2520CMsg bizMsg = (NMAL2520CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
