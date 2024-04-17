/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLBL3000.common;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NLBL3000.NLBL3000CMsg;
import business.servlet.NLBL3000.NLBL3000BMsg;
import business.servlet.NLBL3000.NLBL3000_SBMsgArray;
import business.servlet.NLBL3000.constant.NLBL3000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 *  Serial Number Entry PopUp
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2012   Fujitsu         T.Ishii         Create          N/A
 * 11/27/2012   Fujitsu         Y.Taoka         Update          
 * 02/12/2012   Fujitsu         F.Saito         Update          WDS Defect#567
 * 07/26/2013   Fujitsu         M.Nakamura      Update          R-OM031
 * 11/28/2015   CSAI            Y.Imazu         Update          CSA
 *</pre>
 */
public class NLBL3000CommonLogic implements NLBL3000Constant {

    /**
     * Initialize Display Information
     * <p>
     * init display information
     * </p>
     * @param handler EZDCommonHandler
     * @param bMsg EZDBMsg
     */
    public static void initDisplayInfo(EZDCommonHandler handler, EZDBMsg bMsg) {

        NLBL3000BMsg scrnMsg = (NLBL3000BMsg) bMsg;

        // Initial Common Button
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);

        // Initial Other display Info
        setScreenItemCondition(handler, scrnMsg);
    }

    /**
     * @return bizMsg NLBL3000CMsg
     */
    public static NLBL3000CMsg setRequestData_NLBL3000Scrn00_Search() {

        NLBL3000CMsg bizMsg = new NLBL3000CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);

        return bizMsg;
    }

    /**
     * Execute EZ Default Input Check
     * @param bMsg EZDBMsg
     */
    public static void ezCheck(EZDBMsg bMsg) {

        NLBL3000BMsg scrnMsg = (NLBL3000BMsg) bMsg;

        // 07/26/2013 R-OM031 Del Start
        // scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);
        // scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_A1);
        // 07/26/2013 R-OM031 Del End

        for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.S.no(i).serNum);
        }

        scrnMsg.putErrorScreen();
    }

    /* 11/28/2015 CSAI Y.Imazu Add CSA START */
    /**
     * Check if same value.
     * @param val1 EZDBStringItem
     * @param val2 EZDBStringItem
     * @return boolean
     */
    public static boolean isSameVal(EZDBStringItem val1, EZDBStringItem val2) {

        if (ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2) && val1.getValue().equals(val2.getValue())) {

            return true;

        } else if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return true;
        }

        return false;
    }
    /* 11/28/2015 CSAI Y.Imazu Add CSA END */

    /**
     * Set table BackGround Color(White/Gray)
     * @param bMsg EZDBMsg
     */
    public static void setTableBGColor(EZDBMsg bMsg) {

        NLBL3000BMsg scrnMsg = (NLBL3000BMsg) bMsg;

        // 07/26/2013 R-OM031 Del Start
        // S21TableColorController lineTblColorA = new S21TableColorController(SCRN_ID, scrnMsg);
        // lineTblColorA.setAlternateRowsBG(TABLE_ID_A, scrnMsg.A);
        // 07/26/2013 R-OM031 Del End

        S21TableColorController lineTblColorS = new S21TableColorController(SCRN_ID, scrnMsg);
        lineTblColorS.setAlternateRowsBG(TABLE_ID_S, scrnMsg.S);
    }

    /**
     * @param handler
     * @param bMsg
     */
    private static void setScreenItemCondition(EZDCommonHandler handler, EZDBMsg bMsg) {

        NLBL3000BMsg scrnMsg = (NLBL3000BMsg) bMsg;

        // 07/26/2013 R-OM031 Mod Start
        // scrnMsg.mdseCd_D1.setInputProtected(true);
        scrnMsg.mdseCd_H1.setInputProtected(true);
        // 07/26/2013 R-OM031 Mod End
        /* 11/28/2015 CSAI Y.Imazu Add CSA START */
        scrnMsg.xxHdrNum_H1.setInputProtected(true);
        scrnMsg.rtlWhCd_H1.setInputProtected(true);
        scrnMsg.rtlWhNm_H1.setInputProtected(true);
        /* 11/28/2015 CSAI Y.Imazu Add CSA END */

        boolean readOnly = !SCR_EDT_TP_EDIT.equals(scrnMsg.xxScrEdtTpCd_H1.getValue());

        // 07/26/2013 R-OM031 Mod Start
        // boolean assignable = scrnMsg.S.getValidCount() > 0 && scrnMsg.A.getValidCount() > 0;
        boolean assignable = scrnMsg.S.getValidCount() > 0;
        // 07/26/2013 R-OM031 Mod End

        handler.setButtonEnabled(BTN_ASSIGN, !readOnly & assignable);
        handler.setButtonEnabled(BTN_CMN_CLEAR[0], !readOnly);

        // Serial # List
        for (int option = 0; option < scrnMsg.S.getValidCount(); option++) {

            boolean allowInputSerial = ZYPConstant.FLG_ON_Y.equals(scrnMsg.S.no(option).xxEdtModeFlg.getValue());
            scrnMsg.S.no(option).setInputProtected(!allowInputSerial | readOnly);

            // 07/26/2013 R-OM031 Del Start
            // EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID, NLBL3000Bean.xxRadioBtn + "#" + String.valueOf(option));
            // guiAttr.setVisibility(allowInputSerial & !readOnly);
            // scrnMsg.addGUIAttribute(guiAttr);
            // 07/26/2013 R-OM031 Del End

            /* 11/28/2015 CSAI Y.Imazu Add CSA START */
            scrnMsg.S.no(option).xxHdrNum.setInputProtected(true);
            scrnMsg.S.no(option).xxDplyTrxNumTxt.setInputProtected(true);
            scrnMsg.S.no(option).rtlSwhCd.setInputProtected(true);
            scrnMsg.S.no(option).mdseCd.setInputProtected(true);
            scrnMsg.S.no(option).svcConfigMstrPk.setInputProtected(true);
            /* 11/28/2015 CSAI Y.Imazu Add CSA END */
        }

        // 07/26/2013 R-OM031 Del Start
        // Serial Master List
        // for (int option = 0; option < scrnMsg.A.getValidCount(); option++) {
        //     NLBL3000_ABMsg serialMaster = scrnMsg.A.no(option);
        //     serialMaster.setInputProtected(readOnly);
        //     EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID, NLBL3000Bean.xxRadioBtn_A1 + "#" + String.valueOf(option));
        //     guiAttr.setVisibility(!readOnly);
        //     scrnMsg.addGUIAttribute(guiAttr);
        // }
        // 07/26/2013 R-OM031 Del End
    }

    /**
     * set input parameter
     * @param scrnMsg NLBL3000BMsg
     * @param arg Object[]
     */
    public static void setInputParam(NLBL3000BMsg scrnMsg, Object[] arg) {

        /* 11/28/2015 CSAI Y.Imazu Add CSA STRAT */
        boolean isCheckOk = true;
        /* 11/28/2015 CSAI Y.Imazu Add CSA END */

        if (!(arg instanceof Object[])) {

            /* 11/28/2015 CSAI Y.Imazu Mod CSA START */
            isCheckOk = false;
        // }

        // if (arg.length < MAX_INPUT_PARAM_NUM) {
        } else if (arg.length < MAX_INPUT_PARAM_NUM) {

            isCheckOk = false;
        // }
        } else {
        /* 11/28/2015 CSAI Y.Imazu Mod CSA END */

            // 0.Suffix
            String suffix = null;

            if (arg[INPUT_PARAM_SUFFIX] instanceof String) {

                suffix = (String) arg[INPUT_PARAM_SUFFIX];
            }

            // 1.Header#
            if (arg[INPUT_PARAM_XX_HDR_NUM] instanceof EZDBStringItem) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNum_H1, (EZDBStringItem) arg[INPUT_PARAM_XX_HDR_NUM]);
            }

            /* 11/28/2015 CSAI Y.Imazu Del CSA START */
