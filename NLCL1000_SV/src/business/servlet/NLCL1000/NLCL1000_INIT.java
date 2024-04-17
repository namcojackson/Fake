/**
 *<pre>
 * Copyright (c) 2012 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.servlet.NLCL1000;

import java.io.Serializable;
import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1000.NLCL1000CMsg;
import business.servlet.NLCL1000.common.NLCL1000CommonLogic;
import business.servlet.NLCL1000.constant.NLCL1000Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Stock Over View Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/07/17   Fujitsu         Y.Mori          Create          N/A
 *</pre>
 */
public class NLCL1000_INIT extends S21INITCommonHandler implements NLCL1000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1000BMsg scrnMsg = (NLCL1000BMsg) bMsg;

        // get parameters.
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            EZDBStringItem param0 = (EZDBStringItem) params[0];           // XX_RQST_FLG_01
            EZDBStringItem param1 = (EZDBStringItem) params[1];           // XX_RQST_FLG_02
            EZDBStringItem param2 = (EZDBStringItem) params[2];           // XX_RQST_FLG_03
            EZDBStringItem param3 = (EZDBStringItem) params[3];           // XX_RQST_FLG_04
            EZDBStringItem param4 = (EZDBStringItem) params[4];           // XX_SET_FLG_AV
            EZDBStringItem param5 = (EZDBStringItem) params[5];           // XX_SET_FLG_DI
            EZDBStringItem param6 = (EZDBStringItem) params[6];           // XX_SET_FLG_SE
            EZDBStringItem param7 = (EZDBStringItem) params[7];           // XX_SET_FLG_WH
            EZDBStringItem param8 = (EZDBStringItem) params[8];           // XX_SET_FLG_TE
            EZDMsg.copy((EZDBMsgArray)params[9], null, scrnMsg.P, null); // DetailList

            // scrnMsg.P check
            for (int i = 0; i < scrnMsg.P.getValidCount(); i++) {
                if (scrnMsg.P.no(i) == null) {
                    scrnMsg.P.clear();
                    scrnMsg.P.setValidCount(0);
                    break;
                }
            }

            // CSA Inventory Availability Flag(Input Parameter)
            if (ZYPCommonFunc.hasValue(param0)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_01, param0.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_01, ZYPConstant.FLG_OFF_N);
            }
            // CUSA WS Inventory Availability Flag(Input Parameter)
            if (ZYPCommonFunc.hasValue(param1)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_02, param1.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_02, ZYPConstant.FLG_OFF_N);
            }
            // CUSA Parts Inventory Availability Flag(Input Parameter)
            if (ZYPCommonFunc.hasValue(param2)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_03, param2.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_03, ZYPConstant.FLG_OFF_N);
            }
            // CVI Inventory Availability Flag(Input Parameter)
            if (ZYPCommonFunc.hasValue(param3)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_04, param3.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_04, ZYPConstant.FLG_OFF_N);
            }
            // Available Location Only(Input Parameter)
            if (ZYPCommonFunc.hasValue(param4)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_AV, param4.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_AV, ZYPConstant.FLG_OFF_N);
            }
            // Display Supersede Flag(Input Parameter)
            if (ZYPCommonFunc.hasValue(param5)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_DI, param5.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_DI, ZYPConstant.FLG_OFF_N);
            }
            // Seach by Original Condition(Input Parameter)
            // 12/07/2015 mod start
            /*
            if (ZYPCommonFunc.hasValue(param6)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_SE, param6.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_SE, ZYPConstant.FLG_OFF_N);
            }
            */
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_SE, ZYPConstant.FLG_ON_Y);
            // 12/07/2015 mod end
            // WH Location Flag(Input Parameter)
            if (ZYPCommonFunc.hasValue(param7)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_WH, param7.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_WH, ZYPConstant.FLG_OFF_N);
            }
            // Tech Location Flag(Input Parameter)
            if (ZYPCommonFunc.hasValue(param8)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_TE, param8.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_TE, ZYPConstant.FLG_OFF_N);
            }

//            // Display Supersede Check box(Screen)
//            if (scrnMsg.xxSetFlg_DI.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_DI,ZYPConstant.FLG_ON_Y);
//            } else {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_DI,ZYPConstant.FLG_OFF_N);
//            }
//
//            // Available Location Only Checkbox(Screen)
//            if (scrnMsg.xxSetFlg_AV.getValue().equals(ZYPConstant.FLG_ON_Y)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_AV,ZYPConstant.FLG_ON_Y);
//            } else {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_AV,ZYPConstant.FLG_OFF_N);
//            }

        } else {
            // CSA Inventory Availability Flag(Input Parameter)
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_01, ZYPConstant.FLG_OFF_N);
            // CUSA WS Inventory Availability Flag(Input Parameter)
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_02, ZYPConstant.FLG_OFF_N);
            // CUSA Parts Inventory Availability Flag(Input Parameter)
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_03, ZYPConstant.FLG_OFF_N);
            // CVI Inventory Availability Flag(Input Parameter)
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_04, ZYPConstant.FLG_OFF_N);
            // Available Location Only(Input Parameter)
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_AV, ZYPConstant.FLG_OFF_N);
            // Display Supersede Flag(Input Parameter)
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_DI, ZYPConstant.FLG_OFF_N);
            // Seach by Original Condition(Input Parameter)
            // 12/07/2015 mod start
            // ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_SE, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_SE, ZYPConstant.FLG_ON_Y);
            // 12/07/2015 mod end
            // WH Location Flag(Input Parameter)
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_WH, ZYPConstant.FLG_OFF_N);
            // Tech Location Flag(Input Parameter)
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSetFlg_TE, ZYPConstant.FLG_OFF_N);
            // Detail List(Parameter)
            scrnMsg.P.setValidCount(0);
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

         NLCL1000BMsg scrnMsg = (NLCL1000BMsg) bMsg;
         NLCL1000CMsg bizMsg = new NLCL1000CMsg();
         bizMsg.setBusinessID(MY_BUSINESS_ID);
         bizMsg.setFunctionCode(FUNCTION_ID_SEARCH);
         EZDMsg.copy(scrnMsg, null, bizMsg, null);

         return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1000BMsg scrnMsg = (NLCL1000BMsg) bMsg;
        NLCL1000CMsg bizMsg = (NLCL1000CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL1000CommonLogic.initCommonButton(this);
        NLCL1000CommonLogic.initButton(this);
        NLCL1000CommonLogic.initScrn(scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NLCL1000BMsg scrnMsg = (NLCL1000BMsg) bMsg;
    }
}
