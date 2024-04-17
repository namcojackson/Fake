/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import static business.servlet.NLBL2020.constant.NLBL2020Constant.TAB_SO_LIST;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2030.NLAL2030CMsg;
import business.servlet.NLAL2030.common.NLAL2030CommonLogic;
import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2030Scrn00_PageJump
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 * 06/23/2017   CITS            M.Naito         Update          QC#19314
 *</pre>
 */
public class NLAL2030Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        if (NLAL2030Constant.TAB_ID_ORD.equals(scrnMsg.xxDplyTab.getValue())) {

            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum_A, scrnMsg.xxPageShowToNum_A
                    , scrnMsg.xxPageShowOfNum_A, scrnMsg.xxPageShowCurNum_A, scrnMsg.xxPageShowTotNum_A);

        } else {

            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.B, scrnMsg.xxPageShowFromNum_B, scrnMsg.xxPageShowToNum_B
                    , scrnMsg.xxPageShowOfNum_B, scrnMsg.xxPageShowCurNum_B, scrnMsg.xxPageShowTotNum_B);
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        NLAL2030CMsg bizMsg = new NLAL2030CMsg();
        bizMsg.setBusinessID(NLAL2030Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;
        NLAL2030CMsg bizMsg  = (NLAL2030CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLAL2030CommonLogic.initCmnBtnProp(this);
        NLAL2030CommonLogic.controlScreenFields(this, scrnMsg);
    }
}
