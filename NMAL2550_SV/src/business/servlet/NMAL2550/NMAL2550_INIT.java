/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2550;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2550.NMAL2550CMsg;
import business.servlet.NMAL2550.common.NMAL2550CommonLogic;
import business.servlet.NMAL2550.constant.NMAL2550Constant;
import business.servlet.NMAL2550.constant.NMAL2550Constant.SETTING_BTN;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/11/07   Hitachi         J.Kim           Update          QC#15616
 * 2016/11/25   Hitachi         Y.Tsuchimoto    Update          QC#16198
 * 2018/01/26   CITS            K.Ogino         Update          QC#22473
 *</pre>
 */
public class NMAL2550_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2550BMsg scrnMsg = (NMAL2550BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        Object[] params = (Object[]) arg;

        if (arg instanceof Object[]) {
            setParams(params, scrnMsg);
        }

        NMAL2550CMsg bizMsg = new NMAL2550CMsg();
        bizMsg.setBusinessID(NMAL2550Constant.APP_ID);
        bizMsg.setFunctionCode(NMAL2550Constant.FUNCTION_CODE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2550BMsg scrnMsg = (NMAL2550BMsg) bMsg;
        NMAL2550CMsg bizMsg = (NMAL2550CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2550CommonLogic.initCommonButton(this);
        NMAL2550CommonLogic.clearCondition(scrnMsg);

        setDisplay(scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL2550BMsg scrnMsg = (NMAL2550BMsg) bMsg;

        // Header
        scrnMsg.coaCmpyCd_H1.setNameForMessage(NMAL2550Constant.NAME_FOR_MESSAGE_COMPANY);
        scrnMsg.coaAfflCd_H1.setNameForMessage(NMAL2550Constant.NAME_FOR_MESSAGE_AFFILIATE);
        scrnMsg.coaBrCd_H1.setNameForMessage(NMAL2550Constant.NAME_FOR_MESSAGE_BRANCH);
        scrnMsg.coaCcCd_H1.setNameForMessage(NMAL2550Constant.NAME_FOR_MESSAGE_COST_CENTER);
        scrnMsg.coaAcctCd_H1.setNameForMessage(NMAL2550Constant.NAME_FOR_MESSAGE_ACCOUNT);
        scrnMsg.coaProdCd_H1.setNameForMessage(NMAL2550Constant.NAME_FOR_MESSAGE_PRODUCT);
        scrnMsg.coaChCd_H1.setNameForMessage(NMAL2550Constant.NAME_FOR_MESSAGE_CHANNEL);
        scrnMsg.coaProjCd_H1.setNameForMessage(NMAL2550Constant.NAME_FOR_MESSAGE_PROJECT);
        scrnMsg.coaExtnCd_H1.setNameForMessage(NMAL2550Constant.NAME_FOR_MESSAGE_EXTENSION);
    }

    private void setParams(Object[] params, NMAL2550BMsg scrnMsg) {

        // Input Parameter.
        // Function ID
        EZDBStringItem param0 = (EZDBStringItem) params[0];
        if (ZYPCommonFunc.hasValue(param0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.appFuncId, param0);
        }

        // Company Code
        EZDBStringItem param1 = (EZDBStringItem) params[1];
        if (ZYPCommonFunc.hasValue(param1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaCmpyCd_H1, param1);
        }

        // Affiliate Code
        EZDBStringItem param2 = (EZDBStringItem) params[2];
        if (ZYPCommonFunc.hasValue(param2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaAfflCd_H1, param2);
        }

        // Branch Code
        EZDBStringItem param3 = (EZDBStringItem) params[3];
        if (ZYPCommonFunc.hasValue(param3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaBrCd_H1, param3);
        }

        // Cost Center
        EZDBStringItem param4 = (EZDBStringItem) params[4];
        if (ZYPCommonFunc.hasValue(param4)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaCcCd_H1, param4);
        }

        // Account Code
        EZDBStringItem param5 = (EZDBStringItem) params[5];
        if (ZYPCommonFunc.hasValue(param5)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaAcctCd_H1, param5);
        }

        // Product Code
        EZDBStringItem param6 = (EZDBStringItem) params[6];
        if (ZYPCommonFunc.hasValue(param6)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdCd_H1, param6);
        }

        // Channel Code
        EZDBStringItem param7 = (EZDBStringItem) params[7];
        if (ZYPCommonFunc.hasValue(param7)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaChCd_H1, param7);
        }

        // Project Code
        EZDBStringItem param8 = (EZDBStringItem) params[8];
        if (ZYPCommonFunc.hasValue(param8)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaProjCd_H1, param8);
        }

        // Extension Code
        EZDBStringItem param9 = (EZDBStringItem) params[9];
        if (ZYPCommonFunc.hasValue(param9)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaExtnCd_H1, param9);
        }

        // START 2016/11/07 J.Kim [QC#15616,ADD]
        if (params.length > 10) {
            EZDBStringItem param10 = (EZDBStringItem) params[10];
            if (ZYPCommonFunc.hasValue(param10)) {
                String pgFlg = param10.getValue();
                if (ZYPConstant.FLG_ON_Y.equals(pgFlg) || ZYPConstant.FLG_OFF_N.equals(pgFlg)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxPgFlg, param10);
                }
            }
        }
        // END 2016/11/07 J.Kim [QC#15616,ADD]
    }

    private void setDisplay(NMAL2550BMsg scrnMsg) {

        // START 2016/11/07 J.Kim [QC#15616,ADD]
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxPgFlg.getValue())) {
            scrnMsg.coaCmpyDplyFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.coaAfflDplyFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.coaBrDplyFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.coaCcDplyFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.coaAcctDplyFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.coaProdDplyFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.coaChDplyFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.coaProjDplyFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.coaExtnDplyFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
            setButtonEnabled(NMAL2550Constant.CMN_BTN.CLEAR.getHtmlNm(), false);
            //START 2017/03/15 E.Kameishi [QC#15854,ADD]
            setButtonEnabled(NMAL2550Constant.FILTER_BTN, false);
            //END 2017/03/15 E.Kameishi [QC#15854,ADD]
        }
        // END 2016/11/07 J.Kim [QC#15616,ADD]

        // QC#22473
        boolean isFocus = false;
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.coaCmpyDplyFlg_H1.getValue())) {
            scrnMsg.coaCmpyCd_H1.setInputProtected(false);
            scrnMsg.coaCmpyCd_H2.setInputProtected(false);
            setButtonEnabled(SETTING_BTN.COMPANY.getName(), true);
            if (!isFocus) {
                scrnMsg.setFocusItem(scrnMsg.coaCmpyCd_H1);
                isFocus = true;
            }
        } else {
            scrnMsg.coaCmpyCd_H1.setInputProtected(true);
            scrnMsg.coaCmpyCd_H2.setInputProtected(true);
            setButtonEnabled(SETTING_BTN.COMPANY.getName(), false);
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.coaBrDplyFlg_H1.getValue())) {
            scrnMsg.coaBrCd_H1.setInputProtected(false);
            scrnMsg.coaBrCd_H2.setInputProtected(false);
            setButtonEnabled(SETTING_BTN.BRANCH.getName(), true);
            if (!isFocus) {
                scrnMsg.setFocusItem(scrnMsg.coaBrCd_H1);
                isFocus = true;
            }
        } else {
            scrnMsg.coaBrCd_H1.setInputProtected(true);
            scrnMsg.coaBrCd_H2.setInputProtected(true);
            setButtonEnabled(SETTING_BTN.BRANCH.getName(), false);
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.coaCcDplyFlg_H1.getValue())) {
            scrnMsg.coaCcCd_H1.setInputProtected(false);
            scrnMsg.coaCcCd_H2.setInputProtected(false);
            setButtonEnabled(SETTING_BTN.COST_CENTER.getName(), true);
            if (!isFocus) {
                scrnMsg.setFocusItem(scrnMsg.coaCcCd_H1);
                isFocus = true;
            }
        } else {
            scrnMsg.coaCcCd_H1.setInputProtected(true);
            scrnMsg.coaCcCd_H2.setInputProtected(true);
            setButtonEnabled(SETTING_BTN.COST_CENTER.getName(), false);
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.coaAcctDplyFlg_H1.getValue())) {
            scrnMsg.coaAcctCd_H1.setInputProtected(false);
            scrnMsg.coaAcctCd_H2.setInputProtected(false);
            setButtonEnabled(SETTING_BTN.ACCOUNT.getName(), true);
            if (!isFocus) {
                scrnMsg.setFocusItem(scrnMsg.coaAcctCd_H1);
                isFocus = true;
            }
        } else {
            scrnMsg.coaAcctCd_H1.setInputProtected(true);
            scrnMsg.coaAcctCd_H2.setInputProtected(true);
            setButtonEnabled(SETTING_BTN.ACCOUNT.getName(), false);
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.coaProdDplyFlg_H1.getValue())) {
            scrnMsg.coaProdCd_H1.setInputProtected(false);
            scrnMsg.coaProdCd_H2.setInputProtected(false);
            setButtonEnabled(SETTING_BTN.PRODUCT.getName(), true);
            if (!isFocus) {
                scrnMsg.setFocusItem(scrnMsg.coaProdCd_H1);
                isFocus = true;
            }
        } else {
            scrnMsg.coaProdCd_H1.setInputProtected(true);
            scrnMsg.coaProdCd_H2.setInputProtected(true);
            setButtonEnabled(SETTING_BTN.PRODUCT.getName(), false);
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.coaChDplyFlg_H1.getValue())) {
            scrnMsg.coaChCd_H1.setInputProtected(false);
            scrnMsg.coaChCd_H2.setInputProtected(false);
            setButtonEnabled(SETTING_BTN.CHANNEL.getName(), true);
            if (!isFocus) {
                scrnMsg.setFocusItem(scrnMsg.coaChCd_H1);
                isFocus = true;
            }
        } else {
            scrnMsg.coaChCd_H1.setInputProtected(true);
            scrnMsg.coaChCd_H2.setInputProtected(true);
            setButtonEnabled(SETTING_BTN.CHANNEL.getName(), false);
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.coaAfflDplyFlg_H1.getValue())) {
            scrnMsg.coaAfflCd_H1.setInputProtected(false);
            scrnMsg.coaAfflCd_H2.setInputProtected(false);
            setButtonEnabled(SETTING_BTN.AFFILIATE.getName(), true);
            if (!isFocus) {
                scrnMsg.setFocusItem(scrnMsg.coaAfflCd_H1);
                isFocus = true;
            }
        } else {
            scrnMsg.coaAfflCd_H1.setInputProtected(true);
            scrnMsg.coaAfflCd_H2.setInputProtected(true);
            setButtonEnabled(SETTING_BTN.AFFILIATE.getName(), false);
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.coaProjDplyFlg_H1.getValue())) {
            scrnMsg.coaProjCd_H1.setInputProtected(false);
            scrnMsg.coaProjCd_H2.setInputProtected(false);
            setButtonEnabled(SETTING_BTN.PROJECT.getName(), true);
            if (!isFocus) {
                scrnMsg.setFocusItem(scrnMsg.coaProjCd_H1);
                isFocus = true;
            }
        } else {
            scrnMsg.coaProjCd_H1.setInputProtected(true);
            scrnMsg.coaProjCd_H2.setInputProtected(true);
            setButtonEnabled(SETTING_BTN.PROJECT.getName(), false);
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.coaExtnDplyFlg_H1.getValue())) {
            scrnMsg.coaExtnCd_H1.setInputProtected(false);
            scrnMsg.coaExtnCd_H2.setInputProtected(false);
            setButtonEnabled(SETTING_BTN.EXTENSION.getName(), true);
            if (!isFocus) {
                scrnMsg.setFocusItem(scrnMsg.coaExtnCd_H1);
                isFocus = true;
            }
        } else {
            scrnMsg.coaExtnCd_H1.setInputProtected(true);
            scrnMsg.coaExtnCd_H2.setInputProtected(true);
            setButtonEnabled(SETTING_BTN.EXTENSION.getName(), false);
        }

        if (!isFocus) {
            scrnMsg.setFocusItem(scrnMsg.xxDtlCd_F1);
        }

        // START 2016/11/25 Y.Tsuchimoto [QC#16198,ADD]
        scrnMsg.coaCmpyDescTxt_H1.setInputProtected(true);
        scrnMsg.coaBrDescTxt_H1.setInputProtected(true);
        scrnMsg.coaCcDescTxt_H1.setInputProtected(true);
        scrnMsg.coaAcctDescTxt_H1.setInputProtected(true);
        scrnMsg.coaProdDescTxt_H1.setInputProtected(true);
        scrnMsg.coaChDescTxt_H1.setInputProtected(true);
        scrnMsg.coaAfflDescTxt_H1.setInputProtected(true);
        scrnMsg.coaProjDescTxt_H1.setInputProtected(true);
        scrnMsg.coaExtnDescTxt_H1.setInputProtected(true);
        // END   2016/11/25 Y.Tsuchimoto [QC#16198,ADD]
    }
}
