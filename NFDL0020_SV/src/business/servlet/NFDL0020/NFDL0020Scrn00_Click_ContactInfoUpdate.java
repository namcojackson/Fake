/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFDL0020.NFDL0020CMsg;
//import business.servlet.NFDL0020.constant.NFDL0020Constant;

import business.blap.NFDL0020.NFDL0020CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFDL0020Scrn00_Click_ContactInfoUpdate extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.ctacTpCd_H);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnFirstNm_H);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnMidNm_H);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnLastNm_H);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnTelNum_H);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnEmlAddr_H);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        NFDL0020CMsg bizMsg = new NFDL0020CMsg();
        bizMsg.setBusinessID("NFDL0020");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        NFDL0020CMsg bizMsg  = (NFDL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() != 0) {
            scrnMsg.ctacTpCd_H.setInputProtected(true);
            scrnMsg.ctacPsnFirstNm_H.setInputProtected(true);
            scrnMsg.ctacPsnMidNm_H.setInputProtected(true);
            scrnMsg.ctacPsnLastNm_H.setInputProtected(true);
            scrnMsg.dsCtacPntValTxt_H1.setInputProtected(true);
            scrnMsg.dsCtacPntValTxt_H2.setInputProtected(true);
            this.setButtonEnabled("Click_ContactInfoEdit", true);
            this.setButtonEnabled("Click_ContactInfoUpdate", false);
            this.setButtonEnabled("Click_ContactInfoCancel", false);
        }
    }
}
