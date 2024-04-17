/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2530;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2530.NMAL2530CMsg;
import business.servlet.NMAL2530.common.NMAL2530CommonLogic;
import business.servlet.NMAL2530.constant.NMAL2530Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 * 2015/02/29   Fujitsu         M.Suzuki        Update          QC#4540
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 *</pre>
 */
public class NMAL2530_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2530BMsg scrnMsg = (NMAL2530BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        Object[] params = (Object[]) arg;

        if (arg instanceof Object[]) {
            setParams(params, scrnMsg);
        }

        NMAL2530CMsg bizMsg = new NMAL2530CMsg();
        bizMsg.setBusinessID(NMAL2530Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NMAL2530Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2530BMsg scrnMsg = (NMAL2530BMsg) bMsg;
        NMAL2530CMsg bizMsg  = (NMAL2530CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2530CommonLogic.initialControlScreen(this, scrnMsg);

        S21TableColorController tblColor = new S21TableColorController(NMAL2530Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        S21SortColumnIMGController.clearIMG(NMAL2530Constant.SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.orgStruTpCd_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL2530BMsg scrnMsg = (NMAL2530BMsg) bMsg;

        // Header
        scrnMsg.orgStruTpCd_H1.setNameForMessage("Business Area");
        scrnMsg.orgNm_H1.setNameForMessage("Organization Name");
        scrnMsg.orgNm_H2.setNameForMessage("Parent Organization Name");
        scrnMsg.tocNm_H1.setNameForMessage("Resource Name");
        scrnMsg.psnCd_H1.setNameForMessage("Employee ID");
        scrnMsg.orgTierCd_H1.setNameForMessage("Tier");
        // 2016/02/19 S21_NA#4540 Mod Start ------------
        scrnMsg.csrRgTpCd_H1.setNameForMessage("SCR Region");
        // 2016/02/19 S21_NA#4545 Mod End --------------
        // MOD START S21_NA QC#16481
        scrnMsg.effFromDt_FR.setNameForMessage("Start Date");
        scrnMsg.effFromDt_TO.setNameForMessage("Start Date");
        // MOD END S21_NA QC#16481
    }

    private void setParams(Object[] params, NMAL2530BMsg scrnMsg) {

        // Input Parameter.
        // Business Area
        EZDBStringItem param0 = (EZDBStringItem) params[0];
        if (ZYPCommonFunc.hasValue(param0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgStruTpCd_H1, param0);
        }

        // Organization Name
        EZDBStringItem param1 = (EZDBStringItem) params[1];
        if (ZYPCommonFunc.hasValue(param1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H1, param1);
        }

        // Parent Organization Name
        EZDBStringItem param2 = (EZDBStringItem) params[2];
        if (ZYPCommonFunc.hasValue(param2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H2, param2);
        }

        // Resource Name
        EZDBStringItem param3 = (EZDBStringItem) params[3];
        if (ZYPCommonFunc.hasValue(param3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.tocNm_H1, param3);
        }

        // Employee ID
        EZDBStringItem param4 = (EZDBStringItem) params[4];
        if (ZYPCommonFunc.hasValue(param4)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_H1, param4);
        }

        // Tier
        EZDBStringItem param5 = (EZDBStringItem) params[5];
        if (ZYPCommonFunc.hasValue(param5)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgTierCd_H1, param5);
        }

        // SCR Region
        EZDBStringItem param6 = (EZDBStringItem) params[6];
        if (ZYPCommonFunc.hasValue(param6)) {
            // 2016/02/19 S21_NA#4540 Mod Start --------------
            ZYPEZDItemValueSetter.setValue(scrnMsg.csrRgTpCd_H1, param6);
            // 2016/02/19 S21_NA#4545 Mod Start --------------
        }
    }
}
