/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFDL0020.NFDL0020CMsg;
//import business.servlet.NFDL0020.constant.NFDL0020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFDL0020Scrn00_Click_ContactInfoEdit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        scrnMsg.ctacTpCd_H.setInputProtected(false);
        scrnMsg.ctacPsnFirstNm_H.setInputProtected(false);
        scrnMsg.ctacPsnMidNm_H.setInputProtected(false);
        scrnMsg.ctacPsnLastNm_H.setInputProtected(false);
        scrnMsg.dsCtacPntValTxt_H1.setInputProtected(false);
        scrnMsg.dsCtacPntValTxt_H2.setInputProtected(false);
        this.setButtonEnabled("Click_ContactInfoEdit", false);
        this.setButtonEnabled("Click_ContactInfoUpdate", true);
        this.setButtonEnabled("Click_ContactInfoCancel", true);
    }
}
