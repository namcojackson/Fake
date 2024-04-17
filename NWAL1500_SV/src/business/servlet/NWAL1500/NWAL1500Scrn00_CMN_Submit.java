/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Fujitsu         S.Takami        Create          N/A
 * 2016/02/20   Fujitsu         Y.Kanefusa      Update          QC#3943
 * 2016/02/26   Fujitsu         K.Sato          Update          S21_NA#1729
 *</pre>
 */
public class NWAL1500Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CommonLogic.addCheckItemBizLayerErr(scrnMsg, false);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1500CommonLogic.addCheckItemBizLayerErr(scrnMsg, true);
        scrnMsg.putErrorScreen();

        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);//add QC#3943 
        // 2016/02/26 S21_NA#1729 Mod Start
        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            return;
        }
        // 2016/02/26 S21_NA#1729 Mod End

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        //scrnMsg.xxDplyTab.setValue(TAB_HEADER);
        //scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);

        //NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);  //Del QC#3943 
        NWAL1500CommonLogic.inactiveAddButton(this);
    }
}