//            // 2.Merchandise Code
//            if (arg[INPUT_PARAM_MDSE_CD] instanceof EZDBStringItem) {
//
//                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_H1, (EZDBStringItem) arg[INPUT_PARAM_MDSE_CD]);
//            }
//
//            // 3.Merchandise Name
//            if (arg[INPUT_PARAM_MDSE_NM] instanceof EZDBStringItem) {
//
//                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseNm_H1, (EZDBStringItem) arg[INPUT_PARAM_MDSE_NM]);
//            }
            /* 11/28/2015 CSAI Y.Imazu Del CSA END */

            // 07/26/2013 R-OM031 Del Start
            //4.Machine Master Status Code
//            if (arg[INPUT_PARAM_MACH_MSTR_STS_CD] instanceof String[]) {
//
//                String[] machMstrStsCdArray = (String[]) arg[INPUT_PARAM_MACH_MSTR_STS_CD];
//                int index = 0;
//                for (String machMstrStsCd : machMstrStsCdArray) {
//                scrnMsg.serMstrStsCd_H1.no(index++).setValue(machMstrStsCd);
//                }
//            }
            // 07/26/2013 R-OM031 Del End

            // 4.Qty
            if (arg[INPUT_PARAM_ORD_QTY] instanceof EZDBBigDecimalItem) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.ordQty_H1, (EZDBBigDecimalItem) arg[INPUT_PARAM_ORD_QTY]);

                /* 11/28/2015 CSAI Y.Imazu Add CSA START */
                if (!ZYPCommonFunc.hasValue(scrnMsg.ordQty_H1)) {

                    isCheckOk = false;
                }
                /* 11/28/2015 CSAI Y.Imazu Add CSA END */
            }

            // 5.Mode
            if (arg[INPUT_PARAM_XX_SCR_EDT_TP_CD] instanceof String) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEdtTpCd_H1, (String) arg[INPUT_PARAM_XX_SCR_EDT_TP_CD]);

                /* 11/28/2015 CSAI Y.Imazu Add CSA START */
                if (!ZYPCommonFunc.hasValue(scrnMsg.xxScrEdtTpCd_H1)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEdtTpCd_H1, SCR_EDT_TP_READ);
                }
                /* 11/28/2015 CSAI Y.Imazu Add CSA END */
            }

            /* 11/28/2015 CSAI Y.Imazu Del CSA START */
