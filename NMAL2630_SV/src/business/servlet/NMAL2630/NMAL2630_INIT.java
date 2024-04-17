/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2630;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;


import business.blap.NMAL2630.NMAL2630CMsg;
import business.servlet.NMAL2630.common.NMAL2630CommonLogic;
import business.servlet.NMAL2630.constant.NMAL2630Constant;

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
 * 2016/05/31   SRAA            Y.Chen          Update          QC#9182
 * 2016/06/23   Hitach          A.Kohinata      Update          CSA-QC#10280
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 * 2017/11/28   Fujitsu         Hd.Sugawara     Update          QC#21044
 *</pre>
 */
public class NMAL2630_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2630BMsg scrnMsg = (NMAL2630BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        Object[] params = (Object[]) arg;

        if (arg instanceof Object[]) {
            setParams(params, scrnMsg);
        }

        NMAL2630CMsg bizMsg = new NMAL2630CMsg();
        bizMsg.setBusinessID("NMAL2630");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2630BMsg scrnMsg = (NMAL2630BMsg) bMsg;
        NMAL2630CMsg bizMsg  = (NMAL2630CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2630CommonLogic.initialControlScreen(this, scrnMsg);

        S21TableColorController tblColor = new S21TableColorController(NMAL2630Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        S21SortColumnIMGController.clearIMG(NMAL2630Constant.SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.orgStruTpCd_H1);
        scrnMsg.xxDplyTab.setValue("");

        setButtonEnabled(NMAL2630Constant.BTN_INSERT, true);
        setButtonEnabled(NMAL2630Constant.BTN_DELETE, true);

    }
    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL2630BMsg scrnMsg = (NMAL2630BMsg) bMsg;

        // Header
        scrnMsg.orgStruTpCd_H1.setNameForMessage("Business Area");
        scrnMsg.orgNm_H1.setNameForMessage("Territory Name");
        // QC#9182
        scrnMsg.orgCd_H1.setNameForMessage("Territory ID");
        scrnMsg.orgNm_H2.setNameForMessage("Parent Territory Name");
        scrnMsg.xxPsnNm_H1.setNameForMessage("Resource Name");
        scrnMsg.psnCd_H1.setNameForMessage("Employee ID");
        // 2016/06/23 CSA-QC#10280 Mod Start
        scrnMsg.orgTierCd_H1.setNameForMessage("Tier");
//        scrnMsg.lineBizTpCd_H1.setNameForMessage("Line of Business");
        scrnMsg.trtyTpCd_H1.setNameForMessage("Territory Type");
        scrnMsg.trtyGrpTpCd_H1.setNameForMessage("Territory Group");
        // 2016/06/23 CSA-QC#10280 Mod End
        scrnMsg.orgNm_H3.setNameForMessage("Organization Name");
        scrnMsg.effFromDt_FR.setNameForMessage("Start Date"); // MOD S21_NA QC#16481
        scrnMsg.effFromDt_TO.setNameForMessage("Start Date"); // MOD S21_NA QC#16481
    }
    private void setParams(Object[] params, NMAL2630BMsg scrnMsg) {

        // Input Parameter.
        // Business Area
        EZDBStringItem param0 = (EZDBStringItem) params[0];
        if (ZYPCommonFunc.hasValue(param0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgStruTpCd_H1, param0);
        }

        // Territory Name
        EZDBStringItem param1 = (EZDBStringItem) params[1];
        if (ZYPCommonFunc.hasValue(param1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H1, param1);
        }

        // Parent Territory Name
        EZDBStringItem param2 = (EZDBStringItem) params[2];
        if (ZYPCommonFunc.hasValue(param2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H2, param2);
        }

        // Resource Name
        EZDBStringItem param3 = (EZDBStringItem) params[3];
        if (ZYPCommonFunc.hasValue(param3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_H1, param3);
        }

        // Employee ID
        EZDBStringItem param4 = (EZDBStringItem) params[4];
        if (ZYPCommonFunc.hasValue(param4)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_H1, param4);
        }

        // Rank
        EZDBStringItem param5 = (EZDBStringItem) params[5];
        if (ZYPCommonFunc.hasValue(param5)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgTierCd_H1, param5);
        }

        // 2016/06/23 CSA-QC#10280 Mod Start
        // Territory Group Type
        EZDBStringItem param6 = (EZDBStringItem) params[6];
        if (ZYPCommonFunc.hasValue(param6)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.trtyGrpTpCd_H1, param6);
        }
        // 2016/06/23 CSA-QC#10280 Mod End

        // Organization Name
        EZDBStringItem param7 = (EZDBStringItem) params[7];
        if (ZYPCommonFunc.hasValue(param7)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H3, param7);
        }

        // QC#9182
        if (params.length > 8) {
            EZDBStringItem param8 = (EZDBStringItem) params[8];
            if (ZYPCommonFunc.hasValue(param8)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_H1, param8);
            }
        }
        // 2016/06/23 CSA-QC#10280 Add Start
        // Territory Type
        if (params.length > 9) {
            EZDBStringItem param9 = (EZDBStringItem) params[9];
            if (ZYPCommonFunc.hasValue(param9)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.trtyTpCd_H1, param9);
            }
        }
        // 2016/06/23 CSA-QC#10280 Add End

        // Add Start 2017/11/28 QC#21044
        if (params.length > 10) {
            EZDBStringItem param10 = (EZDBStringItem) params[10];
            if (ZYPCommonFunc.hasValue(param10)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.nonSlsRepFlg_H1, param10);
            }
        }
        // Add End 2017/11/28 QC#21044
    }
}
