/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_HEADER;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_ENTRY_ACT;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/06   Fujitsu         T.Ishii         Create          N/A
 * 2016/08/24   Fujitsu         Y.Taoka         Update          S21_NA#11630
 * 2019/11/27   Fujitsu         S.Takami        Update          S21_NA#54225
 * 2023/05/11   CITS            R.Azucena       Update          QC#61514
 *</pre>
 */
public class NWAL1500_NWAL2000 extends S21CommonHandler {

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
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");  // 2016/02/20 S21_NA#2166 "20" -> "40"
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // START 2023/05/11 R.Azucena [QC#61514 DEL]
        // NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
        // END 2023/05/11 R.Azucena [QC#61514 DEL]

        // S21_NA#11630 ADD START
        NWAL1500CommonLogic.addCheckItemBizLayerErr(scrnMsg, true);
        scrnMsg.putErrorScreen();
        // S21_NA#11630 ADD END

        // START 2023/05/11 R.Azucena [QC#61514 ADD]
        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
        // END 2023/05/11 R.Azucena [QC#61514 ADD]

        if (ORD_ENTRY_ACT.CANCEL.equals(scrnMsg.ordEntryActCd.getValue())) {

            scrnMsg.xxDplyTab.setValue(TAB_HEADER);
            scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);

            NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
            // 2019/11/27 S21_NA#54225 Add Start
            NWAL1500CommonLogic.activeRegistrationButton(this, scrnMsg);
            NWAL1500CommonLogic.inactiveAddButton(this);
            // 2019/11/27 S21_NA#54225 Add End
        } else if (ORD_ENTRY_ACT.CANCEL_2.equals(scrnMsg.ordEntryActCd.getValue())) {

            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
                if (selectedRows.size() > 0) {
                    scrnMsg.setFocusItem(scrnMsg.A.no(selectedRows.get(0)).xxChkBox_LC);
                } else {
                    selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
                    if (selectedRows.size() > 0) {
                        scrnMsg.setFocusItem(scrnMsg.B.no(selectedRows.get(0)).xxChkBox_LL);
                    }
                }
            } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
                List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
                if (selectedRows.size() > 0) {
                    scrnMsg.setFocusItem(scrnMsg.C.no(selectedRows.get(0)).xxChkBox_RC);
                } else {
                    selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
                    if (selectedRows.size() > 0) {
                        scrnMsg.setFocusItem(scrnMsg.D.no(selectedRows.get(0)).xxChkBox_RL);
                    }
                }
            }
        }
    }
}
