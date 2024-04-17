/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_HEADER;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/17   Fujitsu         Y.Taoka         Update          QC#1694
 * 2016/08/04   Fujitsu         T.Yoshdia       Update          S21_NA#11619,13026
 *</pre>
 */
public class NWAL1500Scrn00_TAB_Header extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        if (TAB_LINE_CONFIG.equals(dplyTab) && !ZYPCommonFunc.hasValue(scrnMsg.B.no(0).mdseCd_LL)) {
            return;
        } else if (TAB_RMA.equals(dplyTab) && !ZYPCommonFunc.hasValue(scrnMsg.D.no(0).mdseCd_RL)) {
            return;
        }

        NWAL1500CommonLogic.addCheckItemBizLayerErr(scrnMsg, false);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");

        // For Performance QC#11619,13026 Mod Start
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        EZDMsg.forceCopy(scrnMsg, bizMsg);
        // For Performance QC#11619,13026 Mod End

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;

        // For Performance QC#11619,13026 Mod Start
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        EZDMsg.forceCopy(bizMsg, scrnMsg);
        // For Performance QC#11619,13026 Mod End

        scrnMsg.xxDplyTab.setValue(TAB_HEADER);
        scrnMsg.setFocusItem(scrnMsg.billToCustAcctNm);

        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
        NWAL1500CommonLogic.inactiveAddButton(this);
    }
}
