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
 * 2016/06/22   Hitachi         K.Kojima        Update          QC#10529
 *</pre>
 */
public class NFDL0020Scrn00_Click_ContactInfoCancel extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        NFDL0020CMsg bizMsg = new NFDL0020CMsg();
        bizMsg.setBusinessID("NFDL0020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        NFDL0020CMsg bizMsg  = (NFDL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.ctacTpCd_H.setInputProtected(true);
        scrnMsg.ctacPsnFirstNm_H.setInputProtected(true);
        scrnMsg.ctacPsnMidNm_H.setInputProtected(true);
        scrnMsg.ctacPsnLastNm_H.setInputProtected(true);
        // START 2016/06/22 K.Kojima [QC#10529,MOD]
        // scrnMsg.ctacPsnTelNum_H.setInputProtected(true);
        // scrnMsg.ctacPsnEmlAddr_H.setInputProtected(true);
        scrnMsg.dsCtacPntValTxt_H1.setInputProtected(true);
        scrnMsg.dsCtacPntValTxt_H2.setInputProtected(true);
        // END 2016/06/22 K.Kojima [QC#10529,MOD]
        this.setButtonEnabled("Click_ContactInfoEdit", true);
        this.setButtonEnabled("Click_ContactInfoUpdate", false);
        this.setButtonEnabled("Click_ContactInfoCancel", false);

    }
}
