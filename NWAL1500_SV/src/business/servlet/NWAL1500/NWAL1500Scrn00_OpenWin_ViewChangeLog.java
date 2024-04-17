/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0688E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/16   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_ViewChangeLog extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            List<Integer> checkListItemConfig = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            List<Integer> checkListItemLine = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

            if (checkListItemConfig.size() > 0 && checkListItemLine.size() > 0) {
                scrnMsg.setMessageInfo(NWAM0688E, null);
                throw new EZDFieldErrorException();
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            List<Integer> checkListRMAConfig = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            List<Integer> checkListRMALine = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);

            if (checkListRMAConfig.size() > 0 && checkListRMALine.size() > 0) {
                scrnMsg.setMessageInfo(NWAM0688E, null);
                throw new EZDFieldErrorException();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        Object[] params = NWAL1500CommonLogic.getParamNWAL1810(scrnMsg);
        setArgForSubScreen(params);
    }
}
