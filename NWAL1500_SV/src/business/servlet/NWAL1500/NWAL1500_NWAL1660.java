/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL1500_NWAL1660 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
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

        if (cMsg == null) {
            return;
        }

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
            if (selectedRows.size() > 0) {
                scrnMsg.addCheckItem(scrnMsg.B.no(selectedRows.get(0)).entCpoDtlDealSlsAmt_LL);
                scrnMsg.putErrorScreen();

                scrnMsg.setFocusItem(scrnMsg.B.no(selectedRows.get(0)).xxChkBox_LL);
            }
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
            if (selectedRows.size() > 0) {
                scrnMsg.addCheckItem(scrnMsg.D.no(selectedRows.get(0)).entCpoDtlDealSlsAmt_RL);
                scrnMsg.putErrorScreen();

                scrnMsg.setFocusItem(scrnMsg.D.no(selectedRows.get(0)).xxChkBox_RL);
            }
        }
    }
}
