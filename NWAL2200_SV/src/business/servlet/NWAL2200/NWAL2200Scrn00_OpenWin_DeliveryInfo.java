/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.NWAM0681E;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.NWAM0682E;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_RMA;

import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_OpenWin_DeliveryInfo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL2200Scrn00_OpenWin_DeliveryInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_LC);
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_LL);
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_RC);
        }
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).xxChkBox_RL);
        }

        List<Integer> selectedRowsLC = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
        List<Integer> selectedRowsRC = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
        List<Integer> selectedRowsLL = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
        List<Integer> selectedRowsRL = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);

        if (S21StringUtil.isEquals(scrnMsg.xxDplyTab.getValue(), TAB_LINE_CONFIG)) {

            if (selectedRowsLC.size() > 1) {

                for (int i : selectedRowsLC) {
                    scrnMsg.A.no(i).xxChkBox_LC.setErrorInfo(1, NWAM0682E);
                }
            }

            if (selectedRowsLL.size() != 0) {
                for (int i : selectedRowsLL) {
                    scrnMsg.B.no(i).xxChkBox_LL.setErrorInfo(1, NWAM0681E);
                }
            }
        } else if (S21StringUtil.isEquals(scrnMsg.xxDplyTab.getValue(), TAB_RMA)) {

            if (selectedRowsRC.size() > 1) {

                for (int i : selectedRowsRC) {
                    scrnMsg.C.no(i).xxChkBox_RC.setErrorInfo(1, NWAM0682E);
                }
            }

            if (selectedRowsRL.size() != 0) {
                for (int i : selectedRowsRL) {
                    scrnMsg.D.no(i).xxChkBox_RL.setErrorInfo(1, NWAM0681E);
                }
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            scrnMsg.P.no(i).xxPopPrm.clear();
        }

        if (S21StringUtil.isEquals(scrnMsg.xxDplyTab.getValue(), TAB_LINE_CONFIG)) {

            List<Integer> selectedRowsLC = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            if (selectedRowsLC.size() == 1) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.A.no(selectedRowsLC.get(0)).dsOrdPosnNum_LC);
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, CONFIG_CATG.OUTBOUND);
            }
        } else if (S21StringUtil.isEquals(scrnMsg.xxDplyTab.getValue(), TAB_RMA)) {

            List<Integer> selectedRowsRC = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            if (selectedRowsRC.size() == 1) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.C.no(selectedRowsRC.get(0)).dsOrdPosnNum_RC);
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, CONFIG_CATG.INBOUND);
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, "0");

        Object[] params = new Object[4];
        params[0] = scrnMsg.ordSrcRefNum;
        params[1] = scrnMsg.P.no(0).xxPopPrm;
        params[2] = scrnMsg.P.no(1).xxPopPrm;
        params[3] = scrnMsg.P.no(2).xxPopPrm;

        setArgForSubScreen(params);
    }
}
