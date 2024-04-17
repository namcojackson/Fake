/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.BUSINESS_ID;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.TAB_CONTACTS;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.TAB_INSTRUCTIONS;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6720.NMAL6720CMsg;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Fujitsu         C.Tanaka        Create          QC#2824
 *</pre>
 */
public class NMAL6720Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        if (TAB_CONTACTS.equals(scrnMsg.xxDplyTab.getValue())) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.C, scrnMsg.xxPageShowFromNum_C, scrnMsg.xxPageShowToNum_C, scrnMsg.xxPageShowOfNum_C, scrnMsg.xxPageShowCurNum_C, scrnMsg.xxPageShowTotNum_C);
        } else if (TAB_INSTRUCTIONS.equals(scrnMsg.xxDplyTab.getValue())) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.E, scrnMsg.xxPageShowFromNum_E, scrnMsg.xxPageShowToNum_E, scrnMsg.xxPageShowOfNum_E, scrnMsg.xxPageShowCurNum_E, scrnMsg.xxPageShowTotNum_E);
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        NMAL6720CMsg bizMsg = new NMAL6720CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg = (NMAL6720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
