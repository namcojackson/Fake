/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0690E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_LINE_CONFIG_TAB;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_RMA_TAB;

import java.util.ArrayList;
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
 * 2015/11/12   CUSA            Y.Kanefusa      Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_CopyFrom extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        List<Integer> checkListLine = new ArrayList<Integer>();
        List<Integer> checkListRMA = new ArrayList<Integer>();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            checkListLine = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
        } else if (TAB_RMA.equals(dplyTab)) {
            checkListRMA = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
        }

        if (checkListLine.size() > 0 && checkListRMA.size() > 0) {
            scrnMsg.setMessageInfo(NWAM0690E, new String[] {MSG_PARAM_LINE_CONFIG_TAB, MSG_PARAM_RMA_TAB});
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
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        Object[] params = NWAL1500CommonLogic.getParamNWAL1620ForCopyFrom(scrnMsg);
        setArgForSubScreen(params);
    }
}
