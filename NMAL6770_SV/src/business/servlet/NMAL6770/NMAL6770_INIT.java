/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6770;

import static business.servlet.NMAL6770.constant.NMAL6770Constant.BUSINESS_ID;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_01;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_02;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_03;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_04;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_05;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_06;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_07;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_08;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_09;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_10;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_11;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_12;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_20;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_21;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_22;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_23;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_24;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_25;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_26;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_27;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_28;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_29;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_30;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_31;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.SCREEN_ID;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6770.NMAL6770CMsg;
import business.servlet.NMAL6770.common.NMAL6770CommonLogic;
import business.servlet.NMAL6770.constant.NMAL6770Constant;

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
 * 2015/03/23   Fujitsu         M.Nakamura      Create          N/A
 * 2015/03/31   Fujitsu         S.Tsunaki       Update          N/A
 * 2015/10/01   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/16   Fujitsu         C.Tanaka        Update          QC#2041
 * 2017/08/10   Fujitsu         H.Nagashima     Update          QC#16452
 * 2017/08/31   Fujitsu         T.Aoi           Update          QC#20802
 * 2018/02/28   Fujitsu         A.Kosai         Update          QC#21075
 *</pre>
 */
public class NMAL6770_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6770BMsg scrnMsg = (NMAL6770BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        Object[] params = (Object[]) arg;

        if (arg instanceof Object[]) {
            setParams(params, scrnMsg);
        }
        scrnMsg.xxChkBox_H3.setValue(ZYPConstant.FLG_OFF_N);    //QC#16452 add

        NMAL6770CMsg bizMsg = new NMAL6770CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6770BMsg scrnMsg = (NMAL6770BMsg) bMsg;
        NMAL6770CMsg bizMsg = (NMAL6770CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6770CommonLogic.initialControlScreen(this, scrnMsg);

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        // QC#7781
        if (NMAL6770Constant.MULT_SEL_MODE_CD.equals(scrnMsg.xxModeCd_H1.getValue())) {
            tblColor.setAlternateRowsBG("B", scrnMsg.B);
        }
        
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.dsAcctNm_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL6770BMsg scrnMsg = (NMAL6770BMsg) bMsg;
        // Header
        scrnMsg.dsAcctNm_H1.setNameForMessage("Account Name");
        scrnMsg.dsAcctNum_H1.setNameForMessage("Account#");
        scrnMsg.locNum_H1.setNameForMessage("Location#");
        scrnMsg.dsLocNm_H1.setNameForMessage("Location Name");
        scrnMsg.ctacPsnFirstNm_H1.setNameForMessage("First Name");
        scrnMsg.ctacPsnLastNm_H1.setNameForMessage("Last Name");
        scrnMsg.dsCtacPntValTxt_H2.setNameForMessage("Email Address");
        scrnMsg.dsCtacPntValTxt_H1.setNameForMessage("Phone-Work");
        scrnMsg.billToCustCd_H1.setNameForMessage("Bill To Code");
        // 2018/02/28 S21_NA#21075 Add Start
        scrnMsg.shipToCustCd_H1.setNameForMessage("Ship To Code");
        // 2018/02/28 S21_NA#21075 Add End
    }

    private void setParams(Object[] params, NMAL6770BMsg scrnMsg) {
        // Input Parameter.
        // Type(CTAC_TP)
        if (params[IDX_01] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_01])) {
            EZDBStringItem param1 = (EZDBStringItem) params[IDX_01];
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctacTpCd_H1, param1);
        }

        // Account Name
        if (params[IDX_02] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_02])) {
            EZDBStringItem param2 = (EZDBStringItem) params[IDX_02];
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H1, param2);
        }

        // Account Number
        if (params[IDX_03] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_03])) {
            EZDBStringItem param3 = (EZDBStringItem) params[IDX_03];
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H1, param3);
        }

        // Location Number
        if (params[IDX_04] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_04])) {
            EZDBStringItem param4 = (EZDBStringItem) params[IDX_04];
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNum_H1, param4);
        }

        // Location Name
        if (params[IDX_05] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_05])) {
            EZDBStringItem param5 = (EZDBStringItem) params[IDX_05];
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsLocNm_H1, param5);
        }

        // First Name
        if (params[IDX_06] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_06])) {
            EZDBStringItem param6 = (EZDBStringItem) params[IDX_06];
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctacPsnFirstNm_H1, param6);
        }

        // Last Name
        if (params[IDX_07] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_07])) {
            EZDBStringItem param7 = (EZDBStringItem) params[IDX_07];
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctacPsnLastNm_H1, param7);
        }

        // Phone-Work
        if (params[IDX_08] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_08])) {
            EZDBStringItem param8 = (EZDBStringItem) params[IDX_08];
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCtacPntValTxt_H1, param8);
        }

        // Email
        if (params[IDX_09] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_09])) {
            EZDBStringItem param9 = (EZDBStringItem) params[IDX_09];
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCtacPntValTxt_H2, param9);
        }

        // Role
        if (params[IDX_10] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_10])) {
            EZDBStringItem param10 = (EZDBStringItem) params[IDX_10];
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCtacPsnTtlCd_H1, param10);
        }

        // Start 2015/03/31 S.Tsunaki Add
        // Active
        if (params[IDX_11] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_11])) {
            EZDBStringItem param11 = (EZDBStringItem) params[IDX_11];
            if (ZYPConstant.FLG_ON_Y.equals(param11.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H1, param11);
            }
        }

        // E-Mail
        if (params[IDX_12] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_12])) {
            EZDBStringItem param12 = (EZDBStringItem) params[IDX_12];
            if (ZYPConstant.FLG_ON_Y.equals(param12.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H2, param12);
            }
        }
        // End 2015/03/31 S.Tsunaki Add

        if (params.length > IDX_20 && params[IDX_20] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_20])) {
            EZDBStringItem param20 = (EZDBStringItem) params[IDX_20];
            ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd_H1, param20);
        }

        EZDBStringItem param = null;
        if (params.length > IDX_21 && params[IDX_21] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_21])) {
            param = (EZDBStringItem) params[IDX_21];
            if (ZYPConstant.FLG_ON_Y.equals(param.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(0).xxDplyCtrlFlg_I1, param);
                scrnMsg.dsAcctNum_H1.setInputProtected(true);
            }
        }

        if (params.length > IDX_22 && params[IDX_22] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_22])) {
            param = (EZDBStringItem) params[IDX_22];
            if (ZYPConstant.FLG_ON_Y.equals(param.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(1).xxDplyCtrlFlg_I1, param);
                scrnMsg.dsAcctNm_H1.setInputProtected(true);
            }
        }

        if (params.length > IDX_23 && params[IDX_23] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_23])) {
            param = (EZDBStringItem) params[IDX_23];
            if (ZYPConstant.FLG_ON_Y.equals(param.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(2).xxDplyCtrlFlg_I1, param);
                scrnMsg.locNum_H1.setInputProtected(true);
            }
        }

        if (params.length > IDX_24 && params[IDX_24] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_24])) {
            param = (EZDBStringItem) params[IDX_24];
            if (ZYPConstant.FLG_ON_Y.equals(param.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(3).xxDplyCtrlFlg_I1, param);
                scrnMsg.dsLocNm_H1.setInputProtected(true);
            }
        }

        if (params.length > IDX_25 && params[IDX_25] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_25])) {
            param = (EZDBStringItem) params[IDX_25];
            if (ZYPConstant.FLG_ON_Y.equals(param.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(4).xxDplyCtrlFlg_I1, param);
                scrnMsg.billToCustCd_H1.setInputProtected(true);
            }
        }

        if (params.length > IDX_26 && params[IDX_26] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_26])) {
            param = (EZDBStringItem) params[IDX_26];
            if (ZYPConstant.FLG_ON_Y.equals(param.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(5).xxDplyCtrlFlg_I1, param);
                scrnMsg.xxChkBox_H1.setInputProtected(true);
            }
        }

        if (params.length > IDX_27 && params[IDX_27] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_27])) {
            param = (EZDBStringItem) params[IDX_27];
            if (ZYPConstant.FLG_ON_Y.equals(param.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(6).xxDplyCtrlFlg_I1, param);
                scrnMsg.xxChkBox_H2.setInputProtected(true);
            }
        }

        if (params.length > IDX_28 && params[IDX_28] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_28])) {
            param = (EZDBStringItem) params[IDX_28];
            if (ZYPConstant.FLG_ON_Y.equals(param.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(7).xxDplyCtrlFlg_I1, param);
                scrnMsg.dsCtacPsnTtlCd_H1.setInputProtected(true);
            }
        }
        
        // QC#7781
        if (params.length > IDX_29 && params[IDX_29] != null) {
            param = (EZDBStringItem) params[IDX_29];
            if (ZYPCommonFunc.hasValue(param)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_H1, param.getValue());
            }
        }
        
        if (params.length > IDX_30 && params[IDX_30] != null) {
            // get distinct values
            List<String> list = new ArrayList<String>();
            EZDBBigDecimalItem[] paramArray = (EZDBBigDecimalItem[]) params[IDX_30];
            for (int i = 0; i < paramArray.length; i++) {
                if (ZYPCommonFunc.hasValue(paramArray[i])) {
                    String ctacPsnPk = paramArray[i].getValue().toPlainString();
                    if(!list.contains(ctacPsnPk)){
                        list.add(ctacPsnPk);
                    }
                }
            }
            // set values to bottom list
            for (int i = 0; i < list.size(); i++) {
                String ctacPsnPk = list.get(i);
                if (i < scrnMsg.B.length()) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).ctacPsnPk_B1, new BigDecimal(ctacPsnPk));
                    scrnMsg.B.setValidCount(i + 1);
                } else {
                    break;
                }
            }
        }

        // QC#16452 add Start
        // Disable Include Location
        if (params.length > IDX_31 && params[IDX_31] != null && ZYPCommonFunc.hasValue((EZDBStringItem) params[IDX_31])) {
            param = (EZDBStringItem) params[IDX_31];
            if (ZYPConstant.FLG_ON_Y.equals(param.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(8).xxDplyCtrlFlg_I1, param);
                // QC#20802 Del Start
                //scrnMsg.xxChkBox_H3.setInputProtected(true);
                // QC#20802 Del End
            }
        }
        // QC#16452 add End
    }
}
