/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDGUIAttribute;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2040.NFBL2040CMsg;
//import business.servlet.NFBL2040.constant.NFBL2040Constant;

import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 * 2017/01/13   Hitachi         E.Kameishi      Update          QC#16849
 * 2017/10/11   CITS            T.Tokutomi      Update          QC#21640
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 *</pre>
 */
public class NFBL2040Scrn00_TAB_Header extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CommonLogic.clearRowsBG_H(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_01, TAB_HEADER);

        // QC#21640 : Disable terms text and link.
        if (CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            scrnMsg.vndPmtTermDescTxt_A.setInputProtected(true);
            scrnMsg.vndPmtTermDescTxt.setInputProtected(true);
            EZDGUIAttribute termsLink = new EZDGUIAttribute(SCRN_ID_00, ID_LINK_TERMS);
            termsLink.setAttribute("style", "text-decoration: none; color:#000000;");
            scrnMsg.addGUIAttribute(termsLink);
        }

        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
        // START 2017/01/13 E.Kameishi [QC#16949,ADD]
        scrnMsg.setFocusItem(scrnMsg.dplyVndNm);
        // END 2017/01/13 E.Kameishi [QC#16949,ADD]
        // START 2020/03/16 [QC#55993, ADD]
        NFBL2040CommonLogic.clearHoldReleasePending(scrnMsg, true);
        // END   2020/03/16 [QC#55993, ADD]
    }
}