//            // 6.Transaction Line Number
//            if (arg[INPUT_PARAM_TRX_LINE_NUM] instanceof EZDBStringItem) {
//
//                ZYPEZDItemValueSetter.setValue(scrnMsg.trxLineNum_H1, (EZDBStringItem) arg[INPUT_PARAM_TRX_LINE_NUM]);
//            }
//
//            // 7.Transaction Line Sub Number
//            if (arg[INPUT_PARAM_TRX_LINE_SUB_NUM] instanceof EZDBStringItem) {
//
//                ZYPEZDItemValueSetter.setValue(scrnMsg.trxLineSubNum_H1, (EZDBStringItem) arg[INPUT_PARAM_TRX_LINE_SUB_NUM]);
//            }
            /* 11/28/2015 CSAI Y.Imazu Del CSA END */

            // 8.Serial Number List
            if (arg[INPUT_PARAM_SERIAL] instanceof EZDMsgArray) {

                EZDMsg.copy((EZDMsgArray) arg[INPUT_PARAM_SERIAL], (suffix == null) ? "" : suffix, scrnMsg.S, "");

                /* 11/28/2015 CSAI Y.Imazu Add CSA START */
                for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {

                    if (!ZYPCommonFunc.hasValue(scrnMsg.S.no(i).mdseCd)) {

                        scrnMsg.S.no(i).mdseCd.setErrorInfo(1, NLAM0255E);
                        isCheckOk = false;
                    }

//Start 2016/8/8 QC#13125
//                    if (!ZYPCommonFunc.hasValue(scrnMsg.S.no(i).rtlSwhCd)) {
//
//                        scrnMsg.S.no(i).rtlSwhCd.setErrorInfo(1, NLAM0255E);
//                        isCheckOk = false;
//                    }
//End 2016/8/8 QC#13125
                }
                /* 11/28/2015 CSAI Y.Imazu Add CSA END */
            }

            /* 11/28/2015 CSAI Y.Imazu Add CSA START */
            // 9.Retail Warehouse Code
            if (arg[INPUT_PARAM_RTL_WH_CD] instanceof EZDBStringItem) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H1, (EZDBStringItem) arg[INPUT_PARAM_RTL_WH_CD]);

                if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H1)) {

                    scrnMsg.rtlWhCd_H1.setErrorInfo(1, NLAM0255E);
                    isCheckOk = false;
                }
            }

            // 10.Retail Warehouse Name
            if (arg[INPUT_PARAM_RTL_WH_NM] instanceof EZDBStringItem) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, (EZDBStringItem) arg[INPUT_PARAM_RTL_WH_NM]);
            }

            // 11.Inbound Outbound Code
            if (arg[INPUT_PARAM_INBD_OTBD_CD] instanceof String) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.inbdOtbdCd_H1, (String) arg[INPUT_PARAM_INBD_OTBD_CD]);

                if (!ZYPCommonFunc.hasValue(scrnMsg.inbdOtbdCd_H1)) {

                    isCheckOk = false;
                }
            }
            /* 11/28/2015 CSAI Y.Imazu Add CSA END */

            // 11/27/2012 ADD START
            // Set Original Mdse and Serial#
            for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).mdseCd_S1, scrnMsg.S.no(i).mdseCd);
                ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).serNum_S1, scrnMsg.S.no(i).serNum);
            }
            // 11/27/2012 ADD END

        /* 11/28/2015 CSAI Y.Imazu Add CSA START */
        }

        if (isCheckOk && !INBD_OTBD.INBOUND.equals(scrnMsg.inbdOtbdCd_H1.getValue()) && !INBD_OTBD.OUTBOUND.equals(scrnMsg.inbdOtbdCd_H1.getValue())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEdtTpCd_H1, SCR_EDT_TP_READ);
            scrnMsg.setMessageInfo(NLAM1131E, new String[]{"Inbound Outbound Code", scrnMsg.inbdOtbdCd_H1.getValue()});

        } else if (!isCheckOk) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEdtTpCd_H1, SCR_EDT_TP_READ);
            scrnMsg.setMessageInfo(NLAM0255E);
        }
        /* 11/28/2015 CSAI Y.Imazu Add CSA END */
    }

    /**
     * prepare screen item
     * @param scrnMsg NLBL3000BMsg
     */
    public static void prepareScreenItem(NLBL3000BMsg scrnMsg) {

        // 07/26/2013 R-OM031 Del Start
        // MDSE
        // ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_D1, scrnMsg.mdseCd_H1);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.mdseNm_D1, scrnMsg.mdseNm_H1);
        // 07/26/2013 R-OM031 Del End

        // Serial List
        int qty = scrnMsg.ordQty_H1.getValueInt();
        int validCount;

        if (qty <= scrnMsg.S.length() && qty >= scrnMsg.S.getValidCount()) {

            validCount = qty;

        // 07/26/2013 R-OM031 Add Start
        } else if (qty > scrnMsg.S.length()) {

            validCount = scrnMsg.S.length();
        // 07/26/2013 R-OM031 Add End

        } else {

            // Edit Start 02/12/2013 Defect#567
            // validCount = scrnMsg.S.length();
            validCount = scrnMsg.S.getValidCount();
            // Edit End 02/12/2013 Defect#567
        }

        /* 11/28/2015 CSAI Y.Imazu Del CSA START */
        // int startIndex = scrnMsg.S.getValidCount();

        // for (int i = startIndex; i < validCount; i++) {

        //     ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).xxHdrNum, scrnMsg.xxHdrNum_H1);
        //     ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).mdseCd, scrnMsg.mdseCd_H1);
        //     scrnMsg.S.no(i).xxEdtModeFlg.setValue(ZYPConstant.FLG_ON_Y);
        //     scrnMsg.S.no(i).xxDplyTrxNumTxt.setValue(getAltTrxNum(scrnMsg.trxLineNum_H1, scrnMsg.trxLineSubNum_H1));
        // }
        /* 11/28/2015 CSAI Y.Imazu Del CSA END */
        /* 11/28/2015 CSAI Y.Imazu Add CSA START */
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_H1, ZYPConstant.FLG_OFF_N);
        /* 11/28/2015 CSAI Y.Imazu Add CSA END */

        scrnMsg.S.setValidCount(validCount);
    }

    /**
     * set output parameter
     * @param arg Object[]
     * @param resultArray NLBL3000_SBMsgArray
     */
    public static void setOutputParam(Object[] arg, NLBL3000_SBMsgArray resultArray) {

        if (!(arg instanceof Object[])) {

            return;
        }

        if (arg.length < MAX_INPUT_PARAM_NUM) {

            return;
        }

        String suffix = null;

        // 0.Suffix
        if (arg[INPUT_PARAM_SUFFIX] instanceof String) {

            suffix = (String) arg[INPUT_PARAM_SUFFIX];
        }

        if (arg[INPUT_PARAM_SERIAL] instanceof EZDMsgArray) {

            EZDMsg.copy(resultArray, "", (EZDMsgArray) arg[INPUT_PARAM_SERIAL], (suffix == null) ? "" : suffix);
        }
    }

    // 07/26/2013 R-OM031 Del Start
