/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.SCREEN_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;
import business.servlet.NWAL1620.constant.NWAL1620Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/10   Fujitsu         Y.Kanefusa      Create          N/A
 * 2016/02/18   Fujitsu         T.Ishii         Update          QC#1634
 * 2016/02/24   Fujitsu         M.Hara          Update          QC#4078
 *</pre>
 */
public class NWAL1500_NWAL1620 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard()) || "CMN_Cancel".equals(getLastGuard())) {
            return null;
        }

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = new NWAL1500CMsg();

        bizMsg.setBusinessID("NWAL1500");
        // QC#4078 Mod
        if (NWAL1620Constant.COPY_MODE.equals(scrnMsg.xxPopPrm_P0.getValue()) &&
                NWAL1620Constant.HEADER_MODE.equals(scrnMsg.xxPopPrm_P1.getValue())) {
            bizMsg.setFunctionCode("40");
        } else {
            bizMsg.setFunctionCode("20");
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard()) || "CMN_Cancel".equals(getLastGuard())) {
            return;
        }

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // QC#4078 Add Start
        if (NWAL1620Constant.HEADER_MODE.equals(bizMsg.xxPopPrm_P1.getValue())) {
            NWAL1500CommonLogic.addCheckItemBizLayerErr(scrnMsg, true);
            NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg); 

            scrnMsg.putErrorScreen();
        }
        // QC#4078 Add End

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            // S21_NA#1634NWAL1500CommonLogic.controlMdseField(scrnMsg);
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            // S21_NA#1634NWAL1500CommonLogic.controlMdseFieldForRma(scrnMsg);
        } else {
            NWAL1500CommonLogic.activeRegistrationButton(this, scrnMsg);
            NWAL1500CommonLogic.inactiveAddButton(this);
        }

        scrnMsg.dsOrdPosnNum_P1.clear();
        scrnMsg.dsCpoLineNum_P1.clear();
        scrnMsg.xxQty10Num_AW.clear();
    }
}
