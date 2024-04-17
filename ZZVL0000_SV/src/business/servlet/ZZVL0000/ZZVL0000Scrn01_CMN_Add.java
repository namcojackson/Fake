/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZVL0000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.ZZVL0000.ZZVL0000CMsg;
//import business.servlet.ZZVL0000.constant.ZZVL0000Constant;

import business.blap.ZZVL0000.ZZVL0000CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/04   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0000Scrn01_CMN_Add extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZVL0000BMsg scrnMsg = (ZZVL0000BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.usrId_2);
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.B.no(i).xxChkBox_2.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).scrTblColDefNm_3);
            }
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZVL0000BMsg scrnMsg = (ZZVL0000BMsg) bMsg;

        ZZVL0000CMsg bizMsg = new ZZVL0000CMsg();
        bizMsg.setBusinessID("ZZVL0000");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        //return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZVL0000BMsg scrnMsg = (ZZVL0000BMsg) bMsg;
        ZZVL0000CMsg bizMsg  = (ZZVL0000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).scrTblColDefNm_3);
        }
        scrnMsg.putErrorScreen();

    }
}