//    /**
//     * select serial list for initialize
//     * @param scrnMsg NLBL3000BMsg
//     */
//    public static void setInitialSerialSelection(NLBL3000BMsg scrnMsg) {
//
//        boolean readOnly = !SCR_EDT_TP_EDIT.equals(scrnMsg.xxScrEdtTpCd_H1.getValue());
//        if (readOnly) {
//            return;
//        }
//
//        // Serial # List
//        for (int option = 0; option < scrnMsg.S.getValidCount(); option++) {
//
//            NLBL3000_SBMsg serial = scrnMsg.S.no(option);
//
//            boolean allowInputSerial = ZYPConstant.FLG_ON_Y.equals(serial.xxEdtModeFlg.getValue());
//            if (allowInputSerial) {
//                scrnMsg.xxRadioBtn.setValue(option);
//                break;
//            }
//        }
//    }
//
//    /**
//     * select master list for initialize
//     * @param scrnMsg NLBL3000BMsg
//     */
//    public static void setInitialMasterSelection(NLBL3000BMsg scrnMsg) {
//
//        boolean readOnly = !SCR_EDT_TP_EDIT.equals(scrnMsg.xxScrEdtTpCd_H1.getValue());
//        if (readOnly) {
//            return;
//        }
//        // Serial Master List
//        for (int option = 0; option < scrnMsg.A.getValidCount(); option++) {
//            scrnMsg.xxRadioBtn_A1.setValue(option);
//            break;
//        }
//    }
//
//    /**
//     * is assign enabled
//     * @param scrnMsg NLBL3000BMsg
//     * @param selectedIndex int
//     * @return true:asignable, false:unasignable
//     */
//    public static boolean isAssignEnabled(NLBL3000BMsg scrnMsg, int selectedIndex) {
//
//        boolean readOnly = !SCR_EDT_TP_EDIT.equals(scrnMsg.xxScrEdtTpCd_H1.getValue());
//        if (readOnly) {
//            return false;
//        }
//
//        if (selectedIndex < 0) {
//            return false;
//        }
//
//        if (selectedIndex >= scrnMsg.S.getValidCount()) {
//            return false;
//        }
//
//        // Serial # List
//        NLBL3000_SBMsg serial = scrnMsg.S.no(selectedIndex);
//
//        return ZYPConstant.FLG_ON_Y.equals(serial.xxEdtModeFlg.getValue());
//    }
    // 07/26/2013 R-OM031 Del End

    /* 11/28/2015 CSAI Y.Imazu Del CSA START */
