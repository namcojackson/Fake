/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZVL0000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.ZZVL0000.ZZVL0000CMsg;
//import business.servlet.ZZVL0000.constant.ZZVL0000Constant;

import business.servlet.ZZVL0000.common.ZZVL0000CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/04   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0000Scrn00_Add extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZVL0000BMsg scrnMsg = (ZZVL0000BMsg) bMsg;

        ZZVL0000BMsg scrnMsg = (ZZVL0000BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_1);
        scrnMsg.addCheckItem(scrnMsg.roleNm_1);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZVL0000BMsg scrnMsg = (ZZVL0000BMsg) bMsg;

        //ZZVL0000CMsg bizMsg = new ZZVL0000CMsg();
        //bizMsg.setBusinessID("ZZVL0000");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZVL0000BMsg scrnMsg = (ZZVL0000BMsg) bMsg;
        //ZZVL0000CMsg bizMsg  = (ZZVL0000CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
        ZYPEZDItemValueSetter.setValue(scrnMsg.roleNm_2, scrnMsg.roleNm_1);
//        setNameForMessage(bMsg);
        ZZVL0000CommonLogic.dspScrn01(scrnMsg, this);

    }

//    private void setNameForMessage(EZDBMsg args) {
//        
//        setButtonProperties(ZZVL0000Constant.CMN_BTN1[0], ZZVL0000Constant.CMN_BTN1[1], ZZVL0000Constant.CMN_BTN1[2], 0, null);
//        setButtonProperties(ZZVL0000Constant.CMN_BTN2[0], "CMN_Add", ZZVL0000Constant.CMN_BTN2[2], 1, null);
//        setButtonProperties(ZZVL0000Constant.CMN_BTN3[0], ZZVL0000Constant.CMN_BTN3[1], ZZVL0000Constant.CMN_BTN3[2], 0, null);
//        setButtonProperties(ZZVL0000Constant.CMN_BTN4[0], ZZVL0000Constant.CMN_BTN4[1], ZZVL0000Constant.CMN_BTN4[2], 0, null);
//        setButtonProperties(ZZVL0000Constant.CMN_BTN5[0], ZZVL0000Constant.CMN_BTN5[1], ZZVL0000Constant.CMN_BTN5[2], 0, null);
//        setButtonProperties(ZZVL0000Constant.CMN_BTN6[0], ZZVL0000Constant.CMN_BTN6[1], ZZVL0000Constant.CMN_BTN6[2], 0, null);
//        setButtonProperties(ZZVL0000Constant.CMN_BTN7[0], ZZVL0000Constant.CMN_BTN7[1], ZZVL0000Constant.CMN_BTN7[2], 0, null);
//        setButtonProperties(ZZVL0000Constant.CMN_BTN8[0], ZZVL0000Constant.CMN_BTN8[1], ZZVL0000Constant.CMN_BTN8[2], 0, null);
//        setButtonProperties(ZZVL0000Constant.CMN_BTN9[0], ZZVL0000Constant.CMN_BTN9[1], ZZVL0000Constant.CMN_BTN9[2], 0, null);
//        setButtonProperties(ZZVL0000Constant.CMN_BTN10[0], ZZVL0000Constant.CMN_BTN10[1], ZZVL0000Constant.CMN_BTN10[2], 1, null);
//    }
}
