/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForOrderEntryAction;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_ENTRY_ACT;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1500Scrn00_OnChange_OrderEntryActionDelivery
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         Y.Taoka         Create          QC#1694
 * 2016/05/19   Fujitsu         S.Takami        Update          S21_NA#2104
 * 2017/10/11   Fujitsu         R.Nakamura      Update          S21_NA#19910
 * 2018/07/20   Fujitsu         Mz.takahashi    Update          S21_NA#26188
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 *</pre>
 */
public class NWAL1500Scrn00_OnChange_OrderEntryActionDelivery extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500CommonLogicForOrderEntryAction.checkInput(this, ctx, bMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return NWAL1500CommonLogicForOrderEntryAction.setRequestData(this, ctx, bMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // Please modify the same program below for "NWAL1500Scrn00_OnChange_OrderEntryActionXXX.java".
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        String ordEntryActCd = scrnMsg.ordEntryActCd.getValue();
        Object[] params = NWAL1500CommonLogicForOrderEntryAction.doProcess(this, ctx, scrnMsg, bizMsg);

        if (ORD_ENTRY_ACT.CANCEL_2.equals(ordEntryActCd)) {
            if (params == null) {
                setResult(ZYPConstant.FLG_OFF_0);
            } else {
                setResult(scrnMsg.ordEntryActCd.getValue());
            }
        } else {
            setResult(scrnMsg.ordEntryActCd.getValue());
        }

        if (params != null) {
            setArgForSubScreen(params);
        }

        if (ORD_ENTRY_ACT.HOLDS.equals(ordEntryActCd) || ORD_ENTRY_ACT.HOLDS_2.equals(ordEntryActCd)) {
            // 2016/05/19 S21_NA#2104 Mod Start
            // openMultiModeScreen();
            openMultiModeScreenBySingleWindow();
            // 2016/05/19 S21_NA#2104 Mod End

        } else if (ORD_ENTRY_ACT.DELIVERY_OR_INVOICE.equals(ordEntryActCd) || ORD_ENTRY_ACT.DELIVERY_OR_INVOICE_2.equals(ordEntryActCd)) {
            // 2016/05/19 S21_NA#2104 Mod Start
            // openMultiModeScreen();
            openMultiModeScreenBySingleWindow();
            // 2016/05/19 S21_NA#2104 Mod End
            
        // 2018/07/20 S21_NA#26188 Mod Start
        } else if (ORD_ENTRY_ACT.D_I_S_C_LINE_UPDATE.equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_MASS_APPLY.equals(ordEntryActCd)) {
        //} else if (ORD_ENTRY_ACT..equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_2.equals(ordEntryActCd)) {
        // 2018/07/20 S21_NA#26188 Mod End
            // 2016/05/19 S21_NA#2104 Mod Start
            // openMultiModeScreen();
            // Mod Start 2017/10/11 QC#19910
//            openMultiModeScreenBySingleWindow();
            openMultiModeScreenBySingleWindowWithKey(scrnMsg.cpoOrdNum.getValue());
            // Mod End 2017/10/11 QC#19910
            // 2016/05/19 S21_NA#2104 Mod End

        } else if (ORD_ENTRY_ACT.PROFITABILITY_QA.equals(ordEntryActCd) || ORD_ENTRY_ACT.PROFITABILITY_QA_2.equals(ordEntryActCd)) {
            // 2016/05/19 S21_NA#2104 Mod Start
            // openMultiModeScreen();
            openMultiModeScreenBySingleWindow();
            // 2016/05/19 S21_NA#2104 Mod End

        } else if (ORD_ENTRY_ACT.DATA_INTEGRITY_DI.equals(ordEntryActCd) || ORD_ENTRY_ACT.DATA_INTEGRITY_DI_2.equals(ordEntryActCd)) {
            // 2016/05/19 S21_NA#2104 Mod Start
            // openMultiModeScreen();
            openMultiModeScreenBySingleWindow();
            // 2016/05/19 S21_NA#2104 Mod End

        // 2018/07/20 S21_NA#26188 Del Start
        //} else if (ORD_ENTRY_ACT.SERVICE_PRICING.equals(ordEntryActCd) || ORD_ENTRY_ACT.SERVICE_PRICING_2.equals(ordEntryActCd)) {
        //    // 2016/05/19 S21_NA#2104 Mod Start
        //    // openMultiModeScreen();
        //    openMultiModeScreenBySingleWindow();
        //    // 2016/05/19 S21_NA#2104 Mod End
        // 2018/07/20 S21_NA#26188 Del End

        } else if (ORD_ENTRY_ACT.FINAL_METER.equals(ordEntryActCd)) {
            // 2016/05/19 S21_NA#2104 Mod Start
            // openMultiModeScreen();
            openMultiModeScreenBySingleWindow();
            // 2016/05/19 S21_NA#2104 Mod End

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        } else if (ORD_ENTRY_ACT.SHOW_1ST_CONFIG.equals(ordEntryActCd)) {
            openMultiModeScreenBySingleWindowWithKey(scrnMsg.cpoOrdNum.getValue());
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        }
        // NWAL1500CommonLogicForOrderEntryAction.resetAction(scrnMsg);
    }
}
