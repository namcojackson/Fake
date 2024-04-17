/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWZM2274W;
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
 * NWAL1500Scrn00_OnChange_OrderEntryActionAddlInfo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         Y.Taoka         Create          QC#1694
 * 2016/05/19   Fujitsu         S.Takami        Update          S21_NA#2104
 * 2018/01/15   Fujitsu         A.Kosai         Update          S21_NA#22280
 * 2018/07/03   Fujitsu         A.Kosai         Update          S21_NA#26908
 * 2018/07/20   Fujitsu         Mz.Takahashi    Update          S21_NA#26188
 * 2018/07/27   Fujitsu         T.Noguchi       Update          S21_NA#14307
 *</pre>
 */
public class NWAL1500Scrn00_OnChange_OrderEntryActionAddlInfo extends S21CommonHandler {

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
        // 2018/07/27 S21_NA#14307 Add Start
        } else if(ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS.equals(ordEntryActCd) || ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS_2.equals(ordEntryActCd)) {
            if (params == null) {
                scrnMsg.setMessageInfo(NWZM2274W, null);
                setResult(ZYPConstant.FLG_OFF_0);
            } else {
                setResult(ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS);
            }
        // 2018/07/27 S21_NA#14307 Add End
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
            // 2018/01/15 S21_NA#22280 Mod Start
            //openMultiModeScreenBySingleWindow();
            openMultiModeScreenBySingleWindowWithKey(scrnMsg.cpoOrdNum.getValue());
            // 2018/01/15 S21_NA#22280 Mod End
            // 2016/05/19 S21_NA#2104 Mod End

        // 2018/07/20 S21_NA#26188 Mod Start
        } else if (ORD_ENTRY_ACT.D_I_S_C_LINE_UPDATE.equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_MASS_APPLY.equals(ordEntryActCd)) {
        //} else if (ORD_ENTRY_ACT.D_I_S_C.equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_2.equals(ordEntryActCd)) {
        // 2018/07/20 S21_NA#26188 Mod End
            // 2016/05/19 S21_NA#2104 Mod Start
            // openMultiModeScreen();
            openMultiModeScreenBySingleWindow();
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
            // 2018/01/15 S21_NA#22280 Mod Start
            //openMultiModeScreenBySingleWindow();
            openMultiModeScreenBySingleWindowWithKey(scrnMsg.cpoOrdNum.getValue());
            // 2018/01/15 S21_NA#22280 Mod End
            // 2016/05/19 S21_NA#2104 Mod End

        // 2018/07/03 S21_NA#26908 Add Start
        } else if (ORD_ENTRY_ACT.EDI_ATTRIBUTES.equals(ordEntryActCd) || ORD_ENTRY_ACT.EDI_ATTRIBUTES_2.equals(ordEntryActCd)) {
            openMultiModeScreenBySingleWindow();

        // 2018/07/03 S21_NA#26908 Add End
        }
        // NWAL1500CommonLogicForOrderEntryAction.resetAction(scrnMsg);
    }
}
