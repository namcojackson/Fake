/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CONF_LINE;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0666E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0667E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
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
 * 2015/11/06   Fujitsu         T.Yoshida       Create          N/A
 * 2015/12/10   Fujitsu         T.ishii         Update          S21_NA#1673
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_SalesCredit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        List<Integer> checkList = null;

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
        } else if (TAB_RMA.equals(dplyTab)) {
            checkList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
        }

        if (checkList != null) {
            if (checkList.size() == 1) {
                scrnMsg.xxCellIdx.setValue(checkList.get(0));
            } else if (checkList.size() == 0) {
                    scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_CONF_LINE });
                    throw new EZDFieldErrorException();
                } else if (checkList.size() > 1) {
                    scrnMsg.setMessageInfo(NWAM0666E, new String[] {MSG_PARAM_CONF_LINE });
                    throw new EZDFieldErrorException();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //S21_NA#1673
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        int idx = getButtonSelectNumber();
        scrnMsg.xxCellIdx_BB.setValue(idx);
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;

        //S21_NA#1673
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        Object[] params = NWAL1500CommonLogic.getParamNWAL1600(scrnMsg);
        setArgForSubScreen(params);
    }
}