//    /**
//     * get transaction number alternative.
//     * @param trxLineNum_H1 EZDBStringItem
//     * @param trxLineSubNum_H1 EZDBStringItem
//     * @return Transaction Number
//     */
//    private static String getAltTrxNum(EZDBStringItem trxLineNum, EZDBStringItem trxLineSubNum) {
//
//        if (!ZYPCommonFunc.hasValue(trxLineNum) && !ZYPCommonFunc.hasValue(trxLineSubNum)) {
//            return "";
//        }
//
//        if (!ZYPCommonFunc.hasValue(trxLineSubNum)) {
//            return trxLineNum.getValue();
//        }
//
//        if (!ZYPCommonFunc.hasValue(trxLineNum)) {
//            return trxLineSubNum.getValue();
//        }
//
//        return String.format("%s,%s", trxLineNum.getValue(), trxLineSubNum.getValue());
//    }
    /* 11/28/2015 CSAI Y.Imazu Del CSA END */

    // 07/26/2013 R-OM031 Del Start
//    /**
//     * existsSerialNumberAtSerialAsignList
//     * @param scrnMsg NLBL3000BMsg
//     * @param masterIndex int
//     * @param asignIndex int
//     * @return true:serial number exists, false:serial number not
//     * exists
//     */
//    public static boolean existsSerialNumberAtSerialAsignList(NLBL3000BMsg scrnMsg, int masterIndex, int asignIndex) {
//
//        String masterSerNum = scrnMsg.A.no(masterIndex).serNum_A1.getValue();
//
//        for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {
//            if (i != asignIndex) {
//                if (masterSerNum.equals(scrnMsg.S.no(i).serNum.getValue())) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
    // 07/26/2013 R-OM031 Del End
}
