/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6790;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6790.NMAL6790CMsg;
import business.servlet.NMAL6790.common.NMAL6790CommonLogic;
import business.servlet.NMAL6790.constant.NMAL6790Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Fujitsu         K.Koitabashi    Create          N/A
 * 2018/10/11   Fujitsu         T.Noguchi       Update          QC#27869
 *</pre>
 */
public class NMAL6790_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6790BMsg scrnMsg = (NMAL6790BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        Object[] params = (Object[]) arg;

        if (arg instanceof Object[]) {
            if (params != null && params.length <= NMAL6790Constant.IDX_18) {
                setParams(params, scrnMsg);
            } else {
                return null;
            }
        }

        NMAL6790CMsg bizMsg = new NMAL6790CMsg();
        bizMsg.setBusinessID(NMAL6790Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NMAL6790Constant.FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6790BMsg scrnMsg = (NMAL6790BMsg) bMsg;
        NMAL6790CMsg bizMsg = (NMAL6790CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6790CommonLogic.initialControlScreen(this, scrnMsg);

        S21TableColorController tblColor = new S21TableColorController(NMAL6790Constant.SCREEN_ID, scrnMsg);

        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        S21SortColumnIMGController.clearIMG(NMAL6790Constant.SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        tblColor.setAlternateRowsBG("B", scrnMsg.B);
        S21SortColumnIMGController.clearIMG(NMAL6790Constant.SCREEN_ID, scrnMsg, scrnMsg.B.no(0).getBaseContents());

        tblColor.setRowStyle("A", 0, NMAL6790Constant.BACKGROUND_COLOR_YELLOW);

        scrnMsg.setFocusItem(scrnMsg.ctacTpCd_H1);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL6790BMsg scrnMsg = (NMAL6790BMsg) bMsg;

        // Header
        scrnMsg.dsAcctNm_H1.setNameForMessage("Account Name");
        scrnMsg.dsAcctNum_H1.setNameForMessage("Account#");
        scrnMsg.locNum_H1.setNameForMessage("Location#");
        scrnMsg.serNum_H1.setNameForMessage("Serial#");
        scrnMsg.dsCtacPntValTxt_H2.setNameForMessage("Email Address");
        scrnMsg.ctacPsnFirstNm_H1.setNameForMessage("First Name");
        scrnMsg.ctacPsnLastNm_H1.setNameForMessage("Last Name");
        scrnMsg.dsLocNm_H1.setNameForMessage("Location Name");
        scrnMsg.dsCtacPntValTxt_H1.setNameForMessage("Work Phone");

    }

    private void setParams(Object[] params, NMAL6790BMsg scrnMsg) {
        // Input Parameter.

        // Type
        EZDBStringItem param0 = (EZDBStringItem) params[NMAL6790Constant.IDX_00];
        if (ZYPCommonFunc.hasValue(param0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctacTpCd_H1, param0);
        }

        // Header Inactive Flag
        EZDBStringItem param1 = (EZDBStringItem) params[NMAL6790Constant.IDX_01];
        if (ZYPCommonFunc.hasValue(param1)) {
            if (ZYPConstant.FLG_ON_Y.equals(param1.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_H1, param1);
            }
        }

        // Account Name
        EZDBStringItem param2 = (EZDBStringItem) params[NMAL6790Constant.IDX_02];
        if (ZYPCommonFunc.hasValue(param2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H1, param2);
        }

        // Account Number
        EZDBStringItem param3 = (EZDBStringItem) params[NMAL6790Constant.IDX_03];
        if (ZYPCommonFunc.hasValue(param3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H1, param3);
        }

        // Location Number
        EZDBStringItem param4 = (EZDBStringItem) params[NMAL6790Constant.IDX_04];
        if (ZYPCommonFunc.hasValue(param4)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNum_H1, param4);
        }

        // Location Name
        EZDBStringItem param5 = (EZDBStringItem) params[NMAL6790Constant.IDX_05];
        if (ZYPCommonFunc.hasValue(param5)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsLocNm_H1, param5);
        }

        // First Name
        EZDBStringItem param6 = (EZDBStringItem) params[NMAL6790Constant.IDX_06];
        if (ZYPCommonFunc.hasValue(param6)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctacPsnFirstNm_H1, param6);
        }

        // Last Name
        EZDBStringItem param7 = (EZDBStringItem) params[NMAL6790Constant.IDX_07];
        if (ZYPCommonFunc.hasValue(param7)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctacPsnLastNm_H1, param7);
        }

        // Phone-Work
        EZDBStringItem param8 = (EZDBStringItem) params[NMAL6790Constant.IDX_08];
        if (ZYPCommonFunc.hasValue(param8)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCtacPntValTxt_H1, param8);
        }

        // E-Mail Address
        EZDBStringItem param9 = (EZDBStringItem) params[NMAL6790Constant.IDX_09];
        if (ZYPCommonFunc.hasValue(param9)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCtacPntValTxt_H2, param9);
        }

        // Title
        EZDBStringItem param10 = (EZDBStringItem) params[NMAL6790Constant.IDX_10];
        if (ZYPCommonFunc.hasValue(param10)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCtacPsnTtlCd_H1, param10);
        }

        // Department 
        EZDBStringItem param11 = (EZDBStringItem) params[NMAL6790Constant.IDX_11];
        if (ZYPCommonFunc.hasValue(param11)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCtacPsnDeptCd_H1, param11);
        }

        // Active
        EZDBStringItem param12 = (EZDBStringItem) params[NMAL6790Constant.IDX_12];
        if (ZYPCommonFunc.hasValue(param12)) {
            if (ZYPConstant.FLG_ON_Y.equals(param12.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H1, param12);
            }
        }

        // E-Mail
        EZDBStringItem param13 = (EZDBStringItem) params[NMAL6790Constant.IDX_13];
        if (ZYPCommonFunc.hasValue(param13)) {
            if (ZYPConstant.FLG_ON_Y.equals(param13.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H2, param13);
            }
        }

        // Serial Number 
        EZDBStringItem param14 = (EZDBStringItem) params[NMAL6790Constant.IDX_14];
        if (ZYPCommonFunc.hasValue(param14)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_H1, param14);
        }

        // Address
        EZDBStringItem param15 = (EZDBStringItem) params[NMAL6790Constant.IDX_15];
        if (ZYPCommonFunc.hasValue(param15)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyByItemNm_H1, param15);
        }

        // IB Contact Role
        // 2018/10/11 QC#27869 Del Start
        //EZDBStringItem param16 = (EZDBStringItem) params[NMAL6790Constant.IDX_16];
        //if (ZYPCommonFunc.hasValue(param16)) {
        //    ZYPEZDItemValueSetter.setValue(scrnMsg.svcCtacTpCd_H1, param16);
        //}
        // 2018/10/11 QC#27869 Del End
    }

}
