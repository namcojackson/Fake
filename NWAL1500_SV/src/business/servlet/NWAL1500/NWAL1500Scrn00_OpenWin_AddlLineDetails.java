/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CMPT_LINE;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0666E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0667E;

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
 * 2015/11/13   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_AddlLineDetails extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        List<Integer> checkList = null;

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
        } else {
            checkList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
        }

        if (checkList.size() == 1) {
            scrnMsg.xxCellIdx.setValue(checkList.get(0));
        } else if (checkList.size() == 0) {
            scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_CMPT_LINE });
            throw new EZDFieldErrorException();
        } else if (checkList.size() > 1) {
            scrnMsg.setMessageInfo(NWAM0666E, new String[] {MSG_PARAM_CMPT_LINE });
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        Object[] params = NWAL1500CommonLogic.getParamNWAL1630(scrnMsg);
        setArgForSubScreen(params);
    }
}
