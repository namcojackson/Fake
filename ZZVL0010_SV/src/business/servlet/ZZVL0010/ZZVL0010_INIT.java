/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZVL0010;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.ZZVL0010.ZZVL0010CMsg;
//import business.servlet.ZZVL0010.constant.ZZVL0010Constant;

import business.blap.ZZVL0010.ZZVL0010CMsg;
import business.servlet.ZZVL0010.common.ZZVL0010Common;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/04   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0010_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZVL0010BMsg scrnMsg = (ZZVL0010BMsg) bMsg;
        //this.checkBusinessAppGranted(getContextUserInfo().getUserId(), "ZZVL0010");

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZVL0010BMsg scrnMsg = (ZZVL0010BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            EZDBStringItem param01 = (EZDBStringItem) params[0];

            ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, param01.getValue());
        }

        ZZVL0010CMsg bizMsg = new ZZVL0010CMsg();
        bizMsg.setBusinessID("ZZVL0010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        //return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZVL0010BMsg scrnMsg = (ZZVL0010BMsg) bMsg;
        ZZVL0010CMsg bizMsg  = (ZZVL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.glblCmpyCd.setInputProtected(true);

        ZZVL0010Common.setCommonButtons(this);

        S21TableColorController tblColor = new S21TableColorController("ZZVL0010Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

    }
}
