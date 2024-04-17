/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.BUSINESS_ID;
import static business.servlet.NSAL0010.constant.NSAL0010Constant.TAB_MACH_CONFIG;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0010.NSAL0010CMsg;
//import business.servlet.NSAL0010.constant.NSAL0010Constant;

import business.blap.NSAL0010.NSAL0010CMsg;
import business.servlet.NSAL0010.common.NSAL0010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2017/01/16   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0010Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;

        // START 2017/01/16 K.Ochiai [QC#16331,DEL]
//        Object[] params = (Object[]) getArgForSubScreen();
//        if (params != null && params.length > 0) {
//            if (params.length == 1) {
//                EZDBBigDecimalItem param01 = (EZDBBigDecimalItem) params[0];
//                ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk_H2, param01);
//            }
//        }
        // END 2017/01/16 K.Ochiai [QC#16331,DEL]
        scrnMsg.xxDplyTab_01.setValue(TAB_MACH_CONFIG);

        NSAL0010CMsg bizMsg = new NSAL0010CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        boolean isSearch = false;
        if (ZYPCommonFunc.hasValue(scrnMsg.svcMachMstrPk_H1.getValue())) {
            isSearch = true;
        }
        NSAL0010CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID(), isSearch);
        scrnMsg.setFocusItem(scrnMsg.serNum_H1);
    }
}
