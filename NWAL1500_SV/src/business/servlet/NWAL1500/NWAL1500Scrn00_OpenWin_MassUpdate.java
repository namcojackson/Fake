/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0504E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0688E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0766E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         Y.Kanefusa      Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_MassUpdate extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            List<Integer> checkListItemConfig = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            List<Integer> checkListItemLine = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
            if (checkListItemConfig.size() == 0 && checkListItemLine.size() == 0) {
                scrnMsg.setMessageInfo(NWAM0504E, null);
                throw new EZDFieldErrorException();
            }
            if (checkListItemConfig.size() > 0 && checkListItemLine.size() > 0) {
                scrnMsg.setMessageInfo(NWAM0688E, null);
                throw new EZDFieldErrorException();
            }
            // ADD START 2016/02/12 #1864
            if (checkListItemConfig.size() == 1 || checkListItemLine.size() == 1) {
                scrnMsg.setMessageInfo(NWAM0766E, null);
                throw new EZDFieldErrorException();
            }
            // ADD END 2016/02/12 #1864
        } else if (TAB_RMA.equals(dplyTab)) {
            List<Integer> checkListRMAConfig = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            List<Integer> checkListRMALine = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
            if (checkListRMAConfig.size() == 0 && checkListRMALine.size() == 0) {
                scrnMsg.setMessageInfo(NWAM0504E, null);
                throw new EZDFieldErrorException();
            }
            if (checkListRMAConfig.size() > 0 && checkListRMALine.size() > 0) {
                scrnMsg.setMessageInfo(NWAM0688E, null);
                throw new EZDFieldErrorException();
            }
            // ADD START 2016/02/12 #1864
            if (checkListRMAConfig.size() == 1 || checkListRMALine.size() == 1) {
                scrnMsg.setMessageInfo(NWAM0766E, null);
                throw new EZDFieldErrorException();
            }
            // ADD END 2016/02/12 #1864
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        Object[] params = NWAL1500CommonLogic.getParamNWAL1610(scrnMsg);
        setArgForSubScreen(params);
    }
}
