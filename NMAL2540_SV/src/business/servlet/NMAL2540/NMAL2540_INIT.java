/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2540;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2540.NMAL2540CMsg;
import business.servlet.NMAL2540.common.NMAL2540CommonLogic;
import business.servlet.NMAL2540.constant.NMAL2540Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2540_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // return bizMsg;
        NMAL2540BMsg scrnMsg = (NMAL2540BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        Object[] params = (Object[]) arg;

        if (arg instanceof Object[]) {
            setParams(params, scrnMsg);
        }

        NMAL2540CMsg bizMsg = new NMAL2540CMsg();
        bizMsg.setBusinessID(NMAL2540Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2540Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2540BMsg scrnMsg = (NMAL2540BMsg) bMsg;
        NMAL2540CMsg bizMsg = (NMAL2540CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.firstLineAddr_H1);

        NMAL2540CommonLogic.initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NMAL2540BMsg scrnMsg = (NMAL2540BMsg) arg0;

        // Header
        scrnMsg.firstLineAddr_H1.setNameForMessage(NMAL2540Constant.NAME_FOR_MESSAGE_ADDRESS_1);
        scrnMsg.scdLineAddr_H1.setNameForMessage(NMAL2540Constant.NAME_FOR_MESSAGE_ADDRESS_2);
        scrnMsg.thirdLineAddr_H1.setNameForMessage(NMAL2540Constant.NAME_FOR_MESSAGE_ADDRESS_3);
        scrnMsg.frthLineAddr_H1.setNameForMessage(NMAL2540Constant.NAME_FOR_MESSAGE_ADDRESS_4);
        scrnMsg.ctyAddr_H1.setNameForMessage(NMAL2540Constant.NAME_FOR_MESSAGE_CITY);
        scrnMsg.stCd_H1.setNameForMessage(NMAL2540Constant.NAME_FOR_MESSAGE_STATE);
        scrnMsg.provNm_H1.setNameForMessage(NMAL2540Constant.NAME_FOR_MESSAGE_PROVINCE);
        scrnMsg.cntyNm_H1.setNameForMessage(NMAL2540Constant.NAME_FOR_MESSAGE_COUNTY);
        scrnMsg.postCd_H1.setNameForMessage(NMAL2540Constant.NAME_FOR_MESSAGE_POSTAL_CODE);
        scrnMsg.ctryCd_H1.setNameForMessage(NMAL2540Constant.NAME_FOR_MESSAGE_COUNTRY_CODE);
        scrnMsg.ctryNm_H1.setNameForMessage(NMAL2540Constant.NAME_FOR_MESSAGE_COUNTRY_NAME);
    }

    private void setParams(Object[] params, NMAL2540BMsg scrnMsg) {

        // Input Parameter.
        // Address1
        EZDBStringItem param0 = (EZDBStringItem) params[NMAL2540Constant.IDX_00];
        if (ZYPCommonFunc.hasValue(param0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.firstLineAddr_H1, param0);
        }

        // Address2
        EZDBStringItem param1 = (EZDBStringItem) params[NMAL2540Constant.IDX_01];
        if (ZYPCommonFunc.hasValue(param1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.scdLineAddr_H1, param1);
        }

        // Address3
        EZDBStringItem param2 = (EZDBStringItem) params[NMAL2540Constant.IDX_02];
        if (ZYPCommonFunc.hasValue(param2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.thirdLineAddr_H1, param2);
        }

        // Address4
        EZDBStringItem param3 = (EZDBStringItem) params[NMAL2540Constant.IDX_03];
        if (ZYPCommonFunc.hasValue(param3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.frthLineAddr_H1, param3);
        }

        // City
        EZDBStringItem param4 = (EZDBStringItem) params[NMAL2540Constant.IDX_04];
        if (ZYPCommonFunc.hasValue(param4)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_H1, param4);
        }

        // State
        EZDBStringItem param5 = (EZDBStringItem) params[NMAL2540Constant.IDX_05];
        if (ZYPCommonFunc.hasValue(param5)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_H1, param5);
        }

        // Province
        EZDBStringItem param6 = (EZDBStringItem) params[NMAL2540Constant.IDX_06];
        if (ZYPCommonFunc.hasValue(param6)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.provNm_H1, param6);
        }

        // County
        EZDBStringItem param7 = (EZDBStringItem) params[NMAL2540Constant.IDX_07];
        if (ZYPCommonFunc.hasValue(param7)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_H1, param7);
        }

        // Postal Code
        EZDBStringItem param8 = (EZDBStringItem) params[NMAL2540Constant.IDX_08];
        if (ZYPCommonFunc.hasValue(param8)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_H1, param8);
        }

        // Country Code
        EZDBStringItem param9 = (EZDBStringItem) params[NMAL2540Constant.IDX_09];
        if (ZYPCommonFunc.hasValue(param9)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctryCd_H1, param9);
        }

    }
}
