/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZVL0000;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZVL0000.ZZVL0000CMsg;
import business.servlet.ZZVL0000.common.ZZVL0000CommonLogic;
//import business.servlet.ZZVL0000.constant.ZZVL0000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/04   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0000Scrn01_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZVL0000BMsg scrnMsg = (ZZVL0000BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZVL0000BMsg scrnMsg = (ZZVL0000BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.B);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowFromNum_B, BigDecimal.valueOf(scrnMsg.xxPageShowToNum_B.getValueInt()));
        scrnMsg.xxPageShowToNum_B.clear();

        ZZVL0000CMsg bizMsg = new ZZVL0000CMsg();
        bizMsg.setBusinessID("ZZVL0000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

//        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZVL0000BMsg scrnMsg = (ZZVL0000BMsg) bMsg;
        ZZVL0000CMsg bizMsg  = (ZZVL0000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

//        S21TableColorController tblColor = new S21TableColorController("ZZVL0000Scrn01", scrnMsg);
//        tblColor.setAlternateRowsBG("B", scrnMsg.B);
        ZZVL0000CommonLogic.dspScrn01(scrnMsg, this);

    }
}
