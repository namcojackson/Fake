/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/01   Fujitsu         S.Takami        Create          S21_NA#19808
 *</pre>
 */
public class NWAL1500Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        String xxDplyTab = scrnMsg.xxDplyTab.getValue();
        if (TAB_LINE_CONFIG.equals(xxDplyTab)) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(//
                    scrnMsg //
                    , scrnMsg.B //
                    , scrnMsg.xxPageShowFromNum_LL //
                    , scrnMsg.xxPageShowToNum_LL //
                    , scrnMsg.xxPageShowOfNum_LL //
                    , scrnMsg.xxPageShowCurNum_LL //
                    , scrnMsg.xxPageShowTotNum_LL);
        } else if (TAB_RMA.equals(xxDplyTab)) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(//
                    scrnMsg //
                    , scrnMsg.D //
                    , scrnMsg.xxPageShowFromNum_RL //
                    , scrnMsg.xxPageShowToNum_RL //
                    , scrnMsg.xxPageShowOfNum_RL //
                    , scrnMsg.xxPageShowCurNum_RL //
                    , scrnMsg.xxPageShowTotNum_RL);
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg  = (NWAL1500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1500CommonLogic.activeRegistrationButton(this, scrnMsg);
        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
        NWAL1500CommonLogic.inactiveAddButton(this);
    }
}
